package btrust.btrustOne.admin.workflowManagement.triggersPage.pageObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class TriggerInformationPage extends BasePage {

    public TriggerInformationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".NewTriggerInformation-module__formContainer__wde7A .DocumentFormTitle-module__title__OrQX-")
    protected WebElement pageTitle;
    @FindBy(css = ".NewTriggerInformation-module__label__Q9dOw ,.NewTriggerInformation-module__recuranceTitle__hVH19")
    protected List<WebElement> titleFieldNameList;
    @FindBy(css = ".NewTriggerInformation-module__inputItem__3CtDK [name='triggerName']")
    protected WebElement triggerNameInputField;
    @FindBy(css = ".NewTriggerInformation-module__inputItem__3CtDK [name='description']")
    protected WebElement descriptionInputField;
    @FindBy(css = ".Reccurance-module__recuranceContainer__2MqMH>:nth-child(2) input")
    protected WebElement numberInputField;
    @FindBy(css = ".default__menu-list > .default__option")
    protected List<WebElement> periodList;
    @FindBy(css = ".react-datepicker__time-list>.react-datepicker__time-list-item")
    protected List<WebElement> timeList;
    @FindBy(css = ".Reccurance-module__checkbox__3uf7I [type='checkbox']")
    protected WebElement checkbox;
    @FindBy(css = ".BottomStickyActions-module__wrapper__1b1UJ> button[type='button']")
    protected List<WebElement> buttonList;
    @FindBy(css = ".DateSelector_container > button")
    protected WebElement caseDueDateSelect;
    @FindBy(css = ".calendar-header > .month")
    protected WebElement month;
    @FindBy(css = ".calendar-header > .btn.next")
    protected WebElement nextMonthButton;
    @FindBy(css = ".react-datepicker__month .react-datepicker__day")
    protected List<WebElement> dayList;
    @FindBy(css = ".react-datepicker__month .react-datepicker__day--today")
    protected WebElement todayDate;
    @FindBy(css = ".NewTriggerInformation-module__form__3KD70 input[name='triggerName']")
    protected WebElement attributeName;
    @FindBy(css = ".Reccurance-module__numberContainer__3dH9B input")
    protected WebElement numberFieldIsEnabled;
    @FindBy(css = ":nth-child(3) .default__input>:nth-child(1)")
    protected WebElement periodFieldIsEnabled;
    @FindBy(css = ".react-datepicker__input-container >input[type='text']")
    protected WebElement hourFieldIsEnabled;
    @FindBy(css = ".css-1hwfws3,.react-datepicker__input-container,.NewTriggerInformation-module__formWrapper__3CKNz .ompleteForm2-module__recuranceContainer__2MqMH  input[pattern]")
    protected List<WebElement> inputFieldList;


    @Step("Page Title")
    public String pageTitle() {
        waitForPageFinishLoading();
        return getText(pageTitle);
    }


    @Step("Return the attribute name for trigger name")
    public String attributeNameForTriggerName() {
        waitForElementToBeVisibility(attributeName);
        return getAttribute(attributeName, "value");
    }


    @Step("Select trigger information")
    public void fillTextNewTriggerInformation(String fieldTitleName, String fieldName, String triggerName, boolean future, String chooseDay, String period) {
        for (WebElement el : titleFieldNameList) {
            scrollToElement(el);
            if (getText(el).equalsIgnoreCase(fieldTitleName)) {
                switch (fieldName) {
                    case "TriggerName": {
                        waitForElementToBeClickable(triggerNameInputField);
                        fillText(triggerNameInputField, triggerName);
                        break;
                    }
                    case "ActivationDate": {
                        if (future) {
                            waitForElementToBeClickable(caseDueDateSelect);
                            click(caseDueDateSelect);
                            waitForElementToBeClickable(nextMonthButton);
                            click(nextMonthButton);
                            if (month != null) {
                                for (WebElement ele : dayList) {
                                    if (ele.getText().equalsIgnoreCase(chooseDay)) {
                                        waitForElementToBeClickable(ele);
                                        click(ele);
                                        return;
                                    }
                                }
                            }
                            break;
                        } else {
                            waitForElementToBeClickable(caseDueDateSelect);
                            click(caseDueDateSelect);
                            if (month != null) {
                                scrollToElement(todayDate);
                                waitForElementToBeClickable(todayDate);
                                click(todayDate);
                                return;
                            }
                        }
                        break;
                    }
                    case "Description": {
                        scrollToElement(descriptionInputField);
                        waitForElementToBeClickable(descriptionInputField);
                        fillText(descriptionInputField, "description field test");
                        break;
                    }
                    case "TriggerRecurrence": {
                        periodManger(period);
                        break;
                    }

                }

            }
        }
    }

    public void periodManger(String periodType) {
        List<String> listOfDay = new ArrayList<>();
        listOfDay.add("1");
        listOfDay.add(periodType);
        listOfDay.add("4:00 AM");

        List<String> listOfWeek = new ArrayList<>();
        listOfWeek.add("7");
        listOfWeek.add(periodType);
        listOfWeek.add("Sunday");
        listOfWeek.add("4:00 AM");

        List<String> listOfMonth = new ArrayList<>();
        listOfMonth.add("10");
        listOfMonth.add(periodType);
        listOfMonth.add("1");
        listOfMonth.add("4:00 AM");

        List<String> listOfYear = new ArrayList<>();
        listOfYear.add("2");
        listOfYear.add(periodType);
        listOfYear.add("February");
        listOfYear.add("1");
        listOfYear.add("4:00 AM");

        switch (periodType) {
            case "Day": {
                scrollToElement(inputFieldList.get(0));
                waitForElementToBeClickable(inputFieldList.get(0));
                fillText(numberInputField, listOfDay.get(0));

                scrollToElement(inputFieldList.get(0));
                waitForElementToBeClickable(inputFieldList.get(0));
                click(inputFieldList.get(0));
                chooseValueFromList(listOfDay.get(1));

                waitForElementToBeClickable(inputFieldList.get(1));
                click(inputFieldList.get(1));
                chooseTimeFromList(listOfDay.get(2));
                break;
            }


            case "Week": {
                scrollToElement(inputFieldList.get(0));
                fillText(numberInputField, listOfWeek.get(0));

                mouseHoverOnElement(inputFieldList.get(1));
                waitForElementToBeClickable(inputFieldList.get(0));
                click(inputFieldList.get(0));
                chooseValueFromList(listOfWeek.get(1));

                waitForElementToBeClickable(inputFieldList.get(1));
                scrollToElement(inputFieldList.get(1));
                click(inputFieldList.get(1));
                chooseValueFromList(listOfWeek.get(2));


                waitForElementToBeClickable(inputFieldList.get(2));
                scrollToElement(inputFieldList.get(2));
                click(inputFieldList.get(2));
                chooseTimeFromList(listOfWeek.get(3));

                break;
            }

            case "Month": {
                scrollToElement(inputFieldList.get(0));
                waitForElementToBeClickable(inputFieldList.get(0));
                fillText(numberInputField, listOfMonth.get(0));

                waitForElementToBeClickable(inputFieldList.get(0));
                scrollToElement(inputFieldList.get(0));
                click(inputFieldList.get(0));
                chooseValueFromList(listOfMonth.get(1));


                waitForElementToBeClickable(inputFieldList.get(1));
                scrollToElement(inputFieldList.get(1));
                click(inputFieldList.get(1));
                chooseNumberFromList(listOfMonth.get(2));


                waitForElementToBeClickable(inputFieldList.get(2));
                click(inputFieldList.get(2));
                chooseTimeFromList(listOfMonth.get(3));

                break;
            }

            case "Year": {
                scrollToElement(inputFieldList.get(0));
                waitForElementToBeClickable(inputFieldList.get(0));
                fillText(numberInputField, listOfYear.get(0));

                waitForElementToBeClickable(inputFieldList.get(1));
                scrollToElement(inputFieldList.get(1));
                click(inputFieldList.get(1));
                chooseValueFromList(listOfYear.get(1));


                waitForElementToBeClickable(inputFieldList.get(2));
                click(inputFieldList.get(2));
                chooseValueFromList(listOfYear.get(2));


                waitForElementToBeClickable(inputFieldList.get(3));
                click(inputFieldList.get(3));
                chooseValueFromList(listOfYear.get(3));


                waitForElementToBeClickable(inputFieldList.get(4));
                click(inputFieldList.get(4));
                chooseTimeFromList(listOfYear.get(4));

                break;
            }
        }
    }


    public void chooseValueFromList(String value) {
        waitForPageFinishLoading();
       WebElement el = driver.findElement(By.xpath(String.format("//*[text()='%s']", value)));
       click(el);
    }


    public void chooseNumberFromList(String value) {
        waitForPageFinishLoading();
        List<WebElement> el = driver.findElements(By.xpath(String.format("//*[text()='%s']", value)));
        click(el.get(1));
    }


    public void chooseTimeFromList(String value) {
        for (WebElement el : timeList) {
            scrollToElement(el);
            if (getText(el).equalsIgnoreCase(value)) {
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
    }

    @Step("click on checkbox")
    public void clickOnCheckbox() {
        scrollToElement(checkbox);
        click(checkbox);
    }

    @Step("click on Button")
    public void clickOnButton(String buttonName) {
        waitForPageFinishLoading();
        waitForList(buttonList);
        for (WebElement webElement : buttonList) {
            scrollToElement(webElement);
            if (getText(webElement).equalsIgnoreCase(buttonName)) {
                clickByJS(webElement);
                break;
            }
        }
    }


    @Step("Check Navigation Buttons")
    public boolean checkNavigationButtons(String buttonName) {
        boolean bol = false;
        waitForPageFinishLoading();
        waitForList(buttonList);
        for (WebElement webElement : buttonList) {
            scrollToElement(webElement);
            if (getText(webElement).equalsIgnoreCase(buttonName)) {
                bol = isEnabled(webElement);
                return bol;
            }
        }
        return bol;
    }


    @Step("Check Trigger Recurrence Fields")
    public boolean checkTriggerRecurrenceFields(String fieldTitleName, String fieldType) {
        boolean bol = false;
        for (WebElement webElement : titleFieldNameList) {
            scrollToElement(webElement);
            if (getText(webElement).equalsIgnoreCase(fieldTitleName)) {
                switch (fieldType) {
                    case "Number": {
                        scrollToElement(numberFieldIsEnabled);
                        bol = isEnabled(numberFieldIsEnabled);
                        break;
                    }
                    case "Period": {
                        scrollToElement(periodFieldIsEnabled);
                        bol = isEnabled(periodFieldIsEnabled);
                        break;
                    }
                    case "Time": {
                        scrollToElement(hourFieldIsEnabled);
                        bol = isEnabled(hourFieldIsEnabled);
                        break;
                    }
                }
                break;
            }

        }
        System.out.println(bol);
        return bol;
    }


    @Step("Check ompleteForm2 Fields")
    public boolean checkTriggerReccuranceFields111(String fieldTitleName, String fieldType) {
        boolean bol = false;
        for (WebElement webElement : titleFieldNameList) {
            scrollToElement(webElement);
            if (getText(webElement).equalsIgnoreCase(fieldTitleName)) {
                switch (fieldType) {
                    case "Number": {
                        scrollToElement(numberFieldIsEnabled);
                        bol = isEnabled(numberFieldIsEnabled);
                        break;
                    }
                    case "Period": {
                        scrollToElement(periodFieldIsEnabled);
                        bol = isEnabled(periodFieldIsEnabled);
                        break;
                    }
                    case "Time": {
                        scrollToElement(hourFieldIsEnabled);
                        bol = isEnabled(hourFieldIsEnabled);
                        break;
                    }
                }
                break;
            }

        }
        System.out.println(bol);
        return bol;
    }
}








