package btrust.onboardingProcess.api;

import btrust.onboardingProcess.api.variables.EmailTemplate;
import btrust.onboardingProcess.api.variables.EndpointResults;
import btrust.onboardingProcess.api.variables.MobileInteraction;
import btrust.onboardingProcess.api.variables.PreProcess;
import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Random;

import static btrust.onboardingProcess.api.Utilities.processHandlerV1AndHarel;
import static btrust.onboardingProcess.api.Utilities.processHandlerV2;
import static io.restassured.RestAssured.given;

public class ApiResponses extends ApiRequests {

    Response apiResponse;
    JsonPath jsonPath;

    String ocr_new;
    boolean OCR_NEW;

    private final String btrust1Auth = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJ1c2VyRGF0YSI6IntcImlkXCI6ODMsXCJjb21wYW55SWRcIjo0LFwiYWN0aXZlU3ViQ29tcGFueVwiOjAsXCJyb2xlc1wiOls3XSxcInBlcm1pc3Npb25zXCI6W3tcImlkXCI6MCxcInJvbGVJZFwiOjAsXCJ0eXBlXCI6MCxcInR5cGVJZFwiOjAsXCJjYW5SZWFkXCI6ZmFsc2UsXCJjYW5FZGl0XCI6ZmFsc2UsXCJjYW5DcmVhdGVcIjpmYWxzZX0se1wiaWRcIjowLFwicm9sZUlkXCI6MCxcInR5cGVcIjo2LFwidHlwZUlkXCI6MCxcImNhblJlYWRcIjp0cnVlLFwiY2FuRWRpdFwiOnRydWUsXCJjYW5DcmVhdGVcIjp0cnVlfV19IiwiaWF0IjoxNjIyNTU0NjUxLCJleHAiOjMxOTkzNTQ2NTF9.S53gOLQJ9XqD3UsFZzx_pn9hOJ6hpCM8CDLHI2rXvPR256GLDj4Y0Eb4YKSWrERs8hJlvLA9okxLkJJ6I1JgxHzgrwgb2gdMyDPEji6rGXRijKzsbwWYUzT1xe78p0cb-LUGfrcfM80HK7qV2CePZ_ho7C9Jczg_evV56xVIoN10eZrrpzUDEJ-dfTjtXSK93AkGeh6t1n3Lm8xhDYPrZ2fy59Da4w9MmPzUwl3VaAxIawum2VRM2U6p15hH4bB_p1HZaVdA_2GmXf74KuRkguyPbbDWZI7g9LVU5POj6JGxTsRtDOaGBeFNsRXKVkvb2DizMxCl1AhXPwf_G9-Oaw";
    protected String sessionId;
    protected String mobileFlowLink;
    protected Response response;

    public void MobileInteractionUIBase() {
        mobileFlowLink = null;
        response = null;
        jsonPath = null;
        sessionId = null;
    }


    public String createSessionId(int flowNumber) {
        response = given()
                .header("Authorization", System.getProperty("ACCESS_TOKEN"))
                .header("X-Company-ID", System.getProperty("COMPANY_ID"))
//                .log()
//                .all()
                .get("https://qa-nginx-gateway.scanovateoncloud.com/flow/" + flowNumber + "/link")
                .then()
                .statusCode(200)
                .extract()
                .response();

//        System.out.println(response.getBody().asString());
        jsonPath = response.jsonPath();
        sessionId = jsonPath.getString("data").split("process_id=")[1];
        System.out.println("Session Id: " + sessionId);
        return sessionId;
    }


