package btrust.onboardingProcess.ui.test.btrust1.mobileInteraction.checkServicesUp;

import btrust.onboardingProcess.ui.pagesObject.*;
import btrust.onboardingProcess.ui.test.btrust1.mobileInteraction.MobileInteractionUIBase;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LivenessServiceUp extends MobileInteractionUIBase {


    OCRPage ocrPage;
    ImageInjectionFunctionPage imageInjectionFunctionPage;
    ScanLivenessPage scanLivenessPage;

    @BeforeMethod(alwaysRun = true)
    public void initSession() {
        mobileFlowLink = null;
        ocrPage = new OCRPage(driver);
        imageInjectionFunctionPage = new ImageInjectionFunctionPage(driver);
        scanLivenessPage = new ScanLivenessPage(driver);

    }

    @Test(description = "Check if the Liveness Service is up")
    @Description("Check if the Liveness Service is up")
    public void t01_checkIfLivenessServiceUp_Liveness() throws IOException {
        driver.get(createLinkToFlow(231));
        assertTrue(ocrPage.ocrImage());
        ocrPage.chooseLanguage("English");
        assertTrue(scanLivenessPage.scanLivenessImage());
        assertEquals(scanLivenessPage.scanLivenessTitle(), "Liveness Check");
        assertEquals(scanLivenessPage.scanLivenessSubTitle(), "Before we start make sure that:");
        assertEquals(scanLivenessPage.scanLivenessDescription(), "The phone is at face level | There are good light conditions");
        scanLivenessPage.startButton();
        assertTrue(imageInjectionFunctionPage.stageCounterDisplayed("Liveness"));
        imageInjectionFunctionPage.clickOnExitButton();
    }

}
