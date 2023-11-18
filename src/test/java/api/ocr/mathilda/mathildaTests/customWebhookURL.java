package api.ocr.mathilda.mathildaTests;

import api.ApiResponse;
import api.Variables;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.TestUtils;
public class customWebhookURL {

    ApiResponse apiResponse;
    Variables variables;

    @BeforeMethod
    public void initSession() {
        variables = new Variables();
        apiResponse = new ApiResponse();
    }

    @AfterMethod
    public void verifyWebhookUrlChanged() {
        Assert.assertFalse(variables.isSuccess());
        Assert.assertEquals(variables.getErrorCode(), 2007);
        Assert.assertEquals(variables.getErrorMessage(), "Failed to reach webhook");
    }

    @Test(description = "il id mathilda test of custom webhook url from the config file")
    @Description("Mathilda test of custom webhook url from the config file when choosing IL-ID ocr type")
    public void t01_ILIDCustomWebhookUrl() {
        apiResponse.clientInitResponse(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "IL-ID", "flowConfigName", "configWebhook.json", apiResponse.CLIENT_INIT_OCR);
    }

    @Test(description = "il dl mathilda test of custom webhook url from the config file")
    @Description("Mathilda test of custom webhook url from the config file when choosing IL-DL ocr type")
    public void t02_ILDLCustomWebhookUrl() {
        apiResponse.clientInitResponse(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "IL-DL", "flowConfigName", "configWebhook.json", apiResponse.CLIENT_INIT_OCR);
    }

    @Test(description = "cni mathilda test of custom webhook url from the config file")
    @Description("Mathilda test of custom webhook url from the config file when choosing CNI ocr type")
    public void t03_CNICustomWebhookUrl() {
        apiResponse.clientInitResponse(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "CNI", "flowConfigName", "configWebhook.json", apiResponse.CLIENT_INIT_OCR);
    }

    @Test(description = "mrz mathilda test of custom webhook url from the config file")
    @Description("Mathilda test of custom webhook url from the config file when choosing MRZ ocr type")
    public void t04_MRZCustomWebhookUrl() {
        apiResponse.clientInitResponse(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "MRZ", "flowConfigName", "configWebhook.json", apiResponse.CLIENT_INIT_OCR);
    }


}
