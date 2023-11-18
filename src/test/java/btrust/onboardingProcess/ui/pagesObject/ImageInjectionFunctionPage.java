package btrust.onboardingProcess.ui.pagesObject;

import btrust.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.util.Arrays;
import java.util.Base64;

public class ImageInjectionFunctionPage extends BasePage {
    private final int INJECT_IMAGE_SLEEP_DEFAULT = 12000;

    public ImageInjectionFunctionPage(WebDriver driver) {
        super(driver);
        js.executeScript(DEFAULT_AUDIO_OVERRIDE);
    }

    public static String DEFAULT_AUDIO_OVERRIDE = "window.getOutsideInjectedAudio = function() {" +
            "console.log(`Israel Defense Forces are in control now`);" +
            "return {blob: new Blob()};}";


    @FindBy(css = "#modular-iframe")
    protected WebElement ocrFrame;
    @FindBy(css = "#modular-iframe")
    protected WebElement livenessFrame;
    @FindBy(css = ".CaptureProcessingOverlay_motionContainer__progressLabel__BpENM")
    protected WebElement stageCounter;
    @FindBy(css = "#root > div > div.CaptureProcessingOverlay_container__c97-i > div > div.RecordOverlay_container__INdRS > div.RecordOverlay_container__button__XH\\+05")
    protected WebElement recordingButton;
    @FindBy(css = ".CCSvg_container__ofPbY.CaptureProcessingOverlay_container__backIcon__YUdMY")
    protected WebElement exitButton;




     /*
    ================================
    JS Function --> don't touch!!!
    ================================
     */


    @Step("Check if stage counter is display")
    public boolean stageCounterDisplayed(String serviceName) throws IOException {
        switch (serviceName) {
            case "Ocr":
            case "Liveness":
                driver.switchTo().frame(driver.findElement(By.id("modular-iframe")));
                break;
        }
        waitForPageFinishLoading();
        waitForElementToBeVisibility(stageCounter);
        scrollToElement(stageCounter);
        return isDisplayed(stageCounter);
    }

    @Step("Click On Exit button")
    public void clickOnExitButton() throws IOException {
        waitForElementToBeClickable(exitButton);
        click(exitButton);
    }


    @Step("Scan card detector")
    public void scanCardDetector(String filePath) throws IOException {
        File folder = new File(filePath);
        File[] listOfFiles = folder.listFiles();
        assert listOfFiles != null;
        for (File file : listOfFiles) {
            System.out.println("Choose file: \n" + file.getName());
            BufferedImage image = ImageIO.read((file));
            byte[] arr = encodeToByteArray(image, "jpg");
            StringBuilder sb = new StringBuilder();
            if (arr != null) {
                for (int i = 0; i < arr.length; i++) {
                    if (i == 0) {
                        sb.append("[");
                    }
                    sb.append(arr[i]);
                    if (i != arr.length - 1) {
                        sb.append(",");
                    } else {
                        sb.append("]");
                    }
                }
            }
            for (int y = 0; y < listOfFiles.length; y++) {
                js.executeScript("window.getOutsideInjectedImage = function() {" +
                        "console.log('liad Sending');" +
                        "const myBlob = new Blob([new Uint8Array(JSON.parse('" + sb + "')).buffer], {type: 'image/jpg'});" +
                        "window.liadBlob = myBlob;" +
                        "window.liadSb ='" + sb + "';" +
                        "console.log('liad blob' + myBlob);" +
                        "return myBlob;}");
            }
            System.out.println("Inject image: \n" + file.getName());
            sleep(10000);
        }
    }

    @Step("Scan image")
    public void scanImage(String filePath, String side) throws IOException {
        scanImage(filePath, side, INJECT_IMAGE_SLEEP_DEFAULT);
    }

