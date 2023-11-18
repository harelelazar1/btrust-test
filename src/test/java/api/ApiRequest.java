package api;

import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;

import java.io.File;
import java.util.Random;

import static io.restassured.RestAssured.given;

public class ApiRequest {
    //Liveness path
    public final String UAT_LIVENESS_URL = "https://liveness-uat.scanovate.com";
    public final String QA_LIVENESS_URL = "https://qaliveness.scanovateoncloud.com";
    public final String QA_LIVENESS_NEW_URL = "https://qa-ngliveness.scanovateoncloud.com";

    //OCR
    public final String UAT_OCR_URL = "https://ocr-uat.scanovate.com";
    public final String QA_OCR_URL = "https://qaocr.scanovateoncloud.com";
    public final String QA_OCR_NEW_URL = "https://qa-ngocr.scanovateoncloud.com";


    public String CLIENT_INIT_OCR;
    public String CLIENT_REQUEST_OCR;
    public String SINGLE_FRAME_OCR;

    public String CLIENT_INIT_LIVENESS;
    public String CLIENT_REQUEST_LIVENESS;
    public String SINGLE_FRAME_LIVENESS;

    protected String caseId;
    public boolean success;
    public String libraryName;

    public Response clientRequest;
    public Response clientRequestNew;
    public Response clientInit;

    public boolean response;
    public String token;

    public ApiRequest() {
        String baseOcrUrl = QA_OCR_URL;
        String baseOcrNewUrl = QA_OCR_NEW_URL;
        String baseLivenessUrl = QA_LIVENESS_URL;
        String baseLivenessNewUrl = QA_LIVENESS_NEW_URL;


        if (System.getProperty("OCR_NEW").equalsIgnoreCase("false")) {
            CLIENT_INIT_OCR = baseOcrUrl + "/communication/client_init";
            CLIENT_REQUEST_OCR = baseOcrUrl + "/communication/client_request";
        } else {
            CLIENT_INIT_OCR = baseOcrNewUrl + "/communication/client_init";
            CLIENT_REQUEST_OCR = baseOcrNewUrl + "/communication/client_request";
        }

        if (System.getProperty("TEST_ENV", "QA").equals("UAT")) {
            baseOcrUrl = UAT_OCR_URL;
            baseLivenessUrl = UAT_LIVENESS_URL;
        }

        SINGLE_FRAME_OCR = baseOcrUrl + "/api/v1/single_frame";


        if (System.getProperty("LIVENESS_NEW").equalsIgnoreCase("false")) {
            CLIENT_INIT_LIVENESS = baseLivenessUrl + "/communication/client_init";
            CLIENT_REQUEST_LIVENESS = baseLivenessUrl + "/communication/client_request";
            SINGLE_FRAME_LIVENESS = baseLivenessUrl + "/api/v1/single_frame";
        } else {
            CLIENT_INIT_LIVENESS = baseLivenessNewUrl + "/communication/client_init";
            CLIENT_REQUEST_LIVENESS = baseLivenessNewUrl + "/communication/client_request";
            SINGLE_FRAME_LIVENESS = baseLivenessNewUrl + "/api/v1/single_frame";
        }


    }


    @Step("Client init")
    public Response clientInitNewRequest(String contentType, String caseIdKey, String caseIdValue, String libraryNameKey, String libraryNameValue, String flowConfigNameKey, String flowConfigNameValue, String url, String sessionId, String X_Session_Id, String X_Request_Id,String clientTranslationFileName,String clientTranslationFileValue ,String devModeName, boolean devMode) {
        clientInit = given()
                .contentType(contentType)
                .header("X-Company-ID", System.getProperty("COMPANY_ID"))
                .header("X-Session-Id", X_Session_Id)
                .header("X-Flow-Id", sessionId)
                .header("X-Request-Id", X_Request_Id)


                .body("{\r\n    \"" + caseIdKey + "\":\"" + caseIdValue + "\",\r\n    \"" + libraryNameKey + "\":\"" + libraryNameValue + "\",\r\n    \"" + clientTranslationFileName + "\":\"" + clientTranslationFileValue + "\",\r\n\"" + devModeName + "\": " + devMode + "  ,\r\n\"" + flowConfigNameKey + "\":\"" + flowConfigNameValue + "\"\r\n}")
                .log()
                .all()
                .when()
                .post(url)
                .then()
                .statusCode(200)
                .extract()
                .response();

//        System.out.println(clientInit.getBody().asString());

        libraryName = libraryNameValue;
        System.out.println("clientinit " + clientInit.getBody().asString());
        return clientInit;
    }

