package selenium.ocr.test.newServer.newDl;

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
import utilities.MongoDB.Variables.Ocr.ILIDVariables;
import utilities.MongoDB.Variables.Ocr.OcrHandlers;
import utilities.MongoDB.Variables.StartSessionVariables;
import utilities.TestUtils;

import java.io.IOException;

public class Functionality extends BaseTest {

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

    @Test(description = "new dl front side timeout",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("New dl demo e2e process of front side timeout")
    public void t01_e2eNewDlDemoFrontSideTimeout() throws InterruptedException {
        mainPage.chooseFromServicesList("Israel Driving License");
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
        Assert.assertEquals(CommonVariables.getStatus(), "timeout");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Session reached maximum allowed flow timeout");
        Assert.assertTrue(CommonVariables.isSuccess());
        Assert.assertNotNull(CommonVariables.getVideo());
        Assert.assertEquals(CommonVariables.getWorkingMode(), "multi");

        Assert.assertEquals(ILIDVariables.getActionTypeTimeout(), "stage");
        Assert.assertEquals(ILIDVariables.getOrderTimeout(), 1);
        Assert.assertEquals(ILIDVariables.getTypeUnderStageTimeout(), "front");
        Assert.assertEquals(ILIDVariables.getStageStatusTimeout(), "timeout");
        Assert.assertNotNull(ILIDVariables.getLastReceivedImage());

        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-ocr");
    }

    @Test(description = "new dl back side timeout",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("New dl demo e2e process of back side timeout")
    public void t02_e2eNewDlDemoBackSideTimeout() throws InterruptedException, IOException {
        mainPage.chooseFromServicesList("Israel Driving License");
        Injection injection = new Injection(driver);
        injection.imageInjectionConfigure("./ocr/dl/newDLFront.jpg", null, 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(60), 28);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "timeout");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "mathilda.json");
        Assert.assertTrue(variables.isSuccess());

        //Front section
        Assert.assertEquals(variables.getCardType1(),"new_front");
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
        Assert.assertTrue(variables.isFaceSizeValid());
        Assert.assertTrue(variables.isFacePositionValid());
        Assert.assertFalse(variables.isFaceRotationValid());

        Assert.assertEquals(variables.getFaceImage(), "face_image");
        Assert.assertEquals(variables.getInputImage1(), "input_image");
        Assert.assertEquals(variables.getCroppedImage1(), "cropped_image");

        //Back section
        Assert.assertEquals(variables.getLastReceivedImage(), "last_received_image");

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
        Assert.assertEquals(CommonVariables.getStatus(), "timeout");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Session reached maximum allowed flow timeout");
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

        Assert.assertEquals(ILIDVariables.getActionTypeTimeout(), "complete");
        Assert.assertEquals(ILIDVariables.getOrderTimeout(), 2);
        Assert.assertEquals(ILIDVariables.getTypeUnderStageTimeout(), "back");
        Assert.assertEquals(ILIDVariables.getStageStatusTimeout(), "timeout");
        Assert.assertNotNull(ILIDVariables.getLastReceivedImage());

        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-ocr");
    }

    @Test(description = "new IL-DL e2e run without cPalette inspection",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("new IL-DL e2e run without cPalette inspection using the config file - qa_config.json")
    public void t03_e2eWithoutCpaletteInspection() throws IOException, InterruptedException {
        mainPage.chooseConfigFile(TestUtils.getDefaultMathilda());
        mainPage.chooseFromServicesList("Israel Driving License");
        Injection injection = new Injection(driver);
        injection.imageInjectionConfigure("./ocr/dl/newDLFront.jpg", "./ocr/dl/newDLBack.jpg", 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 36);
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
}
