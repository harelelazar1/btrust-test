package utilities.MongoDB.Variables.Liveness;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

public class GesturesVariables extends LivenessHandlers {

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

    public GesturesVariables() {
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
    }

    public static void setGesturesVariables(String jsonResult, int i) {
        try {
            if (jsonResult.contains("action_type")) {
                setActionType8(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].action_type"));
            }
            if (jsonResult.contains("order")) {
                setOrder8(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].stage.order"));
            }
            if (jsonResult.contains("type")) {
                setTypeUnderStage8(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].stage.type"));
            }
            if (jsonResult.contains("gestures")) {
                JSONArray jsonArray = JsonPath.read(jsonResult, "$.stagesResponsesArray[" + i + "].payload.gestures");
                for (int j = 0; j < jsonArray.size(); j++) {
                    switch (j) {
                        case 0: {
                            setGestures1(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.gestures[" + j + "]"));
                            break;
                        }
                        case 1: {
                            setGestures2(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.gestures[" + j + "]"));
                            break;
                        }
                        case 2: {
                            setGestures3(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.gestures[" + j + "]"));
                            break;
                        }
                    }
                }
            }
            if (jsonResult.contains("sequenceSecondsInterval")) {
                setSequenceSecondsInterval(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.sequenceSecondsInterval"));
            }
            if (jsonResult.contains("resources")) {
                if (getGestures1() != null) {
                    setResourceGesture1(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.resources." + getGestures1() + ".gesture"));
                    setResourceGestureMask1(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.resources." + getGestures1() + ".gesture_mask"));
                    setResourceGestureMaskLine1(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.resources." + getGestures1() + ".gesture_mask_line"));
                }

                if (getGestures2() != null) {
                    setResourceGesture2(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.resources." + getGestures2() + ".gesture"));
                    setResourceGestureMask2(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.resources." + getGestures2() + ".gesture_mask"));
                    setResourceGestureMaskLine2(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.resources." + getGestures2() + ".gesture_mask_line"));
                }

                if (getGestures3() != null) {
                    setResourceGesture3(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.resources." + getGestures3() + ".gesture"));
                    setResourceGestureMask3(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.resources." + getGestures3() + ".gesture_mask"));
                    setResourceGestureMaskLine3(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].payload.resources." + getGestures3() + ".gesture_mask_line"));
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
        GesturesVariables.gestures1 = gestures1;
    }

    public static String getGestures2() {
        return gestures2;
    }

    public static void setGestures2(String gestures2) {
        GesturesVariables.gestures2 = gestures2;
    }

    public static String getGestures3() {
        return gestures3;
    }

    public static void setGestures3(String gestures3) {
        GesturesVariables.gestures3 = gestures3;
    }

    public static int getSequenceSecondsInterval() {
        return sequenceSecondsInterval;
    }

    public static void setSequenceSecondsInterval(int sequenceSecondsInterval) {
        GesturesVariables.sequenceSecondsInterval = sequenceSecondsInterval;
    }

    public static String getResourceGesture1() {
        return resourceGesture1;
    }

    public static void setResourceGesture1(String resource1) {
        GesturesVariables.resourceGesture1 = resource1;
    }

    public static String getResourceGestureMask1() {
        return resourceGestureMask1;
    }

    public static void setResourceGestureMask1(String resource1) {
        GesturesVariables.resourceGestureMask1 = resource1;
    }

    public static String getResourceGestureMaskLine1() {
        return resourceGestureMaskLine1;
    }

    public static void setResourceGestureMaskLine1(String resource1) {
        GesturesVariables.resourceGestureMaskLine1 = resource1;
    }

    public static String getResourceGesture2() {
        return resourceGesture2;
    }

    public static void setResourceGesture2(String resource2) {
        GesturesVariables.resourceGesture2 = resource2;
    }

    public static String getResourceGestureMask2() {
        return resourceGestureMask2;
    }

    public static void setResourceGestureMask2(String resource2) {
        GesturesVariables.resourceGestureMask2 = resource2;
    }

    public static String getResourceGestureMaskLine2() {
        return resourceGestureMaskLine2;
    }

    public static void setResourceGestureMaskLine2(String resource2) {
        GesturesVariables.resourceGestureMaskLine2 = resource2;
    }


    public static String getResourceGesture3() {
        return resourceGesture3;
    }

    public static void setResourceGesture3(String resource3) {
        GesturesVariables.resourceGesture3 = resource3;
    }

    public static String getResourceGestureMask3() {
        return resourceGestureMask2;
    }

    public static void setResourceGestureMask3(String resource3) {
        GesturesVariables.resourceGestureMask3 = resource3;
    }

    public static String getResourceGestureMaskLine3() {
        return resourceGestureMaskLine3;
    }

    public static void setResourceGestureMaskLine3(String resource3) {
        GesturesVariables.resourceGestureMaskLine3 = resource3;
    }



}
