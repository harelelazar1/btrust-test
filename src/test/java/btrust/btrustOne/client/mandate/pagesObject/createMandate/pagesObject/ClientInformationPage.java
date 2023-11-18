package btrust.btrustOne.client.mandate.pagesObject.createMandate.pagesObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Random;

public class ClientInformationPage extends BasePage {
    public ClientInformationPage(WebDriver driver) {
        super(driver);
    }

    protected String value;
    protected String randomValue;

    @FindBy(css = ".Title-module__title__4TKNP > .Title-module__text__CK5Le")
    protected WebElement clientInformationTitle;
    @FindBy(css = ".ClientInformation-module__container__1b2DC .default-select")
    protected List<WebElement> selectsList;
    @FindBy(css = ".ClientInformation-module__container__1b2DC .SelectItem-module__label__-_6h_")
    protected List<WebElement> selectTitlesList;
    @FindBy(css = ".default__menu-list > .default__option")
    protected List<WebElement> optionsFromList;
    @FindBy(css = ".InputItem-module__inputItem__O3lw9 > input")
    protected List<WebElement> inputsList;
    @FindBy(css = ".InputItem-module__inputItem__O3lw9 > .InputItem-module__label__yxQ9D")
    protected List<WebElement> inputTitlesList;
    @FindBy(css = ".custom-phone-input .selected-flag")
    protected WebElement areaCodeSelect;
    @FindBy(css = ".country-list > li")
    protected List<WebElement> areaCodeList;
    @FindBy(css = ".custom-phone-input > input")
    protected WebElement businessPhoneField;
    @FindBy(css = ".ClientInformation-module__questions__1UNqz > .ClientInformation-module__item__1BwPR")
    protected List<WebElement> legalNatureOfTheClientOptions;
    @FindBy(css = "input[name='legalName']")
    protected WebElement legalNameField;

    /*
     list for all the buttons that display on the screen
    */
    @FindBy(css = ".PageWrapper-module__wrapper__JFJiY button")
    protected List<WebElement> buttonsList;

    /*
     error message
    */
    @FindBy(css = ".SelectItem-module__inputItem__kjSQh > .ValidationError-module__container__1w8Qt")
    protected List<WebElement> errorMessageUnderSelects;
    @FindBy(css = ".InputItem-module__inputItem__O3lw9 > .ValidationError-module__container__1w8Qt")
    protected List<WebElement> errorMessageUnderInputs;
    @FindBy(css = ".ClientInformation-module__questionsContainer__3cCfs > .ValidationError-module__container__1w8Qt")
    protected WebElement errorMessageLegalNatureOfClient;
    @FindBy(css = ".PhoneItem-module__inputItem__10bbz > .ValidationError-module__container__1w8Qt")
    protected WebElement errorMessageBusinessPhone;


    @Step("Return the Client Information title")
    public String clientInformationTitle(String title) {
        waitForPageFinishLoading();
        scrollToElement(clientInformationTitle);
        System.out.println(getText(clientInformationTitle));
        waitForTextToBeInElement(clientInformationTitle, title);
        return getText(clientInformationTitle);
    }

    @Step("Type business phone")
    public void fillBusinessPhone(String areaCode, String phone) {
        waitForElementToBeClickable(areaCodeSelect);
        click(areaCodeSelect);
        for (WebElement el : areaCodeList) {
            if (getText(el).contains(areaCode)) {
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
        waitForElementToBeVisibility(businessPhoneField);
        clear(businessPhoneField);
        fillText(businessPhoneField, phone);
    }

    @Step("Type value: {value} in filed: {title}")
    public void typeInField(String title, String value) {
        waitForList(inputTitlesList);
        for (int i = 0; i <= inputTitlesList.size(); i++) {
            WebElement el = inputTitlesList.get(i);
            String t = getText(el);
            if (t.equalsIgnoreCase(title)) {
                WebElement input = inputsList.get(i);
                fillText(input, value);
                break;
            }
        }
    }

    @Step("Open select: {select} and choose option from the list: {option}")
    public void chooseOptionFromSelect(String select, String option) {
        waitForList(selectTitlesList);
        for (int i = 0; i < selectTitlesList.size(); i++) {
            WebElement title = selectTitlesList.get(i);
            scrollToElement(title);
            String s = getText(title);
            if (s.contains(select)) {
                WebElement btn = selectsList.get(i);
                click(btn);
                break;
            }
        }
        waitForList(optionsFromList);
        for (WebElement el : optionsFromList) {
            if (getText(el).equalsIgnoreCase(option)) {
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
    }

    @Step("Click on button: {button}")
    public void chooseButton(String button) {
        waitForList(buttonsList);
        for (WebElement el : buttonsList) {
            if (getText(el).equalsIgnoreCase(button)) {
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
    }

    @Step("Choose legal nature of the client: {option}")
    public void chooseLegalNatureOfTheClient(String option) {
        scrollToElement(legalNatureOfTheClientOptions.get(0));
        waitForList(legalNatureOfTheClientOptions);
        for (WebElement el : legalNatureOfTheClientOptions) {
            if (getText(el).equalsIgnoreCase(option)) {
                scrollToElement(el);
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
    }

    @Step("Return the error message that displayed under input: {input}")
    public String errorMessageUnderInputs(String input) {
        String errorMessage = null;
        waitForList(inputTitlesList);
        for (int i = 0; i < inputTitlesList.size(); i++) {
            WebElement title = inputTitlesList.get(i);
            String field = getText(title);
            if (field.contains(input)) {
                waitForList(errorMessageUnderInputs);
                WebElement error = errorMessageUnderInputs.get(i);
                waitForElementToBeVisibility(error);
                errorMessage = getText(error);
                break;
            }
        }
        return errorMessage;
    }

    @Step("Return the error message that displayed under select: {select}")
    public String errorMessageUnderSelect(String select) {
        String errorMessage = null;
        waitForList(selectTitlesList);
        for (int i = 0; i < selectTitlesList.size(); i++) {
            WebElement title = selectTitlesList.get(i);
            String s = getText(title);
            if (s.contains(select)) {
                waitForList(errorMessageUnderInputs);
                WebElement error = errorMessageUnderSelects.get(i);
                waitForElementToBeVisibility(error);
                errorMessage = getText(error);
                break;
            }
        }
        return errorMessage;
    }

    @Step("Return the error message that appear under legal nature of the client title")
    public String errorMessageLegalNatureOfClient() {
        waitForElementToBeVisibility(errorMessageLegalNatureOfClient);
        return getText(errorMessageLegalNatureOfClient);
    }

    @Step("Return the error message that appear under business phone field")
    public String errorMessageBusinessPhone() {
        waitForElementToBeVisibility(errorMessageBusinessPhone);
        return getText(errorMessageBusinessPhone);
    }

    @Step("create random string")
    public String randomString() {
        // create a string of all characters
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijkelmonpqrstuvwxyz1234567890";
        // create random string builder
        StringBuilder sb = new StringBuilder();
        // create an object of Random class
        Random random = new Random();
        // specify length of random string
        int length = 5;
        for (int i = 0; i < length; i++) {
            // generate random index number
            int index = random.nextInt(alphabet.length());
            // get character specified by index
            // from the string
            char randomChar = alphabet.charAt(index);
            // append the character to string builder
            sb.append(randomChar);
        }

        randomValue = sb.toString();
        return randomValue;
    }

    @Step("Return the value that appear in legal name field")
    public String legalNameValue() {
        waitForElementToBeVisibility(legalNameField);
        return getAttribute(legalNameField, "value");
    }
}