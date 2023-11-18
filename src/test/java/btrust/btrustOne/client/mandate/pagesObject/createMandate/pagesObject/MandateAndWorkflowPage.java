package btrust.btrustOne.client.mandate.pagesObject.createMandate.pagesObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Random;

public class MandateAndWorkflowPage extends BasePage {
    public MandateAndWorkflowPage(WebDriver driver) {
        super(driver);
    }

    protected String value;
    protected String relationshipValue;
    protected String randomValue;

    @FindBy(css = ".Title-module__title__4TKNP > .Title-module__text__CK5Le")
    protected WebElement mandateInformationTitle;
    @FindBy(css = "[name='relationNumber']")
    protected WebElement relationshipNumberField;
    @FindBy(css = ".MandateAndWorkflow-module__container__32H56 .default-select")
    protected List<WebElement> selectsList;
    @FindBy(css = ".MandateAndWorkflow-module__container__32H56 > .SelectItem-module__inputItem__kjSQh")
    protected List<WebElement> selectTitlesList;
    @FindBy(css = ".default__menu-list > .default__option")
    protected List<WebElement> optionsFromList;
    @FindBy(css = ".default__single-value.css-1uccc91-singleValue")
    protected List<WebElement> inputData;

    @FindBy(css = "[name='relationNumber'],.default__single-value.css-1uccc91-singleValue ,.DateSelector_container > button")
    protected List<WebElement> mandateFieldsValue;
    @FindBy(css = "div>.MandateAndWorkflow-module__container__32H56>div>:nth-child(1)")
    protected List<WebElement> mandateTitle;


    /*
    case due date elements
    */
    @FindBy(css = ".DateSelector_container > button")
    protected WebElement caseDueDateSelect;
    @FindBy(css = ".calendar-header > .month")
    protected WebElement month;
    @FindBy(css = ".react-datepicker__month .react-datepicker__day")
    protected List<WebElement> dayList;
    @FindBy(css = ".calendar-header > .btn.next")
    protected WebElement nextMonthButton;


    /*
    list for all the buttons that display on the screen
     */
    @FindBy(css = ".BottomStickyActions-module__wrapper__1b1UJ>[type='button']")
    protected List<WebElement> buttonsList;

    /*
    error message
     */
    @FindBy(css = ".SelectItem-module__inputItem__kjSQh > .ValidationError-module__container__1w8Qt")
    protected List<WebElement> errorMessageUnderSelects;
    @FindBy(css = ".InputItem-module__inputItem__O3lw9 > .ValidationError-module__container__1w8Qt")
    protected WebElement errorMessageRelationshipNumberField;
    @FindBy(css = ".DateItem-module__inputItem__34rJ7 > .ValidationError-module__container__1w8Qt")
    protected WebElement errorMessageUnderDatePicker;


    @Step("Return the Mandate Information title")
    public void mandateInformationFields() {
        waitForPageFinishLoading();
        for (int i = 0; i < mandateTitle.size(); i++) {
            if (!getText(mandateTitle.get(i)).equalsIgnoreCase("Relationship Number")) {
                waitForElementToBeVisibility(mandateFieldsValue.get(i));
                String text = getText(mandateFieldsValue.get(i));
                System.out.println(text);
            } else {
                waitForElementToBeVisibility(relationshipNumberField);
                System.out.println(getAttribute(relationshipNumberField, "value"));
            }
        }
    }


    @Step("Return the Mandate Information title")
    public String mandateInformationTitle() {
        waitForElementToBeVisibility(mandateInformationTitle);
        return getText(mandateInformationTitle);
    }

    @Step("Type in relationship number field: {value}")
    public void fillValueInRelationshipNumberField(String value) {
        waitForElementToBeClickable(relationshipNumberField);
        fillText(relationshipNumberField, value);
    }

    @Step("Return the value from relationship number fields")
    public String getRelationshipValue() {
        waitForElementToBeVisibility(relationshipNumberField);
        relationshipValue = getAttribute(relationshipNumberField, "value");
        return relationshipValue;
    }

    @Step("Open select: {select} and choose option from the list: {option}")
    public void chooseOptionFromSelect(String select, String option) {
        waitForList(selectTitlesList);
        for (int i = 0; i < selectTitlesList.size(); i++) {
            WebElement title = selectTitlesList.get(i);
            if (getText(title).contains(select)) {
                click(selectsList.get(i));
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
        for (WebElement inputDatum : inputData) {
            System.out.println(getText(inputDatum));
        }
    }

    @Step("Return the value of month")
    public String month() {
        waitForElementToBeVisibility(month);
        return getText(month);
    }

    @Step("Open date picker and choose date")
    public void chooseDueDate(String chooseMonth, String chooseDay) {
        waitForElementToBeClickable(caseDueDateSelect);
        click(caseDueDateSelect);
        while (month() != null) {
            if (getText(month).equalsIgnoreCase(chooseMonth)) {
                for (WebElement el : dayList) {
                    System.out.println(getText(el));
                    if (getText(el).equalsIgnoreCase(chooseDay)) {
                        waitForElementToBeClickable(el);
                        el.click();
                        break;
                    }
                }
                break;
            } else {
                click(nextMonthButton);
            }
        }
    }

    @Step("Click on button: {button}")
    public void chooseButton(String button) {
        waitForPageFinishLoading();
        waitForList(buttonsList);
        sleep(10000);
        for (WebElement el : buttonsList) {
            scrollToElement(el);
            if (getText(el).equalsIgnoreCase(button)) {
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
    }

    @Step("Return the error message that displayed under relationship number field")
    public String errorMessageRelationshipNumberField() {
        waitForElementToBeVisibility(errorMessageRelationshipNumberField);
        return getText(errorMessageRelationshipNumberField);
    }

    @Step("Return the error message that displayed under select: {select}")
    public String errorMessageUnderSelect(String select) {
        String errorMessage = null;
        waitForList(selectTitlesList);
        for (int i = 0; i < selectTitlesList.size(); i++) {
            WebElement title = selectTitlesList.get(i);
            String s = getText(title);
            if (s.contains(select)) {
                WebElement error = errorMessageUnderSelects.get(i);
                errorMessage = getText(error);
                break;
            }
        }
        return errorMessage;
    }

    @Step("Return the error message that displayed under Case Due Date field")
    public String errorMessageUnderDatePicker() {
        waitForElementToBeVisibility(errorMessageUnderDatePicker);
        return getText(errorMessageUnderDatePicker);
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
}