package btrust.btrustOne.client.mandate.pagesObject.createMandate.pagesObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Random;

public class EmailAndFormPage extends BasePage {
    public EmailAndFormPage(WebDriver driver) {
        super(driver);
    }

    protected String randomValue;

    @FindBy(css = ".Title-module__title__4TKNP > .Title-module__text__CK5Le")
    protected WebElement emailAndFormRequiredTitle;
    @FindBy(css = ".EmailTemplateForm-module__inputItem__1f26- > input")
    protected WebElement subjectField;
    @FindBy(css = "[name='body']")
    protected WebElement bodyField;
    @FindBy(css = "textarea[name='signature']")
    protected WebElement emailSignatureField;
    @FindBy(css = ".WhiteBoxWrapper-module__container__SSa8u .ValidationError-module__container__1w8Qt")
    protected List<WebElement> errorMessagesList;
    /*
        list for all the buttons that display on the screen
    */
    @FindBy(css = ".PageWrapper-module__wrapper__JFJiY button")
    protected List<WebElement> buttonsList;


    @Step("Return the title of tab")
    public String emailAndFormRequiredTitle(String title) {
        waitForPageFinishLoading();
        waitForTextToBeInElement(emailAndFormRequiredTitle, title);
        return getText(emailAndFormRequiredTitle);
    }

    @Step("Fill the fields that appear in 'email and form required' tab")
    public void fillEmailAndFormRequiredTab(String subject) {
        waitForElementToBeClickable(subjectField);
        fillText(subjectField, subject);
    }

    @Step("Return the error message")
    public String errorMessage(int x, String error) {
        String errorMessage = null;
        waitForElementToBeVisibility(errorMessagesList.get(0));
        for (int i = 0; i <= errorMessagesList.size(); i++) {
            WebElement el = errorMessagesList.get(x);
            if (getText(el).equalsIgnoreCase(error)) {
                waitForTextToBeInElement(el, error);
                errorMessage = getText(el);
                break;
            }
        }
        return errorMessage;
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