package utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.jayway.jsonpath.JsonPath;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import io.qameta.allure.Step;
import net.minidev.json.JSONArray;
import org.testng.Assert;
import selenium.liveness.pageObject.LivenessFieldsObjescts;
import selenium.ocr.pageObject.oldService.OcrFieldsObjects;

import static com.jayway.jsonpath.JsonPath.parse;
import static com.mongodb.client.model.Filters.gte;

public class MongoDBConnection {

    protected final String DECODE_PASSWORD = "2L2W_wVr9YLy4js%40L%5E%26Vk%5E%3DTc_d%3D2y%21x";
    protected final String USER_NAME = "scanovate_FimpiZv1dcyG4VOY";
    protected final String PORT = "10.0.1.75:27017";
    protected final String AUTH = "/?authSource=admin&authMechanism=SCRAM-SHA-1";
    protected MongoCollection collection;
    protected FindIterable find;
    protected MongoCursor cursor;
    protected String json = null;

    @Step("Open mongo connection")
    public void mongoDBInitiateCollection(String collectionName) {
        MongoClientURI uri = new MongoClientURI("mongodb://" + USER_NAME + ":" + DECODE_PASSWORD + "@" + PORT + AUTH);
        MongoClient mongoClient = new com.mongodb.MongoClient(uri);
        MongoDatabase db = mongoClient.getDatabase("webhook");
        collection = db.getCollection(collectionName);
        System.out.println("Successfully Connected" + " to the database");
    }

    @Step("Close mongo connection")
    public void closeMongoDBConnection() {
        MongoClientURI uri = new MongoClientURI("mongodb://" + USER_NAME + ":" + DECODE_PASSWORD + "@" + PORT + AUTH);
        MongoClient mongoClient = new com.mongodb.MongoClient(uri);
        mongoClient.close();
        System.out.println("MongoDB connection closed");
    }

