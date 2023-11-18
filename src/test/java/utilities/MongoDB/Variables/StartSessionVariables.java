package utilities.MongoDB.Variables;

import com.jayway.jsonpath.JsonPath;
import io.qameta.allure.Step;

public class StartSessionVariables {

    private static String objectId;
    private static String caseId;
    private static String serverType;
    private static String serviceType;
    private static String status;
    private static String stageStatus;
    private static String workingMode;

    public StartSessionVariables() {
        objectId = null;
        caseId = null;
        serverType = null;
        serviceType = null;
        status = null;
        stageStatus = null;
        workingMode = null;
    }

//    @Step("Set Values")
    public static void setVariables(String jsonResult) {
        try {
            if (jsonResult.contains("_id")) {
                setObjectId(JsonPath.parse(jsonResult).read("$._id").toString());
            } else setObjectId(null);
            if (jsonResult.contains("caseId")) {
                setCaseId(JsonPath.parse(jsonResult).read("$.caseId"));
            } else setCaseId(null);
            if (jsonResult.contains("serverType")) {
                setServerType(JsonPath.parse(jsonResult).read("$.serverType"));
            } else setServerType(null);
            if (jsonResult.contains("serviceType")) {
                setServiceType(JsonPath.parse(jsonResult).read("$.serviceType"));
            } else setServiceType(null);
            if (jsonResult.contains("status")) {
                setStatus(JsonPath.parse(jsonResult).read("$.status"));
            } else setStatus(null);
            if (jsonResult.contains("workingMode")) {
                setWorkingMode(JsonPath.parse(jsonResult).read("$.workingMode"));
            } else setWorkingMode(null);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //###############################################

    public static String getObjectId() {
        return objectId;
    }

    public static void setObjectId(String objectId) {
        StartSessionVariables.objectId = objectId;
    }

    public static String getCaseId() {
        return caseId;
    }

    public static void setCaseId(String caseId) {
        StartSessionVariables.caseId = caseId;
    }

    public static String getServerType() {
        return serverType;
    }

    public static void setServerType(String serverType) {
        StartSessionVariables.serverType = serverType;
    }

    public static String getServiceType() {
        return serviceType;
    }

    public static void setServiceType(String serviceType) {
        StartSessionVariables.serviceType = serviceType;
    }

    public static String getStatus() {
        return status;
    }

    public static void setStatus(String status) {
        StartSessionVariables.status = status;
    }

    public static String getStageStatus() {
        return stageStatus;
    }

    public static void setStageStatus(String stageStatus) {
        StartSessionVariables.stageStatus = stageStatus;
    }

    public static String getWorkingMode() {
        return workingMode;
    }

    public static void setWorkingMode(String workingMode) {
        StartSessionVariables.workingMode = workingMode;
    }

}
