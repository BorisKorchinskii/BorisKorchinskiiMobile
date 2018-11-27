/*
package hw1;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;


import java.net.MalformedURLException;
import java.net.URL;

public class DriverSetup {

    protected AndroidDriver driver;

    public DriverSetup(SelectProperties properties) {
    }

    void prepareNative() throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        //mandatory capabilities
        capabilities.setCapability("deviceName", "emulator-5554");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("app", "D:\\IdeaProjects\\BorisKorchinskiiMobile\\src\\main\\resources\\ContactManager.apk");

        driver = new AndroidDriver(capabilities);
    }

    void prepareWeb() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        // Mandatory Android capabilities
        capabilities.setCapability("deviceName", "emulator-5554");
        capabilities.setCapability("platformName", "Android");

        // Specific web capabilities
        capabilities.setCapability("browserName", "Chrome");

        // Driver initialization with desired capabilities
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }
}

*/

