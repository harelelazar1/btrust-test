package selenium.ocr.pageObject.oldService;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.BasePage;

import java.util.List;

public class OcrScanPage extends BasePage {

    public OcrScanPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".scanovate-active-ocr-video-text-overlay")
    protected WebElement cameraAlert;
    @FindBy(css = ".header")
    protected List<WebElement> scanError;

    //NEW demo


    @Step("Return the camera alert text")
    public String cameraAlertText(String textToWait) {
        waitForTextToBeInElement(cameraAlert, textToWait);
        isDisplayed(cameraAlert);
        return getText(cameraAlert);
    }

    @Step("Check if the camera alert box is appearing on the screen")
    public boolean cameraAlertAppear() {
        try {
            waitForElementToBeVisibility(cameraAlert);
            isDisplayed(cameraAlert);
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    @Step("Return the scan error message")
    public String scanErrorMessage() {
        waitForList(scanError);
        isDisplayed(scanError.get(scanError.size() - 1));
        return getText(scanError.get(scanError.size() - 1));
    }

}
