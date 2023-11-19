package selenium.ngocr.test.oldDl;

import api.Variables;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import selenium.BaseTest;
import selenium.Injection;
import selenium.ocr.pageObject.newService.MainPage;
import selenium.ocr.pageObject.newService.ResultsPage;
import utilities.MongoDB.MongoHandler;
import utilities.MongoDB.Variables.CommonVariables;
import utilities.MongoDB.Variables.Ocr.ILDLVariables;
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



    @Test(description = "old dl demo - scan card with first name english hidden")
    @Description("old dl demo - scan card with first name english hidden")
    public void t01_OldDl_firstNameEnglishHidden() throws IOException, InterruptedException {
        Errorhandling.chooseConfigFile();
        mainPage.chooseFromServicesList("Israel Driving License");
        Injection injection = new Injection(driver);
        injection.imageInjectionConfigure("./ocr/dl/errorhandling/oldDl/oldDLFront - firstNameEnglishHidden.jpg", "./ocr/dl/newDLBack.jpg", 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getStatus(), "success");
        Assert.assertNotNull(variables.getCaseId());

        Assert.assertNotEquals(resultsPage.valueToCheck(variables, "first_name_english"),"MAYA");
    }

    @Test(description = "old dl demo - scan card with first name hebrew hidden")
    @Description("old dl demo - scan card with first name hebrew hidden")
    public void t02_OldDl_firstNameHebrewHidden() throws IOException, InterruptedException {
        Errorhandling.chooseConfigFile();
        mainPage.chooseFromServicesList("Israel Driving License");
        Injection injection = new Injection(driver);
        injection.imageInjectionConfigure("./ocr/dl/errorhandling/oldDl/oldDLFront - firstNameHebrewHidden.jpg", "./ocr/dl/newDLBack.jpg", 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getStatus(), "success");
        Assert.assertNotNull(variables.getCaseId());

        Assert.assertNull(resultsPage.valueToCheck(variables, "first_name_hebrew"));
        Assert.assertNull(ILDLVariables.getFirstNameHebrew());
    }

    @Test(description = "no card detected error message in the demo")
    @Description("old dl demo - no card detected error message")
    public void t03_OldDl_noCardDetectedMessage() throws IOException, InterruptedException {
        Errorhandling.chooseConfigFile();
        mainPage.chooseFromServicesList("Israel Driving License");
        Injection injection = new Injection(driver);
        Assert.assertTrue(injection.imageInjectionConfigure("./ocr/blueID/blueIDNotInFrame.jpg", null, 1, true, "לא נמצא כרטיס"));

        ResultsPage resultsPage = new ResultsPage(driver);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getErrorCode(), 1112);
        Assert.assertEquals(variables.getErrorName(), "ServerError");
        Assert.assertNotNull(variables.getErrorMessage());
    }


    @Test(description = "wrong side error message in the demo")
    @Description("old dl demo - wrong side error message")
    public void t04_OldDl_wrongSideMessage() throws IOException, InterruptedException {
        Errorhandling.chooseConfigFile();
        mainPage.chooseFromServicesList("Israel Driving License");
        Injection injection = new Injection(driver);
        Assert.assertTrue(injection.imageInjectionConfigure("./ocr/dl/oldDLBack.jpg", null, 1, true, "צד לא נכון"));

        ResultsPage resultsPage = new ResultsPage(driver);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getErrorCode(), 1112);
        Assert.assertEquals(variables.getErrorName(), "ServerError");
        Assert.assertNotNull(variables.getErrorMessage());
    }

    @Test(description = "old dl demo - scan card with date of expiry hidden")
    @Description("old dl demo - scan card with date of expiry hidden")
    public void t05_OldDl_dateOfExpiryHidden() throws IOException, InterruptedException {
        Errorhandling.chooseConfigFile();
        mainPage.chooseFromServicesList("Israel Driving License");
        Injection injection = new Injection(driver);
        injection.imageInjectionConfigure("./ocr/dl/errorhandling/oldDl/oldDLFront - dateOfExpiryHidden.jpg", "./ocr/dl/newDLBack.jpg", 1, false, null);


        ResultsPage resultsPage = new ResultsPage(driver);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getStatus(), "success");
        Assert.assertNotNull(variables.getCaseId());

        Assert.assertNull(resultsPage.valueToCheck(variables, "date_of_expiry"));
        Assert.assertNull(ILDLVariables.getDateOfExpiry());
    }

    @Test(description = "old dl demo - scan card with date of issue hidden")
    @Description("old dl demo - scan card with date of issue hidden")
    public void t06_OldDl_dateOfIssueHidden() throws IOException, InterruptedException {
        Errorhandling.chooseConfigFile();
        mainPage.chooseFromServicesList("Israel Driving License");
        Injection injection = new Injection(driver);
        injection.imageInjectionConfigure("./ocr/dl/errorhandling/oldDl/oldDLFront - dateOfIssueHidden.jpg", "./ocr/dl/newDLBack.jpg", 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getStatus(), "success");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertNull(variables.getDateOfIssue());
        Assert.assertNull(ILDLVariables.getDateOfIssue());
    }

    @Test(description = "old dl demo - scan card with id number hidden")
    @Description("old dl demo - scan card with id number hidden")
    public void t07_OldDl_idNumberHidden() throws IOException, InterruptedException {
        Errorhandling.chooseConfigFile();
        mainPage.chooseFromServicesList("Israel Driving License");
        Injection injection = new Injection(driver);
        injection.imageInjectionConfigure("./ocr/dl/errorhandling/oldDl/oldDLFront - idNumberHidden.jpg", "./ocr/dl/newDLBack.jpg", 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getErrorCode(), 1112);
        Assert.assertEquals(variables.getErrorName(), "ServerError");
        Assert.assertNotNull(variables.getErrorMessage());
    }

    @Test(description = "old dl demo - scan card with license number hidden")
    @Description("old dl demo - scan card with license number hidden")
    public void t08_OldDl_licenseNumberHidden() throws IOException, InterruptedException {
        Errorhandling.chooseConfigFile();
        mainPage.chooseFromServicesList("Israel Driving License");
        Injection injection = new Injection(driver);
        injection.imageInjectionConfigure("./ocr/dl/errorhandling/oldDl/oldDLFront - licenseNumberHidden.jpg", "./ocr/dl/newDLBack.jpg", 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getStatus(), "success");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertNull(resultsPage.valueToCheck(variables, "license_number"));
        Assert.assertNull(ILDLVariables.getLicenseNumber());
    }


}
