package api.liveness.newVersion.mathilda.environmentVariables;

import api.ApiResponse;
import api.Variables;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;

public class TestMinimumTokenDuration {

    ApiResponse apiResponse;
    Variables variables;

    @BeforeMethod
    public void startSession() {
        variables = new Variables();
        apiResponse = new ApiResponse();
    }

    @Test(description = "wait 20 seconds for timeout stage")
    @Description("Wait 20 seconds for timeout stage instead 80 seconds in normal docker env variable")
    public void t01_20secondsTimeoutLIVENESS() throws InterruptedException {
        apiResponse.clientInitResponse(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "LIVENESS", "flowConfigName", "reverseStages.json", apiResponse.CLIENT_INIT_LIVENESS);
        System.out.println("Sleep for 20 seconds");
        Thread.sleep(20000);
        apiResponse.clientRequestApi("multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barCenter.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);

        Assert.assertTrue(apiResponse.clientRequest.path("success"));
    }

    @Test(description = "wait 35 seconds for timeout stage")
    @Description("Wait 35 seconds for timeout stage instead 95 seconds in normal docker env variable")
    public void t02_35secondsInvalidTokenLIVENESS() throws InterruptedException {
        apiResponse.clientInitResponse(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "LIVENESS", "flowConfigName", "reverseStages.json", apiResponse.CLIENT_INIT_LIVENESS);
        System.out.println("Sleep for 35 seconds");
        Thread.sleep(35000);
        apiResponse.clientRequestApi("multipart/form-data", "authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barCenter.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_LIVENESS);

        Assert.assertFalse(apiResponse.clientRequest.path("success"));
        Assert.assertEquals(apiResponse.clientRequest.path("errorCode").toString(), "1006");
        Assert.assertEquals(apiResponse.clientRequest.path("errorMessage"), "Invalid token");
    }
}
