package api.ocr.mathilda.environmentVariables;

import api.ApiResponse;
import api.Variables;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.MongoDB.MongoHandler;
import utilities.MongoDB.Variables.CommonVariables;

import java.io.File;
import java.io.IOException;
import utilities.TestUtils;
public class videoDisabled {

    ApiResponse apiResponse;
    Variables variables;
    MongoHandler mongoHandler;

    @BeforeMethod
    public void startSession() {
        variables = new Variables();
        apiResponse = new ApiResponse();
        mongoHandler = new MongoHandler();
    }

    @Test(description = "il id library without video")
    @Description("IL-ID library without video support")
    public void t01_iLiDlibraryWithoutVideo() throws IOException {
        apiResponse.clientInitResponse(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "IL-ID", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR);
        apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/bioID/nitzan/front/NitzanBioFront1.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);

        Assert.assertEquals(variables.getActionType(), "message");
        Assert.assertEquals(variables.getMessageId(), 103);
        Assert.assertEquals(variables.getMessageText(), "Card detected");
        Assert.assertTrue(variables.isSuccess());

        while (!variables.getActionType().equalsIgnoreCase("stage")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/bioID/nitzan/front/NitzanBioFront1.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        }

        Assert.assertEquals(variables.getActionType(), "stage");
        Assert.assertEquals(variables.getOrder(), 2);
        Assert.assertEquals(variables.getTypeUnderStage(), "back");
        Assert.assertEquals(variables.getStatus(), "success");
        Assert.assertTrue(variables.isSuccess());

