package btrust.onboardingProcess.api.v2.authenticationTests;

import api.ApiResponse;
import api.Variables;
import btrust.onboardingProcess.api.ApiResponses;
import btrust.onboardingProcess.api.Utilities;
import btrust.onboardingProcess.api.variables.*;
import io.qameta.allure.Description;
import org.json.simple.parser.ParseException;
import org.testng.ISuiteListener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.TestUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static btrust.btrustOne.admin.BaseAdminUserTest.randomString;
import static org.testng.Assert.*;


public class FaceRotation {

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
        clientTranslationFileValue = "scanovate_default_en.json";
        devMode = false;
    }


    @Test(description = "Onboarding Api Test BioId card Face Rotation Authentication Fail", retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Onboarding Api Test BioId card Face Rotation Authentication Fail")
    public void t01_bioIdFaceRotationAuthFail() throws IOException, ParseException {
        sessionId = apiResponses.createSessionId(1347);

        apiResponses.initSessionNew(preProcess, 1347, sessionId, System.getProperty("COMPANY_ID"));


        apiResponse.clientInitResponseNew(variables, "application/json", "caseId", preProcess.getCaseId(), "libraryName", "IL-ID", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR, sessionId, X_Session_Id, X_Request_Id, "clientTranslationFileName", clientTranslationFileValue, "devMode", devMode);

        assertTrue(preProcess.isSuccess());
        assertEquals(preProcess.getErrorCode(), 0);
        assertEquals(preProcess.getDataId(), "process-88c1f56e-0cdc-4873-8210-111a0ea82872");
        assertNotNull(preProcess.getInquiryId());
        assertTrue(preProcess.isDataSuccess());
        assertEquals(preProcess.getNextProcess(), "server_ocr");
        assertEquals((preProcess.getMissingFields()).substring(0, 9), "ocrCaseId");
        assertEquals(preProcess.getDataErrorCode(), 1001);
//        assertNotNull(preProcess.getSessionId());
        assertNotNull(preProcess.getOcrTypes());
        assertFalse(preProcess.isBackStepAllow());


        apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                "Bearer " + variables.getToken(), "frame", new File("./ocr/bioID/nitzan/auth/BioIdWithFaceRotation.jpg"),
                "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR, sessionId, X_Session_Id, X_Request_Id);
        while (!variables.getActionType().equalsIgnoreCase("stage")) {
            apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./ocr/bioID/nitzan/auth/BioIdWithFaceRotation.jpg"),
                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR, sessionId, X_Session_Id, X_Request_Id);
        }

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./ocr/bioID/nitzan/back/NitzanBioBack.jpg"),
                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR, sessionId, X_Session_Id, X_Request_Id);
        }


        assertEquals(variables.getActionType(), "complete");
        String ocrCaseId = variables.getCaseId();


        apiResponses.updateRequestPutCall(preProcess, 1347, preProcess.getMissingFields(), ocrCaseId, "sessionId", sessionId);

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
        assertEquals(endpointResults.getDataErrorMessage(), "Document authentication failed");
        assertEquals(endpointResults.getDataErrorCode(), 1026);
        assertEquals(endpointResults.getMetadata(), "");

        assertEquals(ILID.getProcess(), "ocr");
        assertNotNull(ILID.getEnd());
        assertEquals(ILID.getDob(), "21.08.1988");
        assertEquals(ILID.getIssuingDate(), "23.05.2019");
        assertEquals(ILID.getExpiryDate(), "20.05.2029");
        assertEquals(ILID.getFirstName(), "ניצן");
        assertEquals(ILID.getLastName(), "שוקר");
        assertEquals(ILID.getDateOfBirth(), "1988-08-21T00:00:00.000+0000");
        assertEquals(ILID.getGender(), "זכר");
        assertEquals(ILID.getIdNumber(), 200761625);
        assertEquals(ILID.getExpirationDate(), "2029-05-20T00:00:00.000+0000");
        assertNotNull(ILID.getFaceImage());
        assertNotNull(ILID.getFrontImage());
        assertNotNull(ILID.getCardImage());
        //       assertNotNull(ILID.getBackImage());
        assertEquals(ILID.getDocType(), "il_id_bio");
        assertEquals(ILID.getIssueDate(), "2019-05-23T00:00:00.000+0000");
        assertEquals(ILID.getPlaceOfBirth(), "ישראל");
        assertEquals(ILID.getCitizenship(), "אזרהות ישראלית");
        assertEquals(ILID.getOcrType(), "IL-ID");
        assertEquals(ILID.getBackSideIdNumber(), 200761625);
        assertTrue(ILID.isPeriodBetweenExpiryDateAndIssueDate());
        assertFalse(ILID.isFacePosition());
        assertTrue(ILID.isColorCheck());
