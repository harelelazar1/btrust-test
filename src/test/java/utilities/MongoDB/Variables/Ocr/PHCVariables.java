package utilities.MongoDB.Variables.Ocr;

import com.jayway.jsonpath.JsonPath;
import io.qameta.allure.Step;

public class PHCVariables extends OcrHandlers {

    private static String chequeNumber;
    private static String inputImage;

    public PHCVariables() {
        chequeNumber = null;
        inputImage = null;
    }

//    @Step("Set front side values")
    public static void setVariables(String jsonResult, int i) {
        try {
            OcrHandlers.setOcrFrontSideVariables(jsonResult, i);
            if (jsonResult.contains("cheque_number")) {
                setChequeNumber(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.fields.cheque_number"));
            } else setChequeNumber(null);
            if (jsonResult.contains("input_image")) {
                setInputImage(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.images.input_image"));
            } else setInputImage(null);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //###############################################

    public static String getChequeNumber() {
        return chequeNumber;
    }

    public static void setChequeNumber(String chequeNumber) {
        PHCVariables.chequeNumber = chequeNumber;
    }

    public static String getInputImage() {
        return inputImage;
    }

    public static void setInputImage(String inputImage) {
        PHCVariables.inputImage = inputImage;
    }
}
