package homework1;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FirstSimpleTest extends DriverSetup {

    @BeforeClass(description = "Prepare driver to run test(s)")
    // Select specific configuration native/web
    public void setUp() throws Exception {
        prepareAndroidNative();
        //prepareAndroidWeb();
    }

    @AfterClass(description = "Close driver on all tests completion")
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Test(description = "Click on the button 'Add contact'")
    public void simplestTest() {
        String app_package_name = "com.example.android.contactmanager:id/";
        // Locators variants
        By add_btn_byId = By.id(app_package_name + "addContactButton");
        By add_btn_byXpath = By.xpath("//android.widget.Button[@content-desc=\"Add Contact\"]");
        By add_btn = By.className("android.widget.Button");
        driver.findElement(add_btn).click();
        System.out.println("The 'Add contact' button clicked");
    }

    @Test(description = "Open the iana.org page")
    public void webTest() throws InterruptedException {
        driver.get("http://iana.org");
        Thread.sleep(5000);
        System.out.println("The iana.org page has opened");
    }
}