    @Step("display docs from mongo collection - ID")
    public OcrFieldsObjects displayDocumentsID(OcrFieldsObjects ocrFieldsObjects, String collectionName, String caseId, String status) {

        try {
            mongoDBInitiateCollection(collectionName);
            Thread.sleep(3000);
            System.out.println("Successfully Connected" + " to the database");
            System.out.println("Displaying the list" + " of Documents");

            BasicDBObject searchQuery = new BasicDBObject();
            searchQuery.put("caseId", caseId);
            searchQuery.put("status", status);
            find = collection.find(searchQuery);
            cursor = find.iterator();

            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            while (cursor.hasNext()) {
                json = ow.writeValueAsString(cursor.next());
//                System.out.println(json);
                System.out.println("json doc exists !!! (:");
            }

            //session_start
            if (json.contains("\"_id\" :")) {
                ocrFieldsObjects.setObjectId(JsonPath.parse(json).read("$._id"));
            }
            if (json.contains("\"timestamp\" :")) {
                ocrFieldsObjects.setTimestamp(JsonPath.parse(json).read("$.timestamp"));
            }
            if (json.contains("\"caseId\" :")) {
                ocrFieldsObjects.setCaseId(JsonPath.parse(json).read("$.caseId"));
            }
            if (json.contains("\"ocrType\" :")) {
                ocrFieldsObjects.setOcrType(JsonPath.parse(json).read("$.ocrType"));
            }
            if (json.contains("\"service\" :")) {
                ocrFieldsObjects.setServiceType(JsonPath.parse(json).read("$.service"));
            }
            if (json.contains("\"status\" :")) {
                ocrFieldsObjects.setStatus(JsonPath.parse(json).read("$.status"));
            }
            if (json.contains("\"token\" :")) {
                ocrFieldsObjects.setToken(JsonPath.parse(json).read("$.token"));
            }
            if (json.contains("\"workingMode\" :")) {
                ocrFieldsObjects.setWorkingMode(JsonPath.parse(json).read("$.workingMode"));
            }

            //session_start + success
            if (json.contains("\"cardImage\" :")) {
                ocrFieldsObjects.setCardImage(JsonPath.parse(json).read("$.cardImage"));
            }
            if (json.contains("\"faceImage\" :")) {
                ocrFieldsObjects.setFaceImage(JsonPath.parse(json).read("$.faceImage"));
            }
            if (json.contains("\"inputImage\" :")) {
                ocrFieldsObjects.setInputImage(JsonPath.parse(json).read("$.inputImage"));
            }
            if (json.contains("\"video\" :")) {
                ocrFieldsObjects.setVideo(JsonPath.parse(json).read("$.video"));
            }

            // in data
            if (json.contains("\"id_type\" :")) {
                ocrFieldsObjects.setId_type(JsonPath.parse(json).read("$.data.id_type"));
            }
            if (json.contains("\"face_quality_status\" :")) {
                ocrFieldsObjects.setFace_quality_status(parse(json).read("$.data.face_quality_status"));
            }
            if (json.contains("lastProcessedImage")) {
                ocrFieldsObjects.setLastProcessedImage(parse(json).read("$.lastProcessedImage"));
            }

            if (json.contains("\"errorMessage\"")) {
                ocrFieldsObjects.setErrorMessage(parse(json).read("$.errorMessage"));
            }
            if (json.contains("\"errorCode\"")) {
                ocrFieldsObjects.setErrorCode(parse(json).read("$.errorCode"));
            }


            if (json.contains("\"authentication\" :")) {
                // data.authentication
                if (ocrFieldsObjects.getId_type().equalsIgnoreCase("ISRAEL_ID2_TYPE_GREEN")) {

                    ocrFieldsObjects.setCOLORFUL_IMAGE_VALID_NAME(JsonPath.parse(json).read("$.data.authentication[0].name"));
                    ocrFieldsObjects.setCOLORFUL_IMAGE_VALID_VALUE(JsonPath.parse(json).read("$.data.authentication[0].value"));
                    ocrFieldsObjects.setFACE_POSITION_VALID_NAME(JsonPath.parse(json).read("$.data.authentication[1].name"));
                    ocrFieldsObjects.setFACE_POSITION_VALID_VALUE(JsonPath.parse(json).read("$.data.authentication[1].value"));
                    ocrFieldsObjects.setFACE_ROTATION_VALID_NAME(JsonPath.parse(json).read("$.data.authentication[2].name"));
                    ocrFieldsObjects.setFACE_ROTATION_VALID_VALUE(JsonPath.parse(json).read("$.data.authentication[2].value"));
                    ocrFieldsObjects.setFACE_SIZE_VALID_NAME(JsonPath.parse(json).read("$.data.authentication[3].name"));
                    ocrFieldsObjects.setFACE_SIZE_VALID_VALUE(JsonPath.parse(json).read("$.data.authentication[3].value"));
                    ocrFieldsObjects.setID_NUMBER_CHECKSUM_VALID_NAME(JsonPath.parse(json).read("$.data.authentication[4].name"));
                    ocrFieldsObjects.setID_NUMBER_CHECKSUM_VALID_VALUE(JsonPath.parse(json).read("$.data.authentication[4].value"));
                    ocrFieldsObjects.setID_NUMBER_SIZE_VALID_NAME(JsonPath.parse(json).read("$.data.authentication[5].name"));
                    ocrFieldsObjects.setID_NUMBER_SIZE_VALID_VALUE(JsonPath.parse(json).read("$.data.authentication[5].value"));
                    ocrFieldsObjects.setISSUE_DATE_VALID_NAME(JsonPath.parse(json).read("$.data.authentication[6].name"));
                    ocrFieldsObjects.setISSUE_DATE_VALID_VALUE(JsonPath.parse(json).read("$.data.authentication[6].value"));
                    ocrFieldsObjects.setSYMBOLS_ON_FACE_IMAGE_VALID_NAME(JsonPath.parse(json).read("$.data.authentication[7].name"));
                    ocrFieldsObjects.setSYMBOLS_ON_FACE_IMAGE_VALID_VALUE(JsonPath.parse(json).read("$.data.authentication[7].value"));
                    ocrFieldsObjects.setTEMPLATE_MATCHING_VALID_NAME(JsonPath.parse(json).read("$.data.authentication[8].name"));
                    ocrFieldsObjects.setTEMPLATE_MATCHING_VALID_VALUE(JsonPath.parse(json).read("$.data.authentication[8].value"));
                } else if (ocrFieldsObjects.getId_type().equalsIgnoreCase("ISRAEL_ID2_TYPE_BLUE") || ocrFieldsObjects.getId_type().equalsIgnoreCase("ISRAEL_ID2_TYPE_BIOMETRIC_FRONT")) {

                    ocrFieldsObjects.setCOLORFUL_IMAGE_VALID_NAME(JsonPath.parse(json).read("$.data.authentication[0].name"));
                    ocrFieldsObjects.setCOLORFUL_IMAGE_VALID_VALUE(JsonPath.parse(json).read("$.data.authentication[0].value"));
                    ocrFieldsObjects.setEXPIRY_DATE_VALID_NAME(JsonPath.parse(json).read("$.data.authentication[1].name"));
                    ocrFieldsObjects.setEXPIRY_DATE_VALID_VALUE(JsonPath.parse(json).read("$.data.authentication[1].value"));
                    ocrFieldsObjects.setFACE_POSITION_VALID_NAME(JsonPath.parse(json).read("$.data.authentication[2].name"));
                    ocrFieldsObjects.setFACE_POSITION_VALID_VALUE(JsonPath.parse(json).read("$.data.authentication[2].value"));
                    ocrFieldsObjects.setFACE_ROTATION_VALID_NAME(JsonPath.parse(json).read("$.data.authentication[3].name"));
                    ocrFieldsObjects.setFACE_ROTATION_VALID_VALUE(JsonPath.parse(json).read("$.data.authentication[3].value"));
                    ocrFieldsObjects.setFACE_SIZE_VALID_NAME(JsonPath.parse(json).read("$.data.authentication[4].name"));
                    ocrFieldsObjects.setFACE_SIZE_VALID_VALUE(JsonPath.parse(json).read("$.data.authentication[4].value"));
                    ocrFieldsObjects.setID_ISSUED_FOR_5_OR_10_YEARS_NAME(JsonPath.parse(json).read("$.data.authentication[5].name"));
                    ocrFieldsObjects.setID_ISSUED_FOR_5_OR_10_YEARS_VALUE(JsonPath.parse(json).read("$.data.authentication[5].value"));
                    ocrFieldsObjects.setID_NUMBER_CHECKSUM_VALID_NAME(JsonPath.parse(json).read("$.data.authentication[6].name"));
                    ocrFieldsObjects.setID_NUMBER_CHECKSUM_VALID_VALUE(JsonPath.parse(json).read("$.data.authentication[6].value"));
                    ocrFieldsObjects.setID_NUMBER_SIZE_VALID_NAME(JsonPath.parse(json).read("$.data.authentication[7].name"));
                    ocrFieldsObjects.setID_NUMBER_SIZE_VALID_VALUE(JsonPath.parse(json).read("$.data.authentication[7].value"));
                    ocrFieldsObjects.setISSUE_DATE_VALID_NAME(JsonPath.parse(json).read("$.data.authentication[8].name"));
                    ocrFieldsObjects.setISSUE_DATE_VALID_VALUE(JsonPath.parse(json).read("$.data.authentication[8].value"));

                    if (ocrFieldsObjects.getId_type().equalsIgnoreCase("ISRAEL_ID2_TYPE_BLUE")) {
                        ocrFieldsObjects.setSYMBOLS_ON_FACE_IMAGE_VALID_NAME(JsonPath.parse(json).read("$.data.authentication[9].name"));
                        ocrFieldsObjects.setSYMBOLS_ON_FACE_IMAGE_VALID_VALUE(JsonPath.parse(json).read("$.data.authentication[9].value"));
                        ocrFieldsObjects.setTEMPLATE_MATCHING_VALID_NAME(JsonPath.parse(json).read("$.data.authentication[10].name"));
                        ocrFieldsObjects.setTEMPLATE_MATCHING_VALID_VALUE(JsonPath.parse(json).read("$.data.authentication[10].value"));
                    } else if (ocrFieldsObjects.getId_type().equalsIgnoreCase("ISRAEL_ID2_TYPE_BIOMETRIC_FRONT")) {
                        ocrFieldsObjects.setTEMPLATE_MATCHING_VALID_NAME(JsonPath.parse(json).read("$.data.authentication[9].name"));
                        ocrFieldsObjects.setTEMPLATE_MATCHING_VALID_VALUE(JsonPath.parse(json).read("$.data.authentication[9].value"));
                    }
                } else if (ocrFieldsObjects.getId_type().equalsIgnoreCase("ISRAEL_ID2_TYPE_BIOMETRIC_BACK")) {

                    ocrFieldsObjects.setCOLORFUL_IMAGE_VALID_NAME(JsonPath.parse(json).read("$.data.authentication[0].name"));
                    ocrFieldsObjects.setCOLORFUL_IMAGE_VALID_VALUE(JsonPath.parse(json).read("$.data.authentication[0].value"));
                    ocrFieldsObjects.setID_NUMBER_CHECKSUM_VALID_NAME(JsonPath.parse(json).read("$.data.authentication[1].name"));
                    ocrFieldsObjects.setID_NUMBER_CHECKSUM_VALID_VALUE(JsonPath.parse(json).read("$.data.authentication[1].value"));
                    ocrFieldsObjects.setID_NUMBER_SIZE_VALID_NAME(JsonPath.parse(json).read("$.data.authentication[2].name"));
                    ocrFieldsObjects.setID_NUMBER_SIZE_VALID_VALUE(JsonPath.parse(json).read("$.data.authentication[2].value"));
                    ocrFieldsObjects.setTEMPLATE_MATCHING_VALID_NAME(JsonPath.parse(json).read("$.data.authentication[3].name"));
                    ocrFieldsObjects.setTEMPLATE_MATCHING_VALID_VALUE(JsonPath.parse(json).read("$.data.authentication[3].value"));
                }

            }
            // data.fields
            if (json.contains("\"fields\" :")) {
                ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_ID_NUMBER_NAME(JsonPath.parse(json).read("$.data.fields[0].name"));
                ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_ID_NUMBER_VALUE(JsonPath.parse(json).read("$.data.fields[0].value"));
                ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_ID_NUMBER_X_AUTH(JsonPath.parse(json).read("$.data.fields[0].alignment_X_authentication"));
                ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_ID_NUMBER_Y_AUTH(JsonPath.parse(json).read("$.data.fields[0].alignment_Y_authentication"));
                ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_ID_NUMBER_SIZE_AUTH(JsonPath.parse(json).read("$.data.fields[0].character_size_authentication"));
                ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_ID_NUMBER_FONT_AUTH(JsonPath.parse(json).read("$.data.fields[0].font_authentication"));

                ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_LAST_NAME_NAME(JsonPath.parse(json).read("$.data.fields[1].name"));
                ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_LAST_NAME_VALUE(JsonPath.parse(json).read("$.data.fields[1].value"));
                ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_LAST_NAME_X_AUTH(JsonPath.parse(json).read("$.data.fields[1].alignment_X_authentication"));
                ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_LAST_NAME_Y_AUTH(JsonPath.parse(json).read("$.data.fields[1].alignment_Y_authentication"));
                ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_LAST_NAME_SIZE_AUTH(JsonPath.parse(json).read("$.data.fields[1].character_size_authentication"));
                ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_LAST_NAME_FONT_AUTH(JsonPath.parse(json).read("$.data.fields[1].font_authentication"));

                ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_FIRST_NAME_NAME(JsonPath.parse(json).read("$.data.fields[2].name"));
                ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_FIRST_NAME_VALUE(JsonPath.parse(json).read("$.data.fields[2].value"));
                ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_FIRST_NAME_X_AUTH(JsonPath.parse(json).read("$.data.fields[2].alignment_X_authentication"));
                ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_FIRST_NAME_Y_AUTH(JsonPath.parse(json).read("$.data.fields[2].alignment_Y_authentication"));
                ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_FIRST_NAME_SIZE_AUTH(JsonPath.parse(json).read("$.data.fields[2].character_size_authentication"));
                ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_FIRST_NAME_FONT_AUTH(JsonPath.parse(json).read("$.data.fields[2].font_authentication"));

                if (ocrFieldsObjects.getId_type().equalsIgnoreCase("ISRAEL_ID2_TYPE_GREEN")) {

                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_DATE_OF_BIRTH_NAME(JsonPath.parse(json).read("$.data.fields[3].name"));
                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_DATE_OF_BIRTH_VALUE(JsonPath.parse(json).read("$.data.fields[3].value"));
                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_DATE_OF_BIRTH_X_AUTH(JsonPath.parse(json).read("$.data.fields[3].alignment_X_authentication"));
                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_DATE_OF_BIRTH_Y_AUTH(JsonPath.parse(json).read("$.data.fields[3].alignment_Y_authentication"));
                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_DATE_OF_BIRTH_SIZE_AUTH(JsonPath.parse(json).read("$.data.fields[3].character_size_authentication"));
                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_DATE_OF_BIRTH_FONT_AUTH(JsonPath.parse(json).read("$.data.fields[3].font_authentication"));

                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_DATE_OF_ISSUE_NAME(JsonPath.parse(json).read("$.data.fields[4].name"));
                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_DATE_OF_ISSUE_VALUE(JsonPath.parse(json).read("$.data.fields[4].value"));
                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_DATE_OF_ISSUE_X_AUTH(JsonPath.parse(json).read("$.data.fields[4].alignment_X_authentication"));
                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_DATE_OF_ISSUE_Y_AUTH(JsonPath.parse(json).read("$.data.fields[4].alignment_Y_authentication"));
                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_DATE_OF_ISSUE_SIZE_AUTH(JsonPath.parse(json).read("$.data.fields[4].character_size_authentication"));
                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_DATE_OF_ISSUE_FONT_AUTH(JsonPath.parse(json).read("$.data.fields[4].font_authentication"));

                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_PLACE_OF_BIRTH_NAME(JsonPath.parse(json).read("$.data.fields[5].name"));
                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_PLACE_OF_BIRTH_VALUE(JsonPath.parse(json).read("$.data.fields[5].value"));
                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_PLACE_OF_BIRTH_X_AUTH(JsonPath.parse(json).read("$.data.fields[5].alignment_X_authentication"));
                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_PLACE_OF_BIRTH_Y_AUTH(JsonPath.parse(json).read("$.data.fields[5].alignment_Y_authentication"));
                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_PLACE_OF_BIRTH_SIZE_AUTH(JsonPath.parse(json).read("$.data.fields[5].character_size_authentication"));
                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_PLACE_OF_BIRTH_FONT_AUTH(JsonPath.parse(json).read("$.data.fields[5].font_authentication"));

                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_GENDER_NAME(JsonPath.parse(json).read("$.data.fields[6].name"));
                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_GENDER_VALUE(JsonPath.parse(json).read("$.data.fields[6].value"));
                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_GENDER_X_AUTH(JsonPath.parse(json).read("$.data.fields[6].alignment_X_authentication"));
                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_GENDER_Y_AUTH(JsonPath.parse(json).read("$.data.fields[6].alignment_Y_authentication"));
                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_GENDER_SIZE_AUTH(JsonPath.parse(json).read("$.data.fields[6].character_size_authentication"));
                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_GENDER_FONT_AUTH(JsonPath.parse(json).read("$.data.fields[6].font_authentication"));

                } else if (ocrFieldsObjects.getId_type().equalsIgnoreCase("ISRAEL_ID2_TYPE_BLUE")) {

                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_DATE_OF_BIRTH_NAME(JsonPath.parse(json).read("$.data.fields[3].name"));
                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_DATE_OF_BIRTH_VALUE(JsonPath.parse(json).read("$.data.fields[3].value"));
                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_DATE_OF_BIRTH_X_AUTH(JsonPath.parse(json).read("$.data.fields[3].alignment_X_authentication"));
                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_DATE_OF_BIRTH_Y_AUTH(JsonPath.parse(json).read("$.data.fields[3].alignment_Y_authentication"));
                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_DATE_OF_BIRTH_SIZE_AUTH(JsonPath.parse(json).read("$.data.fields[3].character_size_authentication"));
                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_DATE_OF_BIRTH_FONT_AUTH(JsonPath.parse(json).read("$.data.fields[3].font_authentication"));

                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_DATE_OF_ISSUE_NAME(JsonPath.parse(json).read("$.data.fields[4].name"));
                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_DATE_OF_ISSUE_VALUE(JsonPath.parse(json).read("$.data.fields[4].value"));
                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_DATE_OF_ISSUE_X_AUTH(JsonPath.parse(json).read("$.data.fields[4].alignment_X_authentication"));
                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_DATE_OF_ISSUE_Y_AUTH(JsonPath.parse(json).read("$.data.fields[4].alignment_Y_authentication"));
                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_DATE_OF_ISSUE_SIZE_AUTH(JsonPath.parse(json).read("$.data.fields[4].character_size_authentication"));
                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_DATE_OF_ISSUE_FONT_AUTH(JsonPath.parse(json).read("$.data.fields[4].font_authentication"));

                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_DATE_OF_EXPIRY_NAME(JsonPath.parse(json).read("$.data.fields[5].name"));
                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_DATE_OF_EXPIRY_VALUE(JsonPath.parse(json).read("$.data.fields[5].value"));
                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_DATE_OF_EXPIRY_X_AUTH(JsonPath.parse(json).read("$.data.fields[5].alignment_X_authentication"));
                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_DATE_OF_EXPIRY_Y_AUTH(JsonPath.parse(json).read("$.data.fields[5].alignment_Y_authentication"));
                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_DATE_OF_EXPIRY_SIZE_AUTH(JsonPath.parse(json).read("$.data.fields[5].character_size_authentication"));
                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_DATE_OF_EXPIRY_FONT_AUTH(JsonPath.parse(json).read("$.data.fields[5].font_authentication"));

                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_PLACE_OF_BIRTH_NAME(JsonPath.parse(json).read("$.data.fields[6].name"));
                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_PLACE_OF_BIRTH_VALUE(JsonPath.parse(json).read("$.data.fields[6].value"));
                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_PLACE_OF_BIRTH_X_AUTH(JsonPath.parse(json).read("$.data.fields[6].alignment_X_authentication"));
                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_PLACE_OF_BIRTH_Y_AUTH(JsonPath.parse(json).read("$.data.fields[6].alignment_Y_authentication"));
                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_PLACE_OF_BIRTH_SIZE_AUTH(JsonPath.parse(json).read("$.data.fields[6].character_size_authentication"));
                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_PLACE_OF_BIRTH_FONT_AUTH(JsonPath.parse(json).read("$.data.fields[6].font_authentication"));

                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_GENDER_NAME(JsonPath.parse(json).read("$.data.fields[7].name"));
                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_GENDER_VALUE(JsonPath.parse(json).read("$.data.fields[7].value"));
                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_GENDER_X_AUTH(JsonPath.parse(json).read("$.data.fields[7].alignment_X_authentication"));
                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_GENDER_Y_AUTH(JsonPath.parse(json).read("$.data.fields[7].alignment_Y_authentication"));
                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_GENDER_SIZE_AUTH(JsonPath.parse(json).read("$.data.fields[7].character_size_authentication"));
                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_GENDER_FONT_AUTH(JsonPath.parse(json).read("$.data.fields[7].font_authentication"));
                } else if (ocrFieldsObjects.getId_type().equalsIgnoreCase("ISRAEL_ID2_TYPE_BIOMETRIC_FRONT")) {

                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_DATE_OF_BIRTH_NAME(JsonPath.parse(json).read("$.data.fields[3].name"));
                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_DATE_OF_BIRTH_VALUE(JsonPath.parse(json).read("$.data.fields[3].value"));
                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_DATE_OF_BIRTH_X_AUTH(JsonPath.parse(json).read("$.data.fields[3].alignment_X_authentication"));
                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_DATE_OF_BIRTH_Y_AUTH(JsonPath.parse(json).read("$.data.fields[3].alignment_Y_authentication"));
                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_DATE_OF_BIRTH_SIZE_AUTH(JsonPath.parse(json).read("$.data.fields[3].character_size_authentication"));
                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_DATE_OF_BIRTH_FONT_AUTH(JsonPath.parse(json).read("$.data.fields[3].font_authentication"));

                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_DATE_OF_ISSUE_NAME(JsonPath.parse(json).read("$.data.fields[4].name"));
                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_DATE_OF_ISSUE_VALUE(JsonPath.parse(json).read("$.data.fields[4].value"));
                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_DATE_OF_ISSUE_X_AUTH(JsonPath.parse(json).read("$.data.fields[4].alignment_X_authentication"));
                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_DATE_OF_ISSUE_Y_AUTH(JsonPath.parse(json).read("$.data.fields[4].alignment_Y_authentication"));
                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_DATE_OF_ISSUE_SIZE_AUTH(JsonPath.parse(json).read("$.data.fields[4].character_size_authentication"));
                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_DATE_OF_ISSUE_FONT_AUTH(JsonPath.parse(json).read("$.data.fields[4].font_authentication"));

                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_DATE_OF_EXPIRY_NAME(JsonPath.parse(json).read("$.data.fields[5].name"));
                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_DATE_OF_EXPIRY_VALUE(JsonPath.parse(json).read("$.data.fields[5].value"));
                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_DATE_OF_EXPIRY_X_AUTH(JsonPath.parse(json).read("$.data.fields[5].alignment_X_authentication"));
                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_DATE_OF_EXPIRY_Y_AUTH(JsonPath.parse(json).read("$.data.fields[5].alignment_Y_authentication"));
                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_DATE_OF_EXPIRY_SIZE_AUTH(JsonPath.parse(json).read("$.data.fields[5].character_size_authentication"));
                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_DATE_OF_EXPIRY_FONT_AUTH(JsonPath.parse(json).read("$.data.fields[5].font_authentication"));
                } else if (ocrFieldsObjects.getId_type().equalsIgnoreCase("ISRAEL_ID2_TYPE_BIOMETRIC_BACK")) {

                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_PLACE_OF_BIRTH_NAME(JsonPath.parse(json).read("$.data.fields[3].name"));
                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_PLACE_OF_BIRTH_VALUE(JsonPath.parse(json).read("$.data.fields[3].value"));
                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_PLACE_OF_BIRTH_X_AUTH(JsonPath.parse(json).read("$.data.fields[3].alignment_X_authentication"));
                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_PLACE_OF_BIRTH_Y_AUTH(JsonPath.parse(json).read("$.data.fields[3].alignment_Y_authentication"));
                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_PLACE_OF_BIRTH_SIZE_AUTH(JsonPath.parse(json).read("$.data.fields[3].character_size_authentication"));
                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_PLACE_OF_BIRTH_FONT_AUTH(JsonPath.parse(json).read("$.data.fields[3].font_authentication"));

                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_GENDER_NAME(JsonPath.parse(json).read("$.data.fields[4].name"));
                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_GENDER_VALUE(JsonPath.parse(json).read("$.data.fields[4].value"));
                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_GENDER_X_AUTH(JsonPath.parse(json).read("$.data.fields[4].alignment_X_authentication"));
                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_GENDER_Y_AUTH(JsonPath.parse(json).read("$.data.fields[4].alignment_Y_authentication"));
                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_GENDER_SIZE_AUTH(JsonPath.parse(json).read("$.data.fields[4].character_size_authentication"));
                    ocrFieldsObjects.setISRAEL_ID2_FIELD_NAME_GENDER_FONT_AUTH(JsonPath.parse(json).read("$.data.fields[4].font_authentication"));
                }
            }

        } catch (Exception e) {
            System.out.println("Could not find the documents " + "or No document exists");
            System.out.println(e.getMessage());
        }
        closeMongoDBConnection();
        return ocrFieldsObjects;
    }