//        assertTrue(ILID.isFaceStamp());
        assertTrue(ILID.isValidityExpiryDate());
        assertTrue(ILID.isChecksum());
        assertFalse(ILID.isFaceSize());
        assertTrue(ILID.isTemplateMatching());
        assertFalse(ILID.isFaceRotation());
        assertTrue(ILID.isProcessSuccess());
        assertEquals(ILID.getCount0(), 1);
    }

    @Test(description = "Onboarding Api Test BlueId card Face Rotation Authentication Fail",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Onboarding Api Test BlueId card Face Rotation Authentication Fail")
    public void t02_blueIdFaceRotationAuthFail() throws IOException, ParseException {
        sessionId = apiResponses.createSessionId(1347);

        apiResponses.initSessionNew(preProcess, 1347, sessionId, System.getProperty("COMPANY_ID"));


        apiResponse.clientInitResponseNew(variables, "application/json", "caseId", preProcess.getCaseId(), "libraryName", "IL-ID", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR, sessionId, X_Session_Id, X_Request_Id, "clientTranslationFileName", clientTranslationFileValue, "devMode", devMode);

        assertTrue(preProcess.isSuccess());
        assertEquals(preProcess.getErrorCode(), 0);
        assertEquals(preProcess.getDataId(), "process-88c1f56e-0cdc-4873-8210-111a0ea82872");
        assertNotNull(preProcess.getInquiryId());
        assertTrue(preProcess.isDataSuccess());
        assertEquals(preProcess.getNextProcess(), "server_ocr");
        assertEquals((preProcess.getMissingFields()).substring(0, 9), "ocrCaseId");
        assertEquals(preProcess.getDataErrorCode(), 1001);
//        assertNotNull(preProcess.getSessionId());
        assertNotNull(preProcess.getOcrTypes());
        assertFalse(preProcess.isBackStepAllow());


        apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                "Bearer " + variables.getToken(), "frame", new File("./ocr/blueID/auth/blueIdWithFaceRotated.jpg"),
                "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR, sessionId, X_Session_Id, X_Request_Id);

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./ocr/blueID/auth/blueIdWithFaceRotated.jpg"),
                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR, sessionId, X_Session_Id, X_Request_Id);
        }


        assertEquals(variables.getActionType(), "complete");
        String ocrCaseId = variables.getCaseId();


        apiResponses.updateRequestPutCall(preProcess, 1347, preProcess.getMissingFields(), ocrCaseId, "sessionId", sessionId);

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
        assertEquals(endpointResults.getDataErrorMessage(), "Document authentication failed");
        assertEquals(endpointResults.getDataErrorCode(), 1026);
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
        assertEquals(ILID.getDocType(), "blue");
        assertEquals(ILID.getIssueDate(), "2015-01-28T00:00:00.000+0000");
        assertEquals(ILID.getPlaceOfBirth(), "ישראל");
        assertEquals(ILID.getOcrType(), "IL-ID");
        assertTrue(ILID.isPeriodBetweenExpiryDateAndIssueDate());
        assertTrue(ILID.isFacePosition());
        assertTrue(ILID.isColorCheck());
