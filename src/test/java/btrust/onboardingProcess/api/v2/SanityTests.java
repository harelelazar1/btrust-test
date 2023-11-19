package btrust.onboardingProcess.api.v2;

import api.ApiResponse;
import api.Variables;
import btrust.onboardingProcess.api.ApiRequests;
import btrust.onboardingProcess.api.ApiResponses;
import btrust.onboardingProcess.api.Utilities;
import btrust.onboardingProcess.api.variables.*;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utilities.DBUtils;
import utilities.SuiteListener;
import utilities.TestUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import static btrust.btrustOne.admin.BaseAdminUserTest.randomString;
import static org.testng.Assert.*;
@Listeners({SuiteListener.class})
public class SanityTests {

    Map<String, File> processes;
    PreProcess preProcess;
    EndpointResults endpointResults;
    Utilities utilities;
    ApiResponses apiResponses;

    ApiResponse apiResponse;

    String X_Request_Id;
    String X_Session_Id;
    String X_Request_Id1;
    String X_Session_Id1;
    String sessionId;
    String clientTranslationFileValue;
    boolean devMode;


    Variables variables;
    //  String companyUuid = "63f225246ef5df9ba8306568";

    long timeDiff;


    private void clearWebhook() {
        try {
            TestUtils tu = new TestUtils();
            String baseWebhookUrl = "https://webhook.site/";
            Response response = tu.resetWebhook(baseWebhookUrl);
            String webHookTime = response.header("Date");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss");
            Date date1 = simpleDateFormat.parse(webHookTime);
            timeDiff = (new Date().getTime() - date1.getTime());
        } catch (Exception e) {
            System.out.println("webhook clean failed");
        }
    }

    @BeforeMethod(alwaysRun = true)
    public void initSession() {
        preProcess = new PreProcess();
        endpointResults = new EndpointResults();
        processes = new HashMap<>();
        utilities = new Utilities();
        apiResponses = new ApiResponses();
        apiResponse = new ApiResponse();
        variables = new Variables();
        X_Session_Id = "new" + randomString();
        X_Request_Id = "new" + randomString();
        X_Session_Id1 = "new" + randomString();
        X_Request_Id1 = "new" + randomString();
        clientTranslationFileValue = "scanovate_default_en.json";
        devMode = false;

    }


    @Test(description = "Onboarding Api Test Scanning New BioId card", retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Onboarding Api Test Scanning New BioId card")
    public void t01_onboardingWithBioIdNew() throws IOException, ParseException, InterruptedException {
        sessionId = apiResponses.createSessionId(434);

        apiResponses.initSessionNew(preProcess, 434, sessionId, System.getProperty("COMPANY_ID"));


        apiResponse.clientInitResponseNew(variables, "application/json", "caseId", preProcess.getCaseId(), "libraryName", "IL-ID", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR, sessionId, X_Session_Id, X_Request_Id, "clientTranslationFileName", clientTranslationFileValue, "devMode", devMode);

        assertTrue(preProcess.isSuccess());
        assertEquals(preProcess.getErrorCode(), 0);
        assertEquals(preProcess.getDataId(), "process-c6aa3851-8e6a-4752-b5f2-bfcc02c9439e");
        assertNotNull(preProcess.getInquiryId());
        assertTrue(preProcess.isDataSuccess());
        assertEquals(preProcess.getNextProcess(), "server_ocr");
        assertEquals((preProcess.getMissingFields()).substring(0, 9), "ocrCaseId");
        assertEquals(preProcess.getDataErrorCode(), 1001);
//        assertNotNull(preProcess.getSessionId());
        assertNotNull(preProcess.getOcrTypes());
        assertFalse(preProcess.isBackStepAllow());


        apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                "Bearer " + variables.getToken(), "frame", new File("./ocr/new bioID/Gregory/newBioIDFront.jpeg"),
                "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR,sessionId, X_Session_Id, X_Request_Id);
        while (!variables.getActionType().equalsIgnoreCase("stage")) {
            apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./ocr/new bioID/Gregory/newBioIDFront.jpeg"),
                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR, sessionId, X_Session_Id, X_Request_Id);
        }

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./ocr/new bioID/Gregory/newBioIDBack.jpeg"),
                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR, sessionId, X_Session_Id, X_Request_Id);
        }


        assertEquals(variables.getActionType(), "complete");
        String ocrCaseId = variables.getCaseId();


        apiResponses.updateRequestPutCall(preProcess, 434, preProcess.getMissingFields(), ocrCaseId, "sessionId", sessionId);

        assertTrue(preProcess.isSuccess());
        assertTrue(preProcess.isDataSuccess());
        assertEquals(preProcess.getNextProcess(), "process_ended");


        apiResponses.getTokenFromCallbackRequest(preProcess);

        assertTrue(preProcess.isSuccess());
        assertEquals(preProcess.getErrorCode(), 0);
        assertEquals(preProcess.getErrorCode(), 0);
        assertNotNull(preProcess.getData());
        assertNotNull(preProcess.getToken());

        utilities.endpointManager(preProcess, endpointResults, "v2");

        //Final results:
        assertTrue(endpointResults.isSuccess());
        assertEquals(endpointResults.getErrorCode(), 0);
        assertTrue(endpointResults.isDataSuccess());
        assertEquals(endpointResults.getDataErrorMessage(), "");
        assertEquals(endpointResults.getDataErrorCode(), 0);
        assertEquals(endpointResults.getMetadata(), "");

        assertEquals(ILID.getProcess(), "ocr");
        assertNotNull(ILID.getEnd());
        assertEquals(ILID.getDob(), "05.03.1992");
        assertEquals(ILID.getIssuingDate(), "05.04.2022");
        assertEquals(ILID.getExpiryDate(), "03.04.2027");
        assertEquals(ILID.getFirstName(), "גריגורי");
        assertEquals(ILID.getLastName(), "צ׳רנומורדיק");
        assertEquals(ILID.getDateOfBirth(), "1992-03-05T00:00:00.000+0000");
        assertEquals(ILID.getGender(), "זכר");
        assertEquals(ILID.getIdNumber(), 345648513);
        assertEquals(ILID.getExpirationDate(), "2027-04-03T00:00:00.000+0000");
        assertNotNull(ILID.getFaceImage());
        assertNotNull(ILID.getFrontImage());
        assertNotNull(ILID.getCardImage());
        //       assertNotNull(ILID.getBackImage());
        assertEquals(ILID.getDocType(), "il_id_bio");
        assertEquals(ILID.getIssueDate(), "2022-04-05T00:00:00.000+0000");
        assertEquals(ILID.getPlaceOfBirth(), "רוסיה");
        assertEquals(ILID.getCitizenship(), "אזרחות ישראלית");
        assertEquals(ILID.getOcrType(), "IL-ID");
        assertEquals(ILID.getBackSideIdNumber(), 345648513);
    }

    @Test(description = "Onboarding Api Test Scanning BioId card and Liveness process")
    @Description("Onboarding Api Test Scanning BioId card and Liveness process")
    public void t02_onboardingWithBioIdOldAndLiveness() throws IOException, ParseException {
        sessionId = apiResponses.createSessionId(469);
        apiResponses.initSessionNew(preProcess, 469, sessionId, System.getProperty("COMPANY_ID"));

        apiResponse.clientInitResponseNew(variables, "application/json", "caseId", preProcess.getCaseId(), "libraryName", "IL-ID", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR, sessionId, X_Session_Id, X_Request_Id, "clientTranslationFileName", clientTranslationFileValue, "devMode", devMode);


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


        apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                "Bearer " + variables.getToken(), "frame", new File("./ocr/new bioID/Gregory/newBioIDFront.jpeg"),
                "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR, sessionId, X_Session_Id, X_Request_Id);
        while (!variables.getActionType().equalsIgnoreCase("stage")) {
            apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./ocr/new bioID/Gregory/newBioIDFront.jpeg"),
                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR, sessionId, X_Session_Id, X_Request_Id);
        }

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./ocr/new bioID/Gregory/newBioIDBack.jpeg"),
                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR, sessionId, X_Session_Id, X_Request_Id);
        }


        assertEquals(variables.getActionType(), "complete");
        String ocrCaseId = variables.getCaseId();

        apiResponses.updateRequestPutCall(preProcess, 469, preProcess.getMissingFields(), ocrCaseId, "sessionId", sessionId);


        assertTrue(preProcess.isSuccess());
        assertEquals(preProcess.getErrorCode(), 0);
        assertNotNull(preProcess.getInquiryId());
        assertTrue(preProcess.isDataSuccess());
        assertEquals(preProcess.getNextProcess(), "liveness");
        assertEquals(preProcess.getDataErrorCode(), 1001);
        assertNotNull(preProcess.getSessionId());
        preProcess.setCaseId(randomString());
        variables = new Variables();


        apiResponse.clientInitResponseNew(variables, "application/json", "caseId", preProcess.getCaseId(), "libraryName", "LIVENESS", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_LIVENESS, sessionId, X_Session_Id1, X_Request_Id1, "clientTranslationFileName", clientTranslationFileValue, "devMode", devMode);


        apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                "Bearer " + variables.getToken(), "frame", new File("./liveness/Adi/adi.jpg"),
                "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS, sessionId, X_Session_Id1, X_Request_Id1);

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./liveness/Adi/adi.jpg"),
                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS, sessionId, X_Session_Id1, X_Request_Id1);
        }


        assertEquals(variables.getActionType(), "complete");
        String livenessCaseId = variables.getCaseId();

        apiResponses.updateRequestPutCall(preProcess, 469, preProcess.getMissingFields(), livenessCaseId, "sessionId", sessionId);


        assertTrue(preProcess.isSuccess());
        assertFalse(preProcess.isDataSuccess());
        assertEquals(preProcess.getNextProcess(), "process_ended");


        apiResponses.getTokenFromCallbackRequest(preProcess);

        assertTrue(preProcess.isSuccess());
        assertEquals(preProcess.getErrorCode(), 0);
        assertEquals(preProcess.getErrorCode(), 0);
        assertNotNull(preProcess.getData());
        assertNotNull(preProcess.getToken());

        utilities.endpointManager(preProcess, endpointResults, "v2");

        //Final results:
        assertTrue(endpointResults.isSuccess());
        assertEquals(endpointResults.getErrorCode(), 0);
        assertFalse(endpointResults.isDataSuccess());
        assertEquals(endpointResults.getDataErrorMessage(), "face not match");
        assertEquals(endpointResults.getDataErrorCode(), 1013);
        assertEquals(endpointResults.getMetadata(), "");

        assertEquals(ILID.getProcess(), "ocr");
        assertNotNull(ILID.getEnd());
        assertEquals(ILID.getDob(), "05.03.1992");
        assertEquals(ILID.getIssuingDate(), "05.04.2022");
        assertEquals(ILID.getExpiryDate(), "03.04.2027");
        assertEquals(ILID.getFirstName(), "גריגורי");
        assertEquals(ILID.getLastName(), "צ׳רנומורדיק");
        assertEquals(ILID.getDateOfBirth(), "1992-03-05T00:00:00.000+0000");
        assertEquals(ILID.getGender(), "זכר");
        assertEquals(ILID.getIdNumber(), 345648513);
        assertEquals(ILID.getExpirationDate(), "2027-04-03T00:00:00.000+0000");
        assertNotNull(ILID.getFaceImage());
        assertNotNull(ILID.getFrontImage());
        assertNotNull(ILID.getCardImage());
        //       assertNotNull(ILID.getBackImage());
        assertEquals(ILID.getDocType(), "il_id_bio");
        assertEquals(ILID.getIssueDate(), "2022-04-05T00:00:00.000+0000");
        assertEquals(ILID.getPlaceOfBirth(), "רוסיה");
        assertEquals(ILID.getCitizenship(), "אזרחות ישראלית");
        assertEquals(ILID.getOcrType(), "IL-ID");
        assertEquals(ILID.getBackSideIdNumber(), 345648513);


        assertEquals(Liveness.getProcess(), "liveness");
        assertNotNull(Liveness.getFaceImage());
        assertNotNull(Liveness.getVideo());
        assertTrue(Liveness.isProcessSuccess());

        assertEquals(BiometricMatch.getProcess(), "biometric_match");
        assertFalse(BiometricMatch.isProcessSuccess());
