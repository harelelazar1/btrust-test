package btrust.onboardingProcess.api.variables;

import io.restassured.path.json.JsonPath;

public class Questionnaire extends EndpointResults {

    private static String process;
    private static String uploadImage;
    private static boolean processSuccess;

    public Questionnaire() {
        process = null;
        uploadImage = null;
        processSuccess = false;
    }

    public void setQuestionnaireVariables(JsonPath jsonPath) {
        setQuestionnaireVariables(jsonPath, "");
    }

    public void setQuestionnaireVariables(JsonPath jsonPath, String i) {
        String path;
        if (i.equalsIgnoreCase("")) {
            path = "data.results.questionnaire";
        } else path = "data.resultsList[" + i + "]";

        setProcess(jsonPath.getString(path + ".process"));
        setUploadImage(jsonPath.getString(path + ".data[\"upload Image\"]"));
        if (jsonPath.getMap(path).containsKey("success"))
            setProcessSuccess(jsonPath.getBoolean(path + ".success"));
    }

    //#############################################################################

    public static String getProcess() {
        return process;
    }

    public static void setProcess(String process) {
        Questionnaire.process = process;
    }

    public static String getUploadImage() {
        return uploadImage;
    }

    public static void setUploadImage(String uploadImage) {
        Questionnaire.uploadImage = uploadImage;
    }

    public static boolean isProcessSuccess() {
        return processSuccess;
    }

    public static void setProcessSuccess(boolean processSuccess) {
        Questionnaire.processSuccess = processSuccess;
    }
}