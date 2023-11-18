package btrust.btrustOne;

import btrust.btrustOne.admin.AdministratorPage;
import btrust.btrustOne.admin.BaseAdminUserTest;
import btrust.btrustOne.admin.dataModule.dataMapper.pageObject.DataMapperPage;
import btrust.btrustOne.admin.dataModule.dataMapper.pageObject.FieldPage;
import btrust.btrustOne.admin.dataModule.dataMapper.pageObject.PopupPage;
import btrust.btrustOne.admin.dataModule.entitiesManagement.pageObject.EntitiesManagerPage;
import btrust.btrustOne.admin.dataModule.entitiesManagement.pageObject.createBusinessCategory.BusinessCategoryPage;
import btrust.btrustOne.admin.dataModule.entitiesManagement.pageObject.createBusinessCategory.BusinessRelationshipsPage;
import btrust.btrustOne.admin.dataModule.entitiesManagement.pageObject.createBusinessCategory.DataProfilePage;
import btrust.btrustOne.admin.usersManager.users.pageObject.EditUserPage;
import btrust.btrustOne.admin.usersManager.users.pageObject.NewUserPage;
import btrust.btrustOne.admin.usersManager.users.pageObject.UsersPage;
import btrust.btrustOne.admin.workflowManagement.triggersPage.pageObject.SegmentationPage;
import btrust.btrustOne.admin.workflowManagement.triggersPage.pageObject.TriggerBuilderPage;
import btrust.btrustOne.admin.workflowManagement.triggersPage.pageObject.TriggerInformationPage;
import btrust.btrustOne.admin.workflowManagement.triggersPage.pageObject.WorkflowPage;
import io.qameta.allure.Description;
import io.qameta.allure.Link;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SkippedAdminTest extends BaseAdminUserTest {


    @Test(enabled = false, priority = 3, description = "Sum fields")
    @Description("Sum all the fields that appear in the content parent and in content child list" +
            "and check that all the fields exist in the content summary")
    public void sumFields() {
        int parentList;
        int childList;
        int summaryList;
        BusinessCategoryPage businessCategoryPage = new BusinessCategoryPage(driver);
        businessCategoryPage.contentsList("Organization Business Relationships");
        BusinessRelationshipsPage businessRelationshipsPage = new BusinessRelationshipsPage(driver);
        parentList = businessRelationshipsPage.businessRelationshipsList("parent");
        businessCategoryPage.contentsList("liad Business Relationships");
        childList = businessRelationshipsPage.businessRelationshipsList("child");
        businessCategoryPage.contentsList("Summary");
        summaryList = businessRelationshipsPage.businessRelationshipsList("summary");
        Assert.assertEquals(summaryList, parentList + childList);
    }
    @Test(enabled = false, priority = 5, description = "Remove fields")
    @Description("Enter to data profile screen," +
            "navigate to child content screen," +
            "click on remove button and check that the fields is not appear in the field list")
    public void removeField() {
        BusinessCategoryPage businessCategoryPage = new BusinessCategoryPage(driver);
        businessCategoryPage.contentsList("liad fields");
        DataProfilePage dataProfilePage = new DataProfilePage(driver);
        dataProfilePage.clickOnRemoveButton("Automation test");

        btrust.btrustOne.admin.dataModule.entitiesManagement.pageObject.createBusinessCategory.PopupPage popupPage = new btrust.btrustOne.admin.dataModule.entitiesManagement.pageObject.createBusinessCategory.PopupPage(driver);
        Assert.assertEquals(popupPage.title(), "Delete Warning!");
        Assert.assertTrue(popupPage.popupIconIsDisplayed());
        Assert.assertEquals(popupPage.popupTitle(), "Deleting a field");
        Assert.assertEquals(popupPage.popupDescription(), "Please confirm that you would like to delete this field.\n" +
                "Notice that this action cannot be reversed.");

        popupPage.closePopup();
        Assert.assertFalse(popupPage.popupIconIsDisplayed());

        dataProfilePage.clickOnRemoveButton("Automation test");
        Assert.assertTrue(popupPage.popupIconIsDisplayed());
        popupPage.clickOnButton("no", "Cancel");

        dataProfilePage.clickOnRemoveButton("Automation test");
        popupPage.clickOnButton("no", "Confirm");

        businessCategoryPage.searchField("Automation test");
        Assert.assertFalse(dataProfilePage.dataFiledListIsDisplayed());
    }
    @Link("https://trello.com/c/dJ5jWUU6")
    @Test(enabled = false, description = "Create new User")
    @Description("Create new user and check if the new user appear in the user list")
    public void Test_01_createNewUser() {
        String userFirstName;
        String userLastName;
        UsersPage userPage = new UsersPage(driver);
        userPage.clickOnAddUserButton();
        NewUserPage newUserPage = new NewUserPage(driver);
        Assert.assertEquals(newUserPage.usersTitle(), "New User");
        newUserPage.fillTextInNewUserPage("input", "First Name", "Harel" + randomString(), null, null, null, null);
        userFirstName = newUserPage.attributeNameFromAddNewUser("First Name");
        newUserPage.fillTextInNewUserPage("input", "Last Name", "Elazar" + randomString(), null, null, null, null);
        userLastName = newUserPage.attributeNameFromAddNewUser("Last Name");
        newUserPage.fillTextInNewUserPage("input", "Job Title", "QA", null, null, null, null);
        newUserPage.fillTextInNewUserPage("inputPhoneNumber", "Phone Number", null, null, null, "Albania", "0508554415");
        newUserPage.fillTextInNewUserPage("input", "Email", "qa." + randomString() + "@gmail.com", null, null, null, null);
        newUserPage.fillTextInNewUserPage("select", null, null, "Sub Company", "TestSub", null, null);
        newUserPage.fillTextInNewUserPage("select", null, null, "Department", "qa", null, null);
        newUserPage.fillTextInNewUserPage("select", null, null, "Role", "God Role", null, null);
        newUserPage.clickOnButton("Create New User");
        newUserPage.fillTextInFieldTypeUserNameToSearch(userFirstName);
        Assert.assertTrue(userPage.userList("first name", userFirstName));
        newUserPage.fillTextInFieldTypeUserNameToSearch(userLastName);
        Assert.assertTrue(userPage.userList("last name", userLastName));
    }

    @Link("https://trello.com/c/dJ5jWUU6")
    @Test(enabled = false, description = "Create new User with more then one sub company and role")
    @Description("Create new user and check if the new user appear in the user list")
    public void Test_03_CreateNewUserWithMoreThen1SubCompanyAndRole() {
        String userFirstName;
        String userLastName;
        UsersPage userPage = new UsersPage(driver);
        userPage.clickOnAddUserButton();
        NewUserPage newUserPage = new NewUserPage(driver);
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
        newUserPage.clickAddNewRolePermission();
        newUserPage.rolePermissions("Sub Company", "g");
        newUserPage.rolePermissions("Department", "g department");
        newUserPage.rolePermissions("Role", "Main Role");
        newUserPage.rolePermissions("Role", "God Role");
        newUserPage.clickOnButton("Create New User");
        newUserPage.fillTextInFieldTypeUserNameToSearch(userFirstName);
        Assert.assertTrue(userPage.userList("first name", userFirstName));
    }

    @Link("https://trello.com/c/dJ5jWUU6")
    @Test(enabled = false, description = "Change the user status")
    @Description("Edit page-change the user status 'Active' to 'Non Active' to 'Active'")
    public void Test_04_CheckChangeTheStatusActiveToNonActive() {
        String userFirstName;
        String userLastName;
        UsersPage usersPage = new UsersPage(driver);
        usersPage.clickOnAddUserButton();
        NewUserPage newUserPage = new NewUserPage(driver);
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
        newUserPage.clickOnButton("Create New User");
        newUserPage.fillTextInFieldTypeUserNameToSearch(userFirstName);
        Assert.assertTrue(usersPage.userList("first name", userFirstName));
        usersPage.click();
        EditUserPage editUserPage = new EditUserPage(driver);
        Assert.assertTrue(editUserPage.userNameStatus("Active"));
        editUserPage.clickChangeStatusButton();
        editUserPage.selectStatusFromList();
        Assert.assertTrue(editUserPage.userNameStatus("Non-active"));
        editUserPage.clickChangeStatusButton();
        editUserPage.selectStatusFromList();
        Assert.assertTrue(editUserPage.userNameStatus("Active"));
    }

    @Link("https://trello.com/c/dJ5jWUU6")
    @Test(enabled = false, description = "Edit user data")
    @Description("Edit page - Change the user data")
    public void Test_05_EditUserData() {
        String userFirstName;
        String userLastName;
        UsersPage usersPage = new UsersPage(driver);
        usersPage.clickOnAddUserButton();
        NewUserPage newUserPage = new NewUserPage(driver);
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
        newUserPage.clickOnButton("Create New User");
        newUserPage.fillTextInFieldTypeUserNameToSearch(userFirstName);
        Assert.assertTrue(usersPage.userList("first name", userFirstName));
        usersPage.click();
        EditUserPage editUserPage = new EditUserPage(driver);
        editUserPage.fillTextInEditUserPage("input", "Job Title", "Test", null, null, null, null);
        editUserPage.fillTextInEditUserPage("inputPhoneNumber", "Phone Number", null, null, null, "Angola", "0508556625");
        editUserPage.clickAddNewRolePermission();
        editUserPage.rolePermissions("Sub Company", "g");
        editUserPage.rolePermissions("Department", "g department");
        editUserPage.rolePermissions("Role", "Main Role");
        editUserPage.clickOnButton("Save");
    }
    @Test(enabled = false, description = "Add new trigger - current date activation date -> status = Active")
    @Description("Triggers page- add new trigger with future activation date")
    public void Test_01_addNewTriggerCurrentDateActivationDate() {
        String triggerName;
        AdministratorPage administratorPage = new AdministratorPage(driver);
        administratorPage.chooseFromSideBar("Triggers");
        TriggerBuilderPage triggerBuilderPage = new TriggerBuilderPage(driver);
        administratorPage.openAllSideBarGroups();
        Assert.assertEquals(triggerBuilderPage.pageTitle(), "Trigger Builder");
        triggerBuilderPage.ClickAddNewTriggerBuilderButton();
        TriggerInformationPage triggerInformationPage = new TriggerInformationPage(driver);
        Assert.assertEquals(triggerInformationPage.pageTitle(), "New Trigger Information");
        triggerInformationPage.fillTextNewTriggerInformation("Trigger Name", "TriggerName", "QA TEST" + randomString(), false, null, null);
        triggerName = triggerInformationPage.attributeNameForTriggerName();
        System.out.println(triggerName);
        triggerInformationPage.fillTextNewTriggerInformation("Activation Date", "ActivationDate", null, false, null, null);
        triggerInformationPage.fillTextNewTriggerInformation("Description", "Description", null, false, null, null);
        triggerInformationPage.fillTextNewTriggerInformation("Trigger Reccurance", "TriggerReoccurrence", null, false, null, "Day");
        triggerInformationPage.clickOnButton("Continue");
        WorkflowPage workflowPage = new WorkflowPage(driver);
        Assert.assertEquals(workflowPage.pageTitle(), "Workflow");
        workflowPage.chooseFromSelectList("Entity Type", "Organization");
        workflowPage.chooseFromSelectList("Business Category", "Client");
        workflowPage.chooseFromWorkflow("Workflow", "W9 IRS");
        workflowPage.clickOnButton("Continue");
        SegmentationPage segmentationPage = new SegmentationPage(driver);
        Assert.assertEquals(segmentationPage.pageTitle(), "Segmentation");
        segmentationPage.createFirstCondition("firstField", "Entity", null, null, null, null);
        segmentationPage.createFirstCondition("secondField", null, "Client", null, null, null);
        segmentationPage.createFirstCondition("thirdField", null, null, "Legal Name", null, null);
        segmentationPage.createFirstCondition("fourthField", null, null, null, "Is", null);
        segmentationPage.createFirstCondition("fifthField", null, null, null, null, "Bank yahav");
        segmentationPage.clickOnButton("Apply");
        Assert.assertEquals(triggerBuilderPage.pageTitle(), "Trigger Builder");
        Assert.assertEquals(triggerBuilderPage.returnTriggerStatus(triggerName), "Active");
    }

    @Test(enabled = false, description = "Add new trigger - future activation date -> status = Scheduled")
    @Description("Triggers page- add new trigger with future activation date")
    public void Test_02_addNewTriggerFutureActivationdate() {
        String triggerName;
        AdministratorPage administratorPage = new AdministratorPage(driver);
        administratorPage.chooseFromSideBar("Triggers");
        TriggerBuilderPage triggerBuilderPage = new TriggerBuilderPage(driver);
        Assert.assertEquals(triggerBuilderPage.pageTitle(), "Trigger Builder");
        triggerBuilderPage.ClickAddNewTriggerBuilderButton();
        TriggerInformationPage triggerInformationPage = new TriggerInformationPage(driver);
        Assert.assertEquals(triggerInformationPage.pageTitle(), "New Trigger Information");
        triggerInformationPage.fillTextNewTriggerInformation("Trigger Name", "TriggerName", "QA TEST" + randomString(), false, null, null);
        triggerName = triggerInformationPage.attributeNameForTriggerName();
        triggerInformationPage.fillTextNewTriggerInformation("Activation Date", "ActivationDate", null, true, "5", null);
        triggerInformationPage.fillTextNewTriggerInformation("Description", "Description", null, true, null, null);
        triggerInformationPage.fillTextNewTriggerInformation("Trigger Reccurance", "TriggerReoccurrence", null, false, null, "Day");
        triggerInformationPage.clickOnButton("Continue");
        WorkflowPage workflowPage = new WorkflowPage(driver);
        Assert.assertEquals(workflowPage.pageTitle(), "Workflow");
        workflowPage.chooseFromSelectList("Entity Type", "Organization");
        workflowPage.chooseFromSelectList("Business Category", "Client");
        workflowPage.chooseFromWorkflow("Workflow", "W9 IRS");
        workflowPage.clickOnButton("Continue");
        SegmentationPage segmentationPage = new SegmentationPage(driver);
        Assert.assertEquals(segmentationPage.pageTitle(), "Segmentation");
        segmentationPage.createFirstCondition("firstField", "Entity", null, null, null, null);
        segmentationPage.createFirstCondition("secondField", null, "Client", null, null, null);
        segmentationPage.createFirstCondition("thirdField", null, null, "Legal Name", null, null);
        segmentationPage.createFirstCondition("fourthField", null, null, null, "Is", null);
        segmentationPage.createFirstCondition("fifthField", null, null, null, null, "Bank yahav");
        segmentationPage.clickOnButton("Apply");
        Assert.assertEquals(triggerBuilderPage.pageTitle(), "Trigger Builder");
        Assert.assertEquals(triggerBuilderPage.returnTriggerStatus(triggerName), "Scheduled");
    }
    @Test(description = "Market place -edit Service",enabled = false)
    public void editService() {
    }
    @Test(description = "Mobile config test",enabled = false)
    @Description("Mobile config test")
    public void mobileTest() {
    }
    @Test(description = "Portal config test",enabled = false)
    @Description("Portal config test")
    public void portalTest() {
    }
    @Test(description = "Dashboard config test",enabled = false)
    @Description("Dashboard config test")
    public void dashBoardTest() {
    }

    @Test(description = "Add workflow", enabled = false)
    @Description("Add workflow")
    public void addWorkflow() {
    }



}