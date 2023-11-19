package selenium.cardCapture.pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.BasePage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Base64;
import java.util.List;

public class CardCapturePage extends BasePage {
    public CardCapturePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "body button")
    protected List<WebElement> buttonsList;

    @Step("Click on button: {chooseButton}")
    public void clickOnButton(String chooseButton) {
        waitForList(buttonsList);
        for (WebElement el : buttonsList) {
            if (getText(el).contains(chooseButton)) {
                waitForElementToBeClickable(el);
                click(el);
                break;
            }
        }
    }

    @Step("Inject card capture")
    public void injectCardCapture(String filePath, int index) throws IOException {
        sleep(5000);
        BufferedImage image = ImageIO.read(new File(filePath));
        String etay = imgToBase64String(image, "jpg");
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

        js.executeScript("console.log('liad Sending index #" + index + "');" +
//                        "const myBlob = new Blob([new Uint8Array(JSON.parse('" + sb.toString() + "')).buffer], {type: 'image/jpg'});" +
//                        "console.log('data:image/jpg;base64,'+"+ sb.toString()+")"+
//                        "window.liadBlob = myBlob;" +
//                        "window.liadSb ='" + sb.toString() + "';" +
//                        "console.log('liad blob' + myBlob);" +
                "window.cardCaptureFirstImage = 'data:image/jpg;base64,' +'" + etay + "';" +
                "console.log(window.cardCaptureFirstImage);");
        System.out.println("Inject file: \n" + filePath);
        System.out.println("============================================\n");
        sleep(30000);
//        js.executeScript("window.getOutsideInjectedImage = null");
//        WebElement el = driver.findElement(By.cssSelector(".scanovate-card-capture-index-video-container > h1"));
//        waitForTextToBeInElement(el,"Align your ID (front side) in the frame");
//        System.out.println(getText(el));
//        click(el);
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
}
