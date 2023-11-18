package utilities.MongoDB.Variables.Ocr;

import com.jayway.jsonpath.JsonPath;
import io.qameta.allure.Step;
import utilities.MongoDB.Variables.Liveness.GesturesVariables;

public class CNIVariables extends OcrHandlers {

    private static String frontCardType;
    private static String backCardType;
    private static String faceImage;
    private static String inputImage;
    private static String inputImage2;
    private static String croppedImage;
    private static String croppedImage2;
    private static String otpNum;

    //gestures variables
    private static String gestures1;
    private static String gestures2;
    private static String gestures3;
    private static int sequenceSecondsInterval;
    private static String resourceGesture1;
    private static String resourceGestureMask1;
    private static String resourceGestureMaskLine1;
    private static String resourceGesture2;
    private static String resourceGestureMask2;
    private static String resourceGestureMaskLine2;
    private static String resourceGesture3;
    private static String resourceGestureMask3;
    private static String resourceGestureMaskLine3;
    private static String resourceGestureIcons1;
    private static String resourceGestureIcons2;
    private static String resourceGestureIcons3;

    public CNIVariables() {
        frontCardType = null;
        backCardType = null;
        faceImage = null;
        inputImage = null;
        inputImage2 = null;
        croppedImage = null;
        croppedImage2 = null;
        otpNum = null;

        gestures1 = null;
        gestures2 = null;
        gestures3 = null;
        sequenceSecondsInterval = 0;
        resourceGesture1 = null;
        resourceGestureMask1 = null;
        resourceGestureMaskLine1 = null;
        resourceGesture2 = null;
        resourceGestureMask2 = null;
        resourceGestureMaskLine2 = null;
        resourceGesture3 = null;
        resourceGestureMask3 = null;
        resourceGestureMaskLine3 = null;
        resourceGestureIcons1 = null;
        resourceGestureIcons2 = null;
        resourceGestureIcons3 = null;
    }

//    @Step("Set front side values")
    public static void setFrontSideVariables(String jsonResult, int i) {
        try {
            setOcrFrontSideVariables(jsonResult, i);
            if (jsonResult.contains("card_type")) {
                setFrontCardType(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.card_type"));
            } else setFrontCardType(null);
            if (jsonResult.contains("face_image")) {
                setFaceImage(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.images.face_image"));
            } else setFaceImage(null);
            if (jsonResult.contains("input_image")) {
                setInputImage(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.images.input_image"));
            } else setInputImage(null);
            if (jsonResult.contains("cropped_image")) {
                setCroppedImage(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.images.cropped_image"));
            } else setCroppedImage(null);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

//    @Step("Set back side values")
    public static void setBackSideVariables(String jsonResult, int i) {
        try {
            setOcrBackSideVariables(jsonResult, i);
            if (jsonResult.contains("card_type")) {
                setBackCardType(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.card_type"));
            } else setBackCardType(null);
            if (jsonResult.contains("input_image")) {
                setInputImage2(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.images.input_image"));
            } else setInputImage2(null);
            if (jsonResult.contains("cropped_image")) {
                setCroppedImage2(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.images.cropped_image"));
            } else setCroppedImage2(null);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

//    @Step("Set video stage values")
    public static void setVideoVariables(String jsonResult, int i) {
        try {
            switch (JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].stage.order").toString()) {
                case "2": {
                    if (jsonResult.contains("action_type")) {
                        setActionType3(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].action_type"));
                    } else setActionType3(null);
                    if (jsonResult.contains("order")) {
                        setOrder3(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].stage.order"));
                    } else setOrder3(0);
                    if (jsonResult.contains("type")) {
                        setTypeUnderStage3(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].stage.type"));
                    } else setTypeUnderStage3(null);
                    if (jsonResult.contains("status")) {
                        setStageStatus3(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].status"));
                    } else setStageStatus3(null);
                    break;
                }
                case "4": {
                    if (jsonResult.contains("action_type")) {
                        setActionType4(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].action_type"));
                    } else setActionType4(null);
                    if (jsonResult.contains("order")) {
                        setOrder4(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].stage.order"));
                    } else setOrder4(0);
                    if (jsonResult.contains("type")) {
                        setTypeUnderStage4(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].stage.type"));
                    } else setTypeUnderStage4(null);
                    if (jsonResult.contains("status")) {
                        setStageStatus4(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].status"));
                    } else setStageStatus4(null);
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

//    @Step("Set otp stage values")
    public static void setOtpVariables(String jsonResult, int i) {
        try {
            if (jsonResult.contains("action_type")) {
                setActionType5(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].action_type"));
            } else setActionType5(null);
            if (jsonResult.contains("order")) {
                setOrder5(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].stage.order"));
            } else setOrder5(0);
            if (jsonResult.contains("type")) {
                setTypeUnderStage5(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].stage.type"));
            } else setTypeUnderStage5(null);
            if (jsonResult.contains("status")) {
                setStageStatus5(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].status"));
            } else setStageStatus5(null);
            if (jsonResult.contains("otp_number")) {
                setOtpNum(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.otp_number"));
            } else setOtpNum(null);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

//    @Step("Set gestures stage values")
    public static void setGesturesVariables(String jsonResult, int i) {
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
            if (jsonResult.contains("gestures")) {
                setGestures1(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.gestures[0]"));
                setGestures2(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.gestures[1]"));
                setGestures3(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.gestures[2]"));
            }
            if (jsonResult.contains("sequenceSecondsInterval")) {
                setSequenceSecondsInterval(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.sequenceSecondsInterval"));
            }
            if (jsonResult.contains("resources")) {
                if (getGestures1() != null) {
                    setResourceGesture1(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.resources." + getGestures1() + ".gesture"));
                    setResourceGestureMask1(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.resources." + getGestures1() + ".gesture_mask"));
                    setResourceGestureMaskLine1(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.resources." + getGestures1() + ".gesture_mask_line"));
 //                   setResourceGestureIcons1(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.resources." + getGestures1() + ".gesturesIcons"));
                }

                if (getGestures2() != null) {
                    setResourceGesture2(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.resources." + getGestures2() + ".gesture"));
                    setResourceGestureMask2(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.resources." + getGestures2() + ".gesture_mask"));
                    setResourceGestureMaskLine2(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.resources." + getGestures2() + ".gesture_mask_line"));
 //                   setResourceGestureIcons2(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.resources." + getGestures2() + ".gesturesIcons"));
                }

                if (getGestures3() != null) {
                    setResourceGesture3(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.resources." + getGestures3() + ".gesture"));
                    setResourceGestureMask3(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.resources." + getGestures3() + ".gesture_mask"));
                    setResourceGestureMaskLine3(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.resources." + getGestures3() + ".gesture_mask_line"));
 //                   setResourceGestureIcons3(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.resources." + getGestures3() + ".gesturesIcons"));
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //###############################################


    public static String getGestures1() {
        return gestures1;
    }

    public static void setGestures1(String gestures1) {
        CNIVariables.gestures1 = gestures1;
    }

    public static String getGestures2() {
        return gestures2;
    }

    public static void setGestures2(String gestures2) {
        CNIVariables.gestures2 = gestures2;
    }

    public static String getGestures3() {
        return gestures3;
    }

    public static void setGestures3(String gestures3) {
        CNIVariables.gestures3 = gestures3;
    }

    public static int getSequenceSecondsInterval() {
        return sequenceSecondsInterval;
    }

    public static void setSequenceSecondsInterval(int sequenceSecondsInterval) {
        CNIVariables.sequenceSecondsInterval = sequenceSecondsInterval;
    }


    public static String getFrontCardType() {
        return frontCardType;
    }

    public static void setFrontCardType(String frontCardType) {
        CNIVariables.frontCardType = frontCardType;
    }

    public static String getBackCardType() {
        return backCardType;
    }

    public static void setBackCardType(String backCardType) {
        CNIVariables.backCardType = backCardType;
    }

    public static String getFaceImage() {
        return faceImage;
    }

    public static void setFaceImage(String faceImage) {
        CNIVariables.faceImage = faceImage;
    }

    public static String getInputImage() {
        return inputImage;
    }

    public static void setInputImage(String inputImage) {
        CNIVariables.inputImage = inputImage;
    }

    public static String getInputImage2() {
        return inputImage2;
    }

    public static void setInputImage2(String inputImage2) {
        CNIVariables.inputImage2 = inputImage2;
    }

    public static String getCroppedImage() {
        return croppedImage;
    }

    public static void setCroppedImage(String croppedImage) {
        CNIVariables.croppedImage = croppedImage;
    }

    public static String getCroppedImage2() {
        return croppedImage2;
    }

    public static void setCroppedImage2(String croppedImage2) {
        CNIVariables.croppedImage2 = croppedImage2;
    }

    public static String getOtpNum() {
        return otpNum;
    }

    public static void setOtpNum(String otpNum) {
        CNIVariables.otpNum = otpNum;
    }


    public static String getResourceGesture1() {
        return resourceGesture1;
    }

    public static void setResourceGesture1(String resource1) {
        CNIVariables.resourceGesture1 = resource1;
    }

    public static String getResourceGestureMask1() {
        return resourceGestureMask1;
    }

    public static void setResourceGestureMask1(String resource1) {
        CNIVariables.resourceGestureMask1 = resource1;
    }

    public static String getResourceGestureMaskLine1() {
        return resourceGestureMaskLine1;
    }

    public static void setResourceGestureMaskLine1(String resource1) {
        CNIVariables.resourceGestureMaskLine1 = resource1;
    }

    public static String getResourceGestureIcons1() {
        return resourceGestureIcons1;
    }

    public static void setResourceGestureIcons1(String resource1) {
        CNIVariables.resourceGestureIcons1 = resource1;
    }

    public static String getResourceGesture2() {
        return resourceGesture2;
    }

    public static void setResourceGesture2(String resource2) {
        CNIVariables.resourceGesture2 = resource2;
    }

    public static String getResourceGestureMask2() {
        return resourceGestureMask2;
    }

    public static void setResourceGestureMask2(String resource2) {
        CNIVariables.resourceGestureMask2 = resource2;
    }

    public static String getResourceGestureMaskLine2() {
        return resourceGestureMaskLine2;
    }

    public static void setResourceGestureMaskLine2(String resource2) {
        CNIVariables.resourceGestureMaskLine2 = resource2;
    }

    public static String getResourceGestureIcons2() {
        return resourceGestureIcons2;
    }

    public static void setResourceGestureIcons2(String resource2) {
        CNIVariables.resourceGestureIcons2 = resource2;
    }


    public static String getResourceGesture3() {
        return resourceGesture3;
    }

    public static void setResourceGesture3(String resource3) {
        CNIVariables.resourceGesture3 = resource3;
    }

    public static String getResourceGestureMask3() {
        return resourceGestureMask2;
    }

    public static void setResourceGestureMask3(String resource3) {
        CNIVariables.resourceGestureMask3 = resource3;
    }

    public static String getResourceGestureMaskLine3() {
        return resourceGestureMaskLine3;
    }

    public static void setResourceGestureMaskLine3(String resource3) {
        CNIVariables.resourceGestureMaskLine3 = resource3;
    }

    public static String getResourceGestureIcons3() {
        return resourceGestureIcons3;
    }

    public static void setResourceGestureIcons3(String resource3) {
        CNIVariables.resourceGestureIcons3 = resource3;
    }
}
