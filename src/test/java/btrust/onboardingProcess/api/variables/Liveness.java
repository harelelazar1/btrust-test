package btrust.onboardingProcess.api.variables;

import io.restassured.path.json.JsonPath;

public class Liveness extends EndpointResults {

    private static String process;
    private static String faceImage;
    private static String video;
    private static boolean processSuccess;
    private static String sttText;
    private static String originalText;
    private static String language;
    private static float sttScore;
    private static float sttThreshold;

    public Liveness() {
        process = null;
        faceImage = null;
        video = null;
        processSuccess = false;
        sttText = null;
        originalText = null;
        language = null;
        sttScore = 0;
        sttThreshold = 0;
    }

    public void setLivenessVariables(JsonPath jsonPath) {
        setLivenessVariables(jsonPath, "");
    }

    public void setLivenessVariables(JsonPath jsonPath, String i) {
        String path;
        if (i.equalsIgnoreCase("")) {
            path = "data.results.liveness";
        } else path = "data.resultsList[" + i + "]";

        setProcess(jsonPath.getString(path + ".process"));
        setFaceImage(jsonPath.getString(path + ".faceImage"));
        setVideo(jsonPath.getString(path + ".video"));
        setProcessSuccess(jsonPath.getBoolean(path + ".success"));

        if (jsonPath.getMap(path).containsKey("sttText")) {
            setSttText(jsonPath.getString(path + ".sttText"));
            setOriginalText(jsonPath.getString(path + ".originalText"));
            setLanguage(jsonPath.getString(path + ".language"));
            setSttScore(jsonPath.getFloat(path + ".sttScore"));
            setSttThreshold(jsonPath.getFloat(path + ".sttThreshold"));
        }
    }

    //#############################################################################


    public static String getSttText() {
        return sttText;
    }

    public static void setSttText(String sttText) {
        Liveness.sttText = sttText;
    }

    public static String getOriginalText() {
        return originalText;
    }

    public static void setOriginalText(String originalText) {
        Liveness.originalText = originalText;
    }

    public static String getLanguage() {
        return language;
    }

    public static void setLanguage(String language) {
        Liveness.language = language;
    }

    public static float getSttScore() {
        return sttScore;
    }

    public static void setSttScore(float sttScore) {
        Liveness.sttScore = sttScore;
    }

    public static float getSttThreshold() {
        return sttThreshold;
    }

    public static void setSttThreshold(float sttThreshold) {
        Liveness.sttThreshold = sttThreshold;
    }

    public static String getProcess() {
        return process;
    }

    public static void setProcess(String process) {
        Liveness.process = process;
    }

    public static String getFaceImage() {
        return faceImage;
    }

    public static void setFaceImage(String faceImage) {
        Liveness.faceImage = faceImage;
    }

    public static String getVideo() {
        return video;
    }

    public static void setVideo(String video) {
        Liveness.video = video;
    }

    public static boolean isProcessSuccess() {
        return processSuccess;
    }

    public static void setProcessSuccess(boolean processSuccess) {
        Liveness.processSuccess = processSuccess;
    }
}
