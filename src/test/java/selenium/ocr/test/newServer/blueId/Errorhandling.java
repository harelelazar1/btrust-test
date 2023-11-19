package selenium.ocr.test.newServer.blueId;

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
import utilities.MongoDB.Variables.Ocr.ILIDVariables;
import utilities.MongoDB.Variables.StartSessionVariables;

import java.io.IOException;

public class Errorhandling extends BaseTest {

    Variables variables;
    MongoHandler mongoHandler;
    MainPage mainPage;

    @BeforeMethod
    public void startSession() throws InterruptedException {
        Thread.sleep(5000);
        variables = new Variables();
        mongoHandler = new MongoHandler();
        mainPage = new MainPage(driver);
        driver.get(OCR_DEMO);
        mainPage.chooseFromServicesList("Israel ID");
    }

    @Test(description = "blue Id demo - scan card with first name hidden")
    @Description("blue Id demo - scan card with first name hidden")
    public void t01_blueId_firstNameHidden() throws IOException, InterruptedException {
        Injection injection = new Injection(driver);
        injection.imageInjectionConfigure("./ocr/blueID/errorhandling/blueIDWithFirstNameHidden.jpg", null, 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 29);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getStatus(), "success");
        Assert.assertNotNull(variables.getCaseId());

        Assert.assertNull(resultsPage.valueToCheck(variables, "first_name_hebrew"));
        if (System.getProperty("mongoCheck","false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertNull(ILIDVariables.getFirstNameHebrew());

    }

    @Test(description = "blue Id demo - scan card with last name hidden")
    @Description("blue Id demo - scan card with last name hidden")
    public void t02_blueId_lastNameHidden() throws IOException, InterruptedException {
        Injection injection = new Injection(driver);
        injection.imageInjectionConfigure("./ocr/blueID/errorhandling/blueIDWithLastNameHidden.jpg", null, 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 29);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getStatus(), "success");
        Assert.assertNotNull(variables.getCaseId());

        Assert.assertNull(resultsPage.valueToCheck(variables, "last_name_hebrew"));
        if (System.getProperty("mongoCheck","false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertNull(ILIDVariables.getLastNameHebrew());
    }

    @Test(description = "blue Id demo - scan card with date of birth hidden")
    @Description("blue Id demo - scan card with date of birth hidden")
    public void t03_blueId_dateOfBirthHidden() throws IOException, InterruptedException {
        Injection injection = new Injection(driver);
        injection.imageInjectionConfigure("./ocr/blueID/errorhandling/blueIDWithDateOfBirthHidden.jpg", null, 1, false, null);
        Thread.sleep(70000);

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 6);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getStatus(), "timeout");
        Assert.assertNotNull(variables.getCaseId());

        Assert.assertNull(resultsPage.valueToCheck(variables, "date_of_birth"));
        if (System.getProperty("mongoCheck","false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertNull(ILIDVariables.getDateOfBirth());
    }

    @Test(description = "blue Id demo - scan card with date of issue hidden")
    @Description("blue Id demo - scan card with date of issue hidden")
    public void t04_blueId_dateOfIssueHidden() throws IOException, InterruptedException {
        Injection injection = new Injection(driver);
        injection.imageInjectionConfigure("./ocr/blueID/errorhandling/blueIDWithDateOfIssueHidden.jpg", null, 1, false, null);
        Thread.sleep(70000);

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 6);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getStatus(), "timeout");
        Assert.assertNotNull(variables.getCaseId());

        Assert.assertNull(variables.getDateOfIssue());
        if (System.getProperty("mongoCheck","false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertNull(ILIDVariables.getDateOfIssue());
    }

    @Test(description = "blue Id demo - scan card with date of expiry hidden")
    @Description("blue Id demo - scan card with date of expiry hidden")
    public void t05_blueId_dateOfExpiryHidden() throws IOException, InterruptedException {
        Injection injection = new Injection(driver);
        injection.imageInjectionConfigure("./ocr/blueID/errorhandling/blueIDWithDateOfExpiryHidden.jpg", null, 1, false, null);
        Thread.sleep(70000);

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 6);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getStatus(), "timeout");
        Assert.assertNotNull(variables.getCaseId());

        Assert.assertNull(resultsPage.valueToCheck(variables, "date_of_expiry"));
        if (System.getProperty("mongoCheck","false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertNull(ILIDVariables.getDateOfExpiry());
    }

    @Test(description = "blue Id demo - scan card with id number hidden")
    @Description("blue Id demo - scan card with id number hidden")
    public void t06_blueId_idNumberHidden() throws IOException, InterruptedException {
        Injection injection = new Injection(driver);
        injection.imageInjectionConfigure("./ocr/blueID/errorhandling/blueIDWithIdNumberHidden.jpg", null, 1, false, null);
        Thread.sleep(70000);

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 6);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getStatus(), "timeout");
        Assert.assertNotNull(variables.getCaseId());

        Assert.assertNull(resultsPage.valueToCheck(variables, "id_number"));
        if (System.getProperty("mongoCheck","false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertNull(ILIDVariables.getIdNumber());
    }

    @Test(description = "no card detected error message in the demo")
    @Description("blue Id demo - no card detected error message")
    public void t07_blueId_cardNotInFrame() throws IOException, InterruptedException {
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
}
