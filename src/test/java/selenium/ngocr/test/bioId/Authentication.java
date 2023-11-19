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
import utilities.MongoDB.Variables.Ocr.ILIDVariables;

import java.io.IOException;

public class Authentication extends BaseTest {

    MainPage mainPage;
    Variables variables;
    MongoHandler mongoHandler;

    @BeforeClass
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

        Authentication.chooseConfigFile();
        mainPage.chooseFromServicesList("Israel ID");

    }

    public static void chooseConfigFile() {
        MainPage mainPage = new MainPage(driver);
        if (System.getProperty("OCR_NEW").equalsIgnoreCase("false")) {
            mainPage.chooseConfigFile("two_sides.json");
        } else {
            mainPage.chooseConfigFile(System.getProperty("COMPANY_ID"));
        }
    }


    @Test(description = "bio id with chip covered",retryAnalyzer = utilities.RetryAnalyzer.class,groups = {"smoke"})
    @Description("Authentication test of bio Id that have chip covered")
    public void t01_bioIdChipAuth() throws IOException, InterruptedException {
        Injection injection = new Injection(driver);
        injection.imageInjectionConfigure("./ocr/bioID/nitzan/auth/bioIdChipCovered.jpg", "./ocr/bioID/nitzan/back/NitzanBioBack1.jpg", 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "success");
//        Assert.assertNotNull(variables.getCaseId());
        Assert.assertFalse(variables.isChipAuth());
        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertFalse(ILIDVariables.isChipAuth());
    }


    @Test(description = "bio Id demo - scan card with face size invalid",retryAnalyzer = utilities.RetryAnalyzer.class,groups = {"smoke"})
    @Description("bio Id demo - scan card with face size invalid")
    public void t02_bioId_cardWithFaceSizeInvalid() throws IOException, InterruptedException {
        Injection injection = new Injection(driver);
        injection.imageInjectionConfigure("./ocr/bioID/nitzan/auth/BioIdWithFaceSizeInvalid.jpg", "./ocr/bioID/nitzan/back/NitzanBioBack1.jpg", 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
//        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getStatus(), "success");

        Assert.assertFalse(variables.isFaceSizeValid());
        Assert.assertTrue(variables.isFacePositionValid());
        Assert.assertTrue(variables.isFaceRotationValid());

        Assert.assertTrue(resultsPage.verifyImagesAreReal());

        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertFalse(ILIDVariables.isFaceSizeValid());
        Assert.assertTrue(ILIDVariables.isFacePositionValid());
        Assert.assertTrue(ILIDVariables.isFaceRotationValid());
    }

    @Test(description = "bio Id demo - id number no matches front",retryAnalyzer = utilities.RetryAnalyzer.class,groups = {"smoke"})
    @Description("Bio Id demo - front and back id numbers are not matches each other")
    public void t03_bioId_idNumberNoMatchesFront() throws IOException, InterruptedException {
        Injection injection = new Injection(driver);
        injection.imageInjectionConfigure("./ocr/bioID/nitzan/front/NitzanBioFront1.jpg", "./ocr/bioID/22-01-2021_10-20-32.413_did_not_reach_lib.jpg", 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "success");
//        Assert.assertNotNull(variables.getCaseId());

        Assert.assertFalse(variables.isIdNumberMatchesFront());
        Assert.assertFalse(variables.isIdNumberMatchesFront());

        Assert.assertTrue(resultsPage.verifyImagesAreReal());

        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertFalse(ILIDVariables.isIdNumberMatchesFront());
    }
}
