package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.DataProvider;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static io.restassured.RestAssured.given;

public class TestUtils {
    static File file;

    public static final String DEFAULT_MATHILDA = "two_sides.json";
    public static final double LIVENESS_THRESHOLD = 0.56;


    public static String getDefaultMathilda() {
        String ocr = System.getProperty("OCR_NEW");
        Boolean new_ocr = Boolean.parseBoolean(ocr);
        if (new_ocr) {
            String mathildaName = System.getProperty("COMPANY_ID");
            if (mathildaName != null) {
                return mathildaName;
            }
        } else {
            return DEFAULT_MATHILDA;
        }


        String mathildaName = System.getProperty("COMPANY_ID");
        if (mathildaName != null) {
            return mathildaName;
        } else {
            return DEFAULT_MATHILDA;
        }
    }

//        String mathildaName = OCR_COMPANY_UUID;
////        String mathildaName = System.getProperty("DEFAULT_MATHILDA");
//         if (System.getProperty("OCR_NEW").equalsIgnoreCase("true"))  {
//            return mathildaName;
//        } else {
//            return DEFAULT_MATHILDA;
//        }
//    }

    //    @Test()
    public void loadProperties() {
        if (System.getProperty("propertiesFile") != null) {
            loadProperties(System.getProperty("propertiesFile"));
        }
        if (System.getenv("BUILD_NUMBER") != null) {
            loadProperties("build_" + System.getenv("BUILD_NUMBER") + ".properties");
        }
        loadProperties("test.properties");
    }

    public void loadProperties(String testPropsFileName) {
        InputStream input;
        Properties testProps = new Properties();
        File testPropsFile = new File(testPropsFileName);
        if (!testPropsFile.exists()) {
            System.out.println("properties file not found:" + testPropsFileName);
            return;
        }
        try {
            input = Files.newInputStream(testPropsFile.toPath());
            testProps.load(input);
            Set<String> names = testProps.stringPropertyNames();
            for (String name : names) {
                if (System.getProperty(name) == null) {
                    System.setProperty(name, testProps.getProperty(name));
                }
            }
        } catch (Exception e) {
            System.out.println("can not load properties for file:" + testPropsFileName);
        }


    }


    public void saveProperties(Properties p) throws IOException {
        SortedProperties sp = new SortedProperties();
        sp.putAll(p);
        //save in allure report
        File resultsPath = new File("target/allure-results");
        if (!resultsPath.exists()) {
            if (!resultsPath.mkdir()) {
                System.out.println("allure-results folder  creation failed.Can not save env properties");
                return;
            }
        }
        file = new File("target/allure-results/environment.properties");

        FileOutputStream fr = new FileOutputStream(file);
        sp.store(fr, "Properties");
        fr.close();

    }

    public static String getTestProperty(String propertyName, String testPropsFileName) {
        InputStream input;
        Properties testProps = new Properties();
        Properties defaultTestProps = new Properties();
        // Default props
        try {
            File defaultPropsFile = new File("test.properties");
            input = Files.newInputStream(defaultPropsFile.toPath());
            defaultTestProps.load(input);
        } catch (Exception e) {

        }
        // Test props
        try {
            File testPropsFile = new File(testPropsFileName);
            input = Files.newInputStream(testPropsFile.toPath());
            testProps.load(input);
        } catch (Exception e) {

        }
        Properties sysProps = System.getProperties();
        if (sysProps.containsKey(propertyName)) {
            return sysProps.getProperty(propertyName);
        } else if (testProps.containsKey(propertyName)) {
            return testProps.getProperty(propertyName);
        } else if (defaultTestProps.containsKey(propertyName)) {
            return defaultTestProps.getProperty(propertyName);
        }
        return null;
    }