    public String createExternalParamLink(int flowNumber, String idNumber) {

        String requestBody = "{\n" +
                "\"flowId\":" + flowNumber + ",\n" +
                "\"params\":{\"ID_Number\":" + idNumber + "}\n" +
                "}";

        response = given()
                .header("Authorization", System.getProperty("ACCESS_TOKEN"))
                .header("X-Company-ID", "63f225246ef5df9ba8306568")
                .header("Content-Type", "application/json")
                .body(requestBody)
                //               .when()
//                .log()
//                .all()
                .post("https://qa-nginx-gateway.scanovateoncloud.com:443/api/v3/mobile_interaction/linkWithParams")
                .then()
                .statusCode(200)
                .extract()
                .response();

//      System.out.println(response.getBody().asString());
        jsonPath = response.jsonPath();
        sessionId = jsonPath.getString("data").split("process_id=")[1];
        System.out.println("Session Id: " + sessionId);
        return sessionId;
    }



    public void setNewMobileFlow(MobileInteraction mobileInteraction, String workflowName, String flow, String viewScript, int statusNumber) {
        apiResponse = createMobileInteractionNewFlow(workflowName, flow, viewScript, statusNumber);
        jsonPath = apiResponse.jsonPath();
        String path;
        path = "";
        if (jsonPath.getMap(path).containsKey("success"))
            mobileInteraction.setSuccess(jsonPath.getBoolean("success"));
        if (jsonPath.getMap(path).containsKey("errorCode"))
            mobileInteraction.setErrorCode(jsonPath.getInt("errorCode"));
        if (jsonPath.getMap(path).containsKey("data"))
            mobileInteraction.setWorkflowNumber(jsonPath.getInt("data"));
    }


    public void setUpdateDataInsideMobileInteractionFlow(MobileInteraction mobileInteraction, int idNumber, String workflowName, String flow, String viewScript, int statusNumber) {
        apiResponse = UpdateMobileInteractionNewFlow(idNumber, workflowName, flow, viewScript, statusNumber);
        jsonPath = apiResponse.jsonPath();
        String path;
        path = "data";

        mobileInteraction.setSuccess(jsonPath.getBoolean("success"));
        mobileInteraction.setErrorCode(jsonPath.getInt("errorCode"));
        mobileInteraction.setId(jsonPath.getInt(path + ".id"));
        mobileInteraction.setWorkflowName(jsonPath.getString(path + ".name"));
        mobileInteraction.setCreatedBy(jsonPath.getInt(path + ".createdBy"));
        mobileInteraction.setFlow(jsonPath.getString(path + ".flow"));
        mobileInteraction.setViewScript(jsonPath.getString(path + ".viewScript"));
        mobileInteraction.setStatus(jsonPath.getInt(path + ".status"));
    }


