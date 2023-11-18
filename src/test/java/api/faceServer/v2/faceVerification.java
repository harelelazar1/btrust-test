package api.faceServer.v2;

import api.Variables;
import api.faceServer.FaceApiResponse;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.time.LocalDateTime;
public class faceVerification {

    FaceApiResponse faceApiResponse = new FaceApiResponse();
    Variables variables = new Variables();

    @Test(description = "face verification v2 - same person")
    @Description("Face api - face verification v2 with 2 different images of the same person")
    public void t01_faceMatchV2SamePersonDifferentImagesJPGComparison() {
        faceApiResponse.v2VerifyFace(variables, "application/json", "multipart/form-data", faceApiResponse.randomString(), new File("./liveness/liad/1592925591224.jpg"), new File("./liveness/liad/liadFace.jpg"));

        Assert.assertEquals(variables.getScore(), 0.9385911822319031);
        Assert.assertNotNull(variables.getProcessingTime());
        Assert.assertEquals(variables.getThreshold(), 0.67);
        Assert.assertTrue(variables.getScore() > variables.getThreshold());
        Assert.assertTrue(variables.isSuccess());
        Assert.assertNotNull(variables.getCaseId());
    }

    @Test(description = "face verification v2 - same person, same image")
    @Description("Face api - face verification v2 with same image of the same person")
    public void t02_faceMatchV2SamePersonSameImageJPGComparison() {
        faceApiResponse.v2VerifyFace(variables, "application/json", "multipart/form-data", faceApiResponse.randomString(), new File("./liveness/liad/liadFace.jpg"), new File("./liveness/liad/liadFace.jpg"));

        Assert.assertEquals(variables.getScore(), 1);
        Assert.assertNotNull(variables.getProcessingTime());
        Assert.assertEquals(variables.getThreshold(), 0.67);
        Assert.assertTrue(variables.getScore() > variables.getThreshold());
        Assert.assertTrue(variables.isSuccess());
        Assert.assertNotNull(variables.getCaseId());
    }

    @Test(description = "face verification v2 - same person, same image")
    @Description("Face api - face verification v2 with same image of the same person")
    public void t03_faceMatchV2SamePersonSameImageJPGComparison() {
        faceApiResponse.v2VerifyFace(variables, "application/json", "multipart/form-data", faceApiResponse.randomString(), new File("./liveness/liad/liadFace.jpg"), new File("./liveness/liad/liadFace.jpg"));

        Assert.assertEquals(variables.getScore(), 1);
        Assert.assertNotNull(variables.getProcessingTime());
        Assert.assertEquals(variables.getThreshold(), 0.67);
        Assert.assertTrue(variables.getScore() > variables.getThreshold());
        Assert.assertTrue(variables.isSuccess());
        Assert.assertNotNull(variables.getCaseId());
    }

    @Test(description = "face verification v2 - first normal image, second image without face detected")
    @Description("Face api - face verification v2 with first face image normal, and the second image is not with face detection")
    public void t04_faceMatchV2FirstNormalImageSecondImageWithoutFace() {
        faceApiResponse.v2VerifyFace(variables, "application/json", "multipart/form-data", faceApiResponse.randomString(), new File("./liveness/moran/normal.jpg"), new File("./liveness/moran/leftRotation.jpg"));

        Assert.assertEquals(variables.getScore(), 0.8161624670028687);
        Assert.assertNotNull(variables.getProcessingTime());
        Assert.assertEquals(variables.getThreshold(), 0.67);
        Assert.assertTrue(variables.getScore() > variables.getThreshold());
        Assert.assertTrue(variables.isSuccess());
        Assert.assertNotNull(variables.getCaseId());
    }

    @Test(description = "face verification v2 - first image without face, second image normal image")
    @Description("Face api - face verification v2 with first image without face detection, and the second image is normal image")
    public void t05_faceMatchV2FirstImageWithoutFaceSecondNormalImage() {
        faceApiResponse.v2VerifyFace(variables, "application/json", "multipart/form-data", faceApiResponse.randomString(), new File("./liveness/moran/leftRotation.jpg"), new File("./liveness/moran/normal.jpg"));

        Assert.assertEquals(variables.getScore(), 0.8161624670028687);
        Assert.assertNotNull(variables.getProcessingTime());
        Assert.assertEquals(variables.getThreshold(), 0.67);
        Assert.assertTrue(variables.getScore() >variables.getThreshold());
        Assert.assertTrue(variables.isSuccess());
        Assert.assertNotNull(variables.getCaseId());
    }

