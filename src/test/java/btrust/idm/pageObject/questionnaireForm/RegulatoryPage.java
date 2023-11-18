package btrust.idm.pageObject.questionnaireForm;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class RegulatoryPage extends BasePage {
    public RegulatoryPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".Regulatory_title__108y0")
    protected WebElement regulatoryTitle; // regulatory header
    @FindBy(css = ".LabelError_container__b1ltp")
    protected List<WebElement> mandatoryFieldErrorMessageList; // mandatory field error messages list
    @FindBy(css = ".LabelError_container__b1ltp")
    protected WebElement mandatoryFieldErrorMessage; // mandatory field error messages
    @FindBy(css = ".Regulatory_regulatory___lV9W > li")
    protected List<WebElement> regulatoryButtonsList;
    @FindBy(css = ".Regulatory_regulatory___lV9W > li")
    protected WebElement regulatoryButtons;
    @FindBy(css = "textarea[name='tab_3_regulatoryBodies']")
    protected WebElement regulatoryTextBox;
    @FindBy(css = "textarea[name='tab_3_regulatoryLicenseType']")
    protected WebElement regulatoryLicenseTextBox;
    @FindBy(css = ".LabelError_container__b1ltp")
    protected WebElement errorMessage;


    @Step("get the text from regulatory Header")
    public boolean regulatoryTitle(String text) {
        try {
            waitForTextToBeInElement(regulatoryTitle, text);
            getText(regulatoryTitle).equalsIgnoreCase(text);
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


    @Step("fill the regulatory tab")
    public void fillRegulatoryTab(String regulateButton, String regulatoryBodyTextBox) {

        waitForElementToBeClickable(regulatoryButtons);
        click(regulatoryButtons);
        for (WebElement el : regulatoryButtonsList) {
            if (getText(el).contains(regulateButton)) {
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
        fillText(regulatoryTextBox, regulatoryBodyTextBox);
        fillText(regulatoryLicenseTextBox, regulatoryBodyTextBox);
    }


}