    public void updateRequestPutCall(PreProcess preProcess, int flowId, String missingFieldsKey, String caseId, String sessionIdKey, String sessionIdValue) {
        ocr_new = System.getProperty("OCR_NEW");
        OCR_NEW = Boolean.parseBoolean(ocr_new);
        if (!OCR_NEW) {
            apiResponse = initPutForSessionId(flowId, missingFieldsKey, caseId, sessionIdKey, sessionIdValue);
            jsonPath = apiResponse.jsonPath();

            preProcess.setSuccess(jsonPath.getBoolean("success"));
            preProcess.setErrorCode(jsonPath.getInt("errorCode"));
            preProcess.setInquiryId(jsonPath.getInt("data.inquiryId"));
            preProcess.setDataSuccess(jsonPath.getBoolean("data.success"));
            preProcess.setNextProcess(jsonPath.getString("data.nextProcess"));
            if (!preProcess.getNextProcess().equalsIgnoreCase("process_ended")) {
                preProcess.setMissingFields(jsonPath.getString("data.missingFields[0]"));
            }
            preProcess.setDataErrorCode(jsonPath.getInt("data.errorCode"));
            if (jsonPath.getMap("data").containsKey("errorMessage")) {
                preProcess.setDataErrorMessage(jsonPath.getString("data.errorMessage"));
            }
            preProcess.setSessionId(jsonPath.getString("data.sessionId"));

            if (jsonPath.getMap("data").containsKey("configuration")) {
                preProcess.setConfiguration(jsonPath.getMap("data.configuration"));
                switch (jsonPath.getMap("data.configuration").keySet().toString()) {
                    case "[mathildaFileName]": {
                        preProcess.setMathildaFileName(jsonPath.getString("data.configuration.mathildaFileName"));
                        break;
                    }
                    case "[ocrTypes]": {
                        for (String type : jsonPath.getString("data.configuration.ocrTypes").split(",")) {
                            preProcess.getOcrTypes().add(type);
                        }
                        break;
                    }
                    case "[getOcrResults]": {
                        preProcess.setGetOcrResults(jsonPath.getBoolean("data.configuration.getOcrResults"));
                        break;
                    }
                }
            }
        } else {
            String processId = preProcess.getDataId();

            apiResponse = initPutForSessionIdNew(flowId, missingFieldsKey, caseId, sessionIdKey, sessionIdValue, processId);
            jsonPath = apiResponse.jsonPath();

            preProcess.setSuccess(jsonPath.getBoolean("success"));
            preProcess.setErrorCode(jsonPath.getInt("errorCode"));
            preProcess.setDataId(jsonPath.getString("data.id"));
            preProcess.setInquiryId(jsonPath.getInt("data.inquiryId"));
            preProcess.setDataSuccess(jsonPath.getBoolean("data.success"));
            preProcess.setNextProcess(jsonPath.getString("data.nextProcess"));
            if (!preProcess.getNextProcess().equalsIgnoreCase("process_ended")) {
                preProcess.setMissingFields(jsonPath.getString("data.missingFields[0]"));
            }
            preProcess.setDataErrorCode(jsonPath.getInt("data.errorCode"));
            if (jsonPath.getMap("data").containsKey("errorMessage")) {
                preProcess.setDataErrorMessage(jsonPath.getString("data.errorMessage"));
            }
            preProcess.setSessionId(jsonPath.getString("data.flowUUID"));
//
//            if (jsonPath.getMap("data").containsKey("configuration")) {
//                preProcess.setConfiguration(jsonPath.getMap("data.configuration"));
//                switch (jsonPath.getMap("configuration").keySet().toString()) {
//                    case "[mathildaFileName]": {
//                        preProcess.setMathildaFileName(jsonPath.getString("configuration.mathildaFileName"));
//                        break;
//                    }
//                    case "[ocrTypes]": {
//                        for (String type : jsonPath.getString("configuration.ocrTypes").split(",")) {
//                            preProcess.getOcrTypes().add(type);
//                        }
//                        break;
//                    }
//                    case "[getOcrResults]": {
//                        preProcess.setGetOcrResults(jsonPath.getBoolean("configuration.getOcrResults"));
//                        break;
//                    }
//                }
//            }
        }

    }


    public void setNewEmailTemplate(EmailTemplate emailTemplate, String emailTemplateName, String subject, boolean withPortal, String viewScript, String htmlScript, boolean enableEditing) {
        apiResponse = createNewEmailTemplate(emailTemplateName, subject, withPortal, viewScript, htmlScript, enableEditing);
        jsonPath = apiResponse.jsonPath();

        emailTemplate.setSuccess(jsonPath.getBoolean("success"));
        emailTemplate.setErrorCode(jsonPath.getInt("errorCode"));
        emailTemplate.setData(jsonPath.getInt("data"));
    }


    public void updateEmailTemplate(EmailTemplate emailTemplate, String emailTemplateName, String subject, boolean withPortal, String viewScript, String htmlScript, boolean enableEditing, int templateId) {
        apiResponse = updateEmailTemplate(emailTemplateName, subject, withPortal, viewScript, htmlScript, enableEditing, templateId);
        jsonPath = apiResponse.jsonPath();

        emailTemplate.setSuccess(jsonPath.getBoolean("success"));
        emailTemplate.setErrorCode(jsonPath.getInt("errorCode"));
        emailTemplate.setData1(jsonPath.getString("data"));
    }

