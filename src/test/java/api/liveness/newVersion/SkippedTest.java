package api.liveness.newVersion;

import api.ApiResponse;
import api.Variables;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class SkippedTest {

    Variables variables;
    ApiResponse apiResponse;

    @Test(enabled = false, description = "liveness single frame with not enough space to crop")
    @Description("Sending single frame request with image that doesn't have enough space around the face to crop")
    public void t05_livenessSingleFrameWithImageThatDontHaveEnoughSpace() {
        apiResponse.singleFrameRequest("library_name", "LIVENESS", "case_id", apiResponse.randomString(), "frame", new File("./liveness/apiPic/dark_face.jpg"), "image/jpg", apiResponse.SINGLE_FRAME_LIVENESS);
        apiResponse.singleFrameMainResponse(apiResponse.clientRequest, variables);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatusReason(), "Not enough space around image for full crop");
        Assert.assertEquals(variables.getErrorCode(), 168);
        Assert.assertEquals(variables.getStageStatus(), "failure");
    }

}
