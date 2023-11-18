package api.faceServer;

import api.Variables;
import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import java.io.File;
import java.util.List;

public class FaceApiResponse extends FaceApiRequest {

    JsonPath jsonPath;

    @Step("face-api v2 verify_face response")
    public void v2VerifyFace(Variables variables, String accept, String contentType, String caseId, File firstImage, File secondImage) {
        clientRequest = v2VerifyFaceRequest(accept, contentType, caseId, firstImage, secondImage);

        jsonPath = clientRequest.jsonPath();
        if (!jsonPath.getBoolean("success")) {
            faceErrorHandler(variables, jsonPath, 0);
            return;
        }
        variables.setScore(jsonPath.getFloat("score"));
        variables.setProcessingTime(jsonPath.getInt("processingTime"));
        variables.setThreshold(jsonPath.getDouble("threshold"));
        variables.setSuccess(jsonPath.getBoolean("success"));
        variables.setCaseId(jsonPath.getString("caseId"));

    }

    @Step("face-api v1 image Encryption")
    public void v1imageEncryption(Variables variables, String accept, String contentType, String caseId, File firstImage, int idNumber) {
        clientRequest = v1imageEncryptionRequest(accept, contentType, caseId, firstImage,  idNumber);

        jsonPath = clientRequest.jsonPath();
        if (!jsonPath.getBoolean("success")) {
            faceVerifyUuidErrorHandler(variables, jsonPath, 0);
            return;
        }

        variables.setIdNumber(idNumber);
        variables.setIdNumber2(300444440);
        variables.setUuid2("YREU4234982378947UWEYRUYWERUIW");
        variables.setSuccess(jsonPath.getBoolean("success"));
        variables.setCaseId(jsonPath.getString("caseId"));
        variables.setProcessingTime(jsonPath.getInt("processingTime"));
        variables.setUuid(jsonPath.getString("uuid"));
        variables.setImageEncryptedPart(jsonPath.getString("imageEncryptedPart"));
        variables.setImageEncryptedPart2("fsdfjkshdjfhsjkfhsj4923432764786swordfishs476327463278");
    }

    @Step("face-api v1 image Encryption")
    public void v1CompareImageByEncryptionPart(Variables variables, String accept, String contentType, String caseId, File secondImage, int idNumber ,String encryptedPart , String uuid) {
        clientRequest = v1CompareImageByEncryptionPartRequest (accept,contentType,caseId,secondImage,idNumber,encryptedPart,uuid);

        jsonPath = clientRequest.jsonPath();
        if (!jsonPath.getBoolean("success")) {
            CompareImageByEncryptionPartErrorHandler(variables, jsonPath);
            return;
        }
        variables.setScore(jsonPath.getFloat("score"));
        variables.setProcessingTime(jsonPath.getInt("processingTime"));
        variables.setThreshold(jsonPath.getDouble("threshold"));
        variables.setSuccess(jsonPath.getBoolean("success"));
        variables.setCaseId(jsonPath.getString("caseId"));

    }


    @Step("face-api v2 verify_face response")
    public void v2VerifyFaceUuid(Variables variables, String accept, String contentType, String caseId, File firstImage, String objId) {
        clientRequest = v2VerifyFaceUuidRequest(accept, contentType, caseId, firstImage,  objId);

        jsonPath = clientRequest.jsonPath();
        if (!jsonPath.getBoolean("success")) {
            faceVerifyUuidErrorHandler(variables, jsonPath, 0);
            return;
        }
        variables.setScore(jsonPath.getFloat("score"));
        variables.setProcessingTime(jsonPath.getInt("processingTime"));
        variables.setThreshold(jsonPath.getDouble("threshold"));
        variables.setSuccess(jsonPath.getBoolean("success"));
        variables.setCaseId(jsonPath.getString("caseId"));
    }

    @Step("handler of the face errors, navigate to the right method via the error message")
    public void faceErrorHandler(Variables variables, JsonPath jsonPath, int index) {
        variables.setSuccess(jsonPath.getBoolean("success"));
        variables.setCaseId(jsonPath.getString("caseId"));
        variables.setErrorMessage(jsonPath.getString("errors["+index+"].message"));
        switch (variables.getErrorMessage()) {
            case "Could not detect face": {
                setFaceNotDetectedVariables(variables, jsonPath, 0);
                break;
            }
            case "Could not open image": {
                setCountOpenFileVariables(variables, jsonPath, 0);
                break;
            }
        }
    }

    @Step("handler of the face errors, navigate to the right method via the error message")
    public void faceVerifyUuidErrorHandler(Variables variables, JsonPath jsonPath, int index) {
        variables.setSuccess(jsonPath.getBoolean("success"));
        variables.setCaseId(jsonPath.getString("caseId"));
        variables.setErrorMessage(jsonPath.getString("error.message"));
        switch (variables.getErrorMessage()) {
            case "Could not detect face": {
                setFaceNotDetectedVariables(variables, jsonPath, 0);
                break;
            }
            case "Could not open image": {
                setCountOpenFileVariables(variables, jsonPath, 0);
                break;
            }
            case "Did not get face encoding": {
                setDidNotGetFaceEncoding(variables, jsonPath, 0);
                break;
            }
        }
    }



