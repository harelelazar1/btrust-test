package api.ocr.mathilda.mathildaTests;

import api.ApiResponse;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.TestUtils;
public class Audio {

    ApiResponse apiResponse;

    @BeforeMethod
    public void startSession() {
        apiResponse = new ApiResponse();
    }

    @Test(description = "il id with audio support 'on'.")
    @Description("IL-ID library with audio support 'on'.")
    public void t01_il_idAudioOn() {
        apiResponse.clientInitRequest("application/json", "caseId", apiResponse.randomString(), "libraryName", "IL-ID", "flowConfigName", "audioCardRatioCameraFacingMode.json", apiResponse.CLIENT_INIT_OCR);

        Assert.assertTrue(apiResponse.clientInit.path("audio"));
    }

    @Test(description = "il dl with audio support 'on'.")
    @Description("IL-DL library with audio support 'on'.")
    public void t02_il_dlAudioOn() {
        apiResponse.clientInitRequest("application/json", "caseId", apiResponse.randomString(), "libraryName", "IL-DL", "flowConfigName", "audioCardRatioCameraFacingMode.json", apiResponse.CLIENT_INIT_OCR);

        Assert.assertTrue(apiResponse.clientInit.path("audio"));
    }


    @Test(description = "mrz with audio support 'on'.")
    @Description("MRZ library with audio support 'on'.")
    public void t04_mrzAudioOn() {
        apiResponse.clientInitRequest("application/json", "caseId", apiResponse.randomString(), "libraryName", "MRZ", "flowConfigName", "audioCardRatioCameraFacingMode.json", apiResponse.CLIENT_INIT_OCR);

        Assert.assertTrue(apiResponse.clientInit.path("audio"));
    }


    @Test(description = "cni with audio support 'on'.")
    @Description("CNI library with audio support 'on'.")
    public void t06_cniAudioOn() {
        apiResponse.clientInitRequest("application/json", "caseId", apiResponse.randomString(), "libraryName", "CNI", "flowConfigName", "audioCardRatioCameraFacingMode.json", apiResponse.CLIENT_INIT_OCR);

        Assert.assertFalse(apiResponse.clientInit.path("audio"));
    }
}