    @Step("Client init")
    public Response clientInitRequest(String contentType, String caseIdKey, String caseIdValue, String libraryNameKey, String libraryNameValue, String flowConfigNameKey, String flowConfigNameValue, String url) {
        clientInit = given()
                .contentType(contentType)
                .body("{\r\n    \"" + caseIdKey + "\":\"" + caseIdValue + "\",\r\n    \"" + libraryNameKey + "\":\"" + libraryNameValue + "\",\r\n    \"" + flowConfigNameKey + "\":\"" + flowConfigNameValue + "\"\r\n}")
                .log()
                .all()
                .when()
                .post(url)
                .then()
                .statusCode(200)
                .extract()
                .response();

//        System.out.println(clientInit.getBody().asString());

        libraryName = libraryNameValue;
        return clientInit;
    }

    @Step("Client frame request")
    public Response clientRequestApi(String contentType, String headerKey, String headerValue, String frame, File file, String imageType, String messageKey, String messageValue, String url) {
        int counter = 0;
        String errorMessage = "";
        while (counter < 3) {
            counter++;
            try {
                clientRequest = given()
                        .contentType(contentType)
                        .header(headerKey, headerValue)
                        .multiPart(frame, file, imageType)
                        .multiPart(messageKey, messageValue)
                        .multiPart("timestamp", String.valueOf(System.currentTimeMillis()))
                        .cookies(clientInit.getCookies())
                        .when()
                        .post(url)
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();
                errorMessage = "";
                break;
            } catch (AssertionError a) {
                errorMessage = a.getMessage();
                try {
                    Thread.sleep(3000);
                    System.out.println("Retry frame request.Last error:" + errorMessage);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        if (counter == 3 && errorMessage != "") {
            Assert.fail("Client frame request 3 retries failed:" + errorMessage);
        }
        return clientRequest;
    }


    @Step("Client frame request")
    public Response clientRequestApiNew(String contentType, String frame, File file, String imageType, String messageKey, String messageValue, String url, String sessionId, String X_Session_Id, String X_Request_Id) {
        int counter = 0;
        String errorMessage = "";
        while (counter < 5) {
            counter++;
            try {
                clientRequest = given()
                        .contentType(contentType)
//                        .header(headerKey, headerValue)
                        .header("X-Company-ID", System.getProperty("COMPANY_ID"))
                        .header("X-Session-Id", X_Session_Id)
                        .header("X-Flow-Id", sessionId)
                        .header("X-Request-Id", X_Request_Id)

                        .multiPart(frame, file, imageType)
                        .multiPart(messageKey, messageValue)
                        .multiPart("timestamp", String.valueOf(System.currentTimeMillis()))
                        .cookies(clientInit.getCookies())
                        .when()
                        .post(url)
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();
                errorMessage = "";
                break;
            } catch (AssertionError a) {
                errorMessage = a.getMessage();
                try {
                    Thread.sleep(3000);
                    System.out.println("Retry frame request.Last error:" + errorMessage);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        if (counter == 5 && errorMessage != "") {
            Assert.fail("Client frame request 3 retries failed:" + errorMessage);
        }
        return clientRequest;
    }

    @Step("Send client request api OTP stage")
    public Response clientRequestApiOtpStage(String contentType, String headerKey, String headerValue, String jsonObjKey, String jsonObjValue, String messageKey, String messageValue, String url) {
        if (headerKey.equalsIgnoreCase("AUTHORIZATION")) {
            headerKey = "scanovate-auth";
        }
        clientRequest = given()
                .contentType(contentType)
                .header(headerKey, headerValue)
                .multiPart(messageKey, messageValue)
                .multiPart(jsonObjKey, jsonObjValue)
                .multiPart("timestamp", String.valueOf(System.currentTimeMillis()))
                .cookies(clientInit.getCookies())
//                    .log()
//                    .all()
                .when()
                .post(url)
                .then()
                .statusCode(200)
                .extract()
                .response();

        System.out.println("Response for message of type: " + messageValue + ", " + clientRequest.getBody().asString());

        return clientRequest;
    }

    @Step("Send client request api without file")
    public Response clientRequestFileIsMissing(Variables variables, String contentType, String headerKey, String headerValue, String messageKey, String messageValue, String url) {
        if (headerKey.equalsIgnoreCase("AUTHORIZATION")) {
            headerKey = "scanovate-auth";
        }
        clientRequest = given()
                .contentType(contentType)
                .header(headerKey, headerValue)
                .multiPart(messageKey, messageValue)
                .multiPart("frame", "")
                .log()
                .all()
                .when()
                .post(url)
                .then()
                .statusCode(200)
                .extract()
                .response();

        System.out.println(clientRequest.getBody().asString());

        response = clientRequest.getBody().asString().equalsIgnoreCase("{\"success\":true}");
        if (response) {
//            System.out.println(clientRequest.getBody().asString());
        } else {
            System.out.println("\n" + clientRequest.getBody().asString() + "\n");
        }

        JsonPath jsonPath = clientRequest.jsonPath();
        variables.setSuccess(jsonPath.get("success"));
        if (variables.isSuccess()) {
        } else {
            variables.setSuccess(jsonPath.get("success"));
            variables.setErrorCode(jsonPath.get("errorCode"));
            variables.setErrorMessage(jsonPath.get("errorMessage"));
        }

        return clientRequest;
    }


    @Step("Send client request api without file")
    public Response clientRequestFileIsMissingError(Variables variables, String contentType, String headerKey, String headerValue, String url) {
        if (headerKey.equalsIgnoreCase("AUTHORIZATION")) {
            headerKey = "scanovate-auth";
        }
        clientRequest = given()
                .contentType(contentType)
                .header(headerKey, headerValue)
                .log()
                .all()
                .when()
                .post(url)
                .then()
                .statusCode(200)
                .extract()
                .response();

        System.out.println(clientRequest.getBody().asString());

        response = clientRequest.getBody().asString().equalsIgnoreCase("{\"success\":true}");
        if (response) {
//            System.out.println(clientRequest.getBody().asString());
        } else {
            System.out.println("\n" + clientRequest.getBody().asString() + "\n");
        }

        JsonPath jsonPath = clientRequest.jsonPath();
        variables.setSuccess(jsonPath.get("success"));
        if (variables.isSuccess()) {
        } else {
            variables.setSuccess(jsonPath.get("success"));
            variables.setErrorCode(jsonPath.get("errorCode"));
            variables.setErrorMessage(jsonPath.get("errorMessage"));
        }

        return clientRequest;
    }


    @Step("Send single frame client request")
    public Response singleFrameRequest(String libraryNameKey, String libraryNameValue, String caseIdKey, String caseIdValue, String frame, File file, String imageType, String url) {
        clientRequest = given()
                .multiPart(libraryNameKey, libraryNameValue)
                .multiPart(caseIdKey, caseIdValue)
                .multiPart("webhook_params", "{\"my_param_1\": \"param1\"}")
                .multiPart(frame, file, imageType)
//                    .log()
//                    .all()
                .when()
                .post(url)
                .then()
                .statusCode(200)
                .extract()
                .response();

        System.out.println(clientRequest.getBody().asString());

        libraryName = libraryNameValue;
        return clientRequest;
    }

    @Step("create random string")
    public String randomString() {
        // create a string of all characters
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijkelmonpqrstuvwxyz1234567890";
        // create random string builder
        StringBuilder sb = new StringBuilder();
        // create an object of Random class
        Random random = new Random();
        // specify length of random string
        int length = 30;
        for (int i = 0; i < length; i++) {
            // generate random index number
            int index = random.nextInt(alphabet.length());
            // get character specified by index
            // from the string
            char randomChar = alphabet.charAt(index);
            // append the character to string builder
            sb.append(randomChar);
        }

        caseId = sb.toString();
        return caseId;
    }

    public Response ocrLibraries() {
        clientRequest = given()
                .header("accept", "application/json")
                .when()
                .get(UAT_OCR_URL + "/versions")
                .then()
                .statusCode(200)
                .extract()
                .response();
        return clientRequest;
    }
}