package btrust.btrustOne.admin.usersManager.users.tests;

import btrust.btrustOne.admin.AdministratorPage;
import btrust.btrustOne.admin.BaseAdminUserTest;
import btrust.btrustOne.admin.usersManager.users.pageObject.NewUserPage;
import btrust.btrustOne.admin.usersManager.users.pageObject.UsersPage;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class UsersTest extends BaseAdminUserTest {

    String userFirstName;
    AdministratorPage administratorPage;
    UsersPage usersPage;
    NewUserPage newUserPage;

    @BeforeMethod
    @Step("Enter to Users screen")
    public void navigateToRolesAndPermissionsScreen() {
        driver.get("https://qa-nginx-console.scanovateoncloud.com/settings/data-module/entities-management");
        administratorPage = new AdministratorPage(driver);
        administratorPage.openAllSideBarGroups();
        administratorPage.chooseFromSideBar("Users");
        usersPage = new UsersPage(driver);
        Assert.assertEquals(usersPage.usersTitle(), "Users");
        newUserPage = new NewUserPage(driver);
    }


    @Test(description = "cancel process of create new User",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("start process of create  new user  but choose to click cancel button  check if the user appear in the user list")
    public void Test_01_cancelProcessOfCreateNewUser() {
        UsersPage userPage = new UsersPage(driver);
        userPage.clickOnAddUserButton();
        Assert.assertEquals(newUserPage.usersTitle(), "New User");
        newUserPage.fillTextInNewUserPage("input", "First Name", "Harel" + randomString(), null, null, null, null);
        userFirstName = newUserPage.attributeNameFromAddNewUser("First Name");
        newUserPage.fillTextInNewUserPage("input", "Last Name", "Elazar" + randomString(), null, null, null, null);
        newUserPage.fillTextInNewUserPage("input", "Job Title", "QA", null, null, null, null);
        newUserPage.fillTextInNewUserPage("inputPhoneNumber", "Phone Number", null, null, null, "Albania", "0508554415");
        newUserPage.fillTextInNewUserPage("input", "Email", "qa." + randomString() + "@gmail.com", null, null, null, null);
        newUserPage.fillTextInNewUserPage("select", null, null, "Sub Company", "TestSub", null, null);
        newUserPage.fillTextInNewUserPage("select", null, null, "Department", "qa", null, null);
        newUserPage.fillTextInNewUserPage("select", null, null, "Role", "God Role", null, null);
        newUserPage.clickOnButton("Cancel");
        newUserPage.fillTextInFieldTypeUserNameToSearch(userFirstName);
        Assert.assertFalse(userPage.userList("first name", userFirstName));
    }


    @Test(description = "Filtering options by Sub Company and Department",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Go to user page and choose filter options by 'Sub Company' and 'Department'")
    public void Test_02_FilteringOptionsBySubCompanyAndDepartment() {
        usersPage.filteringOption("Sub Company:", "TestSub");
        usersPage.filteringOption("Department:", "qa");
        Assert.assertTrue(usersPage.checkNameInColumn("Sub Company", "TestSub"));
        Assert.assertTrue(usersPage.checkNameInColumn("Department", "qa"));
    }

    @Test(description = "Filtering options by role",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Go to user page and choose filter options by 'Role'")
    public void Test_03_FilteringOptionsByRole() {
        usersPage.filteringOption("Role:", "test");
        Assert.assertTrue(usersPage.checkNameInColumn("Role", "test"));

    }

    @Test(description = "Filtering options by status - Active",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Go to user page and choose filter options by Status - Active")
    public void Test_04_FilteringOptionsByStatusActive() {
        usersPage.filteringOption("Status:", "Active");
        Assert.assertTrue(usersPage.checkNameInColumn("Status", "Active"));
    }

    @Test(description = "Filtering options by status",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Go to user page and choose filter options by Status - Non Active ")
    public void Test_05_FilteringOptionsByStatusNonActive() {
        usersPage.filteringOption("Status:", "Non-active");
        Assert.assertTrue(usersPage.checkNameInColumn("Status", "Non-active"));
    }
}