package hw2;

import nativeapp.ContactManagerApp;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import setup.Hooks;

import java.io.IOException;

@Test(groups = "native")
public class NativeTest extends Hooks {

    private ContactManagerApp contactManagerAppPage;

    private NativeTest() throws IOException {
        super();
    }

    @BeforeClass
    public void beforeClass() throws Exception {
        contactManagerAppPage = PageFactory.initElements(driver(), ContactManagerApp.class);
    }

    @AfterClass
    public void afterClass() {
        contactManagerAppPage.closeApp();
    }

    @Test(description = "Hit \"Add Contact\" and verify UI content")
    public void contactManagerNativeTest() throws Exception {
        contactManagerAppPage.checkAddContactButtonVisible();
        contactManagerAppPage.hitAddButton();
        contactManagerAppPage.typeName();
        contactManagerAppPage.checkIfTitleVisible();
        contactManagerAppPage.checkIfFieldsVisible();
        contactManagerAppPage.checkKeyboardPresence(driver());
    }
}
