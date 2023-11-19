package btrust.onboardingProcess.api.v2;

import api.ApiResponse;
import api.Variables;
import btrust.onboardingProcess.api.ApiResponses;
import btrust.onboardingProcess.api.Utilities;
import btrust.onboardingProcess.api.variables.*;
import io.qameta.allure.Description;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utilities.SuiteListener;
import utilities.TestUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static btrust.btrustOne.admin.BaseAdminUserTest.randomString;
import static org.testng.Assert.*;
@Listeners({SuiteListener.class})
public class ErrorsTests {

    Map<String, File> processes;
    PreProcess preProcess;
    EndpointResults endpointResults;
    Utilities utilities;
    ApiResponses apiResponses;

    ApiResponse apiResponse;

    String sessionId;
    Variables variables;

    String X_Request_Id;
    String X_Session_Id;
    String X_Request_Id1;
    String X_Session_Id1;
    String clientTranslationFileValue;

    boolean devMode;

    @BeforeMethod
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


    @Test(description = "Onboarding Api Test BioId card Biometric match Fail",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Onboarding Api Test BioId card Biometric match Fail")
    public void t01_bioIdBiometricMatchFail() throws IOException, ParseException {
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
                "Bearer " + variables.getToken(), "frame", new File("./ocr/new bioID/Gregory/newBioIDFront.jpeg"),
                "image/jpg", "message_type", "frame_request",  apiResponse.CLIENT_REQUEST_OCR, sessionId, X_Session_Id, X_Request_Id);
        while (!variables.getActionType().equalsIgnoreCase("stage")) {
            apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./ocr/new bioID/Gregory/newBioIDFront.jpeg"),
                    "image/jpg", "message_type", "frame_request",  apiResponse.CLIENT_REQUEST_OCR, sessionId, X_Session_Id, X_Request_Id);
        }

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./ocr/new bioID/Gregory/newBioIDBack.jpeg"),
                    "image/jpg", "message_type", "frame_request",  apiResponse.CLIENT_REQUEST_OCR, sessionId, X_Session_Id, X_Request_Id);
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

        apiResponse.clientInitResponseNew(variables, "application/json", "caseId", preProcess.getCaseId(), "libraryName", "LIVENESS", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_LIVENESS, sessionId, X_Session_Id, X_Request_Id, "clientTranslationFileName", clientTranslationFileValue, "devMode", devMode);

        apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                "Bearer " + variables.getToken(), "frame", new File("./liveness/liad/liadCenterFace.jpg"),
                "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS, sessionId, X_Session_Id, X_Request_Id);

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./liveness/liad/liadCenterFace.jpg"),
                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS, sessionId, X_Session_Id, X_Request_Id);
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
        assertEquals(BiometricMatch.getScore(),0.4154979884624481);
