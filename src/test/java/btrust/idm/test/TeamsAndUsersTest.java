package btrust.idm.test;

import btrust.BaseIdmTest;
import btrust.btrustOne.client.NavigationPage;
import btrust.btrustOne.client.login.pageObject.ChangePasswordPage;
import btrust.btrustOne.client.login.pageObject.LoginPage;
import btrust.idm.pageObject.DashboardPage;
import btrust.idm.pageObject.SettingsPage;
import btrust.idm.pageObject.TeamsUsersPage;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TeamsAndUsersTest extends BaseIdmTest {

    @Test(description = "Enter to teams & users page")
    @Description("Enter to teams & users page and check that all the elements appear")
    public void t_01enterToTeamsUsersPage() {
        NavigationPage navigation = new NavigationPage(driver);
        navigation.settingsButton();
        SettingsPage setting = new SettingsPage(driver);
        setting.chooseTab("Settings", "Teams & Users");
        TeamsUsersPage TeamsUser = new TeamsUsersPage(driver);

        String expected = TeamsUser.teamsUsersTitle("Teams & Users");
        Assert.assertEquals(expected, "Teams & Users");
    }

    @Test(description = "Enter to edit user popup")
    @Description("choose user, Open user's menu, click to edit user and check that edit user popup appear")
    public void t_02enterToEditUserPopup() {
        TeamsUsersPage TeamsUser = new TeamsUsersPage(driver);
        TeamsUser.chooseFromUserMenu("Qa Test", "Edit User");

        String expected = TeamsUser.enterToEditUserPopup("Edit user");
        Assert.assertEquals(expected, "Edit user");

        TeamsUser.cancelButtonEditPopup();
        String teamUsers = TeamsUser.teamsUsersTitle("Teams & Users");
        Assert.assertEquals(teamUsers, "Teams & Users");
    }

    @Test(description = "Edit user")
    @Description("edit name and email of user and click update and check that user appear in the table")
    public void t_03editUserPopup() {
        TeamsUsersPage TeamsUser = new TeamsUsersPage(driver);
        TeamsUser.chooseFromUserMenu("Qa Test", "Edit User");
        TeamsUser.editUser("Qa Test", "testQA89@gmail.com");

        String name = TeamsUser.userName("Qa Test");
        Assert.assertEquals(name, "Qa Test");
        String email = TeamsUser.email("testQA89@gmail.com");
        Assert.assertEquals(email, "testQA89@gmail.com");
    }

    @Test(description = "Check the error message in edit user popup - empty fields")
    @Description("clear the fields and click on update")
    public void t_04errorMessageEmptyFields() {
        TeamsUsersPage TeamsUser = new TeamsUsersPage(driver);
        TeamsUser.newUserButton();
        TeamsUser.addUserCreateButton();

        String nameField = TeamsUser.errorMessageNameField("Please enter the first name and last name of the user you want to add");
        Assert.assertEquals(nameField, "Please enter the first name and last name of the user you want to add");
        String emailField = TeamsUser.errorMessageEmailField("This field is required");
        Assert.assertEquals(emailField, "This field is required");
        String roleSelect = TeamsUser.errorMessageRoleSelect("This field is required");
        Assert.assertEquals(roleSelect, "This field is required");
        String groupSelect = TeamsUser.errorMessageGroupSelect("This field is required");
        Assert.assertEquals(groupSelect, "This field is required");

        TeamsUser.cancelButtonEditPopup();
        String teamUsers = TeamsUser.teamsUsersTitle("Teams & Users");
        Assert.assertEquals(teamUsers, "Teams & Users");
    }

    @Test(description = "Check the error message in edit user popup - wrong value")
    @Description("clear the fields and click on update")
    public void t_05errorMessageEditUserWrongValue() {
        TeamsUsersPage TeamsUser = new TeamsUsersPage(driver);
        TeamsUser.addUser("testQA", "QAtest@gmail", "Admin", "Scanovate QA");

        String nameField = TeamsUser.errorMessageNameField("Please enter at least 2 characters separated by a space");
        Assert.assertEquals(nameField, "Please enter at least 2 characters separated by a space");
        String emailField = TeamsUser.errorMessageEmailField("Please enter a valid email");
        Assert.assertEquals(emailField, "Please enter a valid email");

        TeamsUser.cancelButtonEditPopup();
        String teamUsers = TeamsUser.teamsUsersTitle("Teams & Users");
        Assert.assertEquals(teamUsers, "Teams & Users");
    }

    @Test(description = "Enter to suspend user popup")
    @Description("choose user, Open user's menu, click to suspend user and check that suspend user popup appear")
    public void t_06enterToSuspendUserPopup() {
        TeamsUsersPage TeamsUser = new TeamsUsersPage(driver);
        TeamsUser.chooseFromUserMenu("Qa Test", "Suspend User");

        String suspendPopup = TeamsUser.suspendPopupTitle("Suspend user");
        Assert.assertEquals(suspendPopup, "Suspend user");

        TeamsUser.cancelButtonSuspendPopup();
        String teamUsers = TeamsUser.teamsUsersTitle("Teams & Users");
        Assert.assertEquals(teamUsers, "Teams & Users");
    }

    @Test(description = "Restore user popup")
    @Description("choose user, Open user's menu, click to restore user and check that restore user popup appear")
    public void t_07restoreUserPopup() {
        TeamsUsersPage TeamsUser = new TeamsUsersPage(driver);
        TeamsUser.chooseFromUserMenu("Liad Passive", "Restore User");

        TeamsUser.OKButtonSuspendPopup();
        String expected = TeamsUser.statusUser("Liad Passive");
        Assert.assertEquals(expected, "Active");
    }

    @Test(description = "Suspend user popup")
    @Description("choose user, Open user's menu, click to suspend user and check that suspend user popup appear")
    public void t_08suspendUserPopup() {
        TeamsUsersPage TeamsUser = new TeamsUsersPage(driver);
        TeamsUser.chooseFromUserMenu("Liad Passive", "Suspend User");
        TeamsUser.OKButtonSuspendPopup();

        String expected = TeamsUser.statusUser("Liad Passive");
        Assert.assertEquals(expected, "Passive");
    }

    @Test(description = "Add new user and login")
    @Description("Add new user and check that can login to system with new user")
    public void t_09addUser() {
        TeamsUsersPage TeamsUser = new TeamsUsersPage(driver);
        TeamsUser.addUser("New User", "user@qa.com", "Agent", "Scanovate QA");
        TeamsUser.popupNewUserConfirmationConfirmButton();

        String expected = TeamsUser.popupTitle("User created successfully!");
        Assert.assertEquals(expected, "User created successfully!");

        String pass = TeamsUser.copyPass();
        TeamsUser.popupCreateUserSuccessCloseButton();
        NavigationPage navigation = new NavigationPage(driver);
        navigation.logOut();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Scanovate QA", "user@qa.com", pass);

        ChangePasswordPage changePasswordPage = new ChangePasswordPage(driver);
        Assert.assertTrue(changePasswordPage.changePasswordTitleIsDisplayed("Change Password"));

        changePasswordPage.fillChangePasswordForm(pass, "R4e3w2q1R4e3w2q1", "R4e3w2q1R4e3w2q1");
        changePasswordPage.signInButton();
        LoginPage login = new LoginPage(driver);
        login.login("Scanovate QA", "user@qa.com", "R4e3w2q1R4e3w2q1");

        DashboardPage dashboard = new DashboardPage(driver);
        Assert.assertTrue(dashboard.totalCasesTitle());

        navigation.logOut();
        login.login("Scanovate QA", "qa@scanovate.com", "Scan2018");
        navigation.settingsButton();
        SettingsPage setting = new SettingsPage(driver);
        setting.chooseTab("Settings", "Teams & Users");
        String teamUsers = TeamsUser.teamsUsersTitle("Teams & Users");
        Assert.assertEquals(teamUsers, "Teams & Users");
    }

    @Test(description = "Enter to reset user popup")
    @Description("open the menu of user, click on reset user and check that all the elements appear")
    public void t_10enterToResetUserPopup() {
        TeamsUsersPage TeamsUser = new TeamsUsersPage(driver);
        TeamsUser.chooseFromUserMenu("Qa Test", "Reset password");

        String expected = TeamsUser.resetPassPopupTile("Reset user password");
        Assert.assertEquals(expected, "Reset user password");

        TeamsUser.resetPassPopupCancelButton();
        String teamUsers = TeamsUser.teamsUsersTitle("Teams & Users");
        Assert.assertEquals(teamUsers, "Teams & Users");
    }

    @Test(description = "Enter to delete user popup")
    @Description("open the menu of user, click on delete user and check that all the elements appear")
    public void t_11enterToDeleteUserPopup() {
        TeamsUsersPage TeamsUser = new TeamsUsersPage(driver);
        TeamsUser.chooseFromUserMenu("New User", "Delete user");

        String expected = TeamsUser.deleteUserPopup("Delete user");
        Assert.assertEquals(expected, "Delete user");
        TeamsUser.deleteUserPopupCancelButton();

        String newUser = TeamsUser.userName("New User");
        Assert.assertEquals(newUser, "New User");
    }

    @Test(description = "Delete user")
    @Description("open the menu of user, click on delete user")
    public void t_12deleteUser() {
        TeamsUsersPage TeamsUser = new TeamsUsersPage(driver);
        TeamsUser.chooseFromUserMenu("New User", "Delete user");
        TeamsUser.deleteUserPopupOKButton();
    }
}