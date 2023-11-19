package api.ocr.mathilda.mathildaTests;

import api.ApiResponse;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.TestUtils;
public class CardRatio {

    ApiResponse apiResponse;

    @BeforeMethod
    public void startSession() {
        apiResponse = new ApiResponse();
    }

    @Test(description = "il id library setting new value to card ratio")
    @Description("Setting new value to card ratio (to 10.5) in IL-ID library ")
    public void t01_il_idCardRation() {
        apiResponse.clientInitRequest("application/json", "caseId", apiResponse.randomString(), "libraryName", "IL-ID", "flowConfigName", "audioCardRatioCameraFacingMode.json", apiResponse.CLIENT_INIT_OCR);

        Assert.assertEquals(apiResponse.clientInit.path("cardRatio").toString(), "10.5");
    }

    @Test(description = "il dl library setting new value to card ratio")
    @Description("Setting new value to card ratio (to 10.5) in IL-DL library ")
    public void t02_il_dlCardRation() {
        apiResponse.clientInitRequest("application/json", "caseId", apiResponse.randomString(), "libraryName", "IL-DL", "flowConfigName", "audioCardRatioCameraFacingMode.json", apiResponse.CLIENT_INIT_OCR);

        Assert.assertEquals(apiResponse.clientInit.path("cardRatio").toString(), "10.5");
    }


    @Test(description = "mrz library setting new value to card ratio")
    @Description("Setting new value to card ratio (to 10.5) in MRZ library ")
    public void t04_mrzCardRation() {
        apiResponse.clientInitRequest("application/json", "caseId", apiResponse.randomString(), "libraryName", "MRZ", "flowConfigName", "audioCardRatioCameraFacingMode.json", apiResponse.CLIENT_INIT_OCR);

        Assert.assertEquals(apiResponse.clientInit.path("cardRatio").toString(), "10.5");
    }


    @Test(description = "cni library setting new value to card ratio")
    @Description("Setting new value to card ratio (to 10.5) in CNI library ")
    public void t06_cniCardRation() {
        apiResponse.clientInitRequest("application/json", "caseId", apiResponse.randomString(), "libraryName", "CNI", "flowConfigName", "audioCardRatioCameraFacingMode.json", apiResponse.CLIENT_INIT_OCR);

        Assert.assertEquals(apiResponse.clientInit.path("cardRatio").toString(), "10.5");
    }
}
