package selenium.ocr.test.newServer.sessionsTests;

import api.Variables;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import selenium.Injection;
import selenium.ocr.pageObject.newService.MainPage;
import selenium.ocr.pageObject.newService.ResultsPage;
import selenium.BaseTest;
import utilities.MongoDB.MongoHandler;
import utilities.MongoDB.Variables.CommonVariables;
import utilities.MongoDB.Variables.Ocr.ILDLVariables;
import utilities.MongoDB.Variables.Ocr.OcrHandlers;
import utilities.MongoDB.Variables.StartSessionVariables;

import java.io.IOException;

public class oldDlSessionTests extends BaseTest {

    Variables variables;
    MongoHandler mongoHandler;
    MainPage mainPage;

    @BeforeMethod
    public void startSession() {
        variables = new Variables();
        mongoHandler = new MongoHandler();
        mainPage = new MainPage(driver);
        driver.get(OCR_DEMO);
        mainPage.chooseFromServicesList("Israel Driving License");
    }

    @Test(description = "old il dl positive session test #1")
    @Description("Old IL-DL positive injection folder of full session images #1")
    public void t01_e2ePositiveOldDlFullSessionTest() throws IOException, InterruptedException {
        Injection injection = new Injection(driver);
        injection.injectFolder("./ocr/sessions/oldIlDl/1/front", "./ocr/sessions/oldIlDl/1/back");

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 36);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "success");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "mathilda.json");
        Assert.assertTrue(variables.isSuccess());

        //Front section
        Assert.assertEquals(variables.getCardType1(),"old_front");
        Assert.assertEquals(variables.getLastNameHebrew(), "מאור");
        Assert.assertEquals(variables.getLastNameEnglish(), "MAOR");
        Assert.assertEquals(variables.getFirstNameHebrew(), "אביתר");
        Assert.assertEquals(variables.getFirstNameEnglish(), "EVYATAR");
        Assert.assertEquals(variables.getDateOfBirth(), "04.12.1990");
        Assert.assertEquals(variables.getDateOfIssue(), "05.02.2016");
        Assert.assertEquals(variables.getDateOfExpiry(), "04.12.2025");
        Assert.assertEquals(variables.getIdNumber(), 305485922);
        Assert.assertEquals(Integer.toString(variables.getLicenseNumber()), "9129012");
        Assert.assertEquals(variables.getAddress(), "רבי עקיבא 70 באר שבע");
        Assert.assertEquals(variables.getLicenseCategories(), "B C1");

        Assert.assertTrue(variables.isIdChecksumValid());
        Assert.assertTrue(variables.isTemplateMatched());
        Assert.assertTrue(variables.isDocumentInFrame());
        Assert.assertTrue(variables.isExpiryDateValid());
        Assert.assertTrue(variables.isFaceSizeValid());
        Assert.assertFalse(variables.isFacePositionValid());
        Assert.assertTrue(variables.isFaceRotationValid());

        Assert.assertEquals(variables.getFaceImage(), "face_image");
        Assert.assertEquals(variables.getInputImage1(), "input_image");
        Assert.assertEquals(variables.getCroppedImage1(), "cropped_image");

        //Back section
        Assert.assertEquals(variables.getCardType2(),"old_back");
        Assert.assertEquals(variables.getIdNumber(), 305485922);
        Assert.assertEquals(variables.getbYear(), 2008);

        Assert.assertEquals(variables.getInputImage1(), "input_image");
        Assert.assertEquals(variables.getCroppedImage1(), "cropped_image");

        Assert.assertTrue(variables.isIdChecksumValid());
        Assert.assertTrue(variables.isTemplateMatched());
        Assert.assertTrue(variables.isDocumentInFrame());

        Assert.assertTrue(resultsPage.verifyImagesAreReal());

        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck","false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), "session_start");
		
        Assert.assertNotNull(StartSessionVariables.getObjectId());
        Assert.assertEquals(StartSessionVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(StartSessionVariables.getServerType(), "modular-server-ocr");
        Assert.assertEquals(StartSessionVariables.getServiceType(), "IL-DL");
        Assert.assertEquals(StartSessionVariables.getStatus(), "session_start");
        Assert.assertEquals(StartSessionVariables.getWorkingMode(), "multi");

        if (System.getProperty("mongoCheck","false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertNotNull(CommonVariables.getObjectId());
        Assert.assertEquals(CommonVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(CommonVariables.getConfigFilename(), "mathilda.json");
        Assert.assertEquals(CommonVariables.getServiceType(), "IL-DL");
        Assert.assertEquals(CommonVariables.getStatus(), "failure");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Image injection attempt detected");
        Assert.assertTrue(CommonVariables.isSuccess());
        Assert.assertNotNull(CommonVariables.getVideo());
        Assert.assertEquals(CommonVariables.getWorkingMode(), "multi");

        Assert.assertEquals(OcrHandlers.getActionType(), "stage");
        Assert.assertEquals(OcrHandlers.getOrder(), 1);
        Assert.assertEquals(OcrHandlers.getTypeUnderStage(), "front");

        Assert.assertEquals(ILDLVariables.getLastNameHebrew(), "מאור");
        Assert.assertEquals(ILDLVariables.getLastNameEnglish(), "MAOR");
        Assert.assertEquals(ILDLVariables.getFirstNameHebrew(), "אביתר");
        Assert.assertEquals(ILDLVariables.getFirstNameEnglish(), "EVYATAR");
        Assert.assertEquals(ILDLVariables.getDateOfBirth(), "04.12.1990");
        Assert.assertEquals(ILDLVariables.getDateOfIssue(), "05.02.2016");
        Assert.assertEquals(ILDLVariables.getDateOfExpiry(), "04.12.2025");
        Assert.assertEquals(ILDLVariables.getLicenseNumber(), "9129012");
        Assert.assertEquals(ILDLVariables.getIdNumber(), "305485922");
        Assert.assertEquals(ILDLVariables.getAddress(), "רבי עקיבא 70 באר שבע");
        Assert.assertEquals(ILDLVariables.getLicenseCategories(), "B C1");
        Assert.assertEquals(ILDLVariables.getCardType(), "old_front");
        Assert.assertNotNull(ILDLVariables.getFaceImage());
        Assert.assertNotNull(ILDLVariables.getInputImage());
        Assert.assertNotNull(ILDLVariables.getCroppedImage());
        Assert.assertTrue(ILDLVariables.isIdChecksumValid());
        Assert.assertTrue(ILDLVariables.isTemplateMatched());
        Assert.assertTrue(ILDLVariables.isDocumentInFrame());
        Assert.assertTrue(ILDLVariables.isExpiryDateValid());
        Assert.assertTrue(ILDLVariables.isFaceSizeValid());
        Assert.assertFalse(ILDLVariables.isFacePositionValid());
        Assert.assertTrue(ILDLVariables.isFaceRotationValid());
        Assert.assertEquals(OcrHandlers.getStageStatus(), "success");

        Assert.assertEquals(OcrHandlers.getActionType2(), "complete");
        Assert.assertEquals(OcrHandlers.getOrder2(), 2);
        Assert.assertEquals(OcrHandlers.getTypeUnderStage2(), "back");

        Assert.assertEquals(ILDLVariables.getIdNumber2(), "305485922");
        Assert.assertEquals(ILDLVariables.getbYear(), "2008");
        Assert.assertEquals(ILDLVariables.getCardType2(), "old_back");
        Assert.assertNotNull(ILDLVariables.getInputImage2());
        Assert.assertNotNull(ILDLVariables.getCroppedImage2());
        Assert.assertTrue(ILDLVariables.isIdChecksumValid2());
        Assert.assertTrue(ILDLVariables.isTemplateMatched2());
        Assert.assertTrue(ILDLVariables.isDocumentInFrame2());
        Assert.assertEquals(OcrHandlers.getStageStatus2(), "success");

        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-ocr");
    }

    @Test(description = "old il dl positive session test #3")
    @Description("Old IL-DL positive injection folder of full session images #3")
    public void t02_e2ePositiveOldDlFullSessionTest() throws InterruptedException, IOException {
        Injection injection = new Injection(driver);
        injection.injectFolder("./ocr/sessions/oldIlDl/3/front", "./ocr/sessions/oldIlDl/3/back");

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 36);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "success");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "mathilda.json");
        Assert.assertTrue(variables.isSuccess());

        //Front section
        Assert.assertEquals(variables.getCardType1(),"old_front");
        Assert.assertEquals(variables.getLastNameHebrew(), "מוספא טאהא");
        Assert.assertEquals(variables.getLastNameEnglish(), "MUSTAFA TAHA");
        Assert.assertEquals(variables.getFirstNameHebrew(), "הונוד");
        Assert.assertEquals(variables.getFirstNameEnglish(), "HUIIOD");
        Assert.assertEquals(variables.getDateOfBirth(), "05.02.1977");
        Assert.assertEquals(variables.getDateOfIssue(), "14.05.2012");
        Assert.assertEquals(variables.getDateOfExpiry(), "05.02.2022");
        Assert.assertEquals(variables.getIdNumber(), 33701368);
        Assert.assertEquals(Integer.toString(variables.getLicenseNumber()), "8774956");
        Assert.assertEquals(variables.getAddress(), "ת ד 1802 כפר מנדא");
        Assert.assertEquals(variables.getLicenseCategories(), "B");

        Assert.assertTrue(variables.isIdChecksumValid());
        Assert.assertTrue(variables.isTemplateMatched());
        Assert.assertTrue(variables.isDocumentInFrame());
        Assert.assertFalse(variables.isExpiryDateValid());
        Assert.assertTrue(variables.isFaceSizeValid());
        Assert.assertTrue(variables.isFacePositionValid());
        Assert.assertTrue(variables.isFaceRotationValid());

        Assert.assertEquals(variables.getFaceImage(), "face_image");
        Assert.assertEquals(variables.getInputImage1(), "input_image");
        Assert.assertEquals(variables.getCroppedImage1(), "cropped_image");

        //Back section
        Assert.assertEquals(variables.getCardType2(),"old_back");
        Assert.assertEquals(variables.getIdNumber(), 33701368);
        Assert.assertEquals(variables.getbYear(), 2005);

        Assert.assertEquals(variables.getInputImage1(), "input_image");
        Assert.assertEquals(variables.getCroppedImage1(), "cropped_image");

        Assert.assertTrue(variables.isIdChecksumValid());
        Assert.assertTrue(variables.isTemplateMatched());
        Assert.assertTrue(variables.isDocumentInFrame());

        Assert.assertTrue(resultsPage.verifyImagesAreReal());

        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck","false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), "session_start");
		
        Assert.assertNotNull(StartSessionVariables.getObjectId());
        Assert.assertEquals(StartSessionVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(StartSessionVariables.getServerType(), "modular-server-ocr");
        Assert.assertEquals(StartSessionVariables.getServiceType(), "IL-DL");
        Assert.assertEquals(StartSessionVariables.getStatus(), "session_start");
        Assert.assertEquals(StartSessionVariables.getWorkingMode(), "multi");

        if (System.getProperty("mongoCheck","false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertNotNull(CommonVariables.getObjectId());
        Assert.assertEquals(CommonVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(CommonVariables.getConfigFilename(), "mathilda.json");
        Assert.assertEquals(CommonVariables.getServiceType(), "IL-DL");
        Assert.assertEquals(CommonVariables.getStatus(), "failure");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Image injection attempt detected");
        Assert.assertTrue(CommonVariables.isSuccess());
        Assert.assertNotNull(CommonVariables.getVideo());
        Assert.assertEquals(CommonVariables.getWorkingMode(), "multi");

        Assert.assertEquals(OcrHandlers.getActionType(), "stage");
        Assert.assertEquals(OcrHandlers.getOrder(), 1);
        Assert.assertEquals(OcrHandlers.getTypeUnderStage(), "front");

        Assert.assertEquals(ILDLVariables.getLastNameHebrew(), "מוספא טאהא");
        Assert.assertEquals(ILDLVariables.getLastNameEnglish(), "MUSTAFA TAHA");
        Assert.assertEquals(ILDLVariables.getFirstNameHebrew(), "הונוד");
        Assert.assertEquals(ILDLVariables.getFirstNameEnglish(), "HUIIOD");
        Assert.assertEquals(ILDLVariables.getDateOfBirth(), "05.02.1977");
        Assert.assertEquals(ILDLVariables.getDateOfIssue(), "14.05.2012");
        Assert.assertEquals(ILDLVariables.getDateOfExpiry(), "05.02.2022");
        Assert.assertEquals(ILDLVariables.getLicenseNumber(), "8774956");
        Assert.assertEquals(ILDLVariables.getIdNumber(), "033701368");
        Assert.assertEquals(ILDLVariables.getAddress(), "ת ד 1802 כפר מנדא");
        Assert.assertEquals(ILDLVariables.getLicenseCategories(), "B");
        Assert.assertEquals(ILDLVariables.getCardType(), "old_front");
        Assert.assertNotNull(ILDLVariables.getFaceImage());
        Assert.assertNotNull(ILDLVariables.getInputImage());
        Assert.assertNotNull(ILDLVariables.getCroppedImage());
        Assert.assertTrue(ILDLVariables.isIdChecksumValid());
        Assert.assertTrue(ILDLVariables.isTemplateMatched());
        Assert.assertTrue(ILDLVariables.isDocumentInFrame());
        Assert.assertFalse(ILDLVariables.isExpiryDateValid());
        Assert.assertTrue(ILDLVariables.isFaceSizeValid());
        Assert.assertTrue(ILDLVariables.isFacePositionValid());
        Assert.assertTrue(ILDLVariables.isFaceRotationValid());
        Assert.assertEquals(OcrHandlers.getStageStatus(), "success");

        Assert.assertEquals(OcrHandlers.getActionType2(), "complete");
        Assert.assertEquals(OcrHandlers.getOrder2(), 2);
        Assert.assertEquals(OcrHandlers.getTypeUnderStage2(), "back");

        Assert.assertEquals(ILDLVariables.getIdNumber2(), "033701368");
        Assert.assertEquals(ILDLVariables.getbYear(), "2005");
        Assert.assertEquals(ILDLVariables.getCardType2(), "old_back");
        Assert.assertNotNull(ILDLVariables.getInputImage2());
        Assert.assertNotNull(ILDLVariables.getCroppedImage2());
        Assert.assertTrue(ILDLVariables.isIdChecksumValid2());
        Assert.assertTrue(ILDLVariables.isTemplateMatched2());
        Assert.assertTrue(ILDLVariables.isDocumentInFrame2());
        Assert.assertEquals(OcrHandlers.getStageStatus2(), "success");

        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-ocr");
    }

    @Test(description = "old il dl positive session test #3")
    @Description("Old IL-DL positive injection folder of full session images #4")
    public void t03_e2ePositiveOldDlFullSessionTest() throws InterruptedException, IOException {
        Injection injection = new Injection(driver);
        injection.injectFolder("./ocr/sessions/oldIlDl/4/front", "./ocr/sessions/oldIlDl/4/back");

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 36);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "success");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "mathilda.json");
        Assert.assertTrue(variables.isSuccess());

        //Front section
        Assert.assertEquals(variables.getCardType1(),"old_front");
        Assert.assertEquals(variables.getLastNameHebrew(), "לרנר");
        Assert.assertEquals(variables.getLastNameEnglish(), "LERNER");
        Assert.assertEquals(variables.getFirstNameHebrew(), "ךןעי");
        Assert.assertEquals(variables.getFirstNameEnglish(), "ROI");
        Assert.assertEquals(variables.getDateOfBirth(), "19.04.1997");
        Assert.assertEquals(variables.getDateOfIssue(), "19.08.2016");
        Assert.assertEquals(variables.getDateOfExpiry(), "19.04.2021");
        Assert.assertEquals(variables.getIdNumber(), 315039222);
        Assert.assertEquals(Integer.toString(variables.getLicenseNumber()), "9644449");
        Assert.assertEquals(variables.getAddress(), "שרונה שרונה");
        Assert.assertEquals(variables.getLicenseCategories(), "1 B");

        Assert.assertTrue(variables.isIdChecksumValid());
        Assert.assertTrue(variables.isTemplateMatched());
        Assert.assertTrue(variables.isDocumentInFrame());
        Assert.assertFalse(variables.isExpiryDateValid());
        Assert.assertTrue(variables.isFaceSizeValid());
        Assert.assertTrue(variables.isFacePositionValid());
        Assert.assertTrue(variables.isFaceRotationValid());

        Assert.assertEquals(variables.getFaceImage(), "face_image");
        Assert.assertEquals(variables.getInputImage1(), "input_image");
        Assert.assertEquals(variables.getCroppedImage1(), "cropped_image");

        //Back section
        Assert.assertEquals(variables.getCardType2(),"old_back");
        Assert.assertEquals(variables.getIdNumber(), 315039222);
        Assert.assertEquals(variables.getbYear(), 2014);

        Assert.assertEquals(variables.getInputImage1(), "input_image");
        Assert.assertEquals(variables.getCroppedImage1(), "cropped_image");

        Assert.assertTrue(variables.isIdChecksumValid());
        Assert.assertTrue(variables.isTemplateMatched());
        Assert.assertTrue(variables.isDocumentInFrame());

        Assert.assertTrue(resultsPage.verifyImagesAreReal());

        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck","false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), "session_start");
		
        Assert.assertNotNull(StartSessionVariables.getObjectId());
        Assert.assertEquals(StartSessionVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(StartSessionVariables.getServerType(), "modular-server-ocr");
        Assert.assertEquals(StartSessionVariables.getServiceType(), "IL-DL");
        Assert.assertEquals(StartSessionVariables.getStatus(), "session_start");
        Assert.assertEquals(StartSessionVariables.getWorkingMode(), "multi");

        if (System.getProperty("mongoCheck","false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertNotNull(CommonVariables.getObjectId());
        Assert.assertEquals(CommonVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(CommonVariables.getConfigFilename(), "mathilda.json");
        Assert.assertEquals(CommonVariables.getServiceType(), "IL-DL");
        Assert.assertEquals(CommonVariables.getStatus(), "failure");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Image injection attempt detected");
        Assert.assertTrue(CommonVariables.isSuccess());
        Assert.assertNotNull(CommonVariables.getVideo());
        Assert.assertEquals(CommonVariables.getWorkingMode(), "multi");

        Assert.assertEquals(OcrHandlers.getActionType(), "stage");
        Assert.assertEquals(OcrHandlers.getOrder(), 1);
        Assert.assertEquals(OcrHandlers.getTypeUnderStage(), "front");

        Assert.assertEquals(ILDLVariables.getLastNameHebrew(), "לרנר");
        Assert.assertEquals(ILDLVariables.getLastNameEnglish(), "LERNER");
        Assert.assertEquals(ILDLVariables.getFirstNameHebrew(), "ךןעי");
        Assert.assertEquals(ILDLVariables.getFirstNameEnglish(), "ROI");
        Assert.assertEquals(ILDLVariables.getDateOfBirth(), "19.04.1997");
        Assert.assertEquals(ILDLVariables.getDateOfIssue(), "19.08.2016");
        Assert.assertEquals(ILDLVariables.getDateOfExpiry(), "19.04.2021");
        Assert.assertEquals(ILDLVariables.getLicenseNumber(), "9644449");
        Assert.assertEquals(ILDLVariables.getIdNumber(), "315039222");
        Assert.assertEquals(ILDLVariables.getAddress(), "שרונה שרונה");
        Assert.assertEquals(ILDLVariables.getLicenseCategories(), "1 B");
        Assert.assertEquals(ILDLVariables.getCardType(), "old_front");
        Assert.assertNotNull(ILDLVariables.getFaceImage());
        Assert.assertNotNull(ILDLVariables.getInputImage());
        Assert.assertNotNull(ILDLVariables.getCroppedImage());
        Assert.assertTrue(ILDLVariables.isIdChecksumValid());
        Assert.assertTrue(ILDLVariables.isTemplateMatched());
        Assert.assertTrue(ILDLVariables.isDocumentInFrame());
        Assert.assertFalse(ILDLVariables.isExpiryDateValid());
        Assert.assertTrue(ILDLVariables.isFaceSizeValid());
        Assert.assertTrue(ILDLVariables.isFacePositionValid());
        Assert.assertTrue(ILDLVariables.isFaceRotationValid());
        Assert.assertEquals(OcrHandlers.getStageStatus(), "success");

        Assert.assertEquals(OcrHandlers.getActionType2(), "complete");
        Assert.assertEquals(OcrHandlers.getOrder2(), 2);
        Assert.assertEquals(OcrHandlers.getTypeUnderStage2(), "back");

        Assert.assertEquals(ILDLVariables.getIdNumber2(), "315039222");
        Assert.assertEquals(ILDLVariables.getbYear(), "2014");
        Assert.assertEquals(ILDLVariables.getCardType2(), "old_back");
        Assert.assertNotNull(ILDLVariables.getInputImage2());
        Assert.assertNotNull(ILDLVariables.getCroppedImage2());
        Assert.assertTrue(ILDLVariables.isIdChecksumValid2());
        Assert.assertTrue(ILDLVariables.isTemplateMatched2());
        Assert.assertTrue(ILDLVariables.isDocumentInFrame2());
        Assert.assertEquals(OcrHandlers.getStageStatus2(), "success");

        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-ocr");
    }

    @Test(description = "old il dl positive session test #4")
    @Description("Old IL-DL positive injection folder of full session images #5")
    public void t04_e2ePositiveOldDlFullSessionTest() throws InterruptedException, IOException {
        Injection injection = new Injection(driver);
        injection.injectFolder("./ocr/sessions/oldIlDl/5/front", "./ocr/sessions/oldIlDl/5/back");

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 36);
        resultsPage.collectResults(variables);

        //Common
        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "success");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "mathilda.json");
        Assert.assertTrue(variables.isSuccess());

        //Front section
        Assert.assertEquals(variables.getCardType1(), "old_front");
        Assert.assertEquals(variables.getLastNameHebrew(), "צדוק");
        Assert.assertEquals(variables.getLastNameEnglish(), "ZADOK");
        Assert.assertEquals(variables.getFirstNameHebrew(), "ישראל");
        Assert.assertEquals(variables.getFirstNameEnglish(), "ISRAEL");
        Assert.assertEquals(variables.getDateOfBirth(), "29.10.1962");
        Assert.assertEquals(variables.getDateOfIssue(), "09.09.2012");
        Assert.assertEquals(variables.getDateOfExpiry(), "29.10.2022");
        Assert.assertEquals(variables.getIdNumber(), 57946253);
        Assert.assertEquals(variables.getLicenseNumber(), 6823550);
        Assert.assertEquals(variables.getAddress(), "לפידות ו ירושלים");
        Assert.assertEquals(variables.getLicenseCategories(), "A1 B D");
        Assert.assertTrue(variables.isIdChecksumValid());
        Assert.assertTrue(variables.isTemplateMatched());
        Assert.assertTrue(variables.isDocumentInFrame());
        Assert.assertTrue(variables.isFaceSizeValid());
        Assert.assertTrue(variables.isFacePositionValid());
        Assert.assertTrue(variables.isFaceRotationValid());
        Assert.assertFalse(variables.isExpiryDateValid());

        // Images
        Assert.assertEquals(variables.getFaceImage(), "face_image");
        Assert.assertEquals(variables.getInputImage1(), "input_image");
        Assert.assertEquals(variables.getInputImage2(), "input_image");
        Assert.assertEquals(variables.getCroppedImage1(), "cropped_image");
        Assert.assertEquals(variables.getCroppedImage2(), "cropped_image");

        //Back section
        Assert.assertEquals(variables.getCardType2(), "old_back");
        Assert.assertEquals(variables.getIdNumber2(), 57946253);
        Assert.assertEquals(variables.getbYear(), 1995);
        Assert.assertTrue(variables.isIdChecksumValid2());
        Assert.assertTrue(variables.isTemplateMatched2());
        Assert.assertTrue(variables.isDocumentInFrame2());
        Assert.assertTrue(resultsPage.verifyImagesAreReal());


        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck","false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), "session_start");
		
        Assert.assertNotNull(StartSessionVariables.getObjectId());
        Assert.assertEquals(StartSessionVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(StartSessionVariables.getServerType(), "modular-server-ocr");
        Assert.assertEquals(StartSessionVariables.getServiceType(), "IL-DL");
        Assert.assertEquals(StartSessionVariables.getStatus(), "session_start");
        Assert.assertEquals(StartSessionVariables.getWorkingMode(), "multi");

        if (System.getProperty("mongoCheck","false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertNotNull(CommonVariables.getObjectId());
        Assert.assertEquals(CommonVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(CommonVariables.getConfigFilename(), "mathilda.json");
        Assert.assertEquals(CommonVariables.getServiceType(), "IL-DL");
        Assert.assertEquals(CommonVariables.getStatus(), "failure");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Image injection attempt detected");
        Assert.assertTrue(CommonVariables.isSuccess());
        Assert.assertNotNull(CommonVariables.getVideo());
        Assert.assertEquals(CommonVariables.getWorkingMode(), "multi");

        Assert.assertEquals(OcrHandlers.getActionType(), "stage");
        Assert.assertEquals(OcrHandlers.getOrder(), 1);
        Assert.assertEquals(OcrHandlers.getTypeUnderStage(), "front");

        Assert.assertEquals(ILDLVariables.getLastNameHebrew(), "צדוק");
        Assert.assertEquals(ILDLVariables.getLastNameEnglish(), "ZADOK");
        Assert.assertEquals(ILDLVariables.getFirstNameHebrew(), "ישראל");
        Assert.assertEquals(ILDLVariables.getFirstNameEnglish(), "ISRAEL");
        Assert.assertEquals(ILDLVariables.getDateOfBirth(), "29.10.1962");
        Assert.assertEquals(ILDLVariables.getDateOfIssue(), "09.09.2012");
        Assert.assertEquals(ILDLVariables.getDateOfExpiry(), "29.10.2022");
        Assert.assertEquals(ILDLVariables.getLicenseNumber(), "6823550");
        Assert.assertEquals(ILDLVariables.getIdNumber(), "057946253");
        Assert.assertEquals(ILDLVariables.getAddress(), "לפידות ו ירושלים");
        Assert.assertEquals(ILDLVariables.getLicenseCategories(), "A1 B D");
        Assert.assertEquals(ILDLVariables.getCardType(), "old_front");
        Assert.assertNotNull(ILDLVariables.getFaceImage());
        Assert.assertNotNull(ILDLVariables.getInputImage());
        Assert.assertNotNull(ILDLVariables.getCroppedImage());
        Assert.assertTrue(ILDLVariables.isIdChecksumValid());
        Assert.assertTrue(ILDLVariables.isTemplateMatched());
        Assert.assertTrue(ILDLVariables.isDocumentInFrame());
        Assert.assertFalse(ILDLVariables.isExpiryDateValid());
        Assert.assertTrue(ILDLVariables.isFaceSizeValid());
        Assert.assertTrue(ILDLVariables.isFacePositionValid());
        Assert.assertTrue(ILDLVariables.isFaceRotationValid());
        Assert.assertEquals(OcrHandlers.getStageStatus(), "success");

        Assert.assertEquals(OcrHandlers.getActionType2(), "complete");
        Assert.assertEquals(OcrHandlers.getOrder2(), 2);
        Assert.assertEquals(OcrHandlers.getTypeUnderStage2(), "back");

        Assert.assertEquals(ILDLVariables.getIdNumber2(), "057946253");
        Assert.assertEquals(ILDLVariables.getbYear(), "1995");
        Assert.assertEquals(ILDLVariables.getCardType2(), "old_back");
        Assert.assertNotNull(ILDLVariables.getInputImage2());
        Assert.assertNotNull(ILDLVariables.getCroppedImage2());
        Assert.assertTrue(ILDLVariables.isIdChecksumValid2());
        Assert.assertTrue(ILDLVariables.isTemplateMatched2());
        Assert.assertTrue(ILDLVariables.isDocumentInFrame2());
        Assert.assertEquals(OcrHandlers.getStageStatus2(), "success");

        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-ocr");
    }
}