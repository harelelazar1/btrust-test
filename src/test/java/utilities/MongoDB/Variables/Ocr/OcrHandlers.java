package utilities.MongoDB.Variables.Ocr;

import com.jayway.jsonpath.JsonPath;
import io.qameta.allure.Step;
import utilities.MongoDB.Variables.CommonVariables;

public class OcrHandlers {

    private static String actionType;
    private static String actionType2;
    private static String actionType3;
    private static String actionType4;
    private static String actionType5;
    private static String actionType6;
    private static int order;
    private static int order2;
    private static int order3;
    private static int order4;
    private static int order5;
    private static int order6;
    private static String typeUnderStage;
    private static String typeUnderStage2;
    private static String typeUnderStage3;
    private static String typeUnderStage4;
    private static String typeUnderStage5;
    private static String typeUnderStage6;
    private static String stageStatus;
    private static String stageStatus2;
    private static String stageStatus3;
    private static String stageStatus4;
    private static String stageStatus5;
    private static String stageStatus6;

    //Timeout variables
    private static String actionTypeTimeout;
    private static int orderTimeout;
    private static String typeUnderStageTimeout;
    private static String stageStatusTimeout;
    private static String lastReceivedImage;

    public OcrHandlers() {
        actionType = null;
        actionType2 = null;
        actionType3 = null;
        actionType4 = null;
        actionType5 = null;
        actionType6 = null;
        order = 0;
        order2 = 0;
        order3 = 0;
        order4 = 0;
        order5 = 0;
        order6 = 0;
        typeUnderStage = null;
        typeUnderStage2 = null;
        typeUnderStage3 = null;
        typeUnderStage4 = null;
        typeUnderStage5 = null;
        typeUnderStage6 = null;
        stageStatus = null;
        stageStatus2 = null;
        stageStatus3 = null;
        stageStatus4 = null;
        stageStatus5 = null;
        stageStatus6 = null;
        actionTypeTimeout = null;
        orderTimeout = 0;
        typeUnderStageTimeout = null;
        stageStatusTimeout = null;
        lastReceivedImage = null;
    }

//    @Step("IL-ID handler that check which kind of IL-ID is in progress now")
    public static void ILIDHandler(String jsonResult, int stagesArraySize) {
        System.out.println("Size: " + stagesArraySize);
        for (int i = 0; i < stagesArraySize; i++) {
            if (JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].status").toString().equalsIgnoreCase("timeout")) {
                setTimeoutVariables(jsonResult, i);
                return;
            }
            switch (JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.card_type").toString()) {
                case "blue": {
                    ILIDVariables.setBlueIdVariables(jsonResult, i);
                    break;
                }
                case "green": {
                    ILIDVariables.setGreenIdVariables(jsonResult, i);
                    break;
                }
                case "bio_front": {
                    ILIDVariables.setBioIdFrontVariables(jsonResult, i);
                    break;
                }
                case "bio_back": {
                    ILIDVariables.setBioIdBackVariables(jsonResult, i);
                    break;
                }
            }
        }
    }

//    @Step("IL-DL handler that check which kind of IL-DL is in progress now")
    public static void ILDLHandler(String jsonResult, int stagesArraySize) {
        System.out.println("Size: " + stagesArraySize);
        for (int i = 0; i < stagesArraySize; i++) {
            if (JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].status").toString().equalsIgnoreCase("timeout")) {
                setTimeoutVariables(jsonResult, i);
                return;
            }
            System.out.println("Card type: " + JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.card_type").toString());
            switch (JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.card_type").toString()) {
                case "new_front":
                case "old_front": {
                    ILDLVariables.setFrontSideVariables(jsonResult, i);
                    break;
                }
                case "new_back":
                case "old_back": {
                    ILDLVariables.setBackSideVariables(jsonResult, i);
                    break;
                }
            }
        }
    }

