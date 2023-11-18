package selenium.ngocr.test.bioId;

import api.Variables;
import io.qameta.allure.Description;
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


    @Test(description = "bio Id demo - scan card with first name hidden",retryAnalyzer = utilities.RetryAnalyzer.class,groups = {"smoke"})
    @Description("bio Id demo - scan card with first name hidden")
    public void t01_bioId_firstNameHidden() throws IOException, InterruptedException {
        Errorhandling.chooseConfigFile();
        mainPage.chooseFromServicesList("Israel ID");

        Injection injection = new Injection(driver);
        injection.imageInjectionConfigure("./ocr/bioID/nitzan/front/NitzanBioFront - firstNameHidden.jpg", "./ocr/bioID/nitzan/back/NitzanBioBack1.jpg", 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
        resultsPage.collectResults(variables);


        Assert.assertEquals(variables.getStatus(), "success");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertNull(resultsPage.valueToCheck(variables, "first_name_hebrew"));
        Assert.assertNull(ILIDVariables.getFirstNameHebrew());
    }


    @Test(description = "bio Id demo - scan card with last name hidden",retryAnalyzer = utilities.RetryAnalyzer.class,groups = {"smoke"})
    @Description("bio Id demo - scan card with last name hidden")
    public void t02_bioId_lastNameHidden() throws IOException, InterruptedException {
        Errorhandling.chooseConfigFile();
        mainPage.chooseFromServicesList("Israel ID");
        Injection injection = new Injection(driver);
        injection.imageInjectionConfigure("./ocr/bioID/nitzan/front/NitzanBioFront - lastNameHidden.jpg", "./ocr/bioID/nitzan/back/NitzanBioBack1.jpg", 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getStatus(), "success");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertNull(resultsPage.valueToCheck(variables, "last_name_hebrew"));
        Assert.assertNull(ILIDVariables.getLastNameHebrew());
    }

    @Test(description = "wrong side error message in bio Id demo",retryAnalyzer = utilities.RetryAnalyzer.class,groups = {"smoke"})
    @Description("bio Id demo - wrong side error message")
    public void t03_bioId_wrongSideMessage() throws IOException, InterruptedException {
        Errorhandling.chooseConfigFile();
        mainPage.chooseFromServicesList("Israel ID");
        Injection injection = new Injection(driver);
        Assert.assertTrue(injection.imageInjectionConfigure("./ocr/bioID/nitzan/back/NitzanBioBack1.jpg", null, 1, true, "צד לא נכון"));

        ResultsPage resultsPage = new ResultsPage(driver);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getErrorCode(), 1112);
        Assert.assertEquals(variables.getErrorName(), "ServerError");
        Assert.assertNotNull(variables.getErrorMessage());
    }

    @Test(description = "bio Id demo - card not in frame",retryAnalyzer = utilities.RetryAnalyzer.class,groups = {"smoke"})
    @Description("bio Id demo - card not in frame")
    public void t04_bioId_cardNotInFrame() throws IOException, InterruptedException {
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

    @Test(description = "bio Id demo - scan card with date of birth hidden",retryAnalyzer = utilities.RetryAnalyzer.class,groups = {"smoke"})
    @Description("bio Id demo - scan card with date of birth hidden")
    public void t05_bioId_dateOfBirthHidden() throws InterruptedException, IOException {
        Errorhandling.chooseConfigFile();
        mainPage.chooseFromServicesList("Israel ID");
        Injection injection = new Injection(driver);
        injection.imageInjectionConfigure("./ocr/bioID/nitzan/front/NitzanBioFront - dateOfBirthHidden.jpg", "./ocr/bioID/nitzan/back/NitzanBioBack1.jpg", 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getStatus(), "success");
        Assert.assertNotNull(variables.getCaseId());

        Assert.assertNull(resultsPage.valueToCheck(variables, "date_of_birth"));
        Assert.assertNull(ILIDVariables.getDateOfBirth());
    }

    @Test(description = "bio Id demo - scan card with date of expiry hidden",retryAnalyzer = utilities.RetryAnalyzer.class,groups = {"smoke"})
    @Description("bio Id demo - scan card with date of expiry hidden")
    public void t06_bioId_dateOfExpiryHidden() throws InterruptedException, IOException {
        Errorhandling.chooseConfigFile();
        mainPage.chooseFromServicesList("Israel ID");
        Injection injection = new Injection(driver);
        injection.imageInjectionConfigure("./ocr/bioID/nitzan/front/NitzanBioFront - dateOfExpiredHidden.jpg", "./ocr/bioID/nitzan/back/NitzanBioBack1.jpg", 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getStatus(), "success");
        Assert.assertNotNull(variables.getCaseId());

        Assert.assertNull(resultsPage.valueToCheck(variables, "date_of_expiry"));
        Assert.assertNull(ILIDVariables.getDateOfExpiry());
    }

    @Test(description = "bio Id demo - scan card with date of issue hidden",retryAnalyzer = utilities.RetryAnalyzer.class,groups = {"smoke"})
    @Description("bio Id demo - scan card with date of issue hidden")
    public void t07_bioId_dateOfIssueHidden() throws InterruptedException, IOException {
        Errorhandling.chooseConfigFile();
        mainPage.chooseFromServicesList("Israel ID");
        Injection injection = new Injection(driver);
        injection.imageInjectionConfigure("./ocr/bioID/nitzan/front/NitzanBioFront - dateOfIssueHidden.jpg", "./ocr/bioID/nitzan/back/NitzanBioBack1.jpg", 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getStatus(), "success");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertNull(variables.getDateOfIssue());
        Assert.assertNull(ILIDVariables.getDateOfIssue());
    }

    @Test(description = "bio Id demo - scan card with id number hidden",retryAnalyzer = utilities.RetryAnalyzer.class,groups = {"smoke"})
    @Description("bio Id demo - scan card with id number hidden")
    public void t08_bioId_idNumberHidden() throws IOException, InterruptedException {
        Errorhandling.chooseConfigFile();
        mainPage.chooseFromServicesList("Israel ID");
        Injection injection = new Injection(driver);
        injection.imageInjectionConfigure("./ocr/bioID/nitzan/front/NitzanBioFront - idNumberHidden.jpg", "./ocr/bioID/nitzan/back/NitzanBioBack1.jpg", 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getErrorCode(), 1112);
        Assert.assertEquals(variables.getErrorName(), "ServerError");
        Assert.assertNotNull(variables.getErrorMessage());
    }


}
