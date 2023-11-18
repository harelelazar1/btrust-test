package btrust.btrustOne.admin.usersManager.users.pageObject;

import btrust.BasePage;
import com.google.common.collect.Ordering;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class UsersPage extends BasePage {
    public UsersPage(WebDriver driver) {
        super(driver);
    }

    boolean bol = false;
    ArrayList<String> integerArrayForSorting = new ArrayList<>();

    @FindBy(css = ".TableHeader-module__conteiner__uNlfl .TableHeader-module__headCell__1d08w.false")
    protected List<WebElement> listTitle;
    @FindBy(css = " .Table-module__wrapper__vQJw7 .Table-module__row__33Rxn  .Table-module__item__1iRC_.Table-module__name__35b3u")
    protected List<WebElement> numbers;
    @FindBy(css = ".SettingsHeaderFiltersAndSearch-module__wrapper__Z1s-1 > .SettingsHeaderFiltersAndSearch-module__title__WW0OL")
    protected WebElement usersTitle;
    @FindBy(css = ".SettingsHeaderFiltersAndSearch-module__title__WW0OL>.AddButton-module__btn__2_wO7")
    protected WebElement addUserButton;
    @FindBy(css = ".Table-module__wrapper__vQJw7 > div:nth-of-type(1) > div:nth-of-type(2)")
    protected List<WebElement> userListFirstNameColumn;
    @FindBy(css = ".Table-module__wrapper__vQJw7 > div:nth-of-type(1) > div:nth-of-type(3)")
    protected List<WebElement> userListLastNameColumn;
    @FindBy(css = " .Table-module__wrapper__vQJw7 .Table-module__row__33Rxn :nth-child(2)")
    protected WebElement userName;
    @FindBy(css = ".filter-item>.select-button.blue-false")
    protected List<WebElement> filterBy;
    @FindBy(css = ".select-content .items-container .item .text")
    protected List<WebElement> chooseNameFromList;
    @FindBy(css = ".Table-module__row__33Rxn > div:nth-child(4)")
    protected List<WebElement> subCompanyColumn;
    @FindBy(css = ".Table-module__row__33Rxn > div:nth-child(5)")
    protected List<WebElement> department;
    @FindBy(css = ".Table-module__row__33Rxn > div:nth-child(6)")
    protected List<WebElement> role;
    @FindBy(css = ".Table-module__row__33Rxn > div:nth-child(7)")
    protected List<WebElement> status;
    @FindBy(css = ".Table-module__row__33Rxn :nth-child(2).Table-module__item__1iRC_")
    protected List<WebElement> nameFromList;
    @FindBy(css = ".filter-item>.select-button.blue-true>.selected-elements")
    protected List<WebElement> filterByOpen;

    @Step("Check if add Users Button display")
    public boolean checkIfAddUserButtonDisplay() {
        try {
            waitForPageFinishLoading();
            scrollToElement(addUserButton);
            if (isDisplayed(addUserButton)) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
        return false;
    }


    @Step("check name in Column")
    public boolean checkNameInColumn(String columnName, String nameFromList) {
        switch (columnName) {
            case "Sub Company": {
                waitForPageFinishLoading();
                for (int i = 0; i < subCompanyColumn.size(); i++) {
                    if (!getText(subCompanyColumn.get(i)).equalsIgnoreCase(nameFromList)) {
                        waitForElementToBeVisibility(subCompanyColumn.get(i));
                        return false;
                    }
                }
                break;
            }
            case "Department": {
                waitForPageFinishLoading();
                for (int i = 0; i < department.size(); i++) {

                    if (!getText(department.get(i)).equalsIgnoreCase(nameFromList)) {
                        waitForElementToBeVisibility(department.get(i));
                        return false;
                    }
                }
                break;
            }
            case "Role": {
                waitForPageFinishLoading();
                for (int i = 0; i < role.size(); i++) {

                    if (!getText(role.get(i)).equalsIgnoreCase(nameFromList)) {
                        waitForElementToBeVisibility(role.get(i));
                        return false;
                    }
                }
                break;
            }
            case "Status": {
                waitForPageFinishLoading();
                for (int i = 0; i < status.size(); i++) {

                    if (!getText(status.get(i)).equalsIgnoreCase(nameFromList)) {
                        waitForElementToBeVisibility(status.get(i));
                        return false;
                    }
                }
                break;
            }
        }
        return true;
    }

    @Step("filtering option")
    public void filteringOption(String filterOption, String nameFromList) {
        waitForPageFinishLoading();
        sleep(5000);
        for (int i = 0; i < filterBy.size(); i++) {
            waitForPageFinishLoading();
            scrollToElement(filterBy.get(i));
            if (getText(filterBy.get(i)).contains(filterOption)) {
                waitForElementToBeClickable(filterBy.get(i));
                click(filterBy.get(i));
                for (WebElement el : chooseNameFromList) {
                    scrollToElement(el);
                    if (getText(el).equalsIgnoreCase(nameFromList)) {
                        scrollToElement(el);
                        waitForElementToBeClickable(el);
                        click(el);
                        scrollToElement(filterByOpen.get(0));
                        click(filterByOpen.get(0));
                        break;
                    }
                }
                break;
            }

        }
    }


    @Step("click")
    public void click() {
        click(userName);
    }

    @Step("search user name in user list")
    public boolean userList(String column, String userName) {
        waitForPageFinishLoading();
        switch (column) {
            case "first name": {
                for (WebElement el : userListFirstNameColumn) {
                    if (getText(el).equalsIgnoreCase(userName)) {
                        waitForElementToBeClickable(el);
                        bol = isDisplayed(el);
                        break;
                    }
                }
                break;
            }
            case "last name": {
                for (WebElement el : userListLastNameColumn) {
                    if (getText(el).equalsIgnoreCase(userName)) {
                        waitForElementToBeClickable(el);
                        bol = isDisplayed(el);
                        break;
                    }
                }
                break;
            }
        }
        return bol;
    }


    @Step("choose user from user list")
    public void chooseUserFromUserList(String userName) {
        waitForPageFinishLoading();
        for (WebElement el : nameFromList) {
            scrollToElement(el);
            if (getText(el).equalsIgnoreCase(userName)) {
                click(el);
                break;
            }
        }
    }


    @Step("Return the text of users title")
    public String usersTitle() {
        waitForElementToBeVisibility(usersTitle);
        return getText(usersTitle);
    }

    @Step("Click on add new user button")
    public void clickOnAddUserButton() {
        waitForPageFinishLoading();
        scrollToElement(addUserButton);
        waitForElementToBeClickable(addUserButton);
        clickByJS(addUserButton);
    }

    @Step("sort the data mappers numbers and check if it sorted")
    public boolean isListSorted(boolean isDescending) {
        try {
            waitForPageFinishLoading();
            integerArrayForSorting.clear();
            for (WebElement el : numbers) {
                integerArrayForSorting.add(getText(el));
            }
            waitForPageFinishLoading();
            boolean isSorted;
            if (isDescending) {
                isSorted = Ordering.natural().reverse().isOrdered(integerArrayForSorting);
            } else {
                isSorted = Ordering.natural().isOrdered(integerArrayForSorting);
            }
            waitForPageFinishLoading();
            return isSorted;
        } catch (Exception e) {
        }
        return false;
    }

    @Step("click list title")
    public void listTitle(String columnName) {
        waitForElementToBeVisibility(usersTitle);
        for (int i = 0; i < listTitle.size(); i++) {
            if (getText(listTitle.get(i)).equalsIgnoreCase(columnName)) {
                click(listTitle.get(i));
                break;
            }

        }

    }


}
