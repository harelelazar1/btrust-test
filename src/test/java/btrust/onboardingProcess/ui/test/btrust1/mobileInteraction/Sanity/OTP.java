package btrust.onboardingProcess.ui.test.btrust1.mobileInteraction.Sanity;


import btrust.onboardingProcess.api.variables.MobileInteraction;
import btrust.onboardingProcess.ui.pagesObject.MobileFormPage;
import btrust.onboardingProcess.ui.pagesObject.OTPPage;
import btrust.onboardingProcess.ui.pagesObject.ScanResultPage;
import btrust.onboardingProcess.ui.test.btrust1.mobileInteraction.MobileInteractionUIBase;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utilities.SuiteListener;

import java.io.IOException;

@Listeners({SuiteListener.class})
public class OTP extends MobileInteractionUIBase {

    ScanResultPage scanResultPage;
    OTPPage otpPage;
    String errorMessage;




    @BeforeMethod(alwaysRun = true)
    public void initSession() throws IOException {
        mobileFlowLink = null;
        scanResultPage = new ScanResultPage(driver);
        otpPage = new OTPPage(driver);
    }


    @Test(description = "Onboarding OTP User reach to the maximum number of trials")
    @Description("User reach to the maximum number of trials for OTP")
    public void t01_onboardingOtpReachingMaxNumberOfTrials() throws IOException {

        try {
            String url = createLinkToFlow(1451);
            driver.get(url);
        } catch (Exception e) {
            failTest("Get link error:" + e.getMessage());
        }

        Assert.assertFalse(otpPage.checkIfSendCodeButtonIsEnable());
        otpPage.enterPhoneNumber("526167710");
        Assert.assertTrue(otpPage.checkIfSendCodeButtonIsEnable());
        otpPage.clickOnSendCodeButton();
        Assert.assertFalse(otpPage.checkIfEnterCodeButtonIsEnable());
        otpPage.enterCodeAndApprove("One","1");
        otpPage.enterCodeAndApprove("Two","2");
        otpPage.enterCodeAndApprove("Three","3");
        otpPage.enterCodeAndApprove("Four","4");
        Assert.assertTrue(otpPage.checkIfEnterCodeButtonIsEnable());
        otpPage.clickOnEnterCodeButton();
        errorMessage = otpPage.GetErrorMessage();
        Assert.assertEquals(errorMessage,"Wrong code");
        otpPage.clickOnEnterCodeButton();
        otpPage.clickOnEnterCodeButton();
        errorMessage = otpPage.GetSecondErrorMessage();
        Assert.assertEquals(errorMessage,"Process failed - You've reached maximum trials");
    }



    @Test(description = "Onboarding OTP User reach to the maximum code resend attempts")
    @Description("User reach to the  maximum code resend attempts for OTP")
    public void t02_onboardingOtpReachingMaxResendAttempts() throws IOException, InterruptedException {

        try {
            String url = createLinkToFlow(1451);
            driver.get(url);
        } catch (Exception e) {
            failTest("Get link error:" + e.getMessage());
        }

        Assert.assertFalse(otpPage.checkIfSendCodeButtonIsEnable());
        otpPage.enterPhoneNumber("526167710");
        Assert.assertTrue(otpPage.checkIfSendCodeButtonIsEnable());
        otpPage.clickOnSendCodeButton();
        Assert.assertFalse(otpPage.checkIfEnterCodeButtonIsEnable());
        otpPage.clickOnResendCodeButton();
        Assert.assertTrue(otpPage.checkIfLoaderDisplay());
        Thread.sleep(30000);
        otpPage.clickOnResendCodeButton();
        Assert.assertTrue(otpPage.checkIfTimerAppearAfterLoader());
        errorMessage = otpPage.GetSecondErrorMessage();
        Assert.assertEquals(errorMessage,"You have reached the limit of code sending attempts. The process will be stopped in");
    }



}
