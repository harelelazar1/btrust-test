package btrust.onboardingProcess.api;

import btrust.onboardingProcess.api.variables.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;

public class moranTest {

    public WebDriver driver;
    public Response response;
    public JsonPath jsonPath;
    String url;
    String sessionId;
    String missingFields;
    int newCaseId;
    Map<String, File> processes;
    PreProcess preProcess;
    EndpointResults endpointResults;
    Utilities utilities;

    @BeforeMethod
    public void init() {
        utilities = new Utilities();
        preProcess = new PreProcess();
        endpointResults = new EndpointResults();
        processes = new HashMap<>();
    }

    ApiResponses apiResponses = new ApiResponses();
//    GeneralResponse generalResponse = new GeneralResponse();

    public Response createBtrust1Link(int flowNumber) {
        response = given()
                .header("Authorization",  System.getProperty("ACCESS_TOKEN"))
                .log()
                .all()
                .get("https://qa-nginx-console.scanovateoncloud.com/api/flow/" + flowNumber + "/link")
                .then()
                .statusCode(200)
                .extract()
                .response();

        System.out.println(response.getBody().asString());
        return response;
    }

    public Response initPutForSessionId(int flowId) {
        response = given()
                .header("Authorization",  System.getProperty("ACCESS_TOKEN"))
                .multiPart("flowId", flowId)
//                .log()
//                .all()
                .put("https://qa-nginx-console.scanovateoncloud.com/api/inquiries/")
                .then()
                .statusCode(200)
                .extract()
                .response();

        System.out.println(response.getBody().asString());
        return response;
    }

    public void postJsonFile(Object filePath) {
        response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(filePath)
                .post("https://webhook-qa.scanovate.com/")
                .then()
//                .statusCode(200)
                .extract()
                .response();

        System.out.println(response.getBody().asString());
    }

    public WebDriver clientView(WebDriver driver) {
        WebDriverManager.chromedriver().setup();
        Map<String, String> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", "iPhone X");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("use-fake-ui-for-media-stream");
        options.addArguments("use-fake-device-for-media-stream");
        options.addArguments("allow-file-access-from-files");
        options.addArguments("--disable-infobars");
        options.addArguments("--auto-open-devtools-for-tabs");
        options.addArguments("--incognito");
//        options.addArguments("--headless");
        options.setExperimentalOption("mobileEmulation", mobileEmulation);
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        return driver;
    }


    public JSONObject updateJsonFile(File file) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader(file));
