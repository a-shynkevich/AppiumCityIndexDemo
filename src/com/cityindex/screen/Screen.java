package com.cityindex.screen;

import com.cityindex.manager.TestManager;
import com.cityindex.assistant.AppiumHelper;

public abstract class Screen extends AppiumHelper {

    public Screen(TestManager testManager) {
        super(testManager);
    }


    //For implementation common methods to test app
}
