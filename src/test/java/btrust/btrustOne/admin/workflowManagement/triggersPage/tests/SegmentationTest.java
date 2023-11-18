package btrust.btrustOne.admin.workflowManagement.triggersPage.tests;

import btrust.btrustOne.admin.AdministratorPage;
import btrust.btrustOne.admin.BaseAdminUserTest;
import btrust.btrustOne.admin.workflowManagement.triggersPage.pageObject.SegmentationPage;
import btrust.btrustOne.admin.workflowManagement.triggersPage.pageObject.TriggerBuilderPage;
import btrust.btrustOne.admin.workflowManagement.triggersPage.pageObject.TriggerInformationPage;
import btrust.btrustOne.admin.workflowManagement.triggersPage.pageObject.WorkflowPage;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SegmentationTest extends BaseAdminUserTest {

    String triggerName;
    AdministratorPage administratorPage;
    TriggerBuilderPage triggerBuilderPage;
    WorkflowPage workflowPage;
    TriggerInformationPage triggerInformationPage;

    SegmentationPage segmentationPage;


    @BeforeMethod
    @Step("Goto Segmentation Page")
    public void gotoSegmentationPage() {
        administratorPage = new AdministratorPage(driver);
        administratorPage.chooseFromSideBar("Triggers");
        triggerBuilderPage = new TriggerBuilderPage(driver);
        triggerInformationPage = new TriggerInformationPage(driver);
        workflowPage = new WorkflowPage(driver);
        segmentationPage = new SegmentationPage(driver);
        Assert.assertEquals(triggerBuilderPage.pageTitle(), "Trigger Builder");
        triggerBuilderPage.ClickAddNewTriggerBuilderButton();
        Assert.assertEquals(triggerInformationPage.pageTitle(), "New Trigger Information");
        triggerInformationPage.fillTextNewTriggerInformation("Trigger Name", "TriggerName", "QA TEST" + randomString(), false, null, null);
        triggerName = triggerInformationPage.attributeNameForTriggerName();
        System.out.println(triggerName);
        triggerInformationPage.fillTextNewTriggerInformation("Activation Date", "ActivationDate", null, false, null, null);
        triggerInformationPage.fillTextNewTriggerInformation("Description", "Description", null, false, null, null);
        triggerInformationPage.fillTextNewTriggerInformation("Trigger Recurrence", "TriggerRecurrence", null, false, null, "Day");
        triggerInformationPage.clickOnButton("Continue");
        Assert.assertEquals(workflowPage.pageTitle(), "Action");
        Assert.assertEquals(workflowPage.pageTitle(), "Action");
        workflowPage.chooseFromSelectList("Action", "Create case");
        workflowPage.chooseFromSelectList("Entity Type", "Organization");
        workflowPage.chooseFromSelectList("Business Category", "Client");
        workflowPage.chooseFromWorkflow("Workflow", "W9 IRS");
        workflowPage.clickOnButton("Continue");

    }

    @Test(description = "Segmentation page -> check navigation In Page",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Triggers page-> Segmentation page > check navigation In Page")
    public void Test_01_workflowPageCheckNavigationInPage() {
        Assert.assertEquals(segmentationPage.pageTitle(), "Segmentation");
        Assert.assertTrue(segmentationPage.checkNavigationButtons("Back"));
        Assert.assertFalse(segmentationPage.checkNavigationButtons("Apply"));
        Assert.assertTrue(segmentationPage.checkNavigationButtons("Cancel"));
        segmentationPage.clickOnButton("Back");
        Assert.assertEquals(workflowPage.pageTitle(), "Action");
        workflowPage.clickOnButton("Continue");
        Assert.assertEquals(segmentationPage.pageTitle(), "Segmentation");
        segmentationPage.clickOnButton("Apply");
        Assert.assertEquals(segmentationPage.pageTitle(), "Segmentation");
        segmentationPage.clickOnButton("Cancel");
        Assert.assertEquals(triggerBuilderPage.pageTitle(), "Trigger Builder");
    }


    @Test(description = "Segmentation page -> add sub condition",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Triggers page-> Segmentation page > add sub condition")
    public void Test_02_segmentationPageAddSubcondition() {
        Assert.assertEquals(segmentationPage.pageTitle(), "Segmentation");
        segmentationPage.createFirstCondition("firstField", "Entity", null, null, null, null);
        segmentationPage.createFirstCondition("secondField", null, "Organization", null, null, null);
        segmentationPage.createFirstCondition("thirdField", null, null, "legal_name", null, null);
        segmentationPage.createFirstCondition("fourthField", null, null, null, "Is", null);
        segmentationPage.createFirstCondition("fifthField", null, null, null, null, "Bank yahav");
        segmentationPage.addNewSubConditionButton();
        segmentationPage.chooseSubConditionOperatorFromList("OR");
        segmentationPage.createSubCondition("firstField", "Organization", null, null, null);
        segmentationPage.createSubCondition("secondField", null, "LEI CODE", null, null);
        segmentationPage.createSubCondition("thirdField", null, null, "Is", null);
        segmentationPage.createSubCondition("fourthField", null, null, null, "rrr");
        segmentationPage.addNewSubConditionButton();
        Assert.assertTrue(segmentationPage.subConditionCheckOperatorName("OR"));
        segmentationPage.deleteConditionRow();
        segmentationPage.deleteConditionRow();
        segmentationPage.clickOnButton("Cancel");
        Assert.assertEquals(triggerBuilderPage.pageTitle(), "Trigger Builder");
    }


    @Test(description = "Segmentation page -> add group",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Triggers page-> Segmentation page > add group")
    public void Test_03_segmentationPageAddGroup() {
        Assert.assertEquals(segmentationPage.pageTitle(), "Segmentation");
        segmentationPage.createFirstCondition("firstField", "Entity", null, null, null, null);
        segmentationPage.createFirstCondition("secondField", null, "Organization", null, null, null);
        segmentationPage.createFirstCondition("thirdField", null, null, "legal_name", null, null);
        segmentationPage.createFirstCondition("fourthField", null, null, null, "Is", null);
        segmentationPage.createFirstCondition("fifthField", null, null, null, null, "Bank yahav");
        segmentationPage.addGroupButton();
        segmentationPage.chooseAddGroupOperatorFromList("OR");
        segmentationPage.addGroupAndFillFieldsList("firstField", "Entity", null, null, null, null);
        segmentationPage.addGroupAndFillFieldsList("secondField", null, "Organization", null, null, null);
        segmentationPage.addGroupAndFillFieldsList("thirdField", null, null, "VAT CODE TAX IDENTIFICATION NUMBER", null, null);
        segmentationPage.addGroupAndFillFieldsList("fourthField", null, null, null, "Is", null);
        segmentationPage.addGroupAndFillFieldsList("fifthField", null, null, null, null, "123");
        segmentationPage.addGroupButton();
        Assert.assertTrue(segmentationPage.addGroupCheckOperatorName("OR"));
        segmentationPage.clickOnButton("Cancel");
        Assert.assertEquals(triggerBuilderPage.pageTitle(), "Trigger Builder");
    }


}
