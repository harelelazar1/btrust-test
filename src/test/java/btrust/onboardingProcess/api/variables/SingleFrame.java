package btrust.onboardingProcess.api.variables;

import io.restassured.path.json.JsonPath;

public class SingleFrame extends EndpointResults {

    private static String process;
    private static String ocrType;
    private static boolean processSuccess;

    public SingleFrame() {
        process = null;
        ocrType = null;
        processSuccess = false;
    }

    public void setSingleFrameVariables(JsonPath jsonPath, int i) {
        setProcess(jsonPath.getString("data.resultsList[" + i + "].process"));
        setOcrType(jsonPath.getString("data.resultsList[" + i + "].ocrType"));
        setProcessSuccess(jsonPath.getBoolean("data.resultsList[" + i + "].success"));
    }

    //#############################################################################


    public static String getProcess() {
        return process;
    }

    public static void setProcess(String process) {
        SingleFrame.process = process;
    }

    public static String getOcrType() {
        return ocrType;
    }

    public static void setOcrType(String ocrType) {
        SingleFrame.ocrType = ocrType;
    }

    public static boolean isProcessSuccess() {
        return processSuccess;
    }

    public static void setProcessSuccess(boolean processSuccess) {
        SingleFrame.processSuccess = processSuccess;
    }
}
