package btrust.btrustOne.admin.dataModule.entitiesManagement.pageObject.createBusinessCategory;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class EntityNamePage extends BasePage {
    public EntityNamePage(WebDriver driver) {
        super(driver);
    }

    protected ArrayList<Integer> parentList = new ArrayList<>();
    protected ArrayList<Integer> childList = new ArrayList<>();
    protected String value;
    int listSize;

    @FindBy(css = ".EntityName-module__tableHeader__2CPsY> .EntityName-module__headItem__1IU_S")
    protected List<WebElement> entityNameTitleList;
    @FindBy(css = ".EntityName-module__row__1fjHr > .EntityName-module__item__3CoKz")
    protected List<WebElement> fieldsList;
    @FindBy(css = ".Title-module__title__3WfvS > .Title-module__subTitle__1Q2cH")
    protected WebElement contentSubTitle;
    @FindBy(css = ".Tooltip-module__container__2T0SM > .Tooltip-module__wrapped__VKJko")
    protected WebElement tooltip;


    @Step("Return the fields that appear in the list")
    public String fieldsLst(String contentType, String title) {
        waitForPageFinishLoading();
        waitForList(entityNameTitleList);
        for (int i = 0; i < entityNameTitleList.size(); i++) {
            if (getText(entityNameTitleList.get(i)).equalsIgnoreCase(title)) {
                waitForList(fieldsList);
                value = getText(fieldsList.get(i));
                break;
            }
        }
        return value;
    }

    @Step("Count all the fields that appear in the table")
    public int dataFieldList(String contentType) {
        waitForPageFinishLoading();
        for (int i = 0; i < fieldsList.size(); i++) {
            scrollToElement(fieldsList.get(i));
            switch (contentType) {
                case "parent": {
                    parentList.add(i);
                    listSize = parentList.size();
                    break;
                }
                case "child": {
                    childList.add(i);
                    listSize = childList.size();
                    break;
                }
            }
        }
        return listSize;
    }

    @Step("Return the text of content sub title")
    public String contentSubTitle() {
        waitForElementToBeVisibility(contentSubTitle);
        return getText(contentSubTitle);
    }
}