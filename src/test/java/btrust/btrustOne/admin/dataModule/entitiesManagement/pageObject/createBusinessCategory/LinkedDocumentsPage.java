package btrust.btrustOne.admin.dataModule.entitiesManagement.pageObject.createBusinessCategory;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class LinkedDocumentsPage extends BasePage {
    public LinkedDocumentsPage(WebDriver driver) {
        super(driver);
    }

    protected ArrayList<Integer> parentList = new ArrayList<>();
    protected ArrayList<Integer> childList = new ArrayList<>();
    protected ArrayList<Integer> summaryList = new ArrayList<>();
    int listSize;
    String value;

    @FindBy(css = ".Data-module__tableHeader__1sglu > .Data-module__headItem__1HHti")
    protected List<WebElement> linkedDocumentsTitles;
    @FindBy(css = ".Table-module__row__1d9Uv > :nth-child(1)")
    protected List<WebElement> noList;
    @FindBy(css = ".Table-module__row__1d9Uv > :nth-child(2)")
    protected List<WebElement> documentNameList;
    @FindBy(css = ".Table-module__row__1d9Uv > :nth-child(3)")
    protected List<WebElement> documentTypeList;
    @FindBy(css = ".Table-module__row__1d9Uv > :nth-child(4)")
    protected List<WebElement> documentStatusList;
    @FindBy(css = ".Table-module__row__1d9Uv > :nth-child(5)")
    protected List<WebElement> sourceList;


    @Step("Count all the linked documents that appear in the list")
    public int countLinkedDocuments(String contentType) {
        listSize = 0;
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

    @Step("Check the value that appear in the data profile table")
    public String getValueFromDataProfileTable(String title) {
        for (WebElement element : linkedDocumentsTitles) {
            scrollToElement(element);
            if (getText(element).equalsIgnoreCase(title)) {
                switch (title) {
                    case "NO": {
                        value = getText(noList.get(noList.size() - 1));
                        break;
                    }
                    case "DOCUMENT NAME": {
                        value = getText(documentNameList.get(documentNameList.size() - 1));
                        break;
                    }
                    case "DOCUMENT TYPE": {
                        value = getText(documentTypeList.get(documentTypeList.size() - 1));
                        break;
                    }
                    case "DOCUMENT STATUS": {
                        value = getText(documentStatusList.get(documentStatusList.size() - 1));
                        break;
                    }
                    case "SOURCE": {
                        value = getText(sourceList.get(sourceList.size() - 1));
                        break;
                    }
                }
                break;
            }
        }
        return value;
    }
}