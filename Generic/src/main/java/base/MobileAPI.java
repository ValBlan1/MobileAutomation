package base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class MobileAPI {

    public static AppiumDriver appiumDriver = null;
    public DesiredCapabilities cap = null;
    public static File appDirectory = null;
    public static File findApp = null;

    @Parameters({"OS", "appType", "deviceType", "deviceName", "version", "moduleName", "appName"})
    @BeforeMethod
    public void setUp(@Optional String OS, @Optional String appType, @Optional String deviceType, @Optional String deviceName, @Optional String version, @Optional String moduleName,
                      @Optional String appName) throws MalformedURLException {
        if(OS.equalsIgnoreCase("iOS")){
            //handle IOS
            if(OS.equalsIgnoreCase("Phone")){
                if(deviceType.equalsIgnoreCase("realDevice")){
                    cap = new DesiredCapabilities();
                    cap.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
                    cap.setCapability(MobileCapabilityType.PLATFORM_NAME, deviceType);
                    cap.setCapability(MobileCapabilityType.PLATFORM_VERSION,version);
                    cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
                }else if(deviceType.equalsIgnoreCase("Simulator")){
                    cap = new DesiredCapabilities();
                    cap.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
                    cap.setCapability(MobileCapabilityType.PLATFORM_NAME, deviceType);
                    cap.setCapability(MobileCapabilityType.PLATFORM_VERSION,version);
                    cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
                }

            }else if(OS.equalsIgnoreCase("Tablets")){
                if(deviceType.equalsIgnoreCase("realDevice")){
                    cap = new DesiredCapabilities();
                    cap.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
                    cap.setCapability(MobileCapabilityType.PLATFORM_NAME, deviceType);
                    cap.setCapability(MobileCapabilityType.PLATFORM_VERSION,version);
                    cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
                }else if(deviceType.equalsIgnoreCase("Emulator")){
                    cap = new DesiredCapabilities();
                    cap.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
                    cap.setCapability(MobileCapabilityType.PLATFORM_NAME, deviceType);
                    cap.setCapability(MobileCapabilityType.PLATFORM_VERSION,version);
                    cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
                }
            }
        }else if(OS.equalsIgnoreCase("Android")){
            //Handle Android
            if(appType.equalsIgnoreCase("Phone")){
                appDirectory = new File(moduleName + "/src/app");
                findApp = new File(appDirectory,appName);
                if(deviceType.equalsIgnoreCase("realDevice")){
                    cap = new DesiredCapabilities();
                    cap.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
                    cap.setCapability(MobileCapabilityType.PLATFORM_NAME, deviceType);
                    cap.setCapability(MobileCapabilityType.PLATFORM_VERSION,version);
                    cap.setCapability(MobileCapabilityType.APP, findApp.getAbsolutePath());
                    cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
                    appiumDriver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),cap);
                    appiumDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                }else if(deviceType.equalsIgnoreCase("Emulator")){
                    cap = new DesiredCapabilities();
                    cap.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
                    cap.setCapability(MobileCapabilityType.PLATFORM_NAME, deviceType);
                    cap.setCapability(MobileCapabilityType.PLATFORM_VERSION,version);
                    cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
                }

            }else if(appType.equalsIgnoreCase("Tablets")) {
                if (deviceType.equalsIgnoreCase("realDevice")) {
                    cap = new DesiredCapabilities();
                    cap.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
                    cap.setCapability(MobileCapabilityType.PLATFORM_NAME, deviceType);
                    cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, version);
                    cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
                } else if (deviceType.equalsIgnoreCase("Emulator")) {
                    cap = new DesiredCapabilities();
                    cap.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
                    cap.setCapability(MobileCapabilityType.PLATFORM_NAME, deviceType);
                    cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, version);
                    cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
                }
            }
        }
    }

    @AfterMethod
    public void cleanUpApp(){
        appiumDriver.quit();
    }
}
