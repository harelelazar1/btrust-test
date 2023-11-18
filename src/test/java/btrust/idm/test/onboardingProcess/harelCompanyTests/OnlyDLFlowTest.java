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

public class OnlyDLFlowTest extends BaseIdmClientTest {

    @BeforeMethod
    public void createToken() {
        link = createLinkToFlow("https://btrustqa.scanovate.com/", harelApiKey, "211");
        driver.navigate().to(link);
    }

    @Test(description = "Flow with only ID")
    @Description("Scan DL and check hat created new case in console")
    public void t01_e2eHarelFlow6DL() throws IOException {
        OCRPage ocr = new OCRPage(driver);
 //       Assert.assertTrue(ocr.progressBar());
        ocr.clickOnButton("סריקת רישיון נהיגה");
        ImageInjectionFunctionPage imageInjectionFunctionPage = new ImageInjectionFunctionPage(driver);
        imageInjectionFunctionPage.scanOCR2Sides("./ocr/dl/LiadDLFront.jpg", "./ocr/dl/LiadDLBack.jpg");

        ScanResultPage scanResult = new ScanResultPage(driver);
//        Assert.assertTrue(scanResult.progressBarIsDisplayed());
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

//        FaceCompareResultsPage faceCompareResults = new FaceCompareResultsPage(driver);
//        String processEndedText = faceCompareResults.processEndedText();
//        Assert.assertEquals(processEndedText, "process-ended");
    }
}