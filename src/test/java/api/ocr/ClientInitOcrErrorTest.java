package api.ocr;

import api.ApiResponse;
import api.Variables;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.TestUtils;
public class ClientInitOcrErrorTest {

    ApiResponse apiResponse = new ApiResponse();
    Variables variables = new Variables();

    @Test(description = "ErrorCode: 1008")
    @Description("Send request without json and check that return error message (errorCode: 1008)")
    public void t01_clientInitError1008Code() {
        apiResponse.clientInitResponse(variables, "application/json", "", "", "", "", "", "", apiResponse.CLIENT_INIT_OCR);

        Assert.assertFalse(variables.isSuccess());
        Assert.assertEquals(variables.getErrorCode(), 1008);
        Assert.assertEquals(variables.getErrorMessage(), "'caseId' was not supplied");
    }

    @Test(description = "ErrorCode: 1011")
    @Description("Send request with wrong libraryName and check that return error message (errorCode: 1011)")
    public void t02_clientInitError1011Code() {
        apiResponse.clientInitResponse(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "TEST", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR);

        Assert.assertFalse(variables.isSuccess());
        Assert.assertEquals(variables.getErrorCode(), 1011);
        Assert.assertEquals(variables.getErrorMessage(), "Non-legit libraryName supplied");
    }



}