    @Step("display docs from mongo collection - DL")
    public OcrFieldsObjects displayDocumentsDL(OcrFieldsObjects ocrFieldsObjects, String collectionName, String caseId, String status) {

        try {
            mongoDBInitiateCollection(collectionName);
            Thread.sleep(3000);
            System.out.println("Successfully Connected" + " to the database");
            System.out.println("Displaying the list" + " of Documents");

            BasicDBObject searchQuery = new BasicDBObject();
            searchQuery.put("caseId", caseId);
            searchQuery.put("status", status);
            find = collection.find(searchQuery);
            cursor = find.iterator();
            Thread.sleep(3000);

            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            while (cursor.hasNext()) {
                json = ow.writeValueAsString(cursor.next());
//                System.out.println(json);
                System.out.println("json doc exists !!! (:");
            }

            //session_start
            if (json.contains("\"_id\" :")) {
                ocrFieldsObjects.setObjectId(parse(json).read("$._id"));
            }
            if (json.contains("\"timestamp\" :")) {
                ocrFieldsObjects.setTimestamp(parse(json).read("$.timestamp"));
            }
            if (json.contains("\"caseId\" :")) {
                ocrFieldsObjects.setCaseId(parse(json).read("$.caseId"));
            }
            if (json.contains("\"ocrType\" :")) {
                ocrFieldsObjects.setOcrType(parse(json).read("$.ocrType"));
            }
            if (json.contains("\"service\" :")) {
                ocrFieldsObjects.setServiceType(parse(json).read("$.service"));
            }
            if (json.contains("\"status\" :")) {
                ocrFieldsObjects.setStatus(parse(json).read("$.status"));
            }
            if (json.contains("\"token\" :")) {
                ocrFieldsObjects.setToken(parse(json).read("$.token"));
            }
            if (json.contains("\"workingMode\" :")) {
                ocrFieldsObjects.setWorkingMode(parse(json).read("$.workingMode"));
            }

            if (json.contains("lastProcessedImage")) {
                ocrFieldsObjects.setLastProcessedImage(parse(json).read("$.lastProcessedImage"));
            }
            if (json.contains("\"errorMessage\"")) {
                ocrFieldsObjects.setErrorMessage(parse(json).read("$.errorMessage"));
            }
            if (json.contains("\"errorCode\"")) {
                ocrFieldsObjects.setErrorCode(parse(json).read("$.errorCode"));
            }

            //session_start + success
            if (json.contains("\"faceImage\" :")) {
                ocrFieldsObjects.setFaceImage(parse(json).read("$.faceImage"));
            }
            if (json.contains("\"inputImage\" :")) {
                ocrFieldsObjects.setInputImage(parse(json).read("$.inputImage"));
            }
            if (json.contains("\"video\" :")) {
                ocrFieldsObjects.setVideo(parse(json).read("$.video"));
            }
            if (json.contains("\"croppedImage\" :")) {
                ocrFieldsObjects.setCroppedImage(parse(json).read("$.croppedImage"));
            }


            if (ocrFieldsObjects.getOcrType().equalsIgnoreCase("driver_license_IL") && ocrFieldsObjects.getStatus().equalsIgnoreCase("success")) {

                ocrFieldsObjects.setCardType(JsonPath.parse(json).read("$.data.card_type"));
                if (ocrFieldsObjects.getCardType().equalsIgnoreCase("new_front") || ocrFieldsObjects.getCardType().equalsIgnoreCase("old_front")) {
                    ocrFieldsObjects.setISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_LAST_NAME_HEBREW_NAME(JsonPath.parse(json).read("$.data.fields[0].name"));
                    ocrFieldsObjects.setISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_LAST_NAME_HEBREW_VALUE(JsonPath.parse(json).read("$.data.fields[0].value"));
                    ocrFieldsObjects.setISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_LAST_NAME_ENGLISH_NAME(JsonPath.parse(json).read("$.data.fields[1].name"));
                    ocrFieldsObjects.setISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_LAST_NAME_ENGLISH_VALUE(JsonPath.parse(json).read("$.data.fields[1].value"));
                    ocrFieldsObjects.setISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_FIRST_NAME_HEBREW_NAME(JsonPath.parse(json).read("$.data.fields[2].name"));
                    ocrFieldsObjects.setISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_FIRST_NAME_HEBREW_VALUE(JsonPath.parse(json).read("$.data.fields[2].value"));
                    ocrFieldsObjects.setISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_FIRST_NAME_ENGLISH_NAME(JsonPath.parse(json).read("$.data.fields[3].name"));
                    ocrFieldsObjects.setISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_FIRST_NAME_ENGLISH_VALUE(JsonPath.parse(json).read("$.data.fields[3].value"));
                    ocrFieldsObjects.setISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_DATE_OF_BIRTH_NAME(JsonPath.parse(json).read("$.data.fields[4].name"));
                    ocrFieldsObjects.setISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_DATE_OF_BIRTH_VALUE(JsonPath.parse(json).read("$.data.fields[4].value"));
                    ocrFieldsObjects.setISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_DATE_OF_ISSUE_NAME(JsonPath.parse(json).read("$.data.fields[5].name"));
                    ocrFieldsObjects.setISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_DATE_OF_ISSUE_VALUE(JsonPath.parse(json).read("$.data.fields[5].value"));
                    ocrFieldsObjects.setISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_DATE_OF_EXPIRY_NAME(JsonPath.parse(json).read("$.data.fields[6].name"));
                    ocrFieldsObjects.setISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_DATE_OF_EXPIRY_VALUE(JsonPath.parse(json).read("$.data.fields[6].value"));

                    if (ocrFieldsObjects.getCardType().equalsIgnoreCase("new_front")) {
                        ocrFieldsObjects.setISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_ID_NUMBER_NAME(JsonPath.parse(json).read("$.data.fields[7].name"));
                        ocrFieldsObjects.setISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_ID_NUMBER_VALUE(JsonPath.parse(json).read("$.data.fields[7].value"));
                        ocrFieldsObjects.setISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_LICENSE_NUMBER_NAME(JsonPath.parse(json).read("$.data.fields[8].name"));
                        ocrFieldsObjects.setISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_LICENSE_NUMBER_VALUE(JsonPath.parse(json).read("$.data.fields[8].value"));
                    }
                    if (ocrFieldsObjects.getCardType().equalsIgnoreCase("old_front")) {
                        ocrFieldsObjects.setISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_ID_NUMBER_NAME(JsonPath.parse(json).read("$.data.fields[8].name"));
                        ocrFieldsObjects.setISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_ID_NUMBER_VALUE(JsonPath.parse(json).read("$.data.fields[8].value"));
                        ocrFieldsObjects.setISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_LICENSE_NUMBER_NAME(JsonPath.parse(json).read("$.data.fields[7].name"));
                        ocrFieldsObjects.setISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_LICENSE_NUMBER_VALUE(JsonPath.parse(json).read("$.data.fields[7].value"));
                    }
                    ocrFieldsObjects.setISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_ADDRESS_NAME(JsonPath.parse(json).read("$.data.fields[9].name"));
                    ocrFieldsObjects.setISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_ADDRESS_VALUE(JsonPath.parse(json).read("$.data.fields[9].value"));
                    ocrFieldsObjects.setISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_LICENSE_CATEGORIES_NAME(JsonPath.parse(json).read("$.data.fields[10].name"));
                    ocrFieldsObjects.setISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_LICENSE_CATEGORIES_VALUE(JsonPath.parse(json).read("$.data.fields[10].value"));
                } else if (ocrFieldsObjects.getCardType().equalsIgnoreCase("new_back") || ocrFieldsObjects.getCardType().equalsIgnoreCase("old_back")) {

                    ocrFieldsObjects.setISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_ID_NUMBER_NAME(JsonPath.parse(json).read("$.data.fields[0].name"));
                    ocrFieldsObjects.setISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_ID_NUMBER_VALUE(JsonPath.parse(json).read("$.data.fields[0].value"));
                    ocrFieldsObjects.setISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_B_YEAR_NAME(JsonPath.parse(json).read("$.data.fields[1].name"));
                    ocrFieldsObjects.setISRAEL_DRIVERS_LICENSE2_OCR_FIELD_NAME_B_YEAR_VALUE(JsonPath.parse(json).read("$.data.fields[1].value"));
                }


            }
        } catch (Exception e) {
            System.out.println("Could not find the documents " + "or No document exists");
            System.out.println(e.getMessage());
        }
        closeMongoDBConnection();
        return ocrFieldsObjects;
    }


