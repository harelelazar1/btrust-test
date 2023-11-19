package btrust.idm.test.onboardingProcess.qaCompanyTests;

import btrust.onboardingProcess.ui.pagesObject.*;
import btrust.idm.test.onboardingProcess.BaseIdmClientTest;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class OnboardingIDFlowTest extends BaseIdmClientTest {

    @BeforeMethod
    public void createToken() {
        link = createLinkToFlow("https://btrustqa.scanovate.com/", qaApiKey, "446");
        driver.navigate().to(link);
    }

    @Test(description = "E2E bio ID")
    @Description("E2E bio ID")
    public void t01_e2eBioID() throws IOException {
        createToken();
        OCRPage ocrPage = new OCRPage(driver);
        ocrPage.clickOnButton("Scan Israel ID");
        ImageInjectionFunctionPage imageInjectionFunctionPage = new ImageInjectionFunctionPage(driver);
        imageInjectionFunctionPage.scanOCR2Sides("./ocr/bioID/nitzan/front/NitzanBioFront.jpg", "./ocr/bioID/nitzan/back/NitzanBioBack.jpg");

        ScanResultPage scanResult = new ScanResultPage(driver);
        Assert.assertTrue(scanResult.progressBarIsDisplayed());
        Assert.assertEquals(scanResult.resultsTitle(), "Scan Results");
        Assert.assertEquals(scanResult.resultFaceImageDescription(), "Document image");
        Assert.assertTrue(scanResult.faceImageIsDisplayed());
        Assert.assertEquals(scanResult.results("bio id", "Citizenship"), "אזרהות ישראלית");
        Assert.assertEquals(scanResult.results("bio id", "Last name"), "שוקר");
        Assert.assertEquals(scanResult.results("bio id", "First name"), "ניצן");
        Assert.assertEquals(scanResult.results("bio id", "ID Number"), "200761625");
        Assert.assertEquals(scanResult.results("bio id", "Date of birth"), "21.08.1988");
        Assert.assertEquals(scanResult.results("bio id", "Issuing Date"), "23.5.2019");
        Assert.assertEquals(scanResult.results("bio id", "Expiration Date"), "20.05.2029");
        Assert.assertEquals(scanResult.results("bio id", "Place of birth"), "ישראל");
        Assert.assertEquals(scanResult.results("bio id", "Gender"), "זכר");

        scanResult.clickOnButton("Continue");

        ScanLivenessPage scanLiveness = new ScanLivenessPage(driver);
        Assert.assertTrue(scanLiveness.progressBar());
        Assert.assertTrue(scanLiveness.scanLivenessImage());
        Assert.assertEquals(scanLiveness.scanLivenessTitle(), "Liveness Check");
        Assert.assertEquals(scanLiveness.scanLivenessSubTitle(), "Before we start make sure that:");
        Assert.assertEquals(scanLiveness.scanLivenessDescription(), "The phone is at face level | There are good light conditions");

        scanLiveness.startButton();
        imageInjectionFunctionPage.scanLiveness("./liveness/bar/barCenter.jpg");

        FaceCompareResultsPage faceCompareResults = new FaceCompareResultsPage(driver);
        Assert.assertEquals(faceCompareResults.faceCompareTitle(), "Face Compare Results");
        Assert.assertTrue(faceCompareResults.livenessPictureIsDisplayed());
        Assert.assertEquals(faceCompareResults.livenessDescription(), "Liveness Check");
        Assert.assertTrue(faceCompareResults.ocrPicture());
        Assert.assertEquals(faceCompareResults.ocrDescription(), "Document Image");
        Assert.assertTrue(faceCompareResults.faceMatchIcon());
        assertEquals(faceCompareResults.faceMatchTitle(), "Face doesn't Match");
    }

    @Test(description = "E2E blue ID")
    @Description("E2E blue ID")
    public void t02_e2eBlueID() throws IOException {
        OCRPage ocrPage = new OCRPage(driver);
        Assert.assertTrue(ocrPage.progressBar());
        Assert.assertTrue(ocrPage.ocrImage());
        Assert.assertEquals(ocrPage.OCRTitleIsDisplayed(), "Document Scanning");
        Assert.assertEquals(ocrPage.subTitleOCRPage(), "Before you start make sure that:");
        Assert.assertEquals(ocrPage.ocrDescription(), "You possess the document | The document is clean | Good lightning conditions");
        Assert.assertTrue(ocrPage.startScanButtonEnable());
        Assert.assertTrue(ocrPage.version());
        ocrPage.clickOnButton("Scan Israel ID");

        ImageInjectionFunctionPage imageInjectionFunctionPage = new ImageInjectionFunctionPage(driver);
        imageInjectionFunctionPage.scanOCR("./ocr/blueID/liad/blueID_color.jpg", "front");

        ScanResultPage scanResult = new ScanResultPage(driver);
        Assert.assertTrue(scanResult.progressBarIsDisplayed());
        Assert.assertEquals(scanResult.resultsTitle(), "Scan Results");
        Assert.assertEquals(scanResult.resultFaceImageDescription(), "Document image");
        Assert.assertTrue(scanResult.faceImageIsDisplayed());
        Assert.assertEquals(scanResult.results("blue id", "Citizenship"), "אזרחות ישראלית");
        Assert.assertEquals(scanResult.results("blue id", "Last name"), "טובי");
        Assert.assertEquals(scanResult.results("blue id", "First name"), "ליעד");
        Assert.assertEquals(scanResult.results("blue id", "ID Number"), "307922328");
        Assert.assertEquals(scanResult.results("blue id", "Date of birth"), "17.01.1993");
        Assert.assertEquals(scanResult.results("blue id", "Issuing Date"), "28.01.2015");
        Assert.assertEquals(scanResult.results("blue id", "Expiration Date"), "28.01.2025");
        Assert.assertEquals(scanResult.results("blue id", "Place of birth"), "ישראל");
        Assert.assertEquals(scanResult.results("blue id", "Gender"), "זכר");
        scanResult.clickOnButton("Continue");

        ScanLivenessPage scanLiveness = new ScanLivenessPage(driver);
        Assert.assertTrue(scanLiveness.progressBar());
        Assert.assertTrue(scanLiveness.scanLivenessImage());
        Assert.assertEquals(scanLiveness.scanLivenessTitle(), "Liveness Check");
        Assert.assertEquals(scanLiveness.scanLivenessSubTitle(), "Before we start make sure that:");
        Assert.assertEquals(scanLiveness.scanLivenessDescription(), "The phone is at face level | There are good light conditions");

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

    @Test(description = "E2E green ID")
    @Description("E2E green ID")
    public void t03_e2eGreenID() throws IOException {
        OCRPage ocrPage = new OCRPage(driver);
        Assert.assertTrue(ocrPage.progressBar());
        Assert.assertTrue(ocrPage.ocrImage());
        Assert.assertEquals(ocrPage.OCRTitleIsDisplayed(), "Document Scanning");
        Assert.assertEquals(ocrPage.subTitleOCRPage(), "Before you start make sure that:");
        Assert.assertEquals(ocrPage.ocrDescription(), "You possess the document | The document is clean | Good lightning conditions");
        Assert.assertTrue(ocrPage.startScanButtonEnable());
        Assert.assertTrue(ocrPage.version());
        ocrPage.clickOnButton("Scan Israel ID");
        ImageInjectionFunctionPage imageInjectionFunctionPage = new ImageInjectionFunctionPage(driver);
        imageInjectionFunctionPage.scanOCR("./ocr/greenID/harel/harel_Green_Id.jpg", "front");

        ScanResultPage scanResult = new ScanResultPage(driver);
        Assert.assertTrue(scanResult.progressBarIsDisplayed());
        Assert.assertEquals(scanResult.resultsTitle(), "Scan Results");
        Assert.assertEquals(scanResult.resultFaceImageDescription(), "Document image");
        Assert.assertTrue(scanResult.faceImageIsDisplayed());
        Assert.assertEquals(scanResult.results("green id", "Last name"), "אלעזר");
        Assert.assertEquals(scanResult.results("green id", "First name"), "הראל");
        Assert.assertEquals(scanResult.results("green id", "ID Number"), "300864550");
        Assert.assertEquals(scanResult.results("green id", "Date of birth"), "30.06.198");
        Assert.assertEquals(scanResult.results("green id", "Issuing Date"), "11.01.2010");
        Assert.assertEquals(scanResult.results("green id", "Place of birth"), "ישראל");
        Assert.assertEquals(scanResult.results("green id", "Gender"), "זכר");
        scanResult.clickOnButton("Continue");

        ScanLivenessPage scanLiveness = new ScanLivenessPage(driver);
        Assert.assertTrue(scanLiveness.progressBar());
        Assert.assertTrue(scanLiveness.scanLivenessImage());
        Assert.assertEquals(scanLiveness.scanLivenessTitle(), "Liveness Check");
        Assert.assertEquals(scanLiveness.scanLivenessSubTitle(), "Before we start make sure that:");
        Assert.assertEquals(scanLiveness.scanLivenessDescription(), "The phone is at face level | There are good light conditions");

        scanLiveness.startButton();
        imageInjectionFunctionPage.scanLiveness("./liveness/harel/harelLiveness.jpeg");

        FaceCompareResultsPage faceCompareResults = new FaceCompareResultsPage(driver);
        Assert.assertEquals(faceCompareResults.faceCompareTitle(), "Face Compare Results");
        Assert.assertTrue(faceCompareResults.livenessPictureIsDisplayed());
        Assert.assertEquals(faceCompareResults.livenessDescription(), "Liveness Check");
        Assert.assertTrue(faceCompareResults.ocrPicture());
        Assert.assertEquals(faceCompareResults.ocrDescription(), "Document Image");
        Assert.assertTrue(faceCompareResults.faceMatchIcon());
        Assert.assertEquals(faceCompareResults.faceMatchTitle(), "Face Match");
    }

    @Test(description = "Scan again")
    @Description("Click on scan again button and scan again")
    public void t04_scanAgain() throws IOException {
        OCRPage ocrPage = new OCRPage(driver);
        ocrPage.clickOnButton("Scan Israel ID");
        ImageInjectionFunctionPage imageInjectionFunctionPage = new ImageInjectionFunctionPage(driver);
        imageInjectionFunctionPage.scanOCR2Sides("./ocr/bioID/nitzan/front/NitzanBioFront.jpg", "./ocr/bioID/nitzan/back/NitzanBioBack.jpg");
        ScanResultPage scanResult = new ScanResultPage(driver);
        scanResult.clickOnButton("Scan again");
        imageInjectionFunctionPage.scanOCR2Sides("./ocr/bioID/nitzan/front/NitzanBioFront.jpg", "./ocr/bioID/nitzan/back/NitzanBioBack.jpg");

        Assert.assertEquals(scanResult.resultsTitle(), "Scan Results");
        Assert.assertEquals(scanResult.resultFaceImageDescription(), "Document image");

        scanResult.clickOnButton("Continue");

        ScanLivenessPage scanLiveness = new ScanLivenessPage(driver);
        Assert.assertTrue(scanLiveness.progressBar());
        Assert.assertTrue(scanLiveness.scanLivenessImage());
        Assert.assertEquals(scanLiveness.scanLivenessTitle(), "Liveness Check");
        Assert.assertEquals(scanLiveness.scanLivenessSubTitle(), "Before we start make sure that:");
        Assert.assertEquals(scanLiveness.scanLivenessDescription(), "The phone is at face level | There are good light conditions");

        scanLiveness.startButton();
        imageInjectionFunctionPage.scanLiveness("./liveness/nitzan/nitzanCenter.jpg");

//        FaceCompareResultsPage faceCompareResults = new FaceCompareResultsPage(driver);
//        Assert.assertEquals(faceCompareResults.faceCompareTitle(), "Face Compare Results");
//        Assert.assertTrue(faceCompareResults.livenessPictureIsDisplayed());
//        Assert.assertEquals(faceCompareResults.livenessDescription(), "Liveness Check");
//        Assert.assertTrue(faceCompareResults.ocrPicture());
//        Assert.assertEquals(faceCompareResults.ocrDescription(), "Document Image");
//        Assert.assertTrue(faceCompareResults.faceMatchIcon());
//        Assert.assertEquals(faceCompareResults.faceMatchTitle(), "Face Match");
    }
}