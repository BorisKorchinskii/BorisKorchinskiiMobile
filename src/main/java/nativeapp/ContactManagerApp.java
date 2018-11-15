package nativeapp;

import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import setup.DriverSetup;

import java.util.Arrays;
import java.util.List;

public class ContactManagerApp extends DriverSetup {
    private final String app_package_name = "com.example.android.contactmanager:id/";

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc='Add Contact']")
    private AndroidElement addContactButton;

    @AndroidFindBy(id = "android:id/title")
    private AndroidElement accContactTitle;

    @AndroidFindBy(id = app_package_name + "contactNameEditText")
    private AndroidElement nameField;

    @AndroidFindBy(id = "Contact Name")
    private AndroidElement contactNameTitle;

    @AndroidFindBy(id = "Target Account")
    private AndroidElement targetAccountTitle;

    @AndroidFindBy(id = "Contact Phone")
    private AndroidElement contactFormTitle;

    @AndroidFindBy(id = "Contact Email")
    private AndroidElement contactEmailForm;

    @AndroidFindBy(id = app_package_name + "accountSpinner")
    private AndroidElement targetAccountSpinner;

    @AndroidFindBy(id = app_package_name + "contactNameEditText")
    private AndroidElement contactNameField;

    @AndroidFindBy(id = app_package_name + "contactPhoneEditText")
    private AndroidElement contactFormEditField;

    @AndroidFindBy(id = app_package_name + "contactEmailEditText")
    private AndroidElement contactEmailField;


    List<AndroidElement> actualTetlesNames = Arrays.asList(contactNameTitle, targetAccountTitle,
            contactFormTitle, contactEmailForm);

    List<String> expectedlTetlesNames = Arrays.asList("Target Account", "Contact Name",
            "Contact Phone", "Contact Email");


    // ============== Actions methods =================================================

    public void hitAddButton() {
        addContactButton.click();
    }

    // ============== Elements checkers =================================================

    public void checkIfTitleVisible() {   //// ? checkIfFormsTitlesVisible
        Assert.assertTrue(accContactTitle.isDisplayed());
    }

    public void checkIfFormsTitlesVisible() {
        for (WebElement titles : actualTetlesNames) {
            Assert.assertTrue(titles.isDisplayed());
        }
        for (int i = 0; i < expectedlTetlesNames.size(); i++) {
            Assert.assertEquals(actualTetlesNames.get(i).getText(), expectedlTetlesNames.get(i));
        }
    }

    public void checkIfFieldsVisible() {
        Assert.assertTrue(targetAccountSpinner.isDisplayed());
        Assert.assertTrue(contactNameField.isDisplayed());
        Assert.assertTrue(contactFormEditField.isDisplayed());
        Assert.assertTrue(contactEmailField.isDisplayed());
    }
}
