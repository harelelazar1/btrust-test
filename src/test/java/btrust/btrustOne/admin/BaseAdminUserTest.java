package btrust.btrustOne.admin;

import btrust.BaseDesktopViewTest;
import btrust.btrustOne.client.NavigationPage;
import btrust.btrustOne.client.login.pageObject.LoginPage;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.Random;

public class BaseAdminUserTest extends BaseDesktopViewTest {
    public static String randomString;

    @BeforeClass
    @Step("Login to admin user")
    public void loginToAdminUser() {
        try {
            driver.get("https://qa-console.scanovateoncloud.com/login");
            LoginPage login = new LoginPage(driver);
            login.loginNewForSecurity("harelelazar@gmail.com", "Amitbiton20");
            AdministratorPage administratorPage = new AdministratorPage(driver);
            Assert.assertEquals(administratorPage.administratorTitle(), "Administrator");
            administratorPage.openAllSideBarGroups();
        } catch (Exception e) {
            System.out.println("exception during login");
        }
    }

    @AfterClass(alwaysRun = true)
    public void logout() {
        NavigationPage navigationPage = new NavigationPage(driver);
        navigationPage.logOut();
    }

    @Step("create random string")
    public static String randomString() {
        // create a string of all characters
        String alphabet = "abcdefghijkelmonpqrstuvwxyz1234567890";
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
        randomString = sb.toString();
        return randomString;
    }
}