    public void deleteEmailTemplate(EmailTemplate emailTemplate, int templateId) {
        apiResponse = deleteEmailTemplate(templateId);
        jsonPath = apiResponse.jsonPath();

        emailTemplate.setSuccess(jsonPath.getBoolean("success"));
        emailTemplate.setErrorCode(jsonPath.getInt("errorCode"));
        emailTemplate.setData1(jsonPath.getString("data"));
    }


    public void initSession(PreProcess preProcess, int flowId, String sessionId) {
        apiResponse = initPutForSessionId(flowId, sessionId);
        //       apiResponse = initPutForSessionId1(flowId, sessionId,companyUuid);
        jsonPath = apiResponse.jsonPath();

        preProcess.setFlowId(flowId);
        preProcess.setSuccess(jsonPath.getBoolean("success"));
        preProcess.setErrorCode(jsonPath.getInt("errorCode"));
        preProcess.setDataId(jsonPath.getString("data.id"));
        preProcess.setInquiryId(jsonPath.getInt("data.inquiryId"));
        preProcess.setDataSuccess(jsonPath.getBoolean("data.success"));
        preProcess.setNextProcess(jsonPath.getString("data.nextProcess"));
        preProcess.setMissingFields(jsonPath.getString("data.missingFields[0]"));
        preProcess.setDataErrorCode(jsonPath.getInt("data.errorCode"));
        preProcess.setSessionId(jsonPath.getString("data.sessionId"));

        if (jsonPath.getMap("data").containsKey("configuration")) {
            preProcess.setConfiguration(jsonPath.getMap("data.configuration"));
            switch (jsonPath.getMap("data.configuration").keySet().toString()) {
                case "[mathildaFileName]": {
                    preProcess.setMathildaFileName(jsonPath.getString("data.configuration.mathildaFileName"));
                    break;
                }
                case "[ocrTypes]": {
                    for (String type : jsonPath.getString("data.configuration.ocrTypes").split(",")) {
                        preProcess.getOcrTypes().add(type);
                    }
                    break;
                }
                case "[getOcrResults]": {
                    preProcess.setGetOcrResults(jsonPath.getBoolean("data.configuration.getOcrResults"));
                    break;
                }
            }
        }
        preProcess.setBackStepAllow(jsonPath.getBoolean("data.isBackStepAllow"));
        preProcess.setCaseId(randomString());
    }


    public void initSessionNew(PreProcess preProcess, int flowId, String sessionId, String companyUuid) {
        apiResponse = initPutForSessionIdNew(flowId, sessionId, companyUuid);
        jsonPath = apiResponse.jsonPath();

        preProcess.setFlowId(flowId);
        preProcess.setSuccess(jsonPath.getBoolean("success"));
        preProcess.setErrorCode(jsonPath.getInt("errorCode"));
        preProcess.setDataId(jsonPath.getString("data.id"));
        preProcess.setInquiryId(jsonPath.getInt("data.inquiryId"));
        preProcess.setDataSuccess(jsonPath.getBoolean("data.success"));
        preProcess.setNextProcess(jsonPath.getString("data.nextProcess"));
        preProcess.setMissingFields(jsonPath.getString("data.missingFields[0]"));
        preProcess.setDataErrorCode(jsonPath.getInt("data.errorCode"));
        preProcess.setSessionId(jsonPath.getString("data.flowUUID"));

        if (jsonPath.getMap("data").containsKey("configuration")) {
            preProcess.setConfiguration(jsonPath.getMap("data.configuration"));
            switch (jsonPath.getMap("data.configuration").keySet().toString()) {
                case "[mathildaFileName]": {
                    preProcess.setMathildaFileName(jsonPath.getString("data.configuration.mathildaFileName"));
                    break;
                }
                case "[ocrTypes]": {
                    for (String type : jsonPath.getString("data.configuration.ocrTypes").split(",")) {
                        preProcess.getOcrTypes().add(type);
                    }
                    break;
                }
                case "[getOcrResults]": {
                    preProcess.setGetOcrResults(jsonPath.getBoolean("data.configuration.getOcrResults"));
                    break;
                }
            }
        }
        preProcess.setBackStepAllow(jsonPath.getBoolean("data.isBackStepAllow"));
        preProcess.setCaseId(randomString());
    }


