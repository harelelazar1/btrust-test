package btrust.btrustOne.admin.workflowManagement.triggersPage.tests;

import btrust.btrustOne.admin.AdministratorPage;
import btrust.btrustOne.admin.BaseAdminUserTest;
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

public class NewTriggerInformationTest extends BaseAdminUserTest {

    String triggerName;
    TriggerInformationPage triggerInformationPage;
    WorkflowPage workflowPage ;
    TriggerBuilderPage triggerBuilderPage;
    AdministratorPage administratorPage ;


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
        administratorPage.openAllSideBarGroups();
        administratorPage.chooseFromSideBar("Triggers");
        Assert.assertEquals(triggerBuilderPage.pageTitle(), "Trigger Builder");
        triggerBuilderPage.ClickAddNewTriggerBuilderButton();

    }

    @Test(description = "New Trigger Information page -> check navigation In Page",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Triggers page-> New Trigger Information page > check navigation In Page")
    public void Test_01_newTriggerInformationPageCheckNavigationInPage() {
        Assert.assertEquals(triggerInformationPage.pageTitle(), "New Trigger Information");
        Assert.assertFalse(triggerInformationPage.checkNavigationButtons("Back"));
        Assert.assertFalse(triggerInformationPage.checkNavigationButtons("Continue"));
        Assert.assertTrue(triggerInformationPage.checkNavigationButtons("Cancel"));
        triggerInformationPage.clickOnButton("Back");
        Assert.assertEquals(triggerInformationPage.pageTitle(), "New Trigger Information");
        triggerInformationPage.clickOnButton("Continue");
        Assert.assertEquals(triggerInformationPage.pageTitle(), "New Trigger Information");
        triggerInformationPage.clickOnButton("Cancel");
        Assert.assertEquals(triggerBuilderPage.pageTitle(), "Trigger Builder");
    }


    @Test(description = "New Trigger Information page -> check mandatory fields",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("New Trigger Information page- check mandatory fields")
    public void Test_02_newTriggerInformationPageCheckMandatoryFields() {
        triggerInformationPage.fillTextNewTriggerInformation("Trigger Name", "TriggerName", "QA TEST" + randomString(), false, null, null);
        triggerName = triggerInformationPage.attributeNameForTriggerName();
        Assert.assertFalse(triggerInformationPage.checkNavigationButtons("Continue"));
        triggerInformationPage.fillTextNewTriggerInformation("Activation Date", "ActivationDate", null, false, null, null);
        triggerInformationPage.fillTextNewTriggerInformation("Description", "Description", null, false, null, null);
        Assert.assertTrue(triggerInformationPage.checkNavigationButtons("Continue"));
        triggerInformationPage.clickOnButton("Continue");
        Assert.assertEquals(workflowPage.pageTitle(), "Action");
        triggerInformationPage.clickOnButton("Cancel");
        Assert.assertEquals(triggerBuilderPage.pageTitle(), "Trigger Builder");
    }


    @Test(description = "New Trigger Information page -> check checkbox Recurrence is Active",retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("New Trigger Information page - check checkbox Recurrence is Active")
    public void Test_03_newTriggerInformationPageCheckMandatoryFields() {
        triggerInformationPage.fillTextNewTriggerInformation("Trigger Name", "TriggerName", "QA TEST" + randomString(), false, null, null);
        triggerName = triggerInformationPage.attributeNameForTriggerName();
        triggerInformationPage.fillTextNewTriggerInformation("Activation Date", "ActivationDate", null, false, null, null);
        triggerInformationPage.fillTextNewTriggerInformation("Description", "Description", null, false, null, null);
        triggerInformationPage.clickOnCheckbox();
        Assert.assertFalse(triggerInformationPage.checkTriggerRecurrenceFields("Trigger Recurrence", "Number"));
        Assert.assertFalse(triggerInformationPage.checkTriggerRecurrenceFields("Trigger Recurrence", "Period"));
        Assert.assertFalse(triggerInformationPage.checkTriggerRecurrenceFields("Trigger Recurrence", "Time"));
        triggerInformationPage.clickOnCheckbox();
        Assert.assertTrue(triggerInformationPage.checkTriggerRecurrenceFields("Trigger Recurrence", "Number"));
        Assert.assertTrue(triggerInformationPage.checkTriggerRecurrenceFields("Trigger Recurrence", "Period"));
        Assert.assertTrue(triggerInformationPage.checkTriggerRecurrenceFields("Trigger Recurrence", "Time"));
        triggerInformationPage.clickOnButton("Cancel");
        Assert.assertEquals(triggerBuilderPage.pageTitle(), "Trigger Builder");
    }


}


