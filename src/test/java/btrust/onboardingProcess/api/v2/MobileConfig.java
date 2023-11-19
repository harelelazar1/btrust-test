package btrust.onboardingProcess.api.v2;

import api.ApiResponse;
import api.Variables;
import btrust.onboardingProcess.api.ApiResponses;
import btrust.onboardingProcess.api.Utilities;
import btrust.onboardingProcess.api.variables.EndpointResults;
import btrust.onboardingProcess.api.variables.PreProcess;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utilities.DBUtils;
import utilities.SuiteListener;
import utilities.TestUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static btrust.btrustOne.admin.BaseAdminUserTest.randomString;
import static org.testng.Assert.*;
@Listeners({SuiteListener.class})
public class MobileConfig {
    Map<String, File> processes;
    PreProcess preProcess;
    EndpointResults endpointResults;
    Utilities utilities;
    ApiResponses apiResponses;

    ApiResponse apiResponse;

    String sessionId;
    Variables variables;

    @BeforeMethod
    public void initSession() {
        preProcess = new PreProcess();
        endpointResults = new EndpointResults();
        processes = new HashMap<>();
        utilities = new Utilities();
        apiResponses = new ApiResponses();
        apiResponse = new ApiResponse();
        variables = new Variables();
    }

    @AfterClass
    public void setDefaultMobileConfig(){
        String defaultMobileConfig= TestUtils.getTestProperty("MOBILE_INTERACTION_DEFAULT_CONFIG","");
        Assert.assertNotNull(defaultMobileConfig);
        DBUtils.updateMobileConfig(defaultMobileConfig);
    }
//    @Test(description = "Onboarding Api Test GreenId card Biometric match Fail")
//    @Description("Onboarding Api Test GreenId card Biometric match Fail")
    public void t04_sessionTimeOut() throws IOException, InterruptedException {
        String mobile60Config= TestUtils.getTestProperty("MOBILE_INTERACTION_60_SECS_CONFIG","");
        Assert.assertNotNull(mobile60Config);
        DBUtils.updateMobileConfig(mobile60Config);
        sessionId = apiResponses.createSessionId(26);
        apiResponses.initSession(preProcess, 26, sessionId);
        preProcess.setCaseId(randomString());

//        apiResponses.updateRequestPutCall(preProcess, 26, preProcess.getMissingFields(), ocrCaseId, "sessionId", sessionId);

        apiResponse.clientInitResponse(variables, "application/json", "caseId", preProcess.getCaseId(), "libraryName", "IL-ID", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR);

        assertTrue(preProcess.isSuccess());
        assertEquals(preProcess.getErrorCode(), 0);
        assertEquals(preProcess.getDataId(), "process-76b8ff8c-dbcf-4031-9ffc-b7ca5dfcb92b");
        assertNotNull(preProcess.getInquiryId());
        assertTrue(preProcess.isDataSuccess());
        assertEquals(preProcess.getNextProcess(), "server_ocr");
        assertEquals((preProcess.getMissingFields()).substring(0, 9), "ocrCaseId");
        assertEquals(preProcess.getDataErrorCode(), 1001);
        assertNotNull(preProcess.getSessionId());
        assertNotNull(preProcess.getOcrTypes());
        assertFalse(preProcess.isBackStepAllow());

        apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization",
                "Bearer " + variables.getToken(), "frame", new File("./ocr/greenID/bar/BarGreenID.jpg"),
                "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./ocr/greenID/bar/BarGreenID.jpg"),
                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        }
        assertEquals(variables.getActionType(), "complete");


        Thread.sleep(60000);


        String ocrCaseId = preProcess.getCaseId();
        apiResponses.updateRequestPutCall(preProcess, 26, preProcess.getMissingFields(), ocrCaseId, "sessionId", sessionId);


        assertTrue(preProcess.isSuccess());
        assertEquals(preProcess.getErrorCode(), 0);
        assertNotNull(preProcess.getInquiryId());
        assertFalse(preProcess.isDataSuccess());
        assertEquals(preProcess.getNextProcess(), "timed_out");
        assertEquals((preProcess.getMissingFields()), "livenessCaseId");
        assertEquals(preProcess.getDataErrorCode(), 1001);
        assertNotNull(preProcess.getSessionId());

        apiResponses.getTokenFromCallbackRequest(preProcess);

        assertTrue(preProcess.isSuccess());
        assertEquals(preProcess.getErrorCode(), 0);
        assertEquals(preProcess.getErrorCode(), 0);
        assertNotNull(preProcess.getData());
        assertNotNull(preProcess.getToken());

        utilities.endpointManager(preProcess, endpointResults, "v2");

        //Final results:
        assertTrue(endpointResults.isSuccess());
    }

}
