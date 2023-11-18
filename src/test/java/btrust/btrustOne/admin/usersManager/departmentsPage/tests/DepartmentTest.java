package btrust.btrustOne.admin.usersManager.departmentsPage.tests;

import btrust.btrustOne.admin.AdministratorPage;
import btrust.btrustOne.admin.BaseAdminUserTest;
import btrust.btrustOne.admin.usersManager.departmentsPage.pageObject.DepartmentsPage;
import btrust.btrustOne.admin.usersManager.users.pageObject.UsersPage;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DepartmentTest extends BaseAdminUserTest {

    String departmentNameValue;
    AdministratorPage administratorPage;
    UsersPage usersPage;
    DepartmentsPage departmentsPage;

    @BeforeMethod
    @Step("Enter to department screen")
    public void navigateToDepartmentScreen() {
        administratorPage = new AdministratorPage(driver);
        administratorPage.chooseFromSideBar("Users");
        usersPage = new UsersPage(driver);
        Assert.assertEquals(usersPage.usersTitle(), "Users");
        administratorPage.chooseFromSideBar("Departments");
        departmentsPage = new DepartmentsPage(driver);
        Assert.assertEquals(departmentsPage.departmentTitle(), "Departments");
    }

    @Test(description = "Create new department",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Create new department and check that its appear in the department table")
    public void t01_createNewDepartment() {
        departmentsPage.addNewDepartmentButton();
        departmentsPage.fillDetailsNewDepartment("New Department", "liad " + randomString(), "QA Automation");
        departmentNameValue = departmentsPage.departmentNameFiledValue();
        departmentsPage.clickOnButton("Save");

        Assert.assertEquals(departmentsPage.firstDepartment("Department Name"), departmentNameValue);
    }

    @Test(description = "Close the new department popup",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Enter to new department popup, click on cancel or on close popup button and check that the popup is not displayed")
    public void t02_closeTheNewDepartmentPopup() {
        departmentsPage.addNewDepartmentButton();
        departmentsPage.closePopupButton();
        Assert.assertEquals(departmentsPage.departmentTitle(), "Departments");

        departmentsPage.addNewDepartmentButton();
        departmentsPage.clickOnButton("Cancel");
        Assert.assertEquals(departmentsPage.departmentTitle(), "Departments");
    }

    @Test(description = "Mandatory fields",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Enter to the new department popup, click on save button when the mandatory fields are empty and check that error message are displayed")
    public void t03_mandatoryFields() {
        departmentsPage.addNewDepartmentButton();
        departmentsPage.clickOnButton("Save");

        Assert.assertEquals(departmentsPage.errorMessage("Department Name"), "This field is required");
        Assert.assertEquals(departmentsPage.errorMessage("Sub Company"), "This field is required");

        departmentsPage.clickOnButton("Cancel");
    }

    @Test(description = "Search department",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Perform filter to department list by search filed")
    public void t04_searchField() {
        departmentsPage.searchField("liad");

        Assert.assertTrue(departmentsPage.departmentNames("liad").contains("liad"));
    }

    @Test(description = "No result",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Try to search department that not exist in the department list and check that the department list is not displayed")
    public void t05_noResult() {
        departmentsPage.filterBySubCompany("Qa automation test MBmya");
        Assert.assertFalse(departmentsPage.departmentListIsDisplayed());
        departmentsPage.clearSubCompanyFilterButton();
        Assert.assertTrue(departmentsPage.departmentListIsDisplayed());
        departmentsPage.searchField("bla");

        Assert.assertFalse(departmentsPage.departmentListIsDisplayed());
    }

    @Test(description = "Filter the department list by sub company filter",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Open the ")
    public void t06_filterBuSubCompany() {
        departmentsPage.filterBySubCompany("QA Automation");

        Assert.assertTrue(departmentsPage.departmentListIsDisplayed());
    }
}
