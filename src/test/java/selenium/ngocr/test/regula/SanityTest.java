package selenium.ngocr.test.regula;

import api.Variables;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import selenium.BaseTest;
import selenium.Injection;
import selenium.ocr.pageObject.newService.MainPage;
import selenium.ocr.pageObject.newService.ResultsPage;
import utilities.MongoDB.MongoHandler;


import java.io.IOException;

public class SanityTest extends BaseTest {


    static MainPage mainPage;
    Variables variables;
    MongoHandler mongoHandler;

    @BeforeMethod
    public void resetVariables() {
        mainPage = new MainPage(driver);
        variables = new Variables();
        mongoHandler = new MongoHandler();
        mainPage = new MainPage(driver);
    }

    @BeforeMethod
    public void linkToOCR() {
        if (System.getProperty("OCR_NEW").equalsIgnoreCase("false")) {
            driver.get(OCR_DEMO);
        } else {
            driver.get(OCR_DEMO_NEW);
        }
    }

    public static void chooseConfigFile() {
        MainPage mainPage = new MainPage(driver);
        if (System.getProperty("OCR_NEW").equalsIgnoreCase("false")) {
            mainPage.chooseConfigFile("two_sides.json");
        } else {
            mainPage.chooseConfigFile(System.getProperty("COMPANY_ID"));
        }
    }

    public static void chooseServiceList() {
        mainPage.chooseFromServicesList("Regula");
    }

    @Test(description = "bio Id demo sanity test",retryAnalyzer = utilities.RetryAnalyzer.class,groups = {"smoke"})
    @Severity(SeverityLevel.CRITICAL)
    @Description("Run Demo End to End test of bio Id")
    public void t01_e2eBioIdDemoFlow() throws IOException, InterruptedException {
        SanityTest.chooseConfigFile();
        SanityTest.chooseServiceList();
        Injection injection = new Injection(driver);
        injection.imageInjectionConfigure("./ocr/bioID/nitzan/front/NitzanBioFront1.jpg", "./ocr/bioID/nitzan/back/NitzanBioBack1.jpg", 1, false, null);
        ResultsPage resultsPage = new ResultsPage(driver);
//        Assert.assertEquals(resultsPage.verifyListsSizes(TEN_SECONDS), 41);
        resultsPage.collectResults(variables);


        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "success");


