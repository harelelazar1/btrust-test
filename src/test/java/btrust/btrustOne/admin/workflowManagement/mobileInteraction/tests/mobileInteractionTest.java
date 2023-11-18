package btrust.btrustOne.admin.workflowManagement.mobileInteraction.tests;

import btrust.btrustOne.admin.AdministratorPage;
import btrust.btrustOne.admin.BaseAdminUserTest;
import btrust.btrustOne.admin.workflowManagement.mobileInteraction.pageObject.MobileFlowPage;
import btrust.btrustOne.admin.workflowManagement.mobileInteraction.pageObject.MobileInteractionPage;
import btrust.btrustOne.client.login.pageObject.LoginPage;
import btrust.onboardingProcess.api.ApiResponses;
import btrust.onboardingProcess.api.variables.MobileInteraction;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class mobileInteractionTest extends BaseAdminUserTest {


    LoginPage loginPage;

    AdministratorPage administratorPage;
    MobileInteractionPage mobileInteractionPage;
    MobileFlowPage mobileFlowPage;
    MobileInteraction mobileInteraction;
    ApiResponses apiResponses;
    String workflowName = "Automation Test " + randomString();
    String workflowNewName = "Automation new " + randomString();
    int newFlowNumber;
    String flow;
    String viewScript;
    int status = 1;


    @BeforeClass
    @Step("Move to page from side bar")
    public void moveToPageFromSideBar() throws IOException, ParseException {
        loginPage = new LoginPage(driver);
        mobileInteractionPage = new MobileInteractionPage(driver);
        mobileFlowPage = new MobileFlowPage(driver);
        administratorPage = new AdministratorPage(driver);
        mobileInteraction = new MobileInteraction();
        apiResponses = new ApiResponses();
        createNewMobileInteractionFlowForOcr();
        administratorPage.chooseFromSideBar( "Mobile Interaction");
        Assert.assertEquals(mobileInteractionPage.pageTitle(), "Mobile Interaction");
    }


    public void getDataFromJson(String path) {
        try (FileReader reader = new FileReader("./src/test/resources/mobileInteractionData.json")) {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(reader);

            JSONArray ocrArray = (JSONArray) jsonObject.get(path);

            JSONObject ocrObject = (JSONObject) ocrArray.get(0);
            flow = (String) ocrObject.get("flow");
            viewScript = (String) ocrObject.get("viewScript");


            // do something with the flow value
        } catch (IOException | ParseException | ClassCastException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void createNewMobileInteractionFlowForOcr() {
        apiResponses.setNewMobileFlow(mobileInteraction, workflowName, "{}", "{}", 1);
        assertTrue(mobileInteraction.isSuccess());
        assertEquals(mobileInteraction.getErrorCode(), 0);
        newFlowNumber = mobileInteraction.getWorkflowNumber();
        getDataFromJson("ocr");
        apiResponses.setUpdateDataInsideMobileInteractionFlow(mobileInteraction, newFlowNumber, workflowName, flow, viewScript, status);

        Assert.assertTrue(mobileInteraction.isSuccess());
        Assert.assertEquals(mobileInteraction.getErrorCode(), 0);
        Assert.assertEquals(mobileInteraction.getId(), newFlowNumber);
        Assert.assertNotNull(mobileInteraction.getFlow());
        Assert.assertNotNull(mobileInteraction.getViewScript());
        assertEquals(mobileInteraction.getStatus(), 1);
    }


    @Test(description = "Revert the status of mobile interaction flow")
    @Description("Revert the status of mobile interaction flow")
    public void test_01_RevertStatusOfMobileInteractionFlow()  {
        mobileInteractionPage.showingMobileFlowsByStatus("Active");
        Assert.assertEquals(mobileInteractionPage.returnSelectedStatus(), "Active");
        mobileInteractionPage.searchNameAndClick(workflowName);
        Assert.assertFalse(mobileFlowPage.isSaveButtonEnabled());
        mobileFlowPage.checkFlowStatus();
        mobileFlowPage.selectActionFromKebabList("Revert to draft");
        mobileFlowPage.popupScreen("Revert to Draft Warning!", "Revert to Draft");
        Assert.assertEquals(mobileFlowPage.checkFlowStatus(), "Draft");
        mobileFlowPage.clickBackButton();
    }


    @Test(description = "Clone of mobile interaction flow")
    @Description("Clone mobile interaction flow")
    public void test_02_CloneMobileInteractionFlow() {
        mobileInteractionPage.searchNameAndClick(workflowName);
        Assert.assertFalse(mobileFlowPage.isSaveButtonEnabled());
        mobileFlowPage.selectActionFromKebabList("Clone");
        Assert.assertEquals(mobileInteractionPage.pageTitle(), "Mobile Interaction");
        mobileInteractionPage.searchNameAndClick("Copy of " + workflowName);
        mobileFlowPage.selectActionFromKebabList("Delete");
        mobileFlowPage.popupScreen("Delete Warning!", "Delete");
        Assert.assertEquals(mobileInteractionPage.pageTitle(), "Mobile Interaction");
    }


    @Test(description = "Change mobile interaction flow name and delete")
    @Description("Change mobile interaction flow name and delete")
    public void test_03_changeNameOfMobileInteractionFlowAndDelete() {
        mobileInteractionPage.searchNameAndClick(workflowName);
        Assert.assertFalse(mobileFlowPage.isSaveButtonEnabled());
        Assert.assertEquals(mobileFlowPage.checkFlowStatus(), "Draft");
        Assert.assertEquals(mobileFlowPage.changeFlowName(workflowNewName), workflowNewName);
        mobileFlowPage.clickBackButton();
        Assert.assertTrue(mobileInteractionPage.searchNameAndReturnAnswer(workflowNewName));
        mobileInteractionPage.searchNameAndClick(workflowNewName);
        Assert.assertFalse(mobileFlowPage.isSaveButtonEnabled());
        Assert.assertEquals(mobileFlowPage.checkFlowStatus(), "Draft");
        mobileFlowPage.selectActionFromKebabList("Delete");
        mobileFlowPage.popupScreen("Delete Warning!", "Delete");
        Assert.assertEquals(mobileInteractionPage.pageTitle(), "Mobile Interaction");
        Assert.assertFalse(mobileInteractionPage.searchNameAndReturnAnswer(workflowNewName));
    }


}