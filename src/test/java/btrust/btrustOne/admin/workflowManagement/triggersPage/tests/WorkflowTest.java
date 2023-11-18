package btrust.btrustOne.admin.workflowManagement.triggersPage.tests;

import btrust.btrustOne.admin.AdministratorPage;
import btrust.btrustOne.admin.BaseAdminUserTest;
import btrust.btrustOne.admin.workflowManagement.triggersPage.pageObject.SegmentationPage;
import btrust.btrustOne.admin.workflowManagement.triggersPage.pageObject.TriggerBuilderPage;
import btrust.btrustOne.admin.workflowManagement.triggersPage.pageObject.TriggerInformationPage;
import btrust.btrustOne.admin.workflowManagement.triggersPage.pageObject.WorkflowPage;
import btrust.btrustOne.client.login.pageObject.LoginPage;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WorkflowTest extends BaseAdminUserTest {

    String triggerName;
    TriggerInformationPage triggerInformationPage;
    WorkflowPage workflowPage ;
    TriggerBuilderPage triggerBuilderPage;
    AdministratorPage administratorPage ;
    SegmentationPage segmentationPage;


    @Override
    @BeforeClass
    @Step("Administritive tab")
    public void loginToAdminUser() {
        driver.get("https://qa-console.scanovateoncloud.com/login");
        LoginPage login = new LoginPage(driver);
        login.loginNewForSecurity("harelelazar@gmail.com", "Amitbiton20");

    }

    @BeforeMethod
    @Step("log off and login to btrust")
    public void logOffAndLoginToBtrusr() {
        administratorPage = new AdministratorPage(driver);
        triggerBuilderPage = new TriggerBuilderPage(driver);
        workflowPage = new WorkflowPage(driver);
        triggerInformationPage = new TriggerInformationPage(driver);
        triggerInformationPage = new TriggerInformationPage(driver);
        segmentationPage = new SegmentationPage(driver);
        administratorPage.openAllSideBarGroups();
        administratorPage.chooseFromSideBar("Triggers");
        Assert.assertEquals(triggerBuilderPage.pageTitle(), "Trigger Builder");
        triggerBuilderPage.ClickAddNewTriggerBuilderButton();
        triggerInformationPage.fillTextNewTriggerInformation("Trigger Name", "TriggerName", "QA TEST" + randomString(), false, null, null);
        triggerName = triggerInformationPage.attributeNameForTriggerName();
        triggerInformationPage.fillTextNewTriggerInformation("Activation Date", "ActivationDate", null, false, null, null);
        triggerInformationPage.fillTextNewTriggerInformation("Description", "Description", null, false, null, null);
        workflowPage.clickOnButton("Continue");
    }


    @Test(description = "Workflow page -> check navigation In Page",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Triggers page-> workflow page > check navigation In Page")
    public void Test_01_workflowPageCheckNavigationInPage() {
        Assert.assertEquals(workflowPage.pageTitle(), "Action");
        Assert.assertTrue(workflowPage.checkNavigationButtons("Back"));
        Assert.assertFalse(workflowPage.checkNavigationButtons("Continue"));
        Assert.assertTrue(workflowPage.checkNavigationButtons("Cancel"));
        workflowPage.clickOnButton("Back");
        Assert.assertEquals(triggerInformationPage.pageTitle(), "New Trigger Information");
        triggerInformationPage.clickOnButton("Continue");
        Assert.assertEquals(workflowPage.pageTitle(), "Action");
        workflowPage.clickOnButton("Continue");
        Assert.assertEquals(workflowPage.pageTitle(), "Action");
        workflowPage.clickOnButton("Cancel");
        Assert.assertEquals(triggerBuilderPage.pageTitle(), "Trigger Builder");
    }


    @Test(description = "Action page -> check mandatory fields",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Action page -> check mandatory fields")
    public void Test_02_WorkflowPageCheckMandatoryFields() {
        Assert.assertEquals(workflowPage.pageTitle(), "Action");
        workflowPage.chooseFromSelectList("Action", "Create case");
        Assert.assertFalse(workflowPage.checkNavigationButtons("Continue"));
        workflowPage.chooseFromSelectList("Entity Type", "Organization");
        Assert.assertFalse(workflowPage.checkNavigationButtons("Continue"));
        workflowPage.chooseFromSelectList("Business Category", "Client");
        Assert.assertFalse(workflowPage.checkNavigationButtons("Continue"));
        workflowPage.chooseFromWorkflow("Workflow", "W9 IRS");
        Assert.assertTrue(workflowPage.checkNavigationButtons("Continue"));
        workflowPage.clickOnButton("Continue");
        Assert.assertEquals(segmentationPage.pageTitle(), "Segmentation");
        triggerInformationPage.clickOnButton("Cancel");
        Assert.assertEquals(triggerBuilderPage.pageTitle(), "Trigger Builder");
    }

}