//        assertTrue(Math.abs(BiometricMatch.getScore() - 0.75) < 0.05);
        assertEquals(BiometricMatch.getScore(), 0.5006530284881592);
    }

    @Test(description = "Onboarding Api Test Scanning BlueId card and Liveness process")
    @Description("Onboarding Api Test Scanning BlueId card and Liveness process")
    public void t03_onboardingWithBlueIdAndLiveness() throws IOException, ParseException {
        sessionId = apiResponses.createSessionId(469);
        apiResponses.initSessionNew(preProcess, 469, sessionId, System.getProperty("COMPANY_ID"));

        preProcess.setCaseId(randomString());

        apiResponse.clientInitResponseNew(variables, "application/json", "caseId", preProcess.getCaseId(), "libraryName", "IL-ID", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR, sessionId, X_Session_Id, X_Request_Id, "clientTranslationFileName", clientTranslationFileValue, "devMode", devMode);

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


        apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                "Bearer " + variables.getToken(), "frame", new File("./ocr/blueID/liad/blueID.jpg"),
                "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR,sessionId, X_Session_Id, X_Request_Id);

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./ocr/blueID/liad/blueID.jpg"),
                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR,sessionId, X_Session_Id, X_Request_Id);
        }


        assertEquals(variables.getActionType(), "complete");
        String ocrCaseId = variables.getCaseId();

        apiResponses.updateRequestPutCall(preProcess, 469, preProcess.getMissingFields(), ocrCaseId, "sessionId", sessionId);


        assertTrue(preProcess.isSuccess());
        assertEquals(preProcess.getErrorCode(), 0);
        assertNotNull(preProcess.getInquiryId());
        assertTrue(preProcess.isDataSuccess());
        assertEquals(preProcess.getNextProcess(), "liveness");
        assertEquals(preProcess.getDataErrorCode(), 1001);
        assertNotNull(preProcess.getSessionId());

        preProcess.setCaseId(randomString());
        variables = new Variables();

        apiResponse.clientInitResponseNew(variables, "application/json", "caseId", preProcess.getCaseId(), "libraryName", "LIVENESS", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_LIVENESS, sessionId, X_Session_Id1, X_Request_Id1, "clientTranslationFileName", clientTranslationFileValue, "devMode", devMode);


        apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                "Bearer " + variables.getToken(), "frame", new File("./liveness/liad/liadCenterFace.jpg"),
                "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS, sessionId, X_Session_Id1, X_Request_Id1);

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./liveness/liad/liadCenterFace.jpg"),
                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS, sessionId, X_Session_Id1, X_Request_Id1);
        }


        assertEquals(variables.getActionType(), "complete");
        String livenessCaseId = variables.getCaseId();

        apiResponses.updateRequestPutCall(preProcess, 469, preProcess.getMissingFields(), livenessCaseId, "sessionId", sessionId);

        assertTrue(preProcess.isSuccess());
        assertTrue(preProcess.isDataSuccess());
        assertEquals(preProcess.getNextProcess(), "process_ended");


        apiResponses.getTokenFromCallbackRequest(preProcess);

        assertTrue(preProcess.isSuccess());
        assertEquals(preProcess.getErrorCode(), 0);
        assertEquals(preProcess.getErrorCode(), 0);
        assertNotNull(preProcess.getData());
        assertNotNull(preProcess.getToken());

        utilities.endpointManager(preProcess, endpointResults, "v2");

        //Final results:
        assertTrue(endpointResults.isSuccess());
        assertEquals(endpointResults.getErrorCode(), 0);
        assertTrue(endpointResults.isDataSuccess());
        assertEquals(endpointResults.getDataErrorMessage(), "");
        assertEquals(endpointResults.getDataErrorCode(), 0);
        assertEquals(endpointResults.getMetadata(), "");

        assertEquals(ILID.getProcess(), "ocr");
        assertNotNull(ILID.getEnd());
        assertEquals(ILID.getDob(), "17.01.1993");
        assertEquals(ILID.getIssuingDate(), "28.01.2015");
        assertEquals(ILID.getExpiryDate(), "28.01.2025");
        assertEquals(ILID.getFirstName(), "ליעד");
        assertEquals(ILID.getLastName(), "טובי");
        assertEquals(ILID.getDateOfBirth(), "1993-01-17T00:00:00.000+0000");
        assertEquals(ILID.getGender(), "זכר");
        assertEquals(ILID.getIdNumber(), 307922328);
        assertEquals(ILID.getExpirationDate(), "2025-01-28T00:00:00.000+0000");
        assertNotNull(ILID.getFaceImage());
        assertNotNull(ILID.getFrontImage());
        assertNotNull(ILID.getCardImage());
        //       assertNotNull(ILID.getBackImage());
        assertEquals(ILID.getDocType(), "blue");
        assertEquals(ILID.getIssueDate(), "2015-01-28T00:00:00.000+0000");
        assertEquals(ILID.getPlaceOfBirth(), "ישראל");
        assertEquals(ILID.getCitizenship(), "אזרחות ישראלית");
        assertEquals(ILID.getOcrType(), "IL-ID");
//        assertEquals(ILID.getBackSideIdNumber(), 200761625);


        assertEquals(Liveness.getProcess(), "liveness");
        assertNotNull(Liveness.getFaceImage());
        assertNotNull(Liveness.getVideo());
        assertTrue(Liveness.isProcessSuccess());

        assertEquals(BiometricMatch.getProcess(), "biometric_match");
        assertTrue(BiometricMatch.isProcessSuccess());
        assertTrue(Math.abs(BiometricMatch.getScore() - 0.83) < 0.05);
//        assertEquals(BiometricMatch.getScore(), 0.7344000339508057);
    }


    @Test(description = "Onboarding Api Test Scanning GreenId card and Liveness process", retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Onboarding Api Test Scanning GreenId card and Liveness process")
    public void t04_onboardingWithGreenIdAndLiveness() throws IOException, ParseException {
        sessionId = apiResponses.createSessionId(469);
        apiResponses.initSessionNew(preProcess, 469, sessionId, System.getProperty("COMPANY_ID"));
        preProcess.setCaseId(randomString());

//        apiResponses.updateRequestPutCall(preProcess, 26, preProcess.getMissingFields(), ocrCaseId, "sessionId", sessionId);

        apiResponse.clientInitResponseNew(variables, "application/json", "caseId", preProcess.getCaseId(), "libraryName", "IL-ID", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR, sessionId, X_Session_Id, X_Request_Id, "clientTranslationFileName", clientTranslationFileValue, "devMode", devMode);

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


        apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                "Bearer " + variables.getToken(), "frame", new File("./ocr/greenID/bar/BarGreenID.jpg"),
                "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR,sessionId, X_Session_Id, X_Request_Id);

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./ocr/greenID/bar/BarGreenID.jpg"),
                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR,sessionId, X_Session_Id, X_Request_Id);
        }


        assertEquals(variables.getActionType(), "complete");
        String ocrCaseId = variables.getCaseId();

        apiResponses.updateRequestPutCall(preProcess, 469, preProcess.getMissingFields(), ocrCaseId, "sessionId", sessionId);


        assertTrue(preProcess.isSuccess());
        assertEquals(preProcess.getErrorCode(), 0);
        assertNotNull(preProcess.getInquiryId());
        assertTrue(preProcess.isDataSuccess());
        assertEquals(preProcess.getNextProcess(), "liveness");
        assertEquals(preProcess.getDataErrorCode(), 1001);
        assertNotNull(preProcess.getSessionId());

        preProcess.setCaseId(randomString());
        variables = new Variables();

        apiResponse.clientInitResponseNew(variables, "application/json", "caseId", preProcess.getCaseId(), "libraryName", "LIVENESS", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_LIVENESS, sessionId, X_Session_Id1, X_Request_Id1, "clientTranslationFileName", clientTranslationFileValue, "devMode", devMode);

        apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                "Bearer " + variables.getToken(), "frame", new File("./liveness/bar/bar_new.jpg"),
                "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS,sessionId, X_Session_Id1, X_Request_Id1);

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./liveness/bar/bar_new.jpg"),
                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS, sessionId, X_Session_Id1, X_Request_Id1);
        }


        assertEquals(variables.getActionType(), "complete");
        String livenessCaseId = variables.getCaseId();

        apiResponses.updateRequestPutCall(preProcess, 469, preProcess.getMissingFields(), livenessCaseId, "sessionId", sessionId);

        assertTrue(preProcess.isSuccess());
        assertTrue(preProcess.isDataSuccess());
        assertEquals(preProcess.getNextProcess(), "process_ended");


        apiResponses.getTokenFromCallbackRequest(preProcess);

        assertTrue(preProcess.isSuccess());
        assertEquals(preProcess.getErrorCode(), 0);
        assertEquals(preProcess.getErrorCode(), 0);
        assertNotNull(preProcess.getData());
        assertNotNull(preProcess.getToken());

        utilities.endpointManager(preProcess, endpointResults, "v2");

        //Final results:
        assertTrue(endpointResults.isSuccess());
        assertEquals(endpointResults.getErrorCode(), 0);
        assertTrue(endpointResults.isDataSuccess());
        assertEquals(endpointResults.getDataErrorMessage(), "");
        assertEquals(endpointResults.getDataErrorCode(), 0);
        assertEquals(endpointResults.getMetadata(), "");

        assertEquals(ILID.getProcess(), "ocr");
        assertNotNull(ILID.getEnd());
        assertEquals(ILID.getDob(), "19.02.1994");
        assertEquals(ILID.getIssuingDate(), "03.05.2010");
        assertEquals(ILID.getFirstName(), "בר");
        assertEquals(ILID.getLastName(), "זמסקי");
        assertEquals(ILID.getDateOfBirth(), "1994-02-19T00:00:00.000+0000");
        assertEquals(ILID.getGender(), "נקבה");
        assertEquals(ILID.getIdNumber(), 312534753);
        assertNotNull(ILID.getFaceImage());
        assertNotNull(ILID.getFrontImage());
        assertNotNull(ILID.getCardImage());
        //       assertNotNull(ILID.getBackImage());
        assertEquals(ILID.getDocType(), "green");
        assertEquals(ILID.getIssueDate(), "2010-05-03T00:00:00.000+0000");
        assertEquals(ILID.getPlaceOfBirth(), "ישראל");
        assertEquals(ILID.getOcrType(), "IL-ID");
//        assertEquals(ILID.getBackSideIdNumber(), 200761625);


        assertEquals(Liveness.getProcess(), "liveness");
        assertNotNull(Liveness.getFaceImage());
        assertNotNull(Liveness.getVideo());
        assertTrue(Liveness.isProcessSuccess());

        assertEquals(BiometricMatch.getProcess(), "biometric_match");
        assertTrue(BiometricMatch.isProcessSuccess());
        assertTrue(Math.abs(BiometricMatch.getScore() - 0.7) < 0.05);
