package com.cityindex.utils;

public class Constants {
    public static final String CONFIGURATION_FILE_PATH = "config.properties";
    public static int DEFAULT_TIMEOUT = 30000;
    public static final String TIME_FORMAT = "hh_mm_ss_SSS__dd_MM_yyyy_";
    public static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static class Common{
        public static class xPath{
            public static final String TAB_BAR = "//UIAApplication[1]/UIAWindow[1]/UIAButton[1]";
        }
    }

    public static class LoginScreen{
        public static class xPath{
            public static final String REQUEST_ERROR ="//UIAApplication[1]/UIAWindow[4]/UIAAlert[1]/UIACollectionView[1]/UIACollectionCell[1]/UIAButton[1]";
            public static final String LOGIN_BUTTON_IN_MARKETS = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[2]";
            public static final String LOGIN_FIELD = "//UIAApplication[1]/UIAWindow[1]/UIATextField[1]";
            public static final String PASSWORD_FIELD ="//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]";
            public static final String LOGIN_BUTTON = "//UIAApplication[1]/UIAWindow[1]/UIAButton[1]";
            public static final String CONTINUE_BUTTON = "//UIAApplication[1]/UIAWindow[1]/UIAButton[1]";
            public static final String CLOSE_BUTTON = "//UIAApplication[1]/UIAWindow[1]/UIAButton[1]";
        }
    }
}
