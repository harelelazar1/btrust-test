package btrust.onboardingProcess.api.v2;

import btrust.onboardingProcess.api.ApiResponses;
import btrust.onboardingProcess.api.Utilities;
import btrust.onboardingProcess.api.variables.EndpointResults;
import btrust.onboardingProcess.api.variables.MobileInteraction;
import io.qameta.allure.Description;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utilities.SuiteListener;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

import static btrust.btrustOne.admin.BaseAdminUserTest.randomString;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
@Listeners({SuiteListener.class})
public class CreateMobileInteractionFlowTest {

    Map<String, File> processes;
    EndpointResults endpointResults;
    Utilities utilities;
    ApiResponses apiResponses;

    MobileInteraction mobileInteraction;


    int newFlowNumber;
    String flow;
    String viewScript;
    int status = 1;

    @BeforeMethod(alwaysRun = true)
    public void initSession() {

        endpointResults = new EndpointResults();
        processes = new HashMap<>();
        utilities = new Utilities();
        apiResponses = new ApiResponses();
        mobileInteraction = new MobileInteraction();
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

    @Test(description = "Create new mobile interaction flow for OCR", retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Create new mobile interaction flow for OCR")
    public void t01_createNewMobileInteractionFlowForOcr() throws IOException, ParseException {
        String workflowName = "Automation Test " + randomString();
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


    @Test(description = "Create new mobile interaction flow for Liveness", retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Create new mobile interaction flow for Liveness")
    public void t02_createNewMobileInteractionFlowForLiveness() throws IOException, ParseException {
        String workflowName = "Automation Test " + randomString();
        apiResponses.setNewMobileFlow(mobileInteraction, workflowName, "{}", "{}", 1);
        assertTrue(mobileInteraction.isSuccess());
        assertEquals(mobileInteraction.getErrorCode(), 0);
        newFlowNumber = mobileInteraction.getWorkflowNumber();
        getDataFromJson("liveness");
        apiResponses.setUpdateDataInsideMobileInteractionFlow(mobileInteraction, newFlowNumber, workflowName, flow, viewScript, status);

        Assert.assertTrue(mobileInteraction.isSuccess());
        Assert.assertEquals(mobileInteraction.getErrorCode(), 0);
        Assert.assertEquals(mobileInteraction.getId(), newFlowNumber);
        Assert.assertNotNull(mobileInteraction.getFlow());
        Assert.assertNotNull(mobileInteraction.getViewScript());
        assertEquals(mobileInteraction.getStatus(), 1);
    }

    @Test(description = "Create new mobile interaction flow for E2E", retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Create new mobile interaction flow for E2E")
    public void t03_createNewMobileInteractionFlowForE2E() throws IOException, ParseException {
        String workflowName = "Automation Test " + randomString();
        apiResponses.setNewMobileFlow(mobileInteraction, workflowName, "{}", "{}", 1);
        assertTrue(mobileInteraction.isSuccess());
        assertEquals(mobileInteraction.getErrorCode(), 0);
        newFlowNumber = mobileInteraction.getWorkflowNumber();
        getDataFromJson("e2e");
        apiResponses.setUpdateDataInsideMobileInteractionFlow(mobileInteraction, newFlowNumber, workflowName, flow, viewScript, status);

        Assert.assertTrue(mobileInteraction.isSuccess());
        Assert.assertEquals(mobileInteraction.getErrorCode(), 0);
        Assert.assertEquals(mobileInteraction.getId(), newFlowNumber);
        Assert.assertNotNull(mobileInteraction.getFlow());
        Assert.assertNotNull(mobileInteraction.getViewScript());
        assertEquals(mobileInteraction.getStatus(), 1);
    }

}