//        newCaseId = generateRandomCaseId();
        if (jsonObject.containsKey("caseId")) {
            jsonObject.put("caseId", String.valueOf(newCaseId));
        }
        System.out.println(jsonObject);
        return jsonObject;
    }

    public Response putStep(int flowId, String typeKey, int caseId, String sessionId) {
        response = given()
                .header("Authorization",  System.getProperty("ACCESS_TOKEN"))
                .multiPart("flowId", flowId)
                .multiPart(typeKey, caseId)
                .multiPart("sessionId", sessionId)
                .log()
                .all()
                .put("https://qa-nginx-console.scanovateoncloud.com/api/inquiries/")
                .then()
                .statusCode(200)
                .extract()
                .response();

        System.out.println(response.getBody().asString());
        return response;

    }


    @Test
    public void t01() {
        createBtrust1Link(98);
        jsonPath = response.jsonPath();
        url = jsonPath.getString("data");
        System.out.println("Url is: " + url);
        driver = clientView(this.driver);
        driver.get(url);
        System.out.println(url.split("process_id=")[1]);
        driver.quit();
    }

    @Test
    public void t02() throws IOException, ParseException {
        jsonPath = initPutForSessionId(87).jsonPath();
        sessionId = jsonPath.getString("data.sessionId");
        missingFields = jsonPath.getString("data.missingFields[0]");
        System.out.println("sessionId is: " + sessionId);
        System.out.println("Missing fields is: " + missingFields);

        switch (missingFields) {
            case "livenessCaseId": {
                postJsonFile(updateJsonFile(new File("./jsonFiles/mobileInteraction/greenID/PostSessionLiveness.json")));
                break;
            }
        }
        putStep(87, missingFields, newCaseId, sessionId);
    }

    @Test
    public void t03() throws IOException, ParseException {
        int flowId = 26;
        sessionId = apiResponses.createSessionId(26);
        apiResponses.initSession(preProcess, 26 , sessionId);
        apiResponses.postAndUpdateJsonFile(preProcess, new File("./jsonFiles/mobileInteraction/greenID/PostSessionLiveness.json"));
        apiResponses.updateRequestPutCall(preProcess, flowId, preProcess.getMissingFields(), String.valueOf(preProcess.getCaseId()), "sessionId", preProcess.getSessionId());
        System.out.println(preProcess.getMissingFields());
        apiResponses.postAndUpdateJsonFile(preProcess, new File("./jsonFiles/mobileInteraction/greenID/PostSessionOCR.json"));
        apiResponses.updateRequestPutCall(preProcess, flowId, preProcess.getMissingFields(), String.valueOf(preProcess.getCaseId()), "sessionId", preProcess.getSessionId());
        System.out.println(preProcess.getMissingFields());
        apiResponses.postAndUpdateJsonFile(preProcess, new File("./jsonFiles/mobileInteraction/cheque/cheque.json"));
        apiResponses.updateRequestPutCall(preProcess, flowId, preProcess.getMissingFields(), String.valueOf(preProcess.getCaseId()), "sessionId", preProcess.getSessionId());
        System.out.println(preProcess.getMissingFields());

        apiResponses.getTokenFromCallbackRequest(preProcess);
        System.out.println("****");
        System.out.println(preProcess.getToken());
        System.out.println(preProcess.getCaseId());

    }


    @Test
    public void t04() throws IOException, ParseException {
        sessionId = apiResponses.createSessionId(26);
        apiResponses.initSession(preProcess, 26 , sessionId);

        assertTrue(preProcess.isSuccess());
        assertEquals(preProcess.getErrorCode(), 0);
        assertNotNull(preProcess.getInquiryId());
        assertTrue(preProcess.isDataSuccess());
//        assertEquals(preProcess.getNextProcess(), "server_ocr");
//        assertEquals(preProcess.getMissingFields(), "ocrCaseId3");
        assertEquals(preProcess.getDataErrorCode(), 1001);
        assertNotNull(preProcess.getSessionId());
        assertNotNull(preProcess.getOcrTypes());

        processes.put("server_ocr", new File("./jsonFiles/mobileInteraction/dl/PostSessionOCR.json"));
        processes.put("liveness", new File("./jsonFiles/mobileInteraction/dl/PostSessionLiveness.json"));
        processes.put("server_cheque", new File("./jsonFiles/mobileInteraction/cheque/cheque.json"));
        apiResponses.preEndpointProcess(preProcess, processes);

        assertTrue(preProcess.isSuccess());
        assertEquals(preProcess.getErrorCode(), 0);
        assertNotNull(preProcess.getInquiryId());
        assertTrue(preProcess.isDataSuccess());
        assertEquals(preProcess.getNextProcess(), "process_ended");
        assertEquals(preProcess.getDataErrorCode(), 0);
        assertNotNull(preProcess.getSessionId());

        apiResponses.getTokenFromCallbackRequest(preProcess);

        assertTrue(preProcess.isSuccess());
        assertEquals(preProcess.getErrorCode(), 0);
        assertNotNull(preProcess.getData());
        assertNotNull(preProcess.getToken());

//        apiResponses.endpointManager(preProcess, "v2");
        System.out.println(preProcess.getSessionId());
        System.out.println(preProcess.getToken());
    }

    @Test
    public void flow26WithMRZ() throws IOException, ParseException {
        sessionId = apiResponses.createSessionId(26);
        apiResponses.initSession(preProcess, 26 , sessionId);

        assertTrue(preProcess.isSuccess());
        assertEquals(preProcess.getErrorCode(), 0);
        assertNotNull(preProcess.getInquiryId());
        assertTrue(preProcess.isDataSuccess());
//        assertEquals(preProcess.getNextProcess(), "server_ocr");
//        assertEquals(preProcess.getMissingFields(), "ocrCaseId3");
        assertEquals(preProcess.getDataErrorCode(), 1001);
        assertNotNull(preProcess.getSessionId());
        assertNotNull(preProcess.getOcrTypes());

        processes.put("server_ocr", new File("./jsonFiles/mobileInteraction/mrz/PostSessionOCR.json"));
        processes.put("liveness", new File("./jsonFiles/mobileInteraction/mrz/PostSessionLiveness.json"));
        processes.put("server_cheque", new File("./jsonFiles/mobileInteraction/cheque/cheque.json"));
        apiResponses.preEndpointProcess(preProcess, processes);

        assertTrue(preProcess.isSuccess());
        assertEquals(preProcess.getErrorCode(), 0);
        assertNotNull(preProcess.getInquiryId());
        assertTrue(preProcess.isDataSuccess());
        assertEquals(preProcess.getNextProcess(), "process_ended");
        assertEquals(preProcess.getDataErrorCode(), 0);
        assertNotNull(preProcess.getSessionId());

        apiResponses.getTokenFromCallbackRequest(preProcess);

        assertTrue(preProcess.isSuccess());
        assertEquals(preProcess.getErrorCode(), 0);
        assertNotNull(preProcess.getData());
        assertNotNull(preProcess.getToken());

//        apiResponses.endpointManager(preProcess, "v2");
        System.out.println(preProcess.getSessionId());
        System.out.println(preProcess.getToken());
    }

    @Test
    public void flow26WithDKDL() throws IOException, ParseException {
        sessionId = apiResponses.createSessionId(26);
        apiResponses.initSession(preProcess, 26 , sessionId);

        assertTrue(preProcess.isSuccess());
        assertEquals(preProcess.getErrorCode(), 0);
        assertNotNull(preProcess.getInquiryId());
        assertTrue(preProcess.isDataSuccess());
//        assertEquals(preProcess.getNextProcess(), "server_ocr");
//        assertEquals(preProcess.getMissingFields(), "ocrCaseId3");
        assertEquals(preProcess.getDataErrorCode(), 1001);
        assertNotNull(preProcess.getSessionId());
        assertNotNull(preProcess.getOcrTypes());

        processes.put("server_ocr", new File("./jsonFiles/mobileInteraction/dk dl/PostSessionOCR.json"));
        processes.put("liveness", new File("./jsonFiles/mobileInteraction/dk dl/PostSessionLiveness.json"));
        processes.put("server_cheque", new File("./jsonFiles/mobileInteraction/cheque/cheque.json"));
        apiResponses.preEndpointProcess(preProcess, processes);

        assertTrue(preProcess.isSuccess());
        assertEquals(preProcess.getErrorCode(), 0);
        assertNotNull(preProcess.getInquiryId());
        assertTrue(preProcess.isDataSuccess());
        assertEquals(preProcess.getNextProcess(), "process_ended");
        assertEquals(preProcess.getDataErrorCode(), 0);
        assertNotNull(preProcess.getSessionId());

        apiResponses.getTokenFromCallbackRequest(preProcess);

        assertTrue(preProcess.isSuccess());
        assertEquals(preProcess.getErrorCode(), 0);
        assertNotNull(preProcess.getData());
        assertNotNull(preProcess.getToken());

//        apiResponses.endpointManager(preProcess, "v2");
        System.out.println(preProcess.getSessionId());
        System.out.println(preProcess.getToken());
    }

    @Test
    public void flow87() throws IOException, ParseException {
        sessionId = apiResponses.createSessionId(87);
        apiResponses.initSession(preProcess, 87 , sessionId);

        assertTrue(preProcess.isSuccess());
        assertEquals(preProcess.getErrorCode(), 0);
        assertNotNull(preProcess.getInquiryId());
        assertTrue(preProcess.isDataSuccess());
        assertEquals(preProcess.getNextProcess(), "liveness");
        assertEquals(preProcess.getMissingFields(), "livenessCaseId");
        assertEquals(preProcess.getDataErrorCode(), 1001);
        assertNotNull(preProcess.getSessionId());
        assertNotNull(preProcess.getOcrTypes());

        processes.put("server_ocr", new File("./jsonFiles/mobileInteraction/bioID/PostSessionOCR.json"));
        processes.put("liveness", new File("./jsonFiles/mobileInteraction/bioID/PostSessionLiveness.json"));
        processes.put("server_cheque", new File("./jsonFiles/mobileInteraction/cheque/cheque.json"));
        apiResponses.preEndpointProcess(preProcess, processes);

        assertTrue(preProcess.isSuccess());
        assertEquals(preProcess.getErrorCode(), 0);
        assertNotNull(preProcess.getInquiryId());
        assertTrue(preProcess.isDataSuccess());
        assertEquals(preProcess.getNextProcess(), "process_ended");
        assertEquals(preProcess.getDataErrorCode(), 0);
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
        assertTrue(endpointResults.isDataSuccess());
        assertEquals(endpointResults.getDataErrorMessage(), "");
        assertEquals(endpointResults.getDataErrorCode(), 0);
        assertEquals(endpointResults.getMetadata(), "");

        assertEquals(Liveness.getProcess(), "liveness");
        assertNotNull(Liveness.getFaceImage());
        assertNotNull(Liveness.getVideo());
        assertTrue(Liveness.isProcessSuccess());

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
        assertNotNull(ILID.getBackImage());
        assertEquals(ILID.getDocType(), "il_id_bio");
        assertEquals(ILID.getIssueDate(), "2019-05-23T00:00:00.000+0000");
        assertEquals(ILID.getPlaceOfBirth(), "ישראל");
        assertNotNull(ILID.getScanVideo());
        assertEquals(ILID.getOcrType(), "IL-ID");
        assertEquals(ILID.getBackSideIdNumber(), 200761625);
        assertTrue(ILID.isPeriodBetweenExpiryDateAndIssueDate());
        assertTrue(ILID.isFacePosition());
        assertTrue(ILID.isValidityExpiryDate());
        assertTrue(ILID.isFaceSize());
        assertTrue(ILID.isTemplateMatching());
        assertTrue(ILID.isFaceRotation());
        assertTrue(ILID.isDocumentChipValid());
        assertTrue(ILID.isColorCheck());
        assertTrue(ILID.isFaceImageReplacedWithPassportImage());
        assertTrue(ILID.isColourfulImageBackSide());
        assertTrue(ILID.isChecksum());
        assertTrue(ILID.isValidateIfFrontAndBacksideIdMatch());
        assertTrue(ILID.isChecksumBackside());
        assertTrue(ILID.isTemplateMatchingBackside());
        assertTrue(ILID.isProcessSuccess());
        assertEquals(ILID.getCount0(), 1);

        assertEquals(ILCheque.getProcess(), "ocr");
        assertNotNull(ILCheque.getEnd());
        assertNotNull(ILCheque.getDocumentImage());
        assertNotNull(ILCheque.getChequeNumberLineImage());
        assertEquals(ILCheque.getChequeNumber(), 80021973);
        assertEquals(ILCheque.getBankBranchNumber(), 13644);
        assertEquals(ILCheque.getBankNumber(), 11);
        assertEquals(ILCheque.getBankAccountNumber(), 186449);
        assertEquals(ILCheque.getOcrType(), "cheque_document");
        assertTrue(ILCheque.isProcessSuccess());
        assertEquals(ILCheque.getCount(), 1);

        assertEquals(BiometricMatch.getProcess(), "biometric_match");
        assertTrue(BiometricMatch.isProcessSuccess());
        assertEquals(BiometricMatch.getScore(), 0.8209096193313599);
    }

    @Test
    public void flow85STT() throws IOException, ParseException {
        sessionId = apiResponses.createSessionId(85);
        apiResponses.initSession(preProcess, 85 , sessionId);

        assertTrue(preProcess.isSuccess());
        assertEquals(preProcess.getErrorCode(), 0);
        assertNotNull(preProcess.getInquiryId());
        assertTrue(preProcess.isDataSuccess());
//        assertEquals(preProcess.getNextProcess(), "server_ocr");
//        assertEquals(preProcess.getMissingFields(), "ocrCaseId3");
        assertEquals(preProcess.getDataErrorCode(), 1001);
        assertNotNull(preProcess.getSessionId());
        assertEquals(preProcess.getMathildaFileName(), "stt.json");

//        processes.put("server_ocr", new File("./jsonFiles/mobileInteraction/dk dl/PostSessionOCR.json"));
        processes.put("liveness", new File("./jsonFiles/mobileInteraction/stt/stt.json"));
//        processes.put("server_cheque", new File("./jsonFiles/mobileInteraction/cheque/cheque.json"));
        apiResponses.preEndpointProcess(preProcess, processes);

        assertTrue(preProcess.isSuccess());
        assertEquals(preProcess.getErrorCode(), 0);
        assertNotNull(preProcess.getInquiryId());
        assertTrue(preProcess.isDataSuccess());
        assertEquals(preProcess.getNextProcess(), "process_ended");
        assertEquals(preProcess.getDataErrorCode(), 0);
        assertNotNull(preProcess.getSessionId());

        apiResponses.getTokenFromCallbackRequest(preProcess);
        utilities.endpointManager(preProcess, endpointResults, "v2");
        System.out.println(Liveness.getSttText());
    }

    @Test
    public void questionnaire() throws IOException, ParseException {
        sessionId = apiResponses.createSessionId(89);
        apiResponses.initSession(preProcess, 89 , sessionId);

        System.out.println("next: " + preProcess.getNextProcess());
//        processes.put("server_ocr", new File("./jsonFiles/mobileInteraction/dk dl/PostSessionOCR.json"));
        processes.put("questionnaire_ID", new File("./jsonFiles/mobileInteraction/singleFrame/singleFrameMrz.json"));
//        processes.put("server_cheque", new File("./jsonFiles/mobileInteraction/cheque/cheque.json"));
        apiResponses.preEndpointProcess(preProcess, processes);

        assertTrue(preProcess.isSuccess());
        assertEquals(preProcess.getErrorCode(), 0);
        assertNotNull(preProcess.getInquiryId());
        assertTrue(preProcess.isDataSuccess());
//        assertEquals(preProcess.getNextProcess(), "process_ended");
        assertEquals(preProcess.getDataErrorCode(), 0);
        assertNotNull(preProcess.getSessionId());

        apiResponses.getTokenFromCallbackRequest(preProcess);
        System.out.println(preProcess.getToken());
        utilities.endpointManager(preProcess, endpointResults, "v2");

    }

    @Test
    public void comprator() throws IOException, ParseException {
        sessionId = apiResponses.createSessionId(63);
        apiResponses.initSession(preProcess, 63 , sessionId);
        System.out.println("next: " + preProcess.getNextProcess());
        processes.put("server_ocr", new File("./jsonFiles/mobileInteraction/comparator/PostSessionOCR.json"));
        apiResponses.preEndpointProcess(preProcess, processes);
        apiResponses.getTokenFromCallbackRequest(preProcess);
        utilities.endpointManager(preProcess, endpointResults, "v2");

        assertFalse(endpointResults.isDataSuccess());
        assertEquals(endpointResults.getDataErrorMessage(), "back side number doesn't equals to front side id number ");
        assertEquals(endpointResults.getDataErrorCode(), 5858);
    }

    @Test
    public void dateIf() throws IOException, ParseException {
        sessionId = apiResponses.createSessionId(81);
        apiResponses.initSession(preProcess, 81 , sessionId);
        processes.put("server_ocr", new File("./jsonFiles/mobileInteraction/DateIf/PostSessionOCR.json"));
        apiResponses.preEndpointProcess(preProcess, processes);
        apiResponses.getTokenFromCallbackRequest(preProcess);
        System.out.println(preProcess.getToken());
//        utilities.endpointManager(preProcess, endpointResults, "v1");
    }

    @Test
    public void flow87V1() throws IOException, ParseException {
        sessionId = apiResponses.createSessionId(87);
        apiResponses.initSession(preProcess, 87 , sessionId);
        processes.put("server_ocr", new File("./jsonFiles/mobileInteraction/bioID/PostSessionOCR.json"));
        processes.put("liveness", new File("./jsonFiles/mobileInteraction/bioID/PostSessionLiveness.json"));
        processes.put("server_cheque", new File("./jsonFiles/mobileInteraction/cheque/cheque.json"));
        apiResponses.preEndpointProcess(preProcess, processes);
        apiResponses.getTokenFromCallbackRequest(preProcess);
        System.out.println(preProcess.getToken());
        utilities.endpointManager(preProcess, endpointResults, "v1");
    }

    @Test
    public void flow26WithMRZV1() throws IOException, ParseException {
        sessionId = apiResponses.createSessionId(26);
        apiResponses.initSession(preProcess, 26 , sessionId);
        processes.put("server_ocr", new File("./jsonFiles/mobileInteraction/mrz/PostSessionOCR.json"));
        processes.put("liveness", new File("./jsonFiles/mobileInteraction/mrz/PostSessionLiveness.json"));
        processes.put("server_cheque", new File("./jsonFiles/mobileInteraction/cheque/cheque.json"));
        apiResponses.preEndpointProcess(preProcess, processes);

        apiResponses.getTokenFromCallbackRequest(preProcess);

        System.out.println(preProcess.getToken());
//        utilities.endpointManager(preProcess, endpointResults, "v1");
//        System.out.println(MRZ.getExpirationDate());
    }

    @Test
    public void flow26WithDLV1() throws IOException, ParseException {
        sessionId = apiResponses.createSessionId(26);
        apiResponses.initSession(preProcess, 26 , sessionId);
        processes.put("server_ocr", new File("./jsonFiles/mobileInteraction/dl/PostSessionOCR.json"));
        processes.put("liveness", new File("./jsonFiles/mobileInteraction/dl/PostSessionLiveness.json"));
        processes.put("server_cheque", new File("./jsonFiles/mobileInteraction/cheque/cheque.json"));
        apiResponses.preEndpointProcess(preProcess, processes);

        apiResponses.getTokenFromCallbackRequest(preProcess);

        System.out.println(preProcess.getToken());
        utilities.endpointManager(preProcess, endpointResults, "v1");
    }

    @Test
    public void flow26WithDKDLV1() throws IOException, ParseException {
        sessionId = apiResponses.createSessionId(26);
        apiResponses.initSession(preProcess, 26 , sessionId);
        processes.put("server_ocr", new File("./jsonFiles/mobileInteraction/dk dl/PostSessionOCR.json"));
        processes.put("liveness", new File("./jsonFiles/mobileInteraction/dk dl/PostSessionLiveness.json"));
        processes.put("server_cheque", new File("./jsonFiles/mobileInteraction/cheque/cheque.json"));
        apiResponses.preEndpointProcess(preProcess, processes);

        apiResponses.getTokenFromCallbackRequest(preProcess);

        System.out.println(preProcess.getToken());
        utilities.endpointManager(preProcess, endpointResults, "v1");
    }

    @Test
    public void flow85STTV1() throws IOException, ParseException {
        sessionId = apiResponses.createSessionId(85);
        apiResponses.initSession(preProcess, 85 , sessionId);
        processes.put("liveness", new File("./jsonFiles/mobileInteraction/stt/stt.json"));
        apiResponses.preEndpointProcess(preProcess, processes);

        apiResponses.getTokenFromCallbackRequest(preProcess);

        System.out.println(preProcess.getToken());
        utilities.endpointManager(preProcess, endpointResults, "v1");
        System.out.println(Liveness.getSttText());
        System.out.println(Liveness.getSttScore());
    }

    @Test
    public void questionnaireV1() throws IOException, ParseException {
        sessionId = apiResponses.createSessionId(89);
        apiResponses.initSession(preProcess, 89 , sessionId);

        System.out.println("next: " + preProcess.getNextProcess());
        processes.put("questionnaire_ID", new File("./jsonFiles/mobileInteraction/singleFrame/singleFrameMrz.json"));
        apiResponses.preEndpointProcess(preProcess, processes);

        apiResponses.getTokenFromCallbackRequest(preProcess);
        System.out.println(preProcess.getToken());
        utilities.endpointManager(preProcess, endpointResults, "v1");

    }

    @Test
    public void flow87Harel() throws IOException, ParseException {
        sessionId = apiResponses.createSessionId(87);
        apiResponses.initSession(preProcess, 87 , sessionId);
        processes.put("server_ocr", new File("./jsonFiles/mobileInteraction/blueID/PostSessionOCR.json"));
        processes.put("liveness", new File("./jsonFiles/mobileInteraction/blueID/PostSessionLiveness.json"));
        processes.put("server_cheque", new File("./jsonFiles/mobileInteraction/cheque/cheque.json"));
        apiResponses.preEndpointProcess(preProcess, processes);
        apiResponses.getTokenFromCallbackRequest(preProcess);
        System.out.println(preProcess.getToken());
        utilities.endpointManager(preProcess, endpointResults, "harel");
        System.out.println(ILID.getIssueDate());
    }

    @Test
    public void regulaV2() throws IOException, ParseException {
        sessionId = apiResponses.createSessionId(80);
        apiResponses.initSession(preProcess, 80 , sessionId);
        processes.put("card_capture_front", new File("./jsonFiles/regula/regulaImageFront.jpg"));
//        processes.put("card_capture_back", new File("./ocr/dl/LiadDLBack.jpg"));
        processes.put("card_capture_back", new File("./jsonFiles/regula/regulaBackImage.jpg"));
//        processes.put("server_ocr", new File("./jsonFiles/mobileInteraction/regula/PostSessionOCRbackSide.json.json"));
//        processes.put("liveness", new File("./jsonFiles/mobileInteraction/blueID/PostSessionLiveness.json"));
//        processes.put("server_cheque", new File("./jsonFiles/mobileInteraction/cheque/cheque.json"));
        apiResponses.preEndpointProcess(preProcess, processes);
        apiResponses.getTokenFromCallbackRequest(preProcess);
        System.out.println(preProcess.getToken());
        utilities.endpointManager(preProcess, endpointResults, "v2");
        System.out.println(CardCapture.getImage());
    }

    @Test
    public void maxAttempts() throws IOException, ParseException {
        sessionId = apiResponses.createSessionId(26);
        apiResponses.initSession(preProcess, 26 , sessionId);
        processes.put("server_ocr", new File("./jsonFiles/mobileInteraction/errors/timeout.json"));
        apiResponses.preEndpointProcess(preProcess, processes);
        apiResponses.getTokenFromCallbackRequest(preProcess);
        System.out.println(preProcess.getToken());
        utilities.endpointManager(preProcess, endpointResults, "v2");
    }

}
