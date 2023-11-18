package api.ocr.singleFrame;

import api.ApiResponse;
import api.Variables;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.MongoDB.MongoHandler;
import utilities.MongoDB.Variables.CommonVariables;

import java.io.File;
import utilities.TestUtils;
public class Errorhandling {

    ApiResponse apiResponse = new ApiResponse();
    Variables variables;
    MongoHandler mongoHandler;

    @BeforeMethod
    public void startSession() {
        variables = new Variables();
        mongoHandler = new MongoHandler();
    }

    @Test
    public void t01_ilidFrontImageSentToIldlType() {
        apiResponse.singleFrameRequest("library_name", "IL-DL", "case_id", apiResponse.randomString(), "frame", new File("./ocr/bioID/nitzan/front/NitzanBioFront1.jpg"), "image/jpg", apiResponse.SINGLE_FRAME_OCR);
        apiResponse.singleFrameMainResponse(apiResponse.clientRequest, variables);

        Assert.assertEquals(variables.getStatus(), "failure");
        Assert.assertNull(variables.getCardType());
        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertEquals(CommonVariables.getServiceType(), "IL-DL");
        Assert.assertEquals(CommonVariables.getStatus(), "failure");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Some stages were not completed successfully");
    }

    @Test
    public void t02_ilidFrontImageSentToMrzType() {
        apiResponse.singleFrameRequest("library_name", "MRZ", "case_id", apiResponse.randomString(), "frame", new File("./ocr/bioID/nitzan/front/NitzanBioFront1.jpg"), "image/jpg", apiResponse.SINGLE_FRAME_OCR);
        apiResponse.singleFrameMainResponse(apiResponse.clientRequest, variables);

        Assert.assertEquals(variables.getStatus(), "failure");
        Assert.assertNull(variables.getCardType());
        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertEquals(CommonVariables.getServiceType(), "MRZ");
        Assert.assertEquals(CommonVariables.getStatus(), "failure");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Some stages were not completed successfully");
    }

    @Test
    public void t03_ilidBackImageSentToIldlType() {
        apiResponse.singleFrameRequest("library_name", "IL-DL", "case_id", apiResponse.randomString(), "frame", new File("./ocr/bioID/nitzan/back/NitzanBioBack1.jpg"), "image/jpg", apiResponse.SINGLE_FRAME_OCR);
        apiResponse.singleFrameMainResponse(apiResponse.clientRequest, variables);

        Assert.assertEquals(variables.getStatus(), "failure");
        Assert.assertNull(variables.getCardType());
        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertEquals(CommonVariables.getServiceType(), "IL-DL");
        Assert.assertEquals(CommonVariables.getStatus(), "failure");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Some stages were not completed successfully");
    }

    @Test
    public void t04_ilidBackImageSentToIldlType() {
        apiResponse.singleFrameRequest("library_name", "MRZ", "case_id", apiResponse.randomString(), "frame", new File("./ocr/bioID/nitzan/back/NitzanBioBack1.jpg"), "image/jpg", apiResponse.SINGLE_FRAME_OCR);
        apiResponse.singleFrameMainResponse(apiResponse.clientRequest, variables);

        Assert.assertEquals(variables.getStatus(), "failure");
        Assert.assertNull(variables.getCardType());
        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertEquals(CommonVariables.getServiceType(), "MRZ");
        Assert.assertEquals(CommonVariables.getStatus(), "failure");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Some stages were not completed successfully");
    }

    @Test
    public void t05_blueIdImageSentToIldlType() {
        apiResponse.singleFrameRequest("library_name", "IL-DL", "case_id", apiResponse.randomString(), "frame", new File("./ocr/blueID/liad/blueID_color1.jpg"), "image/jpg", apiResponse.SINGLE_FRAME_OCR);
        apiResponse.singleFrameMainResponse(apiResponse.clientRequest, variables);

        Assert.assertEquals(variables.getStatus(), "failure");
        Assert.assertNull(variables.getCardType());
        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertEquals(CommonVariables.getServiceType(), "IL-DL");
        Assert.assertEquals(CommonVariables.getStatus(), "failure");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Some stages were not completed successfully");
    }

    @Test
    public void t06_blueIdImageSentToMrzType() {
        apiResponse.singleFrameRequest("library_name", "MRZ", "case_id", apiResponse.randomString(), "frame", new File("./ocr/blueID/liad/blueID_color1.jpg"), "image/jpg", apiResponse.SINGLE_FRAME_OCR);
        apiResponse.singleFrameMainResponse(apiResponse.clientRequest, variables);

        Assert.assertEquals(variables.getStatus(), "failure");
        Assert.assertNull(variables.getCardType());
        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertEquals(CommonVariables.getServiceType(), "MRZ");
        Assert.assertEquals(CommonVariables.getStatus(), "failure");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Some stages were not completed successfully");
    }

    @Test
    public void t07_greenIdImageSentToIldlType() {
        apiResponse.singleFrameRequest("library_name", "IL-DL", "case_id", apiResponse.randomString(), "frame", new File("./ocr/greenID/bar/BarGreenID1.jpg"), "image/jpg", apiResponse.SINGLE_FRAME_OCR);
        apiResponse.singleFrameMainResponse(apiResponse.clientRequest, variables);

        Assert.assertEquals(variables.getStatus(), "failure");
        Assert.assertNull(variables.getCardType());
        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertEquals(CommonVariables.getServiceType(), "IL-DL");
        Assert.assertEquals(CommonVariables.getStatus(), "failure");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Some stages were not completed successfully");
    }

