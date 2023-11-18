package api.faceServer.v2;

import api.Variables;
import api.faceServer.FaceApiResponse;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class faceToEncode {

    //These tests takes face images and printing the face location in the picture as binary encode.

    FaceApiResponse faceApiResponse = new FaceApiResponse();
    Variables variables = new Variables();

    @Test(description = "jpg face image encode")
    @Description("Encode face image of jpg file")
    public void t01_jpgEncoding() {
        faceApiResponse.faceToEncoding(variables, "application/json", "multipart/form-data", new File("./liveness/bar/barCenter.jpg"), 200);
        Assert.assertNotNull(variables.getEncodingArray1());
        Assert.assertEquals(variables.getEncodingVersion(), 5.0);
//        Assert.assertNotNull(variables.getNorm());
        Assert.assertTrue(variables.isSuccess());
        System.out.println(variables.getEncodingArray1());
    }

    @Test(description = "jpeg face image encode")
    @Description("Encode face image of jpeg file")
    public void t02_jpegEncoding() {
        faceApiResponse.faceToEncoding(variables, "application/json", "multipart/form-data", new File("./liveness/bar/barCenter.jpeg"), 200);
        Assert.assertNotNull(variables.getEncodingArray1());
        Assert.assertEquals(variables.getEncodingVersion(), 5.0);
//        Assert.assertNotNull(variables.getNorm());
        Assert.assertTrue(variables.isSuccess());
    }

    @Test(description = "png face image encode")
    @Description("Encode face image of png file")
    public void t03_pngEncoding() {
        faceApiResponse.faceToEncoding(variables, "application/json", "multipart/form-data", new File("./liveness/bar/barCenter.png"), 200);
        Assert.assertNotNull(variables.getEncodingArray1());
        Assert.assertEquals(variables.getEncodingVersion(), 5.0);
//        Assert.assertNotNull(variables.getNorm());
        Assert.assertTrue(variables.isSuccess());
    }

    @Test(description = "encode attempt of unsupported file")
    @Description("Trying to encode unsupported file")
    public void t04_encodeUnsupportedFile() {
        faceApiResponse.faceToEncoding(variables, "application/json", "multipart/form-data", new File("./faceImages/unSupportFile/unSupportFile.json"), 500);

        Assert.assertEquals(variables.getErrorMessage(), "Could not open image");
        Assert.assertFalse(variables.isSuccess());
    }

}
