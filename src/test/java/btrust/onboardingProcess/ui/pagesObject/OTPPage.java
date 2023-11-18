package btrust.onboardingProcess.ui.pagesObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class OTPPage extends BasePage {
    public OTPPage(WebDriver driver) {
        super(driver);
    }

    //send code

    @FindBy(css = ".MuiFormControl-root.MuiTextField-root.css-ldyhad>div>input")
    protected WebElement phoneNumberField;
    @FindBy(css = "button[type='button']")
    protected WebElement sendCodeButton;
    @FindBy(css = ".MuiInputBase-root>div input")
    protected WebElement chooseAreaCodeButton;
    @FindBy(css = "ul>li[role='option']>:nth-child(1)")
    protected List<WebElement> chooseCountryFromList;

    //verify code

    @FindBy(css = ".OTP_didNotReceive__ZRf1p button[type=button]")
    protected WebElement resendCodeButton;
    @FindBy(css = ".OTP_container__xU4Xx>button[type='button']")
    protected WebElement enterCodeButton;
    @FindBy(css = ".App_main__dwl8D [role='button']")
    protected WebElement backNavigation;
    @FindBy(css = ".VerifyCode_inputContainer__7P6Ir>input")
    protected List<WebElement> DigitsFields;
    @FindBy(css = "div>.OTP_btError__cShBo")
    protected WebElement wrongCodeMessage;
    @FindBy(css = "div>.OTP_container__xU4Xx>:nth-child(6)")
    protected WebElement MaxAttemptsMessage;

    @FindBy(css = ".MuiBox-root.css-1o8dslu")
    protected WebElement loader;
    @FindBy(css = ".OTP_lastEnterCodeCounter__iLQ2T ")
    protected WebElement timer;

    @Step("Enter phone number for sending the code")
    public void enterPhoneNumber(String phoneNumber) {
        waitForElementToBeVisibility(phoneNumberField);
        scrollToElement(phoneNumberField);
        fillText(phoneNumberField, phoneNumber);
    }

    @Step("Click on send code button")
    public void clickOnSendCodeButton() {
        waitForPageFinishLoading();
        waitForElementToBeVisibility(sendCodeButton);
        scrollToElement(sendCodeButton);
        click(sendCodeButton);
    }


    @Step("Choose area code from list")
    public void chooseAreaCodeFromList(String country) {
        try {
            waitForElementToBeVisibility(chooseAreaCodeButton);
            scrollToElement(chooseAreaCodeButton);
            click(chooseAreaCodeButton);
            for (WebElement countryName : chooseCountryFromList) {
                scrollToElement(countryName);
                if (getText(countryName).equalsIgnoreCase(country)) {
                    waitForElementToBeClickable(countryName);
                    click(countryName);
                }
            }
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
    }


    @Step("Check If Send Code button Is Enable")
    public boolean checkIfSendCodeButtonIsEnable() {
        try {
            waitForElementToBeVisibility(sendCodeButton);
            if (isEnabled(sendCodeButton)) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }


    @Step("Check If Enter code button Is Enable")
    public boolean checkIfEnterCodeButtonIsEnable() {
        try {
            waitForElementToBeVisibility(enterCodeButton);
            if (isEnabled(enterCodeButton)) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }


    @Step("Check If Loader display")
    public boolean checkIfLoaderDisplay() {
        try {
            waitForElementToBeVisibility(loader);
            if (isDisplayed(loader)) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }


    @Step("Check If timer appear after loader")
    public boolean checkIfTimerAppearAfterLoader() {
        Boolean isTimerDisplayed;
        sleep(35000);
        isTimerDisplayed = checkIfTimerDisplay();
        return isTimerDisplayed;
    }


    @Step("Check If timer display")
    public boolean checkIfTimerDisplay() {
        try {
            waitForElementToBeVisibility(timer);
            if (isDisplayed(timer)) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }


    @Step("Check If Back Navigation Button Display")
    public boolean checkIfBackNavigationButtonDisplay() {
        try {
            if (isDisplayed(backNavigation)) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }


    @Step("Enter code And click Enter button")
    public void enterCodeAndApprove(String fieldDigitNumber, String number) {
        try {
            waitForPageFinishLoading();
            WebElement digitField = getDigitFieldByNumber(fieldDigitNumber);
            scrollToElement(digitField);
            digitField.sendKeys(number);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private WebElement getDigitFieldByNumber(String fieldDigitNumber) {
        int index;
        switch (fieldDigitNumber) {
            case "One":
                index = 0;
                break;
            case "Two":
                index = 1;
                break;
            case "Three":
                index = 2;
                break;
            case "Four":
                index = 3;
                break;
            default:
                throw new IllegalArgumentException("Invalid digit field number: " + fieldDigitNumber);
        }
        return DigitsFields.get(index);
    }


    @Step("Click on Resend code button")
    public void clickOnResendCodeButton() {
        waitForElementToBeVisibility(resendCodeButton);
        scrollToElement(resendCodeButton);
        click(resendCodeButton);
    }

    @Step("Click on Enter code button")
    public void clickOnEnterCodeButton() {
        waitForElementToBeClickable(enterCodeButton);
        scrollToElement(enterCodeButton);
        click(enterCodeButton);
    }

    @Step("Click on Back navigation button")
    public void clickOnBackNavigationButton() {
        waitForElementToBeVisibility(backNavigation);
        scrollToElement(backNavigation);
        click(backNavigation);
    }


    @Step("Get the Error message after enter wrong code")
    public String GetErrorMessage() {
        waitForElementToBeVisibility(wrongCodeMessage);
        scrollToElement(wrongCodeMessage);
        return getText(wrongCodeMessage);
    }

    @Step("Get the Error message after enter wrong code")
    public String GetSecondErrorMessage() {
        waitForElementToBeVisibility(MaxAttemptsMessage);
        scrollToElement(MaxAttemptsMessage);
        return getText(MaxAttemptsMessage);
    }
}