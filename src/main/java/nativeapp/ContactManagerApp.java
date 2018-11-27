package nativeapp;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.HasOnScreenKeyboard;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import setup.DriverSetup;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ContactManagerApp extends DriverSetup {

    private final String app_package_name = "com.example.android.contactmanager:id/";

    @FindBy(id = app_package_name + "addContactButton")
    private WebElement  addContactButton;

    @FindBy(id = "android:id/title")
    private WebElement  contactTitle;

    @FindBy(id = app_package_name + "contactNameEditText")
    private WebElement  nameField;

    @FindBy(id = "Target Account")
    private WebElement  targetAccountTitle;

    @FindBy(id = "Contact Name")
    private WebElement  contactNameTitle;

    @FindBy(id = "Contact Phone")
    private WebElement  contactFormTitle;

    @FindBy(id = "Contact Email")
    private WebElement  contactEmailForm;

    @FindBy(id = app_package_name + "accountSpinner")
    private WebElement  targetAccountSpinner;

    @FindBy(id = app_package_name + "contactNameEditText")
    private WebElement  contactNameField;

    @FindBy(id = app_package_name + "contactPhoneEditText")
    private WebElement  contactFormEditField;

    @FindBy(id = app_package_name + "contactEmailEditText")
    private WebElement  contactEmailField;


    private List<WebElement > actualTitlesNames = Arrays.asList(contactNameTitle, targetAccountTitle,
            contactFormTitle, contactEmailForm);

    private List<String> expectedTitlesNames = Arrays.asList("Target Account", "Contact Name",
            "Contact Phone", "Contact Email");

    public ContactManagerApp() throws IOException {
    }

    // ============== Actions methods =================================================

    public void hitAddButton() {
        addContactButton.click();
    }

    public void typeName() {
        nameField.click();
        nameField.sendKeys("Username");
    }

    public void closeApp() {
        driver().closeApp();
    }

    // ============== Elements checkers =================================================

    public void checkKeyboard() {
        driver().getKeyboard();
    }

    public void checkAddContactButtonVisible() {
        Assert.assertTrue(addContactButton.isDisplayed());
    }

    public void checkIfTitleVisible() {
        Assert.assertTrue(contactTitle.isDisplayed());
    }

    public void checkIfFieldsVisible() {
        Assert.assertTrue(targetAccountSpinner.isDisplayed());
        Assert.assertTrue(contactNameField.isDisplayed());
        Assert.assertTrue(contactFormEditField.isDisplayed());
        Assert.assertTrue(contactEmailField.isDisplayed());
    }

    public void checkKeyboardPresence(AppiumDriver driver) {
        if (driver instanceof HasOnScreenKeyboard) {
            Assert.assertTrue(((HasOnScreenKeyboard) driver).isKeyboardShown());
        } else {
            throw new RuntimeException("Current driver " + driver.getClass().getSimpleName() + " doesn't have a HasOnScreenKeyboard implementation");
        }
    }
}
