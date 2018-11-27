package hw2;

import nativeapp.IanaLandingPage;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import setup.Hooks;

import java.io.IOException;

@Test(groups = "web")
public class WebTest extends Hooks {

    private IanaLandingPage ianaLandingPage;

    protected WebTest() throws IOException {
        super();
    }

    @BeforeClass
    public void beforeClass() {
        ianaLandingPage = PageFactory.initElements(driver(), IanaLandingPage.class);
    }

    @AfterClass
    public void afterClass() {
        ianaLandingPage.closeBrowser();
    }

    @Test(description = "Navigate to Iana page and verify content")
    public void navigateToPage() throws Exception {
        driver().get(SUT);
        driverWait().until(ExpectedConditions.urlToBe(SUT + "/"));
        ianaLandingPage.checkifPageAvailable(driver(), SUT + "/");
        ianaLandingPage.checkHttpStatusCode(SUT, 200);
        ianaLandingPage.checkPanelsVisible();
    }
}
