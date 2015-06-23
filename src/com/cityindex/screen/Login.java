package com.cityindex.screen;

import com.cityindex.exception.TestException;
import com.cityindex.manager.TestManager;
import com.cityindex.utils.Constants;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import static com.cityindex.utils.LoggerUtil.e;
import static com.cityindex.utils.LoggerUtil.i;

public class Login extends Screen{

    private WebElement element;

    public Login(TestManager testManager) {
        super(testManager);
    }

    public boolean signIn(String login, String password) throws TestException {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        By xpathRequestError = By.xpath(Constants.LoginScreen.xPath.REQUEST_ERROR);
        By xpathForLoginBtnInMarkets = By.xpath(Constants.LoginScreen.xPath.LOGIN_BUTTON_IN_MARKETS);
        By xpathLoginField = By.xpath(Constants.LoginScreen.xPath.LOGIN_FIELD);
        By xpathPasswordField = By.xpath(Constants.LoginScreen.xPath.PASSWORD_FIELD);
        By xpathLoginBtn = By.xpath(Constants.LoginScreen.xPath.LOGIN_BUTTON);
        By xpathContinueBtn = By.xpath(Constants.LoginScreen.xPath.CONTINUE_BUTTON);
        By xpathCloseBtn = By.xpath(Constants.LoginScreen.xPath.CLOSE_BUTTON);
        By xpathTabBar = By.xpath(Constants.Common.xPath.TAB_BAR);

        if (waitWhileElementExist(xpathRequestError, 5000)){
            element = driver.findElement(xpathRequestError);
            element.click();
        }
        if(!waitWhileElementExist(xpathForLoginBtnInMarkets, 15000)){
            testManager.retest("Login button in markets was not found");
        }
        testManager.testCaseInfo.writeResult("result.json");
        element = driver.findElement(By.name("Login"));
        String loginBtnText = element.getText();
        i("LOGIN BUTTON LANGUAGE:   " + loginBtnText);
        element.click();
        i("Click on Login btn");
        if(!waitWhileElementExist(xpathLoginField, 15000))
            e("Login field was not found");
        // Enter Login
        element = driver.findElement(xpathLoginField);
        MobileElement mobileElement = (MobileElement) element;
        mobileElement.setValue(login);
        i("Enter login " + login);
        // Enter Password
        element = driver.findElement(xpathPasswordField);
        mobileElement = (MobileElement) element;
        mobileElement.setValue(password);
        i("Enter password " + password);
        //Click Login button
        element = driver.findElement(xpathLoginBtn);
        element.click();
        i("Click on Login btn");
        //Wait warning screen and click on Continue button
        if (!waitWhileElementExist(xpathContinueBtn, 20000))
            e("Continue btn was not found");
        element = driver.findElement(xpathContinueBtn);
        element.click();
        i("Click on continue button");
        //Wait Tutorial and click close button
        if (!waitWhileElementExist(xpathCloseBtn, 15000))
            e("Close button was not found");
        element = driver.findElement(xpathCloseBtn);
        element.click();
        i("Click on Close button");
        // wait tab bar
        if (!waitWhileElementExist(xpathTabBar, 15000))
            return false;
        i("LOGIN PASSED");
        return true;
    }
}
