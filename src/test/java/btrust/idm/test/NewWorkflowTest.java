package btrust.idm.test;

import btrust.BaseIdmTest;
import btrust.btrustOne.client.NavigationPage;
import btrust.idm.pageObject.EditWorkflowPage;
import btrust.idm.pageObject.NewWorkflowPage;
import btrust.idm.pageObject.SettingsPage;
import btrust.idm.pageObject.WorkflowBuilderPage;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class NewWorkflowTest extends BaseIdmTest {

    @BeforeMethod
    public void enterToNewWorkflowPage() {
        NavigationPage navigation = new NavigationPage(driver);
        navigation.settingsButton();
        SettingsPage setting = new SettingsPage(driver);
        setting.chooseTab("Settings", "Teams & Users");
        setting.chooseTab("Settings", "Workflow Builder");
        WorkflowBuilderPage workflowBuilderPage = new WorkflowBuilderPage(driver);
        Assert.assertEquals(workflowBuilderPage.workflowBuilderTitle("Workflow Builder"), "Workflow Builder");
        WorkflowBuilderPage workflowBuilder = new WorkflowBuilderPage(driver);
        workflowBuilder.addNewWorkflow();

        NewWorkflowPage newWorkflow = new NewWorkflowPage(driver);
        String expected = newWorkflow.newWorkflowTitle("New Workflow");
        Assert.assertEquals(expected, "New Workflow");
    }

    @Test(description = "click on breadcrumbs to back to Workflow Builder page")
    @Description("click on breadcrumbs and check that move back to Workflow Builder page")
    public void t01_breadcrumbsPage() {
        NewWorkflowPage newWorkflow = new NewWorkflowPage(driver);
        newWorkflow.breadcrumbsPage();

        WorkflowBuilderPage workflowBuilder = new WorkflowBuilderPage(driver);
        String expected = workflowBuilder.workflowBuilderTitle("Workflow Builder");
        Assert.assertEquals(expected, "Workflow Builder");
    }

    @Test(description = "change name")
    @Description("change name and check that name appear in title page")
    public void t02_changeName() {
        NewWorkflowPage newWorkflow = new NewWorkflowPage(driver);
        newWorkflow.workflowName("Liad");

        String workflowName = newWorkflow.newWorkflowTitle("Liad");
        Assert.assertEquals(workflowName, "Liad");
        String breadcrumbsName = newWorkflow.breadcrumbsPageActive("Liad");
        Assert.assertEquals(breadcrumbsName, "Liad");

    }

    @Test(description = "add new workflow")
    @Description("choose type and type json file and click save")
    public void t03_addNewWorkflow() {
        NewWorkflowPage newWorkflow = new NewWorkflowPage(driver);
        newWorkflow.addDetails("Liad", "Onboarding");

        WorkflowBuilderPage workflowBuilder = new WorkflowBuilderPage(driver);
        String expected = workflowBuilder.workflowName("Liad");
        Assert.assertEquals(expected, "Liad");
    }

    @Test(description = "Check error message - BlankDetails")
    @Description("click on save button without fill details")
    public void t04_errorMessageBlankDetails() {
        NewWorkflowPage newWorkflow = new NewWorkflowPage(driver);
        newWorkflow.clickSave();

        String errorNameField = newWorkflow.errorWorkflowName("Please add a valid workflow name");
        Assert.assertEquals(errorNameField, "Please add a valid workflow name");
        String errorSelect = newWorkflow.errorTypeSelect("Please select workflow type");
        Assert.assertEquals(errorSelect, "Please select workflow type");
        String errorJson = newWorkflow.errorJsonField("field is required");
        Assert.assertEquals(errorJson, "field is required");
    }

    @Test(description = "Check error message - wrong value Json field")
    @Description("type wrong value in Json field and click on save button")
    public void t05_wrongValueJsonField() {
        NewWorkflowPage newWorkflow = new NewWorkflowPage(driver);
        newWorkflow.wrongValueJsonField("blabla");
        newWorkflow.clickSave();

        String errorJson = newWorkflow.errorJsonField("a syntax error at line1");
        Assert.assertEquals(errorJson, "a syntax error at line1");
    }

    @Test(description = "Enter to edit workflow page")
    @Description("Check that all the elements appear in edit workflow page")
    public void t06_enterToEditWorkflowPage() {
        NewWorkflowPage newWorkflow = new NewWorkflowPage(driver);
        newWorkflow.clickCancel();

        WorkflowBuilderPage workflowBuilder = new WorkflowBuilderPage(driver);
        workflowBuilder.editWorkflow("Liad");

        EditWorkflowPage editWorkflow = new EditWorkflowPage(driver);
        String expected = editWorkflow.workflowName("Liad");
        Assert.assertEquals(expected, "Liad");
    }

    @Test(description = "Cancel edit workflow")
    @Description("Click on edit button, change the details and click cancel in approve change")
    public void t07_cancelEditWorkflow() {
        NewWorkflowPage newWorkflow = new NewWorkflowPage(driver);
        newWorkflow.clickCancel();
        WorkflowBuilderPage workflowBuilder = new WorkflowBuilderPage(driver);
        workflowBuilder.editWorkflow("Liad");
        EditWorkflowPage editWorkflow = new EditWorkflowPage(driver);
        editWorkflow.editWorkflowName("Liad QA");
        editWorkflow.cancelButtonPopup();

        String expected = editWorkflow.workflowName("Liad QA");
        Assert.assertEquals(expected, "Liad QA");
    }

    @Test(description = "Edit workflow")
    @Description("Click on edit button, change the details and click approve in approve change")
    public void t08_editWorkflowCancelPopup() {
        NewWorkflowPage newWorkflow = new NewWorkflowPage(driver);
        newWorkflow.clickCancel();
        WorkflowBuilderPage workflowBuilder = new WorkflowBuilderPage(driver);
        workflowBuilder.editWorkflow("Liad");
        EditWorkflowPage editWorkflow = new EditWorkflowPage(driver);
        editWorkflow.editWorkflowName("Liad QA");
        editWorkflow.approveButtonPopup();

        String expected = workflowBuilder.workflowName("Liad QA");
        Assert.assertEquals(expected, "Liad QA");
    }

    @Test(description = "Remove workflow")
    @Description("Click on remove workflow")
    public void t09_removeWorkflow() {
        NewWorkflowPage newWorkflow = new NewWorkflowPage(driver);
        newWorkflow.clickCancel();
        WorkflowBuilderPage workflowBuilder = new WorkflowBuilderPage(driver);
        workflowBuilder.removeWorkflow("Liad QA");
        driver.navigate().refresh();
        SettingsPage settingsPage = new SettingsPage(driver);
        settingsPage.chooseTab("Settings", "Workflow Builder");
        Assert.assertFalse(workflowBuilder.checkRemoveWorkflow("Liad QA"));
    }
}