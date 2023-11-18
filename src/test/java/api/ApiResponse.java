package api;

import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.util.List;


public class ApiResponse extends ApiRequest {
    public final int MODULAR_TIMEOUT_MILLISECONDS = 90000;
    public final String ACTIVE_MATHILDA = "qa_config1.json";
    //    public final String ACTIVE_STT__MATHILDA = "qa_config.json";
    JsonPath jsonPath;
//    String devMode = "devMode";
//    boolean devModeValue = false;
//
//    String clientTranslationFileName = "clientTranslationFileName";
//    String clientTranslationFileNameValue = "scanovate_default_he.json";

    String ocr_new;
    boolean OCR_NEW;


    public String createRandomUuid() {
        // Generate a UUID version 4
        UUID uuid = UUID.randomUUID();

        // Convert the UUID to a string in the UUID version 4 format
        String uuidString = uuid.toString();

        System.out.println(uuidString);
        return uuidString;
    }


    public void mainClientResponseNew(Variables variables, String contentType, String headerKey, String headerValue, String frame, File file, String imageType, String messageKey, String messageValue, String url, String sessionId, String X_Session_Id, String X_Request_Id) throws IOException {
        if (headerKey.equalsIgnoreCase("AUTHORIZATION")) {
            headerKey = "scanovate-auth";
        }
        if (variables.getCounter() == 0) {
            variables.setCounter(variables.getCounter() + 1);
        }
        System.out.println("File sent to api: " + file.getName());

        if (System.getProperty("OCR_NEW").equalsIgnoreCase("false")) {
            clientRequest = clientRequestApi(
                    contentType,
                    headerKey, headerValue,
                    frame, file, imageType,
                    messageKey, messageValue,
                    url);
            while (clientRequest.getBody().asString().equalsIgnoreCase("{\"success\":true}")) {
                clientRequest = clientRequestApi(contentType, headerKey, headerValue, frame, file, imageType, messageKey, messageValue, url);
                System.out.println("Request #: " + variables.getCounter());
                variables.setCounter(variables.getCounter() + 1);
            }
            jsonPath = clientRequest.jsonPath();
            String actionType = jsonPath.getString("action_type");
            variables.setActionType(actionType);
            System.out.println("response action type:" + actionType);

            if (actionType != null && actionType.equalsIgnoreCase("message")) {
                System.out.println("response message:" + jsonPath.getString("message.message"));
            }
            if (actionType != null && actionType.equalsIgnoreCase("stage")) {
                System.out.println("response stage:" + jsonPath.get("stage"));
            }

            if (!jsonPath.getBoolean("success")) { //invalid token situations or errors while scanning.
                variables.setSuccess(jsonPath.getBoolean("success"));
                variables.setErrorCode(jsonPath.getInt("errorCode"));
                variables.setErrorMessage(jsonPath.getString("errorMessage"));
                return;
            }
        } else {
            clientRequestNew = clientRequestApiNew(
                    contentType,
                    frame, file, imageType,
                    messageKey, messageValue,
                    url, sessionId, X_Session_Id, X_Request_Id);
            while (true) {
                String responseBody = clientRequestNew.getBody().asString();
                System.out.println();

                if (responseBody.equalsIgnoreCase("{\"success\":true}") || responseBody.contains("\"success\":true,\"message\":\"Stage completed\"")) {
                    clientRequestNew = clientRequestApiNew(contentType, frame, file, imageType, messageKey, messageValue, url, sessionId, X_Session_Id, X_Request_Id);
                    System.out.println("Request #: " + variables.getCounter());
                    variables.setCounter(variables.getCounter() + 1);
                } else {
                    break; // Exit the loop if conditions are not met
                }
            }
            System.out.println();
            jsonPath = clientRequestNew.jsonPath();


//            try {
//                if (clientRequestNew.getStatusCode() == 400) {
//                    String responseBody = clientRequestNew.getBody().asString();
//                    JsonPath jsonPath = new JsonPath(responseBody);
//                    String detail = jsonPath.getString("detail");
//                    System.out.println("Detail: " + detail);
//                    variables.setMandatoryFieldsMessage(detail);
//
//                } else {
//                    // Process the response as usual
//                    System.out.println("Request successful");
//                    // Other processing logic here
//
//                }
//
//            } catch (Exception e) {
//                // Handle other exceptions
//                e.printStackTrace();
//            }

            String actionType = jsonPath.getString("action_type");
            variables.setActionType(actionType);
            System.out.println("response action type:" + actionType);
            String status = jsonPath.getString("status");

            if (actionType != null && actionType.equalsIgnoreCase("message")) {
                System.out.println("response message:" + jsonPath.getString("message.message"));
            }
            if (actionType != null && actionType.equalsIgnoreCase("stage")) {
                System.out.println("response stage:" + jsonPath.get("stage"));
            }

            if (status != null && !status.equalsIgnoreCase("success")) { //invalid token situations or errors while scanning.
                variables.setSuccess(jsonPath.getBoolean("success"));
                variables.setErrorCode(jsonPath.getInt("errorCode"));
                variables.setErrorMessage(jsonPath.getString("errorMessage"));
                return;
            }

        }

        if (System.getProperty("OCR_NEW").equalsIgnoreCase("false")) {
            switch (variables.getActionType()) {
                case "message": {
                    setActionTypeMessageVariables(variables, jsonPath);
                    break;
                }
                case "stage": {
                    setActionTypeStageVariables(variables, jsonPath);
                    break;
                }
                case "complete": {
                    setActionTypeCompleteVariables(variables, jsonPath);
                    break;
                }
            }
        } else {
            switch (variables.getActionType()) {
                case "message": {
                    setActionTypeMessageVariablesNew(variables, jsonPath);
                    break;
                }
                case "stage": {
                    setActionTypeStageVariables(variables, jsonPath);
                    break;
                }
                case "complete": {
                    setActionTypeCompleteVariablesNew(variables, jsonPath);
                    break;
                }
            }
        }
    }

