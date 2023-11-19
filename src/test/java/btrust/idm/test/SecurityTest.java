package btrust.idm.test;

import btrust.BaseIdmTest;
import btrust.btrustOne.client.NavigationPage;
import btrust.btrustOne.client.login.pageObject.LoginPage;
import btrust.idm.pageObject.DashboardPage;
import btrust.idm.pageObject.LicensePage;
import btrust.idm.pageObject.SettingsPage;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SecurityTest extends BaseIdmTest {

    @BeforeClass
    @Override
    public void loginToIdmSystem() {
        driver.get("https://btrustqa.scanovate.com");
        LoginPage login = new LoginPage(driver);
        login.login("Scanovate QA", "securityuser@qa.com", "R4e3w2q1");
        DashboardPage dashboard = new DashboardPage(driver);
        Assert.assertTrue(dashboard.totalCasesTitle());
    }

    @AfterMethod
    public void sleep() throws InterruptedException {
        driver.get("https://btrustqa.scanovate.com/");
        driver.navigate().refresh();
        DashboardPage dashboardPage = new DashboardPage(driver);
        Assert.assertTrue(dashboardPage.totalCasesTitle());
        Thread.sleep(5000);
    }

    @Test(description = "Block user - settings page")
    @Description("Check that user can't enter to settings page")
    public void t01_blockUserSettingsPage() {
        driver.get("https://btrustqa.scanovate.com/settings");

        SettingsPage setting = new SettingsPage(driver);
        setting.blockMessage();
        Assert.assertTrue(setting.blockMessage());
    }

    @Test(description = "Block user - admin page")
    @Description("Check that user can't enter to admin page")
    public void t02_blockUserAdmin() {
        driver.get("https://btrustqa.scanovate.com/admin");
        SettingsPage setting = new SettingsPage(driver);
        Assert.assertTrue(setting.blockMessage());
    }

    @Test(description = "Block user - cases page")
    @Description("Check that user can't enter to cases page")
    public void t03_blockUserCases() {
        NavigationPage navigation = new NavigationPage(driver);
        navigation.logOut();
        LoginPage login = new LoginPage(driver);
        login.login("securityTest", "liadtu1@qa.com", "Liad1234");
        DashboardPage dashboardPage = new DashboardPage(driver);
        Assert.assertTrue(dashboardPage.totalCasesTitle());
        driver.get("https://btrustqa.scanovate.com/cases");
        LicensePage licensePage = new LicensePage(driver);
        licensePage.blockMessage();
        Assert.assertTrue(licensePage.blockMessage());
    }

    @Test(description = "Block user - identity page")
    @Description("Check that user can't enter to identity page")
    public void t04_blockUserIdentity() {
        NavigationPage navigation = new NavigationPage(driver);
        navigation.logOut();
        LoginPage login = new LoginPage(driver);
        login.login("securityTest", "liadtu1@qa.com", "Liad1234");
        DashboardPage dashboardPage = new DashboardPage(driver);
        Assert.assertTrue(dashboardPage.totalCasesTitle());
        driver.get("https://btrustqa.scanovate.com/identity");
        LicensePage license = new LicensePage(driver);
        Assert.assertTrue(license.blockMessage());
    }

    @Test(description = "Block user - search page")
    @Description("Check that user can't enter to search page")
    public void t05_blockUserSearch() {
        NavigationPage navigation = new NavigationPage(driver);
        navigation.logOut();
        LoginPage login = new LoginPage(driver);
        login.login("securityTest", "liadtu1@qa.com", "Liad1234");
        DashboardPage dashboardPage = new DashboardPage(driver);
        Assert.assertTrue(dashboardPage.totalCasesTitle());
        driver.get("https://btrustqa.scanovate.com/search");
        LicensePage license = new LicensePage(driver);
        Assert.assertTrue(license.blockMessage());
    }
}