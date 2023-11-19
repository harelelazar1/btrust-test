package btrust.idm.pageObject.questionnaireForm;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class InternalPoliciesPage extends BasePage {
    public InternalPoliciesPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".InternalPoliciesInformation_title__1O9eL")
    protected WebElement internalPolicesInfoTitle; // internal polices header
    @FindBy(css = ".InternalPoliciesInformation_container__205jG > :nth-child(2) > textarea")
    protected WebElement firstFreeField;
    @FindBy(css = ".InternalPoliciesInformation_container__205jG > :nth-child(4) > textarea")
    protected WebElement secondFreeField;
    @FindBy(css = ".InternalPoliciesInformation_container__205jG > :nth-child(6) > ul > li")
    protected List<WebElement> firstYesNoList;
    @FindBy(css = ".InternalPoliciesInformation_container__205jG > :nth-child(6) > textarea")
    protected WebElement thirdFreeField;
    @FindBy(css = ".InternalPoliciesInformation_container__205jG > :nth-child(8) > ul > li")
    protected List<WebElement> secondYesNoList;
    @FindBy(css = ".InternalPoliciesInformation_container__205jG > :nth-child(8) > textarea")
    protected WebElement fourthFreeField;
    @FindBy(css = ".InternalPoliciesInformation_container__205jG > :nth-child(10) > ul > li")
    protected List<WebElement> thirdYesNoList;
    @FindBy(css = ".InternalPoliciesInformation_container__205jG > :nth-child(10) > textarea")
    protected WebElement fiveFreeField;
    @FindBy(css = ".InternalPoliciesInformation_container__205jG > :nth-child(12) > ul > li")
    protected List<WebElement> fourthYesNoList;
    @FindBy(css = ".InternalPoliciesInformation_container__205jG > :nth-child(12) > textarea")
    protected WebElement sixFreeField;
    @FindBy(css = ".InternalPoliciesInformation_container__205jG > :nth-child(14) > ul > li")
    protected List<WebElement> fiveYesNoList;
    @FindBy(css = ".InternalPoliciesInformation_container__205jG > :nth-child(14) > textarea")
    protected WebElement sevenFreeField;
    @FindBy(css = ".InternalPoliciesInformation_container__205jG > :nth-child(16) > ul > li")
    protected List<WebElement> sixYesNoList;
    @FindBy(css = ".InternalPoliciesInformation_container__205jG > :nth-child(16) > textarea")
    protected WebElement eighthFreeField;
    @FindBy(css = ".InternalPoliciesInformation_container__205jG > :nth-child(18) > ul > li")
    protected List<WebElement> sevenYesNoList;
    @FindBy(css = ".InternalPoliciesInformation_container__205jG > :nth-child(18) > textarea")
    protected WebElement ninthFreeField;


    @Step("Check that internalPolicesInfoTitle appear")
    public boolean internalPolicesInfoTitle(String text) {
        try {
            waitForTextToBeInElement(internalPolicesInfoTitle, text);
            getText(internalPolicesInfoTitle).equalsIgnoreCase(text);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Fill details in Internal Policies Information fields")
    public void fillInternalPoliciesInformation(String text, String chooseFromFirstYesNoList, String chooseFromSecondYesNoList, String chooseFromThirdYesNoList, String chooseFromFourthYesNoList, String chooseFromFiveYesNoList, String chooseFromSixYesNoList) {
        waitForTextToBeInElement(internalPolicesInfoTitle, "Internal Policies Information");
        fillText(firstFreeField, text);
        fillText(secondFreeField, text);
        for (WebElement el : firstYesNoList) {
            if (getText(el).equalsIgnoreCase(chooseFromFirstYesNoList)) {
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
        fillText(thirdFreeField, text);
        for (WebElement el : secondYesNoList) {
            if (getText(el).equalsIgnoreCase(chooseFromSecondYesNoList)) {
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
        fillText(fourthFreeField, text);
        for (WebElement el : thirdYesNoList) {
            if (getText(el).equalsIgnoreCase(chooseFromThirdYesNoList)) {
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
        fillText(fiveFreeField, text);
        for (WebElement el : fourthYesNoList) {
            if (getText(el).equalsIgnoreCase(chooseFromFourthYesNoList)) {
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
        fillText(sixFreeField, text);
        for (WebElement el : fiveYesNoList) {
            if (getText(el).equalsIgnoreCase(chooseFromFiveYesNoList)) {
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
        fillText(sevenFreeField, text);
        for (WebElement el : sixYesNoList) {
            if (getText(el).equalsIgnoreCase(chooseFromSixYesNoList)) {
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
        fillText(eighthFreeField, text);
        for (WebElement el : sevenYesNoList) {
            if (getText(el).equalsIgnoreCase(chooseFromSixYesNoList)) {
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
        fillText(ninthFreeField, text);
    }
}