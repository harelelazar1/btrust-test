package api.faceServer.old;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static io.restassured.RestAssured.given;

public class faceVerificationTest {
    protected String jpgFilePath = "./faceImages/faceVerificationImages/jpgFile";
    protected String jpegFilePath = "./faceImages/faceVerificationImages/jpegFile";
    protected String pngFilePath = "./faceImages/faceVerificationImages/pngFile";
    protected String caseID = randomString();
    protected static final String REQUEST_TYPE = "multipart/form-data";
    protected String message;
    protected int score;
    protected boolean success;

    List<String> score1 = new ArrayList<>();
    List<String> notScore1 = new ArrayList<>();
    List<String> statusCode501 = new ArrayList<>();
    List<String> statusCode504 = new ArrayList<>();

    @Test(description = "Face match")
    @Description("Run face verify request with same faces images and check that the value of score is greater than the value of threshold")
    public void t01_faceVerification() throws InterruptedException {
        File firstImage = new File("./liveness/liad/1592925591224.jpg");
        File secondImage = new File("./liveness/liad/liadFace.jpg");
        Response response = given()
                .multiPart("first_image", firstImage, "image/jpg")
                .multiPart("second_image", secondImage, "image/jpg")
                .multiPart("caseId", caseID)
                .contentType(REQUEST_TYPE)
                .when()
                .log()
                .all()
                .post("https://face-qa.scanovate.com/api/v1/verify_face")
                .then()
                .statusCode(200)
                .extract()
                .response();

        System.out.println(response.getBody().asString());
        JsonPath jsonPath = response.jsonPath();
        Assert.assertTrue(jsonPath.getDouble("score") > jsonPath.getDouble("threshold"));
        Assert.assertTrue(jsonPath.getBoolean("success"));
        Assert.assertEquals(jsonPath.getString("caseId"), caseID);
        Assert.assertEquals(jsonPath.getString("error.message"), "No errors occurred");
        Assert.assertEquals(jsonPath.getInt("error.code"), 0);
    }

    @Test(description = "Face does not match")
    @Description("Run face verify request with 2 different images and Check that the value of threshold is greater than the value of score")
    public void t02_faceDoesNotMatch() {
        File firstImage = new File("./liveness/liad/1592925591224.jpg");
        File secondImage = new File("./liveness/bar/barCenter.jpg");
        Response response = given()
                .multiPart("first_image", firstImage, "image/jpg")
                .multiPart("second_image", secondImage, "image/jpg")
                .multiPart("caseId", caseID)
                .contentType(REQUEST_TYPE)
                .when()
                .log()
                .all()
                .post("https://face-qa.scanovate.com/api/v1/verify_face")
                .then()
                .statusCode(200)
                .extract()
                .response();

        System.out.println(response.getBody().asString());
        JsonPath jsonPath = response.jsonPath();
        Assert.assertTrue(jsonPath.getDouble("score") < jsonPath.getDouble("threshold"));
        Assert.assertTrue(jsonPath.getBoolean("success"));
        Assert.assertEquals(jsonPath.getString("caseId"), caseID);
        Assert.assertEquals(jsonPath.getString("error.message"), "No errors occurred");
        Assert.assertEquals(jsonPath.getInt("error.code"), 0);
    }