    @Step("Scan image")
    public void scanImage(String filePath, String side, int sleepTime) throws IOException {
        BufferedImage image = ImageIO.read(new File(filePath));
        js.executeScript(DEFAULT_AUDIO_OVERRIDE);
        byte[] arr = encodeToByteArray(image, "jpg");
        StringBuilder sb = new StringBuilder();
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                if (i == 0) {
                    sb.append("[");
                }
                sb.append(arr[i]);
                if (i != arr.length - 1) {
                    sb.append(",");
                }
            }
        }
        sb.append("]");
        js.executeScript("window.getOutsideInjectedImage = function() {" +
                "console.log('liad Sending index #" + side + "');" +
                "const myBlob = new Blob([new Uint8Array(JSON.parse('" + sb + "')).buffer], {type: 'image/jpg'});" +
                "window.liadBlob = myBlob;" +
                "window.liadSb ='" + sb + "';" +
                "console.log('liad blob' + myBlob);" +
                "return myBlob;}");

        System.out.println("Inject image: \n" + filePath);
        sleep(sleepTime);
        try {
            js.executeScript("window.getOutsideInjectedImage = null");
        } catch (Exception e) {
            {
            }
            ;
        }
    }

    @Step("Inject card capture")
    public void injectCardCapture(String filePath) throws IOException {
        sleep(5000);
        BufferedImage image = ImageIO.read(new File(filePath));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", baos);
        byte[] bytes = baos.toByteArray();
        String base64image = Base64.getEncoder().encodeToString(bytes);

        js.executeScript("window.getOutsideInjectedImage = function() {" +
                "console.log('liad Sending index #" + 1 + "');" +
                "const myBase64 = '" + base64image + "';" +
                "console.log('liad blob " + base64image + "');" +
                "return myBase64;}");
        System.out.println("Inject file: \n" + filePath);
        System.out.println("============================================\n");
    }

    @Step("Scan OCR")
    public void scanOCR(String ocr, String side) throws IOException {
        waitForElementToBeVisibility(ocrFrame);
        sleep(2000);
        driver.switchTo().frame(ocrFrame);
        scanImage(ocr, side);
        driver.switchTo().defaultContent();
    }

    @Step("Scan OCR 2 sides")
    public void scanOCR2Sides(String frontImageFilePath, String backImageFilePath) throws IOException {
        boolean flag = false;
        waitForElementToBeVisibility(ocrFrame);
        driver.switchTo().frame(ocrFrame);
        if (backImageFilePath != null) {
            do {
                waitForElementToBeVisibility(stageCounter);
                switch (getText(stageCounter)) {
                    case "1/2": {
                        scanImage(frontImageFilePath, null);
                        break;
                    }
                    case "2/2": {
                        scanImage(backImageFilePath, null);
                        flag = true;
                        break;
                    }
                }
            } while (!flag);
        } else {
            scanImage(frontImageFilePath, null);
        }
        driver.switchTo().defaultContent();
    }

    @Step("Scan Liveness")
    public void scanLiveness(String centerPath) throws IOException {
        waitForElementToBeVisibility(livenessFrame);
        driver.switchTo().frame(livenessFrame);
        scanImage(centerPath, null);
        driver.switchTo().defaultContent();
    }

    @Step("Scan Liveness")
    public void scanLiveness(String centerPath,int sleepTime) throws IOException {
        waitForElementToBeVisibility(livenessFrame);
        driver.switchTo().frame(livenessFrame);
        scanImage(centerPath, null,sleepTime);
        driver.switchTo().defaultContent();
    }

    @Step("Scan Liveness")
    public void scanLivenessActive(String leftPath, String centerPath, String rightPath) throws IOException {
        waitForElementToBeVisibility(livenessFrame);
        driver.switchTo().frame(livenessFrame);
        waitForElementToBeVisibility(stageCounter);
        System.out.println(getText(stageCounter));
        switch (getText(stageCounter)) {
            case "1/5": {
                scanImage(centerPath, null);
            }
            case "2/5": {
                scanImage(leftPath, null);
            }
            case "3/5": {
                scanImage(centerPath, null);
            }
            case "4/5": {
                scanImage(rightPath, null);
            }
            case "5/5": {
                click(recordingButton);
                break;
            }
        }
        driver.switchTo().defaultContent();
    }

    @Step("Scan Liveness")
    public void scanLivenessProduction(String filePath) throws IOException {
        File folder = new File(filePath);
        File[] listOfFiles = folder.listFiles();
        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                System.out.println("Choose file: \n" + file.getName());
                BufferedImage image = ImageIO.read((file));
                byte[] arr = encodeToByteArray(image, "jpg");
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < arr.length; i++) {
                    if (i == 0) {
                        sb.append("[");
                    }
                    sb.append(arr[i]);
                    if (i != arr.length - 1) {
                        sb.append(",");
                    } else {
                        sb.append("]");
                    }
                }
                for (int y = 0; y < listOfFiles.length; y++) {
                    JavascriptExecutor js = (JavascriptExecutor) driver;
                    js.executeScript("window.getOutsideInjectedImage = function() {" +
                            "console.log('liad Sending');" +
                            "const myBlob = new Blob([new Uint8Array(JSON.parse('" + sb + "')).buffer], {type: 'image/jpg'});" +
                            "window.liadBlob = myBlob;" +
                            "window.liadSb ='" + sb + "';" +
                            "console.log('liad blob' + myBlob);" +
                            "return {data: myBlob, frameId: Date.now()};" +
                            "}");
                }

                System.out.println("Inject image: \n" + file.getName());
                sleep(20000);
                js.executeScript("window.getOutsideInjectedImage = null");
            }
        }
    }

    public static String imgToBase64String(final RenderedImage img, final String formatName) {
        final ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ImageIO.write(img, formatName, os);
            return Base64.getEncoder().encodeToString(os.toByteArray());
        } catch (final IOException ioe) {
            throw new UncheckedIOException(ioe);
        }
    }

    @Step("Encoded image to byte")
    public static byte[] encodeToByteArray(BufferedImage image, String type) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, type, bos);
            byte[] imageBytes = bos.toByteArray();

            bos.close();
            return imageBytes;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Step("Inject audio in the stt stage")
    public void sttInjection(String chunksPath) {
        js.executeScript(DEFAULT_AUDIO_OVERRIDE + "; console.log('stt start audio chunk injection');");
        try {
            audioInjectionHandler(new File(chunksPath));
            ScanPage scanPage = new ScanPage(driver);
            System.out.println("finished injecting");
            Thread.sleep(8000);
            scanPage.clickRecordingButton();
            driver.switchTo().defaultContent();
        } catch (Exception e) {
            System.out.println("Error in stt injection: " + e.getMessage());
        }
    }

    @Step("Injecting audio chunk from inside a folder")
    public void audioInjectionHandler(File file) throws IOException, InterruptedException {
        ScanPage scanPage = new ScanPage(driver);
        boolean flag = false;
        do {
            waitForElementToBeVisibility(livenessFrame);
            driver.switchTo().frame(livenessFrame);
            if ((scanPage.getInstructionsTitle().equalsIgnoreCase("Read the code out loud")) || (scanPage.getInstructionsTitle().equalsIgnoreCase("Read the text out loud")) || (scanPage.getInstructionsTitle().equalsIgnoreCase("Thinking about it..."))) {
                File[] listOfFiles = file.listFiles();
                Arrays.sort(listOfFiles);
                System.out.println(driver.manage().getCookies());
                if (file.isDirectory()) {
                    int index = 0;
                    for (File file2 : listOfFiles) {
                        System.out.println("Index: " + index);
                        System.out.println("Length: " + (listOfFiles.length - 1));
                        audioInjection(file2, 1, index == (listOfFiles.length - 1));
                        index++;
                    }
                } else audioInjection(file, 1);
                flag = true;
            }
        } while (!flag);
    }

    @Step("Another implementation of audioInjection")
    public void audioInjection(File file, int index) throws IOException, InterruptedException {
        audioInjection(file, index, false);
    }

    @Step("Inject audio chunk")
    public void audioInjection(File file, int index, boolean isFinal) throws IOException, InterruptedException {
        String filename = file.getName();

        byte[] arr = new byte[1590000];
        if (!file.isDirectory()) {
            InputStream target = new FileInputStream(file);
            arr = readBytes(target);
        } else {
            File[] listOfFiles = file.listFiles();
            for (File ff : listOfFiles) {
                InputStream target = new FileInputStream(ff);
                arr = readBytes(target);
            }
        }
        System.out.println("Audio chunk length: " + arr.length);
        driver.manage().addCookie(driver.manage().getCookieNamed("_gat"));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            if (i == 0) {
                sb.append("[");
            }
            sb.append(arr[i]);
            if (i != arr.length - 1) {
                sb.append(",");
            }
        }
        if (isFinal) {
            System.out.println("Final");
        }
        sb.append("]");
        js.executeScript("window.combinedChunks = window.combinedChunks || [];" +
                "window.combinedChunks = window.combinedChunks.concat(JSON.parse('" + sb + "'));" +
                "window.allowAudioSending = true;" +
                "window.getOutsideInjectedAudio = function() {" +
                "if(window.allowAudioSending === false) {return {blob: new Blob()};}" +
                "console.log('moran Sending index #" + index + "');" +
                "const myBlob = new Blob([new Uint8Array(window.combinedChunks).buffer], {type: 'audio/wav'});" +
                "window.audioTestBlob = myBlob;" +
                "console.log(`${Date.now()} - audio chunk injection (fn: " + filename + ", fs: ${myBlob.size}) blob: ${myBlob}`);" +
                "if(" + !isFinal + "){window.combinedChunks = []; " + DEFAULT_AUDIO_OVERRIDE + ";} else {window.allowAudioSending = false; setTimeout(() => {window.allowAudioSending = true}, 3000)}" +
                "return {blob: myBlob};}");

        System.out.println("Inject file: \n" + file.getPath());
        System.out.println("============================================\n");
        if (!isFinal) {
            Thread.sleep(2000);
        }
    }

    @Step("Convert inputStream to array-byte")
    public static byte[] readBytes(InputStream inputStream) throws IOException {
        byte[] b = new byte[605000];
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        int c;
        while ((c = inputStream.read(b)) != -1) {
            os.write(b, 0, c);
        }
        return os.toByteArray();
    }
}