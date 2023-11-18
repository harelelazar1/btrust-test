package btrust.btrustOne.client.mandate.tests.createMandate.tests;

import btrust.btrustOne.client.BaseClientUserTest;
import btrust.btrustOne.client.NavigationPage;
import btrust.btrustOne.client.mandate.pagesObject.MandatesPage;
import btrust.btrustOne.client.mandate.pagesObject.createMandate.pagesObject.CancelMandateCreationPage;
import btrust.btrustOne.client.mandate.pagesObject.createMandate.pagesObject.MandateAndWorkflowPage;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CancelMandateCreationTest extends BaseClientUserTest {

    @BeforeMethod
    public void enterToMandateAndWorkflowScreen() {
        NavigationPage navigationPage = new NavigationPage(driver);
        navigationPage.mainMenuList("Mandates");
        MandatesPage mandatesPage = new MandatesPage(driver);
        Assert.assertEquals(mandatesPage.mandatesTitle("Mandates"), "Mandates");
        mandatesPage.addNewMandateButton();
        MandateAndWorkflowPage mandateAndWorkflowPage = new MandateAndWorkflowPage(driver);
        Assert.assertEquals(mandateAndWorkflowPage.mandateInformationTitle(), "Mandate Information");
    }

}
