package hw2;

import nativeapp.IanaLandingPage;
import org.testng.annotations.Test;
import setup.SelectProperties;

@Test(groups = "web")
public class WebTest extends Hooks {
    private IanaLandingPage ianaLandingPage;

    protected WebTest() {
        super(SelectProperties.WEB);
    }

    @Test(description = "Navigate to Iana page and verify content")
    public void navigateToPage() throws Exception {

        ianaLandingPage.navigateToPage();
        ianaLandingPage.checkifPageAvailable();
        ianaLandingPage.checkPanelsVisible();
    }
}
