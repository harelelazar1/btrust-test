package api.ocr.singleFrame;

import api.ApiResponse;
import api.Variables;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;

import utilities.MongoDB.MongoHandler;
import utilities.TestUtils;
public class singleFrameRequestErrorTests {

    Variables variables;
    ApiResponse apiResponse;


    @BeforeClass
    public void resetVariables() {
        variables = new Variables();
        apiResponse = new ApiResponse();
    }

    @Test
    public void t01_error1001() {
        apiResponse.clientRequestFileIsMissingError(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(),apiResponse.SINGLE_FRAME_OCR);

        Assert.assertEquals(apiResponse.clientRequest.jsonPath().getBoolean("success"), false);
        Assert.assertEquals(apiResponse.clientRequest.jsonPath().getInt("errorCode"), 1001);
        Assert.assertEquals(apiResponse.clientRequest.jsonPath().getString("errorMessage"), "Content length is either zero or not existent");
    }

    @Test
    public void t02_error1008() {
        apiResponse.clientRequest = apiResponse.singleFrameRequest("library_name", "IL-ID", "case_id", "", "frame", new File("./ocr/mrz/guy/guyMrz.jpg"), "image/jpg",apiResponse.SINGLE_FRAME_OCR);

        Assert.assertEquals(apiResponse.clientRequest.jsonPath().getBoolean("success"), false);
        Assert.assertEquals(apiResponse.clientRequest.jsonPath().getInt("errorCode"), 1008);
        Assert.assertEquals(apiResponse.clientRequest.jsonPath().getString("errorMessage"), "'caseId' was not supplied");
    }

    @Test
    public void t03_error1011() {
        apiResponse.clientRequest = apiResponse.singleFrameRequest("library_name", "", "case_id", apiResponse.randomString(), "frame", new File("./ocr/mrz/guy/guyMrz.jpg"), "image/jpg",apiResponse.SINGLE_FRAME_OCR);

        Assert.assertEquals(apiResponse.clientRequest.jsonPath().getBoolean("success"), false);
        Assert.assertEquals(apiResponse.clientRequest.jsonPath().getInt("errorCode"), 1011);
        Assert.assertEquals(apiResponse.clientRequest.jsonPath().getString("errorMessage"), "Non-legit libraryName supplied");
    }

    @Test
    public void t04_error2018() {
        apiResponse.clientRequest = apiResponse.singleFrameRequest("library_name", "LIVENESS", "case_id", apiResponse.randomString(), "frame", new File("./ocr/mrz/guy/guyMrz.jpg"), "image/jpg",apiResponse.SINGLE_FRAME_OCR);

        Assert.assertEquals(apiResponse.clientRequest.jsonPath().getBoolean("success"), false);
        Assert.assertEquals(apiResponse.clientRequest.jsonPath().getInt("errorCode"), 2018);
        Assert.assertEquals(apiResponse.clientRequest.jsonPath().getString("errorMessage"), "No initiated library found for service 'LIVENESS', or license is not given for this service");
    }

    @Test
    public void t05_errorMessage101() {
        apiResponse.singleFrameRequest("library_name", "IL-DL", "case_id", apiResponse.randomString(), "frame", new File("./ocr/blueID/liad/blueID_color1.jpg"), "image/jpg",apiResponse.SINGLE_FRAME_OCR);
        apiResponse.singleFrameMainResponse(apiResponse.clientRequest, variables);

        Assert.assertEquals(variables.getStatusReason(), "No card detected");
        Assert.assertEquals(variables.getErrorCode(), 101);
        Assert.assertEquals(variables.getStageStatus(), "failure");
    }

    @Test
    public void t06_errorMessage104() {
        apiResponse.singleFrameRequest("library_name", "MRZ", "case_id", apiResponse.randomString(), "frame", new File("./ocr/blueID/liad/blueID_color1.jpg"), "image/jpg",apiResponse.SINGLE_FRAME_OCR);
        apiResponse.singleFrameMainResponse(apiResponse.clientRequest, variables);

        Assert.assertEquals(variables.getStatusReason(), "Card too small");
        Assert.assertEquals(variables.getErrorCode(), 104);
        Assert.assertEquals(variables.getStageStatus(), "failure");
    }
}
