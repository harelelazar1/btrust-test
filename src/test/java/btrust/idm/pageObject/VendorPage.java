package btrust.idm.pageObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class VendorPage extends BasePage {
    public VendorPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".info > .vendor-title")
    protected WebElement vendorTitle;
    @FindBy(css = "[name='base_url']")
    protected WebElement serviceURLField;
    @FindBy(css = "[name='user_id']")
    protected WebElement userNameField;
    @FindBy(css = "[name='user_name']")
    protected WebElement userName2Field;
    @FindBy(css = "[name='password']")
    protected WebElement passwordField;
    @FindBy(css = ".btns > .colored")
    protected WebElement replaceButton;
    @FindBy(css = ".btns > :nth-child(2)")
    protected WebElement clearButton;
    @FindBy(css = ".input-item > .question")
    protected WebElement alt;
    @FindBy(css = "#mui-tooltip-39310 > .MuiTooltip-tooltip")
    protected WebElement altMessage;
    @FindBy(css = ".info > .error")
    protected WebElement errorMessage;
    @FindBy(css = ".heading > .h-title")
    protected WebElement popUpTitle;
    @FindBy(css = ".checkbox >.checkbox-item")
    protected WebElement popUpCheckbox;
    @FindBy(css = ".warning-popup .btns > :nth-child(1)")
    protected WebElement cancelButton;
    @FindBy(css = ".warning-popup .btns > .main")
    protected WebElement approveButton;
    @FindBy(css = ".fields > :nth-child(1) .error")
    protected WebElement errorURL;
    @FindBy(css = ".fields > :nth-child(3) .error")
    protected WebElement errorPass;
    @FindBy(css = ".heading > .h-title")
    protected WebElement linkFieldTitle;
    @FindBy(css = ".btns > .main")
    protected WebElement linkFieldClose;
    @FindBy(css = ".btns > .colored")
    protected WebElement editButton;
    @FindBy(css = ".fields > .add-header-btn")
    protected WebElement addHeaderButton;
    @FindBy(css = "#key")
    protected WebElement keyField;
    @FindBy(css = "#value")
    protected WebElement valueField;
    @FindBy(css = "[alt='remove']")
    protected WebElement removeButton;
    @FindBy(css = ".error > span")
    protected WebElement errorMessageKeyValue;
    @FindBy(css = "[name='base_url']")
    protected WebElement endpointField;
    @FindBy(css = "[name='auth_url']")
    protected WebElement authenticationURLField;
    @FindBy(css = "[name='webhook_url']")
    protected WebElement webhookUrlField;
    //    @FindBy(css = "[name='firm']")
