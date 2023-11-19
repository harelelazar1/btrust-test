package btrust.idm.pageObject.questionnaireForm;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MifidPage extends BasePage {
    public MifidPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".MIFID_title__3MezX")
    protected WebElement mifidTitle; // mifid header
    @FindBy(css = ".LabelError_container__b1ltp")
    protected List<WebElement> mandatoryFieldErrorMessageList; // mandatory field error messages list
    @FindBy(css = ".LabelError_container__b1ltp")
    protected WebElement mandatoryFieldErrorMessage; // mandatory field error messages
    @FindBy(css = "div:nth-of-type(2) > .MIFID_regulatory__TNYII > li")
    protected List<WebElement> mifidButtonsList; // MIFID buttons list
    @FindBy(css = "div:nth-of-type(4) > .MIFID_regulatory__TNYII > li")
    protected List<WebElement> giamButtonsList; // GIAM buttons list
    @FindBy(css = "textarea[name='tab_7_comment']")
    protected WebElement giamCommentsTextBox; // GIAM text box
    @FindBy(css = ".LabelError_container__b1ltp")
    protected WebElement errorMessage;


    @Step("Check that mifidTitle appear")
    public boolean mifidTitle(String text) {
        try {
            waitForTextToBeInElement(mifidTitle, text);
            getText(mifidTitle).equalsIgnoreCase(text);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Check that errorMessage appear")
    public String errorMessage(String text) {
        waitForTextToBeInElement(errorMessage, text);
        return getText(errorMessage);
    }

    @Step("get the number of the error messages list")
    public int mandatoryFieldErrorMessageList() {
        waitForElementToBeVisibility(mandatoryFieldErrorMessage);
        return mandatoryFieldErrorMessageList.size();
    }

    @Step("fill the MIFID tab")
    public void fillMIFIDTab(String MIFIDButtons, String GIAMTextBox) {
        // MIFID - yes/no buttons
        waitForElementToBeVisibility(mifidTitle);
        for (WebElement el : mifidButtonsList) {
            if (getText(el).contains(MIFIDButtons)) {
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
        //GIAM  buttons
        for (WebElement el : giamButtonsList) {
            if (getText(el).contains(MIFIDButtons)) {
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
        fillText(giamCommentsTextBox, GIAMTextBox); //Disaster Recovery Plan in place text box - comments
    }


}
