package btrust.onboardingProcess.api.v2.authenticationTests;

import api.ApiResponse;
import api.Variables;
import btrust.onboardingProcess.api.ApiRequests;
import btrust.onboardingProcess.api.ApiResponses;
import btrust.onboardingProcess.api.Utilities;
import btrust.onboardingProcess.api.variables.EndpointResults;
import btrust.onboardingProcess.api.variables.ILID;
import btrust.onboardingProcess.api.variables.PreProcess;
import com.google.gson.Gson;
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
public class ChipAuth {

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



    @Test(description = "Onboarding Api Test BioId card Chip Authentication Fail")
    @Description("Onboarding Api Test BioId card Chip Authentication Fail")
    public void t01_bioIdChipAuthFail() throws IOException, ParseException {
        sessionId = apiResponses.createSessionId(1344);

        apiResponses.initSessionNew(preProcess, 1344, sessionId, System.getProperty("COMPANY_ID"));


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
                "Bearer " + variables.getToken(), "frame", new File("./ocr/bioID/nitzan/auth/bioIdChipCovered.jpg"),
                "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR,sessionId, X_Session_Id, X_Request_Id);
        while (!variables.getActionType().equalsIgnoreCase("stage")) {
            apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./ocr/bioID/nitzan/auth/bioIdChipCovered.jpg"),
                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR, sessionId, X_Session_Id, X_Request_Id);
        }

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./ocr/bioID/nitzan/back/NitzanBioBack.jpg"),
                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR, sessionId, X_Session_Id, X_Request_Id);
        }


        assertEquals(variables.getActionType(), "complete");
        String ocrCaseId = variables.getCaseId();

        apiResponses.updateRequestPutCall(preProcess, 1344, preProcess.getMissingFields(), ocrCaseId, "sessionId", sessionId);


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
        assertEquals(ILID.getDocType(), "il_id_bio");
        assertEquals(ILID.getIssueDate(), "2019-05-23T00:00:00.000+0000");
        assertEquals(ILID.getPlaceOfBirth(), "ישראל");
        assertEquals(ILID.getCitizenship(),"אזרהות ישראלית");
        assertEquals(ILID.getOcrType(), "IL-ID");
        assertEquals(ILID.getBackSideIdNumber(),200761625);
        assertTrue(ILID.isValidateIfFrontAndBacksideIdMatch());
        assertTrue(ILID.isPeriodBetweenExpiryDateAndIssueDate());
//        assertTrue(ILID.isIssueDateValid());
        assertTrue(ILID.isFacePosition());
        assertTrue(ILID.isColorCheck());
        assertTrue(ILID.isValidityExpiryDate());
        assertTrue(ILID.isChecksum());
        assertTrue(ILID.isFaceSize());
        assertTrue(ILID.isTemplateMatching());
        assertTrue(ILID.isFaceRotation());
        assertTrue(ILID.isProcessSuccess());
        assertFalse(ILID.isDocumentChipValid());
        assertEquals(ILID.getCount0(), 1);
    }



    @Test(description = "Onboarding Api Test BioId new card Chip Authentication Fail")
    @Description("Onboarding Api Test BioId new card Chip Authentication Fail")
    public void t02_bioIdNewChipAuthFail() throws IOException, ParseException {
        sessionId = apiResponses.createSessionId(1344);

        apiResponses.initSessionNew(preProcess, 1344, sessionId, System.getProperty("COMPANY_ID"));


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
                "Bearer " + variables.getToken(), "frame", new File("./ocr/new bioID/Harel/newBioIDFront_without_chip.jpg"),
                "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR, sessionId, X_Session_Id, X_Request_Id);
        while (!variables.getActionType().equalsIgnoreCase("stage")) {
            apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./ocr/new bioID/Harel/newBioIDFront_without_chip.jpg"),
                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR, sessionId, X_Session_Id, X_Request_Id);
        }

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./ocr/new bioID/Harel/newBioIDBack.jpg"),
                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR, sessionId, X_Session_Id, X_Request_Id);
        }


        assertEquals(variables.getActionType(), "complete");
        String ocrCaseId = variables.getCaseId();


        apiResponses.updateRequestPutCall(preProcess, 1344, preProcess.getMissingFields(), ocrCaseId, "sessionId", sessionId);

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
        assertEquals(ILID.getIssuingDate(), "16.02.2023");
        assertEquals(ILID.getExpiryDate(), "13.02.2033");
        assertEquals(ILID.getFirstName(), "הראל");
        assertEquals(ILID.getLastName(), "אלעזר");
        assertEquals(ILID.getDateOfBirth(), "1987-06-30T00:00:00.000+0000");
        assertEquals(ILID.getGender(), "זכר");
        assertEquals(ILID.getIdNumber(), 300864550);
        assertEquals(ILID.getExpirationDate(), "2033-02-13T00:00:00.000+0000");
        assertNotNull(ILID.getFaceImage());
        assertNotNull(ILID.getFrontImage());
        assertNotNull(ILID.getCardImage());
        //       assertNotNull(ILID.getBackImage());
        assertEquals(ILID.getDocType(), "il_id_bio");
        assertEquals(ILID.getIssueDate(), "2023-02-16T00:00:00.000+0000");
        assertEquals(ILID.getPlaceOfBirth(), "ישראל");
        assertEquals(ILID.getCitizenship(), "אזרחות ישראלית");
        assertEquals(ILID.getOcrType(), "IL-ID");
        assertEquals(ILID.getBackSideIdNumber(), 300864550);

        assertTrue(ILID.isPeriodBetweenExpiryDateAndIssueDate());
        assertTrue(ILID.isFacePosition());
        assertTrue(ILID.isColorCheck());
        assertTrue(ILID.isValidityExpiryDate());
        assertTrue(ILID.isChecksum());
        assertTrue(ILID.isFaceSize());
        assertTrue(ILID.isTemplateMatching());
        assertTrue(ILID.isFaceRotation());
        assertTrue(ILID.isTemplateMatchingBackside());
        assertFalse(ILID.isDocumentChipValid());

        assertTrue(ILID.isProcessSuccess());
        assertEquals(ILID.getCount0(), 1);
    }
}
