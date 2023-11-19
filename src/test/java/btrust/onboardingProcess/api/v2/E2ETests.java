package btrust.onboardingProcess.api.v2;

import api.ApiResponse;
import api.Variables;
import btrust.BaseDesktopViewTest;
import btrust.btrustOne.client.NavigationPage;
import btrust.btrustOne.client.login.pageObject.LoginPage;
import btrust.btrustOne.client.mobileCases.PageObject.MobileCasePage;
import btrust.onboardingProcess.api.ApiResponses;
import btrust.onboardingProcess.api.Utilities;
import btrust.onboardingProcess.api.variables.*;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.response.Response;
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
import java.sql.Driver;
import java.text.SimpleDateFormat;
import java.util.*;

import static btrust.btrustOne.admin.BaseAdminUserTest.randomString;
import static org.testng.Assert.*;
@Listeners({SuiteListener.class})
public class E2ETests extends BaseDesktopViewTest {

    Map<String, File> processes;
    PreProcess preProcess;
    EndpointResults endpointResults;
    Utilities utilities;
    ApiResponses apiResponses;

    ApiResponse apiResponse;

    String sessionId;
    Variables variables;

    NavigationPage navigationPage;
    long timeDiff;
    DBUtils dbUtils = new DBUtils();
    MobileCasePage mobileCasesPage;
    MobileCasePage mobileCasePage;
    String firstName;
    String lastName;
    String citizenship;
    String IssuingDate;
    String IdNumber;
    String caseID;
    String Sex;
    String ExpirationDate;
    String PlaceOfBirth;


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

    @Step("Login to client user")
    public void loginToClientUser(String caseId) {
        driver.get("https://qa-nginx-console.scanovateoncloud.com/search/" + caseId + "");
        LoginPage login = new LoginPage(driver);
        login.login("qatest", "liad.tubi@scanovate.com", "Scan2018!");
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
        navigationPage = new NavigationPage(driver);
        mobileCasePage = new MobileCasePage(driver);
    }


  //  @Test(description = "E2E-Onboarding Api Test Scanning New BioId card and check result in console")
    @Description("E2E-Onboarding Api Test Scanning New BioId card and check result in console")
    public void t01_onboardingWithBioIdNew() throws IOException, ParseException {
        sessionId = apiResponses.createSessionId(434);
        apiResponses.initSession(preProcess, 434, sessionId);
        preProcess.setCaseId(randomString());


        apiResponse.clientInitResponse(variables, "application/json", "caseId", preProcess.getCaseId(), "libraryName", "IL-ID", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR);

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


        apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization",
                "Bearer " + variables.getToken(), "frame", new File("./ocr/new bioID/Gregory/newBioIDFront.jpeg"),
                "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        while (!variables.getActionType().equalsIgnoreCase("stage")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./ocr/new bioID/Gregory/newBioIDFront.jpeg"),
                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        }

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./ocr/new bioID/Gregory/newBioIDBack.jpeg"),
                    "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        }


        assertEquals(variables.getActionType(), "complete");
        String ocrCaseId = preProcess.getCaseId();

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

        firstName = ILID.getFirstName();
        lastName = ILID.getLastName();
        ExpirationDate = ILID.getExpiryDate();
        PlaceOfBirth = ILID.getPlaceOfBirth();
        citizenship = ILID.getCitizenship();
        IssuingDate = ILID.getIssuingDate();
        IdNumber = Integer.toString(ILID.getIdNumber());
        Sex = ILID.getGender();


        caseID = dbUtils.getCadeID(sessionId);

        loginToClientUser(caseID);
        mobileCasePage.checkTitleFullName(firstName);
        mobileCasePage.collapseCardsName("Scanovate OCR");
        mobileCasePage.collapseCardsName("Document verification");
        Assert.assertEquals(mobileCasePage.compareOcrDataFromCard("First name"), firstName);
        Assert.assertEquals(mobileCasePage.compareOcrDataFromCard("Last name"), lastName);
        Assert.assertEquals(mobileCasePage.compareOcrDataFromCard("Id Number"), IdNumber);
        Assert.assertEquals(mobileCasePage.compareOcrDataFromCard("Place of birth"), PlaceOfBirth);
        Assert.assertEquals(mobileCasePage.compareOcrDataFromCard("Expiration date"), ExpirationDate);
        Assert.assertEquals(mobileCasePage.compareOcrDataFromCard("Sex"), Sex);
        Assert.assertEquals(mobileCasePage.compareOcrDataFromCard("Issuing date"), IssuingDate);
        Assert.assertEquals(mobileCasePage.compareOcrDataFromCard("Citizenship"), citizenship);
        Assert.assertTrue(mobileCasePage.ocrImageAmount(2));
    }


 //   @Test(description = "E2E-Onboarding Api Test Scanning  BlueID card and check result in console")
    @Description("E2E-Onboarding Api Test Scanning BlueID card and check result in console")
    public void t02_onboardingWithBlueIdAndLiveness() throws IOException, ParseException {
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
                "Bearer " + variables.getToken(), "frame", new File("./ocr/blueID/liad/blueID.jpg"),
                "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./ocr/blueID/liad/blueID.jpg"),
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
                "Bearer " + variables.getToken(), "frame", new File("./liveness/liad/liadCenterFace.jpg"),
                "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization",
                    "Bearer " + variables.getToken(), "frame", new File("./liveness/liad/liadCenterFace.jpg"),
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

