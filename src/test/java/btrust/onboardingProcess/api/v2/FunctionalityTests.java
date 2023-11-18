package btrust.onboardingProcess.api.v2;

import api.ApiResponse;
import api.Variables;
import btrust.onboardingProcess.api.ApiResponses;
import btrust.onboardingProcess.api.Utilities;
import btrust.onboardingProcess.api.variables.*;
import io.qameta.allure.Description;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.TestUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static btrust.btrustOne.admin.BaseAdminUserTest.randomString;
import static java.lang.Thread.sleep;
import static org.testng.Assert.*;

public class FunctionalityTests {

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

//    @Test(description = "Test mobile flow with max Attempts - Pass")
    @Description("Test mobile flow with max Attempts - Pass")
    public void t01_maxAttempts() throws IOException, ParseException {

        sessionId = apiResponses.createSessionId(434);
        apiResponses.initSessionNew(preProcess, 434, sessionId, System.getProperty("COMPANY_ID"));
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
                "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/face_not_found.jpg"),
                "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR, sessionId, X_Session_Id, X_Request_Id);
        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/face_not_found.jpg"),
                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR, sessionId, X_Session_Id, X_Request_Id);
        }




//        assertEquals(variables.getActionType(), "complete");
//        assertEquals(variables.getStatus(), "timeout");


        apiResponses.updateRequestPutCall(preProcess, 434, preProcess.getMissingFields(), preProcess.getCaseId(), "sessionId", sessionId);

        assertTrue(preProcess.isSuccess());
        assertEquals(preProcess.getErrorCode(), 0);
        assertEquals(preProcess.getDataId(), "process-c6aa3851-8e6a-4752-b5f2-bfcc02c9439e");
        assertNotNull(preProcess.getInquiryId());
        assertFalse(preProcess.isDataSuccess());
        assertEquals(preProcess.getNextProcess(), "server_ocr");
        assertEquals((preProcess.getMissingFields()).substring(0, 9), "ocrCaseId");
//        assertEquals(preProcess.getDataErrorCode(), 1015);
        System.out.println(preProcess.getDataErrorCode());
        assertNotNull(preProcess.getSessionId());
        assertNotNull(preProcess.getOcrTypes());
        assertFalse(preProcess.isBackStepAllow());

        preProcess.setCaseId(randomString());

        apiResponse.clientInitResponseNew(variables, "application/json", "caseId", preProcess.getCaseId(), "libraryName", "IL-ID", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR,sessionId, X_Session_Id, X_Request_Id, "clientTranslationFileName", clientTranslationFileValue, "devMode", devMode);


        apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/face_not_found.jpg"),
                "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR, sessionId, X_Session_Id, X_Request_Id);
        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/face_not_found.jpg"),
                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR, sessionId, X_Session_Id, X_Request_Id);
        }

//        assertEquals(variables.getActionType(), "complete");
//        assertEquals(variables.getStatus(), "timeout");

        apiResponses.updateRequestPutCall(preProcess, 434, preProcess.getMissingFields(), preProcess.getCaseId(), "sessionId", sessionId);


        assertTrue(preProcess.isSuccess());
        assertEquals(preProcess.getErrorCode(), 0);
        assertEquals(preProcess.getDataId(), "process-c6aa3851-8e6a-4752-b5f2-bfcc02c9439e");
        assertNotNull(preProcess.getInquiryId());
        assertFalse(preProcess.isDataSuccess());
        assertEquals(preProcess.getNextProcess(), "server_ocr");
        assertEquals((preProcess.getMissingFields()).substring(0, 9), "ocrCaseId");
        System.out.println(preProcess.getDataErrorCode());
        System.out.println(preProcess.getDataErrorMessage());
//        assertEquals(preProcess.getDataErrorCode(), 1015);
        assertNotNull(preProcess.getSessionId());
        assertNotNull(preProcess.getOcrTypes());
        assertFalse(preProcess.isBackStepAllow());

        preProcess.setCaseId(randomString());
        apiResponse.clientInitResponseNew(variables, "application/json", "caseId", preProcess.getCaseId(), "libraryName", "IL-ID", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR,sessionId, X_Session_Id, X_Request_Id, "clientTranslationFileName", clientTranslationFileValue, "devMode", devMode);


        apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/face_not_found.jpg"),
                "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR, sessionId, X_Session_Id, X_Request_Id);
        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/face_not_found.jpg"),
                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR, sessionId, X_Session_Id, X_Request_Id);
        }


//        assertEquals(variables.getActionType(), "complete");
//        assertEquals(variables.getStatus(), "timeout");


        apiResponses.updateRequestPutCall(preProcess, 434, preProcess.getMissingFields(), preProcess.getCaseId(), "sessionId", sessionId);

        assertTrue(preProcess.isSuccess());
        assertEquals(preProcess.getErrorCode(), 0);
        assertNotNull(preProcess.getInquiryId());
        assertEquals(preProcess.getNextProcess(), "process_ended");
        assertFalse(preProcess.isDataSuccess());
