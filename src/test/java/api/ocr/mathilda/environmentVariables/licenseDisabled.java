package api.ocr.mathilda.environmentVariables;

import api.ApiResponse;
import api.Variables;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.TestUtils;
public class licenseDisabled {

    ApiResponse apiResponse;
    Variables variables;

    @BeforeMethod
    public void startSession() {
        apiResponse = new ApiResponse();
        variables = new Variables();
    }

    @Test(description = "IL-ID ocr license disabled")
    @Description("IL-ID ocr license disabled")
    public void t01_iLiD_licenseDisabled() {
        apiResponse.clientInitResponse(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "IL-ID", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR);

        Assert.assertFalse(variables.isSuccess());
        Assert.assertEquals(variables.getErrorCode(), 2018);
        Assert.assertEquals(apiResponse.clientInit.path("errorMessage"), "No initiated library found for service 'IL-ID', or license is not given for this service");
    }

    @Test(description = "IL-DL ocr license disabled")
    @Description("IL-DL ocr license disabled")
    public void t02_iLdL_licenseDisabled() {
        apiResponse.clientInitResponse(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "IL-DL", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR);

        Assert.assertFalse(variables.isSuccess());
        Assert.assertEquals(variables.getErrorCode(), 2018);
        Assert.assertEquals(apiResponse.clientInit.path("errorMessage"), "No initiated library found for service 'IL-DL', or license is not given for this service");
    }

    @Test(description = "CNI ocr license disabled")
    @Description("CNI ocr license disabled")
    public void t03_cni_licenseDisabled() {
        apiResponse.clientInitResponse(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "CNI", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR);

        Assert.assertFalse(variables.isSuccess());
        Assert.assertEquals(variables.getErrorCode(), 2018);
        Assert.assertEquals(apiResponse.clientInit.path("errorMessage"), "No initiated library found for service 'CNI', or license is not given for this service");
    }

    @Test(description = "DK-DL ocr license disabled")
    @Description("DK-DL ocr license disabled")
    public void t04_dKdL_licenseDisabled() {
        apiResponse.clientInitResponse(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "DK-DL", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR);

        Assert.assertFalse(variables.isSuccess());
        Assert.assertEquals(variables.getErrorCode(), 2018);
        Assert.assertEquals(apiResponse.clientInit.path("errorMessage"), "No initiated library found for service 'DK-DL', or license is not given for this service");
    }

    @Test(description = "PHC ocr license disabled")
    @Description("PHC ocr license disabled")
    public void t05_phc_licenseDisabled() {
        apiResponse.clientInitResponse(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "PHL-CHEQUES", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR);

        Assert.assertFalse(variables.isSuccess());
        Assert.assertEquals(variables.getErrorCode(), 2018);
        Assert.assertEquals(apiResponse.clientInit.path("errorMessage"), "No initiated library found for service 'PHL-CHEQUES', or license is not given for this service");
    }

}
