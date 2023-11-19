package api.liveness.newVersion;

import api.Variables;
import api.ApiResponse;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ClientInitLivenessErrorTest {

    ApiResponse apiResponse;
    Variables variables;

    @BeforeMethod
    public void startSession() {
        variables = new Variables();
        apiResponse = new ApiResponse();
    }

    @Test(description = "Send request without json and check that return error message (errorCode: 1008)")
    @Description("Send request without json and check that return error message (errorCode: 1008)")
    public void t01_clientInitError1008Code() {
        apiResponse.clientInitResponse(variables, "application/json", "", "", "", "", "", "", apiResponse.CLIENT_INIT_LIVENESS);

        Assert.assertFalse(variables.isSuccess());
        Assert.assertEquals(variables.getErrorCode(), 1008);
        Assert.assertEquals(variables.getErrorMessage(), "'caseId' was not supplied");
    }

    @Test(description = "Send request with wrong libraryName and check that return error message (errorCode: 1011)")
    @Description("Send request with wrong libraryName and check that return error message (errorCode: 1011)")
    public void t02_clientInitError1011Code() {
        apiResponse.clientInitResponse(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "TEST", "flowConfigName", "mathilda.json", apiResponse.CLIENT_INIT_LIVENESS);

        Assert.assertFalse(variables.isSuccess());
        Assert.assertEquals(variables.getErrorCode(), 1011);
        Assert.assertEquals(variables.getErrorMessage(), "Non-legit libraryName supplied");
    }

    @Test(description = "Send request with not supported library and check that return error message (errorCode: 2018)")
    @Description("Send request with not supported library and check that return error message (errorCode: 2018)")
    public void t03_clientInitError2018Code() {
        apiResponse.clientInitResponse(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "IL-ID", "flowConfigName", "mathilda.json", apiResponse.CLIENT_INIT_LIVENESS);

        Assert.assertFalse(variables.isSuccess());
        Assert.assertEquals(variables.getErrorCode(), 2018);
        Assert.assertEquals(variables.getErrorMessage(), "No initiated library found for service 'IL-ID', or license is not given for this service");
    }
}
