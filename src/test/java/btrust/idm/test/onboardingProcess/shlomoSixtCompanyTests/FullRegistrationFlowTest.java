package btrust.idm.test.onboardingProcess.shlomoSixtCompanyTests;

import btrust.onboardingProcess.ui.pagesObject.*;
import btrust.idm.test.onboardingProcess.BaseIdmClientTest;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class FullRegistrationFlowTest extends BaseIdmClientTest {

    @BeforeMethod
    public void createToken() {
        link = createLinkToFlow("https://btrustqa.scanovate.com/", shlomoApiKey, "289");
        driver.navigate().to(link);
    }

    @Test(description = "Full registration")
    @Description("Scan DL, scan face, check that the image from the dl and the face are compare and check that created new case in console")
    public void t01_e2eFullLogin() throws IOException {
        OCRPage ocr = new OCRPage(driver);
        ocr.clickOnButton("סריקת רישיון נהיגה");
        ImageInjectionFunctionPage imageInjectionFunctionPage = new ImageInjectionFunctionPage(driver);
        imageInjectionFunctionPage.scanOCR2Sides("./ocr/dl/LiadDLFront.jpg", "./ocr/dl/LiadDLBack.jpg");

        ScanLivenessPage scanLivenessPage = new ScanLivenessPage(driver);
        scanLivenessPage.startButton();
        imageInjectionFunctionPage.scanLiveness("./liveness/liad/liad_face.jpg");

        FaceCompareResultsPage faceCompareResults = new FaceCompareResultsPage(driver);
        Assert.assertEquals(faceCompareResults.faceCompareTitle(), "תוצאות השוואת פנים");
        Assert.assertTrue(faceCompareResults.livenessPictureIsDisplayed());
        Assert.assertEquals(faceCompareResults.livenessDescription(), "בדיקת חיות");
        Assert.assertTrue(faceCompareResults.ocrPicture());
        Assert.assertEquals(faceCompareResults.ocrDescription(), "תמונה מתעודה");
        Assert.assertTrue(faceCompareResults.faceMatchIcon());
        Assert.assertEquals(faceCompareResults.faceMatchTitle(), "הפנים זהות");
    }

    @Test(description = "Invalid expired date")
    @Description("Scan DL with invalid expired date and check that error message is displayed")
    public void t02_e2eLogin() throws IOException {
        OCRPage ocr = new OCRPage(driver);
        ocr.clickOnButton("סריקת רישיון נהיגה");
        ImageInjectionFunctionPage imageInjectionFunctionPage = new ImageInjectionFunctionPage(driver);
        imageInjectionFunctionPage.scanOCR2Sides("./ocr/dl/invalidExpiredDateDL.jpg", "./ocr/dl/LiadDLBack.jpg");

        PopupPage popupPage = new PopupPage(driver);
        Assert.assertEquals(popupPage.popupDescription(), "אותנטיקציית תעודה נכשלה");
    }
}