    @Step("handler of the face errors, navigate to the right method via the error message")
    public void CompareImageByEncryptionPartErrorHandler(Variables variables, JsonPath jsonPath) {
        variables.setSuccess(jsonPath.getBoolean("success"));
    //    variables.setCaseId(jsonPath.getString("caseId"));
        variables.setErrorMessage(jsonPath.getString("error.message"));
        switch (variables.getErrorMessage()) {
            case "Image not processed": {
                setImageNotProcessed(variables, jsonPath, 0);
                break;
            }
            case "The image could not be decoded": {
                setImageCouldNotBeDecoded(variables, jsonPath, 0);
                break;
            }
        }
    }


    @Step("set 'face not detected' error message variables")
    public void setFaceNotDetectedVariables(Variables variables, JsonPath jsonPath, int index) {
        Assert.assertEquals(jsonPath.getMap("").size(), 3);
        variables.setErrorCode(jsonPath.getInt("errors[" + index + "].code"));
        variables.setFirstErrorImage(jsonPath.getString("errors[" + index + "].image"));
        if (jsonPath.getList("errors").size() > 1) {
            variables.setSecondErrorImage(jsonPath.getString("errors[" + (index + 1) + "].image"));
        }
    }

    @Step("set 'could not open file' error message variables")
    public void setCountOpenFileVariables(Variables variables, JsonPath jsonPath, int index) {
        Assert.assertEquals(jsonPath.getMap("").size(), 3);
        variables.setFirstErrorImage(jsonPath.getString("errors[" + index + "].image"));
        if (jsonPath.getList("errors").size() > 1) {
            variables.setSecondErrorImage(jsonPath.getString("errors[" + (index + 1) + "].image"));
        }
    }

    @Step("set 'Did not get face encoding' error message variables")
    public void setDidNotGetFaceEncoding(Variables variables, JsonPath jsonPath, int index) {
        Assert.assertEquals(jsonPath.getMap("").size(), 2);
        variables.setFirstErrorImage(jsonPath.getString("error.message"));
        variables.setErrorCode(jsonPath.getInt("error.code"));

    }

    @Step("set 'Did not get correct uuid' error message variables")
    public void setImageNotProcessed(Variables variables, JsonPath jsonPath, int index) {
        Assert.assertEquals(jsonPath.getMap("").size(), 2);
        variables.setFirstErrorImage(jsonPath.getString("error.message"));
        variables.setErrorCode(jsonPath.getInt("error.code"));
    }


    @Step("set 'Did not get correct encryptedPart or id' error message variables")
    public void setImageCouldNotBeDecoded(Variables variables, JsonPath jsonPath, int index) {
        Assert.assertEquals(jsonPath.getMap("").size(), 2);
        variables.setFirstErrorImage(jsonPath.getString("error.message"));
        variables.setErrorCode(jsonPath.getInt("error.code"));
    }


    @Step("face encode v1 api response, get faceImage to encode and return the objId of the encoded image")
    public void faceEncodeV1(Variables variables, String accept, String contentType, File imageToEncode, int statusCode) {
        faceEncodeV1(variables, accept, contentType, imageToEncode, "", statusCode);
    }

    @Step("face encode v1 api response, get faceImage to encode and return the objId of the encoded image")
    public void faceEncodeV1(Variables variables, String accept, String contentType, File imageToEncode, String group, int statusCode) {
        clientRequest = v1FaceEncodeRequest(accept, contentType, imageToEncode, group, statusCode);
        jsonPath = clientRequest.jsonPath();

        switch (statusCode) {
            case 200: {
                if (jsonPath.getMap("").containsKey("success")) {
                    if (!jsonPath.getBoolean("success")) {
                        variables.setErrorMessage(jsonPath.getString("error.message"));
                        variables.setErrorCode(jsonPath.getInt("error.code"));
                        variables.setSuccess(jsonPath.getBoolean("success"));
                    }
                } else {
                    if (jsonPath.getMap("").containsKey("group")) {
                        Assert.assertEquals(jsonPath.getMap("").size(), 3);
                        variables.setGroupName(jsonPath.getString("group"));
                    } else Assert.assertEquals(jsonPath.getMap("").size(), 2);
                    variables.setObjId(jsonPath.getString("insertedObjId"));
                    variables.setEncodingVersion(jsonPath.getDouble("encodingVersion"));
                }
                break;
            }
            case 500: {
                Assert.assertEquals(jsonPath.getMap("").size(), 2);
                variables.setErrorMessage(jsonPath.getString("error.message"));
                variables.setSuccess(jsonPath.getBoolean("success"));
                break;
            }
        }
    }

