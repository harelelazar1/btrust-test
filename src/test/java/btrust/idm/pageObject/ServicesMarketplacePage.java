package btrust.idm.pageObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ServicesMarketplacePage extends BasePage {
    public ServicesMarketplacePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".page-container > .title")
    protected WebElement servicesMarketplaceTitle;
    @FindBy(css = ".top-bar > :nth-child(1) > .select-button")
    protected WebElement serviceFilter;
    @FindBy(css = ".top-bar > :nth-child(2) > .select-button")
    protected WebElement statusFilter;
    @FindBy(css = "[name='search']")
    protected WebElement containTextField;
    @FindBy(css = ".items-container > .item")
    protected List<WebElement> serviceFilterList;
    @FindBy(css = ".items-container > .item")
    protected List<WebElement> statusFilterList;
    @FindBy(css = ".services-container .title")
    protected WebElement vendorName;
    @FindBy(css = ".services-container .title")
    protected List<WebElement> vendorNameList;
    @FindBy(css = ".top-bar > :nth-child(1) > .clear")
    protected WebElement serviceClearButton;
    @FindBy(css = ".top-bar > :nth-child(2) > .clear")
    protected WebElement statusClearButton;
    @FindBy(css = ".page-container .heading")
    protected WebElement noVendorFoundMessage;
    @FindBy(css = ".service-item > .title")
    protected List<WebElement> vendorList;
    @FindBy(css = ".services-container > [role='tabpanel'] > .status > span")
    protected List<WebElement> statusList;
    @FindBy(css = ".services-container > [role='tabpanel'] > .status > span")
    protected WebElement statusVendor;
    @FindBy(css = ".pagination > .pages")
    protected WebElement pagesNumber;

    /**
     * Create new account that can test adding vendors
     */
    @FindBy(css = ".add-new-btn > button")
    protected WebElement addNewAccountButton;
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
    @FindBy(css = ".default__menu-list > .default__option")
    protected List<WebElement> organizationFieldList;
    @FindBy(css = ".input-half.isMulti .default__control.css-yk16xz-control")
    protected WebElement screensSelect;
    @FindBy(css = ".inputs > :nth-child(7) > :nth-child(2) .default__control.css-yk16xz-control")
    protected WebElement dashboardSelect;
    @FindBy(css = ".default__menu .default__option")
    protected List<WebElement> screensList;
    @FindBy(css = ".default__menu-list > .default__option")
    protected List<WebElement> dashboardList;
    @FindBy(css = "[name='firstName']")
    protected WebElement firstNameField;
    @FindBy(css = "[name='lastName']")
    protected WebElement lastNameField;
    @FindBy(css = ".inputs > :nth-child(2) > :nth-child(1) > .default-select")
    protected WebElement languageSelect;
    @FindBy(css = ".inputs > :nth-child(2) > :nth-child(1) > .default-select .default__menu-list > .default__option")
    protected List<WebElement> languageList;
    @FindBy(css = "[name='adminEmail']")
    protected WebElement adminEmailField;
    @FindBy(css = "[name='adminPassword']")
    protected WebElement passwordField;
    @FindBy(css = ".btns > .blue")
    protected WebElement generateAccountButton;
    @FindBy(css = ".pages > .companies")
    protected WebElement totalAdmin;

    /**
     * Login
     */
    @FindBy(css = "#email")
    protected WebElement userNameLoginField;
    @FindBy(css = "#password")
    protected WebElement passwordLoginField;
    @FindBy(css = ".btn_login")
    protected WebElement loginButton;

    @FindBy(css = ".one-vendor > .status ")
    protected WebElement status;

    protected String email;
    protected String companyName;
    protected int num;


    @Step("check that services marketplace title appear")
    public String servicesMarketplaceTitle() {
        waitForElementToBeVisibility(servicesMarketplaceTitle);
        return getText(servicesMarketplaceTitle);
    }

    @Step("Return the pagesNumber")
    public String pagesNumber(String text) {
        waitForPageFinishLoading();
        waitForTextToBeInElement(pagesNumber, text);
        return getText(pagesNumber);
    }

    @Step("check that filter bar is enable")
    public void statusFilterIsEnable() {
        waitForElementToBeVisibility(serviceFilter);
        isEnabled(serviceFilter);
        waitForElementToBeVisibility(statusFilter);
        isEnabled(statusFilter);
        waitForElementToBeVisibility(containTextField);
        isEnabled(containTextField);
    }

    @Step("open service filter and select 1 option: {chooseFromServiceList}")
    public void filterByService(String chooseFromServiceList) {
        waitForElementToBeVisibility(vendorName);
        waitForElementToBeClickable(serviceFilter);
        click(serviceFilter);
        for (WebElement el : serviceFilterList) {
            if (el.getText().equalsIgnoreCase(chooseFromServiceList)) {
                waitForElementToBeClickable(el);
                el.click();
                break;
            }
        }
        waitForElementToBeClickable(serviceFilter);
        click(serviceFilter);
        waitForPageFinishLoading();
    }

    @Step("open flow filter and select 2 option: {choose1FromServiceList} and {choose2FromServiceList}")
    public void filterByService2Options(String choose1FromServiceList, String choose2FromServiceList) {
        waitForElementToBeClickable(serviceFilter);
        click(serviceFilter);
        for (WebElement el : serviceFilterList) {
            if (el.getText().equalsIgnoreCase(choose1FromServiceList)) {
                waitForElementToBeClickable(el);
                el.click();
                break;
            }
            if (el.getText().equalsIgnoreCase(choose2FromServiceList)) {
                waitForElementToBeClickable(el);
                el.click();
                break;
            }
        }
        waitForElementToBeClickable(serviceFilter);
        click(serviceFilter);
    }

    @Step("open status filter and select 1 option: {chooseFromStatusList}")
    public void filterByStatus(String chooseFromStatusList) {
        waitForElementToBeClickable(statusFilter);
        click(statusFilter);
        for (WebElement el : statusFilterList) {
            if (el.getText().equalsIgnoreCase(chooseFromStatusList)) {
                waitForElementToBeClickable(el);
                el.click();
                break;
            }
        }
        waitForElementToBeClickable(statusFilter);
        click(statusFilter);
    }

    @Step("check that vendor name appear")
    public String vendorName(String text, String chooseVendor) {
        waitForPageFinishLoading();
        sleep(5000);
        String vendor = null;
        for (WebElement el : vendorNameList) {
            if (getText(el).equalsIgnoreCase(chooseVendor)) {
                scrollToElement(el);
                waitForTextToBeInElement(el, text);
                getText(el);
                vendor = getText(el);
                break;
            } else {
                System.out.println(vendorNameList.size() + "\n" + getText(el));
            }
        }
        return vendor;
    }

    @Step("click on clear service filter button")
    public void serviceClearButton() {
        waitForElementToBeClickable(serviceClearButton);
        click(serviceClearButton);
    }

    @Step("click on clear status filter button")
    public void statusClearButton() {
        waitForElementToBeClickable(statusClearButton);
        click(statusClearButton);
    }

    @Step("filter by contain text")
    public void filterByContainText(String vendorName) {
        waitForElementToBeVisibility(serviceFilter);
        sleep(5000);
        fillText(containTextField, vendorName);
    }

    @Step("clear contain text field")
    public void clearContainText() {
        clear(containTextField);
    }

    @Step("check that no vendor found message appear")
    public String noVendorFoundMessage(String text) {
        waitForTextToBeInElement(noVendorFoundMessage, text);
        return getText(noVendorFoundMessage);
    }

    @Step("check that status of vendor appear")
    public String statusVendor(String status) {
        waitForTextToBeInElement(statusVendor, status);
        return getText(statusVendor);
    }

    @Step("choose vendor: {vendorName}")
    public void chooseVendorByName(String waitToVendor) {
        waitForTextToBeInElement(vendorName, waitToVendor);
        waitForElementToBeClickable(vendorName);
        sleep(5000);
        click(vendorName);
    }

    @Step("check counter admin")
    public int totalAdmin() {
        waitForElementToBeVisibility(totalAdmin);
        String total = getText(totalAdmin);
        num = Integer.parseInt(total);
        System.out.println("The number of admin is: " + num);
        return num;
    }

    @Step("type details of new company")
    public void addNewAccount(String organizationName, String organizationNumber, String chooseFromOrganizationFields, String organizationAddress, String chooseFromScreensList, String chooseFromDashboardList, String firstName, String lastName, String chooseFromLanguageList, String userEmail, String finalEmail, String password) {
        waitForElementToBeClickable(addNewAccountButton);
        click(addNewAccountButton);
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
        waitForElementToBeClickable(screensSelect);
        click(screensSelect);
        for (WebElement el : screensList) {
            if (el.getText().equalsIgnoreCase(chooseFromScreensList)) {
                waitForElementToBeClickable(el);
                el.click();
                break;
            }
        }
        waitForElementToBeClickable(dashboardSelect);
        click(dashboardSelect);
        for (WebElement el : dashboardList) {
            if (el.getText().equalsIgnoreCase(chooseFromDashboardList)) {
                waitForElementToBeClickable(el);
                el.click();
                break;
            }
        }
        waitForElementToBeClickable(firstNameField);
        fillText(firstNameField, firstName);
        waitForElementToBeClickable(lastNameField);
        fillText(lastNameField, lastName);
        waitForElementToBeClickable(languageSelect);
        click(languageSelect);
        for (WebElement el : languageList) {
            if (el.getText().equalsIgnoreCase(chooseFromLanguageList)) {
                waitForElementToBeClickable(el);
                el.click();
                break;
            }
        }
        waitForElementToBeClickable(adminEmailField);
        fillText(adminEmailField, userEmail + finalEmail);
        waitForElementToBeClickable(passwordField);
        fillText(passwordField, password);
    }

    @Step("Copy company name")
    public String copyCompanyName() {
        waitForElementToBeVisibility(organizationNameField);
        companyName = getAttribute(organizationNameField, "value");
        System.out.println("copy: " + companyName);
        return companyName;
    }

    @Step("Copy email address")
    public String copyEmail() {
        waitForElementToBeVisibility(adminEmailField);
        email = getAttribute(adminEmailField, "value");
        System.out.println("copy: " + email);
        return email;
    }

    @Step("click on generateAccountButton")
    public void generateAccountButton() {
        waitForElementToBeClickable(generateAccountButton);
        click(generateAccountButton);
    }

    @Step("login to system with the new admin")
    public void login(String password) {
        waitForElementToBeClickable(userNameLoginField);
        System.out.println("paste: " + email);
        fillText(userNameLoginField, email);
        waitForElementToBeClickable(passwordLoginField);
        fillText(passwordLoginField, password);
        waitForElementToBeClickable(loginButton);
        click(loginButton);
    }

    @Step("check the status of the vendor")
    public String statusVendor(String vendorN, String vName) {
        waitForTextToBeInElement(vendorName, vendorN);
        String foundStatus = null;
        List<WebElement> chooseVendor = vendorList;
        for (int i = 0; i < chooseVendor.size(); i++) {
            WebElement vendor = chooseVendor.get(i);
            String nameV = getText(vendor);
            if (nameV.equalsIgnoreCase(vName)) {
                System.out.println(nameV);
                Actions action = new Actions(driver);
                action.moveToElement(vendor).build().perform();
                waitForElementToBeVisibility(statusVendor);
                WebElement status = statusList.get(i);
                foundStatus = getText(status);
                break;
            }
        }
        return foundStatus;
    }
}