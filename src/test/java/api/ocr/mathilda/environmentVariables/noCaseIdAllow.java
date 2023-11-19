package api.ocr.mathilda.environmentVariables;

import api.ApiResponse;
import api.Variables;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.TestUtils;
public class noCaseIdAllow {

    ApiResponse apiResponse;
    Variables variables;

    @BeforeMethod
    public void initSession() {
        variables = new Variables();
        apiResponse = new ApiResponse();
    }

    @Test(description = "no caseId allow")
    @Description("No caseId allow from the ocr docker environment variable IL-ID library")
    public void t01_clientInitWithNoCaseIdAllowFromDockerILID() {
        apiResponse.clientInitResponse(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "IL-ID", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR);

        Assert.assertFalse(variables.isSuccess());
        Assert.assertEquals(variables.getErrorCode(), 1005);
        Assert.assertEquals(apiResponse.clientInit.path("errorMessage"), "Token was not provided");
    }

    @Test(description = "no caseId allow")
    @Description("No caseId allow from the ocr docker environment variable IL-DL library")
    public void t02_clientInitWithNoCaseIdAllowFromDockerILDL() {
        apiResponse.clientInitResponse(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "IL-DL", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR);

        Assert.assertFalse(variables.isSuccess());
        Assert.assertEquals(variables.getErrorCode(), 1005);
        Assert.assertEquals(apiResponse.clientInit.path("errorMessage"), "Token was not provided");
    }

    @Test(description = "no caseId allow")
    @Description("No caseId allow from the ocr docker environment variable CNI library")
    public void t03_clientInitWithNoCaseIdAllowFromDockerCNI() {
        apiResponse.clientInitResponse(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "CNI", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR);

        Assert.assertFalse(variables.isSuccess());
        Assert.assertEquals(variables.getErrorCode(), 1005);
        Assert.assertEquals(apiResponse.clientInit.path("errorMessage"), "Token was not provided");
    }

    @Test(description = "no caseId allow")
    @Description("No caseId allow from the ocr docker environment variable CNI library")
    public void t04_clientInitWithNoCaseIdAllowFromDockerMRZ() {
        apiResponse.clientInitResponse(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "MRZ", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR);

        Assert.assertFalse(variables.isSuccess());
        Assert.assertEquals(variables.getErrorCode(), 1005);
        Assert.assertEquals(apiResponse.clientInit.path("errorMessage"), "Token was not provided");
    }

 //   @Test(description = "no caseId allow")
    @Description("No caseId allow from the ocr docker environment variable DK-DL library")
    public void t05_clientInitWithNoCaseIdAllowFromDockerDKDL() {
        apiResponse.clientInitResponse(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "DK-DL", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR);

        Assert.assertFalse(variables.isSuccess());
        Assert.assertEquals(variables.getErrorCode(), 1005);
        Assert.assertEquals(apiResponse.clientInit.path("errorMessage"), "Token was not provided");
    }

 //   @Test(description = "no caseId allow")
    @Description("No caseId allow from the ocr docker environment variable PHC library")
    public void t06_clientInitWithNoCaseIdAllowFromDockerPHC() {
        apiResponse.clientInitResponse(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "PHL-CHEQUES", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR);

        Assert.assertFalse(variables.isSuccess());
        Assert.assertEquals(variables.getErrorCode(), 1005);
        Assert.assertEquals(apiResponse.clientInit.path("errorMessage"), "Token was not provided");
    }
}
