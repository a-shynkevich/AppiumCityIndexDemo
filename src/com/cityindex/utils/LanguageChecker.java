package com.cityindex.utils;


public class LanguageChecker {

    public enum  Language {ENGLISH, DEUTSCH, ARABIC}

    public boolean checkLanguage(String text, Language lang){
        switch (lang){
            case ENGLISH:
                return text.equals("Login");
            case DEUTSCH:
                return text.equals("Einloggen");
            case ARABIC:
                return text.equals("تسجيل دخول");
        }
        return false;
    }

}
