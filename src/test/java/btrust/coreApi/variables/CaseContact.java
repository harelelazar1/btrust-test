package btrust.coreApi.variables;

public class CaseContact {


    private int id;

    private int caseId;
    private int contactId;

    private int contactStatusTypeId;
    private String portalLink;
    private Boolean success;
    private int errorCode;

    private String sessionId;
    private String url;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCaseId() {
        return caseId;
    }

    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public int getContactStatusTypeId() {
        return contactStatusTypeId;
    }

    public void setContactStatusTypeId(int contactStatusTypeId) {
        this.contactStatusTypeId = contactStatusTypeId;
    }

    public String getPortalLink() {
        return portalLink;
    }

    public void setPortalLink(String portalLink) {
        this.portalLink = portalLink;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setIsSuccess(boolean success) {
        this.success = success;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