    public void preEndpointProcess(PreProcess preProcess, Map<String, File> map) throws IOException, ParseException {
        while (!preProcess.getNextProcess().equalsIgnoreCase("process_ended")) {
            switch (preProcess.getNextProcess()) {
                case "server_ocr": {
                    postAndUpdateJsonFile(preProcess, map.get("server_ocr"));
                    break;
                }
                case "liveness": {
                    postAndUpdateJsonFile(preProcess, map.get("liveness"));
                    break;
                }
                case "server_cheque": {
                    postAndUpdateJsonFile(preProcess, map.get("server_cheque"));
                    break;
                }
                case "questionnaire": {
                    getQuestionnaireResponseFromApi(preProcess, map.get("questionnaire_ID"));
                    return;
                }
                case "card_capture": {
                    while (!preProcess.getNextProcess().equalsIgnoreCase("process_ended")) {
                        if (preProcess.getDataErrorCode() == 1004) {
                            cardCaptureImagesResponse(preProcess, map.get("card_capture_back"));
                            return;
                        }
                        cardCaptureImagesResponse(preProcess, map.get("card_capture_front"));
                    }
                }
            }
            updateRequestPutCall(preProcess, preProcess.getFlowId(), preProcess.getMissingFields(), preProcess.getCaseId(), "sessionId", preProcess.getSessionId());
            if (!preProcess.isDataSuccess()) {
                return;
            }
        }
    }

    public void getQuestionnaireResponseFromApi(PreProcess preProcess, File questionnaireFile) {
        apiResponse = putQuestionnaireRequest(preProcess.getFlowId(), questionnaireFile, preProcess.getSessionId());
        jsonPath = apiResponse.jsonPath();

        preProcess.setSuccess(jsonPath.getBoolean("success"));
        preProcess.setErrorCode(jsonPath.getInt("errorCode"));
        preProcess.setInquiryId(jsonPath.getInt("data.inquiryId"));
        preProcess.setDataSuccess(jsonPath.getBoolean("data.success"));
        preProcess.setNextProcess(jsonPath.getString("data.nextProcess"));
        preProcess.setDataErrorCode(jsonPath.getInt("data.errorCode"));
    }

