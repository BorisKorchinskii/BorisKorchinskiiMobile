package nativeapp;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.HasOnScreenKeyboard;
import org.openqa.selenium.By;
import org.testng.Assert;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ContactManagerApp {

    private final String app_package_name = "com.example.android.contactmanager:id/";
    private AppiumDriver driver;

    String pageTitle = "Contact Manager";

    private By contactTitle = By.id("android:id/title");

    private By addContactButton = By.id(app_package_name + "addContactButton");

    private By nameField = By.id(app_package_name + "contactNameEditText");

    private By targetAccountSpinner = By.id(app_package_name + "accountSpinner");

    private By contactNameField = By.id(app_package_name + "contactNameEditText");

    private By contactFormEditField = By.id(app_package_name + "contactPhoneEditText");

    private By contactEmailField = By.id(app_package_name + "contactEmailEditText");

    public ContactManagerApp(AppiumDriver appiumDriver) {
        this.driver = appiumDriver;
    }

    // ============== Actions methods =================================================

    public void hitAddButton() {
        driver.findElement(addContactButton).click();
    }

    public void typeName() {
        driver.findElement(nameField).click();
        driver.findElement(nameField).sendKeys("Username");
    }

    // ============== Elements checkers =================================================

    public void checkIfTitleVisible() {
        assertEquals(driver.findElement(contactTitle).getText(), pageTitle);
    }

    public void checkAddContactButtonVisible() {
        assertTrue(driver.findElement(addContactButton).isDisplayed());
    }

    public void checkIfFieldsVisible() {
        assertTrue(driver.findElement(targetAccountSpinner).isDisplayed());
        assertTrue(driver.findElement(contactNameField).isDisplayed());
        assertTrue(driver.findElement(contactFormEditField).isDisplayed());
        assertTrue(driver.findElement(contactEmailField).isDisplayed());
    }

    public void checkKeyboardPresence(AppiumDriver driver) {
        if (driver instanceof HasOnScreenKeyboard) {
            Assert.assertTrue(((HasOnScreenKeyboard) driver).isKeyboardShown());
        } else {
            throw new RuntimeException("Current driver " + driver.getClass().getSimpleName() + " doesn't have a HasOnScreenKeyboard implementation");
        }
    }
}