    @Step("display docs from mongo collection - MRZ")
    public OcrFieldsObjects displayDocumentsMRZ(OcrFieldsObjects ocrFieldsObjects, String collectionName, String caseId, String status) {

        try {
            mongoDBInitiateCollection(collectionName);
            Thread.sleep(3000);
            System.out.println("Successfully Connected" + " to the database");
            System.out.println("Displaying the list" + " of Documents");

            BasicDBObject searchQuery = new BasicDBObject();
            searchQuery.put("caseId", caseId);
            searchQuery.put("status", status);
            find = collection.find(searchQuery);
            cursor = find.iterator();

            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            while (cursor.hasNext()) {
                json = ow.writeValueAsString(cursor.next());
//                System.out.println(json);
                System.out.println("json doc exists !!! (:");
            }


            //session_start
            if (json.contains("\"_id\" :")) {
                ocrFieldsObjects.setObjectId(parse(json).read("$._id"));
            }
            if (json.contains("\"timestamp\" :")) {
                ocrFieldsObjects.setTimestamp(parse(json).read("$.timestamp"));
            }
            if (json.contains("\"caseId\" :")) {
                ocrFieldsObjects.setCaseId(parse(json).read("$.caseId"));
            }
            if (json.contains("\"ocrType\" :")) {
                ocrFieldsObjects.setOcrType(parse(json).read("$.ocrType"));
            }
            if (json.contains("\"service\" :")) {
                ocrFieldsObjects.setServiceType(parse(json).read("$.service"));
            }
            if (json.contains("\"status\" :")) {
                ocrFieldsObjects.setStatus(parse(json).read("$.status"));
            }
            if (json.contains("\"token\" :")) {
                ocrFieldsObjects.setToken(parse(json).read("$.token"));
            }
            if (json.contains("\"workingMode\" :")) {
                ocrFieldsObjects.setWorkingMode(parse(json).read("$.workingMode"));
            }

            if (json.contains("lastProcessedImage")) {
                ocrFieldsObjects.setLastProcessedImage(parse(json).read("$.lastProcessedImage"));
            }
            if (json.contains("\"errorMessage\"")) {
                ocrFieldsObjects.setErrorMessage(parse(json).read("$.errorMessage"));
            }
            if (json.contains("\"errorCode\"")) {
                ocrFieldsObjects.setErrorCode(parse(json).read("$.errorCode"));
            }


            //session_start + success
            if (json.contains("\"faceImage\" :")) {
                ocrFieldsObjects.setFaceImage(parse(json).read("$.faceImage"));
            }
            if (json.contains("\"inputImage\" :")) {
                ocrFieldsObjects.setInputImage(parse(json).read("$.inputImage"));
            }
            if (json.contains("\"video\" :")) {
                ocrFieldsObjects.setVideo(parse(json).read("$.video"));
            }
            if (json.contains("\"cardID_type\" :")) {
                ocrFieldsObjects.setCardID_type(parse(json).read("$.data.cardID_type"));
            }

            ocrFieldsObjects.setFirst_row_name(JsonPath.parse(json).read("$.data.mrz_rows[0].name"));
            ocrFieldsObjects.setFirst_row_value(JsonPath.parse(json).read("$.data.mrz_rows[0].value"));
            ocrFieldsObjects.setSecond_row_name(JsonPath.parse(json).read("$.data.mrz_rows[1].name"));
            ocrFieldsObjects.setSecond_row_value(JsonPath.parse(json).read("$.data.mrz_rows[1].value"));
            ocrFieldsObjects.setThird_row_name(JsonPath.parse(json).read("$.data.mrz_rows[2].name"));
            ocrFieldsObjects.setThird_row_value(JsonPath.parse(json).read("$.data.mrz_rows[2].value"));

            ocrFieldsObjects.setDate_of_birth_name(JsonPath.parse(json).read("$.data.fields[0].name"));
            ocrFieldsObjects.setDate_of_birth_value(JsonPath.parse(json).read("$.data.fields[0].value"));
            ocrFieldsObjects.setDate_of_expiry_name(JsonPath.parse(json).read("$.data.fields[1].name"));
            ocrFieldsObjects.setDate_of_expiry_value(JsonPath.parse(json).read("$.data.fields[1].value"));
            ocrFieldsObjects.setFirst_name_name_in_fields(JsonPath.parse(json).read("$.data.fields[2].name"));
            ocrFieldsObjects.setFirst_name_value_in_fields(JsonPath.parse(json).read("$.data.fields[2].value"));
            ocrFieldsObjects.setGender_name(JsonPath.parse(json).read("$.data.fields[3].name"));
            ocrFieldsObjects.setGender_value(JsonPath.parse(json).read("$.data.fields[3].value"));
            ocrFieldsObjects.setId_number_name(JsonPath.parse(json).read("$.data.fields[4].name"));
            ocrFieldsObjects.setId_number_value(JsonPath.parse(json).read("$.data.fields[4].value"));
            ocrFieldsObjects.setIssuing_country_name(JsonPath.parse(json).read("$.data.fields[5].name"));
            ocrFieldsObjects.setIssuing_country_value(JsonPath.parse(json).read("$.data.fields[5].value"));
            ocrFieldsObjects.setLast_name_name_in_fields(JsonPath.parse(json).read("$.data.fields[6].name"));
            ocrFieldsObjects.setLast_name_value_in_fields(JsonPath.parse(json).read("$.data.fields[6].value"));
            ocrFieldsObjects.setNationality_name(JsonPath.parse(json).read("$.data.fields[7].name"));
            ocrFieldsObjects.setNationality_value(JsonPath.parse(json).read("$.data.fields[7].value"));
            ocrFieldsObjects.setPassport_number_name(JsonPath.parse(json).read("$.data.fields[8].name"));
            ocrFieldsObjects.setPassport_number_value(JsonPath.parse(json).read("$.data.fields[8].value"));

            ocrFieldsObjects.setLast_name_name_in_vis(JsonPath.parse(json).read("$.data.vis_fields[0].name"));
            ocrFieldsObjects.setLast_name_value_in_vis(JsonPath.parse(json).read("$.data.vis_fields[0].value"));
            ocrFieldsObjects.setFirst_name_name_in_vis(JsonPath.parse(json).read("$.data.vis_fields[1].name"));
            ocrFieldsObjects.setFirst_name_value_in_vis(JsonPath.parse(json).read("$.data.vis_fields[1].value"));

            ocrFieldsObjects.setTruncated(parse(json).read("$.data.extra.isTruncated"));
            ocrFieldsObjects.setFaceAvailable(parse(json).read("$.data.extra.isFaceAvailable"));

        } catch (Exception e) {
            System.out.println("Could not find the documents " + "or No document exists");
            System.out.println(e.getMessage());
        }
        closeMongoDBConnection();
        return ocrFieldsObjects;
    }

