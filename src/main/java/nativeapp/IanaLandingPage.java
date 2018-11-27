package nativeapp;

import io.appium.java_client.AppiumDriver;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import setup.DriverSetup;

import java.io.IOException;
import java.util.List;

import static org.testng.AssertJUnit.assertTrue;

public class IanaLandingPage extends DriverSetup {


    @FindBy(css = "#home-panel")
    private List<WebElement> homePanels;

    public IanaLandingPage() throws IOException {
        super();
    }

    // ============== Actions methods =================================================

    public void closeBrowser() {
        driver().closeApp();
    }

    // ============== Elements checkers =================================================

    public void checkifPageAvailable(AppiumDriver driver, String expUrl) {
        Assert.assertEquals(driver.getCurrentUrl(), expUrl);
    }

    public void checkHttpStatusCode(String url, int expCode) throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);
        HttpResponse response = client.execute(request);
        Assert.assertEquals(response.getStatusLine().getStatusCode(), expCode);
    }

    public void checkPanelsVisible() {
        Assert.assertEquals(homePanels.size(), 3);
        for (WebElement section : homePanels) {
            assertTrue(section.isDisplayed());
        }
    }
}
