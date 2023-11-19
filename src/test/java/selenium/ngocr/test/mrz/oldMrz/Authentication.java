package selenium.ngocr.test.mrz.oldMrz;

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
import utilities.MongoDB.Variables.Ocr.MRZVariables;
import utilities.MongoDB.Variables.Ocr.OcrHandlers;
import utilities.MongoDB.Variables.StartSessionVariables;

import java.io.IOException;

public class Authentication extends BaseTest {

    MainPage mainPage;
    Variables variables;
    MongoHandler mongoHandler;
    Injection injection;

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
        mainPage.chooseFromServicesList("MRZ");

    }

    public static void chooseConfigFile() {
        MainPage mainPage = new MainPage(driver);
        if (System.getProperty("OCR_NEW").equalsIgnoreCase("false")) {
            mainPage.chooseConfigFile("two_sides.json");
        } else {
            mainPage.chooseConfigFile(System.getProperty("COMPANY_ID"));
        }
    }


    @BeforeMethod
    public void startSession() {
        variables = new Variables();
        mongoHandler = new MongoHandler();
        mainPage = new MainPage(driver);
        injection = new Injection(driver);
        mainPage.chooseFromServicesList("MRZ");
    }



    @Test(description = "old mrz without face image",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Old mrz without face image")
    public void t01_oldMrzWithFaceSizeInvalid() throws IOException, InterruptedException {
        injection.imageInjectionConfigure("./ocr/mrz/guy/guyMrzWithFaceSizeInvalid.jpg", null, 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
 //       Assert.assertEquals(resultsPage.verifyListsSizes(), 27);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "success");
//        Assert.assertNotNull(variables.getCaseId());


        Assert.assertEquals(variables.getMrzText(), "P<ISRSTIEBEL<<GUY<<<<<<<<<<<<<<<<<<<<<<<<<<<23369458<8ISR8609199M26120410<2649440<1<<<80");
        Assert.assertEquals(variables.getMrzType(), "TD3");
        Assert.assertEquals(variables.getDocumentType(), "Passport");
        
        Assert.assertEquals(variables.getIssuingCountryCode(), "ISR");
        Assert.assertEquals(variables.getLastNameEnglish(), "STIEBEL");
        Assert.assertEquals(variables.getFirstNameEnglish(), "GUY");
        Assert.assertEquals(variables.getPassportNumber(), 23369458);
        Assert.assertEquals(variables.getNationalityCode(), "ISR");
        Assert.assertEquals(variables.getDateOfBirth(), "19.09.86");
        Assert.assertEquals(variables.getGender(), "Male");
        Assert.assertEquals(variables.getDateOfExpiry(), "04.12.26");
        Assert.assertEquals(variables.getPersonalNumber(), "0-2649440-1");

        Assert.assertEquals(variables.getFaceImage(), "face_image");
        Assert.assertEquals(variables.getInputImage1(), "input_image");
        Assert.assertEquals(variables.getAlignedCard(), "aligned_card");
        Assert.assertEquals(variables.getAlignedField(), "aligned_field");
        

        Assert.assertTrue(variables.isDocumentInFrame());
        Assert.assertTrue(variables.isExpiryDateValid());
        Assert.assertFalse(variables.isFaceSizeValid());
        Assert.assertFalse(variables.isFacePositionValid());
        Assert.assertTrue(variables.isFaceRotationValid());

        Assert.assertTrue(resultsPage.verifyImagesAreReal());

    }

    @Test(description = "old mrz with face image roatation",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Old mrz with face image roatation")
    public void t02_oldMrzWithFaceRotation() throws IOException, InterruptedException {
        injection.imageInjectionConfigure("./ocr/mrz/guy/guyMrzWithFaceRoatation.jpg", null, 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
//        Assert.assertEquals(resultsPage.verifyListsSizes(), 27);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "success");
//        Assert.assertNotNull(variables.getCaseId());


        Assert.assertEquals(variables.getMrzText(), "P<ISRSTIEBEL<<GUY<<<<<<<<<<<<<<<<<<<<<<<<<<<23369458<8ISR8609199M26120410<2649440<1<<<80");
        Assert.assertEquals(variables.getMrzType(), "TD3");
        Assert.assertEquals(variables.getDocumentType(), "Passport");
        
        Assert.assertEquals(variables.getIssuingCountryCode(), "ISR");
        Assert.assertEquals(variables.getLastNameEnglish(), "STIEBEL");
        Assert.assertEquals(variables.getFirstNameEnglish(), "GUY");
        Assert.assertEquals(variables.getPassportNumber(), 23369458);
        Assert.assertEquals(variables.getNationalityCode(), "ISR");
        Assert.assertEquals(variables.getDateOfBirth(), "19.09.86");
        Assert.assertEquals(variables.getGender(), "Male");
        Assert.assertEquals(variables.getDateOfExpiry(), "04.12.26");
        Assert.assertEquals(variables.getPersonalNumber(), "0-2649440-1");

        Assert.assertEquals(variables.getFaceImage(), "face_image");
        Assert.assertEquals(variables.getInputImage1(), "input_image");
        Assert.assertEquals(variables.getAlignedCard(), "aligned_card");
        Assert.assertEquals(variables.getAlignedField(), "aligned_field");
        

        Assert.assertTrue(variables.isDocumentInFrame());
        Assert.assertTrue(variables.isExpiryDateValid());
        Assert.assertFalse(variables.isFaceSizeValid());
        Assert.assertFalse(variables.isFacePositionValid());
        Assert.assertTrue(variables.isFaceRotationValid());

        Assert.assertTrue(resultsPage.verifyImagesAreReal());
    }
}
