package utilities.MongoDB.Variables.Liveness;

import com.jayway.jsonpath.JsonPath;
import io.qameta.allure.Step;
import utilities.MongoDB.MongoHandler;
import utilities.MongoDB.Variables.CommonVariables;

public class LivenessHandlers {

    private static String actionType;
    private static String actionType2;
    private static String actionType3;
    private static String actionType4;
    private static String actionType5;
    private static String actionType6;
    private static String actionType7;
    private static String actionType8;
    private static int order;
    private static int order2;
    private static int order3;
    private static int order4;
    private static int order5;
    private static int order6;
    private static int order7;
    private static int order8;
    private static String typeUnderStage;
    private static String typeUnderStage2;
    private static String typeUnderStage3;
    private static String typeUnderStage4;
    private static String typeUnderStage5;
    private static String typeUnderStage6;
    private static String typeUnderStage7;
    private static String typeUnderStage8;
    private static String stageStatus;
    private static String stageStatus2;
    private static String stageStatus3;
    private static String stageStatus4;
    private static String stageStatus5;
    private static String stageStatus6;
    private static String stageStatus7;
    private static String stageStatus8;

    //Timeout variables
    private static String actionTypeTimeout;
    private static int orderTimeout;
    private static String typeUnderStageTimeout;
    private static String stageStatusTimeout;
    private static String lastReceivedImage;

    public LivenessHandlers() {
        actionType = null;
        actionType2 = null;
        actionType3 = null;
        actionType4 = null;
        actionType5 = null;
        actionType6 = null;
        actionType7 = null;
        actionType8 = null;
        order = 0;
        order2 = 0;
        order3 = 0;
        order4 = 0;
        order5 = 0;
        order6 = 0;
        order7 = 0;
        order8 = 0;
        typeUnderStage = null;
        typeUnderStage2 = null;
        typeUnderStage3 = null;
        typeUnderStage4 = null;
        typeUnderStage5 = null;
        typeUnderStage6 = null;
        typeUnderStage7 = null;
        typeUnderStage8 = null;
        stageStatus = null;
        stageStatus2 = null;
        stageStatus3 = null;
        stageStatus4 = null;
        stageStatus5 = null;
        stageStatus6 = null;
        stageStatus7 = null;
        stageStatus8 = null;
        actionTypeTimeout = null;
        orderTimeout = 0;
        typeUnderStageTimeout = null;
        stageStatusTimeout = null;
        lastReceivedImage = null;
    }

//    @Step("Handler that check which liveness side is in progress now")
    public static void LivenessHandler(String jsonResult, int stagesArraySize) {
        System.out.println("Size: " + stagesArraySize);
        for (int i = 0; i < stagesArraySize; i++) {
            System.out.println(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].stage.type").toString());
            System.out.println(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].status").toString());
            if (JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].status").toString().equalsIgnoreCase("timeout")) {
                setTimeoutVariables(jsonResult, i);
                return;
            }
            switch (JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].stage.type").toString()) {
                case "passive": {
                    PassiveVariables.setVariables(jsonResult, i);
                    PassiveVariables.setHeadPositions(jsonResult, i, MongoHandler.getHeadPositionChangesArraySize(CommonVariables.getCaseId(), CommonVariables.getStatus(), i));
                    break;
                }
                case "otp": {
                    OtpVariables.setVariables(jsonResult, i);
                    break;
                }
                case "stt": {
                    SttVariables.setVariables(jsonResult, i);
                    break;
                }
                case "right":
                case "center":
                case "left": {
                    LeftRightCenterVariables.setVariables(jsonResult, i);
                    LeftRightCenterVariables.setHeadPositions(jsonResult, i, MongoHandler.getHeadPositionChangesArraySize(CommonVariables.getCaseId(), CommonVariables.getStatus(), i));
                    break;
                }
                case "gestures": {
                    GesturesVariables.setGesturesVariables(jsonResult, i);
                }
            }
        }
    }

