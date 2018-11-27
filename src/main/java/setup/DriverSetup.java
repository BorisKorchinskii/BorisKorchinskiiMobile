package setup;

import exceptions.UnclearAppTypeException;
import exceptions.UnknownPatformException;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class DriverSetup extends TestProperties {

    private static AppiumDriver driverSingle = null;
    private static WebDriverWait waitSingle;
    protected DesiredCapabilities capabilities;

    // Properties

    protected static String AUT;
    protected static String SUT;
    protected static String TEST_PLATFORM;
    protected static String DRIVER;
    protected static String DEVICE_NAME;
    protected static String APP_PACKAGE;
    protected static String APP_ACTIVITY;

    // Initialize driver with appropriate capabilities depending on platform and application

    protected DriverSetup() throws IOException {

        AUT = getProp("aut");
        String t_sut = getProp("sut");
        SUT = t_sut == null ? null : "https://" + t_sut;
        TEST_PLATFORM = getProp("platform");
        DRIVER = getProp("driver");
        DEVICE_NAME = getProp("devicename");
        APP_PACKAGE = getProp("app_package");
        APP_ACTIVITY = getProp("app_activity");
    }

    /**
     * Initialize driver with appropriate capabilities depending on platform and application
     *
     * @throws Exception
     */

    void prepareDriver() throws Exception {

        capabilities = new DesiredCapabilities();
        String browserName;

        switch (TEST_PLATFORM) {
            case "Android":
                browserName = "Chrome";
                break;
            case "iOS":
                browserName = "Safari";
                break;
            default:
                throw new UnknownPatformException();
        }

        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, DEVICE_NAME);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, TEST_PLATFORM);

        // Setup testing type: mobile, web, hybrid

        if (AUT != null && SUT == null) {
            // Native
            File app = new File(AUT);
            switch (TEST_PLATFORM) {
                case "Android":
                    capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, APP_PACKAGE);
                    capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, APP_ACTIVITY);
                    break;
                case "iOS":
                    capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
                    break;
                default:
                    throw new UnclearAppTypeException();
            }
        } else if (SUT != null && AUT == null) {
            // Web
            capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, browserName);
        } else {
            throw new UnclearAppTypeException();
        }

        // Init driver for local Appium server with capabilities set

        // Init driver for local Appium server with capabilities have been set
        if (driverSingle == null) {
            switch (TEST_PLATFORM) {
                case "Android":
                    driverSingle = new AndroidDriver(new URL(DRIVER), capabilities);
                    break;
                case "iOS":
                    driverSingle = new IOSDriver(new URL(DRIVER), capabilities);
                    break;
                default:
                    throw new UnknownPatformException();
            }
        }

        // Timeout handling

        if (waitSingle == null) {
            waitSingle = new WebDriverWait(driver(), 10);
        }

        if (driverSingle == null) {
            driverSingle = new AppiumDriver(new URL(DRIVER), capabilities);
        }
    }

    protected AppiumDriver driver() {
        return driverSingle;
    }

    protected WebDriverWait driverWait() {
        return waitSingle;
    }
}






