package btrust.btrustOne.admin.dataModule.entitiesManagement.pageObject.createBusinessCategory;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class LinkedWorkflowsPage extends BasePage {
    public LinkedWorkflowsPage(WebDriver driver) {
        super(driver);
    }

    String value;
    protected ArrayList<Integer> parentList = new ArrayList<>();
    protected ArrayList<Integer> childList = new ArrayList<>();
    protected ArrayList<Integer> summaryList = new ArrayList<>();
    int listSize;

    @FindBy(css = ".Data-module__tableHeader__3v1hI > .Data-module__headItem__1GIJz")
    protected List<WebElement> linkedWorkflowsTitles;
    @FindBy(css = ".Table-module__row__3LOG9 > :nth-child(1)")
    protected List<WebElement> noList;
    @FindBy(css = ".Table-module__row__3LOG9 > :nth-child(2)")
    protected List<WebElement> workflowNameList;
    @FindBy(css = ".Table-module__row__3LOG9 > :nth-child(3)")
    protected List<WebElement> relationshipToWorkflowList;
    @FindBy(css = ".Table-module__row__3LOG9 > :nth-child(4)")
    protected List<WebElement> sourceList;

    @Step("Return the value that appeat under the title of the table")
    public String getLinkedWorkflowsTable(String title) {
        waitForPageFinishLoading();
        for (WebElement el : linkedWorkflowsTitles) {
            scrollToElement(el);
            if (getText(el).equalsIgnoreCase(title)) {
                switch (title) {
                    case "NO": {
                        scrollToElement(noList.get(noList.size() - 1));
                        value = getText(noList.get(noList.size() - 1));
                        break;
                    }
                    case "WORKFLOW NAME": {
                        scrollToElement(workflowNameList.get(workflowNameList.size() - 1));
                        value = getText(workflowNameList.get(workflowNameList.size() - 1));
                        break;
                    }
                    case "RELATIONSHIP TO WORKFLOW": {
                        scrollToElement(relationshipToWorkflowList.get(relationshipToWorkflowList.size() - 1));
                        value = getText(relationshipToWorkflowList.get(relationshipToWorkflowList.size() - 1));
                        break;
                    }
                    case "SOURCE": {
                        scrollToElement(sourceList.get(sourceList.size() - 1));
                        value = getText(sourceList.get(sourceList.size() - 1));
                        break;
                    }
                }
            }
            break;
        }
        return value;
    }

    @Step("Count all the linked workflows that appear in the list")
    public int countLinkedWorkflows(String contentType) {
        waitForPageFinishLoading();
        for (int i = 0; i < noList.size(); i++) {
            scrollToElement(noList.get(i));
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
                case "summary": {
                    summaryList.add(i);
                    listSize = summaryList.size();
                    break;
                }
            }
        }
        return listSize;
    }
}