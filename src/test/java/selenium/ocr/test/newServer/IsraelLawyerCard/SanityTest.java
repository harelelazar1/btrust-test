package selenium.ocr.test.newServer.IsraelLawyerCard;

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
import utilities.TestUtils;

import java.io.IOException;

public class SanityTest extends BaseTest {

    Variables variables = new Variables();

    @Test(description = "Israel Lawyer Card")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Israel Lawyer Card, check lawyer data from card")
    public void t01_israelLawyerCard() throws IOException, InterruptedException {
        driver.get(OCR_DEMO);
        MainPage mainPage = new MainPage(driver);
        mainPage.chooseConfigFile(TestUtils.getDefaultMathilda());
        mainPage.chooseFromServicesList("Israel Lawyer Card");
        Injection injection = new Injection(driver);
        injection.imageInjectionConfigure("./ocr/lawyerId/yoram_2020_1.jpeg", null, 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 14);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "success");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), TestUtils.getDefaultMathilda());
        Assert.assertTrue(variables.isSuccess());

        //Front section
        Assert.assertEquals(variables.getCardType1(), "israel_lawyer_card_front");
        Assert.assertEquals(variables.getFullNameHebrew(), "יורם יהודה");
        Assert.assertEquals(variables.getFullNameEnglish(), "YORAM YEHUDA");
        Assert.assertEquals(variables.getMemberNumber(), 14215);
        Assert.assertEquals(variables.getIdNumber(), 57798639);
        Assert.assertEquals(variables.getAddress(), "לוי אליהו סאלם 41/30ב רמלה 7250300");
        Assert.assertEquals(variables.getInternetCode(), 142158839);


        //Images
        Assert.assertEquals(variables.getInputImage1(), "input_image");
        Assert.assertEquals(variables.getCroppedImage1(), "cropped_image");
        Assert.assertTrue(resultsPage.verifyImagesAreReal());
    }
}
