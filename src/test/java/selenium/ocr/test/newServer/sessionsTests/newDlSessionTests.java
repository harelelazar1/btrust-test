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

public class newDlSessionTests extends BaseTest {

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

    @Test(description = "new il dl positive session test #1",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("New IL-DL positive injection folder of full session images #1")
    public void t01_e2ePositiveNewDlFullSessionTest() throws IOException, InterruptedException {
        Injection injection = new Injection(driver);
        injection.injectFolder("./ocr/sessions/newIlDl/1/front", "./ocr/sessions/newIlDl/1/back");

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 36);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "success");
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

        Assert.assertEquals(ILDLVariables.getLastNameHebrew(), "כליב");
        Assert.assertEquals(ILDLVariables.getLastNameEnglish(), "KLEB");
        Assert.assertEquals(ILDLVariables.getFirstNameHebrew(), "נור");
        Assert.assertEquals(ILDLVariables.getFirstNameEnglish(), "NOUR");
        Assert.assertEquals(ILDLVariables.getDateOfBirth(), "24.11.1998");
        Assert.assertEquals(ILDLVariables.getDateOfIssue(), "21.03.2021");
        Assert.assertEquals(ILDLVariables.getDateOfExpiry(), "24.11.2025");
        Assert.assertEquals(ILDLVariables.getLicenseNumber(), "1790842");
        Assert.assertEquals(ILDLVariables.getIdNumber(), "209158385");
        Assert.assertEquals(ILDLVariables.getAddress(), "ת״ד 88 שייח׳ דנון");
        Assert.assertEquals(ILDLVariables.getLicenseCategories(), "B");
        Assert.assertEquals(ILDLVariables.getCardType(), "new_front");
        Assert.assertNotNull(ILDLVariables.getFaceImage());
        Assert.assertNotNull(ILDLVariables.getInputImage());
        Assert.assertNotNull(ILDLVariables.getCroppedImage());
        Assert.assertTrue(ILDLVariables.isIdChecksumValid());
        Assert.assertTrue(ILDLVariables.isTemplateMatched());
        Assert.assertTrue(ILDLVariables.isDocumentInFrame());
        Assert.assertTrue(ILDLVariables.isExpiryDateValid());
        Assert.assertTrue(ILDLVariables.isFaceSizeValid());
        Assert.assertTrue(ILDLVariables.isFacePositionValid());
        Assert.assertTrue(ILDLVariables.isFaceRotationValid());
        Assert.assertEquals(OcrHandlers.getStageStatus(), "success");

        Assert.assertEquals(OcrHandlers.getActionType2(), "complete");
        Assert.assertEquals(OcrHandlers.getOrder2(), 2);
        Assert.assertEquals(OcrHandlers.getTypeUnderStage2(), "back");

