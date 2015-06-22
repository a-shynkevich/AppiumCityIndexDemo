package com.cityindex.screen;

import com.cityindex.TestRunner;
import com.cityindex.exception.TestException;

public abstract class Screen extends TestRunner{

    public abstract void runTest(String testId) throws TestException;

    //For implementation common methods to test app
}
