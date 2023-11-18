package btrust.btrustOne.admin.dataModule.entitiesManagement.pageObject.createBusinessCategory;

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

    boolean bol;

    @FindBy(css = ".PopupTitle-module__title__Khvfk > .PopupTitle-module__text__3pjTU")
    protected WebElement title;
    @FindBy(css = ".WarningPopup-module__icon__2vSzF > .MuiSvgIcon-root")
    protected WebElement popupIcon;
    @FindBy(css = ".WarningPopup-module__icon__2vSzF>svg")
    protected WebElement leavingPopupIcon;
    @FindBy(css = ".WarningPopup-module__content__xSQnb > .WarningPopup-module__title__2S4NL")
    protected WebElement popupTitle;
    @FindBy(css = "div.WarningPopup-module__content__xSQnb .WarningPopup-module__title__2S4NL")
    protected WebElement leavingPopupTitle;
    @FindBy(css = ".WarningPopup-module__content__xSQnb >.WarningPopup-module__description__3yVro")
    protected WebElement popupDescription;
    @FindBy(css = ".WarningPopup-module__content__xSQnb > .WarningPopup-module__description__3yVro")
    protected WebElement leavingPopupDescription;
    @FindBy(css = ".WarningPopup-module__checkbox__1shfC .MuiButtonBase-root")
    protected WebElement popupCheckbox;
    @FindBy(css = ".WarningPopup-module__checkbox__1shfC input")
    protected WebElement checkBoxIsSelected;
    @FindBy(css = "div.WarningPopup-module__checkbox__1shfC")
    protected WebElement checkboxText;
    @FindBy(css = ".WarningPopup-module__actions__2bC09 > button")
    protected List<WebElement> buttonsList;
    @FindBy(css = ".MuiSvgIcon-root.MuiSvgIcon-fontSizeLarge")
    protected WebElement leavingPopupButton;
    @FindBy(css = ".PopupTitle-module__text__3pjTU > .PopupTitle-module__icon__hcL5f")
    protected WebElement closeButton;


    @Step("Return the text of the title")
    public String title() {
        waitForElementToBeVisibility(title);
        return getText(title);
    }

    @Step("Check that popup icon is displayed")
    public boolean popupIconIsDisplayed() {
        try {
            scrollToElement(popupIcon);
            return imageIsDisplayed(popupIcon);
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Check that leaving popup icon is displayed")
    public boolean leavingPopupIconIsDisplayed() {
        try {
            waitForElementToBeVisibility(leavingPopupIcon);
            return imageIsDisplayed(leavingPopupIcon);
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Return the text of popup title")
    public String popupTitle() {
        scrollToElement(popupTitle);
        return getText(popupTitle);
    }

    @Step("Return the text of popup description")
    public String popupDescription() {
        scrollToElement(popupDescription);
        return getText(popupDescription);
    }

    @Step("Return the text of leaving popup title")
    public String leavingPopupTitle() {
        waitForElementToBeVisibility(leavingPopupTitle);
        return getText(leavingPopupTitle);
    }

    @Step("Return the text of leaving popup description")
    public String leavingPopupDescription() {
        waitForElementToBeVisibility(leavingPopupDescription);
        return getText(leavingPopupDescription);
    }

    @Step("Check the checkbox")
    public void checkThePopupCheckbox() {
        waitForElementToBeClickable(popupCheckbox);
        click(popupCheckbox);
    }

    @Step("Check if the checkbox is selected")
    public boolean checkBoxIsSelected() {
        boolean isSelect = isSelected(checkBoxIsSelected);
        return isSelect;
    }

    @Step("Return the text of checkbox text")
    public String checkboxText() {
        waitForElementToBeVisibility(checkboxText);
        return getText(checkboxText);
    }

    @Step("Click on close popup button")
    public void closePopup() {
        scrollToElement(closeButton);
        click(closeButton);
        sleep(2000);
    }

    @Step("Click on button")
    public boolean clickOnButton(String isDisabled, String button) {
        waitForPageFinishLoading();
        switch (isDisabled) {
            case "no":
                waitForList(buttonsList);
                for (WebElement el : buttonsList) {
                    if (getText(el).equalsIgnoreCase(button)) {
                        click(el);
                        break;
                    }
                }
                break;
            case "yes":
                waitForList(buttonsList);
                for (WebElement el : buttonsList) {
                    scrollToElement(el);
                    if (getText(el).equalsIgnoreCase(button)) {
                        bol = isEnabled(el);
                        click(el);
                        break;
                    }
                }
                break;
        }
        return bol;
    }

    @Step("Click on goBack button")
    public void goBackButton() {
        waitForElementToBeClickable(leavingPopupButton);
        click(leavingPopupButton);
    }
}
