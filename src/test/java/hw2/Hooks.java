package hw2;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import setup.DriverSetup;
import setup.SelectProperties;

@Test(groups = {"native", "web"})
public class Hooks extends DriverSetup {

    private SelectProperties currentProperties;

    public Hooks(SelectProperties properties) {
        this.currentProperties = properties;
    }

    @BeforeSuite(description = "Prepare driver")
    public void setUp() throws Exception {
        setPropertyFile(currentProperties);
        prepareDriver();
    }

    @AfterSuite(description = "Close driver")
    public void tearDown() throws Exception {
        driver().quit();
    }
}
