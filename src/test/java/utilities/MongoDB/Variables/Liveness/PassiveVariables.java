package utilities.MongoDB.Variables.Liveness;

import com.jayway.jsonpath.JsonPath;
import io.qameta.allure.Step;

public class PassiveVariables extends LivenessHandlers {

    private static double threshold;
    private static double score;
    private static String faceImage;
    private static String inputImage;
    private static String headPositionType;
    private static boolean headPositionExpected;

    public PassiveVariables() {
        threshold = 0;
        score = 0;
        faceImage = null;
        inputImage = null;
        headPositionType = null;
        headPositionExpected = false;
    }

//    @Step("Set liveness passive variables")
    public static void setVariables(String jsonResult, int i) {
        try {
            setLivenessHandlerVariables(jsonResult, i);
            if (jsonResult.contains("threshold")) {
                setThreshold(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.threshold"));
            }
            if (jsonResult.contains("score")) {
                setScore(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.score"));
            }
            if (jsonResult.contains("face_image")) {
                setFaceImage(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.images.face_image"));
            }
            if (jsonResult.contains("input_image")) {
                setInputImage(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.images.input_image"));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

//    @Step("Set liveness variables")
    public static void setLivenessHandlerVariables(String jsonResult, int i) {
        try {
            if (jsonResult.contains("action_type")) {
                setActionType2(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].action_type"));
            }
            if (jsonResult.contains("order")) {
                setOrder2(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].stage.order"));
            }
            if (jsonResult.contains("type")) {
                setTypeUnderStage2(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].stage.type"));
            }
            if (jsonResult.contains("status")) {
                setStageStatus2(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].status"));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

//    @Step("Set liveness passive head position variables")
    public static void setHeadPositions(String jsonResult, int stagesArrayIndex, int headPositionsArrayIndex) {
        if (jsonResult.contains("head_position_changes")) {
            setHeadPositionType(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + stagesArrayIndex + "].payload.head_position_changes[" + (headPositionsArrayIndex - 1) + "].type"));
            setHeadPositionExpected(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + stagesArrayIndex + "].payload.head_position_changes[" + (headPositionsArrayIndex - 1) + "].expected"));
        }
    }

    //###############################################

    public static boolean isHeadPositionExpected() {
        return headPositionExpected;
    }

    public static void setHeadPositionExpected(boolean headPositionExpected) {
        PassiveVariables.headPositionExpected = headPositionExpected;
    }

    public static String getHeadPositionType() {
        return headPositionType;
    }

    public static void setHeadPositionType(String headPositionType) {
        PassiveVariables.headPositionType = headPositionType;
    }

    public static double getThreshold() {
        return threshold;
    }

    public static void setThreshold(double threshold) {
        PassiveVariables.threshold = threshold;
    }

    public static double getScore() {
        System.out.println(score);
        return score;
    }

    public static void setScore(double score) {
        PassiveVariables.score = score;
    }

    public static String getFaceImage() {
        return faceImage;
    }

    public static void setFaceImage(String faceImage) {
        PassiveVariables.faceImage = faceImage;
    }

    public static String getInputImage() {
        return inputImage;
    }

    public static void setInputImage(String inputImage) {
        PassiveVariables.inputImage = inputImage;
    }
}
