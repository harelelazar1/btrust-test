package api.ngocr.dl.oldDl;

import api.ApiResponse;
import api.Variables;
import btrust.onboardingProcess.api.variables.PreProcess;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.MongoDB.MongoHandler;
import utilities.TestUtils;

import java.io.File;
import java.io.IOException;

import static btrust.btrustOne.admin.BaseAdminUserTest.randomString;

public class SanityTest {


    Variables variables;
    MongoHandler mongoHandler;
    ApiResponse apiResponse;
    String sessionId;
    String X_Request_Id;
    String X_Session_Id;
    String clientTranslationFileValue;
    boolean devMode ;
    PreProcess preProcess;



    @BeforeMethod
    public void resetVariables() {
        preProcess = new PreProcess();
        variables = new Variables();
        mongoHandler = new MongoHandler();
        apiResponse = new ApiResponse();
        sessionId = "new" + randomString();
        X_Session_Id = "new" + randomString();
        X_Request_Id = "new" + randomString();
        clientTranslationFileValue = "scanovate_default_en.json";
        devMode = false;
    }

    @Test(description = "old dl api sanity test",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Api israel old driving license e2e sanity test")
    public void t01_e2eOldDlFlow() throws IOException {
        apiResponse.clientInitResponseNew(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "IL-DL", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR, sessionId, X_Session_Id, X_Request_Id, "clientTranslationFileName", clientTranslationFileValue, "devMode", devMode);

        apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                "Bearer " + variables.getToken(), "frame", new File("./ocr/dl/oldDLFront.jpg"),
                "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR, sessionId, X_Session_Id, X_Request_Id);


        while (!variables.getActionType().equalsIgnoreCase("message") && variables.getMessageId() == 103) {
            apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./ocr/dl/oldDLFront.jpg"),
                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR, sessionId, X_Session_Id, X_Request_Id);

        }

        Assert.assertEquals(variables.getActionType(), "message");
        Assert.assertTrue(variables.getMessageId()== 103 || variables.getMessageId()==101);
        Assert.assertEquals(variables.getMessageText(), "Card detected");

        while (!variables.getActionType().equalsIgnoreCase("stage")) {
            apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./ocr/dl/oldDLFront.jpg"),
                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR, sessionId, X_Session_Id, X_Request_Id);

        }

        Assert.assertEquals(variables.getActionType(), "stage");
        Assert.assertEquals(variables.getOrder(), 2);
        Assert.assertEquals(variables.getTypeUnderStage(), "back");
        Assert.assertEquals(variables.getStatus(), "success");

        while (variables.getMessageId() != 100) {
            apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./ocr/dl/oldDLFront.jpg"),
                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR, sessionId, X_Session_Id, X_Request_Id);
        }

        Assert.assertEquals(variables.getActionType(), "message");
        Assert.assertEquals(variables.getMessageId(), 100);
        Assert.assertEquals(variables.getMessageText(), "Wrong side");

        while (variables.getMessageId() != 103) {
            apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./ocr/dl/oldDLBack.jpg"),
                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR, sessionId, X_Session_Id, X_Request_Id);
        }

        Assert.assertEquals(variables.getActionType(), "message");
        Assert.assertEquals(variables.getMessageId(), 103);
        Assert.assertEquals(variables.getMessageText(), "Card detected");

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./ocr/dl/oldDLBack.jpg"),
                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR, sessionId, X_Session_Id, X_Request_Id);
        }

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "success");

        //Front side data
        Assert.assertEquals(variables.getActionType2(), "stage");
        Assert.assertEquals(variables.getOrder2(), 1);
        Assert.assertEquals(variables.getType2(), "front");
        Assert.assertEquals(variables.getLastNameHebrew(), "גרוס פישמן");
        Assert.assertEquals(variables.getLastNameEnglish(), "FISHMAMGROSS");
        Assert.assertEquals(variables.getFirstNameHebrew(), "מאיה");
        Assert.assertEquals(variables.getFirstNameEnglish(), "MAYA");
        Assert.assertEquals(variables.getDateOfBirth(), "06.01.1978");
        Assert.assertEquals(variables.getDateOfIssue(), "14.12.2006");
        Assert.assertEquals(variables.getDateOfExpiry(), "06.01.2017");
        Assert.assertEquals(variables.getIdNumber(), 34471045);
        Assert.assertEquals(variables.getLicenseNumber(), 6905739);
        Assert.assertEquals(variables.getAddress(), "אלוף דוד 58 רמת גן");
        Assert.assertEquals(variables.getLicenseCategories(), "B");
        Assert.assertEquals(variables.getCardType2(), "old_front");
        Assert.assertNotNull(variables.getFaceImage2());
        Assert.assertNotNull(variables.getInputImage2());
        Assert.assertNotNull(variables.getCroppedImage2());
        Assert.assertTrue(variables.isIdChecksumValid());
        Assert.assertTrue(variables.isTemplateMatched());
        Assert.assertTrue(variables.isDocumentInFrame());
        Assert.assertFalse(variables.isExpiryDateValid());
        Assert.assertTrue(variables.isFaceSizeValid());
        Assert.assertTrue(variables.isFacePositionValid());
        Assert.assertTrue(variables.isFaceRotationValid());
        Assert.assertEquals(variables.getStageStatus3(), "success");

        //Back side data
        Assert.assertEquals(variables.getActionType1(), "complete");
        Assert.assertEquals(variables.getOrder1(), 2);
        Assert.assertEquals(variables.getType1(), "back");
        Assert.assertEquals(variables.getIdNumber(), 34471045);
        Assert.assertEquals(variables.getbYear(), 1995);
        Assert.assertEquals(variables.getCardType1(), "old_back");
        Assert.assertNotNull(variables.getInputImage1());
        Assert.assertNotNull(variables.getCroppedImage1());
        Assert.assertTrue(variables.isIdChecksumValid2());
        Assert.assertTrue(variables.isTemplateMatched2());
        Assert.assertTrue(variables.isDocumentInFrame2());
        Assert.assertEquals(variables.getStageStatus2(), "success");

        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), TestUtils.getDefaultMathilda());
    }




}
