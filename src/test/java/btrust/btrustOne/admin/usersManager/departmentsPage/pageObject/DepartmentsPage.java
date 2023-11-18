package btrust.btrustOne.admin.usersManager.departmentsPage.pageObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class DepartmentsPage extends BasePage {
    public DepartmentsPage(WebDriver driver) {
        super(driver);
    }

    String departmentInfo;
    String errorMessage;
    String departmentName;
    boolean departmentListIsDisplayed;

    @FindBy(css = ".SettingsHeader-module__wrapper__2OOUN > .SettingsHeader-module__title__acJ6f")
    protected WebElement departmentTitle;
    @FindBy(css = ".SettingsHeader-module__title__acJ6f > button")
    protected WebElement addNewDepartmentButton;
    @FindBy(css = ".InputSearch-module__inputItem__2Oj7R > input")
    protected WebElement searchField;
    @FindBy(css = ".filter-item > .select-button")
    protected WebElement subCompanyFilter;
    @FindBy(css = ".items-container > .item")
    protected List<WebElement> subCompaniesList;
    @FindBy(css = ".TableHeader-module__conteiner__35XW5 > .TableHeader-module__headCell__1iV-q")
    protected List<WebElement> departmentTableTitles;
    @FindBy(css = ".Table-module__wrapper__kKA9g > :nth-child(1) > .Table-module__item__ugDWd")
    protected List<WebElement> firstDepartmentInformation;
    @FindBy(css = ".Table-module__row__2N5vI > :nth-child(2)")
    protected List<WebElement> departmentNameList;
    @FindBy(css = ".select-button > .clear.change-position")
    protected WebElement clearSubCompanyFilterButton;
    /*
    new department elements
     */
    @FindBy(css = ".PopupTitle-module__title__Khvfk > .PopupTitle-module__text__3pjTU")
    protected WebElement newDepartmentTitle;
    @FindBy(css = ".PopupTitle-module__text__3pjTU > .PopupTitle-module__icon__hcL5f")
    protected WebElement closePopupButton;
    @FindBy(css = ".NewDepartmentMiniPopup-module__inputItem__1khD_ > input")
    protected WebElement departmentNameField;
    @FindBy(css = ".default-custom-selector > .default-select")
    protected WebElement subCompanySelect;
    @FindBy(css = ".default__menu-list > .default__option")
    protected List<WebElement> subCompaniesOptions;
    @FindBy(css = ".NewDepartmentMiniPopup-module__actions__lOZxd > button")
    protected List<WebElement> buttonsList;
    /*
    error message elements
     */
    @FindBy(css = ".NewDepartmentMiniPopup-module__inputItem__1khD_ > .NewDepartmentMiniPopup-module__required__3DiEG")
    protected List<WebElement> newDepartmentTitles;
    @FindBy(css = ".NewDepartmentMiniPopup-module__inputItem__1khD_ > .ValidationError-module__container__1w8Qt")
    protected List<WebElement> errorMessageList;
    /*
    edit department
     */
    @FindBy(css = ".MuiIconButton-label [type=checkbox]")
    protected List<WebElement> checkBoxButton;
    @FindBy(css = ".TopBar-module__title__1eD61 .EditTitleInput-module__edit__2ZSDr")
    protected List<WebElement> editDepartmentNameButton;


    @Step("check if Department Edit Name Button Display")
    public boolean checkIfDepartmentEditNameButtonDisplay() {
        try {
            waitForPageFinishLoading();
            for (WebElement el : editDepartmentNameButton) {
                scrollToElement(el);
                if (isDisplayed(el)) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
        return false;
    }


    @Step("check if checkbox field editable")
    public boolean checkboxFieldEditable() {
        try {
            waitForPageFinishLoading();
            for (WebElement el : checkBoxButton) {
                scrollToElement(el);
                if (isEnabled(el)) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
        return false;
    }


    @Step("choose user from user list")
    public void chooseDepartFromUserList(String departmentName) {
        waitForPageFinishLoading();
        for (WebElement el : departmentNameList) {
            scrollToElement(el);
            if (getText(el).equalsIgnoreCase(departmentName)) {
                click(el);
                break;
            }
        }
    }


    @Step("Return the text of department title")
    public String departmentTitle() {
        waitForElementToBeVisibility(departmentTitle);
        return getText(departmentTitle);
    }

    @Step("Click on add new department button")
    public void addNewDepartmentButton() {
        waitForPageFinishLoading();
        scrollToElement(addNewDepartmentButton);
        waitForElementToBeClickable(addNewDepartmentButton);
        click(addNewDepartmentButton);
    }

    @Step("check if add new department button display")
    public boolean checkIfAddNewDepartmentButtonDisplay() {
        try {
            waitForPageFinishLoading();
            if (isDisplayed(addNewDepartmentButton)) {
                scrollToElement(addNewDepartmentButton);
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
        return false;
    }

    @Step("Type in search field")
    public void searchField(String value) {
        waitForElementToBeClickable(searchField);
        fillText(searchField, value);
    }

    @Step("Click on clear sub company filter button")
    public void clearSubCompanyFilterButton() {
        waitForElementToBeClickable(clearSubCompanyFilterButton);
        click(clearSubCompanyFilterButton);
    }

    @Step("Filter the department table by sub company")
    public void filterBySubCompany(String subCompany) {
        waitForElementToBeClickable(subCompanyFilter);
        click(subCompanyFilter);
        waitForList(subCompaniesList);
        for (WebElement el : subCompaniesList) {
            if (getText(el).equalsIgnoreCase(subCompany)) {
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
    }

    @Step("Fill details in new department popup")
    public void fillDetailsNewDepartment(String title, String departmentName, String subCompany) {
        waitForTextToBeInElement(newDepartmentTitle, title);
        waitForElementToBeClickable(departmentNameField);
        fillText(departmentNameField, departmentName);
        waitForElementToBeClickable(subCompanySelect);
        click(subCompanySelect);
        waitForList(subCompaniesOptions);
        for (WebElement el : subCompaniesOptions) {
            if (getText(el).equalsIgnoreCase(subCompany)) {
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
    }

    @Step("Click on button")
    public void clickOnButton(String button) {
        waitForList(buttonsList);
        for (WebElement el : buttonsList) {
            if (getText(el).equalsIgnoreCase(button)) {
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
    }

    @Step("Click on close popup button")
    public void closePopupButton() {
        waitForElementToBeClickable(closePopupButton);
        click(closePopupButton);
    }

    @Step("Return the information that appear in the first departmant that appear in teh department table")
    public String firstDepartment(String title) {
        waitForPageFinishLoading();
        waitForElementToBeVisibility(departmentTableTitles.get(0));
        for (int i = 0; i <= departmentTableTitles.size(); i++) {
            if (getText(departmentTableTitles.get(i)).equalsIgnoreCase(title)) {
                scrollToElement(firstDepartmentInformation.get(i));
                departmentInfo = getText(firstDepartmentInformation.get(i));
                break;
            }
        }
        return departmentInfo;
    }

    @Step("Return on all the department names that appear in the department list")
    public String departmentNames(String name) {
        waitForPageFinishLoading();
        waitForList(departmentNameList);
        waitForElementToBeVisibility(departmentNameList.get(0));
        for (WebElement el : departmentNameList) {
            if (getText(el).contains(name)) {
                waitForElementToBeVisibility(el);
                departmentName = getText(el);
                break;
            }
        }
        return departmentName;
    }

    @Step("Return the value that appear in the department mane field")
    public String departmentNameFiledValue() {
        waitForElementToBeVisibility(departmentNameField);
        return getAttribute(departmentNameField, "value");
    }

    @Step("Return the error message that displyed under the title: {title}")
    public String errorMessage(String title) {
        String errorMessage = null;
        try {
            waitForList(newDepartmentTitles);
            for (int i = 0; i <= newDepartmentTitles.size(); i++) {
                scrollToElement(newDepartmentTitles.get(i));
                if (getText(newDepartmentTitles.get(i)).equalsIgnoreCase(title)) {
                    scrollToElement(errorMessageList.get(i));
                    errorMessage = getText(errorMessageList.get(i));
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
      return errorMessage;
    }

    @Step("Check if department list is displayed")
    public boolean departmentListIsDisplayed() {
        departmentListIsDisplayed = true;
        try {
            waitForPageFinishLoading();
            waitForList(departmentNameList);
            waitForElementToBeVisibility(departmentNameList.get(0));
            isDisplayed(departmentNameList.get(0));
            return departmentListIsDisplayed;
        } catch (Exception e) {
            return false;
        }
    }
}
