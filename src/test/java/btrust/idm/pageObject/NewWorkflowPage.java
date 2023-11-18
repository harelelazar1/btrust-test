package btrust.idm.pageObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class NewWorkflowPage extends BasePage {

    public NewWorkflowPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".page-container > .title")
    protected WebElement newWorkflowTitle;
    @FindBy(css = ".breadcrumbs > span > .bread-page-pointer")
    protected WebElement breadcrumbsPage;
    @FindBy(css = ".breadcrumbs > .active")
    protected WebElement breadcrumbsActivePage;
    @FindBy(css = ".workflow-name > input")
    protected WebElement addWorkflowNameField;
    @FindBy(css = ".selector > :nth-child(1)")
    protected WebElement selectWorkflowType;
    @FindBy(css = "#a_unique_id-body > #a_unique_id")
    protected WebElement jsonDisplayField;
    @FindBy(css = ".btns > :nth-child(1)")
    protected WebElement cancelButton;
    @FindBy(css = ".btns > :nth-child(2)")
    protected WebElement saveButton;
    @FindBy(css = ".tab-panel-content > .json-error")
    protected WebElement errorMessage;
    @FindBy(css = ".default__menu-list > .default__option")
    protected List<WebElement> selectWorkflowTypeList;
    @FindBy(css = ".workflow-name > .error")
    protected WebElement errorWorkflowName;
    @FindBy(css = ".selector > .error")
    protected WebElement errorTypeSelect;
    @FindBy(css = ".tab-panel-content > .json-error")
    protected WebElement errorJsonField;

    protected String json = "{\"name\":\"Onboarding\",\"inputs\":[{\"name\":\"First name\",\"field\":\"firstName\",\"type\":\"text\"},{\"name\":\"Last name\",\"field\":\"lastName\",\"type\":\"text\"},{\"name\":\"Date of birth\",\"field\":\"dateOfBirth\",\"type\":\"Date\"},{\"name\":\"Nationality\",\"field\":\"nationality\",\"type\":\"Country\"},{\"name\":\"Address\",\"field\":\"address\",\"type\":\"text\"},{\"name\":\"Gender\",\"field\":\"gender\",\"type\":\"text\"},{\"name\":\"Expiration date\",\"field\":\"expirationDate\",\"type\":\"text\"},{\"name\":\"Document Number\",\"field\":\"documentNumber\",\"type\":\"text\"},{\"name\":\"ID number\",\"field\":\"idNumber\",\"type\":\"text\"},{\"name\":\"Issuing country\",\"field\":\"issuingCountry\",\"type\":\"text\"},{\"name\":\"Face image\",\"field\":\"faceImage\",\"type\":\"file\"},{\"name\":\"Front image\",\"field\":\"frontImage\",\"type\":\"file\"},{\"name\":\"Document type\",\"field\":\"docType\",\"type\":\"text\"},{\"name\":\"Request Id\",\"field\":\"reqId\",\"type\":\"text\"},{\"name\":\"Code\",\"field\":\"code\",\"type\":\"text\"},{\"name\":\"Case Id\",\"field\":\"caseId\",\"type\":\"text\"},{\"name\":\"Phone Number\",\"field\":\"phoneNumber\",\"type\":\"text\"}],\"flow\":[{\"id\":\"ocr\",\"name\":\"OCR\",\"controller_id\":\"server_ocr\",\"mandatory\":true,\"custom\":false,\"inputs\":[\"I[ocrCaseId]:caseId\"],\"rules\":[{\"name\":\"Front side required\",\"filter\":{\"target\":\"docType\",\"sign\":\"=\",\"value\":\"ISRAEL_ID2_TYPE_BIOMETRIC_BACK\",\"next\":\"ocr\"}},{\"name\":\"Ocr success\",\"filter\":{\"target\":\"success\",\"sign\":\"=\",\"value\":true,\"next\":\"liveness\"}},{\"name\":\"Default\",\"is_default\":true,\"filter\":{\"next\":\"ocr\"}}]},{\"id\":\"backside_ocr\",\"name\":\"OCR\",\"controller_id\":\"server_ocr\",\"mandatory\":false,\"custom\":false,\"inputs\":[\"I[backOcrCaseId]:caseId\"],\"rules\":[{\"name\":\"Default\",\"is_default\":true,\"filter\":{\"next\":\"liveness\"}}]},{\"id\":\"liveness\",\"name\":\"Liveness\",\"controller_id\":\"liveness\",\"mandatory\":true,\"inputs\":[\"I[livenessCaseId]:caseId\",\"I[duration]:duration\",\"I[reason]:reason\"],\"rules\":[{\"name\":\"Liveness case found\",\"filter\":{\"target\":\"success\",\"sign\":\"=\",\"value\":true,\"next\":\"megamatcher_verify\"}},{\"name\":\"Default\",\"is_default\":true,\"filter\":{\"next\":\"liveness\"}}]},{\"id\":\"liveness_holder\",\"name\":\"Proceed Liveness\",\"controller_id\":\"holder\",\"mandatory\":true,\"custom\":false,\"inputs\":[\"I[proceed_liveness]:proceed_liveness\"],\"rules\":[{\"name\":\"Default\",\"is_default\":true,\"filter\":{\"next\":\"biometric\"}}]},{\"id\":\"biometric\",\"name\":\"Face comparison\",\"controller_id\":\"biometric\",\"mandatory\":true,\"inputs\":[\"ocr[faceImage]:documentPhoto\",\"liveness[faceImage]:selfieImage\"],\"rules\":[{\"name\":\"Faces match\",\"filter\":{\"target\":\"success\",\"sign\":\"=\",\"value\":true,\"next\":\"end\"}},{\"name\":\"Biometric no match\",\"is_default\":true,\"flagged\":true,\"filter\":{\"next\":\"stop\"}}]}]}";

    @Step("check the active breadcrumbs")
    public String breadcrumbsPageActive(String text) {
        waitForTextToBeInElement(breadcrumbsActivePage, text);
        return getText(breadcrumbsActivePage);
    }

    @Step("click on breadcrumbs to back to Workflow Builder page")
    public void breadcrumbsPage() {
        waitForElementToBeClickable(breadcrumbsPage);
        click(breadcrumbsPage);
    }

    @Step("check that workflow name appear in title: {workflowName}")
    public void workflowName(String workflowName) {
        waitForElementToBeClickable(addWorkflowNameField);
        fillText(addWorkflowNameField, workflowName);
    }

    @Step("check that workflow name appear in title")
    public String newWorkflowTitle(String text) {
        waitForTextToBeInElement(newWorkflowTitle, text);
        return getText(newWorkflowTitle);
    }

    @Step("choose type: {chooseFromTypesList} and fill json text: {json} and click save")
    public void addDetails(String workflowName, String chooseFromTypesList) {
        waitForElementToBeClickable(addWorkflowNameField);
        fillText(addWorkflowNameField, workflowName);
        waitForElementToBeClickable(selectWorkflowType);
        sleep(2000);
        click(selectWorkflowType);
        for (WebElement el : selectWorkflowTypeList) {
            if (el.getText().equalsIgnoreCase(chooseFromTypesList)) {
                waitForElementToBeClickable(el);
                el.click();
                break;
            }
        }
        waitForElementToBeClickable(jsonDisplayField);
        sleep(2000);
        fillText(jsonDisplayField, json);
        sleep(5000);
        scrollToElement(saveButton);
        waitForElementToBeClickable(saveButton);
        click(saveButton);
    }

    @Step("click on save button")
    public void clickSave() {
        waitForPageFinishLoading();
        scrollToElement(saveButton);
        waitForElementToBeClickable(saveButton);
        click(saveButton);
    }

    @Step("click on cancel button")
    public void clickCancel() {
        waitForElementToBeClickable(cancelButton);
        click(cancelButton);
    }

    @Step("click on save button without fill details")
    public String errorWorkflowName(String text) {
        waitForPageFinishLoading();
        scrollToElement(errorWorkflowName);
        waitForTextToBeInElement(errorWorkflowName, text);
        return getText(errorWorkflowName);
    }

    @Step("click on save button without fill details")
    public String errorTypeSelect(String text) {
        waitForTextToBeInElement(errorTypeSelect, text);
        return getText(errorTypeSelect);
    }

    @Step("click on save button without fill details")
    public String errorJsonField(String text) {
        waitForPageFinishLoading();
        scrollToElement(errorJsonField);
        waitForTextToBeInElement(errorJsonField, text);
        return getText(errorJsonField);
    }

    @Step("type wrong value in Json field")
    public void wrongValueJsonField(String json) {
        waitForElementToBeVisibility(jsonDisplayField);
        fillText(jsonDisplayField, json);
    }
}