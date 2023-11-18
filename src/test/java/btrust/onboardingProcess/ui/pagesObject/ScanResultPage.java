package btrust.onboardingProcess.ui.pagesObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

public class ScanResultPage extends BasePage {
    public ScanResultPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".Stepper_line__3tRm-")
    protected WebElement progressBar;
    @FindBy(css = ".Completed_container__hyin7>div")
    protected WebElement resultsTitleForMobileForm;
    @FindBy(css = ".Results_title__OJZ7G")
    protected WebElement resultsTitle;
    @FindBy(css = ".Results_faceImage__2rg2a")
    protected WebElement resultFace;
    @FindBy(css = ".Results_faceImage__9KbQ2>div")
    protected WebElement faceImageDescription;
    @FindBy(css = ".Results_picture__P1hA2 > img")
    protected WebElement resultFaceImage;
    @FindBy(css = ".Results_btns__VM9bt > button")
    protected List<WebElement> buttonList;
    @FindBy(css = "div>.Results_label__zu19P")
    protected List<WebElement> titles;
    @FindBy(css = "div > div.Results_value__Al7Em")
    protected List<WebElement> values;

    @Step("Check if progressBarIsDisplayed")
    public boolean progressBarIsDisplayed() {
        try {
            Thread.sleep(10000);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        waitForElementToBeVisibility(progressBar);
        return isDisplayed(progressBar);
    }

    @Step("Return the text of result title")
    public boolean resultsTitleForMobileForm() {
        try {
            waitForPageFinishLoading();
            waitForElementToBeVisibility(resultsTitleForMobileForm);
            if (isDisplayed(resultsTitleForMobileForm)) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }


    @Step("Return the text of result title")
    public String resultsTitle() {
        waitForPageFinishLoading();
        waitForElementToBeVisibility(resultsTitle);
        return getText(resultsTitle);
    }

    @Step("Return the text of result face image description")
    public String resultFaceImageDescription() {
        waitForElementToBeVisibility(faceImageDescription);
        return getText(faceImageDescription);
    }

    @Step("Check if face image is displayed")
    public boolean faceImageIsDisplayed() {
        waitForElementToBeVisibility(resultFaceImage);
        return imageIsDisplayed(resultFaceImage);
    }

    @Step("Click on button: {button}")
    public void clickOnButton(String button) {
        waitForList(buttonList);
        for (WebElement el : buttonList) {
            if (getText(el).contains(button)) {
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
    }

    @Step("Return the value that displayed under the title: {title}")
    public String results(String idType, String title) {
        String result = null;
        waitForPageFinishLoading();
        for (int i = 0; i < titles.size(); i++) {
            switch (idType) {
                case "cheque":
                    Assert.assertEquals(titles.size(), 4);
                    break;
                case "bio id":
                case "blue id":
                case "MRZ":
                    Assert.assertEquals(titles.size(), 9);
                    break;
                case "green id":
                    Assert.assertEquals(titles.size(), 7);
                    break;
                case "dl":
                    Assert.assertEquals(titles.size(), 12);
                    break;
                case "lawyer":
                    Assert.assertTrue(titles.size() == 6, "expected 6 results from lawyer card scan");
                case "card capture":
                    Assert.assertEquals(titles.size(), 6);
                    break;
                case "card detector":
                    assert titles.size() == 10;
                    break;
            }
            if (getText(titles.get(i)).contains(title)) {
                result = getText(values.get(i));
                break;
            }
        }
        return result;
    }
}