    @Step("display docs from mongo collection - Liveness")
    public LivenessFieldsObjescts displayDocumentsLiveness(LivenessFieldsObjescts livenessFieldsObjescts, String collectionName, String caseId, String status) {

        try {
            mongoDBInitiateCollection(collectionName);

            System.out.println("Successfully Connected" + " to the database");
            System.out.println("Displaying the list" + " of Documents");

            BasicDBObject searchQuery = new BasicDBObject();
            searchQuery.put("caseId", caseId);
            searchQuery.put("status", status);
            find = collection.find(searchQuery);
            cursor = find.iterator();

            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            while (cursor.hasNext()) {
                json = ow.writeValueAsString(cursor.next());
//                System.out.println(json);
                System.out.println("json doc exists !!! (:");
            }

            //session_start
            if (json.contains("\"_id\" :")) {
                livenessFieldsObjescts.setObjectId(parse(json).read("$._id"));
            }
            if (json.contains("\"caseId\" :")) {
                livenessFieldsObjescts.setCaseId(parse(json).read("$.caseId"));
            }
            if (json.contains("\"livenessEngineThreshold\" :")) {
                livenessFieldsObjescts.setCaseId(parse(json).read("$.caseId"));
            }
            if (json.contains("\"service\" :")) {
                livenessFieldsObjescts.setService(parse(json).read("$.service"));
            }
            if (json.contains("\"status\" :")) {
                livenessFieldsObjescts.setStatus(parse(json).read("$.status"));
            }
            if (json.contains("\"statusReason\" :")) {
                livenessFieldsObjescts.setStatus(parse(json).read("$.status"));
            }
            if (json.contains("\"token\" :")) {
                livenessFieldsObjescts.setToken(parse(json).read("$.token"));
            }
            if (json.contains("\"workingMode\" :")) {
                livenessFieldsObjescts.setWorkingMode(parse(json).read("$.workingMode"));
            }
            if (json.contains("\"errorMessage\"")) {
                livenessFieldsObjescts.setErrorMessage(parse(json).read("$.errorMessage"));
            }
            if (json.contains("\"errorCode\"")) {
                livenessFieldsObjescts.setErrorCode(parse(json).read("$.errorCode"));
            }
            if (json.contains("\"livenessEngineThreshold\"")) {
                livenessFieldsObjescts.setLivenessEngineThreshold(parse(json).read("$.livenessEngineThreshold"));
            }
            if (json.contains("\"statusReason\"")) {
                livenessFieldsObjescts.setStatusReason(parse(json).read("$.statusReason"));
            }
            if (json.contains("\"libraryConfigurationName\"")) {
                livenessFieldsObjescts.setLibraryConfigurationName(parse(json).read("$.libraryConfigurationName"));
            }


            //session_start + success
            if (json.contains("\"faceImage\" :")) {
                livenessFieldsObjescts.setFaceImage(parse(json).read("$.faceImage"));
            }
            if (json.contains("\"frameImage\" :")) {
                livenessFieldsObjescts.setFrameImage(parse(json).read("$.frameImage"));
            }
            if (json.contains("\"video\" :")) {
                livenessFieldsObjescts.setVideo(parse(json).read("$.video"));
            }

        } catch (Exception e) {
            System.out.println("Could not find the documents " + "or No document exists");
            System.out.println(e.getMessage());
        }
        closeMongoDBConnection();
        return livenessFieldsObjescts;
    }

    @Step("display docs from mongo collection - Cheque")
    public OcrFieldsObjects displayDocumentsCheque(OcrFieldsObjects ocrFieldsObjects, String collectionName, String caseId, String status) {
        System.out.println("caseID is: " + caseId);
        try {
            mongoDBInitiateCollection(collectionName);
            Thread.sleep(3000);
            System.out.println("Successfully Connected" + " to the database");
            System.out.println("Displaying the list" + " of Documents");

            BasicDBObject searchQuery = new BasicDBObject();
            searchQuery.put("caseId", caseId);
            searchQuery.put("status", status);
            find = collection.find(searchQuery);
            cursor = find.iterator();

            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            while (cursor.hasNext()) {
                json = ow.writeValueAsString(cursor.next());
                System.out.println("json doc exists !!! (:");
            }


            //session_start
            if (json.contains("\"requiredMinimumCorrelation\" :")) {
                ocrFieldsObjects.setRequiredMinimumCorrelation(parse(json).read("$.data.requiredMinimumCorrelation"));
            }

            if (json.contains("\"_id\" :")) {
                ocrFieldsObjects.setObjectId(parse(json).read("$._id"));
            }
            if (json.contains("\"timestamp\" :")) {
                ocrFieldsObjects.setTimestamp(parse(json).read("$.timestamp"));
            }

            if (json.contains("\"numberLineImage\" :")) {
                ocrFieldsObjects.setNumberLineImage(parse(json).read("$.numberLineImage"));
            }

            if (json.contains("\"croppedImage\" :")) {
                ocrFieldsObjects.setCroppedImage(parse(json).read("$.croppedImage"));
            }

            if (json.contains("\"caseId\" :")) {
                ocrFieldsObjects.setCaseId(parse(json).read("$.caseId"));
            }

            if (json.contains("\"cheque_number\" :")) {
                ocrFieldsObjects.setCheque_number(parse(json).read("$.data.fields.cheque_number"));
            }

            if (json.contains("\"blocks\" :")) {
                ocrFieldsObjects.setFirstRowBlocks(parse(json).read("$.data.blocks[0]"));
                ocrFieldsObjects.setSecondRowBlocks(parse(json).read("$.data.blocks[1]"));
                ocrFieldsObjects.setThirdRowBlocks(parse(json).read("$.data.blocks[2]"));
            }

            if (json.contains("\"characters\" :")) {
                JSONArray jsonArray = JsonPath.read(json, "$.data.characters[0][*].character");
                ocrFieldsObjects.setFirstBlockCharactersSize(jsonArray.size());

                JSONArray jsonArray2 = JsonPath.read(json, "$.data.characters[1][*].character");
                ocrFieldsObjects.setSecondBlockCharactersSize(jsonArray2.size());

                JSONArray jsonArray3 = JsonPath.read(json, "$.data.characters[2][*].character");
                ocrFieldsObjects.setThirdBlockCharactersSize(jsonArray3.size());

                JSONArray jsonArray4 = JsonPath.read(json, "$.data.characters[*][*].correlation");

                for (Object cc : jsonArray4) {
                    ocrFieldsObjects.setCorrelationInRange(Double.valueOf((Double) cc) < 1 && Double.valueOf((Double) cc) >= ocrFieldsObjects.getRequiredMinimumCorrelation());
                }

                JSONArray firstBlockCharacter = JsonPath.read(json, "$.data.characters[0][*].character");
                JSONArray secondBlockCharacter = JsonPath.read(json, "$.data.characters[1][*].character");
                JSONArray thirdBlockCharacter = JsonPath.read(json, "$.data.characters[2][*].character");

                StringBuilder stringBuilderOfFirstRowCharacters = new StringBuilder();
                StringBuilder stringBuilderOfSecondRowCharacters = new StringBuilder();
                StringBuilder stringBuilderOfThirdRowCharacters = new StringBuilder();

                for (int i = 0; i < firstBlockCharacter.size(); i++) {
                    stringBuilderOfFirstRowCharacters.append(firstBlockCharacter.get(i));
                }

                for (int i = 0; i < secondBlockCharacter.size(); i++) {
                    stringBuilderOfSecondRowCharacters.append(secondBlockCharacter.get(i));
                }

                for (int i = 0; i < thirdBlockCharacter.size(); i++) {
                    stringBuilderOfThirdRowCharacters.append(thirdBlockCharacter.get(i));
                }

                for (int i = 0; i < ocrFieldsObjects.getFirstRowBlocks().length(); i++) {
                    ocrFieldsObjects.setFirstBlockCharactersLetter(stringBuilderOfFirstRowCharacters.toString().charAt(i) == ocrFieldsObjects.getFirstRowBlocks().charAt(i));
                }

                for (int i = 0; i < ocrFieldsObjects.getSecondRowBlocks().length(); i++) {
                    ocrFieldsObjects.setSecondBlockCharactersLetter(stringBuilderOfSecondRowCharacters.toString().charAt(i) == ocrFieldsObjects.getSecondRowBlocks().charAt(i));
                }

                for (int i = 0; i < ocrFieldsObjects.getThirdRowBlocks().length(); i++) {
                    ocrFieldsObjects.setThirdBlockCharactersLetter(stringBuilderOfThirdRowCharacters.toString().charAt(i) == ocrFieldsObjects.getThirdRowBlocks().charAt(i));
                }
            }

            if (json.contains("\"ocrType\" :")) {
                ocrFieldsObjects.setOcrType(parse(json).read("$.ocrType"));
            }
            if (json.contains("\"service\" :")) {
                ocrFieldsObjects.setServiceType(parse(json).read("$.service"));
            }
            if (json.contains("\"status\" :")) {
                ocrFieldsObjects.setStatus(parse(json).read("$.status"));
            }
            if (json.contains("\"token\" :")) {
                ocrFieldsObjects.setToken(parse(json).read("$.token"));
            }
            if (json.contains("\"workingMode\" :")) {
                ocrFieldsObjects.setWorkingMode(parse(json).read("$.workingMode"));
            }

            if (json.contains("\"lastProcessedImage\" :")) {
                ocrFieldsObjects.setLastProcessedImage(parse(json).read("$.lastProcessedImage"));
            }

            if (json.contains("\"minimumCorrelation\" :")) {
                ocrFieldsObjects.setMinimumCorrelation(parse(json).read("$.data.minimumCorrelation"));
            }

            if (json.contains("\"averageCorrelation\" :")) {
                ocrFieldsObjects.setAverageCorrelation(parse(json).read("$.data.averageCorrelation"));
            }


            if (json.contains("\"video\" :")) {
                ocrFieldsObjects.setVideo(parse(json).read("$.video"));
            }

        } catch (Exception e) {
            System.out.println("Could not find the documents " + "or No document exists");
            System.out.println(e.getMessage());
        }
        closeMongoDBConnection();
        return ocrFieldsObjects;
    }

