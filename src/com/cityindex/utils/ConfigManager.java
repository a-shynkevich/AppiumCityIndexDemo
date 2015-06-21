package com.cityindex.utils;


public class ConfigManager {
    private PropertiesManager propertiesManager;

    public ConfigManager() {
        propertiesManager = new PropertiesManager();
    }

    public PropertiesManager getPropertiesManager() {
        return propertiesManager;
    }

    public String getProperty(String configName){
        String property =  propertiesManager.getProperty(configName);
        System.out.println("Property:" + configName + " : " + property);
        return property;
    }
}
