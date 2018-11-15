package setup;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.net.URL;

import static io.appium.java_client.remote.MobileBrowserType.CHROME;
import static io.appium.java_client.remote.MobileBrowserType.SAFARI;
import static io.appium.java_client.remote.MobilePlatform.ANDROID;
import static io.appium.java_client.remote.MobilePlatform.IOS;

public class DriverSetup extends TestProperties {

    private static AppiumDriver driverSingle = null;
    private DesiredCapabilities capabilities;
    private static WebDriverWait waitSingle;

    // Properties to be read
    private static String AUT; // (mobile) app under testing
    protected static String SUT; // site under testing
    private static String TEST_PLATFORM;
    private static String DRIVER;
    private static String DEVICE_NAME;

    // Constructor initializes properties on driver creation
/*
    protected void DriverSetup() throws IOException {
        AUT = getProp("aut");
        String t_sut = getProp("sut");
        SUT = t_sut == null ? null : "http://" + t_sut;
        TEST_PLATFORM = getProp("platform");
        DRIVER = getProp("driver");
        DEVICE_NAME = getProp("devicename");
    }
*/

    /*** Initialize driver with appropriate capabilities depending on platform and application
     * @throws Exception*/

    protected void prepareDriver() throws Exception {
        capabilities = new DesiredCapabilities();
        String browserName;

        // log
        System.out.println("Properties: " + currentPropertyFile);
        String resourcePath = "./src/main/resources/";
        String mobileAppName = getProp("aut");
        AUT = mobileAppName == null ? null : resourcePath + mobileAppName;
        System.out.println("aut=" + AUT);

        // init parameters
        //AUT = getProp("aut");
        String t_sut = getProp("sut");
        SUT = t_sut == null ? null : "http://" + t_sut;
        System.out.println("sut=" + SUT);
        TEST_PLATFORM = getProp("platform");
        System.out.println("platform=" + TEST_PLATFORM);
        DRIVER = getProp("driver");
        System.out.println("driver=" + DRIVER);
        DEVICE_NAME = getProp("devicename ");
        System.out.println("devicename=" + DEVICE_NAME);

        if (AUT != null && SUT == null) {
            // Native
            File app = new File(AUT);
            capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());


        } else if (SUT != null && AUT == null) {
            // Web Android or iOS.
            switch (Platform.valueOf(TEST_PLATFORM.toUpperCase())) {
                case ANDROID:
                    capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, CHROME);
                    break;
                case IOS:
                    capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, SAFARI);
                    break;
            }
        } else {
            throw new Exception("Unclear type of mobile app");
        }


        switch (TEST_PLATFORM) {
            case ANDROID:
                driverSingle = new AndroidDriver(new URL(DRIVER), capabilities);
                break;
            case IOS:
                driverSingle = new IOSDriver(new URL(DRIVER), capabilities);
                break;
            default:
                throw new Exception("Unknown mobile platform");
        }

        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, TEST_PLATFORM);

        // Timeout handling
        if (waitSingle == null) {
            waitSingle = new WebDriverWait(driver(), 10);
        }

        // Driver initialized with new AndroidDriver
        if (driverSingle == null) {
            driverSingle = new AndroidDriver(new URL(DRIVER), capabilities);
        }
    }

    // Singleton access
    protected AppiumDriver driver() throws Exception {
        if (driverSingle == null) {
            prepareDriver();
        }
        return driverSingle;
    }

    protected WebDriverWait driverWait() throws Exception {
        return waitSingle;
    }
}