    public void postAndUpdateJsonFile(PreProcess preProcess, File file) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader(file));
        if (jsonObject.containsKey("caseId")) {
            jsonObject.put("caseId", preProcess.getCaseId());
        }

        apiResponse = postJsonFile(jsonObject);
        Assert.assertNotNull(preProcess.getCaseId());
    }

    public void cardCaptureImagesResponse(PreProcess preProcess, File file) {
        apiResponse = putRequestForImages(preProcess, file);
        jsonPath = apiResponse.jsonPath();

        preProcess.setSuccess(jsonPath.getBoolean("success"));
        preProcess.setErrorCode(jsonPath.getInt("errorCode"));
        preProcess.setInquiryId(jsonPath.getInt("data.inquiryId"));
        preProcess.setDataSuccess(jsonPath.getBoolean("data.success"));
        preProcess.setNextProcess(jsonPath.getString("data.nextProcess"));
        if (!preProcess.getNextProcess().equalsIgnoreCase("process_ended")) {
            preProcess.setMissingFields(jsonPath.getString("data.missingFields[0]"));
        }
        preProcess.setDataErrorCode(jsonPath.getInt("data.errorCode"));
        preProcess.setDataErrorMessage(jsonPath.getString("data.errorMessage"));
    }

    public void getTokenFromCallbackRequest(PreProcess preProcess) {
        apiResponse = callbackToGetToken(preProcess.getSessionId());
        jsonPath = apiResponse.jsonPath();

        preProcess.setSuccess(jsonPath.getBoolean("success"));
        preProcess.setErrorCode(jsonPath.getInt("errorCode"));
        preProcess.setData(jsonPath.getString("data"));
        preProcess.setToken(preProcess.getData().split("token=")[1]);
    }

    public void getV2Result(PreProcess preProcess, EndpointResults endpointResults) {
        apiResponse = getV2ResultRequest(preProcess.getToken());
        jsonPath = apiResponse.jsonPath();

        endpointResults.setSuccess(jsonPath.getBoolean("success"));
        endpointResults.setErrorCode(jsonPath.getInt("errorCode"));
        if (jsonPath.getString("errorMessage") != null) {
            endpointResults.setErrorMessage(jsonPath.getString("errorMessage"));
        }
        if (jsonPath.getMap("data") != null) {
            endpointResults.setDataSuccess(jsonPath.getBoolean("data.success"));
            endpointResults.setDataErrorMessage(jsonPath.getString("data.errorMessage"));
            endpointResults.setDataErrorCode(jsonPath.getInt("data.errorCode"));
            endpointResults.setMetadata(jsonPath.getString("data.metadata"));
        } else return;

        processHandlerV2(jsonPath);
    }

    public void getV1Result(PreProcess preProcess, EndpointResults endpointResults) {
        apiResponse = getV1ResultRequest(preProcess.getToken());
        jsonPath = apiResponse.jsonPath();

        endpointResults.setSuccess(jsonPath.getBoolean("success"));
        endpointResults.setErrorCode(jsonPath.getInt("errorCode"));
        if (jsonPath.getString("errorMessage") != null) {
            endpointResults.setErrorMessage(jsonPath.getString("errorMessage"));
        }
        if (jsonPath.getMap("data") != null) {
            endpointResults.setDataSuccess(jsonPath.getBoolean("data.success"));
            endpointResults.setDataErrorMessage(jsonPath.getString("data.errorMessage"));
            endpointResults.setDataErrorCode(jsonPath.getInt("data.errorCode"));
            endpointResults.setMetadata(jsonPath.getString("data.metadata"));
        } else return;

        processHandlerV1AndHarel(jsonPath);
    }

    public void getHarelResult(PreProcess preProcess, EndpointResults endpointResults) {
        apiResponse = getV1ResultRequest(preProcess.getToken());
        jsonPath = apiResponse.jsonPath();

        endpointResults.setSuccess(jsonPath.getBoolean("success"));
        endpointResults.setErrorCode(jsonPath.getInt("errorCode"));
        if (jsonPath.getString("errorMessage") != null) {
            endpointResults.setErrorMessage(jsonPath.getString("errorMessage"));
        }
        if (jsonPath.getMap("data") != null) {
            endpointResults.setDataSuccess(jsonPath.getBoolean("data.success"));
            endpointResults.setDataErrorMessage(jsonPath.getString("data.errorMessage"));
            endpointResults.setDataErrorCode(jsonPath.getInt("data.errorCode"));
            endpointResults.setMetadata(jsonPath.getString("data.metadata"));
        } else return;

        processHandlerV1AndHarel(jsonPath);
    }

    @Step("create random string")
    public String randomString() {
        // create a string of all characters
        String alphabet = "abcdefghijkelmonpqrstuvwxyz1234567890";
        // create random string builder
        StringBuilder sb = new StringBuilder();
        // create an object of Random class
        Random random = new Random();
        // specify length of random string
        int length = 8;
        for (int i = 0; i < length; i++) {
            // generate random index number
            int index = random.nextInt(alphabet.length());
            // get character specified by index
            // from the string
            char randomChar = alphabet.charAt(index);
            // append the character to string builder
            sb.append(randomChar);
        }
        return sb.toString();
    }


}
