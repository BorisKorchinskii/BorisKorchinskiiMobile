package homework2.nativeTests;

import nativeapp.ContactManagerApp;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import setup.Driver;

import java.io.IOException;


@Test(groups = "native")
public class NativeTest extends Driver {

    private ContactManagerApp contactManagerAppPage;

    protected NativeTest() throws IOException {
        super();
    }

    @BeforeSuite(description = "Prepare driver to run tests")
    public void setUp() throws Exception {
        prepareDriver();
        System.out.println("Driver prepared");
        contactManagerAppPage = new ContactManagerApp(driverSingle);
    }

    @AfterSuite(description = "Close driver on tests complete")
    public void tearDown() throws Exception {
        driverSingle.quit();
        System.out.println("Driver closed");
    }

    @Test(description = "Hit Add Contact, type name and verify UI content")
    public void contactManagerNativeTest() throws Exception {
        contactManagerAppPage.checkIfTitleVisible();
        contactManagerAppPage.checkAddContactButtonVisible();
        contactManagerAppPage.hitAddButton();
        contactManagerAppPage.typeName();
        contactManagerAppPage.checkIfFieldsVisible();
        contactManagerAppPage.checkKeyboardPresence(driverSingle);
    }
}
