package selenium.ocr.test.newServer.cni;

import api.Variables;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import selenium.Injection;
import selenium.ocr.pageObject.newService.MainPage;
import selenium.ocr.pageObject.newService.ResultsPage;
import selenium.BaseTest;
import utilities.MongoDBConnection;
import utilities.MongoDBVariables;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Functionality extends BaseTest {

    Variables variables = new Variables();
    MongoDBConnection mongoDBConnection = new MongoDBConnection();
    MongoDBVariables mongoDBVariables = new MongoDBVariables();

    @BeforeMethod
    public void startSession() {
        driver.get(OCR_DEMO);
        MainPage mainPage = new MainPage(driver);
        mainPage.chooseFromServicesList("Europe Card");
//        mongoDBVariables.setCounter(0);
    }

    @Test(description = "cni demo timeout session stage 1")
    @Description("Cni demo timeout session at stage 1 (front)")
    public void t01_e2eCNIDemoFrontSideStage1Timeout() throws InterruptedException {
        Thread.sleep(85000);

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 6);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "timeout");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "mathilda.json");
        Assert.assertTrue(variables.isSuccess());

        Assert.assertEquals(variables.getLastReceivedImage(), "last_received_image");

        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck","false").equals("false")) {
            return;
        }
        mongoDBConnection.mongoDBHandler(mongoDBVariables, "events", variables.getCaseId(), "session_start");
        Assert.assertNotNull(mongoDBVariables.getObjectId());
        Assert.assertEquals(mongoDBVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(mongoDBVariables.getServerType(), "modular-server-ocr");
        Assert.assertEquals(mongoDBVariables.getServiceType(), "CNI");
        Assert.assertEquals(mongoDBVariables.getStatus(), "session_start");
        Assert.assertEquals(mongoDBVariables.getWorkingMode(), "multi");

        mongoDBConnection.mongoDBHandler(mongoDBVariables, "events", variables.getCaseId(), "timeout");

        Assert.assertNotNull(mongoDBVariables.getObjectId());
        Assert.assertEquals(mongoDBVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(mongoDBVariables.getServiceType(), "CNI");
        Assert.assertEquals(mongoDBVariables.getConfigFilename(), "mathilda.json");
        Assert.assertEquals(mongoDBVariables.getStatus(), "timeout");
        Assert.assertEquals(mongoDBVariables.getStatusReason(), "Session reached maximum allowed flow timeout");
        Assert.assertTrue(mongoDBVariables.isSuccess());
        Assert.assertNotNull(mongoDBVariables.getVideo());
        Assert.assertEquals(mongoDBVariables.getWorkingMode(), "multi");

        Assert.assertEquals(mongoDBVariables.getActionType(), "stage");
        Assert.assertEquals(mongoDBVariables.getOrder(), 1);
        Assert.assertEquals(mongoDBVariables.getTypeUnderStage(), "front");
        Assert.assertNotNull(mongoDBVariables.getLastReceivedImage());
        Assert.assertEquals(mongoDBVariables.getStageStatus(), "timeout");

        Assert.assertEquals(mongoDBVariables.getServerType(), "modular-server-ocr");
    }

    @Test(description = "cni demo timeout session stage 3")
    @Description("Cni demo timeout session at stage 3 (back)")
    public void t02_e2eCNIDemoBackSideStage3Timeout() throws IOException, InterruptedException, UnsupportedAudioFileException {
        Injection injection = new Injection(driver);
        injection.imageInjectionWithTilt("./ocr/cni/France_CNI_Front.jpg", "./ocr/small_jpg.jpg", 1, true, "3");

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.verifyListsSizes(), 10);
        resultsPage.collectResults(variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "timeout");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "mathilda.json");
        Assert.assertTrue(variables.isSuccess());

        Assert.assertEquals(variables.getCardType1(),"france_id_cni_front");
        Assert.assertEquals(variables.getFaceImage(), "face_image");
        Assert.assertEquals(variables.getInputImage1(), "input_image");
        Assert.assertEquals(variables.getCroppedImage1(), "cropped_image");

        Assert.assertEquals(variables.getLastReceivedImage(), "last_received_image");
        Assert.assertTrue(resultsPage.verifyImagesAreReal());

        if (System.getProperty("mongoCheck","false").equals("false")) {
            return;
        }
        // Mongo Testing ****************************************************************
        mongoDBConnection.mongoDBHandler(mongoDBVariables, "events", variables.getCaseId(), "session_start");
        Assert.assertNotNull(mongoDBVariables.getObjectId());
        Assert.assertEquals(mongoDBVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(mongoDBVariables.getServerType(), "modular-server-ocr");
        Assert.assertEquals(mongoDBVariables.getServiceType(), "CNI");
        Assert.assertEquals(mongoDBVariables.getStatus(), "session_start");
        Assert.assertEquals(mongoDBVariables.getWorkingMode(), "multi");

        mongoDBConnection.mongoDBHandler(mongoDBVariables, "events", variables.getCaseId(), variables.getStatus());
        Assert.assertNotNull(mongoDBVariables.getObjectId());
        Assert.assertEquals(mongoDBVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(mongoDBVariables.getServiceType(), "CNI");
        Assert.assertEquals(mongoDBVariables.getConfigFilename(), "mathilda.json");
        Assert.assertEquals(mongoDBVariables.getStatus(), "timeout");
        Assert.assertEquals(mongoDBVariables.getStatusReason(), "Session reached maximum allowed flow timeout");
        Assert.assertTrue(mongoDBVariables.isSuccess());
        Assert.assertNotNull(mongoDBVariables.getVideo());
        Assert.assertEquals(mongoDBVariables.getWorkingMode(), "multi");

        Assert.assertEquals(mongoDBVariables.getActionType(), "stage");
        Assert.assertEquals(mongoDBVariables.getOrder(), 1);
        Assert.assertEquals(mongoDBVariables.getTypeUnderStage(), "front");
        Assert.assertEquals(mongoDBVariables.getCardType(), "cni_front");
        Assert.assertEquals(mongoDBVariables.getStageStatus(), "success");

        Assert.assertNotNull(mongoDBVariables.getInputImage());
        Assert.assertNotNull(mongoDBVariables.getCroppedImage());
        Assert.assertNotNull(mongoDBVariables.getFaceImage());

        mongoDBConnection.mongoDBHandler(mongoDBVariables, "events", variables.getCaseId(), variables.getStatus());
        Assert.assertEquals(mongoDBVariables.getActionType(), "stage");
        Assert.assertEquals(mongoDBVariables.getOrder(), 2);
        Assert.assertEquals(mongoDBVariables.getTypeUnderStage(), "video");
        Assert.assertEquals(mongoDBVariables.getStageStatus(), "success");

        mongoDBConnection.mongoDBHandler(mongoDBVariables, "events", variables.getCaseId(), variables.getStatus());
        Assert.assertEquals(mongoDBVariables.getActionType(), "stage");
        Assert.assertEquals(mongoDBVariables.getOrder(), 3);
        Assert.assertEquals(mongoDBVariables.getTypeUnderStage(), "back");
        Assert.assertEquals(mongoDBVariables.getStageStatus(), "timeout");
        Assert.assertNotNull(mongoDBVariables.getLastReceivedImage());

        Assert.assertEquals(mongoDBVariables.getServerType(), "modular-server-ocr");
    }
}