        //Front section
//        Assert.assertEquals(variables.getCardType1(), "bio_front");
//        Assert.assertEquals(variables.getLastNameHebrew(), "שוקר");
//        Assert.assertEquals(variables.getFirstNameHebrew(), "ניצן");
//        Assert.assertEquals(variables.getDateOfBirth(), "21.08.1988");
//        Assert.assertEquals(variables.getDateOfIssue(), "23.5.2019");
//        Assert.assertEquals(variables.getDateOfExpiry(), "20.05.2029");
//        Assert.assertEquals(variables.getIdNumber(), 200761625);
//        Assert.assertTrue(variables.isImageIsColorful());
//        Assert.assertTrue(variables.isIdChecksumValid());
//        Assert.assertTrue(variables.isTemplateMatched());
//        Assert.assertTrue(variables.isDocumentInFrame());
//        Assert.assertTrue(variables.isPeriodBetweenIssueAndExpiry());
//        Assert.assertTrue(variables.isChipAuth());
//        Assert.assertFalse(variables.isFaceImageReplacedWithPassportImage());
//        Assert.assertTrue(variables.isFaceSizeValid());
//        Assert.assertTrue(variables.isFacePositionValid());
//        Assert.assertTrue(variables.isFaceRotationValid());
//        Assert.assertTrue(variables.isExpiryDateValid());
//        Assert.assertTrue(variables.isIssueDateValid());
//        // Images
//        Assert.assertEquals(variables.getFaceImage(), "face_image");
//        Assert.assertEquals(variables.getInputImage1(), "input_image");
//        Assert.assertEquals(variables.getInputImage2(), "input_image");
//        Assert.assertEquals(variables.getCroppedImage1(), "cropped_image");
//        Assert.assertEquals(variables.getCroppedImage2(), "cropped_image");
//
//        //Back section
//        Assert.assertEquals(variables.getCardType2(), "bio_back");
//        Assert.assertEquals(variables.getIdNumber2(), 200761625);
//        Assert.assertEquals(variables.getPlaceOfBirth(), "ישראל");
//        Assert.assertEquals(variables.getGender(), "זכר");
//        Assert.assertEquals(variables.getCitizenship(), "אזרחות ישראלית");
//        Assert.assertTrue(variables.isImageIsColorful2());
//        Assert.assertTrue(variables.isIdChecksumValid2());
//        Assert.assertTrue(variables.isTemplateMatched2());
//        Assert.assertTrue(variables.isDocumentInFrame2());
//        Assert.assertTrue(variables.isIdNumberMatchesFront());
////        Assert.assertTrue(resultsPage.verifyImagesAreReal());

    }


    @Test(description = "bio Id demo sanity test",retryAnalyzer = utilities.RetryAnalyzer.class,groups = {"smoke"})
    @Severity(SeverityLevel.CRITICAL)
    @Description("Run Demo End to End test of bio Id")
    public void t02_e2eBioIdNewDemoFlow() throws IOException, InterruptedException {
        SanityTest.chooseConfigFile();
        SanityTest.chooseServiceList();
        Injection injection = new Injection(driver);
        injection.imageInjectionConfigure("./ocr/new bioID/Harel/newBioIDFront.jpg", "./ocr/new bioID/Harel/newBioIDBack.jpg", 1, false, null);
        ResultsPage resultsPage = new ResultsPage(driver);
//        Assert.assertEquals(resultsPage.verifyListsSizes(TEN_SECONDS), 41);
        resultsPage.collectResults(variables);


        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "success");

        //Front section
