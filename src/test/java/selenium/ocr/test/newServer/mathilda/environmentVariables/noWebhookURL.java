package selenium.ocr.test.newServer.mathilda.environmentVariables;

import api.Variables;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import selenium.Injection;
import selenium.ocr.pageObject.newService.MainPage;
import selenium.ocr.pageObject.newService.ResultsPage;
import selenium.BaseTest;
import utilities.MongoDB.MongoHandler;
import utilities.MongoDB.Variables.StartSessionVariables;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class noWebhookURL extends BaseTest {

    Variables variables;
    MongoHandler mongoHandler;
    MainPage mainPage;

    @BeforeMethod
    public void startSession() {
        variables = new Variables();
        mongoHandler = new MongoHandler();
        mainPage = new MainPage(driver);
        driver.get(OCR_DEMO);
    }

    @Test
    public void t01_ilidWithoutWebhookURL() throws IOException, InterruptedException {
        mainPage.chooseFromServicesList("Israel ID");
        Injection injection = new Injection(driver);
        injection.imageInjectionConfigure("./ocr/blueID/liad/blueID_color1.jpg", null, 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 30);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "failure");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "mathilda.json");
        Assert.assertTrue(variables.isSuccess());

        //Front section
        Assert.assertEquals(variables.getCardType1(),"blue");
        Assert.assertEquals(variables.getDateOfBirth(), "17.01.1993");
        Assert.assertEquals(variables.getDateOfExpiry(), "28.01.2025");
        Assert.assertEquals(variables.getDateOfIssue(), "28.01.2015");
        Assert.assertEquals(variables.getFirstNameHebrew(), "ליעד");
        Assert.assertEquals(variables.getGender(), "זכר");
        Assert.assertEquals(variables.getIdNumber(), 307922328);
        Assert.assertEquals(variables.getLastNameHebrew(), "טובי");
        Assert.assertEquals(variables.getPlaceOfBirth(), "ישראל");

        Assert.assertTrue(variables.isImageIsColorful());
        Assert.assertTrue(variables.isIdChecksumValid());
        Assert.assertTrue(variables.isTemplateMatched());
        Assert.assertTrue(variables.isDocumentInFrame());
        Assert.assertTrue(variables.isPeriodBetweenIssueAndExpiry());
        Assert.assertTrue(variables.isStampDetected());
        Assert.assertTrue(variables.isFaceSizeValid());
        Assert.assertTrue(variables.isFacePositionValid());
        Assert.assertFalse(variables.isFaceRotationValid());
        Assert.assertTrue(variables.isExpiryDateValid());
                Assert.assertTrue(variables.isIssueDateValid());

        Assert.assertEquals(variables.getFaceImage(), "face_image");
        Assert.assertEquals(variables.getInputImage1(), "input_image");
        Assert.assertEquals(variables.getCroppedImage1(), "cropped_image");

        Assert.assertTrue(resultsPage.verifyImagesAreReal());

        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck","false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), "session_start");
		
        Assert.assertNull(StartSessionVariables.getObjectId());
        Assert.assertNull(StartSessionVariables.getCaseId());
    }

    @Test
    public void t02_ildlWithoutWebhookURL() throws IOException, InterruptedException {
        mainPage.chooseFromServicesList("Israel Driving License");
        Injection injection = new Injection(driver);
        injection.injectFolder("./ocr/sessions/newIlDl/1/front", "./ocr/sessions/newIlDl/1/back");

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 35);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "failure");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "mathilda.json");
        Assert.assertTrue(variables.isSuccess());

        //Front section
        Assert.assertEquals(variables.getCardType1(),"new_front");
        Assert.assertEquals(variables.getLastNameHebrew(), "כליב");
        Assert.assertEquals(variables.getLastNameEnglish(), "KLEB");
        Assert.assertEquals(variables.getFirstNameHebrew(), "נור");
        Assert.assertEquals(variables.getFirstNameEnglish(), "NOUR");
        Assert.assertEquals(variables.getDateOfBirth(), "24.11.1998");
        Assert.assertEquals(variables.getDateOfIssue(), "21.03.2021");
        Assert.assertEquals(variables.getDateOfExpiry(), "24.11.2025");
        Assert.assertEquals(variables.getIdNumber(), 209158385);
        Assert.assertEquals(Integer.toString(variables.getLicenseNumber()), "1790842");
        Assert.assertEquals(variables.getAddress(), "ת״ד 88 שייח׳ דנון");
        Assert.assertEquals(variables.getLicenseCategories(), "B");

        Assert.assertTrue(variables.isIdChecksumValid());
        Assert.assertTrue(variables.isTemplateMatched());
        Assert.assertTrue(variables.isDocumentInFrame());
        Assert.assertTrue(variables.isExpiryDateValid());
        Assert.assertTrue(variables.isFaceSizeValid());
        Assert.assertTrue(variables.isFacePositionValid());
        Assert.assertTrue(variables.isFaceRotationValid());

        Assert.assertEquals(variables.getFaceImage(), "face_image");
        Assert.assertEquals(variables.getInputImage1(), "input_image");
        Assert.assertEquals(variables.getCroppedImage1(), "cropped_image");

        //Back section
        Assert.assertEquals(variables.getCardType2(),"new_back");
        Assert.assertEquals(variables.getIdNumber(), 209158385);
        Assert.assertEquals(variables.getbYear(), 2019);

        Assert.assertTrue(variables.isIdChecksumValid());
        Assert.assertTrue(variables.isTemplateMatched());
        Assert.assertTrue(variables.isDocumentInFrame());

        Assert.assertEquals(variables.getInputImage1(), "input_image");
        Assert.assertEquals(variables.getCroppedImage1(), "cropped_image");

        Assert.assertTrue(resultsPage.verifyImagesAreReal());

        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck","false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), "session_start");
		
        Assert.assertNull(StartSessionVariables.getObjectId());
        Assert.assertNull(StartSessionVariables.getCaseId());
    }

    @Test
    public void t03_cniWithoutWebhookURL() throws UnsupportedAudioFileException, IOException, InterruptedException {
        mainPage.chooseFromServicesList("CNI");
        Injection injection = new Injection(driver);
        injection.imageInjectionWithTilt("./ocr/cni/good_fr_3.jpg", "./ocr/cni/good_fr_back_1.jpg", 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 13);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "failure");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "mathilda.json");
        Assert.assertTrue(variables.isSuccess());

        //Front fields
        Assert.assertEquals(variables.getCardType1(),"cni_front");

        Assert.assertEquals(variables.getFaceImage(), "face_image");
        Assert.assertEquals(variables.getInputImage1(), "input_image");
        Assert.assertEquals(variables.getCroppedImage1(), "cropped_image");

        //Back fields
        Assert.assertEquals(variables.getCardType2(),"cni_back");
        Assert.assertEquals(variables.getInputImage1(), "input_image");
        Assert.assertEquals(variables.getCroppedImage1(), "cropped_image");

        Assert.assertEquals(variables.getOtpNum(), 1111);

        Assert.assertTrue(resultsPage.verifyImagesAreReal());

        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck","false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), "session_start");
		
        Assert.assertNull(StartSessionVariables.getObjectId());
        Assert.assertNull(StartSessionVariables.getCaseId());
    }