//    protected WebElement firmField;
    @FindBy(css = "[name='api_token']")
    protected WebElement apiTokenField;
    @FindBy(css = ".h-title > span")
    protected WebElement doubleBookedTitle;

    @Step("click on edit button, fill ULR in service URL field: {serviceURL}, user in user name field: {userName} and password in password field: {password}")
    public void editVendor(String serviceURL, String userName, String password) {
        waitForElementToBeClickable(serviceURLField);
        fillText(serviceURLField, serviceURL);
        fillText(userNameField, userName);
        fillText(passwordField, password);
        click(replaceButton);
    }

    @Step("get the value from password field")
    public String getPassword(String text) {
        waitForTextToBeInElement(passwordField, text);
        return getText(passwordField);
    }

    @Step("get the vendor name")
    public String getVendorName(String text) {
        waitForTextToBeInElement(vendorTitle, text);
        return getText(vendorTitle);
    }

    @Step("Check that doubleBookedTitle is appear")
    public String doubleBookedTitle(String text) {
        waitForTextToBeInElement(doubleBookedTitle, text);
        return getText(doubleBookedTitle);
    }

    @Step("click on clear button")
    public void clearVendor() {
        waitForElementToBeClickable(clearButton);
        click(clearButton);
    }

    @Step("check the checkbox and click on approve")
    public void popUpBlockedApprove() {
        waitForElementToBeClickable(popUpCheckbox);
        click(popUpCheckbox);
        waitForElementToBeClickable(approveButton);
        click(approveButton);
    }

    @Step("click cancel")
    public void popUpBlockedCancel() {
        waitForElementToBeClickable(cancelButton);
        click(cancelButton);
    }

    @Step("click on edit button")
    public void editButton() {
        waitForElementToBeClickable(editButton);
        click(editButton);
    }

    @Step("check that error message appear")
    public String errorMessage(String text) {
        waitForTextToBeInElement(errorURL, text);
        return getText(errorPass);
    }

    @Step("check that all the elements appear in link failed pop up")
    public String linkFailedPopUp() {
        waitForElementToBeVisibility(linkFieldTitle);
        return getText(linkFieldTitle);
    }

    @Step("click on close button")
    public void linkFieldClose() {
        waitForElementToBeClickable(linkFieldClose);
        click(linkFieldClose);
    }

    @Step("check error message when type wrong URL")
    public String errorMessageURL(String text) {
        waitForTextToBeInElement(errorURL, text);
        return getText(errorURL);
    }

    @Step("click on addHeaderButton")
    public void addHeaderButton() {
        waitForElementToBeClickable(addHeaderButton);
        click(addHeaderButton);
    }

    @Step("click on removeButton")
    public void removeButton() {
        waitForElementToBeClickable(removeButton);
        click(removeButton);
    }

    @Step("check that keyField is not display")
    public boolean keyFieldsIsNotDisplayed() {
        try {
            keyField.isDisplayed();
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    @Step("check that valueField is not display")
    public boolean valueFieldsIsNotDisplayed() {
        try {
            valueField.isDisplayed();
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    @Step("check that errorMessageKeyValue appear")
    public String errorMessageKeyValue(String text) {
        waitForTextToBeInElement(errorMessageKeyValue, text);
        return getText(errorMessageKeyValue);
    }

    @Step("fill details in key field")
    public void fillKeyField(String key) {
        waitForElementToBeClickable(keyField);
        fillText(keyField, key);
        sleep(2000);
    }

    @Step("fill details in value field")
    public void fillValueField(String value) {
        waitForElementToBeClickable(valueField);
        fillText(valueField, value);
        sleep(2000);
    }

    @Step("get text from keyField")
    public String keyField() {
        waitForElementToBeVisibility(keyField);
        return getAttribute(keyField, "value");
    }

    @Step("get text from valueField")
    public String valueField() {
        waitForElementToBeVisibility(valueField);
        return getAttribute(valueField, "value");
    }

    @Step("Type in endpointField: {endpoint} and click on editButton")
    public void vendor1Field(String endpoint) {
        waitForElementToBeClickable(endpointField);
        fillText(endpointField, endpoint);
        waitForElementToBeClickable(editButton);
        click(editButton);
    }

    @Step("Type in serviceURLField: {serverUrl}, webhookUrlField: {webhookUrl} and click on editButton")
    public void vendor2Field(String serverUrl, String webhookUrl) {
        waitForElementToBeClickable(serviceURLField);
        fillText(serviceURLField, serverUrl);
        waitForElementToBeClickable(webhookUrlField);
        fillText(webhookUrlField, webhookUrl);
        waitForElementToBeClickable(editButton);
        click(editButton);
    }

//    @Step("Type in serviceURLField: {serviceURL}, userNameField: {userName}, passwordField: {password} and click on editButton")
//    public void vendor3Field(String serviceURL, String userName, String password) {
//        waitForElementToBeClickable(serviceURLField);
//        fillText(serviceURLField, serviceURL);
//        waitForElementToBeClickable(userNameField);
//        fillText(userNameField, userName);
//        waitForElementToBeClickable(passwordField);
//        fillText(passwordField, password);
//        waitForElementToBeClickable(editButton);
//        click(editButton);
//    }
//
//    @Step("Type in serviceURLField: {serviceURL}, authenticationURLField: {authenticationURL}, userNameField: {userName}, passwordField: {password} and click on editButton")
//    public void vendor4Field(String serviceURL, String authenticationURL, String userName, String password) {
//        waitForElementToBeClickable(serviceURLField);
//        fillText(serviceURLField, serviceURL);
//        waitForElementToBeClickable(authenticationURLField);
//        fillText(authenticationURLField, authenticationURL);
//        waitForElementToBeClickable(userNameField);
//        fillText(userNameField, userName);
//        waitForElementToBeClickable(passwordField);
//        fillText(passwordField, password);
//        waitForElementToBeClickable(editButton);
//        click(editButton);
//    }
//
//    @Step("Type in serviceURLField: {serviceURL}, userNameField: {userName}, passwordField: {password}, firmUrlField: {Firm} and click on editButton")
//    public void RDCVendorFields(String serviceURL, String userName, String password, String Firm) {
//        waitForElementToBeClickable(serviceURLField);
//        fillText(serviceURLField, serviceURL);
//        waitForElementToBeClickable(userName2Field);
//        fillText(userName2Field, userName);
//        waitForElementToBeClickable(passwordField);
//        fillText(passwordField, password);
//        waitForElementToBeClickable(firmField);
//        fillText(firmField, Firm);
//        waitForElementToBeClickable(editButton);
//        click(editButton);
//    }

    @Step("Type in serverUrlField: {serverUrl}, APITokenField: {APITokenField} and click on editButton")
    public void BureauVanDijkendorFields(String serverUrl, String APIToken) {
        waitForElementToBeClickable(serviceURLField);
        fillText(serviceURLField, serverUrl);
        waitForElementToBeClickable(apiTokenField);
        fillText(apiTokenField, APIToken);
        waitForElementToBeClickable(editButton);
        click(editButton);
    }

    @Step("Type in serviceURLField: {serviceURL}, userNameField: {userName}, passwordField: {password} and click on editButton")
    public void genldFields(String serviceURL, String userName, String password) {
        waitForElementToBeClickable(serviceURLField);
        fillText(serviceURLField, serviceURL);
        waitForElementToBeClickable(userName2Field);
        fillText(userName2Field, userName);
        waitForElementToBeClickable(passwordField);
        fillText(passwordField, password);
        waitForElementToBeClickable(editButton);
        click(editButton);
    }
}