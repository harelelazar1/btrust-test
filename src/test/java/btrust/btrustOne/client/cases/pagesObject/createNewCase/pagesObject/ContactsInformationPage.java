package btrust.btrustOne.client.cases.pagesObject.createNewCase.pagesObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ContactsInformationPage extends BasePage {
    public ContactsInformationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "div.Title-module__text__3zZ5G")
    protected WebElement contactsTitle;
    @FindBy(css = "div .Title-module__description__2Gq0B")
    protected WebElement contactsDescription;
    @FindBy(css = ".MuiButtonBase-root.MuiIconButton-root.MuiCheckbox-root.MuiCheckbox-colorSecondary.MuiIconButton-colorSecondary")
    protected List<WebElement> contactCheckbox;
    @FindBy(css = "div.WhiteBoxWrapper-module__container__1tY-A>:nth-child(3)>div>:nth-child(2)")
    protected List<WebElement> contactNameList;
    @FindBy(css = "div.WhiteBoxWrapper-module__container__1tY-A>:nth-child(1) button[type='button']")
    protected WebElement addNewContactPersonButton;
    @FindBy(css = ".BottomStickyActions-module__actions__1YB7n>div>[type='button']")
    protected List<WebElement> buttonsList;

    @Step("Return the text of contacts title")
    public String contactsTitle() {
        waitForPageFinishLoading();
        //   waitForElementToBeClickable(addNewContactPersonButton);
        waitForElementToBeVisibility(contactsTitle);
        return getText(contactsTitle);
    }

    @Step("Return the text og contacts description")
    public String contactsDescription() {
        waitForElementToBeVisibility(contactsDescription);
        return getText(contactsDescription);
    }

    @Step("Click on add new contact person button")
    public void addNewContactPersonButton() {
        waitForElementToBeClickable(addNewContactPersonButton);
        click(addNewContactPersonButton);
    }

    @Step("Select contact from the contacts list")
    public void chooseContact(String contactName) {
        waitForPageFinishLoading();
        for (int i = 0; i <= contactNameList.size(); i++) {
            if (getText(contactNameList.get(i)).contains(contactName)) {
                waitForList(contactCheckbox);
                waitForElementToBeClickable(contactCheckbox.get(i));
                click(contactCheckbox.get(i));
                break;
            }
        }
    }

    @Step("Click on button: {button}")
    public void chooseButton(String button) {
        for (WebElement el : buttonsList) {
            scrollToElement(el);
            if (getText(el).equalsIgnoreCase(button)) {
                click(el);
                break;
            }
        }
    }

    @Step("Check if button is enabled")
    public boolean buttonIsEnabled(String button) {
        boolean buttonIsEnabled = false;
        for (WebElement el : buttonsList) {
            scrollToElement(el);
            if (getText(el).contains(button)) {
                buttonIsEnabled = isEnabled(el);
                break;
            }
        }
        return buttonIsEnabled;
    }
}