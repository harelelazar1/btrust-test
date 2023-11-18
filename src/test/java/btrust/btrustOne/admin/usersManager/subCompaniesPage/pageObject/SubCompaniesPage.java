package btrust.btrustOne.admin.usersManager.subCompaniesPage.pageObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SubCompaniesPage extends BasePage {
    public SubCompaniesPage(WebDriver driver) {
        super(driver);
    }

    String subCompanyInformation;
    boolean buttonIsEnabled;

    @FindBy(css = ".SettingsHeader-module__wrapper__2OOUN > .SettingsHeader-module__title__acJ6f")
    protected WebElement subCompaniesTitle;
    @FindBy(css = ".SettingsHeader-module__title__acJ6f > button")
    protected WebElement addSubCompanyButton;
    @FindBy(css = ".InputSearch-module__inputItem__2Oj7R > input")
    protected WebElement searchField;
    @FindBy(css = ".TableHeader-module__conteiner__wJuGi > .TableHeader-module__headCell__1gGeq")
    protected List<WebElement> subCompaniesTableTitles;
    @FindBy(css = ".Table-module__wrapper__2ZU-8 > :nth-child(1) > .Table-module__item__ivs2a")
    protected List<WebElement> firstSubCompanyInformation;
    /*
    New Sub Company elements
     */
    @FindBy(css = ".PopupTitle-module__title__Khvfk > .PopupTitle-module__text__3pjTU")
    protected WebElement newSubCompanyTitle;
    @FindBy(css = ".SubCompanyPopup-module__inputItem__2hhux > .SubCompanyPopup-module__label__lrvyi")
    protected List<WebElement> inputTitles;
    @FindBy(css = ".SubCompanyPopup-module__inputItem__2hhux > input")
    protected List<WebElement> inputFields;
    @FindBy(css = ".default-custom-selector > .default-select")
    protected WebElement selectCountry;
    @FindBy(css = ".default__menu-list > .default__option")
    protected List<WebElement> countriesList;
    @FindBy(css = ".PopupBottomButtons-module__container__20krY > button")
    protected List<WebElement> buttonsList;
    @FindBy(css = ".PopupTitle-module__text__3pjTU > .PopupTitle-module__icon__hcL5f")
    protected WebElement closePopupButton;


    @Step("Return the text of sub companies title")
    public String subCompaniesTitle() {
        waitForElementToBeVisibility(subCompaniesTitle);
        return getText(subCompaniesTitle);
    }

    @Step("Type in search field")
    public void searchField(String value) {
        waitForPageFinishLoading();
        scrollToElement(searchField);
        waitForElementToBeClickable(searchField);
        fillText(searchField, value);
    }

    @Step("Return the information on the first sub company that appear in the sub company table")
    public String subCompanyInformation(String title, String subCompany) {
        waitForPageFinishLoading();
        waitForElementToBeVisibility(subCompaniesTableTitles.get(0));
        for (int i = 0; i <= subCompaniesTableTitles.size(); i++) {
            scrollToElement(subCompaniesTableTitles.get(i));
            if (getText(subCompaniesTableTitles.get(i)).equalsIgnoreCase(title)) {
                if (getText(firstSubCompanyInformation.get(i)).equalsIgnoreCase(subCompany)) {
                    scrollToElement(firstSubCompanyInformation.get(i));
                    waitForElementToBeVisibility(firstSubCompanyInformation.get(i));
                    subCompanyInformation = getText(firstSubCompanyInformation.get(i));
                    break;
                }
            }
        }
        return subCompanyInformation;
    }

    @Step("Click on add new sub company button")
    public void clickOnAddSubCompanyButton() {
        waitForPageFinishLoading();
        waitForElementToBeClickable(addSubCompanyButton);
        click(addSubCompanyButton);
    }


    @Step("Check if add new sub company button display")
    public boolean checkIfAddSubCompanyButtonDisplay() {
        try {
            waitForPageFinishLoading();
            if (isDisplayed(addSubCompanyButton)) {
                scrollToElement(addSubCompanyButton);
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
        return false;
    }


    @Step("Click on close popup button")
    public void closePopupButton() {
        waitForElementToBeClickable(closePopupButton);
        click(closePopupButton);
    }

    @Step("Return the text of new sub company title")
    public String newSubCompanyTitle() {
        waitForElementToBeVisibility(newSubCompanyTitle);
        return getText(newSubCompanyTitle);
    }

    @Step("Fill value in filed")
    public void fillInFiled(String title, String value) {
        waitForList(inputTitles);
        for (int i = 0; i <= inputTitles.size(); i++) {
            if (getText(inputTitles.get(i)).equalsIgnoreCase(title)) {
                waitForList(inputFields);
                waitForElementToBeVisibility(inputFields.get(i));
                fillText(inputFields.get(i), value);
                break;
            }
        }
    }

    @Step("Select county")
    public void selectCountry(String country) {
        waitForElementToBeClickable(selectCountry);
        click(selectCountry);
        for (WebElement el : countriesList) {
            if (getText(el).equalsIgnoreCase(country)) {
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
    }

    @Step("Click on button")
    public void clickOnButton(String button) {
        waitForList(buttonsList);
        for (WebElement el : buttonsList) {
            scrollToElement(el);
            if (getText(el).equalsIgnoreCase(button)) {
                if (isEnabled(el)) {
                    waitForElementToBeClickable(el);
                    click(el);
                    break;
                }
            }
        }
    }

    @Step("Check if button is enabled")
    public boolean buttonIsEnabled(String button) {
        buttonIsEnabled = true;
        waitForList(buttonsList);
        for (WebElement el : buttonsList) {
            if (getText(el).contains(button)) {
                buttonIsEnabled = isEnabled(el);
                break;
            }
        }
        return buttonIsEnabled;
    }

    @Step("Return the value that appear in the sub company name field")
    public String valueOfSubCompanyNameField() {
        waitForElementToBeVisibility(inputFields.get(0));
        return getAttribute(inputFields.get(0), "value");
    }
}
