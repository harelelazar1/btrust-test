package btrust.btrustOne.admin.dataModule.entitiesManagement.pageObject.createBusinessCategory;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AssignANewFormPage extends BasePage {
    public AssignANewFormPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".PopupTitle-module__title__Khvfk > .PopupTitle-module__text__3pjTU")
    protected WebElement title;
    @FindBy(css = ".AssignFormTopBar-module__container__3zzrC > .AssignFormTopBar-module__description__2GS5Y")
    protected WebElement subTitle;
    @FindBy(css = ".PopupTitle-module__text__3pjTU > .PopupTitle-module__icon__hcL5f")
    protected WebElement closeButton;

    @FindBy(css = ".PopupTitle-module__text__3pjTU .MuiSvgIcon-root")
    protected WebElement closeButton1;
    @FindBy(css = ".MultiCheckboxSelect-module__container__MNxh0 > button")
    protected WebElement allTypesButton;
    @FindBy(css = ".MultiCheckboxSelect-module__menu__1dWgk > li")
    protected List<WebElement> typesList;
    @FindBy(css = ".AssignFormTopBar-module__inputContainer__3WHIH > input")
    protected WebElement searchField;
    @FindBy(css = ".TableHeader-module__headCell__1yprP > span")
    protected List<WebElement> titlesList;
    @FindBy(css = ".Table-module__row__1d9Uv > :nth-child(1)")
    protected List<WebElement> checkboxesList;
    @FindBy(css = ".Table-module__row__1d9Uv > :nth-child(2)")
    protected List<WebElement> documentNameList;
    @FindBy(css = ".Table-module__row__1d9Uv > :nth-child(3)")
    protected List<WebElement> documentTypeList;
    @FindBy(css = ".Table-module__row__1d9Uv > :nth-child(4)")
    protected List<WebElement> documentStatusList;
    @FindBy(css = ".AssignFormFooter-module__container__2fKDV > button")
    protected List<WebElement> buttonList;
    @FindBy(css = ".AssignFormTopBar-module__inputContainer__3WHIH > .AssignFormTopBar-module__searchIcon__Vdwmg")
    protected WebElement searchIcon;
    @FindBy(css = ".AssignForm-module__tableContainer__3p4em .Table-module__wrapper__2khXy")
    protected WebElement documentTable;

    String value;
    boolean bol;


    @Step("Return the text of the title")
    public String title() {
        scrollToElement(title);
        return getText(title);
    }

    @Step("Return the text of the sub title")
    public String subTitle() {
        scrollToElement(subTitle);
        return getText(subTitle);
    }

    @Step("Type in the search field")
    public void typeInSearchField(String value) {
        waitForElementToBeClickable(searchField);
        fillText(searchField, value);
        waitForElementToBeVisibility(searchIcon);
        sleep(1000);
    }

    @Step("Click on button")
    public boolean clickOnButton(String button) {
        waitForPageFinishLoading();
        bol = false;
        for (WebElement el : buttonList) {
            scrollToElement(el);
            if (getText(el).equals(button)) {
                bol = isEnabled(el);
                String str = String.valueOf(bol);
                switch (str) {
                    case "true":
                        click(el);
                        break;
                    case "false":
                        return bol;
                }
                break;
            }
        }
        return bol;
    }

    @Step("Click on close button")
    public void clickOnCloseButton() {
        waitForElementToBeClickable(closeButton);
        scrollToElement(closeButton);
        click(closeButton);
    }

    @Step("Choose form type")
    public void chooseFormType(String option) {
        waitForPageFinishLoading();
        waitForElementToBeVisibility(allTypesButton);
        scrollToElement(allTypesButton);
        click(allTypesButton);
        waitForElementToBeClickable(typesList.get(0));
        for (WebElement el : typesList) {
            scrollToElement(el);
            if (getText(el).equals(option)) {
                click(el);
                break;
            }
        }
        click(allTypesButton);
    }

    @Step("Return value from the table")
    public String getValueFromTheTable(String title) {
        waitForList(titlesList);
        for (WebElement el : titlesList) {
            scrollToElement(el);
            if (getText(el).equalsIgnoreCase(title)) {
                System.out.println(getText(el));
                break;
            }
        }
        switch (title) {
            case "DOCUMENT NAME": {
                waitForList(documentNameList);
                for (WebElement el : documentNameList) {
                    scrollToElement(el);
                    value = getText(el);
                }
                break;
            }
            case "DOCUMENT TYPE": {
                waitForList(documentTypeList);
                for (WebElement el : documentTypeList) {
                    scrollToElement(el);
                    value = getText(el);
                }
                break;
            }
            case "DOCUMENT STATUS": {
                waitForList(documentStatusList);
                for (WebElement el : documentStatusList) {
                    scrollToElement(el);
                    value = getText(el);
                }
                break;
            }
        }
        return value;
    }

    @Step("Select checkbox")
    public void selectCheckbox() {
        waitForElementToBeClickable(checkboxesList.get(checkboxesList.size() - 1));
        click(checkboxesList.get(checkboxesList.size() - 1));
    }
}
