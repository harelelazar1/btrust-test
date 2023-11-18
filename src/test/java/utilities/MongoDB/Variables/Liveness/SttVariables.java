package utilities.MongoDB.Variables.Liveness;

import com.jayway.jsonpath.JsonPath;
import io.qameta.allure.Step;

public class SttVariables extends LivenessHandlers {

    private static String sttText;
    private static String originalText;
    private static double threshold;
    private static String language;
    private static double score;

    public SttVariables() {
        sttText = null;
        originalText = null;
        threshold = 0;
        language = null;
        score = 0;
    }

//    @Step("Set liveness stt variables")
    public static void setVariables(String jsonResult, int i) {
        try {
            setLivenessHandlerVariables(jsonResult, i);
            if (jsonResult.contains("stt_text")) {
                setSttText(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.stt_text"));
            }
            if (jsonResult.contains("original_text")) {
                setOriginalText(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.original_text"));
            }
            if (jsonResult.contains("threshold")) {
                setThreshold(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.threshold"));
            }
            if (jsonResult.contains("language")) {
                setLanguage(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.language"));
            }
            if (jsonResult.contains("score")) {
                setScore(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.score"));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

//    @Step("Set liveness variables")
    public static void setLivenessHandlerVariables(String jsonResult, int i) {
        try {
            if (jsonResult.contains("action_type")) {
                setActionType6(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].action_type"));
            }
            if (jsonResult.contains("order")) {
                setOrder6(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].stage.order"));
            }
            if (jsonResult.contains("type")) {
                setTypeUnderStage6(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].stage.type"));
            }
            if (jsonResult.contains("status")) {
                setStageStatus6(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].status"));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //###############################################

    public static String getSttText() {
        return sttText;
    }

    public static void setSttText(String sttText) {
        SttVariables.sttText = sttText;
    }

    public static String getOriginalText() {
        return originalText;
    }

    public static void setOriginalText(String originalText) {
        SttVariables.originalText = originalText;
    }

    public static double getThreshold() {
        return threshold;
    }

    public static void setThreshold(double threshold) {
        SttVariables.threshold = threshold;
    }

    public static String getLanguage() {
        return language;
    }

    public static void setLanguage(String language) {
        SttVariables.language = language;
    }

    public static double getScore() {
        return score;
    }

    public static void setScore(double score) {
        SttVariables.score = score;
    }
}
