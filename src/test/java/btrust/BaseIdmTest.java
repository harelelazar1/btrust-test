package btrust;

import btrust.btrustOne.client.login.pageObject.LoginPage;
import io.qameta.allure.Step;
import org.testng.annotations.BeforeClass;

public class BaseIdmTest extends BaseDesktopViewTest {

    @BeforeClass
    @Step("Login to idm system")
    public void loginToIdmSystem() {
        driver.get("https://qa-nginx-console.scanovateoncloud.com/");
        LoginPage login = new LoginPage(driver);
        login.login("qatest", "harel.elazar+test1@scanovate.com", "Scan1234");
    }
}