    @Step("Make search query and set the result to json variable")
    public void makeSearchQuery(String caseId, String status) {
        try {
            BasicDBObject searchQuery = new BasicDBObject();
            searchQuery.put("caseId", caseId);
            searchQuery.put("status", status);
            Thread.sleep(3000);
            find = collection.find(searchQuery);
            cursor = find.iterator();
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            while (cursor.hasNext()) {
                json = ow.writeValueAsString(cursor.next());
                System.out.println("json doc exists !!! (:");
            }
            System.out.println("Query: " + searchQuery);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Step("delete all values in collection")
    public void deleteMongoCollection(String collectionName) {
        try {
            MongoClientURI uri = new MongoClientURI("mongodb://" + USER_NAME + ":" + DECODE_PASSWORD + "@" + PORT + AUTH);
            MongoClient mongoClient = new com.mongodb.MongoClient(uri);
            MongoDatabase db = mongoClient.getDatabase("webhook");
            collection = db.getCollection(collectionName);
            collection.deleteMany(gte("timestamp", 100));
            Assert.assertEquals(collection.estimatedDocumentCount(), 0);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Step("Init the mongo connection and set mongo variables")
    public void mongoDBHandler(MongoDBVariables mongoVariables, String collectionName, String caseId, String status) {
        try {
            System.out.println("caseId: " + caseId);
            if (json == null) {
                mongoDBInitiateCollection(collectionName);
                Thread.sleep(3000);
                System.out.println("Successfully Connected" + " to the database");
                System.out.println("Displaying the list" + " of Documents");
            }
            makeSearchQuery(caseId, status);
            mongoVariables.setCaseId(JsonPath.parse(json).read("$.caseId"));
            mongoVariables.setStatus(JsonPath.parse(json).read("$.status"));
            if (mongoVariables.getStatus().equalsIgnoreCase("session_start")) {
                setSessionStartVariables(caseId, status, mongoVariables);
            } else {
                mongoVariables.setObjectId(JsonPath.parse(json).read("$._id").toString());
                mongoVariables.setServiceType(JsonPath.parse(json).read("$.serviceType"));
                mongoVariables.setConfigFilename(JsonPath.parse(json).read("$.configFilename"));
                mongoVariables.setStatus(JsonPath.parse(json).read("$.status"));
                mongoVariables.setStatusReason(JsonPath.parse(json).read("$.statusReason"));
                mongoVariables.setSuccess(JsonPath.parse(json).read("$.success"));
                mongoVariables.setWorkingMode(JsonPath.parse(json).read("$.workingMode"));
                if (!mongoVariables.getWorkingMode().equalsIgnoreCase("single")) {
                    mongoVariables.setVideo(JsonPath.parse(json).read("$.video"));
                }
                mongoVariables.setStageStatus(JsonPath.parse(json).read("$.stagesResponsesArray[0].status"));
                System.out.println("Service-Type: " + mongoVariables.getServiceType());
            }
            /*
            set null inorder to "clean" the mongo variables
             */
            mongoVariables.setFirstNameHebrew(null);
            mongoVariables.setLastNameHebrew(null);

            //every other status beside "session_start"
            System.out.println("status: " + mongoVariables.getStatus());
            if (!mongoVariables.getStatus().equalsIgnoreCase("session_start")) {
                if (mongoVariables.getStatus().equalsIgnoreCase("timeout")) {
                    setVariablesOfTimeout(mongoVariables, 0);
                    if (mongoVariables.getStageStatus().equalsIgnoreCase("timeout"))
                        return;
                }
                switch (mongoVariables.getServiceType()) {
                    case "IL-ID": {
                        //Auth that all cards have:
                        mongoVariables.setImageIsColorful(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.auth.image_is_colorful"));
                        mongoVariables.setIdChecksumValid(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.auth.id_checksum_valid"));
                        mongoVariables.setTemplateMatch(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.auth.template_matched"));

                        mongoVariables.setFaceSizeValid((JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.auth.face_size_valid")));
                        mongoVariables.setFacePositionValid(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.auth.face_position_valid"));
                        mongoVariables.setFaceRotationValid(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.auth.face_rotation_valid"));

                        //Set variables according id type
                        mongoVariables.setCardType(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.card_type"));
                        if (!mongoVariables.getCardType().equalsIgnoreCase("bio_front")) {
                            oneSideId(mongoVariables);
                        } else {
                            twoSidesId(caseId, status, mongoVariables);
                            return;
                        }
                        //Images
                        mongoVariables.setFaceImage(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.images.face_image"));
                        mongoVariables.setInputImage(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.images.input_image"));
                        mongoVariables.setCroppedImage(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.images.cropped_image"));
                        break;
                    }
                    case "IL-DL": {
                        dlMongoDBVariables(mongoVariables);
                        break;
                    }
                    case "CNI": {
                        cniMongoDBVariables(mongoVariables);
                        break;
                    }
                    case "LIVENESS": {
                        if (mongoVariables.getStatusReason().equalsIgnoreCase("Image injection attempt detected")) {
                            System.out.println(mongoVariables.getCaseId());
                        }
                        livenessMongoDBVariables(json, mongoVariables);
                        break;
                    }
                    case "PHL-CHEQUES": {
                        philippinesChequesMongoDBVariables(mongoVariables);
                        break;
                    }
                    case "DK-DL": {
                        denmarkDlMongoDBVariables(mongoVariables);
                        break;
                    }
                    case "MRZ": {
                        mrzMongoDBVariables(mongoVariables);
                        break;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error Main Mongo Connection: " + e.getMessage());
        }
        closeMongoDBConnection();
    }

    public void mrzMongoDBVariables(MongoDBVariables mongoVariables) {
        try {
            mongoVariables.setActionType(JsonPath.parse(json).read("$.stagesResponsesArray[0].action_type"));
            mongoVariables.setOrder(JsonPath.parse(json).read("$.stagesResponsesArray[0].stage.order"));
            mongoVariables.setTypeUnderStage(JsonPath.parse(json).read("$.stagesResponsesArray[0].stage.type"));

            mongoVariables.setMrzText(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.fields.mrz_text"));
            mongoVariables.setMrzType(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.fields.mrz_type"));
            mongoVariables.setDocumentType(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.fields.document_type"));
            mongoVariables.setDocumentSubType(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.fields.document_sub_type"));
            mongoVariables.setIssuingCountryCode(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.fields.issuing_country_code"));
            mongoVariables.setLastNameEnglish(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.fields.last_name"));
            mongoVariables.setFirstNameEnglish(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.fields.first_name"));
            mongoVariables.setPassportNumber(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.fields.passport_number"));
            mongoVariables.setNationalityCode(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.fields.nationality_code"));
            mongoVariables.setDateOfBirth(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.fields.date_of_birth"));
            mongoVariables.setGender(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.fields.gender"));
            mongoVariables.setDateOfExpiry(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.fields.date_of_expiry"));
            mongoVariables.setPersonalNumber(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.fields.personal_number"));

            mongoVariables.setMightBeTruncated(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.metadata.might_be_truncated"));

            mongoVariables.setFaceImage(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.images.face_image"));
            mongoVariables.setInputImage(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.images.input_image"));
            mongoVariables.setCroppedImage(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.images.cropped_image"));
            mongoVariables.setCroppedFieldImage(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.images.cropped_field_image"));

            mongoVariables.setStageStatus(JsonPath.parse(json).read("$.stagesResponsesArray[0].status"));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void setSessionStartVariables(String caseId, String status, MongoDBVariables mongoDBVariables) {
        try {
            makeSearchQuery(caseId, status);
            mongoDBVariables.setObjectId(JsonPath.parse(json).read("$._id").toString());
            mongoDBVariables.setCaseId(JsonPath.parse(json).read("$.caseId"));
            mongoDBVariables.setServerType(JsonPath.parse(json).read("$.serverType"));
            mongoDBVariables.setServiceType(JsonPath.parse(json).read("$.serviceType"));
            mongoDBVariables.setStatus(JsonPath.parse(json).read("$.status"));
            mongoDBVariables.setWorkingMode(JsonPath.parse(json).read("$.workingMode"));
            mongoDBVariables.setTimestamp(JsonPath.parse(json).read("$.timestamp"));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Step("Init mongoDB variables of timeout")
    public void setVariablesOfTimeout(MongoDBVariables mongoVariables, int index) {
        System.out.println("entered timeout method");
        try {
            mongoVariables.setConfigFilename(JsonPath.parse(json).read("$.configFilename"));
            mongoVariables.setStatusReason(JsonPath.parse(json).read("$.statusReason"));
            mongoVariables.setSuccess(JsonPath.parse(json).read("$.success"));
            mongoVariables.setVideo(JsonPath.parse(json).read("$.video"));

            mongoVariables.setActionType(JsonPath.parse(json).read("$.stagesResponsesArray[" + index + "].action_type"));
            mongoVariables.setOrder(JsonPath.parse(json).read("$.stagesResponsesArray[" + index + "].stage.order"));
            mongoVariables.setTypeUnderStage(JsonPath.parse(json).read("$.stagesResponsesArray[" + index + "].stage.type"));
            mongoVariables.setStatusInLivenessStage(JsonPath.parse(json).read("$.stagesResponsesArray[" + index + "].status"));
            if (mongoVariables.getStatusInLivenessStage().equalsIgnoreCase("timeout")) {
                mongoVariables.setLastReceivedImage(JsonPath.parse(json).read("$.stagesResponsesArray[" + index + "].payload.images.last_received_image"));
            }
            if (JsonPath.parse(json).read("$.stagesResponsesArray[" + index + "].payload.fields.date_of_birth") != null) {
                mongoVariables.setDateOfBirth(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.fields.date_of_birth"));
            } else mongoVariables.setDateOfBirth(null);
            if (JsonPath.parse(json).read("$.stagesResponsesArray[" + index + "].payload.fields.date_of_expiry") != null) {
                mongoVariables.setDateOfExpiry(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.fields.date_of_expiry"));
            } else mongoVariables.setDateOfExpiry(null);
            if (JsonPath.parse(json).read("$.stagesResponsesArray[" + index + "].payload.fields.date_of_issue") != null) {
                mongoVariables.setDateOfIssue(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.fields.date_of_issue"));
            } else mongoVariables.setDateOfIssue(null);
        }catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Step("Init mongoDB ID that have one side variables")
    public void oneSideId(MongoDBVariables mongoDBVariables) {
        try {
            mongoDBVariables.setActionType(JsonPath.parse(json).read("$.stagesResponsesArray[0].action_type"));
            mongoDBVariables.setOrder(JsonPath.parse(json).read("$.stagesResponsesArray[0].stage.order"));
            mongoDBVariables.setTypeUnderStage(JsonPath.parse(json).read("$.stagesResponsesArray[0].stage.type"));

            if (JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.fields.date_of_birth") != null) {
                mongoDBVariables.setDateOfBirth(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.fields.date_of_birth"));
            } else mongoDBVariables.setDateOfBirth(null);
            if (mongoDBVariables.getCardType().equalsIgnoreCase("blue")) {
                if (JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.fields.date_of_expiry") != null) {
                    mongoDBVariables.setDateOfExpiry(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.fields.date_of_expiry"));
                } else mongoDBVariables.setDateOfExpiry(null);
                mongoDBVariables.setPeriodBetweenIssueAndExpiry(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.auth.period_between_issue_and_expiry_valid"));
            }
            if (JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.fields.date_of_issue") != null) {
                mongoDBVariables.setDateOfIssue(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.fields.date_of_issue"));
            } else mongoDBVariables.setDateOfIssue(null);
            mongoDBVariables.setFirstNameHebrew(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.fields.first_name_hebrew"));
            mongoDBVariables.setGender(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.fields.gender"));
            mongoDBVariables.setIdNumber(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.fields.id_number"));
            mongoDBVariables.setLastNameHebrew(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.fields.last_name_hebrew"));
            mongoDBVariables.setPlaceOfBirth(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.fields.place_of_birth"));

            //Auth
            mongoDBVariables.setStampDetected(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.auth.stamp_detected"));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Step("Init mongoDB ID that have two sides variables")
    public void twoSidesId(String caseId, String status, MongoDBVariables mongoDBVariables) {
        try {
            makeSearchQuery(caseId, status);
            switch (mongoDBVariables.getCounter()) {
                case 0: {
                    mongoDBVariables.setActionType(JsonPath.parse(json).read("$.stagesResponsesArray[0].action_type"));
                    mongoDBVariables.setOrder(JsonPath.parse(json).read("$.stagesResponsesArray[0].stage.order"));
                    mongoDBVariables.setTypeUnderStage(JsonPath.parse(json).read("$.stagesResponsesArray[0].stage.type"));

                    mongoDBVariables.setCardType(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.card_type"));

                    mongoDBVariables.setLastNameHebrew(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.fields.last_name_hebrew"));
                    mongoDBVariables.setFirstNameHebrew(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.fields.first_name_hebrew"));
                    mongoDBVariables.setDateOfBirth(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.fields.date_of_birth"));
                    mongoDBVariables.setDateOfIssue(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.fields.date_of_issue"));
                    mongoDBVariables.setDateOfExpiry(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.fields.date_of_expiry"));
                    mongoDBVariables.setIdNumber(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.fields.id_number"));

                    mongoDBVariables.setPeriodBetweenIssueAndExpiry(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.auth.period_between_issue_and_expiry_valid"));
                    mongoDBVariables.setChipAuth(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.auth.chip_auth"));
                    mongoDBVariables.setFaceImageReplacedWithPassportImage((JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.auth.face_image_replaced_with_passport_image")));

                    mongoDBVariables.setFaceImage(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.images.face_image"));
                    mongoDBVariables.setInputImage(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.images.input_image"));
                    mongoDBVariables.setCroppedImage(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.images.cropped_image"));

                    mongoDBVariables.setCounter(1);
                    break;
                }
                case 1: {
                    mongoDBVariables.setActionType(JsonPath.parse(json).read("$.stagesResponsesArray[1].action_type"));
                    mongoDBVariables.setOrder(JsonPath.parse(json).read("$.stagesResponsesArray[1].stage.order"));
                    mongoDBVariables.setTypeUnderStage(JsonPath.parse(json).read("$.stagesResponsesArray[1].stage.type"));

                    mongoDBVariables.setStageStatus(JsonPath.parse(json).read("$.stagesResponsesArray[1].status"));
                    if (mongoDBVariables.getStageStatus().equalsIgnoreCase("timeout")) {
                        mongoDBVariables.setLastReceivedImage(JsonPath.parse(json).read("$.stagesResponsesArray[1].payload.images.last_received_image"));
                        break;
                    }
                    mongoDBVariables.setCardType(JsonPath.parse(json).read("$.stagesResponsesArray[1].payload.card_type"));

                    mongoDBVariables.setIdNumber(JsonPath.parse(json).read("$.stagesResponsesArray[1].payload.fields.id_number"));
                    mongoDBVariables.setPlaceOfBirth(JsonPath.parse(json).read("$.stagesResponsesArray[1].payload.fields.place_of_birth"));
                    mongoDBVariables.setGender(JsonPath.parse(json).read("$.stagesResponsesArray[1].payload.fields.gender"));

                    mongoDBVariables.setIdNumberMatchFront(JsonPath.parse(json).read("$.stagesResponsesArray[1].payload.auth.id_number_matches_front"));

                    mongoDBVariables.setInputImage(JsonPath.parse(json).read("$.stagesResponsesArray[1].payload.images.input_image"));
                    mongoDBVariables.setCroppedImage(JsonPath.parse(json).read("$.stagesResponsesArray[1].payload.images.cropped_image"));
                    mongoDBVariables.setFaceImage(null);

                    mongoDBVariables.setCounter(0);
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error liveness mongo vars: " + e.getMessage());
        }
    }

    @Step("Init mongoDB DL variables")
    public void dlMongoDBVariables(MongoDBVariables mongoDBVariables) {
        try {
            switch (mongoDBVariables.getCounter()) {
                case 0: {
                    mongoDBVariables.setActionType(JsonPath.parse(json).read("$.stagesResponsesArray[0].action_type"));
                    mongoDBVariables.setOrder(JsonPath.parse(json).read("$.stagesResponsesArray[0].stage.order"));
                    mongoDBVariables.setTypeUnderStage(JsonPath.parse(json).read("$.stagesResponsesArray[0].stage.type"));
                    mongoDBVariables.setCardType(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.card_type"));

                    mongoDBVariables.setLastNameHebrew(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.fields.last_name_hebrew"));
                    mongoDBVariables.setLastNameEnglish(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.fields.last_name_english"));
                    mongoDBVariables.setFirstNameHebrew(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.fields.first_name_hebrew"));
                    mongoDBVariables.setFirstNameEnglish(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.fields.first_name_english"));
                    if (JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.fields.date_of_birth") != null) {
                        mongoDBVariables.setDateOfBirth(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.fields.date_of_birth"));
                    }
                    if (JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.fields.date_of_issue") != null) {
                        mongoDBVariables.setDateOfIssue(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.fields.date_of_issue"));
                    }
                    if (JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.fields.date_of_expiry") != null) {
                        mongoDBVariables.setDateOfExpiry(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.fields.date_of_expiry"));
                    }
                    mongoDBVariables.setLicenseNumber(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.fields.license_number"));
                    mongoDBVariables.setIdNumber(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.fields.id_number"));
                    mongoDBVariables.setAddress(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.fields.address"));
                    mongoDBVariables.setLicenseCategories(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.fields.license_categories"));

                    mongoDBVariables.setInputImage(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.images.input_image"));
                    mongoDBVariables.setCroppedImage(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.images.cropped_image"));
                    mongoDBVariables.setFaceImage(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.images.face_image"));

                    mongoDBVariables.setCounter(1);
                    break;
                }
                case 1: {
                    mongoDBVariables.setActionType(JsonPath.parse(json).read("$.stagesResponsesArray[1].action_type"));
                    mongoDBVariables.setOrder(JsonPath.parse(json).read("$.stagesResponsesArray[1].stage.order"));
                    mongoDBVariables.setTypeUnderStage(JsonPath.parse(json).read("$.stagesResponsesArray[1].stage.type"));

                    mongoDBVariables.setStageStatus(JsonPath.parse(json).read("$.stagesResponsesArray[1].status"));
                    if (mongoDBVariables.getStageStatus().equalsIgnoreCase("timeout")) {
                        mongoDBVariables.setLastReceivedImage(JsonPath.parse(json).read("$.stagesResponsesArray[1].payload.images.last_received_image"));
                        break;
                    }
                    mongoDBVariables.setCardType(JsonPath.parse(json).read("$.stagesResponsesArray[1].payload.card_type"));

                    mongoDBVariables.setIdNumber(JsonPath.parse(json).read("$.stagesResponsesArray[1].payload.fields.id_number"));
                    mongoDBVariables.setbYear(JsonPath.parse(json).read("$.stagesResponsesArray[1].payload.fields.b_year"));

                    mongoDBVariables.setInputImage(JsonPath.parse(json).read("$.stagesResponsesArray[1].payload.images.input_image"));
                    mongoDBVariables.setCroppedImage(JsonPath.parse(json).read("$.stagesResponsesArray[1].payload.images.cropped_image"));

                    mongoDBVariables.setCounter(0);
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Step("Init mongoDB CNI variables")
    public void cniMongoDBVariables(MongoDBVariables mongoDBVariables) {
        try {
            switch (mongoDBVariables.getCounter()) {
                case 0: {
                    mongoDBVariables.setActionType(JsonPath.parse(json).read("$.stagesResponsesArray[0].action_type"));
                    mongoDBVariables.setOrder(JsonPath.parse(json).read("$.stagesResponsesArray[0].stage.order"));
                    mongoDBVariables.setTypeUnderStage(JsonPath.parse(json).read("$.stagesResponsesArray[0].stage.type"));

                    mongoDBVariables.setCardType(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.card_type"));

                    mongoDBVariables.setInputImage(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.images.input_image"));
                    mongoDBVariables.setCroppedImage(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.images.cropped_image"));
                    if (mongoDBVariables.getCardType().equalsIgnoreCase("cni_front")) {
                        mongoDBVariables.setFaceImage(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.images.face_image"));
                    }
                    mongoDBVariables.setCounter(1);
                    break;
                }
                case 1: {
                    mongoDBVariables.setActionType(JsonPath.parse(json).read("$.stagesResponsesArray[1].action_type"));
                    mongoDBVariables.setOrder(JsonPath.parse(json).read("$.stagesResponsesArray[1].stage.order"));
                    mongoDBVariables.setTypeUnderStage(JsonPath.parse(json).read("$.stagesResponsesArray[1].stage.type"));
                    mongoDBVariables.setStageStatus(JsonPath.parse(json).read("$.stagesResponsesArray[1].status"));
                    if (mongoDBVariables.getStageStatus().equalsIgnoreCase("timeout")) {
                        mongoDBVariables.setLastReceivedImage(JsonPath.parse(json).read("$.stagesResponsesArray[1].payload.images.last_received_image"));
                        break;
                    }
                    mongoDBVariables.setCounter(2);
                    break;
                }
                case 2: {
                    mongoDBVariables.setActionType(JsonPath.parse(json).read("$.stagesResponsesArray[2].action_type"));
                    mongoDBVariables.setOrder(JsonPath.parse(json).read("$.stagesResponsesArray[2].stage.order"));
                    mongoDBVariables.setTypeUnderStage(JsonPath.parse(json).read("$.stagesResponsesArray[2].stage.type"));
                    mongoDBVariables.setStageStatus(JsonPath.parse(json).read("$.stagesResponsesArray[2].status"));
                    if (mongoDBVariables.getStageStatus().equalsIgnoreCase("timeout")) {
                        mongoDBVariables.setLastReceivedImage(JsonPath.parse(json).read("$.stagesResponsesArray[2].payload.images.last_received_image"));
                        break;
                    }
                    mongoDBVariables.setCardType(JsonPath.parse(json).read("$.stagesResponsesArray[2].payload.card_type"));

                    mongoDBVariables.setInputImage(JsonPath.parse(json).read("$.stagesResponsesArray[2].payload.images.input_image"));
                    mongoDBVariables.setCroppedImage(JsonPath.parse(json).read("$.stagesResponsesArray[2].payload.images.cropped_image"));

                    if (mongoDBVariables.getCardType().equalsIgnoreCase("cni_front")) {
                        mongoDBVariables.setFaceImage(JsonPath.parse(json).read("$.stagesResponsesArray[2].payload.images.face_image"));
                    }

                    mongoDBVariables.setCounter(3);
                    break;
                }
                case 3: {
                    mongoDBVariables.setActionType(JsonPath.parse(json).read("$.stagesResponsesArray[3].action_type"));
                    mongoDBVariables.setOrder(JsonPath.parse(json).read("$.stagesResponsesArray[3].stage.order"));
                    mongoDBVariables.setTypeUnderStage(JsonPath.parse(json).read("$.stagesResponsesArray[3].stage.type"));
                    mongoDBVariables.setStageStatus(JsonPath.parse(json).read("$.stagesResponsesArray[3].status"));
                    if (mongoDBVariables.getStageStatus().equalsIgnoreCase("timeout")) {
                        mongoDBVariables.setLastReceivedImage(JsonPath.parse(json).read("$.stagesResponsesArray[3].payload.images.last_received_image"));
                        break;
                    }
                    mongoDBVariables.setCounter(4);
                    break;
                }
                case 4: {
                    mongoDBVariables.setActionType(JsonPath.parse(json).read("$.stagesResponsesArray[4].action_type"));
                    mongoDBVariables.setOrder(JsonPath.parse(json).read("$.stagesResponsesArray[4].stage.order"));
                    mongoDBVariables.setTypeUnderStage(JsonPath.parse(json).read("$.stagesResponsesArray[4].stage.type"));
                    mongoDBVariables.setStageStatus(JsonPath.parse(json).read("$.stagesResponsesArray[4].status"));
                    if (mongoDBVariables.getStageStatus().equalsIgnoreCase("timeout")) {
                        mongoDBVariables.setLastReceivedImage(JsonPath.parse(json).read("$.stagesResponsesArray[4].payload.images.last_received_image"));
                        break;
                    }
                    mongoDBVariables.setStageData(JsonPath.parse(json).read("$.stagesResponsesArray[4].payload.otp_number"));
                    mongoDBVariables.setCounter(0);
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Step("Init mongoDB liveness active variables")
    public void livenessMongoDBVariables(String json, MongoDBVariables mongoDBVariables) {
        try {
            if (mongoDBVariables.getStatus().equalsIgnoreCase("success")) {
            } else if (mongoDBVariables.getStatus().equalsIgnoreCase("failure") && mongoDBVariables.getStatusReason().equalsIgnoreCase("Process ended with failure")) {
                Thread.sleep(3500);// connection time for failure mongo connection
                mongoDBVariables.setErrorCode(JsonPath.parse(json).read("$.error.code"));
                mongoDBVariables.setErrorMessage(JsonPath.parse(json).read("$.error.message"));
                mongoDBVariables.setClientReported(JsonPath.parse(json).read("$.error.clientReported"));
            }
            mongoDBVariables.setConfigFilename(JsonPath.parse(json).read("$.configFilename"));
            mongoDBVariables.setActionType(JsonPath.parse(json).read("$.stagesResponsesArray[0].action_type"));
            if (mongoDBVariables.getActionType().equalsIgnoreCase("complete")) {
                mongoDBVariables.setOrder(JsonPath.parse(json).read("$.stagesResponsesArray[0].stage.order"));
                mongoDBVariables.setTypeUnderStage(JsonPath.parse(json).read("$.stagesResponsesArray[0].stage.type"));
                mongoDBVariables.setStatusInLivenessStage(JsonPath.parse(json).read("$.stagesResponsesArray[0].status"));
                mongoDBVariables.setThreshold(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.threshold"));
                mongoDBVariables.setScore(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.score"));
                mongoDBVariables.setFaceImage(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.images.face_image"));
                mongoDBVariables.setInputImage(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.images.input_image"));
                mongoDBVariables.setTimeStampInLivenessStage(JsonPath.parse(json).read("$.stagesResponsesArray[0].timestamp"));
            } else {
                System.out.println("counter number is: " + mongoDBVariables.getCounter());
                switch (mongoDBVariables.getCounter()) {
                    case 0: {
                        mongoDBVariables.setActionType(JsonPath.parse(json).read("$.stagesResponsesArray[0].action_type"));
                        mongoDBVariables.setOrder(JsonPath.parse(json).read("$.stagesResponsesArray[0].stage.order"));
                        mongoDBVariables.setTypeUnderStage(JsonPath.parse(json).read("$.stagesResponsesArray[0].stage.type"));
                        mongoDBVariables.setStatusInLivenessStage(JsonPath.parse(json).read("$.stagesResponsesArray[0].status"));
                        mongoDBVariables.setThreshold(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.threshold"));
                        mongoDBVariables.setScore(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.score"));
                        mongoDBVariables.setFaceImage(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.images.face_image"));
                        mongoDBVariables.setInputImage(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.images.input_image"));
                        mongoDBVariables.setTimeStampInLivenessStage(JsonPath.parse(json).read("$.stagesResponsesArray[0].timestamp"));
                        mongoDBVariables.setCounter(1);
                        break;
                    }
                    case 1: {
                        mongoDBVariables.setStatusInLivenessStage(JsonPath.parse(json).read("$.stagesResponsesArray[1].status"));
                        if (mongoDBVariables.getStatusInLivenessStage().equalsIgnoreCase("timeout")) {
                            setVariablesOfTimeout(mongoDBVariables, mongoDBVariables.getCounter());
                        }
                        mongoDBVariables.setActionType(JsonPath.parse(json).read("$.stagesResponsesArray[1].action_type"));
                        mongoDBVariables.setOrder(JsonPath.parse(json).read("$.stagesResponsesArray[1].stage.order"));
                        mongoDBVariables.setTypeUnderStage(JsonPath.parse(json).read("$.stagesResponsesArray[1].stage.type"));
                        if (mongoDBVariables.getTypeUnderStage().equalsIgnoreCase("otp")) {
                            mongoDBVariables.setStageData(JsonPath.parse(json).read("$.stagesResponsesArray[1].payload.otp_number"));
                        }
                        mongoDBVariables.setCounter(2);
                        break;
                    }
                    case 2: {
                        mongoDBVariables.setStatusInLivenessStage(JsonPath.parse(json).read("$.stagesResponsesArray[2].status"));
                        if (mongoDBVariables.getStatusInLivenessStage().equalsIgnoreCase("timeout")) {
                            setVariablesOfTimeout(mongoDBVariables, mongoDBVariables.getCounter());
                        }
                        mongoDBVariables.setActionType(JsonPath.parse(json).read("$.stagesResponsesArray[2].action_type"));
                        mongoDBVariables.setOrder(JsonPath.parse(json).read("$.stagesResponsesArray[2].stage.order"));
                        mongoDBVariables.setTypeUnderStage(JsonPath.parse(json).read("$.stagesResponsesArray[2].stage.type"));
                        mongoDBVariables.setTimeStampInLivenessStage(JsonPath.parse(json).read("$.stagesResponsesArray[2].timestamp"));
                        mongoDBVariables.setCounter(3);
                        break;
                    }
                    case 3: {
                        mongoDBVariables.setStatusInLivenessStage(JsonPath.parse(json).read("$.stagesResponsesArray[3].status"));
                        if (mongoDBVariables.getStatusInLivenessStage().equalsIgnoreCase("timeout")) {
                            setVariablesOfTimeout(mongoDBVariables, mongoDBVariables.getCounter());
                        }
                        mongoDBVariables.setActionType(JsonPath.parse(json).read("$.stagesResponsesArray[3].action_type"));
                        mongoDBVariables.setOrder(JsonPath.parse(json).read("$.stagesResponsesArray[3].stage.order"));
                        mongoDBVariables.setTypeUnderStage(JsonPath.parse(json).read("$.stagesResponsesArray[3].stage.type"));
                        mongoDBVariables.setTimeStampInLivenessStage(JsonPath.parse(json).read("$.stagesResponsesArray[3].timestamp"));
                        mongoDBVariables.setCounter(4);
                        break;
                    }
                    case 4: {
                        mongoDBVariables.setStatusInLivenessStage(JsonPath.parse(json).read("$.stagesResponsesArray[4].status"));
                        if (mongoDBVariables.getStatusInLivenessStage().equalsIgnoreCase("timeout")) {
                            setVariablesOfTimeout(mongoDBVariables, mongoDBVariables.getCounter());
                        }
                        mongoDBVariables.setActionType(JsonPath.parse(json).read("$.stagesResponsesArray[4].action_type"));
                        mongoDBVariables.setOrder(JsonPath.parse(json).read("$.stagesResponsesArray[4].stage.order"));
                        mongoDBVariables.setTypeUnderStage(JsonPath.parse(json).read("$.stagesResponsesArray[4].stage.type"));
                        mongoDBVariables.setStageData(JsonPath.parse(json).read("$.stagesResponsesArray[4].payload.otp_number"));
                        mongoDBVariables.setCounter(0);
                        break;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Step("Init mongoDB philippines cheques variables")
    public void philippinesChequesMongoDBVariables(MongoDBVariables mongoDBVariables) {
        try {
            mongoDBVariables.setActionType(JsonPath.parse(json).read("$.stagesResponsesArray[0].action_type"));
            mongoDBVariables.setOrder(JsonPath.parse(json).read("$.stagesResponsesArray[0].stage.order"));
            mongoDBVariables.setTypeUnderStage(JsonPath.parse(json).read("$.stagesResponsesArray[0].stage.type"));
            mongoDBVariables.setChequeNumber(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.fields.cheque_number"));
            mongoDBVariables.setInputImage(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.images.input_image"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Step("Init mongoDB denmark dl variables")
    public void denmarkDlMongoDBVariables(MongoDBVariables mongoDBVariables) {
        try {
            mongoDBVariables.setActionType(JsonPath.parse(json).read("$.stagesResponsesArray[0].action_type"));
            mongoDBVariables.setOrder(JsonPath.parse(json).read("$.stagesResponsesArray[0].stage.order"));
            mongoDBVariables.setTypeUnderStage(JsonPath.parse(json).read("$.stagesResponsesArray[0].stage.type"));

            mongoDBVariables.setCardType(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.card_type"));
            mongoDBVariables.setLastNameEnglish(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.fields.last_name"));
            mongoDBVariables.setFirstNameEnglish(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.fields.first_name"));
            mongoDBVariables.setDateOfBirth(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.fields.date_of_birth"));
            mongoDBVariables.setDateOfIssue(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.fields.date_of_issue"));
            mongoDBVariables.setDateOfExpiry(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.fields.date_of_expiry"));
            mongoDBVariables.setPersonalNumber(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.fields.personal_number"));
            mongoDBVariables.setLicenseNumber(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.fields.license_number"));
            mongoDBVariables.setStageStatus(JsonPath.parse(json).read("$.stagesResponsesArray[0].status"));

            mongoDBVariables.setInputImage(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.images.input_image"));
            mongoDBVariables.setCroppedImage(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.images.cropped_image"));
            mongoDBVariables.setFaceImage(JsonPath.parse(json).read("$.stagesResponsesArray[0].payload.images.face_image"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}




