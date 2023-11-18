package api.liveness.newVersion;

import api.Variables;
import api.ApiResponse;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class ClientRequestLivenessErrorTest {

    ApiResponse apiResponse;
    Variables variables;

    @BeforeMethod
    public void startSession() {
        variables = new Variables();
        apiResponse = new ApiResponse();
        apiResponse.clientInitResponse(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "LIVENESS", "flowConfigName", "mathilda.json", apiResponse.CLIENT_INIT_LIVENESS);
        apiResponse.verifyInitVariables(variables);
    }

    @Test(description = "Send request without contentType and check that return error message (error code: 1000)")
    @Description("Send request without contentType and check that return error message (error code: 1000)")
    public void t01_errorMessage1000() throws IOException {
        apiResponse.mainClientResponse(variables, "multipart/form-", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barCenter.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);

        Assert.assertFalse(variables.isSuccess());
        Assert.assertEquals(variables.getErrorCode(), 1000);
        System.out.println(variables.getErrorMessage());
        Assert.assertEquals(variables.getErrorMessage(), "Expecting a request of type 'multipart/form-data'");
    }

    @Test(description = "Send request without file and check that return error message (error code: 1001)")
    @Description("Send request without file and check that return error message (error code: 1001)")
    public void t02_errorMessage1001() {
        apiResponse.clientRequestFileIsMissingError(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), apiResponse.CLIENT_REQUEST_LIVENESS);

        Assert.assertFalse(variables.isSuccess());
        Assert.assertEquals(variables.getErrorCode(), 1001);
        Assert.assertEquals(variables.getErrorMessage(), "Content length is either zero or not existent");
    }

    @Test(description = "Send request without file and check that return error message (error code: 1002)")
    @Description("Send request without file and check that return error message (error code: 1002)")
    public void t03_errorMessage1002() throws IOException {
        apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/Blank.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);

        Assert.assertFalse(variables.isSuccess());
        Assert.assertEquals(variables.getErrorCode(), 1002);
        Assert.assertEquals(variables.getErrorMessage(), "File content was found to be empty");
    }

    @Test(description = "Send request without token in header and check that return error message (error code: 1005)")
    @Description("Send request without token in header and check that return error message (error code: 1005)")
    public void t04_errorMessage1005() throws IOException {
        apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", " ", "frame", new File("./liveness/apiPic/barCenter.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);

        Assert.assertFalse(variables.isSuccess());
        Assert.assertEquals(variables.getErrorCode(), 1005);
        Assert.assertEquals(variables.getErrorMessage(), "Token was not provided");
    }

    @Test(description = "Send request with wrong token and check that return error message (error code: 1006)")
    @Description("Send request with wrong token and check that return error message (error code: 1006)")
    public void t05_errorMessage1006() throws IOException {
        apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Barear ", "frame", new File("./liveness/apiPic/barCenter.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);

        Assert.assertFalse(variables.isSuccess());
        Assert.assertEquals(variables.getErrorCode(), 1006);
        Assert.assertEquals(variables.getErrorMessage(), "Invalid token");
    }

    @Test(description = "Send request without frame and check that return error message (error code: 1009)")
    @Description("Send request without frame and check that return error message (error code: 1009)")
    public void t06_errorMessage1009() {
        apiResponse.clientRequestFileIsMissing(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);

        Assert.assertFalse(variables.isSuccess());
        Assert.assertEquals(variables.getErrorCode(), 1009);
        Assert.assertEquals(variables.getErrorMessage(), "No frame received");
    }

    @Test(description = "Send request with error in multipart form and check that return error message (error code: 1013)")
    @Description("Send request with error in multipart form and check that return error message (error code: 1013)")
    public void t07_errorMessage1013() throws IOException {
        apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barCenter.jpg"), "image/jpg", "message_type", "report_failure", apiResponse.CLIENT_REQUEST_LIVENESS);

        Assert.assertFalse(variables.isSuccess());
        Assert.assertEquals(variables.getErrorCode(), 1013);
        Assert.assertEquals(variables.getErrorMessage(), "Error reporting wrong structure, correct structure: {errorCode: number, errorMessage: string}");
    }

    @Test(description = "Send request without message_type and check that return error message (error code: 1014)")
    @Description("Send request without message_type and check that return error message (error code: 1014)")
    public void t08_errorMessage1014() throws IOException {
        apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barCenter.jpg"), "image/jpg", "", "", apiResponse.CLIENT_REQUEST_LIVENESS);

        Assert.assertFalse(variables.isSuccess());
        Assert.assertEquals(variables.getErrorCode(), 1014);
        Assert.assertEquals(variables.getErrorMessage(), "Form must have a field 'message_type'");
    }

    @Test(description = "Send request without value to message_type and check that return error message (error code: 1015)")
    @Description("Send request without value to message_type and check that return error message (error code: 1015)")
    public void t09_errorMessage1015() throws IOException {
        apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barCenter.jpg"), "image/jpg", "message_type", "", apiResponse.CLIENT_REQUEST_LIVENESS);

        Assert.assertFalse(variables.isSuccess());
        Assert.assertEquals(variables.getErrorCode(), 1015);
        Assert.assertEquals(variables.getErrorMessage(), "Specified 'message_type' is not one of the allowed: frame_request,report_failure,audio_chunk_request,report_stage_ending,webhook_done_check,video_request,part_video_request");
    }
}
