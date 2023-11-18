package btrust.btrustOne.admin.generalAdmin.emailTemplate.pageObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class EmailTemplatePage extends BasePage {


    public EmailTemplatePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".SettingsHeaderSeaarchOnly-module__top__3hYpZ input")
    protected WebElement searchField;
    @FindBy(css = ".SettingsHeaderSeaarchOnly-module__container__2kWTZ  .SettingsHeaderSeaarchOnly-module__title__2B9H_")
    protected WebElement titlePage;
    @FindBy(css = ".SettingsHeaderSeaarchOnly-module__top__3hYpZ button")
    protected WebElement addEmailTemplateButton;
    @FindBy(css = ".Table-module__row__2QnG0>:nth-child(2)")
    protected List<WebElement> templateNameColumnList;
    @FindBy(css = ".Table-module__row__2QnG0>:nth-child(5)")
    protected List<WebElement> editRowButtonList;




    @Step("Return the text of the page title")
    public String emailTemplatePageTitle() {
        waitForElementToBeVisibility(titlePage);
        return getText(titlePage);
    }


    @Step("Type in search field")
    public void searchField(String text) {
        scrollToElement(searchField);
        fillText(searchField, text);
    }

    @Step("Click on add new data mapper button")
    public void clickOnAddButton() {
        waitForElementToBeClickable(addEmailTemplateButton);
        scrollToElement(addEmailTemplateButton);
        click(addEmailTemplateButton);
    }

//
//    @Step("Click On Edit Row button")
//    public void clickOnEditRowButton(String templateName) {
//        try {
//
//            for (int i = 0; i <templateNameColumnList.size() ; i++) {
//                scrollToElement(templateNameColumnList.get(i));
//                if (getText(templateNameColumnList.get(i)).equalsIgnoreCase(templateName)){
//                    scrollToElement(editRowButtonList.get(i));
//                    waitForElementToBeClickable(editRowButtonList.get(i));
//                    click((editRowButtonList.get(i));
//
//
//                }
//
//
//            }
//        } catch (Exception e) {
//            System.out.println("The button is not found");
//        }
//    }


}