//        Assert.assertEquals(variables.getCardType1(), "bio_type_2_front");
//        Assert.assertEquals(variables.getLastNameHebrew(), "צ׳רנומורדיק");
//        Assert.assertEquals(variables.getFirstNameHebrew(), "גריגורי");
//        Assert.assertEquals(variables.getDateOfBirth(), "05.03.1992");
//        Assert.assertEquals(variables.getDateOfIssue(), "5.4.2022");
//        Assert.assertEquals(variables.getDateOfExpiry(), "03.04.2027");
//        Assert.assertEquals(variables.getIdNumber(), 345648513);
//        Assert.assertTrue(variables.isImageIsColorful());
//        Assert.assertTrue(variables.isIdChecksumValid());
//        Assert.assertTrue(variables.isTemplateMatched());
//        Assert.assertTrue(variables.isDocumentInFrame());
//        Assert.assertTrue(variables.isPeriodBetweenIssueAndExpiry());
//        Assert.assertTrue(variables.isChipAuth());
//        Assert.assertFalse(variables.isFaceImageReplacedWithPassportImage());
//        Assert.assertTrue(variables.isFaceSizeValid());
//        Assert.assertFalse(variables.isFacePositionValid());
//        Assert.assertTrue(variables.isFaceRotationValid());
//        Assert.assertTrue(variables.isExpiryDateValid());
//        Assert.assertTrue(variables.isIssueDateValid());
//        // Images
//        Assert.assertEquals(variables.getFaceImage(), "face_image");
//        Assert.assertEquals(variables.getInputImage1(), "input_image");
//        Assert.assertEquals(variables.getInputImage2(), "input_image");
//        Assert.assertEquals(variables.getCroppedImage1(), "cropped_image");
//        Assert.assertEquals(variables.getCroppedImage2(), "cropped_image");
//
//        //Back section
//        Assert.assertEquals(variables.getCardType2(), "bio_type_2_back");
//        Assert.assertEquals(variables.getIdNumber2(), 345648513);
//        Assert.assertEquals(variables.getPlaceOfBirth(), "רוסיה");
//        Assert.assertEquals(variables.getGender(), "זכר");
//        Assert.assertEquals(variables.getCitizenship(), "אזרחות ישראלית");
//        Assert.assertTrue(variables.isImageIsColorful2());
//        Assert.assertTrue(variables.isIdChecksumValid2());
//        Assert.assertTrue(variables.isTemplateMatched2());
//        Assert.assertTrue(variables.isDocumentInFrame2());
//        Assert.assertTrue(variables.isIdNumberMatchesFront());
    }

    @Test(description = "blue Id demo sanity test",retryAnalyzer = utilities.RetryAnalyzer.class,groups = {"smoke"})
    @Severity(SeverityLevel.CRITICAL)
    @Description("Run Demo End to End test of blue Id")
    public void t03_e2eBlueIdDemoFlow() throws IOException, InterruptedException {
        SanityTest.chooseConfigFile();
        SanityTest.chooseServiceList();
        Injection injection = new Injection(driver);
        injection.imageInjectionConfigure("./ocr/blueID/liad/blueID_color1.jpg", null, 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
        //      Assert.assertEquals(resultsPage.verifyListsSizes(), 30);
        resultsPage.collectResults(variables);


        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "success");


        //Front section
//        Assert.assertEquals(variables.getCardType1(), "blue");
//        Assert.assertEquals(variables.getCardType1(), "blue");
//        Assert.assertEquals(variables.getDateOfBirth(), "17.01.1993");
//
//        Assert.assertEquals(variables.getDateOfExpiry(), "28.01.2025");
//        Assert.assertEquals(variables.getDateOfIssue(), "28.01.2015");
//        Assert.assertEquals(variables.getFirstNameHebrew(), "ליעד");
//        Assert.assertEquals(variables.getGender(), "זכר");
//
//        Assert.assertEquals(variables.getIdNumber(), 307922328);
//        Assert.assertEquals(variables.getLastNameHebrew(), "טובי");
//        Assert.assertEquals(variables.getPlaceOfBirth(), "ישראל");
//        Assert.assertEquals(variables.getCitizenship(), "אזרחות ישראלית");
//        Assert.assertTrue(variables.isImageIsColorful());
//        Assert.assertTrue(variables.isIdChecksumValid());
//        Assert.assertTrue(variables.isTemplateMatched());
//        Assert.assertTrue(variables.isDocumentInFrame());
//        Assert.assertTrue(variables.isPeriodBetweenIssueAndExpiry());
//        Assert.assertTrue(variables.isStampDetected());
//        Assert.assertTrue(variables.isFaceSizeValid());
//        Assert.assertTrue(variables.isFacePositionValid());
//        Assert.assertTrue(variables.isFaceRotationValid());
//        Assert.assertTrue(variables.isExpiryDateValid());
//        Assert.assertTrue(variables.isIssueDateValid());
//
//        Assert.assertEquals(variables.getFaceImage(), "face_image");
//        Assert.assertEquals(variables.getInputImage1(), "input_image");
//        Assert.assertEquals(variables.getCroppedImage1(), "cropped_image");
//
//        Assert.assertTrue(resultsPage.verifyImagesAreReal());
    }


    @Test(description = "green Id demo sanity test",retryAnalyzer = utilities.RetryAnalyzer.class,groups = {"smoke"})
    @Severity(SeverityLevel.CRITICAL)
    @Description("Run Demo End to End test of green Id")
    public void t04_e2eGreenIdDemoFlow() throws IOException, InterruptedException {
        SanityTest.chooseConfigFile();
        SanityTest.chooseServiceList();
        Injection injection = new Injection(driver);

        injection.imageInjectionConfigure("./ocr/greenID/auth/face_stamp_true.jpg", null, 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
        //       Assert.assertEquals(resultsPage.verifyListsSizes(), 26);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "success");


        //Front section
//        Assert.assertEquals(variables.getCardType1(), "green");
//        Assert.assertEquals(variables.getIdNumber(), 332358530);
//        Assert.assertEquals(variables.getLastNameHebrew(), "לביק");
//        Assert.assertEquals(variables.getDateOfBirth(), "25.11.1967");
//        Assert.assertEquals(variables.getDateOfIssue(), "21.05.2009");
//        Assert.assertEquals(variables.getFirstNameHebrew(), "אדם ברדלי");
//        Assert.assertEquals(variables.getGender(), "זכר");
//        Assert.assertEquals(variables.getPlaceOfBirth(), "ארצות הבריתי");
//
//        Assert.assertTrue(variables.isImageIsColorful());
//        Assert.assertTrue(variables.isIdChecksumValid());
//        Assert.assertTrue(variables.isTemplateMatched());
//        Assert.assertTrue(variables.isDocumentInFrame());
//        Assert.assertTrue(variables.isStampDetected());
//        Assert.assertTrue(variables.isFaceSizeValid());
//        Assert.assertTrue(variables.isFacePositionValid());
//        Assert.assertTrue(variables.isFaceRotationValid());
//        Assert.assertTrue(variables.isIssueDateValid());
//
//        Assert.assertEquals(variables.getFaceImage(), "face_image");
//        Assert.assertEquals(variables.getInputImage1(), "input_image");
//        Assert.assertEquals(variables.getCroppedImage1(), "cropped_image");
//
//        Assert.assertTrue(resultsPage.verifyImagesAreReal());
    }


    @Test(description = "green Id demo sanity test",retryAnalyzer = utilities.RetryAnalyzer.class,groups = {"smoke"})
    @Severity(SeverityLevel.CRITICAL)
    @Description("Run Demo End to End test of green Id")
    public void t05_e2eGreenIdDemoFlow() throws IOException, InterruptedException {
        SanityTest.chooseConfigFile();
        SanityTest.chooseServiceList();
        Injection injection = new Injection(driver);

        injection.imageInjectionConfigure("./ocr/greenId_old/israel_id_green_old.jpg", null, 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
        //       Assert.assertEquals(resultsPage.verifyListsSizes(), 26);
        resultsPage.collectResults(variables);


        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "success");


        //Front section
//        Assert.assertEquals(variables.getCardType1(), "green_old");
//        Assert.assertEquals(variables.getIdNumber(), 32846545);
//        Assert.assertEquals(variables.getFirstNameHebrew(), "מנאר");
//        Assert.assertEquals(variables.getLastNameHebrew(), "אבו זקיקה");
//        Assert.assertEquals(variables.getDateOfBirth(), "12.02.1978");
//        Assert.assertEquals(variables.getDateOfIssue(), "04.08.97");
//
//        Assert.assertEquals(variables.getGender(), "נקבה");
//        Assert.assertEquals(variables.getPlaceOfBirth(), "ישראל");
//
//        Assert.assertTrue(variables.isImageIsColorful());
//        Assert.assertTrue(variables.isIdChecksumValid());
//        Assert.assertTrue(variables.isTemplateMatched());
//        Assert.assertTrue(variables.isDocumentInFrame());
//        Assert.assertTrue(variables.isStampDetected());
//        Assert.assertTrue(variables.isFaceSizeValid());
//        Assert.assertTrue(variables.isFacePositionValid());
//        Assert.assertTrue(variables.isFaceRotationValid());
//        Assert.assertTrue(variables.isIssueDateValid());
//
//        Assert.assertEquals(variables.getFaceImage(), "face_image");
//        Assert.assertEquals(variables.getInputImage1(), "input_image");
//        Assert.assertEquals(variables.getCroppedImage1(), "cropped_image");
//
//        Assert.assertTrue(resultsPage.verifyImagesAreReal());

    }

    @Test(description = "new Mrz demo sanity test",retryAnalyzer = utilities.RetryAnalyzer.class,groups = {"smoke"})
    @Severity(SeverityLevel.CRITICAL)
    @Description("Run Demo End to End test of new Mrz")
    public void t06_e2eNewMrzDemoFlow() throws IOException, InterruptedException {
        SanityTest.chooseConfigFile();
        SanityTest.chooseServiceList();
        Injection injection = new Injection(driver);
        injection.imageInjectionConfigure("./ocr/mrz/nadav/nadavMrz.jpg", null, 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
        //       Assert.assertEquals(resultsPage.verifyListsSizes(), 27);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "success");
//        Assert.assertNotNull(variables.getCaseId());


//        Assert.assertEquals(variables.getMrzText(), "P<ISRSHLOMO<<NADAV<<<<<<<<<<<<<<<<<<<<<<<<<<31027245<4ISR9609082M26051883<1539659<8<<<52");
//        Assert.assertEquals(variables.getMrzType(), "TD3");
//        Assert.assertEquals(variables.getDocumentType(), "Passport");
//        Assert.assertEquals(variables.getIssuingCountryCode(), "ISR");
//        Assert.assertEquals(variables.getLastNameEnglish(), "SHLOMO");
//        Assert.assertEquals(variables.getFirstNameEnglish(), "NADAV");
//        Assert.assertEquals(variables.getPassportNumber(), 31027245);
//        Assert.assertEquals(variables.getNationalityCode(), "ISR");
//        Assert.assertEquals(variables.getDateOfBirth(), "08.09.96");
//        Assert.assertEquals(variables.getGender(), "Male");
//        Assert.assertEquals(variables.getDateOfExpiry(), "18.05.26");
//        Assert.assertEquals(variables.getPersonalNumber(), "3-1539659-8");
//
//        Assert.assertEquals(variables.getFaceImage(), "face_image");
//        Assert.assertEquals(variables.getInputImage1(), "input_image");
//        Assert.assertEquals(variables.getAlignedCard(), "aligned_card");
//        Assert.assertEquals(variables.getAlignedField(), "aligned_field");
//        //       Assert.assertEquals(variables.getCroppedImage1(), "aligned_card");
//        Assert.assertTrue(variables.isDocumentInFrame());
//        Assert.assertTrue(variables.isExpiryDateValid());
//        Assert.assertFalse(variables.isFaceSizeValid());
//        Assert.assertFalse(variables.isFacePositionValid());
//        Assert.assertTrue(variables.isFaceRotationValid());
//
//        Assert.assertTrue(resultsPage.verifyImagesAreReal());
    }


    @Test(description = "old Mrz demo sanity test",retryAnalyzer = utilities.RetryAnalyzer.class,groups = {"smoke"})
    @Severity(SeverityLevel.CRITICAL)
    @Description("Run Demo End to End test of old Mrz")
    public void t07_e2eOldMrzDemoFlow() throws IOException, InterruptedException {
        SanityTest.chooseConfigFile();
        SanityTest.chooseServiceList();
        Injection injection = new Injection(driver);
        injection.imageInjectionConfigure("./ocr/mrz/guy/guyMrz.jpg", null, 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
//        Assert.assertEquals(resultsPage.verifyListsSizes(), 27);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "success");
        //     Assert.assertNotNull(variables.getCaseId());


//        Assert.assertEquals(variables.getMrzText(), "P<ISRSTIEBEL<<GUY<<<<<<<<<<<<<<<<<<<<<<<<<<<23369458<8ISR8609199M26120410<2649440<1<<<80");
//        Assert.assertEquals(variables.getMrzType(), "TD3");
//        Assert.assertEquals(variables.getDocumentType(), "Passport");
//
//        Assert.assertEquals(variables.getIssuingCountryCode(), "ISR");
//        Assert.assertEquals(variables.getLastNameEnglish(), "STIEBEL");
//        Assert.assertEquals(variables.getFirstNameEnglish(), "GUY");
//        Assert.assertEquals(variables.getPassportNumber(), 23369458);
//        Assert.assertEquals(variables.getNationalityCode(), "ISR");
//        Assert.assertEquals(variables.getDateOfBirth(), "19.09.86");
//        Assert.assertEquals(variables.getGender(), "Male");
//        Assert.assertEquals(variables.getDateOfExpiry(), "04.12.26");
//        Assert.assertEquals(variables.getPersonalNumber(), "0-2649440-1");
//
//        Assert.assertEquals(variables.getFaceImage(), "face_image");
//        Assert.assertEquals(variables.getInputImage1(), "input_image");
//        Assert.assertEquals(variables.getAlignedCard(), "aligned_card");
//        Assert.assertEquals(variables.getAlignedField(), "aligned_field");
//
//
//        Assert.assertTrue(variables.isDocumentInFrame());
//        Assert.assertTrue(variables.isExpiryDateValid());
//        Assert.assertFalse(variables.isFaceSizeValid());
//        Assert.assertFalse(variables.isFacePositionValid());
//        Assert.assertTrue(variables.isFaceRotationValid());
//
//        Assert.assertTrue(resultsPage.verifyImagesAreReal());
    }

    @Test(description = "new dl demo sanity test",retryAnalyzer = utilities.RetryAnalyzer.class,groups = {"smoke"})
    @Severity(SeverityLevel.CRITICAL)
    @Description("Run Demo End to End test of new dl")
    public void t08_e2eNewDlDemoFlow() throws IOException, InterruptedException {
        SanityTest.chooseConfigFile();
        SanityTest.chooseServiceList();
        Injection injection = new Injection(driver);
        injection.imageInjectionConfigure("./ocr/dl/newDLFront.jpg", "./ocr/dl/newDLBack.jpg", 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
//        Assert.assertEquals(resultsPage.verifyListsSizes(), 36);
        resultsPage.collectResults(variables);


        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "success");


        //Front section
//        Assert.assertEquals(variables.getCardType1(), "new_front");
//        Assert.assertEquals(variables.getLastNameHebrew(), "עבוש");
//        Assert.assertEquals(variables.getLastNameEnglish(), "ABOSH");
//        Assert.assertEquals(variables.getFirstNameHebrew(), "מנחם מאור");
//        Assert.assertEquals(variables.getFirstNameEnglish(), "MENACHEM MAOR");
//        Assert.assertEquals(variables.getDateOfBirth(), "26.03.1992");
//        Assert.assertEquals(variables.getDateOfIssue(), "31.03.2016");
//        Assert.assertEquals(variables.getDateOfExpiry(), "26.03.2026");
//        Assert.assertEquals(variables.getIdNumber(), 203114798);
//        Assert.assertEquals(Integer.toString(variables.getLicenseNumber()), "9182620");
//        Assert.assertEquals(variables.getAddress(), "ביאליק 40א בית שמש");
//        Assert.assertEquals(variables.getLicenseCategories(), "A1 B");
//
//        Assert.assertTrue(variables.isIdChecksumValid());
//        Assert.assertTrue(variables.isTemplateMatched());
//        Assert.assertTrue(variables.isDocumentInFrame());
//        Assert.assertTrue(variables.isExpiryDateValid());
//        Assert.assertTrue(variables.isFaceSizeValid());
//        Assert.assertTrue(variables.isFacePositionValid());
//        Assert.assertTrue(variables.isFaceRotationValid());
//
//        Assert.assertEquals(variables.getFaceImage(), "face_image");
//        Assert.assertEquals(variables.getInputImage1(), "input_image");
//        Assert.assertEquals(variables.getCroppedImage1(), "cropped_image");
//
//        //Back section
//        Assert.assertEquals(variables.getCardType1(), "new_front");
//        Assert.assertEquals(variables.getIdNumber(), 203114798);
//        Assert.assertEquals(variables.getbYear(), 2009);
//
//        Assert.assertEquals(variables.getInputImage1(), "input_image");
//        Assert.assertEquals(variables.getCroppedImage1(), "cropped_image");
//
//        Assert.assertTrue(variables.isIdChecksumValid());
//        Assert.assertTrue(variables.isTemplateMatched());
//        Assert.assertTrue(variables.isDocumentInFrame());
//
//        Assert.assertTrue(resultsPage.verifyImagesAreReal());

    }

    @Test(description = "old dl demo sanity test",retryAnalyzer = utilities.RetryAnalyzer.class,groups = {"smoke"})
    @Severity(SeverityLevel.CRITICAL)
    @Description("Run Demo End to End test of old dl")
    public void t09_e2eOldDlDemoFlow() throws IOException, InterruptedException {
        selenium.ngocr.test.newDl.SanityTest.chooseConfigFile();
        SanityTest.chooseServiceList();
        Injection injection = new Injection(driver);
        injection.imageInjectionConfigure("./ocr/dl/oldDLFront.jpg", "./ocr/dl/oldDLBack.jpg", 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
        //       Assert.assertEquals(resultsPage.verifyListsSizes(), 36);
        resultsPage.collectResults(variables);


        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "success");


        //Front section
//        Assert.assertEquals(variables.getCardType1(), "old_front");
//        Assert.assertEquals(variables.getLastNameHebrew(), "גרוס פישמן");
//        Assert.assertEquals(variables.getLastNameEnglish(), "FISHMAMGROSS");
//        Assert.assertEquals(variables.getFirstNameHebrew(), "מאיה");
//        Assert.assertEquals(variables.getFirstNameEnglish(), "MAYA");
//        Assert.assertEquals(variables.getDateOfBirth(), "06.01.1978");
//        Assert.assertEquals(variables.getDateOfIssue(), "14.12.2006");
//        Assert.assertEquals(variables.getDateOfExpiry(), "06.01.2017");
//        Assert.assertEquals(variables.getIdNumber(), 34471045);
//        Assert.assertEquals(Integer.toString(variables.getLicenseNumber()), "6905739");
//        Assert.assertEquals(variables.getAddress(), "אלוף דוד 58 רמת גן");
//        Assert.assertEquals(variables.getLicenseCategories(), "B");
//
//        Assert.assertTrue(variables.isIdChecksumValid());
//        Assert.assertTrue(variables.isTemplateMatched());
//        Assert.assertTrue(variables.isDocumentInFrame());
//        Assert.assertFalse(variables.isExpiryDateValid());
//        Assert.assertTrue(variables.isFaceSizeValid());
//        Assert.assertTrue(variables.isFacePositionValid());
//        Assert.assertTrue(variables.isFaceRotationValid());
//
//        Assert.assertEquals(variables.getFaceImage(), "face_image");
//        Assert.assertEquals(variables.getInputImage1(), "input_image");
//        Assert.assertEquals(variables.getCroppedImage1(), "cropped_image");
//
//        //Back section
//        Assert.assertEquals(variables.getCardType1(), "old_front");
//        Assert.assertEquals(variables.getIdNumber(), 34471045);
//        Assert.assertEquals(variables.getbYear(), 1995);
//
//        Assert.assertEquals(variables.getInputImage1(), "input_image");
//        Assert.assertEquals(variables.getCroppedImage1(), "cropped_image");
//
//        Assert.assertTrue(variables.isIdChecksumValid());
//        Assert.assertTrue(variables.isTemplateMatched());
//        Assert.assertTrue(variables.isDocumentInFrame());
//
//        Assert.assertTrue(resultsPage.verifyImagesAreReal());
    }
}
