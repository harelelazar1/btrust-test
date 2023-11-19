package selenium.ocr.test.newServer.creditCard;

import api.Variables;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import selenium.BaseTest;
import selenium.Injection;
import selenium.ocr.pageObject.newService.MainPage;
import selenium.ocr.pageObject.newService.ResultsPage;

import java.io.IOException;


public class FunctionalityTest extends BaseTest {

    Variables variables = new Variables();

    @Test(description = "Fake credit card timeout")
    @Description("Fake credit card timeout")
    public void t01_fakeCreditCardTimeout() throws IOException, InterruptedException{
        driver.get(OCR_DEMO);
        MainPage mainPage = new MainPage(driver);
        mainPage.chooseFromServicesList("Credit Card");
        Injection injection = new Injection(driver);
        injection.imageInjectionConfigure("./ocr/creditCard/fakeCreditCard.jpg", null, 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(80), 6);
        resultsPage.collectResults(variables);
        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "timeout");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "mathilda.json");
        Assert.assertTrue(variables.isSuccess());

        Assert.assertEquals(variables.getLastReceivedImage(), "last_received_image");
        Assert.assertTrue(resultsPage.verifyImagesAreReal());
    }

}
