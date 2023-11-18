package btrust.btrustOne.client.cases.pagesObject.createNewCase.pagesObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CasesCreationPage extends BasePage {
    public CasesCreationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".ApprovalPopup-module__title__2aTmf > .ApprovalPopup-module__text__e_k28")
    protected WebElement casesCreationTitle;
    @FindBy(css = ".ApprovalPopup-module__title__2aTmf > .ApprovalPopup-module__close__32E9K")
    protected WebElement closePopupButton;
    @FindBy(css = ".ApprovalPopup-module__content__3DcfQ > h3")
    protected WebElement popupTitle;
    @FindBy(css = ".ApprovalPopup-module__content__3DcfQ > span")
    protected WebElement popupDescription;
    @FindBy(css = ".PopupBottomButtons-module__container__20krY > button")
    protected List<WebElement> buttonsList;


    @Step("Return the text of cases creation title")
    public String casesCreationTitle() {
        waitForElementToBeVisibility(casesCreationTitle);
        return getText(casesCreationTitle);
    }

    @Step("Return the popup title")
    public String popupTitle() {
        waitForElementToBeVisibility(popupTitle);
        return getText(popupTitle);
    }

    @Step("Return the popup description")
    public String popupDescription(String text) {
        waitForTextToBeInElement(popupDescription, text);
        return getText(popupDescription);
    }

    @Step("Click on close popup button")
    public void closePopupButton() {
        waitForElementToBeClickable(closePopupButton);
        click(closePopupButton);
    }

    @Step("Click on button: {button}")
    public void clickOnButton(String button) {
        waitForPageFinishLoading();
        for (WebElement el : buttonsList) {
            waitForElementToBeClickable(el);
            if (getText(el).equalsIgnoreCase(button)) {
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
    }
}