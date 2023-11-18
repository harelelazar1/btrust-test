package selenium.ocr.test.newServer.StayPermit;

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
        mainPage.chooseConfigFile("mathilda.json");
    }

    @Test(description = "Israel Stay Permit")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Israel Stay Permit sanity, check permit type and expiry")
    public void t01_israelStayPermit() throws IOException, InterruptedException {
        SanityTest.chooseConfigFile();
        mainPage.chooseFromServicesList("Israel Stay Permit");
        Injection injection = new Injection(driver);
        injection.imageInjectionConfigure("./ocr/stayPermit/israel_stay_permit_front.jpg", null, 1, false, null);
        ResultsPage resultsPage = new ResultsPage(driver);
//        Assert.assertEquals(resultsPage.verifyListsSizes(), 10);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "success");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "mathilda.json");
        Assert.assertTrue(variables.isSuccess());


        //Front section
        Assert.assertEquals(variables.getCardType1(), "israel_stay_permit");
        Assert.assertEquals(variables.getPermitType(), "B2");
        Assert.assertEquals(variables.getDateOfExpiry(), "06/01/17");
        Assert.assertEquals(variables.getPassportNumber1(), "EB8806818");
        //Images
        Assert.assertEquals(variables.getInputImage1(), "input_image");
        Assert.assertEquals(variables.getCroppedImage1(), "cropped_image");
        Assert.assertTrue(resultsPage.verifyImagesAreReal());
    }
}
