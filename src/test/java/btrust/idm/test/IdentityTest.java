package btrust.idm.test;

import btrust.BaseIdmTest;
import btrust.btrustOne.client.NavigationPage;
import btrust.btrustOne.client.login.pageObject.LoginPage;
import btrust.idm.pageObject.IdentityPage;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class IdentityTest extends BaseIdmTest {
    File file = new File("./liveness/liad/liad_face.jpg");

    @Test(priority = 1, description = "Update biometric DB")
    @Description("Update biometric DB and check that insertedObjId change")
    public void updateBiometricDB() {
        NavigationPage navigationPage = new NavigationPage(driver);
        navigationPage.mainMenuList("Identity");
        IdentityPage identityPage = new IdentityPage(driver);
        Assert.assertTrue(identityPage.identityTableIsDisplay());
        String beforeUpdate = compareImage();
        identityPage.updateBiometricDBButton();
        navigationPage.mainMenuList("Dashboard");
        navigationPage.mainMenuList("Identity");
        Assert.assertTrue(identityPage.identityTableIsDisplay());
        String afterUpdate = compareImage();
        Assert.assertTrue(identityPage.identityTableIsDisplay());
        Assert.assertNotEquals(beforeUpdate, afterUpdate);
    }

    @Test(priority = 2, description = "Update biometric DB button not displayed")
    @Description("Enter to system with not admin user")
    public void updateBiometricDBButtonNotDisplayed() {
        NavigationPage navigationPage = new NavigationPage(driver);
        navigationPage.logOut();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Scanovate QA", "securityuser@qa.com", "R4e3w2q1");
        navigationPage.mainMenuList("Identity");
        IdentityPage identityPage = new IdentityPage(driver);
        Assert.assertFalse(identityPage.updateBiometricDBButtonIsDisplay());
    }

    @Step("Run API request and return the insertedObjId from response")
    public String compareImage() {
        Response response = given()
                .multiPart("image", file, "image/jpg")
                .multiPart("group", "2")
                .contentType("multipart/form-data")
                .log()
                .all()
                .post("https://face-qa.scanovate.com/api/v1/multiple_face_compare")
                .then()
                .statusCode(200)
                .extract()
                .response();

        JsonPath jsonPath = response.jsonPath();
        String insertedObjId = jsonPath.get("[0].insertedObjId");
        System.out.println("insertedObjId - " + insertedObjId);

        return insertedObjId;
    }
}