    private void checkBrowsers(Properties p) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        WebDriver chromeDriver = new ChromeDriver(options);
        Capabilities capabilities = ((HasCapabilities) chromeDriver).getCapabilities();
        p.setProperty("CHROME_VERSION", capabilities.getVersion());
        chromeDriver.quit();
    }


    public int verifyStats(String jobName, int completeRate) throws IOException {
        double threadCount = 0;
        String statsPath = ".\\target\\jmeter\\reports\\" + jobName + "\\statistics.json";
        File statsFile = new File(statsPath);
        if (statsFile.exists()) {
            // check file date
            Path path = statsFile.toPath();
            BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);
            long hourDiff = (System.currentTimeMillis() - attr.creationTime().to(TimeUnit.MILLISECONDS)) / 1000 / 3600;
            Assert.assertTrue(hourDiff <= 1, "No new report found");
            FileInputStream is = null;
            String jsonTxt;
            JSONObject json = null;
            JSONObject successStats = null;
            JSONObject initStats = null;

            //Attach allure file
            try {
                FileInputStream is1 = FileUtils.openInputStream(statsFile);
                Allure.addAttachment("statistics.json", is1);
            } catch (Exception e) {
                System.out.println("error attaching file:" + e.getMessage());
            }

            try {
                is = FileUtils.openInputStream(statsFile);
                jsonTxt = IOUtils.toString(is, StandardCharsets.UTF_8);
                json = new JSONObject(jsonTxt);
            } catch (Exception e) {
                if (is != null) {
                    is.close();
                }
                Assert.fail("error opening stats json file");
            }

            Assert.assertTrue(checkKeyInList(json.keys(), "Session Complete OK"));
            Assert.assertTrue(checkKeyInList(json.keys(), "Client Init"));
            successStats = json.getJSONObject("Session Complete OK");
            initStats = json.getJSONObject("Client Init");
            threadCount = initStats.getDouble("sampleCount");
            double successCount = successStats.getDouble("sampleCount");
            System.out.println("success rate:" + successCount / threadCount * 100);
            Assert.assertTrue(successCount / threadCount * 100 >= completeRate);

        } else {
            Assert.fail("No new report found");
        }
        return (int) threadCount;

    }

    public boolean checkKeyInList(Iterator<String> keyList, String keyName) {
        for (Iterator<String> it = keyList; it.hasNext(); ) {
            String s = it.next();
            if (s.equals(keyName)) {
                return true;
            }
        }
        return false;
    }


    public Response resetWebhook(String baseWebhookUrl) {
        DBUtils du = new DBUtils();
        String newTokenUrl = baseWebhookUrl + "token";
        Response newTokenResponse = given().post(newTokenUrl).then().extract().response();
        Assert.assertTrue(newTokenResponse.statusCode() == 200 || newTokenResponse.statusCode() == 201);
        JsonPath jsonPath = newTokenResponse.jsonPath();
        String newUuid = jsonPath.get("uuid");
        Assert.assertTrue(du.updateWebHookUrl(baseWebhookUrl + newUuid), "Failed updating company config with new webhook url");
        return newTokenResponse;
    }

    public static Response getWebhookRequests(String dateFrom) {
        DBUtils du = new DBUtils();
        String baseUrl = "https://webhook.site/";
        String webhookUrl = du.getWebHook();
        Assert.assertNotSame("", webhookUrl);
        webhookUrl = webhookUrl.replace(baseUrl, "https://webhook.site/token/");
        webhookUrl += "/requests";
        if (!Objects.equals(dateFrom, "")) {
            webhookUrl += "?date_from=" + dateFrom;
        }
        System.out.println(webhookUrl);
        return given()
                .get(webhookUrl)
                .then()
                .statusCode(200)
                .extract()
                .response();
    }

    public static void printResultLines(ITestResult result) {
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);
        System.out.println("Test ended at: " + formattedDate);
        System.out.println("------------ Test " + result.getName() + " Failed ------------");
    }

    @DataProvider(name = "dp_multiplier")
    public Object[][] dpMultiplier() {
        int multiplier = 1; //default
        if (System.getProperty("DP_MULTIPLIER") != null) {
            multiplier = Integer.parseInt(System.getProperty("DP_MULTIPLIER"));
        }
        return providerArray(multiplier);
    }

    @DataProvider(name = "onboarding_params")
    public Object[][] dpMethod1() {
        return new Object[][]{{true}, {false}};
//        return new Object[][]{{true}};
    }

    private Object[][] providerArray(int rangeSize) {
        Object[][] rangeTen = new Object[rangeSize][1];
        IntStream.range(1, rangeSize + 1).forEachOrdered(n -> rangeTen[n - 1][0] = n);
        return rangeTen;
    }

    public void getSwaggerDef(String swaggerUrl) throws MalformedURLException {
        Response response = given()
                .when()
                .get(swaggerUrl)
                .then()
                .statusCode(200)
                .extract()
                .response();
        System.setProperty("SWAGGER_DEF", response.getBody().asString());
        URL url = new URL(swaggerUrl);
        String protocol = url.getProtocol();
        String host = url.getHost();
        int port = url.getPort();
        StringBuilder baseUrl = new StringBuilder();
        baseUrl.append(protocol).append("://").append(host);
        if (port != -1) {
            baseUrl.append(":").append(port);
        }
        System.setProperty("SWAGGER_BASE_URL", baseUrl.toString());
    }


    class SortedProperties extends Properties {
        public Enumeration keys() {
            Enumeration keysEnum = super.keys();
            Vector<String> keyList = new Vector<String>();
            while (keysEnum.hasMoreElements()) {
                keyList.add((String) keysEnum.nextElement());
            }
            Collections.sort(keyList);
            return keyList.elements();
        }
    }
}