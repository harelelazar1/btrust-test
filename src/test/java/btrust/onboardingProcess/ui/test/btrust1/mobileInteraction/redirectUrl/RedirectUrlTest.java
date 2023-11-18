package btrust.onboardingProcess.ui.test.btrust1.mobileInteraction.redirectUrl;

import btrust.onboardingProcess.ui.pagesObject.ImageInjectionFunctionPage;
import btrust.onboardingProcess.ui.pagesObject.OCRPage;
import btrust.onboardingProcess.ui.pagesObject.ScanResultPage;
import btrust.onboardingProcess.ui.test.btrust1.mobileInteraction.MobileInteractionUIBase;
import com.google.gson.Gson;
import io.qameta.allure.Description;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utilities.DBUtils;
import utilities.SuiteListener;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;
@Listeners(SuiteListener.class)
public class RedirectUrlTest extends MobileInteractionUIBase {
    OCRPage ocrPage;
    ImageInjectionFunctionPage imageInjectionFunctionPage;
    ScanResultPage scanResultPage;

    @BeforeMethod(alwaysRun = true)
    public void initSession() throws IOException {
        ocrPage = new OCRPage(driver);
        imageInjectionFunctionPage = new ImageInjectionFunctionPage(driver);
        scanResultPage = new ScanResultPage(driver);
    }

    @AfterClass(alwaysRun = true)
    private void setDefaultUrl() {
        String redirectUrl="https://qa-nginx-client.scanovateoncloud.com/completed";
        updateUrl(redirectUrl);
    }

    private void updateUrl(String redirectUrl) {
        Assert.assertNotNull(redirectUrl,  "Redirect URL not defined");
        DBUtils dbu = new DBUtils();
        Assert.assertTrue(dbu.updateRedirectUrl(redirectUrl));
    }

    private URL getConfigUrl(String redirectUrl, String overrideQuery) throws MalformedURLException {
        Gson gson = new Gson();
        Map overrideMap = gson.fromJson(overrideQuery, Map.class);
        if (overrideMap.containsKey("callback_url")) {
            return new URL((String) overrideMap.get("callback_url"));
        } else {
            return new URL(redirectUrl);
        }
    }

    private void compareUrls(String overrideQuery, URL configUrl, URL redirectUrl) throws MalformedURLException {
        // Compare current utl to config
        Assert.assertEquals(redirectUrl.getProtocol(), configUrl.getProtocol(), "Redirect url protocol mismatch");
        Assert.assertEquals(redirectUrl.getHost(), configUrl.getHost(), "Redirect url host mismatch");
        String currentPath = redirectUrl.getPath();
        if (currentPath.charAt(currentPath.length() - 1) == '/') {
            currentPath = currentPath.substring(0, currentPath.length() - 1);
        }
        Assert.assertEquals(currentPath, configUrl.getPath(), "Redirect url path mismatch");

        //Compare url query params
        Map<String, String> urlQueryMap = buildQueryMap(redirectUrl, null);
        Map<String, String> configQueryMap = buildQueryMap(configUrl, overrideQuery);
        Assert.assertTrue(urlQueryMap.containsKey("processId"), "Missing process id param from url");
        Assert.assertEquals(urlQueryMap.get("processId"), sessionId, "Redirect url process id mismatch");
        Assert.assertTrue(urlQueryMap.containsKey("token"), "Missing token from url");
        for (String configKey : configQueryMap.keySet()) {
            Assert.assertTrue(urlQueryMap.containsKey(configKey), "Missing config query param:" + configKey);
            Assert.assertEquals(urlQueryMap.get(configKey), configQueryMap.get(configKey), "Value mismatch in config query param:" + configKey);
        }
    }

    private Map buildQueryMap(URL redirectUrl, String overrideQuery) throws MalformedURLException {
        Map configQueryMap = new HashMap();
        Gson gson = new Gson();
        Map overrideMap = gson.fromJson(overrideQuery, Map.class);
        if (overrideMap != null) {
            if (overrideMap.containsKey("callback_url")) {
                redirectUrl = new URL((String) overrideMap.get("callback_url"));
            }
            for (Object keyName : overrideMap.keySet()) {
                if (!keyName.equals("callback_url")) {
                    configQueryMap.put(keyName, overrideMap.get(keyName));
                }
            }
        }
        String configQuery = redirectUrl.getQuery();
        if (configQuery != null) {
            String[] configQueryPairs = configQuery.split("&");

            for (String pair : configQueryPairs) {
                String[] kv = pair.split("=");
                configQueryMap.put(kv[0], kv[1]);
            }
        }
        return configQueryMap;
    }

