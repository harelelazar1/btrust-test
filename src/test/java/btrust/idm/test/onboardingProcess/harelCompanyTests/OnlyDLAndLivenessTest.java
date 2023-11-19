package btrust.idm.test.onboardingProcess.harelCompanyTests;

import btrust.onboardingProcess.ui.pagesObject.*;
import btrust.idm.test.onboardingProcess.BaseIdmClientTest;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class OnlyDLAndLivenessTest extends BaseIdmClientTest {

    OCRPage ocrPage;
    @BeforeMethod
    public void createToken() {
        link = createLinkToFlow("https://btrustqa.scanovate.com/", harelApiKey, "213");
        driver.navigate().to(link);
        ocrPage = new OCRPage(driver);
    }

    @Test(description = "Flow with only DL and liveness")
    @Description("Scan DL, scan liveness and check hat created new case in console")
    public void t01_e2eHarelFlow8DL() throws IOException {
        OCRPage ocr = new OCRPage(driver);
        ocrPage.chooseLanguage("עברית");
        ocr.clickOnButton("סריקת רישיון נהיגה");
        ImageInjectionFunctionPage imageInjectionFunctionPage = new ImageInjectionFunctionPage(driver);
        imageInjectionFunctionPage.scanOCR2Sides("./ocr/dl/LiadDLFront.jpg", "./ocr/dl/LiadDLBack.jpg");

        ScanResultPage scanResult = new ScanResultPage(driver);
        Assert.assertEquals(scanResult.resultsTitle(), "תוצאות הסריקה");
        Assert.assertEquals(scanResult.resultFaceImageDescription(), "תמונה מתעודה");
        Assert.assertTrue(scanResult.faceImageIsDisplayed());
        Assert.assertEquals(scanResult.results("dl", "שם משפחה"), "טובי");
        Assert.assertEquals(scanResult.results("dl", "שם משפחה באנגלית"), "TOBI");
        Assert.assertEquals(scanResult.results("dl", "שם פרטי"), "ליעד");
        Assert.assertEquals(scanResult.results("dl", "שם פרטי באנגלית"), "LIAD");
        Assert.assertEquals(scanResult.results("dl", "תאריך לידה"), "17.01.1993");
        Assert.assertEquals(scanResult.results("dl", "תאריך הנפקה"), "15.12.2016");
        Assert.assertEquals(scanResult.results("dl", "תוקף"), "17.01.2027");
        Assert.assertEquals(scanResult.results("dl", "מספר רישיון"), "9254737");
        Assert.assertEquals(scanResult.results("dl", "מספר זהות"), "307922328");
        Assert.assertEquals(scanResult.results("dl", "כתובת"), "קדם 33ב שוהם");
        Assert.assertEquals(scanResult.results("dl", "סוג רישיון"), "B");
        Assert.assertEquals(scanResult.results("dl", "שנת הוצאת רישיון B (רכב פרטי)"), "2010");

        scanResult.clickOnButton("נמשיך");

        ScanLivenessPage scanLiveness = new ScanLivenessPage(driver);
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
}