package api.liveness.newVersion;

import api.Variables;
import api.ApiResponse;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class ErrorhandlingTest {

    ApiResponse apiResponse;
    Variables variables;

    @BeforeMethod
    public void initSession() {
        variables = new Variables();
        apiResponse = new ApiResponse();
        apiResponse.clientInitResponse(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "LIVENESS", "flowConfigName", "mathilda.json", apiResponse.CLIENT_INIT_LIVENESS);
        apiResponse.verifyInitVariables(variables);

        Assert.assertNotNull(variables.getToken());
    }

    @Test(description = "Injecting bad liveness image to see error message 154 - face too close")
    @Description("Injecting bad liveness image to see error message 154 - face too close")
    public void t01_faceTooCloseError154Message() throws IOException {
        apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/face_too_close.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);

        Assert.assertEquals(variables.getActionType(), "message");
        Assert.assertEquals(variables.getMessageId(), 154);
        Assert.assertEquals(variables.getMessageText(), "Face too close");
        Assert.assertTrue(variables.isSuccess());
    }

    @Test(description = "Injecting bad liveness image to see error message 156 - no face found")
    @Description("Injecting bad liveness image to see error message 156 - no face found")
    public void t02_noFaceFoundError156Message() throws IOException {
        apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/face_not_found.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);

        Assert.assertEquals(variables.getActionType(), "message");
        Assert.assertEquals(variables.getMessageId(), 156);
        Assert.assertEquals(variables.getMessageText(), "No face found");
        Assert.assertTrue(variables.isSuccess());
    }

    //TODO: bug https://trello.com/c/PGGBY5fP
    @Test(description = "Injecting bad liveness image to see error message 159 - Face too small")
    @Description("Injecting bad liveness image to see error message 159 - Face too small")
    public void t03_comeCloserError159Message() throws IOException {
        apiResponse.mainClientResponse(variables, "multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/too_small.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);

        Assert.assertEquals(variables.getActionType(), "message");
        Assert.assertEquals(variables.getMessageId(), 159);
        Assert.assertEquals(variables.getMessageText(), "Face too small");
        Assert.assertTrue(variables.isSuccess());
    }

}
