package api.faceServer.v2;

import api.Variables;
import api.faceServer.FaceApiResponse;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;


public class faceToObjID {

    //This tests takes image and then convert them to ObjectID

    FaceApiResponse faceApiResponse = new FaceApiResponse();
    Variables variables = new Variables();

    @Test(description = "successful insert JPG encoding")
    @Description("successful insert JPG encoding")
    public void t01_successfulEncodingJPG() {
        faceApiResponse.faceEncodeV1(variables, "application/json", "multipart/form-data", new File("./liveness/bar/barCenter.jpg"), 200);
        Assert.assertNotNull(variables.getObjId());
        Assert.assertEquals(variables.getEncodingVersion(), 5.0);
    }

    @Test(description = "successful insert JPEG encoding")
    @Description("successful insert JPEG encoding")
    public void t02_successfulEncodingJPEG() {
        faceApiResponse.faceEncodeV1(variables, "application/json", "multipart/form-data", new File("./liveness/bar/barCenter.jpeg"), 200);
        Assert.assertNotNull(variables.getObjId());
        Assert.assertEquals(variables.getEncodingVersion(), 5.0);
    }

    @Test(description = "successful insert PNG encoding")
    @Description("successful insert PNG encoding")
    public void t03_successfulEncodingPNG() {
        faceApiResponse.faceEncodeV1(variables, "application/json", "multipart/form-data", new File("./liveness/bar/barCenter.png"), 200);
        Assert.assertNotNull(variables.getObjId());
        Assert.assertEquals(variables.getEncodingVersion(), 5.0);
    }

    @Test(description = "insert attempt of unsupported file")
    @Description("Unsuccessful attempt to insert unsupported file")
    public void t04_encodingOfUnsupportedFile() {
        faceApiResponse.faceEncodeV1(variables, "application/json", "multipart/form-data", new File("./faceImages/unSupportFile/sample.pdf"), 500);
        Assert.assertEquals(variables.getErrorMessage(), "Could not open image");
        Assert.assertFalse(variables.isSuccess());
    }

    @Test(description = "encode image without face")
    @Description("Encode image that doesn't contain face")
    public void t05_noFaceDetectedToEncode() {
        faceApiResponse.faceEncodeV1(variables, "application/json", "multipart/form-data", new File("./liveness/bar/barWithoutFace.png"), 200);
        Assert.assertEquals(variables.getErrorMessage(), "Did not get face encoding");
        Assert.assertEquals(variables.getErrorCode(), 504);
        Assert.assertFalse(variables.isSuccess());
    }
}
