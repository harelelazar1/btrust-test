package api.ocr.cni.newVersion;

import api.Variables;
import api.ApiResponse;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utilities.MongoDB.MongoHandler;

import java.io.File;
import java.io.IOException;
import utilities.TestUtils;
public class EuropeCardSanityTest {

    Variables variables;
    MongoHandler mongoHandler;
    ApiResponse apiResponse;


    @BeforeClass
    public void resetVariables() {
        variables = new Variables();
        mongoHandler = new MongoHandler();
        apiResponse = new ApiResponse();
    }



    @DataProvider(name = "data-provider")
    public Object[][] dpMethod() {
        Object[][] params=new Object[][]{{"CNI", "./ocr/cni/France_CNI_Front.jpg", "./ocr/cni/France_CNI_Back.jpg", "france_id_cni_front", "france_id_cni_back" },
            {"CNI2", "./ocr/cni/France_CNI_Front.jpg", "./ocr/cni/France_CNI_Back.jpg", "france_id_cni_front", "france_id_cni_back" },
            {"ECNI", "./ocr/cni/France_ECNI_Front.jpg", "./ocr/cni/France_ECNI_Back.jpg", "france_id_ecni_front", "france_id_ecni_back" },
            {"France Permit New", "./ocr/cni/France_Stay_New_Front.jpg", "./ocr/cni/France_Stay_New_Back.jpg", "france_stay_permit_new_front", "france_stay_permit_new_back" },
            {"France Permit Old", "./ocr/cni/France_Stay_Old_Front.jpg", "./ocr/cni/France_Stay_Old_Back.jpg", "france_stay_permit_old_front", "france_stay_permit_old_back" },
            {"Belgium ID 2020", "./ocr/cni/belgium_id_new_2020_front.jpg", "./ocr/cni/belgium_id_new_2020_back.jpg", "belgium_id_2020_front", "belgium_id_2020_back" },
            {"Romania ID 2001", "./ocr/cni/romania_id_2001_front.jpg",  "","romania_id_2001_front", "belgium_id_2020_back" },
            {"Spain ID 2015", "./ocr/cni/spain_id_2015_front.jpg", "./ocr/cni/spain_id_2015_back.jpg", "spain_id_2015_front", "spain_id_2015_back" },
            {"Portugal ID 2007", "./ocr/cni/portugal_id_2007_front.jpg", "./ocr/cni/portugal_id_2007_back.jpg", "portugal_id_2007_front", "portugal_id_2007_back" },
            {"Italy ID 2016", "./ocr/cni/Italy_Id_2016_Front.jpg", "./ocr/cni/Italy_Id_2016_Back.jpg", "italy_id_2016_front", "italy_id_2016_back" },
            {"CNI 180 Rotate", "./ocr/cni/France_CNI_Front_180.jpg", "./ocr/cni/France_CNI_Back_180.jpg", "france_id_cni_front", "france_id_cni_back" },
            {"CNI 90 Rotate", "./ocr/cni/France_CNI_Front_90.jpg", "./ocr/cni/France_CNI_Back_90.jpg", "france_id_cni_front", "france_id_cni_back" },
            {"CNI No Face", "./ocr/cni/France_CNI_Front_No_Face.jpg", "./ocr/cni/France_CNI_Back_90.jpg", "france_id_cni_front", "france_id_cni_back" }};

  //      return new Object[][]{params[6]};
        return params;
    }

    @Test(description = "Cni api sanity test", dataProvider = "data-provider")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Api cni e2e sanity test")
    public void EuroCardTest(String testName, String frontImage, String backImage, String frontCardType, String backCardType) throws IOException, InterruptedException {
        String noCardImage="./ocr/small_jpg.jpg";
        AllureLifecycle lifecycle = Allure.getLifecycle();
        lifecycle.updateTestCase(testResult -> testResult.setName(testName));
        String configFile=TestUtils.getDefaultMathilda();
        apiResponse.clientInitResponse(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "CNI", "flowConfigName", configFile, apiResponse.CLIENT_INIT_OCR);
        apiResponse.verifyInitVariables(variables);
        String nextImage=frontImage;
        apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File(nextImage), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        nextImage=backImage;

        if (variables.getTypeUnderStage().equalsIgnoreCase("video")){
            long start = System.currentTimeMillis();
            while (!(variables.getTypeUnderStage().equalsIgnoreCase("back")) && (System.currentTimeMillis()-start)/1000<90){
                System.out.println(System.currentTimeMillis()-start/1000);
                apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File(noCardImage), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
            }
        }
        if (!(backImage.equalsIgnoreCase(""))){
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File(nextImage), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
            Assert.assertEquals(variables.getStatus(), "success");
        }
        else{
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File(noCardImage), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
            Assert.assertEquals(variables.getStatus(), "success");
        }
        if (variables.getTypeUnderStage().equalsIgnoreCase("video")){
            long start = System.currentTimeMillis();
            while (!(variables.getActionType().equalsIgnoreCase("complete")) && (System.currentTimeMillis()-start)/1000<90){
                System.out.println(System.currentTimeMillis()-start/1000);
                apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File(noCardImage), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
            }
        }
        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "success");
        Assert.assertEquals(variables.getCardType(), frontCardType);
        Assert.assertNotNull(variables.getFaceImage());
        Assert.assertNotNull(variables.getCroppedImage());

        if (!(backImage.equalsIgnoreCase(""))){
            Assert.assertEquals(variables.getCardType2(), backCardType);
            Assert.assertNotNull(variables.getCroppedImage2());
        }
    }


    @AfterMethod
    public void closingWebhookConnection(Object[] testData) {
        if (System.getProperty("mongoCheck", "false").equals("true")) {
            mongoHandler.closeMongoDBConnection();
        }
    }
}
