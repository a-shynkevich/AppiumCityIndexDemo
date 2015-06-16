package com.cityindex.screen;

import com.cityindex.exception.TestException;
import com.cityindex.manager.TestManager;
import com.cityindex.utils.LanguageChecker;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.cityindex.utils.LoggerUtils.e;
import static com.cityindex.utils.LoggerUtils.i;

public class Login extends Screen{
    private WebElement element;

    public Login(AppiumDriver driver) {
        super(driver);
    }

    public void signIn() throws TestException {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        By xpathRequestError = By.xpath("//UIAApplication[1]/UIAWindow[4]/UIAAlert[1]/UIACollectionView[1]/UIACollectionCell[1]/UIAButton[1]");
        By xpathForLoginBtnInMarkets = By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[2]");
        By xpathLoginField = By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]");
        By xpathPasswordField = By.xpath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]");
        By xpathLoginBtn = By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAButton[1]");
        By xpathContinueBtn = By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAButton[1]");
        By xpathCloseBtn = By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAButton[1]");
        By xpathTabBar = By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATabBar[1]");


        if (testHelper.waitWhileElementExist(xpathRequestError, 5000)){
            element = driver.findElement(xpathRequestError);
            element.click();
        }
        if(!testHelper.waitWhileElementExist(xpathForLoginBtnInMarkets, 15000)){
            TestManager.retestTest("Login button in markets was not found");
        }
        element = driver.findElement(xpathForLoginBtnInMarkets);
//        element = driver.findElement(By.name("test"));
//        element.get
        String loginBtnText = element.getText();
        i("LOGIN BUTTON LANGUAGE:   " + loginBtnText);
        if(!new LanguageChecker().checkLanguage(loginBtnText, LanguageChecker.Language.ARABIC))
            TestManager.failTest("Wrong language displayed");
            element.click();
        i("Click on Login btn");
        if(!testHelper.waitWhileElementExist(xpathLoginField, 15000))
            e("Login field was not found");
        String login = "dm348630";
        String pass = "password";
        // Enter Login
        element = driver.findElement(xpathLoginField);
        MobileElement mobileElement = (MobileElement) element;
        mobileElement.setValue(login);
        i("Enter login " + login);
        // Enter Pasword
        element = driver.findElement(xpathPasswordField);
        mobileElement = (MobileElement) element;
        mobileElement.setValue(pass);
        i("Enter password " + pass);
        //Click Login button
        element = driver.findElement(xpathLoginBtn);
        element.click();
        i("Click on Login btn");
        //Wait warning screen and click on Continue button
        if (!testHelper.waitWhileElementExist(xpathContinueBtn, 20000))
            e("Continue btn was not found");
        element = driver.findElement(xpathContinueBtn);
        element.click();
        i("Click on continue button");
        //Wait Tutorial and click close button
        if (!testHelper.waitWhileElementExist(xpathCloseBtn, 15000))
            e("Close button was not found");
        element = driver.findElement(xpathCloseBtn);
        element.click();
        i("Click on Close button");
        // wait tab bar
        if (testHelper.waitWhileElementExist(xpathTabBar, 15000))
            i("LOGIN PASSED");
        else TestManager.failTest("LOGIN FAILED");
    }
}
