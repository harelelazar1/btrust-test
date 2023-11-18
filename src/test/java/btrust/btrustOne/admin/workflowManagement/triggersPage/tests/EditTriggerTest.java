package btrust.btrustOne.admin.workflowManagement.triggersPage.tests;

import btrust.btrustOne.admin.AdministratorPage;
import btrust.btrustOne.admin.BaseAdminUserTest;
import btrust.btrustOne.admin.workflowManagement.triggersPage.pageObject.*;
import btrust.btrustOne.client.login.pageObject.LoginPage;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class EditTriggerTest extends BaseAdminUserTest {

    String triggerName;
    TriggerInformationPage triggerInformationPage;
    EditTriggerPage editTriggerPage;
    WorkflowPage workflowPage;
    SegmentationPage segmentationPage;
    TriggerBuilderPage triggerBuilderPage;
    AdministratorPage administratorPage;

    @Override
    @BeforeClass
    @Step("Administrative tab")
    public void loginToAdminUser() {
        driver.get("https://qa-console.scanovateoncloud.com/login");
        LoginPage login = new LoginPage(driver);
        login.loginNewForSecurity("harelelazar@gmail.com", "Amitbiton20");
        administratorPage = new AdministratorPage(driver);
        administratorPage.openAllSideBarGroups1();
        editTriggerPage = new EditTriggerPage(driver);
        triggerInformationPage = new TriggerInformationPage(driver);
        workflowPage = new WorkflowPage(driver);
        segmentationPage = new SegmentationPage(driver);
        triggerBuilderPage = new TriggerBuilderPage(driver);
    }

    @BeforeMethod
    @Step("Choose module from side bar")
    public void chooseModuleFromSideBar() {
        administratorPage.chooseFromSideBar("Triggers");
        TriggerBuilderPage triggerBuilderPage = new TriggerBuilderPage(driver);
        Assert.assertEquals(triggerBuilderPage.pageTitle(), "Trigger Builder");

    }

    @Step("Create trigger by period")
    public void createTriggerByPeriod(String period) {
        triggerBuilderPage.ClickAddNewTriggerBuilderButton();
        Assert.assertEquals(triggerInformationPage.pageTitle(), "New Trigger Information");
        triggerInformationPage.fillTextNewTriggerInformation("Trigger Name", "TriggerName", "QA TEST" + randomString(), false, null, null);
        triggerName = triggerInformationPage.attributeNameForTriggerName();
        System.out.println(triggerName);
        triggerInformationPage.fillTextNewTriggerInformation("Activation Date", "ActivationDate", null, false, null, null);
        triggerInformationPage.fillTextNewTriggerInformation("Description", "Description", null, false, null, null);
        triggerInformationPage.fillTextNewTriggerInformation("Trigger Recurrence", "TriggerRecurrence", null, false, null, period);
        triggerInformationPage.clickOnButton("Continue");
        Assert.assertEquals(workflowPage.pageTitle(), "Action");
        workflowPage.chooseFromSelectList("Action", "Create case");
        workflowPage.chooseFromSelectList("Entity Type", "Organization");
        workflowPage.chooseFromSelectList("Business Category", "Client");
        workflowPage.chooseFromWorkflow("Workflow", "W9 IRS");
        workflowPage.clickOnButton("Continue");
        Assert.assertEquals(segmentationPage.pageTitle(), "Segmentation");
        segmentationPage.createFirstCondition("firstField", "Entity", null, null, null, null);
        segmentationPage.createFirstCondition("secondField", null, "Organization", null, null, null);
        segmentationPage.createFirstCondition("thirdField", null, null, "legal_name", null, null);
        segmentationPage.createFirstCondition("fourthField", null, null, null, "Is", null);
        segmentationPage.createFirstCondition("fifthField", null, null, null, null, "Bank yahav");
        segmentationPage.clickOnButton("Apply");
    }


    @Test(description = "Edit trigger status", retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Triggers page-> Edit trigger page > change trigger status")
    public void Test_01_editTriggerStatus() {
        createTriggerByPeriod("Day");
        Assert.assertEquals(triggerBuilderPage.pageTitle(), "Trigger Builder");
        triggerBuilderPage.chooseTriggerNameFromList(triggerName, "Active");
        Assert.assertEquals(editTriggerPage.changeTriggerStatus("Active"), "Non Active");
        editTriggerPage.clickCancelOrApplyButton("Apply");
        editTriggerPage.linkBack();
        triggerBuilderPage.chooseTriggerNameFromList(triggerName, "Non Active");
        editTriggerPage.linkBack();
    }


    @Test(description = "Delete trigger", retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Triggers page-> Edit trigger page > delete trigger")
    public void Test_02_DeleteTrigger() {
        createTriggerByPeriod("Day");
        Assert.assertEquals(triggerBuilderPage.pageTitle(), "Trigger Builder");
        triggerBuilderPage.chooseTriggerNameFromList(triggerName, "Active");
        editTriggerPage.deleteTrigger("Confirm");
        Assert.assertEquals(editTriggerPage.returnTriggerStatus(), "Deleted");
        editTriggerPage.linkBack();
        Assert.assertFalse(triggerBuilderPage.checkIfTriggerNameDisplayInList(triggerName));
    }


    @Test(description = "Recurrence is Not Active", retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Triggers page-> Edit trigger page > Recurrence is Not Active")
    public void Test_03_recurrenceIsNotActive() {
        createTriggerByPeriod("Day");
        Assert.assertEquals(triggerBuilderPage.pageTitle(), "Trigger Builder");
        triggerBuilderPage.chooseTriggerNameFromList(triggerName, "Active");
        editTriggerPage.clickCheckboxForRecurrence();
        Assert.assertFalse(editTriggerPage.checkTriggerReccuranceFields("Number"));
        Assert.assertFalse(editTriggerPage.checkTriggerReccuranceFields("Period"));
        Assert.assertFalse(editTriggerPage.checkTriggerReccuranceFields("Time"));
        editTriggerPage.deleteTrigger("Confirm");
        Assert.assertEquals(editTriggerPage.returnTriggerStatus(), "Deleted");
        editTriggerPage.linkBack();
    }


    @Test(description = "Update exist condition", retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Triggers page-> Edit trigger page > edit condition ")
    public void Test_04_recurrenceIsNotActive() {
        createTriggerByPeriod("Day");
        Assert.assertEquals(triggerBuilderPage.pageTitle(), "Trigger Builder");
        triggerBuilderPage.chooseTriggerNameFromList(triggerName, "Active");
        editTriggerPage.createFirstCondition("firstField", "Entity", null, null, null, null);
        editTriggerPage.createFirstCondition("secondField", null, "Organization", null, null, null);
        editTriggerPage.createFirstCondition("thirdField", null, null, "legal_name", null, null);
        editTriggerPage.createFirstCondition("fourthField", null, null, null, "Is", null);
        editTriggerPage.createFirstCondition("fifthField", null, null, null, null, "Bank leumi");
        editTriggerPage.clickCancelOrApplyButton("Apply");
        editTriggerPage.deleteTrigger("Confirm");
        Assert.assertEquals(editTriggerPage.returnTriggerStatus(), "Deleted");
        editTriggerPage.linkBack();
        Assert.assertEquals(triggerBuilderPage.pageTitle(), "Trigger Builder");
    }


    @Test(description = "Add new sub condition", retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Triggers page-> Edit trigger page > Add New Sub Condition")
    public void Test_05_addNewSubCondition() {
        createTriggerByPeriod("Day");
        Assert.assertEquals(triggerBuilderPage.pageTitle(), "Trigger Builder");
        triggerBuilderPage.chooseTriggerNameFromList(triggerName, "Active");
        editTriggerPage.addNewSubConditionButton();
        editTriggerPage.chooseSubConditionOperatorFromList("OR");
        editTriggerPage.createSubCondition("firstField", "Organization", null, null, null);
        editTriggerPage.createSubCondition("secondField", null, "LEI CODE", null, null);
        editTriggerPage.createSubCondition("thirdField", null, null, "Is", null);
        editTriggerPage.createSubCondition("fourthField", null, null, null, "rrr");
        editTriggerPage.addNewSubConditionButton();
        Assert.assertTrue(editTriggerPage.subConditionCheckOperatorName("OR"));
        editTriggerPage.createSubCondition("firstField", "Organization", null, null, null);
        editTriggerPage.createSubCondition("secondField", null, "LEI CODE", null, null);
        editTriggerPage.createSubCondition("thirdField", null, null, "Is", null);
        editTriggerPage.createSubCondition("fourthField", null, null, null, "rrr");
        editTriggerPage.deleteTrigger("Confirm");
        Assert.assertEquals(editTriggerPage.returnTriggerStatus(), "Deleted");
        editTriggerPage.linkBack();
    }


    @Test(description = "Add new group", retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Triggers page-> Edit trigger page > Add Group")
    public void Test_06_addGroup() {
        createTriggerByPeriod("Day");
        Assert.assertEquals(triggerBuilderPage.pageTitle(), "Trigger Builder");
        triggerBuilderPage.chooseTriggerNameFromList(triggerName, "Active");
        editTriggerPage.addNewGroupButton();
        editTriggerPage.chooseAddGroupOperatorFromList("OR");
        editTriggerPage.addGroupAndFillFieldsList("firstField", "Entity", null, null, null, null);
        editTriggerPage.addGroupAndFillFieldsList("secondField", null, "Organization", null, null, null);
        editTriggerPage.addGroupAndFillFieldsList("thirdField", null, null, "VAT CODE TAX IDENTIFICATION NUMBER", null, null);
        editTriggerPage.addGroupAndFillFieldsList("fourthField", null, null, null, "Contains", null);
        editTriggerPage.addGroupAndFillFieldsList("fifthField", null, null, null, null, "123");
        editTriggerPage.addNewGroupButton();
        Assert.assertTrue(editTriggerPage.addGroupCheckOperatorName("OR"));
        editTriggerPage.deleteTrigger("Confirm");
        Assert.assertEquals(editTriggerPage.returnTriggerStatus(), "Deleted");
        editTriggerPage.linkBack();
    }


    @Test(description = "Triggers page-> Edit trigger page > check period name >Week", retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Triggers page-> Edit trigger page > check period name")
    public void Test_07_checkWeekRecurrenceDisplay() {
        createTriggerByPeriod("Week");
        Assert.assertEquals(triggerBuilderPage.pageTitle(), "Trigger Builder");
        triggerBuilderPage.chooseTriggerNameFromList(triggerName, "Active");
        Assert.assertEquals(editTriggerPage.periodName(), "Week");
        editTriggerPage.deleteTrigger("Confirm");
        Assert.assertEquals(editTriggerPage.returnTriggerStatus(), "Deleted");
        editTriggerPage.linkBack();
    }


    @Test(description = "Triggers page-> Edit trigger page > check period name > Month", retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Triggers page-> Edit trigger page > check period name")
    public void Test_08_checkMonthRecurrenceDisplay() {
        createTriggerByPeriod("Month");
        Assert.assertEquals(triggerBuilderPage.pageTitle(), "Trigger Builder");
        triggerBuilderPage.chooseTriggerNameFromList(triggerName, "Active");
        Assert.assertEquals(editTriggerPage.periodName(), "Month");
        editTriggerPage.deleteTrigger("Confirm");
        Assert.assertEquals(editTriggerPage.returnTriggerStatus(), "Deleted");
        editTriggerPage.linkBack();
    }


}
