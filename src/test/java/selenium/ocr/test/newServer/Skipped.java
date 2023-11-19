package selenium.ocr.test.newServer;

import api.Variables;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import selenium.BaseTest;
import selenium.Injection;
import selenium.ocr.pageObject.newService.MainPage;
import selenium.ocr.pageObject.newService.ResultsPage;
import selenium.ocr.pageObject.newService.ScanPage;
import utilities.MongoDB.MongoHandler;
import utilities.MongoDB.Variables.CommonVariables;
import utilities.MongoDB.Variables.Ocr.*;
import utilities.MongoDB.Variables.StartSessionVariables;
import utilities.TestUtils;

import java.io.IOException;

public class Skipped extends BaseTest {

    Variables variables;
    MainPage mainPage;
    MongoHandler mongoHandler;


    @Test(enabled = false,description = "phc mathilda test of custom webhook url from the config file")
    @Description("Mathilda test of custom webhook url from the config file when choosing PHC ocr type")
    public void t05_PHCCustomWebhookUrl() {
        mainPage.chooseFromServicesList("Philippines Cheque");
    }

    @Test(enabled = false,description = "dk dl mathilda test of custom webhook url from the config file")
    @Description("Mathilda test of custom webhook url from the config file when choosing DK-DL ocr type")
    public void t06_DKDLCustomWebhookUrl() {
        mainPage.chooseFromServicesList("Denmark Driving License");
    }

    // Open bug  = https://scanovate.atlassian.net/browse/BD-1019
    @Test(enabled = false,description = "bio Id demo - scan card with face size rotated")
    @Description("bio Id demo - scan card with face size rotated")
    public void t04_bioId_cardWithFaceRotated() throws IOException, InterruptedException {
        Injection injection = new Injection(driver);
        injection.imageInjectionConfigure("./ocr/bioID/nitzan/auth/BioIdWithFaceRotation.jpg", "./ocr/bioID/nitzan/back/NitzanBioBack1.jpg", 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "failure");
        Assert.assertNotNull(variables.getCaseId());

        Assert.assertFalse(variables.isFaceSizeValid());
        Assert.assertFalse(variables.isFacePositionValid());
        Assert.assertFalse(variables.isFaceRotationValid());

        Assert.assertTrue(resultsPage.verifyImagesAreReal());

        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck","false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertFalse(ILIDVariables.isFaceSizeValid());
        Assert.assertFalse(ILIDVariables.isFacePositionValid());
        Assert.assertFalse(ILIDVariables.isFaceRotationValid());
    }
    // Open bug  = https://scanovate.atlassian.net/browse/BD-1019
    @Test(enabled = false,description = "blue id with face size invalid")
    @Description("Blue id with face size invalid auth test")
    public void t03_blueIdSWithFaceSizeInvalid() throws IOException, InterruptedException {
        Injection injection = new Injection(driver);
        injection.imageInjectionConfigure("./ocr/blueID/auth/blueIdWithFaceSizeInvalid.jpg", null, 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "failure");
        Assert.assertNotNull(variables.getCaseId());

        Assert.assertFalse(variables.isStampDetected());
        Assert.assertFalse(variables.isFacePositionValid());
        Assert.assertFalse(variables.isFaceSizeValid());
        Assert.assertTrue(variables.isFaceRotationValid());

        Assert.assertTrue(resultsPage.verifyImagesAreReal());

        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck","false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertFalse(ILIDVariables.isFaceSizeValid());
        Assert.assertFalse(ILIDVariables.isStampDetected());
        Assert.assertTrue(ILIDVariables.isFaceRotationValid());
        Assert.assertTrue(ILIDVariables.isFacePositionValid());
    }
    // Open bug  = https://scanovate.atlassian.net/browse/BD-1019
    @Test(enabled = false,description = "blue id with face size rotated")
    @Description("Blue id with face size rotated auth test")
    public void t04_blueIdSWithFaceRotated() throws IOException, InterruptedException {
        Injection injection = new Injection(driver);
        injection.imageInjectionConfigure("./ocr/blueID/auth/blueIdWithFaceRotated.jpg", null, 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "failure");
        Assert.assertNotNull(variables.getCaseId());

        Assert.assertFalse(variables.isFacePositionValid());
        Assert.assertFalse(variables.isFaceSizeValid());
        Assert.assertFalse(variables.isFaceRotationValid());

        Assert.assertTrue(resultsPage.verifyImagesAreReal());

        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck","false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertFalse(ILIDVariables.isFaceSizeValid());
        Assert.assertFalse(ILIDVariables.isFaceRotationValid());
        Assert.assertFalse(ILIDVariables.isFacePositionValid());
    }