//        System.out.println(preProcess.getDataErrorCode());
        assertEquals(preProcess.getDataErrorCode(), 1018);
        assertNotNull(preProcess.getSessionId());


        apiResponses.getTokenFromCallbackRequest(preProcess);

        assertEquals(preProcess.getErrorCode(), 0);
        assertNotNull(preProcess.getData());
        assertNotNull(preProcess.getToken());

        utilities.endpointManager(preProcess, endpointResults, "v2");

        //Final results:
        assertTrue(endpointResults.isSuccess());
        assertEquals(endpointResults.getErrorCode(), 0);
        assertFalse(endpointResults.isDataSuccess());
        assertEquals(endpointResults.getDataErrorMessage().substring(0,44), "Client reached max retry attempts in process");
        assertEquals(endpointResults.getDataErrorCode(), 1018);

        assertNotNull(ILID.getFrontImage());
        assertEquals(ILID.getOcrType(), "IL-ID");
        assertFalse(ILID.isProcessSuccess());
    }

//    @Test(description = "Test mobile flow with second Attempt - Pass")
    @Description("Test mobile flow with second Attempt - Pass")
    public void t02_secondAttemptPass() throws IOException, ParseException {
        sessionId = apiResponses.createSessionId(469);
        apiResponses.initSession(preProcess, 469, sessionId);
        preProcess.setCaseId(randomString());


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
                "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/face_not_found.jpg"),
                "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/face_not_found.jpg"),
                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        }


        assertEquals(variables.getActionType(), "complete");
        assertEquals(variables.getStatus(), "timeout");


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
                "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/face_not_found.jpg"),
                "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/face_not_found.jpg"),
                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        }

        assertEquals(variables.getActionType(), "complete");
        assertEquals(variables.getStatus(), "timeout");

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
                "Bearer " + variables.getToken(), "frame", new File("./ocr/bioID/Adi/front/bioIdFront2.jpg"),
                "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        while (!variables.getActionType().equalsIgnoreCase("stage")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./ocr/bioID/Adi/front/bioIdFront2.jpg"),
                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        }

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./ocr/bioID/Adi/back/bioIdBack.jpg"),
                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        }


        assertEquals(variables.getActionType(), "complete");
        String ocrCaseId = preProcess.getCaseId();

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

        apiResponse.clientInitResponse(variables, "application/json", "caseId", preProcess.getCaseId(), "libraryName", "LIVENESS", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_LIVENESS);

        apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization",
                "Bearer " + variables.getToken(), "frame", new File("./liveness/Adi/adi.jpg"),
                "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./liveness/Adi/adi.jpg"),
                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);
        }


        assertEquals(variables.getActionType(), "complete");
        String livenessCaseId = preProcess.getCaseId();

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
        assertEquals(ILID.getDob(), "13.06.1985");
        assertEquals(ILID.getIssuingDate(), "28.07.2020");
        assertEquals(ILID.getExpiryDate(), "27.07.2030");
        assertEquals(ILID.getFirstName(), "עד");
        assertEquals(ILID.getLastName(), "רם-מזור");
        assertEquals(ILID.getDateOfBirth(), "1985-06-13T00:00:00.000+0000");
        assertEquals(ILID.getGender(), "נקבה");
        assertEquals(ILID.getIdNumber(), 21525290);
        assertEquals(ILID.getExpirationDate(), "2030-07-27T00:00:00.000+0000");
        assertNotNull(ILID.getFaceImage());
        assertNotNull(ILID.getFrontImage());
        assertNotNull(ILID.getCardImage());
        //       assertNotNull(ILID.getBackImage());
        assertEquals(ILID.getDocType(), "il_id_bio");
        assertEquals(ILID.getIssueDate(), "2020-07-28T00:00:00.000+0000");
        assertEquals(ILID.getPlaceOfBirth(), "ישראל");
        assertEquals(ILID.getCitizenship(), "אזרחות ישראלית");
        assertEquals(ILID.getOcrType(), "IL-ID");
        assertEquals(ILID.getBackSideIdNumber(), 21525290);


        assertEquals(Liveness.getProcess(), "liveness");
        assertNotNull(Liveness.getFaceImage());
        assertNotNull(Liveness.getVideo());
        assertTrue(Liveness.isProcessSuccess());

        assertEquals(BiometricMatch.getProcess(), "biometric_match");
        assertTrue(BiometricMatch.isProcessSuccess());
        assertTrue(Math.abs(BiometricMatch.getScore()-0.76)<0.05);
