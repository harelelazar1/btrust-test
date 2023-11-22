package btrust.btrustOne.admin.generalAdmin.emailTemplate.tests.pageObject;

import btrust.BasePage;
import io.qameta.allure.Step;
// import javafx.stage.Screen;
import org.openqa.selenium.*;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import utilities.RetryAnalyzer;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.regex.Pattern;

public class AddNewTemplatePage extends BasePage {


    public AddNewTemplatePage(WebDriver driver) {
        super(driver);
    }


    @FindBy(css = ".Title-module__container__1gy_T>:nth-child(3)")
    protected WebElement saveButton;
    @FindBy(css = ".MuiSwitch-root.Title-module__root__2v1ov .Mui-checked")
    protected WebElement enableEditingChecked;
    @FindBy(css = "button.BackToList-module__container__3Av2H")
    protected WebElement backToListButton;
    @FindBy(css = ".nav.flex-column.nav-tabs  .nav-name")
    protected List<WebElement> tabContentList;

    @FindBy(css = ".blockbuilder-content-tools .blockbuilder-content-tool-name")
    protected List<WebElement> contentNameList;
    @FindBy(css = ".Title-module__container__1gy_T> div :nth-child(1) button")
    protected WebElement editEmailTemplateNameButton;
    @FindBy(css = ".EditTitleInput-module__withSave__3fZWd input")
    protected WebElement inputEmailTemplateNameField;
    @FindBy(css = ".EditTitleInput-module__withSave__3fZWd input")
    protected WebElement SaveEmailTemplateName;

    @FindBy(css = ".Title-module__container__1gy_T> div :nth-child(2) button")
    protected WebElement editEmailSubjectNameButton;
    @FindBy(css = ".EditTitleInput-module__withSave__3fZWd input")
    protected WebElement inputEmailTemplateSubjectNameField;
    @FindBy(css = ".EditTitleInput-module__withSave__3fZWd input")
    protected WebElement SaveEmailTemplateSubjectName;

    @FindBy(css = "#u_column_1 .blockbuilder-layer.blockbuilder-layer-selectable")
    protected WebElement afterdragging;

    @FindBy(css = "div .blockbuilder-placeholder-empty")
    protected WebElement target;
    @FindBy(css = ".tab-content [draggable=true]")
    protected List<WebElement> sourceList;


    @Step("Click on Save Button")
    public void clickOnSaveButton() {
        waitForElementToBeClickable(saveButton);
        scrollToElement(saveButton);
        click(saveButton);
    }

    @Step("Change Email Template Name")
    public void changeEmailTemplateName(String emailTemplateName) {
        waitForElementToBeClickable(editEmailTemplateNameButton);
        scrollToElement(editEmailTemplateNameButton);
        click(editEmailTemplateNameButton);
        scrollToElement(inputEmailTemplateNameField);
        fillText(inputEmailTemplateNameField, emailTemplateName);
        click(SaveEmailTemplateName);
    }


    @Step("Change Email Template Subject Name")
    public void changeEmailTemplateSubjectName(String emailTemplateName) {
        waitForElementToBeClickable(editEmailSubjectNameButton);
        scrollToElement(editEmailSubjectNameButton);
        click(editEmailSubjectNameButton);
        scrollToElement(inputEmailTemplateSubjectNameField);
        fillText(inputEmailTemplateSubjectNameField, emailTemplateName);
        click(SaveEmailTemplateSubjectName);
    }


    @Step("Click On Back To List")
    public void clickOnBackToList() {
        waitForElementToBeClickable(backToListButton);
        scrollToElement(backToListButton);
        click(backToListButton);
    }

    @Step("Add new content to the email body")
    public boolean addContentToTheEmailBody(String tabName, String contentName) throws InterruptedException {
        sleep(5000);

        WebElement iframeElement = driver.findElement(By.xpath("//iframe"));
        driver.switchTo().frame(iframeElement);


        for (int i = 0; i < tabContentList.size(); i++) {
            scrollToElement(tabContentList.get(i));
            if (getText(tabContentList.get(i)).equalsIgnoreCase(tabName)) {
                click((tabContentList.get(i)));
                for (WebElement content : contentNameList) {
                    scrollToElement(content);
                    if (getText(content).equalsIgnoreCase(contentName)) {
                        sleep(3000);
                        Actions builder = new Actions(driver);
//                        builder.dragAndDrop(sourceList.get(3), target).perform();

                        Action dragAndDrop = builder.clickAndHold(sourceList.get(3))
                                .moveToElement(target)
                                .release(target)
                                .build();
                        Thread.sleep(2000);
                        dragAndDrop.perform();

                        return true;
                    }
                }
            }

        }
        return false;
    }


}
