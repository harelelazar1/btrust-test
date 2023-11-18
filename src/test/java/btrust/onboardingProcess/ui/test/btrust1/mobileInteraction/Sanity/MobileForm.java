package btrust.onboardingProcess.ui.test.btrust1.mobileInteraction.Sanity;


import btrust.onboardingProcess.api.variables.MobileInteraction;
import btrust.onboardingProcess.ui.pagesObject.*;
import btrust.onboardingProcess.ui.test.btrust1.mobileInteraction.MobileInteractionUIBase;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;


public class MobileForm extends MobileInteractionUIBase {

    ScanResultPage scanResultPage;
    MobileFormPage mobileFormPage;
    MobileInteraction mobileInteraction;


    @BeforeMethod(alwaysRun = true)
    public void initSession() throws IOException {
        mobileFlowLink = null;
        scanResultPage = new ScanResultPage(driver);
        mobileFormPage = new MobileFormPage(driver);
        mobileInteraction = new MobileInteraction();
    }


    @Test(description = "Onboarding UI Test for Mobile Form", retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Onboarding UI Test for Mobile Form")
    public void t01_onboardingForMobileForm() throws IOException {

        try {
            String url = createLinkToFlow(1417);
            driver.get(url);
        } catch (Exception e) {
            failTest("Get link error:" + e.getMessage());
        }

        Assert.assertFalse(mobileFormPage.checkIfBackNavigationDisplay());
        mobileFormPage.clickOnNextButton();
        Assert.assertTrue(mobileFormPage.checkIfBackNavigationDisplay());
        mobileFormPage.clickOnNextButton();
        mobileFormPage.clickOnNextButton();
        mobileFormPage.clickOnNextButton();
        mobileFormPage.clickOnNextButton();
        Assert.assertTrue(scanResultPage.resultsTitleForMobileForm());
    }


}