//        assertEquals(BiometricMatch.getScore(), 0.7691401839256287);
    }


    @Test(description = "Test mobile flow with comparator of 'id number'- receive data from ocr and external param - id number equal")
    @Description("Test mobile flow with comparator of 'id number'- receive data from ocr and external param - id number equal")
    public void t03_onboardingComparatorWithExternalParamsEqual() throws IOException, ParseException {
        sessionId = apiResponses.createExternalParamLink(658, "312534753");
        apiResponses.initSessionNew(preProcess, 658, sessionId, System.getProperty("COMPANY_ID"));
        preProcess.setCaseId(randomString());

        apiResponse.clientInitResponseNew(variables, "application/json", "caseId",  preProcess.getCaseId(), "libraryName", "IL-ID", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR, sessionId, X_Session_Id, X_Request_Id, "clientTranslationFileName", clientTranslationFileValue, "devMode", devMode);

        assertTrue(preProcess.isSuccess());
        assertEquals(preProcess.getErrorCode(), 0);
        assertEquals(preProcess.getDataId(), "process-897dec31-cc1f-4dff-a867-074163b95321");
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

        apiResponses.updateRequestPutCall(preProcess, 658, preProcess.getMissingFields(), ocrCaseId, "sessionId", sessionId);


        assertTrue(preProcess.isSuccess());
        assertEquals(preProcess.getErrorCode(), 0);
        assertNotNull(preProcess.getInquiryId());
        assertTrue(preProcess.isDataSuccess());
        assertEquals(preProcess.getNextProcess(), "process_ended");
        assertEquals(preProcess.getDataErrorCode(), 0);
        assertNotNull(preProcess.getSessionId());

        preProcess.setCaseId(randomString());
        variables = new Variables();


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
        assertEquals(ILID.getDocType(), "green");
        assertEquals(ILID.getIssueDate(), "2010-05-03T00:00:00.000+0000");
        assertEquals(ILID.getOcrType(), "IL-ID");
        assertTrue(ILID.isFacePosition());
        assertTrue(ILID.isValidityExpiryDate());
        assertTrue(ILID.isChecksum());
        assertTrue(ILID.isFaceSize());
        assertTrue(ILID.isTemplateMatching());
        assertTrue(ILID.isFaceRotation());
        assertTrue(ILID.isProcessSuccess());
        assertEquals(ILID.getCount0(), 1);
        assertTrue(ILID.isComparatorEquals());
        assertTrue(ILID.isComparatorSuccess());
    }

    @Test(description = "Test mobile flow with comparator of 'id number'- receive data from ocr and external param - id number not equal")
    @Description("Test mobile flow with comparator of 'id number'- receive data from ocr and external param - id number not equal")
    public void t04_onboardingComparatorWithExternalParamsNotEqual() throws IOException, ParseException {
        sessionId = apiResponses.createExternalParamLink(658, "300864550");
        apiResponses.initSessionNew(preProcess, 658, sessionId, System.getProperty("COMPANY_ID"));
        preProcess.setCaseId(randomString());


        apiResponse.clientInitResponseNew(variables, "application/json", "caseId",  preProcess.getCaseId(), "libraryName", "IL-ID", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR, sessionId, X_Session_Id, X_Request_Id, "clientTranslationFileName", clientTranslationFileValue, "devMode", devMode);

        assertTrue(preProcess.isSuccess());
        assertEquals(preProcess.getErrorCode(), 0);
        assertEquals(preProcess.getDataId(), "process-897dec31-cc1f-4dff-a867-074163b95321");
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

        apiResponses.updateRequestPutCall(preProcess, 658, preProcess.getMissingFields(), ocrCaseId, "sessionId", sessionId);


        assertTrue(preProcess.isSuccess());
        assertEquals(preProcess.getErrorCode(), 0);
        assertNotNull(preProcess.getInquiryId());
        assertFalse(preProcess.isDataSuccess());
        assertEquals(preProcess.getNextProcess(), "process_ended");
        assertEquals(preProcess.getDataErrorCode(), 1155);
        assertNotNull(preProcess.getSessionId());

        preProcess.setCaseId(randomString());
        variables = new Variables();


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
        assertEquals(endpointResults.getDataErrorMessage(), "ID_Number not equal");
        assertEquals(endpointResults.getDataErrorCode(), 1155);
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
        assertEquals(ILID.getDocType(), "green");
        assertEquals(ILID.getIssueDate(), "2010-05-03T00:00:00.000+0000");
        assertEquals(ILID.getOcrType(), "IL-ID");
        assertTrue(ILID.isFacePosition());
        assertTrue(ILID.isValidityExpiryDate());
        assertTrue(ILID.isChecksum());
        assertTrue(ILID.isFaceSize());
        assertTrue(ILID.isTemplateMatching());
        assertTrue(ILID.isFaceRotation());
        assertTrue(ILID.isProcessSuccess());
        assertEquals(ILID.getCount0(), 1);
        assertFalse(ILID.isComparatorEquals());
        assertTrue(ILID.isComparatorSuccess());
    }
}
