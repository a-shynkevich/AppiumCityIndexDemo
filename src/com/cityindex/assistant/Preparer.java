package com.cityindex.assistant;

import com.cityindex.exception.TestException;
import com.cityindex.manager.TestManager;
import com.cityindex.param.ConfigParam;
import com.cityindex.screen.Login;
import com.cityindex.screen.Screen;

public class Preparer extends Screen {

    public Preparer(TestManager testManager) {
        super(testManager);
    }

    public void login(ConfigParam accNumber, ConfigParam pass) throws TestException {
        Login loginScreen = new Login(testManager);
        String login = testManager.configManager.getProperty(accNumber.name());
        String password = testManager.configManager.getProperty(pass.name());
        if(!loginScreen.signIn(login, password))
            testManager.retest("Error in Login");
    }
}
