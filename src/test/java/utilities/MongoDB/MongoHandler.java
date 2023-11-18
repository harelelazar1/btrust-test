package utilities.MongoDB;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.jayway.jsonpath.JsonPath;
import com.mongodb.*;
import com.mongodb.MongoClient;
import com.mongodb.client.*;
import io.qameta.allure.Step;
import utilities.MongoDB.Variables.*;
import utilities.MongoDB.Variables.Liveness.LivenessHandlers;
import utilities.MongoDB.Variables.Ocr.*;

import java.util.*;

public class MongoHandler {

    protected final String DECODE_PASSWORD = "2L2W_wVr9YLy4js%40L%5E%26Vk%5E%3DTc_d%3D2y%21x";
    protected final String USER_NAME = "scanovate_FimpiZv1dcyG4VOY";
    protected final String PORT = "10.0.1.75:27017";
    protected final String AUTH = "/?authSource=admin&authMechanism=SCRAM-SHA-1";
    protected static MongoCollection collection;
    protected FindIterable find;
    protected MongoCursor cursor;
    String jsonResult = null;
    JsonPath jsonResult2 = null;

    @Step("Open mongo connection")
    public void mongoDBInitiateCollection2(String collectionName) {
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

    @Step("Make search query and set the result to json variable")
    public void makeSearchQuery2(String caseId, String status) {
        try {
            BasicDBObject searchQuery = new BasicDBObject();
            searchQuery.put("caseId", caseId);
            searchQuery.put("status", status);
//            Thread.sleep(3000);
            find = collection.find(searchQuery);
            cursor = find.iterator();
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            while (cursor.hasNext()) {
                jsonResult = ow.writeValueAsString(cursor.next());
                System.out.println("json doc exists 2!!! (:");
            }
            System.out.println("Query2: " + searchQuery);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Step("Make search query and set the result to json variable")
    public int getStagesResponsesArraySize(String caseId, String status) {
        Map morMap = new HashMap();
        morMap.put("caseId", caseId);
        morMap.put("status", status);

        Iterable<DBObject> list = collection.aggregate(Arrays.asList(
                (DBObject) new BasicDBObject("$match", new BasicDBObject("caseId", caseId).append("status", status)),
                (DBObject) new BasicDBObject("$project", new BasicDBObject("size", new BasicDBObject("$size", Arrays.asList("$stagesResponsesArray"))))
        ));
        String dd = null;
        for (Object obj : list) {
            dd = String.valueOf(obj).split(" size=")[1];
        }
        return Integer.parseInt(dd.split("}}")[0]);
    }

    @Step("Make search query and set the result to json variable")
    public static int getHeadPositionChangesArraySize(String caseId, String status, int stagesArrayIndex) {
        Map morMap = new HashMap();
        morMap.put("caseId", caseId);
        morMap.put("status", status);

        Iterable<DBObject> list = collection.aggregate(Arrays.asList(
                (DBObject) new BasicDBObject("$match", new BasicDBObject("caseId", caseId).append("status", status)),
                (DBObject) new BasicDBObject("$project", new BasicDBObject("specific", new BasicDBObject("$arrayElemAt", Arrays.asList("$stagesResponsesArray", stagesArrayIndex)))),
                (DBObject) new BasicDBObject("$project", new BasicDBObject("size", new BasicDBObject("$size", Arrays.asList("$specific.payload.head_position_changes"))))

        ));
        String dd = null;
        for (Object obj : list) {
            dd = String.valueOf(obj).split(" size=")[1];
        }
        return Integer.parseInt(dd.split("}}")[0]);
    }

    @Step("Make search query and set the result to json variable")
    public void getStageInformation(String caseId, String status, int stagesArrayIndex) {
        Map morMap = new HashMap();
        morMap.put("caseId", caseId);
        morMap.put("status", status);

        Iterable<DBObject> list = collection.aggregate(Arrays.asList(
                (DBObject) new BasicDBObject("$match", new BasicDBObject("caseId", caseId).append("status", status)),
                (DBObject) new BasicDBObject("$project", new BasicDBObject("myStage", new BasicDBObject("$arrayElemAt", Arrays.asList("$stagesResponsesArray", stagesArrayIndex))))
        ));
        String[] arr = new String[1];
        for (Object obj : list) {
            jsonResult2.read(obj);
        }
    }

    @Step("Set variables of 'session_start' mongoDB stage")
    public void setSessionStartVariables2(String caseId, String status) {
        try {
            makeSearchQuery2(caseId, status);
            StartSessionVariables.setVariables(jsonResult);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Step("Set variables of complete (Timeout/Failure/Success) mongoDB stage according the library tested")
    public void setCompleteResponse(String caseId, String status) {
        try {
            makeSearchQuery2(caseId, status);
            CommonVariables.setVariables(jsonResult);
            switch (CommonVariables.getServiceType()) {
                case "IL-ID": {
                    ILIDVariables ilidVariables = new ILIDVariables();
                    OcrHandlers.ILIDHandler(jsonResult, getStagesResponsesArraySize(caseId, status));
                    break;
                }
                case "IL-DL": {
                    ILDLVariables ildlVariables = new ILDLVariables();
                    OcrHandlers.ILDLHandler(jsonResult, getStagesResponsesArraySize(caseId, status));
                    break;
                }
                case "MRZ": {
                    MRZVariables mrzVariables = new MRZVariables();
                    MRZVariables.setMRZVariables(jsonResult, getStagesResponsesArraySize(caseId, status) - 1);
                    break;
                }
                case "PHL-CHEQUES": {
                    PHCVariables phcVariables = new PHCVariables();
                    PHCVariables.setVariables(jsonResult, getStagesResponsesArraySize(caseId, status) - 1);
                    break;
                }
                case "DK-DL": {
                    DKDLVariables dkdlVariables = new DKDLVariables();
                    DKDLVariables.setVariables(jsonResult, getStagesResponsesArraySize(caseId, status) - 1);
                    break;
                }
                case "LIVENESS": {
                    LivenessHandlers livenessHandlers = new LivenessHandlers();
                    LivenessHandlers.LivenessHandler(jsonResult, getStagesResponsesArraySize(caseId, status));
                    break;
                }
                case "CNI": {
                    CNIVariables cniVariables = new CNIVariables();
                    OcrHandlers.CNIHandler(jsonResult, getStagesResponsesArraySize(caseId, status));
                    break;
                }
                case "CAPTURE": {
                    CaptureVariables captureVariables = new CaptureVariables();
                    OcrHandlers.CaptureHandler(jsonResult, getStagesResponsesArraySize(caseId, status));
                }
            }
        } catch (Exception e) {
            System.out.println("Error2: " + e.getMessage());
        }
    }

    @Step("Start mongoDB query process by initialize query to the chosen status")
    public void queryHandler(String caseId, String status) {
        if (jsonResult == null) {
            mongoDBInitiateCollection2("events");
        }
        System.out.println("Status is: " + status);
        switch (status) {
            case "session_start": {
                setSessionStartVariables2(caseId, status);
                break;
            }
            case "timeout":
            case "success":
            case "failure": {
                setCompleteResponse(caseId, status);
                break;
            }
        }
    }
}
