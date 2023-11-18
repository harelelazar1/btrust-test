package utilities.MongoDB.Variables.Ocr;

import com.jayway.jsonpath.JsonPath;

public class CaptureVariables extends OcrHandlers{

    private static String frontInputImage;
    private static String backInputImage;

    public CaptureVariables() {
        frontInputImage = null;
        backInputImage = null;
    }

    public static void setFrontCaptureVariables(String jsonResult, int i) {
        try {
            setOcrFrontSideVariables(jsonResult, i);
            if (jsonResult.contains("input_image")) {
                setFrontInputImage(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.images.input_image"));
            }
        }catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void setBackCaptureVariables(String jsonResult, int i) {
        try {
            setOcrBackSideVariables(jsonResult, i);
            if (jsonResult.contains("input_image")) {
                setBackInputImage(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.images.input_image"));
            }
        }catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //###############################################

    public static String getFrontInputImage() {
        return frontInputImage;
    }

    public static void setFrontInputImage(String frontInputImage) {
        CaptureVariables.frontInputImage = frontInputImage;
    }

    public static String getBackInputImage() {
        return backInputImage;
    }

    public static void setBackInputImage(String backInputImage) {
        CaptureVariables.backInputImage = backInputImage;
    }
}
