package btrust.idm.test.onboardingProcess.qaCompanyTests;

import btrust.onboardingProcess.ui.pagesObject.*;
import btrust.idm.test.onboardingProcess.BaseIdmClientTest;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class OnboardingWorkflowMRZTest extends BaseIdmClientTest {

    @BeforeMethod
    public void createToken() {
        link = createLinkToFlow("https://btrustqa.scanovate.com/", qaApiKey, "446");
        driver.navigate().to(link);
    }

    @Test(description = "End to End - old MRZ")
    @Description("Scan old mrz and check that created new case in the console system")
    public void t01_e2eOldMRZ() throws IOException {
        OCRPage ocrPage = new OCRPage(driver);
        ocrPage.clickOnButton("Scan Passport");
        ImageInjectionFunctionPage imageInjectionFunctionPage = new ImageInjectionFunctionPage(driver);
        imageInjectionFunctionPage.scanOCR("./ocr/mrz/liad/liadMrzNewImage.jpg", "front");
        ScanResultPage scanResult = new ScanResultPage(driver);
        Assert.assertTrue(scanResult.progressBarIsDisplayed());
        Assert.assertEquals(scanResult.resultsTitle(), "Scan Results");
        Assert.assertEquals(scanResult.resultFaceImageDescription(), "Document image");
        Assert.assertTrue(scanResult.faceImageIsDisplayed());
        Assert.assertEquals(scanResult.results("MRZ", "Issuing country code"), "ISR");
        Assert.assertEquals(scanResult.results("MRZ", "Last name"), "TOBI");
        Assert.assertEquals(scanResult.results("MRZ", "First name"), "LIAD");
        Assert.assertEquals(scanResult.results("MRZ", "Passport number"), "21308777");
        Assert.assertEquals(scanResult.results("MRZ", "Nationality code"), "ISR");
        Assert.assertEquals(scanResult.results("MRZ", "Date of birth"), "17/01/93");
        Assert.assertEquals(scanResult.results("MRZ", "Gender"), "Male");
        Assert.assertEquals(scanResult.results("MRZ", "Expiration Date"), "27/10/23");
        Assert.assertEquals(scanResult.results("MRZ", "ID Number"), "3-0792232-8");
        scanResult.clickOnButton("Continue");
        ScanLivenessPage scanLiveness = new ScanLivenessPage(driver);
        scanLiveness.startButton();
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

    @Test(description = "End to End - new MRZ")
    @Description("Scan new mrz and check that created new case in the console system")
    public void t02_e2eNewMRZ() throws IOException {
        OCRPage ocrPage = new OCRPage(driver);
        ocrPage.clickOnButton("Scan Passport");
        ImageInjectionFunctionPage imageInjectionFunctionPage = new ImageInjectionFunctionPage(driver);
        imageInjectionFunctionPage.scanOCR("./ocr/mrz/avner/avner2.jpg", "front");
        ScanResultPage scanResult = new ScanResultPage(driver);
        Assert.assertTrue(scanResult.progressBarIsDisplayed());
        Assert.assertEquals(scanResult.resultsTitle(), "Scan Results");
        Assert.assertEquals(scanResult.resultFaceImageDescription(), "Document image");
        Assert.assertTrue(scanResult.faceImageIsDisplayed());
        Assert.assertEquals(scanResult.results("MRZ", "Issuing country code"), "ISR");
        Assert.assertEquals(scanResult.results("MRZ", "Last name"), "BLUER");
        Assert.assertEquals(scanResult.results("MRZ", "First name"), "AVNER");
        Assert.assertEquals(scanResult.results("MRZ", "Gender"), "Male");
        Assert.assertEquals(scanResult.results("MRZ", "Nationality code"), "ISR");
        Assert.assertEquals(scanResult.results("MRZ", "Date of birth"), "02/08/80");
        Assert.assertEquals(scanResult.results("MRZ", "Passport number"), "30266026");
        Assert.assertEquals(scanResult.results("MRZ", "Expiration Date"), "10/06/24");
        Assert.assertEquals(scanResult.results("MRZ", "ID Number"), "0-4037370-6");
        scanResult.clickOnButton("Continue");
        ScanLivenessPage scanLiveness = new ScanLivenessPage(driver);
        scanLiveness.startButton();
        imageInjectionFunctionPage.scanLiveness("./liveness/avner/avnerCenter.jpg");

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