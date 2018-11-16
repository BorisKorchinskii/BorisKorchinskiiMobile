package setup;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.net.URL;

public class DriverSetup extends TestProperties {

    private static AppiumDriver driverSingle = null;
    private static WebDriverWait waitSingle;
    private DesiredCapabilities capabilities;

    // Properties to be read

    private static String AUT; // (mobile) app under testing
    protected static String SUT; // site under testing
    private static String TEST_PLATFORM;
    private static String DRIVER;
    private static String DEVICE_NAME;

    // Constructor initializes properties on driver creation and log

/*    protected void DriverSetup() throws IOException {
        String propertiesPath = "./src/main/resources/";
        String t_aut = getProp("aut");
        AUT = t_aut == null ? null : propertiesPath + t_aut;
        String t_sut = getProp("sut");
        SUT = t_sut == null ? null : "http://" + t_sut;
        TEST_PLATFORM = getProp("platform");
        DRIVER = getProp("driver");
        DEVICE_NAME = getProp("devicename");
    }*/

    // Initialize driver with appropriate capabilities depending on platform and application

    protected void prepareDriver() throws Exception {
        capabilities = new DesiredCapabilities();
        String browserName;

        String propertiesPath = "./src/main/resources/";
        String t_aut = getProp("aut");
        AUT = t_aut == null ? null : propertiesPath + t_aut;
        String t_sut = getProp("sut");
        SUT = t_sut == null ? null : "http://" + t_sut;
        TEST_PLATFORM = getProp("platform");
        DRIVER = getProp("driver");
        DEVICE_NAME = getProp("devicename");

        switch (TEST_PLATFORM) {
            case "Android":
                capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, DEVICE_NAME);
                browserName = "Chrome";
                break;
            case "iOS":
                browserName = "Safari";
                break;
            default:
                throw new Exception("Unknown mobile platform");
        }
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, TEST_PLATFORM);

        //find the corresponding capabilities for WEB/NATIVE test in the config
        //If both SUT and AUT are not null - we pick NATIVE test up

        if (AUT != null && SUT == null) {
            // Native
            File app = new File(AUT);
            capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        } else if (SUT != null && AUT == null) {
            // Web
            capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, browserName);
        } else {
            throw new Exception("Unclear type of mobile app");
        }

        // Driver initialized with new AndroidDriver
        if (driverSingle == null) {
            driverSingle = new AndroidDriver(new URL(DRIVER), capabilities);
        }

        // Timeout handling
        if (waitSingle == null) {
            waitSingle = new WebDriverWait(driver(), 10);
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






