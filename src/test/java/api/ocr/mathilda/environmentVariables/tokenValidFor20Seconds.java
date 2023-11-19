package api.ocr.mathilda.environmentVariables;

import api.ApiResponse;
import api.Variables;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import utilities.TestUtils;
public class tokenValidFor20Seconds {

    ApiResponse apiResponse;
    Variables variables;

    @BeforeMethod
    public void startSession() {
        variables = new Variables();
        apiResponse = new ApiResponse();
    }

    @Test(description = "wait 20 seconds for timeout stage in IL-ID library")
    @Description("Wait 20 seconds in IL-ID library for timeout stage instead 80 seconds in normal docker env variable")
    public void t01_20secondsTimeoutILID() throws InterruptedException {
        apiResponse.clientInitResponse(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "IL-ID", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR);
        System.out.println("Sleep for 20 seconds");
        Thread.sleep(20000);
        apiResponse.clientRequestApi("multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/bioID/nitzan/front/NitzanBioFront1.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);

        Assert.assertEquals(apiResponse.clientRequest.path("status"), "failure");
        Assert.assertEquals(apiResponse.clientRequest.path("stages[0].status"), "timeout");
    }

    @Test(description = "wait 20 seconds for timeout stage in IL-DL library")
    @Description("Wait 20 seconds in IL-DL library for timeout stage instead 80 seconds in normal docker env variable")
    public void t02_20secondsTimeoutILDL() throws InterruptedException {
        apiResponse.clientInitResponse(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "IL-DL", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR);
        System.out.println("Sleep for 20 seconds");
        Thread.sleep(20000);
        apiResponse.clientRequestApi("multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/dl/oldDLFront.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);

        Assert.assertEquals(apiResponse.clientRequest.path("status"), "failure");
        Assert.assertEquals(apiResponse.clientRequest.path("stages[0].status"), "timeout");
    }

    @Test(description = "wait 20 seconds for timeout stage in MRZ library")
    @Description("Wait 20 seconds in MRZ library for timeout stage instead 80 seconds in normal docker env variable")
    public void t03_20secondsTimeoutMRZ() throws InterruptedException {
        apiResponse.clientInitResponse(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "MRZ", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR);
        System.out.println("Sleep for 20 seconds");
        Thread.sleep(20000);
        apiResponse.clientRequestApi("multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/mrz/avner/avner2.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);

        Assert.assertEquals(apiResponse.clientRequest.path("status"), "failure");
        Assert.assertEquals(apiResponse.clientRequest.path("stages[0].status"), "timeout");
    }

 //   @Test(description = "wait 20 seconds for timeout stage in PHC library")
    @Description("Wait 20 seconds in PHC library for timeout stage instead 80 seconds in normal docker env variable")
    public void t04_20secondsTimeoutPHC() throws InterruptedException {
        apiResponse.clientInitResponse(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "PHL-CHEQUES", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR);
        System.out.println("Sleep for 20 seconds");
        Thread.sleep(20000);
        apiResponse.clientRequestApi("multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/cheque/PhilippinesCheque/UCPBChueqe.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);

        Assert.assertEquals(apiResponse.clientRequest.path("status"), "failure");
        Assert.assertEquals(apiResponse.clientRequest.path("stages[0].status"), "timeout");
    }

    @Test(description = "wait 20 seconds for timeout stage in CNI library")
    @Description("Wait 20 seconds in CNI library for timeout stage instead 80 seconds in normal docker env variable")
    public void t05_20secondsTimeoutCNI() throws InterruptedException {
        apiResponse.clientInitResponse(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "CNI", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR);
        System.out.println("Sleep for 20 seconds");
        Thread.sleep(20000);
        apiResponse.clientRequestApi("multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/cni/good_fr_3.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);

        Assert.assertEquals(apiResponse.clientRequest.path("status"), "failure");
        Assert.assertEquals(apiResponse.clientRequest.path("stages[0].status"), "timeout");
    }

 //   @Test(description = "wait 20 seconds for timeout stage in DK-DL library")
    @Description("Wait 20 seconds in DK-DL library for timeout stage instead 80 seconds in normal docker env variable")
    public void t06_20secondsTimeoutDKDL() throws InterruptedException {
        apiResponse.clientInitResponse(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "DK-DL", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR);
        System.out.println("Sleep for 20 seconds");
        Thread.sleep(20000);
        apiResponse.clientRequestApi("multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/denmarkDrivingLicense/newDKDL/314764922.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);

        Assert.assertEquals(apiResponse.clientRequest.path("status"), "failure");
        Assert.assertEquals(apiResponse.clientRequest.path("stages[0].status"), "timeout");
    }

    @Test(description = "wait 35 seconds for timeout stage in IL-ID library")
    @Description("Wait 35 seconds in IL-ID library for timeout stage instead 95 seconds in normal docker env variable")
    public void t07_35secondsInvalidTokenILID() throws InterruptedException {
        apiResponse.clientInitResponse(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "IL-ID", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR);
        System.out.println("Sleep for 35 seconds");
        Thread.sleep(35000);
        apiResponse.clientRequestApi("multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/bioID/nitzan/front/NitzanBioFront1.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);

        Assert.assertFalse(apiResponse.clientRequest.path("success"));
        Assert.assertEquals(apiResponse.clientRequest.path("errorCode").toString(), "1006");
        Assert.assertEquals(apiResponse.clientRequest.path("errorMessage"), "Invalid token");
    }

    @Test(description = "wait 35 seconds for timeout stage in ID-DL library")
    @Description("Wait 35 seconds in IL-DL library for timeout stage instead 95 seconds in normal docker env variable")
    public void t08_35secondsInvalidTokenILDL() throws InterruptedException {
        apiResponse.clientInitResponse(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "IL-DL", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR);
        System.out.println("Sleep for 35 seconds");
        Thread.sleep(35000);
        apiResponse.clientRequestApi("multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/dl/oldDLFront.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);

        Assert.assertFalse(apiResponse.clientRequest.path("success"));
        Assert.assertEquals(apiResponse.clientRequest.path("errorCode").toString(), "1006");
        Assert.assertEquals(apiResponse.clientRequest.path("errorMessage"), "Invalid token");
    }

    @Test(description = "wait 35 seconds for timeout stage in MRZ library")
    @Description("Wait 35 seconds in MRZ library for timeout stage instead 95 seconds in normal docker env variable")
    public void t09_35secondsInvalidTokenMRZ() throws InterruptedException {
        apiResponse.clientInitResponse(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "MRZ", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR);
        System.out.println("Sleep for 35 seconds");
        Thread.sleep(35000);
        apiResponse.clientRequestApi("multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/mrz/avner/avner2.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);

        Assert.assertFalse(apiResponse.clientRequest.path("success"));
        Assert.assertEquals(apiResponse.clientRequest.path("errorCode").toString(), "1006");
        Assert.assertEquals(apiResponse.clientRequest.path("errorMessage"), "Invalid token");
    }

    @Test(description = "wait 35 seconds for timeout stage in PHC library")
    @Description("Wait 35 seconds in PHC library for timeout stage instead 95 seconds in normal docker env variable")
    public void t10_35secondsInvalidTokenPHC() throws InterruptedException {
        apiResponse.clientInitResponse(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "MRZ", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR);
        System.out.println("Sleep for 35 seconds");
        Thread.sleep(35000);
        apiResponse.clientRequestApi("multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/cheque/PhilippinesCheque/UCPBChueqe.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);

        Assert.assertFalse(apiResponse.clientRequest.path("success"));
        Assert.assertEquals(apiResponse.clientRequest.path("errorCode").toString(), "1006");
        Assert.assertEquals(apiResponse.clientRequest.path("errorMessage"), "Invalid token");
    }

    @Test(description = "wait 35 seconds for timeout stagein CNI library")
    @Description("Wait 35 seconds in CNI library for timeout stage instead 95 seconds in normal docker env variable")
    public void t11_35secondsInvalidTokenCNI() throws InterruptedException {
        apiResponse.clientInitResponse(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "CNI", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR);
        System.out.println("Sleep for 35 seconds");
        Thread.sleep(35000);
        apiResponse.clientRequestApi("multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/cni/good_fr_3.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);

        Assert.assertFalse(apiResponse.clientRequest.path("success"));
        Assert.assertEquals(apiResponse.clientRequest.path("errorCode").toString(), "1006");
        Assert.assertEquals(apiResponse.clientRequest.path("errorMessage"), "Invalid token");
    }

    @Test(description = "wait 35 seconds for timeout stage in DK-DL library")
    @Description("Wait 35 seconds in DK-DL library for timeout stage instead 95 seconds in normal docker env variable")
    public void t12_35secondsInvalidTokenDKDL() throws InterruptedException {
        apiResponse.clientInitResponse(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "DK-DL", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR);
        System.out.println("Sleep for 35 seconds");
        Thread.sleep(35000);
        apiResponse.clientRequestApi("multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/denmarkDrivingLicense/newDKDL/314764922.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);

        Assert.assertFalse(apiResponse.clientRequest.path("success"));
        Assert.assertEquals(apiResponse.clientRequest.path("errorCode").toString(), "1006");
        Assert.assertEquals(apiResponse.clientRequest.path("errorMessage"), "Invalid token");
    }
}