//        assertTrue(Math.abs(BiometricMatch.getScore()-0.57)<0.05);

    }

    @Test(description = "Onboarding Api Test BlueId card Biometric match Fail",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Onboarding Api Test BlueId card Biometric match Fail")
    public void t02_blueIdBiometricMatchFail() throws IOException, ParseException {
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

        apiResponse.clientInitResponseNew(variables, "application/json", "caseId", preProcess.getCaseId(), "libraryName", "LIVENESS", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_LIVENESS, sessionId, X_Session_Id, X_Request_Id, "clientTranslationFileName", clientTranslationFileValue, "devMode", devMode);

        apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                "Bearer " + variables.getToken(), "frame", new File("./liveness/Adi/adi.jpg"),
                "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS,sessionId, X_Session_Id, X_Request_Id);

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./liveness/Adi/adi.jpg"),
                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS,sessionId, X_Session_Id, X_Request_Id);
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
        assertFalse(BiometricMatch.isProcessSuccess());
        assertTrue(Math.abs(BiometricMatch.getScore()-0.5)<0.05);
    }

    @Test(description = "Onboarding Api Test GreenId card Biometric match Fail",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Onboarding Api Test GreenId card Biometric match Fail")
    public void t03_greenIdBiometricMatchFail() throws IOException, ParseException {
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

        apiResponse.clientInitResponseNew(variables, "application/json", "caseId", preProcess.getCaseId(), "libraryName", "LIVENESS", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_LIVENESS, sessionId, X_Session_Id, X_Request_Id, "clientTranslationFileName", clientTranslationFileValue, "devMode", devMode);

        apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                "Bearer " + variables.getToken(), "frame", new File("./liveness/Adi/adi.jpg"),
                "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS,sessionId, X_Session_Id, X_Request_Id);

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./liveness/Adi/adi.jpg"),
                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS,sessionId, X_Session_Id, X_Request_Id);
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
        assertFalse(BiometricMatch.isProcessSuccess());
        assertTrue(Math.abs(BiometricMatch.getScore()-0.46)<0.05);
//        assertEquals(BiometricMatch.getScore(), 0.46877557039260864);
    }


    @Test(description = "Onboarding Api Test ILDL card Biometric match Fail",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Onboarding Api Test ILDL card Biometric match Fail")
    public void t04_ilDlBiometricMatchFail() throws IOException, ParseException {
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
        String ocrCaseId =  variables.getCaseId();

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

        apiResponse.clientInitResponseNew(variables, "application/json", "caseId", preProcess.getCaseId(), "libraryName", "LIVENESS", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_LIVENESS, sessionId, X_Session_Id, X_Request_Id, "clientTranslationFileName", clientTranslationFileValue, "devMode", devMode);

        apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                "Bearer " + variables.getToken(), "frame", new File("./liveness/Adi/adi.jpg"),
                "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS,sessionId, X_Session_Id, X_Request_Id);

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./liveness/Adi/adi.jpg"),
                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS,sessionId, X_Session_Id, X_Request_Id);
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

        assertEquals(Liveness.getProcess(), "liveness");
        assertNotNull(Liveness.getFaceImage());
        assertNotNull(Liveness.getVideo());
        assertTrue(Liveness.isProcessSuccess());

        assertEquals(BiometricMatch.getProcess(), "biometric_match");
        assertFalse(BiometricMatch.isProcessSuccess());
        assertTrue(Math.abs(BiometricMatch.getScore()-0.5)<0.05);
//        assertEquals(BiometricMatch.getScore(), 0.5051852464675903);
    }

    @Test(description = "Onboarding Api Test MRZ card Biometric match Fail")
    @Description("Onboarding Api Test MRZ card Biometric match Fail")
    public void t05_mrzBiometricMatchFail() throws IOException, ParseException {
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
                "Bearer " + variables.getToken(), "frame", new File("./ocr/mrz/liad/1.jpg"),
                "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR,sessionId, X_Session_Id, X_Request_Id);
        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./ocr/mrz/liad/1.jpg"),
                    "image/jpg", "message_type", "frame_request",  apiResponse.CLIENT_REQUEST_OCR,sessionId, X_Session_Id, X_Request_Id);
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

        apiResponse.clientInitResponseNew(variables, "application/json", "caseId", preProcess.getCaseId(), "libraryName", "LIVENESS", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_LIVENESS, sessionId, X_Session_Id, X_Request_Id, "clientTranslationFileName", clientTranslationFileValue, "devMode", devMode);

        apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                "Bearer " + variables.getToken(), "frame", new File("./liveness/bar/bar_new.jpg"),
                "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS,sessionId, X_Session_Id, X_Request_Id);

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./liveness/bar/bar_new.jpg"),
                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS,sessionId, X_Session_Id, X_Request_Id);
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

        assertEquals(MRZ.getProcess(), "ocr");
        assertNotNull(MRZ.getEnd());
        assertEquals(MRZ.getDob(), "17.01.1993");
        assertEquals(MRZ.getExpiryDate(), "27.10.2023");
        assertEquals(MRZ.getFirstName(), "LIAD");
        assertEquals(MRZ.getLastName(), "TOBI");
        assertEquals(MRZ.getDateOfBirth(), "1993-01-17T00:00:00.000+0000");
        assertEquals(MRZ.getNationalityName(), "Israel");
        assertEquals(MRZ.getNationalityAlpha2(), "IL");
        assertEquals(MRZ.getNationalityAlpha3(), "ISR");
        assertEquals(MRZ.getGender(), "Male");
        assertEquals(MRZ.getIdNumber(), "3-0792232-8");
        assertEquals(MRZ.getDocumentNumber(), 21308777);
        assertEquals(MRZ.getExpirationDate(), "2023-10-27T00:00:00.000+0000");
        assertEquals(MRZ.getIssuingCountryName(), "Israel");
        assertEquals(MRZ.getIssuingCountryAlpha2(), "IL");
        assertEquals(MRZ.getIssuingCountryAlpha3(), "ISR");
        assertEquals(MRZ.getCountryCode(), "IL");
        assertNotNull(MRZ.getFaceImage());
 //       assertNotNull(MRZ.getFrontImage());
        assertNotNull(MRZ.getCardImage());
        assertEquals(MRZ.getDocType(), "israel_passport");
        assertEquals(MRZ.getOcrType(), "MRZ");
        assertFalse(MRZ.isValidityExpiryDate());
        assertTrue(MRZ.isChecksum());
        assertTrue(MRZ.isTemplateMatching());
        assertFalse(MRZ.isFacePosition());
        assertTrue(MRZ.isFaceRotation());
        assertFalse(MRZ.isFaceSize());
        assertTrue(MRZ.isProcessSuccess());
        assertEquals(MRZ.getCount(), 1);

        assertEquals(Liveness.getProcess(), "liveness");
        assertNotNull(Liveness.getFaceImage());
        assertNotNull(Liveness.getVideo());
        assertTrue(Liveness.isProcessSuccess());

        assertEquals(BiometricMatch.getProcess(), "biometric_match");
        assertFalse(BiometricMatch.isProcessSuccess());
        assertTrue(Math.abs(BiometricMatch.getScore()-0.4901579022407532)<0.1);
    }


}
