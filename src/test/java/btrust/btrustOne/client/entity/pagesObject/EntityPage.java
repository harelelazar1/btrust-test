package btrust.btrustOne.client.entity.pagesObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class EntityPage extends BasePage {
    public EntityPage(WebDriver driver) {
        super(driver);
    }

    String value;
    String contactName;

    @FindBy(css = ".Sidebar-module__container__1mjaR > .CompanyTitle-module__container__1kEQV > .CompanyTitle-module__data__Q4fMS > :nth-child(1)")
    protected WebElement entityName;
    @FindBy(css = ".CompanyInfo-module__container__2l5vc > li > :nth-child(1)")
    protected List<WebElement> entityInfoKeys;
    @FindBy(css = ".CompanyInfo-module__container__2l5vc > li > :nth-child(2)")
    protected List<WebElement> entityInfoValues;
    @FindBy(css = "ul.SidebarMenu-module__menu__11HOn >.SidebarMenu-module__menuItem__3d00o")
    protected List<WebElement> sideBarList;
    @FindBy(css = "div .SidebarMenu-module__menuItem__3d00o.SidebarMenu-module__submenu__j7-pB>div")
    protected List<WebElement> sideBarBusinessCategoryList;
    @FindBy(css = "ul > a.SidebarMenu-module__menuItem__3d00o.SidebarMenu-module__submenu__j7-pB> button")
    protected List<WebElement> sideBarBusinessCategoryPlusButtonList;
    @FindBy(xpath = "/html/body//div[3]/ul/li")
    protected List<WebElement> chooseActionFromList;
    @FindBy(css = ".ContactPopup-module__title__3x6jv [type='button']")
    protected WebElement closePopup;
    @FindBy(css = ".Data-module__wrapper__1rRo8 .ExpandPanelEntity-module__toggler__18yi8")
    protected List<WebElement> dataTabList;
    @FindBy(css = ".RoundedWhiteBox-module__container__myicl > .RoundedWhiteBox-module__title__T2HzX")
    protected List<WebElement> dataTitles;
    @FindBy(css = "div .IdentificationData-module__item__3CDQf > :nth-child(1)")
    protected List<WebElement> identificationDataTitles;
    @FindBy(css = ".IdentificationData-module__identData__2zJEx > .IdentificationData-module__item__3CDQf > :nth-child(2)")
    protected List<WebElement> identificationDataValues;
    @FindBy(css = ".Tables-module__header__1NAxG > .Tables-module__headCell__1kjLj")
    protected List<WebElement> addressTitles;
    @FindBy(css = ".Tables-module__row__3Lpwg > .Tables-module__item__1tFw1")
    protected List<WebElement> addressValues;
    @FindBy(css = ".Contacts-module__header__TsVJh > .Contacts-module__headCell__3FTAG")
    protected List<WebElement> contactInformationTitles;
    @FindBy(css = ".Contacts-module__row__1F2El > .Contacts-module__item__3gxiG")
    protected List<WebElement> contactInformationValues;
    @FindBy(css = ".RoundedWhiteBox-module__action__34eaF > button")
    protected WebElement newContactButton;
    @FindBy(css = ".Contacts-module__row__1F2El  .MuiButtonBase-root.MuiButton-root.MuiButton-text")
    protected WebElement contactHamburgerMenuButton;
    @FindBy(css = ".MuiList-root.MuiMenu-list.MuiList-padding .MuiListItem-button")
    protected List<WebElement> chooseFromHamburgerMenu;

    /*
    Beneficial Owner
     */
    @FindBy(css = ".EntityTabPage-module__wrapper__2QfPC  .EntityTabPage-module__title___Z9_n")
    protected WebElement beneficialOwnerPageTitle;
    @FindBy(css = ".Table-module__row__25WU2  .Table-module__item__1cbS5")
    protected List<WebElement> firstBeneficialOwnerInformation;
    @FindBy(css = ".EntityTabPage-module__wrapper__2QfPC .Table-module__row__25WU2 :nth-child(1)")
    protected List<WebElement> beneficialOwnerRow;
    /*

    Mandates Tab
     */
    @FindBy(css = ".TableHeader-module__wrapper__3r1tI > [role=tabpanel]")
    protected List<WebElement> mandatesTableTitles;
    @FindBy(css = ".Table-module__container___xp3e > [role='tabpanel'] > .Table-module__item__25wbZ")
    protected List<WebElement> firstMandateInformation;
    @FindBy(css = ".TopBar-module__btn__1wEIX[type =button]")
    protected WebElement createNewMandateButton;
    /*
    Cases Tab
     */
    @FindBy(css = ".TopBar-module__container__1u32G> .TopBar-module__title__3VsV9")
    protected WebElement casesListTitle;
    @FindBy(css = ".TableHeader-module__wrapper__3WBi- > .TableHeader-module__headCell__3syyJ")
    protected List<WebElement> casesTableTitles;
    @FindBy(css = ".Table-module__row__3OVEo > .Table-module__item__2BSnR")
    protected List<WebElement> firstCaseId;
    @FindBy(css = ".TopBar-module__container__1u32G .TopBar-module__btn__KQHWK")
    protected WebElement createNewCaseButton;
    @FindBy(css = ".Table-module__row__3OVEo :nth-child(3) .Table-module__open__3XI6o")
    protected List<WebElement> casesListStatus;
    @FindBy(css = ".Table-module__row__3OVEo :nth-child(2)")
    protected List<WebElement> casesListWorkflow;
    /*
     Risk Profile Tab
     */
    @FindBy(css = ".Title-module__container__3pbeQ  .Title-module__title__1Mx3r")
    protected WebElement riskProfileTitle;
    @FindBy(css = ".RiskProfileItem-module__row__-VQ34> :nth-child(1) .RiskProfileItem-module__value__37Eh- ")
    protected List<WebElement> nameList;
    @FindBy(css = ".RiskProfile-module__wrapper__31mK8 .RiskProfileItem-module__row__-VQ34")
    protected List<WebElement> numberOfRowsList;
    /*
     Linked Entities Tab
     */
    @FindBy(css = "div.PopupTitle-module__text__3pjTU")
    protected WebElement popUpTitle;
    @FindBy(css = ".InputSearch-module__inputItem__2JwLp >input")
    protected WebElement popUpSearchField;
    @FindBy(css = ".SearchPopup-module__selectors__h0xol>div>.SearchPopup-module__text__3d51P,.SearchPopup-module__selectors__h0xol .SelectableRow-module__row__2ntnq.false> :nth-child(2)>:nth-child(1)")
    protected List<WebElement> popUpContactName;
    @FindBy(css = ".SearchPopup-module__selectors__h0xol .SelectableRow-module__row__2ntnq.false> :nth-child(2)>:nth-child(2)")
    protected List<WebElement> popUpContactId;
    @FindBy(css = ".SearchPopup-module__selectors__h0xol .SelectableRow-module__row__2ntnq.false>:nth-child(2)>:nth-child(2) span")
    protected List<WebElement> popUpCheckLinkedList;
    @FindBy(css = ".SearchPopup-module__selectors__h0xol [role='button'] >:nth-child(1)")
    protected List<WebElement> popUpContactCheckboxList;
    @FindBy(css = ".SearchPopup-module__actions__2Hkvx> button")
    protected List<WebElement> popUpChooseButton;
    @FindBy(css = ".LinkedConfirmation-module__actions__3dOK0 >button")
    protected List<WebElement> linkedClientConfirmationChooseButton;
    @FindBy(css = " .PopupBottomButtons-module__container__20krY>button")
    protected List<WebElement> linkingEntityChooseButton;
    @FindBy(css = ".EntityItemPage-module__wrapper__1tdV_ >:nth-child(3)>div>:nth-child(3)")
    protected List<WebElement> linkedEntitiesNameList;
    @FindBy(css = ".DotsMenu-module__menu__Bq0FO>button[type='button']")
    protected List<WebElement> linkedEntitiesKebabButtonList;
    @FindBy(css = ".MuiPaper-root.MuiMenu-paper.MuiPopover-paper.DotsMenu-module__paper__3iWPP>ul[role='menu']>li")
    protected List<WebElement> disconnectedLink;


    @Step("Check the Linked entities name inside the Linked Entities tab")
    public boolean checkLinkedEntitiesToMainEntity() {
        sleep(4000);
//        waitForElementToBeVisibility(linkedEntitiesNameList.get(0));
        for (WebElement el : linkedEntitiesNameList) {
            scrollToElement(el);
            if (getText(el).equalsIgnoreCase(contactName)) {
                waitForElementToBeClickable(el);
                click(el);
                return true;
            }
        }
        return false;
    }


    @Step("Choose to Disconnect Linked entity")
    public void chooseToDisconnectLinkEntity() {
        waitForElementToBeVisibility(linkedEntitiesNameList.get(0));
        for (int i = 0; i < linkedEntitiesNameList.size(); i++) {
            scrollToElement(linkedEntitiesNameList.get(i));
            if (getText(linkedEntitiesNameList.get(i)).equalsIgnoreCase(contactName)) {
                scrollToElement(linkedEntitiesKebabButtonList.get(i));
                waitForElementToBeClickable(linkedEntitiesKebabButtonList.get(i));
                click(disconnectedLink.get(i));
                break;
            }
        }
    }


    @Step("Popup Search and Add Clients to connect")
    public void searchAndAddClientToConnect(String titleName, String clientName, String contactNameFromList, String checkLinked, String popupButtonName, String linkedClientButtonName, String linkingEntityButtonName) {
        waitForTextToBeInElement(popUpTitle, titleName);
        fillText(popUpSearchField, clientName);
        chooseClientToConnect(contactNameFromList, checkLinked, popupButtonName, linkedClientButtonName, linkingEntityButtonName);
    }


    @Step("Popup Search Clients to connect")
    public void chooseClientToConnect(String contactNameFromList, String checkLinked, String popupButtonName, String linkedClientButtonName, String linkingEntityButtonName) {
        try {
            waitForList(popUpContactName);
            for (int i = 1; i < popUpContactName.size(); i++) {
                String currentContactName = getText(popUpContactName.get(i));
                if (currentContactName.contains(contactNameFromList)) {
                    contactName = getText(popUpContactName.get(i));
                    scrollToElement(popUpContactCheckboxList.get(i));
                    waitForElementToBeClickable(popUpContactCheckboxList.get(i));
                    click(popUpContactCheckboxList.get(i));
                    chooseButtonFromPopup(popupButtonName);
                    if (getText(popUpTitle).contains(checkLinked)) {
                        chooseButtonFromLinkedClientConfirmation(linkedClientButtonName);
                        return;
                    }
                    linkingEntityPopupChooseButton(linkingEntityButtonName);
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    @Step("Choose buttons from popup Search Clients to Connect")
    public void chooseButtonFromPopup(String buttonName) {
        waitForElementToBeVisibility(popUpChooseButton.get(0));
        for (WebElement el : popUpChooseButton) {
            scrollToElement(el);
            if (getText(el).equalsIgnoreCase(buttonName)) {
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
    }

    @Step("Choose buttons from popup linked client confirmation(entity already linked)")
    public void chooseButtonFromLinkedClientConfirmation(String buttonName) {
        waitForList(linkedClientConfirmationChooseButton);
        for (WebElement el : linkedClientConfirmationChooseButton) {
            scrollToElement(el);
            if (getText(el).equalsIgnoreCase(buttonName)) {
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
    }


    @Step("Choose buttons from popup linking entity")
    public void linkingEntityPopupChooseButton(String buttonName) {
        waitForList(linkingEntityChooseButton);
        for (WebElement el : linkingEntityChooseButton) {
            scrollToElement(el);
            if (getText(el).equalsIgnoreCase(buttonName)) {
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
    }


    @Step("Choose Business Category from side bar menu : {option}")
    public void chooseBusinessCategoryFromSideBar(String option, String businessCategory, String action) {
        try {
            chooseFromSideBar(option);
            waitForList(sideBarBusinessCategoryList);
            for (int i = 0; i < sideBarBusinessCategoryList.size(); i++) {
                waitForElementToBeVisibility(sideBarBusinessCategoryList.get(i));
                scrollToElement(sideBarBusinessCategoryList.get(i));
                if (getText(sideBarBusinessCategoryList.get(i)).equalsIgnoreCase(businessCategory)) {
                    scrollToElement(sideBarBusinessCategoryPlusButtonList.get(i));
                    waitForElementToBeClickable(sideBarBusinessCategoryPlusButtonList.get(i));
                    click(sideBarBusinessCategoryPlusButtonList.get(i));
                    sleep(2000);
                    for (WebElement el : chooseActionFromList) {
                        scrollToElement(el);
                        if (getText(el).equalsIgnoreCase(action)) {
                            waitForElementToBeClickable(el);
                            click(el);
                            break;
                        }
                    }
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    public void chooseValueFromList(String value) {
        waitForPageFinishLoading();
        WebElement el = driver.findElement(By.xpath(String.format("//*[text()='%s']", value)));
        click(el);
    }

    @Step("Return the info of entity")
    public int numbersOfRows() {
        waitForList(numberOfRowsList);
        return numberOfRowsList.size();
    }


    @Step("Return the info of entity")
    public int numberOfRows() {
        int numbers = 0;
//        waitForElementToBeVisibility(numberOfRowsList.get(0));
        waitForPageFinishLoading();
        for (int i = 0; i < numberOfRowsList.size(); i++) {
            scrollToElement(numberOfRowsList.get(i));
            ++numbers;
        }
        System.out.println(numbers);
        return numbers;
    }


    @Step("Return the name of entity")
    public String entityName() {
        waitForPageFinishLoading();
        scrollToElement(entityName);
        waitForElementToBeVisibility(entityName);
        return getText(entityName);
    }

    @Step("Return the info of entity")
    public String entityInformation(String key, String value) {
        waitForPageFinishLoading();
        scrollToElement(entityInfoKeys.get(0));
        waitForElementToBeVisibility(entityInfoKeys.get(0));
        for (int i = 0; i <= entityInfoKeys.size(); i++) {
            scrollToElement(entityInfoKeys.get(i));
            if (getText(entityInfoKeys.get(i)).contains(key)) {
                waitForList(entityInfoValues);
                if (getText(entityInfoValues.get(i)).equalsIgnoreCase(value)) {
                    value = getText(entityInfoValues.get(i));
                    break;
                }
            }
        }
        return value;
    }

    @Step("Choose from side bar menu : {option}")
    public void chooseFromSideBar(String option) {
        waitForList(sideBarList);
        for (WebElement el : sideBarList) {
            scrollToElement(el);
            if (getText(el).equalsIgnoreCase(option)) {
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
    }

    @Step("Return the get text of title : {title}")
    public String dataTitles(String title) {
        waitForList(dataTitles);
        for (int i = 0; i <= dataTitles.size(); i++) {
            if (getText(dataTitles.get(i)).contains(title)) {
                waitForTextToBeInElement(dataTitles.get(i), title);
                value = getText(dataTitles.get(i));
                break;
            }
        }
        return value;
    }

    @Step("Return the value: {value} that appear under the title: {title}")
    public String identificationData(String title, String value) {
        String result = null;
        waitForList(identificationDataTitles);
        for (int i = 0; i < identificationDataTitles.size(); i++) {
            if (getText(identificationDataTitles.get(i)).contains(title)) {
                result = getText(identificationDataValues.get(i));
                break;
            }
        }
        return result;
    }

    @Step("Return the value: {value} that appear under the title: {title}")
    public String addressData(String title, String value) {
        waitForList(addressTitles);
        for (int i = 0; i < addressTitles.size(); i++) {
            if (getText(addressTitles.get(i)).equalsIgnoreCase(title)) {
                waitForList(addressValues);
                if (getText(addressValues.get(i)).equalsIgnoreCase(value)) {
                    waitForElementToBeVisibility(addressValues.get(i));
                    value = getText(addressValues.get(i));
                    break;
                }
            }
        }
        return value;
    }

    @Step("Return the value: {value} that appear under the title: {title}")
    public String contactInformationData(String title, String value) {
        waitForList(contactInformationTitles);
        for (int i = 0; i <= contactInformationTitles.size(); i++) {
            if (getText(contactInformationTitles.get(i)).equalsIgnoreCase(title)) {
                if (getText(contactInformationValues.get(i)).contains(value)) {
                    waitForElementToBeVisibility(contactInformationValues.get(i));
                    value = getText(contactInformationValues.get(i));
                    break;
                }
            }
        }
        return value;
    }

    @Step("Return the value: {value} that appear under the title: {title}")
    public String TEST(String title, String value) {
        waitForList(contactInformationTitles);
        for (int i = 0; i <= contactInformationTitles.size(); i++) {
            if (getText(contactInformationTitles.get(i)).equalsIgnoreCase(title)) {
                waitForElementToBeVisibility(contactInformationValues.get(i));
                value = getText(contactInformationValues.get(i));
                break;
            }
        }
        return value;
    }

    @Step("Click on new contact button")
    public void newContactButton() {
        waitForElementToBeClickable(newContactButton);
        click(newContactButton);
    }

    @Step("Return the first mandate information")
    public String firstMandateInformation(String title) {
        waitForPageFinishLoading();
        waitForElementToBeVisibility(mandatesTableTitles.get(0));
        if (mandatesTableTitles.isEmpty()) {
            return value;
        } else {
            for (int i = 0; i < mandatesTableTitles.size(); i++) {
                scrollToElement(mandatesTableTitles.get(i));
                if (getText(mandatesTableTitles.get(i)).equalsIgnoreCase(title)) {
                    waitForElementToBeVisibility(firstMandateInformation.get(i));
                    value = getText(firstMandateInformation.get(i));
                    break;
                }
            }
        }
        return value;
    }

    @Step("Return the first case information")
    public String firstCaseId(String title) {
        waitForPageFinishLoading();
        waitForElementToBeVisibility(casesTableTitles.get(0));
        for (int i = 0; i <= casesTableTitles.size(); i++) {
            if (getText(casesTableTitles.get(i)).equalsIgnoreCase(title)) {
                waitForElementToBeVisibility(firstCaseId.get(i));
                value = getText(firstCaseId.get(i));
                break;
            }
        }
        return value;
    }

    @Step("Click on create new mandate button")
    public void createNewMandateButton() {
        waitForElementToBeClickable(createNewMandateButton);
        click(createNewMandateButton);
    }

    @Step("Return the text of case list title")
    public String casesListTitle() {
        waitForPageFinishLoading();
        scrollToElement(casesListTitle);
        return getText(casesListTitle);
    }

    @Step("Click on create new case button")
    public void createNewCaseButton() {
        waitForPageFinishLoading();
        scrollToElement(createNewCaseButton);
        click(createNewCaseButton);
    }

    @Step("Click on case from cases list by status name")
    public void chooseCaseFromCasesListByStatusName(String Status) {
        waitForPageFinishLoading();
        for (WebElement el : casesListStatus) {
            if (getText(el).equalsIgnoreCase(Status)) {
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
    }

    @Step("Click on case from cases list ")
    public void chooseFromCasesListByWorkflow(String workflow) {
        waitForElementToBeClickable(casesListWorkflow.get(0));
        for (WebElement el : casesListWorkflow) {
            if (getText(el).equalsIgnoreCase(workflow)) {
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
    }


    @Step("check if 'New Contact' button display")
    public boolean checkNewContactButtonDisplay() {
        try {
            waitForPageFinishLoading();
            sleep(5000);

            if (isDisplayed(newContactButton)) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }


    @Step("check hamburger Menu Button of 'contact information'")
    public boolean checkHamburgerMenuButtonDisplay(String nameFromMenu) {
        try {
            waitForPageFinishLoading();
            if (contactHamburgerMenuButton.isDisplayed()) {
                scrollToElement(contactHamburgerMenuButton);
                click(contactHamburgerMenuButton);
                for (WebElement el : chooseFromHamburgerMenu) {
                    if (getText(el).contains(nameFromMenu)) {
                        scrollToElement(el);
                        waitForElementToBeClickable(el);
                        click(chooseFromHamburgerMenu.get(0));
                        waitForElementToBeClickable(closePopup);
                        click(closePopup);
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }


    @Step("check 'Create New Case' button display")
    public boolean checkCreateNewCaseButtonDisplay(String buttonName) {
        try {
            waitForPageFinishLoading();
            if (getText(createNewCaseButton).contains(buttonName)) {
                waitForElementToBeClickable(createNewCaseButton);
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }


    @Step("Return the get text of title : {title}")
    public String benficialOwnerTitles(String title) {
        String value = null;
        waitForPageFinishLoading();
        if (getText(beneficialOwnerPageTitle).equalsIgnoreCase(title)) {
            waitForElementToBeClickable(beneficialOwnerPageTitle);
            value = getText(beneficialOwnerPageTitle);
        }
        return value;
    }

    @Step("Benficial owner rows")
    public void benficialOwnerRows(String idField) {
        waitForPageFinishLoading();
        for (WebElement el : beneficialOwnerRow) {
            if (getText(el).equalsIgnoreCase(idField)) {
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
    }

}






