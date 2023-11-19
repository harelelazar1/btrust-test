package selenium.liveness.test.newServer.newVersion.mathilda.environmentVariables;

import api.ApiResponse;
import api.Variables;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import selenium.BaseTest;
import selenium.Injection;
import selenium.ocr.pageObject.newService.MainPage;
import selenium.ocr.pageObject.newService.ResultsPage;
import utilities.MongoDB.MongoHandler;
import utilities.MongoDB.Variables.CommonVariables;
import utilities.MongoDB.Variables.Liveness.*;
import utilities.MongoDB.Variables.StartSessionVariables;
import utilities.TestUtils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class DisableDemoEndpoint extends BaseTest {

    Variables variables;
    MongoHandler mongoHandler;
    MainPage mainPage;
    ApiResponse apiResponse;
    String configFile;
    Injection injection;

    @BeforeMethod
    public void startSession() {
        configFile = null;
        variables = new Variables();
        mongoHandler = new MongoHandler();
        apiResponse = new ApiResponse();
        mainPage = new MainPage(driver);
        injection = new Injection(driver);
        driver.get(LIVENESS_DEMO);
    }

    @Test(description = "Liveness passive sanity test with direct URL to the flow")
    @Description("Liveness passive sanity test with direct URL because the demo is disabled")
    public void t01_LivenessPassiveWithDirectUrl() throws IOException, InterruptedException {
        HttpURLConnection con = (HttpURLConnection) new URL(driver.getCurrentUrl()).openConnection();

        System.out.println("Status code: " + con.getResponseCode());
        Assert.assertEquals(con.getResponseCode(), 404);
        configFile = "mathilda.json";
        String newCaseId = apiResponse.randomString();
        System.out.println(newCaseId);
        String directUrl = LIVENESS_DEMO + "/?params={%22url%22:%22https:\\/\\/liveness-qa.scanovate.com%22," +
                "%22extraData%22:{%22caseId%22:%22" + newCaseId + "%22,%22libraryName%22:%22LIVENESS%22,%22flowConfigName%22:%22" + configFile + "%22}}";
        driver.get(directUrl);

        injection.imageInjection("./liveness/apiPic/barCenter.jpg", 1, false);
        injection.sttInjection("./liveness/audio/stt/fullStt");

        ResultsPage resultsPage = new ResultsPage(driver);
        resultsPage.collectResults(variables);

        Assert.assertEquals(resultsPage.verifyListsSizes(), 14);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), variables.getStatus());
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "mathilda.json");
        Assert.assertTrue(variables.isSuccess());

        Assert.assertEquals(variables.getThreshold(), TestUtils.LIVENESS_THRESHOLD);
        Assert.assertEquals(resultsPage.valueToCheck(variables, "score\n" + "0.9221184849739075"), "score\n" + "0.9221184849739075");
        Assert.assertEquals(variables.getFaceImage(), "face_image");
        Assert.assertEquals(variables.getInputImage1(), "input_image");

        Assert.assertNotNull(resultsPage.valueToCheck(variables, "stt_text"));
        Assert.assertEquals(resultsPage.valueToCheck(variables, "original_text"), "original_text\n" + "החשבון יהיה בבעלותי ולא יהיו בו נהנים אחרים");
        Assert.assertEquals(resultsPage.valueToCheck(variables, "threshold\n" + "0.7"), "threshold\n" + "0.7");
        Assert.assertEquals(resultsPage.valueToCheck(variables, "language\n"), "language\n" + "he-IL");
        Assert.assertNotNull(resultsPage.valueToCheck(variables, "score"));
        Assert.assertTrue(Double.parseDouble(resultsPage.valueToCheck(variables, "score").split("\n")[1]) > Double.parseDouble(resultsPage.valueToCheck(variables, "threshold\n" + "0.7").split("\n")[1]));
        System.out.println(Double.parseDouble(resultsPage.valueToCheck(variables, "score").split("\n")[1]));
        System.out.println(Double.parseDouble(resultsPage.valueToCheck(variables, "threshold\n" + "0.7").split("\n")[1]));

        Assert.assertTrue(resultsPage.verifyImagesAreReal());

        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), "session_start");
        Assert.assertNotNull(StartSessionVariables.getObjectId());
        Assert.assertEquals(StartSessionVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(StartSessionVariables.getServerType(), "modular-server-liveness");
        Assert.assertEquals(StartSessionVariables.getServiceType(), "LIVENESS");
        Assert.assertEquals(StartSessionVariables.getStatus(), "session_start");
        Assert.assertEquals(StartSessionVariables.getWorkingMode(), "multi");

        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());

        Assert.assertNotNull(CommonVariables.getObjectId());
        Assert.assertEquals(CommonVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(CommonVariables.getConfigFilename(), "mathilda.json");
        Assert.assertEquals(CommonVariables.getServiceType(), "LIVENESS");
        Assert.assertEquals(CommonVariables.getStatus(), "failure");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Image injection attempt detected");
        Assert.assertTrue(CommonVariables.isSuccess());
        Assert.assertNotNull(CommonVariables.getVideo());
        Assert.assertEquals(CommonVariables.getWorkingMode(), "multi");

        Assert.assertEquals(LivenessHandlers.getActionType2(), "stage");
        Assert.assertEquals(LivenessHandlers.getOrder2(), 1);
        Assert.assertEquals(LivenessHandlers.getTypeUnderStage2(), "passive");
        Assert.assertEquals(PassiveVariables.getThreshold(), 0.1);
        Assert.assertEquals(PassiveVariables.getScore(), 0.9221184849739075);
        Assert.assertNotNull(PassiveVariables.getFaceImage());
        Assert.assertNotNull(PassiveVariables.getInputImage());
        Assert.assertEquals(PassiveVariables.getStageStatus2(), "success");

        Assert.assertEquals(SttVariables.getActionType6(), "complete");
        Assert.assertEquals(SttVariables.getOrder6(), 2);
        Assert.assertEquals(SttVariables.getTypeUnderStage6(), "stt");
        Assert.assertEquals(SttVariables.getSttText(), "החשבון יהיה בבעלותי ולא יהיו בו נהנים");
        Assert.assertEquals(SttVariables.getOriginalText(), "החשבון יהיה בבעלותי ולא יהיו בו נהנים אחרים");
        Assert.assertEquals(SttVariables.getThreshold(), 0.7);
        Assert.assertEquals(SttVariables.getLanguage(), "he-IL");
        Assert.assertEquals(SttVariables.getScore(), 0.9230769230769231);
        Assert.assertEquals(SttVariables.getStageStatus6(), "success");

        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-liveness");
    }

    @Test(description = "Liveness active sanity test with direct URL to the flow")
    @Description("Liveness active sanity test with direct URL because the demo is disabled")
    public void t02_LivenessActiveWithDirectUrl() throws IOException, InterruptedException {
        HttpURLConnection con = (HttpURLConnection) new URL(driver.getCurrentUrl()).openConnection();

        System.out.println("Status code: " + con.getResponseCode());
        Assert.assertEquals(con.getResponseCode(), 404);
        configFile = "qa_config.json";
        String newCaseId = apiResponse.randomString();
        System.out.println(newCaseId);
        String directUrl = LIVENESS_DEMO + "/?params={%22url%22:%22https:\\/\\/liveness-qa.scanovate.com%22," +
                "%22extraData%22:{%22stagesConfiguration%22:{%22otp%22:{%22number%22:%221111%22}},%22caseId%22:%22" + newCaseId + "%22,%22libraryName%22:%22LIVENESS%22,%22flowConfigName%22:%22" + configFile + "%22}}";
        driver.get(directUrl);

        injection.livenessInjection2("./liveness/apiPic/barLeft.jpg", "./liveness/apiPic/barCenter.jpg", "./liveness/apiPic/barRight.jpg");
        injection.otpInjection("./liveness/audio/otp/otpAudioChunks");
        injection.sttInjection("./liveness/audio/stt/sttAudioChunksWithoutFirstChunk");

        ResultsPage resultsPage = new ResultsPage(driver);
        resultsPage.collectResults(variables);

        Assert.assertEquals(resultsPage.verifyListsSizes(), 15);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "success");
        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), "qa_config.json");
        Assert.assertTrue(variables.isSuccess());

        Assert.assertEquals(variables.getThreshold(), TestUtils.LIVENESS_THRESHOLD);
        Assert.assertEquals(resultsPage.valueToCheck(variables, "score\n" + "0.9221184849739075"), "score\n" + "0.9221184849739075");
        Assert.assertEquals(variables.getFaceImage(), "face_image");
        Assert.assertEquals(variables.getInputImage1(), "input_image");
        Assert.assertEquals(resultsPage.valueToCheck(variables, "otp_number"), "otp_number\n" + variables.getOtpNum());

        Assert.assertNotNull(resultsPage.valueToCheck(variables, "stt_text"));
        Assert.assertEquals(resultsPage.valueToCheck(variables, "original_text"), "original_text\n" + "החשבון יהיה בבעלותי ולא יהיו בו נהנים אחרים");
        Assert.assertEquals(resultsPage.valueToCheck(variables, "threshold\n" + "0.7"), "threshold\n" + "0.7");
        Assert.assertEquals(resultsPage.valueToCheck(variables, "language\n"), "language\n" + "he-IL");
        Assert.assertNotNull(resultsPage.valueToCheck(variables, "score"));
        Assert.assertTrue(Double.parseDouble(resultsPage.valueToCheck(variables, "score").split("\n")[1]) > Double.parseDouble(resultsPage.valueToCheck(variables, "threshold\n" + "0.7").split("\n")[1]));
        System.out.println(Double.parseDouble(resultsPage.valueToCheck(variables, "score").split("\n")[1]));
        System.out.println(Double.parseDouble(resultsPage.valueToCheck(variables, "threshold\n" + "0.7").split("\n")[1]));

        Assert.assertTrue(resultsPage.verifyImagesAreReal());

        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), "session_start");
        Assert.assertNotNull(StartSessionVariables.getObjectId());
        Assert.assertEquals(StartSessionVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(StartSessionVariables.getServerType(), "modular-server-liveness");
        Assert.assertEquals(StartSessionVariables.getServiceType(), "LIVENESS");
        Assert.assertEquals(StartSessionVariables.getStatus(), "session_start");
        Assert.assertEquals(StartSessionVariables.getWorkingMode(), "multi");

        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());

        Assert.assertNotNull(CommonVariables.getObjectId());
        Assert.assertEquals(CommonVariables.getCaseId(), variables.getCaseId());
        Assert.assertEquals(CommonVariables.getConfigFilename(), "qa_config.json");
        Assert.assertEquals(CommonVariables.getServiceType(), "LIVENESS");
        Assert.assertEquals(CommonVariables.getStatus(), "success");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Process ended successfully");
        Assert.assertTrue(CommonVariables.isSuccess());
        Assert.assertNotNull(CommonVariables.getVideo());
        Assert.assertEquals(CommonVariables.getWorkingMode(), "multi");

        Assert.assertEquals(LivenessHandlers.getActionType2(), "stage");
        Assert.assertEquals(LivenessHandlers.getOrder2(), 1);
        Assert.assertEquals(LivenessHandlers.getTypeUnderStage2(), "passive");
        Assert.assertEquals(PassiveVariables.getThreshold(), 0.1);
        Assert.assertEquals(PassiveVariables.getScore(), 0.9221184849739075);
        Assert.assertNotNull(PassiveVariables.getFaceImage());
        Assert.assertNotNull(PassiveVariables.getInputImage());
        Assert.assertEquals(PassiveVariables.getStageStatus2(), "success");

        Assert.assertEquals(LeftRightCenterVariables.getActionType3(), "stage");
        Assert.assertEquals(LeftRightCenterVariables.getOrder3(), 2);
        Assert.assertNotNull(LeftRightCenterVariables.getTypeUnderStage3());
        Assert.assertEquals(LeftRightCenterVariables.getStageStatus3(), "success");

        Assert.assertEquals(LeftRightCenterVariables.getActionType4(), "stage");
        Assert.assertEquals(LeftRightCenterVariables.getOrder4(), 3);
        Assert.assertEquals(LeftRightCenterVariables.getTypeUnderStage4(), "center");
        Assert.assertEquals(LeftRightCenterVariables.getStageStatus4(), "success");

        Assert.assertEquals(LeftRightCenterVariables.getActionType7(), "stage");
        Assert.assertEquals(LeftRightCenterVariables.getOrder7(), 4);
        Assert.assertNotNull(LeftRightCenterVariables.getTypeUnderStage7());
        Assert.assertEquals(LeftRightCenterVariables.getStageStatus7(), "success");

        Assert.assertEquals(OtpVariables.getActionType5(), "stage");
        Assert.assertEquals(OtpVariables.getOrder5(), 5);
        Assert.assertEquals(OtpVariables.getTypeUnderStage5(), "otp");
        Assert.assertEquals(OtpVariables.getOtpNumber(), 1111);
        Assert.assertEquals(OtpVariables.getStageStatus5(), "success");

        Assert.assertEquals(SttVariables.getActionType6(), "complete");
        Assert.assertEquals(SttVariables.getOrder6(), 6);
        Assert.assertEquals(SttVariables.getTypeUnderStage6(), "stt");
        Assert.assertEquals(SttVariables.getSttText(), "החשבון החשבון יהיה בבעלותי ולא יהיו בו נהנים");
        Assert.assertEquals(SttVariables.getOriginalText(), "החשבון יהיה בבעלותי ולא יהיו בו נהנים אחרים");
        Assert.assertEquals(SttVariables.getThreshold(), 0.7);
        Assert.assertEquals(SttVariables.getLanguage(), "he-IL");
        Assert.assertEquals(SttVariables.getScore(), 0.8470588235294118);
        Assert.assertEquals(SttVariables.getStageStatus6(), "success");

        Assert.assertEquals(CommonVariables.getServerType(), "modular-server-liveness");
    }
}
