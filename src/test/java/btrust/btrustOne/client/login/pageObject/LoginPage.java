package btrust.btrustOne.client.login.pageObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "[placeholder='Company name']")
    protected WebElement companyNameField;
    @FindBy(css = "div div button")
    protected WebElement nextButton;
    @FindBy(css = "input[placeholder='User name']")
    protected WebElement userNameField;
    @FindBy(css = "input[placeholder='Password']")
    protected WebElement passwordField;
    @FindBy(css = "div div button")
    protected WebElement signInButton;

    @FindBy(css = "#root > div > div > iframe")
    protected WebElement iframe;

    @FindBy(css = "input[placeholder='username']")
    protected WebElement userNameFieldForSecurity;
    @FindBy(css = "input[placeholder='password']")
    protected WebElement passwordFieldForSecurity;
    @FindBy(css = "div div button")
    protected WebElement signInButtonForSecurity;

    @FindBy(css = "div#login-div>#errorMessage")
    protected WebElement errorMessage;
    @FindBy(css = ".code-container > input:nth-child(1)")
    protected WebElement OTPField1;
    @FindBy(css = ".code-container > input:nth-child(2)")
    protected WebElement OTPField2;
    @FindBy(css = ".code-container > input:nth-child(3)")
    protected WebElement OTPField3;
    @FindBy(css = ".code-container > input:nth-child(4)")
    protected WebElement OTPField4;
    @FindBy(css = ".code-container > input:nth-child(5)")
    protected WebElement OTPField5;
    @FindBy(css = ".code-container > input:nth-child(6)")
    protected WebElement OTPField6;
    @FindBy(css = "div>.content span")
    protected WebElement titleLoginPage;


    @Step("Login with company:{companyName}, user: {userName}, password: {password}")
    public void login(String companyName, String userName, String password) {
        try {
            waitForPageFinishLoading();
            WebStorage webStorage = (WebStorage) driver;
            webStorage.getSessionStorage().clear();
            webStorage.getLocalStorage().clear();
            driver.navigate().refresh();
            sleep(2000);
            waitForPageFinishLoading();
            scrollToElement(companyNameField);
            fillText(companyNameField, companyName);
            scrollToElement(nextButton);
            click(nextButton);
            try {
                errorMessagesDisplayed();
            } catch (Exception e) {
                scrollToElement(userNameField);
                fillText(userNameField, userName);
                scrollToElement(passwordField);
                fillText(passwordField, password);
                scrollToElement(signInButton);
                click(signInButton);
            }
        } catch (Exception e) {
            driver.navigate().refresh();
            waitForPageFinishLoading();
            driver.manage().deleteAllCookies();
            sleep(2000);
            waitForPageFinishLoading();
            scrollToElement(companyNameField);
            fillText(companyNameField, companyName);
            scrollToElement(nextButton);
            click(nextButton);
            try {
                errorMessagesDisplayed();
            } catch (Exception exception) {
                waitForElementToBeVisibility(userNameField);
                scrollToElement(userNameField);
                fillText(userNameField, userName);
                scrollToElement(passwordField);
                fillText(passwordField, password);
                scrollToElement(signInButton);
                click(signInButton);
            }
        }
    }

    @Step("login with company:{companyName}, user: {userName}, password: {password}")
    public void loginNew(String companyName, String userName, String password) {
        boolean companyFlag = true;
        try {
            waitForElementToBeVisibility(companyNameField);
        } catch (Exception e) {
            waitForElementToBeVisibility(userNameField);
            companyFlag = false;
        }
        if (companyFlag) {
            fillText(getCompanyNameField(), companyName);
            click(nextButton);
            waitForElementToBeVisibility(userNameField);
            fillText(userNameField, userName);
            fillText(passwordField, password);
            click(signInButton);
        } else {
            fillText(userNameField, userName);
            fillText(passwordField, password);
            click(signInButton);
        }
    }


    @Step("login with user: {userName}, password: {password}")
    public void loginNewForSecurity(String userName, String password) {
        driver.switchTo().frame(iframe);
        waitForElementToBeVisibility(userNameFieldForSecurity);
        fillText(userNameFieldForSecurity, userName);
        fillText(passwordFieldForSecurity, password);
        click(signInButton);
        driver.switchTo().defaultContent();
    }

    @Step("Check that errorMessage appear")
    public String errorMessage(String text) {
        driver.switchTo().frame(iframe);
        waitForTextToBeInElement(errorMessage, text);
        return getText(errorMessage);
    }

    @Step("Check that errorMessagesDisplayed")
    public boolean errorMessagesDisplayed() {
        driver.switchTo().frame(iframe);
        sleep(5000);
        return isDisplayed(errorMessage);
    }

    @Step("Type OTP code")
    public void typeOTP(String input1, String input2, String input3, String input4, String input5, String input6) {
        fillText(OTPField1, input1);
        fillText(OTPField2, input2);
        fillText(OTPField3, input3);
        fillText(OTPField4, input4);
        fillText(OTPField5, input5);
        fillText(OTPField6, input6);
    }

    public WebElement getCompanyNameField() {
        return companyNameField;
    }

    public void setCompanyNameField(WebElement companyNameField) {
        this.companyNameField = companyNameField;
    }

    public String checkTitleName() {
        driver.switchTo().frame(iframe);
        scrollToElement(titleLoginPage);
        String titleName = getText(titleLoginPage);
        return titleName;
    }
}