package btrust.idm.test.onboardingProcess.qaCompanyTests;

import btrust.onboardingProcess.ui.pagesObject.*;
import btrust.idm.test.onboardingProcess.BaseIdmClientTest;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class OnboardingRegulaFlowTest extends BaseIdmClientTest {

    @BeforeMethod
    public void createToken() {
        link = createLinkToFlow("https://btrustqa.scanovate.com/", qaApiKey, "283");
        driver.navigate().to(link);
    }

    @Test(description = "Card capture flow")
    @Description("Scan US-DL (card capture), scan liveness and check that all the data appear is console system")
    public void t01_cardCaptureFlow() throws IOException, InterruptedException {
        CardCapturePage cardCapturePage = new CardCapturePage(driver);
        Assert.assertEquals(cardCapturePage.cardCaptureTitle(), "Card Capture");
        ImageInjectionFunctionPage imageInjectionFunctionPage = new ImageInjectionFunctionPage(driver);
        imageInjectionFunctionPage.injectCardCapture("./ocr/usaDL/us_front.jpg");
        cardCapturePage.okButton();
        cardCapturePage.videoFrame();
        PopupPage popupPage = new PopupPage(driver);
        popupPage.popupButton();
        imageInjectionFunctionPage.injectCardCapture("./ocr/usaDL/us_back.jpg");
        cardCapturePage.videoFrame();
        ScanResultPage scanResultPage = new ScanResultPage(driver);
        Assert.assertTrue(scanResultPage.faceImageIsDisplayed());
        Assert.assertEquals(scanResultPage.resultsTitle(), "Scan Results");
        Assert.assertEquals(scanResultPage.resultFaceImageDescription(), "Document image");
        Assert.assertEquals(scanResultPage.results("card capture", "First name"), "STEVEN MICHAEL");
        Assert.assertEquals(scanResultPage.results("card capture", "Last name"), "CARROLL");
        Assert.assertEquals(scanResultPage.results("card capture", "Document NO."), "A65939698");
        Assert.assertEquals(scanResultPage.results("card capture", "Issued by"), "United States");
        Assert.assertEquals(scanResultPage.results("card capture", "Sex"), "M");
        Assert.assertEquals(scanResultPage.results("card capture", "Address"), "715 VERMILLION DR NE LEESBURG VA 20176-3622");
        scanResultPage.clickOnButton("Continue");

        ScanLivenessPage scanLivenessPage = new ScanLivenessPage(driver);
        scanLivenessPage.startButton();
        imageInjectionFunctionPage.scanLiveness("./liveness/liad/liad_face.jpg");

        FaceCompareResultsPage faceCompareResults = new FaceCompareResultsPage(driver);
        Assert.assertEquals(faceCompareResults.faceCompareTitle(), "Face Compare Results");
        Assert.assertTrue(faceCompareResults.livenessPictureIsDisplayed());
        Assert.assertEquals(faceCompareResults.livenessDescription(), "Liveness Check");
        Assert.assertTrue(faceCompareResults.ocrPicture());
        Assert.assertEquals(faceCompareResults.ocrDescription(), "Document Image");
        Assert.assertTrue(faceCompareResults.faceMatchIcon());
        Assert.assertEquals(faceCompareResults.faceMatchTitle(), "Face Match");
    }
}