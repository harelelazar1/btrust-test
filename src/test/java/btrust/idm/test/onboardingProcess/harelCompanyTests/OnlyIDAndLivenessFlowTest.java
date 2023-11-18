package btrust.idm.test.onboardingProcess.harelCompanyTests;

import btrust.onboardingProcess.ui.pagesObject.*;
import btrust.idm.test.onboardingProcess.BaseIdmClientTest;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class OnlyIDAndLivenessFlowTest extends BaseIdmClientTest {

    @BeforeMethod
    public void createToken() {
        link = createLinkToFlow("https://btrustqa.scanovate.com/", harelApiKey, "212");
        driver.navigate().to(link);
    }

    @Test(description = "Flow with only ID and liveness")
    @Description("Scan ID, scan liveness and check hat created new case in console")
    public void t01_idAndLiveness() throws IOException {
        OCRPage ocr = new OCRPage(driver);
//        Assert.assertTrue(ocr.progressBar());
        ocr.clickOnButton("סריקת תעודת זהות");
        ImageInjectionFunctionPage imageInjectionFunctionPage = new ImageInjectionFunctionPage(driver);
        imageInjectionFunctionPage.scanOCR("./ocr/blueID/liad/blueID_color.jpg", "front");

        ScanResultPage scanResult = new ScanResultPage(driver);
//        Assert.assertTrue(scanResult.progressBarIsDisplayed());
        Assert.assertEquals(scanResult.resultsTitle(), "תוצאות הסריקה");
        Assert.assertEquals(scanResult.resultFaceImageDescription(), "תמונה מתעודה");
        Assert.assertTrue(scanResult.faceImageIsDisplayed());
        Assert.assertEquals(scanResult.results("blue id", "מעמד"), "אזרחות ישראלית");
        Assert.assertEquals(scanResult.results("blue id", "שם משפחה"), "טובי");
        Assert.assertEquals(scanResult.results("blue id", "שם פרטי"), "ליעד");
        Assert.assertEquals(scanResult.results("blue id", "מספר זהות"), "307922328");
        Assert.assertEquals(scanResult.results("blue id", "תאריך לידה"), "17.01.1993");
        Assert.assertEquals(scanResult.results("blue id", "תאריך הנפקה"), "28.01.2015");
        Assert.assertEquals(scanResult.results("blue id", "תוקף"), "28.01.2025");
        Assert.assertEquals(scanResult.results("blue id", "ארץ לידה"), "ישראל");
        Assert.assertEquals(scanResult.results("blue id", "מין"), "זכר");

        scanResult.clickOnButton("נמשיך");

        ScanLivenessPage scanLiveness = new ScanLivenessPage(driver);
//        Assert.assertTrue(scanLiveness.progressBar());
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