//        assertTrue(ILID.isFaceStamp());
        assertTrue(ILID.isValidityExpiryDate());
        assertTrue(ILID.isChecksum());
        assertFalse(ILID.isFaceSize());
        assertTrue(ILID.isTemplateMatching());
        assertFalse(ILID.isFaceRotation());
        assertTrue(ILID.isProcessSuccess());
        assertEquals(ILID.getCount0(), 1);
    }

    @Test(description = "Onboarding Api Test GreenId card Face Rotation Authentication Fail",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Onboarding Api Test GreenId card Face Rotation Authentication Fail")
    public void t03_greenIdFaceRotationAuthFail() throws IOException, ParseException {
        sessionId = apiResponses.createSessionId(1347);

        apiResponses.initSessionNew(preProcess, 1347, sessionId, System.getProperty("COMPANY_ID"));


        apiResponse.clientInitResponseNew(variables, "application/json", "caseId", preProcess.getCaseId(), "libraryName", "IL-ID", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR, sessionId, X_Session_Id, X_Request_Id, "clientTranslationFileName", clientTranslationFileValue, "devMode", devMode);

        assertTrue(preProcess.isSuccess());
        assertEquals(preProcess.getErrorCode(), 0);
        assertEquals(preProcess.getDataId(), "process-88c1f56e-0cdc-4873-8210-111a0ea82872");
        assertNotNull(preProcess.getInquiryId());
        assertTrue(preProcess.isDataSuccess());
        assertEquals(preProcess.getNextProcess(), "server_ocr");
        assertEquals((preProcess.getMissingFields()).substring(0, 9), "ocrCaseId");
        assertEquals(preProcess.getDataErrorCode(), 1001);
//        assertNotNull(preProcess.getSessionId());
        assertNotNull(preProcess.getOcrTypes());
        assertFalse(preProcess.isBackStepAllow());


        apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                "Bearer " + variables.getToken(), "frame", new File("./ocr/greenID/auth/GreenIdWithFaceRotated.jpg"),
                "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR, sessionId, X_Session_Id, X_Request_Id);

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./ocr/greenID/auth/GreenIdWithFaceRotated.jpg"),
                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR, sessionId, X_Session_Id, X_Request_Id);
        }


        assertEquals(variables.getActionType(), "complete");
        String ocrCaseId = variables.getCaseId();


        apiResponses.updateRequestPutCall(preProcess, 1347, preProcess.getMissingFields(), ocrCaseId, "sessionId", sessionId);

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
        assertEquals(endpointResults.getDataErrorMessage(), "Document authentication failed");
        assertEquals(endpointResults.getDataErrorCode(), 1026);
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
        assertEquals(ILID.getPlaceOfBirth(), "ישראל");
        assertEquals(ILID.getOcrType(), "IL-ID");
        assertTrue(ILID.isFacePosition());
        assertTrue(ILID.isColorCheck());
        assertFalse(ILID.isFaceStamp());
        assertTrue(ILID.isChecksum());
        assertTrue(ILID.isFaceSize());
        assertTrue(ILID.isTemplateMatching());
        assertFalse(ILID.isFaceRotation());
        assertTrue(ILID.isProcessSuccess());
        assertEquals(ILID.getCount0(), 1);
    }



    @Test(description = "Onboarding Api Test ILDL card Face Rotation Authentication Fail")
    @Description("Onboarding Api Test ILDL card Face Rotation Authentication Fail")
    public void t04_ilDlFaceRotationAuthFail() throws IOException, ParseException {
        sessionId = apiResponses.createSessionId(1347);

        apiResponses.initSessionNew(preProcess, 1347, sessionId, System.getProperty("COMPANY_ID"));


        apiResponse.clientInitResponseNew(variables, "application/json", "caseId", preProcess.getCaseId(), "libraryName", "IL-DL", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR, sessionId, X_Session_Id, X_Request_Id,"clientTranslationFileName",clientTranslationFileValue,"devMode",devMode);

        assertTrue(preProcess.isSuccess());
        assertEquals(preProcess.getErrorCode(), 0);
        assertEquals(preProcess.getDataId(), "process-88c1f56e-0cdc-4873-8210-111a0ea82872");
        assertNotNull(preProcess.getInquiryId());
        assertTrue(preProcess.isDataSuccess());
        assertEquals(preProcess.getNextProcess(), "server_ocr");
        assertEquals((preProcess.getMissingFields()).substring(0, 9), "ocrCaseId");
        assertEquals(preProcess.getDataErrorCode(), 1001);
//        assertNotNull(preProcess.getSessionId());
        assertNotNull(preProcess.getOcrTypes());
        assertFalse(preProcess.isBackStepAllow());


        apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                "Bearer " + variables.getToken(), "frame", new File("./ocr/dl/auth/new/NewDlWithFaceRotated.jpg"),
                "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR, sessionId, X_Session_Id, X_Request_Id);
        while (!variables.getActionType().equalsIgnoreCase("stage")) {
            apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./ocr/dl/auth/new/NewDlWithFaceRotated.jpg"),
                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR, sessionId, X_Session_Id, X_Request_Id);
        }

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./ocr/dl/newDLBack.jpg"),
                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR, sessionId, X_Session_Id, X_Request_Id);
        }


        assertEquals(variables.getActionType(), "complete");
        String ocrCaseId = variables.getCaseId();


        apiResponses.updateRequestPutCall(preProcess, 1347, preProcess.getMissingFields(), ocrCaseId, "sessionId", sessionId);

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
        assertEquals(endpointResults.getDataErrorMessage(), "Document authentication failed");
        assertEquals(endpointResults.getDataErrorCode(), 1026);
        assertEquals(endpointResults.getMetadata(), "");

        assertEquals(ILDL.getProcess(), "ocr");
        assertNotNull(ILDL.getEnd());
        assertEquals(ILDL.getDob(), "26.03.1992");
        assertEquals(ILDL.getIssuingDate(), "31.03.2016");
        assertEquals(ILDL.getExpiryDate(), "26.03.2026");
        assertEquals(ILDL.getFirstName(), "מנחם מאור");
        assertEquals(ILDL.getLastName(), "עבוש");
        assertEquals(ILDL.getDateOfBirth(), "1992-03-26T00:00:00.000+0000");
        assertEquals(ILDL.getAddress(), "ביאליק 40א בית שמש");
        assertEquals(ILDL.getIdNumber(), 203114798);
        assertEquals(ILDL.getExpirationDate(), "2026-03-26T00:00:00.000+0000");
        assertNotNull(ILDL.getFaceImage());
        assertNotNull(ILDL.getFrontImage());
        assertNotNull(ILDL.getCardImage());
        //       assertNotNull(ILID.getBackImage());
        assertEquals(ILDL.getDocType(), "new_back");
        assertEquals(ILDL.getIssueDate(), "2016-03-31T00:00:00.000+0000");
        assertEquals(ILDL.getLicenseCategory(), "A1 B");
        assertEquals(ILDL.getLicenseIssuingYear(), "2009-01-01T00:00:00.000+0000");
        assertEquals(ILDL.getOcrType(), "IL-DL");
        assertEquals(ILDL.getBackSideIdNumber(), 203114798);
        assertTrue(ILDL.isChecksum());
        assertFalse(ILDL.isFacePosition());
        assertFalse(ILDL.isFaceRotation());
        assertFalse(ILDL.isFaceSize());
        assertTrue(ILDL.isValidityExpiryDate());
        assertTrue(ILDL.isTemplateMatching());

        assertTrue(ILDL.isProcessSuccess());
        assertEquals(ILDL.getCount(), 1);
    }



    @Test(description = "Onboarding Api Test MRZ card Face Rotation Authentication Fail")
    @Description("Onboarding Api Test MRZ card Face Rotation Authentication Fail")
    public void t05_mrzFaceRotationAuthFail() throws IOException, ParseException {
        sessionId = apiResponses.createSessionId(1347);

        apiResponses.initSessionNew(preProcess, 1347, sessionId, System.getProperty("COMPANY_ID"));


        apiResponse.clientInitResponseNew(variables, "application/json", "caseId", preProcess.getCaseId(), "libraryName", "MRZ", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR, sessionId, X_Session_Id, X_Request_Id, "clientTranslationFileName", clientTranslationFileValue, "devMode", devMode);

        assertTrue(preProcess.isSuccess());
        assertEquals(preProcess.getErrorCode(), 0);
        assertEquals(preProcess.getDataId(), "process-88c1f56e-0cdc-4873-8210-111a0ea82872");
        assertNotNull(preProcess.getInquiryId());
        assertTrue(preProcess.isDataSuccess());
        assertEquals(preProcess.getNextProcess(), "server_ocr");
        assertEquals((preProcess.getMissingFields()).substring(0, 9), "ocrCaseId");
        assertEquals(preProcess.getDataErrorCode(), 1001);
//        assertNotNull(preProcess.getSessionId());
        assertNotNull(preProcess.getOcrTypes());
        assertFalse(preProcess.isBackStepAllow());


        apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                "Bearer " + variables.getToken(), "frame", new File("./ocr/mrz/nadav/nadavMrzWithFaceRoatation.jpg"),
                "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR, sessionId, X_Session_Id, X_Request_Id);

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./ocr/mrz/nadav/nadavMrzWithFaceRoatation.jpg"),
                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR, sessionId, X_Session_Id, X_Request_Id);
        }


        assertEquals(variables.getActionType(), "complete");
        String ocrCaseId = variables.getCaseId();


        apiResponses.updateRequestPutCall(preProcess, 1347, preProcess.getMissingFields(), ocrCaseId, "sessionId", sessionId);

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
        assertEquals(endpointResults.getDataErrorMessage(), "Document authentication failed");
        assertEquals(endpointResults.getDataErrorCode(), 1026);
        assertEquals(endpointResults.getMetadata(), "");

        assertEquals(MRZ.getProcess(), "ocr");
        assertNotNull(MRZ.getEnd());
        assertEquals(MRZ.getDob(), "08.09.1996");
        assertEquals(MRZ.getExpiryDate(), "18.05.2026");
        assertEquals(MRZ.getFirstName(), "NADAV");
        assertEquals(MRZ.getLastName(), "SHLOMO");
        assertEquals(MRZ.getDateOfBirth(), "1996-09-08T00:00:00.000+0000");
        assertEquals(MRZ.getNationalityName(), "Israel");
        assertEquals(MRZ.getNationalityAlpha2(), "IL");
        assertEquals(MRZ.getNationalityAlpha3(), "ISR");
        assertEquals(MRZ.getGender(), "Male");
        assertEquals(MRZ.getIdNumber(), "3-1539659-8");
        assertEquals(MRZ.getDocumentNumber(), 31027245);
        assertEquals(MRZ.getExpirationDate(), "2026-05-18T00:00:00.000+0000");
        assertEquals(MRZ.getIssuingCountryName(), "Israel");
        assertEquals(MRZ.getIssuingCountryAlpha2(), "IL");
        assertEquals(MRZ.getIssuingCountryAlpha3(), "ISR");
        assertEquals(MRZ.getCountryCode(), "IL");
        assertNotNull(MRZ.getFaceImage());
//        assertNotNull(MRZ.getFrontImage());
        assertNotNull(MRZ.getCardImage());
        assertEquals(MRZ.getDocType(), "israel_passport");
        assertEquals(MRZ.getOcrType(), "MRZ");
        assertTrue(MRZ.isValidityExpiryDate());
        assertTrue(MRZ.isChecksum());
        assertTrue(MRZ.isTemplateMatching());
        assertFalse(MRZ.isFacePosition());
        assertFalse(MRZ.isFaceRotation());
        assertFalse(MRZ.isFaceSize());
        assertTrue(MRZ.isProcessSuccess());
        assertEquals(MRZ.getCount(), 1);
    }
}
