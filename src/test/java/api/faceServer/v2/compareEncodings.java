package api.faceServer.v2;

import api.Variables;
import api.faceServer.FaceApiResponse;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class compareEncodings {

    FaceApiResponse faceApiResponse = new FaceApiResponse();
    Variables variables = new Variables();

    @Test(description = "2 jpg encodes comparison - same person , same image")
    @Description("2 JPG face images of the same person that each of them is break into his encode and then a comparison is made between the 2 JPG face images")
    public void t01_sameJPGImagesEncodingComparison() {
        faceApiResponse.compareEncodes(variables, "application/json", "multipart/form-data", "application/json", new File("./liveness/bar/barCenter.jpg"), new File("./liveness/bar/barCenter.jpg"), null, 200);

        Assert.assertEquals(variables.getOriginalIndex(), 0);
        Assert.assertEquals(variables.getScore(), 1);
        Assert.assertTrue(variables.getMismatchedEncodingIndexes().isEmpty());
        Assert.assertEquals(variables.getThreshold(), 0.67);
        Assert.assertTrue(variables.getScore() > variables.getThreshold());
        Assert.assertTrue(variables.isSuccess());
    }

    @Test(description = "2 jpEg encodes comparison - same person , same image")
    @Description("2 JPEG face images of the same person that each of them is break into his encode and then a comparison is made between the 2 JPEG face images")
    public void t02_sameJPEGImagesEncodingComparison() {
        faceApiResponse.compareEncodes(variables, "application/json", "multipart/form-data", "application/json", new File("./liveness/bar/barCenter.jpeg"), new File("./liveness/bar/barCenter.jpeg"), null, 200);

        Assert.assertEquals(variables.getOriginalIndex(), 0);
        Assert.assertEquals(variables.getScore(), 1);
        Assert.assertTrue(variables.getMismatchedEncodingIndexes().isEmpty());
        Assert.assertEquals(variables.getThreshold(), 0.67);
        Assert.assertTrue(variables.getScore() > variables.getThreshold());
        Assert.assertTrue(variables.isSuccess());
    }

    @Test(description = "2 png encodes comparison - same person , same image")
    @Description("2 PNG face images of the same person that each of them is break into his encode and then a comparison is made between the 2 PNG face images")
    public void t03_samePNGImagesEncodingComparison() {
        faceApiResponse.compareEncodes(variables, "application/json", "multipart/form-data", "application/json", new File("./liveness/bar/barCenter.png"), new File("./liveness/bar/barCenter.png"), null, 200);

        Assert.assertEquals(variables.getOriginalIndex(), 0);
        Assert.assertEquals(variables.getScore(), 1);
        Assert.assertTrue(variables.getMismatchedEncodingIndexes().isEmpty());
        Assert.assertEquals(variables.getThreshold(), 0.67);
        Assert.assertTrue(variables.getScore() > variables.getThreshold());
        Assert.assertTrue(variables.isSuccess());
    }

    @Test(description = "2 encodes comparison - different persons")
    @Description("2 face images of the different persons that each of them is break into his encode and then a comparison is made between the 2 face images")
    public void t04_differentPersons() {
        faceApiResponse.compareEncodes(variables, "application/json", "multipart/form-data", "application/json", new File("./liveness/bar/barCenter.jpg"), new File("./liveness/liad/liadFace.jpg"), 0.5, 200);

        Assert.assertEquals(variables.getOriginalIndex(), 0);
        Assert.assertEquals(variables.getScore(),0.5009334683418274);
        Assert.assertTrue(variables.getMismatchedEncodingIndexes().isEmpty());
        Assert.assertEquals(variables.getThreshold(), 0.5);
        Assert.assertTrue(variables.getScore() > variables.getThreshold());
        Assert.assertTrue(variables.isSuccess());
    }

    @Test(description = "2 encodes comparison - same person , different face images")
    @Description("2 face images of the same person but with different face images that each of them is break into his encode and then a comparison is made between the 2 face images")
    public void t05_samePersonDifferentFaceImages() {
        faceApiResponse.compareEncodes(variables, "application/json", "multipart/form-data", "application/json", new File("./liveness/nitzan/straight.jpg"), new File("./liveness/nitzan/nitzan2.jpg"), 0.5, 200);

        Assert.assertEquals(variables.getOriginalIndex(), 0);
        Assert.assertEquals(variables.getScore(), 0.9212278127670288);
        Assert.assertTrue(variables.getMismatchedEncodingIndexes().isEmpty());
        Assert.assertEquals(variables.getThreshold(), 0.5);
        Assert.assertTrue(variables.getScore() > variables.getThreshold());
        Assert.assertTrue(variables.isSuccess());
    }

    @Test(description = "encode comparison attempt of unsupported file with normal image")
    @Description("encode comparison attempt of unsupported file with normal image")
    public void t06_encodeComparisonOfUnsupportedFile() {
        faceApiResponse.compareEncodes(variables, "application/json", "multipart/form-data", "application/json", new File("./faceImages/unSupportFile/sample.pdf"), new File("./liveness/nitzan/straight2.jpg"), 0.5, 500);

        Assert.assertEquals(variables.getErrorMessage(), "Could not open image");
        Assert.assertFalse(variables.isSuccess());
    }

}
