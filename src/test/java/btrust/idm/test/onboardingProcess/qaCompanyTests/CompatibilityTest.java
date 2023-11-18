package btrust.idm.test.onboardingProcess.qaCompanyTests;

import btrust.onboardingProcess.ui.pagesObject.CompatibilityPage;
import btrust.idm.test.onboardingProcess.BaseIdmClientTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CompatibilityTest extends BaseIdmClientTest {

    @BeforeClass
    @Override
    public void setup(ITestContext testContext) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("disable-infobars");
        driver = new ChromeDriver(options);
        testContext.setAttribute("WebDriver", this.driver);
        driver.manage().window().maximize();
        link = createLinkToFlow("https://btrustqa.scanovate.com/", qaApiKey, "2");
        driver.navigate().to(link);
    }

    @Test(enabled = false, description = "Compatibility browser")
    @Description("Enter btrust client when the resolution screen is desktop screen")
    public void t01_compatibilityBrowser() {
        CompatibilityPage compatibilityPage = new CompatibilityPage(driver);
        Assert.assertEquals(compatibilityPage.popupTitle(), "This browser is not supported");
        Assert.assertEquals(compatibilityPage.popupDescriptionIsDisplayed(), "Please open the link on your mobile in one of the following browsers");
        Assert.assertTrue(compatibilityPage.compatibilityBrowserIcons());
        Assert.assertTrue(compatibilityPage.copyLinkButtonIsDisplayed());
    }
}