//        assertEquals(ILID.getProcess(), "ocr");
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

        firstName = ILID.getFirstName();
        lastName = ILID.getLastName();
        ExpirationDate = ILID.getExpiryDate();
        PlaceOfBirth = ILID.getPlaceOfBirth();
        citizenship = ILID.getCitizenship();
        IssuingDate = ILID.getIssuingDate();
        IdNumber = Integer.toString(ILID.getIdNumber());
        Sex = ILID.getGender();


        caseID = dbUtils.getCadeID(sessionId);

//        loginToClientUser(caseID);
//        mobileCasePage.checkTitleFullName(firstName);
//        mobileCasePage.checkIfCollapseCardsNameDisplay("OCR");
//        mobileCasePage.checkIfCollapseCardsNameDisplay("Document verification");
//        mobileCasePage.checkIfCollapseCardsNameDisplay("Liveness");
//        mobileCasePage.checkIfCollapseCardsNameDisplay("Scanovate Biometrics");
//
//
//        mobileCasePage.collapseCardsName("OCR");
//
//        Assert.assertEquals(mobileCasePage.compareOcrDataFromCard("First name"), firstName);
//        Assert.assertEquals(mobileCasePage.compareOcrDataFromCard("Last name"), lastName);
//        Assert.assertEquals(mobileCasePage.compareOcrDataFromCard("Id Number"), IdNumber);
//        Assert.assertEquals(mobileCasePage.compareOcrDataFromCard("Place of birth"), PlaceOfBirth);
//        Assert.assertEquals(mobileCasePage.compareOcrDataFromCard("Expiration date"), ExpirationDate);
//        Assert.assertEquals(mobileCasePage.compareOcrDataFromCard("Sex"), Sex);
//        Assert.assertEquals(mobileCasePage.compareOcrDataFromCard("Issuing date"), IssuingDate);
//        Assert.assertEquals(mobileCasePage.compareOcrDataFromCard("Citizenship"), citizenship);
//        Assert.assertTrue(mobileCasePage.ocrImageAmount(1));
//        mobileCasePage.collapseCardsName("Scanovate OCR");
//        mobileCasePage.collapseCardsName("Document verification");
//        Assert.assertEquals(mobileCasePage.checkAuthenticationResult("Checksum"),"pass");
//
//        mobileCasePage.collapseCardsName("Document verification");
//        mobileCasePage.collapseCardsName("Liveness");
//        Assert.assertTrue(mobileCasePage.livenessImageAmount(2));
//        Assert.assertEquals(mobileCasePage.checkLivenessResult("Result"),"Success");
//
//        mobileCasePage.collapseCardsName("Liveness");
//        mobileCasePage.collapseCardsName("Scanovate Biometrics");
//        Assert.assertTrue( mobileCasePage.livenessImageAmount(2));
//        Assert.assertEquals(mobileCasePage.checkFaceMatchResult("Result"),"Success");
//        driver.close();
    }
}