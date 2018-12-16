package homework1;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class DriverSetup {

    protected AndroidDriver driver;

    protected void prepareAndroidNative() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        // Mandatory Capabilities
        capabilities.setCapability("deviceName", "J6AXB764P150FLB");
        capabilities.setCapability("platformName", "Android");

        //Path to app
        File app = new File("src\\main\\resources\\ContactManager.apk");
        capabilities.setCapability("app", app.getAbsolutePath());

        //Driver initialization using desired capabilities
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    protected void prepareAndroidWeb() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        // Mandatory Capabilities
        capabilities.setCapability("deviceName", "J6AXB764P150FLB");
        capabilities.setCapability("platformName", "Android");

        //Specific web capabilities
        capabilities.setCapability("browserName", "Chrome");

        //Driver initialization using desired capabilities
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }
}
