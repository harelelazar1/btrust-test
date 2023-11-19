package api.ocr.mathilda.mathildaTests;

import api.ApiResponse;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.TestUtils;
public class cameraFacingMode {

    ApiResponse apiResponse;

    @BeforeMethod
    public void startSession() {
        apiResponse = new ApiResponse();
    }

    @Test(description = "il id library verification camera facing mode")
    @Description("IL-ID library verification camera facing mode - 'face'.")
    public void t01_il_idCameraFacingMode() {
        apiResponse.clientInitRequest("application/json", "caseId", apiResponse.randomString(), "libraryName", "IL-ID", "flowConfigName", "audioCardRatioCameraFacingMode.json", apiResponse.CLIENT_INIT_OCR);

        Assert.assertEquals(apiResponse.clientInit.path("cameraFacingMode"), "face");
    }

    @Test(description = "il dl library verification camera facing mode")
    @Description("IL-DL library verification camera facing mode - 'face'.")
    public void t02_il_dlCameraFacingMode() {
        apiResponse.clientInitRequest("application/json", "caseId", apiResponse.randomString(), "libraryName", "IL-DL", "flowConfigName", "audioCardRatioCameraFacingMode.json", apiResponse.CLIENT_INIT_OCR);

        Assert.assertEquals(apiResponse.clientInit.path("cameraFacingMode"), "face");
    }


    @Test(description = "mrz library verification camera facing mode")
    @Description("MRZ library verification camera facing mode - 'face'.")
    public void t04_mrzCameraFacingMode() {
        apiResponse.clientInitRequest("application/json", "caseId", apiResponse.randomString(), "libraryName", "MRZ", "flowConfigName", "audioCardRatioCameraFacingMode.json", apiResponse.CLIENT_INIT_OCR);

        Assert.assertEquals(apiResponse.clientInit.path("cameraFacingMode"), "face");
    }


    @Test(description = "cni library verification camera facing mode")
    @Description("CNI library verification camera facing mode - 'environment'.")
    public void t06_cniCameraFacingMode() {
        apiResponse.clientInitRequest("application/json", "caseId", apiResponse.randomString(), "libraryName", "CNI", "flowConfigName", "audioCardRatioCameraFacingMode.json", apiResponse.CLIENT_INIT_OCR);

        Assert.assertEquals(apiResponse.clientInit.path("cameraFacingMode"), "environment");
    }
}
