package btrust.btrustOne.client.mandate.pagesObject.createMandate.pagesObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ContactInformationPage extends BasePage {
    public ContactInformationPage(WebDriver driver) {
        super(driver);
    }

    protected String value;
    boolean cardIsDisplayed;

    @FindBy(css = ".Title-module__title__4TKNP > .Title-module__text__CK5Le")
    protected WebElement contactsTitle;
    @FindBy(css = ".ContactItemEditable-module__container__2luqN > .ContactItemEditable-module__title__1mcJs")
    protected List<WebElement> cardTitleList;
    @FindBy(css = ".ContactItemEditable-module__container__2luqN > .ContactItemEditable-module__description__2Opw6")
    protected List<WebElement> cardDescriptionList;
    @FindBy(css = ".ContactsTable-module__container__1RXiD .MuiButtonBase-root.MuiIconButton-root")
    protected List<WebElement> contactCheckboxesList;
    @FindBy(css = ".Title-module__title__4TKNP > .Title-module__description__1DoDl")
    protected WebElement exitContactCardDescription;
    /*
    Entity and Mandate’s Contact person card
     */
    @FindBy(css = ".ContactInformation-module__container__2BjBS > :nth-child(1) .InputItem-module__inputItem__O3lw9 > input")
    protected List<WebElement> defaultCardInputsList;
    @FindBy(css = ".ContactInformation-module__container__2BjBS > :nth-child(1) .InputItem-module__inputItem__O3lw9 > .InputItem-module__label__yxQ9D")
    protected List<WebElement> defaultCardInputTitlesList;
    @FindBy(css = ".ContactInformation-module__container__2BjBS > :nth-child(1) .PhoneItem-module__inputItem__10bbz > .PhoneItem-module__label__2lTwl")
    protected List<WebElement> defaultCardCallsTitles;
    @FindBy(css = ".ContactInformation-module__container__2BjBS > :nth-child(1) .custom-phone-input .selected-flag")
    protected List<WebElement> defaultCardAreaCodeSelects;
    @FindBy(css = ".ContactInformation-module__container__2BjBS > :nth-child(1) .country-list > li")
    protected List<WebElement> defaultCardAreaCodeList;
    @FindBy(css = ".ContactInformation-module__container__2BjBS > :nth-child(1) .custom-phone-input > input")
    protected List<WebElement> defaultCardCallsField;
    /*
    Mandate's Contact card
     */
    @FindBy(css = ".ContactInformation-module__container__2BjBS > :nth-child(2) .InputItem-module__inputItem__O3lw9 > input")
    protected List<WebElement> addContactCardInputsList;
    @FindBy(css = ".ContactInformation-module__container__2BjBS > :nth-child(2) .InputItem-module__inputItem__O3lw9 > .InputItem-module__label__yxQ9D")
    protected List<WebElement> addContactCardInputTitlesList;
    @FindBy(css = ".ContactInformation-module__container__2BjBS > :nth-child(2) .PhoneItem-module__inputItem__10bbz > .PhoneItem-module__label__2lTwl")
    protected List<WebElement> addContactCardCallsTitles;
    @FindBy(css = ".ContactInformation-module__container__2BjBS > :nth-child(2) .custom-phone-input .selected-flag")
    protected List<WebElement> addContactCardAreaCodeSelects;
    @FindBy(css = ".ContactInformation-module__container__2BjBS > :nth-child(2) .country-list > li")
    protected List<WebElement> addContactCardAreaCodeList;
    @FindBy(css = ".ContactInformation-module__container__2BjBS > :nth-child(2) .custom-phone-input > input")
    protected List<WebElement> addContactCardCallsField;
    /*
        list for all the buttons that display on the screen
    */
    @FindBy(css = ".PageWrapper-module__wrapper__JFJiY button")
    protected List<WebElement> buttonsList;
    /*
        error message in default card
     */
    @FindBy(css = ".ContactInformation-module__container__2BjBS > :nth-child(1) .InputItem-module__inputItem__O3lw9 > .ValidationError-module__container__1w8Qt")
    protected List<WebElement> defaultCardErrorMessageUnderInputs;
    @FindBy(css = ".ContactInformation-module__container__2BjBS > :nth-child(1) .PhoneItem-module__inputItem__10bbz > .ValidationError-module__container__1w8Qt")
    protected List<WebElement> defaultCardErrorMessageUnderCallFields;


    @Step("Return the contacts title")
    public String contactsTitle(String title) {
        waitForTextToBeInElement(contactsTitle, title);
        return getText(contactsTitle);
    }

    @Step("Return the card title: {title}")
    public String cardTitle(String title) {
        String cardTitle = null;
        waitForList(cardTitleList);
        for (WebElement el : cardTitleList) {
            if (getText(el).equalsIgnoreCase(title)) {
                waitForTextToBeInElement(el, title);
                scrollToElement(el);
                cardTitle = getText(el);
                break;
            }
        }
        return cardTitle;
    }

    @Step("Return the card description: {description}")
    public String cardDescription(String description) {
        String cardDescription = null;
        waitForList(cardDescriptionList);
        for (WebElement el : cardDescriptionList) {
            if (getText(el).contains(description)) {
                waitForElementToBeVisibility(el);
                cardDescription = getText(el);
                break;
            }
        }
        return cardDescription;
    }

    @Step("Check if contact card is displayed")
    public boolean contactCardIsDisplayed(String title) {
        try {
            cardIsDisplayed = Boolean.parseBoolean(cardTitle(title));
            return cardIsDisplayed;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Type value: {value} in filed: {title}")
    public void defaultCardTypeInField(String title, String value) {
        waitForList(defaultCardInputTitlesList);
        for (int x = 0; x <= defaultCardInputTitlesList.size(); x++) {
            WebElement el = defaultCardInputTitlesList.get(x);
            if (getText(el).equalsIgnoreCase(title)) {
                WebElement input = defaultCardInputsList.get(x);
                fillText(input, value);
                break;
            }
        }
    }

    @Step("Fill call number")
    public void defaultCardFillCallNumber(String title, String areaCode, String number) {
        waitForList(defaultCardCallsTitles);
        for (int i = 0; i <= defaultCardCallsTitles.size(); i++) {
            WebElement el = defaultCardCallsTitles.get(i);
            if (getText(el).contains(title)) {
                WebElement areaCodeSelect = defaultCardAreaCodeSelects.get(i);
                waitForElementToBeClickable(areaCodeSelect);
                click(areaCodeSelect);
                for (WebElement element : defaultCardAreaCodeList) {
                    if (getText(element).contains(areaCode)) {
                        waitForElementToBeClickable(element);
                        clickByJS(element);
                        break;
                    }
                }
                waitForList(defaultCardCallsField);
                WebElement callField = defaultCardCallsField.get(i);
                waitForElementToBeVisibility(callField);
                clear(callField);
                fillText(callField, number);
                break;
            }
        }
    }

    @Step("Type value: {value} in filed: {title}")
    public void addContactCardTypeInField(String title, String value) {
        waitForList(addContactCardInputTitlesList);
        for (int x = 0; x <= addContactCardInputTitlesList.size(); x++) {
            WebElement el = addContactCardInputTitlesList.get(x);
            if (getText(el).equalsIgnoreCase(title)) {
                WebElement input = addContactCardInputsList.get(x);
                scrollToElement(input);
                fillText(input, value);
                break;
            }
        }
    }

    @Step("Fill call number")
    public void addContactCardFillCallNumber(String title, String areaCode, String number) {
        waitForList(addContactCardCallsTitles);
        for (int i = 0; i <= addContactCardCallsTitles.size(); i++) {
            WebElement el = addContactCardCallsTitles.get(i);
            if (getText(el).contains(title)) {
                WebElement areaCodeSelect = addContactCardAreaCodeSelects.get(i);
                waitForElementToBeClickable(areaCodeSelect);
                click(areaCodeSelect);
                for (WebElement element : addContactCardAreaCodeList) {
                    if (getText(element).contains(areaCode)) {
                        waitForElementToBeClickable(element);
                        scrollToElement(element);
                        clickByJS(element);
                        break;
                    }
                }
                break;
            }
            WebElement callField = addContactCardCallsField.get(i);
            waitForElementToBeVisibility(callField);
            scrollToElement(callField);
            fillText(callField, number);
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

    @Step("Check if button is enabled")
    public boolean buttonIsEnabled(String button) {
        boolean buttonIsEnabled = true;
        waitForList(buttonsList);
        for (WebElement el : buttonsList) {
            if (getText(el).contains(button)) {
                buttonIsEnabled = isEnabled(el);
                break;
            }
        }
        return buttonIsEnabled;
    }

    @Step("Return the error message that displayed under input: {input}")
    public String errorMessageUnderInputs(String input) {
        String errorMessage = null;
        waitForList(defaultCardInputTitlesList);
        try {
            for (int i = 0; i < defaultCardInputTitlesList.size(); i++) {
                WebElement title = defaultCardInputTitlesList.get(i);
                if (getText(title).contains(input)) {
                    waitForList(defaultCardErrorMessageUnderInputs);
                    WebElement error = defaultCardErrorMessageUnderInputs.get(i);
                    waitForElementToBeVisibility(error);
                    errorMessage = getText(error);
                    break;
                }

            }
        } catch (Exception e) {
            for (int i = 0; i < defaultCardInputTitlesList.size(); i++) {
                WebElement title = defaultCardInputTitlesList.get(defaultCardInputTitlesList.size() - 1);
                if (getText(title).contains(input)) {
                    waitForList(defaultCardErrorMessageUnderInputs);
                    WebElement error = defaultCardErrorMessageUnderInputs.get(defaultCardErrorMessageUnderInputs.size() - 1);
                    waitForElementToBeVisibility(error);
                    errorMessage = getText(error);
                    break;
                }
            }
        }
        return errorMessage;
    }

    @Step("Return the error message that displayed under select: {select}")
    public String errorMessageUnderSelect(String select) {
        String errorMessage = null;
        waitForList(defaultCardCallsTitles);
        for (int i = 0; i < defaultCardCallsTitles.size(); i++) {
            WebElement title = defaultCardCallsTitles.get(i);
            if (getText(title).contains(select)) {
                waitForList(defaultCardErrorMessageUnderCallFields);
                WebElement error = defaultCardErrorMessageUnderCallFields.get(i);
                waitForElementToBeVisibility(error);
                errorMessage = getText(error);
                break;
            }
        }
        return errorMessage;
    }

    @Step("Select the first contact from the list")
    public void selectFirstContact() {
        waitForElementToBeClickable(contactCheckboxesList.get(0));
        click(contactCheckboxesList.get(0));
    }

    @Step("Return the description of exist contact card")
    public String exitContactDescription() {
        waitForElementToBeVisibility(exitContactCardDescription);
        return getText(exitContactCardDescription);
    }
}