package btrust.idm.pageObject.questionnaireForm;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class TaxPage extends BasePage {
    public TaxPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".Tax_title__RjQw2 ")
    protected WebElement taxTitle; // tax header
    @FindBy(css = ".LabelError_container__b1ltp")
    protected List<WebElement> mandatoryFieldErrorMessageList; // mandatory field error messages list
    @FindBy(css = ".LabelError_container__b1ltp")
    protected WebElement mandatoryFieldErrorMessage; // mandatory field error messages
    @FindBy(css = ".Tax_regulatory__3RW06 > li")
    protected List<WebElement> fatcaButtonsList; //  Is your company intending to be FATCA compliant? - buttons yes/no
    @FindBy(css = "textarea[name='tab_5_comment']")
    protected WebElement statusTextBox; //  If yes, which status and by when?no
    @FindBy(css = ".LabelError_container__b1ltp")
    protected WebElement errorMessage;


    @Step("Check that taxTitle appear")
    public boolean taxTitle(String text) {
        try {
            waitForTextToBeInElement(taxTitle, text);
            getText(taxTitle).equalsIgnoreCase(text);
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

    @Step("fill the tax tab")
    public void fillTaxTab(String FATCAButtons, String fillTextBox) {
        //Qualified Financial Statements - yes/no
        waitForElementToBeVisibility(taxTitle);
        for (WebElement el : fatcaButtonsList) {
            if (getText(el).contains(FATCAButtons)) {
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
        fillText(statusTextBox, fillTextBox); //Name of the appointed Auditors
    }


}
