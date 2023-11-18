package btrust.btrustOne.client.entity.pagesObject.CreateEntity;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class OptionalFieldsPage extends BasePage {
    public OptionalFieldsPage(WebDriver driver) {
        super(driver);
    }


    @FindBy(css = ".Title-module__container__11DMw .Title-module__text__1LN-A")
    protected WebElement additionalNonMandatoryInformationTitle;
    @FindBy(css = ".InputItem-module__label__g4G7d.undefined , .DateItem-module__inputItem__2H4md")
    protected List<WebElement> optionalFieldsTitleList;
    @FindBy(css = "input , .DateSelector_container")
    protected List<WebElement> optionalTypeBoxList;


    @Step("fill the text boxes in non-mandatory fields")
    public void fillOptionalFieldsForEntity(String optionalFieldTitle, String textInput) {
        for (int i = 0; i <= optionalFieldsTitleList.size(); i++) {
            if (getText(optionalFieldsTitleList.get(i)).contains(optionalFieldTitle)) {
                scrollToElement(optionalFieldsTitleList.get(i));
                fillText(optionalTypeBoxList.get(i), textInput);
                break;
            }
        }
    }

    @Step("Return the text of 'additional NonMandatory Information Title'")
    public String additionalNonMandatoryInformationTitle(String Title) {
        waitForTextToBeInElement(additionalNonMandatoryInformationTitle, Title);
        return getText(additionalNonMandatoryInformationTitle);
    }
}