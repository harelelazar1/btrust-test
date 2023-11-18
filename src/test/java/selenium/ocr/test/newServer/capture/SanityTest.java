package selenium.ocr.test.newServer.capture;

import api.Variables;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;
import selenium.BaseTest;
import selenium.Injection;
import selenium.ocr.pageObject.newService.MainPage;
import selenium.ocr.pageObject.newService.ResultsPage;
import utilities.MongoDB.MongoHandler;
import utilities.MongoDB.Variables.CommonVariables;
import utilities.MongoDB.Variables.Ocr.CaptureVariables;
import utilities.MongoDB.Variables.Ocr.OcrHandlers;
import utilities.MongoDB.Variables.StartSessionVariables;

import java.io.IOException;

public class SanityTest extends BaseTest {

    Variables variables = new Variables();
    MongoHandler mongoHandler = new MongoHandler();

    @Test(description = "ocr capture e2e process")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Ocr capture end-to-end process, with mongoDB support")
    public void t01_captureE2E() throws IOException, InterruptedException {
        driver.get(OCR_DEMO);
        MainPage mainPage = new MainPage(driver);
        mainPage.chooseFromServicesList("Capture");
        Injection injection = new Injection(driver);
        injection.imageInjectionConfigure("./ocr/blueID/liad/blueID_color1.jpg", null, 1, false, null);
        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 4);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "success");
        Assert.assertTrue(variables.isSuccess());
        Assert.assertNotNull(resultsPage.valueToCheck(variables, "input_image"));

        Assert.assertTrue(resultsPage.verifyImagesAreReal());
    }
}
