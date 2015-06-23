package com.cityindex.tests;

import com.cityindex.exception.TestException;
import com.cityindex.json.Status;
import com.cityindex.manager.TestManager;
import com.cityindex.screen.Login;

public class LoginTests extends Login{

    public LoginTests(TestManager testManager) {
        super(testManager);
    }

    public void checkLogin() throws TestException {
        if(!signIn("dm538902", "password")) {
            testManager.failTest("LOGIN FAILED");
        }
        testManager.testCaseInfo.setStatusId(Status.PASSED);
    }
}