//    @Step("Set liveness variables")
    public static void setLivenessHandlerVariables(String jsonResult, int i) {
        try {
            if (jsonResult.contains("action_type")) {
                setActionType(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].action_type"));
            }
            if (jsonResult.contains("order")) {
                setOrder(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].stage.order"));
            }
            if (jsonResult.contains("type")) {
                setTypeUnderStage(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].stage.type"));
            }
            if (jsonResult.contains("status")) {
                setStageStatus(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].status"));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

//    @Step("Set liveness timeout variables")
    public static void setTimeoutVariables(String jsonResult, int i) {
        try {
            if (jsonResult.contains("action_type")) {
                setActionTypeTimeout(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].action_type"));
            }
            if (jsonResult.contains("order")) {
                setOrderTimeout(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].stage.order"));
            }
            if (jsonResult.contains("type")) {
                setTypeUnderStageTimeout(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].stage.type"));
            }
            if (jsonResult.contains("status")) {
                setStageStatusTimeout(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].status"));
            }
            if (jsonResult.contains("last_received_image")) {
                setLastReceivedImage(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.images.last_received_image"));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

//###############################################

    public static String getActionType8() {
        return actionType8;
    }

    public static void setActionType8(String actionType8) {
        LivenessHandlers.actionType8 = actionType8;
    }

    public static int getOrder8() {
        return order8;
    }

    public static void setOrder8(int order8) {
        LivenessHandlers.order8 = order8;
    }

    public static String getTypeUnderStage8() {
        return typeUnderStage8;
    }

    public static void setTypeUnderStage8(String typeUnderStage8) {
        LivenessHandlers.typeUnderStage8 = typeUnderStage8;
    }

    public static String getStageStatus8() {
        return stageStatus8;
    }

    public static void setStageStatus8(String stageStatus8) {
        LivenessHandlers.stageStatus8 = stageStatus8;
    }

    public static String getActionType7() {
        return actionType7;
    }

    public static void setActionType7(String actionType7) {
        LivenessHandlers.actionType7 = actionType7;
    }

    public static int getOrder7() {
        return order7;
    }

    public static void setOrder7(int order7) {
        LivenessHandlers.order7 = order7;
    }

    public static String getTypeUnderStage7() {
        return typeUnderStage7;
    }

    public static void setTypeUnderStage7(String typeUnderStage7) {
        LivenessHandlers.typeUnderStage7 = typeUnderStage7;
    }

    public static String getStageStatus7() {
        return stageStatus7;
    }

    public static void setStageStatus7(String stageStatus7) {
        LivenessHandlers.stageStatus7 = stageStatus7;
    }

    public static String getActionType() {
        return actionType;
    }

    public static void setActionType(String actionType) {
        LivenessHandlers.actionType = actionType;
    }

    public static String getActionType2() {
        return actionType2;
    }

    public static void setActionType2(String actionType2) {
        LivenessHandlers.actionType2 = actionType2;
    }

    public static String getActionType3() {
        return actionType3;
    }

    public static void setActionType3(String actionType3) {
        LivenessHandlers.actionType3 = actionType3;
    }

    public static String getActionType4() {
        return actionType4;
    }

    public static void setActionType4(String actionType4) {
        LivenessHandlers.actionType4 = actionType4;
    }

    public static String getActionType5() {
        return actionType5;
    }

    public static void setActionType5(String actionType5) {
        LivenessHandlers.actionType5 = actionType5;
    }

    public static String getActionType6() {
        return actionType6;
    }

    public static void setActionType6(String actionType6) {
        LivenessHandlers.actionType6 = actionType6;
    }

    public static int getOrder() {
        return order;
    }

    public static void setOrder(int order) {
        LivenessHandlers.order = order;
    }

    public static int getOrder2() {
        return order2;
    }

    public static void setOrder2(int order2) {
        LivenessHandlers.order2 = order2;
    }

    public static int getOrder3() {
        return order3;
    }

    public static void setOrder3(int order3) {
        LivenessHandlers.order3 = order3;
    }

    public static int getOrder4() {
        return order4;
    }

    public static void setOrder4(int order4) {
        LivenessHandlers.order4 = order4;
    }

    public static int getOrder5() {
        return order5;
    }

    public static void setOrder5(int order5) {
        LivenessHandlers.order5 = order5;
    }

    public static int getOrder6() {
        return order6;
    }

    public static void setOrder6(int order6) {
        LivenessHandlers.order6 = order6;
    }

    public static String getTypeUnderStage() {
        return typeUnderStage;
    }

    public static void setTypeUnderStage(String typeUnderStage) {
        LivenessHandlers.typeUnderStage = typeUnderStage;
    }

    public static String getTypeUnderStage2() {
        return typeUnderStage2;
    }

    public static void setTypeUnderStage2(String typeUnderStage2) {
        LivenessHandlers.typeUnderStage2 = typeUnderStage2;
    }

    public static String getTypeUnderStage3() {
        return typeUnderStage3;
    }

    public static void setTypeUnderStage3(String typeUnderStage3) {
        LivenessHandlers.typeUnderStage3 = typeUnderStage3;
    }

    public static String getTypeUnderStage4() {
        return typeUnderStage4;
    }

    public static void setTypeUnderStage4(String typeUnderStage4) {
        LivenessHandlers.typeUnderStage4 = typeUnderStage4;
    }

    public static String getTypeUnderStage5() {
        return typeUnderStage5;
    }

    public static void setTypeUnderStage5(String typeUnderStage5) {
        LivenessHandlers.typeUnderStage5 = typeUnderStage5;
    }

    public static String getTypeUnderStage6() {
        return typeUnderStage6;
    }

    public static void setTypeUnderStage6(String typeUnderStage6) {
        LivenessHandlers.typeUnderStage6 = typeUnderStage6;
    }

    public static String getStageStatus() {
        return stageStatus;
    }

    public static void setStageStatus(String stageStatus) {
        LivenessHandlers.stageStatus = stageStatus;
    }

    public static String getStageStatus2() {
        return stageStatus2;
    }

    public static void setStageStatus2(String stageStatus2) {
        LivenessHandlers.stageStatus2 = stageStatus2;
    }

    public static String getStageStatus3() {
        return stageStatus3;
    }

    public static void setStageStatus3(String stageStatus3) {
        LivenessHandlers.stageStatus3 = stageStatus3;
    }

    public static String getStageStatus4() {
        return stageStatus4;
    }

    public static void setStageStatus4(String stageStatus4) {
        LivenessHandlers.stageStatus4 = stageStatus4;
    }

    public static String getStageStatus5() {
        return stageStatus5;
    }

    public static void setStageStatus5(String stageStatus5) {
        LivenessHandlers.stageStatus5 = stageStatus5;
    }

    public static String getStageStatus6() {
        return stageStatus6;
    }

    public static void setStageStatus6(String stageStatus6) {
        LivenessHandlers.stageStatus6 = stageStatus6;
    }

    public static String getActionTypeTimeout() {
        return actionTypeTimeout;
    }

    public static void setActionTypeTimeout(String actionTypeTimeout) {
        LivenessHandlers.actionTypeTimeout = actionTypeTimeout;
    }

    public static int getOrderTimeout() {
        return orderTimeout;
    }

    public static void setOrderTimeout(int orderTimeout) {
        LivenessHandlers.orderTimeout = orderTimeout;
    }

    public static String getTypeUnderStageTimeout() {
        return typeUnderStageTimeout;
    }

    public static void setTypeUnderStageTimeout(String typeUnderStageTimeout) {
        LivenessHandlers.typeUnderStageTimeout = typeUnderStageTimeout;
    }

    public static String getStageStatusTimeout() {
        return stageStatusTimeout;
    }

    public static void setStageStatusTimeout(String stageStatusTimeout) {
        LivenessHandlers.stageStatusTimeout = stageStatusTimeout;
    }

    public static String getLastReceivedImage() {
        return lastReceivedImage;
    }

    public static void setLastReceivedImage(String lastReceivedImage) {
        LivenessHandlers.lastReceivedImage = lastReceivedImage;
    }
}