//    @Step("CNI handler that check which kind of CNI is in progress now")
    public static void CNIHandler(String jsonResult, int stagesArraySize) {
        System.out.println("Size: " + stagesArraySize);
        for (int i = 0; i < stagesArraySize; i++) {
            if (JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].status").toString().equalsIgnoreCase("timeout")) {
                setTimeoutVariables(jsonResult, i);
                return;
            }
            System.out.println("Stage type: " + JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].stage.type").toString());
            System.out.println("Stage status: " + JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].status").toString());
            switch (JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].stage.type").toString()) {
                case "front": {
                    CNIVariables.setFrontSideVariables(jsonResult, i);
                    break;
                }
                case "back": {
                    CNIVariables.setBackSideVariables(jsonResult, i);
                    break;
                }
                case "video": {
                    CNIVariables.setVideoVariables(jsonResult, i);
                    break;
                }
                case "otp": {
                    CNIVariables.setOtpVariables(jsonResult, i);
                    break;
                }
                case "gestures": {
                    CNIVariables.setGesturesVariables(jsonResult, i);
                    break;
                }
            }
        }
    }

//    @Step("Set front side values")
    public static void setOcrFrontSideVariables(String jsonResult, int i) {
        try {
            if (JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].status").toString().equalsIgnoreCase("timeout")) {
                setTimeoutVariables(jsonResult, i);
            } else {
                if (jsonResult.contains("action_type")) {
                    setActionType(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].action_type"));
                } else setActionType(null);
                if (jsonResult.contains("order")) {
                    setOrder(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].stage.order"));
                } else setOrder(0);
                if (jsonResult.contains("type")) {
                    setTypeUnderStage(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].stage.type"));
                } else setTypeUnderStage(null);
                if (jsonResult.contains("status")) {
                    setStageStatus(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].status"));
                } else setStageStatus(null);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

//    @Step("Set back side values")
    public static void setOcrBackSideVariables(String jsonResult, int i) {
        try {
            if (jsonResult.contains("action_type")) {
                setActionType2(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].action_type"));
            } else setActionType2(null);
            if (jsonResult.contains("order")) {
                setOrder2(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].stage.order"));
            } else setOrder2(0);
            if (jsonResult.contains("type")) {
                setTypeUnderStage2(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].stage.type"));
            } else setTypeUnderStage2(null);
            if (jsonResult.contains("status")) {
                setStageStatus2(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].status"));
            } else setStageStatus2(null);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

//    @Step("Set timeout values")
    public static void setTimeoutVariables(String jsonResult, int i) {
        try {
            if (jsonResult.contains("action_type")) {
                setActionTypeTimeout(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].action_type"));
            } else setActionTypeTimeout(null);
            if (jsonResult.contains("order")) {
                setOrderTimeout(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].stage.order"));
            } else setOrderTimeout(0);
            if (jsonResult.contains("type")) {
                setTypeUnderStageTimeout(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].stage.type"));
            } else setTypeUnderStageTimeout(null);
            if (jsonResult.contains("status")) {
                setStageStatusTimeout(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].status"));
            } else setStageStatusTimeout(null);
            if (jsonResult.contains("last_received_image")) {
                setLastReceivedImage(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.images.last_received_image"));
            } else setLastReceivedImage(null);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void CaptureHandler(String jsonResult, int stagesArraySize) {
        for (int i = 0; i < stagesArraySize; i++) {
            switch (JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].stage.type").toString()) {
                case "front": {
                    CaptureVariables.setFrontCaptureVariables(jsonResult, i);
                    break;
                }
                case "back": {
                    CaptureVariables.setBackCaptureVariables(jsonResult, i);
                    break;
                }
            }
        }
    }

    //###############################################


    public static String getActionType6() {
        return actionType6;
    }

    public static void setActionType6(String actionType6) {
        OcrHandlers.actionType6 = actionType6;
    }

    public static int getOrder6() {
        return order6;
    }

    public static void setOrder6(int order6) {
        OcrHandlers.order6 = order6;
    }

    public static String getTypeUnderStage6() {
        return typeUnderStage6;
    }

    public static void setTypeUnderStage6(String typeUnderStage6) {
        OcrHandlers.typeUnderStage6 = typeUnderStage6;
    }

    public static String getStageStatus6() {
        return stageStatus6;
    }

    public static void setStageStatus6(String stageStatus6) {
        OcrHandlers.stageStatus6 = stageStatus6;
    }

    public static String getLastReceivedImage() {
        return lastReceivedImage;
    }

    public static void setLastReceivedImage(String lastReceivedImage) {
        OcrHandlers.lastReceivedImage = lastReceivedImage;
    }

    public static String getActionTypeTimeout() {
        return actionTypeTimeout;
    }

    public static void setActionTypeTimeout(String actionTypeTimeout) {
        OcrHandlers.actionTypeTimeout = actionTypeTimeout;
    }

    public static int getOrderTimeout() {
        return orderTimeout;
    }

    public static void setOrderTimeout(int orderTimeout) {
        OcrHandlers.orderTimeout = orderTimeout;
    }

    public static String getTypeUnderStageTimeout() {
        return typeUnderStageTimeout;
    }

    public static void setTypeUnderStageTimeout(String typeUnderStageTimeout) {
        OcrHandlers.typeUnderStageTimeout = typeUnderStageTimeout;
    }

    public static String getStageStatusTimeout() {
        return stageStatusTimeout;
    }

    public static void setStageStatusTimeout(String stageStatusTimeout) {
        OcrHandlers.stageStatusTimeout = stageStatusTimeout;
    }

    public static String getActionType2() {
        return actionType2;
    }

    public static void setActionType2(String actionType2) {
        OcrHandlers.actionType2 = actionType2;
    }

    public static int getOrder2() {
        return order2;
    }

    public static void setOrder2(int order2) {
        OcrHandlers.order2 = order2;
    }

    public static String getTypeUnderStage2() {
        return typeUnderStage2;
    }

    public static void setTypeUnderStage2(String typeUnderStage2) {
        OcrHandlers.typeUnderStage2 = typeUnderStage2;
    }

    public static String getStageStatus2() {
        return stageStatus2;
    }

    public static void setStageStatus2(String stageStatus2) {
        OcrHandlers.stageStatus2 = stageStatus2;
    }

    public static String getActionType() {
        return actionType;
    }

    public static void setActionType(String actionType) {
        OcrHandlers.actionType = actionType;
    }

    public static int getOrder() {
        return order;
    }

    public static void setOrder(int order) {
        OcrHandlers.order = order;
    }

    public static String getTypeUnderStage() {
        return typeUnderStage;
    }

    public static void setTypeUnderStage(String typeUnderStage) {
        OcrHandlers.typeUnderStage = typeUnderStage;
    }

    public static String getStageStatus() {
        return stageStatus;
    }

    public static void setStageStatus(String stageStatus) {
        OcrHandlers.stageStatus = stageStatus;
    }

    public static String getStageStatus3() {
        return stageStatus3;
    }

    public static void setStageStatus3(String stageStatus3) {
        OcrHandlers.stageStatus3 = stageStatus3;
    }

    public static String getStageStatus4() {
        return stageStatus4;
    }

    public static void setStageStatus4(String stageStatus4) {
        OcrHandlers.stageStatus4 = stageStatus4;
    }

    public static String getActionType3() {
        return actionType3;
    }

    public static void setActionType3(String actionType3) {
        OcrHandlers.actionType3 = actionType3;
    }

    public static String getActionType4() {
        return actionType4;
    }

    public static void setActionType4(String actionType4) {
        OcrHandlers.actionType4 = actionType4;
    }

    public static int getOrder3() {
        return order3;
    }

    public static void setOrder3(int order3) {
        OcrHandlers.order3 = order3;
    }

    public static int getOrder4() {
        return order4;
    }

    public static void setOrder4(int order4) {
        OcrHandlers.order4 = order4;
    }

    public static String getTypeUnderStage3() {
        return typeUnderStage3;
    }

    public static void setTypeUnderStage3(String typeUnderStage3) {
        OcrHandlers.typeUnderStage3 = typeUnderStage3;
    }

    public static String getTypeUnderStage4() {
        return typeUnderStage4;
    }

    public static void setTypeUnderStage4(String typeUnderStage4) {
        OcrHandlers.typeUnderStage4 = typeUnderStage4;
    }

    public static String getActionType5() {
        return actionType5;
    }

    public static void setActionType5(String actionType5) {
        OcrHandlers.actionType5 = actionType5;
    }

    public static int getOrder5() {
        return order5;
    }

    public static void setOrder5(int order5) {
        OcrHandlers.order5 = order5;
    }

    public static String getTypeUnderStage5() {
        return typeUnderStage5;
    }

    public static void setTypeUnderStage5(String typeUnderStage5) {
        OcrHandlers.typeUnderStage5 = typeUnderStage5;
    }

    public static String getStageStatus5() {
        return stageStatus5;
    }

    public static void setStageStatus5(String stageStatus5) {
        OcrHandlers.stageStatus5 = stageStatus5;
    }
}
