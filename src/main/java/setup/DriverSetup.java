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
import java.net.URL;
import java.util.Properties;

import static io.appium.java_client.remote.MobileCapabilityType.APP;
import static io.appium.java_client.remote.MobileCapabilityType.BROWSER_NAME;

public class DriverSetup extends TestProperties {

    private static AppiumDriver driverSingle = null;
    private static WebDriverWait waitSingle;
    protected DesiredCapabilities capabilities;

    // Properties

    protected String SUT;
    private String AUT;
    private String TEST_PLATFORM;
    private String DRIVER;
    private String UDID;
    private String DEVICE_NAME;
    private String APP_PACKAGE;
    private String APP_ACTIVITY;

    private Properties properties;

    /**
     * Initialize driver with appropriate capabilities depending on platform and application
     *
     * @throws Exception
     */

    protected void prepareDriver() throws Exception, UnknownPatformException, UnclearAppTypeException {

        String resourcePath = "src/main/resources/";
        String mobileAppName = getProp("aut");
        AUT = mobileAppName == null ? null : resourcePath + mobileAppName;
        String t_sut = getProp("sut");
        SUT = t_sut == null ? null : "https://" + t_sut;
        TEST_PLATFORM = getProp("platform");
        DRIVER = getProp("driver");
        UDID = getProp("udid");
        DEVICE_NAME = getProp("devicename");
        APP_PACKAGE = getProp("app_package");
        APP_ACTIVITY = getProp("app_activity");
        String browserName;

        capabilities = new DesiredCapabilities();
        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, APP_PACKAGE);
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, APP_ACTIVITY);
        capabilities.setCapability(MobileCapabilityType.UDID, UDID);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, TEST_PLATFORM);

        // Setup test platform Android/iOS

        switch (TEST_PLATFORM) {
            case "Android":
                browserName = "Chrome";
                capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, DEVICE_NAME);
                break;
            case "iOS":
                browserName = "Safari";
                break;
            default:
                throw new UnknownPatformException();
        }

        // Setup test type - mobile/web/hybrid

        if (AUT != null && SUT == null) {
            // Native
            File app = new File(AUT);
            capabilities.setCapability(APP, app.getAbsolutePath());
        } else if (SUT != null && AUT == null) {
            capabilities.setCapability(BROWSER_NAME, browserName);
        } else {
            throw new UnclearAppTypeException();
        }

        // Init driver for local Appium server with the following capabilities set

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

        // Timeout handling with set objects

        if (waitSingle == null) {
            waitSingle = new WebDriverWait(driver(), 10);
        }
    }

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







