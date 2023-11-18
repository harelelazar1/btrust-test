package selenium.liveness.test.newServer.newVersion.parallel.passive;

import api.Variables;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import selenium.Injection;
import selenium.ocr.pageObject.newService.MainPage;
import selenium.ocr.pageObject.newService.ResultsPage;

import java.io.IOException;
import java.util.*;

public class ParallelTest {
    Variables variables = new Variables();
    private final int MAX_THREADS = 2;
    private final int INVOCATIONS = 10;

    private final int THRESHOLD = 75;
    private List<WebDriver> drivers = new ArrayList<>();
    private String[] browsers = new String[MAX_THREADS];

    public final String LIVENESS_DEMO = "https://qaliveness.scanovateoncloud.com/demo";

    private boolean[] threadsState = new boolean[MAX_THREADS];

    int lastFinished = -1;

    int completedCount = 0;

    @BeforeClass
    private void createDrivers(ITestContext testContext) {
        Arrays.fill(threadsState, false);

        if (MAX_THREADS == 2) {
            browsers[0] = "chrome";
            browsers[1] = "firefox";
        } else if (MAX_THREADS == 1) {
            browsers[0] = "chrome";
        } else {
            Assert.fail("Max 2 threads allowed");
        }
        WebDriver driver;
        for (String b : browsers) {
            switch (b) {
                case ("firefox"):
                    String user_agent = "Mozilla/5.0 (iPhone; U; CPU iPhone OS 3_0 like Mac OS X; en-us) AppleWebKit/528.18 (KHTML, like Gecko) Version/4.0 Mobile/7A341 Safari/528.16";
                    WebDriverManager.firefoxdriver().setup();
                    FirefoxOptions ffOptions = new FirefoxOptions();
                    ffOptions.addArguments("-private");
                    ffOptions.addArguments("-devtools");
                    ffOptions.addPreference("media.navigator.streams.fake", true);
                    ffOptions.addPreference("media.navigator.permission.disabled", true);
                    ffOptions.addPreference("general.useragent.override", user_agent);
                    ffOptions.setLogLevel(FirefoxDriverLogLevel.INFO);
                    if (System.getProperty("headless", "false").equals("true")) {
                        ffOptions.setHeadless(true);
                    }
                    driver = new FirefoxDriver(ffOptions);
                    testContext.setAttribute("WebDriver", driver);
                    drivers.add(driver);
                    break;
                case ("chrome"):
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--log-level=OFF");
                    options.addArguments("--incognito");
//                options.addArguments("--headless");
                    Map<String, String> mobileEmulation = new HashMap<>();
                    mobileEmulation.put("deviceName", "iPhone X");
                    options.addArguments("use-fake-ui-for-media-stream");
                    options.addArguments("use-fake-device-for-media-stream");
                    options.addArguments("allow-file-access-from-files");
                    options.addArguments("--auto-open-devtools-for-tabs");
                    if (System.getProperty("HEADLESS", "false").equals("true")) {
                        options.addArguments("--headless");
                    }
                    options.setExperimentalOption("mobileEmulation", mobileEmulation);
                    driver = new ChromeDriver(options);
                    testContext.setAttribute("WebDriver", driver);
                    drivers.add(driver);
            }

        }
    }

    @Test(threadPoolSize = MAX_THREADS, invocationCount = INVOCATIONS, successPercentage = THRESHOLD)
    public void t01_liveness() throws Exception {
        String threadName = Thread.currentThread().getName();
        int threadNum;
        if (threadName.equalsIgnoreCase("main")) {
            threadNum = 0;
        } else {
            threadNum = Integer.parseInt(threadName.substring(threadName.length() - 1)) - 1;
        }
        int otherThread = threadNum ^ 1;
//        System.out.println(java.time.LocalDateTime.now()+"\\t"+"Thread#"+threadNum+" invoked. Other thread state:"+threadsState[otherThread]+". last finished:"+lastFinished);
        if (MAX_THREADS == 2) {
            while (threadsState[otherThread] && lastFinished == threadNum) {
                Thread.sleep(3000);
            }
        }
        boolean failed=false;
        String errorMessage="";
        threadsState[threadNum] = true;
        try {
//            simulate(threadNum);
            doLiveness(threadNum);
            completedCount++;
        } catch (Exception e) {
            failed=true;
            errorMessage=e.getMessage();
        }
        finally {
            threadsState[threadNum] = false;
            lastFinished = threadNum;
            if (failed){
                throw new Exception(errorMessage);
//                Assert.fail(errorMessage);
            }
        }
    }

    private void doLiveness(int threadNum) throws IOException, InterruptedException {
        WebDriver driver = drivers.get(threadNum);
        driver.get(LIVENESS_DEMO);
        MainPage mainPage = new MainPage(driver);
        mainPage.chooseFromServicesList("Liveness");
        Injection injection = new Injection(driver);
        injection.imageInjection("./liveness/apiPic/barCenter.jpg", 1, false);
        ResultsPage resultsPage = new ResultsPage(driver);
        resultsPage.collectResults(variables);
        Assert.assertEquals(variables.getActionType(), "complete");
        threadsState[threadNum] = false;
        lastFinished = threadNum;
    }

    private void simulate(int threadNum) throws Exception {
        List<Integer> duration = Arrays.asList(5000, 5000, 10000, 10000);
        Random rand = new Random();
        int sleepTime = duration.get(rand.nextInt(duration.size()));
        Thread.sleep(sleepTime);
        if (sleepTime == 10000) {
            throw new Exception("fail");
        }
    }

    @AfterClass
    public void checkPercent() {
        for (WebDriver driver : drivers) {
            driver.quit();
        }
        System.out.println("completed:" + completedCount);
        System.out.println("invoked:" + INVOCATIONS);
        float passed = (float) completedCount / (float) INVOCATIONS;
        float thresh = (float) THRESHOLD / 100.0f;
        if (passed < thresh) {
            Assert.fail("percentage failed(" + passed * 100 + "%):" + completedCount + " passed out of " + INVOCATIONS + ". Threshold:" + THRESHOLD + "%");
        }
    }
}
