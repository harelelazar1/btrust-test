package btrust.idm.test.onboardingProcess.harelCompanyTests;

import btrust.onboardingProcess.ui.pagesObject.*;
import btrust.idm.test.onboardingProcess.BaseIdmClientTest;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class RegistrationFlowTest extends BaseIdmClientTest {

    @BeforeMethod
    public void createToken() {
        link = createLinkToFlow("https://btrustqa.scanovate.com/", harelApiKey, "203");
        driver.navigate().to(link);
    }

    @Test(description = "Registration with ID")
    @Description("Scan ID, scan face and check that created new case in console")
    public void t01_registrationID() throws IOException {
        OCRPage ocrPage = new OCRPage(driver);
        Assert.assertTrue(ocrPage.progressBar());
        ocrPage.clickOnButton("סריקת תעודת זהות");
        ImageInjectionFunctionPage imageInjectionFunctionPage = new ImageInjectionFunctionPage(driver);
        imageInjectionFunctionPage.scanOCR2Sides("./ocr/blueID/liad/blueID_color.jpg", null);

        ScanResultPage scanResult = new ScanResultPage(driver);
        Assert.assertTrue(scanResult.progressBarIsDisplayed());
        Assert.assertEquals(scanResult.resultsTitle(), "תוצאות הסריקה");
        Assert.assertEquals(scanResult.resultFaceImageDescription(), "תמונה מתעודה");
        Assert.assertTrue(scanResult.faceImageIsDisplayed());
        Assert.assertEquals(scanResult.results("blue id", "מעמד"), "אזרחות ישראלית");
        Assert.assertEquals(scanResult.results("blue id", "שם משפחה"), "טובי");
        Assert.assertEquals(scanResult.results("blue id", "שם פרטי"), "ליעד");
        Assert.assertEquals(scanResult.results("blue id", "מספר ת.ז"), "307922328");
        Assert.assertEquals(scanResult.results("blue id", "ת. לידה"), "17.01.1993");
        Assert.assertEquals(scanResult.results("blue id", "תאריך הנפקה"), "28.01.2015");
        Assert.assertEquals(scanResult.results("blue id", "תוקף"), "28.01.2025");
        Assert.assertEquals(scanResult.results("blue id", "ארץ לידה"), "ישראל");
        Assert.assertEquals(scanResult.results("blue id", "מין"), "זכר");
        scanResult.clickOnButton("נמשיך");

        ScanLivenessPage scanLiveness = new ScanLivenessPage(driver);
        Assert.assertTrue(scanLiveness.progressBar());
        Assert.assertTrue(scanLiveness.scanLivenessImage());
        Assert.assertEquals(scanLiveness.scanLivenessTitle(), "בדיקת חיות");
        Assert.assertEquals(scanLiveness.scanLivenessSubTitle(), "לפני שנתחיל צריך לוודא ש:");
        Assert.assertEquals(scanLiveness.scanLivenessDescription(), "המכשיר ממש מול הפנים | תנאי תאורה טובים");
        scanLiveness.startButton();
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

    @Test(description = "Registration with DL")
    @Description("Scan DL, scan face and check that created new case in console")
    public void t02_registrationDL() throws IOException {
        OCRPage ocr = new OCRPage(driver);
        Assert.assertTrue(ocr.progressBar());
        ocr.clickOnButton("סריקת רישיון נהיגה");
        ImageInjectionFunctionPage imageInjectionFunctionPage = new ImageInjectionFunctionPage(driver);
        imageInjectionFunctionPage.scanOCR2Sides("./ocr/ilDL/harel-ilDL2.jpg", "./ocr/dl/harel-ilDLBack.jpg");

        ScanResultPage scanResult = new ScanResultPage(driver);
        Assert.assertTrue(scanResult.progressBarIsDisplayed());
        Assert.assertEquals(scanResult.resultsTitle(), "תוצאות הסריקה");
        Assert.assertEquals(scanResult.resultFaceImageDescription(), "תמונה מתעודה");
        Assert.assertTrue(scanResult.faceImageIsDisplayed());
        Assert.assertEquals(scanResult.results("dl", "שם משפחה"), "אלעזר");
        Assert.assertEquals(scanResult.results("dl", "שם משפחה באנגלית"), "ELAZAR");
        Assert.assertEquals(scanResult.results("dl", "שם פרטי"), "הראל");
        Assert.assertEquals(scanResult.results("dl", "שם פרטי באנגלית"), "HAREL");
        Assert.assertEquals(scanResult.results("dl", "ת. לידה"), "30.06.1987");
        Assert.assertEquals(scanResult.results("dl", "תאריך הנפקה"), "10.08.2018");
        Assert.assertEquals(scanResult.results("dl", "תוקף"), "30.06.2028");
        Assert.assertEquals(scanResult.results("dl", "מספר הרישיון"), "9503218");
        Assert.assertEquals(scanResult.results("dl", "מספר ת.ז"), "300864550");
        Assert.assertEquals(scanResult.results("dl", "כתובת"), "שפירא משה חיים 13 אשדוד");
        Assert.assertEquals(scanResult.results("dl", "סוג רישיון"), "B");
        Assert.assertEquals(scanResult.results("dl", "שנת הוצאת רישיון B (רכב פרטי)"), "2012");
        scanResult.clickOnButton("נמשיך");

        ScanLivenessPage scanLiveness = new ScanLivenessPage(driver);
        Assert.assertTrue(scanLiveness.progressBar());
        Assert.assertTrue(scanLiveness.scanLivenessImage());
        Assert.assertEquals(scanLiveness.scanLivenessTitle(), "בדיקת חיות");
        Assert.assertEquals(scanLiveness.scanLivenessSubTitle(), "לפני שנתחיל צריך לוודא ש:");
        Assert.assertEquals(scanLiveness.scanLivenessDescription(), "המכשיר ממש מול הפנים | תנאי תאורה טובים");
        scanLiveness.startButton();
        imageInjectionFunctionPage.scanLiveness("./liveness/harel/harelLivenessCenter1.jpg");

        FaceCompareResultsPage faceCompareResults = new FaceCompareResultsPage(driver);
        Assert.assertEquals(faceCompareResults.faceCompareTitle(), "תוצאות השוואת פנים");
        Assert.assertTrue(faceCompareResults.livenessPictureIsDisplayed());
        Assert.assertEquals(faceCompareResults.livenessDescription(), "בדיקת חיות");
        Assert.assertTrue(faceCompareResults.ocrPicture());
        Assert.assertEquals(faceCompareResults.ocrDescription(), "תמונה מתעודה");
        Assert.assertTrue(faceCompareResults.faceMatchIcon());
        Assert.assertEquals(faceCompareResults.faceMatchTitle(), "הפנים זהות");
    }
}