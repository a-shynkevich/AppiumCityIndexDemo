package com.cityindex.utils;

public class LoggerUtil {
    private static boolean isDebug = true;

    private static final String INFO = "INFO:";
    private static final String ERROR = "ERROR:";
    private static final String DEBUG = "DEBUG:";

       public static void i(String logMessage) {
        if(isDebug) {
            System.out.println(INFO + " " + logMessage);

        }
    }

    public static void e(String logMessage) {
        if(isDebug) {
            System.out.println(ERROR + " " + logMessage);
        }
    }

    public static void d(String logMessage) {
        if(isDebug) {
            System.out.println(DEBUG + " " + logMessage);
        }
    }
}
