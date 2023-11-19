package api.faceServer.v2;

import api.Variables;
import api.faceServer.FaceApiResponse;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class imageEncryption {
    //This tests takes image and then convert them to ObjectID

    FaceApiResponse faceApiResponse = new FaceApiResponse();
    Variables variables = new Variables();

//    @Test(description = "successful compering between 2 Encryption Parts To another Image of the same person",enabled = false)
//    @Description("successful compering between between 2 Encryption Part To another Image of the same person")
    public void t01_successfulComperingBetween2EncryptionPartToAnotherImage () {
        faceApiResponse.v1imageEncryption(variables,"application/json","multipart/form-data","1",new File("./liveness/harel/harel1.jpg"),300864550);
        Assert.assertNotNull(variables.getImageEncryptedPart());
        Assert.assertTrue(variables.isSuccess());
        Assert.assertEquals(variables.getCaseId(),"1");
        Assert.assertNotNull(variables.getProcessingTime());
        Assert.assertNotNull(variables.getUuid());

        faceApiResponse.v1CompareImageByEncryptionPart(variables, "application/json", "multipart/form-data","1" , new File("./liveness/harel/harel2.jpg"),variables.getIdNumber(),variables.getImageEncryptedPart(),variables.getUuid());

        Assert.assertEquals(variables.getScore(), 0.8069144487380981);
        Assert.assertNotNull(variables.getProcessingTime());
        Assert.assertEquals(variables.getThreshold(), 0.67);
        Assert.assertTrue(variables.getScore() > variables.getThreshold());
        Assert.assertTrue(variables.isSuccess());
        Assert.assertNotNull(variables.getCaseId());
    }


//    @Test(description = "successful compering between 2 Encryption Parts To the same Image",enabled = false)
//    @Description("successful compering between between 2 Encryption Part To the same Image")
    public void t02_successfulComperingBetween2EncryptionPartToTheSameImage () {
        faceApiResponse.v1imageEncryption(variables,"application/json","multipart/form-data","1",new File("./liveness/harel/harel1.jpg"),300864550);
        Assert.assertNotNull(variables.getImageEncryptedPart());
        Assert.assertTrue(variables.isSuccess());
        Assert.assertEquals(variables.getCaseId(),"1");
        Assert.assertNotNull(variables.getProcessingTime());
        Assert.assertNotNull(variables.getUuid());

        faceApiResponse.v1CompareImageByEncryptionPart(variables, "application/json", "multipart/form-data","1" , new File("./liveness/harel/harel1.jpg"),variables.getIdNumber(),variables.getImageEncryptedPart(),variables.getUuid());

        Assert.assertEquals(variables.getScore(), 1.0);
        Assert.assertNotNull(variables.getProcessingTime());
        Assert.assertEquals(variables.getThreshold(), 0.67);
        Assert.assertTrue(variables.getScore() > variables.getThreshold());
        Assert.assertTrue(variables.isSuccess());
        Assert.assertNotNull(variables.getCaseId());
    }


//    @Test(description = "successful compering between 2 Encryption Parts To image of another person",enabled = false)
//    @Description("successful compering between between 2 Encryption Part To image of another person")
    public void t03_successfulComperingBetween2EncryptionPartToTheSameImage () {
        faceApiResponse.v1imageEncryption(variables,"application/json","multipart/form-data","1",new File("./liveness/harel/harel1.jpg"),300864550);
        Assert.assertNotNull(variables.getImageEncryptedPart());
        Assert.assertTrue(variables.isSuccess());
        Assert.assertEquals(variables.getCaseId(),"1");
        Assert.assertNotNull(variables.getProcessingTime());
        Assert.assertNotNull(variables.getUuid());

        faceApiResponse.v1CompareImageByEncryptionPart(variables, "application/json", "multipart/form-data","1" , new File("./liveness/liad/liadFace.jpg"),variables.getIdNumber(),variables.getImageEncryptedPart(),variables.getUuid());

        Assert.assertEquals(variables.getScore(), 0.5245822668075562);
        Assert.assertNotNull(variables.getProcessingTime());
        Assert.assertEquals(variables.getThreshold(), 0.67);
        Assert.assertTrue(variables.getScore() < variables.getThreshold());
        Assert.assertTrue(variables.isSuccess());
        Assert.assertNotNull(variables.getCaseId());
    }


//    @Test(description = "Error Handling -try to compare between 2 Encryption with wrong id number",enabled = false)
//    @Description("Error Handling -try to compare between 2 Encryption with wrong id number")
    public void t04_ErrorHandlingCompareBetween2EncryptionWithWrongIdNumber () {
        faceApiResponse.v1imageEncryption(variables,"application/json","multipart/form-data","1",new File("./liveness/harel/harel1.jpg"),300864550);
        Assert.assertNotNull(variables.getImageEncryptedPart());
        Assert.assertTrue(variables.isSuccess());
        Assert.assertEquals(variables.getCaseId(),"1");
        Assert.assertNotNull(variables.getProcessingTime());
        Assert.assertNotNull(variables.getUuid());

        faceApiResponse.v1CompareImageByEncryptionPart(variables, "application/json", "multipart/form-data","1" , new File("./liveness/harel/harel1.jpg"),variables.getIdNumber2(),variables.getImageEncryptedPart(),variables.getUuid());

        Assert.assertFalse(variables.isSuccess());
        Assert.assertEquals(variables.getFirstErrorImage(),"The image could not be decoded");
        Assert.assertEquals(variables.getErrorCode(),505);
    }


//    @Test(description = "Error Handling -try to compare between 2 Encryption with wrong encryptedPart",enabled = false)
//    @Description("Error Handling -try to compare between 2 Encryption with wrong encryptedPart")
    public void t05_ErrorHandlingCompareBetween2EncryptionWithWrongEncryptedPart () {
        faceApiResponse.v1imageEncryption(variables,"application/json","multipart/form-data","1",new File("./liveness/harel/harel1.jpg"),300864550);
        Assert.assertNotNull(variables.getImageEncryptedPart());
        Assert.assertTrue(variables.isSuccess());
        Assert.assertEquals(variables.getCaseId(),"1");
        Assert.assertNotNull(variables.getProcessingTime());
        Assert.assertNotNull(variables.getUuid());

        faceApiResponse.v1CompareImageByEncryptionPart(variables, "application/json", "multipart/form-data","1" , new File("./liveness/harel/harel1.jpg"),variables.getIdNumber(),variables.getImageEncryptedPart2(),variables.getUuid());

        Assert.assertFalse(variables.isSuccess());
        Assert.assertEquals(variables.getFirstErrorImage(),"The image could not be decoded");
        Assert.assertEquals(variables.getErrorCode(),505);
    }


//    @Test(description = "Error Handling -try to compare between 2 Encryption with wrong uuid",enabled = false)
//    @Description("Error Handling -try to compare between 2 Encryption with wrong uuid")
    public void t06_ErrorHandlingCompareBetween2EncryptionWithWrongUuid () {
        faceApiResponse.v1imageEncryption(variables,"application/json","multipart/form-data","1",new File("./liveness/harel/harel1.jpg"),300864550);
        Assert.assertNotNull(variables.getImageEncryptedPart());
        Assert.assertTrue(variables.isSuccess());
        Assert.assertEquals(variables.getCaseId(),"1");
        Assert.assertNotNull(variables.getProcessingTime());
        Assert.assertNotNull(variables.getUuid());

        faceApiResponse.v1CompareImageByEncryptionPart(variables, "application/json", "multipart/form-data","1" , new File("./liveness/harel/harel1.jpg"),variables.getIdNumber(),variables.getImageEncryptedPart(),variables.getUuid2());

        Assert.assertFalse(variables.isSuccess());
        Assert.assertEquals(variables.getFirstErrorImage(),"Image not processed");
        Assert.assertEquals(variables.getErrorCode(),503);
    }

}
