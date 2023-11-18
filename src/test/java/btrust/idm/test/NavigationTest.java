package btrust.idm.test;

import btrust.BaseIdmTest;
import btrust.btrustOne.client.NavigationPage;
import btrust.idm.pageObject.DashboardPage;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NavigationTest extends BaseIdmTest {

    @Test(description = "open notification window")
    @Description("click on notification icon and check notification title appear")
    public void t_01openNotificationWindow() {
        NavigationPage navigation = new NavigationPage(driver);
        navigation.notificationIcon();

        String expected = navigation.notificationTitle("Notifications");
        navigation.closeNotificationButton();
        Assert.assertEquals(expected, "Notifications");
    }

    @Test(description = "close notification window")
    @Description("click on close button and check that the notification windows close")
    public void t_02closeNotificationWindow() {
        NavigationPage navigation = new NavigationPage(driver);
        navigation.notificationIcon();
        navigation.closeNotificationButton();

        DashboardPage dashboard = new DashboardPage(driver);
        Assert.assertTrue(dashboard.totalCasesTitle());
    }
}