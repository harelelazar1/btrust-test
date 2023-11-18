package btrust.onboardingProcess.ui.pagesObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CompletedPage extends BasePage {
    public CompletedPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".Completed_container__hyin7 > div")
    protected WebElement processEndedText;
    @FindBy(css = ".ChangeLanguage_container__langButton__wuR\\+j")
    protected WebElement changeLanguageButton;
    @FindBy(css = ".LanguageList_langItem__container__ox24P")
    protected List<WebElement> chooseLanguageButton;

    @Step("Return the text of result face image description")
    public String getCompleteText() {
        waitForElementToBeVisibility(processEndedText);
        return getText(processEndedText);
    }

    @Step("Choose langauge")
    public void chooseLanguage(String languageName) {
        waitForPageFinishLoading();
        waitForElementToBeClickable(changeLanguageButton,60);
        click(changeLanguageButton);
        waitForList(chooseLanguageButton);
        for (WebElement el : chooseLanguageButton) {
            if (getText(el).equalsIgnoreCase(languageName)) {
                click(el);
                break;
            }
        }
    }
}