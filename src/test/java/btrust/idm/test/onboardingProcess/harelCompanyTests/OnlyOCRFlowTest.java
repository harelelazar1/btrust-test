package btrust.idm.test.onboardingProcess.harelCompanyTests;

import btrust.onboardingProcess.ui.pagesObject.FaceCompareResultsPage;
import btrust.onboardingProcess.ui.pagesObject.ImageInjectionFunctionPage;
import btrust.onboardingProcess.ui.pagesObject.OCRPage;
import btrust.onboardingProcess.ui.pagesObject.ScanResultPage;
import btrust.idm.test.onboardingProcess.BaseIdmClientTest;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class OnlyOCRFlowTest extends BaseIdmClientTest {

    @BeforeMethod
    public void createToken() {
        link = createLinkToFlow("https://btrustqa.scanovate.com/", harelApiKey, "208");
        driver.navigate().to(link);
    }

    @Test(description = "Flow with only OCR - scan ID")
    @Description("Scan ID and check that created new case in console")
    public void t01_onlyScanID() throws IOException {
        OCRPage ocrPage = new OCRPage(driver);
        Assert.assertTrue(ocrPage.progressBar());
        ocrPage.clickOnButton("סריקת תעודת זהות");
        ImageInjectionFunctionPage imageInjectionFunctionPage = new ImageInjectionFunctionPage(driver);
        imageInjectionFunctionPage.scanOCR2Sides("./ocr/bioID/nitzan/front/NitzanBioFront.jpg", "./ocr/bioID/nitzan/back/NitzanBioBack.jpg");

        ScanResultPage scanResult = new ScanResultPage(driver);
        Assert.assertTrue(scanResult.progressBarIsDisplayed());
        Assert.assertEquals(scanResult.resultsTitle(), "תוצאות הסריקה");
        Assert.assertEquals(scanResult.resultFaceImageDescription(), "תמונה מתעודה");
        Assert.assertTrue(scanResult.faceImageIsDisplayed());
        Assert.assertEquals(scanResult.results("bio id", "מעמד"), "אזרהות ישראלית");
        Assert.assertEquals(scanResult.results("bio id", "שם משפחה"), "שוקר");
        Assert.assertEquals(scanResult.results("bio id", "שם פרטי"), "ניצן");
        Assert.assertEquals(scanResult.results("bio id", "מספר ת.ז"), "200761625");
        Assert.assertEquals(scanResult.results("bio id", "ת. לידה"), "21.08.1988");
        Assert.assertEquals(scanResult.results("bio id", "תאריך הנפקה"), "23.5.2019");
        Assert.assertEquals(scanResult.results("bio id", "תוקף"), "20.05.2029");
        Assert.assertEquals(scanResult.results("bio id", "ארץ לידה"), "ישראל");
        Assert.assertEquals(scanResult.results("bio id", "מין"), "זכר");
        scanResult.clickOnButton("נמשיך");

        FaceCompareResultsPage faceCompareResults = new FaceCompareResultsPage(driver);
        String processEndedText = faceCompareResults.processEndedText();
        Assert.assertEquals(processEndedText, "process-ended");
    }

    @Test(description = "Flow with only OCR - scan DL")
    @Description("Scan DL and check that created new case in console")
    public void t02_onlyScanDL() throws IOException {
        OCRPage ocr = new OCRPage(driver);
        Assert.assertTrue(ocr.progressBar());
        ocr.clickOnButton("סריקת רישיון נהיגה");
        ImageInjectionFunctionPage imageInjectionFunctionPage = new ImageInjectionFunctionPage(driver);
        imageInjectionFunctionPage.scanOCR2Sides("./ocr/dl/LiadDLFront.jpg", "./ocr/dl/LiadDLBack.jpg");

        ScanResultPage scanResult = new ScanResultPage(driver);
        Assert.assertTrue(scanResult.progressBarIsDisplayed());
        Assert.assertEquals(scanResult.resultsTitle(), "תוצאות הסריקה");
        Assert.assertEquals(scanResult.resultFaceImageDescription(), "תמונה מתעודה");
        Assert.assertTrue(scanResult.faceImageIsDisplayed());
        Assert.assertEquals(scanResult.results("dl", "שם משפחה"), "טובי");
        Assert.assertEquals(scanResult.results("dl", "שם משפחה באנגלית"), "TOBI");
        Assert.assertEquals(scanResult.results("dl", "שם פרטי"), "ליעד");
        Assert.assertEquals(scanResult.results("dl", "שם פרטי באנגלית"), "LIAD");
        Assert.assertEquals(scanResult.results("dl", "ת. לידה"), "17.01.1993");
        Assert.assertEquals(scanResult.results("dl", "תאריך הנפקה"), "15.12.2016");
        Assert.assertEquals(scanResult.results("dl", "תוקף"), "17.01.2027");
        Assert.assertEquals(scanResult.results("dl", "מספר הרישיון"), "9254737");
        Assert.assertEquals(scanResult.results("dl", "מספר ת.ז"), "307922328");
        Assert.assertEquals(scanResult.results("dl", "כתובת"), "קדם 33ב שוהם");
        Assert.assertEquals(scanResult.results("dl", "סוג רישיון"), "B");
        Assert.assertEquals(scanResult.results("dl", "שנת הוצאת רישיון B (רכב פרטי)"), "2010");
        scanResult.clickOnButton("נמשיך");

        FaceCompareResultsPage faceCompareResults = new FaceCompareResultsPage(driver);
        String processEndedText = faceCompareResults.processEndedText();
        Assert.assertEquals(processEndedText, "process-ended");
    }
}