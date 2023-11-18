package btrust.btrustOne.client.login.test;

import btrust.BaseDesktopViewTest;
import btrust.btrustOne.admin.AdministratorPage;
import btrust.btrustOne.admin.dataModule.entitiesManagement.pageObject.EntitiesManagerPage;
import btrust.btrustOne.client.NavigationPage;
import btrust.btrustOne.client.entity.pagesObject.EntitiesPage;
import btrust.btrustOne.client.login.pageObject.LoginPage;
import btrust.idm.pageObject.DashboardPage;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends BaseDesktopViewTest {


    LoginPage login;
    LoginPage loginPage;
    DashboardPage dashboard;
    AdministratorPage adminPage;
    NavigationPage navigationPage;
    NavigationPage navigation;
    EntitiesManagerPage entitiesManagerTitle;
    EntitiesPage entitiesPage;
    EntitiesManagerPage entitiesManagerPage;


    @BeforeClass(alwaysRun = true)
    @Step("Login to admin user")
    public void enterToLoginPage() {
        driver.get("https://qa-console.scanovateoncloud.com/login");
    }

    @BeforeMethod(alwaysRun = true)
    @Step("createObject")
    public void createObject() {
        login = new LoginPage(driver);
        loginPage = new LoginPage(driver);
        dashboard = new DashboardPage(driver);
        adminPage=new AdministratorPage(driver);
        navigationPage = new NavigationPage(driver);
        navigation = new NavigationPage(driver);
        entitiesManagerTitle = new EntitiesManagerPage(driver);
        entitiesPage = new EntitiesPage(driver);
        entitiesManagerPage = new EntitiesManagerPage(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void refresh() {
        driver.navigate().refresh();
    }

    @Test(description = "Check login success",retryAnalyzer = utilities.RetryAnalyzer.class,groups = {"smoke"})
    @Severity(SeverityLevel.CRITICAL)
    @Description("Type valid user, valid password and click login")
    public void t_01loginSuccessNavigateToUserSide() throws InterruptedException {
        login.loginNewForSecurity( "harelelazar@gmail.com", "Amitbiton20");
        Assert.assertEquals(entitiesManagerPage.entitiesManagerTitle(), "Entities Manager");
        navigation.logOut();
        Assert.assertEquals(login.checkTitleName(),"Login");
    }

//   @Test(description = "Check login success",retryAnalyzer = utilities.RetryAnalyzer.class,groups = {"smoke"})
    @Severity(SeverityLevel.CRITICAL)
    @Description("Type valid user, valid password and click login")
    public void t_02loginSuccessNavigateToAdminSide() {
        driver.get("https://qa-console.scanovateoncloud.com/login");
       login.loginNewForSecurity( "harelelazar@gmail.com", "Amitbiton20");
        Assert.assertEquals(adminPage.administratorTitle(), "Administrator");
        navigation.logOut();
        Assert.assertEquals(login.checkTitleName(),"Login");
    }

    @Test(description = "Check error message when try login without type user details",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("click on login button without type user and password")
    public void t_03loginFailBlankField() {
        login.loginNewForSecurity("", "");

        Assert.assertEquals(login.errorMessage("Wrong credentials"), "Wrong credentials");
    }

    @Test(description = "Check error message when type incorrect user and incorrect password",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("click on login button after type incorrect user and incorrect password")
    public void t_04loginFailWrongUserDetails() {
        login.loginNewForSecurity("qa.zgt9@gmail.com", "12345");

        String expected = login.errorMessage("Wrong credentials");
        Assert.assertEquals(expected, "Wrong credentials");
    }

    @Test(description = "Check error message when type incorrect password",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("click on login button after type valid user and incorrect password")
    public void t_05loginFailWrongPassword() {
        login.loginNewForSecurity( "qa.zygt9@gmail.com", "1234");

        String expected = login.errorMessage("Wrong credentials");
        Assert.assertEquals(expected, "Wrong credentials");
    }

//    @Test(description = "Check error message when try login with user with status Passive",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("click on login button after type details of user with passive status")
    public void t_06passiveStatus() {
        login.login("qatest", "bar@scanovate.com", "RQNQr52nLh");

        String expected = login.errorMessage("Wrong credentials");
        Assert.assertEquals(expected, "Wrong credentials");
    }

 //   @Test(description = "Block user",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Block user for 10 minutes after 3 failures in login")
    public void t_07blockUser() throws InterruptedException {
        login.login("qatest", "harel.elazarbsufa@gmail.com", "Liad");
        String one = login.errorMessage("Wrong credentials");
        Assert.assertEquals(one, "Wrong credentials");

        login.login("qatest", "harel.elazarbsufa@gmail.com", "Liad");
        String two = login.errorMessage("Wrong credentials");
        Assert.assertEquals(two, "Wrong credentials");

        login.login("qatest", "harel.elazarbsufa@gmail.com", "Liad");

        Thread.sleep(660000); //11 min
        driver.get("https://qa-nginx-console.scanovateoncloud.com/");
        login.login("qatest", "harel.elazarbsufa@gmail.com", "Scan12345");
        Assert.assertEquals(dashboard.dashboardTitle(), "New Dashboard");

        navigationPage.logOut();
    }

//    @Test(description = "Reset counter",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Failed 2 times when login to system, success to login, logout from the system, field 1 time to login. success to login and check that the user enter to system")
    public void t_08resetCounter() {
        login.login("qatest", "qa.0vdse@gmail.com", "Liad");
        String one = login.errorMessage("Wrong credentials");
        Assert.assertEquals(one, "Wrong credentials");

        login.login("qatest", "qa.0vdse@gmail.com", "Liad");
        String two = login.errorMessage("Wrong credentials");
        Assert.assertEquals(two, "Wrong credentials");


        login.login("qatest", "qa.0vdse@gmail.com", "Scan1234");
        navigationPage.mainMenuList("Entities");

        navigationPage.logOut();

        login.login("qatest", "qa.0vdse@gmail.com", "Liad");
        String tree = login.errorMessage("Wrong credentials");
        Assert.assertEquals(tree, "Wrong credentials");

        login.login("qatest", "qa.0vdse@gmail.com", "Scan1234");
        Assert.assertEquals(entitiesManagerPage.entitiesManagerTitle(), "Entities Manager");

        navigationPage.logOut();
    }

 //   @Test(description = "Company name not exist",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Type company name that not exist and check that error message is displayed")
    public void t_09companyNameNoExist() {
        loginPage.login("q", "", "");

        Assert.assertEquals(loginPage.errorMessage("We couldn't find the company, please try again"), "We couldn't find the company, please try again");
    }

//    @Test(description = "User details is not connect to company name",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Type user details that not connect to company name and check that error message is displayed")
    public void t_10userDetailsNotConnectToCompany() {
        loginPage.login("Scanovate", "qa@scanovate.com", "Scan2018");
        Assert.assertEquals(loginPage.errorMessage("Wrong credentials"), "Wrong credentials");
    }
}