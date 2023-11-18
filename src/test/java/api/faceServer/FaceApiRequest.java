package api.faceServer;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.io.File;
import java.util.List;
import java.util.Random;

import static io.restassured.RestAssured.given;

public class FaceApiRequest {

    public Response clientRequest;
    public Response clientInit;

    protected String caseId;

    @Step("Face verification v2 api request")
    public Response v2VerifyFaceRequest(String accept, String contentType, String caseId, File firstImage, File secondImage) {
        clientInit = given()
                .header("accept", accept)
                .header("Content-Type", contentType)
                .multiPart("first_image", firstImage, "image/jpg")
                .multiPart("second_image", secondImage, "image/jpg")
                .multiPart("caseId", caseId)
                .when()
//                .log()
//                .all()
                .post("https://face-qa.scanovate.com/api/v2/verify_face")
                .then()
                .statusCode(200)
                .extract()
                .response();
//        System.out.println(clientInit.getBody().asString());
        return clientInit;
    }


    @Step("Face image encryption for split")
    public Response v1imageEncryptionRequest(String accept, String contentType, String caseId, File firstImage, int idNumber) {
        clientInit = given()
                .header("accept", accept)
                .header("Content-Type", contentType)
                .multiPart("image", firstImage, "image/jpg")
                .multiPart("id", idNumber)
                .multiPart("caseId", caseId)
                .when()
                .log()
                .all()
                .post("https://face-qa.scanovate.com/api/v1/image_encryption")
                .then()
                .statusCode(200)
                .extract()
                .response();
        System.out.println(clientInit.getBody().asString());
        return clientInit;
    }


    @Step("Face compare image by encryption part after split")
    public Response v1CompareImageByEncryptionPartRequest(String accept, String contentType, String caseId, File secondImage, int idNumber, String encryptedPart, String uuid) {
        clientInit = given()
                .header("accept", accept)
                .header("Content-Type", contentType)
                .multiPart("image", secondImage, "image/jpg")
                .multiPart("id", idNumber)
                .multiPart("caseId", caseId)
                .multiPart("encryptedPart", encryptedPart)
                .multiPart("uuid", uuid)
                .when()
                .log()
                .all()
                .post("https://face-qa.scanovate.com/api/v1/compare_image_by_encryption_part")
                .then()
                .statusCode(200)
                .extract()
                .response();
        System.out.println(clientInit.getBody().asString());
        return clientInit;
    }


    @Step("Face verification v2 api request")
    public Response v2VerifyFaceUuidRequest(String accept, String contentType, String caseId, File firstImage, String objId) {
        clientInit = given()
                .header("accept", accept)
                .header("Content-Type", contentType)
                .multiPart("first_image", firstImage, "image/jpg")
                .multiPart("uuid", objId)
                .multiPart("caseId", caseId)
                .when()
                .log()
                .all()
                .post("https://face-qa.scanovate.com/api/v2/verify_face_uuid")
                .then()
                .statusCode(200)
                .extract()
                .response();
        System.out.println(clientInit.getBody().asString());
        return clientInit;
    }

    @Step("Face encode v1 api request")
    public Response v1FaceEncodeRequest(String accept, String contentType, File imageToEncode, String group, int statusCode) {
        clientInit = given()
                .header("accept", accept)
                .header("Content-Type", contentType)
                .multiPart("image", imageToEncode, "image/png")
                .multiPart("group", group)
                .when()
                .log()
                .all()
                .post("https://face-qa.scanovate.com/api/v1/face_encode")
                .then()
                .statusCode(statusCode)
                .extract()
                .response();
        System.out.println(clientInit.getBody().asString());
        return clientInit;
    }

    @Step("Face to encode api request")
    public Response FaceToEncodingRequest(String accept, String contentType, File imageToEncode, int statusCode) {
        clientInit = given()
                .header("accept", accept)
                .header("Content-Type", contentType)
                .multiPart("image", imageToEncode, "image/png")
                .when()
                .log()
                .all()
                .post("https://face-qa.scanovate.com/api/v1/face_to_encoding")
                .then()
                .statusCode(statusCode)
                .extract()
                .response();
        System.out.println(clientInit.getBody().asString());
        return clientInit;
    }

    @Step("Face to encode api request")
    public Response encodingCompare(String accept, String contentType, List<Float> firstEncoding, List<Float> secondEncoding, String thresholdVariable, int statusCode) {
        String requestBody = "{\n" +
                "\"singleEncoding\":{\"encoding\":" + firstEncoding +",\"encoding_version\":5"+
                "},\"multipleEncodings\":[{\"encoding\":" + secondEncoding + ",\"encoding_version\":5}]" + "}";
        clientInit = given()
                .header("accept", accept)
                .header("Content-Type", contentType)
                .body(requestBody)
                .when()
                .log()
                .all()
                .post("https://face-qa.scanovate.com/api/v1/compare_multiple_encodings" + thresholdVariable)
                .then()
                .statusCode(statusCode)
                .extract()
                .response();
        System.out.println(clientInit.getBody().asString());
        return clientInit;
    }

    @Step("Face to encode api request")
    public Response multipleFaceCompareRequest(String accept, String contentType, File faceImage, Double threshold, String group, int statusCode) {
        clientInit = given()
                .header("accept", accept)
                .header("Content-Type", contentType)
                .multiPart("image", faceImage, "image/jpg")
                .multiPart("threshold", threshold)
                .multiPart("group", group)
                .when()
                .log()
                .all()
                .post("https://face-qa.scanovate.com/api/v1/multiple_face_compare")
                .then()
                .statusCode(statusCode)
                .extract()
                .response();
        System.out.println(clientInit.getBody().asString());
        return clientInit;
    }

    @Step("create random string")
    public String randomString() {
        // create a string of all characters
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijkelmonpqrstuvwxyz1234567890";
        // create random string builder
        StringBuilder sb = new StringBuilder();
        // create an object of Random class
        Random random = new Random();
        // specify length of random string
        int length = 30;
        for (int i = 0; i < length; i++) {
            // generate random index number
            int index = random.nextInt(alphabet.length());
            // get character specified by index
            // from the string
            char randomChar = alphabet.charAt(index);
            // append the character to string builder
            sb.append(randomChar);
        }

        caseId = sb.toString();
        return caseId;
    }
}
