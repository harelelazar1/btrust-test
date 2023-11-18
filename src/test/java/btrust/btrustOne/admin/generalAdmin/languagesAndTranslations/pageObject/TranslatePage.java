package btrust.btrustOne.admin.generalAdmin.languagesAndTranslations.pageObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class TranslatePage extends BasePage {
    public TranslatePage(WebDriver driver) {
        super(driver);
    }

    public String randomString;


    @FindBy(css = "div .TopBar-module__title__16hU9")
    protected WebElement titleName;

    @FindBy(css = ".TableFilter-module__container__fE7EN input")
    protected WebElement searchField;
    @FindBy(css = ".react-horizontal-scrolling-menu--scroll-container  .Tab-module__container__3D-OC")
    protected List<WebElement> moduleNameList;
    @FindBy(css = ".LanguageEditor-module__wrapper__iHvVv li")
    protected List<WebElement> serviceNameList;
    @FindBy(css = ".TableList-module__container__15O90 .ElementName-module__title__1RsZE")
    protected List<WebElement> fieldLabelList;
    @FindBy(css = ".InputCells-module__inputActions__3pdYB button")
    protected List<WebElement> changeDefaultTranslationButton;
    @FindBy(css = ".TableCell-module__editContainer__1fD2S>:nth-child(2) >:nth-child(2)>div :nth-child(2) input")
    protected List<WebElement> translationFieldList;
    @FindBy(css = ".TableCell-module__editContainer__1fD2S>:nth-child(2) >:nth-child(2)>div :nth-child(3)")
    protected List<WebElement> removeButtonList;
    @FindBy(css = ".BottomStickyActions-module__actions__1YB7n button")
    protected WebElement saveButton;
    @FindBy(css = "div>.PopupTitle-module__text__3pjTU")
    protected WebElement popupTitle;
    @FindBy(css = "div>.WarningPopup-module__title__2S4NL")
    protected WebElement popUpBodyMessage;
    @FindBy(css = ".WarningPopup-module__actions__2bC09> button")
    protected List<WebElement> popUpButtons;

    @FindBy(css = "span [role='tabpanel']")
    protected WebElement backButton;


    @Step("Return the page title name")
    public String pageTitleName() {
        waitForElementToBeVisibility(titleName);
        scrollToElement(titleName);
        String pageTitle = getText(titleName);
        return pageTitle;
    }

    @Step("Choose module from from screen")
    public void chooseModuleForTranslate(String moduleName) {
        waitForPageFinishLoading();
        for (WebElement module : moduleNameList) {
            scrollToElement(module);
            if (getText(module).equalsIgnoreCase(moduleName)) {
                scrollToElement(module);
                waitForElementToBeClickable(module);
                click(module);
                break;
            }
        }
    }

    @Step("Choose service from sidebar list")
    public void chooseServiceFromList(String serviceName) {
        waitForPageFinishLoading();
        for (WebElement service : serviceNameList) {
            scrollToElement(service);
            if (getText(service).equalsIgnoreCase(serviceName)) {
                scrollToElement(service);
                waitForElementToBeClickable(service);
                click(service);
                break;
            }
        }
    }


    @Step("search name in filed label column")
    public boolean searchFieldNameInFieldLabelColumn(String fieldName) {
        try {
            waitForElementToBeVisibility(searchField);
            fillText(searchField, fieldName);
            for (WebElement field : fieldLabelList) {
                waitForElementToBeVisibility(field);
                scrollToElement(field);
                if (getText(field).equalsIgnoreCase(fieldName)) {
                    scrollToElement(field);
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("some thing went wrong");
        }
        return false;
    }


    @Step("Add new translation for field")
    public void addNewTranslateForField(String fieldName, String newTranslate) {
        try {
            waitForElementToBeVisibility(searchField);
            fillText(searchField, fieldName);
            for (int i = 0; i < fieldLabelList.size(); i++) {
                waitForElementToBeVisibility(fieldLabelList.get(i));
                scrollToElement(fieldLabelList.get(i));
                if (getText(fieldLabelList.get(i)).equalsIgnoreCase(fieldName)) {
                    scrollToElement(changeDefaultTranslationButton.get(i));
                    click(changeDefaultTranslationButton.get(i));
                    waitForElementToBeVisibility(removeButtonList.get(i));
                    fillText(translationFieldList.get(i), newTranslate);
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("error :" + e.getMessage());
        }
    }


    @Step("Delete new translation field")
    public void deleteNewTranslateField(String fieldName) {
        try {
            waitForElementToBeVisibility(searchField);
            fillText(searchField, fieldName);
            for (int i = 0; i < fieldLabelList.size(); i++) {
                waitForElementToBeVisibility(fieldLabelList.get(i));
                scrollToElement(fieldLabelList.get(i));
                if (getText(fieldLabelList.get(i)).equalsIgnoreCase(fieldName)) {
                    scrollToElement(removeButtonList.get(i));
                    click(removeButtonList.get(i));
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("error :" + e.getMessage());
        }
    }


    @Step("Click on Pop up Warning buttons")
    public void clickOnPopeUpButtons(String buttonName) {
        waitForElementToBeVisibility(popUpBodyMessage);
        for (WebElement button : popUpButtons) {
            scrollToElement(button);
            if (getText(button).equalsIgnoreCase(buttonName)) {
                waitForElementToBeClickable(button);
                click(button);
                break;
            }
        }
    }


    @Step("Click on Save button")
    public void clickOnSaveButton() {
        waitForElementToBeClickable(saveButton);
        scrollToElement(saveButton);
        click(saveButton);
    }


    @Step("Click on Back button")
    public void clickOnBackButton() {
        waitForElementToBeClickable(backButton);
        scrollToElement(backButton);
        click(backButton);
    }


}


