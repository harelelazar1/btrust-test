package api.liveness.newVersion.mathilda.environmentVariables;

import api.ApiResponse;
import api.Variables;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LivenessLicenseDisableTest {

    Variables variables;
    ApiResponse apiResponse;

    public LivenessLicenseDisableTest() {
        apiResponse = new ApiResponse();
        variables = new Variables();
    }

    @Test(description = "liveness with english stt")
    @Description("liveness with english stt as language and sentence")
    public void t01_livenessLicenseDisabled() {
        apiResponse.clientInitResponse(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "LIVENESS", "flowConfigName", "mathilda.json", apiResponse.CLIENT_INIT_LIVENESS);

        Assert.assertFalse(variables.isSuccess());
        Assert.assertEquals(variables.getErrorCode(), 2018);
        Assert.assertEquals(apiResponse.clientInit.path("errorMessage"), "No initiated library found for service 'LIVENESS', or license is not given for this service");
    }
}
