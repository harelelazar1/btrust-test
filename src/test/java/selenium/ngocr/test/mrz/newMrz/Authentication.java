package selenium.ngocr.test.mrz.newMrz;

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


    @Test(description = "new mrz with face size invalid")
    @Description("new mrz with face size invalid")
    public void t01_newMrzWithFaceSizeInvalid() throws IOException, InterruptedException {
        injection.imageInjectionConfigure("./ocr/mrz/nadav/nadavMrzWithFaceSizeInvalid2.jpg", null, 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
        //       Assert.assertEquals(resultsPage.verifyListsSizes(), 27);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "success");
//        Assert.assertNotNull(variables.getCaseId());


        Assert.assertEquals(variables.getMrzText(), "P<ISRSHLOMO<<NADAV<<<<<<<<<<<<<<<<<<<<<<<<<<31027245<4ISR9609082M26051883<1539659<8<<<52");
        Assert.assertEquals(variables.getMrzType(), "TD3");
        Assert.assertEquals(variables.getDocumentType(), "Passport");
        Assert.assertEquals(variables.getIssuingCountryCode(), "ISR");
        Assert.assertEquals(variables.getLastNameEnglish(), "SHLOMO");
        Assert.assertEquals(variables.getFirstNameEnglish(), "NADAV");
        Assert.assertEquals(variables.getPassportNumber(), 31027245);
        Assert.assertEquals(variables.getNationalityCode(), "ISR");
        Assert.assertEquals(variables.getDateOfBirth(), "08.09.96");
        Assert.assertEquals(variables.getGender(), "Male");
        Assert.assertEquals(variables.getDateOfExpiry(), "18.05.26");
        Assert.assertEquals(variables.getPersonalNumber(), "3-1539659-8");

        Assert.assertEquals(variables.getFaceImage(), "face_image");
        Assert.assertEquals(variables.getInputImage1(), "input_image");
        Assert.assertEquals(variables.getAlignedCard(), "aligned_card");
        Assert.assertEquals(variables.getAlignedField(), "aligned_field");
        Assert.assertTrue(variables.isDocumentInFrame());
        Assert.assertTrue(variables.isExpiryDateValid());
        Assert.assertFalse(variables.isFaceSizeValid());
        Assert.assertFalse(variables.isFacePositionValid());
        Assert.assertFalse(variables.isFaceRotationValid());

        Assert.assertTrue(resultsPage.verifyImagesAreReal());

    }

    @Test(description = "new mrz with face image roatation")
    @Description("New mrz with face image roatation")
    public void t02_newMrzWithFaceRotation() throws IOException, InterruptedException {
        injection.imageInjectionConfigure("./ocr/mrz/nadav/nadavMrzWithFaceRoatation.jpg", null, 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
//        Assert.assertEquals(resultsPage.verifyListsSizes(10), 27);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "success");
//        Assert.assertNotNull(variables.getCaseId());


        Assert.assertEquals(variables.getMrzText(), "P<ISRSHLOMO<<NADAV<<<<<<<<<<<<<<<<<<<<<<<<<<31027245<4ISR9609082M26051883<1539659<8<<<52");
        Assert.assertEquals(variables.getMrzType(), "TD3");
        Assert.assertEquals(variables.getDocumentType(), "Passport");
        Assert.assertEquals(variables.getIssuingCountryCode(), "ISR");
        Assert.assertEquals(variables.getLastNameEnglish(), "SHLOMO");
        Assert.assertEquals(variables.getFirstNameEnglish(), "NADAV");
        Assert.assertEquals(variables.getPassportNumber(), 31027245);
        Assert.assertEquals(variables.getNationalityCode(), "ISR");
        Assert.assertEquals(variables.getDateOfBirth(), "08.09.96");
        Assert.assertEquals(variables.getGender(), "Male");
        Assert.assertEquals(variables.getDateOfExpiry(), "18.05.26");
        Assert.assertEquals(variables.getPersonalNumber(), "3-1539659-8");
        Assert.assertEquals(variables.getFaceImage(), "face_image");
        Assert.assertEquals(variables.getInputImage1(), "input_image");
        Assert.assertEquals(variables.getAlignedCard(), "aligned_card");
        Assert.assertEquals(variables.getAlignedField(), "aligned_field");
        Assert.assertTrue(variables.isDocumentInFrame());
        Assert.assertTrue(variables.isExpiryDateValid());
        Assert.assertFalse(variables.isFaceSizeValid());
        Assert.assertFalse(variables.isFacePositionValid());
        Assert.assertFalse(variables.isFaceRotationValid());

        Assert.assertTrue(resultsPage.verifyImagesAreReal());
    }
}
