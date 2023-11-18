package btrust.btrustOne.admin.workflowManagement.mobileInteraction.pageObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MobileFlowPage extends BasePage {
    public MobileFlowPage(WebDriver driver) {
        super(driver);
    }


    @FindBy(css = ".Header-module__btns__2nTIC :nth-child(2)")
    protected WebElement flowStatus;
    @FindBy(css = ".Header-module__btns__2nTIC :nth-child(1)")
    protected WebElement editFlowParametersButton;
    @FindBy(css = ".Header-module__btns__2nTIC :nth-child(3)")
    protected WebElement saveButton;

    @FindBy(css = ".Header-module__btns__2nTIC :nth-child(4)")
    protected WebElement kebabMenuList;
    @FindBy(css = ".MuiList-root.MuiMenu-list.MuiList-padding li")
    protected List<WebElement> menuList;
    @FindBy(css = "div .EditTitleInput-module__title__28vuN")
    protected WebElement flowNameField;
    @FindBy(css = "div.EditTitleInput-module__title__28vuN button")
    protected WebElement editFlowNameButton;
    @FindBy(css = "div.EditTitleInput-module__inputItem__1-koz input")
    protected WebElement editFlowNameField;
    @FindBy(css = ".EditTitleInput-module__withSave__3fZWd button")
    protected WebElement saveNewFlowName;
    @FindBy(css = "div.BackToList-module__container__2RFgn button")
    protected WebElement backButton;
    @FindBy(css = "div .PopupTitle-module__title__Khvfk")
    protected WebElement popUpTitle;
    @FindBy(css = "div.WarningPopup-module__actions__2bC09 button")
    protected List<WebElement> popUpButtons;


    @Step("Click on back button")
    public void clickBackButton() {
        scrollToElement(backButton);
        click(backButton);
    }


    @Step("Check the flow status")
    public String checkFlowStatus() {
        scrollToElement(flowStatus);
        return getText(flowStatus);
    }


    @Step("Check if the Save button enable")
    public boolean isSaveButtonEnabled() {
        scrollToElement(saveButton);
        return isEnabled(saveButton);
    }


    @Step("Change flow name")
    public String changeFlowName(String flowName) {
        //      waitForPageFinishLoading();
        scrollToElement(editFlowNameButton);
        waitForElementToBeClickable(editFlowNameButton);
        click(editFlowNameButton);
        fillText(editFlowNameField, flowName);
        click(saveNewFlowName);
        waitForElementToBeVisibility(flowNameField);
        return getText(flowNameField);
    }


    @Step("Select Action From Kebab List")
    public void selectActionFromKebabList(String actionName) {
        scrollToElement(kebabMenuList);
        waitForElementToBeClickable(kebabMenuList);
        click(kebabMenuList);
        sleep(2000);
        for (int j = 0; j < menuList.size(); j++) {
            waitForElementToBeVisibility(menuList.get(j));
            scrollToElement(menuList.get(j));
            if (getText(menuList.get(j)).equalsIgnoreCase(actionName)) {
                waitForElementToBeClickable(menuList.get(j));
                click(menuList.get(j));
                break;
            }
        }
    }


    @Step("Pop-up screen")
    public void popupScreen(String titleName, String buttonName) {
        waitForTextToBeInElement(popUpTitle, titleName);
        for (WebElement el : popUpButtons) {
            scrollToElement(el);
            if (getText(el).equalsIgnoreCase(buttonName)) {
                scrollToElement(el);
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
    }


}


