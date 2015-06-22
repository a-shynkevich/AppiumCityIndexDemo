package com.cityindex.assistant;

import com.cityindex.exception.TestException;
import com.cityindex.param.ConfigParam;
import com.cityindex.screen.Login;
import com.cityindex.screen.Screen;

public class Preparer extends Screen {
    Login loginScreen;

    public Preparer() {
        loginScreen = new Login();
    }

    @Override
    public void runTest(String testId) throws TestException {
    }

    public void login(ConfigParam accNumber, ConfigParam pass) throws TestException {
        String login = testManager.configManager.getProperty(accNumber.name());
        String password = testManager.configManager.getProperty(pass.name());
        if(!loginScreen.signIn(login, password))
            testManager.retest("Error in Login");
    }

}
