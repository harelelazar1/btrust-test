package selenium.ocr.test.newServer.mathilda.environmentVariables;

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
import utilities.MongoDB.Variables.Ocr.ILIDVariables;

import java.io.IOException;

public class NoRequiredFieldsILID extends BaseTest {

    Variables variables;
    MongoHandler mongoHandler;
    MainPage mainPage;
    Injection injection;

    @BeforeMethod
    public void startSession() {
        variables = new Variables();
        mongoHandler = new MongoHandler();
        mainPage = new MainPage(driver);
        injection = new Injection(driver);
        driver.get(OCR_DEMO);
        mainPage.chooseFromServicesList("Israel ID");
    }

    @Test(description = "bio Id - scan card with date of birth hidden")
    @Description("bio Id - scan card with date of birth hidden")
    public void t01_bioId_dateOfBirthHidden() throws IOException, InterruptedException {
        injection.imageInjectionConfigure("./ocr/bioID/nitzan/front/NitzanBioFront - dateOfBirthHidden.jpg", "./ocr/bioID/nitzan/back/NitzanBioBack1.jpg", 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 40);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "failure");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "mathilda.json");
        Assert.assertTrue(variables.isSuccess());

        Assert.assertNull(resultsPage.valueToCheck(variables, "date_of_birth"));
        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck","false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertNull(ILIDVariables.getDateOfBirth());
    }

    @Test(description = "bio Id - scan card with date of expiry hidden")
    @Description("bio Id - scan card with date of expiry hidden")
    public void t02_bioId_dateOfExpiryHidden() throws IOException, InterruptedException {
        injection.imageInjectionConfigure("./ocr/bioID/nitzan/front/NitzanBioFront - dateOfExpiredHidden.jpg", "./ocr/bioID/nitzan/back/NitzanBioBack1.jpg", 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 38);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "failure");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "mathilda.json");
        Assert.assertTrue(variables.isSuccess());

        Assert.assertNull(resultsPage.valueToCheck(variables, "date_of_expiry"));
        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck","false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertNull(ILIDVariables.getDateOfExpiry());
    }

    @Test(description = "bio Id - scan card with date of issue hidden")
    @Description("bio Id - scan card with date of issue hidden")
    public void t03_bioId_dateOfIssueHidden() throws IOException, InterruptedException {
        injection.imageInjectionConfigure("./ocr/bioID/nitzan/front/NitzanBioFront - dateOfIssueHidden.jpg", "./ocr/bioID/nitzan/back/NitzanBioBack1.jpg", 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 38);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "failure");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "mathilda.json");
        Assert.assertTrue(variables.isSuccess());

        Assert.assertNotNull(variables.getDateOfIssue());
        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck","false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertNull(ILIDVariables.getDateOfIssue());
    }

    @Test(description = "blue Id - scan card with date of birth hidden")
    @Description("blue Id - scan card with date of birth hidden")
    public void t04_blueId_dateOfBirthHidden() throws IOException, InterruptedException {
        injection.imageInjectionConfigure("./ocr/blueID/errorhandling/blueIDWithDateOfBirthHidden.jpg", null, 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 29);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "failure");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "mathilda.json");
        Assert.assertTrue(variables.isSuccess());

        Assert.assertNull(resultsPage.valueToCheck(variables, "date_of_birth"));
        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck","false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertNull(ILIDVariables.getDateOfBirth());
    }

    @Test(description = "blue id - scan card with date of expiry hidden")
    @Description("blue id - scan card with date of expiry hidden")
    public void t05_blueId_dateOfExpiryHidden() throws IOException, InterruptedException {
        injection.imageInjectionConfigure("./ocr/blueID/errorhandling/blueIDWithDateOfExpiryHidden.jpg", null, 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 27);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "failure");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "mathilda.json");
        Assert.assertTrue(variables.isSuccess());

        Assert.assertNull(resultsPage.valueToCheck(variables, "date_of_expiry"));
        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck","false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertNull(ILIDVariables.getDateOfExpiry());
    }

    @Test(description = "blue id - scan card with date of issue hidden")
    @Description("blue id - scan card with date of issue hidden")
    public void t06_blueId_dateOfIssueHidden() throws IOException, InterruptedException {
        injection.imageInjectionConfigure("./ocr/blueID/errorhandling/blueIDWithDateOfIssueHidden.jpg", null, 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 27);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "failure");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "mathilda.json");
        Assert.assertTrue(variables.isSuccess());

        Assert.assertNotNull(variables.getDateOfIssue());
        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck","false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertNull(ILIDVariables.getDateOfIssue());
    }

    @Test(description = "green Id - scan card with date of birth hidden")
    @Description("green Id - scan card with date of birth hidden")
    public void t07_greenId_dateOfBirthHidden() throws IOException, InterruptedException {
        injection.imageInjectionConfigure("./ocr/greenID/errorhandling/greenIdWithDateOfBirthHidden.jpg", null, 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 25);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "failure");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "mathilda.json");
        Assert.assertTrue(variables.isSuccess());

        Assert.assertNull(resultsPage.valueToCheck(variables, "date_of_birth"));
        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck","false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertNull(ILIDVariables.getDateOfBirth());
    }

    @Test(description = "green Id - scan card with date of issue hidden")
    @Description("green Id - scan card with date of issue hidden")
    public void t08_greenId_dateOfIssueHidden() throws IOException, InterruptedException {
        injection.imageInjectionConfigure("./ocr/greenID/errorhandling/greenIdWithDateOfIssueHidden.jpg", null, 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 24);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "failure");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "mathilda.json");
        Assert.assertTrue(variables.isSuccess());

        Assert.assertNotNull(variables.getDateOfIssue());
        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck","false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertNull(ILIDVariables.getDateOfIssue());
    }
}