package btrust.idm.pageObject.questionnaireForm;


import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class FinancialsPage extends BasePage {
    public FinancialsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".Financials_title__1u9jl")
    protected WebElement financialsTitle; // financials header
    @FindBy(css = ".LabelError_container__b1ltp")
    protected List<WebElement> mandatoryFieldErrorMessageList; // mandatory field error messages list
    @FindBy(css = ".LabelError_container__b1ltp")
    protected WebElement mandatoryFieldErrorMessage; // mandatory field error messages
    @FindBy(css = ".Financials_regulatory__1L05Y > li")
    protected List<WebElement> financialStatementsList; // Qualified Financial Statements buttons List, yes/no
    @FindBy(css = ".Financials_regulatory__1L05Y > li")
    protected WebElement financialStatementsButtons; // Qualified Financial Statements buttons, yes/no
    @FindBy(css = "textarea[name='tab_4_nameOfAuditors']")
    protected WebElement auditorsTextBox; // Name of the appointed Auditors text box
    @FindBy(css = ".Financials_body__3UNVk  input[name]")
    protected List<WebElement> financialDataList; // provide the last three years of financial data.
    @FindBy(css = "textarea[name='tab_4_financialComment']")
    protected WebElement commentsTextBox; // comments text box
    @FindBy(css = ".Financials_raitings__1SEIh input")
    protected List<WebElement> creditRatingsList; // creditRatings data.
    @FindBy(css = "textarea[name='tab_4_ratingsComment']")
    protected WebElement creditRatingCommentsTextBox; // credit Rating Comments Text Box
    @FindBy(css = ".LabelError_container__b1ltp")
    protected WebElement errorMessage;


    @Step("get the text from financials Header")
    public boolean financialsTitle(String text) {
        try {
            waitForTextToBeInElement(financialsTitle, text);
            getText(financialsTitle).equalsIgnoreCase(text);
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

    @Step("fill the financials tab")
    public void fillRegulatoryTab(String financialStatementsButton, String fillCommentsTextBox, String financialData) {
        //Qualified Financial Statements - yes/no
        waitForElementToBeClickable(financialStatementsButtons);
        for (WebElement el : financialStatementsList) {
            if (getText(el).contains(financialStatementsButton)) {
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
        fillText(auditorsTextBox, fillCommentsTextBox); //Name of the appointed Auditors

        // last three years of financial data.
        for (WebElement el : financialDataList) {
            waitForElementToBeClickable(el);
            fillText(el, financialData);
        }
        fillText(commentsTextBox, fillCommentsTextBox); // Comments for  financial data.

        // credit rating list
        for (WebElement el : creditRatingsList) {
            waitForElementToBeClickable(el);
            fillText(el, financialData);
        }
        fillText(creditRatingCommentsTextBox, fillCommentsTextBox);  // Comments for  Credit Ratings

    }
}
