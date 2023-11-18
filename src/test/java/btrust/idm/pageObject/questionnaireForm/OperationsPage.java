package btrust.idm.pageObject.questionnaireForm;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class OperationsPage extends BasePage {
    public OperationsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".Operations_title__2cJLg")
    protected WebElement operationsTitle; // operations header
    @FindBy(css = ".LabelError_container__b1ltp")
    protected List<WebElement> mandatoryFieldErrorMessageList; // mandatory field error messages list
    @FindBy(css = ".LabelError_container__b1ltp")
    protected WebElement mandatoryFieldErrorMessage; // mandatory field error messages
    @FindBy(css = "div:nth-of-type(2) > .Operations_regulatory__3cLkF > li")
    protected List<WebElement> continuityButtonsList; //  Business Continuity Plan in place buttons list
    @FindBy(css = "textarea[name='tab_6_businessPlanComment']")
    protected WebElement continuityTextBox; // Business Continuity Plan in place  text box
    @FindBy(css = "div:nth-of-type(4) > .Operations_regulatory__3cLkF > li")
    protected List<WebElement> recoveryPlanButtonsList; //  disaster Recovery Plan in place buttons list
    @FindBy(css = "textarea[name='tab_6_recoveryPlanComment']")
    protected WebElement recoveryPlanTextBox; // disaster Recovery Plan in place  text box
    @FindBy(css = ".Operations_container__2lm8m > :nth-child(2) .LabelError_container__b1ltp")
    protected WebElement firstErrorMessage;
    @FindBy(css = ".Operations_container__2lm8m > :nth-child(4) .LabelError_container__b1ltp")
    protected WebElement secondErrorMessage;


    @Step("Check that operationsTitle appear")
    public boolean operationsTitle(String text) {
        try {
            waitForTextToBeInElement(operationsTitle, text);
            getText(operationsTitle).equalsIgnoreCase(text);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Check that firstErrorMessage appear")
    public String firstErrorMessage(String text) {
        waitForTextToBeInElement(firstErrorMessage, text);
        return getText(firstErrorMessage);
    }

    @Step("Check that secondErrorMessage appear")
    public String secondErrorMessage(String text) {
        waitForTextToBeInElement(secondErrorMessage, text);
        return getText(secondErrorMessage);
    }

    @Step("get the number of the error messages list")
    public int mandatoryFieldErrorMessageList() {
        waitForElementToBeVisibility(mandatoryFieldErrorMessage);
        return mandatoryFieldErrorMessageList.size();
    }

    @Step("fill the Operations tab")
    public void fillOperationsTab(String operationsButtons, String operationsTextBox) {
        // Business Continuity Plan in place - yes/no
        waitForElementToBeVisibility(operationsTitle);
        for (WebElement el : continuityButtonsList) {
            if (getText(el).contains(operationsButtons)) {
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
        fillText(continuityTextBox, operationsTextBox); // Business Continuity Plan in place text box - comments
        //Disaster Recovery Plan in place
        waitForElementToBeVisibility(operationsTitle);
        for (WebElement el : recoveryPlanButtonsList) {
            if (getText(el).contains(operationsButtons)) {
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
        fillText(recoveryPlanTextBox, operationsTextBox); //Disaster Recovery Plan in place text box - comments
    }

}
