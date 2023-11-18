package btrust.onboardingProcess.api.variables;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PreProcess {

    private int flowId;
    private boolean success;
    private int inquiryId;
    private String dataId;
    private boolean dataSuccess;
    private String nextProcess;
    private String missingFields;
    private int errorCode;
    private String sessionId;
    private String caseId;
    private final List<String> ocrTypes = new ArrayList<>();
    private String errorMessage;
    private int dataErrorCode;
    private String dataErrorMessage;
    private String metadata;
    private String data;
    private String token;
    private String mathildaFileName;
    private boolean getOcrResults;
    private Map<String, String> configuration;
    private boolean backStepAllow;

    //#############################################################################

    public boolean isBackStepAllow() {
        return backStepAllow;
    }

    public void setBackStepAllow(boolean backStepAllow) {
        this.backStepAllow = backStepAllow;
    }

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public Map<String, String> getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Map<String, String> configuration) {
        this.configuration = configuration;
    }

    public String getDataErrorMessage() {
        return dataErrorMessage;
    }

    public void setDataErrorMessage(String dataErrorMessage) {
        this.dataErrorMessage = dataErrorMessage;
    }

    public boolean isGetOcrResults() {
        return getOcrResults;
    }

    public void setGetOcrResults(boolean getOcrResults) {
        this.getOcrResults = getOcrResults;
    }

    public String getMathildaFileName() {
        return mathildaFileName;
    }

    public void setMathildaFileName(String mathildaFileName) {
        this.mathildaFileName = mathildaFileName;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<String> getOcrTypes() {
        return ocrTypes;
    }

    public int getFlowId() {
        return flowId;
    }

    public void setFlowId(int flowId) {
        this.flowId = flowId;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getInquiryId() {
        return inquiryId;
    }

    public void setInquiryId(int inquiryId) {
        this.inquiryId = inquiryId;
    }

    public boolean isDataSuccess() {
        return dataSuccess;
    }

    public void setDataSuccess(boolean dataSuccess) {
        this.dataSuccess = dataSuccess;
    }

    public String getNextProcess() {
        return nextProcess;
    }

    public void setNextProcess(String nextProcess) {
        this.nextProcess = nextProcess;
    }

    public String getMissingFields() {
        return missingFields;
    }

    public void setMissingFields(String missingFields) {
        this.missingFields = missingFields;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getDataErrorCode() {
        return dataErrorCode;
    }

    public void setDataErrorCode(int dataErrorCode) {
        this.dataErrorCode = dataErrorCode;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }
}
