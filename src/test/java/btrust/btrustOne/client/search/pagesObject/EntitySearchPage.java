package btrust.btrustOne.client.search.pagesObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class EntitySearchPage extends BasePage {
    public EntitySearchPage(WebDriver driver) {
        super(driver);
    }

    String entityName;
    boolean bo;

    @FindBy(css = ".EntitySearchResults-module__wrapper__2xX3n > .EntitySearchResults-module__title__2OBew")
    protected WebElement entitySearchTitle;
    @FindBy(css = ".Table-module__container__1mfyG > .Table-module__title__3eFIr")
    protected WebElement resultTableTitle;
    @FindBy(css = ".Table-module__container__1mfyG > .Table-module__subtitle__3LhJg")
    protected WebElement resultTableDescription;
    @FindBy(css = ".Table-module__row__3scrR > :nth-child(2) > .Table-module__mainText__37Ee4")
    protected List<WebElement> entitiesNameList;
    @FindBy(css = ".Table-module__row__3scrR > :nth-child(5) > button")
    protected List<WebElement> runWorkflowButtons;
    @FindBy(css = ".AddButton-module__container__1EEUu > button")
    protected WebElement createNewEntityButton;
    @FindBy(css = ".NoData-module__container__2Tdzf > img")
    protected WebElement noResultIcon;
    @FindBy(css = ".NoData-module__container__2Tdzf > .NoData-module__title__dgZPT")
    protected WebElement noResultMessage;
    @FindBy(css = ".NoData-module__containerClassName__Sdz_C > button")
    protected WebElement noResultCreateNewEntityButton;
    @FindBy(css = ".NoData-module__description__3D9Vu > span")
    protected WebElement noResultDescription;
    @FindBy(css = ".CreateNewEntityBtn-module__container__1SZWz  [type='button']")
    protected WebElement ResultCreateNewEntityButton;
    @FindBy(css = ".Table-module__row__3scrR>:nth-child(5)")
    protected List<WebElement> RunWorkFlowButton;


    @Step("Return the text of entity search title")
    public String entitySearchTitle() {
        waitForElementToBeVisibility(entitySearchTitle);
        return getText(entitySearchTitle);
    }

    @Step("Return the text of result table title")
    public String resultTableTitle() {
        waitForElementToBeVisibility(resultTableTitle);
        return getText(resultTableTitle);
    }

    @Step("Return the text of result table description")
    public String resultTableDescription() {
        waitForElementToBeVisibility(resultTableDescription);
        return getText(resultTableDescription);
    }

    @Step("Return the all entities name that appear in the table")
    public String entitiesName(String entity) {
        waitForPageFinishLoading();
        waitForElementToBeVisibility(entitiesNameList.get(0));
        for (WebElement el : entitiesNameList) {
            if (getText(el).contains(entity)) {
                waitForElementToBeVisibility(el);
                entityName = getText(el);
            }
        }
        return entityName;
    }

    @Step("Click on run workflow button")
    public void runWorkflow(String entity) {
        waitForList(entitiesNameList);
        for (int i = 0; i <= entitiesNameList.size(); i++) {
            if (getText(entitiesNameList.get(i)).equalsIgnoreCase(entity)) {
                waitForList(runWorkflowButtons);
                waitForElementToBeClickable(runWorkflowButtons.get(i));
                click(runWorkflowButtons.get(i));
                break;
            }
        }
    }

    @Step("Click on create new entity button")
    public void createNewEntityButton() {
        waitForElementToBeClickable(createNewEntityButton);
        click(createNewEntityButton);
    }

    @Step("Return the text of no result message")
    public String noResultMessage() {
        waitForElementToBeVisibility(noResultMessage);
        return getText(noResultMessage);
    }

    @Step("Check if no result icon is displayd")
    public boolean noResultIcon() {
        waitForElementToBeVisibility(noResultIcon);
        return isDisplayed(noResultIcon);
    }

    @Step("Check if Create new entity button is displayed")
    public boolean noResultCreateNewEntityButton() {
        try {
            if (isDisplayed(noResultCreateNewEntityButton)) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }

    @Step("Check if Create new entity button is displayed")
    public boolean ResultCreateNewEntityButton() {
        try {
            waitForPageFinishLoading();
            if (isDisplayed(ResultCreateNewEntityButton)) {
                waitForElementToBeClickable(ResultCreateNewEntityButton);
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }

    @Step("Check if workflow button is displayed")
    public boolean ResultRunWorkflowButton() {
        try {
            for (WebElement el : RunWorkFlowButton) {
                if (isDisplayed(el))
                    waitForElementToBeClickable(ResultCreateNewEntityButton);
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }


    @Step("Return the text of no result dscription")
    public String noResultDescription() {
        waitForElementToBeVisibility(noResultDescription);
        return getText(noResultDescription);
    }


}