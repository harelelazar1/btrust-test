package btrust.btrustOne.client.entity.pagesObject.CreateEntity;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class EmailAndFormsPage extends BasePage {
    public EmailAndFormsPage(WebDriver driver) {
        super(driver);
    }


    @FindBy(css = ".EmailTemplateForm-module__title__Nk8En")
    protected WebElement emailTemplatesTitle;

    @FindBy(css = "[class] [tabindex='-1']")
    protected List<WebElement> emailTemplateList;
    @FindBy(css = ".css-1wy0on6.default__indicators")
    protected WebElement emailTemplateArrow;

    @Step("Return the text of 'email templates Title'")
    public String emailTemplatesTitle(String Title) {
        waitForTextToBeInElement(emailTemplatesTitle, Title);
        return getText(emailTemplatesTitle);
    }


    @Step("select email template: {emailTemplate} ")
    public void selectEmailTemplate(String emailTemplateName) {
        waitForElementToBeClickable(emailTemplateArrow);
        click(emailTemplateArrow);
        waitForPageFinishLoading();
        waitForList(emailTemplateList);
        for (WebElement el : emailTemplateList) {
            if (getText(el).equalsIgnoreCase(emailTemplateName)) {
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
    }
}