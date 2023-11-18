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

public class OnlyIDFlowTest extends BaseIdmClientTest {

    @BeforeMethod
    public void createToken() {
        link = createLinkToFlow("https://btrustqa.scanovate.com/", harelApiKey, "210");
        driver.navigate().to(link);
    }

    @Test(description = "Flow with only ID")
    @Description("Scan ID and check hat created new case in console")
    public void t01_onlyID() throws IOException {
        OCRPage ocrPage = new OCRPage(driver);
 //       Assert.assertTrue(ocrPage.progressBar());
        ocrPage.clickOnButton("סריקת תעודת זהות");
        ImageInjectionFunctionPage imageInjectionFunctionPage = new ImageInjectionFunctionPage(driver);
        imageInjectionFunctionPage.scanOCR2Sides("./ocr/bioID/nitzan/front/NitzanBioFront.jpg", "./ocr/bioID/nitzan/back/NitzanBioBack.jpg");

        ScanResultPage scanResult = new ScanResultPage(driver);
//        Assert.assertTrue(scanResult.progressBarIsDisplayed());
        Assert.assertEquals(scanResult.resultsTitle(), "תוצאות הסריקה");
        Assert.assertEquals(scanResult.resultFaceImageDescription(), "תמונה מתעודה");
        Assert.assertTrue(scanResult.faceImageIsDisplayed());
        Assert.assertEquals(scanResult.results("bio id", "מעמד"), "אזרהות ישראלית");
        Assert.assertEquals(scanResult.results("bio id", "שם משפחה"), "שוקר");
        Assert.assertEquals(scanResult.results("bio id", "שם פרטי"), "ניצן");
        Assert.assertEquals(scanResult.results("bio id", "מספר זהות"), "200761625");
        Assert.assertEquals(scanResult.results("bio id", "תאריך לידה"), "21.08.1988");
        Assert.assertEquals(scanResult.results("bio id", "תאריך הנפקה"), "23.5.2019");
        Assert.assertEquals(scanResult.results("bio id", "תוקף"), "20.05.2029");
        Assert.assertEquals(scanResult.results("bio id", "ארץ לידה"), "ישראל");
        Assert.assertEquals(scanResult.results("bio id", "מין"), "זכר");
        scanResult.clickOnButton("נמשיך");

//        FaceCompareResultsPage faceCompareResults = new FaceCompareResultsPage(driver);
//        String processEndedText = faceCompareResults.processEndedText();
//        Assert.assertEquals(processEndedText, "process-ended");
    }
}