package btrust.onboardingProcess.api.variables;

public class MobileInteraction {


    private int workflowNumber;
    private int errorCode;
    private int id;
    private int status;
    private boolean success;
    private String workflowName;
    private String flow;
    private String viewScript;

    private int createdBy;


    public MobileInteraction() {

        success = false;
        errorCode = 0;
        workflowNumber = 0;
        id = 0;
        workflowName = null;
        createdBy = 0;
        flow = null;
        viewScript = null;
        status = 0;

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

    public void setWorkflowNumber(int workflowNumber) {
        this.workflowNumber = workflowNumber;
    }

    public int getWorkflowNumber() {
        return workflowNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setWorkflowName(String workflowName) {
        this.workflowName = workflowName;
    }

    public String getWorkflowName() {
        return workflowName;
    }


    public void setFlow(String flow) {
        this.flow = flow;
    }

    public String getFlow() {
        return flow;
    }

    public void setViewScript(String viewScript) {
        this.viewScript = viewScript;
    }

    public String getViewScript() {
        return viewScript;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public int getCreatedBy() {
        return createdBy;
    }


}