    @Test(description = "Test Redirect to Default URL")
    @Description("TTest Redirect to Default URL")
    public void t01_redirectDefaultUrl() throws IOException {
        String redirectUrl="https://qa-nginx-client.scanovateoncloud.com/completed";
        updateUrl(redirectUrl);
        String overrideQuery = "{}";
        URL configUrl = getConfigUrl(redirectUrl, overrideQuery);
        runOcr(434, "./ocr/greenID/bar/BarGreenID.jpg", null, ocrPage, imageInjectionFunctionPage, overrideQuery);
        assertEquals(scanResultPage.results("green id", "First Name"), "בר");
        assertEquals(scanResultPage.resultsTitle(), "Scan results");
        scanResultPage.clickOnButton("Continue");

        // Verify current URL contains config URL
        redirectWait.until(ExpectedConditions.urlContains(configUrl.toString()));
        // Compare URL params
        URL currentUrl = new URL(driver.getCurrentUrl());
        compareUrls(overrideQuery, configUrl, currentUrl);
    }
    @Test(description = "Test Redirect to Non Default URL")
    @Description("Test Redirect to Non Default URL")
    public void t02_redirectNonDefaultUrl() throws IOException {
        String redirectUrl="https://www.google.com";
        updateUrl(redirectUrl);
        String overrideQuery = "{}";
        URL configUrl = getConfigUrl(redirectUrl, overrideQuery);
        runOcr(434, "./ocr/greenID/bar/BarGreenID.jpg", null, ocrPage, imageInjectionFunctionPage, overrideQuery);
        assertEquals(scanResultPage.results("green id", "First Name"), "בר");
        assertEquals(scanResultPage.resultsTitle(), "Scan results");
        scanResultPage.clickOnButton("Continue");

        // Verify current URL contains config URL
        redirectWait.until(ExpectedConditions.urlContains(configUrl.toString()));
        // Compare URL params
        URL currentUrl = new URL(driver.getCurrentUrl());
        compareUrls(overrideQuery, configUrl, currentUrl);
    }
    @Test(description = "Test Redirect to URL With Params")
    @Description("Test Redirect to URL With Params")
    public void t03_redirectUrlWithParams() throws IOException {
        String redirectUrl="https://qa-nginx-client.scanovateoncloud.com/completed?key1=val1&key2=val2";
        updateUrl(redirectUrl);
        String overrideQuery = "{}";
        URL configUrl = getConfigUrl(redirectUrl, overrideQuery);
        runOcr(434, "./ocr/greenID/bar/BarGreenID.jpg", null, ocrPage, imageInjectionFunctionPage, overrideQuery);
        assertEquals(scanResultPage.results("green id", "First Name"), "בר");
        assertEquals(scanResultPage.resultsTitle(), "Scan results");
        scanResultPage.clickOnButton("Continue");

        // Verify current URL contains config URL
        redirectWait.until(ExpectedConditions.urlContains(configUrl.toString()));
        // Compare URL params
        URL currentUrl = new URL(driver.getCurrentUrl());
        compareUrls(overrideQuery, configUrl, currentUrl);
    }


    @Test(description = "Test Override Callback URL With Param")
    @Description("Test Override Callback URL With Param")
    public void t04_overrideCallbackUrlWithParam() throws IOException {
        String redirectUrl="https://qa-nginx-client.scanovateoncloud.com/completed";
        updateUrl(redirectUrl);
        String overrideQuery = "{\"callback_url\":\"https://www.google.com\",\"param2\":\"val2\",\"param3\":\"val3\"}";
        URL configUrl = getConfigUrl(redirectUrl, overrideQuery);
        runOcr(434, "./ocr/greenID/bar/BarGreenID.jpg", null, ocrPage, imageInjectionFunctionPage, overrideQuery);
        assertEquals(scanResultPage.results("green id", "First Name"), "בר");
        assertEquals(scanResultPage.resultsTitle(), "Scan results");
        scanResultPage.clickOnButton("Continue");

        // Verify current URL contains config URL
        redirectWait.until(ExpectedConditions.urlContains(configUrl.toString()));
        // Compare URL params
        URL currentUrl = new URL(driver.getCurrentUrl());
        compareUrls(overrideQuery, configUrl, currentUrl);
    }
}
