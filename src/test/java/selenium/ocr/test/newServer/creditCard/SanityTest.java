package selenium.ocr.test.newServer.creditCard;

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
            mainPage.chooseConfigFile("63f225246ef5df9ba8306568");
        }
    }

    @Test(description = "Visa Cal sanity",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Visa Cal sanity, check card number and expiry")
    public void t01_visaCalDemo() throws IOException, InterruptedException {
        SanityTest.chooseConfigFile();
        mainPage.chooseFromServicesList("Credit Card");
        Injection injection = new Injection(driver);
        injection.imageInjectionConfigure("./ocr/creditCard/visaCal.jpg", null, 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
//        Assert.assertEquals(resultsPage.verifyListsSizes(), 10);
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
        Assert.assertEquals(variables.getCardType1(),"credit_card_front");
        Assert.assertEquals(variables.getCreditCardNumber(), "5521830000317254");
        Assert.assertEquals(variables.getDateOfExpiry(), "01/26");
        //Images
        Assert.assertEquals(variables.getInputImage1(), "input_image");
        Assert.assertEquals(variables.getCroppedImage1(), "cropped_image");
        Assert.assertTrue(resultsPage.verifyImagesAreReal());
    }
    @Test(description = "Leumi Card sanity",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Leumi Card sanity, check card number and expiry")
    public void t02_leumiCardDemo() throws IOException, InterruptedException {
        SanityTest.chooseConfigFile();
        MainPage mainPage = new MainPage(driver);
        mainPage.chooseFromServicesList("Credit Card");
        Injection injection = new Injection(driver);
        injection.imageInjectionConfigure("./ocr/creditCard/leumiCard.jpg", null, 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
 //       Assert.assertEquals(resultsPage.verifyListsSizes(), 10);
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
        Assert.assertEquals(variables.getCardType1(),"credit_card_front");
        Assert.assertEquals(variables.getCreditCardNumber(), "4580110790813114");
        Assert.assertEquals(variables.getDateOfExpiry(), "03/20");
        //Images
        Assert.assertEquals(variables.getInputImage1(), "input_image");
        Assert.assertEquals(variables.getCroppedImage1(), "cropped_image");
        Assert.assertTrue(resultsPage.verifyImagesAreReal());
    }
    @Test(description = "MasterCard sanity",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("MasterCard sanity, check card number and expiry")
    public void t03_masterCardDemo() throws IOException, InterruptedException {
        SanityTest.chooseConfigFile();
        mainPage.chooseFromServicesList("Credit Card");
        Injection injection = new Injection(driver);
        injection.imageInjectionConfigure("./ocr/creditCard/masterCard.jpg", null, 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
 //       Assert.assertEquals(resultsPage.verifyListsSizes(), 10);
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
        Assert.assertEquals(variables.getCardType1(),"credit_card_front");
        Assert.assertEquals(variables.getCreditCardNumber(), "5326100314433644");
        Assert.assertEquals(variables.getDateOfExpiry(), "02/20");
        //Images
        Assert.assertEquals(variables.getInputImage1(), "input_image");
        Assert.assertEquals(variables.getCroppedImage1(), "cropped_image");
        Assert.assertTrue(resultsPage.verifyImagesAreReal());
    }
}
