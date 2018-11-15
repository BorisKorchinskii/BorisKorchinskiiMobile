package hw1;


import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import setup.SelectProperties;

public class FirstSimpleTest extends DriverSetup{

    public FirstSimpleTest(SelectProperties properties) {
        super(properties);
    }

    @BeforeClass(description = "Prepare driver to run Web/Native test(s)")
    public void setUp() throws Exception {
        // Comment to set driver to Native or Web settings
        prepareNative();
        //prepareWeb();
    }

    @AfterClass(description = "Close driver on all tests completion")
    public void tearDown()  {
        driver.quit();
    }

    @Test (description = "This simple test just click on button 'Add contact'")
    public void SimpleNativeTest(){
        driver.findElement(By.xpath("//android.widget.Button[@content-desc='Add Contact']")).click();
        //Possible locators could be:
        //xpath("//android.widget.Button[@content-desc='Add Contact']");
        //className("android.widget.Button");
        //id("com.example.android.contactmanager:id/addContactButton");
        System.out.println("Contact added");
    }

    @Test(description = "Navigate to the Iana.org page")
    public void SimpleWebTest(){
                driver.get("http://iana.org");
        System.out.println("Navigated to the Iana.org page");
    }
}





