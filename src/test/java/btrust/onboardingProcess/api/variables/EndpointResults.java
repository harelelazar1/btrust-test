package btrust.onboardingProcess.api.variables;

public class EndpointResults {

    private boolean success;
    private int errorCode;
    private String errorMessage;

    private boolean dataSuccess;
    private String dataErrorMessage;
    private int dataErrorCode;
    private String metadata;

    //#############################################################################


    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public boolean isDataSuccess() {
        return dataSuccess;
    }

    public void setDataSuccess(boolean dataSuccess) {
        this.dataSuccess = dataSuccess;
    }

    public String getDataErrorMessage() {
        return dataErrorMessage;
    }

    public void setDataErrorMessage(String dataErrorMessage) {
        this.dataErrorMessage = dataErrorMessage;
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
