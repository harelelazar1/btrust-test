package btrust.btrustOne.client.mandate.pagesObject.createMandate.pagesObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CancelMandateCreationPage extends BasePage {
    public CancelMandateCreationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".PopupTitle-module__title__Khvfk > .PopupTitle-module__text__3pjTU")
    protected WebElement cancelMandateCreationTitle;
    @FindBy(css = ".ConfirmPopup-module__main__31gHx > .ConfirmPopup-module__title__1JdG0")
    protected WebElement popupTitle;
    @FindBy(css = ".ConfirmPopup-module__main__31gHx > .ConfirmPopup-module__description__2tDH0")
    protected WebElement popupDescription;
    @FindBy(css = ".PopupBottomButtons-module__container__20krY>button")
    protected List<WebElement> popupButtons;


    @Step("Return the Cancel Mandate Creation title")
    public String cancelMandateCreationTitle() {
        waitForElementToBeVisibility(cancelMandateCreationTitle);
        return getText(cancelMandateCreationTitle);
    }

    @Step("Return the title of Cancel Mandate Creation popup")
    public String popupTitle() {
        waitForElementToBeVisibility(popupTitle);
        return getText(popupTitle);
    }

    @Step("Return the description of Cancel Mandate Creation popup")
    public String popupDescription() {
        waitForElementToBeVisibility(popupDescription);
        return getText(popupDescription);
    }

    @Step("Click on button: {button}")
    public void popupButtons(String button) {
        waitForList(popupButtons);
        for (WebElement el : popupButtons) {
            if (getText(el).equalsIgnoreCase(button)) {
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
    }
}