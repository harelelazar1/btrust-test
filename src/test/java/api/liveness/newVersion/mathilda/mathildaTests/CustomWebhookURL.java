package api.liveness.newVersion.mathilda.mathildaTests;

import api.ApiResponse;
import api.Variables;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

public class CustomWebhookURL {

    ApiResponse apiResponse = new ApiResponse();
    Variables variables = new Variables();

    @Test(description = "liveness mathilda test of custom webhook url from the config file")
    @Description("Mathilda test of custom webhook url from the config file when choosing liveness")
    public void t01_LivenessCustomWebhookUrl() {
        apiResponse.clientInitResponse(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "IL-ID", "flowConfigName", "configWebhook.json", apiResponse.CLIENT_INIT_LIVENESS);
    }
}
