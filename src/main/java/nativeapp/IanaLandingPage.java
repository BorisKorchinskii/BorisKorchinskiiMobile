package nativeapp;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import setup.DriverSetup;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class IanaLandingPage extends DriverSetup {

    @FindBy(css = "h1")
    private RemoteWebElement pageHeader;

    @FindBy(css = "#home-panels > div")
    private List<RemoteWebElement> homePanels;



    // ============== Actions methods =================================================

    public void navigateToPage() throws Exception {
        driver().get(SUT);
        driverWait().until(ExpectedConditions.urlToBe(SUT + "/"));
    }

    // ============== Elements checkers =================================================

    public void checkifPageAvailable() {
        assertTrue(pageHeader.isDisplayed());
    }

    public void checkPanelsVisible() {
        Assert.assertEquals(homePanels.size(), 3);
        for (WebElement section : homePanels) {
            assertTrue(section.isDisplayed());
        }
    }
}
