package btrust.idm.pageObject.questionnaireForm;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

public class ContactInformationPage extends BasePage {
    public ContactInformationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".ContactInformation_contactInformation__3qU-J > .ContactInformation_title__2LxPB")
    protected WebElement contactInfoTitle; // contact info header
    @FindBy(css = ".BrokerQuestionnaire_tabItemContainer__1QBPt .Alert_container__1o01c")
    protected WebElement commentToClientInContactInfoTab;

    //contact info
    @FindBy(css = "input[name='tab_1_companyName']")
    protected WebElement companyName;
    @FindBy(css = "input[name='tab_1_leiCode']")
    protected WebElement leiCode;
    @FindBy(css = "input[name='tab_1_address']")
    protected WebElement companyAddress;
    @FindBy(css = "div:nth-of-type(4) > div[role='combobox'] button[title='Open']")
    protected WebElement countryArrowButton; //country arrow button
    @FindBy(css = "ul[role='listbox'] > li")
    protected List<WebElement> countryList; // the country list
    @FindBy(css = ":nth-of-type(4) [aria-invalid]")
    protected WebElement countryField; // the final text box for the country
    @FindBy(css = ".ContactInformation_contactInformation__3qU-J > :nth-child(2) > :nth-child(5) > :nth-child(2)")
    protected WebElement citySelect; //city arrow button
    @FindBy(css = ".MuiPaper-root > ul > li")
    protected List<WebElement> cityList; // the city list
    @FindBy(css = ":nth-of-type(5) [aria-invalid]")
    protected WebElement cityField; // the final text box for the city
    @FindBy(css = "input[name='tab_1_postalCode']")
    protected WebElement postalCode;


    // Contacts
    @FindBy(css = "input[name='tab_1_contacts_firstName']")
    protected WebElement firstNameContact;
    @FindBy(css = "input[name='tab_1_contacts_lastName']")
    protected WebElement lastNameContact;
    @FindBy(css = "input[name='tab_1_contacts_position']")
    protected WebElement positionContact;
    @FindBy(css = "input[name='tab_1_contacts_email']")
    protected WebElement emailContact;
    @FindBy(css = ".ContactInformation_container__3bJKI > :nth-child(2) > :nth-child(2) > :nth-child(4) [role='button']")
    protected WebElement businessPhoneAreaCodeContactsSelect;
    @FindBy(css = ".ContactInformation_container__3bJKI > :nth-child(2) > :nth-child(2) > :nth-child(4) .country-list > li")
    protected List<WebElement> businessPhoneAreaCodeContactsList;
    @FindBy(css = ".ContactInformation_container__3bJKI > :nth-child(2) > :nth-child(2) > :nth-child(4) input")
    protected WebElement businessPhoneContactsField;
    @FindBy(css = ".ContactInformation_container__3bJKI > :nth-child(2) > :nth-child(2) > :nth-child(5) [role='button']")
    protected WebElement secondPhoneAreaCodeContactsSelect;
    @FindBy(css = ".ContactInformation_container__3bJKI > :nth-child(2) > :nth-child(2) > :nth-child(5) .country-list > li")
    protected List<WebElement> secondPhoneAreaCodeContactsList;
    @FindBy(css = ".ContactInformation_container__3bJKI > :nth-child(2) > :nth-child(2) > :nth-child(5) input")
    protected WebElement secondPhoneAreaCodeContactsField;
    @FindBy(css = ".ContactInformation_container__3bJKI > :nth-child(2) > :nth-child(2) > :nth-child(6) [role='button']")
    protected WebElement faxNumberAreaCodeContactsSelect;
    @FindBy(css = ".ContactInformation_container__3bJKI > :nth-child(2) > :nth-child(2) > :nth-child(6) .country-list > li")
    protected List<WebElement> faxNumberAreaCodeContactsList;
    @FindBy(css = ".ContactInformation_container__3bJKI > :nth-child(2) > :nth-child(2) > :nth-child(6) input")
    protected WebElement faxNumberAreaCodeContactsField;

    //Call back referent 1
    @FindBy(css = "input[name='tab_1_contact_ref_1_firstName']")
    protected WebElement firstNameReferent1;
    @FindBy(css = "input[name='tab_1_contact_ref_1_lastName']")
    protected WebElement lastNameReferent1;
    @FindBy(css = "input[name='tab_1_contact_ref_1_position']")
    protected WebElement positionReferent1;
    @FindBy(css = "input[name='tab_1_contact_ref_1_email']")
    protected WebElement emailReferent1;
    @FindBy(css = ".ContactInformation_container__3bJKI > :nth-child(3) > :nth-child(2) > :nth-child(6) [role='button']")
    protected WebElement businessPhoneAreaCodeCallBackReferent1Select;
    @FindBy(css = ".ContactInformation_container__3bJKI > :nth-child(3) > :nth-child(2) > :nth-child(6) .country-list > li")
    protected List<WebElement> businessPhoneAreaCodeCallBackReferent1List;
    @FindBy(css = ".ContactInformation_container__3bJKI > :nth-child(3) > :nth-child(2) > :nth-child(6) input")
    protected WebElement businessPhoneAreaCodeCallBackReferent1Field;
    @FindBy(css = ".ContactInformation_container__3bJKI > :nth-child(3) > :nth-child(2) > :nth-child(7) [role='button']")
    protected WebElement secondPhoneAreaCodeCallBackReferent1Select;
    @FindBy(css = ".ContactInformation_container__3bJKI > :nth-child(3) > :nth-child(2) > :nth-child(7) .country-list > li")
    protected List<WebElement> secondPhoneAreaCodeCallBackReferent1List;
    @FindBy(css = ".ContactInformation_container__3bJKI > :nth-child(3) > :nth-child(2) > :nth-child(7) input")
    protected WebElement secondPhoneAreaCodeCallBackReferent1Field;
    @FindBy(css = ".ContactInformation_container__3bJKI > :nth-child(3) > :nth-child(2) > :nth-child(8) [role='button']")
    protected WebElement faxNumberAreaCodeCallBackReferent1Select;
    @FindBy(css = ".ContactInformation_container__3bJKI > :nth-child(2) > :nth-child(2) > :nth-child(8) .country-list > li")
    protected List<WebElement> faxNumberAreaCodeCallBackReferent1List;
    @FindBy(css = ".ContactInformation_container__3bJKI > :nth-child(3) > :nth-child(2) > :nth-child(8) input")
    protected WebElement faxNumberAreaCodeCallBackReferent1Field;

    //Call back referent 2
    @FindBy(css = "input[name='tab_1_contact_ref_2_firstName']")
    protected WebElement firstNameReferent2;
    @FindBy(css = "input[name='tab_1_contact_ref_2_lastName']")
    protected WebElement lastNameReferent2;
    @FindBy(css = "input[name='tab_1_contact_ref_2_position']")
    protected WebElement positionReferent2;
    @FindBy(css = "input[name='tab_1_contact_ref_2_email']")
    protected WebElement emailReferent2;
    @FindBy(css = ".ContactInformation_container__3bJKI > :nth-child(3) > :nth-child(4) > :nth-child(5) [role='button']")
    protected WebElement businessPhoneAreaCodeCallBackReferent2Select;
    @FindBy(css = ".ContactInformation_container__3bJKI > :nth-child(3) > :nth-child(4) > :nth-child(5) .country-list > li")
    protected List<WebElement> businessPhoneAreaCodeCallBackReferent2List;
    @FindBy(css = ".ContactInformation_container__3bJKI > :nth-child(3) > :nth-child(4) > :nth-child(5) input")
    protected WebElement businessPhoneAreaCodeCallBackReferent2Field;
    @FindBy(css = ".ContactInformation_container__3bJKI > :nth-child(3) > :nth-child(4) > :nth-child(6) [role='button']")
    protected WebElement secondPhoneAreaCodeCallBackReferent2Select;
    @FindBy(css = ".ContactInformation_container__3bJKI > :nth-child(3) > :nth-child(4) > :nth-child(6) .country-list > li")
    protected List<WebElement> secondPhoneAreaCodeCallBackReferent2List;
    @FindBy(css = ".ContactInformation_container__3bJKI > :nth-child(3) > :nth-child(4) > :nth-child(6) input")
    protected WebElement secondPhoneAreaCodeCallBackReferent2Field;
    @FindBy(css = ".ContactInformation_container__3bJKI > :nth-child(3) > :nth-child(4) > :nth-child(7) [role='button']")
    protected WebElement faxNumberAreaCodeCallBackReferent2Select;
    @FindBy(css = ".ContactInformation_container__3bJKI > :nth-child(3) > :nth-child(4) > :nth-child(7) .country-list > li")
    protected List<WebElement> faxNumberAreaCodeCallBackReferent2List;
    @FindBy(css = ".ContactInformation_container__3bJKI > :nth-child(3) > :nth-child(4) > :nth-child(7) input")
    protected WebElement faxNumberAreaCodeCallBackReferent2Field;

    /*
    Error message
    */
    @FindBy(css = ".ContactInformation_contactInformation__3qU-J > :nth-child(2) > :nth-child(1) > .LabelError_container__b1ltp")
    protected WebElement errorMessageCompanyName;
    @FindBy(css = ".ContactInformation_contactInformation__3qU-J > :nth-child(2) > :nth-child(2) > .LabelError_container__b1ltp")
    protected WebElement errorMessageLEICode;
    @FindBy(css = ".ContactInformation_contactInformation__3qU-J > :nth-child(2) > :nth-child(3) > .LabelError_container__b1ltp")
    protected WebElement errorMessageAddress;
    @FindBy(css = ".ContactInformation_contactInformation__3qU-J > :nth-child(2) > :nth-child(6) > .LabelError_container__b1ltp")
    protected WebElement errorMessagePostalCode;
    @FindBy(css = ".ContactInformation_container__3bJKI > :nth-child(2) > :nth-child(2) > :nth-child(1) > .LabelError_container__b1ltp")
    protected WebElement errorMessageFirstNameContacts;
    @FindBy(css = ".ContactInformation_container__3bJKI > :nth-child(2) > :nth-child(2) > :nth-child(2) > .LabelError_container__b1ltp")
    protected WebElement errorMessageLastNameContacts;
    @FindBy(css = ".ContactInformation_container__3bJKI > :nth-child(2) > :nth-child(2) > :nth-child(3) > .LabelError_container__b1ltp")
    protected WebElement errorMessagePositionContacts;
    @FindBy(css = ".ContactInformation_container__3bJKI > :nth-child(2) > :nth-child(2) > :nth-child(4) > .LabelError_container__b1ltp")
    protected WebElement errorMessageBusinessPhoneContacts;
    @FindBy(css = ".ContactInformation_container__3bJKI > :nth-child(2) > :nth-child(2) > :nth-child(7) > .LabelError_container__b1ltp")
    protected WebElement errorMessageEmailContacts;
    @FindBy(css = ".ContactInformation_container__3bJKI > :nth-child(3) > :nth-child(2) > :nth-child(3) > .LabelError_container__b1ltp")
    protected WebElement errorMessageFirstNameContactsCallBackReferent1;
    @FindBy(css = ".ContactInformation_container__3bJKI > :nth-child(3) > :nth-child(2) > :nth-child(4) > .LabelError_container__b1ltp")
    protected WebElement errorMessageLastNameContactsCallBackReferent1;
    @FindBy(css = ".ContactInformation_container__3bJKI > :nth-child(3) > :nth-child(2) > :nth-child(5) > .LabelError_container__b1ltp")
    protected WebElement errorMessagePositionContactsCallBackReferent1;
    @FindBy(css = ".ContactInformation_container__3bJKI > :nth-child(3) > :nth-child(2) > :nth-child(6) > .LabelError_container__b1ltp")
    protected WebElement errorMessageBusinessPhoneContactsCallBackReferent1;
    @FindBy(css = ".ContactInformation_container__3bJKI > :nth-child(3) > :nth-child(2) > :nth-child(9) > .LabelError_container__b1ltp")
    protected WebElement errorMessageEmailContactsCallBackReferent1;
    @FindBy(css = ".ContactInformation_container__3bJKI > :nth-child(3) > :nth-child(4) > :nth-child(2) > .LabelError_container__b1ltp")
    protected WebElement errorMessageFirstNameContactsCallBackReferent2;
    @FindBy(css = ".ContactInformation_container__3bJKI > :nth-child(3) > :nth-child(4) > :nth-child(3) > .LabelError_container__b1ltp")
    protected WebElement errorMessageLastNameContactsCallBackReferent2;
    @FindBy(css = ".ContactInformation_container__3bJKI > :nth-child(3) > :nth-child(4) > :nth-child(5) > .LabelError_container__b1ltp")
    protected WebElement errorMessageBusinessPhoneContactsCallBackReferent2;
    @FindBy(css = ".ContactInformation_container__3bJKI > :nth-child(3) > :nth-child(4) > :nth-child(8) > .LabelError_container__b1ltp")
    protected WebElement errorMessageEmailContactsCallBackReferent2;


    @Step("check all the data saved in the form")
    public boolean checkDataSavedInContactInfoTab(String companyTextInput, String leiCodeInput, String companyAddressInput, String countryInput, String cityInput,
                                                  String postalCodeInput, String firstNameContactInput, String lastNameContactInput, String positionContactInput,
                                                  String businessPhoneFieldInput, String secondPhoneFieldInput, String faxPhoneFieldInput,
                                                  String emailContactInput) {
        try {
            //contact info
            waitForPageFinishLoading();
            waitForElementToBeVisibility(companyName);
            Assert.assertEquals(getAttribute(companyName, "value"), companyTextInput);
            waitForElementToBeVisibility(leiCode);
            Assert.assertEquals(getAttribute(leiCode, "value"), leiCodeInput);
            waitForElementToBeVisibility(companyAddress);
            Assert.assertEquals(getAttribute(companyAddress, "value"), companyAddressInput);
            waitForElementToBeVisibility(countryField);
            Assert.assertEquals(getAttribute(countryField, "value"), countryInput);
            waitForElementToBeVisibility(cityField);
            Assert.assertEquals(getAttribute(cityField, "value"), cityInput);
            waitForElementToBeVisibility(postalCode);
            Assert.assertEquals(getAttribute(postalCode, "value"), postalCodeInput);
            //contact
            waitForElementToBeVisibility(firstNameContact);
            Assert.assertEquals(getAttribute(firstNameContact, "value"), firstNameContactInput);
            waitForElementToBeVisibility(lastNameContact);
            Assert.assertEquals(getAttribute(lastNameContact, "value"), lastNameContactInput);
            waitForElementToBeVisibility(positionContact);
            Assert.assertEquals(getAttribute(positionContact, "value"), positionContactInput);
            waitForElementToBeVisibility(businessPhoneContactsField);
            Assert.assertEquals(getAttribute(businessPhoneContactsField, "value"), businessPhoneFieldInput);
            waitForElementToBeVisibility(secondPhoneAreaCodeContactsField);
            Assert.assertEquals(getAttribute(secondPhoneAreaCodeContactsField, "value"), secondPhoneFieldInput);
            waitForElementToBeVisibility(faxNumberAreaCodeContactsField);
            Assert.assertEquals(getAttribute(faxNumberAreaCodeContactsField, "value"), faxPhoneFieldInput);
            waitForElementToBeVisibility(emailContact);
            Assert.assertEquals(getAttribute(emailContact, "value"), emailContactInput);
            //referent 1
            waitForElementToBeVisibility(firstNameReferent1);
            Assert.assertEquals(getAttribute(firstNameReferent1, "value"), firstNameContactInput);
            waitForElementToBeVisibility(lastNameReferent1);
            Assert.assertEquals(getAttribute(lastNameReferent1, "value"), lastNameContactInput);
            waitForElementToBeVisibility(positionReferent1);
            Assert.assertEquals(getAttribute(positionReferent1, "value"), positionContactInput);
            waitForElementToBeVisibility(businessPhoneAreaCodeCallBackReferent1Field);
            Assert.assertEquals(getAttribute(businessPhoneAreaCodeCallBackReferent1Field, "value"), businessPhoneFieldInput);
            waitForElementToBeVisibility(secondPhoneAreaCodeCallBackReferent1Field);
            Assert.assertEquals(getAttribute(secondPhoneAreaCodeCallBackReferent1Field, "value"), secondPhoneFieldInput);
            waitForElementToBeVisibility(faxNumberAreaCodeCallBackReferent1Field);
            Assert.assertEquals(getAttribute(faxNumberAreaCodeCallBackReferent1Field, "value"), faxPhoneFieldInput);
            waitForElementToBeVisibility(emailReferent1);
            Assert.assertEquals(getAttribute(emailReferent1, "value"), emailContactInput);
            //referent 2
            waitForElementToBeVisibility(firstNameReferent2);
            Assert.assertEquals(getAttribute(firstNameReferent2, "value"), firstNameContactInput);
            waitForElementToBeVisibility(lastNameReferent2);
            Assert.assertEquals(getAttribute(lastNameReferent2, "value"), lastNameContactInput);
            waitForElementToBeVisibility(positionReferent2);
            Assert.assertEquals(getAttribute(positionReferent2, "value"), positionContactInput);
            waitForElementToBeVisibility(businessPhoneAreaCodeCallBackReferent2Field);
            Assert.assertEquals(getAttribute(businessPhoneAreaCodeCallBackReferent2Field, "value"), businessPhoneFieldInput);
            waitForElementToBeVisibility(secondPhoneAreaCodeCallBackReferent2Field);
            Assert.assertEquals(getAttribute(secondPhoneAreaCodeCallBackReferent2Field, "value"), secondPhoneFieldInput);
            waitForElementToBeVisibility(faxNumberAreaCodeCallBackReferent2Field);
            Assert.assertEquals(getAttribute(faxNumberAreaCodeCallBackReferent2Field, "value"), faxPhoneFieldInput);
            waitForElementToBeVisibility(emailReferent2);
            Assert.assertEquals(getAttribute(emailReferent2, "value"), emailContactInput);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Step("Fill all the fields in the contact information")
    public void fillContactInformation(String company, String lieCode, String address, String country, String city, String postalNum, String firstName,
                                       String lastName, String position, String chooseFromAreaCodeList, String phoneNum, String email) {
        waitForPageFinishLoading();
        waitForElementToBeVisibility(contactInfoTitle);
        //contact info
        waitForElementToBeClickable(companyName);
        fillText(companyName, company);
        waitForElementToBeClickable(leiCode);
        fillText(leiCode, lieCode);
        waitForElementToBeClickable(companyAddress);
        fillText(companyAddress, address);
        waitForElementToBeClickable(countryArrowButton);
        click(countryArrowButton);
        for (WebElement el : countryList) {
            if (getText(el).contains(country)) {
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
        waitForElementToBeClickable(citySelect);
        click(citySelect);
        for (WebElement el : cityList) {
            if (getText(el).contains(city)) {
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
        waitForElementToBeClickable(postalCode);
        fillText(postalCode, postalNum);
        //Contacts
        waitForElementToBeClickable(firstNameContact);
        fillText(firstNameContact, firstName);
        waitForElementToBeClickable(lastNameContact);
        fillText(lastNameContact, lastName);
        waitForElementToBeClickable(positionContact);
        fillText(positionContact, position);
        //business phone
        waitForElementToBeClickable(businessPhoneAreaCodeContactsSelect);
        click(businessPhoneAreaCodeContactsSelect);
        for (WebElement el : businessPhoneAreaCodeContactsList) {
            if (getText(el).contains(chooseFromAreaCodeList)) {
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
        waitForElementToBeClickable(businessPhoneContactsField);
        fillText(businessPhoneContactsField, phoneNum);
        //second phone
        waitForElementToBeClickable(secondPhoneAreaCodeContactsSelect);
        click(secondPhoneAreaCodeContactsSelect);
        for (WebElement el : secondPhoneAreaCodeContactsList) {
            if (getText(el).contains(chooseFromAreaCodeList)) {
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
        waitForElementToBeClickable(secondPhoneAreaCodeContactsField);
        fillText(secondPhoneAreaCodeContactsField, phoneNum);
        //fax
        waitForElementToBeClickable(faxNumberAreaCodeContactsSelect);
        click(faxNumberAreaCodeContactsSelect);
        for (WebElement el : faxNumberAreaCodeContactsList) {
            if (getText(el).contains(chooseFromAreaCodeList)) {
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
        waitForElementToBeClickable(faxNumberAreaCodeContactsField);
        fillText(faxNumberAreaCodeContactsField, phoneNum);
        waitForElementToBeClickable(emailContact);
        fillText(emailContact, email);

        //Call back referent 1
        waitForElementToBeClickable(firstNameReferent1);
        fillText(firstNameReferent1, firstName);
        waitForElementToBeClickable(lastNameReferent1);
        fillText(lastNameReferent1, lastName);
        waitForElementToBeClickable(positionReferent1);
        fillText(positionReferent1, position);
        //business phone
        waitForElementToBeClickable(businessPhoneAreaCodeCallBackReferent1Select);
        click(businessPhoneAreaCodeCallBackReferent1Select);
        for (WebElement el : businessPhoneAreaCodeCallBackReferent1List) {
            if (getText(el).contains(chooseFromAreaCodeList)) {
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
        waitForElementToBeClickable(businessPhoneAreaCodeCallBackReferent1Field);
        fillText(businessPhoneAreaCodeCallBackReferent1Field, phoneNum);
        //second phone
        waitForElementToBeClickable(secondPhoneAreaCodeCallBackReferent1Select);
        click(secondPhoneAreaCodeCallBackReferent1Select);
        for (WebElement el : secondPhoneAreaCodeCallBackReferent1List) {
            if (getText(el).contains(chooseFromAreaCodeList)) {
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
        waitForElementToBeClickable(secondPhoneAreaCodeCallBackReferent1Field);
        fillText(secondPhoneAreaCodeCallBackReferent1Field, phoneNum);
        //fax
        waitForElementToBeClickable(faxNumberAreaCodeCallBackReferent1Select);
        click(faxNumberAreaCodeCallBackReferent1Select);
        for (WebElement el : faxNumberAreaCodeCallBackReferent1List) {
            if (getText(el).contains(chooseFromAreaCodeList)) {
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
        waitForElementToBeClickable(faxNumberAreaCodeCallBackReferent1Field);
        fillText(faxNumberAreaCodeCallBackReferent1Field, phoneNum);
        waitForElementToBeClickable(emailReferent1);
        fillText(emailReferent1, email);

        //Call back referent 2
        waitForElementToBeClickable(firstNameReferent2);
        fillText(firstNameReferent2, firstName);
        waitForElementToBeClickable(lastNameReferent2);
        fillText(lastNameReferent2, lastName);
        waitForElementToBeClickable(positionReferent2);
        fillText(positionReferent2, position);
        //business phone
        waitForElementToBeClickable(businessPhoneAreaCodeCallBackReferent2Select);
        click(businessPhoneAreaCodeCallBackReferent2Select);
        for (WebElement el : businessPhoneAreaCodeCallBackReferent2List) {
            if (getText(el).contains(chooseFromAreaCodeList)) {
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
        waitForElementToBeClickable(businessPhoneAreaCodeCallBackReferent2Field);
        fillText(businessPhoneAreaCodeCallBackReferent2Field, phoneNum);
        //second phone
        waitForElementToBeClickable(secondPhoneAreaCodeCallBackReferent2Select);
        click(secondPhoneAreaCodeCallBackReferent2Select);
        for (WebElement el : secondPhoneAreaCodeCallBackReferent2List) {
            if (getText(el).contains(chooseFromAreaCodeList)) {
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
        waitForElementToBeClickable(secondPhoneAreaCodeCallBackReferent2Field);
        fillText(secondPhoneAreaCodeCallBackReferent2Field, phoneNum);
        //fax
        waitForElementToBeClickable(faxNumberAreaCodeCallBackReferent2Select);
        click(faxNumberAreaCodeCallBackReferent2Select);
        for (WebElement el : faxNumberAreaCodeCallBackReferent2List) {
            if (getText(el).contains(chooseFromAreaCodeList)) {
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
        waitForElementToBeClickable(faxNumberAreaCodeCallBackReferent2Field);
        fillText(faxNumberAreaCodeCallBackReferent2Field, phoneNum);
        waitForElementToBeClickable(emailReferent2);
        fillText(emailReferent2, email);
    }

    @Step("get the text from contact info header")
    public boolean contactInfoTitle(String text) {
        try {
            waitForTextToBeInElement(contactInfoTitle, text);
            getText(contactInfoTitle);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Check that errorMessageCompanyName appear")
    public String errorMessageCompanyName(String errorMessage) {
        scrollToElement(errorMessageAddress);
        waitForTextToBeInElement(errorMessageCompanyName, errorMessage);
        return getText(errorMessageCompanyName);
    }

    @Step("Check that errorMessageLEICode appear")
    public String errorMessageLEICode(String errorMessage) {
        scrollToElement(errorMessageLEICode);
        waitForTextToBeInElement(errorMessageLEICode, errorMessage);
        return getText(errorMessageLEICode);
    }

    @Step("Check that errorMessageAddress appear")
    public String errorMessageAddress(String errorMessage) {
        scrollToElement(errorMessageAddress);
        waitForTextToBeInElement(errorMessageAddress, errorMessage);
        return getText(errorMessageAddress);
    }

    @Step("Check that errorMessagePostalCode appear")
    public String errorMessagePostalCode(String errorMessage) {
        scrollToElement(errorMessagePostalCode);
        waitForTextToBeInElement(errorMessagePostalCode, errorMessage);
        return getText(errorMessagePostalCode);
    }

    @Step("Check that errorMessageFirstNameContacts appear")
    public String errorMessageFirstNameContacts(String errorMessage) {
        scrollToElement(errorMessageFirstNameContacts);
        waitForTextToBeInElement(errorMessageFirstNameContacts, errorMessage);
        return getText(errorMessageFirstNameContacts);
    }

    @Step("Check that errorMessageLastNameContacts appear")
    public String errorMessageLastNameContacts(String errorMessage) {
        scrollToElement(errorMessageLastNameContacts);
        waitForTextToBeInElement(errorMessageLastNameContacts, errorMessage);
        return getText(errorMessageLastNameContacts);
    }

    @Step("Check that errorMessagePositionContacts appear")
    public String errorMessagePositionContacts(String errorMessage) {
        scrollToElement(errorMessagePositionContacts);
        waitForTextToBeInElement(errorMessagePositionContacts, errorMessage);
        return getText(errorMessagePositionContacts);
    }

    @Step("Check that errorMessageBusinessPhoneContacts appear")
    public String errorMessageBusinessPhoneContacts(String errorMessage) {
        scrollToElement(errorMessageBusinessPhoneContacts);
        waitForTextToBeInElement(errorMessageBusinessPhoneContacts, errorMessage);
        return getText(errorMessageBusinessPhoneContacts);
    }

    @Step("Check that errorMessageEmailContacts appear")
    public String errorMessageEmailContacts(String errorMessage) {
        scrollToElement(errorMessageEmailContacts);
        waitForTextToBeInElement(errorMessageEmailContacts, errorMessage);
        return getText(errorMessageEmailContacts);
    }

    @Step("Check that errorMessageFirstNameContactsCallBackReferent1 appear")
    public String errorMessageFirstNameContactsCallBackReferent1(String errorMessage) {
        scrollToElement(errorMessageFirstNameContactsCallBackReferent1);
        waitForTextToBeInElement(errorMessageFirstNameContactsCallBackReferent1, errorMessage);
        return getText(errorMessageFirstNameContactsCallBackReferent1);
    }

    @Step("Check that errorMessageLastNameContactsCallBackReferent1 appear")
    public String errorMessageLastNameContactsCallBackReferent1(String errorMessage) {
        scrollToElement(errorMessageLastNameContactsCallBackReferent1);
        waitForTextToBeInElement(errorMessageLastNameContactsCallBackReferent1, errorMessage);
        return getText(errorMessageLastNameContactsCallBackReferent1);
    }

    @Step("Check that errorMessagePositionContactsCallBackReferent1 appear")
    public String errorMessagePositionContactsCallBackReferent1(String errorMessage) {
        scrollToElement(errorMessagePositionContactsCallBackReferent1);
        waitForTextToBeInElement(errorMessagePositionContactsCallBackReferent1, errorMessage);
        return getText(errorMessagePositionContactsCallBackReferent1);
    }

    @Step("Check that errorMessageBusinessPhoneContactsCallBackReferent1 appear")
    public String errorMessageBusinessPhoneContactsCallBackReferent1(String errorMessage) {
        scrollToElement(errorMessageBusinessPhoneContactsCallBackReferent1);
        waitForTextToBeInElement(errorMessageBusinessPhoneContactsCallBackReferent1, errorMessage);
        return getText(errorMessageBusinessPhoneContactsCallBackReferent1);
    }

    @Step("Check that errorMessageEmailContactsCallBackReferent1 appear")
    public String errorMessageEmailContactsCallBackReferent1(String errorMessage) {
        scrollToElement(errorMessageEmailContactsCallBackReferent1);
        waitForTextToBeInElement(errorMessageEmailContactsCallBackReferent1, errorMessage);
        return getText(errorMessageEmailContactsCallBackReferent1);
    }

    @Step("wait to that have been wrote comment to client text")
    public String commentToClientInContactInfoTab(String text) {
        waitForElementToBeVisibility(contactInfoTitle);
        waitForTextToBeInElement(commentToClientInContactInfoTab, text);
        return getText(commentToClientInContactInfoTab);
    }
}