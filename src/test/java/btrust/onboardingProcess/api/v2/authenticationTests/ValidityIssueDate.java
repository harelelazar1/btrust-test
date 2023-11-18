package btrust.onboardingProcess.api.v2.authenticationTests;

import api.ApiResponse;
import api.Variables;
import btrust.onboardingProcess.api.ApiResponses;
import btrust.onboardingProcess.api.Utilities;
import btrust.onboardingProcess.api.variables.EndpointResults;
import btrust.onboardingProcess.api.variables.ILID;
import btrust.onboardingProcess.api.variables.PreProcess;
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

@Listeners(SuiteListener.class)
public class ValidityIssueDate {



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
    boolean devMode ;

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


    @Test(description = "Onboarding Api Test GreenId Issue date valid Authentication Fail",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Onboarding Api Test GreenId Issue date valid Authentication Fail")
    public void t01_greenIdIssueDateValidAuthFail() throws IOException, ParseException {
        sessionId = apiResponses.createSessionId(1358);

        apiResponses.initSessionNew(preProcess, 1358, sessionId, System.getProperty("COMPANY_ID"));


        apiResponse.clientInitResponseNew(variables, "application/json", "caseId", preProcess.getCaseId(), "libraryName", "IL-ID", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR, sessionId, X_Session_Id, X_Request_Id,"clientTranslationFileName",clientTranslationFileValue,"devMode",devMode);

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
                "Bearer " + variables.getToken(), "frame", new File("./ocr/greenID/harel/harel_Green_Id_Issue_Date2.jpg"),
                "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR, sessionId, X_Session_Id, X_Request_Id);

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./ocr/greenID/harel/harel_Green_Id_Issue_Date2.jpg"),
                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR, sessionId, X_Session_Id, X_Request_Id);
        }


        assertEquals(variables.getActionType(), "complete");
        String ocrCaseId = variables.getCaseId();