//        assertEquals(BiometricMatch.getScore(), 0.7017582058906555);
    }

    @Test(description = "Onboarding Api Test Scanning ILDL card and Liveness process")
    @Description("Onboarding Api Test Scanning ILDL card and Liveness process")
    public void t05_onboardingWithILDLNewAndLiveness() throws IOException, ParseException {
        sessionId = apiResponses.createSessionId(1258);
        apiResponses.initSessionNew(preProcess, 1258, sessionId, System.getProperty("COMPANY_ID"));
        preProcess.setCaseId(randomString());


        apiResponse.clientInitResponseNew(variables, "application/json", "caseId", preProcess.getCaseId(), "libraryName", "IL-ID", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR, sessionId, X_Session_Id, X_Request_Id, "clientTranslationFileName", clientTranslationFileValue, "devMode", devMode);

        assertTrue(preProcess.isSuccess());
        assertEquals(preProcess.getErrorCode(), 0);
        assertEquals(preProcess.getDataId(), "process-88c1f56e-0cdc-4873-8210-111a0ea82872");
        assertNotNull(preProcess.getInquiryId());
        assertTrue(preProcess.isDataSuccess());
        assertEquals(preProcess.getNextProcess(), "server_ocr");
        assertEquals((preProcess.getMissingFields()).substring(0, 9), "ocrCaseId");
        assertEquals(preProcess.getDataErrorCode(), 1001);
        assertNotNull(preProcess.getSessionId());
        assertNotNull(preProcess.getOcrTypes());
        assertFalse(preProcess.isBackStepAllow());


        apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                "Bearer " + variables.getToken(), "frame", new File("./ocr/dl/newDLFront.jpg"),
                "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR,sessionId, X_Session_Id, X_Request_Id);
        while (!variables.getActionType().equalsIgnoreCase("stage")) {
            apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./ocr/dl/newDLFront.jpg"),
                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR,sessionId, X_Session_Id, X_Request_Id);
        }

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./ocr/dl/newDLBack.jpg"),
                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR,sessionId, X_Session_Id, X_Request_Id);
        }


        assertEquals(variables.getActionType(), "complete");
        String ocrCaseId = variables.getCaseId();

        apiResponses.updateRequestPutCall(preProcess, 1258, preProcess.getMissingFields(), ocrCaseId, "sessionId", sessionId);


        assertTrue(preProcess.isSuccess());
        assertTrue(preProcess.isDataSuccess());
        assertEquals(preProcess.getNextProcess(), "process_ended");


        apiResponses.getTokenFromCallbackRequest(preProcess);

        assertTrue(preProcess.isSuccess());
        assertEquals(preProcess.getErrorCode(), 0);
        assertEquals(preProcess.getErrorCode(), 0);
        assertNotNull(preProcess.getData());
        assertNotNull(preProcess.getToken());

        utilities.endpointManager(preProcess, endpointResults, "v2");


        //Final results:
        assertTrue(endpointResults.isSuccess());
        assertEquals(endpointResults.getErrorCode(), 0);
        assertTrue(endpointResults.isDataSuccess());
        assertEquals(endpointResults.getDataErrorMessage(), "");
        assertEquals(endpointResults.getDataErrorCode(), 0);
        assertEquals(endpointResults.getMetadata(), "");

        assertEquals(ILDL.getProcess(), "ocr");
        assertNotNull(ILDL.getEnd());
        assertEquals(ILDL.getDob(), "30.06.1987");
        assertEquals(ILDL.getIssuingDate(), "10.08.2018");
        assertEquals(ILDL.getExpiryDate(), "30.06.2028");
        assertEquals(ILDL.getFirstName(), "הראל");
        assertEquals(ILDL.getLastName(), "אלעזר");
        assertEquals(ILDL.getDateOfBirth(), "1987-06-30T00:00:00.000+0000");
        assertEquals(ILDL.getAddress(), "שפירא משה ח ם 13 אשדוד");
        assertEquals(ILDL.getIdNumber(), 300864550);
        assertEquals(ILDL.getDocumentNumber(), 9503218);
        assertEquals(ILDL.getExpirationDate(), "2028-06-30T00:00:00.000+0000");
        assertNotNull(ILDL.getFaceImage());
        assertNotNull(ILDL.getFrontImage());
        assertNotNull(ILDL.getCardImage());
        assertNotNull(ILDL.getBackImage());
        assertEquals(ILDL.getDocType(), "new_back");
        assertEquals(ILDL.getIssueDate(), "2018-08-10T00:00:00.000+0000");
        assertEquals(ILDL.getLicenseCategory(), "B");
        assertEquals(ILDL.getLicenseIssuingYear(), "2012-01-01T00:00:00.000+0000");
        assertEquals(ILDL.getLicenseIssueYear(), 2012);
        assertEquals(ILDL.getOcrType(), "IL-DL");
        assertEquals(ILDL.getBackSideIdNumber(), 300864550);
        assertTrue(ILDL.isFacePosition());
        assertTrue(ILDL.isValidityExpiryDate());
        assertTrue(ILDL.isChecksum());
        assertTrue(ILDL.isFaceSize());
        assertTrue(ILDL.isTemplateMatching());
        assertTrue(ILDL.isFaceRotation());
        assertTrue(ILDL.isProcessSuccess());
        assertEquals(ILDL.getCount(), 1);
    }

    @Test(description = "Onboarding Api Test Scanning old ILDL card and Liveness process", retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Onboarding Api Test Scanning old ILDL card and Liveness process")
    public void t06_onboardingWithILDLOldAndLiveness() throws IOException, ParseException {

        sessionId = apiResponses.createSessionId(469);
        apiResponses.initSessionNew(preProcess, 469, sessionId, System.getProperty("COMPANY_ID"));
        preProcess.setCaseId(randomString());


        apiResponse.clientInitResponseNew(variables, "application/json", "caseId", preProcess.getCaseId(), "libraryName", "IL-DL", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR, sessionId, X_Session_Id, X_Request_Id, "clientTranslationFileName", clientTranslationFileValue, "devMode", devMode);


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


        apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                "Bearer " + variables.getToken(), "frame", new File("./ocr/dl/LiadDLFront.jpg"),
                "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR,sessionId, X_Session_Id, X_Request_Id);
        while (!variables.getActionType().equalsIgnoreCase("stage")) {
            apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./ocr/dl/LiadDLFront.jpg"),
                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR,sessionId, X_Session_Id, X_Request_Id);
        }

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./ocr/dl/LiadDLBack.jpg"),
                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR,sessionId, X_Session_Id, X_Request_Id);
        }


        assertEquals(variables.getActionType(), "complete");
        String ocrCaseId = variables.getCaseId();

        apiResponses.updateRequestPutCall(preProcess, 469, preProcess.getMissingFields(), ocrCaseId, "sessionId", sessionId);


        assertTrue(preProcess.isSuccess());
        assertEquals(preProcess.getErrorCode(), 0);
        assertNotNull(preProcess.getInquiryId());
        assertTrue(preProcess.isDataSuccess());
        assertEquals(preProcess.getNextProcess(), "liveness");
        assertEquals(preProcess.getDataErrorCode(), 1001);
        assertNotNull(preProcess.getSessionId());

        preProcess.setCaseId(randomString());
        variables = new Variables();


        apiResponse.clientInitResponseNew(variables, "application/json", "caseId", preProcess.getCaseId(), "libraryName", "LIVENESS", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_LIVENESS, sessionId, X_Session_Id1, X_Request_Id1, "clientTranslationFileName", clientTranslationFileValue, "devMode", devMode);

        apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                "Bearer " + variables.getToken(), "frame", new File("./liveness/liad/liadCenterFace.jpg"),
                "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS,sessionId, X_Session_Id1, X_Request_Id1);

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./liveness/liad/liadCenterFace.jpg"),
                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS,sessionId,X_Session_Id1, X_Request_Id1);
        }


        assertEquals(variables.getActionType(), "complete");
        String livenessCaseId = variables.getCaseId();

        apiResponses.updateRequestPutCall(preProcess, 469, preProcess.getMissingFields(), livenessCaseId, "sessionId", sessionId);

        assertTrue(preProcess.isSuccess());
        assertTrue(preProcess.isDataSuccess());
        assertEquals(preProcess.getNextProcess(), "process_ended");


        apiResponses.getTokenFromCallbackRequest(preProcess);

        assertTrue(preProcess.isSuccess());
        assertEquals(preProcess.getErrorCode(), 0);
        assertEquals(preProcess.getErrorCode(), 0);
        assertNotNull(preProcess.getData());
        assertNotNull(preProcess.getToken());

        utilities.endpointManager(preProcess, endpointResults, "v2");


        //Final results:
        assertTrue(endpointResults.isSuccess());
        assertEquals(endpointResults.getErrorCode(), 0);
        assertTrue(endpointResults.isDataSuccess());
        assertEquals(endpointResults.getDataErrorMessage(), "");
        assertEquals(endpointResults.getDataErrorCode(), 0);
        assertEquals(endpointResults.getMetadata(), "");

        assertEquals(ILDL.getProcess(), "ocr");
        assertNotNull(ILDL.getEnd());
        assertEquals(ILDL.getDob(), "17.01.1993");
        assertEquals(ILDL.getIssuingDate(), "15.12.2016");
        assertEquals(ILDL.getExpiryDate(), "17.01.2027");
        assertEquals(ILDL.getFirstName(), "ליעד");
        assertEquals(ILDL.getLastName(), "טובי");
        assertEquals(ILDL.getDateOfBirth(), "1993-01-17T00:00:00.000+0000");
        assertEquals(ILDL.getAddress(), "קדם 33ב שוהם");
        assertEquals(ILDL.getIdNumber(), 307922328);
        assertEquals(ILDL.getDocumentNumber(), 9254737);
        assertEquals(ILDL.getExpirationDate(), "2027-01-17T00:00:00.000+0000");
        assertNotNull(ILDL.getFaceImage());
        assertNotNull(ILDL.getFrontImage());
        assertNotNull(ILDL.getCardImage());
        assertNotNull(ILDL.getBackImage());
        assertEquals(ILDL.getDocType(), "old_back");
        assertEquals(ILDL.getIssueDate(), "2016-12-15T00:00:00.000+0000");
        assertEquals(ILDL.getLicenseCategory(), "B");
        assertEquals(ILDL.getLicenseIssuingYear(), "2010-01-01T00:00:00.000+0000");
        assertEquals(ILDL.getLicenseIssueYear(), 2010);
        assertEquals(ILDL.getOcrType(), "IL-DL");
        assertEquals(ILDL.getBackSideIdNumber(), 307922328);
        assertTrue(ILDL.isFacePosition());
        assertTrue(ILDL.isValidityExpiryDate());
        assertTrue(ILDL.isChecksum());
        assertTrue(ILDL.isFaceSize());
        assertTrue(ILDL.isTemplateMatching());
        assertTrue(ILDL.isFaceRotation());
        assertTrue(ILDL.isProcessSuccess());
        assertEquals(ILDL.getCount(), 1);

        assertEquals(Liveness.getProcess(), "liveness");
        assertNotNull(Liveness.getFaceImage());
        assertNotNull(Liveness.getVideo());
        assertTrue(Liveness.isProcessSuccess());

        assertEquals(BiometricMatch.getProcess(), "biometric_match");
        assertTrue(BiometricMatch.isProcessSuccess());
        assertTrue(BiometricMatch.getScore() > 0.6);
    }


    @Test(description = "Onboarding Api Test Scanning MRZ card and Liveness process")
    @Description("Onboarding Api Test Scanning MRZ card and Liveness process")
    public void t07_onboardingWithMRZNewAndLiveness() throws IOException, ParseException {
        sessionId = apiResponses.createSessionId(469);
        apiResponses.initSessionNew(preProcess, 469, sessionId, System.getProperty("COMPANY_ID"));
        preProcess.setCaseId(randomString());


        apiResponse.clientInitResponseNew(variables, "application/json", "caseId", preProcess.getCaseId(), "libraryName", "MRZ", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR, sessionId, X_Session_Id, X_Request_Id, "clientTranslationFileName", clientTranslationFileValue, "devMode", devMode);


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



        apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                "Bearer " + variables.getToken(), "frame", new File("./ocr/mrz/avner/avner2.jpg"),
                "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR,sessionId, X_Session_Id, X_Request_Id);
        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./ocr/mrz/avner/avner2.jpg"),
                    "image/jpg", "message_type", "frame_request",apiResponse.CLIENT_REQUEST_OCR,sessionId, X_Session_Id, X_Request_Id);
        }


        assertEquals(variables.getActionType(), "complete");
        String ocrCaseId = variables.getCaseId();

        apiResponses.updateRequestPutCall(preProcess, 469, preProcess.getMissingFields(), ocrCaseId, "sessionId", sessionId);


        assertTrue(preProcess.isSuccess());
        assertEquals(preProcess.getErrorCode(), 0);
        assertNotNull(preProcess.getInquiryId());
        assertTrue(preProcess.isDataSuccess());
        assertEquals(preProcess.getNextProcess(), "liveness");
        assertEquals(preProcess.getDataErrorCode(), 1001);
        assertNotNull(preProcess.getSessionId());

        preProcess.setCaseId(randomString());
        variables = new Variables();



        apiResponse.clientInitResponseNew(variables, "application/json", "caseId", preProcess.getCaseId(), "libraryName", "LIVENESS", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_LIVENESS, sessionId, X_Session_Id1, X_Request_Id1, "clientTranslationFileName", clientTranslationFileValue, "devMode", devMode);


        apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                "Bearer " + variables.getToken(), "frame", new File("./liveness/avner/avnerCenter.jpg"),
                "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS,sessionId,X_Session_Id1, X_Request_Id1);

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./liveness/avner/avnerCenter.jpg"),
                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS,sessionId,X_Session_Id1, X_Request_Id1);
        }


        assertEquals(variables.getActionType(), "complete");
        String livenessCaseId = variables.getCaseId();

        apiResponses.updateRequestPutCall(preProcess, 469, preProcess.getMissingFields(), livenessCaseId, "sessionId", sessionId);

        assertTrue(preProcess.isSuccess());
        assertTrue(preProcess.isDataSuccess());
        assertEquals(preProcess.getNextProcess(), "process_ended");


        apiResponses.getTokenFromCallbackRequest(preProcess);

        assertTrue(preProcess.isSuccess());
        assertEquals(preProcess.getErrorCode(), 0);
        assertEquals(preProcess.getErrorCode(), 0);
        assertNotNull(preProcess.getData());
        assertNotNull(preProcess.getToken());

        utilities.endpointManager(preProcess, endpointResults, "v2");


        //Final results:
        assertTrue(endpointResults.isSuccess());
        assertEquals(endpointResults.getErrorCode(), 0);
        assertTrue(endpointResults.isDataSuccess());
        assertEquals(endpointResults.getDataErrorMessage(), "");
        assertEquals(endpointResults.getDataErrorCode(), 0);
        assertEquals(endpointResults.getMetadata(), "");

        assertEquals(MRZ.getProcess(), "ocr");
        assertNotNull(MRZ.getEnd());
        assertEquals(MRZ.getDob(), "02.08.1980");
        assertEquals(MRZ.getExpiryDate(), "10.06.2024");
        assertEquals(MRZ.getFirstName(), "AVNER");
        assertEquals(MRZ.getLastName(), "BLUER");
        assertEquals(MRZ.getDateOfBirth(), "1980-08-02T00:00:00.000+0000");
        assertEquals(MRZ.getNationalityName(), "Israel");
        assertEquals(MRZ.getNationalityAlpha2(), "IL");
        assertEquals(MRZ.getNationalityAlpha3(), "ISR");
        assertEquals(MRZ.getGender(), "Male");
        assertEquals(MRZ.getIdNumber(), "0-4037370-6");
        assertEquals(MRZ.getDocumentNumber(), 30266026);
        assertEquals(MRZ.getExpirationDate(), "2024-06-10T00:00:00.000+0000");
        assertEquals(MRZ.getIssuingCountryName(), "Israel");
        assertEquals(MRZ.getIssuingCountryAlpha2(), "IL");
        assertEquals(MRZ.getIssuingCountryAlpha3(), "ISR");
        assertEquals(MRZ.getCountryCode(), "IL");
        assertNotNull(MRZ.getFaceImage());
        //assertNotNull(MRZ.getFrontImage());
        assertNotNull(MRZ.getCardImage());
        assertEquals(MRZ.getDocType(), "israel_passport");
        assertEquals(MRZ.getOcrType(), "MRZ");
        assertTrue(MRZ.isValidityExpiryDate());
        assertTrue(MRZ.isChecksum());
        assertTrue(MRZ.isTemplateMatching());
        assertFalse(MRZ.isFacePosition());
        assertTrue(MRZ.isFaceRotation());
        assertFalse(MRZ.isFaceSize());
        assertTrue(MRZ.isDocumentInFrame());
        assertTrue(MRZ.isProcessSuccess());
        assertEquals(MRZ.getCount(), 1);

        assertEquals(Liveness.getProcess(), "liveness");
        assertNotNull(Liveness.getFaceImage());
        assertNotNull(Liveness.getVideo());
        assertTrue(Liveness.isProcessSuccess());

        assertEquals(BiometricMatch.getProcess(), "biometric_match");
        assertTrue(BiometricMatch.isProcessSuccess());
        assertTrue(Math.abs(BiometricMatch.getScore() - 0.8) < 0.05);
    }


    @Test(description = "Onboarding Api Test Liveness process", retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Onboarding Api Test Liveness process")
    public void t08_onboardingWithLivenessOnly() throws IOException, ParseException {

        sessionId = apiResponses.createSessionId(231);
        apiResponses.initSessionNew(preProcess, 231, sessionId, System.getProperty("COMPANY_ID"));
        preProcess.setCaseId(randomString());

        assertTrue(preProcess.isSuccess());
        assertEquals(preProcess.getErrorCode(), 0);
        assertEquals(preProcess.getDataId(), "process-7f7a91f2-1d4a-463b-a2d5-a399d8c264c6");
        assertNotNull(preProcess.getInquiryId());
        assertTrue(preProcess.isDataSuccess());
        assertEquals(preProcess.getNextProcess(), "liveness");
        assertEquals(preProcess.getMissingFields(), "livenessCaseId");
        assertEquals(preProcess.getDataErrorCode(), 1001);
        assertNotNull(preProcess.getSessionId());
        assertNotNull(preProcess.getOcrTypes());
        assertFalse(preProcess.isBackStepAllow());

        preProcess.setCaseId(randomString());
        variables = new Variables();


        apiResponse.clientInitResponseNew(variables, "application/json", "caseId", preProcess.getCaseId(), "libraryName", "LIVENESS", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_LIVENESS, sessionId, X_Session_Id1, X_Request_Id1, "clientTranslationFileName", clientTranslationFileValue, "devMode", devMode);

        apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                "Bearer " + variables.getToken(), "frame", new File("./liveness/avner/avnerCenter.jpg"),
                "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS,sessionId,X_Session_Id1, X_Request_Id1);

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./liveness/avner/avnerCenter.jpg"),
                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS,sessionId,X_Session_Id1, X_Request_Id1);
        }


        assertEquals(variables.getActionType(), "complete");
        String livenessCaseId = variables.getCaseId();

        apiResponses.updateRequestPutCall(preProcess, 231, preProcess.getMissingFields(), livenessCaseId, "sessionId", sessionId);

        assertTrue(preProcess.isSuccess());
        assertTrue(preProcess.isDataSuccess());
        assertEquals(preProcess.getNextProcess(), "process_ended");


        apiResponses.getTokenFromCallbackRequest(preProcess);

        assertTrue(preProcess.isSuccess());
        assertEquals(preProcess.getErrorCode(), 0);
        assertEquals(preProcess.getErrorCode(), 0);
        assertNotNull(preProcess.getData());
        assertNotNull(preProcess.getToken());

        utilities.endpointManager(preProcess, endpointResults, "v2");

        //Final results:
        assertTrue(endpointResults.isSuccess());
        assertEquals(endpointResults.getErrorCode(), 0);
        assertTrue(endpointResults.isDataSuccess());
        assertEquals(endpointResults.getDataErrorMessage(), "");
        assertEquals(endpointResults.getDataErrorCode(), 0);
        assertEquals(endpointResults.getMetadata(), "");

        assertEquals(Liveness.getProcess(), "liveness");
        assertNotNull(Liveness.getFaceImage());
        assertNotNull(Liveness.getVideo());
        assertTrue(Liveness.isProcessSuccess());
    }


    @Test(description = "Onboarding Api Test OCR process")
    @Description("Onboarding Api Test OCR process")
    public void t10_onboardingWithOcrOnly() throws IOException, ParseException {
        sessionId = apiResponses.createSessionId(324);
        apiResponses.initSessionNew(preProcess, 324, sessionId, System.getProperty("COMPANY_ID"));
        preProcess.setCaseId(randomString());


        apiResponse.clientInitResponseNew(variables, "application/json", "caseId", preProcess.getCaseId(), "libraryName", "IL-ID", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR, sessionId, X_Session_Id, X_Request_Id, "clientTranslationFileName", clientTranslationFileValue, "devMode", devMode);

        assertTrue(preProcess.isSuccess());
        assertEquals(preProcess.getErrorCode(), 0);
        assertEquals(preProcess.getDataId(), "process-c6aa3851-8e6a-4752-b5f2-bfcc02c9439e");
        assertNotNull(preProcess.getInquiryId());
        assertTrue(preProcess.isDataSuccess());
        assertEquals(preProcess.getNextProcess(), "server_ocr");
        assertEquals((preProcess.getMissingFields()).substring(0, 9), "ocrCaseId");
        assertEquals(preProcess.getDataErrorCode(), 1001);
        assertNotNull(preProcess.getSessionId());
        assertNotNull(preProcess.getOcrTypes());
        assertFalse(preProcess.isBackStepAllow());



        apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                "Bearer " + variables.getToken(), "frame", new File("./ocr/greenID/bar/BarGreenID.jpg"),
                "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR,sessionId, X_Session_Id, X_Request_Id);

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./ocr/greenID/bar/BarGreenID.jpg"),
                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR,sessionId, X_Session_Id, X_Request_Id);
        }


        assertEquals(variables.getActionType(), "complete");
        String ocrCaseId = variables.getCaseId();


        apiResponses.updateRequestPutCall(preProcess, 324, preProcess.getMissingFields(),ocrCaseId, "sessionId", sessionId);

        assertTrue(preProcess.isSuccess());
        assertTrue(preProcess.isDataSuccess());
        assertEquals(preProcess.getNextProcess(), "process_ended");


        apiResponses.getTokenFromCallbackRequest(preProcess);

        assertTrue(preProcess.isSuccess());
        assertEquals(preProcess.getErrorCode(), 0);
        assertEquals(preProcess.getErrorCode(), 0);
        assertNotNull(preProcess.getData());
        assertNotNull(preProcess.getToken());

        utilities.endpointManager(preProcess, endpointResults, "v2");

        //Final results:
        assertTrue(endpointResults.isSuccess());
        assertEquals(endpointResults.getErrorCode(), 0);
        assertTrue(endpointResults.isDataSuccess());
        assertEquals(endpointResults.getDataErrorMessage(), "");
        assertEquals(endpointResults.getDataErrorCode(), 0);
        assertEquals(endpointResults.getMetadata(), "");

        assertEquals(ILID.getProcess(), "ocr");
        assertNotNull(ILID.getEnd());
        assertEquals(ILID.getDob(), "19.02.1994");
        assertEquals(ILID.getIssuingDate(), "03.05.2010");
        assertEquals(ILID.getFirstName(), "בר");
        assertEquals(ILID.getLastName(), "זמסקי");
        assertEquals(ILID.getDateOfBirth(), "1994-02-19T00:00:00.000+0000");
        assertEquals(ILID.getGender(), "נקבה");
        assertEquals(ILID.getIdNumber(), 312534753);
        assertNotNull(ILID.getFaceImage());
        assertNotNull(ILID.getFrontImage());
        assertNotNull(ILID.getCardImage());
        //       assertNotNull(ILID.getBackImage());
        assertEquals(ILID.getDocType(), "green");
        assertEquals(ILID.getIssueDate(), "2010-05-03T00:00:00.000+0000");
        assertEquals(ILID.getPlaceOfBirth(), "ישראל");
        assertEquals(ILID.getOcrType(), "IL-ID");
