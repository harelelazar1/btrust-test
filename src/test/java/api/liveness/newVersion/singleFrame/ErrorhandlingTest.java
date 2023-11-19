package api.liveness.newVersion.singleFrame;

import api.ApiResponse;
import api.Variables;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;

public class ErrorhandlingTest {

    Variables variables;
    ApiResponse apiResponse;

    @BeforeMethod
    public void startSession() {
        variables = new Variables();
        apiResponse = new ApiResponse();
    }

    @Test(description = "liveness single frame with left side image")
    @Description("Sending single frame request with left side image")
    public void t01_livenessSingleFrameWithLeftSideImage() {
        apiResponse.singleFrameRequest("library_name", "LIVENESS", "case_id", apiResponse.randomString(), "frame", new File("./liveness/apiPic/barLeft.jpg"), "image/jpg", apiResponse.SINGLE_FRAME_LIVENESS);
        apiResponse.singleFrameMainResponse(apiResponse.clientRequest, variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatusReason(), "Look straight");
        Assert.assertEquals(variables.getErrorCode(), 153);
        Assert.assertEquals(variables.getStageStatus(), "failure");
    }

    @Test(description = "liveness single frame with right side image")
    @Description("Sending single frame request with right side image")
    public void t02_livenessSingleFrameWithRightSideImage() {
        apiResponse.singleFrameRequest("library_name", "LIVENESS", "case_id", apiResponse.randomString(), "frame", new File("./liveness/apiPic/barRight.jpg"), "image/jpg", apiResponse.SINGLE_FRAME_LIVENESS);
        apiResponse.singleFrameMainResponse(apiResponse.clientRequest, variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatusReason(), "Look straight");
        Assert.assertEquals(variables.getErrorCode(), 153);
        Assert.assertEquals(variables.getStageStatus(), "failure");
    }

    @Test(description = "liveness single frame with face mask")
    @Description("Sending single frame request with face mask")
    public void t03_livenessSingleFrameWithFaceMaskImage() {
        apiResponse.singleFrameRequest("library_name", "LIVENESS", "case_id", apiResponse.randomString(), "frame", new File("./liveness/apiPic/moranWithFaceMask.jpg"), "image/jpg", apiResponse.SINGLE_FRAME_LIVENESS);
        apiResponse.singleFrameMainResponse(apiResponse.clientRequest, variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatusReason(), "Face mask detected");
        Assert.assertEquals(variables.getErrorCode(), 167);
        Assert.assertEquals(variables.getStageStatus(), "failure");
    }

    @Test(description = "liveness single frame with face image that is far")
    @Description("Sending single frame request with face image that is far")
    public void t04_livenessSingleFrameWithFaceTooFar() {
        apiResponse.singleFrameRequest("library_name", "LIVENESS", "case_id", apiResponse.randomString(), "frame", new File("./liveness/roleMessages/TooFar/MoranTooFar.jpg"), "image/jpg", apiResponse.SINGLE_FRAME_LIVENESS);
        apiResponse.singleFrameMainResponse(apiResponse.clientRequest, variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatusReason(), "Face too small");
        Assert.assertEquals(variables.getErrorCode(), 159);
        Assert.assertEquals(variables.getStageStatus(), "failure");
    }


    @Test(description = "liveness single frame with dark image")
    @Description("Sending single frame request with dark image that isn't illuminated enough")
    public void t06_livenessSingleFrameWithImageThatIsntIlluminatedEnough() {
        apiResponse.singleFrameRequest("library_name", "LIVENESS", "case_id", apiResponse.randomString(), "frame", new File("./liveness/apiPic/too_bright.jpg"), "image/jpg", apiResponse.SINGLE_FRAME_LIVENESS);
        apiResponse.singleFrameMainResponse(apiResponse.clientRequest, variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatusReason(), "Face not properly illuminated");
        Assert.assertEquals(variables.getErrorCode(), 165);
        Assert.assertEquals(variables.getStageStatus(), "failure");
    }

    @Test(description = "liveness single frame with blurry image")
    @Description("Sending single frame request with blurry image")
    public void t07_livenessSingleFrameWithBlurryImage() {
        apiResponse.singleFrameRequest("library_name", "LIVENESS", "case_id", apiResponse.randomString(), "frame", new File("./liveness/roleMessages/BadFaceFocus/Blur.jpg"), "image/jpg", apiResponse.SINGLE_FRAME_LIVENESS);
        apiResponse.singleFrameMainResponse(apiResponse.clientRequest, variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatusReason(), "Face not in focus");
        Assert.assertEquals(variables.getErrorCode(), 166);
        Assert.assertEquals(variables.getStageStatus(), "failure");
    }
}
