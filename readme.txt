Command to run test:

"java -jar AppiumTests.jar -e testClass testClass -e testId testId -e deviceName "deviceName"  deviceUUID -e pathToResultFolder \"path/to/results/folder\" -e pathToBuild(Optional) \"path/to/build.app(ipa)\"");

Sample: java -jar AppiumCityIndexDemo.jar -e testClass LoginTests -e testId 12345 -e deviceName "iPhone 4" -e deviceOS 7.1.2 -e uuid 4ea58559a4fa01c8a380d05eac4b97421f72995d -e pathToBuild "/Users/robert/Downloads/CityIndex-2.ipa"

java -jar AppiumCityIndexDemo.jar -e testClass LoginTests -e testId 12345 -e deviceName "iPhone 5" -e deviceOS 7.1.2 -e uuid daa13011d9faebce689464847bd7e136d2c94114 -e pathToBuild "/Users/admin/Downloads/CityIndex-2.ipa"