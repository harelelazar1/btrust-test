package api.faceServer.v2;

import api.Variables;
import api.faceServer.FaceApiResponse;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class verifyFaceUuid {

    //This tests takes image and then convert them to ObjectID

    FaceApiResponse faceApiResponse = new FaceApiResponse();
    Variables variables = new Variables();

    @Test(description = "successful compering between image to uuid")
    @Description("successful compering between image to uuid")
    public void t01_successfulComperingImageToUuid () {
        faceApiResponse.faceEncodeV1(variables, "application/json", "multipart/form-data", new File("./liveness/harel/harel1.jpg"), 200);
        Assert.assertNotNull(variables.getObjId());
        Assert.assertEquals(variables.getEncodingVersion(), 5.0);

        faceApiResponse.v2VerifyFaceUuid(variables, "application/json", "multipart/form-data", faceApiResponse.randomString(), new File("./liveness/harel/harel2.jpg"),variables.getObjId());

        Assert.assertEquals(variables.getScore(), 0.8727815747261047);
        Assert.assertNotNull(variables.getProcessingTime());
        Assert.assertEquals(variables.getThreshold(), 0.67);
        Assert.assertTrue(variables.getScore() > variables.getThreshold());
        Assert.assertTrue(variables.isSuccess());
        Assert.assertNotNull(variables.getCaseId());
    }


    @Test(description = "try to compare image to false uuid")
    @Description("try to compare image to false uuid")
    public void t02_noFaceDetectedToEncode() {
        faceApiResponse.v2VerifyFaceUuid(variables, "application/json", "multipart/form-data", faceApiResponse.randomString(), new File("./liveness/harel/harel2.jpg"),"3a2793909e2a11ecbe2ecfea227208cfds4");

        Assert.assertEquals(variables.getErrorMessage(),"Did not get face encoding");
        Assert.assertEquals(variables.getErrorCode(),504);
        Assert.assertFalse(variables.isSuccess());
    }













}
