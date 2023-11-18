package api.ocr;

import api.ApiResponse;
import api.Variables;
import io.qameta.allure.Description;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import utilities.TestUtils;
public class ClientRequestOcrErrorTest {

    ApiResponse apiResponse = new ApiResponse();
    Variables variables = new Variables();

    @BeforeMethod
    public void startSession() {
        apiResponse.clientInitResponse(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "IL-DL", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR);
        apiResponse.verifyInitVariables(variables);
    }

    @Test(description = "Error message 1000")
    @Description("Send request without contentType and check that return error message (error code: 1000)")
    public void t01_errorMessage1000() throws IOException {
        apiResponse.mainClientResponse(variables, "multipart/form-", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barCenter.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);

        Assert.assertFalse(variables.isSuccess());
        Assert.assertEquals(variables.getErrorCode(), 1000);
        Assert.assertEquals(variables.getErrorMessage(), "Expecting a request of type 'multipart/form-data'");
    }

    @Test(description = "Error message 1001")
    @Description("Send request without file and check that return error message (error code: 1001)")
    public void t02_errorMessage1001() {
        apiResponse.clientRequestFileIsMissingError(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), apiResponse.CLIENT_REQUEST_OCR);

        Assert.assertFalse(variables.isSuccess());
        Assert.assertEquals(variables.getErrorCode(), 1001);
        Assert.assertEquals(variables.getErrorMessage(), "Content length is either zero or not existent");
    }

    @Test(description = "Error message 1002")
    @Description("Send request without file and check that return error message (error code: 1002)")
    public void t03_errorMessage1002() throws IOException {
        apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/Blank.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);

        Assert.assertFalse(variables.isSuccess());
        Assert.assertEquals(variables.getErrorCode(), 1002);
        Assert.assertEquals(variables.getErrorMessage(), "File content was found to be empty");
    }

    @Test(description = "Error message 1005")
    @Description("Send request without token in header and check that return error message (error code: 1005)")
    public void t04_errorMessage1005() throws IOException {
        apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", " ", "frame", new File("./liveness/apiPic/barCenter.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);

        Assert.assertFalse(variables.isSuccess());
        Assert.assertEquals(variables.getErrorCode(), 1005);
        Assert.assertEquals(variables.getErrorMessage(), "Token was not provided");
    }

    @Test(description = "Error message 1006 normal")
    @Description("Send request with wrong token and check that return error message (error code: 1006)")
    public void t05_errorMessage1006_normal() throws IOException {
        apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Barear ", "frame", new File("./liveness/apiPic/barCenter.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);

        Assert.assertFalse(variables.isSuccess());
        Assert.assertEquals(variables.getErrorCode(), 1006);
        Assert.assertEquals(variables.getErrorMessage(), "Invalid token");
    }

 //   @Test(description = "Error message 1006 timeout")
    @Description("Send request with expired token and check that return error message (error code: 1006)")
    public void t05_errorMessage1006_timeout() throws IOException, InterruptedException {
        apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Barear ", "frame", new File("./ocr/bioID/moran/cardNotFound/cardNotFound.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        Thread.sleep(105000);
        Response r=apiResponse.clientRequestApi( "multipart/form-data", "scanovate-auth", "Bearer " + variables.getToken(), "frame", new File("./ocr/bioID/moran/cardNotFound/cardNotFound.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        JsonPath jsonPath = r.jsonPath();
        System.out.println(r.getBody().asString());
        Assert.assertEquals(jsonPath.getInt("errorCode"),1006);
        Assert.assertEquals(jsonPath.getString("errorMessage"),"Invalid token");
    }

    @Test(description = "Error message 1009")
    @Description("Send request without frame and check that return error message (error code: 1009)")
    public void t06_errorMessage1009() {
        apiResponse.clientRequestFileIsMissing(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);

        Assert.assertFalse(variables.isSuccess());
        Assert.assertEquals(variables.getErrorCode(), 1009);
        Assert.assertEquals(variables.getErrorMessage(), "No frame received");
    }

    @Test(description = "Error message 1013")
    @Description("Send request with error in multipart form and check that return error message (error code: 1013)")
    public void t07_errorMessage1013() throws IOException {
        apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barCenter.jpg"), "image/jpg", "message_type", "report_failure", apiResponse.CLIENT_REQUEST_OCR);

        Assert.assertFalse(variables.isSuccess());
        Assert.assertEquals(variables.getErrorCode(), 1013);
        Assert.assertEquals(variables.getErrorMessage(), "Error reporting wrong structure, correct structure: {errorCode: number, errorMessage: string}");
    }

    @Test(description = "Error message 1014")
    @Description("Send request without message_type and check that return error message (error code: 1014)")
    public void t08_errorMessage1014() throws IOException {
        apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barCenter.jpg"), "image/jpg", "", "", apiResponse.CLIENT_REQUEST_OCR);

        Assert.assertFalse(variables.isSuccess());
        Assert.assertEquals(variables.getErrorCode(), 1014);
        Assert.assertEquals(variables.getErrorMessage(), "Form must have a field 'message_type'");
    }

    @Test(description = "Error message 1015")
    @Description("Send request without value to message_type and check that return error message (error code: 1015)")
    public void t09_errorMessage1015() throws IOException {
        apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barCenter.jpg"), "image/jpg", "message_type", "", apiResponse.CLIENT_REQUEST_OCR);

        Assert.assertFalse(variables.isSuccess());
        Assert.assertEquals(variables.getErrorCode(), 1015);
        Assert.assertEquals(variables.getErrorMessage(), "Specified 'message_type' is not one of the allowed: frame_request,report_failure,audio_chunk_request,report_stage_ending,webhook_done_check,video_request,part_video_request");
    }
}
