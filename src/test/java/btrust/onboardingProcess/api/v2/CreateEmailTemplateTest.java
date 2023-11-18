package btrust.onboardingProcess.api.v2;

import btrust.onboardingProcess.api.ApiResponses;
import btrust.onboardingProcess.api.Utilities;
import btrust.onboardingProcess.api.variables.EmailTemplate;
import btrust.onboardingProcess.api.variables.EndpointResults;
import io.qameta.allure.Description;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static btrust.btrustOne.admin.BaseAdminUserTest.randomString;


public class CreateEmailTemplateTest {


    Map<String, File> processes;
    EndpointResults endpointResults;
    Utilities utilities;
    ApiResponses apiResponses;

    EmailTemplate emailTemplate;


    String emailTemplateName;
    String subject;
    boolean withPortal;
    String viewScript;
    String htmlScript;

    boolean enableEditing;
    int templateId;


    @BeforeMethod(alwaysRun = true)
    public void initSession() {

        endpointResults = new EndpointResults();
        processes = new HashMap<>();
        utilities = new Utilities();
        apiResponses = new ApiResponses();
        emailTemplate = new EmailTemplate();
    }

    public void getDataFromJson(String path) {
        try (FileReader reader = new FileReader("./src/test/resources/emailTemplateData.json")) {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(reader);

            JSONArray ocrArray = (JSONArray) jsonObject.get(path);

            JSONObject emailTemplate = (JSONObject) ocrArray.get(0);
            emailTemplateName = (String) emailTemplate.get("name");
            subject = (String) emailTemplate.get("subject");
            withPortal = (boolean) emailTemplate.get("withPortal");
            viewScript = (String) emailTemplate.get("viewScript");
            htmlScript = (String) emailTemplate.get("htmlScript");
            enableEditing = (boolean) emailTemplate.get("enableEditing");


            // do something with the flow value
        } catch (IOException | ParseException | ClassCastException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Test(description = "Create new Email Template", retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Create new Email Template")
    public void t01_createNewEmailTemplate() throws IOException, ParseException {
        getDataFromJson("emailTemplate");
        apiResponses.setNewEmailTemplate(emailTemplate, emailTemplateName, subject, withPortal, viewScript, htmlScript, enableEditing);
        Assert.assertTrue(emailTemplate.isSuccess());
        Assert.assertEquals(emailTemplate.getErrorCode(), 0);
        Assert.assertNotNull(emailTemplate.getData());
        templateId = emailTemplate.getData();
        emailTemplateName = emailTemplateName + randomString();
    }


    @Test(description = "Update email template", retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Update Email Template")
    public void t02_updateEmailTemplate() throws IOException, ParseException {
        apiResponses.updateEmailTemplate(emailTemplate, emailTemplateName, subject, withPortal, viewScript, htmlScript, enableEditing, templateId);

        Assert.assertTrue(emailTemplate.isSuccess());
        Assert.assertEquals(emailTemplate.getErrorCode(), 0);
        Assert.assertEquals(emailTemplate.getData1(), "[:]");
    }


    @Test(description = "Delete email template", retryAnalyzer = utilities.RetryAnalyzer.class)
    @Description("Delete email template")
    public void t03_deleteEmailTemplate() throws IOException, ParseException {

        apiResponses.deleteEmailTemplate(emailTemplate, templateId);

        Assert.assertTrue(emailTemplate.isSuccess());
        Assert.assertEquals(emailTemplate.getErrorCode(), 0);
        Assert.assertEquals(emailTemplate.getData1(), "[:]");
    }


}
