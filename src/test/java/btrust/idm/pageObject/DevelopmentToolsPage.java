package btrust.idm.pageObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DevelopmentToolsPage extends BasePage {
    public DevelopmentToolsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".page-container > .title > span")
    protected WebElement title;
    @FindBy(css = ".settings-box > :nth-child(1) > :nth-child(1) > :nth-child(2) > .checkbox")
    protected WebElement saveDataCheckBox;
    @FindBy(css = ".form > :nth-child(3) > input")
    protected WebElement amountField;
    @FindBy(css = ".settings-box > :nth-child(1) > button")
    protected WebElement sendButton;

    @Step("Check that developmentToolsTitle appear")
    public String developmentToolsTitle(String text) {
        waitForTextToBeInElement(title, text);
        return getText(title);
    }

    @Step("Check that saveDataCheckBox is not check")
    public boolean saveDataCheckBox() {
        try {
            waitForElementToBeClickable(saveDataCheckBox);
            if (isSelected(saveDataCheckBox)) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    @Step("return the value that appear in amountField")
    public String amountField() {
        waitForElementToBeVisibility(amountField);
        return amountField.getAttribute("value");
    }

    @Step("click on sendButton")
    public void sendButton() {
        waitForElementToBeClickable(sendButton);
        click(sendButton);
    }
}
