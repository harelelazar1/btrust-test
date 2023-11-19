package selenium.ocr.test.newServer.mathilda.mathildaTests.reverseStages;

import api.Variables;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import selenium.Injection;
import selenium.ocr.pageObject.newService.MainPage;
import selenium.ocr.pageObject.newService.ResultsPage;
import selenium.BaseTest;
import utilities.MongoDB.MongoHandler;
import utilities.MongoDB.Variables.CommonVariables;
import utilities.MongoDB.Variables.Ocr.CNIVariables;
import utilities.MongoDB.Variables.Ocr.OcrHandlers;
import utilities.MongoDB.Variables.StartSessionVariables;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class cni extends BaseTest {

    Variables variables = new Variables();
    MongoHandler mongoHandler = new MongoHandler();

    @Test(description = "cni with reverse stages")
    @Description("CNI with reverse stages (back side first and then front side)")
    public void t01_cniReverseStage() throws UnsupportedAudioFileException, IOException, InterruptedException {
        MainPage mainPage = new MainPage(driver);
        Injection injection = new Injection(driver);
        driver.get(OCR_DEMO);
        mainPage.chooseConfigFile("reverseStages.json");
        mainPage.chooseFromServicesList("CNI");
        injection.imageInjectionWithTilt("./ocr/cni/good_fr_back_1.jpg", "./ocr/cni/good_fr_3.jpg", 1, false, null);

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 13);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "failure");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "reverseStages.json");
        Assert.assertTrue(variables.isSuccess());

        //Back fields
        Assert.assertEquals(variables.getCardType2(),"cni_back");
        Assert.assertEquals(variables.getInputImage1(), "input_image");
        Assert.assertEquals(variables.getCroppedImage1(), "cropped_image");
        Assert.assertEquals(variables.getOtpNum(), 1111);

        //Front fields
        Assert.assertEquals(variables.getCardType1(),"cni_front");
        Assert.assertEquals(variables.getFaceImage(), "face_image");
        Assert.assertEquals(variables.getInputImage1(), "input_image");
        Assert.assertEquals(variables.getCroppedImage1(), "cropped_image");

        Assert.assertTrue(resultsPage.verifyImagesAreReal());

        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck","false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), "session_start");
		
        Assert.assertNotNull(StartSessionVariables.getObjectId());
        Assert.assertEquals(StartSessionVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(StartSessionVariables.getServerType(), "modular-server-ocr");
        Assert.assertEquals(StartSessionVariables.getServiceType(), "CNI");
        Assert.assertEquals(StartSessionVariables.getStatus(), "session_start");
        Assert.assertEquals(StartSessionVariables.getWorkingMode(), "multi");

        if (System.getProperty("mongoCheck","false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertNotNull(CommonVariables.getObjectId());
        Assert.assertEquals(CommonVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(CommonVariables.getConfigFilename(), "reverseStages.json");
        Assert.assertEquals(CommonVariables.getServiceType(), "CNI");
        switch (CommonVariables.getStatus()) {
            case "failure": {
                Assert.assertEquals(CommonVariables.getStatus(), "failure");
                Assert.assertEquals(CommonVariables.getStatusReason(), "Image injection attempt detected");
                break;
            }
            case "success": {
                Assert.assertEquals(CommonVariables.getStatus(), "success");
                Assert.assertEquals(CommonVariables.getStatusReason(), "Process ended successfully");
                break;
            }
        }
        Assert.assertTrue(CommonVariables.isSuccess());
        Assert.assertNotNull(CommonVariables.getVideo());
        Assert.assertEquals(CommonVariables.getWorkingMode(), "multi");

        //Back fields
        Assert.assertEquals(OcrHandlers.getActionType2(), "stage");
        Assert.assertEquals(OcrHandlers.getOrder2(), 1);
        Assert.assertEquals(OcrHandlers.getTypeUnderStage2(), "back");
        Assert.assertEquals(CNIVariables.getBackCardType(), "cni_back");
        Assert.assertNotNull(CNIVariables.getInputImage2());
        Assert.assertNotNull(CNIVariables.getCroppedImage2());
        Assert.assertEquals(CNIVariables.getStageStatus2(), "success");

        //video back side
        Assert.assertEquals(CNIVariables.getActionType3(), "stage");
        Assert.assertEquals(CNIVariables.getOrder3(), 2);
        Assert.assertEquals(CNIVariables.getTypeUnderStage3(), "video");
        Assert.assertEquals(CNIVariables.getStageStatus3(), "success");

        //Front fields
        Assert.assertEquals(OcrHandlers.getActionType(), "stage");
        Assert.assertEquals(OcrHandlers.getOrder(), 3);
        Assert.assertEquals(OcrHandlers.getTypeUnderStage(), "front");
        Assert.assertEquals(CNIVariables.getFrontCardType(), "cni_front");
        Assert.assertNotNull(CNIVariables.getFaceImage());
        Assert.assertNotNull(CNIVariables.getInputImage());
        Assert.assertNotNull(CNIVariables.getCroppedImage());
        Assert.assertEquals(CNIVariables.getStageStatus(), "success");

        //video front side
        Assert.assertEquals(CNIVariables.getActionType4(), "stage");
        Assert.assertEquals(CNIVariables.getOrder4(), 4);
        Assert.assertEquals(CNIVariables.getTypeUnderStage4(), "video");
        Assert.assertEquals(CNIVariables.getStageStatus4(), "success");

        //otp
        Assert.assertEquals(CNIVariables.getActionType5(), "complete");
        Assert.assertEquals(CNIVariables.getOrder5(), 5);
        Assert.assertEquals(CNIVariables.getTypeUnderStage5(), "otp");
        Assert.assertEquals(CNIVariables.getOtpNum(), "1111");
        Assert.assertEquals(CNIVariables.getStageStatus5(), "success");

        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-ocr");
    }
}
