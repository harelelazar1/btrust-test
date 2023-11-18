package api.faceServer.v2;

import api.ApiResponse;
import api.Variables;
import api.faceServer.FaceApiResponse;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;

public class multipleFaceCompare {

    FaceApiResponse faceApiResponse;
    Variables variables;

    @BeforeMethod
    public void resetArrays() {
        faceApiResponse = new FaceApiResponse();
        variables = new Variables();
        variables.getSimilarityScore().clear();
        variables.getObjDBArray().clear();
    }

    @Test(description = "check DB with similar encoding without threshold - JPG file")
    @Description("check DB with similar encoding for the JPG file, without threshold check")
    public void t01_checkDBForEncodeSimilarityWithoutThresholdJPGFile() {
        faceApiResponse.addEncodedImageToDB(variables, "application/json", "multipart/form-data", new File("./liveness/bar/barCenter.jpg"), null, 200);

        Assert.assertNotNull(variables.getObjDBArray());
        Assert.assertTrue(variables.getObjArraySize() > 0);
        Assert.assertNotNull(variables.getObjArray());
        Assert.assertNotNull(variables.getSimilarityScore());
    }

    @Test(description = "check DB with similar encoding without threshold - JPEG file")
    @Description("check DB with similar encoding for the JPEG file, without threshold check")
    public void t02_checkDBForEncodeSimilarityWithoutThresholdJPEGFile() {
        faceApiResponse.addEncodedImageToDB(variables, "application/json", "multipart/form-data", new File("./liveness/bar/barCenter.jpeg"), null, 200);

        Assert.assertNotNull(variables.getObjDBArray());
        Assert.assertTrue(variables.getObjArraySize() > 0);
        Assert.assertNotNull(variables.getObjArray());
        Assert.assertNotNull(variables.getSimilarityScore());
    }

    @Test(description = "check DB with similar encoding without threshold - PNG file")
    @Description("check DB with similar encoding for the PNG file, without threshold check")
    public void t03_checkDBForEncodeSimilarityWithoutThresholdPNGFile() {
        faceApiResponse.addEncodedImageToDB(variables, "application/json", "multipart/form-data", new File("./liveness/bar/barCenter.png"), null, 200);

        Assert.assertNotNull(variables.getObjDBArray());
        Assert.assertTrue(variables.getObjArraySize() > 0);
        Assert.assertNotNull(variables.getObjArray());
        Assert.assertNotNull(variables.getSimilarityScore());
    }

    @Test(description = "check DB with similar encoding with threshold - JPG file")
    @Description("check DB with similar encoding for the JPG file, with threshold check")
    public void t04_checkDBForEncodeSimilarityWithThreshold() {
        faceApiResponse.addEncodedImageToDB(variables, "application/json", "multipart/form-data", new File("./liveness/bar/barCenter.jpg"), 0.98, 200);

        Assert.assertNotNull(variables.getObjDBArray());
        Assert.assertTrue(variables.getObjArraySize() > 0);
        Assert.assertEquals(variables.getThreshold(), 0.98);
        Assert.assertNotNull(variables.getSimilarityScore());
        for (double scoreToCheck : variables.getSimilarityScore()) {
            Assert.assertTrue(scoreToCheck >= variables.getThreshold());
        }
        Assert.assertNotNull(variables.getObjArray());
    }

    @Test(description = "check DB encode that don't have similarity")
    @Description("check DB encode that don't have similarity")
    public void t05_checkDBForEncodeThatDontHaveSimilarity() {
        faceApiResponse.addEncodedImageToDB(variables, "application/json", "multipart/form-data", new File("./liveness/moran/normal.jpg"), 0.5, 200);
        System.out.println(variables.getObjArraySize());
        System.out.println(variables.getObjDBArray());
        Assert.assertEquals(variables.getObjDBArray().toString(), "[]");
        Assert.assertEquals(variables.getObjArraySize(), 0);
    }

    @Test(description = "check DB for encode that of unsupported file")
    @Description("check DB for encode that of unsupported fil")
    public void t06_checkDBForEncodeOfUnsupportedFile() {
        faceApiResponse.addEncodedImageToDB(variables, "application/json", "multipart/form-data", new File("./faceImages/unSupportFile/sample.pdf"), 0.5, 500);

        Assert.assertEquals(variables.getErrorMessage(), "Could not open image");
        Assert.assertFalse(variables.isSuccess());
    }

    @Test(description = "compare face to many face with group filter")
    @Description("compare face to many face with group filter, group filter is a random string that generate each run")
    public void t07_compareOneToManyWithGroup() {
        ApiResponse apiResponse = new ApiResponse();
        String groupName = apiResponse.randomString();
        faceApiResponse.faceEncodeV1(variables, "application/json", "multipart/form-data", new File("./liveness/bar/barCenter.jpg"), groupName, 200);
        faceApiResponse.addEncodedImageToDB(variables, "application/json", "multipart/form-data", new File("./liveness/bar/barCenter.jpg"), null, groupName, 200);

        System.out.println("Group name is: " + groupName);
        Assert.assertNotNull(variables.getGroupName());
        Assert.assertNotNull(variables.getObjDBArray());
        Assert.assertTrue(variables.getObjArraySize() > 0);
        Assert.assertEquals(variables.getObjArraySize(), 1);
        Assert.assertNotNull(variables.getObjArray());
        Assert.assertNotNull(variables.getSimilarityScore());
    }
}
