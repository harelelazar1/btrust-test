package btrust.btrustOne.admin.workflowManagement.mobileForms.pageObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FormPage extends BasePage {
    public FormPage(WebDriver driver) {
        super(driver);
    }

    String value;
    String value1;

    @FindBy(css = ".MobileFormsBuilderItem-module__container__addCard__3DVVk button")
    protected WebElement addCardButton;
    @FindBy(css = "div .FormCardSelectionModal-module__container__1-uIc button")
    protected List<WebElement> CardsTypeList;
    @FindBy(css = ".FormBuilderHeader-module__leftContainer__mainSection__3w6Yo > [type='button']")
    protected WebElement backButton;
    @FindBy(css = ".FormBuilderHeader-module__container__2Kul9>[type='button']")
    protected WebElement saveButton;
    @FindBy(css = "div.FormCardHeader-module__container__div__2d6-G input")
    protected List<WebElement> cardTitleNameList;
    @FindBy(css = "div.FormCardHeader-module__container__div__2d6-G input")
    protected WebElement cardTitleName;
    @FindBy(css = "div.FormCardHeader-module__container__div__2d6-G input")
    protected List<WebElement> cardTitleName1;
    @FindBy(css = ".FormCardFooter-module__container__actionFooter__31vQc :nth-child(2)")
    protected List<WebElement> deleteCardList;
    @FindBy(css = "ompleteForm2")
    protected WebElement startMessage;
    @FindBy(css = ".FormCardHeader-module__container__div__2d6-G .MuiSvgIcon-root")
    protected List<WebElement> editCardTitleNameList;
    @FindBy(css = "div .FormBuilderPreview-module__container__titleDiv__span__33AQR")
    protected WebElement previewScreen;
    @FindBy(css = "div.PopupTitle-module__text__3pjTU")
    protected WebElement popUpTitle;
    @FindBy(css = "div .DeletePopup-module__actions__31SVI button")
    protected List<WebElement> popUpButtons;

    @FindBy(css = ".InputItem-module__inputItem__1O8RA>div,div.SelectColorItem-module__label__1_y5-.SelectColorItem-module__required__385Cb")
    protected List<WebElement> inputTitleField;

    @FindBy(css = "div.InputItem-module__inputItem__1O8RA input,.SelectColorItem-module__inputsContainer__i9OUL  [type=\"text\"]")
    protected List<WebElement> inputField;

    @FindBy(css = "div .SelectItem-module__required__3agw8")
    protected List<WebElement> selectionTitleField;

    @FindBy(css = "div.SelectItem-module__container__1vSSf .default__value-container")
    protected List<WebElement> selectionInputField;



    @Step("Create new file in folder")
    public void createNewFile(String filePath) throws IOException {
        File file = new File(filePath);
        file.createNewFile();
    }

    @Step("Fill field inside card")
    public void fillFieldInsideCard(String fieldType, String fieldTitle, String inputData, String nameFromList) {
        try {

            switch (fieldType) {
                case "input":

                    for (int i = 0; i < inputTitleField.size(); i++) {
                        scrollToElement(inputTitleField.get(i));
                        if (getText(inputTitleField.get(i)).equalsIgnoreCase(fieldTitle)) {
                            scrollToElement(inputField.get(i));
                            click(inputField.get(i));
                            fillText(inputField.get(i), inputData);
                            return;
                        }
                    }
                    break;
                case "selection":
                    for (int i = 0; i < selectionTitleField.size(); i++) {
                        scrollToElement(selectionTitleField.get(i));
                        if (getText(selectionTitleField.get(i)).equalsIgnoreCase(fieldTitle)) {
                            scrollToElement(selectionInputField.get(i));
                            click(selectionInputField.get(i));
                            chooseValueFromList(nameFromList);
                            return;
                        }
                    }
                    break;
            }

        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
        }

    }

    @Step("Make sure message display")
    public String messageDisplay() {
        waitForPageFinishLoading();
        waitForElementToBeVisibility(startMessage);
        scrollToElement(startMessage);
        String messageName = getText(startMessage);
        return messageName;
    }


    @Step("Check the name of the card title name")
    public String cardTitleName() {
        waitForElementToBeVisibility(cardTitleName);
        scrollToElement(cardTitleName);
        value1 = cardTitleName.getAttribute("value");
        return value1;
    }


    @Step("Check the name of the card title name")
    public boolean cardTitleName1(String titleName) {
        try {
            value = null;
            waitForPageFinishLoading();
            sleep(2000);
            for (WebElement el : cardTitleName1) {
                scrollToElement(el);
                waitForElementToBeVisibility(el);
                value = el.getAttribute("value");
                if (value.equalsIgnoreCase(titleName)) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
        }
        return false;
    }


    @Step("Delete card from form")
    public void deleteCardFromForm(String titleName, String buttonName) {
        try {
            value = null;
            waitForPageFinishLoading();
            sleep(2000);
            for (int i = 1; i < cardTitleName1.size(); i++) {
                waitForElementToBeVisibility(cardTitleName1.get(i));
                scrollToElement(cardTitleName1.get(i));
                value = cardTitleName1.get(i).getAttribute("value");
                if (value.equalsIgnoreCase(titleName)) {
                    waitForElementToBeClickable(deleteCardList.get(i - 1));
                    scrollToElement(deleteCardList.get(i - 1));
                    sleep(2000);
                    click(deleteCardList.get(i - 1));
                    deleteCardPopUp(buttonName);
                    break;
                }

            }

        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
        }
    }


    @Step("Delete card from popup")
    public void deleteCardFromPopup(String titleName, String buttonName) {
        try {
            value = null;
            waitForPageFinishLoading();
            sleep(2000);
            for (int i = 0; i < cardTitleName1.size(); i++) {
                waitForElementToBeVisibility(cardTitleName1.get(i));
                scrollToElement(cardTitleName1.get(i));
                value = cardTitleName1.get(i).getAttribute("value");
                if (value.equalsIgnoreCase(titleName)) {
                    waitForElementToBeClickable(deleteCardList.get(i));
                    scrollToElement(deleteCardList.get(i));
                    sleep(2000);
                    click(deleteCardList.get(i));
                    deleteCardPopUp(buttonName);
                    break;
                }

            }

        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
        }
    }


    @Step("Choose card type from list")
    public void chooseCardType(String cardName) {
        try {
            waitForElementToBeVisibility(previewScreen);
            scrollToElement(addCardButton);
            waitForElementToBeClickable(addCardButton);
            click(addCardButton);
            sleep(3000);
            for (WebElement card : CardsTypeList) {
                waitForElementToBeVisibility(card);
                if (getText(card).equalsIgnoreCase(cardName)) {
                    waitForElementToBeClickable(card);
                    scrollToElement(card);
                    click(card);
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
        }

    }

    @Step("Clice on Save Button")
    public void clickOnSaveButton() {
        waitForElementToBeClickable(saveButton);
        scrollToElement(saveButton);
        click(saveButton);
    }

    @Step("Clice on Back Button")
    public void clickOnBackButton() {
        sleep(3000);
        scrollToElement(backButton);
        click(backButton);
    }

    @Step("Delete card form pop-up")
    public void deleteCardPopUp(String buttonName) {
        waitForElementToBeVisibility(popUpTitle);
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

    public void chooseValueFromList(String value) {
        waitForPageFinishLoading();
        WebElement el = driver.findElement(By.xpath(String.format("//*[text()='%s']", value)));
        click(el);
    }


}