        apiResponses.updateRequestPutCall(preProcess, 1358, preProcess.getMissingFields(), ocrCaseId, "sessionId", sessionId);

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
        assertEquals(ILID.getDob(), "30.06.1987");
        assertEquals(ILID.getIssuingDate(), "11.01.1937");
        assertEquals(ILID.getFirstName(), "הראל");
        assertEquals(ILID.getLastName(), "אלעזר");
        assertEquals(ILID.getDateOfBirth(), "1987-06-30T00:00:00.000+0000");
        assertEquals(ILID.getGender(), "זכר");
        assertEquals(ILID.getIdNumber(), 300864550);
        assertNotNull(ILID.getFaceImage());
        assertNotNull(ILID.getFrontImage());
        assertNotNull(ILID.getCardImage());
        assertEquals(ILID.getDocType(), "green");
        assertEquals(ILID.getIssueDate(), "1937-01-11T00:00:00.000+0000");
        assertEquals(ILID.getPlaceOfBirth(), "ישראל");
        assertEquals(ILID.getOcrType(), "IL-ID");
        assertFalse(ILID.isIssueDateValid());
        assertTrue(ILID.isFacePosition());
        assertFalse(ILID.isFaceStamp());
        assertTrue(ILID.isValidityExpiryDate());
        assertTrue(ILID.isChecksum());
        assertTrue(ILID.isFaceSize());
        assertTrue(ILID.isTemplateMatching());
        assertTrue(ILID.isFaceRotation());
        assertTrue(ILID.isProcessSuccess());
        assertEquals(ILID.getCount0(), 1);
    }


    @Test(description = "Onboarding Api Test blueID Issue date valid Authentication Fail",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Onboarding Api Test blueID Issue date valid Authentication Fail")
    public void t02_blueIdIssueDateValidAuthFail() throws IOException, ParseException {
        sessionId = apiResponses.createSessionId(1358);

        apiResponses.initSessionNew(preProcess, 1358, sessionId, System.getProperty("COMPANY_ID"));


        apiResponse.clientInitResponseNew(variables, "application/json", "caseId", preProcess.getCaseId(), "libraryName", "IL-ID", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR, sessionId, X_Session_Id, X_Request_Id,"clientTranslationFileName",clientTranslationFileValue,"devMode",devMode);

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
                "Bearer " + variables.getToken(), "frame", new File("./ocr/blueID/liad/blueId_issueDate.jpg"),
                "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR, sessionId, X_Session_Id, X_Request_Id);

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./ocr/blueID/liad/blueId_issueDate.jpg"),
                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR, sessionId, X_Session_Id, X_Request_Id);
        }


        assertEquals(variables.getActionType(), "complete");
        String ocrCaseId = variables.getCaseId();


        apiResponses.updateRequestPutCall(preProcess, 1358, preProcess.getMissingFields(), ocrCaseId, "sessionId", sessionId);

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
        assertEquals(ILID.getIssuingDate(), "28.01.2012");
        assertEquals(ILID.getExpiryDate(), "28.01.2025");
        assertEquals(ILID.getFirstName(), "ליעדו");
        assertEquals(ILID.getLastName(), "טובי");
        assertEquals(ILID.getDateOfBirth(), "1993-01-17T00:00:00.000+0000");
        assertEquals(ILID.getGender(), "זכר");
        assertEquals(ILID.getIdNumber(), 307922328);
        assertEquals(ILID.getExpirationDate(), "2025-01-28T00:00:00.000+0000");
        assertNotNull(ILID.getFaceImage());
        assertNotNull(ILID.getFrontImage());
        assertNotNull(ILID.getCardImage());
        assertEquals(ILID.getDocType(), "blue");
        assertEquals(ILID.getIssueDate(), "2012-01-28T00:00:00.000+0000");
        assertEquals(ILID.getPlaceOfBirth(), "ישראל׳");
        assertEquals(ILID.getCitizenship(),"אזרחות ישראלית");
        assertEquals(ILID.getOcrType(), "IL-ID");
        assertFalse(ILID.isIssueDateValid());
        assertFalse(ILID.isPeriodBetweenExpiryDateAndIssueDate());
        assertTrue(ILID.isFacePosition());
        assertTrue(ILID.isColorCheck());
        assertTrue(ILID.isFaceStamp());
        assertTrue(ILID.isValidityExpiryDate());
        assertTrue(ILID.isChecksum());
        assertTrue(ILID.isFaceSize());
        assertTrue(ILID.isTemplateMatching());
        assertTrue(ILID.isFaceRotation());
        assertTrue(ILID.isProcessSuccess());
        assertEquals(ILID.getCount0(), 1);
    }


    @Test(description = "Onboarding Api Test bioID Issue date valid Authentication Fail")
    @Description("Onboarding Api Test bioID Issue date valid Authentication Fail")
    public void t03_bioIdIssueDateValidAuthFail() throws IOException, ParseException {
        sessionId = apiResponses.createSessionId(1358);

        apiResponses.initSessionNew(preProcess, 1358, sessionId, System.getProperty("COMPANY_ID"));


        apiResponse.clientInitResponseNew(variables, "application/json", "caseId", preProcess.getCaseId(), "libraryName", "IL-ID", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR, sessionId, X_Session_Id, X_Request_Id,"clientTranslationFileName",clientTranslationFileValue,"devMode",devMode);

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
                "Bearer " + variables.getToken(), "frame", new File("./ocr/bioID/nimrod/Bio_Front_IssueDate1.jpg"),
                "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR, sessionId, X_Session_Id, X_Request_Id);
        while (!variables.getActionType().equalsIgnoreCase("stage")) {
            apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./ocr/bioID/nimrod/Bio_Front_IssueDate1.jpg"),
                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR, sessionId, X_Session_Id, X_Request_Id);
        }

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./ocr/bioID/nimrod/nimrod_Bio_Back.jpg"),
                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR, sessionId, X_Session_Id, X_Request_Id);
        }


        assertEquals(variables.getActionType(), "complete");
        String ocrCaseId = variables.getCaseId();


        apiResponses.updateRequestPutCall(preProcess, 1358, preProcess.getMissingFields(), ocrCaseId, "sessionId", sessionId);

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
        assertEquals(ILID.getDob(), "17.05.1981");
        assertEquals(ILID.getIssuingDate(), "05.12.2011");
        assertEquals(ILID.getExpiryDate(), "01.12.2031");
        assertEquals(ILID.getFirstName(), "נמרוד");
        assertEquals(ILID.getLastName(), "בורוכוב");
        assertEquals(ILID.getDateOfBirth(), "1981-05-17T00:00:00.000+0000");
        assertEquals(ILID.getGender(), "זכר");
        assertEquals(ILID.getIdNumber(), 31726177);
        assertEquals(ILID.getExpirationDate(), "2031-12-01T00:00:00.000+0000");
        assertNotNull(ILID.getFaceImage());
        assertNotNull(ILID.getFrontImage());
        assertNotNull(ILID.getCardImage());
        assertEquals(ILID.getDocType(), "il_id_bio");
        assertEquals(ILID.getIssueDate(), "2011-12-05T00:00:00.000+0000");
        assertEquals(ILID.getPlaceOfBirth(), "ישראל");
        assertEquals(ILID.getCitizenship(),"אזרחות ישראלית");
        assertEquals(ILID.getOcrType(), "IL-ID");
        assertEquals(ILID.getBackSideIdNumber(),31726177);
        assertTrue(ILID.isChecksum());
        assertTrue(ILID.isChecksumBackside());
        assertTrue(ILID.isFacePosition());
        assertTrue(ILID.isDocumentChipValid());
        assertTrue(ILID.isFaceSize());
        assertTrue(ILID.isDocumentInFrame());
        assertTrue(ILID.isColorCheck());
        assertTrue(ILID.isColourfulImageBackSide());
        assertTrue(ILID.isValidateIfFrontAndBacksideIdMatch());
        assertTrue(ILID.isValidityExpiryDate());
        assertTrue(ILID.isTemplateMatching());
        assertTrue(ILID.isTemplateMatchingBackside());
        assertTrue(ILID.isFaceRotation());
        assertTrue(ILID.isFaceImageReplacedWithPassportImage());
        assertFalse(ILID.isPeriodBetweenExpiryDateAndIssueDate());
        assertFalse(ILID.isIssueDateValid());
        assertTrue(ILID.isFaceImageReplacedWithPassportImage());
        assertTrue(ILID.isProcessSuccess());
        assertEquals(ILID.getCount0(), 1);
    }



}
