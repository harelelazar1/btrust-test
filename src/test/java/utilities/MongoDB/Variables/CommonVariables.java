package utilities.MongoDB.Variables;

import com.jayway.jsonpath.JsonPath;
import io.qameta.allure.Step;

public class CommonVariables {

    private static String objectId;
    private static String caseId;
    private static String configFilename;
    private static String serviceType;
    private static String status;
    private static String statusReason;
    private static boolean success;
    private static String video;
    private static String workingMode;
    private static String serverType;

    public CommonVariables() {
        objectId = null;
        caseId = null;
        configFilename = null;
        serviceType = null;
        status = null;
        statusReason = null;
        success = false;
        video = null;
        workingMode = null;
        serverType = null;
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
            if (jsonResult.contains("configFilename")) {
                setConfigFilename(JsonPath.parse(jsonResult).read("$.configFilename"));
            } else setConfigFilename(null);
            if (jsonResult.contains("serviceType")) {
                setServiceType(JsonPath.parse(jsonResult).read("$.serviceType"));
            } else setServiceType(null);
            if (jsonResult.contains("status")) {
                setStatus(JsonPath.parse(jsonResult).read("$.status"));
            } else setStatus(null);
            if (jsonResult.contains("statusReason")) {
                setStatusReason(JsonPath.parse(jsonResult).read("$.statusReason"));
            } else setStatusReason(null);
            if (jsonResult.contains("success")) {
                setSuccess(JsonPath.parse(jsonResult).read("$.success"));
            } else setSuccess(false);
            if (jsonResult.contains("video")) {
                setVideo(JsonPath.parse(jsonResult).read("$.video"));
            } else setVideo(null);
            if (jsonResult.contains("workingMode")) {
                setWorkingMode(JsonPath.parse(jsonResult).read("$.workingMode"));
            } else setWorkingMode(null);
            if (jsonResult.contains("serverType")) {
                setServerType(JsonPath.parse(jsonResult).read("$.serverType"));
            } else setServerType(null);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //###############################################

    public static String getObjectId() {
        return objectId;
    }

    public static void setObjectId(String objectId) {
        CommonVariables.objectId = objectId;
    }

    public static String getCaseId() {
        return caseId;
    }

    public static void setCaseId(String caseId) {
        CommonVariables.caseId = caseId;
    }

    public static String getConfigFilename() {
        return configFilename;
    }

    public static void setConfigFilename(String configFilename) {
        CommonVariables.configFilename = configFilename;
    }

    public static String getServiceType() {
        return serviceType;
    }

    public static void setServiceType(String serviceType) {
        CommonVariables.serviceType = serviceType;
    }

    public static String getStatus() {
        return status;
    }

    public static void setStatus(String status) {
        CommonVariables.status = status;
    }

    public static String getStatusReason() {
        return statusReason;
    }

    public static void setStatusReason(String statusReason) {
        CommonVariables.statusReason = statusReason;
    }

    public static boolean isSuccess() {
        return success;
    }

    public static void setSuccess(boolean success) {
        CommonVariables.success = success;
    }

    public static String getVideo() {
        return video;
    }

    public static void setVideo(String video) {
        CommonVariables.video = video;
    }

    public static String getWorkingMode() {
        return workingMode;
    }

    public static void setWorkingMode(String workingMode) {
        CommonVariables.workingMode = workingMode;
    }

    public static String getServerType() {
        return serverType;
    }

    public static void setServerType(String serverType) {
        CommonVariables.serverType = serverType;
    }
}
