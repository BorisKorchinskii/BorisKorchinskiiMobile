package homework2.webTests;

import nativeapp.IanaLandingPage;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import setup.Driver;

import java.io.IOException;

@Test(groups = "web")
public class WebTest extends Driver {

    private IanaLandingPage ianaLandingPage;

    protected WebTest() throws IOException {
        super();
    }

    @BeforeSuite(description = "Prepare driver to run tests")
    public void setUp() throws Exception {
        prepareDriver();
        ianaLandingPage = new IanaLandingPage(driverSingle);
        System.out.println("Driver prepared");
    }

    @AfterSuite(description = "Close driver on all tests completion")
    public void tearDown() throws Exception {
        driverSingle.quit();
        System.out.println("Driver closed");
    }

    @Test(description = "Navigate to Iana page and verify content")
    public void navigateToPage() throws Exception {
        ianaLandingPage.openPage(SUT, waitSingle);
        ianaLandingPage.checkifPageAvailable();
        ianaLandingPage.checkUrl();
        ianaLandingPage.checkHttpStatusCode(SUT, 200);
        ianaLandingPage.checkPanelsVisible();
    }
}
