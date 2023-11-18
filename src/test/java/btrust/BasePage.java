package btrust;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;

public class BasePage {
    protected WebDriver driver;
    protected JavascriptExecutor js;
    protected String mainWindow; //to move between windows
    protected final int timeOutInSeconds = 20;
    WebDriverWait wait;
    Actions actions;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        js = (JavascriptExecutor) driver;
        wait = new WebDriverWait(driver, timeOutInSeconds);
        actions = new Actions(driver);
    }

    protected void fillText(WebElement el, String text) {
        highlightElement(el, "yellow");
        el.click();
        clear(el);
        el.sendKeys(text);
    }

    protected void uploadFile(WebElement el, String filePath) {
        highlightElement(el, "yellow");
        el.sendKeys(filePath);
    }

    protected void clear(WebElement el) {
        highlightElement(el, "yellow");
        el.sendKeys(Keys.chord(Keys.CONTROL, "a") + Keys.DELETE);
        el.clear();
    }

    protected void clearByJS(WebElement el, String value) {
        highlightElement(el, "yellow");
        js.executeScript("arguments[0].value = '';", value);
    }

    protected void click(WebElement el) {
        highlightElement(el, "yellow");
        el.click();
    }

    protected void clickByJS(WebElement el) {
        highlightElement(el, "yellow");
        js.executeScript("arguments[0].click()", el);
    }

    protected String getText(WebElement el) {
        highlightElement(el, "yellow");
        return el.getText();
    }

    protected String getAttribute(WebElement el, String attribute) {
        return el.getAttribute(attribute);
    }

    protected boolean isEnabled(WebElement el) {
        highlightElement(el, "yellow");
        return el.isEnabled();

    }

    protected boolean isDisplayed(WebElement el) {
        highlightElement(el, "yellow");
        return el.isDisplayed();
    }

    protected boolean isSelected(WebElement el) {
        highlightElement(el, "yellow");
        return el.isSelected();
    }

    public void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void moveToNewWindow() {
        mainWindow = driver.getWindowHandle();
        System.out.println(mainWindow);

        Set<String> list = driver.getWindowHandles();
        for (String win : list) {
            driver.switchTo().window(win);
        }
    }

    public boolean imageIsDisplayed(WebElement el) {
        try {
            js.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", el);
            System.out.println(js.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", el));
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    protected void waitForElementToBeClickable(WebElement el) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(el));
        } catch (Exception e) {
            scrollToElement(el);
            wait.until(ExpectedConditions.elementToBeClickable(el));
        }
    }

    protected void waitForElementToBeClickable(WebElement el, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        wait.until(ExpectedConditions.elementToBeClickable(el));
    }

    protected void waitForElementToBeVisibility(WebElement el) {
        wait.until(ExpectedConditions.visibilityOf(el));
    }

    protected void waitForTextToBeInElement(WebElement el, String text) {
        try {
            wait.until(ExpectedConditions.textToBePresentInElement(el, text));
        } catch (Exception e) {
            scrollToElement(el);
            wait.until(ExpectedConditions.textToBePresentInElement(el, text));
        }
    }

    protected void waitForList(List<WebElement> list) {
        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(list));
        } catch (Exception e) {
            scrollToElement(list.get(0));
            wait.until(ExpectedConditions.visibilityOfAllElements(list));
        }
    }

    protected void waitForPageFinishLoading() {
        js.executeScript("return document.readyState").equals("complete");
        sleep(2000);
    }

    protected void scrollToElement(WebElement el) {
        highlightElement(el, "yellow");
        js.executeScript("arguments[0].scrollIntoView();", el);
    }

    protected void mouseHoverOnElement(WebElement el) {
        actions.moveToElement(el).perform();
    }

    private void highlightElement(WebElement element, String color) {
        //keep the old style to change it back
        String originalStyle = element.getAttribute("style");
        String newStyle = "background: yellow;border: 1px solid " + color + ";" + originalStyle;
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Change the style
        js.executeScript("var tmpArguments = arguments;setTimeout(function () {tmpArguments[0].setAttribute('style', '" + newStyle + "');},0);",
                element);

        // Change the style back after few miliseconds
        js.executeScript("var tmpArguments = arguments;setTimeout(function () {tmpArguments[0].setAttribute('style', '"
                + originalStyle + "');},400);", element);
    }

}