    @Test(description = "Without face")
    @Description("Run face verify request with 2 images without face and check that return error code 501")
    public void t03_withOutFace() {
        File firstImage = new File("./liveness/apiPic/face_not_found.jpg");
        File secondImage = new File("./liveness/apiPic/face_not_found.jpg");
        Response response = given()
                .multiPart("first_image", firstImage, "image/jpg")
                .multiPart("second_image", secondImage, "image/jpg")
                .multiPart("caseId", caseID)
                .contentType(REQUEST_TYPE)
                .when()
                .log()
                .all()
                .post("https://face-qa.scanovate.com/api/v1/verify_face")
                .then()
                .statusCode(200)
                .extract()
                .response();

        System.out.println(response.getBody().asString());
        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.getString("error.message"), "Could not detect face");
        Assert.assertEquals(jsonPath.getInt("error.code"), 501);
        Assert.assertEquals(jsonPath.getString("error.caseId"), caseID);
        Assert.assertEquals(jsonPath.getString("error.image"), "first_image");
        Assert.assertFalse(jsonPath.getBoolean("success"));
    }

    @Test(description = "Unsupported files")
    @Description("Run face verify request with 2 unsupported filed and check that return error message")
    public void t04_unsupportedFile() {
        File firstImage = new File("./liveness/audio/0_1611227975025");
        File secondImage = new File("./liveness/audio/0_1611227975025");
        Response response = given()
                .multiPart("first_image", firstImage, "image/jpg")
                .multiPart("second_image", secondImage, "image/jpg")
                .multiPart("caseId", caseID)
                .contentType(REQUEST_TYPE)
                .when()
                .log()
                .all()
                .post("https://face-qa.scanovate.com/api/v1/verify_face")
                .then()
                .statusCode(200)
                .extract()
                .response();

        System.out.println(response.getBody().asString());
        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.getString("error.message"), "Could not open image");
        Assert.assertEquals(jsonPath.getString("error.caseId"), caseID);
        Assert.assertEquals(jsonPath.getString("error.image"), "first_image");
        Assert.assertFalse(jsonPath.getBoolean("success"));
    }

    @Test(enabled = false, description = "Face verification - JPG files")
    @Description("Run api.faceCompare.API request that verify between 2 face images")
    public void t05_faceVerificationJPGFile() throws InterruptedException {
        File folder = new File(jpgFilePath);
        File[] listOfFiles = folder.listFiles();
        for (File file : listOfFiles) {
            Thread.sleep(2000);
//            System.out.println("==========\nChoose file: " + file.getName());
            Response response = given()
                    .multiPart("first_image", file, "image/jpg")
                    .multiPart("second_image", file, "image/jpg")
                    .multiPart("caseId", caseID)
                    .contentType(REQUEST_TYPE)
                    .when()
                    .log()
                    .all()
                    .post("https://face-qa.scanovate.com/api/v1/verify_face")
                    .then()
                    .statusCode(200)
                    .extract()
                    .response();

//            System.out.println(response.getBody().asString());

            success = response.path("success");

            if (success) {
                score = response.path("score");
                if (score == 1) {
                    score1.add(file.getName());
                } else {
                    notScore1.add(file.getName());
                }
            } else {
                message = response.path("error.message");
                if (message.contains("Could not detect face")) {
                    statusCode501.add(file.getName());
                } else if (message.contains("Did not get face encoding")) {
                    statusCode504.add(file.getName());
                }
            }
        }
        System.out.println("The score is 1:");
        for (String s : score1) {
            System.out.println(s);
            System.out.println(score1.size());
        }
        System.out.println("==========\n");

        System.out.println("The score is not 1:");
        for (String s : notScore1) {
            System.out.println(s);
        }
        System.out.println("==========\n");

        System.out.println("Get status code 501 and the error message is Could not detect face:");
        for (String s : statusCode501) {
            System.out.println(s);
        }
        System.out.println("==========\n");

        System.out.println("Get status code 504 and the error message is Did not get face encoding:");
        for (String s : statusCode504) {
            System.out.println(s);
        }
        System.out.println("==========\n");
        Assert.assertEquals(score1.size(), 72);
        Assert.assertEquals(notScore1.size(), 0);
        Assert.assertEquals(statusCode501.size(), 12);
        Assert.assertEquals(statusCode504.size(), 0);
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
        int length = 5;
        for (int i = 0; i < length; i++) {
            // generate random index number
            int index = random.nextInt(alphabet.length());
            // get character specified by index
            // from the string
            char randomChar = alphabet.charAt(index);
            // append the character to string builder
            sb.append(randomChar);
        }

        String caseId = sb.toString();
        return caseId;
    }
}