//        assertEquals(ILID.getBackSideIdNumber(), 200761625);
    }

    @Test(description = "Onboarding Api Test OCR with Date Diff task")
    @Description("Onboarding Api Test OCR with Date Diff task")
    public void t11_onboardingWithDateIf() throws IOException, ParseException {
        sessionId = apiResponses.createSessionId(144);
        apiResponses.initSessionNew(preProcess, 144, sessionId, System.getProperty("COMPANY_ID"));
        preProcess.setCaseId(randomString());


        apiResponse.clientInitResponseNew(variables, "application/json", "caseId", preProcess.getCaseId(), "libraryName", "IL-DL", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR, sessionId, X_Session_Id, X_Request_Id, "clientTranslationFileName", clientTranslationFileValue, "devMode", devMode);


        assertTrue(preProcess.isSuccess());
        assertEquals(preProcess.getErrorCode(), 0);
        assertEquals(preProcess.getDataId(), "process-e0e791a1-2708-4506-9fab-e5842e204136");
        assertNotNull(preProcess.getInquiryId());
        assertTrue(preProcess.isDataSuccess());
        assertEquals(preProcess.getNextProcess(), "server_ocr");
        assertEquals((preProcess.getMissingFields()).substring(0, 9), "ocrCaseId");
        assertEquals(preProcess.getDataErrorCode(), 1001);
        assertNotNull(preProcess.getSessionId());
        assertFalse(preProcess.isBackStepAllow());




        apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                "Bearer " + variables.getToken(), "frame", new File("./ocr/dl/harel_dl_front.jpg"),
                "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR,sessionId, X_Session_Id, X_Request_Id);
        while (!variables.getActionType().equalsIgnoreCase("stage")) {
            apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./ocr/dl/harel_dl_front.jpg"),
                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR,sessionId, X_Session_Id, X_Request_Id);
        }

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./ocr/dl/harel_dl_back.jpg"),
                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR,sessionId, X_Session_Id, X_Request_Id);
        }

        assertEquals(variables.getActionType(), "complete");
        String ocrCaseId = variables.getCaseId();

        apiResponses.updateRequestPutCall(preProcess, 144, preProcess.getMissingFields(), ocrCaseId, "sessionId", sessionId);

        assertTrue(preProcess.isSuccess());
        assertEquals(preProcess.getErrorCode(), 0);
        assertNotNull(preProcess.getInquiryId());
        assertFalse(preProcess.isDataSuccess());
        assertEquals(preProcess.getNextProcess(), "process_ended");
        assertEquals(preProcess.getDataErrorCode(), 23);
        assertEquals(preProcess.getDataErrorMessage(), "under age");
        assertNotNull(preProcess.getSessionId());

        apiResponses.getTokenFromCallbackRequest(preProcess);

        assertTrue(preProcess.isSuccess());
        assertEquals(preProcess.getErrorCode(), 0);
        assertNotNull(preProcess.getData());
        assertNotNull(preProcess.getToken());

        utilities.endpointManager(preProcess, endpointResults, "v2");

        //Final results:
        assertTrue(endpointResults.isSuccess());
        assertEquals(endpointResults.getErrorCode(), 0);
        assertFalse(endpointResults.isDataSuccess());
        assertEquals(endpointResults.getDataErrorMessage(), "under age");
        assertEquals(endpointResults.getDataErrorCode(), 23);
        assertEquals(endpointResults.getMetadata(), "");

        assertEquals(ILDL.getProcess(), "ocr");
        assertNotNull(ILDL.getEnd());
        assertEquals(ILDL.getDob(), "30.06.1987");
        assertEquals(ILDL.getIssuingDate(), "10.08.2018");
        assertEquals(ILDL.getExpiryDate(), "30.06.2028");
        assertEquals(ILDL.getFirstName(), "הראל");
        assertEquals(ILDL.getLastName(), "אלעזר");
        assertEquals(ILDL.getDateOfBirth(), "1987-06-30T00:00:00.000+0000");
        assertEquals(ILDL.getAddress(), "שפירא משה ח ם 3 ו אשדוד");
        assertEquals(ILDL.getIdNumber(), 300864550);
        assertEquals(ILDL.getDocumentNumber(), 9503218);
        assertEquals(ILDL.getExpirationDate(), "2028-06-30T00:00:00.000+0000");
        assertNotNull(ILDL.getFaceImage());
        assertNotNull(ILDL.getFrontImage());
        assertNotNull(ILDL.getCardImage());
        assertNotNull(ILDL.getBackImage());
        assertEquals(ILDL.getDocType(), "new_back");
        assertEquals(ILDL.getIssueDate(), "2018-08-10T00:00:00.000+0000");
        assertEquals(ILDL.getLicenseCategory(), "B");
        assertEquals(ILDL.getLicenseIssuingYear(), "2012-01-01T00:00:00.000+0000");
        assertEquals(ILDL.getLicenseIssueYear(), 2012);
        assertEquals(ILDL.getOcrType(), "IL-DL");
        assertEquals(ILDL.getBackSideIdNumber(), 300864550);
        assertTrue(ILDL.isFacePosition());
        assertTrue(ILDL.isValidityExpiryDate());
        assertTrue(ILDL.isChecksum());
        assertTrue(ILDL.isFaceSize());
        assertTrue(ILDL.isTemplateMatching());
        assertTrue(ILDL.isFaceRotation());
        assertTrue(ILDL.isProcessSuccess());
        assertEquals(ILDL.getCount(), 1);
    }

    @Test(description = "Onboarding Api Test OCR with Comparator task")
    @Description("Onboarding Api Test OCR with Comparator task")
    public void t12_onboardingWithComparator() throws IOException, ParseException {
        sessionId = apiResponses.createSessionId(617);
        apiResponses.initSessionNew(preProcess, 617, sessionId, System.getProperty("COMPANY_ID"));
        preProcess.setCaseId(randomString());


        apiResponse.clientInitResponseNew(variables, "application/json", "caseId", preProcess.getCaseId(), "libraryName", "IL-DL", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR, sessionId, X_Session_Id, X_Request_Id, "clientTranslationFileName", clientTranslationFileValue, "devMode", devMode);


        assertTrue(preProcess.isSuccess());
        assertEquals(preProcess.getErrorCode(), 0);
        assertEquals(preProcess.getDataId(), "process-917fd50c-18ac-4e78-a175-3861b42b5fcb");
        assertNotNull(preProcess.getInquiryId());
        assertTrue(preProcess.isDataSuccess());
        assertEquals(preProcess.getNextProcess(), "server_ocr");
        assertEquals((preProcess.getMissingFields()).substring(0, 9), "ocrCaseId");
        assertEquals(preProcess.getDataErrorCode(), 1001);
        assertNotNull(preProcess.getSessionId());
        assertFalse(preProcess.isBackStepAllow());




        apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                "Bearer " + variables.getToken(), "frame", new File("./ocr/dl/harel_dl_front.jpg"),
                "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR,sessionId, X_Session_Id, X_Request_Id);
        while (!variables.getActionType().equalsIgnoreCase("stage")) {
            apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./ocr/dl/harel_dl_front.jpg"),
                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR,sessionId, X_Session_Id, X_Request_Id);
        }

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./ocr/dl/newDLBack.jpg"),
                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR,sessionId, X_Session_Id, X_Request_Id);
        }

        assertEquals(variables.getActionType(), "complete");
        String ocrCaseId = variables.getCaseId();

        apiResponses.updateRequestPutCall(preProcess, 617, preProcess.getMissingFields(), ocrCaseId, "sessionId", sessionId);

        assertTrue(preProcess.isSuccess());
        assertEquals(preProcess.getErrorCode(), 0);
        assertNotNull(preProcess.getInquiryId());
        assertFalse(preProcess.isDataSuccess());
        assertEquals(preProcess.getNextProcess(), "process_ended");
        assertEquals(preProcess.getDataErrorCode(), 5858);
        assertEquals(preProcess.getDataErrorMessage(), "back side number doesn't equals to front side id number ");
        assertNotNull(preProcess.getSessionId());

        apiResponses.getTokenFromCallbackRequest(preProcess);

        assertTrue(preProcess.isSuccess());
        assertEquals(preProcess.getErrorCode(), 0);
        assertNotNull(preProcess.getData());
        assertNotNull(preProcess.getToken());

        utilities.endpointManager(preProcess, endpointResults, "v2");

        //Final results:
        assertTrue(endpointResults.isSuccess());
        assertEquals(endpointResults.getErrorCode(), 0);
        assertFalse(endpointResults.isDataSuccess());
        assertEquals(endpointResults.getDataErrorMessage(), "back side number doesn't equals to front side id number ");
        assertEquals(endpointResults.getDataErrorCode(), 5858);
        assertEquals(endpointResults.getMetadata(), "");

        assertEquals(ILDL.getProcess(), "ocr");
        assertNotNull(ILDL.getEnd());
        assertEquals(ILDL.getDob(), "30.06.1987");
        assertEquals(ILDL.getIssuingDate(), "10.08.2018");
        assertEquals(ILDL.getExpiryDate(), "30.06.2028");
        assertEquals(ILDL.getFirstName(), "הראל");
        assertEquals(ILDL.getLastName(), "אלעזר");
        assertEquals(ILDL.getDateOfBirth(), "1987-06-30T00:00:00.000+0000");
        assertEquals(ILDL.getAddress(), "שפירא משה ח ם 3 ו אשדוד");
        assertEquals(ILDL.getIdNumber(), 300864550);
        assertEquals(ILDL.getDocumentNumber(), 9503218);
        assertEquals(ILDL.getExpirationDate(), "2028-06-30T00:00:00.000+0000");
        assertNotNull(ILDL.getFaceImage());
        assertNotNull(ILDL.getFrontImage());
        assertNotNull(ILDL.getCardImage());
        assertNotNull(ILDL.getBackImage());
        assertEquals(ILDL.getDocType(), "new_back");
        assertEquals(ILDL.getIssueDate(), "2018-08-10T00:00:00.000+0000");
        assertEquals(ILDL.getLicenseCategory(), "B");
        assertEquals(ILDL.getLicenseIssuingYear(), "2009-01-01T00:00:00.000+0000");
        assertEquals(ILDL.getLicenseIssueYear(), 2009);
        assertEquals(ILDL.getOcrType(), "IL-DL");
        assertEquals(ILDL.getBackSideIdNumber(), 203114798);
        assertTrue(ILDL.isFacePosition());
        assertTrue(ILDL.isValidityExpiryDate());
        assertTrue(ILDL.isChecksum());
        assertTrue(ILDL.isFaceSize());
        assertTrue(ILDL.isTemplateMatching());
        assertTrue(ILDL.isFaceRotation());
        assertTrue(ILDL.isProcessSuccess());
        assertEquals(ILDL.getCount(), 1);
    }

    @Test(description = "Onboarding Api Test Scanning Credit card")
    @Description("Onboarding Api Test Scanning Credit card")
    public void t13_onboardingWithCreditCard() throws IOException, ParseException {
        sessionId = apiResponses.createSessionId(556);
        apiResponses.initSessionNew(preProcess, 556, sessionId, System.getProperty("COMPANY_ID"));
        preProcess.setCaseId(randomString());


        apiResponse.clientInitResponseNew(variables, "application/json", "caseId", preProcess.getCaseId(), "libraryName", "C-CARD", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR, sessionId, X_Session_Id, X_Request_Id, "clientTranslationFileName", clientTranslationFileValue, "devMode", devMode);



        assertTrue(preProcess.isSuccess());
        assertEquals(preProcess.getErrorCode(), 0);
        assertEquals(preProcess.getDataId(), "process-cb035494-3b75-4b7a-80d7-7fca5f7ebbb8");
        assertNotNull(preProcess.getInquiryId());
        assertTrue(preProcess.isDataSuccess());
        assertEquals(preProcess.getNextProcess(), "server_ocr");
        assertEquals((preProcess.getMissingFields()).substring(0, 9), "ocrCaseId");
        assertEquals(preProcess.getDataErrorCode(), 1001);
        assertNotNull(preProcess.getSessionId());
        assertFalse(preProcess.isBackStepAllow());



        apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                "Bearer " + variables.getToken(), "frame", new File("./ocr/creditCard/masterCard.jpg"),
                "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR,sessionId, X_Session_Id, X_Request_Id);
        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./ocr/creditCard/masterCard.jpg"),
                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR,sessionId, X_Session_Id, X_Request_Id);
        }

        assertEquals(variables.getActionType(), "complete");
        String ocrCaseId = variables.getCaseId();

        apiResponses.updateRequestPutCall(preProcess, 556, preProcess.getMissingFields(), ocrCaseId, "sessionId", sessionId);

        assertTrue(preProcess.isSuccess());
        assertTrue(preProcess.isDataSuccess());
        assertEquals(preProcess.getNextProcess(), "process_ended");


        apiResponses.getTokenFromCallbackRequest(preProcess);

        assertTrue(preProcess.isSuccess());
        assertEquals(preProcess.getErrorCode(), 0);
        assertEquals(preProcess.getErrorCode(), 0);
        assertNotNull(preProcess.getData());
        assertNotNull(preProcess.getToken());

        utilities.endpointManager(preProcess, endpointResults, "v2");

        //Final results:
        assertTrue(endpointResults.isSuccess());
        assertEquals(endpointResults.getErrorCode(), 0);
        assertTrue(endpointResults.isDataSuccess());
        assertEquals(endpointResults.getDataErrorMessage(), "");
        assertEquals(endpointResults.getDataErrorCode(), 0);
        assertEquals(endpointResults.getMetadata(), "");

        assertEquals(CreditCard.getProcess(), "ocr");
        assertNotNull(CreditCard.getEnd());

        assertEquals(CreditCard.getExpiryDate(), "27.02.2020");
        assertEquals(CreditCard.getExpirationDate(), "2020-02-27T00:00:00.000+0000");
        assertNotNull(CreditCard.getFrontImage());
        assertNotNull(CreditCard.getCardImage());
        assertEquals(CreditCard.getDocType(), "credit_card_front");
        assertEquals(CreditCard.getOcrType(), "C-CARD");
        assertEquals(CreditCard.getNumberLine(), "5326100314433644");

        assertTrue(CreditCard.isProcessSuccess());
        assertEquals(CreditCard.getCount(), 1);
    }


    @Test(description = "Onboarding Api Test Scanning Stay Permit card")
    @Description("Onboarding Api Test Scanning Stay Permit card")
    public void t14_onboardingWithStayPermit() throws IOException, ParseException {
        sessionId = apiResponses.createSessionId(556);
        apiResponses.initSessionNew(preProcess, 556, sessionId, System.getProperty("COMPANY_ID"));
        preProcess.setCaseId(randomString());


        apiResponse.clientInitResponseNew(variables, "application/json", "caseId", preProcess.getCaseId(), "libraryName", "IL-STP", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR, sessionId, X_Session_Id, X_Request_Id, "clientTranslationFileName", clientTranslationFileValue, "devMode", devMode);

        assertTrue(preProcess.isSuccess());
        assertEquals(preProcess.getErrorCode(), 0);
        assertEquals(preProcess.getDataId(), "process-cb035494-3b75-4b7a-80d7-7fca5f7ebbb8");
        assertNotNull(preProcess.getInquiryId());
        assertTrue(preProcess.isDataSuccess());
        assertEquals(preProcess.getNextProcess(), "server_ocr");
        assertEquals((preProcess.getMissingFields()).substring(0, 9), "ocrCaseId");
        assertEquals(preProcess.getDataErrorCode(), 1001);
        assertNotNull(preProcess.getSessionId());
        assertFalse(preProcess.isBackStepAllow());



        apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                "Bearer " + variables.getToken(), "frame", new File("./ocr/stayPermit/israel_stay_permit_front.jpg"),
                "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR,sessionId,X_Session_Id, X_Request_Id);
        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./ocr/stayPermit/israel_stay_permit_front.jpg"),
                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR,sessionId,X_Session_Id, X_Request_Id);
        }

        assertEquals(variables.getActionType(), "complete");
        String ocrCaseId = variables.getCaseId();

        apiResponses.updateRequestPutCall(preProcess, 556, preProcess.getMissingFields(), ocrCaseId, "sessionId", sessionId);

        assertTrue(preProcess.isSuccess());
        assertTrue(preProcess.isDataSuccess());
        assertEquals(preProcess.getNextProcess(), "process_ended");


        apiResponses.getTokenFromCallbackRequest(preProcess);

        assertTrue(preProcess.isSuccess());
        assertEquals(preProcess.getErrorCode(), 0);
        assertEquals(preProcess.getErrorCode(), 0);
        assertNotNull(preProcess.getData());
        assertNotNull(preProcess.getToken());

        utilities.endpointManager(preProcess, endpointResults, "v2");

        //Final results:
        assertTrue(endpointResults.isSuccess());
        assertEquals(endpointResults.getErrorCode(), 0);
        assertTrue(endpointResults.isDataSuccess());
        assertEquals(endpointResults.getDataErrorMessage(), "");
        assertEquals(endpointResults.getDataErrorCode(), 0);
        assertEquals(endpointResults.getMetadata(), "");

        assertEquals(StayPermit.getProcess(), "ocr");

        assertEquals(StayPermit.getExpiryDate(), "06.01.2017");
        assertEquals(StayPermit.getExpirationDate(), "2017-01-06T00:00:00.000+0000");
        assertNotNull(StayPermit.getFrontImage());
        assertEquals(StayPermit.getDocType(), "israel_stay_permit");
        assertEquals(StayPermit.getOcrType(), "IL-STP");
        assertEquals(StayPermit.getPermitType(), "B2");
        assertEquals(StayPermit.getDocumentNumber(),"EB8806818");


        assertTrue(StayPermit.isProcessSuccess());
        assertEquals(StayPermit.getCount(), 1);
    }

    @Test(description = "Onboarding Api Test for BIZI")
    @Description("Onboarding Api Test for BIZI")
    public void t15_biziNewDLOnboarding() throws IOException {

        sessionId = apiResponses.createSessionId(716);
        apiResponses.initSessionNew(preProcess, 716, sessionId, System.getProperty("COMPANY_ID"));

        preProcess.setCaseId(randomString());

        apiResponse.clientInitResponseNew(variables, "application/json", "caseId", preProcess.getCaseId(), "libraryName", "IL-DL", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR, sessionId, X_Session_Id, X_Request_Id, "clientTranslationFileName", clientTranslationFileValue, "devMode", devMode);


        assertTrue(preProcess.isSuccess());
        assertEquals(preProcess.getErrorCode(), 0);
        assertNotNull(preProcess.getInquiryId());
        assertTrue(preProcess.isDataSuccess());
        assertEquals(preProcess.getNextProcess(), "server_ocr");
        assertEquals((preProcess.getMissingFields()).substring(0, 9), "ocrCaseId");
        assertEquals(preProcess.getDataErrorCode(), 1001);
        assertNotNull(preProcess.getSessionId());
        assertNotNull(preProcess.getOcrTypes());
        assertFalse(preProcess.isBackStepAllow());




        apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                "Bearer " + variables.getToken(), "frame", new File("./ocr/dl/harel_dl_front.jpg"),
                "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR,sessionId,X_Session_Id, X_Request_Id);
        while (!variables.getActionType().equalsIgnoreCase("stage")) {
            apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./ocr/dl/harel_dl_front.jpg"),
                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR,sessionId,X_Session_Id, X_Request_Id);
        }

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./ocr/dl/harel_dl_back.jpg"),
                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR,sessionId,X_Session_Id, X_Request_Id);
        }


        assertEquals(variables.getActionType(), "complete");
        String ocrCaseId = variables.getCaseId();

        apiResponses.updateRequestPutCall(preProcess, 716, preProcess.getMissingFields(), ocrCaseId, "sessionId", sessionId);


        assertTrue(preProcess.isSuccess());
        assertEquals(preProcess.getErrorCode(), 0);
        assertNotNull(preProcess.getInquiryId());
        assertTrue(preProcess.isDataSuccess());
        assertEquals(preProcess.getNextProcess(), "liveness");
        assertEquals(preProcess.getDataErrorCode(), 1001);
        assertNotNull(preProcess.getSessionId());

        preProcess.setCaseId(randomString());
        variables = new Variables();




        apiResponse.clientInitResponseNew(variables, "application/json", "caseId", preProcess.getCaseId(), "libraryName", "LIVENESS", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_LIVENESS, sessionId, X_Session_Id1, X_Request_Id1, "clientTranslationFileName", clientTranslationFileValue, "devMode", devMode);


        apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                "Bearer " + variables.getToken(), "frame", new File("./liveness/harel/harelLivenessCenter.jpg"),
                "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS,sessionId,X_Session_Id1, X_Request_Id1);

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./liveness/harel/harelLivenessCenter.jpg"),
                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS,sessionId,X_Session_Id1, X_Request_Id1);
        }


        assertEquals(variables.getActionType(), "complete");
        String livenessCaseId = variables.getCaseId();

        apiResponses.updateRequestPutCall(preProcess, 716, preProcess.getMissingFields(), livenessCaseId, "sessionId", sessionId);

        assertTrue(preProcess.isSuccess());
        assertTrue(preProcess.isDataSuccess());
        assertEquals(preProcess.getNextProcess(), "process_ended");


        apiResponses.getTokenFromCallbackRequest(preProcess);

        assertTrue(preProcess.isSuccess());
        assertEquals(preProcess.getErrorCode(), 0);
        assertEquals(preProcess.getErrorCode(), 0);
        assertNotNull(preProcess.getData());
        assertNotNull(preProcess.getToken());

        utilities.endpointManager(preProcess, endpointResults, "v2");


        //Final results:
        assertTrue(endpointResults.isSuccess());
        assertEquals(endpointResults.getErrorCode(), 0);
        assertTrue(endpointResults.isDataSuccess());
        assertEquals(endpointResults.getDataErrorMessage(), "");
        assertEquals(endpointResults.getDataErrorCode(), 0);
        assertEquals(endpointResults.getMetadata(), "");

        assertEquals(ILDL.getProcess(), "ocr");
        assertNotNull(ILDL.getEnd());
        assertEquals(ILDL.getDob(), "30.06.1987");
        assertEquals(ILDL.getIssuingDate(), "10.08.2018");
        assertEquals(ILDL.getExpiryDate(), "30.06.2028");
        assertEquals(ILDL.getFirstName(), "הראל");
        assertEquals(ILDL.getLastName(), "אלעזר");
        assertEquals(ILDL.getDateOfBirth(), "1987-06-30T00:00:00.000+0000");
        assertEquals(ILDL.getAddress(), "שפירא משה ח ם 13 אשדוד");
        assertEquals(ILDL.getIdNumber(), 300864550);
        assertEquals(ILDL.getDocumentNumber(), 9503218);
        assertEquals(ILDL.getExpirationDate(), "2028-06-30T00:00:00.000+0000");
        assertNotNull(ILDL.getFaceImage());
        assertNotNull(ILDL.getFrontImage());
        assertNotNull(ILDL.getCardImage());
        assertNotNull(ILDL.getBackImage());
        assertEquals(ILDL.getDocType(), "new_back");
        assertEquals(ILDL.getIssueDate(), "2018-08-10T00:00:00.000+0000");
        assertEquals(ILDL.getLicenseCategory(), "B");
        assertEquals(ILDL.getLicenseIssuingYear(), "2012-01-01T00:00:00.000+0000");
        assertEquals(ILDL.getLicenseIssueYear(), 2012);
        assertEquals(ILDL.getOcrType(), "IL-DL");
        assertEquals(ILDL.getBackSideIdNumber(), 300864550);
        assertTrue(ILDL.isFacePosition());
        assertTrue(ILDL.isValidityExpiryDate());
        assertTrue(ILDL.isChecksum());
        assertTrue(ILDL.isFaceSize());
        assertTrue(ILDL.isTemplateMatching());
        assertTrue(ILDL.isFaceRotation());
        assertTrue(ILDL.isProcessSuccess());
        assertEquals(ILDL.getCount(), 1);

        assertEquals(Liveness.getProcess(), "liveness");
        assertNotNull(Liveness.getFaceImage());
        assertNotNull(Liveness.getVideo());
        assertTrue(Liveness.isProcessSuccess());

        assertEquals(BiometricMatch.getProcess(), "biometric_match");
        assertTrue(BiometricMatch.isProcessSuccess());
        assertTrue(Math.abs(BiometricMatch.getScore() - 0.84) < 0.05);