    @Step("return the encoding of a face image")
    public void faceToEncoding(Variables variables, String accept, String contentType, File imageToEncode, int statusCode) {
        clientRequest = FaceToEncodingRequest(accept, contentType, imageToEncode, statusCode);
        jsonPath = clientRequest.jsonPath();

        switch (statusCode) {
            case 200: {
                Assert.assertEquals(jsonPath.getMap("").size(), 3);
                for (Object encoding : jsonPath.getList("encoding")) {
                    variables.getEncodingArray1().add((Float) encoding);
                }
                variables.setEncodingVersion(jsonPath.getDouble("encodingVersion"));
//                variables.setNorm(jsonPath.getDouble("norm"));
                variables.setSuccess(jsonPath.getBoolean("success"));
                break;
            }
            case 500: {
                Assert.assertEquals(jsonPath.getMap("").size(), 2);
                variables.setErrorMessage(jsonPath.getString("error.message"));
                variables.setSuccess(jsonPath.getBoolean("success"));
                break;
            }
        }
    }

    @Step("comparison of two images encodings")
    public void compareEncodes(Variables variables, String accept, String faceEncodeContentType, String faceCompareContentType, File firstImage, File secondImage, Double threshold, int code) {
        clientRequest = FaceToEncodingRequest(accept, faceEncodeContentType, firstImage, code);
        jsonPath = clientRequest.jsonPath();

        if (jsonPath.getMap("").containsKey("error")) {
            variables.setErrorMessage(jsonPath.getString("error.message"));
            variables.setSuccess(jsonPath.getBoolean("success"));
            return;
        }
        List<Float> firstImageEncoding = jsonPath.getList("encoding");
        clientRequest = FaceToEncodingRequest(accept, faceEncodeContentType, secondImage, code);
        jsonPath = clientRequest.jsonPath();

        if (jsonPath.getMap("").containsKey("error")) {
            variables.setErrorMessage(jsonPath.getString("error.message"));
            variables.setSuccess(jsonPath.getBoolean("success"));
            return;
        }

        List<Float> secondImageEncoding = jsonPath.getList("encoding");
        if (threshold == null) {
            clientRequest = encodingCompare(accept, faceCompareContentType, firstImageEncoding, secondImageEncoding, "", code);
        } else {
            String thresholdURL = "?threshold=" + threshold;
            clientRequest = encodingCompare(accept, faceCompareContentType, firstImageEncoding, secondImageEncoding, thresholdURL, code);
        }
        jsonPath = clientRequest.jsonPath();
        Assert.assertEquals(jsonPath.getMap("").size(), 4);
        if (!jsonPath.getList("matches").isEmpty()) {
            variables.setOriginalIndex(jsonPath.getInt("matches[0].originalIndex"));
            variables.setScore(jsonPath.getFloat("matches[0].score"));
        } else {
            for (Object match : jsonPath.getList("matches")) {
                variables.getOriginalIndexArray().add((Integer) match);
            }
        }
        for (Object index : jsonPath.getList("mismatchedEncodingsIndexes")) {
            variables.getMismatchedEncodingIndexes().add((Integer) index);
        }
        variables.setThreshold(jsonPath.getDouble("threshold"));
        variables.setSuccess(jsonPath.getBoolean("success"));
    }

    @Step("get face image and check in the DB for encoding similarity")
    public void addEncodedImageToDB(Variables variables, String accept, String contentType, File faceImage, Double threshold, int code) {
        addEncodedImageToDB(variables, accept, contentType, faceImage, threshold, "", code);
    }

    @Step("get face image and check in the DB for encoding similarity")
    public void addEncodedImageToDB(Variables variables, String accept, String contentType, File faceImage, Double threshold, String group, int code) {
        clientRequest = multipleFaceCompareRequest(accept, contentType, faceImage, threshold, group, code);
        jsonPath = clientRequest.jsonPath();

        switch (code) {
            case 200: {
                if (!jsonPath.getList("").isEmpty()) {
                    variables.setObjArraySize(jsonPath.getList("").size());
                    for (Object object : jsonPath.getList("")) {
                        variables.getObjDBArray().add(object);
                    }

                    System.out.println("Not empty");
                    System.out.println("Threshold: " + threshold);
                    System.out.println("Size: " + jsonPath.getList("").size());
                    for (int i = 0; i < jsonPath.getList("").size(); i++) {
                        variables.getSimilarityScore().add(jsonPath.getDouble("[" + i + "].similarityScore"));
                        variables.getObjArray().add(jsonPath.getString("[" + i + "].insertedObjId"));
                    }
                    if (threshold != null) {
                        variables.setThreshold(threshold);
                    }
                } else {
                    System.out.println("Empty");
                    variables.setObjArraySize(jsonPath.getList("").size());
                    for (Object object : jsonPath.getList("")) {
                        variables.getObjDBArray().add(object);
                    }
                }
                break;
            }
            case 500: {
                variables.setErrorMessage(jsonPath.getString("error.message"));
                variables.setSuccess(jsonPath.getBoolean("success"));
                break;
            }
        }
    }
}

