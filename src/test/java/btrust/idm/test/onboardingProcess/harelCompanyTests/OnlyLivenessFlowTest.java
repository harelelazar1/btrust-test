package btrust.idm.test.onboardingProcess.harelCompanyTests;

import btrust.onboardingProcess.ui.pagesObject.FaceCompareResultsPage;
import btrust.onboardingProcess.ui.pagesObject.ImageInjectionFunctionPage;
import btrust.onboardingProcess.ui.pagesObject.ScanLivenessPage;
import btrust.idm.test.onboardingProcess.BaseIdmClientTest;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class OnlyLivenessFlowTest extends BaseIdmClientTest {

    @BeforeMethod
    public void createToken() {
        link = createLinkToFlow("https://btrustqa.scanovate.com/", harelApiKey, "209");
        driver.navigate().to(link);
    }

    @Test(description = "Flow with only liveness")
    @Description("Perform liveness")
    public void t01_e2eHarelFlow4ID() throws IOException {
        ScanLivenessPage scanLiveness = new ScanLivenessPage(driver);
 //       Assert.assertTrue(scanLiveness.progressBar());
        Assert.assertTrue(scanLiveness.scanLivenessImage());
        Assert.assertEquals(scanLiveness.scanLivenessTitle(), "בדיקת חיות");
        Assert.assertEquals(scanLiveness.scanLivenessSubTitle(), "לפני שנתחיל צריך לוודא ש:");
        Assert.assertEquals(scanLiveness.scanLivenessDescription(), "המכשיר ממש מול הפנים | תנאי תאורה טובים");
        scanLiveness.startButton();
        ImageInjectionFunctionPage imageInjectionFunctionPage = new ImageInjectionFunctionPage(driver);
        imageInjectionFunctionPage.scanLiveness("./liveness/bar/barCenter.jpg");

        FaceCompareResultsPage faceCompareResults = new FaceCompareResultsPage(driver);
        String processEndedText = faceCompareResults.processEndedText();
        Assert.assertEquals(processEndedText, "process-ended");
    }
}