    public void clientInitResponseNew(Variables variables, String contentType, String caseIdKey, String caseIdValue, String libraryNameKey, String libraryNameValue, String flowConfigNameKey, String flowConfigNameValue, String url, String sessionId, String X_Session_Id, String X_Request_Id, String clientTranslationFileName, String clientTranslationFileValue, String devModeName, boolean devMode) {
        clientInitNewRequest(contentType, caseIdKey, caseIdValue, libraryNameKey, libraryNameValue, flowConfigNameKey, flowConfigNameValue, url, sessionId, X_Session_Id, X_Request_Id, clientTranslationFileName, clientTranslationFileValue, devModeName, devMode);
//        getOcrLibraries(variables);
        JsonPath jsonPath = clientInit.jsonPath();


        if (!jsonPath.getBoolean("success")) {
            variables.setSuccess(jsonPath.getBoolean("success"));
            variables.setErrorCode(jsonPath.getInt("errorCode"));
            variables.setErrorMessage(jsonPath.getString("errorMessage"));
            return;
        }

        switch (libraryNameValue) {
            case "IL-ID":
                System.out.println("Irad library for IL-ID: " + variables.getIsraelId());
                break;
            case "IL-DL":
                System.out.println("Irad library for IL-DL: " + variables.getIsraelDl());
                break;
            case "DK-DL":
                System.out.println("Irad library for DK-DL: " + variables.getDenmarkDl());
                break;
            case "CNI":
                System.out.println("Irad library for CNI: " + variables.getFranceId());
                break;
            case "MRZ":
                System.out.println("Irad library for MRZ: " + variables.getPassport());
                break;
            case "PHL-CHEQUES":
                System.out.println("Irad library for PHC: " + variables.getPhilippineCheque());
                break;
        }

        variables.setConfigFileName(flowConfigNameValue);
        //       Assert.assertEquals(jsonPath.getMap("").size(), 12);
        switch (libraryNameValue) { //stages array
            case "CAPTURE":
            case "IL-DL":
            case "IL-ID": {
//                Assert.assertEquals(jsonPath.getMap("").size(), 12);
                Assert.assertEquals(jsonPath.getList("stages").size(), 2);
                switch (flowConfigNameValue) {
                    case "mathilda.json": {
                        Assert.assertEquals(jsonPath.getList("stages").toString(), "[front, back]");
                        break;
                    }
                    case "reverseStages.json": {
                        Assert.assertEquals(jsonPath.getList("stages").toString(), "[back, front]");
                        break;
                    }
                    default:
                        if (System.getProperty("COMPANY_ID").equals(flowConfigNameValue)) {
                            Assert.assertEquals(jsonPath.getList("stages").toString(), "[front, back]");
                        }
                }
                break;
            }
//            case "CNI": {
//                Assert.assertEquals(jsonPath.getMap("").size(), 12);
//                switch (flowConfigNameValue) {
//                    case "mathilda.json": {
//                        Assert.assertEquals(jsonPath.getList("stages").size(), 2);
//                        Assert.assertEquals(jsonPath.getList("stages").toString(), "[front, back]");
//                        break;
//                    }
//                    case "reverseStages.json": {
//                        Assert.assertEquals(jsonPath.getList("stages").size(), 5);
//                        Assert.assertEquals(jsonPath.getList("stages").toString(), "[back, video, front, video, otp]");
//                        break;
//                    }
//                    case "gesturesWithActive.json": {
//                        Assert.assertEquals(jsonPath.getList("stages").size(), 6);
//                        Assert.assertEquals(jsonPath.getList("stages").toString(), "[front, video, back, video, otp, gestures]");
//                        break;
//                    }
//                }
//                break;
//            }

            case "MRZ":
            case "DK-DL":
            case "PHL-CHEQUES": {
//                Assert.assertEquals(jsonPath.getMap("").size(), 12);
                Assert.assertEquals(jsonPath.getList("stages").size(), 1);
                Assert.assertEquals(jsonPath.getList("stages").toString(), "[front]");
                break;
            }

            case "C-CARD":
            case "IL-STP": {
                Assert.assertEquals(jsonPath.getMap("").size(), 11);
                Assert.assertEquals(jsonPath.getList("stages").size(), 1);
                Assert.assertEquals(jsonPath.getList("stages").toString(), "[front]");
                break;
            }
            case "LIVENESS": {
                switch (flowConfigNameValue) {
                    case "mathilda.json": {
                        Assert.assertEquals(jsonPath.getList("stages").size(), 1);
                        Assert.assertEquals(jsonPath.getList("stages").toString(), "[passive]");
//                        Assert.assertEquals(jsonPath.getList("stages").size(), 2);
//                        Assert.assertEquals(jsonPath.getList("stages").toString(), "[passive, stt]");
                        break;
                    }
                    case "qa_config.json": {
                        Assert.assertEquals(jsonPath.getList("stages").size(), 6);
                        variables.setStage2RightOrLeft(jsonPath.getString("stages[1]"));
                        variables.setStage4RightOrLeft(jsonPath.getString("stages[3]"));
                        String stage1 = null;
                        String stage3 = null;
                        if (jsonPath.getString("stages[1]").equalsIgnoreCase("left")) {
                            stage1 = "left";
                        } else stage1 = "right";
                        if (jsonPath.getString("stages[3]").equalsIgnoreCase("left")) {
                            stage3 = "left";
                        } else stage3 = "right";
                        Assert.assertEquals(jsonPath.getList("stages").toString(), "[passive, " + stage1 + ", center, " + stage3 + ", otp, stt]");
                        break;
                    }
                    case "qa_config1.json": {
                        Assert.assertEquals(jsonPath.getList("stages").size(), 4);
                        variables.setStage2RightOrLeft(jsonPath.getString("stages[1]"));
                        variables.setStage4RightOrLeft(jsonPath.getString("stages[3]"));
                        String stage1 = null;
                        String stage3 = null;
                        if (jsonPath.getString("stages[1]").equalsIgnoreCase("left")) {
                            stage1 = "left";
                        } else stage1 = "right";
                        if (jsonPath.getString("stages[3]").equalsIgnoreCase("left")) {
                            stage3 = "left";
                        } else stage3 = "right";
                        Assert.assertEquals(jsonPath.getList("stages").toString(), "[passive, " + stage1 + ", center, " + stage3 + "]");
                        break;
                    }
                    case "reverseStages.json": {
                        Assert.assertEquals(jsonPath.getList("stages").size(), 6);
                        variables.setStage2RightOrLeft(jsonPath.getString("stages[2]"));
                        variables.setStage4RightOrLeft(jsonPath.getString("stages[4]"));
                        String stage1 = null;
                        String stage3 = null;
                        if (jsonPath.getString("stages[2]").equalsIgnoreCase("left")) {
                            stage1 = "left";
                        } else stage1 = "right";
                        if (jsonPath.getString("stages[4]").equalsIgnoreCase("left")) {
                            stage3 = "left";
                        } else stage3 = "right";
                        Assert.assertEquals(jsonPath.getList("stages").toString(), "[stt, otp, " + stage1 + ", center, " + stage3 + ", passive]");
                        break;
                    }
                    case "63f225246ef5df9ba8306568": {
                        Assert.assertEquals(jsonPath.getList("stages").size(), 1);
//                        variables.setStage2RightOrLeft(jsonPath.getString("stages[1]"));
//                        variables.setStage4RightOrLeft(jsonPath.getString("stages[3]"));
                        String stage1 = null;
                        String stage3 = null;
                        if (jsonPath.getString("stages[0]").equalsIgnoreCase("passive")) {
                            stage1 = "passive";
                        }
                    }
                }
                break;
            }
        }
        variables.setCpalette(jsonPath.getString("cPalette"));
        switch (libraryNameValue) { //audio
            case "C-CARD":
            case "IL-STP":
            case "CAPTURE":
            case "MRZ":
            case "DK-DL":
            case "PHL-CHEQUES":
            case "IL-ID":
            case "IL-DL": {
                Assert.assertFalse(jsonPath.getBoolean("audio"));
                break;
            }
            case "LIVENESS":
            case "CNI": {
//                Assert.assertTrue(jsonPath.getBoolean("audio"));
                break;
            }
        }
        switch (libraryNameValue) { //card ratio
            case "C-CARD":
            case "IL-STP":
            case "CNI":
            case "CAPTURE":
            case "IL-DL":
            case "IL-ID": {
                Assert.assertEquals(jsonPath.getDouble("cardRatio"), 1.5);
                break;
            }
            case "PHL-CHEQUES": {
                Assert.assertEquals(jsonPath.getDouble("cardRatio"), 2.65);
                break;
            }
            case "LIVENESS": {
                Assert.assertEquals(jsonPath.getDouble("cardRatio"), 0.8);
                break;
            }
        }
        switch (libraryNameValue) { //camera facing mode
            case "C-CARD":
            case "IL-STP":
            case "MRZ":
            case "DK-DL":
            case "PHL-CHEQUES":
            case "IL-ID":
            case "CAPTURE":
            case "IL-DL":
            case "CNI": {
                Assert.assertEquals(jsonPath.getString("cameraFacingMode"), "environment");
                break;
            }
            case "LIVENESS": {
                Assert.assertEquals(jsonPath.getString("cameraFacingMode"), "user");
                break;
            }
        }
        variables.setCameraFacingMode(jsonPath.getString("cameraFacingMode"));
        Assert.assertTrue(jsonPath.getBoolean("success"));
//        Assert.assertEquals(jsonPath.getMap("messageTranslations").size(), 30);
        variables.setMessageTranslations97(jsonPath.getString("messageTranslations.97"));
        variables.setMessageTranslations98(jsonPath.getString("messageTranslations.98"));
        variables.setMessageTranslations99(jsonPath.getString("messageTranslations.99"));
        variables.setMessageTranslations100(jsonPath.getString("messageTranslations.100"));
        variables.setMessageTranslations101(jsonPath.getString("messageTranslations.101"));
        variables.setMessageTranslations102(jsonPath.getString("messageTranslations.102"));
        variables.setMessageTranslations103(jsonPath.getString("messageTranslations.103"));
        variables.setMessageTranslations104(jsonPath.getString("messageTranslations.104"));
        variables.setMessageTranslations149(jsonPath.getString("messageTranslations.149"));
        variables.setMessageTranslations150(jsonPath.getString("messageTranslations.150"));
        variables.setMessageTranslations151(jsonPath.getString("messageTranslations.151"));
        variables.setMessageTranslations152(jsonPath.getString("messageTranslations.152"));
        variables.setMessageTranslations153(jsonPath.getString("messageTranslations.153"));
        variables.setMessageTranslations154(jsonPath.getString("messageTranslations.154"));
        variables.setMessageTranslations155(jsonPath.getString("messageTranslations.155"));
        variables.setMessageTranslations156(jsonPath.getString("messageTranslations.156"));
        variables.setMessageTranslations157(jsonPath.getString("messageTranslations.157"));
        variables.setMessageTranslations158(jsonPath.getString("messageTranslations.158"));
        variables.setMessageTranslations159(jsonPath.getString("messageTranslations.159"));
        variables.setMessageTranslations160(jsonPath.getString("messageTranslations.160"));
        variables.setMessageTranslations161(jsonPath.getString("messageTranslations.161"));
        variables.setMessageTranslations162(jsonPath.getString("messageTranslations.162"));
        variables.setMessageTranslations163(jsonPath.getString("messageTranslations.163"));
        variables.setMessageTranslations164(jsonPath.getString("messageTranslations.164"));
        variables.setMessageTranslations165(jsonPath.getString("messageTranslations.165"));
        variables.setMessageTranslations166(jsonPath.getString("messageTranslations.166"));
        variables.setMessageTranslations167(jsonPath.getString("messageTranslations.167"));
        variables.setMessageTranslations168(jsonPath.getString("messageTranslations.168"));
        variables.setMessageTranslations169(jsonPath.getString("messageTranslations.169"));
        variables.setMessageTranslations250(jsonPath.getString("messageTranslations.250"));
        if (System.getProperty("OCR_NEW").equalsIgnoreCase("false")) {
            Assert.assertNotNull(jsonPath.getString("token"));
            variables.setToken(jsonPath.getString("token"));
        }
        if (System.getProperty("OCR_NEW").equalsIgnoreCase("false")) {
            switch (libraryNameValue) { //preStageTranslations
                case "CAPTURE":
                case "MRZ": {
//                Assert.assertEquals(jsonPath.getMap("preStageTranslations").size(), 10);
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("SCAN_YOUR_CARD"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("SCAN_YOUR_CARD_FRONT_SIDE"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("SCAN_YOUR_CARD_BACK_SIDE"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("DONE"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("TURN_ID_OVER"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("TILT_YOUR_CARD"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("RECORD_OTP"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("RECORD_STT"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("FINISH_RECORD"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("ERROR"));
                    break;
                }
                case "LIVENESS": {
//                Assert.assertEquals(jsonPath.getMap("preStageTranslations").size(), 10);
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("DONE"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("CENTER"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("RIGHT"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("PASSIVE"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("LEFT"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("RECORD_OTP"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("RECORD_STT"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("FINISH_RECORD"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("ERROR"));
//                Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("RECORD_GESTURE"));
                    break;
                }
                case "CNI": {
                    Assert.assertEquals(jsonPath.getMap("preStageTranslations").size(), 9);
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("RECORD_OTP"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("RECORD_STT"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("FINISH_RECORD"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("SCAN_YOUR_CARD_FRONT_SIDE"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("SCAN_YOUR_CARD_BACK_SIDE"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("DONE"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("TURN_ID_OVER"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("TILT_YOUR_CARD"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("RECORD_GESTURE"));
                    break;
                }
                case "PHL-CHEQUES":
                case "DK-DL": {
                    Assert.assertEquals(jsonPath.getMap("preStageTranslations").size(), 8);
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("RECORD_OTP"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("RECORD_STT"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("FINISH_RECORD"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("SCAN_YOUR_CARD_FRONT_SIDE"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("SCAN_YOUR_CARD_BACK_SIDE"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("DONE"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("TURN_ID_OVER"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("TILT_YOUR_CARD"));
                    break;
                }
                case "IL-ID": {
                    Assert.assertEquals(jsonPath.getMap("preStageTranslations").size(), 9);
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("SCAN_YOUR_CARD"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("SCAN_YOUR_CARD_FRONT_SIDE"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("SCAN_YOUR_CARD_BACK_SIDE"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("TILT_YOUR_CARD"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("DONE"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("RECORD_OTP"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("RECORD_STT"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("FINISH_RECORD"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("ERROR"));
                    break;
                }
                case "IL-DL": {
                    Assert.assertEquals(jsonPath.getMap("preStageTranslations").size(), 10);
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("SCAN_YOUR_CARD"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("SCAN_YOUR_CARD_FRONT_SIDE"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("SCAN_YOUR_CARD_BACK_SIDE"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("TILT_YOUR_CARD"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("DONE"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("TURN_ID_OVER"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("RECORD_OTP"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("RECORD_STT"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("FINISH_RECORD"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("ERROR"));
                    break;
                }
                case "C-CARD":
                case "IL-STP": {
                    Assert.assertEquals(jsonPath.getMap("preStageTranslations").size(), 8);
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("SCAN_YOUR_CARD"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("SCAN_YOUR_CARD_FRONT_SIDE"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("TILT_YOUR_CARD"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("DONE"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("RECORD_OTP"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("RECORD_STT"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("FINISH_RECORD"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("ERROR"));
                    break;
                }
                default: {
                    Assert.assertEquals(jsonPath.getMap("preStageTranslations").size(), 9);
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("RECORD_OTP"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("RECORD_STT"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("SCAN_YOUR_CARD"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("SCAN_YOUR_CARD_FRONT_SIDE"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("SCAN_YOUR_CARD_BACK_SIDE"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("DONE"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("TURN_ID_OVER"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("FINISH_RECORD"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("ERROR"));
                    break;
                }

            }
        }
//        Assert.assertEquals(jsonPath.getMap("styles").size(), 13);
    }

    public void clientInitResponse(Variables variables, String contentType, String caseIdKey, String caseIdValue, String libraryNameKey, String libraryNameValue, String flowConfigNameKey, String flowConfigNameValue, String url) {
        if (System.getProperty("OCR_NEW").equalsIgnoreCase("false")) {
            clientInitRequest(contentType, caseIdKey, caseIdValue, libraryNameKey, libraryNameValue, flowConfigNameKey, flowConfigNameValue, url);
//        getOcrLibraries(variables);
        }
        JsonPath jsonPath = clientInit.jsonPath();


        if (!jsonPath.getBoolean("success")) {
            variables.setSuccess(jsonPath.getBoolean("success"));
            variables.setErrorCode(jsonPath.getInt("errorCode"));
            variables.setErrorMessage(jsonPath.getString("errorMessage"));
            return;
        }

        switch (libraryNameValue) {
            case "IL-ID":
                System.out.println("Irad library for IL-ID: " + variables.getIsraelId());
                break;
            case "IL-DL":
                System.out.println("Irad library for IL-DL: " + variables.getIsraelDl());
                break;
            case "DK-DL":
                System.out.println("Irad library for DK-DL: " + variables.getDenmarkDl());
                break;
            case "CNI":
                System.out.println("Irad library for CNI: " + variables.getFranceId());
                break;
            case "MRZ":
                System.out.println("Irad library for MRZ: " + variables.getPassport());
                break;
            case "PHL-CHEQUES":
                System.out.println("Irad library for PHC: " + variables.getPhilippineCheque());
                break;
        }

        variables.setConfigFileName(flowConfigNameValue);
        //       Assert.assertEquals(jsonPath.getMap("").size(), 12);
        switch (libraryNameValue) { //stages array
            case "CAPTURE":
            case "IL-DL":
            case "IL-ID": {
//                Assert.assertEquals(jsonPath.getMap("").size(), 12);
                Assert.assertEquals(jsonPath.getList("stages").size(), 2);
                switch (flowConfigNameValue) {
                    case "mathilda.json": {
                        Assert.assertEquals(jsonPath.getList("stages").toString(), "[front, back]");
                        break;
                    }
                    case "reverseStages.json": {
                        Assert.assertEquals(jsonPath.getList("stages").toString(), "[back, front]");
                        break;
                    }
                    default:
                        if (System.getProperty("COMPANY_ID").equals(flowConfigNameValue)) {
                            Assert.assertEquals(jsonPath.getList("stages").toString(), "[front, back]");
                        }
                }
                break;
            }
//            case "CNI": {
//                Assert.assertEquals(jsonPath.getMap("").size(), 12);
//                switch (flowConfigNameValue) {
//                    case "mathilda.json": {
//                        Assert.assertEquals(jsonPath.getList("stages").size(), 2);
//                        Assert.assertEquals(jsonPath.getList("stages").toString(), "[front, back]");
//                        break;
//                    }
//                    case "reverseStages.json": {
//                        Assert.assertEquals(jsonPath.getList("stages").size(), 5);
//                        Assert.assertEquals(jsonPath.getList("stages").toString(), "[back, video, front, video, otp]");
//                        break;
//                    }
//                    case "gesturesWithActive.json": {
//                        Assert.assertEquals(jsonPath.getList("stages").size(), 6);
//                        Assert.assertEquals(jsonPath.getList("stages").toString(), "[front, video, back, video, otp, gestures]");
//                        break;
//                    }
//                }
//                break;
//            }

            case "MRZ":
            case "DK-DL":
            case "PHL-CHEQUES": {
//                Assert.assertEquals(jsonPath.getMap("").size(), 12);
                Assert.assertEquals(jsonPath.getList("stages").size(), 1);
                Assert.assertEquals(jsonPath.getList("stages").toString(), "[front]");
                break;
            }

            case "C-CARD":
            case "IL-STP": {
                Assert.assertEquals(jsonPath.getMap("").size(), 13);
                Assert.assertEquals(jsonPath.getList("stages").size(), 1);
                Assert.assertEquals(jsonPath.getList("stages").toString(), "[front]");
                break;
            }
            case "LIVENESS": {
                switch (flowConfigNameValue) {
                    case "mathilda.json": {
                        Assert.assertEquals(jsonPath.getList("stages").size(), 1);
                        Assert.assertEquals(jsonPath.getList("stages").toString(), "[passive]");
//                        Assert.assertEquals(jsonPath.getList("stages").size(), 2);
//                        Assert.assertEquals(jsonPath.getList("stages").toString(), "[passive, stt]");
                        break;
                    }
                    case "qa_config.json": {
                        Assert.assertEquals(jsonPath.getList("stages").size(), 6);
                        variables.setStage2RightOrLeft(jsonPath.getString("stages[1]"));
                        variables.setStage4RightOrLeft(jsonPath.getString("stages[3]"));
                        String stage1 = null;
                        String stage3 = null;
                        if (jsonPath.getString("stages[1]").equalsIgnoreCase("left")) {
                            stage1 = "left";
                        } else stage1 = "right";
                        if (jsonPath.getString("stages[3]").equalsIgnoreCase("left")) {
                            stage3 = "left";
                        } else stage3 = "right";
                        Assert.assertEquals(jsonPath.getList("stages").toString(), "[passive, " + stage1 + ", center, " + stage3 + ", otp, stt]");
                        break;
                    }
                    case "qa_config1.json": {
                        Assert.assertEquals(jsonPath.getList("stages").size(), 4);
                        variables.setStage2RightOrLeft(jsonPath.getString("stages[1]"));
                        variables.setStage4RightOrLeft(jsonPath.getString("stages[3]"));
                        String stage1 = null;
                        String stage3 = null;
                        if (jsonPath.getString("stages[1]").equalsIgnoreCase("left")) {
                            stage1 = "left";
                        } else stage1 = "right";
                        if (jsonPath.getString("stages[3]").equalsIgnoreCase("left")) {
                            stage3 = "left";
                        } else stage3 = "right";
                        Assert.assertEquals(jsonPath.getList("stages").toString(), "[passive, " + stage1 + ", center, " + stage3 + "]");
                        break;
                    }
                    case "reverseStages.json": {
                        Assert.assertEquals(jsonPath.getList("stages").size(), 6);
                        variables.setStage2RightOrLeft(jsonPath.getString("stages[2]"));
                        variables.setStage4RightOrLeft(jsonPath.getString("stages[4]"));
                        String stage1 = null;
                        String stage3 = null;
                        if (jsonPath.getString("stages[2]").equalsIgnoreCase("left")) {
                            stage1 = "left";
                        } else stage1 = "right";
                        if (jsonPath.getString("stages[4]").equalsIgnoreCase("left")) {
                            stage3 = "left";
                        } else stage3 = "right";
                        Assert.assertEquals(jsonPath.getList("stages").toString(), "[stt, otp, " + stage1 + ", center, " + stage3 + ", passive]");
                        break;
                    }
                }
                break;
            }
        }
        variables.setCpalette(jsonPath.getString("cPalette"));
        switch (libraryNameValue) { //audio
            case "C-CARD":
            case "IL-STP":
            case "CAPTURE":
            case "MRZ":
            case "DK-DL":
            case "PHL-CHEQUES":
            case "IL-ID":
            case "IL-DL": {
                Assert.assertFalse(jsonPath.getBoolean("audio"));
                break;
            }
            case "LIVENESS":
            case "CNI": {
//                Assert.assertTrue(jsonPath.getBoolean("audio"));
                break;
            }
        }
        switch (libraryNameValue) { //card ratio
            case "C-CARD":
            case "IL-STP":
            case "CNI":
            case "CAPTURE":
            case "IL-DL":
            case "IL-ID": {
                Assert.assertEquals(jsonPath.getDouble("cardRatio"), 1.5);
                break;
            }
            case "PHL-CHEQUES": {
                Assert.assertEquals(jsonPath.getDouble("cardRatio"), 2.65);
                break;
            }
            case "LIVENESS": {
                Assert.assertEquals(jsonPath.getDouble("cardRatio"), 0.8);
                break;
            }
        }
        switch (libraryNameValue) { //camera facing mode
            case "C-CARD":
            case "IL-STP":
            case "MRZ":
            case "DK-DL":
            case "PHL-CHEQUES":
            case "IL-ID":
            case "CAPTURE":
            case "IL-DL":
            case "CNI": {
                Assert.assertEquals(jsonPath.getString("cameraFacingMode"), "environment");
                break;
            }
            case "LIVENESS": {
                Assert.assertEquals(jsonPath.getString("cameraFacingMode"), "face");
                break;
            }
        }
        variables.setCameraFacingMode(jsonPath.getString("cameraFacingMode"));
        Assert.assertTrue(jsonPath.getBoolean("success"));
//        Assert.assertEquals(jsonPath.getMap("messageTranslations").size(), 30);
        variables.setMessageTranslations97(jsonPath.getString("messageTranslations.97"));
        variables.setMessageTranslations98(jsonPath.getString("messageTranslations.98"));
        variables.setMessageTranslations99(jsonPath.getString("messageTranslations.99"));
        variables.setMessageTranslations100(jsonPath.getString("messageTranslations.100"));
        variables.setMessageTranslations101(jsonPath.getString("messageTranslations.101"));
        variables.setMessageTranslations102(jsonPath.getString("messageTranslations.102"));
        variables.setMessageTranslations103(jsonPath.getString("messageTranslations.103"));
        variables.setMessageTranslations104(jsonPath.getString("messageTranslations.104"));
        variables.setMessageTranslations149(jsonPath.getString("messageTranslations.149"));
        variables.setMessageTranslations150(jsonPath.getString("messageTranslations.150"));
        variables.setMessageTranslations151(jsonPath.getString("messageTranslations.151"));
        variables.setMessageTranslations152(jsonPath.getString("messageTranslations.152"));
        variables.setMessageTranslations153(jsonPath.getString("messageTranslations.153"));
        variables.setMessageTranslations154(jsonPath.getString("messageTranslations.154"));
        variables.setMessageTranslations155(jsonPath.getString("messageTranslations.155"));
        variables.setMessageTranslations156(jsonPath.getString("messageTranslations.156"));
        variables.setMessageTranslations157(jsonPath.getString("messageTranslations.157"));
        variables.setMessageTranslations158(jsonPath.getString("messageTranslations.158"));
        variables.setMessageTranslations159(jsonPath.getString("messageTranslations.159"));
        variables.setMessageTranslations160(jsonPath.getString("messageTranslations.160"));
        variables.setMessageTranslations161(jsonPath.getString("messageTranslations.161"));
        variables.setMessageTranslations162(jsonPath.getString("messageTranslations.162"));
        variables.setMessageTranslations163(jsonPath.getString("messageTranslations.163"));
        variables.setMessageTranslations164(jsonPath.getString("messageTranslations.164"));
        variables.setMessageTranslations165(jsonPath.getString("messageTranslations.165"));
        variables.setMessageTranslations166(jsonPath.getString("messageTranslations.166"));
        variables.setMessageTranslations167(jsonPath.getString("messageTranslations.167"));
        variables.setMessageTranslations168(jsonPath.getString("messageTranslations.168"));
        variables.setMessageTranslations169(jsonPath.getString("messageTranslations.169"));
        variables.setMessageTranslations250(jsonPath.getString("messageTranslations.250"));
        if (System.getProperty("OCR_NEW").equalsIgnoreCase("false")) {
            Assert.assertNotNull(jsonPath.getString("token"));
            variables.setToken(jsonPath.getString("token"));
        }
        if (System.getProperty("OCR_NEW").equalsIgnoreCase("false")) {
            switch (libraryNameValue) { //preStageTranslations
                case "CAPTURE":
                case "MRZ": {
//                Assert.assertEquals(jsonPath.getMap("preStageTranslations").size(), 10);
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("SCAN_YOUR_CARD"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("SCAN_YOUR_CARD_FRONT_SIDE"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("SCAN_YOUR_CARD_BACK_SIDE"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("DONE"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("TURN_ID_OVER"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("TILT_YOUR_CARD"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("RECORD_OTP"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("RECORD_STT"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("FINISH_RECORD"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("ERROR"));
                    break;
                }
                case "LIVENESS": {
//                Assert.assertEquals(jsonPath.getMap("preStageTranslations").size(), 10);
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("DONE"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("CENTER"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("RIGHT"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("PASSIVE"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("LEFT"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("RECORD_OTP"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("RECORD_STT"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("FINISH_RECORD"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("ERROR"));
//                Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("RECORD_GESTURE"));
                    break;
                }
                case "CNI": {
                    Assert.assertEquals(jsonPath.getMap("preStageTranslations").size(), 9);
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("RECORD_OTP"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("RECORD_STT"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("FINISH_RECORD"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("SCAN_YOUR_CARD_FRONT_SIDE"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("SCAN_YOUR_CARD_BACK_SIDE"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("DONE"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("TURN_ID_OVER"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("TILT_YOUR_CARD"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("RECORD_GESTURE"));
                    break;
                }
                case "PHL-CHEQUES":
                case "DK-DL": {
                    Assert.assertEquals(jsonPath.getMap("preStageTranslations").size(), 8);
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("RECORD_OTP"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("RECORD_STT"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("FINISH_RECORD"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("SCAN_YOUR_CARD_FRONT_SIDE"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("SCAN_YOUR_CARD_BACK_SIDE"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("DONE"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("TURN_ID_OVER"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("TILT_YOUR_CARD"));
                    break;
                }
                case "IL-ID": {
                    Assert.assertEquals(jsonPath.getMap("preStageTranslations").size(), 9);
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("SCAN_YOUR_CARD"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("SCAN_YOUR_CARD_FRONT_SIDE"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("SCAN_YOUR_CARD_BACK_SIDE"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("TILT_YOUR_CARD"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("DONE"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("RECORD_OTP"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("RECORD_STT"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("FINISH_RECORD"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("ERROR"));
                    break;
                }
                case "IL-DL": {
                    Assert.assertEquals(jsonPath.getMap("preStageTranslations").size(), 10);
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("SCAN_YOUR_CARD"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("SCAN_YOUR_CARD_FRONT_SIDE"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("SCAN_YOUR_CARD_BACK_SIDE"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("TILT_YOUR_CARD"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("DONE"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("TURN_ID_OVER"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("RECORD_OTP"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("RECORD_STT"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("FINISH_RECORD"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("ERROR"));
                    break;
                }
                case "C-CARD":
                case "IL-STP": {
                    Assert.assertEquals(jsonPath.getMap("preStageTranslations").size(), 8);
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("SCAN_YOUR_CARD"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("SCAN_YOUR_CARD_FRONT_SIDE"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("TILT_YOUR_CARD"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("DONE"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("RECORD_OTP"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("RECORD_STT"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("FINISH_RECORD"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("ERROR"));
                    break;
                }
                default: {
                    Assert.assertEquals(jsonPath.getMap("preStageTranslations").size(), 9);
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("RECORD_OTP"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("RECORD_STT"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("SCAN_YOUR_CARD"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("SCAN_YOUR_CARD_FRONT_SIDE"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("SCAN_YOUR_CARD_BACK_SIDE"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("DONE"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("TURN_ID_OVER"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("FINISH_RECORD"));
                    Assert.assertTrue(jsonPath.getMap("preStageTranslations").containsKey("ERROR"));
                    break;
                }

            }
        }
//        Assert.assertEquals(jsonPath.getMap("styles").size(), 13);
    }

    @Step("Init liveness timeout variables according to the stage which had timeout status")
    public void setNewTimeoutValues(Variables variables, JsonPath jsonPath, int index) {
        Assert.assertEquals(jsonPath.getMap("").size(), 6);
        Assert.assertEquals(jsonPath.getList("stages").size(), index + 1);
        Assert.assertEquals(jsonPath.getMap("stages[" + index + "]").size(), 4);
        Assert.assertEquals(jsonPath.getMap("stages[" + index + "].stage").size(), 2);
        Assert.assertEquals(jsonPath.getMap("stages[" + index + "].payload.images").size(), 1);

        variables.setStatus(jsonPath.getString("status"));
        variables.setCaseId(jsonPath.getString("caseId"));
        variables.setConfigFileName(jsonPath.getString("configFilename"));
        variables.setSuccess(jsonPath.getBoolean("success"));
        variables.setActionTypeTimeout(jsonPath.getString("stages[" + index + "].action_type"));
        variables.setOrderTimeout(jsonPath.getInt("stages[" + index + "].stage.order"));
        variables.setStageTypeTimeout(jsonPath.getString("stages[" + index + "].stage.type"));
        variables.setLastReceivedImage(jsonPath.getString("stages[" + index + "].payload.images.last_received_image"));
        variables.setStageStatusTimeout(jsonPath.getString("stages[" + index + "].status"));
    }

    @Step("Init json values of blue Id")
    public void setValuesOfBlueId(Variables variables, JsonPath jsonPath) {
        try {
            if (!OCR_NEW) {
                if (jsonPath.getMap("stages[0].payload.fields").size() < 8)
                    Assert.assertEquals(jsonPath.getMap("stages[0].payload.fields").size(), 7);
                else
                    Assert.assertEquals(jsonPath.getMap("stages[0].payload.fields").size(), 9); //Changing from ID to ID, need to move to "setValuesof...ID"

                variables.setActionType1(jsonPath.getString("stages[0].action_type"));
                variables.setOrder(jsonPath.getInt("stages[0].stage.order"));
                variables.setTypeUnderStage(jsonPath.getString("stages[0].stage.type"));

                variables.setDateOfBirth(jsonPath.get("stages[0].payload.fields.date_of_birth"));
                variables.setDateOfExpiry(jsonPath.get("stages[0].payload.fields.date_of_expiry"));
                variables.setDateOfIssue(jsonPath.get("stages[0].payload.fields.date_of_issue"));
                variables.setFirstNameHebrew(jsonPath.get("stages[0].payload.fields.first_name_hebrew"));
                variables.setGender(jsonPath.get("stages[0].payload.fields.gender"));
                variables.setCitizenship(jsonPath.get("stages[0].payload.fields.citizenship"));
                variables.setIdNumber(jsonPath.getInt("stages[0].payload.fields.id_number"));
                variables.setLastNameHebrew(jsonPath.get("stages[0].payload.fields.last_name_hebrew"));
                variables.setPlaceOfBirth(jsonPath.get("stages[0].payload.fields.place_of_birth"));
                variables.setStageStatus(jsonPath.getString("stages[0].status"));

                variables.setCardType(jsonPath.getString("stages[0].payload.card_type"));

                variables.setFaceImage(jsonPath.getString("stages[0].payload.images.face_image"));
                variables.setInputImage(jsonPath.getString("stages[0].payload.images.input_image"));
                variables.setCroppedImage(jsonPath.getString("stages[0].payload.images.cropped_image"));

                variables.setImageIsColorful(jsonPath.getBoolean("stages[0].payload.auth.image_is_colorful"));
                variables.setIdChecksumValid(jsonPath.getBoolean("stages[0].payload.auth.id_checksum_valid"));
                variables.setTemplateMatched(jsonPath.getBoolean("stages[0].payload.auth.template_matched"));
                if (jsonPath.getMap("stages[0].payload.auth").containsKey("period_between_issue_and_expiry_valid")) {
                    variables.setPeriodBetweenIssueAndExpiry(jsonPath.getBoolean("stages[0].payload.auth.period_between_issue_and_expiry_valid"));
                }
                variables.setStampDetected(jsonPath.getBoolean("stages[0].payload.auth.stamp_detected"));
                variables.setFaceSizeValid(jsonPath.getBoolean("stages[0].payload.auth.face_size_valid"));
                variables.setFaceRotationValid(jsonPath.getBoolean("stages[0].payload.auth.face_rotation_valid"));
                variables.setFacePositionValid(jsonPath.getBoolean("stages[0].payload.auth.face_position_valid"));
                variables.setDocumentInFrame(jsonPath.getBoolean("stages[0].payload.auth.document_in_frame"));
                variables.setExpiryDateValid(jsonPath.getBoolean("stages[0].payload.auth.expiry_date_valid"));
                variables.setIssueDateValid(jsonPath.getBoolean("stages[0].payload.auth.issue_date_valid"));
            } else {

                if (jsonPath.getMap("stages[0].payload.fields").size() < 9)
                    Assert.assertEquals(jsonPath.getMap("stages[0].payload.fields").size(), 8);
                else
                    Assert.assertEquals(jsonPath.getMap("stages[0].payload.fields").size(), 9); //Changing from ID to ID, need to move to "setValuesof...ID"

                variables.setActionType1(jsonPath.getString("stages[0].action_type"));
                variables.setOrder(jsonPath.getInt("stages[0].order"));
                variables.setTypeUnderStage(jsonPath.getString("stages[0].type"));

                variables.setDateOfBirth(jsonPath.get("stages[0].payload.fields.date_of_birth"));
                variables.setDateOfExpiry(jsonPath.get("stages[0].payload.fields.date_of_expiry"));
                variables.setDateOfIssue(jsonPath.get("stages[0].payload.fields.date_of_issue"));
                variables.setFirstNameHebrew(jsonPath.get("stages[0].payload.fields.first_name_hebrew"));
                variables.setGender(jsonPath.get("stages[0].payload.fields.gender"));
                variables.setCitizenship(jsonPath.get("stages[0].payload.fields.citizenship"));
                variables.setIdNumber(jsonPath.getInt("stages[0].payload.fields.id_number"));
                variables.setLastNameHebrew(jsonPath.get("stages[0].payload.fields.last_name_hebrew"));
                variables.setPlaceOfBirth(jsonPath.get("stages[0].payload.fields.place_of_birth"));
                variables.setStageStatus(jsonPath.getString("stages[0].status"));

                variables.setCardType(jsonPath.getString("stages[0].payload.card_type"));

                variables.setFaceImage(jsonPath.getString("stages[0].payload.images.face_image"));
                variables.setInputImage(jsonPath.getString("stages[0].payload.images.input_image"));
                variables.setCroppedImage(jsonPath.getString("stages[0].payload.images.cropped_image"));

                variables.setImageIsColorful(jsonPath.getBoolean("stages[0].payload.auth.image_is_colorful"));
                variables.setIdChecksumValid(jsonPath.getBoolean("stages[0].payload.auth.id_checksum_valid"));
                variables.setTemplateMatched(jsonPath.getBoolean("stages[0].payload.auth.template_matched"));
                if (jsonPath.getMap("stages[0].payload.auth").containsKey("period_between_issue_and_expiry_valid")) {
                    variables.setPeriodBetweenIssueAndExpiry(jsonPath.getBoolean("stages[0].payload.auth.period_between_issue_and_expiry_valid"));
                }
                variables.setStampDetected(jsonPath.getBoolean("stages[0].payload.auth.stamp_detected"));
                variables.setFaceSizeValid(jsonPath.getBoolean("stages[0].payload.auth.face_size_valid"));
                variables.setFaceRotationValid(jsonPath.getBoolean("stages[0].payload.auth.face_rotation_valid"));
                variables.setFacePositionValid(jsonPath.getBoolean("stages[0].payload.auth.face_position_valid"));
                variables.setDocumentInFrame(jsonPath.getBoolean("stages[0].payload.auth.document_in_frame"));
                variables.setExpiryDateValid(jsonPath.getBoolean("stages[0].payload.auth.expiry_date_valid"));
                variables.setIssueDateValid(jsonPath.getBoolean("stages[0].payload.auth.issue_date_valid"));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Step("Init json values of green Id")
    public void setValuesOfGreenId(Variables variables, JsonPath jsonPath) {
        try {
            if (!OCR_NEW) {
                if (jsonPath.getMap("stages[0].payload.fields").size() < 7)
                    Assert.assertEquals(jsonPath.getMap("stages[0].payload.fields").size(), 6);
                else
                    Assert.assertEquals(jsonPath.getMap("stages[0].payload.fields").size(), 7); //Changing from ID to ID, need to move to "setValuesof...ID"

                variables.setActionType1(jsonPath.getString("stages[0].action_type"));
                variables.setOrder(jsonPath.getInt("stages[0].stage.order"));
                variables.setTypeUnderStage(jsonPath.getString("stages[0].stage.type"));

                variables.setDateOfBirth(jsonPath.get("stages[0].payload.fields.date_of_birth"));
                variables.setDateOfIssue(jsonPath.get("stages[0].payload.fields.date_of_issue"));
                variables.setFirstNameHebrew(jsonPath.get("stages[0].payload.fields.first_name_hebrew"));
                variables.setGender(jsonPath.get("stages[0].payload.fields.gender"));
                variables.setIdNumber(jsonPath.getInt("stages[0].payload.fields.id_number"));
                variables.setLastNameHebrew(jsonPath.get("stages[0].payload.fields.last_name_hebrew"));
                variables.setPlaceOfBirth(jsonPath.get("stages[0].payload.fields.place_of_birth"));
                variables.setStageStatus(jsonPath.getString("stages[0].status"));

                variables.setCardType(jsonPath.getString("stages[0].payload.card_type"));

                variables.setFaceImage(jsonPath.getString("stages[0].payload.images.face_image"));
                variables.setInputImage(jsonPath.getString("stages[0].payload.images.input_image"));
                variables.setCroppedImage(jsonPath.getString("stages[0].payload.images.cropped_image"));

                variables.setImageIsColorful(jsonPath.getBoolean("stages[0].payload.auth.image_is_colorful"));
                variables.setIdChecksumValid(jsonPath.getBoolean("stages[0].payload.auth.id_checksum_valid"));
                variables.setTemplateMatched(jsonPath.getBoolean("stages[0].payload.auth.template_matched"));
                variables.setStampDetected(jsonPath.getBoolean("stages[0].payload.auth.stamp_detected"));
                variables.setFaceSizeValid(jsonPath.getBoolean("stages[0].payload.auth.face_size_valid"));
                variables.setFaceRotationValid(jsonPath.getBoolean("stages[0].payload.auth.face_rotation_valid"));
                variables.setFacePositionValid(jsonPath.getBoolean("stages[0].payload.auth.face_position_valid"));
                variables.setDocumentInFrame(jsonPath.getBoolean("stages[0].payload.auth.document_in_frame"));
                variables.setIssueDateValid(jsonPath.getBoolean("stages[0].payload.auth.issue_date_valid"));
            } else {
                if (jsonPath.getMap("stages[0].payload.fields").size() < 7)
                    Assert.assertEquals(jsonPath.getMap("stages[0].payload.fields").size(), 6);
                else
                    Assert.assertEquals(jsonPath.getMap("stages[0].payload.fields").size(), 7); //Changing from ID to ID, need to move to "setValuesof...ID"

                variables.setActionType1(jsonPath.getString("stages[0].action_type"));
                variables.setOrder(jsonPath.getInt("stages[0].order"));
                variables.setTypeUnderStage(jsonPath.getString("stages[0].type"));

                variables.setDateOfBirth(jsonPath.get("stages[0].payload.fields.date_of_birth"));
                variables.setDateOfIssue(jsonPath.get("stages[0].payload.fields.date_of_issue"));
                variables.setFirstNameHebrew(jsonPath.get("stages[0].payload.fields.first_name_hebrew"));
                variables.setGender(jsonPath.get("stages[0].payload.fields.gender"));
                variables.setIdNumber(jsonPath.getInt("stages[0].payload.fields.id_number"));
                variables.setLastNameHebrew(jsonPath.get("stages[0].payload.fields.last_name_hebrew"));
                variables.setPlaceOfBirth(jsonPath.get("stages[0].payload.fields.place_of_birth"));
                variables.setStageStatus(jsonPath.getString("stages[0].status"));

                variables.setCardType(jsonPath.getString("stages[0].payload.card_type"));

                variables.setFaceImage(jsonPath.getString("stages[0].payload.images.face_image"));
                variables.setInputImage(jsonPath.getString("stages[0].payload.images.input_image"));
                variables.setCroppedImage(jsonPath.getString("stages[0].payload.images.cropped_image"));

                variables.setImageIsColorful(jsonPath.getBoolean("stages[0].payload.auth.image_is_colorful"));
                variables.setIdChecksumValid(jsonPath.getBoolean("stages[0].payload.auth.id_checksum_valid"));
                variables.setTemplateMatched(jsonPath.getBoolean("stages[0].payload.auth.template_matched"));
                variables.setStampDetected(jsonPath.getBoolean("stages[0].payload.auth.stamp_detected"));
                variables.setFaceSizeValid(jsonPath.getBoolean("stages[0].payload.auth.face_size_valid"));
                variables.setFaceRotationValid(jsonPath.getBoolean("stages[0].payload.auth.face_rotation_valid"));
                variables.setFacePositionValid(jsonPath.getBoolean("stages[0].payload.auth.face_position_valid"));
                variables.setDocumentInFrame(jsonPath.getBoolean("stages[0].payload.auth.document_in_frame"));
                variables.setIssueDateValid(jsonPath.getBoolean("stages[0].payload.auth.issue_date_valid"));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Step("Preparing api to otp stage")
    public void clientResponseLivenessOtpStage(Variables variables, String contentType, String headerKey, String headerValue, String jsonObjKey, String jsonObjValue, String messageKey, String messageValue, String url) {
        clientRequest = clientRequestApiOtpStage(contentType, headerKey, headerValue, jsonObjKey, jsonObjValue, messageKey, messageValue, url);
    }

    @Step("Sending audio file to api")
    public void sendOtpAudioFile(String token, File file, String url) {
        System.out.println("File: " + file);
        clientRequest = clientRequestApi("multipart/form-data", "Authorization", "Bearer " + token, "frame", file, "audio/wav", "message_type", "audio_chunk_request", url);
    }

    @Step("Verification of the init variables")
    public void verifyInitVariables(Variables variables) {
        if (System.getProperty("OCR_NEW").equalsIgnoreCase("false")) {
            Assert.assertNotNull(variables.getCpalette());
            System.out.println("LibraryName: " + libraryName);
            Assert.assertEquals(variables.getMessageTranslations97(), "No cheque detected");
            Assert.assertEquals(variables.getMessageTranslations98(), "Cheque not in frame");
            Assert.assertEquals(variables.getMessageTranslations99(), "Cheque in frame");
            Assert.assertEquals(variables.getMessageTranslations100(), "Wrong side");
            Assert.assertEquals(variables.getMessageTranslations101(), "No card detected");
            Assert.assertEquals(variables.getMessageTranslations102(), "Card not in frame");
            Assert.assertEquals(variables.getMessageTranslations103(), "Card detected");
            Assert.assertEquals(variables.getMessageTranslations104(), "Card too far");
            Assert.assertEquals(variables.getMessageTranslations149(), "Unexpected response received");
            Assert.assertEquals(variables.getMessageTranslations150(), "Avoid moving");
            Assert.assertEquals(variables.getMessageTranslations151(), "Look left");
            Assert.assertEquals(variables.getMessageTranslations152(), "Look right");
            Assert.assertEquals(variables.getMessageTranslations153(), "Look straight");
            Assert.assertEquals(variables.getMessageTranslations154(), "face too close");
            Assert.assertEquals(variables.getMessageTranslations155(), "Thinking about it...");
            Assert.assertEquals(variables.getMessageTranslations156(), "No face found");
            Assert.assertEquals(variables.getMessageTranslations157(), "Too many faces");
            Assert.assertEquals(variables.getMessageTranslations158(), "Stay in the frame");
            Assert.assertEquals(variables.getMessageTranslations159(), "Come closer");
            Assert.assertEquals(variables.getMessageTranslations160(), "You're too close");
            Assert.assertEquals(variables.getMessageTranslations161(), "Broken image");
            Assert.assertEquals(variables.getMessageTranslations162(), "Hold your phone straight");
            Assert.assertEquals(variables.getMessageTranslations163(), "Stay in the frame");
            Assert.assertEquals(variables.getMessageTranslations164(), "Frame received");
            Assert.assertEquals(variables.getMessageTranslations165(), "Face not properly illuminated");
            Assert.assertEquals(variables.getMessageTranslations166(), "Face not in focus");
            Assert.assertEquals(variables.getMessageTranslations167(), "Face mask detected");
            Assert.assertEquals(variables.getMessageTranslations168(), "Not enough space around image for full crop");

            Assert.assertNotNull(variables.getToken());
        }
    }

    @Step("Get a string and divide it by and desire quantity")
    private static List<String> getParts(String string, int partitionSize) {
        List<String> parts = new ArrayList<>();
        int len = string.length();
        for (int i = 0; i < len; i += partitionSize) {
            parts.add(string.substring(i, Math.min(len, i + partitionSize)));
        }
        return parts;
    }

    @Step("Injecting new pallet to a pixel")
    public File setNewPixelsPallet(Variables variables, File file, float compressionQuality) throws IOException {
        List<String> partsArray = new ArrayList<>();
        List<Color> colorArrayFromPallet = new ArrayList<>();

        System.out.println(variables.getCpalette());
        for (String part : getParts(variables.getCpalette(), 6)) {
            System.out.println(part);
            partsArray.add(part);
            colorArrayFromPallet.add(Color.decode("#" + part));
        }
        for (Color col : colorArrayFromPallet) {
            System.out.println(col);
        }

        BufferedImage bufferedImage = ImageIO.read(file);
        String TopLeft = bufferedImage.getMinX() + "," + bufferedImage.getMinY();
        String TopRight = bufferedImage.getWidth() + "," + bufferedImage.getMinY();
        String BottomRight = bufferedImage.getWidth() + "," + bufferedImage.getHeight();
        String BottomLeft = bufferedImage.getMinX() + "," + bufferedImage.getHeight();
        System.out.println("Top left: " + TopLeft);
        System.out.println("Top right: " + TopRight);
        System.out.println("Bottom right: " + BottomRight);
        System.out.println("Bottom left: " + BottomLeft);
        System.out.println("****");

        //Color the top left corner
        for (int i = bufferedImage.getMinX(); i < 8; i++) {
            for (int j = bufferedImage.getMinY(); j < 8; j++) {
//                System.out.println(i + "," + j);
                bufferedImage.setRGB(i, j, colorArrayFromPallet.get(0).getRGB()); //top left
            }
        }


        //Color the top right corner
        for (int i = bufferedImage.getWidth() - 1; i > bufferedImage.getWidth() - 9; i--) {
            for (int j = bufferedImage.getMinY(); j < 8; j++) {
//                System.out.println(i + "," + j);
                bufferedImage.setRGB(i, j, colorArrayFromPallet.get(1).getRGB()); //top right

            }
        }

        //Color the bottom right corner
        for (int i = bufferedImage.getWidth() - 1; i > bufferedImage.getWidth() - 9; i--) {
            for (int j = bufferedImage.getHeight() - 1; j > bufferedImage.getHeight() - 9; j--) {
//                System.out.println(i + "," + j);
                bufferedImage.setRGB(i, j, colorArrayFromPallet.get(2).getRGB()); //bottom right

            }
        }

        //Color the bottom left corner
        for (int i = bufferedImage.getMinX(); i < 8; i++) {
            for (int j = bufferedImage.getHeight() - 1; j > bufferedImage.getHeight() - 9; j--) {
//                System.out.println(i + "," + j);
                bufferedImage.setRGB(i, j, colorArrayFromPallet.get(3).getRGB()); //bottom right

            }
        }

        File outfile = new File("./ocr/blueID/liad/saved2.png");
        ImageIO.write(bufferedImage, "png", outfile);

        File outputFileAfterCompression = new File("./ocr/blueID/liad/compressedSaved2.jpg");
        checkPixelsRGBAfterCompression(compressJPG(outfile, compressionQuality, outputFileAfterCompression));
        System.out.println("Return file: " + outputFileAfterCompression.getName());
        return outputFileAfterCompression;
    }

    public File compressJPG(File inputFile, float quality2, File outputFile) throws IOException {
        File compressedImageFile = outputFile;

        InputStream is = new FileInputStream(inputFile);
        OutputStream os = new FileOutputStream(compressedImageFile);

        float quality = quality2;
        BufferedImage image = ImageIO.read(is);
        Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpg");
        if (!writers.hasNext())
            throw new IllegalStateException("No writers found");

        ImageWriter writer = (ImageWriter) writers.next();
        ImageOutputStream ios = ImageIO.createImageOutputStream(os);
        writer.setOutput(ios);
        ImageWriteParam param = writer.getDefaultWriteParam();
        param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        param.setCompressionQuality(quality);
        writer.write(null, new IIOImage(image, null, null), param);
        is.close();
        os.close();
        ios.close();
        writer.dispose();
        System.out.println(compressedImageFile.getName());
        return compressedImageFile;
    }

    public void checkPixelsRGBAfterCompression(File file) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(file);
        List<Color> colorArrayAfterCompression = new ArrayList<>();
        colorArrayAfterCompression.add(Color.decode(String.valueOf(bufferedImage.getRGB(bufferedImage.getMinX(), bufferedImage.getMinY()))));
        colorArrayAfterCompression.add(Color.decode(String.valueOf(bufferedImage.getRGB(bufferedImage.getWidth() - 1, bufferedImage.getMinY()))));
        colorArrayAfterCompression.add(Color.decode(String.valueOf(bufferedImage.getRGB(bufferedImage.getWidth() - 1, bufferedImage.getHeight() - 1))));
        colorArrayAfterCompression.add(Color.decode(String.valueOf(bufferedImage.getRGB(bufferedImage.getMinX(), bufferedImage.getHeight() - 1))));

        System.out.println(colorArrayAfterCompression.get(0));
        System.out.println(colorArrayAfterCompression.get(1));
        System.out.println(colorArrayAfterCompression.get(2));
        System.out.println(colorArrayAfterCompression.get(3));
    }

    public void mainClientResponse(Variables variables, String contentType, String headerKey, String headerValue, String frame, File file, String imageType, String messageKey, String messageValue, String url) throws IOException {
        if (headerKey.equalsIgnoreCase("AUTHORIZATION")) {
            headerKey = "scanovate-auth";
        }
        if (variables.getCounter() == 0) {
            variables.setCounter(variables.getCounter() + 1);
        }
        System.out.println("File sent to api: " + file.getName());

        if (System.getProperty("OCR_NEW").equalsIgnoreCase("false")) {
            clientRequest = clientRequestApi(
                    contentType,
                    headerKey, headerValue,
                    frame, file, imageType,
                    messageKey, messageValue,
                    url);
            while (clientRequest.getBody().asString().equalsIgnoreCase("{\"success\":true}")) {
                clientRequest = clientRequestApi(contentType, headerKey, headerValue, frame, file, imageType, messageKey, messageValue, url);
                System.out.println("Request #: " + variables.getCounter());
                variables.setCounter(variables.getCounter() + 1);
            }
            jsonPath = clientRequest.jsonPath();
            String actionType = jsonPath.getString("action_type");
            variables.setActionType(actionType);
            System.out.println("response action type:" + actionType);

            if (actionType != null && actionType.equalsIgnoreCase("message")) {
                System.out.println("response message:" + jsonPath.getString("message.message"));
            }
            if (actionType != null && actionType.equalsIgnoreCase("stage")) {
                System.out.println("response stage:" + jsonPath.get("stage"));
            }

            if (!jsonPath.getBoolean("success")) { //invalid token situations or errors while scanning.
                variables.setSuccess(jsonPath.getBoolean("success"));
                variables.setErrorCode(jsonPath.getInt("errorCode"));
                variables.setErrorMessage(jsonPath.getString("errorMessage"));
                return;
            }
        } else {
//            clientRequestNew = clientRequestApiNew(
//                    contentType,
//                    frame, file, imageType,
//                    messageKey, messageValue,
//                    url);
//            while (clientRequest.getBody().asString().equalsIgnoreCase("{\"success\":true}")) {
//                clientRequestNew = clientRequestApiNew(contentType, frame, file, imageType, messageKey, messageValue, url);
//                System.out.println("Request #: " + variables.getCounter());
//                variables.setCounter(variables.getCounter() + 1);
//            }
            jsonPath = clientRequestNew.jsonPath();
            String actionType = jsonPath.getString("action_type");
            variables.setActionType(actionType);
            System.out.println("response action type:" + actionType);
            String status = jsonPath.getString("status");

            if (actionType != null && actionType.equalsIgnoreCase("message")) {
                System.out.println("response message:" + jsonPath.getString("message.message"));
            }
            if (actionType != null && actionType.equalsIgnoreCase("stage")) {
                System.out.println("response stage:" + jsonPath.get("stage"));
            }

            if (status != null && !status.equalsIgnoreCase("success")) { //invalid token situations or errors while scanning.
                variables.setSuccess(jsonPath.getBoolean("success"));
                variables.setErrorCode(jsonPath.getInt("errorCode"));
                variables.setErrorMessage(jsonPath.getString("errorMessage"));
                return;
            }

        }

        if (System.getProperty("OCR_NEW").equalsIgnoreCase("false")) {
            switch (variables.getActionType()) {
                case "message": {
                    setActionTypeMessageVariables(variables, jsonPath);
                    break;
                }
                case "stage": {
                    setActionTypeStageVariables(variables, jsonPath);
                    break;
                }
                case "complete": {

                    setActionTypeCompleteVariables(variables, jsonPath);
                    break;
                }
            }
        } else {
            switch (variables.getActionType()) {
                case "message": {
                    setActionTypeMessageVariablesNew(variables, jsonPath);
                    break;
                }
                case "stage": {
                    setActionTypeStageVariables(variables, jsonPath);
                    break;
                }
                case "complete": {
                    setActionTypeCompleteVariablesNew(variables, jsonPath);
                    break;
                }
            }
        }
    }

    @Step("Set action type 'message' variables")
    private void setActionTypeMessageVariables(Variables variables, JsonPath jsonPath) {
        int expectedFieldCount = jsonPath.getMap("").size();
        // Check if the expectedFieldCount is either 3 or 4
        boolean isValidFieldCount = expectedFieldCount == 3 || expectedFieldCount == 4;
        Assert.assertTrue(isValidFieldCount, "Unexpected field count");


        variables.setActionType(jsonPath.getString("action_type"));
        variables.setMessageId(jsonPath.getInt("message.id"));
        variables.setMessageText(jsonPath.getString("message.message"));
        variables.setSuccess(jsonPath.getBoolean("success"));
    }

    @Step("Set action type 'message' variables")
    private void setActionTypeMessageVariablesNew(Variables variables, JsonPath jsonPath) {
        int expectedFieldCount = jsonPath.getMap("").size();
        // Check if the expectedFieldCount is either 3 or 4
        boolean isValidFieldCount = expectedFieldCount == 8;
        Assert.assertTrue(isValidFieldCount, "Unexpected field count");

        variables.setActionType(jsonPath.getString("action_type"));
        variables.setMessageId(jsonPath.getInt("message.id"));
        variables.setMessageText(jsonPath.getString("message.message"));
//            variables.setSuccess(jsonPath.getBoolean("success"));
    }


    @Step("Set action type 'stage' variables")
    private void setActionTypeStageVariables(Variables variables, JsonPath jsonPath) {
        ocr_new = System.getProperty("OCR_NEW");
        OCR_NEW = Boolean.parseBoolean(ocr_new);
        variables.setActionType(jsonPath.getString("action_type"));
        if (OCR_NEW) {
            variables.setOrder(jsonPath.getInt("order"));
            variables.setTypeUnderStage(jsonPath.getString("type"));
            variables.setStatus(jsonPath.getString("status"));
        } else {
            variables.setOrder(jsonPath.getInt("stage.order"));
            variables.setTypeUnderStage(jsonPath.getString("stage.type"));
            variables.setStatus(jsonPath.getString("status"));
            variables.setSuccess(jsonPath.getBoolean("success"));
        }

        if (variables.getTypeUnderStage().equalsIgnoreCase("otp")) {
            variables.setOtpNum(jsonPath.getInt("stageData.otp_number"));
        }
        if (variables.getTypeUnderStage().equalsIgnoreCase("stt")) {
            variables.setSttText(jsonPath.getString("stageData.stt_text"));
        }
        switch (variables.getTypeUnderStage()) {
            case "otp": {
                variables.setOtpNum(jsonPath.getInt("stageData.otp_number"));
                break;
            }
            case "stt": {
                variables.setSttText(jsonPath.getString("stageData.stt_text"));
                break;
            }
            case "gestures": {
                variables.setGesturesArray(jsonPath.getList("stageData.gestures"));
                variables.setSequenceSecondsInterval(jsonPath.getInt("stageData.sequenceSecondsInterval"));
                break;
            }
        }
        switch (libraryName) {
            case "CNI": {
                switch (variables.getOrder()) {
                    case 5:
                    case 6: {
                        Assert.assertEquals(jsonPath.getMap("").size(), 5);
                        break;
                    }
                    default: {
                        Assert.assertEquals(jsonPath.getMap("").size(), 4);
                        break;
                    }
                }
                break;
            }
            case "LIVENESS": {
                switch (jsonPath.getString("stage.type")) {
                    case "left":
                    case "right": {
                        Assert.assertEquals(jsonPath.getMap("").size(), 4);
                        break;
                    }
                    case "otp":
                    case "stt":
                    case "gestures": {
                        Assert.assertEquals(jsonPath.getMap("").size(), 5);
                        break;
                    }
                }
                break;
            }
            default: {
                // Exit the loop (if there is one)
                if (OCR_NEW) { // Check if OCR_NEW is true
                    Assert.assertEquals(jsonPath.getMap("").size(), 8); // Assertion for JSON map size = 8
                } else {
                    Assert.assertEquals(jsonPath.getMap("").size(), 4); // Assertion for JSON map size = 4
                }
                break; // Exit the loop (if there is one)
            }
        }
    }

    @Step("Set action type 'complete' variables, each ocr type")
    private void setActionTypeCompleteVariables(Variables variables, JsonPath jsonPath) {
        if (variables.getCaseId() == null) {
            variables.setCaseId(jsonPath.getString("caseId"));
        }
        variables.setActionType(jsonPath.getString("action_type"));
        variables.setStatus(jsonPath.getString("status"));
        variables.setCaseId(jsonPath.getString("caseId"));
        variables.setConfigFileName(jsonPath.getString("configFilename"));
        variables.setSuccess(jsonPath.getBoolean("success"));

        ///"stages" = null situation happening when using docker test that disabling full response.
        if (jsonPath.getList("stages") == null) {
            return;
        }

        for (int i = 0; i < jsonPath.getList("stages").size(); i++) {
            System.out.println("index: " + i + " for stages-array, " + jsonPath.getString("stages[" + i + "].stage.type") + " , status: " + jsonPath.getString("stages[" + i + "].status"));
            if (jsonPath.getString("stages[" + i + "].status").equalsIgnoreCase("timeout")) {
                setNewTimeoutValues(variables, jsonPath, i);
                return;
            }
            if (libraryName.equalsIgnoreCase("MRZ") || libraryName.equalsIgnoreCase("DK-DL")
                    || libraryName.equalsIgnoreCase("PHL-CHEQUES") || libraryName.equalsIgnoreCase("LIVENESS")) {
                variables.setActionType1(jsonPath.getString("stages[" + i + "].action_type"));
                variables.setOrder(jsonPath.getInt("stages[" + i + "].stage.order"));
                variables.setTypeUnderStage(jsonPath.getString("stages[" + i + "].stage.type"));
            }
            switch (jsonPath.getString("stages[" + i + "].stage.type")) {
                case "passive": {
                    if ((jsonPath.getString("configFilename").equalsIgnoreCase("")) && (jsonPath.getString("status").equalsIgnoreCase("failure"))) {
                        setSingleFrameErrorVariables(variables, jsonPath, i);
                    } else setLivenessPassiveCompleteVariables(variables, jsonPath, i);
                    break;
                }
                case "otp": {
                    setOTPFields(variables, jsonPath, i);
                    break;
                }
                case "stt": {
                    setLivenessSTTStageVariables(variables, jsonPath, i);
                    break;
                }
                case "center":
                case "left":
                case "right": {
                    setLivenessStagesVariables(variables, jsonPath, i);
                    break;
                }
                case "gestures": {
                    setGesturesVariables(variables, jsonPath, i);
                    break;
                }
                case "front": {
                    switch (libraryName) {
                        case "IL-ID": {
                            switch (jsonPath.getString("stages[" + i + "].payload.card_type")) {
                                case "blue": {
                                    setValuesOfBlueId(variables, jsonPath);
                                    break;
                                }
                                case "green": {
                                    setValuesOfGreenId(variables, jsonPath);
                                    break;
                                }
                                case "bio_front": {
                                    setILIDFrontSideFields(variables, jsonPath, i);
                                    break;
                                }
                            }
                            break;
                        }
                        case "IL-DL": {
                            setILDLFrontSideFields(variables, jsonPath, i);
                            break;
                        }
//                        case "C-CARD": {
//                            setCreditCardFrontSideFields(variables, jsonPath, i);
//                            break;
//                        }
//                        case "IL-STP": {
//                            setStayPermitFrontSideFields(variables, jsonPath, i);
//                            break;
//                        }
                        case "CNI": {
                            setCNIFrontSideFields(variables, jsonPath, i);
                            break;
                        }
                        case "MRZ": {
                            setMRZCompleteStageVariables(variables, jsonPath, i);
                            break;
                        }
                        case "DK-DL": {
                            setDKDLCompleteStageVariables(variables, jsonPath, i);
                            break;
                        }
                        case "PHL-CHEQUES": {
                            setPHCCompleteStageVariables(variables, jsonPath, i);
                            break;
                        }
                        case "CAPTURE": {
                            setCaptureFrontSideVariables(variables, jsonPath, i);
                            break;
                        }

                    }
                    break;
                }
                case "back": {
                    switch (libraryName) {
                        case "CAPTURE": {
                            setCaptureBackSideVariables(variables, jsonPath, i);
                            break;
                        }
                        case "IL-ID": {
                            setILIDBackSideFields(variables, jsonPath, i);
                            break;
                        }
                        case "IL-DL": {
                            setILDLBackSideFields(variables, jsonPath, i);
                            break;
                        }
                        case "CNI": {
                            setCNIBackSideFields(variables, jsonPath, i);
                            break;
                        }
                    }
                    break;
                }
                case "any": { //single frame
                    if (libraryName.equalsIgnoreCase("MRZ") && jsonPath.getString("status").equalsIgnoreCase("success")) {
                        setMRZCompleteStageVariables(variables, jsonPath, i);
                    } else {
                        if (jsonPath.getString("status").equalsIgnoreCase("failure")) {
                            setSingleFrameErrorVariables(variables, jsonPath, i);
                            return;
                        }
                        switch (jsonPath.getString("stages[" + i + "].payload.card_type")) {
                            case "new_front":
                            case "old_front": {
                                setILDLFrontSideFields(variables, jsonPath, i);
                                break;
                            }
                            case "new_back":
                            case "old_back": {
                                setILDLBackSideFields(variables, jsonPath, i);
                                break;
                            }
                            case "bio_front": {
                                setILIDFrontSideFields(variables, jsonPath, i);
                                break;
                            }
                            case "bio_back": {
                                setILIDBackSideFields(variables, jsonPath, i);
                                break;
                            }
                            case "green": {
                                setValuesOfGreenId(variables, jsonPath);
                                break;
                            }
                            case "blue": {
                                setValuesOfBlueId(variables, jsonPath);
                                break;
                            }
                        }
                    }
                }
            }
        }
    }


    @Step("Set action type 'complete' variables, each ocr type")
    private void setActionTypeCompleteVariablesNew(Variables variables, JsonPath jsonPath) {
        ocr_new = System.getProperty("OCR_NEW");
        OCR_NEW = Boolean.parseBoolean(ocr_new);
        if (variables.getCaseId() == null) {
            variables.setCaseId(jsonPath.getString("case_Id"));
        }
        variables.setActionType(jsonPath.getString("action_type"));
        variables.setStatus(jsonPath.getString("status"));
        variables.setCaseId(jsonPath.getString("case_id"));

        ///"stages" = null situation happening when using docker test that disabling full response.
        if (jsonPath.getList("stages") == null) {
            return;
        }

        for (int i = 0; i < jsonPath.getList("stages").size(); i++) {
            System.out.println("index: " + i + " for stages-array, " + jsonPath.getString("stages[" + i + "].type") + " , status: " + jsonPath.getString("stages[" + i + "].status"));
            if (jsonPath.getString("stages[" + i + "].status").equalsIgnoreCase("timeout")) {
                setNewTimeoutValues(variables, jsonPath, i);
                return;
            }
            if (libraryName.equalsIgnoreCase("MRZ") || libraryName.equalsIgnoreCase("DK-DL")
                    || libraryName.equalsIgnoreCase("PHL-CHEQUES") || libraryName.equalsIgnoreCase("LIVENESS")) {
                variables.setActionType1(jsonPath.getString("stages[" + i + "].action_type"));
                variables.setOrder(jsonPath.getInt("stages[" + i + "].order"));
                variables.setTypeUnderStage(jsonPath.getString("stages[" + i + "].type"));
            }
            switch (jsonPath.getString("stages[" + i + "].type")) {
                case "passive": {
                    if ((jsonPath.getString("status").equalsIgnoreCase("failure"))) {
                        setSingleFrameErrorVariables(variables, jsonPath, i);
                    } else
                        setLivenessPassiveCompleteVariablesNew(variables, jsonPath, i);
                    break;
                }
                case "otp": {
                    setOTPFields(variables, jsonPath, i);
                    break;
                }
                case "stt": {
                    setLivenessSTTStageVariables(variables, jsonPath, i);
                    break;
                }
                case "center":
                case "left":
                case "right": {
                    setLivenessStagesVariables(variables, jsonPath, i);
                    break;
                }
                case "gestures": {
                    setGesturesVariables(variables, jsonPath, i);
                    break;
                }
                case "front": {
                    switch (libraryName) {
                        case "IL-ID": {
                            switch (jsonPath.getString("stages[" + i + "].payload.card_type")) {
                                case "blue": {
                                    setValuesOfBlueId(variables, jsonPath);
                                    break;
                                }
                                case "green":
                                case "green_old": {
                                    setValuesOfGreenId(variables, jsonPath);
                                    break;
                                }
                                case "bio_front":
                                case "bio_type_2_front": {
                                    setILIDFrontSideFields(variables, jsonPath, i);
                                    break;
                                }

                            }
                            break;
                        }
                        case "IL-DL": {
                            setILDLFrontSideFields(variables, jsonPath, i);
                            break;
                        }
                        case "C-CARD": {
                            setCreditCardFrontSideFields(variables, jsonPath, i);
                            break;
                        }
                        case "IL-STP": {
                            setStayPermitFrontSideFields(variables, jsonPath, i);
                            break;
                        }
                        case "CNI": {
                            setCNIFrontSideFields(variables, jsonPath, i);
                            break;
                        }
                        case "MRZ": {
                            setMRZCompleteStageVariables(variables, jsonPath, i);
                            break;
                        }
                        case "DK-DL": {
                            setDKDLCompleteStageVariables(variables, jsonPath, i);
                            break;
                        }
                        case "PHL-CHEQUES": {
                            setPHCCompleteStageVariables(variables, jsonPath, i);
                            break;
                        }
                        case "CAPTURE": {
                            setCaptureFrontSideVariables(variables, jsonPath, i);
                            break;
                        }

                    }
                    break;
                }
                case "back": {
                    switch (libraryName) {
                        case "CAPTURE": {
                            setCaptureBackSideVariables(variables, jsonPath, i);
                            break;
                        }
                        case "IL-ID": {
                            setILIDBackSideFields(variables, jsonPath, i);
                            break;
                        }
                        case "IL-DL": {
                            setILDLBackSideFields(variables, jsonPath, i);
                            break;
                        }
                        case "CNI": {
                            setCNIBackSideFields(variables, jsonPath, i);
                            break;
                        }
                    }
                    break;
                }
                case "any": { //single frame
                    if (libraryName.equalsIgnoreCase("MRZ") && jsonPath.getString("status").equalsIgnoreCase("success")) {
                        setMRZCompleteStageVariables(variables, jsonPath, i);
                    } else {
                        if (jsonPath.getString("status").equalsIgnoreCase("failure")) {
                            setSingleFrameErrorVariables(variables, jsonPath, i);
                            return;
                        }
                        switch (jsonPath.getString("stages[" + i + "].payload.card_type")) {
                            case "new_front":
                            case "old_front": {
                                setILDLFrontSideFields(variables, jsonPath, i);
                                break;
                            }
                            case "new_back":
                            case "old_back": {
                                setILDLBackSideFields(variables, jsonPath, i);
                                break;
                            }
                            case "bio_front": {
                                setILIDFrontSideFields(variables, jsonPath, i);
                                break;
                            }
                            case "bio_back": {
                                setILIDBackSideFields(variables, jsonPath, i);
                                break;
                            }
                            case "green": {
                                setValuesOfGreenId(variables, jsonPath);
                                break;
                            }
                            case "blue": {
                                setValuesOfBlueId(variables, jsonPath);
                                break;
                            }
                        }
                    }
                }
            }
        }
    }


    @Step("Set Stay Permit front side variables")
    private void setStayPermitFrontSideFields(Variables variables, JsonPath jsonPath, int index) {
        try {
            if (!OCR_NEW) {
                Assert.assertEquals(jsonPath.getMap("").size(), 6);
                Assert.assertEquals(jsonPath.getMap("stages[" + index + "].payload").size(), 5);
                if (jsonPath.getMap("stages[" + index + "].payload.fields").size() < 6) {
//                Assert.assertEquals(jsonPath.getMap("stages[" + index + "].payload.fields").size(), 5);
//                Assert.assertEquals(jsonPath.getMap("stages[" + index + "].payload.auth").size(), 8);
                } else {
//                Assert.assertEquals(jsonPath.getMap("stages[" + index + "].payload.fields").size(), 6);
//                Assert.assertEquals(jsonPath.getMap("stages[" + index + "].payload.images").size(), 3);
//                Assert.assertEquals(jsonPath.getMap("stages[" + index + "].payload.auth").size(), 9);
                }

                variables.setActionType2(jsonPath.getString("stages[" + index + "].action_type"));

                variables.setOrder2(jsonPath.getInt("stages[" + index + "].order"));
                variables.setType2(jsonPath.getString("stages[" + index + "].type"));

                variables.setPermitType(jsonPath.getString("stages[" + index + "].payload.fields.permit_type"));
                variables.setDateOfExpiry(jsonPath.getString("stages[" + index + "].payload.fields.expiration_date"));
                variables.setPassportNumber1(jsonPath.getString("stages[" + index + "].payload.fields.passport_number"));

                variables.setCardType(jsonPath.getString("stages[" + index + "].payload.card_type"));
                variables.setInputImage(jsonPath.getString("stages[" + index + "].payload.images.input_image"));
                variables.setCroppedImage(jsonPath.getString("stages[" + index + "].payload.images.cropped_image"));
                variables.setStageStatus3(jsonPath.getString("stages[" + index + "].status"));
            } else {
                Assert.assertEquals(jsonPath.getMap("").size(), 8);
                Assert.assertEquals(jsonPath.getMap("stages[" + index + "].payload").size(), 6);
                if (jsonPath.getMap("stages[" + index + "].payload.fields").size() < 6) {
//                Assert.assertEquals(jsonPath.getMap("stages[" + index + "].payload.fields").size(), 5);
//                Assert.assertEquals(jsonPath.getMap("stages[" + index + "].payload.auth").size(), 8);
                } else {
//                Assert.assertEquals(jsonPath.getMap("stages[" + index + "].payload.fields").size(), 6);
//                Assert.assertEquals(jsonPath.getMap("stages[" + index + "].payload.images").size(), 3);
//                Assert.assertEquals(jsonPath.getMap("stages[" + index + "].payload.auth").size(), 9);
                }

                variables.setActionType2(jsonPath.getString("stages[" + index + "].action_type"));

                variables.setOrder2(jsonPath.getInt("stages[" + index + "].order"));
                variables.setType2(jsonPath.getString("stages[" + index + "].type"));

                variables.setPermitType(jsonPath.getString("stages[" + index + "].payload.fields.permit_type"));
                variables.setDateOfExpiry(jsonPath.getString("stages[" + index + "].payload.fields.expiration_date"));
                variables.setPassportNumber1(jsonPath.getString("stages[" + index + "].payload.fields.passport_number"));

                variables.setCardType(jsonPath.getString("stages[" + index + "].payload.card_type"));
                variables.setInputImage(jsonPath.getString("stages[" + index + "].payload.images.input_image"));
                variables.setCroppedImage(jsonPath.getString("stages[" + index + "].payload.images.cropped_image"));
                variables.setStageStatus3(jsonPath.getString("stages[" + index + "].status"));


            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    @Step("Set Credit Card front side variables")
    private void setCreditCardFrontSideFields(Variables variables, JsonPath jsonPath, int index) {
        try {
            if (!OCR_NEW) {
                Assert.assertEquals(jsonPath.getMap("").size(), 6);
                Assert.assertEquals(jsonPath.getMap("stages[" + index + "].payload").size(), 5);

                variables.setActionType2(jsonPath.getString("stages[" + index + "].action_type"));
                variables.setStatus1(jsonPath.getString("stages[" + index + "].status"));


                variables.setOrder2(jsonPath.getInt("stages[" + index + "].stage.order"));
                variables.setType2(jsonPath.getString("stages[" + index + "].stage.type"));
                variables.setCardType1(jsonPath.getString("stages[" + index + "].payload.card_type"));
                variables.setCreditCardNumber(jsonPath.getString("stages[" + index + "].payload.fields.number_line"));
                variables.setDateOfExpiry(jsonPath.getString("stages[" + index + "].payload.fields.expiration_date"));


                variables.setInputImage(jsonPath.getString("stages[" + index + "].payload.images.input_image"));
                variables.setCroppedImage(jsonPath.getString("stages[" + index + "].payload.images.cropped_image"));

            } else {

                Assert.assertEquals(jsonPath.getMap("").size(), 8);
                Assert.assertEquals(jsonPath.getMap("stages[" + index + "].payload").size(), 6);


                variables.setActionType2(jsonPath.getString("stages[" + index + "].action_type"));
                variables.setStatus1(jsonPath.getString("stages[" + index + "].status"));


                variables.setOrder2(jsonPath.getInt("stages[" + index + "].order"));
                variables.setType2(jsonPath.getString("stages[" + index + "].type"));

                variables.setCardType1(jsonPath.getString("stages[" + index + "].payload.card_type"));
                variables.setCreditCardNumber(jsonPath.getString("stages[" + index + "].payload.fields.number_line"));
                variables.setDateOfExpiry(jsonPath.getString("stages[" + index + "].payload.fields.expiration_date"));


                variables.setInputImage(jsonPath.getString("stages[" + index + "].payload.images.input_image"));
                variables.setCroppedImage(jsonPath.getString("stages[" + index + "].payload.images.cropped_image"));

            }
            variables.setStageStatus3(jsonPath.getString("stages[" + index + "].status"));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Step("Set IL-DL back side variables")
    private void setILDLBackSideFields(Variables variables, JsonPath jsonPath, int index) {
        if (!OCR_NEW) {
            Assert.assertEquals(jsonPath.getMap("stages[" + index + "]").size(), 4);
            Assert.assertEquals(jsonPath.getMap("stages[" + index + "].stage").size(), 2);
            Assert.assertEquals(jsonPath.getMap("stages[" + index + "].payload").size(), 4);
            Assert.assertEquals(jsonPath.getMap("stages[" + index + "].payload.fields").size(), 2);
            Assert.assertEquals(jsonPath.getMap("stages[" + index + "].payload.images").size(), 2);

            int actualSize = jsonPath.getMap("stages[" + index + "].payload.auth").size();
            int expectedSize1 = 3;
            int expectedSize2 = 4;

            if (actualSize != expectedSize1 && actualSize != expectedSize2) {
                Assert.fail("Unexpected result size");
            }

            //         Assert.assertEquals(jsonPath.getMap("stages[" + index + "].payload.auth").size(), 4);

            try {
                variables.setActionType1(jsonPath.getString("stages[" + index + "].action_type"));
                variables.setOrder1(jsonPath.getInt("stages[" + index + "].stage.order"));
                variables.setType1(jsonPath.getString("stages[" + index + "].stage.type"));

                variables.setIdNumber(jsonPath.getInt("stages[" + index + "].payload.fields.id_number"));
                variables.setbYear(jsonPath.getInt("stages[" + index + "].payload.fields.b_year"));
                variables.setCardType1(jsonPath.getString("stages[" + index + "].payload.card_type"));

                variables.setInputImage1(jsonPath.getString("stages[" + index + "].payload.images.input_image"));
                variables.setCroppedImage1(jsonPath.getString("stages[" + index + "].payload.images.cropped_image"));

                variables.setIdChecksumValid2(jsonPath.getBoolean("stages[" + index + "].payload.auth.id_checksum_valid"));
                variables.setTemplateMatched2(jsonPath.getBoolean("stages[" + index + "].payload.auth.template_matched"));
                variables.setDocumentInFrame2(jsonPath.getBoolean("stages[" + index + "].payload.auth.document_in_frame"));

                variables.setStageStatus2(jsonPath.getString("stages[" + index + "].status"));
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else {

            Assert.assertEquals(jsonPath.getMap("stages[" + index + "]").size(), 5);
//            Assert.assertEquals(jsonPath.getMap("stages[" + index + "]").size(), 2);
            Assert.assertEquals(jsonPath.getMap("stages[" + index + "].payload").size(), 6);
            Assert.assertEquals(jsonPath.getMap("stages[" + index + "].payload.fields").size(), 2);
            Assert.assertEquals(jsonPath.getMap("stages[" + index + "].payload.images").size(), 3);

            Assert.assertEquals(jsonPath.getMap("stages[" + index + "].payload.auth").size(), 4);

            try {
                variables.setActionType1(jsonPath.getString("stages[" + index + "].action_type"));
                variables.setOrder1(jsonPath.getInt("stages[" + index + "].order"));
                variables.setType1(jsonPath.getString("stages[" + index + "].type"));

                variables.setIdNumber(jsonPath.getInt("stages[" + index + "].payload.fields.id_number"));
                variables.setbYear(jsonPath.getInt("stages[" + index + "].payload.fields.b_year"));
                variables.setCardType1(jsonPath.getString("stages[" + index + "].payload.card_type"));

                variables.setInputImage1(jsonPath.getString("stages[" + index + "].payload.images.input_image"));
                variables.setCroppedImage1(jsonPath.getString("stages[" + index + "].payload.images.cropped_image"));

                variables.setIdChecksumValid2(jsonPath.getBoolean("stages[" + index + "].payload.auth.id_checksum_valid"));
                variables.setTemplateMatched2(jsonPath.getBoolean("stages[" + index + "].payload.auth.template_matched"));
                variables.setDocumentInFrame2(jsonPath.getBoolean("stages[" + index + "].payload.auth.document_in_frame"));

                variables.setStageStatus2(jsonPath.getString("stages[" + index + "].status"));
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }


        }
    }

    @Step("Set IL-DL front side variables")
    private void setILDLFrontSideFields(Variables variables, JsonPath jsonPath, int index) {
        try {
            if (!OCR_NEW) {
                variables.setActionType2(jsonPath.getString("stages[" + index + "].action_type"));

                variables.setOrder2(jsonPath.getInt("stages[" + index + "].stage.order"));
                variables.setType2(jsonPath.getString("stages[" + index + "].stage.type"));

                variables.setLastNameHebrew(jsonPath.getString("stages[" + index + "].payload.fields.last_name_hebrew"));
                variables.setLastNameEnglish(jsonPath.getString("stages[" + index + "].payload.fields.last_name_english"));
                if (jsonPath.getMap("stages[" + index + "].payload.fields").containsKey("first_name_hebrew")) {
                    variables.setFirstNameHebrew(jsonPath.getString("stages[" + index + "].payload.fields.first_name_hebrew"));
                }
                if (jsonPath.getMap("stages[" + index + "].payload.fields").containsKey("first_name_english")) {
                    variables.setFirstNameEnglish(jsonPath.getString("stages[" + index + "].payload.fields.first_name_english"));
                }
                variables.setDateOfBirth(jsonPath.getString("stages[" + index + "].payload.fields.date_of_birth"));
                if (jsonPath.getMap("stages[" + index + "].payload.fields").containsKey("date_of_issue")) {
                    variables.setDateOfIssue(jsonPath.getString("stages[" + index + "].payload.fields.date_of_issue"));
                }
                if (jsonPath.getMap("stages[" + index + "].payload.fields").containsKey("date_of_expiry")) {
                    variables.setDateOfExpiry(jsonPath.getString("stages[" + index + "].payload.fields.date_of_expiry"));
                }
                variables.setIdNumber(jsonPath.getInt("stages[" + index + "].payload.fields.id_number"));
                if (jsonPath.getMap("stages[" + index + "].payload.fields").containsKey("license_number")) {
                    variables.setLicenseNumber(jsonPath.getInt("stages[" + index + "].payload.fields.license_number"));
                }

                variables.setAddress(jsonPath.getString("stages[" + index + "].payload.fields.address"));
                if (jsonPath.getMap("stages[" + index + "].payload.fields").containsKey("license_categories")) {
                    variables.setLicenseCategories(jsonPath.getString("stages[" + index + "].payload.fields.license_categories"));
                }
                variables.setCardType2(jsonPath.getString("stages[" + index + "].payload.card_type"));

                variables.setFaceImage2(jsonPath.getString("stages[" + index + "].payload.images.face_image"));
                variables.setInputImage2(jsonPath.getString("stages[" + index + "].payload.images.input_image"));
                variables.setCroppedImage2(jsonPath.getString("stages[" + index + "].payload.images.cropped_image"));

                variables.setIdChecksumValid(jsonPath.getBoolean("stages[" + index + "].payload.auth.id_checksum_valid"));
                variables.setTemplateMatched(jsonPath.getBoolean("stages[" + index + "].payload.auth.template_matched"));
                variables.setDocumentInFrame(jsonPath.getBoolean("stages[" + index + "].payload.auth.document_in_frame"));
                variables.setExpiryDateValid(jsonPath.getBoolean("stages[" + index + "].payload.auth.expiry_date_valid"));
                variables.setFaceSizeValid(jsonPath.getBoolean("stages[" + index + "].payload.auth.face_size_valid"));
                variables.setFacePositionValid(jsonPath.getBoolean("stages[" + index + "].payload.auth.face_position_valid"));
                variables.setFaceRotationValid(jsonPath.getBoolean("stages[" + index + "].payload.auth.face_rotation_valid"));

                variables.setStageStatus3(jsonPath.getString("stages[" + index + "].status"));

            } else {
                variables.setActionType2(jsonPath.getString("stages[" + index + "].action_type"));

                variables.setOrder2(jsonPath.getInt("stages[" + index + "].order"));
                variables.setType2(jsonPath.getString("stages[" + index + "].type"));

                variables.setLastNameHebrew(jsonPath.getString("stages[" + index + "].payload.fields.last_name_hebrew"));
                variables.setLastNameEnglish(jsonPath.getString("stages[" + index + "].payload.fields.last_name_english"));
                if (jsonPath.getMap("stages[" + index + "].payload.fields").containsKey("first_name_hebrew")) {
                    variables.setFirstNameHebrew(jsonPath.getString("stages[" + index + "].payload.fields.first_name_hebrew"));
                }
                if (jsonPath.getMap("stages[" + index + "].payload.fields").containsKey("first_name_english")) {
                    variables.setFirstNameEnglish(jsonPath.getString("stages[" + index + "].payload.fields.first_name_english"));
                }
                variables.setDateOfBirth(jsonPath.getString("stages[" + index + "].payload.fields.date_of_birth"));
                if (jsonPath.getMap("stages[" + index + "].payload.fields").containsKey("date_of_issue")) {
                    variables.setDateOfIssue(jsonPath.getString("stages[" + index + "].payload.fields.date_of_issue"));
                }
                if (jsonPath.getMap("stages[" + index + "].payload.fields").containsKey("date_of_expiry")) {
                    variables.setDateOfExpiry(jsonPath.getString("stages[" + index + "].payload.fields.date_of_expiry"));
                }
                variables.setIdNumber(jsonPath.getInt("stages[" + index + "].payload.fields.id_number"));
                if (jsonPath.getMap("stages[" + index + "].payload.fields").containsKey("license_number")) {
                    variables.setLicenseNumber(jsonPath.getInt("stages[" + index + "].payload.fields.license_number"));
                }

                variables.setAddress(jsonPath.getString("stages[" + index + "].payload.fields.address"));
                if (jsonPath.getMap("stages[" + index + "].payload.fields").containsKey("license_categories")) {
                    variables.setLicenseCategories(jsonPath.getString("stages[" + index + "].payload.fields.license_categories"));
                }
                variables.setCardType2(jsonPath.getString("stages[" + index + "].payload.card_type"));

                variables.setFaceImage2(jsonPath.getString("stages[" + index + "].payload.images.face_image"));
                variables.setInputImage2(jsonPath.getString("stages[" + index + "].payload.images.input_image"));
                variables.setCroppedImage2(jsonPath.getString("stages[" + index + "].payload.images.cropped_image"));

                variables.setIdChecksumValid(jsonPath.getBoolean("stages[" + index + "].payload.auth.id_checksum_valid"));
                variables.setTemplateMatched(jsonPath.getBoolean("stages[" + index + "].payload.auth.template_matched"));
                variables.setDocumentInFrame(jsonPath.getBoolean("stages[" + index + "].payload.auth.document_in_frame"));
                variables.setExpiryDateValid(jsonPath.getBoolean("stages[" + index + "].payload.auth.expiry_date_valid"));
                variables.setFaceSizeValid(jsonPath.getBoolean("stages[" + index + "].payload.auth.face_size_valid"));
                variables.setFacePositionValid(jsonPath.getBoolean("stages[" + index + "].payload.auth.face_position_valid"));
                variables.setFaceRotationValid(jsonPath.getBoolean("stages[" + index + "].payload.auth.face_rotation_valid"));

                variables.setStageStatus3(jsonPath.getString("stages[" + index + "].status"));

            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Step("Set IL-ID back side variables")
    private void setILIDBackSideFields(Variables variables, JsonPath jsonPath, int index) {
        try {
            if (!OCR_NEW) {
                Assert.assertEquals(jsonPath.getMap("").size(), 6);
                Assert.assertEquals(jsonPath.getMap("stages[" + index + "].payload").size(), 5);
                Assert.assertEquals(jsonPath.getMap("stages[" + index + "].payload.fields").size(), 4);
                Assert.assertEquals(jsonPath.getMap("stages[" + index + "].payload.images").size(), 2);
                if (jsonPath.getString("stages[0].stage.type").equalsIgnoreCase("any")) {
                    Assert.assertEquals(jsonPath.getMap("stages[" + index + "].payload.auth").size(), 4);
                } else {
                    Assert.assertEquals(jsonPath.getMap("stages[" + index + "].payload.auth").size(), 5);
                    variables.setIdNumberMatchesFront(jsonPath.getBoolean("stages[" + index + "].payload.auth.id_number_matches_front"));
                }
                variables.setActionType1(jsonPath.getString("stages[" + index + "].action_type"));

                variables.setOrder1(jsonPath.getInt("stages[" + index + "].stage.order"));
                variables.setType1(jsonPath.getString("stages[" + index + "].stage.type"));

                variables.setIdNumber(jsonPath.getInt("stages[" + index + "].payload.fields.id_number"));
                variables.setPlaceOfBirth(jsonPath.getString("stages[" + index + "].payload.fields.place_of_birth"));
                variables.setGender(jsonPath.getString("stages[" + index + "].payload.fields.gender"));
                variables.setCardType1(jsonPath.getString("stages[" + index + "].payload.card_type"));

                variables.setInputImage1(jsonPath.getString("stages[" + index + "].payload.images.input_image"));
                variables.setCroppedImage1(jsonPath.getString("stages[" + index + "].payload.images.cropped_image"));

                variables.setImageIsColorful(jsonPath.getBoolean("stages[" + index + "].payload.auth.image_is_colorful"));
                variables.setIdChecksumValid(jsonPath.getBoolean("stages[" + index + "].payload.auth.id_checksum_valid"));
                variables.setTemplateMatched(jsonPath.getBoolean("stages[" + index + "].payload.auth.template_matched"));
                variables.setDocumentInFrame(jsonPath.getBoolean("stages[" + index + "].payload.auth.document_in_frame"));

                variables.setStageStatus2(jsonPath.getString("stages[" + index + "].status"));
            } else {
                Assert.assertEquals(jsonPath.getMap("").size(), 8);
                Assert.assertEquals(jsonPath.getMap("stages[" + index + "].payload").size(), 6);
                Assert.assertEquals(jsonPath.getMap("stages[" + index + "].payload.fields").size(), 4);
                Assert.assertEquals(jsonPath.getMap("stages[" + index + "].payload.images").size(), 3);
                if (jsonPath.getString("stages[0].type").equalsIgnoreCase("any")) {
                    Assert.assertEquals(jsonPath.getMap("stages[" + index + "].payload.auth").size(), 4);
                } else {
                    Assert.assertEquals(jsonPath.getMap("stages[" + index + "].payload.auth").size(), 5);
                    variables.setIdNumberMatchesFront(jsonPath.getBoolean("stages[" + index + "].payload.auth.id_number_matches_front"));
                }
                variables.setActionType1(jsonPath.getString("stages[" + index + "].action_type"));

                variables.setOrder1(jsonPath.getInt("stages[" + index + "].order"));
                variables.setType1(jsonPath.getString("stages[" + index + "].type"));

                variables.setIdNumber(jsonPath.getInt("stages[" + index + "].payload.fields.id_number"));
                variables.setPlaceOfBirth(jsonPath.getString("stages[" + index + "].payload.fields.place_of_birth"));
                variables.setGender(jsonPath.getString("stages[" + index + "].payload.fields.gender"));
                variables.setCardType1(jsonPath.getString("stages[" + index + "].payload.card_type"));

                variables.setInputImage1(jsonPath.getString("stages[" + index + "].payload.images.input_image"));
                variables.setCroppedImage1(jsonPath.getString("stages[" + index + "].payload.images.cropped_image"));

                variables.setImageIsColorful(jsonPath.getBoolean("stages[" + index + "].payload.auth.image_is_colorful"));
                variables.setIdChecksumValid(jsonPath.getBoolean("stages[" + index + "].payload.auth.id_checksum_valid"));
                variables.setTemplateMatched(jsonPath.getBoolean("stages[" + index + "].payload.auth.template_matched"));
                variables.setDocumentInFrame(jsonPath.getBoolean("stages[" + index + "].payload.auth.document_in_frame"));

                variables.setStageStatus2(jsonPath.getString("stages[" + index + "].status"));

            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Step("Set IL-ID front side variables")
    private void setILIDFrontSideFields(Variables variables, JsonPath jsonPath, int index) {
        try {
            if (!OCR_NEW) {
                Assert.assertEquals(jsonPath.getMap("").size(), 6);
                Assert.assertEquals(jsonPath.getMap("stages[" + index + "].payload").size(), 5);
                if (jsonPath.getMap("stages[" + index + "].payload.fields").size() < 6) {
//                Assert.assertEquals(jsonPath.getMap("stages[" + index + "].payload.fields").size(), 5);
//                Assert.assertEquals(jsonPath.getMap("stages[" + index + "].payload.auth").size(), 8);
                } else {
//                Assert.assertEquals(jsonPath.getMap("stages[" + index + "].payload.fields").size(), 6);
//                Assert.assertEquals(jsonPath.getMap("stages[" + index + "].payload.images").size(), 3);
//                Assert.assertEquals(jsonPath.getMap("stages[" + index + "].payload.auth").size(), 9);
                }

                variables.setActionType2(jsonPath.getString("stages[" + index + "].action_type"));

                variables.setOrder2(jsonPath.getInt("stages[" + index + "].stage.order"));
                variables.setType2(jsonPath.getString("stages[" + index + "].stage.type"));

                variables.setLastNameHebrew(jsonPath.getString("stages[" + index + "].payload.fields.last_name_hebrew"));
                variables.setFirstNameHebrew(jsonPath.getString("stages[" + index + "].payload.fields.first_name_hebrew"));
                variables.setDateOfBirth(jsonPath.getString("stages[" + index + "].payload.fields.date_of_birth"));
                variables.setDateOfIssue(jsonPath.getString("stages[" + index + "].payload.fields.date_of_issue"));
                variables.setDateOfExpiry(jsonPath.getString("stages[" + index + "].payload.fields.date_of_expiry"));
                variables.setIdNumber(jsonPath.getInt("stages[" + index + "].payload.fields.id_number"));
                variables.setCardType2(jsonPath.getString("stages[" + index + "].payload.card_type"));

                variables.setFaceImage2(jsonPath.getString("stages[" + index + "].payload.images.face_image"));
                variables.setInputImage2(jsonPath.getString("stages[" + index + "].payload.images.input_image"));
                variables.setCroppedImage2(jsonPath.getString("stages[" + index + "].payload.images.cropped_image"));

                if (jsonPath.getMap("stages[" + index + "].payload.auth").containsKey("period_between_issue_and_expiry_valid")) {
                    variables.setPeriodBetweenIssueAndExpiry(jsonPath.getBoolean("stages[" + index + "].payload.auth.period_between_issue_and_expiry_valid"));
                }
                if (jsonPath.getMap("stages[" + index + "].payload.auth").containsKey("face_image_replaced_with_passport_image")) {
                    variables.setFaceImageReplacedWithPassportImage(jsonPath.getBoolean("stages[" + index + "].payload.auth.face_image_replaced_with_passport_image"));
                }
                if (jsonPath.getMap("stages[" + index + "].payload.auth").containsKey("image_is_colorful")) {
                    variables.setImageIsColorful(jsonPath.getBoolean("stages[" + index + "].payload.auth.image_is_colorful"));
                }
                if (jsonPath.getMap("stages[" + index + "].payload.auth").containsKey("chip_auth")) {
                    variables.setChipAuth(jsonPath.getBoolean("stages[" + index + "].payload.auth.chip_auth"));
                }
                if (jsonPath.getMap("stages[" + index + "].payload.auth").containsKey("face_size_valid")) {
                    variables.setFaceSizeValid(jsonPath.getBoolean("stages[" + index + "].payload.auth.face_size_valid"));
                }
                if (jsonPath.getMap("stages[" + index + "].payload.auth").containsKey("face_position_valid")) {
                    variables.setFacePositionValid(jsonPath.getBoolean("stages[" + index + "].payload.auth.face_position_valid"));
                }
                if (jsonPath.getMap("stages[" + index + "].payload.auth").containsKey("face_rotation_valid")) {
                    variables.setFaceRotationValid(jsonPath.getBoolean("stages[" + index + "].payload.auth.face_rotation_valid"));
                }
                if (jsonPath.getMap("stages[" + index + "].payload.auth").containsKey("id_checksum_valid")) {
                    variables.setIdChecksumValid(jsonPath.getBoolean("stages[" + index + "].payload.auth.id_checksum_valid"));
                }
                if (jsonPath.getMap("stages[" + index + "].payload.auth").containsKey("template_matched")) {
                    variables.setTemplateMatched(jsonPath.getBoolean("stages[" + index + "].payload.auth.template_matched"));
                }
                if (jsonPath.getMap("stages[" + index + "].payload.auth").containsKey("document_in_frame")) {
                    variables.setDocumentInFrame(jsonPath.getBoolean("stages[" + index + "].payload.auth.document_in_frame"));
                }
                if (jsonPath.getMap("stages[" + index + "].payload.auth").containsKey("expiry_date_valid")) {
                    variables.setExpiryDateValid(jsonPath.getBoolean("stages[" + index + "].payload.auth.expiry_date_valid"));
                }
                if (jsonPath.getMap("stages[" + index + "].payload.auth").containsKey("issue_date_valid")) {
                    variables.setIssueDateValid(jsonPath.getBoolean("stages[" + index + "].payload.auth.issue_date_valid"));
                }

                variables.setStageStatus3(jsonPath.getString("stages[" + index + "].status"));
            } else {
                Assert.assertEquals(jsonPath.getMap("").size(), 8);
                Assert.assertEquals(jsonPath.getMap("stages[" + index + "].payload").size(), 6);
                if (jsonPath.getMap("stages[" + index + "].payload.fields").size() < 6) {
//                Assert.assertEquals(jsonPath.getMap("stages[" + index + "].payload.fields").size(), 5);
//                Assert.assertEquals(jsonPath.getMap("stages[" + index + "].payload.auth").size(), 8);
                } else {
//                Assert.assertEquals(jsonPath.getMap("stages[" + index + "].payload.fields").size(), 6);
//                Assert.assertEquals(jsonPath.getMap("stages[" + index + "].payload.images").size(), 3);
//                Assert.assertEquals(jsonPath.getMap("stages[" + index + "].payload.auth").size(), 9);
                }

                variables.setActionType2(jsonPath.getString("stages[" + index + "].action_type"));

                variables.setOrder2(jsonPath.getInt("stages[" + index + "].order"));
                variables.setType2(jsonPath.getString("stages[" + index + "].type"));

                variables.setLastNameHebrew(jsonPath.getString("stages[" + index + "].payload.fields.last_name_hebrew"));
                variables.setFirstNameHebrew(jsonPath.getString("stages[" + index + "].payload.fields.first_name_hebrew"));
                variables.setDateOfBirth(jsonPath.getString("stages[" + index + "].payload.fields.date_of_birth"));
                variables.setDateOfIssue(jsonPath.getString("stages[" + index + "].payload.fields.date_of_issue"));
                variables.setDateOfExpiry(jsonPath.getString("stages[" + index + "].payload.fields.date_of_expiry"));
                variables.setIdNumber(jsonPath.getInt("stages[" + index + "].payload.fields.id_number"));
                variables.setCardType2(jsonPath.getString("stages[" + index + "].payload.card_type"));

                variables.setFaceImage2(jsonPath.getString("stages[" + index + "].payload.images.face_image"));
                variables.setInputImage2(jsonPath.getString("stages[" + index + "].payload.images.input_image"));
                variables.setCroppedImage2(jsonPath.getString("stages[" + index + "].payload.images.cropped_image"));

                if (jsonPath.getMap("stages[" + index + "].payload.auth").containsKey("period_between_issue_and_expiry_valid")) {
                    variables.setPeriodBetweenIssueAndExpiry(jsonPath.getBoolean("stages[" + index + "].payload.auth.period_between_issue_and_expiry_valid"));
                }
                if (jsonPath.getMap("stages[" + index + "].payload.auth").containsKey("face_image_replaced_with_passport_image")) {
                    variables.setFaceImageReplacedWithPassportImage(jsonPath.getBoolean("stages[" + index + "].payload.auth.face_image_replaced_with_passport_image"));
                }
                if (jsonPath.getMap("stages[" + index + "].payload.auth").containsKey("image_is_colorful")) {
                    variables.setImageIsColorful(jsonPath.getBoolean("stages[" + index + "].payload.auth.image_is_colorful"));
                }
                if (jsonPath.getMap("stages[" + index + "].payload.auth").containsKey("chip_auth")) {
                    variables.setChipAuth(jsonPath.getBoolean("stages[" + index + "].payload.auth.chip_auth"));
                }
                if (jsonPath.getMap("stages[" + index + "].payload.auth").containsKey("face_size_valid")) {
                    variables.setFaceSizeValid(jsonPath.getBoolean("stages[" + index + "].payload.auth.face_size_valid"));
                }
                if (jsonPath.getMap("stages[" + index + "].payload.auth").containsKey("face_position_valid")) {
                    variables.setFacePositionValid(jsonPath.getBoolean("stages[" + index + "].payload.auth.face_position_valid"));
                }
                if (jsonPath.getMap("stages[" + index + "].payload.auth").containsKey("face_rotation_valid")) {
                    variables.setFaceRotationValid(jsonPath.getBoolean("stages[" + index + "].payload.auth.face_rotation_valid"));
                }
                if (jsonPath.getMap("stages[" + index + "].payload.auth").containsKey("id_checksum_valid")) {
                    variables.setIdChecksumValid(jsonPath.getBoolean("stages[" + index + "].payload.auth.id_checksum_valid"));
                }
                if (jsonPath.getMap("stages[" + index + "].payload.auth").containsKey("template_matched")) {
                    variables.setTemplateMatched(jsonPath.getBoolean("stages[" + index + "].payload.auth.template_matched"));
                }
                if (jsonPath.getMap("stages[" + index + "].payload.auth").containsKey("document_in_frame")) {
                    variables.setDocumentInFrame(jsonPath.getBoolean("stages[" + index + "].payload.auth.document_in_frame"));
                }
                if (jsonPath.getMap("stages[" + index + "].payload.auth").containsKey("expiry_date_valid")) {
                    variables.setExpiryDateValid(jsonPath.getBoolean("stages[" + index + "].payload.auth.expiry_date_valid"));
                }
                if (jsonPath.getMap("stages[" + index + "].payload.auth").containsKey("issue_date_valid")) {
                    variables.setIssueDateValid(jsonPath.getBoolean("stages[" + index + "].payload.auth.issue_date_valid"));
                }

                variables.setStageStatus3(jsonPath.getString("stages[" + index + "].status"));
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Step("Set CNI front side variables")
    private void setCNIFrontSideFields(Variables variables, JsonPath jsonPath, int index) {
        Assert.assertEquals(jsonPath.getMap("").size(), 6);
        Assert.assertEquals(jsonPath.getMap("stages[" + index + "].payload").size(), 2);
        Assert.assertEquals(jsonPath.getMap("stages[" + index + "].payload.images").size(), 3);

        variables.setActionType1(jsonPath.getString("stages[" + index + "].action_type"));
        variables.setOrder1(jsonPath.getInt("stages[" + index + "].stage.order"));
        variables.setType1(jsonPath.getString("stages[" + index + "].stage.type"));

        variables.setCardType(jsonPath.getString("stages[" + index + "].payload.card_type"));

        variables.setFaceImage(jsonPath.getString("stages[" + index + "].payload.images.face_image"));
        variables.setInputImage(jsonPath.getString("stages[" + index + "].payload.images.input_image"));
        variables.setCroppedImage(jsonPath.getString("stages[" + index + "].payload.images.cropped_image"));

        variables.setStageStatus(jsonPath.getString("stages[" + index + "].status"));

        //second stage (video)
        if (jsonPath.getList("stages").size() >= index + 2) {
            variables.setActionType2(jsonPath.getString("stages[" + (index + 1) + "].action_type"));
            variables.setOrder2(jsonPath.getInt("stages[" + (index + 1) + "].stage.order"));
            variables.setType2(jsonPath.getString("stages[" + (index + 1) + "].stage.type"));
            variables.setStageStatus2(jsonPath.getString("stages[" + (index + 1) + "].status"));
        }
    }

    @Step("Set CNI back side variables")
    private void setCNIBackSideFields(Variables variables, JsonPath jsonPath, int index) {
        Assert.assertEquals(jsonPath.getMap("").size(), 6);
        Assert.assertEquals(jsonPath.getMap("stages[" + index + "].payload").size(), 2);
        Assert.assertEquals(jsonPath.getMap("stages[" + index + "].payload.images").size(), 2);

        variables.setActionType3(jsonPath.getString("stages[" + index + "].action_type"));
        variables.setOrder3(jsonPath.getInt("stages[" + index + "].stage.order"));
        variables.setType3(jsonPath.getString("stages[" + index + "].stage.type"));
        variables.setCardType2(jsonPath.getString("stages[" + index + "].payload.card_type"));

        variables.setInputImage2(jsonPath.getString("stages[" + index + "].payload.images.input_image"));
        variables.setCroppedImage2(jsonPath.getString("stages[" + index + "].payload.images.cropped_image"));
        variables.setStageStatus3(jsonPath.getString("stages[" + index + "].status"));

    }

    @Step("Set OTP stage variables")
    private void setOTPFields(Variables variables, JsonPath jsonPath, int index) {
        variables.setActionType5(jsonPath.getString("stages[" + index + "].action_type"));
        variables.setOrder5(jsonPath.getInt("stages[" + index + "].stage.order"));
        variables.setType5(jsonPath.getString("stages[" + index + "].stage.type"));

        variables.setOtpNum(jsonPath.getInt("stages[" + index + "].payload.otp_number"));
        System.out.println();
        if (String.valueOf(variables.getOtpNum()).length() < 4) {
            variables.setOtpNum(Integer.parseInt("0") + variables.getOtpNum());
        }
        variables.setStageStatus5(jsonPath.getString("stages[" + index + "].status"));
    }

    @Step("Set complete stage variables of PHC")
    private void setPHCCompleteStageVariables(Variables variables, JsonPath jsonPath, int index) {
        Assert.assertEquals(jsonPath.getMap("").size(), 6);
        Assert.assertEquals(jsonPath.getMap("stages[" + index + "].payload").size(), 2);
        Assert.assertEquals(jsonPath.getMap("stages[" + index + "].payload.fields").size(), 1);
        Assert.assertEquals(jsonPath.getMap("stages[" + index + "].payload.images").size(), 1);

        variables.setChequeNumber(jsonPath.getString("stages[" + index + "].payload.fields.cheque_number"));
        variables.setInputImage(jsonPath.getString("stages[" + index + "].payload.images.input_image"));
        variables.setStageStatus(jsonPath.getString("stages[" + index + "].status"));
    }

    @Step("Set complete stage variables of MRZ")
    private void setMRZCompleteStageVariables(Variables variables, JsonPath jsonPath, int index) {
        try {
            if (!OCR_NEW) {
                Assert.assertEquals(jsonPath.getMap("").size(), 6);
                Assert.assertEquals(jsonPath.getMap("stages[" + index + "].payload").size(), 5);
                Assert.assertEquals(jsonPath.getMap("stages[" + index + "].payload.fields").size(), 13);
                Assert.assertEquals(jsonPath.getMap("stages[" + index + "].payload.images").size(), 4);
                Assert.assertEquals(jsonPath.getMap("stages[" + index + "].payload.auth").size(), 5);

                variables.setMrzText(jsonPath.getString("stages[" + index + "].payload.fields.mrz_text"));
                variables.setMrzType(jsonPath.getString("stages[" + index + "].payload.fields.mrz_type"));
                variables.setDocumentType(jsonPath.getString("stages[" + index + "].payload.fields.document_type"));
                variables.setDocumentSubType(jsonPath.getString("stages[" + index + "].payload.fields.document_sub_type"));
                variables.setIssuingCountryCode(jsonPath.getString("stages[" + index + "].payload.fields.issuing_country_code"));
                variables.setLastNameEnglish(jsonPath.getString("stages[" + index + "].payload.fields.last_name"));
                variables.setFirstNameEnglish(jsonPath.getString("stages[" + index + "].payload.fields.first_name"));
                variables.setPassportNumber(jsonPath.getInt("stages[" + index + "].payload.fields.passport_number"));
                variables.setNationalityCode(jsonPath.getString("stages[" + index + "].payload.fields.nationality_code"));
                variables.setDateOfBirth(jsonPath.getString("stages[" + index + "].payload.fields.date_of_birth"));
                variables.setGender(jsonPath.getString("stages[" + index + "].payload.fields.gender"));
                variables.setDateOfExpiry(jsonPath.getString("stages[" + index + "].payload.fields.date_of_expiry"));
                variables.setPersonalNumber(jsonPath.getString("stages[" + index + "].payload.fields.personal_number"));
                variables.setMightBeTruncated(jsonPath.getBoolean("stages[" + index + "].payload.metadata.might_be_truncated"));
                variables.setFaceImage(jsonPath.getString("stages[" + index + "].payload.images.face_image"));
                variables.setInputImage(jsonPath.getString("stages[" + index + "].payload.images.input_image"));

                String NewOCR = System.getProperty("OCR_NEW");
                boolean boolValue = Boolean.parseBoolean(NewOCR);
                if (boolValue) {
                    variables.setCroppedImage(jsonPath.getString("stages[" + index + "].payload.images.aligned_card"));
                    variables.setCroppedFieldImage(jsonPath.getString("stages[" + index + "].payload.images.aligned_field"));
                } else {
                    variables.setCroppedImage(jsonPath.getString("stages[" + index + "].payload.images.cropped_image"));
                    variables.setCroppedFieldImage(jsonPath.getString("stages[" + index + "].payload.images.cropped_field_image"));
                }
                variables.setDocumentInFrame(jsonPath.getBoolean("stages[" + index + "].payload.auth.document_in_frame"));
                variables.setExpiryDateValid(jsonPath.getBoolean("stages[" + index + "].payload.auth.expiry_date_valid"));
                variables.setFaceSizeValid(jsonPath.getBoolean("stages[" + index + "].payload.auth.face_size_valid"));
                variables.setFacePositionValid(jsonPath.getBoolean("stages[" + index + "].payload.auth.face_position_valid"));
                variables.setFaceRotationValid(jsonPath.getBoolean("stages[" + index + "].payload.auth.face_rotation_valid"));
                variables.setStageStatus(jsonPath.getString("stages[" + index + "].status"));
            } else {
                Assert.assertEquals(jsonPath.getMap("").size(), 8);
                Assert.assertEquals(jsonPath.getMap("stages[" + index + "].payload").size(), 6);
                Assert.assertEquals(jsonPath.getMap("stages[" + index + "].payload.fields").size(), 13);
                Assert.assertEquals(jsonPath.getMap("stages[" + index + "].payload.images").size(), 4);
                Assert.assertEquals(jsonPath.getMap("stages[" + index + "].payload.auth").size(), 6);

                variables.setMrzText(jsonPath.getString("stages[" + index + "].payload.fields.mrz_text"));
                variables.setMrzType(jsonPath.getString("stages[" + index + "].payload.fields.mrz_type"));
                variables.setDocumentType(jsonPath.getString("stages[" + index + "].payload.fields.document_type"));
                variables.setDocumentSubType(jsonPath.getString("stages[" + index + "].payload.fields.document_sub_type"));
                variables.setIssuingCountryCode(jsonPath.getString("stages[" + index + "].payload.fields.issuing_country_code"));
                variables.setLastNameEnglish(jsonPath.getString("stages[" + index + "].payload.fields.last_name"));
                variables.setFirstNameEnglish(jsonPath.getString("stages[" + index + "].payload.fields.first_name"));
                variables.setPassportNumber(jsonPath.getInt("stages[" + index + "].payload.fields.passport_number"));
                variables.setNationalityCode(jsonPath.getString("stages[" + index + "].payload.fields.nationality_code"));
                variables.setDateOfBirth(jsonPath.getString("stages[" + index + "].payload.fields.date_of_birth"));
                variables.setGender(jsonPath.getString("stages[" + index + "].payload.fields.gender"));
                variables.setDateOfExpiry(jsonPath.getString("stages[" + index + "].payload.fields.date_of_expiry"));
                variables.setPersonalNumber(jsonPath.getString("stages[" + index + "].payload.fields.personal_number"));
//                variables.setMightBeTruncated(jsonPath.getBoolean("stages[" + index + "].payload.metadata.might_be_truncated"));
                variables.setFaceImage(jsonPath.getString("stages[" + index + "].payload.images.face_image"));
                variables.setInputImage(jsonPath.getString("stages[" + index + "].payload.images.input_image"));

                String NewOCR = System.getProperty("OCR_NEW");
                boolean boolValue = Boolean.parseBoolean(NewOCR);
                if (boolValue) {
                    variables.setCroppedImage(jsonPath.getString("stages[" + index + "].payload.images.aligned_card"));
                    variables.setCroppedFieldImage(jsonPath.getString("stages[" + index + "].payload.images.aligned_field"));
                } else {
                    variables.setCroppedImage(jsonPath.getString("stages[" + index + "].payload.images.cropped_image"));
                    variables.setCroppedFieldImage(jsonPath.getString("stages[" + index + "].payload.images.cropped_field_image"));
                }
                variables.setDocumentInFrame(jsonPath.getBoolean("stages[" + index + "].payload.auth.document_in_frame"));
                variables.setExpiryDateValid(jsonPath.getBoolean("stages[" + index + "].payload.auth.expiry_date_valid"));
                variables.setFaceSizeValid(jsonPath.getBoolean("stages[" + index + "].payload.auth.face_size_valid"));
                variables.setFacePositionValid(jsonPath.getBoolean("stages[" + index + "].payload.auth.face_position_valid"));
                variables.setFaceRotationValid(jsonPath.getBoolean("stages[" + index + "].payload.auth.face_rotation_valid"));
                variables.setStageStatus(jsonPath.getString("stages[" + index + "].status"));

            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    @Step("Set complete stage variables of MRZ")
    private void setMRZCompleteStageVariablesNew(Variables variables, JsonPath jsonPath, int index) {
        Assert.assertEquals(jsonPath.getMap("").size(), 6);
        Assert.assertEquals(jsonPath.getMap("stages[" + index + "].payload").size(), 5);
        Assert.assertEquals(jsonPath.getMap("stages[" + index + "].payload.fields").size(), 13);
        Assert.assertEquals(jsonPath.getMap("stages[" + index + "].payload.images").size(), 4);
        Assert.assertEquals(jsonPath.getMap("stages[" + index + "].payload.auth").size(), 5);

        variables.setMrzText(jsonPath.getString("stages[" + index + "].payload.fields.mrz_text"));
        variables.setMrzType(jsonPath.getString("stages[" + index + "].payload.fields.mrz_type"));
        variables.setDocumentType(jsonPath.getString("stages[" + index + "].payload.fields.document_type"));
        variables.setDocumentSubType(jsonPath.getString("stages[" + index + "].payload.fields.document_sub_type"));
        variables.setIssuingCountryCode(jsonPath.getString("stages[" + index + "].payload.fields.issuing_country_code"));
        variables.setLastNameEnglish(jsonPath.getString("stages[" + index + "].payload.fields.last_name"));
        variables.setFirstNameEnglish(jsonPath.getString("stages[" + index + "].payload.fields.first_name"));
        variables.setPassportNumber(jsonPath.getInt("stages[" + index + "].payload.fields.passport_number"));
        variables.setNationalityCode(jsonPath.getString("stages[" + index + "].payload.fields.nationality_code"));
        variables.setDateOfBirth(jsonPath.getString("stages[" + index + "].payload.fields.date_of_birth"));
        variables.setGender(jsonPath.getString("stages[" + index + "].payload.fields.gender"));
        variables.setDateOfExpiry(jsonPath.getString("stages[" + index + "].payload.fields.date_of_expiry"));
        variables.setPersonalNumber(jsonPath.getString("stages[" + index + "].payload.fields.personal_number"));
        variables.setMightBeTruncated(jsonPath.getBoolean("stages[" + index + "].payload.metadata.might_be_truncated"));
        variables.setFaceImage(jsonPath.getString("stages[" + index + "].payload.images.face_image"));
        variables.setInputImage(jsonPath.getString("stages[" + index + "].payload.images.input_image"));

        String NewOCR = System.getProperty("OCR_NEW");
        boolean boolValue = Boolean.parseBoolean(NewOCR);
        if (boolValue) {
            variables.setCroppedImage(jsonPath.getString("stages[" + index + "].payload.images.aligned_card"));
            variables.setCroppedFieldImage(jsonPath.getString("stages[" + index + "].payload.images.aligned_field"));
        } else {
            variables.setCroppedImage(jsonPath.getString("stages[" + index + "].payload.images.cropped_image"));
            variables.setCroppedFieldImage(jsonPath.getString("stages[" + index + "].payload.images.cropped_field_image"));
        }
        variables.setDocumentInFrame(jsonPath.getBoolean("stages[" + index + "].payload.auth.document_in_frame"));
        variables.setExpiryDateValid(jsonPath.getBoolean("stages[" + index + "].payload.auth.expiry_date_valid"));
        variables.setFaceSizeValid(jsonPath.getBoolean("stages[" + index + "].payload.auth.face_size_valid"));
        variables.setFacePositionValid(jsonPath.getBoolean("stages[" + index + "].payload.auth.face_position_valid"));
        variables.setFaceRotationValid(jsonPath.getBoolean("stages[" + index + "].payload.auth.face_rotation_valid"));
        variables.setStageStatus(jsonPath.getString("stages[" + index + "].status"));
    }


    @Step("Set complete stage variables of DK-DL")
    private void setDKDLCompleteStageVariables(Variables variables, JsonPath jsonPath, int index) {
        Assert.assertEquals(jsonPath.getMap("").size(), 6);
        Assert.assertEquals(jsonPath.getMap("stages[" + index + "].payload").size(), 3);
        if (jsonPath.getMap("stages[" + index + "].payload.fields").size() == 6) {
            Assert.assertEquals(jsonPath.getMap("stages[" + index + "].payload.fields").size(), 6);
        } else Assert.assertEquals(jsonPath.getMap("stages[" + index + "].payload.fields").size(), 7);
        Assert.assertEquals(jsonPath.getMap("stages[" + index + "].payload.images").size(), 3);

        variables.setLastNameEnglish(jsonPath.getString("stages[" + index + "].payload.fields.last_name"));
        variables.setFirstNameEnglish(jsonPath.getString("stages[" + index + "].payload.fields.first_name"));
        variables.setDateOfBirth(jsonPath.getString("stages[" + index + "].payload.fields.date_of_birth"));
        variables.setDateOfIssue(jsonPath.getString("stages[" + index + "].payload.fields.date_of_issue"));
        variables.setDateOfExpiry(jsonPath.getString("stages[" + index + "].payload.fields.date_of_expiry"));
        variables.setPersonalNumber(jsonPath.getString("stages[" + index + "].payload.fields.personal_number"));
        variables.setLicenseNumber(jsonPath.getInt("stages[" + index + "].payload.fields.license_number"));
        variables.setCardType(jsonPath.getString("stages[" + index + "].payload.card_type"));
        variables.setFaceImage(jsonPath.getString("stages[" + index + "].payload.images.face_image"));
        variables.setInputImage(jsonPath.getString("stages[" + index + "].payload.images.input_image"));
        variables.setCroppedImage(jsonPath.getString("stages[" + index + "].payload.images.cropped_image"));
        variables.setStageStatus(jsonPath.getString("stages[" + index + "].status"));
    }

    @Step("Set complete stage variables of liveness passive")
    private void setLivenessPassiveCompleteVariables(Variables variables, JsonPath jsonPath, int index) {
        Assert.assertEquals(jsonPath.getMap("stages[" + index + "]").size(), 4);
        Assert.assertEquals(jsonPath.getMap("stages[" + index + "].stage").size(), 2);
        Assert.assertEquals(jsonPath.getMap("stages[" + index + "].payload").size(), 4);
        Assert.assertEquals(jsonPath.getMap("stages[" + index + "].payload.images").size(), 2);

        variables.setActionType1(jsonPath.getString("stages[" + index + "].action_type"));
        variables.setOrder1(jsonPath.getInt("stages[" + index + "].stage.order"));
        variables.setType1(jsonPath.getString("stages[" + index + "].stage.type"));

        variables.setThreshold(jsonPath.getDouble("stages[" + index + "].payload.threshold"));
        variables.setLivenessScore(jsonPath.getFloat("stages[" + index + "].payload.score"));
        variables.setFaceImage(jsonPath.getString("stages[" + index + "].payload.images.face_image"));
        variables.setInputImage(jsonPath.getString("stages[" + index + "].payload.images.input_image"));

        if (jsonPath.getList("stages[" + index + "].payload.head_position_changes").size() > 0) {
            variables.setHeadPositionType(jsonPath.getString("stages[" + index + "].payload.head_position_changes[" + (jsonPath.getList("stages[" + index + "].payload.head_position_changes").size() - 1) + "].type"));
            variables.setHeadPositionExpected(jsonPath.getBoolean("stages[" + index + "].payload.head_position_changes[" + (jsonPath.getList("stages[" + index + "].payload.head_position_changes").size() - 1) + "].expected"));
        }

        variables.setStageStatus(jsonPath.getString("stages[" + index + "].status"));
    }

    @Step("Set complete stage variables of liveness passive")
    private void setLivenessPassiveCompleteVariablesNew(Variables variables, JsonPath jsonPath, int index) {
        Assert.assertEquals(jsonPath.getMap("stages[" + index + "]").size(), 5);
//        Assert.assertEquals(jsonPath.getMap("stages[" + index + "].stage").size(), 2);
        Assert.assertEquals(jsonPath.getMap("stages[" + index + "].payload").size(), 3);
        Assert.assertEquals(jsonPath.getMap("stages[" + index + "].payload.images").size(), 2);

        variables.setActionType1(jsonPath.getString("stages[" + index + "].action_type"));
        variables.setOrder1(jsonPath.getInt("stages[" + index + "].order"));
        variables.setType1(jsonPath.getString("stages[" + index + "].stage.type"));

        variables.setThreshold(jsonPath.getDouble("stages[" + index + "].payload.threshold"));
        variables.setLivenessScore(jsonPath.getFloat("stages[" + index + "].payload.score"));
        variables.setFaceImage(jsonPath.getString("stages[" + index + "].payload.images.face_image"));
        variables.setInputImage(jsonPath.getString("stages[" + index + "].payload.images.input_image"));

//        if (jsonPath.getList("stages[" + index + "].payload.head_position_changes").size() > 0) {
//            variables.setHeadPositionType(jsonPath.getString("stages[" + index + "].payload.head_position_changes[" + (jsonPath.getList("stages[" + index + "].payload.head_position_changes").size() - 1) + "].type"));
//            variables.setHeadPositionExpected(jsonPath.getBoolean("stages[" + index + "].payload.head_position_changes[" + (jsonPath.getList("stages[" + index + "].payload.head_position_changes").size() - 1) + "].expected"));
//        }

        variables.setStageStatus(jsonPath.getString("stages[" + index + "].status"));
    }

    @Step("Set complete stage variables of liveness passive")
    private void setLivenessSTTStageVariables(Variables variables, JsonPath jsonPath, int index) {
        Assert.assertEquals(jsonPath.getMap("stages[" + index + "]").size(), 4);
        Assert.assertEquals(jsonPath.getMap("stages[" + index + "].stage").size(), 2);
        Assert.assertEquals(jsonPath.getMap("stages[" + index + "].payload").size(), 5);

        variables.setActionType2(jsonPath.getString("stages[" + index + "].action_type"));
        variables.setOrder2(jsonPath.getInt("stages[" + index + "].stage.order"));
        variables.setType2(jsonPath.getString("stages[" + index + "].stage.type"));

        variables.setSttText(jsonPath.getString("stages[" + index + "].payload.stt_text"));
        variables.setOriginalText(jsonPath.getString("stages[" + index + "].payload.original_text"));
        variables.setThreshold2(jsonPath.getDouble("stages[" + index + "].payload.threshold"));
        variables.setLanguage(jsonPath.getString("stages[" + index + "].payload.language"));
        variables.setScore(jsonPath.getFloat("stages[" + index + "].payload.score"));
        variables.setStageStatus5(jsonPath.getString("stages[" + index + "].status"));
    }

    @Step("Set single frame error variables")
    private void setSingleFrameErrorVariables(Variables variables, JsonPath jsonPath, int index) {
        variables.setStatusReason(jsonPath.getString("stages[" + index + "].statusReason"));
        variables.setErrorCode(jsonPath.getInt("stages[" + index + "].statusCode"));
        variables.setStageStatus(jsonPath.getString("stages[" + index + "].status"));
    }

    @Step("Single frame main response, passing to complete stage (only stage)")
    public void singleFrameMainResponse(Response response, Variables variables) {
        jsonPath = response.jsonPath();
        setActionTypeCompleteVariables(variables, jsonPath);
    }

    @Step("Set liveness stages variables according the stage order")
    private void setLivenessStagesVariables(Variables variables, JsonPath jsonPath, int index) {
        variables.setActionType3(jsonPath.getString("stages[" + index + "].action_type"));
        if (variables.getConfigFileName().equalsIgnoreCase("reverseStages.json") || variables.getConfigFileName().equalsIgnoreCase("gesturesWithActive.json")) {
            switch (jsonPath.getInt("stages[" + index + "].stage.order")) {
                case 3: {
                    variables.setOrder3(jsonPath.getInt("stages[" + index + "].stage.order"));
                    variables.setType3(jsonPath.getString("stages[" + index + "].stage.type"));
                    variables.setHeadPositionType1(jsonPath.getString("stages[" + index + "].payload.head_position_changes[" + (jsonPath.getList("stages[" + index + "].payload.head_position_changes").size() - 1) + "].type"));
                    variables.setHeadPositionExpected1(jsonPath.getBoolean("stages[" + index + "].payload.head_position_changes[" + (jsonPath.getList("stages[" + index + "].payload.head_position_changes").size() - 1) + "].expected"));
                    variables.setStageStatus3(jsonPath.getString("stages[" + index + "].status"));
                    break;
                }
                case 4: {
                    variables.setOrder4(jsonPath.getInt("stages[" + index + "].stage.order"));
                    variables.setType4(jsonPath.getString("stages[" + index + "].stage.type"));
                    variables.setHeadPositionType2(jsonPath.getString("stages[" + index + "].payload.head_position_changes[" + (jsonPath.getList("stages[" + index + "].payload.head_position_changes").size() - 1) + "].type"));
                    variables.setHeadPositionExpected2(jsonPath.getBoolean("stages[" + index + "].payload.head_position_changes[" + (jsonPath.getList("stages[" + index + "].payload.head_position_changes").size() - 1) + "].expected"));
                    variables.setStageStatus4(jsonPath.getString("stages[" + index + "].status"));
                    break;
                }
                case 5: {
                    variables.setOrder6(jsonPath.getInt("stages[" + index + "].stage.order"));
                    variables.setType6(jsonPath.getString("stages[" + index + "].stage.type"));
                    variables.setHeadPositionType3(jsonPath.getString("stages[" + index + "].payload.head_position_changes[" + (jsonPath.getList("stages[" + index + "].payload.head_position_changes").size() - 1) + "].type"));
                    variables.setHeadPositionExpected3(jsonPath.getBoolean("stages[" + index + "].payload.head_position_changes[" + (jsonPath.getList("stages[" + index + "].payload.head_position_changes").size() - 1) + "].expected"));
                    variables.setStageStatus6(jsonPath.getString("stages[" + index + "].status"));
                    break;
                }
            }
        } else {
            switch (jsonPath.getInt("stages[" + index + "].stage.order")) {
                case 2: {
                    variables.setOrder3(jsonPath.getInt("stages[" + index + "].stage.order"));
                    variables.setType3(jsonPath.getString("stages[" + index + "].stage.type"));

                    variables.setHeadPositionType1(jsonPath.getString("stages[" + index + "].payload.head_position_changes[" + (jsonPath.getList("stages[" + index + "].payload.head_position_changes").size() - 1) + "].type"));
                    variables.setHeadPositionExpected1(jsonPath.getBoolean("stages[" + index + "].payload.head_position_changes[" + (jsonPath.getList("stages[" + index + "].payload.head_position_changes").size() - 1) + "].expected"));

                    variables.setStageStatus3(jsonPath.getString("stages[" + index + "].status"));
                    break;
                }
                case 3: {
                    variables.setOrder4(jsonPath.getInt("stages[" + index + "].stage.order"));
                    variables.setType4(jsonPath.getString("stages[" + index + "].stage.type"));

                    variables.setHeadPositionType2(jsonPath.getString("stages[" + index + "].payload.head_position_changes[" + (jsonPath.getList("stages[" + index + "].payload.head_position_changes").size() - 1) + "].type"));
                    variables.setHeadPositionExpected2(jsonPath.getBoolean("stages[" + index + "].payload.head_position_changes[" + (jsonPath.getList("stages[" + index + "].payload.head_position_changes").size() - 1) + "].expected"));

                    variables.setStageStatus4(jsonPath.getString("stages[" + index + "].status"));
                    break;
                }
                case 4: {
                    variables.setOrder6(jsonPath.getInt("stages[" + index + "].stage.order"));
                    variables.setType6(jsonPath.getString("stages[" + index + "].stage.type"));

                    variables.setHeadPositionType3(jsonPath.getString("stages[" + index + "].payload.head_position_changes[" + (jsonPath.getList("stages[" + index + "].payload.head_position_changes").size() - 1) + "].type"));
                    variables.setHeadPositionExpected3(jsonPath.getBoolean("stages[" + index + "].payload.head_position_changes[" + (jsonPath.getList("stages[" + index + "].payload.head_position_changes").size() - 1) + "].expected"));

                    variables.setStageStatus6(jsonPath.getString("stages[" + index + "].status"));
                    break;
                }
            }
        }
    }

    @Step("Print the Irad's library version")
    public void getOcrLibraries(Variables variables) {
        clientRequest = ocrLibraries();
        jsonPath = clientRequest.jsonPath();

        variables.setCmc7(jsonPath.getString("\"cmc7_ocr_python.git\""));
        variables.setFranceId(jsonPath.getString("\"france_id_ocr_python.git\""));
        variables.setDenmarkDl(jsonPath.getString("\"denmark_dl_ocr_python.git\""));
        variables.setIsraelDl(jsonPath.getString("\"israel_dl_ocr_python.git\""));
        variables.setIsraelId(jsonPath.getString("\"israel_id_ocr_python.git\""));
        variables.setPhilippineCheque(jsonPath.getString("\"philippine_cheque_ocr_python.git\""));
        variables.setPassport(jsonPath.getString("\"passport_ocr_python.git\""));
    }

    @Step("Set gestures variables")
    public void setGesturesVariables(Variables variables, JsonPath jsonPath, int index) {
        Assert.assertEquals(jsonPath.getMap("stages[" + index + "].payload").size(), 2);
        switch (variables.getGesturesArray().size()) {
            case 1: {
                Assert.assertEquals(jsonPath.getList("stages[" + index + "].payload.gestures").size(), 1);
                break;
            }
            case 2: {
                Assert.assertEquals(jsonPath.getList("stages[" + index + "].payload.gestures").size(), 2);
                break;
            }
            case 3: {
                Assert.assertEquals(jsonPath.getList("stages[" + index + "].payload.gestures").size(), 3);
                break;
            }
        }

        variables.setGestureActionType(jsonPath.getString("stages[" + index + "].action_type"));
        variables.setGestureOrder(jsonPath.getInt("stages[" + index + "].stage.order"));
        variables.setGestureType(jsonPath.getString("stages[" + index + "].stage.type"));

        variables.setGesturesArray(jsonPath.getList("stages[" + index + "].payload.gestures"));
        variables.setSequenceSecondsInterval(jsonPath.getInt("stages[" + index + "].payload.sequenceSecondsInterval"));

        variables.setGestureStageStatus(jsonPath.getString("stages[" + index + "].status"));
    }

    @Step("Set ocr Capture front side variables")
    public void setCaptureFrontSideVariables(Variables variables, JsonPath jsonPath, int index) {
        variables.setActionType1(jsonPath.getString("stages[" + index + "].action_type"));
        variables.setOrder1(jsonPath.getInt("stages[" + index + "].stage.order"));
        variables.setType1(jsonPath.getString("stages[" + index + "].stage.type"));
        variables.setStageStatus(jsonPath.getString("stages[" + index + "].status"));

        variables.setInputImage(jsonPath.getString("stages[" + index + "].payload.images.input_image"));
    }

    @Step("Set ocr Capture back side variables")
    public void setCaptureBackSideVariables(Variables variables, JsonPath jsonPath, int index) {
        variables.setActionType2(jsonPath.getString("stages[" + index + "].action_type"));
        variables.setOrder2(jsonPath.getInt("stages[" + index + "].stage.order"));
        variables.setType2(jsonPath.getString("stages[" + index + "].stage.type"));
        variables.setStageStatus2(jsonPath.getString("stages[" + index + "].status"));

        variables.setInputImage2(jsonPath.getString("stages[" + index + "].payload.images.input_image"));
    }
}
