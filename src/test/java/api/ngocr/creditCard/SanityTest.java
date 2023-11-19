package api.ngocr.creditCard;

import api.ApiResponse;
import api.Variables;
import btrust.onboardingProcess.api.variables.PreProcess;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
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
    boolean devMode;
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

    @Test(description = "Visa Cal sanity",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Visa Cal sanity, check card number and expiry")
    public void t01_visaCalDemo() throws IOException, InterruptedException {
        apiResponse.clientInitResponseNew(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "C-CARD", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR, sessionId, X_Session_Id, X_Request_Id, "clientTranslationFileName", clientTranslationFileValue, "devMode", devMode);

        apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                "Bearer " + variables.getToken(), "frame", new File("./ocr/creditCard/visaCal.jpg"),
                "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR, sessionId, X_Session_Id, X_Request_Id);


        Assert.assertEquals(variables.getActionType(), "message");
        Assert.assertTrue(variables.getMessageId() == 101 || variables.getMessageId() == 103);
        Assert.assertEquals(variables.getMessageText(), "Card detected");

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./ocr/creditCard/visaCal.jpg"),
                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR, sessionId, X_Session_Id, X_Request_Id);

        }

        Assert.assertEquals(variables.getActionType2(), "complete");
        Assert.assertEquals(variables.getStatus1(), "success");

        //Front section
        Assert.assertEquals(variables.getCardType1(), "credit_card_front");
        Assert.assertEquals(variables.getCreditCardNumber(), "5521830000317254");
        Assert.assertEquals(variables.getDateOfExpiry(), "01/26");
        //Images
        Assert.assertNotNull(variables.getInputImage());
        Assert.assertNotNull(variables.getCroppedImage());
    }

    @Test(description = "Leumi Card sanity", retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Leumi Card sanity, check card number and expiry")
    public void t02_leumiCardDemo() throws IOException, InterruptedException {
        apiResponse.clientInitResponseNew(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "C-CARD", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR, sessionId, X_Session_Id, X_Request_Id, "clientTranslationFileName", clientTranslationFileValue, "devMode", devMode);

        apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                "Bearer " + variables.getToken(), "frame", new File("./ocr/creditCard/leumiCard.jpg"),
                "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR, sessionId, X_Session_Id, X_Request_Id);


        Assert.assertEquals(variables.getActionType(), "message");
        Assert.assertTrue(variables.getMessageId() == 101 || variables.getMessageId() == 103);
        Assert.assertEquals(variables.getMessageText(), "Card detected");

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./ocr/creditCard/leumiCard.jpg"),
                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR, sessionId, X_Session_Id, X_Request_Id);

        }

        Assert.assertEquals(variables.getActionType2(), "complete");
        Assert.assertEquals(variables.getStatus1(), "success");

        //Front section
        Assert.assertEquals(variables.getCardType1(), "credit_card_front");
        Assert.assertEquals(variables.getCreditCardNumber(), "4580110790813114");
        Assert.assertEquals(variables.getDateOfExpiry(), "03/20");

        //Images
        Assert.assertNotNull(variables.getInputImage());
        Assert.assertNotNull(variables.getCroppedImage());
    }


    @Test(description = "MasterCard sanity", retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("MasterCard sanity, check card number and expiry")
    public void t03_masterCardDemo() throws IOException, InterruptedException {
        apiResponse.clientInitResponseNew(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "C-CARD", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR, sessionId, X_Session_Id, X_Request_Id, "clientTranslationFileName", clientTranslationFileValue, "devMode", devMode);

        apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                "Bearer " + variables.getToken(), "frame", new File("./ocr/creditCard/masterCard.jpg"),
                "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR, sessionId, X_Session_Id, X_Request_Id);


        Assert.assertEquals(variables.getActionType(), "message");
        Assert.assertTrue(variables.getMessageId() == 101 || variables.getMessageId() == 103);
        Assert.assertEquals(variables.getMessageText(), "Card detected");

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponseNew(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./ocr/creditCard/masterCard.jpg"),
                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR, sessionId, X_Session_Id, X_Request_Id);

        }

        Assert.assertEquals(variables.getActionType2(), "complete");
        Assert.assertEquals(variables.getStatus1(), "success");

        //Front section
        Assert.assertEquals(variables.getCardType1(), "credit_card_front");
        Assert.assertEquals(variables.getCreditCardNumber(), "5326100314433644");
        Assert.assertEquals(variables.getDateOfExpiry(), "02/20");


        //Images
        Assert.assertNotNull(variables.getInputImage());
        Assert.assertNotNull(variables.getCroppedImage());
    }

}
