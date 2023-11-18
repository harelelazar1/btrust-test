package selenium.ngocr.test.greenid_old;

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
import utilities.MongoDB.Variables.CommonVariables;
import utilities.MongoDB.Variables.Ocr.ILIDVariables;
import utilities.MongoDB.Variables.Ocr.OcrHandlers;
import utilities.MongoDB.Variables.StartSessionVariables;

import java.io.IOException;

public class SanityTest extends BaseTest {

    MainPage mainPage;
    Variables variables = new Variables();
    MongoHandler mongoHandler = new MongoHandler();

    @BeforeClass
    public void resetVariables() {
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


    @Test(description = "green Id demo sanity test",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Run Demo End to End test of green Id")
    public void t01_e2eGreenIdDemoFlow() throws IOException, InterruptedException {
        SanityTest.chooseConfigFile();
        MainPage mainPage = new MainPage(driver);
        mainPage.chooseFromServicesList("Israel ID");
        Injection injection = new Injection(driver);

        injection.imageInjectionConfigure("./ocr/greenId_old/israel_id_green_old.jpg", null, 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
 //       Assert.assertEquals(resultsPage.verifyListsSizes(), 26);
        resultsPage.collectResults(variables);

        if (System.getProperty("OCR_NEW").equalsIgnoreCase("false")) {
            Assert.assertEquals(variables.getActionType(), "complete");
            Assert.assertEquals(variables.getStatus(), "success");
            Assert.assertNotNull(variables.getCaseId());
            Assert.assertEquals(variables.getConfigFileName(), "two_sides.json");
            Assert.assertTrue(variables.isSuccess());
        } else {
            Assert.assertEquals(variables.getActionType(), "complete");
            Assert.assertEquals(variables.getStatus(), "success");
//            Assert.assertNotNull(variables.getCaseId());
//            Assert.assertEquals(variables.getConfigFileName(), "mathilda.json");
//            Assert.assertTrue(variables.isSuccess());
        }

        //Front section
        Assert.assertEquals(variables.getCardType1(),"green_old");
        Assert.assertEquals(variables.getIdNumber(), 32846545);
        Assert.assertEquals(variables.getFirstNameHebrew(), "מנאר");
        Assert.assertEquals(variables.getLastNameHebrew(), "אבו זקיקה");
        Assert.assertEquals(variables.getDateOfBirth(), "12.02.1978");
        Assert.assertEquals(variables.getDateOfIssue(), "04.08.97");

        Assert.assertEquals(variables.getGender(), "נקבה");
        Assert.assertEquals(variables.getPlaceOfBirth(), "ישראל");

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

        Assert.assertEquals(ILIDVariables.getDateOfBirth(), "19.02.1994");
        Assert.assertEquals(ILIDVariables.getDateOfIssue(), "03.05.2010");
        Assert.assertEquals(ILIDVariables.getFirstNameHebrew(), "בר");
        Assert.assertEquals(ILIDVariables.getLastNameHebrew(), "זמסקי");
        Assert.assertEquals(ILIDVariables.getGender(), "נקבה");
        Assert.assertEquals(ILIDVariables.getIdNumber(), "312534753");
        Assert.assertEquals(ILIDVariables.getPlaceOfBirth(), "ישראל");
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
