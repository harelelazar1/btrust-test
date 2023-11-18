package btrust.onboardingProcess.ui.pagesObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ScanPage extends BasePage {
    public ScanPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".CaptureInstruction_container__label__nXjqv")
    protected WebElement instructionsTitle;
    @FindBy(css = ".RecordOverlay_container__button__XH+05")
    protected WebElement recordingButton;
    @FindBy(css = ".RecordOverlay_container__button__2Lhtp")
    protected WebElement finishRecordingLabel;

    @Step("Return the instructions title text")
    public String getInstructionsTitle() {
        scrollToElement(instructionsTitle);
        waitForElementToBeVisibility(instructionsTitle);
        return getText(instructionsTitle);
    }

    @Step("Return answer if the title appearing in the page")
    public boolean isLabelAppear() {
        try {
            return isDisplayed(finishRecordingLabel);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }

    @Step("Click on the recording button")
    public void clickRecordingButton() {
        boolean flag = false;
        try {
            do {
                scrollToElement(recordingButton);
                waitForElementToBeVisibility(finishRecordingLabel);
                if (isLabelAppear()) {
                    scrollToElement(recordingButton);
                    if (isDisplayed(recordingButton)) {
//                        Assert.assertNotNull(getOtpNum());
                        click(recordingButton);
                        flag = true;
                        js.executeScript("console.log('click audio chunk injection');");
                    }
                }
            } while (!flag);

        } catch (Exception e) {
            System.out.println("Error with clicking the record button: " + e.getMessage());
        }
    }
}