        while (variables.getMessageId() != 100) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/bioID/nitzan/front/NitzanBioFront1.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        }

        while (variables.getMessageId() != 103) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/bioID/nitzan/back/NitzanBioBack1.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        }

        Assert.assertEquals(variables.getActionType(), "message");
        Assert.assertEquals(variables.getMessageId(), 103);
        Assert.assertEquals(variables.getMessageText(), "Card detected");
        Assert.assertTrue(variables.isSuccess());

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/bioID/nitzan/back/NitzanBioBack1.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        }

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "failure");

        //Front side data
        Assert.assertEquals(variables.getActionType2(), "stage");
        Assert.assertEquals(variables.getOrder2(), 1);
        Assert.assertEquals(variables.getType2(), "front");
        Assert.assertEquals(variables.getLastNameHebrew(), "שוקר");
        Assert.assertEquals(variables.getFirstNameHebrew(), "ניצן");
        Assert.assertEquals(variables.getDateOfBirth(), "21.08.1988");
        Assert.assertEquals(variables.getDateOfIssue(), "23.5.2019");
        Assert.assertEquals(variables.getDateOfExpiry(), "20.05.2029");
        Assert.assertEquals(variables.getIdNumber(), 200761625);
        Assert.assertEquals(variables.getCardType2(), "bio_front");
        Assert.assertNotNull(variables.getFaceImage2());
        Assert.assertNotNull(variables.getInputImage2());
        Assert.assertNotNull(variables.getCroppedImage2());
        Assert.assertTrue(variables.isImageIsColorful());
        Assert.assertTrue(variables.isIdChecksumValid());
        Assert.assertTrue(variables.isTemplateMatched());
        Assert.assertTrue(variables.isPeriodBetweenIssueAndExpiry());
        Assert.assertTrue(variables.isChipAuth());
        Assert.assertFalse(variables.isFaceImageReplacedWithPassportImage());
        Assert.assertTrue(variables.isFaceSizeValid());
        Assert.assertTrue(variables.isFacePositionValid());
        Assert.assertTrue(variables.isFaceRotationValid());
        Assert.assertEquals(variables.getStageStatus3(), "success");

        //Back side data
        Assert.assertEquals(variables.getActionType1(), "complete");
        Assert.assertEquals(variables.getOrder1(), 2);
        Assert.assertEquals(variables.getType1(), "back");
        Assert.assertEquals(variables.getIdNumber(), 200761625);
        Assert.assertEquals(variables.getPlaceOfBirth(), "ישראל");
        Assert.assertEquals(variables.getGender(), "זכר");
        Assert.assertEquals(variables.getCardType1(), "bio_back");
        Assert.assertNotNull(variables.getInputImage1());
        Assert.assertNotNull(variables.getCroppedImage1());
        Assert.assertTrue(variables.isImageIsColorful());
        Assert.assertTrue(variables.isIdChecksumValid());
        Assert.assertTrue(variables.isTemplateMatched());
        Assert.assertTrue(variables.isIdNumberMatchesFront());
        Assert.assertEquals(variables.getStageStatus2(), "success");

        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), TestUtils.getDefaultMathilda());
        Assert.assertTrue(variables.isSuccess());

        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertNull(CommonVariables.getVideo());
    }

    @Test(description = "il dl library without video")
    @Description("IL-DL library without video support")
    public void t02_iLdLlibraryWithoutVideo() throws IOException {
        apiResponse.clientInitResponse(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "IL-DL", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR);
        apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/dl/oldDLFront.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);

        while (!variables.getActionType().equalsIgnoreCase("message") && variables.getMessageId() == 103) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/dl/oldDLFront.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        }

        Assert.assertEquals(variables.getActionType(), "message");
        Assert.assertEquals(variables.getMessageId(), 103);
        Assert.assertEquals(variables.getMessageText(), "Card detected");
        Assert.assertTrue(variables.isSuccess());

        while (!variables.getActionType().equalsIgnoreCase("stage")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/dl/oldDLFront.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        }

        Assert.assertEquals(variables.getActionType(), "stage");
        Assert.assertEquals(variables.getOrder(), 2);
        Assert.assertEquals(variables.getTypeUnderStage(), "back");
        Assert.assertEquals(variables.getStatus(), "success");
        Assert.assertTrue(variables.isSuccess());

        while (variables.getMessageId() != 100) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/dl/oldDLFront.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        }

        Assert.assertEquals(variables.getActionType(), "message");
        Assert.assertEquals(variables.getMessageId(), 100);
        Assert.assertEquals(variables.getMessageText(), "Wrong side");
        Assert.assertTrue(variables.isSuccess());

        while (variables.getMessageId() != 103) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/dl/oldDLBack.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        }

        Assert.assertEquals(variables.getActionType(), "message");
        Assert.assertEquals(variables.getMessageId(), 103);
        Assert.assertEquals(variables.getMessageText(), "Card detected");
        Assert.assertTrue(variables.isSuccess());

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/dl/oldDLBack.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        }

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "failure");

        //Front side data
        Assert.assertEquals(variables.getActionType2(), "stage");
        Assert.assertEquals(variables.getOrder2(), 1);
        Assert.assertEquals(variables.getType2(), "front");
        Assert.assertEquals(variables.getLastNameHebrew(), "גרוס פישמן");
        Assert.assertEquals(variables.getLastNameEnglish(), "FISHMAMGROSS");
        Assert.assertEquals(variables.getFirstNameHebrew(), "מאיה");
        Assert.assertEquals(variables.getFirstNameEnglish(), "MAYA");
        Assert.assertEquals(variables.getDateOfBirth(), "06.01.1978");
        Assert.assertEquals(variables.getDateOfIssue(), "14.12.2006");
        Assert.assertEquals(variables.getDateOfExpiry(), "06.01.2017");
        Assert.assertEquals(variables.getIdNumber(), 34471045);
        Assert.assertEquals(variables.getLicenseNumber(), 6905739);
        Assert.assertEquals(variables.getAddress(), "אלוף דוד 158 רמת גן");
        Assert.assertEquals(variables.getLicenseCategories(), "B");
        Assert.assertEquals(variables.getCardType2(), "old_front");
        Assert.assertNotNull(variables.getFaceImage2());
        Assert.assertNotNull(variables.getInputImage2());
        Assert.assertNotNull(variables.getCroppedImage2());
        Assert.assertTrue(variables.isFaceSizeValid());
        Assert.assertTrue(variables.isFacePositionValid());
        Assert.assertTrue(variables.isFaceRotationValid());
        Assert.assertEquals(variables.getStageStatus3(), "success");

        //Back side data
        Assert.assertEquals(variables.getActionType1(), "complete");
        Assert.assertEquals(variables.getOrder1(), 2);
        Assert.assertEquals(variables.getType1(), "back");
        Assert.assertEquals(variables.getIdNumber(), 34471045);
        Assert.assertEquals(variables.getbYear(), 1995);
        Assert.assertEquals(variables.getCardType1(), "old_back");
        Assert.assertNotNull(variables.getInputImage1());
        Assert.assertNotNull(variables.getCroppedImage1());
        Assert.assertEquals(variables.getStageStatus2(), "success");

        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), TestUtils.getDefaultMathilda());
        Assert.assertTrue(variables.isSuccess());

        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertNull(CommonVariables.getVideo());
    }

    @Test(description = "mrz library without video")
    @Description("MRZ library without video support")
    public void t03_mrzLibraryWithoutVideo() throws IOException {
        apiResponse.clientInitResponse(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "MRZ", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR);
        apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/mrz/guy/guyMrz.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "failure");

        Assert.assertEquals(variables.getActionType1(), "complete");
        Assert.assertEquals(variables.getOrder(), 1);
        Assert.assertEquals(variables.getTypeUnderStage(), "front");
        Assert.assertEquals(variables.getMrzText(), "P<ISRSTIEBEL<<GUY<<<<<<<<<<<<<<<<<<<<<<<<<<<23369458<8ISR8609199M26120410<2649440<1<<<80");
        Assert.assertEquals(variables.getMrzType(), "TD3");
        Assert.assertEquals(variables.getDocumentType(), "passport");
        Assert.assertEquals(variables.getDocumentSubType(), "");
        Assert.assertEquals(variables.getIssuingCountryCode(), "ISR");
        Assert.assertEquals(variables.getLastNameEnglish(), "STIEBEL");
        Assert.assertEquals(variables.getFirstNameEnglish(), "GUY");
        Assert.assertEquals(variables.getPassportNumber(), 23369458);
        Assert.assertEquals(variables.getNationalityCode(), "ISR");
        Assert.assertEquals(variables.getDateOfBirth(), "19/09/86");
        Assert.assertEquals(variables.getGender(), "Male");
        Assert.assertEquals(variables.getDateOfExpiry(), "04/12/26");
        Assert.assertEquals(variables.getPersonalNumber(), "0-2649440-1");
        Assert.assertFalse(variables.isMightBeTruncated());
        Assert.assertNotNull(variables.getFaceImage());
        Assert.assertNotNull(variables.getInputImage());
        Assert.assertNotNull(variables.getCroppedImage());
        Assert.assertNotNull(variables.getCroppedFieldImage());
        Assert.assertEquals(variables.getStageStatus(), "success");

        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), TestUtils.getDefaultMathilda());
        Assert.assertTrue(variables.isSuccess());

        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertNull(CommonVariables.getVideo());
    }

    //    @Test(description = "phc library without video")
    @Description("PHC library without video support")
    public void t04_phcLibraryWithoutVideo() throws IOException {
        apiResponse.clientInitResponse(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "PHL-CHEQUES", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR);
        apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/cheque/PhilippinesCheque/BDOCheque.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);

        Assert.assertEquals(variables.getActionType(), "message");
        Assert.assertEquals(variables.getMessageId(), 97);
        Assert.assertEquals(variables.getMessageText(), "Cheque not detected");
        Assert.assertTrue(variables.isSuccess());

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/cheque/PhilippinesCheque/BDOCheque.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        }

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "failure");

        Assert.assertEquals(variables.getActionType1(), "complete");
        Assert.assertEquals(variables.getOrder(), 1);
        Assert.assertEquals(variables.getTypeUnderStage(), "front");
        Assert.assertEquals(variables.getChequeNumber(), "c0000184451c01053d4524a008018016049c000");
        Assert.assertNotNull(variables.getInputImage());
        Assert.assertEquals(variables.getStageStatus(), "success");

        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), TestUtils.getDefaultMathilda());
        Assert.assertTrue(variables.isSuccess());

        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertNull(CommonVariables.getVideo());
    }

    @Test(description = "cni library without video")
    @Description("CNI library without video support")
    public void t05_cniLibraryWithoutVideo() throws InterruptedException, IOException {
        int iterationCounter = 1;
        apiResponse.clientInitResponse(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "CNI", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR);
        apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/cni/good_fr_3.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);

        switch (variables.getActionType()) {
            case "stage": {
                Assert.assertEquals(variables.getActionType(), "stage");
                Assert.assertEquals(variables.getStatus(), "success");
                Assert.assertTrue(variables.isSuccess());
                switch (variables.getOrder()) {
                    case 2: {
                        Assert.assertEquals(variables.getOrder(), 2);
                        Assert.assertEquals(variables.getTypeUnderStage(), "video");
                        break;
                    }
                    case 3: {
                        Assert.assertEquals(variables.getOrder(), 3);
                        Assert.assertEquals(variables.getTypeUnderStage(), "back");
                        break;
                    }
                    case 4: {
                        Assert.assertEquals(variables.getOrder(), 4);
                        Assert.assertEquals(variables.getTypeUnderStage(), "video");
                        break;
                    }
                    case 5: {
                        Assert.assertEquals(variables.getOrder(), 5);
                        Assert.assertEquals(variables.getTypeUnderStage(), "otp");
                        Assert.assertTrue(variables.getOtpNum()>0);
                        break;
                    }
                }
                Assert.assertEquals(variables.getActionType(), "stage");
                Assert.assertEquals(variables.getOrder(), 2);
                Assert.assertEquals(variables.getTypeUnderStage(), "video");
                break;
            }
            case "message": {
                Assert.assertEquals(variables.getActionType(), "message");
                Assert.assertTrue(variables.isSuccess());
                switch (variables.getMessageId()) {
                    case 100: {
                        Assert.assertEquals(variables.getMessageId(), 100);
                        Assert.assertEquals(variables.getMessageText(), "Wrong side");
                        break;
                    }
                    case 103: {
                        Assert.assertEquals(variables.getMessageId(), 103);
                        Assert.assertEquals(variables.getMessageText(), "Card detected");
                        break;
                    }
                }
                break;
            }
        }

        apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/cni/good_fr_3.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);

        switch (variables.getActionType()) {
            case "stage": {
                Assert.assertEquals(variables.getActionType(), "stage");
                Assert.assertEquals(variables.getStatus(), "success");
                Assert.assertTrue(variables.isSuccess());
                switch (variables.getOrder()) {
                    case 2: {
                        Assert.assertEquals(variables.getOrder(), 2);
                        Assert.assertEquals(variables.getTypeUnderStage(), "video");
                        break;
                    }
                    case 3: {
                        Assert.assertEquals(variables.getOrder(), 3);
                        Assert.assertEquals(variables.getTypeUnderStage(), "back");
                        break;
                    }
                    case 4: {
                        Assert.assertEquals(variables.getOrder(), 4);
                        Assert.assertEquals(variables.getTypeUnderStage(), "video");
                        break;
                    }
                    case 5: {
                        Assert.assertEquals(variables.getOrder(), 5);
                        Assert.assertEquals(variables.getTypeUnderStage(), "otp");
                        Assert.assertTrue(variables.getOtpNum()>0);
                        break;
                    }
                }
                Assert.assertEquals(variables.getActionType(), "stage");
                Assert.assertEquals(variables.getOrder(), 2);
                Assert.assertEquals(variables.getTypeUnderStage(), "video");
                break;
            }
            case "message": {
                Assert.assertEquals(variables.getActionType(), "message");
                Assert.assertTrue(variables.isSuccess());
                switch (variables.getMessageId()) {
                    case 100: {
                        Assert.assertEquals(variables.getMessageId(), 100);
                        Assert.assertEquals(variables.getMessageText(), "Wrong side");
                        break;
                    }
                    case 103: {
                        Assert.assertEquals(variables.getMessageId(), 103);
                        Assert.assertEquals(variables.getMessageText(), "Card detected");
                        break;
                    }
                }
                break;
            }
        }

        while (!variables.getActionType().equalsIgnoreCase("stage")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/cni/good_fr_3.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        }

        switch (variables.getActionType()) {
            case "stage": {
                Assert.assertEquals(variables.getActionType(), "stage");
                Assert.assertEquals(variables.getStatus(), "success");
                Assert.assertTrue(variables.isSuccess());
                switch (variables.getOrder()) {
                    case 2: {
                        Assert.assertEquals(variables.getOrder(), 2);
                        Assert.assertEquals(variables.getTypeUnderStage(), "video");
                        break;
                    }
                    case 3: {
                        Assert.assertEquals(variables.getOrder(), 3);
                        Assert.assertEquals(variables.getTypeUnderStage(), "back");
                        break;
                    }
                    case 4: {
                        Assert.assertEquals(variables.getOrder(), 4);
                        Assert.assertEquals(variables.getTypeUnderStage(), "video");
                        break;
                    }
                    case 5: {
                        Assert.assertEquals(variables.getOrder(), 5);
                        Assert.assertEquals(variables.getTypeUnderStage(), "otp");
                        Assert.assertTrue(variables.getOtpNum()>0);
                        break;
                    }
                }
                break;
            }
            case "message": {
                Assert.assertEquals(variables.getActionType(), "message");
                Assert.assertTrue(variables.isSuccess());
                switch (variables.getMessageId()) {
                    case 100: {
                        Assert.assertEquals(variables.getMessageId(), 100);
                        Assert.assertEquals(variables.getMessageText(), "Wrong side");
                        break;
                    }
                    case 103: {
                        Assert.assertEquals(variables.getMessageId(), 103);
                        Assert.assertEquals(variables.getMessageText(), "Card detected");
                        break;
                    }
                }
                break;
            }
        }

        while (variables.getMessageId() != 100) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/cni/good_fr_3.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        }

        switch (variables.getActionType()) {
            case "stage": {
                Assert.assertEquals(variables.getActionType(), "stage");
                Assert.assertEquals(variables.getStatus(), "success");
                Assert.assertTrue(variables.isSuccess());
                switch (variables.getOrder()) {
                    case 2: {
                        Assert.assertEquals(variables.getOrder(), 2);
                        Assert.assertEquals(variables.getTypeUnderStage(), "video");
                        break;
                    }
                    case 3: {
                        Assert.assertEquals(variables.getOrder(), 3);
                        Assert.assertEquals(variables.getTypeUnderStage(), "back");
                        break;
                    }
                    case 4: {
                        Assert.assertEquals(variables.getOrder(), 4);
                        Assert.assertEquals(variables.getTypeUnderStage(), "video");
                        break;
                    }
                    case 5: {
                        Assert.assertEquals(variables.getOrder(), 5);
                        Assert.assertEquals(variables.getTypeUnderStage(), "otp");
                        Assert.assertTrue(variables.getOtpNum()>0);
                        break;
                    }
                }
                break;
            }
            case "message": {
                Assert.assertEquals(variables.getActionType(), "message");
                Assert.assertTrue(variables.isSuccess());
                switch (variables.getMessageId()) {
                    case 100: {
                        Assert.assertEquals(variables.getMessageId(), 100);
                        Assert.assertEquals(variables.getMessageText(), "Wrong side");
                        break;
                    }
                    case 103: {
                        Assert.assertEquals(variables.getMessageId(), 103);
                        Assert.assertEquals(variables.getMessageText(), "Card detected");
                        break;
                    }
                }
                break;
            }
        }

        while (!variables.getActionType().equalsIgnoreCase("stage")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/cni/good_fr_back_1.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        }

        switch (variables.getActionType()) {
            case "stage": {
                Assert.assertEquals(variables.getActionType(), "stage");
                Assert.assertEquals(variables.getStatus(), "success");
                Assert.assertTrue(variables.isSuccess());
                switch (variables.getOrder()) {
                    case 2: {
                        Assert.assertEquals(variables.getOrder(), 2);
                        Assert.assertEquals(variables.getTypeUnderStage(), "video");
                        break;
                    }
                    case 3: {
                        Assert.assertEquals(variables.getOrder(), 3);
                        Assert.assertEquals(variables.getTypeUnderStage(), "back");
                        break;
                    }
                    case 4: {
                        Assert.assertEquals(variables.getOrder(), 4);
                        Assert.assertEquals(variables.getTypeUnderStage(), "video");
                        break;
                    }
                    case 5: {
                        Assert.assertEquals(variables.getOrder(), 5);
                        Assert.assertEquals(variables.getTypeUnderStage(), "otp");
                        Assert.assertTrue(variables.getOtpNum()>0);
                        break;
                    }
                }
                break;
            }
            case "message": {
                Assert.assertEquals(variables.getActionType(), "message");
                Assert.assertTrue(variables.isSuccess());
                switch (variables.getMessageId()) {
                    case 100: {
                        Assert.assertEquals(variables.getMessageId(), 100);
                        Assert.assertEquals(variables.getMessageText(), "Wrong side");
                        break;
                    }
                    case 103: {
                        Assert.assertEquals(variables.getMessageId(), 103);
                        Assert.assertEquals(variables.getMessageText(), "Card detected");
                        break;
                    }
                }
                break;
            }
        }

        while (variables.getMessageId() != 103) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/cni/good_fr_back_1.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        }

        switch (variables.getActionType()) {
            case "stage": {
                Assert.assertEquals(variables.getActionType(), "stage");
                Assert.assertEquals(variables.getStatus(), "success");
                Assert.assertTrue(variables.isSuccess());
                switch (variables.getOrder()) {
                    case 2: {
                        Assert.assertEquals(variables.getOrder(), 2);
                        Assert.assertEquals(variables.getTypeUnderStage(), "video");
                        break;
                    }
                    case 3: {
                        Assert.assertEquals(variables.getOrder(), 3);
                        Assert.assertEquals(variables.getTypeUnderStage(), "back");
                        break;
                    }
                    case 4: {
                        Assert.assertEquals(variables.getOrder(), 4);
                        Assert.assertEquals(variables.getTypeUnderStage(), "video");
                        break;
                    }
                    case 5: {
                        Assert.assertEquals(variables.getOrder(), 5);
                        Assert.assertEquals(variables.getTypeUnderStage(), "otp");
                        Assert.assertTrue(variables.getOtpNum()>0);
                        break;
                    }
                }
                break;
            }
            case "message": {
                Assert.assertEquals(variables.getActionType(), "message");
                Assert.assertTrue(variables.isSuccess());
                switch (variables.getMessageId()) {
                    case 100: {
                        Assert.assertEquals(variables.getMessageId(), 100);
                        Assert.assertEquals(variables.getMessageText(), "Wrong side");
                        break;
                    }
                    case 103: {
                        Assert.assertEquals(variables.getMessageId(), 103);
                        Assert.assertEquals(variables.getMessageText(), "Card detected");
                        break;
                    }
                }
                break;
            }
        }

        while (!variables.getActionType().equalsIgnoreCase("stage")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/cni/good_fr_back_1.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        }

        switch (variables.getActionType()) {
            case "stage": {
                Assert.assertEquals(variables.getActionType(), "stage");
                Assert.assertEquals(variables.getStatus(), "success");
                Assert.assertTrue(variables.isSuccess());
                switch (variables.getOrder()) {
                    case 2: {
                        Assert.assertEquals(variables.getOrder(), 2);
                        Assert.assertEquals(variables.getTypeUnderStage(), "video");
                        break;
                    }
                    case 3: {
                        Assert.assertEquals(variables.getOrder(), 3);
                        Assert.assertEquals(variables.getTypeUnderStage(), "back");
                        break;
                    }
                    case 4: {
                        Assert.assertEquals(variables.getOrder(), 4);
                        Assert.assertEquals(variables.getTypeUnderStage(), "video");
                        break;
                    }
                    case 5: {
                        Assert.assertEquals(variables.getOrder(), 5);
                        Assert.assertEquals(variables.getTypeUnderStage(), "otp");
                        Assert.assertTrue(variables.getOtpNum()>0);
                        break;
                    }
                }
                break;
            }
            case "message": {
                Assert.assertEquals(variables.getActionType(), "message");
                Assert.assertTrue(variables.isSuccess());
                switch (variables.getMessageId()) {
                    case 100: {
                        Assert.assertEquals(variables.getMessageId(), 100);
                        Assert.assertEquals(variables.getMessageText(), "Wrong side");
                        break;
                    }
                    case 103: {
                        Assert.assertEquals(variables.getMessageId(), 103);
                        Assert.assertEquals(variables.getMessageText(), "Card detected");
                        break;
                    }
                }
                break;
            }
        }

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            if (iterationCounter == 1) {
                apiResponse.sendOtpAudioFile(variables.getToken(), new File("./liveness/audio/stt/fullStt/beneficiary_chunk.wavaa"), apiResponse.CLIENT_REQUEST_OCR);
            }
            apiResponse.sendOtpAudioFile(variables.getToken(), new File("./liveness/audio/stt/fullStt/beneficiary_chunk.wavab"), apiResponse.CLIENT_REQUEST_OCR);
            apiResponse.sendOtpAudioFile(variables.getToken(), new File("./liveness/audio/stt/fullStt/beneficiary_chunk.wavac"), apiResponse.CLIENT_REQUEST_OCR);
            apiResponse.sendOtpAudioFile(variables.getToken(), new File("./liveness/audio/stt/fullStt/beneficiary_chunk.wavad"), apiResponse.CLIENT_REQUEST_OCR);
            apiResponse.sendOtpAudioFile(variables.getToken(), new File("./liveness/audio/stt/fullStt/beneficiary_chunk.wavae"), apiResponse.CLIENT_REQUEST_OCR);
            apiResponse.sendOtpAudioFile(variables.getToken(), new File("./liveness/audio/stt/fullStt/beneficiary_chunk.wavaf"), apiResponse.CLIENT_REQUEST_OCR);
            apiResponse.clientResponseLivenessOtpStage(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "json_obj", "{\"stageName\":\"otp\",\"stageOrdinal\":5}", "message_type", "report_stage_ending", apiResponse.CLIENT_REQUEST_OCR);
            Thread.sleep(1500);
            apiResponse.sendOtpAudioFile(variables.getToken(), new File("./liveness/audio/stt/fullStt/beneficiary_chunk.wavad"), apiResponse.CLIENT_REQUEST_OCR);
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barCenter.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
            if (apiResponse.clientRequest.getBody().asString().contains("\"action_type\":\"complete\"")) {
                apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./liveness/apiPic/barCenter.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
            }
            iterationCounter++;
        }

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "failure");

        //stage 0 (front)
        Assert.assertEquals(variables.getActionType1(), "stage");
        Assert.assertEquals(variables.getOrder1(), 1);
        Assert.assertEquals(variables.getType1(), "front");
        Assert.assertEquals(variables.getCardType(), "cni_front");
        Assert.assertNotNull(variables.getFaceImage());
        Assert.assertNotNull(variables.getInputImage());
        Assert.assertNotNull(variables.getCroppedImage());
        Assert.assertEquals(variables.getStageStatus(), "success");
        //stage 1 (video)
        Assert.assertEquals(variables.getActionType2(), "stage");
        Assert.assertEquals(variables.getOrder2(), 2);
        Assert.assertEquals(variables.getType2(), "video");
        Assert.assertEquals(variables.getStageStatus2(), "success");
        //stage 2 (back)
        Assert.assertEquals(variables.getActionType3(), "stage");
        Assert.assertEquals(variables.getOrder3(), 3);
        Assert.assertEquals(variables.getType3(), "back");
        Assert.assertEquals(variables.getCardType2(), "cni_back");
        Assert.assertNotNull(variables.getInputImage2());
        Assert.assertNotNull(variables.getCroppedImage2());
        Assert.assertEquals(variables.getStageStatus3(), "success");
        //stage 3 (video)
        Assert.assertEquals(variables.getActionType4(), "stage");
        Assert.assertEquals(variables.getOrder4(), 4);
        Assert.assertEquals(variables.getType4(), "video");
        Assert.assertEquals(variables.getStageStatus4(), "success");
        //stage 4 (otp)
        Assert.assertEquals(variables.getActionType5(), "complete");
        Assert.assertEquals(variables.getOrder5(), 5);
        Assert.assertEquals(variables.getType5(), "otp");
        Assert.assertTrue(variables.getOtpNum()>0);
        Assert.assertEquals(variables.getStageStatus4(), "success");

        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), TestUtils.getDefaultMathilda());
        Assert.assertTrue(variables.isSuccess());

        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertNull(CommonVariables.getVideo());
    }

    //   @Test(description = "dk dl library without video")
    @Description("DK-DL library without video support")
    public void t06_dKdLLibraryWithoutVideo() throws IOException {
        apiResponse.clientInitResponse(variables, "application/json", "caseId", apiResponse.randomString(), "libraryName", "DK-DL", "flowConfigName", TestUtils.getDefaultMathilda(), apiResponse.CLIENT_INIT_OCR);
        apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/denmarkDrivingLicense/newDKDL/314764922.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);

        while (!variables.getActionType().equalsIgnoreCase("complete")) {
            apiResponse.mainClientResponse(variables, "multipart/form-data", "Authorization", "Bearer " + variables.getToken(), "frame", new File("./ocr/denmarkDrivingLicense/newDKDL/314764922.jpg"), "image/jpg", "message_type", "frame_request", apiResponse.CLIENT_REQUEST_OCR);
        }

        Assert.assertEquals(variables.getActionType(), "complete");
        Assert.assertEquals(variables.getStatus(), "failure");

        Assert.assertEquals(variables.getActionType1(), "complete");
        Assert.assertEquals(variables.getOrder(), 1);
        Assert.assertEquals(variables.getTypeUnderStage(), "front");

        Assert.assertEquals(variables.getLastNameEnglish(), "Petersen");
        Assert.assertEquals(variables.getFirstNameEnglish(), "Fredrik Lang");
        Assert.assertEquals(variables.getDateOfBirth(), "16.10.1998");
        Assert.assertEquals(variables.getDateOfIssue(), "09.12.2016");
        Assert.assertEquals(variables.getDateOfExpiry(), "09.12.2031");
        Assert.assertEquals(variables.getPersonalNumber(), "161098-2103");
        Assert.assertEquals(variables.getLicenseNumber(), 31476492);
        Assert.assertEquals(variables.getCardType(), "new_front");
        Assert.assertNotNull(variables.getFaceImage());
        Assert.assertNotNull(variables.getInputImage());
        Assert.assertNotNull(variables.getCroppedImage());
        Assert.assertEquals(variables.getStageStatus(), "success");

        Assert.assertNotNull(variables.getCaseId());
        Assert.assertEquals(variables.getConfigFileName(), TestUtils.getDefaultMathilda());
        Assert.assertTrue(variables.isSuccess());

        // Mongo Testing ****************************************************************
        if (System.getProperty("mongoCheck", "false").equals("false")) {
            return;
        }
        mongoHandler.queryHandler(variables.getCaseId(), variables.getStatus());
        Assert.assertNull(CommonVariables.getVideo());
    }
}
