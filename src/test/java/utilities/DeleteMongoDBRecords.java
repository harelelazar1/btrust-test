package utilities;

import io.qameta.allure.Description;
import org.testng.annotations.Test;

public class DeleteMongoDBRecords extends MongoDBConnection{

    @Test(description = "delete collection values")
    @Description("Delete all values from collection by using delete query")
    public void deleteCollection() {
        deleteMongoCollection("events");
        closeMongoDBConnection();
        System.out.println("Collection has been deleted and connection closed");
    }
}
