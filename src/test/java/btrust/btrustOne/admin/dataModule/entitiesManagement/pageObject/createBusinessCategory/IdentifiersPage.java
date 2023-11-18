package btrust.btrustOne.admin.dataModule.entitiesManagement.pageObject.createBusinessCategory;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class IdentifiersPage extends BasePage {
    public IdentifiersPage(WebDriver driver) {
        super(driver);
    }

    protected ArrayList<String> list = new ArrayList<>();

    @FindBy(css = ".Identifiers-module__row__2gr_a > :nth-child(1)")
    protected List<WebElement> noList;
    @FindBy(css = ".Identifiers-module__row__2gr_a > :nth-child(2)")
    protected List<WebElement> fieldsToIdentifyList;


    @Step("Return the text of all the values that appear in relationship workflow column")
    public List<String> noList() {
        waitForList(noList);
        for (WebElement el : noList) {
            list.add(getText(el));
        }
        return list;
    }

    @Step("Return the text of all the values that appear in relationship workflow column")
    public List<String> fieldsToIdentifyList() {
        waitForList(fieldsToIdentifyList);
        for (WebElement el : fieldsToIdentifyList) {
            list.add(getText(el));
        }
        return list;
    }
}
