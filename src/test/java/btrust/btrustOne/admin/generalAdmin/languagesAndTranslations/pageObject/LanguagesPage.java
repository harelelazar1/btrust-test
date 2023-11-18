package btrust.btrustOne.admin.generalAdmin.languagesAndTranslations.pageObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class LanguagesPage extends BasePage {
    public LanguagesPage(WebDriver driver) {
        super(driver);
    }

    public String randomString;


    @FindBy(css = "div .SettingsHeader-module__title__acJ6f")
    protected WebElement pageTitle;
    @FindBy(css = "div .SettingsHeader-module__description__YsOmA")
    protected WebElement LanguagesSubTitle;
    @FindBy(css = ".Container-module__container__zCpSo  h3")
    protected List<WebElement> languageNameList;


    public String pageTitleName() {
        waitForElementToBeVisibility(pageTitle);
        scrollToElement(pageTitle);
        String titleName = getText(pageTitle);
        return titleName;
    }


    public String pageSubTitleName() {
        scrollToElement(LanguagesSubTitle);
        String subTitleName = getText(LanguagesSubTitle);
        return subTitleName;
    }


    public boolean checkIfLanguageDisplay(String languageName) {
        try {
            waitForPageFinishLoading();
            for (WebElement langName : languageNameList) {
                scrollToElement(langName);
                if (getText(langName).equalsIgnoreCase(languageName)) {
                    scrollToElement(langName);
                    return true;
                }
            }

        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
        }
        return false;
    }


    public void chooseLanguageFromList(String languageName) {
        waitForPageFinishLoading();
        for (WebElement langName : languageNameList) {
            scrollToElement(langName);
            if (getText(langName).equalsIgnoreCase(languageName)) {
                scrollToElement(langName);
                waitForElementToBeClickable(langName);
                click(langName);
                break;
            }
        }
    }


}
