package btrust.idm.test.onboardingProcess.harelCompanyTests;

import btrust.onboardingProcess.ui.pagesObject.*;
import btrust.idm.test.onboardingProcess.BaseIdmClientTest;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class OCRAndLivenessFlowTest extends BaseIdmClientTest {

    OCRPage ocrPage;

    @BeforeMethod
    public void createToken() {
        link = createLinkToFlow("https://btrustqa.scanovate.com/", harelApiKey, "207");
        driver.navigate().to(link);
        ocrPage = new OCRPage(driver);
    }

    @Test(description = "OCR & Liveness with ID")
    @Description("Perform flow with ocr (ID) and liveness (without face compare) and check that created new case in console")
    public void t01_ocrAndLivnessID() throws IOException {
        OCRPage ocr = new OCRPage(driver);
        ocrPage.chooseLanguage("עברית");
        ocr.clickOnButton("סריקת תעודת זהות");
        ImageInjectionFunctionPage imageInjectionFunctionPage = new ImageInjectionFunctionPage(driver);
        imageInjectionFunctionPage.scanOCR("./ocr/greenID/bar/BarGreenID.jpg", "front");

        ScanResultPage scanResult = new ScanResultPage(driver);
        Assert.assertEquals(scanResult.resultsTitle(), "תוצאות הסריקה");
        Assert.assertEquals(scanResult.resultFaceImageDescription(), "תמונה מתעודה");
        Assert.assertTrue(scanResult.faceImageIsDisplayed());
        Assert.assertEquals(scanResult.results("green id", "שם משפחה"), "זמסקי");
        Assert.assertEquals(scanResult.results("green id", "שם פרטי"), "בר");
        Assert.assertEquals(scanResult.results("green id", "מספר זהות"), "312534753");
        Assert.assertEquals(scanResult.results("green id", "תאריך לידה"), "19.02.1994");
        Assert.assertEquals(scanResult.results("green id", "תאריך הנפקה"), "03.05.2010");
        Assert.assertEquals(scanResult.results("green id", "ארץ לידה"), "ישראל");
        Assert.assertEquals(scanResult.results("green id", "מין"), "נקבה");

        scanResult.clickOnButton("נמשיך");

        ScanLivenessPage scanLiveness = new ScanLivenessPage(driver);
        Assert.assertTrue(scanLiveness.scanLivenessImage());
        Assert.assertEquals(scanLiveness.scanLivenessTitle(), "בדיקת חיות");
        Assert.assertEquals(scanLiveness.scanLivenessSubTitle(), "לפני שנתחיל צריך לוודא ש:");
        Assert.assertEquals(scanLiveness.scanLivenessDescription(), "המכשיר ממש מול הפנים | תנאי תאורה טובים");

        scanLiveness.startButton();
        imageInjectionFunctionPage.scanLiveness("./liveness/bar/barCenter.jpg");

        FaceCompareResultsPage faceCompareResults = new FaceCompareResultsPage(driver);
        String processEndedText = faceCompareResults.processEndedText();
        Assert.assertEquals(processEndedText, "התהליך נגמר");
    }

    @Test(description = "OCR & Liveness with")
    @Description("Perform flow with ocr (DL) and liveness (without face compare) and check that created new case in console")
    public void t02_e2eHarelFlow2DL() throws IOException {
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
        String processEndedText = faceCompareResults.processEndedText();
        Assert.assertEquals(processEndedText, "התהליך נגמר");
    }
}