//        assertEquals(BiometricMatch.getScore(), 0.7849493622779846);
    }

      // @Test(description = "Onboarding Api Test for BIZI Callback", dataProvider = "dp_multiplier", dataProviderClass = TestUtils.class, groups = {"performance"})
    @Description("Onboarding Api Test for BIZI Callback")
    public void t16_onboardingBiziCallback(int invokeIndex) throws IOException, ParseException {
        clearWebhook();
        Allure.getLifecycle().updateTestCase(testResult -> testResult.setName(testResult.getName() + "_" + invokeIndex));
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDate = sdf1.format(System.currentTimeMillis() - (timeDiff));
        t15_biziNewDLOnboarding();
        String sessionId = this.sessionId;
        Response whRequests = TestUtils.getWebhookRequests(currentDate);
        JsonPath jsonResponse = whRequests.jsonPath();
        ArrayList<LinkedHashMap> jsonRequests = jsonResponse.get("data");
        boolean sessionFound = false;
        for (LinkedHashMap request : jsonRequests) {
            JSONObject jsonContent = (JSONObject) new JSONParser().parse((String) request.get("content"));
            if (jsonContent.get("sessionId").equals(sessionId)) {
                if (jsonContent.containsKey("token")) {
                    sessionFound = true;
                    break;
                }
            }
        }
        Assert.assertTrue(sessionFound, "callback token not found for session id:" + sessionId);
    }

    //    @Test(description = "Card capture  Api Test for Regula flow")
    @Description("Card capture Api Test for for Regula flow")
    public void t17_regulaOcrOnly() throws IOException {
        sessionId = apiResponses.createSessionId(803);
        apiResponses.initSession(preProcess, 803, sessionId);
//        preProcess.setCaseId(randomString());
//
//
//        apiResponse.clientInitResponse(variables, "application/json", "caseId", preProcess.getCaseId(), "libraryName", "IL-DL", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR);
//
//        assertTrue(preProcess.isSuccess());
//        assertEquals(preProcess.getErrorCode(), 0);
//        assertNotNull(preProcess.getInquiryId());
//        assertTrue(preProcess.isDataSuccess());
//        assertEquals(preProcess.getNextProcess(), "server_ocr");
//        assertEquals((preProcess.getMissingFields()).substring(0, 9), "ocrCaseId");
//        assertEquals(preProcess.getDataErrorCode(), 1001);
//        assertNotNull(preProcess.getSessionId());
//        assertNotNull(preProcess.getOcrTypes());
//        assertFalse(preProcess.isBackStepAllow());
//
//
//        apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization",
//                "Bearer " + variables.getToken(), "frame", new File("./ocr/dl/harel_dl_front.jpg"),
//                "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
//        while (!variables.getActionType().equalsIgnoreCase("stage")) {
//            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization",
//                    "Bearer " + variables.getToken(), "frame", new File("./ocr/dl/harel_dl_front.jpg"),
//                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
//        }
//
//        while (!variables.getActionType().equalsIgnoreCase("complete")) {
//            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization",
//                    "Bearer " + variables.getToken(), "frame", new File("./ocr/dl/harel_dl_back.jpg"),
//                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
//        }
//
//
//        assertEquals(variables.getActionType(), "complete");
//        String ocrCaseId = preProcess.getCaseId();
//
//        apiResponses.updateRequestPutCall(preProcess, 716, preProcess.getMissingFields(), ocrCaseId, "sessionId", sessionId);
//
//
//        assertTrue(preProcess.isSuccess());
//        assertEquals(preProcess.getErrorCode(), 0);
//        assertNotNull(preProcess.getInquiryId());
//        assertTrue(preProcess.isDataSuccess());
//        assertEquals(preProcess.getNextProcess(), "liveness");
//        assertEquals(preProcess.getDataErrorCode(), 1001);
//        assertNotNull(preProcess.getSessionId());
//
//        preProcess.setCaseId(randomString());
//        variables = new Variables();
//
//        apiResponse.clientInitResponse(variables, "application/json", "caseId", preProcess.getCaseId(), "libraryName", "LIVENESS", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_LIVENESS);
//
//        apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization",
//                "Bearer " + variables.getToken(), "frame", new File("./liveness/harel/harelLivenessCenter.jpg"),
//                "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
//
//        while (!variables.getActionType().equalsIgnoreCase("complete")) {
//            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization",
//                    "Bearer " + variables.getToken(), "frame", new File("./liveness/harel/harelLivenessCenter.jpg"),
//                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
//        }
//
//
//        assertEquals(variables.getActionType(), "complete");
//        String livenessCaseId = preProcess.getCaseId();
//
//        apiResponses.updateRequestPutCall(preProcess, 716, preProcess.getMissingFields(), livenessCaseId, "sessionId", sessionId);
//
//        assertTrue(preProcess.isSuccess());
//        assertTrue(preProcess.isDataSuccess());
//        assertEquals(preProcess.getNextProcess(), "process_ended");
//
//
//        apiResponses.getTokenFromCallbackRequest(preProcess);
//
//        assertTrue(preProcess.isSuccess());
//        assertEquals(preProcess.getErrorCode(), 0);
//        assertEquals(preProcess.getErrorCode(), 0);
//        assertNotNull(preProcess.getData());
//        assertNotNull(preProcess.getToken());
//
//        utilities.endpointManager(preProcess, endpointResults, "v2");
//
//
//        //Final results:
//        assertTrue(endpointResults.isSuccess());
//        assertEquals(endpointResults.getErrorCode(), 0);
//        assertTrue(endpointResults.isDataSuccess());
//        assertEquals(endpointResults.getDataErrorMessage(), "");
//        assertEquals(endpointResults.getDataErrorCode(), 0);
//        assertEquals(endpointResults.getMetadata(), "");
//
//        assertEquals(ILDL.getProcess(), "ocr");
//        assertNotNull(ILDL.getEnd());
//        assertEquals(ILDL.getDob(), "30.06.1987");
//        assertEquals(ILDL.getIssuingDate(), "10.08.2018");
//        assertEquals(ILDL.getExpiryDate(), "30.06.2028");
//        assertEquals(ILDL.getFirstName(), "הראל");
//        assertEquals(ILDL.getLastName(), "אלעזר");
//        assertEquals(ILDL.getDateOfBirth(), "1987-06-30T00:00:00.000+0000");
//        assertEquals(ILDL.getAddress(), "שפירא משה ח ם 13 אשדוד");
//        assertEquals(ILDL.getIdNumber(), 300864550);
//        assertEquals(ILDL.getDocumentNumber(), 9503218);
//        assertEquals(ILDL.getExpirationDate(), "2028-06-30T00:00:00.000+0000");
//        assertNotNull(ILDL.getFaceImage());
//        assertNotNull(ILDL.getFrontImage());
//        assertNotNull(ILDL.getCardImage());
//        assertNotNull(ILDL.getBackImage());
//        assertEquals(ILDL.getDocType(), "new_back");
//        assertEquals(ILDL.getIssueDate(), "2018-08-10T00:00:00.000+0000");
//        assertEquals(ILDL.getLicenseCategory(), "B");
//        assertEquals(ILDL.getLicenseIssuingYear(), "2012-01-01T00:00:00.000+0000");
//        assertEquals(ILDL.getLicenseIssueYear(), 2012);
//        assertEquals(ILDL.getOcrType(), "IL-DL");
//        assertEquals(ILDL.getBackSideIdNumber(), 300864550);
//        assertTrue(ILDL.isFacePosition());
//        assertTrue(ILDL.isValidityExpiryDate());
//        assertTrue(ILDL.isChecksum());
//        assertTrue(ILDL.isFaceSize());
//        assertTrue(ILDL.isTemplateMatching());
//        assertTrue(ILDL.isFaceRotation());
//        assertTrue(ILDL.isProcessSuccess());
//        assertEquals(ILDL.getCount(), 1);
//
//        assertEquals(Liveness.getProcess(), "liveness");
//        assertNotNull(Liveness.getFaceImage());
//        assertNotNull(Liveness.getVideo());
//        assertTrue(Liveness.isProcessSuccess());
//
//        assertEquals(BiometricMatch.getProcess(), "biometric_match");
//        assertTrue(BiometricMatch.isProcessSuccess());
//        assertTrue(Math.abs(BiometricMatch.getScore() - 0.84) < 0.05);
////        assertEquals(BiometricMatch.getScore(), 0.7849493622779846);
//    }
//
//    @Test(description = "Onboarding Api Test for BIZI Callback", dataProvider = "dp_multiplier", dataProviderClass = TestUtils.class, groups = {"performance"})
//    @Description("Onboarding Api Test for BIZI Callback")
//    public void t16_onboardingBiziCallback(int invokeIndex) throws IOException, ParseException {
//        clearWebhook();
//        Allure.getLifecycle().updateTestCase(testResult -> testResult.setName(testResult.getName() + "_" + invokeIndex));
//        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String currentDate = sdf1.format(System.currentTimeMillis() - (timeDiff));
//        t15_biziNewDLOnboarding();
//        String sessionId = this.sessionId;
//        Response whRequests = TestUtils.getWebhookRequests(currentDate);
//        JsonPath jsonResponse = whRequests.jsonPath();
//        ArrayList<LinkedHashMap> jsonRequests = jsonResponse.get("data");
//        boolean sessionFound = false;
//        for (LinkedHashMap request : jsonRequests) {
//            JSONObject jsonContent = (JSONObject) new JSONParser().parse((String) request.get("content"));
//            if (jsonContent.get("sessionId").equals(sessionId)) {
//                if (jsonContent.containsKey("token")) {
//                    sessionFound = true;
//                    break;
//                }
//            }
//        }
//        Assert.assertTrue(sessionFound, "callback token not found for session id:" + sessionId);
//    }
//
//    @Test(description = "Card capture  Api Test for Regula flow")
//    @Description("Card capture Api Test for for Regula flow")
//    public void t17_regulaOcrOnly() throws IOException {
//        sessionId = apiResponses.createSessionId(803);
//        apiResponses.initSession(preProcess, 803, sessionId);
////        preProcess.setCaseId(randomString());
//
////        apiResponse.clientInitResponse(variables, "application/json", "caseId", preProcess.getCaseId(), "libraryName", "IL-DL", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR);
//        assertTrue(preProcess.isSuccess());
//        assertEquals(preProcess.getErrorCode(), 0);
//        assertNotNull(preProcess.getInquiryId());
//        assertTrue(preProcess.isDataSuccess());
//        assertEquals(preProcess.getNextProcess(), "card_capture");
//        assertEquals((preProcess.getMissingFields()), "image");
//        assertEquals(preProcess.getDataErrorCode(), 1001);
//        assertNotNull(preProcess.getSessionId());
//        assertNotNull(preProcess.getOcrTypes());
//        assertFalse(preProcess.isBackStepAllow());
//        apiResponses.cardCaptureImagesResponse(preProcess, new File("./ocr/dl/guy-usdl.jpg"));
//        assertEquals(preProcess.getNextProcess(), "process_ended");
//        assertTrue(preProcess.isSuccess());
//        assertEquals(preProcess.getErrorCode(), 0);
//        assertNotNull(preProcess.getInquiryId());
//        assertTrue(preProcess.isDataSuccess());
//        assertNotNull(preProcess.getSessionId());
//        assertNotNull(preProcess.getOcrTypes());
//        assertFalse(preProcess.isBackStepAllow());
//
//
//        apiResponses.getTokenFromCallbackRequest(preProcess);
//        assertNotNull(preProcess.getToken());
//        utilities.endpointManager(preProcess, endpointResults, "v2");
//        assertTrue(endpointResults.isSuccess());
//        assertEquals(endpointResults.getErrorCode(), 0);
//        assertTrue(endpointResults.isDataSuccess());
//        assertEquals(endpointResults.getDataErrorMessage(), "");
//        assertEquals(endpointResults.getDataErrorCode(), 0);
//
//        assertTrue(CardCapture.getDob().equalsIgnoreCase("19.09.1986"));
//        assertTrue(CardCapture.getIssuingDate().equalsIgnoreCase("08.05.2014"));
//        assertTrue(CardCapture.getExpiryDate().equalsIgnoreCase("14.06.2022"));
//        assertTrue(CardCapture.getFirstName().equalsIgnoreCase("GUY"));
//        assertTrue(CardCapture.getLastName().equalsIgnoreCase("STIEBEL"));
//        assertTrue(CardCapture.getAddress().equalsIgnoreCase("150 PICCADILLY, LONDON"));
//        assertTrue(CardCapture.getDocumentNumber().equalsIgnoreCase("STIEB809196A99TW"));
//        assertTrue(CardCapture.getPlaceOfBirth().equalsIgnoreCase("ISRAEL"));
//    }
//
//    @Test(description = "Regula Full Shlomo flow")
//    @Description("Regula Full Shlomo flow")
//    public void t18_regulaShlomoFull() throws IOException {
//        sessionId = apiResponses.createSessionId(1305);
//        apiResponses.initSession(preProcess, 1305, sessionId);
//        preProcess.setCaseId(randomString());
//        apiResponse.clientInitResponse(variables, "application/json", "caseId", preProcess.getCaseId(), "libraryName", "MRZ", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR);
//        assertTrue(preProcess.isSuccess());
//        assertEquals(preProcess.getErrorCode(), 0);
//        assertNotNull(preProcess.getInquiryId());
//        assertTrue(preProcess.isDataSuccess());
//        assertEquals(preProcess.getNextProcess(), "server_ocr");
//        assertEquals((preProcess.getMissingFields()).substring(0, 9), "ocrCaseId");
//        assertEquals(preProcess.getDataErrorCode(), 1001);
//        assertNotNull(preProcess.getSessionId());
//        assertNotNull(preProcess.getOcrTypes());
//        assertFalse(preProcess.isBackStepAllow());
//        apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization",
//                "Bearer " + variables.getToken(), "frame", new File("./ocr/mrz/avner/avner1.jpg"),
//                "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
//        while (!variables.getActionType().equalsIgnoreCase("complete")) {
//            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization",
//                    "Bearer " + variables.getToken(), "frame", new File("./ocr/mrz/avner/avner1.jpg"),
//                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
//        }
//        assertEquals(variables.getActionType(), "complete");
//        String ocrCaseId = preProcess.getCaseId();
//
//        apiResponses.updateRequestPutCall(preProcess, 1305, preProcess.getMissingFields(), ocrCaseId, "sessionId", sessionId);
//        assertTrue(preProcess.isSuccess());
//        assertEquals(preProcess.getErrorCode(), 0);
//        assertNotNull(preProcess.getInquiryId());
//        assertTrue(preProcess.isDataSuccess());
//        assertEquals(preProcess.getNextProcess(), "card_capture");
//        assertEquals((preProcess.getMissingFields()), "image");
//        assertEquals(preProcess.getDataErrorCode(), 1001);
//
//        apiResponses.cardCaptureImagesResponse(preProcess, new File("./ocr/dl/guy-usdl.jpg"));
//        apiResponses.updateRequestPutCall(preProcess, 1305, preProcess.getMissingFields(), ocrCaseId, "sessionId", sessionId);
//        assertTrue(preProcess.isSuccess());
//        assertEquals(preProcess.getErrorCode(), 0);
//        assertNotNull(preProcess.getInquiryId());
//        assertEquals(preProcess.getNextProcess(), "liveness");
//
//        //Liveness
//        preProcess.setCaseId(randomString());
//        variables = new Variables();
//
//        apiResponse.clientInitResponse(variables, "application/json", "caseId", preProcess.getCaseId(), "libraryName", "LIVENESS", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_LIVENESS);
//
//        apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization",
//                "Bearer " + variables.getToken(), "frame", new File("./liveness/avner/avnerCenter.jpg"),
//                "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
//
//        while (!variables.getActionType().equalsIgnoreCase("complete")) {
//            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization",
//                    "Bearer " + variables.getToken(), "frame", new File("./liveness/avner/avnerCenter.jpg"),
//                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
//        }
//
//
//        assertEquals(variables.getActionType(), "complete");
//        String livenessCaseId = preProcess.getCaseId();
//
//        apiResponses.updateRequestPutCall(preProcess, 1305, preProcess.getMissingFields(), livenessCaseId, "sessionId", sessionId);
//
//        assertTrue(preProcess.isSuccess());
////        assertTrue(preProcess.isDataSuccess());
//        assertEquals(preProcess.getNextProcess(), "process_ended");
//
//
//        apiResponses.getTokenFromCallbackRequest(preProcess);
//
//        assertTrue(preProcess.isSuccess());
//        assertEquals(preProcess.getErrorCode(), 0);
//        assertEquals(preProcess.getErrorCode(), 0);
//        assertNotNull(preProcess.getData());
//        assertNotNull(preProcess.getToken());
//
//        utilities.endpointManager(preProcess, endpointResults, "v2");
//
//
//        //Final results:
//        assertTrue(endpointResults.isSuccess());
////        assertEquals(endpointResults.getErrorCode(), 0);
////        assertTrue(endpointResults.isDataSuccess());
////        assertEquals(endpointResults.getDataErrorMessage(), "");
////        assertEquals(endpointResults.getDataErrorCode(), 0);
////        assertEquals(endpointResults.getMetadata(), "");
//
//        assertEquals(MRZ.getProcess(), "ocr");
//        assertNotNull(MRZ.getEnd());
//        assertEquals(MRZ.getDob(), "02.08.1980");
//        assertEquals(MRZ.getExpiryDate(), "10.06.2024");
//        assertEquals(MRZ.getFirstName(), "AVNER");
//        assertEquals(MRZ.getLastName(), "BLUER");
//        assertEquals(MRZ.getDateOfBirth(), "1980-08-02T00:00:00.000+0000");
//        assertEquals(MRZ.getNationalityName(), "Israel");
//        assertEquals(MRZ.getNationalityAlpha2(), "IL");
//        assertEquals(MRZ.getNationalityAlpha3(), "ISR");
//        assertEquals(MRZ.getGender(), "Male");
//        assertEquals(MRZ.getIdNumber(), "0-4037370-6");
//        assertEquals(MRZ.getDocumentNumber(), 30266026);
//        assertEquals(MRZ.getExpirationDate(), "2024-06-10T00:00:00.000+0000");
//        assertEquals(MRZ.getIssuingCountryName(), "Israel");
//        assertEquals(MRZ.getIssuingCountryAlpha2(), "IL");
//        assertEquals(MRZ.getIssuingCountryAlpha3(), "ISR");
//        assertEquals(MRZ.getCountryCode(), "IL");
//        assertNotNull(MRZ.getFaceImage());
//        assertNotNull(MRZ.getFrontImage());
//        assertNotNull(MRZ.getCardImage());
//        assertEquals(MRZ.getDocType(), "MRZ");
//        assertEquals(MRZ.getOcrType(), "MRZ");
//        assertTrue(MRZ.isValidityExpiryDate());
//        assertTrue(MRZ.isChecksum());
//        assertTrue(MRZ.isTemplateMatching());
//        assertTrue(MRZ.isFacePosition());
//        assertTrue(MRZ.isFaceRotation());
//        assertTrue(MRZ.isFaceSize());
//        assertTrue(MRZ.isProcessSuccess());
//        assertEquals(MRZ.getCount(), 1);
//
//        assertEquals(Liveness.getProcess(), "liveness");
//        assertNotNull(Liveness.getFaceImage());
//        assertNotNull(Liveness.getVideo());
//        assertTrue(Liveness.isProcessSuccess());
//
//        //Biometric match results
//        assertEquals(BiometricMatch.getProcess(), "biometric_match");
//        assertTrue(BiometricMatch.isProcessSuccess());
//        assertTrue(Math.abs(BiometricMatch.getScore() - 0.8) < 0.05);
//
//        //Card capture regula results
//        assertTrue(CardCapture.getDob().equalsIgnoreCase("19.09.1986"));
//        assertTrue(CardCapture.getIssuingDate().equalsIgnoreCase("08.05.2014"));
//        assertTrue(CardCapture.getExpiryDate().equalsIgnoreCase("14.06.2022"));
//        assertTrue(CardCapture.getFirstName().equalsIgnoreCase("GUY"));
//        assertTrue(CardCapture.getLastName().equalsIgnoreCase("STIEBEL"));
//        assertTrue(CardCapture.getAddress().equalsIgnoreCase("150 PICCADILLY, LONDON"));
//        assertTrue(CardCapture.getDocumentNumber().equalsIgnoreCase("STIEB809196A99TW"));
//        assertTrue(CardCapture.getPlaceOfBirth().equalsIgnoreCase("ISRAEL"));
//    }
//
//    @Test(description = "Onboarding Api Test for Callback Headers", dataProviderClass = TestUtils.class)
//    @Description("Onboarding Api Test for Callback Headers")
//    public void t19_onboardingCallbackHeaders() throws IOException, ParseException {
//        Assert.assertNotNull(System.getProperty("CALLBACK_HEADERS"), "CALLBACK_HEADERS property not defined");
//        JSONArray callbackHeaders = new JSONArray(System.getProperty("CALLBACK_HEADERS"));
//        DBUtils dbu=new DBUtils();
//        Assert.assertTrue(dbu.updateCallbackHeaders(System.getProperty("CALLBACK_HEADERS")),"failed update Callback headers in DB");
//        clearWebhook();
//        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String currentDate = sdf1.format(System.currentTimeMillis() - (timeDiff));
//        t14_onboardingWithStayPermit();
//        String sessionId = this.sessionId;
//        Response whRequests = TestUtils.getWebhookRequests(currentDate);
//        JsonPath jsonResponse = whRequests.jsonPath();
//        ArrayList<LinkedHashMap> jsonRequests = jsonResponse.get("data");
//        boolean sessionFound = false;
//        for (LinkedHashMap request : jsonRequests) {
//            JSONObject jsonContent = (JSONObject) new JSONParser().parse((String) request.get("content"));
//            if (jsonContent.get("sessionId").equals(sessionId)) {
//                // check headers
//                LinkedHashMap requestHeaders = (LinkedHashMap) request.get("headers");
//                for (Object callbackHeader : callbackHeaders) {
//                    org.json.JSONObject jsonHeader = (org.json.JSONObject) callbackHeader;
//                    String keyName = (String) jsonHeader.get("key");
//                    Assert.assertTrue(requestHeaders.containsKey(keyName));
//                    Assert.assertEquals(jsonHeader.get("value"), ((ArrayList) requestHeaders.get(keyName)).get(0));
//                }
//                // check session id
//                if (jsonContent.containsKey("token")) {
//                    sessionFound = true;
//                    break;
//                }
//            }
//        }
//        Assert.assertTrue(sessionFound, "callback token not found for session id:" + sessionId);
//
//    }
    }
}