Command to run test:

"java -jar AppiumTests.jar -e testClass testClass -e testId testId -e deviceName "deviceName"  deviceUUID -e pathToResultFolder \"path/to/results/folder\" -e pathToBuild(Optional) \"path/to/build.app(ipa)\"");

Sample: java -jar AppiumCityIndexDemo.jar -e testClass LoginTests -e testId testDemo -e deviceName "iPhone 4" -e deviceOS 7.1.2 -e uuid 4ea58559a4fa01c8a380d05eac4b97421f72995d -e pathToBuild "/Users/robert/Downloads/CityIndex-2.ipa"