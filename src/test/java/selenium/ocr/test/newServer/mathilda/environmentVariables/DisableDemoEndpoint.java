package selenium.ocr.test.newServer.mathilda.environmentVariables;

import api.ApiResponse;
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
import utilities.MongoDB.Variables.Ocr.*;
import utilities.MongoDB.Variables.StartSessionVariables;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class DisableDemoEndpoint extends BaseTest {

    Variables variables;
    MongoHandler mongoHandler;
    MainPage mainPage;
    ApiResponse apiResponse;
    String library;
    Injection injection;

    @BeforeMethod
    public void startSession() {
        library = null;
        variables = new Variables();
        mongoHandler = new MongoHandler();
        apiResponse = new ApiResponse();
        mainPage = new MainPage(driver);
        injection = new Injection(driver);
        driver.get(OCR_DEMO);
    }

    @Test(description = "IL-ID sanity test with direct URL to the flow")
    @Description("IL-ID sanity test with direct URL because the demo is disabled")
    public void t01_ILIDWithDirectUrl() throws IOException, InterruptedException {
        HttpURLConnection con = (HttpURLConnection) new URL(driver.getCurrentUrl()).openConnection();

        System.out.println("Status code: " + con.getResponseCode());
        Assert.assertEquals(con.getResponseCode(), 404);
        library = "IL-ID";
        String newCaseId = apiResponse.randomString();
        System.out.println(newCaseId);
        String directUrl = "https://ocr-qa.scanovate.com/demo/?params={%22url%22:%22https:\\/\\/ocr-qa.scanovate.com%22," +
                "%22extraData%22:{%22caseId%22:%22" + newCaseId + "%22,%22libraryName%22:%22" + library + "%22,%22flowConfigName%22:%22" + "mathilda.json" + "%22}}";
        driver.get(directUrl);

        injection.imageInjectionConfigure("./ocr/bioID/nitzan/front/NitzanBioFront1.jpg", "./ocr/bioID/nitzan/back/NitzanBioBack1.jpg", 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 41);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "failure");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "mathilda.json");
        Assert.assertTrue(variables.isSuccess());

        //Front section
        Assert.assertEquals(variables.getCardType1(), "bio_front");
        Assert.assertEquals(variables.getLastNameHebrew(), "שוקר");
        Assert.assertEquals(variables.getFirstNameHebrew(), "ניצן");
        Assert.assertEquals(variables.getDateOfBirth(), "21.08.1988");
        Assert.assertEquals(variables.getDateOfIssue(), "23.5.2019");
        Assert.assertEquals(variables.getDateOfExpiry(), "20.05.2029");
        Assert.assertEquals(variables.getIdNumber(), 200761625);

        Assert.assertTrue(variables.isImageIsColorful());
        Assert.assertTrue(variables.isIdChecksumValid());
        Assert.assertTrue(variables.isTemplateMatched());
        Assert.assertTrue(variables.isDocumentInFrame());
        Assert.assertTrue(variables.isPeriodBetweenIssueAndExpiry());
        Assert.assertTrue(variables.isChipAuth());
        Assert.assertFalse(variables.isFaceImageReplacedWithPassportImage());
        Assert.assertTrue(variables.isFaceSizeValid());
        Assert.assertTrue(variables.isFacePositionValid());
        Assert.assertTrue(variables.isFaceRotationValid());
        Assert.assertTrue(variables.isExpiryDateValid());
        Assert.assertTrue(variables.isIssueDateValid());

        Assert.assertEquals(variables.getFaceImage(), "face_image");
        Assert.assertEquals(variables.getInputImage1(), "input_image");
        Assert.assertEquals(variables.getCroppedImage1(), "cropped_image");

        //Back section
        Assert.assertEquals(variables.getCardType2(), "bio_back");
        Assert.assertEquals(variables.getIdNumber(), 200761625);
        Assert.assertEquals(variables.getPlaceOfBirth(), "ישראל");
        Assert.assertEquals(variables.getGender(), "זכר");

        Assert.assertTrue(variables.isImageIsColorful());
        Assert.assertTrue(variables.isIdChecksumValid());
        Assert.assertTrue(variables.isTemplateMatched());
        Assert.assertTrue(variables.isDocumentInFrame());
        Assert.assertTrue(variables.isIdNumberMatchesFront());

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
        Assert.assertEquals(StartSessionVariables.getServiceType(), "IL-ID");
        Assert.assertEquals(StartSessionVariables.getStatus(), "session_start");
        Assert.assertEquals(StartSessionVariables.getWorkingMode(), "multi");

        if (System.getProperty("mongoCheck","false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertNotNull(CommonVariables.getObjectId());
        Assert.assertEquals(CommonVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(CommonVariables.getConfigFilename(), "mathilda.json");
        Assert.assertEquals(CommonVariables.getServiceType(), "IL-ID");
        Assert.assertEquals(CommonVariables.getStatus(), "failure");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Image injection attempt detected");
        Assert.assertTrue(CommonVariables.isSuccess());
        Assert.assertNotNull(CommonVariables.getVideo());
        Assert.assertEquals(CommonVariables.getWorkingMode(), "multi");

        Assert.assertEquals(OcrHandlers.getActionType(), "stage");
        Assert.assertEquals(OcrHandlers.getOrder(), 1);
        Assert.assertEquals(OcrHandlers.getTypeUnderStage(), "front");

        Assert.assertEquals(ILIDVariables.getLastNameHebrew(), "שוקר");
        Assert.assertEquals(ILIDVariables.getFirstNameHebrew(), "ניצן");
        Assert.assertEquals(ILIDVariables.getDateOfBirth(), "21.08.1988");
        Assert.assertEquals(ILIDVariables.getDateOfIssue(), "23.5.2019");
        Assert.assertEquals(ILIDVariables.getDateOfExpiry(), "20.05.2029");
        Assert.assertEquals(ILIDVariables.getIdNumber(), "200761625");
        Assert.assertEquals(ILIDVariables.getCardType(), "bio_front");
        Assert.assertNotNull(ILIDVariables.getFaceImage());
        Assert.assertNotNull(ILIDVariables.getInputImage());
        Assert.assertNotNull(ILIDVariables.getCroppedImage());
        Assert.assertTrue(ILIDVariables.isImageIsColorful());
        Assert.assertTrue(ILIDVariables.isIdChecksumValid());
        Assert.assertTrue(ILIDVariables.isTemplateMatch());
        Assert.assertTrue(ILIDVariables.isPeriodBetweenIssueAndExpiryValid());
        Assert.assertTrue(ILIDVariables.isChipAuth());
        Assert.assertTrue(ILIDVariables.isFaceImageReplacedWithPassportImage());
        Assert.assertTrue(ILIDVariables.isFaceSizeValid());
        Assert.assertTrue(ILIDVariables.isFacePositionValid());
        Assert.assertTrue(ILIDVariables.isFaceRotationValid());
        Assert.assertTrue(ILIDVariables.isDocumentInFrame());
        Assert.assertTrue(ILIDVariables.isExpiryDateValid());
        Assert.assertTrue(ILIDVariables.isIssueDateValid());
        Assert.assertEquals(OcrHandlers.getStageStatus(), "success");

        Assert.assertEquals(OcrHandlers.getActionType2(), "complete");
        Assert.assertEquals(OcrHandlers.getOrder2(), 2);
        Assert.assertEquals(OcrHandlers.getTypeUnderStage2(), "back");
        Assert.assertEquals(ILIDVariables.getIdNumber2(), "200761625");
        Assert.assertEquals(ILIDVariables.getPlaceOfBirth(), "ישראל");
        Assert.assertEquals(ILIDVariables.getGender(), "זכר");
        Assert.assertEquals(ILIDVariables.getCardType2(), "bio_back");
        Assert.assertNotNull(ILIDVariables.getInputImage2());
        Assert.assertNotNull(ILIDVariables.getCroppedImage2());
        Assert.assertTrue(ILIDVariables.isImageIsColorful2());
        Assert.assertTrue(ILIDVariables.isIdChecksumValid2());
        Assert.assertTrue(ILIDVariables.isTemplateMatch2());
        Assert.assertTrue(ILIDVariables.isIdNumberMatchesFront());
        Assert.assertTrue(ILIDVariables.isDocumentInFrame2());
        Assert.assertEquals(OcrHandlers.getStageStatus2(), "success");

        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-ocr");
    }

    @Test(description = "IL-DL sanity test with direct URL to the flow")
    @Description("IL-DL sanity test with direct URL because the demo is disabled")
    public void t02_ILDLWithDirectUrl() throws IOException, InterruptedException {
        HttpURLConnection con = (HttpURLConnection) new URL(driver.getCurrentUrl()).openConnection();

        System.out.println("Status code: " + con.getResponseCode());
        Assert.assertEquals(con.getResponseCode(), 404);
        library = "IL-DL";
        String newCaseId = apiResponse.randomString();
        System.out.println(newCaseId);
        String directUrl = "https://ocr-qa.scanovate.com/demo/?params={%22url%22:%22https:\\/\\/ocr-qa.scanovate.com%22," +
                "%22extraData%22:{%22caseId%22:%22" + newCaseId + "%22,%22libraryName%22:%22" + library + "%22,%22flowConfigName%22:%22" + "mathilda.json" + "%22}}";
        driver.get(directUrl);

        injection.imageInjectionConfigure("./ocr/dl/newDLFront.jpg", "./ocr/dl/newDLBack.jpg", 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 35);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "failure");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "mathilda.json");
        Assert.assertTrue(variables.isSuccess());

        //Front section
        Assert.assertEquals(variables.getCardType1(), "new_front");
        Assert.assertEquals(variables.getLastNameHebrew(), "עבוש");
        Assert.assertEquals(variables.getLastNameEnglish(), "ABOSH");
        Assert.assertEquals(variables.getFirstNameHebrew(), "מנחם מאור");
        Assert.assertEquals(variables.getFirstNameEnglish(), "MENACHEM MAOR");
        Assert.assertEquals(variables.getDateOfBirth(), "26.03.1992");
        Assert.assertEquals(variables.getDateOfIssue(), "31.03.2016");
        Assert.assertEquals(variables.getDateOfExpiry(), "26.03.2026");
        Assert.assertEquals(variables.getIdNumber(), 203114798);
        Assert.assertEquals(Integer.toString(variables.getLicenseNumber()), "9182620");
        Assert.assertEquals(variables.getAddress(), "ביאליק 40א בית שמש");
        Assert.assertEquals(variables.getLicenseCategories(), "A1 B");
        Assert.assertTrue(variables.isIdChecksumValid());
        Assert.assertTrue(variables.isTemplateMatched());
        Assert.assertTrue(variables.isDocumentInFrame());
        Assert.assertTrue(variables.isExpiryDateValid());
        Assert.assertTrue(variables.isFaceSizeValid());
        Assert.assertTrue(variables.isFacePositionValid());
        Assert.assertFalse(variables.isFaceRotationValid());

        Assert.assertEquals(variables.getFaceImage(), "face_image");
        Assert.assertEquals(variables.getInputImage1(), "input_image");
        Assert.assertEquals(variables.getCroppedImage1(), "cropped_image");

        //Back section
        Assert.assertEquals(variables.getCardType1(), "new_front");
        Assert.assertEquals(variables.getIdNumber(), 203114798);
        Assert.assertEquals(variables.getbYear(), 2009);

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

        Assert.assertEquals(ILDLVariables.getLastNameHebrew(), "עבוש");
        Assert.assertEquals(ILDLVariables.getLastNameEnglish(), "ABOSH");
        Assert.assertEquals(ILDLVariables.getFirstNameHebrew(), "מנחם מאור");
        Assert.assertEquals(ILDLVariables.getFirstNameEnglish(), "MENACHEM MAOR");
        Assert.assertEquals(ILDLVariables.getDateOfBirth(), "26.03.1992");
        Assert.assertEquals(ILDLVariables.getDateOfIssue(), "31.03.2016");
        Assert.assertEquals(ILDLVariables.getDateOfExpiry(), "26.03.2026");
        Assert.assertEquals(ILDLVariables.getLicenseNumber(), "9182620");
        Assert.assertEquals(ILDLVariables.getIdNumber(), "203114798");
        Assert.assertEquals(ILDLVariables.getAddress(), "ביאליק 40א בית שמש");
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
        Assert.assertFalse(ILDLVariables.isFaceRotationValid());
        Assert.assertEquals(OcrHandlers.getStageStatus(), "success");

        Assert.assertEquals(OcrHandlers.getActionType2(), "complete");
        Assert.assertEquals(OcrHandlers.getOrder2(), 2);
        Assert.assertEquals(OcrHandlers.getTypeUnderStage2(), "back");

        Assert.assertEquals(ILDLVariables.getIdNumber2(), "203114798");
        Assert.assertEquals(ILDLVariables.getbYear(), "2009");
        Assert.assertEquals(ILDLVariables.getCardType2(), "new_back");
        Assert.assertNotNull(ILDLVariables.getInputImage2());
        Assert.assertNotNull(ILDLVariables.getCroppedImage2());
        Assert.assertTrue(ILDLVariables.isIdChecksumValid2());
        Assert.assertTrue(ILDLVariables.isTemplateMatched2());
        Assert.assertTrue(ILDLVariables.isDocumentInFrame2());
        Assert.assertEquals(OcrHandlers.getStageStatus2(), "success");

        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-ocr");
    }

    //    @Test(description = "DK-DL sanity test with direct URL to the flow")
    @Description("DK-DL sanity test with direct URL because the demo is disabled")
    public void t03_DKDLWithDirectUrl() throws IOException, InterruptedException {
        HttpURLConnection con = (HttpURLConnection) new URL(driver.getCurrentUrl()).openConnection();

        System.out.println("Status code: " + con.getResponseCode());
        Assert.assertEquals(con.getResponseCode(), 404);
        library = "DK-DL";
        String newCaseId = apiResponse.randomString();
        System.out.println(newCaseId);
        String directUrl = "https://ocr-qa.scanovate.com/demo/?params={%22url%22:%22https:\\/\\/ocr-qa.scanovate.com%22," +
                "%22extraData%22:{%22caseId%22:%22" + newCaseId + "%22,%22libraryName%22:%22" + library + "%22,%22flowConfigName%22:%22" + "mathilda.json" + "%22}}";
        driver.get(directUrl);

        injection.imageInjectionConfigure("./ocr/denmarkDrivingLicense/newDKDL/314764922.jpg", null, 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 16);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "failure");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "mathilda.json");
        Assert.assertTrue(variables.isSuccess());
        Assert.assertEquals(variables.getCardType1(), "new_front");

        //Front fields
        Assert.assertEquals(variables.getLastNameEnglish(), "Petersen");
        Assert.assertEquals(variables.getFirstNameEnglish(), "Fredrik Lang");
        Assert.assertEquals(variables.getDateOfBirth(), "16.10.1998");
        Assert.assertEquals(variables.getDateOfIssue(), "09.12.2016");
        Assert.assertEquals(variables.getDateOfExpiry(), "09.12.2031");
        Assert.assertEquals(variables.getPersonalNumber(), "161098-2103");
        Assert.assertEquals(Integer.toString(variables.getLicenseNumber()), "31476492");

        Assert.assertEquals(variables.getInputImage1(), "input_image");
        Assert.assertEquals(variables.getCroppedImage1(), "cropped_image");
        Assert.assertEquals(variables.getFaceImage(), "face_image");

        Assert.assertTrue(resultsPage.verifyImagesAreReal());

        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck","false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), "session_start");
		
        Assert.assertNotNull(StartSessionVariables.getObjectId());
        Assert.assertEquals(StartSessionVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(StartSessionVariables.getServerType(), "modular-server-ocr");
        Assert.assertEquals(StartSessionVariables.getServiceType(), "DK-DL");
        Assert.assertEquals(StartSessionVariables.getStatus(), "session_start");
        Assert.assertEquals(StartSessionVariables.getWorkingMode(), "multi");

        if (System.getProperty("mongoCheck","false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertNotNull(CommonVariables.getObjectId());
        Assert.assertEquals(CommonVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(CommonVariables.getConfigFilename(), "mathilda.json");
        Assert.assertEquals(CommonVariables.getServiceType(), "DK-DL");
        Assert.assertEquals(CommonVariables.getStatus(), "failure");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Image injection attempt detected");
        Assert.assertTrue(CommonVariables.isSuccess());
        Assert.assertNotNull(CommonVariables.getVideo());
        Assert.assertEquals(CommonVariables.getWorkingMode(), "multi");

        Assert.assertEquals(OcrHandlers.getActionType(), "complete");
        Assert.assertEquals(OcrHandlers.getOrder(), 1);
        Assert.assertEquals(OcrHandlers.getTypeUnderStage(), "front");

        Assert.assertEquals(DKDLVariables.getLastName(), "Petersen");
        Assert.assertEquals(DKDLVariables.getFirstName(), "Fredrik Lang");
        Assert.assertEquals(DKDLVariables.getDateOfBirth(), "16.10.1998");
        Assert.assertEquals(DKDLVariables.getDateOfIssue(), "09.12.2016");
        Assert.assertEquals(DKDLVariables.getDateOfExpiry(), "09.12.2031");
        Assert.assertEquals(DKDLVariables.getPersonalNumber(), "161098-2103");
        Assert.assertEquals(DKDLVariables.getLicenseNumber(), "31476492");
        Assert.assertEquals(DKDLVariables.getCardType(), "new_front");

        Assert.assertNotNull(DKDLVariables.getFaceImage());
        Assert.assertNotNull(DKDLVariables.getInputImage());
        Assert.assertNotNull(DKDLVariables.getCroppedImage());

        Assert.assertEquals(OcrHandlers.getStageStatus(), "success");
        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-ocr");
    }

    //   @Test(description = "PHC sanity test with direct URL to the flow")
    @Description("PHC sanity test with direct URL because the demo is disabled")
    public void t04_PHCWithDirectUrl() throws IOException, InterruptedException {
        HttpURLConnection con = (HttpURLConnection) new URL(driver.getCurrentUrl()).openConnection();

        System.out.println("Status code: " + con.getResponseCode());
        Assert.assertEquals(con.getResponseCode(), 404);
        library = "PHL-CHEQUES";
        String newCaseId = apiResponse.randomString();
        System.out.println(newCaseId);
        String directUrl = "https://ocr-qa.scanovate.com/demo/?params={%22url%22:%22https:\\/\\/ocr-qa.scanovate.com%22," +
                "%22extraData%22:{%22caseId%22:%22" + newCaseId + "%22,%22libraryName%22:%22" + library + "%22,%22flowConfigName%22:%22" + "mathilda.json" + "%22}}";
        driver.get(directUrl);

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
		
        Assert.assertNotNull(StartSessionVariables.getObjectId());
        Assert.assertEquals(StartSessionVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(StartSessionVariables.getServerType(), "modular-server-ocr");
        Assert.assertEquals(StartSessionVariables.getServiceType(), "PHL-CHEQUES");
        Assert.assertEquals(StartSessionVariables.getStatus(), "session_start");
        Assert.assertEquals(StartSessionVariables.getWorkingMode(), "multi");

        if (System.getProperty("mongoCheck","false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertNotNull(CommonVariables.getObjectId());
        Assert.assertEquals(CommonVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(CommonVariables.getConfigFilename(), "mathilda.json");
        Assert.assertEquals(CommonVariables.getServiceType(), "PHL-CHEQUES");
        Assert.assertEquals(CommonVariables.getStatus(), "failure");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Image injection attempt detected");
        Assert.assertTrue(CommonVariables.isSuccess());
        Assert.assertNotNull(CommonVariables.getVideo());
        Assert.assertEquals(CommonVariables.getWorkingMode(), "multi");

        Assert.assertEquals(OcrHandlers.getActionType(), "complete");
        Assert.assertEquals(OcrHandlers.getOrder(), 1);
        Assert.assertEquals(OcrHandlers.getTypeUnderStage(), "front");

        Assert.assertEquals(PHCVariables.getChequeNumber(), "c0009000262c01028d0593a000115134213c000");
        Assert.assertNotNull(PHCVariables.getInputImage());

        Assert.assertEquals(OcrHandlers.getStageStatus(), "success");
        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-ocr");
    }

    @Test(description = "MRZ sanity test with direct URL to the flow")
    @Description("MRZ sanity test with direct URL because the demo is disabled")
    public void t05_MRZWithDirectUrl() throws IOException, InterruptedException {
        HttpURLConnection con = (HttpURLConnection) new URL(driver.getCurrentUrl()).openConnection();

        System.out.println("Status code: " + con.getResponseCode());
        Assert.assertEquals(con.getResponseCode(), 404);
        library = "MRZ";
        String newCaseId = apiResponse.randomString();
        System.out.println(newCaseId);
        String directUrl = "https://ocr-qa.scanovate.com/demo/?params={%22url%22:%22https:\\/\\/ocr-qa.scanovate.com%22," +
                "%22extraData%22:{%22caseId%22:%22" + newCaseId + "%22,%22libraryName%22:%22" + library + "%22,%22flowConfigName%22:%22" + "mathilda.json" + "%22}}";
        driver.get(directUrl);

        injection.imageInjectionConfigure("./ocr/mrz/nadav/nadavMrz.jpg", null, 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 27);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "failure");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "mathilda.json");
        Assert.assertTrue(variables.isSuccess());

        Assert.assertEquals(variables.getMrzText(), "P<ISRSHLOMO<<NADAV<<<<<<<<<<<<<<<<<<<<<<<<<<31027245<4ISR9609082M26051883<1539659<8<<<52");
        Assert.assertEquals(variables.getMrzType(), "TD3");
        Assert.assertEquals(variables.getDocumentType(), "passport");
        Assert.assertEquals(variables.getIssuingCountryCode(), "ISR");
        Assert.assertEquals(variables.getLastNameEnglish(), "SHLOMO");
        Assert.assertEquals(variables.getFirstNameEnglish(), "NADAV");
        Assert.assertEquals(variables.getPassportNumber(), 31027245);
        Assert.assertEquals(variables.getNationalityCode(), "ISR");
        Assert.assertEquals(variables.getDateOfBirth(), "08/09/96");
        Assert.assertEquals(variables.getGender(), "Male");
        Assert.assertEquals(variables.getDateOfExpiry(), "18/05/26");
        Assert.assertEquals(variables.getPersonalNumber(), "3-1539659-8");
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
		
        Assert.assertNotNull(StartSessionVariables.getObjectId());
        Assert.assertEquals(StartSessionVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(StartSessionVariables.getServerType(), "modular-server-ocr");
        Assert.assertEquals(StartSessionVariables.getServiceType(), "MRZ");
        Assert.assertEquals(StartSessionVariables.getStatus(), "session_start");
        Assert.assertEquals(StartSessionVariables.getWorkingMode(), "multi");

        if (System.getProperty("mongoCheck","false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertNotNull(CommonVariables.getObjectId());
        Assert.assertEquals(CommonVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(CommonVariables.getConfigFilename(), "mathilda.json");
        Assert.assertEquals(CommonVariables.getServiceType(), "MRZ");
        Assert.assertEquals(CommonVariables.getStatus(), "failure");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Image injection attempt detected");
        Assert.assertTrue(CommonVariables.isSuccess());
        Assert.assertNotNull(CommonVariables.getVideo());
        Assert.assertEquals(CommonVariables.getWorkingMode(), "multi");

        Assert.assertEquals(OcrHandlers.getActionType(), "complete");
        Assert.assertEquals(OcrHandlers.getOrder(), 1);
        Assert.assertEquals(OcrHandlers.getTypeUnderStage(), "front");
        Assert.assertEquals(MRZVariables.getMrzText(), "P<ISRSHLOMO<<NADAV<<<<<<<<<<<<<<<<<<<<<<<<<<31027245<4ISR9609082M26051883<1539659<8<<<52");
        Assert.assertEquals(MRZVariables.getMrzType(), "TD3");
        Assert.assertEquals(MRZVariables.getDocumentType(), "passport");
        Assert.assertEquals(MRZVariables.getDocumentSubType(), "");
        Assert.assertEquals(MRZVariables.getIssuingCountryCode(), "ISR");
        Assert.assertEquals(MRZVariables.getLastName(), "SHLOMO");
        Assert.assertEquals(MRZVariables.getFirstName(), "NADAV");
        Assert.assertEquals(MRZVariables.getPassportNumber(), "31027245");
        Assert.assertEquals(MRZVariables.getNationalityCode(), "ISR");
        Assert.assertEquals(MRZVariables.getDateOfBirth(), "08/09/96");
        Assert.assertEquals(MRZVariables.getGender(), "Male");
        Assert.assertEquals(MRZVariables.getDateOfExpiry(), "18/05/26");
        Assert.assertEquals(MRZVariables.getPersonalNumber(), "3-1539659-8");
        Assert.assertFalse(MRZVariables.isMightBeTruncated());
        Assert.assertNotNull(MRZVariables.getFaceImage());
        Assert.assertNotNull(MRZVariables.getInputImage());
        Assert.assertNotNull(MRZVariables.getCroppedImage());
        Assert.assertNotNull(MRZVariables.getCroppedFieldImage());
        Assert.assertTrue(MRZVariables.isDocumentInFrame());
        Assert.assertTrue(MRZVariables.isExpiryDateValid());
        Assert.assertTrue(MRZVariables.isFaceSizeValid());
        Assert.assertTrue(MRZVariables.isFacePositionValid());
        Assert.assertTrue(MRZVariables.isFaceRotationValid());
        Assert.assertEquals(OcrHandlers.getStageStatus(), "success");

        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-ocr");
    }

    @Test(description = "CNI sanity test with direct URL to the flow")
    @Description("CNI sanity test with direct URL because the demo is disabled")
    public void t06_CNIWithDirectUrl() throws IOException, InterruptedException, UnsupportedAudioFileException {
        HttpURLConnection con = (HttpURLConnection) new URL(driver.getCurrentUrl()).openConnection();

        System.out.println("Status code: " + con.getResponseCode());
        Assert.assertEquals(con.getResponseCode(), 404);
        library = "CNI";
        String newCaseId = apiResponse.randomString();
        System.out.println(newCaseId);
        String directUrl = "https://ocr-qa.scanovate.com/demo/?params={%22url%22:%22https:\\/\\/ocr-qa.scanovate.com%22," +
                "%22extraData%22:{%22stagesConfiguration%22:{%22otp%22:{%22number%22:%221111%22}},%22caseId%22:%22" + newCaseId + "%22,%22libraryName%22:%22" + library + "%22,%22flowConfigName%22:%22" + "mathilda.json" + "%22}}";
        driver.get(directUrl);

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
        Assert.assertEquals(variables.getCardType1(), "cni_front");
        Assert.assertEquals(variables.getFaceImage(), "face_image");
        Assert.assertEquals(variables.getInputImage1(), "input_image");
        Assert.assertEquals(variables.getCroppedImage1(), "cropped_image");

        //Back fields
        Assert.assertEquals(variables.getCardType2(), "cni_back");
        Assert.assertEquals(variables.getInputImage1(), "input_image");
        Assert.assertEquals(variables.getCroppedImage1(), "cropped_image");
        Assert.assertEquals(variables.getOtpNum(), 1111);

        Assert.assertTrue(resultsPage.verifyImagesAreReal());

        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck","false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), "session_start");
		
        Assert.assertNotNull(StartSessionVariables.getObjectId());
        Assert.assertEquals(StartSessionVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(StartSessionVariables.getServerType(), "modular-server-ocr");
        Assert.assertEquals(StartSessionVariables.getServiceType(), "CNI");
        Assert.assertEquals(StartSessionVariables.getStatus(), "session_start");
        Assert.assertEquals(StartSessionVariables.getWorkingMode(), "multi");

        if (System.getProperty("mongoCheck","false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertNotNull(CommonVariables.getObjectId());
        Assert.assertEquals(CommonVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(CommonVariables.getConfigFilename(), "mathilda.json");
        Assert.assertEquals(CommonVariables.getServiceType(), "CNI");
        switch (CommonVariables.getStatus()) {
            case "failure": {
                Assert.assertEquals(CommonVariables.getStatus(), "failure");
                Assert.assertEquals(CommonVariables.getStatusReason(), "Image injection attempt detected");
                break;
            }
            case "success": {
                Assert.assertEquals(CommonVariables.getStatus(), "success");
                Assert.assertEquals(CommonVariables.getStatusReason(), "Process ended successfully");
                break;
            }
        }
        Assert.assertTrue(CommonVariables.isSuccess());
        Assert.assertNotNull(CommonVariables.getVideo());
        Assert.assertEquals(CommonVariables.getWorkingMode(), "multi");

        //Front fields
        Assert.assertEquals(OcrHandlers.getActionType(), "stage");
        Assert.assertEquals(OcrHandlers.getOrder(), 1);
        Assert.assertEquals(OcrHandlers.getTypeUnderStage(), "front");
        Assert.assertEquals(CNIVariables.getFrontCardType(), "cni_front");
        Assert.assertNotNull(CNIVariables.getFaceImage());
        Assert.assertNotNull(CNIVariables.getInputImage());
        Assert.assertNotNull(CNIVariables.getCroppedImage());
        Assert.assertEquals(CNIVariables.getStageStatus(), "success");

        //video front side
        Assert.assertEquals(CNIVariables.getActionType3(), "stage");
        Assert.assertEquals(CNIVariables.getOrder3(), 2);
        Assert.assertEquals(CNIVariables.getTypeUnderStage3(), "video");
        Assert.assertEquals(CNIVariables.getStageStatus3(), "success");

        //Back fields
        Assert.assertEquals(OcrHandlers.getActionType2(), "stage");
        Assert.assertEquals(OcrHandlers.getOrder2(), 3);
        Assert.assertEquals(OcrHandlers.getTypeUnderStage2(), "back");
        Assert.assertEquals(CNIVariables.getBackCardType(), "cni_back");
        Assert.assertNotNull(CNIVariables.getInputImage2());
        Assert.assertNotNull(CNIVariables.getCroppedImage2());
        Assert.assertEquals(CNIVariables.getStageStatus2(), "success");

        //video back side
        Assert.assertEquals(CNIVariables.getActionType4(), "stage");
        Assert.assertEquals(CNIVariables.getOrder4(), 4);
        Assert.assertEquals(CNIVariables.getTypeUnderStage4(), "video");
        Assert.assertEquals(CNIVariables.getStageStatus4(), "success");

        //otp
        Assert.assertEquals(CNIVariables.getActionType5(), "complete");
        Assert.assertEquals(CNIVariables.getOrder5(), 5);
        Assert.assertEquals(CNIVariables.getTypeUnderStage5(), "otp");
        Assert.assertEquals(CNIVariables.getOtpNum(), "1111");
        Assert.assertEquals(CNIVariables.getStageStatus5(), "success");

        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-ocr");
    }
}