        Assert.assertEquals(ILDLVariables.getIdNumber2(), "209158385");
        Assert.assertEquals(ILDLVariables.getbYear(), "2019");
        Assert.assertEquals(ILDLVariables.getCardType2(), "new_back");
        Assert.assertNotNull(ILDLVariables.getInputImage2());
        Assert.assertNotNull(ILDLVariables.getCroppedImage2());
        Assert.assertTrue(ILDLVariables.isIdChecksumValid2());
        Assert.assertTrue(ILDLVariables.isTemplateMatched2());
        Assert.assertTrue(ILDLVariables.isDocumentInFrame2());
        Assert.assertEquals(OcrHandlers.getStageStatus2(), "success");

        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-ocr");
    }

    @Test(description = "new il dl positive session test #2",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("New IL-DL positive injection folder of full session images #2")
    public void t02_e2ePositiveNewDlFullSessionTest() throws InterruptedException, IOException {
        Injection injection = new Injection(driver);
        injection.injectFolder("./ocr/sessions/newIlDl/2/front", "./ocr/sessions/newIlDl/2/back");

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 36);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "success");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "mathilda.json");
        Assert.assertTrue(variables.isSuccess());

        //Front section
        Assert.assertEquals(variables.getCardType1(),"new_front");
        Assert.assertEquals(variables.getLastNameHebrew(), "מנחם");
        Assert.assertEquals(variables.getLastNameEnglish(), "MENACHEM");
        Assert.assertEquals(variables.getFirstNameHebrew(), "עמית יוסף");
        Assert.assertEquals(variables.getFirstNameEnglish(), "AMIT YOSLF");
        Assert.assertEquals(variables.getDateOfBirth(), "03.03.1996");
        Assert.assertEquals(variables.getDateOfIssue(), "14.03.2021");
        Assert.assertEquals(variables.getDateOfExpiry(), "03.03.2031");
        Assert.assertEquals(variables.getIdNumber(), 314733775);
        Assert.assertEquals(Integer.toString(variables.getLicenseNumber()), "9754940");
        Assert.assertEquals(variables.getAddress(), "דלתון 97 דלתון");
        Assert.assertEquals(variables.getLicenseCategories(), "1 B");

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
        Assert.assertEquals(variables.getIdNumber(), 314733775);
        Assert.assertEquals(variables.getbYear(), 2014);

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
        Assert.assertEquals(CommonVariables.getStatus(), "success");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Image injection attempt detected");
        Assert.assertTrue(CommonVariables.isSuccess());
        Assert.assertNotNull(CommonVariables.getVideo());
        Assert.assertEquals(CommonVariables.getWorkingMode(), "multi");

        Assert.assertEquals(OcrHandlers.getActionType(), "stage");
        Assert.assertEquals(OcrHandlers.getOrder(), 1);
        Assert.assertEquals(OcrHandlers.getTypeUnderStage(), "front");

        Assert.assertEquals(ILDLVariables.getLastNameHebrew(), "מנחם");
        Assert.assertEquals(ILDLVariables.getLastNameEnglish(), "MENACHEM");
        Assert.assertEquals(ILDLVariables.getFirstNameHebrew(), "עמית יוסף");
        Assert.assertEquals(ILDLVariables.getFirstNameEnglish(), "AMIT YOSLF");
        Assert.assertEquals(ILDLVariables.getDateOfBirth(), "03.03.1996");
        Assert.assertEquals(ILDLVariables.getDateOfIssue(), "14.03.2021");
        Assert.assertEquals(ILDLVariables.getDateOfExpiry(), "03.03.2031");
        Assert.assertEquals(ILDLVariables.getLicenseNumber(), "9754940");
        Assert.assertEquals(ILDLVariables.getIdNumber(), "314733775");
        Assert.assertEquals(ILDLVariables.getAddress(), "דלתון 97 דלתון");
        Assert.assertEquals(ILDLVariables.getLicenseCategories(), "1 B");
        Assert.assertEquals(ILDLVariables.getCardType(), "new_front");
        Assert.assertNotNull(ILDLVariables.getFaceImage());
        Assert.assertNotNull(ILDLVariables.getInputImage());
        Assert.assertNotNull(ILDLVariables.getCroppedImage());
        Assert.assertTrue(ILDLVariables.isIdChecksumValid());
        Assert.assertTrue(ILDLVariables.isTemplateMatched());
        Assert.assertTrue(ILDLVariables.isDocumentInFrame());
        Assert.assertTrue(ILDLVariables.isExpiryDateValid());
        Assert.assertTrue(ILDLVariables.isFaceSizeValid());
        Assert.assertTrue(ILDLVariables.isFacePositionValid());
        Assert.assertTrue(ILDLVariables.isFaceRotationValid());
        Assert.assertEquals(OcrHandlers.getStageStatus(), "success");

        Assert.assertEquals(OcrHandlers.getActionType2(), "complete");
        Assert.assertEquals(OcrHandlers.getOrder2(), 2);
        Assert.assertEquals(OcrHandlers.getTypeUnderStage2(), "back");

        Assert.assertEquals(ILDLVariables.getIdNumber2(), "314733775");
        Assert.assertEquals(ILDLVariables.getbYear(), "2014");
        Assert.assertEquals(ILDLVariables.getCardType2(), "new_back");
        Assert.assertNotNull(ILDLVariables.getInputImage2());
        Assert.assertNotNull(ILDLVariables.getCroppedImage2());
        Assert.assertTrue(ILDLVariables.isIdChecksumValid2());
        Assert.assertTrue(ILDLVariables.isTemplateMatched2());
        Assert.assertTrue(ILDLVariables.isDocumentInFrame2());
        Assert.assertEquals(OcrHandlers.getStageStatus2(), "success");

        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-ocr");
    }

    @Test(description = "new il dl positive session test #3")
    @Description("New IL-DL positive injection folder of full session images #4")
    public void t03_e2ePositiveNewDlFullSessionTest() throws InterruptedException, IOException {
        Injection injection = new Injection(driver);
        injection.injectFolder("./ocr/sessions/newIlDl/4/front", "./ocr/sessions/newIlDl/4/back");

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 36);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "success");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "mathilda.json");
        Assert.assertTrue(variables.isSuccess());

        //Front section
        Assert.assertEquals(variables.getCardType1(),"new_front");
        Assert.assertEquals(variables.getLastNameHebrew(), "פרץ");
        Assert.assertEquals(variables.getLastNameEnglish(), "PERETS");
        Assert.assertEquals(variables.getFirstNameHebrew(), "ליאור");
        Assert.assertEquals(variables.getFirstNameEnglish(), "LIOR");
        Assert.assertEquals(variables.getDateOfBirth(), "18.12.1976");
        Assert.assertEquals(variables.getDateOfIssue(), "06.02.2020");
        Assert.assertEquals(variables.getDateOfExpiry(), "18.01.2030");
        Assert.assertEquals(variables.getIdNumber(), 33386871);
        Assert.assertEquals(Integer.toString(variables.getLicenseNumber()), "6634582");
        Assert.assertEquals(variables.getAddress(), "השלושה 25 בת ים");
        Assert.assertEquals(variables.getLicenseCategories(), "A1 B");

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
        Assert.assertEquals(variables.getIdNumber(), 33386871);
        Assert.assertEquals(variables.getbYear(), 1995);

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

        Assert.assertEquals(ILDLVariables.getLastNameHebrew(), "פרץ");
        Assert.assertEquals(ILDLVariables.getLastNameEnglish(), "PERETS");
        Assert.assertEquals(ILDLVariables.getFirstNameHebrew(), "ליאור");
        Assert.assertEquals(ILDLVariables.getFirstNameEnglish(), "LIOR");
        Assert.assertEquals(ILDLVariables.getDateOfBirth(), "18.12.1976");
        Assert.assertEquals(ILDLVariables.getDateOfIssue(), "06.02.2020");
        Assert.assertEquals(ILDLVariables.getDateOfExpiry(), "18.01.2030");
        Assert.assertEquals(ILDLVariables.getLicenseNumber(), "6634582");
        Assert.assertEquals(ILDLVariables.getIdNumber(), "033386871");
        Assert.assertEquals(ILDLVariables.getAddress(), "השלושה 25 בת ים");
        Assert.assertEquals(ILDLVariables.getLicenseCategories(), "A1 B");
        Assert.assertEquals(ILDLVariables.getCardType(), "new_front");
        Assert.assertNotNull(ILDLVariables.getFaceImage());
        Assert.assertNotNull(ILDLVariables.getInputImage());
        Assert.assertNotNull(ILDLVariables.getCroppedImage());
        Assert.assertTrue(ILDLVariables.isIdChecksumValid());
        Assert.assertTrue(ILDLVariables.isTemplateMatched());
        Assert.assertTrue(ILDLVariables.isDocumentInFrame());
        Assert.assertTrue(ILDLVariables.isExpiryDateValid());
        Assert.assertTrue(ILDLVariables.isFaceSizeValid());
        Assert.assertTrue(ILDLVariables.isFacePositionValid());
        Assert.assertTrue(ILDLVariables.isFaceRotationValid());
        Assert.assertEquals(OcrHandlers.getStageStatus(), "success");

        Assert.assertEquals(OcrHandlers.getActionType2(), "complete");
        Assert.assertEquals(OcrHandlers.getOrder2(), 2);
        Assert.assertEquals(OcrHandlers.getTypeUnderStage2(), "back");

        Assert.assertEquals(ILDLVariables.getIdNumber2(), "033386871");
        Assert.assertEquals(ILDLVariables.getbYear(), "1995");
        Assert.assertEquals(ILDLVariables.getCardType2(), "new_back");
        Assert.assertNotNull(ILDLVariables.getInputImage2());
        Assert.assertNotNull(ILDLVariables.getCroppedImage2());
        Assert.assertTrue(ILDLVariables.isIdChecksumValid2());
        Assert.assertTrue(ILDLVariables.isTemplateMatched2());
        Assert.assertTrue(ILDLVariables.isDocumentInFrame2());
        Assert.assertEquals(OcrHandlers.getStageStatus2(), "success");

        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-ocr");
    }

    @Test(description = "new il dl positive session test #4")
    @Description("New IL-DL positive injection folder of full session images #5")
    public void t04_e2ePositiveNewDlFullSessionTest() throws InterruptedException, IOException {
        Injection injection = new Injection(driver);
        injection.injectFolder("./ocr/sessions/newIlDl/5/front", "./ocr/sessions/newIlDl/5/back");

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 36);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "success");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "mathilda.json");
        Assert.assertTrue(variables.isSuccess());

        //Front section
        Assert.assertEquals(variables.getCardType1(),"new_front");
        Assert.assertEquals(variables.getLastNameHebrew(), "שלמה");
        Assert.assertEquals(variables.getLastNameEnglish(), "SHLOMO");
        Assert.assertEquals(variables.getFirstNameHebrew(), "נדב");
        Assert.assertEquals(variables.getFirstNameEnglish(), "NADAV");
        Assert.assertEquals(variables.getDateOfBirth(), "08.09.1996");
        Assert.assertEquals(variables.getDateOfIssue(), "02.08.2020");
        Assert.assertEquals(variables.getDateOfExpiry(), "08.09.2030");
        Assert.assertEquals(variables.getIdNumber(), 315396598);
        Assert.assertEquals(Integer.toString(variables.getLicenseNumber()), "9703471");
        Assert.assertEquals(variables.getAddress(), "צה״ל 68 פתח תקווה");
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
        Assert.assertEquals(variables.getIdNumber(), 315396598);
        Assert.assertEquals(variables.getbYear(), 2013);

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

        Assert.assertEquals(ILDLVariables.getLastNameHebrew(), "שלמה");
        Assert.assertEquals(ILDLVariables.getLastNameEnglish(), "SHLOMO");
        Assert.assertEquals(ILDLVariables.getFirstNameHebrew(), "נדב");
        Assert.assertEquals(ILDLVariables.getFirstNameEnglish(), "NADAV");
        Assert.assertEquals(ILDLVariables.getDateOfBirth(), "08.09.1996");
        Assert.assertEquals(ILDLVariables.getDateOfIssue(), "02.08.2020");
        Assert.assertEquals(ILDLVariables.getDateOfExpiry(), "08.09.2030");
        Assert.assertEquals(ILDLVariables.getLicenseNumber(), "9703471");
        Assert.assertEquals(ILDLVariables.getIdNumber(), "315396598");
        Assert.assertEquals(ILDLVariables.getAddress(), "צה״ל 68 פתח תקווה");
        Assert.assertEquals(ILDLVariables.getLicenseCategories(), "B");
        Assert.assertEquals(ILDLVariables.getCardType(), "new_front");
        Assert.assertNotNull(ILDLVariables.getFaceImage());
        Assert.assertNotNull(ILDLVariables.getInputImage());
        Assert.assertNotNull(ILDLVariables.getCroppedImage());
        Assert.assertTrue(ILDLVariables.isIdChecksumValid());
        Assert.assertTrue(ILDLVariables.isTemplateMatched());
        Assert.assertTrue(ILDLVariables.isDocumentInFrame());
        Assert.assertTrue(ILDLVariables.isExpiryDateValid());
        Assert.assertTrue(ILDLVariables.isFaceSizeValid());
        Assert.assertTrue(ILDLVariables.isFacePositionValid());
        Assert.assertTrue(ILDLVariables.isFaceRotationValid());
        Assert.assertEquals(OcrHandlers.getStageStatus(), "success");

        Assert.assertEquals(OcrHandlers.getActionType2(), "complete");
        Assert.assertEquals(OcrHandlers.getOrder2(), 2);
        Assert.assertEquals(OcrHandlers.getTypeUnderStage2(), "back");

        Assert.assertEquals(ILDLVariables.getIdNumber2(), "315396598");
        Assert.assertEquals(ILDLVariables.getbYear(), "2013");
        Assert.assertEquals(ILDLVariables.getCardType2(), "new_back");
        Assert.assertNotNull(ILDLVariables.getInputImage2());
        Assert.assertNotNull(ILDLVariables.getCroppedImage2());
        Assert.assertTrue(ILDLVariables.isIdChecksumValid2());
        Assert.assertTrue(ILDLVariables.isTemplateMatched2());
        Assert.assertTrue(ILDLVariables.isDocumentInFrame2());
        Assert.assertEquals(OcrHandlers.getStageStatus2(), "success");

        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-ocr");
    }
}
