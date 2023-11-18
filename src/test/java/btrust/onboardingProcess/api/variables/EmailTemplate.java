package btrust.onboardingProcess.api.variables;

public class EmailTemplate {


    private boolean success;
    private int errorCode;
    private int data;

    private String data1;


    public EmailTemplate() {

        success = false;
        errorCode = 0;
        data = 0;
        data1 = null;


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

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public void setData1(String data1) {
        this.data1 = data1;
    }

    public String getData1() {
        return data1;
    }


}
