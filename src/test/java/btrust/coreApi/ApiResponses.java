package btrust.coreApi;

import btrust.coreApi.variables.CaseContact;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ApiResponses extends ApiRequests {


    Response apiResponse;
    JsonPath jsonPath;

    private final String btrust1Auth = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJ1c2VyRGF0YSI6IntcImlkXCI6ODMsXCJjb21wYW55SWRcIjo0LFwiYWN0aXZlU3ViQ29tcGFueVwiOjAsXCJyb2xlc1wiOls3XSxcInBlcm1pc3Npb25zXCI6W3tcImlkXCI6MCxcInJvbGVJZFwiOjAsXCJ0eXBlXCI6MCxcInR5cGVJZFwiOjAsXCJjYW5SZWFkXCI6ZmFsc2UsXCJjYW5FZGl0XCI6ZmFsc2UsXCJjYW5DcmVhdGVcIjpmYWxzZX0se1wiaWRcIjowLFwicm9sZUlkXCI6MCxcInR5cGVcIjo2LFwidHlwZUlkXCI6MCxcImNhblJlYWRcIjp0cnVlLFwiY2FuRWRpdFwiOnRydWUsXCJjYW5DcmVhdGVcIjp0cnVlfV19IiwiaWF0IjoxNjIyNTU0NjUxLCJleHAiOjMxOTkzNTQ2NTF9.S53gOLQJ9XqD3UsFZzx_pn9hOJ6hpCM8CDLHI2rXvPR256GLDj4Y0Eb4YKSWrERs8hJlvLA9okxLkJJ6I1JgxHzgrwgb2gdMyDPEji6rGXRijKzsbwWYUzT1xe78p0cb-LUGfrcfM80HK7qV2CePZ_ho7C9Jczg_evV56xVIoN10eZrrpzUDEJ-dfTjtXSK93AkGeh6t1n3Lm8xhDYPrZ2fy59Da4w9MmPzUwl3VaAxIawum2VRM2U6p15hH4bB_p1HZaVdA_2GmXf74KuRkguyPbbDWZI7g9LVU5POj6JGxTsRtDOaGBeFNsRXKVkvb2DizMxCl1AhXPwf_G9-Oaw";
    protected String sessionId;
    protected String mobileFlowLink;
    protected Response response;

    public void MobileInteractionUIBase() {
        mobileFlowLink = null;
        response = null;
        jsonPath = null;
        sessionId = null;
    }


    public void setCaseContact(CaseContact caseContact, int caseId) {
        apiResponse = getCaseContact(caseId);
        jsonPath = apiResponse.jsonPath();
        String path;
        path = "[0]";

        caseContact.setId((Integer) jsonPath.getMap(path).get("id"));
        caseContact.setCaseId((Integer) jsonPath.getMap(path).get("caseId"));
        caseContact.setContactId((Integer) jsonPath.getMap(path).get("contactId"));
        caseContact.setContactStatusTypeId((Integer) jsonPath.getMap(path).get("contactStatusTypeId"));
    }


    public void setCreatePortalLink(CaseContact caseContact, int caseId, int contactId) {
        apiResponse = createPortalLink(caseId, contactId);
        jsonPath = apiResponse.jsonPath();

        caseContact.setId(jsonPath.getInt("contactId"));
        caseContact.setPortalLink(jsonPath.getString("link"));
    }


    public void setSessionStatus(CaseContact caseContact, String caseId, String contactId) {
        apiResponse = getSession(caseId, contactId);
        jsonPath = apiResponse.jsonPath();
        String path;
        path = "data";
        caseContact.setIsSuccess(jsonPath.getBoolean("success"));
        caseContact.setErrorCode(jsonPath.getInt("errorCode"));
        if (jsonPath.getMap(path).containsKey("sessionId"))
            caseContact.setSessionId((String) jsonPath.getMap(path).get("sessionId"));
        if (jsonPath.getMap(path).containsKey("url"))
            caseContact.setUrl((String) jsonPath.getMap(path).get("url"));
    }


}
