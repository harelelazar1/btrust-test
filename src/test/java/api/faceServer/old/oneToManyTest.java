package api.faceServer.old;

import io.qameta.allure.Description;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.enableLoggingOfRequestAndResponseIfValidationFails;
import static io.restassured.RestAssured.given;

public class oneToManyTest {
    protected String insertedObjId;
    protected static final String REQUEST_TYPE = "multipart/form-data";

    File faceImage = new File("./liveness/bar/barCenter.jpg");
    File imageWithoutFace = new File("./faceImages/imageWithoutFace/withoutFace.jpg");
    File unSupportFile = new File("./faceImages/unSupportFile/unSupportFile.json");

    @Test(description = "Insert Image")
    @Description("Insert image to DB")
    public void t01_insertImage() {
        Response response = given()
                .multiPart("image", faceImage, "image/jpg")
                .contentType(REQUEST_TYPE)
                .log()
                .all()
                .post("https://face-qa.scanovate.com/api/v1/face_encode")
                .then()
                .statusCode(200)
                .extract()
                .response();

        System.out.println(response.getBody().asString());
        JsonPath jsonPath = response.jsonPath();
        insertedObjId = jsonPath.getString("insertedObjId");
        System.out.println(insertedObjId);
    }

    @Test(description = "Compare image")
    @Description("Check that found image that exist in DB")
    public void t02_compareImage() {
        Response response = given()
                .multiPart("image", faceImage, "image/jpg")
                .contentType(REQUEST_TYPE)
                .log()
                .all()
                .post("https://face-qa.scanovate.com/api/v1/multiple_face_compare")
                .then()
                .statusCode(200)
                .extract()
                .response();

        System.out.println(response.getBody().asString());
        JsonPath jsonPath = response.jsonPath();
//        Assert.assertEquals(jsonPath.get("[0].insertedObjId"), insertedObjId);
        for (int i = 0; i < jsonPath.getList("").size(); i++) {
            if (jsonPath.getString("[" + i + "].insertedObjId").equalsIgnoreCase(insertedObjId)) {
                Assert.assertEquals(jsonPath.get("[" + i + "].insertedObjId"), insertedObjId);
            } else System.out.println("No match");
        }
    }

    @Test(description = "Delete image")
    @Description("Delete image that exist in DB")
    public void t03_deleteImage() {
        Response response = given()
                .pathParam("insertedObjId", insertedObjId)
                .when()
                .log()
                .all()
                .delete("https://face-qa.scanovate.com/api/v1/face_encode?db_object_id={insertedObjId}")
                .then()
                .statusCode(200)
                .extract()
                .response();

        System.out.println(response.getBody().asString());
        Assert.assertFalse(response.getBody().asString() == null);
    }

    @Test(description = "Image not exist")
    @Description("Check that the request not return data when search image that not exist in DB")
    public void t04_ImageNotExist() {
        Response response = given()
                .multiPart("image", faceImage, "image/jpg")
                .contentType(REQUEST_TYPE)
                .log()
                .all()
                .post("https://face-qa.scanovate.com/api/v1/multiple_face_compare")
                .then()
                .extract()
                .response();

        System.out.println(response.getBody().asString());
        Assert.assertNotNull(response.getBody().asString());
    }

    @Test(description = "Face not exist")
    @Description("Check that can not insert image without face and get in response error message + status code 504")
    public void t05_faceNotExist() {
        Response response = given()
                .multiPart("image", imageWithoutFace, "image/jpg")
                .contentType(REQUEST_TYPE)
                .log()
                .all()
                .post("https://face-qa.scanovate.com/api/v1/face_encode")
                .then()
                .statusCode(200)
                .extract()
                .response();

        System.out.println(response.getBody().asString());
        JsonPath jsonPath = response.jsonPath();

        Assert.assertEquals(jsonPath.getString("error.message"), "Did not get face encoding");
        Assert.assertEquals(jsonPath.getInt("error.code"), 504);
        Assert.assertEquals(jsonPath.getBoolean("success"), false);
    }

    @Test(description = "Compare image without face")
    @Description("Check that get error message when try found image without face")
    public void t06_compareImageWithoutFace() {
        Response response = given()
                .multiPart("image", imageWithoutFace, "image/jpg")
                .contentType(REQUEST_TYPE)
                .log()
                .all()
                .post("https://face-qa.scanovate.com/api/v1/multiple_face_compare")
                .then()
                .statusCode(200)
                .extract()
                .response();

        System.out.println(response.getBody().asString());
        JsonPath jsonPath = response.jsonPath();

        Assert.assertEquals(jsonPath.getString("error.message"), "Did not get face encoding");
        Assert.assertEquals(jsonPath.getInt("error.code"), 504);
        Assert.assertEquals(jsonPath.getBoolean("success"), false);
    }

    @Test(description = "Insert UnSupport file")
    @Description("Check that can not insert un support file")
    public void t07_insertUnSupportFile() {
        Response response = given()
                .multiPart("image", unSupportFile, "image/jpg")
                .contentType(REQUEST_TYPE)
                .log()
                .all()
                .post("https://face-qa.scanovate.com/api/v1/face_encode")
                .then()
                .statusCode(500)
                .extract()
                .response();

        System.out.println(response.getBody().asString());
        JsonPath jsonPath = response.jsonPath();

        Assert.assertEquals(jsonPath.getString("error.message"), "Could not open image");
        Assert.assertFalse(jsonPath.getBoolean("success"));
    }

    @Test(description = "Compare UnSupportFile")
    @Description("Check that get error message when try compare un support file")
    public void t08_compareUnSupportFile() {
        Response response = given()
                .multiPart("image", unSupportFile, "image/jpg")
                .contentType(REQUEST_TYPE)
                .log()
                .all()
                .post("https://face-qa.scanovate.com/api/v1/multiple_face_compare")
                .then()
                .statusCode(500)
                .extract()
                .response();

        System.out.println(response.getBody().asString());
        JsonPath jsonPath = response.jsonPath();

        Assert.assertEquals(jsonPath.getString("error.message"), "Could not open image");
        Assert.assertFalse(jsonPath.getBoolean("success"));
    }
}