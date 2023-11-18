package btrust.idm.test.onboardingProcess.qaCompanyTests;

import btrust.onboardingProcess.ui.pagesObject.*;
import btrust.idm.test.onboardingProcess.BaseIdmClientTest;
import io.qameta.allure.Description;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SecurityTest extends BaseIdmClientTest {

    @Test(enabled = false, description = "Save session")
    @Description("Crete token, perform OCR, close the session, go back to session and check that the session is saved")
    public void t01_saveSession() throws InterruptedException, IOException {
        link = createLinkToFlow("https://btrustqa.scanovate.com/", qaApiKey, "446");
        driver.navigate().to(link);

        OCRPage ocrPage = new OCRPage(driver);
        ocrPage.clickOnButton("Scan Israel ID");
        ImageInjectionFunctionPage imageInjectionFunctionPage = new ImageInjectionFunctionPage(driver);
        imageInjectionFunctionPage.scanOCR("./ocr/blueID/liad/blueID_color.jpg", "front");

        ScanResultPage scanResult = new ScanResultPage(driver);
        Assert.assertTrue(scanResult.progressBarIsDisplayed());
        Assert.assertEquals(scanResult.resultsTitle(), "Scan Results");
        Assert.assertEquals(scanResult.resultFaceImageDescription(), "Document image");
        Assert.assertTrue(scanResult.faceImageIsDisplayed());
        scanResult.clickOnButton("Continue");

        ScanLivenessPage scanLiveness = new ScanLivenessPage(driver);
        Assert.assertTrue(scanLiveness.progressBar());
        Assert.assertTrue(scanLiveness.scanLivenessImage());
        Assert.assertEquals(scanLiveness.scanLivenessTitle(), "Liveness Check");
        Assert.assertEquals(scanLiveness.scanLivenessSubTitle(), "Before we start make sure that:");
        Assert.assertEquals(scanLiveness.scanLivenessDescription(), "The phone is at face level | There are good light conditions");

        driver.quit();
        Thread.sleep(2000);
        Map<String, String> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", "iPhone X");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("use-fake-ui-for-media-stream");
        options.addArguments("use-fake-device-for-media-stream");
        options.addArguments("allow-file-access-from-files");
        options.addArguments("disable-infobars");
        options.addArguments("--incognito");
        options.setExperimentalOption("mobileEmulation", mobileEmulation);
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.navigate().to(link);

        ScanLivenessPage scanLivenessPage = new ScanLivenessPage(driver);
        Assert.assertTrue(scanLivenessPage.progressBar());
        Assert.assertTrue(scanLivenessPage.scanLivenessImage());
        Assert.assertEquals(scanLivenessPage.scanLivenessTitle(), "Liveness Check");
        Assert.assertEquals(scanLivenessPage.scanLivenessSubTitle(), "Before we start make sure that:");
        Assert.assertEquals(scanLivenessPage.scanLivenessDescription(), "The phone is at face level | There are good light conditions");
        scanLivenessPage.startButton();
        ImageInjectionFunctionPage imageInjectionFunctionPage1 = new ImageInjectionFunctionPage(driver);
        imageInjectionFunctionPage1.scanLiveness("./liveness/liad/liad_face.jpg");

        FaceCompareResultsPage faceCompareResults = new FaceCompareResultsPage(driver);
        Assert.assertEquals(faceCompareResults.faceCompareTitle(), "Face Compare Results");
        Assert.assertTrue(faceCompareResults.livenessPictureIsDisplayed());
        Assert.assertEquals(faceCompareResults.livenessDescription(), "Liveness Check");
        Assert.assertTrue(faceCompareResults.ocrPicture());
        Assert.assertEquals(faceCompareResults.ocrDescription(), "Document Image");
        Assert.assertTrue(faceCompareResults.faceMatchIcon());
        Assert.assertEquals(faceCompareResults.faceMatchTitle(), "Face Match");
    }

    @Test(enabled = false, description = "lockSession")
    @Description("Enter to finish session and check that the session not start again")
    public void t02_lockSession() {
        driver.navigate().to(link);
        FaceCompareResultsPage faceCompareResults = new FaceCompareResultsPage(driver);
        Assert.assertEquals(faceCompareResults.faceCompareTitle(), "Face Compare Results");
        Assert.assertTrue(faceCompareResults.livenessPictureIsDisplayed());
        Assert.assertEquals(faceCompareResults.livenessDescription(), "Liveness Check");
        Assert.assertTrue(faceCompareResults.ocrPicture());
        Assert.assertEquals(faceCompareResults.ocrDescription(), "Document Image");
        Assert.assertTrue(faceCompareResults.faceMatchIcon());
        Assert.assertEquals(faceCompareResults.faceMatchTitle(), "Face Match");
    }

    @Test(description = "Refresh session")
    @Description("Refresh the browser every phase in the session and check that the user is back to session")
    public void t03_refreshSession() throws IOException {
        link = createLinkToFlow("https://btrustqa.scanovate.com/", qaApiKey, "446");
        driver.navigate().to(link);
        OCRPage ocrPage = new OCRPage(driver);
        Assert.assertTrue(ocrPage.startScanButtonEnable());
        driver.navigate().refresh();
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
       //driver.navigate().refresh();
        scanResult.clickOnButton("Continue");


        ScanLivenessPage scanLiveness = new ScanLivenessPage(driver);
        Assert.assertTrue(scanLiveness.progressBar());
        Assert.assertTrue(scanLiveness.scanLivenessImage());
        Assert.assertEquals(scanLiveness.scanLivenessTitle(), "Liveness Check");
        Assert.assertEquals(scanLiveness.scanLivenessSubTitle(), "Before we start make sure that:");
        Assert.assertEquals(scanLiveness.scanLivenessDescription(), "The phone is at face level | There are good light conditions");

        driver.navigate().refresh();

        ScanLivenessPage scanLivenessPage = new ScanLivenessPage(driver);
        Assert.assertTrue(scanLivenessPage.progressBar());
        Assert.assertTrue(scanLivenessPage.scanLivenessImage());
        Assert.assertEquals(scanLivenessPage.scanLivenessTitle(), "Liveness Check");
        Assert.assertEquals(scanLivenessPage.scanLivenessSubTitle(), "Before we start make sure that:");
        Assert.assertEquals(scanLivenessPage.scanLivenessDescription(), "The phone is at face level | There are good light conditions");

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

    @Test(description = "Token expired")
    @Description("Crete token and check that the token expired after 2 min")
    public void t04_tokenExpired() throws InterruptedException {
        link = createLinkToFlow("https://btrustqa.scanovate.com/", qaApiKey, "446");
        Thread.sleep(125000);
        driver.navigate().to(link);

        BadRequestPage badRequestPage = new BadRequestPage(driver);
        Assert.assertTrue(badRequestPage.badRequestImageIsDisplayed());
        Assert.assertEquals(badRequestPage.badRequestTitle(), "Link expired");
        Assert.assertEquals(badRequestPage.badRequestDescription(), "Please request a new link");
    }
}