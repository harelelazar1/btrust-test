package btrust.onboardingProcess.ui.test.btrust1.mobileInteraction.Sanity;

import btrust.onboardingProcess.ui.pagesObject.*;
import btrust.onboardingProcess.ui.test.btrust1.mobileInteraction.MobileInteractionUIBase;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import selenium.Injection;
import selenium.ocr.pageObject.newService.MainPage;
import utilities.RetryAnalyzer;
import utilities.SuiteListener;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Listeners({SuiteListener.class})
public class Liveness extends MobileInteractionUIBase {
    CompletedPage completedPage;
    OCRPage ocrPage;
    ImageInjectionFunctionPage imageInjectionFunctionPage;
    ScanResultPage scanResultPage;
    ScanLivenessPage scanLivenessPage;
    FaceCompareResultsPage faceCompareResults;
    MainPage mainPage;

    @BeforeMethod(alwaysRun = true)
    public void initSession() {
        mobileFlowLink = null;
        response = null;
        jsonPath = null;
        sessionId = null;
        ocrPage = new OCRPage(driver);
        imageInjectionFunctionPage = new ImageInjectionFunctionPage(driver);
        scanResultPage = new ScanResultPage(driver);
        scanLivenessPage = new ScanLivenessPage(driver);
        faceCompareResults = new FaceCompareResultsPage(driver);
        completedPage = new CompletedPage(driver);
        mainPage = new MainPage(driver);
    }

    @Test(description = "Onboarding UI Test Liveness process", retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Onboarding UI Test Liveness process")
    public void t01_liveness() throws IOException {
        driver.get(createLinkToFlow(231));
        ocrPage.chooseLanguage("English");
        assertEquals(scanLivenessPage.scanLivenessTitle(), "Liveness Check");
        assertEquals(scanLivenessPage.scanLivenessSubTitle(), "Before we start make sure that:");
        assertEquals(scanLivenessPage.scanLivenessDescription(), "The phone is at face level | There are good light conditions");
        scanLivenessPage.startButton();
        imageInjectionFunctionPage.scanLiveness("./liveness/bar/bar_new.jpg");
        Assert.assertEquals(completedPage.getCompleteText(), "Process ended");
    }
}
