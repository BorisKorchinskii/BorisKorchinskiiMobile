package hw2;

import hw1.DriverSetup;
import nativeapp.ContactManagerApp;
import org.testng.annotations.Test;
import setup.SelectProperties;

@Test(groups = "native")
public class NativeTest extends DriverSetup {
    private ContactManagerApp contactManagerAppPage;

    protected NativeTest() {
        super(SelectProperties.NATIVE);
    }

    @Test(description = "Hit \"Add Contact\" and verify UI content")
    public void contactManagerNativeTest() throws Exception {
        contactManagerAppPage.hitAddButton();
        contactManagerAppPage.checkIfFormsTitlesVisible();
        contactManagerAppPage.checkIfTitleVisible();
        contactManagerAppPage.checkIfFieldsVisible();
    }
}
