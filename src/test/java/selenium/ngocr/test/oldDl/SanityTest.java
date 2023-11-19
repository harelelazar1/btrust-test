package selenium.ngocr.test.oldDl;

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
import utilities.MongoDB.Variables.Ocr.ILDLVariables;
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
            mainPage.chooseConfigFile("mathilda.json");
        } else {
            mainPage.chooseConfigFile(System.getProperty("COMPANY_ID"));
        }
    }

    @Test(description = "old dl demo sanity test",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Run Demo End to End test of old dl")
    public void t01_e2eOldDlDemoFlow() throws IOException, InterruptedException {
        selenium.ngocr.test.newDl.SanityTest.chooseConfigFile();
        mainPage.chooseFromServicesList("Israel Driving License");
        Injection injection = new Injection(driver);
        injection.imageInjectionConfigure("./ocr/dl/oldDLFront.jpg", "./ocr/dl/oldDLBack.jpg", 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
 //       Assert.assertEquals(resultsPage.verifyListsSizes(), 36);
        resultsPage.collectResults(variables);

        if (System.getProperty("OCR_NEW").equalsIgnoreCase("false")) {
            Assert.assertEquals(variables.getActionType(), "complete");
            Assert.assertEquals(variables.getStatus(), "success");
            Assert.assertNotNull(variables.getCaseId());
            Assert.assertEquals(variables.getConfigFileName(), "mathilda.json");
            Assert.assertTrue(variables.isSuccess());
        } else {
            Assert.assertEquals(variables.getActionType(), "complete");
            Assert.assertEquals(variables.getStatus(), "success");
//            Assert.assertNotNull(variables.getCaseId());
//            Assert.assertEquals(variables.getConfigFileName(), "mathilda.json");
//            Assert.assertTrue(variables.isSuccess());
        }


        //Front section
        Assert.assertEquals(variables.getCardType1(),"old_front");
        Assert.assertEquals(variables.getLastNameHebrew(), "גרוס פישמן");
        Assert.assertEquals(variables.getLastNameEnglish(), "FISHMAMGROSS");
        Assert.assertEquals(variables.getFirstNameHebrew(), "מאיה");
        Assert.assertEquals(variables.getFirstNameEnglish(), "MAYA");
        Assert.assertEquals(variables.getDateOfBirth(), "06.01.1978");
        Assert.assertEquals(variables.getDateOfIssue(), "14.12.2006");
        Assert.assertEquals(variables.getDateOfExpiry(), "06.01.2017");
        Assert.assertEquals(variables.getIdNumber(), 34471045);
        Assert.assertEquals(Integer.toString(variables.getLicenseNumber()), "6905739");
        Assert.assertEquals(variables.getAddress(), "אלוף דוד 58 רמת גן");
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
        Assert.assertEquals(variables.getCardType1(),"old_front");
        Assert.assertEquals(variables.getIdNumber(), 34471045);
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

        Assert.assertEquals(ILDLVariables.getLastNameHebrew(), "גרוס פישמן");
        Assert.assertEquals(ILDLVariables.getLastNameEnglish(), "FISHMAMGROSS");
        Assert.assertEquals(ILDLVariables.getFirstNameHebrew(), "מאיה");
        Assert.assertEquals(ILDLVariables.getFirstNameEnglish(), "MAYA");
        Assert.assertEquals(ILDLVariables.getDateOfBirth(), "06.01.1978");
        Assert.assertEquals(ILDLVariables.getDateOfIssue(), "14.12.2006");
        Assert.assertEquals(ILDLVariables.getDateOfExpiry(), "06.01.2017");
        Assert.assertEquals(ILDLVariables.getLicenseNumber(), "6905739");
        Assert.assertEquals(ILDLVariables.getIdNumber(), "034471045");
        Assert.assertEquals(ILDLVariables.getAddress(), "אלוף דוד 58 רמת גן");
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

        Assert.assertEquals(ILDLVariables.getIdNumber2(), "034471045");
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
