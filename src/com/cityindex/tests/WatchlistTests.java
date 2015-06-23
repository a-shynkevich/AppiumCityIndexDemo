package com.cityindex.tests;

import com.cityindex.exception.TestException;
import com.cityindex.json.Status;
import com.cityindex.manager.TestManager;
import com.cityindex.screen.Watchlist;

public class WatchlistTests extends Watchlist {

    public WatchlistTests(TestManager testManager) {
        super(testManager);
    }

    public void checkCreateNewWatchList() throws TestException {
        if(!createNewWatchList()){
            testManager.retest("retest");
        }
        testManager.testCaseInfo.setStatusId(Status.PASSED);
    }
}
