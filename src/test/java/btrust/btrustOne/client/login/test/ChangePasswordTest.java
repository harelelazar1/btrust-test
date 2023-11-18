//package btrust.btrustOne.client.login.test;
//
//import btrust.BaseDesktopViewTest;
//import btrust.btrustOne.client.NavigationPage;
//import btrust.btrustOne.client.login.pageObject.ChangePasswordPage;
//import btrust.btrustOne.client.login.pageObject.LoginPage;
//import btrust.idm.pageObject.DashboardPage;
//import io.qameta.allure.Description;
//import io.qameta.allure.Severity;
//import io.qameta.allure.SeverityLevel;
//import org.testng.Assert;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//import utilities.DBUtils;
//
//public class ChangePasswordTest extends BaseDesktopViewTest {
//
//    ChangePasswordPage changePasswordPage;
//    LoginPage login;
//    DashboardPage dashboard;
//    NavigationPage navigation;
//
//    @BeforeClass
//    public void resetPassword() {
//        driver.get("https://qa-nginx-console.scanovateoncloud.com/");
//        DBUtils dbUtils = new DBUtils();
//        Assert.assertTrue(dbUtils.resetPassword());
//        Assert.assertTrue(dbUtils.updatePassword());
//    }
//
//    @BeforeMethod
//    public void login() {
//        login = new LoginPage(driver);
//        changePasswordPage = new ChangePasswordPage(driver);
//        dashboard = new DashboardPage(driver);
//        navigation = new NavigationPage(driver);
//        login.login("qatest", "harel.elazar+test1@scanovate.com", "Scan1234");
//        Assert.assertTrue(changePasswordPage.changePasswordTitleIsDisplayed("Change Password"));
//    }
//
//
//    @Test(description = "Type password without uppercase letter",enabled = false)
//    @Description("Type password without uppercase letter and check that appear error message")
//    public void t01_typePasswordWithoutUppercaseLetter() {
//        changePasswordPage.fillChangePasswordForm("Scan1234", "liad123", "liad123");
//        changePasswordPage.signInButton();
//
//        Assert.assertTrue(changePasswordPage.errorMessageIsDisplayed("Please enter a minimum length of 8 characters and an uppercase letter"));
//    }
//
//    @Test(description = "Type less than 7 characters",enabled = false)
//    @Description("Type less than 7 characters and check that appear error message")
//    public void t02_typeLessThan7Characters() {
//        changePasswordPage.fillChangePasswordForm("Scan1234", "Liad123", "Liad123");
//        changePasswordPage.signInButton();
//
//        Assert.assertTrue(changePasswordPage.errorMessageIsDisplayed("Please enter a minimum length of 8 characters"));
//
//    }
//
//    @Test(description = "Type password without lowercase letter",enabled = false)
//    @Description("Type password without lowercase letter and check that appear error message")
//    public void t03_typePasswordWithoutLowercaseLetter() {
//        changePasswordPage.fillChangePasswordForm("Scan1234", "LIAD1234", "LIAD1234");
//        changePasswordPage.signInButton();
//
//        Assert.assertTrue(changePasswordPage.errorMessageIsDisplayed("Please enter an lowercase letter"));
//    }
//
//    @Test(description = "Type password without numbers",enabled = false)
//    @Description("Type password without numbers and check that appear error message")
//    public void t04_typePasswordWithoutNumbers() {
//        changePasswordPage.fillChangePasswordForm("Scan1234", "LIADliad", "LIADliad");
//        changePasswordPage.signInButton();
//
//        Assert.assertTrue(changePasswordPage.errorMessageIsDisplayed("Please enter a number"));
//    }
//
//    @Test(description = "Type password without letters",enabled = false)
//    @Description("Type password without letters and check that appear error message")
//    public void t05_typePasswordWithoutLetters() {
//        changePasswordPage.fillChangePasswordForm("Scan1234", "12341234", "12341234");
//        changePasswordPage.signInButton();
//
//        Assert.assertTrue(changePasswordPage.errorMessageIsDisplayed("Please enter an uppercase letter and a lowercase letter"));
//    }
//
//    @Test(description = "Type wrong temporary password",enabled = false)
//    @Description("Type wrong temporary password and check that appear error message")
//    public void t06_typeWrongTemporaryPassword() {
//        changePasswordPage.fillChangePasswordForm("Liad1234", "Liad1234", "Liad1234");
//        changePasswordPage.signInButton();
//
//        Assert.assertTrue(changePasswordPage.errorMessageIsDisplayed("Passwords do not match"));
//    }
//
//    @Test(description = "Type password without letters and numbers",enabled = false)
//    @Description("Type password without letters and numbers and check that appear error message")
//    public void t07_typePasswordWithoutLettersAndNumbers() {
//        changePasswordPage.fillChangePasswordForm("Scan1234", "!@#$!@#$", "!@#$!@#$");
//        changePasswordPage.signInButton();
//
//        Assert.assertTrue(changePasswordPage.errorMessageIsDisplayed("Please enter a number an uppercase letter and a lowercase letter"));
//    }
//
//    @Test(description = "Type other new password and confirm password",enabled = false)
//    @Description("Type other new password and confirm password and numbers and check that appear error message")
//    public void t08_typeOtherNewPassAndConfirmPass() {
//        changePasswordPage.fillChangePasswordForm("Scan1234", "Liad1234", "Nadav1234");
//        changePasswordPage.signInButton();
//
//        Assert.assertTrue(changePasswordPage.errorMessageIsDisplayed("Passwords does not match"));
//    }
//
//    @Test(description = "Type password with numbers, letters and special characters",enabled = false)
//    @Severity(SeverityLevel.CRITICAL)
//    @Description("Type password with numbers, letters and special characters and perform login to system")
//    public void t09_changePasswordWithAllTheRoles() {
//        changePasswordPage.fillChangePasswordForm("Scan1234", "Liad1234!", "Liad1234!");
//        changePasswordPage.signInButton();
//
//        LoginPage loginPage = new LoginPage(driver);
//        loginPage.login("qatest", "harel.elazar+test1@scanovate.com", "Liad1234!");
//        Assert.assertEquals(dashboard.dashboardTitle(), "New Dashboard");
//        navigation.logOut();
//
//    }
//}