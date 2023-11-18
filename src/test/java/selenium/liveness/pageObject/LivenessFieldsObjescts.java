package selenium.liveness.pageObject;

import java.util.HashMap;

public class LivenessFieldsObjescts {

    private HashMap objectId;
    private String caseId;
    private String service;
    private String status;
    private String token;
    private String workingMode;

    private double livenessEngineThreshold;
    private String libraryConfigurationName;
    private String statusReason;
    private String faceImage;
    private String frameImage;
    private String video;
    private String errorMessage;
    private String errorCode;

    public HashMap getObjectId() {
        return objectId;
    }

    public void setObjectId(HashMap objectId) {
        this.objectId = objectId;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }


    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getWorkingMode() {
        return workingMode;
    }

    public void setWorkingMode(String workingMode) {
        this.workingMode = workingMode;
    }

    public double getLivenessEngineThreshold() {
        return livenessEngineThreshold;
    }

    public void setLivenessEngineThreshold(double livenessEngineThreshold) {
        this.livenessEngineThreshold = livenessEngineThreshold;
    }

    public String getStatusReason() {
        return statusReason;
    }

    public void setStatusReason(String statusReason) {
        this.statusReason = statusReason;
    }

    public String getFaceImage() {
        return faceImage;
    }

    public void setFaceImage(String faceImage) {
        this.faceImage = faceImage;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getLibraryConfigurationName() {
        return libraryConfigurationName;
    }

    public void setLibraryConfigurationName(String libraryConfigurationName) {
        this.libraryConfigurationName = libraryConfigurationName;
    }

    public String getFrameImage() {
        return frameImage;
    }

    public void setFrameImage(String frameImage) {
        this.frameImage = frameImage;
    }
}
