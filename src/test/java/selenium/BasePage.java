package selenium;

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


    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        js = (JavascriptExecutor) driver;
    }

    protected void fillText(WebElement el, String text) {
        highlightElement(el, "yellow");
        el.click();
        el.clear();
        el.sendKeys(text);
    }

    protected void sendFile(WebElement el, String filePath) {
        el.sendKeys(filePath);
    }

    protected void clear(WebElement el) {
        highlightElement(el, "yellow");
        String del = Keys.chord(Keys.CONTROL, "a") + Keys.DELETE;
        el.sendKeys(del);
    }

    protected void click(WebElement el) {
        highlightElement(el, "yellow");
        el.click();
    }

    protected void clickByJS(WebElement el) {
        js.executeScript("arguments[0].click()", el);
    }

    protected String getText(WebElement el) {
        highlightElement(el, "yellow");
        return el.getText();
    }

    protected String getAttribute(WebElement el, String attribute) {
        highlightElement(el, "yellow");
        return el.getAttribute(attribute);
    }

    protected String getCssValue(WebElement el, String cssValue) {
        highlightElement(el, "yellow");
        return el.getCssValue(cssValue);
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

    protected void alertOK(String name) {
        driver.switchTo().alert().sendKeys(name);
        driver.switchTo().alert().accept();
    }

    protected void alertOK() {
        driver.switchTo().alert().accept();
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
        // run test on new window
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

    protected void backToMainWindow() {
        driver.close();
        driver.switchTo().window(mainWindow);
    }

    public void openNewBlankTab() {
        js.executeScript("window.open()");
    }

    protected void waitForElementToBeClickable(WebElement el) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(el));
        } catch (Exception e) {
            wait.until(ExpectedConditions.elementToBeClickable(el));
        }
    }

    protected void waitForElementToBeVisibility(WebElement el) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        try {
            wait.until(ExpectedConditions.visibilityOf(el));
        } catch (Exception e) {
            wait.until(ExpectedConditions.visibilityOf(el));
        }
    }

    protected void waitForTextToBeInElement(WebElement el, String text) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        try {
            wait.until(ExpectedConditions.textToBePresentInElement(el, text));
        } catch (Exception e) {
            wait.until(ExpectedConditions.textToBePresentInElement(el, text));
        }
    }

    protected void waitForList(List<WebElement> list) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(list));
        } catch (Exception e) {
            wait.until(ExpectedConditions.visibilityOfAllElements(list));
        }
    }
    protected void waitForList(List<WebElement> list,int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(list));
        } catch (Exception e) {
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
        Actions actions = new Actions(driver);
        actions.moveToElement(el).perform();
    }

    /*
     * Call this method with your element and a color like (red,green,orange etc...)
     */
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
