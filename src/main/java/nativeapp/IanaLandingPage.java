package nativeapp;

import io.appium.java_client.AppiumDriver;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class IanaLandingPage {

    private AppiumDriver driver;

    private String pageUrl = "https://www.iana.org/";
    private String pageTitle = "Internet Assigned Numbers Authority";
    private By domainNamesPanel = By.id("home-panel-domains");
    private By numberResourcesPanel = By.id("home-panel-numbers");
    private By protocolAssignmentsPanel = By.id("home-panel-numbers");

    public IanaLandingPage(AppiumDriver appiumDriver) throws IOException {
        this.driver = appiumDriver;
    }

    // ============== Actions methods =================================================

    public void openPage(String sut, WebDriverWait driverWait) {
        driver.get(sut);
        driverWait.until(ExpectedConditions.urlToBe(sut + "/"));
    }

    // ============== Elements checkers =================================================

    public void checkifPageAvailable() {
        assertEquals(driver.getTitle(), pageTitle);
    }

    public void checkUrl() {
        assertEquals(driver.getCurrentUrl(), pageUrl);
    }

    public void checkHttpStatusCode(String url, int expCode) throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);
        HttpResponse response = client.execute(request);
        assertEquals(response.getStatusLine().getStatusCode(), expCode);
    }

    public void checkPanelsVisible() {
        assertTrue(driver.findElement(domainNamesPanel).isDisplayed());
        assertTrue(driver.findElement(numberResourcesPanel).isDisplayed());
        assertTrue(driver.findElement(protocolAssignmentsPanel).isDisplayed());
    }
}

