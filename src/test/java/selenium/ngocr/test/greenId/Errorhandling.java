package selenium.ngocr.test.greenId;

import api.Variables;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import selenium.BaseTest;
import selenium.Injection;
import selenium.ngocr.test.bioId.SanityTest;
import selenium.ocr.pageObject.newService.MainPage;
import selenium.ocr.pageObject.newService.ResultsPage;
import utilities.MongoDB.MongoHandler;
import utilities.MongoDB.Variables.CommonVariables;
import utilities.MongoDB.Variables.Ocr.ILIDVariables;
import utilities.MongoDB.Variables.StartSessionVariables;

import java.io.IOException;

public class Errorhandling extends BaseTest {

    MainPage mainPage;
    Variables variables;
    MongoHandler mongoHandler;

    @BeforeMethod
    public void resetVariables() {
        mainPage = new MainPage(driver);
        variables = new Variables();
        mongoHandler = new MongoHandler();
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


    @Test(description = "green Id demo - scan card with first name hidden",retryAnalyzer = utilities.RetryAnalyzer.class,groups = {"smoke"})
    @Description("green Id demo - scan card with first name hidden")
    public void t01_greenId_firstNameHidden() throws IOException, InterruptedException {
        Errorhandling.chooseConfigFile();
        mainPage.chooseFromServicesList("Israel ID");
        Injection injection = new Injection(driver);
        injection.imageInjectionConfigure("./ocr/greenID/errorhandling/FirstNameHidden.jpg", null, 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getStatus(), "success");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertNull(resultsPage.valueToCheck(variables, "first_name_hebrew"));
        Assert.assertNull(ILIDVariables.getFirstNameHebrew());
    }

    @Test(description = "green Id demo - scan card with last name hidden",retryAnalyzer = utilities.RetryAnalyzer.class,groups = {"smoke"})
    @Description("green Id demo - scan card with last name hidden")
    public void t02_greenId_lastNameHidden() throws IOException, InterruptedException {
        Errorhandling.chooseConfigFile();
        mainPage.chooseFromServicesList("Israel ID");
        Injection injection = new Injection(driver);
        injection.imageInjectionConfigure("./ocr/greenID/errorhandling/greenIdWithLastNameHidden.jpg", null, 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getStatus(), "success");
        Assert.assertNotNull(variables.getCaseId());

        Assert.assertNull(resultsPage.valueToCheck(variables, "last_name_hebrew"));
        Assert.assertNull(ILIDVariables.getLastNameHebrew());
    }

    @Test(description = "green Id demo - scan card with date of birth hidden",retryAnalyzer = utilities.RetryAnalyzer.class,groups = {"smoke"})
    @Description("green Id demo - scan card with date of birth hidden")
    public void t03_greenId_dateOfBirthHidden() throws IOException, InterruptedException {
        Errorhandling.chooseConfigFile();
        mainPage.chooseFromServicesList("Israel ID");
        Injection injection = new Injection(driver);
        injection.imageInjectionConfigure("./ocr/greenID/errorhandling/greenIdWithDateOfBirthHidden.jpg", null, 1, false, null);


        ResultsPage resultsPage = new ResultsPage(driver);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getStatus(), "success");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertNull(resultsPage.valueToCheck(variables, "date_of_birth"));
        Assert.assertNull(ILIDVariables.getDateOfBirth());
    }

    @Test(description = "green Id demo - scan card with date of issue hidden",retryAnalyzer = utilities.RetryAnalyzer.class,groups = {"smoke"})
    @Description("green Id demo - scan card with date of issue hidden")
    public void t04_greenId_dateOfIssueHidden() throws IOException, InterruptedException {
        Errorhandling.chooseConfigFile();
        mainPage.chooseFromServicesList("Israel ID");
        Injection injection = new Injection(driver);
        injection.imageInjectionConfigure("./ocr/greenID/errorhandling/greenIdWithDateOfIssueHidden.jpg", null, 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getStatus(), "success");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertNull(variables.getDateOfIssue());
        Assert.assertNull(ILIDVariables.getDateOfIssue());
    }

    @Test(description = "green Id demo - scan card with id number hidden",retryAnalyzer = utilities.RetryAnalyzer.class,groups = {"smoke"})
    @Description("green Id demo - scan card with id number hidden")
    public void t05_greenId_idNumberHidden() throws IOException, InterruptedException {
        Errorhandling.chooseConfigFile();
        mainPage.chooseFromServicesList("Israel ID");
        Injection injection = new Injection(driver);
        injection.imageInjectionConfigure("./ocr/greenID/errorhandling/greenIdWithIdNumberHidden.jpg", null, 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getErrorCode(), 1112);
        Assert.assertEquals(variables.getErrorName(), "ServerError");
        Assert.assertNotNull(variables.getErrorMessage());
    }

    @Test(description = "no card detected error message in the demo",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("green Id demo - no card detected error message")
    public void t06_greenId_cardNotInFrame() throws IOException, InterruptedException {
        Errorhandling.chooseConfigFile();
        mainPage.chooseFromServicesList("Israel ID");
        Injection injection = new Injection(driver);
        Assert.assertTrue(injection.imageInjectionConfigureNew("./ocr/blueID/blueIDNotInFrame.jpg", null, 1, true, "לא נמצא כרטיס"));

        ResultsPage resultsPage = new ResultsPage(driver);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getErrorCode(), 1112);
        Assert.assertEquals(variables.getErrorName(), "ServerError");
        Assert.assertNotNull(variables.getErrorMessage());

    }

}
