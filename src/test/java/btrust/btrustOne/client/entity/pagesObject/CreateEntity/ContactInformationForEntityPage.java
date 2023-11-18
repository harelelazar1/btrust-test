package btrust.btrustOne.client.entity.pagesObject.CreateEntity;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ContactInformationForEntityPage extends BasePage {
    public ContactInformationForEntityPage(WebDriver driver) {
        super(driver);
    }


    @FindBy(css = ".AddContactToTableList-module__btn__upAVr[type=button]")
    protected WebElement addNewContactPerson;
    @FindBy(css = ".Title-module__text__1LN-A")
    protected WebElement contactsTitle;
    @FindBy(css = ".ContactsTableHeader-module__headCell__4LbXK [type='checkbox']")
    protected WebElement parentCheckboxForContacts;

    //contact person
    @FindBy(css = ".ContactPopup-module__flex-items__2shet > div input , .ContactPopup-module__content__3anVq input")
    protected List<WebElement> addContactCardInputsList;
    @FindBy(css = "div > .ContactPopup-module__label__1jgmP")
    protected List<WebElement> addContactCardInputTitlesList;
    @FindBy(css = ".AddContactToTableList-module__btn__upAVr[type='button']")
    protected WebElement createContactButton;
    @FindBy(css = ".WhiteBoxWrapper-module__container__1PzPb .ContactsTable-module__row__1iyD9")
    protected List<WebElement> contactPersonList;
    @FindBy(css = ".ContactsTable-module__row__1iyD9 > .ContactsTable-module__item__yZA2B.ContactsTable-module__name__12Z60")
    protected List<WebElement> fullNameList;
    @FindBy(css = ".ContactsTable-module__row__1iyD9 button")
    protected List<WebElement> editContactPersonListButton;
    @FindBy(css = ".ContactPopup-module__flex-items__2shet > div , .ContactPopup-module__item__1Nl7W")
    protected List<WebElement> titleAndErrorMessageFieldsErrorList;
    @FindBy(css = ".ContactPopup-module__text__1bcF2")
    protected WebElement newContactPopupTitle;
    @FindBy(css = "div .PopupBottomButtons-module__container__20krY :nth-child(1)")
    protected WebElement cancelButton;
    @FindBy(css = ".ContactsTableHeader-module__conteiner__3vRK7 > .ContactsTableHeader-module__headCell__4LbXK")
    protected List<WebElement> contactsTitleList;
    @FindBy(css = ".ContactsTable-module__row__1iyD9 > .ContactsTable-module__item__yZA2B")
    protected List<WebElement> contactValuesList;
    @FindBy(css = ".PopupBottomButtons-module__container__20krY [type='button']")
    protected List<WebElement> clickOnContactButton;


    @Step("click on buttons inside the contact popup")
    public void clickOnButtonsInPopup (String buttonName) {
        for (WebElement el:clickOnContactButton) {
            if (getText(el).equalsIgnoreCase(buttonName)){
                scrollToElement(el);
                click(el);
                break;
            }
        }
    }


    @Step("Return the text of 'contacts Title'")
    public String contactsTitle(String Title) {
        waitForTextToBeInElement(contactsTitle, Title);
        return getText(contactsTitle);
    }

    @Step("click on create contact button")
    public void createContactButton() {
        scrollToElement(createContactButton);
        click(createContactButton);
    }

    @Step("click on cancel button")
    public void cancelButton() {
        scrollToElement(cancelButton);
        click(cancelButton);
    }

    @Step("click on parent checkbox for contacts")
    public void parentCheckboxForContacts() {
        mouseHoverOnElement(parentCheckboxForContacts);
        driver.findElement(By.cssSelector(".ContactsTableHeader-module__headCell__4LbXK [type='checkbox']")).click();
    }

    @Step("click on continue button")
    public void addNewContactPerson() {
        scrollToElement(addNewContactPerson);
        click(addNewContactPerson);
    }

    @Step("Type value: {value} in filed: {title}")
    public void fillContactDetails(String title, String value) {
        for (int i = 0; i < addContactCardInputTitlesList.size(); i++) {
            scrollToElement(addContactCardInputTitlesList.get(i));
            if (getText(addContactCardInputTitlesList.get(i)).equalsIgnoreCase(title)) {
                clearByJS(addContactCardInputsList.get(i), "");
                fillText(addContactCardInputsList.get(i), value);
                break;
            }
        }
    }

    @Step("return the text in data contact info popup")
    public String verifyTitlesAndDataInContactInfoPersonPopup(String text) {
        String dataField = null;
        for (WebElement el : titleAndErrorMessageFieldsErrorList) {
            scrollToElement(el);
            if (getText(el).contains(text)) {
                dataField = getText(el);
            }
        }
        return dataField;
    }

    @Step("return the text in contact person list")
    public String verifyContactInfoPersonList(String text) {
        String dataField = null;
        for (int i = 0; i < contactsTitleList.size(); i++) {
            scrollToElement(contactsTitleList.get(i));
            if (getText(contactsTitleList.get(i)).equalsIgnoreCase(text)) {
                dataField = getText(contactValuesList.get(i));
                break;
            }
        }
        return dataField;
    }

    @Step("click on edit button for contact person")
    public void clickOnEditContactPerson(String text) {
        for (int i = 0; i < fullNameList.size(); i++) {
            scrollToElement(fullNameList.get(i));
            if (getText(fullNameList.get(i)).equalsIgnoreCase(text)) {
                click(editContactPersonListButton.get(i));
                break;
            }
        }
    }
}