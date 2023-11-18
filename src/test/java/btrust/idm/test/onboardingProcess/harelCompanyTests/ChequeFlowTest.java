package btrust.idm.test.onboardingProcess.harelCompanyTests;

import btrust.onboardingProcess.ui.pagesObject.*;
import btrust.idm.test.onboardingProcess.BaseIdmClientTest;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class ChequeFlowTest extends BaseIdmClientTest {

    OCRPage ocrPage;
    ChequePage chequePage;
    ScanResultPage scanResult;
    PopupPage popupPage;
    ImageInjectionFunctionPage imageInjectionFunctionPage;
    FaceCompareResultsPage faceCompareResultsPage;


    @BeforeMethod
    public void createToken() {
        link = createLinkToFlow("https://btrustqa.scanovate.com/", harelApiKey, "223");
        driver.navigate().to(link);
        ocrPage = new OCRPage(driver);
        chequePage = new ChequePage(driver);
        scanResult = new ScanResultPage(driver);
        popupPage = new PopupPage(driver);
        imageInjectionFunctionPage = new ImageInjectionFunctionPage(driver);
        faceCompareResultsPage = new FaceCompareResultsPage(driver);
    }

    @Test(description = "Scan cheque")
    @Description("Scan cheque and check that created new case in console")
    public void t01_normalChequeScan() throws IOException {
        Assert.assertTrue(chequePage.ocrImage());
        ocrPage.chooseLanguage("עברית");
        Assert.assertEquals(chequePage.OCRTitleIsDisplayed(), "סריקת שיק");
        Assert.assertEquals(chequePage.subTitleOCRPage(), "לפני שנתחיל צריך לוודא ש:");
        Assert.assertEquals(chequePage.ocrDescription(), "ודא שהשיק לא קרוע | השיק נמצא על רקע בהיר");
        chequePage.clickOnButton("סריקת שיק");
        imageInjectionFunctionPage.scanImage("./ocr/cheque/normalCheque.jpg", "front");

      //  Assert.assertTrue(scanResult.progressBarIsDisplayed());
        Assert.assertEquals(scanResult.resultsTitle(), "תוצאות הסריקה");
        Assert.assertEquals(scanResult.resultFaceImageDescription(), "תמונת הצ׳יק");
        Assert.assertTrue(scanResult.faceImageIsDisplayed());
        Assert.assertEquals(scanResult.results("cheque", "מספר צ׳יק"), "0050104");
        Assert.assertEquals(scanResult.results("cheque", "מספר בנק"), "04");
        Assert.assertEquals(scanResult.results("cheque", "מספר סניף"), "12300");
        Assert.assertEquals(scanResult.results("cheque", "מספר חשבון"), "109592");
    }

    @Test(description = "Missing account number digit")
    @Description("Open Harel 223 flow and scan cheque that have account number with missing digits")
    public void t02_negativeFlowMissingAccountNumberDigit() throws IOException {
        chequePage.clickOnButton("סריקת שיק");
        imageInjectionFunctionPage.scanImage("./ocr/cheque/missingAccountNumberDigit.jpg", "front");

        Assert.assertEquals(popupPage.popupTitle(), "אופס… משהו השתבש, יש לנסות בשנית");
        Assert.assertEquals(popupPage.popupDescription(), "Could not read document");
    }

    @Test(description = "Missing bank number digit")
    @Description("Open Harel 223 flow and scan cheque that have bank number with missing digits")
    public void t03_negativeFlowMissingBankNumberDigit() throws IOException {
        chequePage.clickOnButton("סריקת שיק");
        imageInjectionFunctionPage.scanImage("./ocr/cheque/missingBankNumberDigit.jpg", "front");

        Assert.assertEquals(popupPage.popupTitle(), "אופס.. משהו השתבש, יש לנסות בשנית");
        Assert.assertEquals(popupPage.popupDescription(), "Could not read document");
    }

    @Test(description = "Missing branch number digit")
    @Description("Open Harel 223 flow and scan cheque that have branch number with missing digits")
    public void t04_negativeFlowMissingBranchNumberDigit() throws IOException {
        chequePage.clickOnButton("סריקת שיק");
        imageInjectionFunctionPage.scanImage("./ocr/cheque/missingBranchNumberDigit.jpg", "front");

        Assert.assertEquals(popupPage.popupTitle(), "אופס.. משהו השתבש, יש לנסות בשנית");
        Assert.assertEquals(popupPage.popupDescription(), "Could not read document");
    }

    @Test(description = "Missing cheque number digit")
    @Description("Open Harel 223 flow and scan cheque that have cheque number with missing digits")
    public void t05_negativeFlowMissingChequeNumberDigit() throws IOException {
        chequePage.clickOnButton("סריקת שיק");
        imageInjectionFunctionPage.scanImage("./ocr/cheque/missingChequeNumberDigits.jpg", "front");

        Assert.assertEquals(popupPage.popupTitle(), "אופס.. משהו השתבש, יש לנסות בשנית");
        Assert.assertEquals(popupPage.popupDescription(), "Could not read document");
    }

    @Test(description = "Block user")
    @Description("Scan cheque when the number cheque is missing 3 times and check that the user is blocked")
    public void t06_blockUser() throws IOException {
        chequePage.clickOnButton("סריקת שיק");
        imageInjectionFunctionPage.scanImage("./ocr/cheque/missingChequeNumberDigits.jpg", "front");

        Assert.assertEquals(popupPage.popupTitle(), "אופס.. משהו השתבש, יש לנסות בשנית");
        Assert.assertEquals(popupPage.popupDescription(), "Could not read document");
        popupPage.popupButton();
        chequePage.clickOnButton("סריקת שיק");
        imageInjectionFunctionPage.scanImage("./ocr/cheque/missingChequeNumberDigits.jpg", "front");

        Assert.assertEquals(popupPage.popupTitle(), "אופס.. משהו השתבש, יש לנסות בשנית");
        Assert.assertEquals(popupPage.popupDescription(), "Could not read document");
        popupPage.popupButton();
        chequePage.clickOnButton("סריקת שיק");
        imageInjectionFunctionPage.scanImage("./ocr/cheque/missingChequeNumberDigits.jpg", "front");

        Assert.assertEquals(popupPage.popupTitle(), "שגיאה כללית");
        Assert.assertEquals(popupPage.popupDescription(), "Could not read document");
        popupPage.popupButton();

        Assert.assertEquals(faceCompareResultsPage.processEndedText(), "process-ended");
    }

    @Test(description = "Scan again")
    @Description("Scan chque, click on scan again button in scan result screen and check that the user finished the flow")
    public void t07_ScanAgain() throws IOException {
        chequePage.clickOnButton("סריקת שיק");
        imageInjectionFunctionPage.scanImage("./ocr/cheque/normalCheque.jpg", "front");

        Assert.assertTrue(scanResult.progressBarIsDisplayed());
        Assert.assertEquals(scanResult.resultsTitle(), "תוצאות הסריקה");
        Assert.assertEquals(scanResult.resultFaceImageDescription(), "תמונת הצ׳ק");
        Assert.assertTrue(scanResult.faceImageIsDisplayed());
        Assert.assertEquals(scanResult.results("cheque", "מספר צ׳ק"), "0050104");
        Assert.assertEquals(scanResult.results("cheque", "מספר בנק"), "04");
        Assert.assertEquals(scanResult.results("cheque", "מספר סניף בנק"), "12300");
        Assert.assertEquals(scanResult.results("cheque", "מספר חשבון"), "109592");

        scanResult.clickOnButton("נסרוק שוב");
        imageInjectionFunctionPage.scanImage("./ocr/cheque/normalCheque.jpg", "front");

        Assert.assertTrue(scanResult.progressBarIsDisplayed());
        Assert.assertEquals(scanResult.resultsTitle(), "תוצאות הסריקה");
        Assert.assertEquals(scanResult.resultFaceImageDescription(), "תמונת הצ׳ק");
        Assert.assertTrue(scanResult.faceImageIsDisplayed());
        Assert.assertEquals(scanResult.results("cheque", "מספר צ׳ק"), "0050104");
        Assert.assertEquals(scanResult.results("cheque", "מספר בנק"), "04");
        Assert.assertEquals(scanResult.results("cheque", "מספר סניף בנק"), "12300");
        Assert.assertEquals(scanResult.results("cheque", "מספר חשבון"), "109592");
    }
}