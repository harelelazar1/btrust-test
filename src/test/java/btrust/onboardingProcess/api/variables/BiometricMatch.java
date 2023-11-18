package btrust.onboardingProcess.api.variables;

import io.restassured.path.json.JsonPath;

public class BiometricMatch extends EndpointResults {

    private static String process;
    private static boolean processSuccess;
    private static float score;

    public BiometricMatch() {
        process = null;
        processSuccess = false;
        score = 0;
    }

    public void setBiometricMatchVariables(JsonPath jsonPath) {
        setBiometricMatchVariables(jsonPath, "");
    }

    public void setBiometricMatchVariables(JsonPath jsonPath, String i) {
        String path;
        if (i.equalsIgnoreCase("")) {
            path = "data.results.face";
        } else path = "data.resultsList[" + i + "]";

        setProcess(jsonPath.getString(path + ".process"));
        setProcessSuccess(jsonPath.getBoolean(path + ".success"));
        setScore(jsonPath.getFloat(path + ".score"));
    }

    //#############################################################################

    public static String getProcess() {
        return process;
    }

    public static void setProcess(String process) {
        BiometricMatch.process = process;
    }

    public static boolean isProcessSuccess() {
        return processSuccess;
    }

    public static void setProcessSuccess(boolean processSuccess) {
        BiometricMatch.processSuccess = processSuccess;
    }

    public static float getScore() {
        return score;
    }

    public static void setScore(float score) {
        BiometricMatch.score = score;
    }
}