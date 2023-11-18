package btrust.btrustOne.admin.workflowManagement.mobileForms.pageObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.List;

public class MobileFormPage extends BasePage {


    public MobileFormPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "div.MobileFormsSettingsHeader-module__container__title__3_Rc5")
    protected WebElement mobileFormTitle;
    @FindBy(css = ".MobileFormsSettingsHeader-module__container__title__3_Rc5>button")
    protected WebElement addNewModuleButton;
    @FindBy(css = ".MuiPaper-root.MuiMenu-paper.AddNew-module__paper__Z31DX.MuiPopover-paper.MuiPaper-elevation8.MuiPaper-rounded li")
    protected List<WebElement> newModuleMenuList;
    @FindBy(css = ".MobileFormsSettingsHeader-module__container__right__1pxVd button")
    protected WebElement generalUiSettingsButton;
    @FindBy(css = " div.InputSearch-module__inputItem__2Oj7R input")
    protected WebElement searchField;
    @FindBy(css = ".TableForm-module__row__2X-Qo>:nth-child(5) button")
    protected List<WebElement> kebabMenuButton;
    @FindBy(css = "body .MuiPaper-root.MuiMenu-paper.Actions-module__paper__4vggO.MuiPopover-paper.MuiPaper-elevation8.MuiPaper-rounded li")
    protected List<WebElement> kebabMenuList;
    @FindBy(css = ".TableForm-module__row__2X-Qo>:nth-child(1)")
    protected List<WebElement> columnNameList;
    @FindBy(css = ".InputItem-module__inputItem__1O8RA>input")
    protected WebElement newModuleFieldName;
    @FindBy(css = ".FormPopup-module__actions__3dkoA> button")
    protected List<WebElement> newFormPopUpButtons;
    @FindBy(css = "div.PopupTitle-module__text__3pjTU")
    protected WebElement popUpTitle;
    @FindBy(css = "div.WarningPopup-module__actions__2bC09> button")
    protected List<WebElement> popUpButtons;
    @FindBy(css = ".MobileFormsSettingsHeader-module__container__tabs__2FY-j> :nth-child(1),.MobileFormsSettingsHeader-module__container__tabs__2FY-j> :nth-child(2)")
    protected List<WebElement> tabList;
    @FindBy(xpath = "//input[@type='file']")
    protected WebElement fileInput;



    @Step("Upload file")
    public void uploadFile(String filePath) {
        fileInput.sendKeys(filePath);
//        fileInput.submit();
    }

    @Step("Delete file from folder")
    public void deleteFileFromFolder(String filePath) throws IOException {
        Path path = FileSystems.getDefault().getPath(filePath);
        try {
            Files.delete(path);
        } catch (NoSuchFileException x) {
            System.err.format("%s: no such" + " file or directory%n", path);
        } catch (IOException x) {
            System.err.println(x);
        }
    }

    @Step("Create new file in folder")
    public void createNewFile(String filePath) throws IOException {
        File file = new File(filePath);
        file.createNewFile();

    }

    @Step("Delete form or pop-up")
    public void deletePopUp(String titleName, String buttonName) {
        waitForTextToBeInElement(popUpTitle, titleName);
        for (WebElement el : popUpButtons) {
            scrollToElement(el);
            if (getText(el).equalsIgnoreCase(buttonName)) {
                scrollToElement(el);
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
    }


    @Step("Select Action From Kebab List")
    public void selectActionFromKebabList(String formName, String actionName) {
        sleep(5000);
        scrollToElement(searchField);
        fillText(searchField, formName);
        sleep(2000);
        for (int i = 0; i < columnNameList.size(); i++) {
            scrollToElement(columnNameList.get(i));
            if (getText(columnNameList.get(i)).equalsIgnoreCase(formName)) {
                scrollToElement(kebabMenuButton.get(i));
                waitForElementToBeClickable(kebabMenuButton.get(i));
                click(kebabMenuButton.get(i));
                for (int j = 0; j < kebabMenuList.size(); j++) {
                    waitForElementToBeVisibility(kebabMenuList.get(j));
                    scrollToElement(kebabMenuList.get(j));
                    if (getText(kebabMenuList.get(j)).equalsIgnoreCase(actionName)) {
                        waitForElementToBeClickable(kebabMenuList.get(j));
                        click(kebabMenuList.get(j));
                        break;
                    }
                }
            }
        }
    }


    @Step("Check the title of the page")
    public String pageTitle() {
        waitForPageFinishLoading();
        scrollToElement(mobileFormTitle);
        waitForElementToBeVisibility(mobileFormTitle);
        return getText(mobileFormTitle);
    }


    @Step("Create new module")
    public void createNewModule(String moduleName, String newName, String buttonName) {
        waitForPageFinishLoading();
        waitForElementToBeClickable(addNewModuleButton);
        scrollToElement(addNewModuleButton);
        click(addNewModuleButton);
        for (WebElement el : newModuleMenuList) {
            waitForElementToBeClickable(el);
            if (getText(el).equalsIgnoreCase(moduleName)) {
                waitForElementToBeClickable(el);
                scrollToElement(el);
                click(el);
                fillText(newModuleFieldName, newName);
                for (WebElement el1 : newFormPopUpButtons) {
                    if (getText(el1).equalsIgnoreCase(buttonName)) {
                        scrollToElement(el1);
                        click(el1);
                        break;
                    }

                }
                break;
            }
        }
    }


    @Step("Click on General UI Settings ")
    public void clickOnGeneralUiSettings() {
        scrollToElement(generalUiSettingsButton);
        waitForElementToBeClickable(generalUiSettingsButton);
        click(generalUiSettingsButton);
    }


    @Step("search name in table and choose")
    public void searchNameAndClick(String name) {
        //      waitForPageFinishLoading();
        scrollToElement(searchField);
        fillText(searchField, name);
        sleep(2000);
        for (WebElement el : columnNameList) {
            waitForElementToBeVisibility(el);
            scrollToElement(el);
            if (getText(el).equalsIgnoreCase(name)) {
                scrollToElement(el);
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
    }

    @Step("search name in table")
    public boolean searchNameInTable(String name) {
        try {
            //       waitForPageFinishLoading();
            sleep(3000);
            scrollToElement(searchField);
            fillText(searchField, name);
            for (WebElement el : columnNameList) {
                scrollToElement(el);
                sleep(2000);
                if (getText(el).equalsIgnoreCase(name)) {
                    scrollToElement(el);
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
        }
        return false;
    }

    @Step("Choose tab for display form/Popup list")
    public void chooseTabForDisplayList(String tabName) {
        sleep(4000);
        for (WebElement el : tabList) {
            scrollToElement(el);
            if (getText(el).equalsIgnoreCase(tabName)) {
                scrollToElement(el);
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
    }



}