//    @Test
    public void t04_phcWithoutWebhookURL() throws IOException, InterruptedException {
        mainPage.chooseFromServicesList("Philippines Cheque");
        Injection injection = new Injection(driver);
        injection.imageInjection("./ocr/cheque/PhilippinesCheque/RCBCCheque2.jpg", 1, false);

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 7);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "failure");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "mathilda.json");
        Assert.assertTrue(variables.isSuccess());

        Assert.assertEquals(variables.getChequeNumber(), "c0009000262c01028d0593a000115134213c000");
        Assert.assertEquals(variables.getInputImage1(), "input_image");

        Assert.assertTrue(resultsPage.verifyImagesAreReal());

        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck","false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), "session_start");
		
        Assert.assertNull(StartSessionVariables.getObjectId());
        Assert.assertNull(StartSessionVariables.getCaseId());
    }

    @Test
    public void t05_mrzWithoutWebhookURL() throws IOException, InterruptedException {
        mainPage.chooseFromServicesList("MRZ");
        Injection injection = new Injection(driver);
        injection.imageInjectionConfigure("./ocr/mrz/guy/guyMrz.jpg", null, 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 27);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "failure");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "mathilda.json");
        Assert.assertTrue(variables.isSuccess());

        Assert.assertEquals(variables.getMrzText(), "P<ISRSTIEBEL<<GUY<<<<<<<<<<<<<<<<<<<<<<<<<<<23369458<8ISR8609199M26120410<2649440<1<<<80");
        Assert.assertEquals(variables.getMrzType(), "TD3");
        Assert.assertEquals(variables.getDocumentType(), "passport");
        Assert.assertEquals(variables.getIssuingCountryCode(), "ISR");
        Assert.assertEquals(variables.getLastNameEnglish(), "STIEBEL");
        Assert.assertEquals(variables.getFirstNameEnglish(), "GUY");
        Assert.assertEquals(variables.getPassportNumber(), 23369458);
        Assert.assertEquals(variables.getNationalityCode(), "ISR");
        Assert.assertEquals(variables.getDateOfBirth(), "19/09/86");
        Assert.assertEquals(variables.getGender(), "Male");
        Assert.assertEquals(variables.getDateOfExpiry(), "04/12/26");
        Assert.assertEquals(variables.getPersonalNumber(), "0-2649440-1");

        Assert.assertEquals(variables.getFaceImage(), "face_image");
        Assert.assertEquals(variables.getInputImage1(), "input_image");
        Assert.assertEquals(variables.getCroppedImage1(), "cropped_image");

        Assert.assertTrue(variables.isDocumentInFrame());
        Assert.assertTrue(variables.isExpiryDateValid());
        Assert.assertTrue(variables.isFaceSizeValid());
        Assert.assertTrue(variables.isFacePositionValid());
        Assert.assertTrue(variables.isFaceRotationValid());

        Assert.assertTrue(resultsPage.verifyImagesAreReal());

        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck","false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), "session_start");
		
        Assert.assertNull(StartSessionVariables.getObjectId());
        Assert.assertNull(StartSessionVariables.getCaseId());
    }

 //   @Test
    public void t06_dkdlWithoutWebhookURL() throws IOException, InterruptedException {
        mainPage.chooseFromServicesList("Denmark Driving License");
        Injection injection = new Injection(driver);
        injection.imageInjectionConfigure("./ocr/denmarkDrivingLicense/oldDKDL/210015722.jpg", null, 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 16);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "failure");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "mathilda.json");
        Assert.assertTrue(variables.isSuccess());
        Assert.assertEquals(variables.getCardType1(),"old_front");

        //Front fields
        Assert.assertEquals(variables.getLastNameEnglish(), "Christensen");
        Assert.assertEquals(variables.getFirstNameEnglish(), "Clara Kofoed");
        Assert.assertEquals(variables.getDateOfBirth(), "1982-05-23");
        Assert.assertEquals(variables.getDateOfIssue(), "2001-01-31");
        Assert.assertEquals(variables.getDateOfExpiry(), "2052-05-23");
        Assert.assertEquals(variables.getPersonalNumber(), "230582-1880");
        Assert.assertEquals(Integer.toString(variables.getLicenseNumber()), "21001572");

        Assert.assertEquals(variables.getInputImage1(), "input_image");
        Assert.assertEquals(variables.getCroppedImage1(), "cropped_image");
        Assert.assertEquals(variables.getFaceImage(), "face_image");

        Assert.assertTrue(resultsPage.verifyImagesAreReal());

        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck","false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), "session_start");
		
        Assert.assertNull(StartSessionVariables.getObjectId());
        Assert.assertNull(StartSessionVariables.getCaseId());
    }
}