    @Test(description = "face verification v2 - first image without face, second image normal image")
    @Description("Face api - face verification v2 with first image without face detection, and the second image is normal image")
    public void t06_faceMatchV2BothImagesWithoutFaceDetection() {
        faceApiResponse.v2VerifyFace(variables, "application/json", "multipart/form-data", faceApiResponse.randomString(), new File("./liveness/moran/leftRotation.jpg"), new File("./liveness/moran/rightRotation.jpg"));

        Assert.assertEquals(variables.getScore(), 0.8368268013000488);
        Assert.assertNotNull(variables.getProcessingTime());
        Assert.assertEquals(variables.getThreshold(), 0.67);
        Assert.assertTrue(variables.getScore() > variables.getThreshold());
        Assert.assertTrue(variables.isSuccess());
        Assert.assertNotNull(variables.getCaseId());
    }

    @Test(description = "face verification v2 - first file unsupported (pdf), second file normal image")
    @Description("Face api - face verification v2 with first face is unsupported file, and the second file is normal with face image")
    public void t07_firstUnsupportedFileSecondNormalImage() {
        faceApiResponse.v2VerifyFace(variables, "application/json", "multipart/form-data", faceApiResponse.randomString(), new File("./faceImages/unSupportFile/sample.pdf"), new File("./liveness/moran/normal.jpg"));

        Assert.assertEquals(variables.getErrorMessage(), "Could not open image");
        Assert.assertEquals(variables.getFirstErrorImage(), "first_image");
        Assert.assertFalse(variables.isSuccess());
        Assert.assertNotNull(variables.getCaseId());
    }

    @Test(description = "face verification v2 - first file unsupported (pdf), second file normal image")
    @Description("Face api - face verification v2 with first face is unsupported file, and the second file is normal with face image")
    public void t08_firstNormalImageSecondUnsupportedFile() {
        faceApiResponse.v2VerifyFace(variables, "application/json", "multipart/form-data", faceApiResponse.randomString(), new File("./liveness/moran/normal.jpg"), new File("./faceImages/unSupportFile/sample.pdf"));

        Assert.assertEquals(variables.getErrorMessage(), "Could not open image");
        Assert.assertEquals(variables.getFirstErrorImage(), "second_image");
        Assert.assertFalse(variables.isSuccess());
        Assert.assertNotNull(variables.getCaseId());
    }

    @Test(description = "face verification v2 - same person, different images, both JPEG")
    @Description("Face api - face verification v2 with 2 different images of the same person, both images are JPEG file type")
    public void t09_faceMatchV2SamePersonDifferentImagesJPEGComparison() {
        faceApiResponse.v2VerifyFace(variables, "application/json", "multipart/form-data", faceApiResponse.randomString(), new File("./liveness/bar/jpegFile.jpeg"), new File("./liveness/bar/barCenter.jpeg"));

        Assert.assertEquals(variables.getScore(), 0.9938796162605286);
        Assert.assertNotNull(variables.getProcessingTime());
        Assert.assertEquals(variables.getThreshold(), 0.67);
        Assert.assertTrue(variables.getScore() > variables.getThreshold());
        Assert.assertTrue(variables.isSuccess());
        Assert.assertNotNull(variables.getCaseId());
    }

    @Test(description = "face verification v2 - same person, same image, both JPEG")
    @Description("Face api - face verification v2 with same image of the same person, both images are JPEG file type")
    public void t10_faceMatchV2SamePersonSameImageJPEGComparison() {
        faceApiResponse.v2VerifyFace(variables, "application/json", "multipart/form-data", faceApiResponse.randomString(), new File("./liveness/bar/jpegFile.jpeg"), new File("./liveness/bar/jpegFile.jpeg"));

        Assert.assertEquals(variables.getScore(), 1);
        Assert.assertNotNull(variables.getProcessingTime());
        Assert.assertEquals(variables.getThreshold(), 0.67);
        Assert.assertTrue(variables.getScore() > variables.getThreshold());
        Assert.assertTrue(variables.isSuccess());
        Assert.assertNotNull(variables.getCaseId());
    }

    @Test(description = "face verification v2 - same person, different images, both PNG")
    @Description("Face api - face verification v2 with 2 different images of the same person, both images are PNG file type")
    public void t11_faceMatchV2SamePersonDifferentImagesPNGComparison() {
        faceApiResponse.v2VerifyFace(variables, "application/json", "multipart/form-data", faceApiResponse.randomString(), new File("./liveness/bar/pngFile.png"), new File("./liveness/bar/barCenter.png"));

        Assert.assertEquals(variables.getScore(), 0.9933370351791382);
        Assert.assertNotNull(variables.getProcessingTime());
        Assert.assertEquals(variables.getThreshold(), 0.67);
        Assert.assertTrue(variables.getScore() > variables.getThreshold());
        Assert.assertTrue(variables.isSuccess());
        Assert.assertNotNull(variables.getCaseId());
    }

