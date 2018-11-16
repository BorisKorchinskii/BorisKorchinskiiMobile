package nativeapp;

import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.testng.Assert;
import setup.DriverSetup;

import java.util.Arrays;
import java.util.List;

public class ContactManagerApp extends DriverSetup {
    private final String app_package_name = "com.example.android.contactmanager:id/";

    @AndroidFindBy(id = app_package_name + "addContactButton")
    private AndroidElement addContactButton;

    @AndroidFindBy(id = "android:id/title")
    private AndroidElement contactTitle;

    @AndroidFindBy(id = app_package_name + "contactNameEditText")
    private AndroidElement nameField;

    @AndroidFindBy(id = "Target Account")
    private AndroidElement targetAccountTitle;

    @AndroidFindBy(id = "Contact Name")
    private AndroidElement contactNameTitle;

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


    private List<AndroidElement> actualTitlesNames = Arrays.asList(contactNameTitle, targetAccountTitle,
            contactFormTitle, contactEmailForm);

    private List<String> expectedTitlesNames = Arrays.asList("Target Account", "Contact Name",
            "Contact Phone", "Contact Email");


    // ============== Actions methods =================================================

    public void hitAddButton() {
        addContactButton.click();
    }

    public void typeName() {
        nameField.click();
        nameField.sendKeys("Username");
    }

    // ============== Elements checkers =================================================

    public void checkIfTitleVisible() {
        Assert.assertTrue(contactTitle.isDisplayed());
    }

    public void checkIfFormsTitlesVisible() {
        for (AndroidElement titles : actualTitlesNames) {
            Assert.assertTrue(titles.isDisplayed());
        }
        for (int i = 0; i < expectedTitlesNames.size(); i++) {
            Assert.assertEquals(actualTitlesNames.get(i).getText(), expectedTitlesNames.get(i));
        }
    }

    public void checkIfFieldsVisible() {
        Assert.assertTrue(targetAccountSpinner.isDisplayed());
        Assert.assertTrue(contactNameField.isDisplayed());
        Assert.assertTrue(contactFormEditField.isDisplayed());
        Assert.assertTrue(contactEmailField.isDisplayed());
    }
}
