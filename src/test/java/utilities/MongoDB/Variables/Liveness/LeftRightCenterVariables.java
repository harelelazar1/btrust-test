package utilities.MongoDB.Variables.Liveness;

import com.jayway.jsonpath.JsonPath;
import io.qameta.allure.Step;
import utilities.MongoDB.Variables.CommonVariables;

public class LeftRightCenterVariables extends LivenessHandlers {

    private static String headPositionType;
    private static String headPositionType1;
    private static String headPositionType2;
    private static boolean headPositionExpected;
    private static boolean headPositionExpected1;
    private static boolean headPositionExpected2;


    public LeftRightCenterVariables() {
        headPositionType = null;
        headPositionType1 = null;
        headPositionType2 = null;
        headPositionExpected = false;
        headPositionExpected1 = false;
        headPositionExpected2 = false;
    }

//    @Step("Set values according the liveness stage")
    public static void setVariables(String jsonResult, int i) {
        try {
            if (CommonVariables.getConfigFilename().equalsIgnoreCase("reverseStages.json") || CommonVariables.getConfigFilename().equalsIgnoreCase("gesturesWithActive.json") ) {
                switch (JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].stage.order").toString()) {
                    case "3": {
                        if (jsonResult.contains("action_type")) {
                            setActionType3(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].action_type"));
                        }
                        if (jsonResult.contains("order")) {
                            setOrder3(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].stage.order"));
                        }
                        if (jsonResult.contains("type")) {
                            setTypeUnderStage3(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].stage.type"));
                        }
                        if (jsonResult.contains("status")) {
                            setStageStatus3(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].status"));
                        }
                        break;
                    }
                    case "4": {
                        if (jsonResult.contains("action_type")) {
                            setActionType4(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].action_type"));
                        }
                        if (jsonResult.contains("order")) {
                            setOrder4(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].stage.order"));
                        }
                        if (jsonResult.contains("type")) {
                            setTypeUnderStage4(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].stage.type"));
                        }
                        if (jsonResult.contains("status")) {
                            setStageStatus4(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].status"));
                        }
                        break;
                    }
                    case "5": {
                        if (jsonResult.contains("action_type")) {
                            setActionType7(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].action_type"));
                        }
                        if (jsonResult.contains("order")) {
                            setOrder7(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].stage.order"));
                        }
                        if (jsonResult.contains("type")) {
                            setTypeUnderStage7(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].stage.type"));
                        }
                        if (jsonResult.contains("status")) {
                            setStageStatus7(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].status"));
                        }
                        break;
                    }
                }
            } else {
                switch (JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].stage.order").toString()) {
                    case "2": {
                        if (jsonResult.contains("action_type")) {
                            setActionType3(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].action_type"));
                        }
                        if (jsonResult.contains("order")) {
                            setOrder3(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].stage.order"));
                        }
                        if (jsonResult.contains("type")) {
                            setTypeUnderStage3(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].stage.type"));
                        }
                        if (jsonResult.contains("status")) {
                            setStageStatus3(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].status"));
                        }
                        break;
                    }
                    case "3": {
                        if (jsonResult.contains("action_type")) {
                            setActionType4(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].action_type"));
                        }
                        if (jsonResult.contains("order")) {
                            setOrder4(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].stage.order"));
                        }
                        if (jsonResult.contains("type")) {
                            setTypeUnderStage4(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].stage.type"));
                        }
                        if (jsonResult.contains("status")) {
                            setStageStatus4(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].status"));
                        }
                        break;
                    }
                    case "4": {
                        if (jsonResult.contains("action_type")) {
                            setActionType7(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].action_type"));
                        }
                        if (jsonResult.contains("order")) {
                            setOrder7(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].stage.order"));
                        }
                        if (jsonResult.contains("type")) {
                            setTypeUnderStage7(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].stage.type"));
                        }
                        if (jsonResult.contains("status")) {
                            setStageStatus7(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + i + "].status"));
                        }
                        break;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

//    @Step("Set liveness active dynamic sides head position variables")
    public static void setHeadPositions(String jsonResult, int stagesArrayIndex, int headPositionsArrayIndex) {
        if (CommonVariables.getConfigFilename().equalsIgnoreCase("reverseStages.json") || CommonVariables.getConfigFilename().equalsIgnoreCase("gesturesWithActive.json")) {
            switch (JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + stagesArrayIndex + "].stage.order").toString()) {
                case "3": {
                    if (jsonResult.contains("head_position_changes")) {
                        setHeadPositionType(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + stagesArrayIndex + "].payload.head_position_changes[" + (headPositionsArrayIndex - 1) + "].type"));
                        setHeadPositionExpected(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + stagesArrayIndex + "].payload.head_position_changes[" + (headPositionsArrayIndex - 1) + "].expected"));
                    }
                    break;
                }

                case "4": {
                    if (jsonResult.contains("head_position_changes")) {
                        setHeadPositionType1(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + stagesArrayIndex + "].payload.head_position_changes[" + (headPositionsArrayIndex - 1) + "].type"));
                        setHeadPositionExpected1(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + stagesArrayIndex + "].payload.head_position_changes[" + (headPositionsArrayIndex - 1) + "].expected"));
                    }
                    break;

                }
                case "5": {
                    if (jsonResult.contains("head_position_changes")) {
                        setHeadPositionType2(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + stagesArrayIndex + "].payload.head_position_changes[" + (headPositionsArrayIndex - 1) + "].type"));
                        setHeadPositionExpected2(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + stagesArrayIndex + "].payload.head_position_changes[" + (headPositionsArrayIndex - 1) + "].expected"));
                    }
                    break;
                }
            }
        } else {
            switch (JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + stagesArrayIndex + "].stage.order").toString()) {
                case "2": {
                    if (jsonResult.contains("head_position_changes")) {
                        setHeadPositionType(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + stagesArrayIndex + "].payload.head_position_changes[" + (headPositionsArrayIndex - 1) + "].type"));
                        setHeadPositionExpected(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + stagesArrayIndex + "].payload.head_position_changes[" + (headPositionsArrayIndex - 1) + "].expected"));
                    }
                    break;
                }
                case "3": {
                    if (jsonResult.contains("head_position_changes")) {
                        setHeadPositionType1(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + stagesArrayIndex + "].payload.head_position_changes[" + (headPositionsArrayIndex - 1) + "].type"));
                        setHeadPositionExpected1(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + stagesArrayIndex + "].payload.head_position_changes[" + (headPositionsArrayIndex - 1) + "].expected"));
                    }
                    break;
                }
                case "4": {
                    if (jsonResult.contains("head_position_changes")) {
                        setHeadPositionType2(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + stagesArrayIndex + "].payload.head_position_changes[" + (headPositionsArrayIndex - 1) + "].type"));
                        setHeadPositionExpected2(JsonPath.parse(jsonResult).read("$.stagesResponsesArray[" + stagesArrayIndex + "].payload.head_position_changes[" + (headPositionsArrayIndex - 1) + "].expected"));
                    }
                    break;
                }
            }
        }
    }

    //###############################################

    public static String getHeadPositionType() {
        return headPositionType;
    }

    public static void setHeadPositionType(String headPositionType) {
        LeftRightCenterVariables.headPositionType = headPositionType;
    }

    public static String getHeadPositionType1() {
        return headPositionType1;
    }

    public static void setHeadPositionType1(String headPositionType1) {
        LeftRightCenterVariables.headPositionType1 = headPositionType1;
    }

    public static String getHeadPositionType2() {
        return headPositionType2;
    }

    public static void setHeadPositionType2(String headPositionType2) {
        LeftRightCenterVariables.headPositionType2 = headPositionType2;
    }

    public static boolean isHeadPositionExpected() {
        return headPositionExpected;
    }

    public static void setHeadPositionExpected(boolean headPositionExpected) {
        LeftRightCenterVariables.headPositionExpected = headPositionExpected;
    }

    public static boolean isHeadPositionExpected1() {
        return headPositionExpected1;
    }

    public static void setHeadPositionExpected1(boolean headPositionExpected1) {
        LeftRightCenterVariables.headPositionExpected1 = headPositionExpected1;
    }

    public static boolean isHeadPositionExpected2() {
        return headPositionExpected2;
    }

    public static void setHeadPositionExpected2(boolean headPositionExpected2) {
        LeftRightCenterVariables.headPositionExpected2 = headPositionExpected2;
    }
}