    @Test(enabled = false,description = "denmark new dl - No card detected - demo error message")
    @Description("Denmark new driving license - No card detected - demo error message")
    public void t01_newDKDL_noCardDetectedDemo() throws IOException, InterruptedException {
        Injection injection = new Injection(driver);
        Assert.assertTrue(injection.imageInjectionConfigure("./ocr/blueID/blueIDNotInFrame.jpg", null, 1, true, "No card detected"));
        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(60), 6);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "timeout");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "mathilda.json");
        Assert.assertTrue(variables.isSuccess());

        Assert.assertEquals(variables.getLastReceivedImage(), "last_received_image");

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
        Assert.assertEquals(CommonVariables.getStatus(), "timeout");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Session reached maximum allowed flow timeout");
        Assert.assertTrue(CommonVariables.isSuccess());
        Assert.assertNotNull(CommonVariables.getVideo());
        Assert.assertEquals(CommonVariables.getWorkingMode(), "multi");

        Assert.assertEquals(ILIDVariables.getActionTypeTimeout(), "complete");
        Assert.assertEquals(ILIDVariables.getOrderTimeout(), 1);
        Assert.assertEquals(ILIDVariables.getTypeUnderStageTimeout(), "front");
        Assert.assertEquals(ILIDVariables.getStageStatusTimeout(), "timeout");
        Assert.assertNotNull(ILIDVariables.getLastReceivedImage());

        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-ocr");
    }

    @Test(enabled = false,description = "demo denmark new dl - last name hidden")
    @Description("Demo denmark new driving license - Scanning card with last name hidden")
    public void t02_newDKDL_lastNameHiddenDemo() throws IOException, InterruptedException {
        Injection injection = new Injection(driver);
        injection.imageInjectionConfigure("./ocr/denmarkDrivingLicense/newDKDL/errorhandling/denmarkNewDl_lastNameHidden.jpg", null, 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(60), 15);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "failure");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "mathilda.json");
        Assert.assertTrue(variables.isSuccess());
        Assert.assertEquals(variables.getCardType1(),"new_front");

        Assert.assertNull(resultsPage.valueToCheck(variables, "last_name"));
        if (System.getProperty("mongoCheck","false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertNull(DKDLVariables.getLastName());
    }

    @Test(enabled = false,description = "demo denmark new dl - first name hidden")
    @Description("Demo denmark new driving license - Scanning card with first name hidden")
    public void t03_newDKDL_firstNameHiddenDemo() throws IOException, InterruptedException {
        Injection injection = new Injection(driver);
        injection.imageInjectionConfigure("./ocr/denmarkDrivingLicense/newDKDL/errorhandling/denmarkNewDl_firstNameHidden.jpg", null, 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 15);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "failure");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "mathilda.json");
        Assert.assertTrue(variables.isSuccess());
        Assert.assertEquals(variables.getCardType1(),"new_front");

        Assert.assertNull(resultsPage.valueToCheck(variables, "first_name"));
        if (System.getProperty("mongoCheck","false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertNull(DKDLVariables.getFirstName());
    }

    @Test(enabled = false,description = "demo denmark new dl - date of birth hidden")
    @Description("Demo denmark new driving license - Scanning card with date of birth hidden")
    public void t04_newDKDL_dateOfBirthHiddenDemo() throws IOException, InterruptedException {
        Injection injection = new Injection(driver);
        injection.imageInjectionConfigure("./ocr/denmarkDrivingLicense/newDKDL/errorhandling/denmarkNewDl_dateOfBirthHidden.jpg", null, 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 15);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "failure");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "mathilda.json");
        Assert.assertTrue(variables.isSuccess());
        Assert.assertEquals(variables.getCardType1(),"new_front");

        Assert.assertNull(resultsPage.valueToCheck(variables, "date_of_birth"));
        if (System.getProperty("mongoCheck","false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertNull(DKDLVariables.getDateOfBirth());
    }
    @Test(enabled = false,description = "new dk dl front side timeout")
    @Description("New dk dl demo e2e process of front side timeout")
    public void t01_e2eNewDkDlDemoFrontSideTimeout() throws InterruptedException {
        mainPage.chooseFromServicesList("Denmark Driving License");
        Thread.sleep(85000);

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 6);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "timeout");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "mathilda.json");
        Assert.assertTrue(variables.isSuccess());

        Assert.assertEquals(variables.getLastReceivedImage(), "last_received_image");

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
        Assert.assertEquals(CommonVariables.getStatus(), "timeout");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Session reached maximum allowed flow timeout");
        Assert.assertTrue(CommonVariables.isSuccess());
        Assert.assertNotNull(CommonVariables.getVideo());
        Assert.assertEquals(CommonVariables.getWorkingMode(), "multi");

        Assert.assertEquals(ILIDVariables.getActionTypeTimeout(), "complete");
        Assert.assertEquals(ILIDVariables.getOrderTimeout(), 1);
        Assert.assertEquals(ILIDVariables.getTypeUnderStageTimeout(), "front");
        Assert.assertEquals(ILIDVariables.getStageStatusTimeout(), "timeout");
        Assert.assertNotNull(ILIDVariables.getLastReceivedImage());

        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-ocr");
    }


    @Test(enabled = false,description = "denmark old dl - No card detected - demo error message")
    @Description("Denmark old driving license - No card detected - demo error message")
    public void t01_oldDKDL_noCardDetectedDemo() throws IOException, InterruptedException {
        Injection injection = new Injection(driver);
        Assert.assertTrue(injection.imageInjectionConfigure("./ocr/blueID/blueIDNotInFrame.jpg", null, 1, true, "No card detected"));
        Thread.sleep(70000);

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 6);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "timeout");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "mathilda.json");
        Assert.assertTrue(variables.isSuccess());

        Assert.assertEquals(variables.getLastReceivedImage(), "last_received_image");

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
        Assert.assertEquals(CommonVariables.getStatus(), "timeout");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Session reached maximum allowed flow timeout");
        Assert.assertTrue(CommonVariables.isSuccess());
        Assert.assertNotNull(CommonVariables.getVideo());
        Assert.assertEquals(CommonVariables.getWorkingMode(), "multi");

        Assert.assertEquals(ILIDVariables.getActionTypeTimeout(), "complete");
        Assert.assertEquals(ILIDVariables.getOrderTimeout(), 1);
        Assert.assertEquals(ILIDVariables.getTypeUnderStageTimeout(), "front");
        Assert.assertEquals(ILIDVariables.getStageStatusTimeout(), "timeout");
        Assert.assertNotNull(ILIDVariables.getLastReceivedImage());

        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-ocr");
    }

    @Test(enabled = false,description = "demo denmark old dl - last name hidden")
    @Description("Demo denmark old driving license - Scanning card with last name hidden")
    public void t02_oldDKDL_lastNameHiddenDemo() throws IOException, InterruptedException {
        Injection injection = new Injection(driver);
        injection.imageInjectionConfigure("./ocr/denmarkDrivingLicense/oldDKDL/errorhandling/denmarkOldDl_lastNameHidden.jpg", null, 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 15);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "failure");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "mathilda.json");
        Assert.assertTrue(variables.isSuccess());
        Assert.assertEquals(variables.getCardType1(),"old_front");
        Assert.assertNull(variables.getLastNameEnglish());

        if (System.getProperty("mongoCheck","false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertNull(DKDLVariables.getLastName());
    }

    @Test(enabled = false,description = "demo denmark old dl - first name hidden")
    @Description("Demo denmark old driving license - Scanning card with first name hidden")
    public void t03_oldDKDL_firstNameHiddenDemo() throws IOException, InterruptedException {
        Injection injection = new Injection(driver);
        injection.imageInjectionConfigure("./ocr/denmarkDrivingLicense/oldDKDL/errorhandling/denmarkOldDl_firstNameHidden.jpg", null, 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 15);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "failure");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "mathilda.json");
        Assert.assertTrue(variables.isSuccess());
        Assert.assertEquals(variables.getCardType1(),"old_front");

        Assert.assertNull(resultsPage.valueToCheck(variables, "first_name"));
        if (System.getProperty("mongoCheck","false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertNull(DKDLVariables.getFirstName());
    }

    @Test(enabled = false,description = "demo denmark old dl - date of birth hidden")
    @Description("Demo denmark old driving license - Scanning card with date of birth hidden")
    public void t04_oldDKDL_dateOfBirthHiddenDemo() throws IOException, InterruptedException {
        Injection injection = new Injection(driver);
        injection.imageInjectionConfigure("./ocr/denmarkDrivingLicense/oldDKDL/errorhandling/denmarkOldDl_dateOfBirthHidden.jpg", null, 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 15);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "failure");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "mathilda.json");
        Assert.assertTrue(variables.isSuccess());
        Assert.assertEquals(variables.getCardType1(),"old_front");

        Assert.assertNull(resultsPage.valueToCheck(variables, "date_of_birth"));
        if (System.getProperty("mongoCheck","false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertNull(DKDLVariables.getDateOfBirth());
    }

    @Test(enabled = false,description = "old dk dl front side timeout")
    @Description("Old dk dl demo e2e process of front side timeout")
    public void t01_e2eOldDkDlDemoFrontSideTimeout() throws InterruptedException {
        mainPage.chooseFromServicesList("Denmark Driving License");
        Thread.sleep(85000);

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 6);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "timeout");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "mathilda.json");
        Assert.assertTrue(variables.isSuccess());

        Assert.assertEquals(variables.getLastReceivedImage(), "last_received_image");

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
        Assert.assertEquals(CommonVariables.getStatus(), "timeout");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Session reached maximum allowed flow timeout");
        Assert.assertTrue(CommonVariables.isSuccess());
        Assert.assertNotNull(CommonVariables.getVideo());
        Assert.assertEquals(CommonVariables.getWorkingMode(), "multi");

        Assert.assertEquals(ILIDVariables.getActionTypeTimeout(), "complete");
        Assert.assertEquals(ILIDVariables.getOrderTimeout(), 1);
        Assert.assertEquals(ILIDVariables.getTypeUnderStageTimeout(), "front");
        Assert.assertEquals(ILIDVariables.getStageStatusTimeout(), "timeout");
        Assert.assertNotNull(ILIDVariables.getLastReceivedImage());

        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-ocr");
    }

    @Test(enabled = false,description = "old dk e2e run without cPalette inspection")
    @Description("old dk e2e run without cPalette inspection using the config file - qa_config.json")
    public void t02_e2eWithoutCpaletteInspection() throws IOException, InterruptedException {
        MainPage mainPage = new MainPage(driver);
        mainPage.chooseConfigFile(TestUtils.getDefaultMathilda());
        mainPage.chooseFromServicesList("Denmark Driving License");
        Injection injection = new Injection(driver);
        injection.imageInjectionConfigure("./ocr/denmarkDrivingLicense/oldDKDL/210015722.jpg", null, 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 16);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "success");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), TestUtils.getDefaultMathilda());
        Assert.assertTrue(variables.isSuccess());

        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck","false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertEquals(CommonVariables.getStatus(), "success");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Process ended successfully");
    }
    @Test(enabled = false,description = "denmark old dl demo sanity test")
    @Description("Run Demo End to End test of denmark old dl")
    public void t01_e2eDenmarkOldDlDemoFlow() throws IOException, InterruptedException {
        driver.get(OCR_DEMO);
        MainPage mainPage = new MainPage(driver);

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
        //Due to bad photo of old dk-dl, sometime we get status success, and sometimes we get status failure. after photo will replace, the switch - case will cancel.
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

        Assert.assertEquals(OcrHandlers.getActionType(), "complete");
        Assert.assertEquals(OcrHandlers.getOrder(), 1);
        Assert.assertEquals(OcrHandlers.getTypeUnderStage(), "front");

        Assert.assertEquals(DKDLVariables.getLastName(), "Christensen");
        Assert.assertEquals(DKDLVariables.getFirstName(), "Clara Kofoed");
        Assert.assertEquals(DKDLVariables.getDateOfBirth(), "1982-05-23");
        Assert.assertEquals(DKDLVariables.getDateOfIssue(), "2001-01-31");
        Assert.assertEquals(DKDLVariables.getDateOfExpiry(), "2052-05-23");
        Assert.assertEquals(DKDLVariables.getPersonalNumber(), "230582-1880");
        Assert.assertEquals(DKDLVariables.getLicenseNumber(), "21001572");
        Assert.assertEquals(DKDLVariables.getCardType(), "old_front");

        Assert.assertNotNull(DKDLVariables.getFaceImage());
        Assert.assertNotNull(DKDLVariables.getInputImage());
        Assert.assertNotNull(DKDLVariables.getCroppedImage());

        Assert.assertEquals(OcrHandlers.getStageStatus(), "success");
        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-ocr");
    }

    @Test(enabled = false,description = "green id with face rotated")
    @Description("Green id with face rotated auth test")
    public void t04_greenIdFaceRotated() throws IOException, InterruptedException {
        Injection injection = new Injection(driver);
        injection.imageInjectionConfigure("./ocr/greenID/auth/GreenIdWithFaceRotated.jpg", null, 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "failure");
        Assert.assertNotNull(variables.getCaseId());

        Assert.assertTrue(variables.isFaceSizeValid());
        Assert.assertTrue(variables.isFacePositionValid());
        Assert.assertFalse(variables.isFaceRotationValid());

        Assert.assertTrue(resultsPage.verifyImagesAreReal());

        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck","false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertFalse(ILIDVariables.isFaceSizeValid());
        Assert.assertTrue(ILIDVariables.isFacePositionValid());
        Assert.assertFalse(ILIDVariables.isFaceRotationValid());
    }

    @Test(enabled = false,description = "old dl demo - scan card with face rotated")
    @Description("old Dl demo - scan card with face rotated")
    public void t02_oldDl_cardWithFaceRotated() throws IOException, InterruptedException {
        Injection injection = new Injection(driver);
        injection.imageInjectionConfigure("./ocr/dl/auth/old/OldDlWithFaceRotated.jpg", "./ocr/dl/oldDLBack.jpg", 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "failure");
        Assert.assertNotNull(variables.getCaseId());

        Assert.assertFalse(variables.isFaceSizeValid());
        Assert.assertFalse(variables.isFacePositionValid());
        Assert.assertFalse(variables.isFaceRotationValid());

        Assert.assertTrue(resultsPage.verifyImagesAreReal());

        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck","false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertFalse(ILDLVariables.isFaceSizeValid());
        Assert.assertFalse(ILDLVariables.isFacePositionValid());
        Assert.assertFalse(ILDLVariables.isFaceRotationValid());
    }

    @Test(enabled = false,description = "ucpb bank e2e demo flow")
    @Description("UCPB end to end scan demo flow")
    public void t01_UCPBBankCheque() throws IOException, InterruptedException {
        Injection injection = new Injection(driver);
        injection.imageInjection("./ocr/cheque/PhilippinesCheque/UCPBChueqe.jpg", 1, false);

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 7);
        resultsPage.collectResults(variables);


        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "failure");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "mathilda.json");
        Assert.assertTrue(variables.isSuccess());


        Assert.assertEquals(variables.getChequeNumber(), "c6007357213c01029d0761a201680013798c000");
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

        Assert.assertEquals(PHCVariables.getChequeNumber(), "c6007357213c01029d0761a201680013798c000");
        Assert.assertNotNull(PHCVariables.getInputImage());

        Assert.assertEquals(OcrHandlers.getStageStatus(), "success");
        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-ocr");
    }

    @Test(enabled = false,description = "bdoc bank e2e demo flow")
    @Description("BDOC end to end scan demo flow")
    public void t02_BDOCBankCheque() throws IOException, InterruptedException {
        Injection injection = new Injection(driver);
        injection.imageInjection("./ocr/cheque/PhilippinesCheque/BDOCheque.jpg", 1, false);

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 7);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "failure");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "mathilda.json");
        Assert.assertTrue(variables.isSuccess());

        Assert.assertEquals(variables.getChequeNumber(), "c0000184451c01053d4524a008018016049c000");
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

        Assert.assertEquals(PHCVariables.getChequeNumber(), "c0000184451c01053d4524a008018016049c000");
        Assert.assertNotNull(PHCVariables.getInputImage());

        Assert.assertEquals(OcrHandlers.getStageStatus(), "success");
        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-ocr");
    }

    @Test(enabled = false,description = "china bank e2e demo flow")
    @Description("China bank end to end scan demo flow")
    public void t03_ChinaBankCheque() throws IOException, InterruptedException {
        Injection injection = new Injection(driver);
        injection.imageInjection("./ocr/cheque/PhilippinesCheque/ChinaBankCheque.jpg", 1, false);

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 7);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "failure");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "mathilda.json");
        Assert.assertTrue(variables.isSuccess());

        Assert.assertEquals(variables.getChequeNumber(), "c0002965986c01010d0217a001210278511c000");
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

        Assert.assertEquals(PHCVariables.getChequeNumber(), "c0002965986c01010d0217a001210278511c000");
        Assert.assertNotNull(PHCVariables.getInputImage());

        Assert.assertEquals(OcrHandlers.getStageStatus(), "success");
        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-ocr");
    }

    @Test(enabled = false,description = "rcbc bank e2e demo flow")
    @Description("RCBC end to end scan demo flow")
    public void t04_RCBCBankCheque() throws IOException, InterruptedException {
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

    @Test(enabled = false,description = "not supported bank e2e demo flow")
    @Description("Scanning not supported PHC - israeli cheque, demo flow")
    public void t05_notSupportedPHC() throws IOException, InterruptedException {
        Injection injection = new Injection(driver);
        injection.imageInjection("./ocr/cheque/NotSupported/notSupportedBank.jpg", 1, false);
        Assert.assertTrue(injection.imageInjectionConfigure("./ocr/cheque/NotSupported/notSupportedBank.jpg", null, 1, true, "No cheque detected"));

        Thread.sleep(85000);
        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 6);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "timeout");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "mathilda.json");
        Assert.assertTrue(variables.isSuccess());

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
        Assert.assertEquals(CommonVariables.getStatus(), "timeout");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Session reached maximum allowed flow timeout");
        Assert.assertTrue(CommonVariables.isSuccess());
        Assert.assertNotNull(CommonVariables.getVideo());
        Assert.assertEquals(CommonVariables.getWorkingMode(), "multi");

        Assert.assertEquals(ILIDVariables.getActionTypeTimeout(), "complete");
        Assert.assertEquals(ILIDVariables.getOrderTimeout(), 1);
        Assert.assertEquals(ILIDVariables.getTypeUnderStageTimeout(), "front");
        Assert.assertEquals(ILIDVariables.getStageStatusTimeout(), "timeout");
        Assert.assertNotNull(ILIDVariables.getLastReceivedImage());

        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-ocr");
    }

    @Test(enabled = false,description = "no cheque detected error message in the demo")
    @Description("RCBC Philippine Cheque demo - no cheque detected error message")
    public void t01_noChequeDetectedMessage() throws IOException, InterruptedException {
        Injection injection = new Injection(driver);
        Assert.assertTrue(injection.imageInjectionConfigure("./ocr/blueID/blueIDNotInFrame.jpg", null, 1, true, "No cheque detected"));
        Thread.sleep(70000);
        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 6);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "timeout");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "mathilda.json");
        Assert.assertTrue(variables.isSuccess());

        Assert.assertEquals(variables.getLastReceivedImage(), "last_received_image");

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
        Assert.assertEquals(CommonVariables.getStatus(), "timeout");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Session reached maximum allowed flow timeout");
        Assert.assertTrue(CommonVariables.isSuccess());
        Assert.assertNotNull(CommonVariables.getVideo());
        Assert.assertEquals(CommonVariables.getWorkingMode(), "multi");

        Assert.assertEquals(ILIDVariables.getActionTypeTimeout(), "complete");
        Assert.assertEquals(ILIDVariables.getOrderTimeout(), 1);
        Assert.assertEquals(ILIDVariables.getTypeUnderStageTimeout(), "front");
        Assert.assertEquals(ILIDVariables.getStageStatusTimeout(), "timeout");
        Assert.assertNotNull(ILIDVariables.getLastReceivedImage());

        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-ocr");
    }

    @Test(enabled = false,description = "phc scan with cheque number hidden")
    @Description("Philippine cheque scan with cheque number hidden")
    public void t02_PhilippineCheque_chequeNumberHidden() throws IOException, InterruptedException {
        Injection injection = new Injection(driver);
        injection.imageInjection("./ocr/cheque/PhilippinesCheque/errorhandling/RCBCCheque_chequeNumberHidden.jpg", 1, false);
        ScanPage scanPage = new ScanPage(driver);
        Assert.assertEquals(scanPage.getInstructionsTitle(), "No cheque detected");
        Thread.sleep(85000);
        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 6);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "timeout");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "mathilda.json");
        Assert.assertTrue(variables.isSuccess());

        Assert.assertEquals(variables.getLastReceivedImage(), "last_received_image");

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
        Assert.assertEquals(CommonVariables.getStatus(), "timeout");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Session reached maximum allowed flow timeout");
        Assert.assertTrue(CommonVariables.isSuccess());
        Assert.assertNotNull(CommonVariables.getVideo());
        Assert.assertEquals(CommonVariables.getWorkingMode(), "multi");

        Assert.assertEquals(ILIDVariables.getActionTypeTimeout(), "complete");
        Assert.assertEquals(ILIDVariables.getOrderTimeout(), 1);
        Assert.assertEquals(ILIDVariables.getTypeUnderStageTimeout(), "front");
        Assert.assertEquals(ILIDVariables.getStageStatusTimeout(), "timeout");
        Assert.assertNotNull(ILIDVariables.getLastReceivedImage());

        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-ocr");
    }
    @Test(enabled = false,description = "PHC timeout session")
    @Description("PHC demo e2e timeout session")
    public void t01_e2ePHCDemoTimeoutSession() throws InterruptedException {
        mainPage.chooseFromServicesList("Philippines Cheque");
        Thread.sleep(85000);
        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 6);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "timeout");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "mathilda.json");
        Assert.assertTrue(variables.isSuccess());

        Assert.assertEquals(variables.getLastReceivedImage(), "last_received_image");

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
        Assert.assertEquals(CommonVariables.getStatus(), "timeout");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Session reached maximum allowed flow timeout");
        Assert.assertTrue(CommonVariables.isSuccess());
        Assert.assertNotNull(CommonVariables.getVideo());
        Assert.assertEquals(CommonVariables.getWorkingMode(), "multi");

        Assert.assertEquals(ILIDVariables.getActionTypeTimeout(), "complete");
        Assert.assertEquals(ILIDVariables.getOrderTimeout(), 1);
        Assert.assertEquals(ILIDVariables.getTypeUnderStageTimeout(), "front");
        Assert.assertEquals(ILIDVariables.getStageStatusTimeout(), "timeout");
        Assert.assertNotNull(ILIDVariables.getLastReceivedImage());

        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-ocr");
    }

    @Test(enabled = false,description = "PHC e2e run without cPalette inspection")
    @Description("PHC e2e run without cPalette inspection using the config file - qa_config.json")
    public void t02_e2eWithoutCpaletteInspection1() throws IOException, InterruptedException {
        MainPage mainPage = new MainPage(driver);
        mainPage.chooseConfigFile(TestUtils.getDefaultMathilda());
        mainPage.chooseFromServicesList("Philippines Cheque");
        Injection injection = new Injection(driver);
        injection.imageInjection("./ocr/cheque/PhilippinesCheque/RCBCCheque2.jpg", 1, false);

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 7);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "success");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), TestUtils.getDefaultMathilda());
        Assert.assertTrue(variables.isSuccess());

        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck","false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertEquals(CommonVariables.getStatus(), "success");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Process ended successfully");
    }
    @Test(enabled = false,description = "philippines cheque demo sanity test")
    @Description("Run Demo End to End test of philippines Cheque")
    public void t01_e2ePhilippinesChequeDemoFlow() throws IOException, InterruptedException {
        driver.get(OCR_DEMO);
        MainPage mainPage = new MainPage(driver);

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
    @Test(enabled = false, description = "blue id positive session test #3")
    @Description("Blue id positive injection folder of full session images #3")
    public void t03_e2ePositiveBlueIdFullSessionTest() throws IOException, InterruptedException {
        Injection injection = new Injection(driver);
        injection.injectFolder("./ocr/sessions/blueId/3", null);

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
        Assert.assertEquals(variables.getDateOfBirth(), "13.08.1986");
        Assert.assertEquals(variables.getDateOfExpiry(), "08.11.2026");
        Assert.assertEquals(variables.getDateOfIssue(), "08.11.2016");
        Assert.assertEquals(variables.getFirstNameHebrew(), "דיקלה");
        Assert.assertEquals(variables.getGender(), "נקבה");
        Assert.assertEquals(variables.getIdNumber(), 300113776);
        Assert.assertEquals(variables.getLastNameHebrew(), "קסקס");
        Assert.assertEquals(variables.getPlaceOfBirth(), "ישראל");

        Assert.assertTrue(variables.isImageIsColorful());
        Assert.assertTrue(variables.isIdChecksumValid());
        Assert.assertTrue(variables.isTemplateMatched());
        Assert.assertTrue(variables.isDocumentInFrame());
        Assert.assertTrue(variables.isPeriodBetweenIssueAndExpiry());
        Assert.assertTrue(variables.isStampDetected());
        Assert.assertTrue(variables.isFaceSizeValid());
        Assert.assertTrue(variables.isFacePositionValid());
        Assert.assertTrue(variables.isFaceRotationValid());
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

        Assert.assertEquals(OcrHandlers.getActionType(), "complete");
        Assert.assertEquals(OcrHandlers.getOrder(), 1);
        Assert.assertEquals(OcrHandlers.getTypeUnderStage(), "front");

        Assert.assertEquals(ILIDVariables.getDateOfBirth(), "13.08.1986");
        Assert.assertEquals(ILIDVariables.getDateOfExpiry(), "08.11.2026");
        Assert.assertEquals(ILIDVariables.getDateOfIssue(), "08.11.2016");
        Assert.assertEquals(ILIDVariables.getFirstNameHebrew(), "דיקלה");
        Assert.assertEquals(ILIDVariables.getLastNameHebrew(), "קסקס");
        Assert.assertEquals(ILIDVariables.getGender(), "נקבה");
        Assert.assertEquals(ILIDVariables.getIdNumber(), "300113776");
        Assert.assertEquals(ILIDVariables.getPlaceOfBirth(), "ישראל");
        Assert.assertEquals(ILIDVariables.getCardType(), "blue");
        Assert.assertNotNull(ILIDVariables.getFaceImage());
        Assert.assertNotNull(ILIDVariables.getInputImage());
        Assert.assertNotNull(ILIDVariables.getCroppedImage());
        Assert.assertTrue(ILIDVariables.isImageIsColorful());
        Assert.assertTrue(ILIDVariables.isIdChecksumValid());
        Assert.assertTrue(ILIDVariables.isTemplateMatch());
        Assert.assertTrue(ILIDVariables.isPeriodBetweenIssueAndExpiryValid());
        Assert.assertTrue(ILIDVariables.isStampDetected());
        Assert.assertTrue(ILIDVariables.isFaceSizeValid());
        Assert.assertTrue(ILIDVariables.isFacePositionValid());
        Assert.assertTrue(ILIDVariables.isFaceRotationValid());
        Assert.assertTrue(ILIDVariables.isDocumentInFrame());
        Assert.assertTrue(ILIDVariables.isExpiryDateValid());
        Assert.assertTrue(ILIDVariables.isIssueDateValid());

        Assert.assertEquals(OcrHandlers.getStageStatus(), "success");
        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-ocr");
    }
    @Test(enabled = false, description = "green id positive session test #4")
    @Description("Green id positive injection folder of full session images #4")
    public void t04_e2ePositiveGreenIdFullSessionTest() throws IOException, InterruptedException {
        Injection injection = new Injection(driver);
        injection.injectFolder("./ocr/sessions/greenId/4", null);

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 26);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "failure");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "mathilda.json");
        Assert.assertTrue(variables.isSuccess());

        //Front section
        Assert.assertEquals(variables.getCardType1(),"green");
        Assert.assertEquals(variables.getDateOfBirth(), "01.12.1975");
        Assert.assertEquals(variables.getDateOfIssue(), "05.03.2012");
        Assert.assertEquals(variables.getFirstNameHebrew(), "אלי");
        Assert.assertEquals(variables.getGender(), "זכר");
        Assert.assertEquals(variables.getIdNumber(), 304662422);
        Assert.assertEquals(variables.getLastNameHebrew(), "הופמן");
        Assert.assertEquals(variables.getPlaceOfBirth(), "ברית המועצות");

        Assert.assertTrue(variables.isImageIsColorful());
        Assert.assertTrue(variables.isIdChecksumValid());
        Assert.assertTrue(variables.isTemplateMatched());
        Assert.assertTrue(variables.isDocumentInFrame());
        Assert.assertTrue(variables.isStampDetected());
        Assert.assertTrue(variables.isFaceSizeValid());
        Assert.assertTrue(variables.isFacePositionValid());
        Assert.assertTrue(variables.isFaceRotationValid());
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

        Assert.assertEquals(OcrHandlers.getActionType(), "complete");
        Assert.assertEquals(OcrHandlers.getOrder(), 1);
        Assert.assertEquals(OcrHandlers.getTypeUnderStage(), "front");

        Assert.assertEquals(ILIDVariables.getDateOfBirth(), "01.12.1975");
        Assert.assertEquals(ILIDVariables.getDateOfIssue(), "05.03.2012");
        Assert.assertEquals(ILIDVariables.getFirstNameHebrew(), "אלי");
        Assert.assertEquals(ILIDVariables.getLastNameHebrew(), "הופמן");
        Assert.assertEquals(ILIDVariables.getGender(), "זכר");
        Assert.assertEquals(ILIDVariables.getIdNumber(), "304662422");
        Assert.assertEquals(ILIDVariables.getPlaceOfBirth(), "ברית המועצות");
        Assert.assertEquals(ILIDVariables.getCardType(), "green");
        Assert.assertNotNull(ILIDVariables.getFaceImage());
        Assert.assertNotNull(ILIDVariables.getInputImage());
        Assert.assertNotNull(ILIDVariables.getCroppedImage());
        Assert.assertTrue(ILIDVariables.isImageIsColorful());
        Assert.assertTrue(ILIDVariables.isIdChecksumValid());
        Assert.assertTrue(ILIDVariables.isTemplateMatch());
        Assert.assertTrue(ILIDVariables.isStampDetected());
        Assert.assertTrue(ILIDVariables.isFaceSizeValid());
        Assert.assertTrue(ILIDVariables.isFacePositionValid());
        Assert.assertTrue(ILIDVariables.isFaceRotationValid());
        Assert.assertTrue(ILIDVariables.isDocumentInFrame());
        Assert.assertTrue(ILIDVariables.isIssueDateValid());

        Assert.assertEquals(OcrHandlers.getStageStatus(), "success");
        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-ocr");
    }

}
