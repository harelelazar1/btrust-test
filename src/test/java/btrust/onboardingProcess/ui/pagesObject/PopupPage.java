package btrust.onboardingProcess.ui.pagesObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class PopupPage extends BasePage {
    public PopupPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#popupTitle")
    protected WebElement popupTitle;
    @FindBy(css = "#popupDescr")
    protected WebElement popupDescription;
    @FindBy(css = "div button")
    protected WebElement popupButton;

    @FindBy(css = ".NetworkSpeed_title__Bel9x > span")
    protected WebElement networkPopupTitle;
    @FindBy(css = ".NetworkSpeed_btns___OAE0 > button")
    protected List<WebElement> networkPopupButtons;


    @Step("Return the text of popup title")
    public String popupTitle() {
        waitForPageFinishLoading();
        waitForElementToBeVisibility(popupTitle);
        return getText(popupTitle);
    }

    @Step("Return the text of popup description")
    public String popupDescription() {
        waitForElementToBeVisibility(popupDescription);
        return getText(popupDescription);
    }

    @Step("Click on popupButton")
    public void popupButton() {
        waitForElementToBeClickable(popupButton);
        click(popupButton);
    }

    @Step("Check if networkPopup is displayed")
    public void networkPopup(String button) {
        waitForPageFinishLoading();
        try {
            waitForElementToBeVisibility(networkPopupTitle);
            System.out.println("Network speed is displayed!!!");
            if (isDisplayed(networkPopupTitle)) {
                for (WebElement el : networkPopupButtons) {
                    if (getText(el).equalsIgnoreCase(button))
                        waitForElementToBeClickable(el);
                    click(el);
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Network speed is not displayed");
        }
    }
}