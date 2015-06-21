package com.cityindex.param;

import java.util.ArrayList;

import static com.cityindex.utils.LoggerUtil.e;

public class ParamsParser {

    private static ParamsParser paramsParser;

    private ParamsParser() {}

    public static ParamsParser getInstance(){
        if(paramsParser == null) paramsParser = new ParamsParser();
        return paramsParser;
    }

    private String deviceUuid;
    private String deviceOS;
    private String deviceName;
    private String testClass;
    private String testId;
    private String pathToResultsFolder;
    private String pathToBuild;


    private ArrayList<String> argsName = new ArrayList<String>();
    {
        argsName.add("deviceOS");
        argsName.add("deviceName");
        argsName.add("testClass");
        argsName.add("testId");
        argsName.add("uuid");
        argsName.add("pathToResultFolder");
        argsName.add("pathToBuild");
    }

    public boolean parse(String... args) {
        if(args.length % 6 != 0 || args.length == 0) {
            e("Wrong arguments count or format\n" +
                    "usage: \n" +
                    "java -jar AppiumTests.jar -e testClass testClass -e testId testId -e deviceName \"device name\" -uuid deviceUUID -e deviceOS 8.2" +
                    " -e pathToResultFolder \"path/to/reults/folder\" -e pathToBuild(Optional) \"path/to/build.app(ipa)\"");
            System.exit(0);
            return false;
        }

        String command = "";

        for(int currentIndex = 0; currentIndex < args.length; currentIndex ++){
            if(!args[currentIndex].equals("-e") || !argsName.contains(command = args[currentIndex + 1])) {
                e("Wrong arguments count or format\n" +
                        "usage: \n" +
                        "java -jar IOsCiTests -e testId testId  -e deviceName \"device name\" -e uuid deviceUUID -e deviceOS 8.2" +
                        " -e pathToResultFolder \"path/to/reults/folder\" -e pathToBuild(Optional) \"path/to/build.app(ipa)\"");
                System.exit(0);
                return false;
            }
            currentIndex = currentIndex + 2;
            if (command.equals("testClass")){
                testClass = args[currentIndex];
            } else if(command.equals("testId")){
                testId = args[currentIndex];
            } else if(command.equals("uuid")) {
                deviceUuid = args[currentIndex];
            } else if(command.equals("pathToResultFolder")) {
                pathToResultsFolder = args[currentIndex];
            } else if(command.equals("pathToBuild")) {
                pathToBuild = args[currentIndex];
            } else if (command.equals("deviceOS")){
                deviceOS = args[currentIndex];
            } else if (command.equals("deviceName")){
                deviceName = args[currentIndex];
            }
        }
        if(deviceUuid == null || testId == null || testClass == null) {
            e("Missed argument\n" +
                    "usage: \n" +
                    "java -jar AppiumTests.jar -e testClass testClass -e testId testId -e uuid deviceUUID -e pathToResultFolder \"path/to/reults/folder\" -e pathToBuild(Optional) \"path/to/build.app(ipa)\"");
            System.exit(0);
            return false;
        }
        return true;
    }

    public String getDeviceOS(){return deviceOS;}

    public String getTestClass() {return testClass;}

    public String getDeviceUuid() {
        return deviceUuid;
    }

    public String getTestId() {
        return testId;
    }

    public String getPathToBuild() {
        return pathToBuild;
    }

    public String getPathToResultsFolder() {
        return pathToResultsFolder;
    }

    public String getDeviceName() {return deviceName;}
}