    @Test(description = "face verification v2 - same person, same image, both PNG")
    @Description("Face api - face verification v2 with same image of the same person, both images are PNG file type")
    public void t12_faceMatchV2SamePersonSameImagePNGComparison() {
        faceApiResponse.v2VerifyFace(variables, "application/json", "multipart/form-data", faceApiResponse.randomString(), new File("./liveness/bar/pngFile.png"), new File("./liveness/bar/pngFile.png"));

        Assert.assertEquals(variables.getScore(), 1);
        Assert.assertNotNull(variables.getProcessingTime());
        Assert.assertEquals(variables.getThreshold(), 0.67);
        Assert.assertTrue(variables.getScore() > variables.getThreshold());
        Assert.assertTrue(variables.isSuccess());
        Assert.assertNotNull(variables.getCaseId());
    }

    @Test(description = "face verification v2 - same person, same image, different file types - jpg vs. jpeg")
    @Description("Face api - face verification v2 with same image of the same person, but in a different file types - jpg vs. jpeg")
    public void t13_jpgAndJpegComparison() {
        faceApiResponse.v2VerifyFace(variables, "application/json", "multipart/form-data", faceApiResponse.randomString(), new File("./liveness/bar/barCenter.jpg"), new File("./liveness/bar/barCenter.jpeg"));

        Assert.assertEquals(variables.getScore(), 0.9998104572296143);
        Assert.assertNotNull(variables.getProcessingTime());
        Assert.assertEquals(variables.getThreshold(), 0.67);
        Assert.assertTrue(variables.getScore() > variables.getThreshold());
        Assert.assertTrue(variables.isSuccess());
        Assert.assertNotNull(variables.getCaseId());
    }

    @Test(description = "face verification v2 - same person, same image, different file types - jpg vs. png")
    @Description("Face api - face verification v2 with same image of the same person, but in a different file types - jpg vs. png")
    public void t14_jpgAndPngComparison() {
        faceApiResponse.v2VerifyFace(variables, "application/json", "multipart/form-data", faceApiResponse.randomString(), new File("./liveness/bar/barCenter.jpg"), new File("./liveness/bar/barCenter.png"));

        Assert.assertEquals(variables.getScore(), 0.9996702671051025);
        Assert.assertNotNull(variables.getProcessingTime());
        Assert.assertEquals(variables.getThreshold(), 0.67);
        Assert.assertTrue(variables.getScore() > variables.getThreshold());
        Assert.assertTrue(variables.isSuccess());
        Assert.assertNotNull(variables.getCaseId());
    }

    @Test(description = "face verification v2 - same person, same image, different file types - jpeg vs. png")
    @Description("Face api - face verification v2 with same image of the same person, but in a different file types - jpeg vs. png")
    public void t15_jpegAndPngComparison() {
        faceApiResponse.v2VerifyFace(variables, "application/json", "multipart/form-data", faceApiResponse.randomString(), new File("./liveness/bar/barCenter.jpeg"), new File("./liveness/bar/barCenter.png"));

        Assert.assertEquals(variables.getScore(), 0.9998230934143066);
        Assert.assertNotNull(variables.getProcessingTime());
        Assert.assertEquals(variables.getThreshold(), 0.67);
        Assert.assertTrue(variables.getScore() > variables.getThreshold());
        Assert.assertTrue(variables.isSuccess());
        Assert.assertNotNull(variables.getCaseId());
    }

    @Test(description = "face verification v2 - same person")
    @Description("Face api - face verification v2 with same person")
    public void t16_faceMatchV2SamePerson() {
        faceApiResponse.v2VerifyFace(variables, "application/json", "multipart/form-data", faceApiResponse.randomString(), new File("./liveness/harel/harel2.jpg"), new File("./liveness/harel/harel2.jpg"));

        Assert.assertTrue(variables.getScore()-1<0.01);
        Assert.assertNotNull(variables.getProcessingTime());
        Assert.assertEquals(variables.getThreshold(), 0.67);
        Assert.assertTrue(variables.getScore() > variables.getThreshold());
        Assert.assertTrue(variables.isSuccess());
        Assert.assertNotNull(variables.getCaseId());
    }
}
