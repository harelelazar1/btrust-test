package btrust.btrustOne.client.mandate.pagesObject.createMandate.pagesObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ConfirmationPage extends BasePage {
    public ConfirmationPage(WebDriver driver) {
        super(driver);
    }

    protected String value;

    @FindBy(css = ".Title-module__title__4TKNP > .Title-module__text__CK5Le")
    protected WebElement confirmationTitle;
    @FindBy(css = ".Confirmation-module__title__1STQY > button")
    protected List<WebElement> editButtonsList;
    @FindBy(css = ".Confirmation-module__title__1STQY > .BlueTitle-module__title__1PV49")
    protected List<WebElement> cardTitlesList;
    @FindBy(css = ".Confirmation-module__flexContainer__1Lwub > .Confirmation-module__item__e_DQE > .Confirmation-module__label__2jrMd")
    protected List<WebElement> titles;
    @FindBy(css = ".Confirmation-module__flexContainer__1Lwub > .Confirmation-module__item__e_DQE > .Confirmation-module__value__Cd_B7")
    protected List<WebElement> values;
    @FindBy(css = ".Confirmation-module__description__2Zj2f > .Confirmation-module__bold__1GlbL")
    protected WebElement clientNameValue;
    @FindBy(css = ".Contacts-module__header__TsVJh > .Contacts-module__headCell__3FTAG")
    protected List<WebElement> contactInformationTitles;
    @FindBy(css = ".Contacts-module__table__2IjVP  .Contacts-module__item__3gxiG")
    protected List<WebElement> contactInformationValues;

    /*
        list for all the buttons that display on the screen
    */
    @FindBy(css = ".PageWrapper-module__wrapper__JFJiY button")
    protected List<WebElement> buttonsList;


    @Step("Return the 'We're Almost There' title")
    public String confirmationTile(String title) {
        waitForTextToBeInElement(confirmationTitle, title);
        return getText(confirmationTitle);
    }

    @Step("Click on edit button: {button}")
    public void chooseEditButton(String title, String button) {
        waitForList(cardTitlesList);
        for (int i = 0; i <= cardTitlesList.size(); i++) {
            WebElement cardTitle = cardTitlesList.get(i);
            if (getText(cardTitle).equalsIgnoreCase(title)) {
                waitForList(editButtonsList);
                WebElement editButton = editButtonsList.get(i);
                if (getText(editButton).equalsIgnoreCase(button)) {
                    waitForElementToBeClickable(editButton);
                    click(editButton);
                    break;
                }
            }
        }
    }

    @Step("Click on button: {button}")
    public void chooseButton(String button) {
        waitForList(buttonsList);
        for (WebElement el : buttonsList) {
            if (getText(el).contains(button)) {
                waitForElementToBeClickable(el);
                clickByJS(el);
                break;
            }
        }
    }

    @Step("Return the value: {value} that appear under the title : {title}")
    public String getValue(String title, String value) {
        for (int i = 0; i <= titles.size(); i++) {
            WebElement t = titles.get(i);
            if (getText(t).equalsIgnoreCase(title)) {
                waitForList(values);
                WebElement v = values.get(i);
                if (getText(v).contains(value)) {
                    waitForElementToBeVisibility(v);
                    value = getText(v);
                    break;
                }
            }
        }
        return value;
    }

    @Step("Return the value of client name")
    public String clientNameValue() {
        waitForElementToBeVisibility(clientNameValue);
        scrollToElement(clientNameValue);
        return getText(clientNameValue);
    }

    @Step("Return the value: {value} that appear under the title : {title}")
    public String getContactValue(String title, String value) {
        waitForList(contactInformationTitles);
        for (int i = 0; i <= contactInformationTitles.size(); i++) {
            WebElement t = contactInformationTitles.get(i);
            scrollToElement(t);
            if (getText(t).contains(title)) {
                WebElement v = contactInformationValues.get(i);
                if (getText(v).contains(value)) {
                    scrollToElement(v);
                    waitForElementToBeVisibility(v);
                    value = getText(v);
                    break;
                }
            }
        }
        return value;
    }
}