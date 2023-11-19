package api.liveness.newVersion.mathilda.environmentVariables;

import api.ApiResponse;
import api.Variables;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class noCaseIdTest {

    ApiResponse apiResponse;
    Variables variables;

    public noCaseIdTest() {
        apiResponse = new ApiResponse();
        variables = new Variables();
    }

    @Test(description = "no caseId allow")
    @Description("No caseId allow from the liveness docker environment variable")
    public void t01_clientInitWithNoCaseIdAllowFromDocker() {
        apiResponse.clientInitResponse(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "LIVENESS", "flowConfigName", "mathilda.json", apiResponse.CLIENT_INIT_LIVENESS);

        Assert.assertFalse(variables.isSuccess());
        Assert.assertEquals(variables.getErrorCode(), 1005);
        Assert.assertEquals(apiResponse.clientInit.path("errorMessage"), "Token was not provided");
    }
}
