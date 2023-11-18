package utilities.MongoDB.Variables.Liveness;

import com.jayway.jsonpath.JsonPath;
import io.qameta.allure.Step;

public class OtpVariables extends LivenessHandlers {

    private static int otpNumber;

    public OtpVariables() {
        otpNumber = 0;
    }

//    @Step("Set liveness otp variables")
    public static void setVariables(String jsonResult, int i) {
        try {
            setLivenessHandlerVariables(jsonResult, i);
            if (jsonResult.contains("otp_number")) {
                setOtpNumber(Integer.parseInt(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.otp_number")));
            } else setOtpNumber(0);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

//    @Step("Set liveness variables")
    public static void setLivenessHandlerVariables(String jsonResult, int i) {
        try {
            if (jsonResult.contains("action_type")) {
                setActionType5(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].action_type"));
            }
            if (jsonResult.contains("order")) {
                setOrder5(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].stage.order"));
            }
            if (jsonResult.contains("type")) {
                setTypeUnderStage5(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].stage.type"));
            }
            if (jsonResult.contains("status")) {
                setStageStatus5(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].status"));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //###############################################

    public static int getOtpNumber() {
        return otpNumber;
    }

    public static void setOtpNumber(int otpNumber) {
        OtpVariables.otpNumber = otpNumber;
    }
}
