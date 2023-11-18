package btrust.idm.test;

import btrust.BaseIdmTest;
import btrust.btrustOne.client.NavigationPage;
import btrust.btrustOne.client.login.pageObject.LoginPage;
import btrust.idm.pageObject.SettingsPage;
import btrust.idm.pageObject.WorkflowBuilderPage;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WorkflowBuilderTest extends BaseIdmTest {

    @BeforeClass
    @Override
    public void loginToIdmSystem() {
        driver.get("https://btrustqa.scanovate.com");
        LoginPage login = new LoginPage(driver);
        login.login("Scanovate QA", "qa@scanovate.com", "Scan2018");
        NavigationPage navigation = new NavigationPage(driver);
        navigation.settingsButton();
    }

    @BeforeMethod
    public void navigate() {
        driver.navigate().refresh();
        SettingsPage setting = new SettingsPage(driver);
        setting.chooseTab("Settings", "Teams & Users");
        setting.chooseTab("Settings", "Workflow Builder");
    }

    @Test(description = "Enter to workflow builder page")
    @Description("Enter to workflow builder page and check that all the elements appear")
    public void t_01enterToWorkflowBuilderPage() {
        WorkflowBuilderPage workflowBuilder = new WorkflowBuilderPage(driver);
        String expected = workflowBuilder.workflowBuilderTitle("Workflow Builder");
        Assert.assertEquals(expected, "Workflow Builder");
    }

    @Test(description = "filter by contain text field 3 char")
    @Description("Type 3 characters in contain text filed")
    public void t_02filterByContainTextField3Char() {
        WorkflowBuilderPage workflowBuilder = new WorkflowBuilderPage(driver);
        workflowBuilder.containTextField("onb");

        String expected = workflowBuilder.workflowName("Onboarding");
        Assert.assertEquals(expected, "Onboarding");
    }

    @Test(description = "Check that you can search after type full name")
    @Description("Type full name in contain text filed")
    public void t_03filterByContainTextFieldFullName() {
        WorkflowBuilderPage workflowBuilder = new WorkflowBuilderPage(driver);
        workflowBuilder.containTextField("Onboarding");

        String expected = workflowBuilder.workflowName("Onboarding");
        Assert.assertEquals(expected, "Onboarding");
    }

    @Test(description = "No workflow were found - Heb")
    @Description("Type a value in Hebrew that is not in the list in contain text field")
    public void t_04noWorkflowWereFoundMessageHeb() {
        WorkflowBuilderPage workflowBuilder = new WorkflowBuilderPage(driver);
        workflowBuilder.containTextField("בלה");

        String expected = workflowBuilder.noWorkflowWereFoundMessage("No workflow were found");
        Assert.assertEquals(expected, "No workflow were found");
    }
}