    @Test
    public void t08_greenIdImageSentToMrzType() {
        apiResponse.singleFrameRequest("library_name", "MRZ", "case_id", apiResponse.randomString(), "frame", new File("./ocr/greenID/bar/BarGreenID1.jpg"), "image/jpg", apiResponse.SINGLE_FRAME_OCR);
        apiResponse.singleFrameMainResponse(apiResponse.clientRequest, variables);

        Assert.assertEquals(variables.getStatus(), "failure");
        Assert.assertNull(variables.getCardType());
        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertEquals(CommonVariables.getServiceType(), "MRZ");
        Assert.assertEquals(CommonVariables.getStatus(), "failure");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Some stages were not completed successfully");
    }


    @Test
    public void t10_oldIldlFrontImageSentToIlidType() {
        apiResponse.singleFrameRequest("library_name", "IL-ID", "case_id", apiResponse.randomString(), "frame", new File("./ocr/dl/oldDLFront.jpg"), "image/jpg", apiResponse.SINGLE_FRAME_OCR);
        apiResponse.singleFrameMainResponse(apiResponse.clientRequest, variables);

        Assert.assertEquals(variables.getStatus(), "success");
        Assert.assertNull(variables.getCardType());
        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertEquals(CommonVariables.getServiceType(), "IL-ID");
        Assert.assertEquals(CommonVariables.getStatus(), "failure");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Some stages were not completed successfully");
    }

    @Test()
    public void t11_newIldlBackImageSentToIlidType() {
        apiResponse.singleFrameRequest("library_name", "IL-ID", "case_id", apiResponse.randomString(), "frame", new File("./ocr/dl/newDLBack.jpg"), "image/jpg", apiResponse.SINGLE_FRAME_OCR);
        apiResponse.singleFrameMainResponse(apiResponse.clientRequest, variables);

        Assert.assertEquals(variables.getStatus(), "failure");
        Assert.assertNull(variables.getCardType());
        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertEquals(CommonVariables.getServiceType(), "IL-ID");
        Assert.assertEquals(CommonVariables.getStatus(), "failure");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Some stages were not completed successfully");
    }


    @Test()
    public void t13_newIldlFrontImageSentToMrzType() {
        apiResponse.singleFrameRequest("library_name", "MRZ", "case_id", apiResponse.randomString(), "frame", new File("./ocr/dl/newDLFront.jpg"), "image/jpg", apiResponse.SINGLE_FRAME_OCR);
        apiResponse.singleFrameMainResponse(apiResponse.clientRequest, variables);

        Assert.assertEquals(variables.getStatus(), "failure");
        Assert.assertNull(variables.getCardType());
        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertEquals(CommonVariables.getServiceType(), "MRZ");
        Assert.assertEquals(CommonVariables.getStatus(), "failure");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Some stages were not completed successfully");
    }

    @Test()
    public void t14_oldIldlFrontImageSentToMrzType() {
        apiResponse.singleFrameRequest("library_name", "MRZ", "case_id", apiResponse.randomString(), "frame", new File("./ocr/dl/oldDLFront.jpg"), "image/jpg", apiResponse.SINGLE_FRAME_OCR);
        apiResponse.singleFrameMainResponse(apiResponse.clientRequest, variables);

        Assert.assertEquals(variables.getStatus(), "failure");
        Assert.assertNull(variables.getCardType());
        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertEquals(CommonVariables.getServiceType(), "MRZ");
        Assert.assertEquals(CommonVariables.getStatus(), "failure");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Some stages were not completed successfully");
    }

    @Test()
    public void t15_newIldlBackImageSentToMrzType() {
        apiResponse.singleFrameRequest("library_name", "MRZ", "case_id", apiResponse.randomString(), "frame", new File("./ocr/dl/newDLBack.jpg"), "image/jpg", apiResponse.SINGLE_FRAME_OCR);
        apiResponse.singleFrameMainResponse(apiResponse.clientRequest, variables);

        Assert.assertEquals(variables.getStatus(), "failure");
        Assert.assertNull(variables.getCardType());
        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertEquals(CommonVariables.getServiceType(), "MRZ");
        Assert.assertEquals(CommonVariables.getStatus(), "failure");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Some stages were not completed successfully");
    }

    @Test()
    public void t16_oldIldlBackImageSentToMrzType() {
        apiResponse.singleFrameRequest("library_name", "MRZ", "case_id", apiResponse.randomString(), "frame", new File("./ocr/dl/oldDLBack.jpg"), "image/jpg", apiResponse.SINGLE_FRAME_OCR);
        apiResponse.singleFrameMainResponse(apiResponse.clientRequest, variables);

        Assert.assertEquals(variables.getStatus(), "failure");
        Assert.assertNull(variables.getCardType());
        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertEquals(CommonVariables.getServiceType(), "MRZ");
        Assert.assertEquals(CommonVariables.getStatus(), "failure");
        Assert.assertEquals(CommonVariables.getStatusReason(), "Some stages were not completed successfully");
    }

}
