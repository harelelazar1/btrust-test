package selenium;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import selenium.ocr.pageObject.newService.ResultsPage;
import selenium.ocr.pageObject.newService.ScanPage;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Injection extends BasePage {

    public static String DEFAULT_AUDIO_OVERRIDE = "window.getOutsideInjectedAudio = function() {" +
            "console.log(`Israel Defense Forces are in control now`);" +
            "return {blob: new Blob()};}";

    public Injection(WebDriver driver) {
        super(driver);
    }

    @Step("Get a string and divide it by and desire quantity")
    private static List<String> getParts(String string, int partitionSize) {
        List<String> parts = new ArrayList<>();
        int len = string.length();
        for (int i = 0; i < len; i += partitionSize) {
            parts.add(string.substring(i, Math.min(len, i + partitionSize)));
        }
        return parts;
    }

    public void backImagePixels(List<Color> colorArrayFromPallet) {
        js = (JavascriptExecutor) driver;
        BufferedImage image = new BufferedImage(256, 256, BufferedImage.TYPE_INT_RGB);

        for (int i = image.getMinX(); i < 8; i++) {
            for (int j = image.getMinY(); j < 8; j++) {
                image.setRGB(i, j, colorArrayFromPallet.get(0).getRGB()); //top left
            }
        }


        //Color the top right corner
        for (int i = image.getWidth() - 1; i > image.getWidth() - 9; i--) {
            for (int j = image.getMinY(); j < 8; j++) {
                image.setRGB(i, j, colorArrayFromPallet.get(1).getRGB()); //top right

            }
        }

        //Color the bottom right corner
        for (int i = image.getWidth() - 1; i > image.getWidth() - 9; i--) {
            for (int j = image.getHeight() - 1; j > image.getHeight() - 9; j--) {
                image.setRGB(i, j, colorArrayFromPallet.get(2).getRGB()); //bottom right

            }
        }

        //Color the bottom left corner
        for (int i = image.getMinX(); i < 8; i++) {
            for (int j = image.getHeight() - 1; j > image.getHeight() - 9; j--) {
                image.setRGB(i, j, colorArrayFromPallet.get(3).getRGB()); //bottom right
            }
        }

        byte[] arr = encodeToByteArray(image, "jpg");
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
        sb.append("]");
        js.executeScript("window.getOutsideInjectedImage = function() {" +
                "console.log('pixels doron Sending index #doron');" +
                "const myBlob = new Blob([new Uint8Array(JSON.parse('" + sb + "')).buffer], {type: 'image/jpg'});" +
                "window.videoTestBlob = myBlob;" +
                "console.log(`${Date.now()} - video frame injection (fn: , fs: ${myBlob.size}) blob: ${myBlob}`);" +
                "return myBlob;}");

        js.executeScript(Injection.DEFAULT_AUDIO_OVERRIDE);
    }

    @Step("Injecting colored pixels to image")
    public File injectPixels(String cPalette, File inputImage, float compressionQuality, File outputImage) throws IOException {
        System.out.println("Original file: " + inputImage.getName());
        int partitionSize = 6;
        List<String> parts = new ArrayList<>();
        List<Color> colorArrayFromPallet = new ArrayList<>();

        int len = cPalette.length();
        for (int i = 0; i < len; i += partitionSize) {
            parts.add(cPalette.substring(i, Math.min(len, i + partitionSize)));
        }

        for (String part : getParts(cPalette, 6)) {
            System.out.println(part);
            colorArrayFromPallet.add(Color.decode("#" + part));
        }

//        backImagePixels(colorArrayFromPallet);

        BufferedImage bufferedImage = ImageIO.read(inputImage);
        String TopLeft = bufferedImage.getMinX() + "," + bufferedImage.getMinY();
        String TopRight = bufferedImage.getWidth() + "," + bufferedImage.getMinY();
        String BottomRight = bufferedImage.getWidth() + "," + bufferedImage.getHeight();
        String BottomLeft = bufferedImage.getMinX() + "," + bufferedImage.getHeight();
        System.out.println("Top left: " + TopLeft);
        System.out.println("Top right: " + TopRight);
        System.out.println("Bottom right: " + BottomRight);
        System.out.println("Bottom left: " + BottomLeft);
        System.out.println("****");

        //Color the top left corner
        for (int i = bufferedImage.getMinX(); i < 8; i++) {
            for (int j = bufferedImage.getMinY(); j < 8; j++) {
//                System.out.println(i + "," + j);
                bufferedImage.setRGB(i, j, colorArrayFromPallet.get(0).getRGB()); //top left
            }
        }


        //Color the top right corner
        for (int i = bufferedImage.getWidth() - 1; i > bufferedImage.getWidth() - 9; i--) {
            for (int j = bufferedImage.getMinY(); j < 8; j++) {
//                System.out.println(i + "," + j);
                bufferedImage.setRGB(i, j, colorArrayFromPallet.get(1).getRGB()); //top right

            }
        }

        //Color the bottom right corner
        for (int i = bufferedImage.getWidth() - 1; i > bufferedImage.getWidth() - 9; i--) {
            for (int j = bufferedImage.getHeight() - 1; j > bufferedImage.getHeight() - 9; j--) {
//                System.out.println(i + "," + j);
                bufferedImage.setRGB(i, j, colorArrayFromPallet.get(2).getRGB()); //bottom right

            }
        }

        //Color the bottom left corner
        for (int i = bufferedImage.getMinX(); i < 8; i++) {
            for (int j = bufferedImage.getHeight() - 1; j > bufferedImage.getHeight() - 9; j--) {
//                System.out.println(i + "," + j);
                bufferedImage.setRGB(i, j, colorArrayFromPallet.get(3).getRGB()); //bottom right

            }
        }

        File pngPixelatedImage = new File("./ocr/pixelatedImages/pngPixelatedImage.png");
        ImageIO.write(bufferedImage, "png", pngPixelatedImage);
        File outputFileAfterCompression = outputImage;
        checkPixelsRGBAfterCompression(compressJPG(pngPixelatedImage, compressionQuality, outputFileAfterCompression));
        System.out.println("Return file: " + outputFileAfterCompression.getName());
        return outputFileAfterCompression;
    }

    public File compressJPG(File inputFile, float quality2, File outputFile) throws IOException {
        File compressedImageFile = outputFile;

        InputStream is = new FileInputStream(inputFile);
        OutputStream os = new FileOutputStream(compressedImageFile);

        float quality = quality2;
        BufferedImage image = ImageIO.read(is);
        Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpg");
        if (!writers.hasNext())
            throw new IllegalStateException("No writers found");

        ImageWriter writer = (ImageWriter) writers.next();
        ImageOutputStream ios = ImageIO.createImageOutputStream(os);
        writer.setOutput(ios);
        ImageWriteParam param = writer.getDefaultWriteParam();
        param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        param.setCompressionQuality(quality);
        writer.write(null, new IIOImage(image, null, null), param);
        is.close();
        os.close();
        ios.close();
        writer.dispose();
        System.out.println(compressedImageFile.getName());
        return compressedImageFile;
    }

    public void checkPixelsRGBAfterCompression(File file) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(file);
        List<Color> colorArrayAfterCompression = new ArrayList<>();
        colorArrayAfterCompression.add(Color.decode(String.valueOf(bufferedImage.getRGB(bufferedImage.getMinX(), bufferedImage.getMinY()))));
        colorArrayAfterCompression.add(Color.decode(String.valueOf(bufferedImage.getRGB(bufferedImage.getWidth() - 1, bufferedImage.getMinY()))));
        colorArrayAfterCompression.add(Color.decode(String.valueOf(bufferedImage.getRGB(bufferedImage.getWidth() - 1, bufferedImage.getHeight() - 1))));
        colorArrayAfterCompression.add(Color.decode(String.valueOf(bufferedImage.getRGB(bufferedImage.getMinX(), bufferedImage.getHeight() - 1))));

        System.out.println(colorArrayAfterCompression.get(0));
        System.out.println(colorArrayAfterCompression.get(1));
        System.out.println(colorArrayAfterCompression.get(2));
        System.out.println(colorArrayAfterCompression.get(3));
    }

    @Step("CNI images injection according the instruction title")
    public void cniInjection(String frontImageFilePath, String backImageFilePath) throws IOException, InterruptedException {
        ScanPage scanPage = new ScanPage(driver);
        do {
            switch (scanPage.getInstructionsTitle()) {
                case "Scan ID front side": {
                    imageInjection(frontImageFilePath, 1, false);
                    break;
                }
                case "Scan ID back side":
                case "No card detected": {
                    imageInjection(backImageFilePath, 1, false);
                    break;
                }
                case "Tilt your card": {
                    System.out.println("Video stage");
                    Thread.sleep(5000);
                    break;
                }
            }
        } while (!scanPage.isLabelAppear());
    }

    @Step("Inject front or back images according with tilt support")
    public void imageInjectionWithTilt(String frontImageFilePath, String backImageFilePath, int index, boolean backSideTimeoutCheck, String timeoutStage) throws IOException, InterruptedException, UnsupportedAudioFileException {
        long start = System.currentTimeMillis();
        String stgCounter;
        String stgTitle;
        ScanPage scanPage = new ScanPage(driver);
        ResultsPage resultsPage = new ResultsPage(driver);
        boolean flag = false;
        if (backSideTimeoutCheck) {
            do {
                stgCounter = scanPage.getStageCounter();
                stgCounter = stgCounter.substring(0, 1);
                switch (stgCounter) {
                    case "1":
                        if (stgCounter.equalsIgnoreCase(timeoutStage)) {
                            System.out.println("inside if - stage: " + stgCounter);
                            Thread.sleep(88000);
                            flag = true;
                        } else {
                            System.out.println("inside else - stage: " + stgCounter);
                            imageInjection(frontImageFilePath, index, false);
                        }
                        break;
                    case "2":
                        Thread.sleep(8000);
                        System.out.println("stage 2");
                        break;
                    case "3":
                        if (stgCounter.equalsIgnoreCase(timeoutStage)) {
                            System.out.println("inside if - stage: " + stgCounter);
                            Thread.sleep(88000);
                            flag = true;
                        } else {
                            System.out.println("inside else - stage: " + stgCounter);
                            imageInjection(backImageFilePath, index, false);
                        }
                        break;
                    case "4":
                        Thread.sleep(8000);
                        System.out.println("stage 4");
                        break;
                }
            } while (!flag && System.currentTimeMillis() - start < 90000);
        } else {
            do {
                try{
                    stgCounter = scanPage.getStageCounter();
                    stgCounter = stgCounter.substring(0, 1);
                }
                catch (Exception e){
                    stgCounter="0";
                }
                try{
                    stgTitle = scanPage.getInstructionsTitle();
                }
                catch (Exception e){
                    stgTitle="No stage title found";
                }
                switch (stgTitle) {
                    case "Scan ID front side": {
                        System.out.println("Stage#: " + stgCounter);
                        System.out.println("Stage title: " + stgTitle);
                        imageInjection(frontImageFilePath, index, false);
                        break;
                    }
                    case "Scan ID back side":
                    case "Wrong side":
                        System.out.println("Stage#: " + stgCounter);
                        System.out.println("Stage title: " + stgTitle);
                        imageInjection(backImageFilePath, index, false);
                        break;
                    case "Tilt your card": {
                        System.out.println("Stage#: " + stgCounter);
                        System.out.println("Stage title: " + stgTitle);
                        Thread.sleep(8000);
                        break;
                    }
                    case "Read the code out loud": {
                        System.out.println("Stage#: " + stgCounter);
                        System.out.println("Stage title: " + stgTitle);
                        audioInjectionHandler1(new File("./liveness/audio/otp/otpAudioChunks"));
                        Thread.sleep(5000);
                        scanPage.clickRecordingButton();
                        break;
                    }
                }
            } while (!resultsPage.titleExists() && System.currentTimeMillis() - start < 90000);
            System.out.println("Exiting tilt injection method.");
        }
    }

    @Step("Inject gesture image in the gestures stage")
    public void injectGesturesImages(ScanPage scanPage, String imageToInject) {
        if (scanPage.getInstructionsTitle().equalsIgnoreCase("Reproduce gestures in order")) {
            for (int i = 1; i <= scanPage.getGestureArrow(); i++) {
                System.out.println("Gesture number: " + i);
                try {
                    imageInjection(imageToInject, 1, false);
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }
        //Check if the gestures stage in the final stage and if so, loader should appear.
        if (scanPage.getStageCounter().split("/")[0].equalsIgnoreCase(scanPage.getStageCounter().split("/")[1])) {
            System.out.println("gestures loader check");
            //           Assert.assertTrue(scanPage.isGesturesLoaderAppeared());
        }
    }

    @Step("Inject gesture image in the gestures stage")
    public void injectGesturesImages1(ScanPage scanPage, String imageToInject) {
        if (scanPage.getInstructionsTitle().equalsIgnoreCase("Reproduce gestures in order")) {
            for (int i = 1; i <= scanPage.getGestureArrow(); i++) {
                System.out.println("Gesture number: " + i);
                try {
                    imageInjection(imageToInject, 1, false);
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }
        //Check if the gestures stage in the final stage and if so, loader should appear.
        if (scanPage.getStageCounter().split("/")[0].equalsIgnoreCase(scanPage.getStageCounter().split("/")[1])) {
            System.out.println("gestures loader check");
            //           Assert.assertTrue(scanPage.isGesturesLoaderAppeared());
        }
    }



    @Step("Inject front or back card images according to the card type, with errorMessage indication support")
    public boolean imageInjectionConfigureNew(String frontImageFilePath, String backImageFilePath, int index, boolean errorMessageCheck, String errorMessage) throws IOException, InterruptedException {
        ScanPage scanPage = new ScanPage(driver);
        int maxAttempts = 5;
        int maxStage2Attempts = 10;

        try {
            if (errorMessageCheck) {
                for (int counter = 1; counter <= maxAttempts; counter++) {
                    System.out.println("Injection attempt: " + counter);
                    imageInjection(frontImageFilePath, index, false);

                    if (scanPage.getInstructionsTitleNew(errorMessage).equalsIgnoreCase(errorMessage)) {
                        System.out.println("Error message: -" + errorMessage + "- appeared! :)");
                        return true;
                    } else if (counter == maxAttempts) {
                        System.out.println("Too many injection attempts");
                        return false;
                    } else {
                        System.out.println("Error message: -" + errorMessage + "- isn't appeared! :(");
                    }
                }
            } else {
                imageInjection(frontImageFilePath, index, true);

                if (backImageFilePath != null) {
                    for (int counter = 1; counter <= maxStage2Attempts; counter++) {
                        if (scanPage.getStageCounter().equalsIgnoreCase("2/2")) {
                            imageInjection(backImageFilePath, index, true);
                            return true;
                        }

                        counter++;
                        if (counter == maxStage2Attempts) {
                            System.out.println("Stage 2 didn't appear for 10 times. Fail injection");
                            return false;
                        }
                    }
                }

                if (scanPage.CardCaptureButtonDisplay()) {
                    scanPage.clickCardCaptureButton();
                }
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return false;
    }

    @Step("Inject front or back card images according the card type, with errorMessage indication support")
    public boolean imageInjectionConfigure(String frontImageFilePath, String backImageFilePath, int index, boolean errorMessageCheck, String errorMessage) throws IOException, InterruptedException {
        ScanPage scanPage = new ScanPage(driver);
        int counter = 1;
        try {
            if (errorMessageCheck) {
                boolean flag = false;
                do {
                    System.out.println("Injection attempt: " + counter);
                    imageInjection(frontImageFilePath, index, false);
                    if (scanPage.getInstructionsTitle().equalsIgnoreCase(errorMessage)) {
                        System.out.println("Error message: -" + errorMessage + "- appeared! :)");
                        flag = true;
                    } else {
                        if (counter == 5) {
                            System.out.println("Too many injection attempts");
                            return false;
                        }
                        System.out.println("Error message: -" + errorMessage + "- isn't appeared! :(");
                        counter++;
                    }
                } while (!flag);
            } else {
                imageInjection(frontImageFilePath, index, true);
                if (backImageFilePath != null) {
                    boolean flag = false;
                    counter = 1;
                    do {
                        if (scanPage.getStageCounter().equalsIgnoreCase("2/2")) {
                            imageInjection(backImageFilePath, index, true);
                            flag = true;
                        }
                        counter++;
                        if (counter == 11) {
                            System.out.println("Stage 2 didn't appear for 10 times. fail injection ");
                            return false;
                        }
                    } while (!flag);
                }
                if (scanPage.CardCaptureButtonDisplay()) {
                    scanPage.clickCardCaptureButton();
                }

            }
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    @Step("Inject liveness active images")
    public boolean livenessInjection(String leftPath, String centerPath, String rightPath, int index, boolean isNotFaceFoundFromErrorMessages, String errorMessage) throws IOException, InterruptedException {
        ScanPage scanPage = new ScanPage(driver);
        boolean flag = false;

        if (!isNotFaceFoundFromErrorMessages) {
//            if (scanPage.getStageCounter().equalsIgnoreCase("1/5")) {
//                imageInjection(centerPath, index, false);
//            } else
//                if (scanPage.getStageCounter().equalsIgnoreCase("")) {
//                imageInjection(centerPath, index, false);
//                return true;
//            }

            do {
                String dynamicLivenessStage = scanPage.getInstructionsTitle();
                switch (dynamicLivenessStage) {
                    case "Turn your head to the left":
                    case "הזיזו את הראש שמאלה":
                    case "Look left": {
                        imageInjection(leftPath, index, false);
                        break;
                    }

                    case "Face not properly illuminated":
                    case "Face mask detected":
                    case "Face not in focus":
                    case "תאורה לא טובה":
                    case "זוהתה מסכת פנים":
                    case "הפנים אינן בפוקוס":
                    case "לא נמצאו פנים":
                    case "הביטו ישר":
                    case "מקמו את הפנים במסגרת":
                    case "No face found":
                    case "Place your face in the frame":
                    case "Look straight": {
                        if (dynamicLivenessStage.equalsIgnoreCase(errorMessage)) {
                            flag = true;
                            System.out.println(errorMessage + " appears at the screen");
                            break;
                        } else System.out.println("no");
                        imageInjection(centerPath, index, false);
                        break;
                    }

                    case "Turn your head to the right":
                    case "הזיזו את הראש ימינה":
                    case "Look right": {
                        imageInjection(rightPath, index, false);
                        break;
                    }

                    case "Read the code out loud":
                    case "קראו את הקוד בקול": {
                        scanPage.clickRecordingButton();
                        flag = true;
                        break;
                    }

                    case "Too many faces":
                    case "Come closer": {
                        flag = true;
                        System.out.println(errorMessage + " appears at the screen");
                        break;
                    }
                }
            } while (!flag);
        } else {
            do {
                String dynamicLivenessStage = scanPage.getInstructionsTitle();
                switch (dynamicLivenessStage) {
                    case "No face found": {
                        System.out.println("No face found - from Error Message");
                        flag = scanPage.waitForErrorTextToBeInInstructionsTitle(errorMessage);
                        break;
                    }
                }
            } while (!flag);
        }
        return flag;
    }

    @Step("Liveness images injection according the stage and direction")
    public void livenessInjection2(String leftPath, String centerPath, String rightPath) throws IOException, InterruptedException {
        ScanPage scanPage = new ScanPage(driver);
        boolean flag = false;
        boolean isFinalStage = false;

        do {
            switch (scanPage.getInstructionsTitle()) {
                case "Look straight":
                case "Place your face in the frame": {
                    imageInjection(centerPath, 1, false);
                    break;
                }
                case "Turn your head to the right":
                case "Look right": {
                    if (scanPage.getStageCounter().split("/")[0].equalsIgnoreCase(scanPage.getStageCounter().split("/")[1])) {
                        isFinalStage = true;
                    }
                    imageInjection(rightPath, 1, false);
                    if (isFinalStage) {
                        flag = true;
                    }
                    break;
                }
                case "Turn your head to the left":
                case "Look left": {
                    if (scanPage.getStageCounter().split("/")[0].equalsIgnoreCase(scanPage.getStageCounter().split("/")[1])) {
                        isFinalStage = true;
                    }
                    imageInjection(leftPath, 1, false);
                    if (isFinalStage) {
                        flag = true;
                    }
                    break;
                }
                case "No face found": {
                    imageInjection(rightPath, 1, false);
                    switch (scanPage.getInstructionsTitle()) {
                        case "Look left": {
                            if (scanPage.getStageCounter().split("/")[0].equalsIgnoreCase(scanPage.getStageCounter().split("/")[1])) {
                                isFinalStage = true;
                            }
                            imageInjection(leftPath, 1, false);
                            if (isFinalStage) {
                                flag = true;
                            }
                            break;
                        }
                        case "Look right": {
                            if (scanPage.getStageCounter().split("/")[0].equalsIgnoreCase(scanPage.getStageCounter().split("/")[1])) {
                                isFinalStage = true;
                            }
                            imageInjection(rightPath, 1, false);
                            if (isFinalStage) {
                                flag = true;
                            }
                            break;
                        }
                        case "Look straight": {
                            imageInjection(centerPath, 1, false);
                            break;
                        }
                    }
                    break;
                }
                case "Read the code out loud":
                case "Read the text out loud": {
                    System.out.println("Exiting.");
                    flag = true;
                    break;
                }
            }
        } while (!flag);
    }


    @Step("Inject audio in the otp stage")
    public void otpInjection(String chunkPath) {
        try {
            boolean flag = false;
            audioInjectionHandler(new File(chunkPath));
            ScanPage scanPage = new ScanPage(driver);
            scanPage.clickRecordingButton();
            do {
                if (scanPage.getInstructionsTitle().equalsIgnoreCase("Read the text out loud") || scanPage.getInstructionsTitle().equalsIgnoreCase("Reproduce gestures in order")) {
                    flag = true;
                }
            } while (!flag);
        } catch (Exception e) {
            System.out.println("Error in otp injection: " + e.getMessage());
        }
    }


    @Step("Inject audio in the otp stage")
    public void otpInjection1(String chunkPath) {
        try {
            boolean flag = false;
            audioInjectionHandler1(new File(chunkPath));
            ScanPage scanPage = new ScanPage(driver);
            scanPage.clickRecordingButton();
            do {
                if (scanPage.getInstructionsTitle().equalsIgnoreCase("Read the text out loud") || scanPage.getInstructionsTitle().equalsIgnoreCase("Reproduce gestures in order")) {
                    flag = true;
                }
            } while (!flag);
        } catch (Exception e) {
            System.out.println("Error in otp injection: " + e.getMessage());
        }
    }

    @Step("Inject audio in the stt stage")
    public void sttInjection(String chunksPath) {
        js.executeScript(DEFAULT_AUDIO_OVERRIDE + "; console.log('stt start audio chunk injection');");
        try {
            audioInjectionHandler(new File(chunksPath));
            ScanPage scanPage = new ScanPage(driver);
//            Thread.sleep(3000);
            scanPage.clickRecordingButton();
        } catch (Exception e) {
            System.out.println("Error in stt injection: " + e.getMessage());
        }
    }


    @Step("Inject image and check if error message is returning from it")
    public boolean injectionErrorMessage(String imagePath, String errorMessage) {
        boolean flag = false;
        ScanPage scanPage = new ScanPage(driver);
        try {
            imageInjection(imagePath, 1, false);
            do {
                if (scanPage.getInstructionsTitle().equalsIgnoreCase(errorMessage)) {
                    System.out.println("Error message: '" + errorMessage + "' appeared on the screen.");
                    flag = true;
                } else {
                    System.out.println("Error message didn't appeared on the screen.");
                    imageInjection(imagePath, 1, false);
                }
            } while (!flag);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return flag;
    }

    @Step("Inject folder of images")
    public void injectFolder(String frontFolderPath, String backFolderPath) throws IOException, InterruptedException {
        ResultsPage resultsPage = new ResultsPage(driver);
        ScanPage scanPage = new ScanPage(driver);
        sleep(2000);
        File folder = new File(frontFolderPath);
        File[] listOfFiles = folder.listFiles();
        Arrays.sort(listOfFiles);
        System.out.println("Folder? " + folder.isDirectory());
        if (folder.isDirectory()) {
            for (File file : listOfFiles) {
                System.out.println("File: " + file);
                if (resultsPage.getResultPageTitle()) {
                    System.out.println("Stopping Injection.");
                    break;
                }
                if (scanPage.getStageCounter().equalsIgnoreCase("2/2")) {
                    System.out.println("Flip side.");
                    break;
                }
                imageInjection(file.getAbsolutePath(), 1, true);
            }
        } else {
            System.out.println("No a folder.");
            imageInjection(frontFolderPath, 1, false);
        }
        if (backFolderPath != null) {
            folder = new File(backFolderPath);
            listOfFiles = folder.listFiles();
            for (File file : listOfFiles) {
                if (resultsPage.getResultPageTitle()) {
                    System.out.println("Stopping Injection.");
                    break;
                }
                imageInjection(file.getAbsolutePath(), 1, false);
            }
        }
    }

    @Step("Injecting audio chunk from inside a folder")
    public void audioInjectionHandler(File file) throws IOException, InterruptedException {
        ScanPage scanPage = new ScanPage(driver);
        try {
            driver.switchTo().frame(driver.findElement(By.id("modular-iframe")));
        }
        catch (Exception e){

        }
        boolean flag = false;
        do {
            if ((scanPage.getInstructionsTitle().equalsIgnoreCase("Read the code out loud")) || (scanPage.getInstructionsTitle().equalsIgnoreCase("Read the text out loud"))) {
                if (file.isDirectory()) {
                    File[] listOfFiles = file.listFiles();
                    Arrays.sort(listOfFiles);
                    int index = 0;
                    for (File file2 : listOfFiles) {
                        audioInjection(file2, 1, index == (listOfFiles.length - 1));
                        index++;
                    }
                } else audioInjection(file, 1,true);
                flag = true;
            }
        } while (!flag);

    }

    @Step("Injecting audio chunk from inside a folder")
    public void audioInjectionHandler1(File file) throws IOException, InterruptedException {
        File[] listOfFiles = file.listFiles();
        Arrays.sort(listOfFiles);
        if (file.isDirectory()) {
            int index = 0;
            for (File file2 : listOfFiles) {
                audioInjection(file2, 1, index == (listOfFiles.length - 1));
                index++;
            }
        } else {
            audioInjection(file, 1);
        }
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
                "if(" + !isFinal + "){window.combinedChunks = []; " + DEFAULT_AUDIO_OVERRIDE + ";} else " +
                "{window.allowAudioSending = false; setTimeout(() => {window.allowAudioSending = true}, 3000)}" +
                "return {blob: myBlob};}");

        System.out.println("Inject file: \n" + file.getPath());
        System.out.println("============================================\n");
        if (!isFinal) {
            Thread.sleep(3000);
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

    @Step("Inject an image")
    public void imageInjection(String filePath, int index, boolean isSession) throws
            IOException, InterruptedException {
        js.executeScript(DEFAULT_AUDIO_OVERRIDE);
        BufferedImage image = ImageIO.read(new File(filePath));
        byte[] arr = encodeToByteArray(image, "jpg");
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
        sb.append("]");

        js.executeScript("window.getOutsideInjectedImage = function() {" +
                "console.log('liad Sending index #" + index + "');" +
                "const myBlob = new Blob([new Uint8Array(JSON.parse('" + sb + "')).buffer], {type: 'image/jpg'});" +
                "window.videoTestBlob = myBlob;" +
                "console.log(`${Date.now()} - video frame injection (fn: , fs: ${myBlob.size}) blob: ${myBlob}`);" +
                "return myBlob;}");

//        System.out.println("Inject file: \n" + filePath);
//        System.out.println("============================================\n");
        if (isSession) {
            Thread.sleep(12500);
        } else Thread.sleep(8500);
        js.executeScript("window.getOutsideInjectedImage = null");
    }




    @Step("Inject two images")
    public void imageInjection1(String filePath1, String filePath2, int index1, int index2, boolean isSession1, boolean isSession2) throws
            IOException, InterruptedException {
        js.executeScript(DEFAULT_AUDIO_OVERRIDE);

        // Load the first image
        BufferedImage image1 = ImageIO.read(new File(filePath1));
        byte[] arr1 = encodeToByteArray(image1, "jpg");

        // Load the second image
        BufferedImage image2 = ImageIO.read(new File(filePath2));
        byte[] arr2 = encodeToByteArray(image2, "jpg");

        StringBuilder sb1 = new StringBuilder();
        for (int i = 0; i < arr1.length; i++) {
            if (i == 0) {
                sb1.append("[");
            }
            sb1.append(arr1[i]);
            if (i != arr1.length - 1) {
                sb1.append(",");
            }
        }
        sb1.append("]");

        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i < arr2.length; i++) {
            if (i == 0) {
                sb2.append("[");
            }
            sb2.append(arr2[i]);
            if (i != arr2.length - 1) {
                sb2.append(",");
            }
        }
        sb2.append("]");

        js.executeScript("window.getOutsideInjectedImage = function() {" +
                "console.log('liad Sending index #" + index1 + "');" +
                "const myBlob1 = new Blob([new Uint8Array(JSON.parse('" + sb1 + "')).buffer], {type: 'image/jpg'});" +
                "window.videoTestBlob1 = myBlob1;" +
                "console.log(`${Date.now()} - video frame injection (fn: , fs: ${myBlob1.size}) blob: ${myBlob1}`);" +
                "return myBlob1;}");

        js.executeScript("window.getOutsideInjectedImage = function() {" +
                "console.log('liad Sending index #" + index2 + "');" +
                "const myBlob2 = new Blob([new Uint8Array(JSON.parse('" + sb2 + "')).buffer], {type: 'image/jpg'});" +
                "window.videoTestBlob2 = myBlob2;" +
                "console.log(`${Date.now()} - video frame injection (fn: , fs: ${myBlob2.size}) blob: ${myBlob2}`);" +
                "return myBlob2;}");

        if (isSession1) {
            Thread.sleep(12500);
        } else {
            Thread.sleep(8500);
        }

        if (isSession2) {
            Thread.sleep(12500);
        } else {
            Thread.sleep(8500);
        }

        js.executeScript("window.getOutsideInjectedImage = null");
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






















}



