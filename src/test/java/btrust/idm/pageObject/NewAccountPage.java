package btrust.idm.pageObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class NewAccountPage extends BasePage {
    public NewAccountPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".breadcrumbs > span > .bread-page-pointer")
    protected WebElement breadcrumbsPreviousPage;
    @FindBy(css = ".account-top> .title")
    protected WebElement newAccountTitle;
    @FindBy(css = ".btns > .blue")
    protected WebElement generateAccountButton;
    //Company information
    @FindBy(css = ".account-form > :nth-child(1) .title > span")
    protected WebElement companyInformationTitle;
    @FindBy(css = "[name='orgName']")
    protected WebElement organizationNameField;
    @FindBy(css = "[name='orgNumber']")
    protected WebElement organizationNumberField;
    @FindBy(css = ".input-half > .default-select")
    protected WebElement organizationFieldSelect;
    @FindBy(css = "[name='orgAddress']")
    protected WebElement organizationAddressField;
    @FindBy(css = "[name='postalCode']")
    protected WebElement postalCodeField;
    @FindBy(css = ".input-item > .error > span")
    protected WebElement organizationNameErrorMessage;
    @FindBy(css = ".default__menu-list > .default__option")
    protected List<WebElement> organizationFieldList;
    @FindBy(css = ".input-half.isMulti .default__control")
    protected WebElement screensSelect;
    @FindBy(css = ".inputs > :nth-child(7) > :nth-child(2) .default__control.css-yk16xz-control")
    protected WebElement dashboardSelect;
    @FindBy(css = ".default__menu-list > .default__option")
    protected List<WebElement> screensList;
    @FindBy(css = ".default__menu-list > .default__option")
    protected List<WebElement> dashboardList;
    @FindBy(css = ".input-half.isMulti > .error > span")
    protected WebElement screensErrorMessage;
    @FindBy(css = ".inputs > :nth-child(7) > :nth-child(2) > .error > span")
    protected WebElement dashboardErrorMessage;
    //Contact Person
    @FindBy(css = ".account-form > :nth-child(2) .title > span")
    protected WebElement contactPersonTitle;
    @FindBy(css = "[name='firstName']")
    protected WebElement firstNameField;
    @FindBy(css = "[name='lastName']")
    protected WebElement lastNameField;
    @FindBy(css = ".account-form > :nth-child(2) > .inputs > div:nth-child(1) > div:nth-child(1) > .error > span")
    protected WebElement firstNameErrorMessage;
    @FindBy(css = ".account-form > :nth-child(2) > .inputs > div:nth-child(1) > div:nth-child(2) > .error > span")
    protected WebElement lastNameErrorMessage;
    //Demo/Licence
    @FindBy(css = ".account-form > :nth-child(3) .title > span")
    protected WebElement demoLicenceTitle;
    @FindBy(css = ".inputs > :nth-child(2) > :nth-child(1) > .default-select")
    protected WebElement languageSelect;
    @FindBy(css = ".inputs > :nth-child(2) > :nth-child(1) > .default-select .default__menu-list > .default__option")
    protected List<WebElement> languageList;
    @FindBy(css = ".inputs > :nth-child(2) > :nth-child(1) > .error > span")
    protected WebElement languageErrorMessage;
    //User Admin
    @FindBy(css = ".account-form > :nth-child(4) .title > span")
    protected WebElement userAdminTitle;
    @FindBy(css = "[name='adminEmail']")
    protected WebElement adminEmailField;
    @FindBy(css = "[name='adminPassword']")
    protected WebElement passwordField;
    @FindBy(css = ".account-form > :nth-child(4) > .inputs > .input-item-half > :nth-child(1) > .error > span")
    protected WebElement adminEmailErrorMessage;
    @FindBy(css = ".account-form > :nth-child(4) > .inputs > .input-item-half > :nth-child(2) > .error > span")
    protected WebElement passwordErrorMessage;
    //popup create admin
    @FindBy(css = ".action > button")
    protected WebElement popupSuccessCreateAccountCloseButton;
    @FindBy(css = ".body > :nth-child(3) > .data")
    protected WebElement password;

    @FindBy(css = ".pages > .companies")
    protected WebElement totalAdmin;

    // login elements
    @FindBy(css = "#email")
    protected WebElement userNameFieldLogin;
    @FindBy(css = "#password")
    protected WebElement passwordFieldLogin;
    @FindBy(css = ".btn_login")
    protected WebElement loginButton;

    protected int num;
    protected String email;
    protected String pass;


    @Step("check the title of the page")
    public String newAccountTitle(String text) {
        waitForTextToBeInElement(newAccountTitle, text);
        return getText(newAccountTitle);
    }

    @Step("click on generateAccountButton")
    public void breadcrumbsPreviousPage() {
        waitForElementToBeClickable(breadcrumbsPreviousPage);
        click(breadcrumbsPreviousPage);
    }

    @Step("click on generateAccountButton")
    public void generateAccountButton() {
        waitForElementToBeClickable(generateAccountButton);
        click(generateAccountButton);
    }

    @Step("check the error message of organization name field")
    public String organizationNameErrorMessage(String text) {
        js.executeScript("arguments[0].scrollIntoView();", organizationNameErrorMessage);
        waitForTextToBeInElement(organizationNameErrorMessage, text);
        return getText(organizationNameErrorMessage);
    }

    @Step("check the error message of screens select")
    public String screensErrorMessage(String text) {
        js.executeScript("arguments[0].scrollIntoView();", screensErrorMessage);
        waitForTextToBeInElement(screensErrorMessage, text);
        return getText(screensErrorMessage);
    }

    @Step("check the error message of dashboard select")
    public String dashboardErrorMessage(String text) {
        js.executeScript("arguments[0].scrollIntoView();", dashboardErrorMessage);
        waitForTextToBeInElement(dashboardErrorMessage, text);
        return getText(dashboardErrorMessage);
    }

    @Step("check the error message of language select")
    public String languageErrorMessage(String text) {
        js.executeScript("arguments[0].scrollIntoView();", languageErrorMessage);
        waitForTextToBeInElement(languageErrorMessage, text);
        return getText(languageErrorMessage);
    }

    @Step("check the error message of admin email field")
    public String adminEmailErrorMessage(String text) {
        sleep(5000);
        js.executeScript("arguments[0].scrollIntoView();", adminEmailErrorMessage);
        waitForTextToBeInElement(adminEmailErrorMessage, text);
        return getText(adminEmailErrorMessage);
    }

    @Step("check the error message of password field")
    public String passwordErrorMessage(String text) {
        js.executeScript("arguments[0].scrollIntoView();", adminEmailErrorMessage);
        waitForTextToBeInElement(passwordErrorMessage, text);
        return getText(passwordErrorMessage);
    }

    @Step("type details of exist company")
    public void addExistAccount(String organizationName, String organizationNumber, String chooseFromOrganizationFields, String organizationAddress, String chooseFromScreensList, String chooseFromDashboardList, String firstName, String lastName, String chooseFromLanguageList, String adminEmail, String password) {
        waitForElementToBeClickable(organizationNameField);
        fillText(organizationNameField, organizationName);
        waitForElementToBeClickable(organizationNumberField);
        fillText(organizationNumberField, organizationNumber);
        waitForElementToBeClickable(organizationFieldSelect);
        click(organizationFieldSelect);
        for (WebElement el : organizationFieldList) {
            if (el.getText().equalsIgnoreCase(chooseFromOrganizationFields)) {
                waitForElementToBeClickable(el);
                el.click();
                break;
            }
        }
        waitForElementToBeClickable(organizationAddressField);
        fillText(organizationAddressField, organizationAddress);
        waitForElementToBeClickable(postalCodeField);
        fillText(postalCodeField, organizationNumber);
        js.executeScript("arguments[0].scrollIntoView();", screensSelect);
        waitForElementToBeClickable(screensSelect);
        click(screensSelect);
        for (WebElement el : screensList) {
            if (el.getText().equalsIgnoreCase(chooseFromScreensList)) {
                waitForElementToBeClickable(el);
                el.click();
                break;
            }
        }
        js.executeScript("arguments[0].scrollIntoView();", dashboardSelect);
        waitForElementToBeClickable(dashboardSelect);
        click(dashboardSelect);
        for (WebElement el : dashboardList) {
            if (el.getText().equalsIgnoreCase(chooseFromDashboardList)) {
                waitForElementToBeClickable(el);
                el.click();
                break;
            }
        }
        js.executeScript("arguments[0].scrollIntoView();", firstNameField);
        waitForElementToBeClickable(firstNameField);
        fillText(firstNameField, firstName);
        js.executeScript("arguments[0].scrollIntoView();", lastNameField);
        waitForElementToBeClickable(lastNameField);
        fillText(lastNameField, lastName);
        js.executeScript("arguments[0].scrollIntoView();", languageSelect);
        waitForElementToBeClickable(languageSelect);
        click(languageSelect);
        for (WebElement el : languageList) {
            if (el.getText().equalsIgnoreCase(chooseFromLanguageList)) {
                waitForElementToBeClickable(el);
                el.click();
                break;
            }
        }
        js.executeScript("arguments[0].scrollIntoView();", adminEmailField);
        waitForElementToBeClickable(adminEmailField);
        fillText(adminEmailField, adminEmail);
        js.executeScript("arguments[0].scrollIntoView();", passwordField);
        waitForElementToBeClickable(passwordField);
        fillText(passwordField, password);
        js.executeScript("arguments[0].scrollIntoView();", generateAccountButton);
        waitForElementToBeClickable(generateAccountButton);
        click(generateAccountButton);
    }

    @Step("check that companyInformationTitle appear")
    public String companyInformationTitle(String text) {
        waitForTextToBeInElement(companyInformationTitle, text);
        return getText(companyInformationTitle);
    }

    @Step("check that companyInformationTitle appear")
    public String contactPersonTitle(String text) {
        waitForTextToBeInElement(contactPersonTitle, text);
        return getText(contactPersonTitle);
    }

    @Step("check that companyInformationTitle appear")
    public String firstNameErrorMessage(String text) {
        js.executeScript("arguments[0].scrollIntoView();", firstNameErrorMessage);
        waitForTextToBeInElement(firstNameErrorMessage, text);
        return getText(firstNameErrorMessage);
    }

    @Step("check that companyInformationTitle appear")
    public String lastNameErrorMessage(String text) {
        js.executeScript("arguments[0].scrollIntoView();", lastNameErrorMessage);
        waitForTextToBeInElement(lastNameErrorMessage, text);
        return getText(lastNameErrorMessage);
    }

    @Step("check that demoLicenceTitle appear")
    public String demoLicenceTitle(String text) {
        waitForTextToBeInElement(demoLicenceTitle, text);
        return getText(demoLicenceTitle);
    }

    @Step("check that userAdminTitle appear")
    public String userAdminTitle(String text) {
        waitForTextToBeInElement(userAdminTitle, text);
        return getText(userAdminTitle);
    }

    @Step("check counter admin")
    public int totalAdmin() {
        waitForElementToBeVisibility(totalAdmin);
        String total = getText(totalAdmin);
        num = Integer.parseInt(total);
        return num;
    }

    @Step("Copy email address")
    public String copyEmail(String text) {
        waitForTextToBeInElement(adminEmailField, text);
        email = getAttribute(adminEmailField, "value");
        return email;
    }

    @Step("Copy pass address")
    public String copyPass(String text) {
        waitForTextToBeInElement(passwordField, text);
        pass = getAttribute(passwordField, "value");
        return pass;
    }

    @Step("click on popupSuccessCreateAccountCloseButton")
    public void popupSuccessCreateAccountCloseButton() {
        waitForElementToBeClickable(popupSuccessCreateAccountCloseButton);
        click(popupSuccessCreateAccountCloseButton);
    }

    @Step("login to system with the new admin")
    public void login() {
        waitForElementToBeClickable(userNameFieldLogin);
        fillText(userNameFieldLogin, email);
        waitForElementToBeClickable(passwordFieldLogin);
        fillText(passwordFieldLogin, pass);
        waitForElementToBeClickable(loginButton);
        click(loginButton);
    }
}