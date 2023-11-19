package selenium.ngocr.test.greenId;

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


    @Test(description = "green id stamp detected")
    @Description("Green id stamp detected auth test")
    public void t01_greenIdStampDetected() throws IOException, InterruptedException {
        Injection injection = new Injection(driver);
        injection.imageInjectionConfigure("./ocr/greenID/auth/greenIdStampCovered.jpg", null, 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "success");
 //       Assert.assertNotNull(variables.getCaseId());

        Assert.assertFalse(variables.isStampDetected());

        Assert.assertTrue(resultsPage.verifyImagesAreReal());

        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck","false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertFalse(ILIDVariables.isStampDetected());
    }

    @Test(description = "green id with face size invalid")
    @Description("Green id with face size invalid auth test")
    public void t02_greenIdFaceSizeInvalid() throws IOException, InterruptedException {
        Injection injection = new Injection(driver);
        injection.imageInjectionConfigure("./ocr/greenID/auth/GreenIdWithFaceSizeInvalid.jpg", null, 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(),"success");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertFalse(variables.isStampDetected());
        Assert.assertFalse(variables.isFaceSizeValid());
        Assert.assertFalse(variables.isFacePositionValid());
        Assert.assertFalse(variables.isFaceRotationValid());
        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck","false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertFalse(ILIDVariables.isStampDetected());
        Assert.assertFalse(ILIDVariables.isFaceSizeValid());
        Assert.assertFalse(ILIDVariables.isFacePositionValid());
        Assert.assertFalse(ILIDVariables.isFaceRotationValid());
    }

    // Open bug  = https://scanovate.atlassian.net/browse/BD-1019

}
