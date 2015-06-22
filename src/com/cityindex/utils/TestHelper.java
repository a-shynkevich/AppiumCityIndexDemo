package com.cityindex.utils;

import com.cityindex.exception.TestException;
import com.cityindex.manager.TestManager;
import com.cityindex.param.ConfigParam;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;

import static com.cityindex.utils.LoggerUtil.i;

public class TestHelper {

    protected static final int SWIPE_LEFT = -1;
    protected static final int SWIPE_RIGHT = 0;
    protected static final int SWIPE_DOWN = 1;
    protected static final int SWIPE_UP = 2;
    protected AppiumDriver driver;
    protected TestManager testManager;
    private ConfigManager configManager;
    private Process process;

    public TestHelper (TestManager testManager) {
        this.testManager = testManager;
        this.driver = testManager.getDriver();

    }

    //For implementation common methods to use appium driver

    private void launchServer() {
        new Thread(new Runnable() {
            public void run() {
                String[] commands = new String[]{
                        "/usr/local/bin/appium",
                        "-U",
                        configManager.getProperty(ConfigParam.IOS_DEVICE_ID.name())
                };

                try {
                    process = Runtime.getRuntime().exec(commands);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public WebElement findElement (By byElement, boolean isElementDisplayed){
        WebElement element = null;
        try {
            element = driver.findElement(byElement);
        }catch (Exception e) {
            return null;
        }
        if(!element.isEnabled()){
            return null;
        }
        if(isElementDisplayed) {
            if (!element.isDisplayed()) {
                return null;
            }
        }
        return element;
    }

    public List <WebElement> findElements (By byElement){
        List<WebElement> elements = null;
        try {
            elements = driver.findElements(byElement);
        }catch (Exception e) {
            return null;
        }
        return elements;
    }

    public boolean isElementPresent(By by){
        try {
            WebElement element = driver.findElement(by);
            if(element.isEnabled()) {
                return true;
            }
        }catch (Exception e){
            log("element by" + by.toString() + "is not found.");
            return false;
        }
        return false;
    }

    public boolean waitWhileElementExist(By by, long waitTimeMs){

        long startTime = System.currentTimeMillis();

        while (findElement(by, true) == null){
            if (System.currentTimeMillis() - startTime > 1000) {
                log("wait while element by "+by.toString() + " is present.");
            }
            if (System.currentTimeMillis() - startTime >= waitTimeMs){
                return false;
            }
        }
        return true;
    }

    protected WebElement waitForElement(final By by, long timeMs, boolean isElementDisplayed) {
        long waiterStartTime = System.currentTimeMillis();
        long startTime = 0;

        WebElement element = findElement(by, isElementDisplayed);
        while (element == null){
            if (System.currentTimeMillis() - startTime > 1000) {
                log("wait element by: \"" + by.toString() + "\".");
                startTime = System.currentTimeMillis();
            }
            if (System.currentTimeMillis() - waiterStartTime >= timeMs){
                log("element by: \"" + by.toString() + "\" was not found.");
                return null;
            }
            element = findElement(by, isElementDisplayed);
        }

        return element;
    }

    protected boolean waitChildOfElement(By byParent, By byChild, long waitTimeMS){
        WebElement element = findElement(byParent, false);
        List<WebElement> elementList = element.findElements(byChild);

        long waiterStartTime = System.currentTimeMillis();
        long startTime = 0;
        while(elementList.size() <= 0){
            element = findElement(byParent, false);
            elementList = element.findElements(byChild);
            if (System.currentTimeMillis() - startTime > 1000) {
                log("wait child of: \"" + element.toString() + "\".");
                startTime = System.currentTimeMillis();
            }
            if (System.currentTimeMillis() - waiterStartTime >= waitTimeMS){
                log("child of: \"" + element.toString() + "\" were not found.");
                return false;
            }
        }return true;
    }

    protected WebElement waitForElement(final By firstBy, final By secondBy, long timeMs, boolean isElementDisplayed) {
        long waiterStartTime = System.currentTimeMillis();
        long startTime = 0;

        WebElement element = findElement(firstBy, isElementDisplayed);
        WebElement element1 = findElement(secondBy, isElementDisplayed);

        while (element == null && element1 == null){
            if (System.currentTimeMillis() - startTime > 1000) {
                log("wait elements by: \"" + firstBy.toString() + "\" or +" +secondBy.toString() +".");
                startTime = System.currentTimeMillis();
            }
            if (System.currentTimeMillis() - waiterStartTime >= timeMs){
                log("elements by: \"" + firstBy.toString() + "\"" + secondBy.toString() + "was not found.");
                return null;
            }
            element = findElement(firstBy, isElementDisplayed);
            element1 = findElement(secondBy, isElementDisplayed);
        }
        if(element != null){
            return element;
        }else
        if(element1 != null){
            return element1;
        }
        return null;
    }

    protected WebElement waitForElement(final By by, int index, long timeMs, boolean isElementDisplayed) {
        WebDriverWait wait = new WebDriverWait(driver, timeMs/1000);
        WebElement element;
        try {
            List<WebElement> elements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
            element = elements.get(index);
            if (isElementDisplayed){
                if (!element.isDisplayed()) {
                    return null;
                }
            }
        }catch (Exception e){
            log("element by: \"" + by.toString() + "\" was not found.");
            return null;
        }return element;
    }

    protected WebElement waitElementByLabel(By className, String label, long waitTimeMS){
        WebElement element = null;
        List<WebElement> elementList;

        long waiterStartTime = System.currentTimeMillis();
        long startTime = 0;

        while(element == null){
            elementList = findElements(className);
            if(elementList.size() > 0){
                for (int i = 0; i < elementList.size(); i++){
//                    log(i +" Element "+ elementList.get(i).toString());
                    if(elementList.get(i).getAttribute("label").equals(label)){
                        element = elementList.get(i);
                    }
                }
            }
            if (System.currentTimeMillis() - startTime > 1000) {
                log("wait child by label: \"" + label + "\".");
                startTime = System.currentTimeMillis();
            }
            if (System.currentTimeMillis() - waiterStartTime >= waitTimeMS){
                log("child by: \"" + label + "\" was not found.");
                return null;
            }
        }if (element.isDisplayed()) {
            return element;
        }else return null;
    }

    protected WebElement getWebElement(By by){
        WebElement webElement = null;
        try {
            webElement = driver.findElement(by);
        } catch (Exception ex) {
            webElement = null;
        }
        return webElement;
    }

    protected void sleep(long ms){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected void log(String message) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss:SSS");
        System.out.println(simpleDateFormat.format(System.currentTimeMillis()) + " " + message);
    }

    protected Point getCenter(WebElement element) {

        Point upperLeft = element.getLocation();
        Dimension dimensions = element.getSize();
        return new Point(upperLeft.getX() + dimensions.getWidth()/2, upperLeft.getY() + dimensions.getHeight()/2);
    }

    public boolean swipe(WebElement webElement, int swipeSide){
        return swipe(webElement, swipeSide, 0);
    }

    public boolean swipe(WebElement webElement, int swipeSide, int distance) {
        if (webElement == null || !webElement.isDisplayed())
            return false;
        int width = webElement.getSize().getWidth();
        int height = webElement.getSize().getHeight();
        int startPoint;

        Point point = webElement.getLocation();
        int x = point.getX();
        int y = point.getY();

        switch (swipeSide) {
            case SWIPE_LEFT:
                startPoint = x+ width - 10;
                if (distance == 0)
                    distance = x+ width - width + 10;
                else
                    distance = startPoint - distance;
                driver.swipe(startPoint, height/2, distance, height/2, 1000);
                break;
            case SWIPE_RIGHT:
                startPoint = x+ 10;
                if (distance == 0)
                    distance = x + width - 10;
                else
                    distance = startPoint + distance;
                driver.swipe(startPoint, height/2, distance, height/2, 1000);
                break;
            case SWIPE_UP:
                startPoint = y + height-10;
                if (distance == 0)
                    distance = y + 10;
                else
                    distance = startPoint -distance;
                driver.swipe(width/2, startPoint, width/2, distance, 1000);
                break;
            case SWIPE_DOWN:
                startPoint = y + 10;
                if (distance == 0)
                    distance = y+ height - 10;
                else
                    distance = startPoint + distance;
                driver.swipe(width/2, startPoint, width/2, distance, 1000);
                break;
        }
        return true;
    }

    /*** comparing 2 images
     *  method arguments should be string value of the file path
     *  that should consist of /data/local/tmp/filename.png
     *  where filename.png is the name of your screenshot image
     ***/
    public boolean compareTwoImages(String img1path, String img2path) throws TestException {
        if(!new File(img1path).exists() || !new File(img2path).exists())
            testManager.retest("Some of screenshots not found:\n" +
                    img1path + "\n" +
                    img2path);
        Image image1 = Toolkit.getDefaultToolkit().getImage(img1path);
        Image image2 = Toolkit.getDefaultToolkit().getImage(img2path);

        try {
            PixelGrabber grab1 = new PixelGrabber(image1, 0, 0, -1, -1, false);
            PixelGrabber grab2 = new PixelGrabber(image2, 0, 0, -1, -1, false);
            int[] data1 = null;

            if (grab1.grabPixels()) {
                int width = grab1.getWidth();
                int height = grab1.getHeight();
                data1 = new int[width * height];
                data1 = (int[]) grab1.getPixels();
            }

            int[] data2 = null;

            if (grab2.grabPixels()) {
                int width = grab2.getWidth();
                int height = grab2.getHeight();
                data2 = new int[width * height];
                data2 = (int[]) grab2.getPixels();
            }
            i("Pixels equal: " + java.util.Arrays.equals(data1, data2));
            return java.util.Arrays.equals(data1, data2);

        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        throw new TestException("Error in compare image", driver).retest();
    }

    public static String getRandomString(int length) {
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder( length );
        for(int i = 0; i < length; i++) {
            sb.append(Constants.AB.charAt(rnd.nextInt(Constants.AB.length())));
        }
        return sb.toString();
    }
}

