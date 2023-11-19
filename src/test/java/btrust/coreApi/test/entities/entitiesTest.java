package btrust.coreApi.test.entities;

import btrust.coreApi.gatewayApi;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class entitiesTest {
    int newEntityId;

    @Test
    public void createEntity() {
        gatewayApi gw = new gatewayApi();
        String operationID = "create_entity_with_data_api_v3_entities_create_with_data_post";
        String requestBody = "{\"contacts\":[{\"callbackReferent\":true,\"contact\":{\"ccEmails\":\"string\",\"email\":\"aa@aa.com\",\"externalId\":\"string\",\"fax\":\"string\",\"firstName\":\"automation\",\"lastName\":\"test\",\"phone\":\"string\",\"position\":\"string\",\"secondPhone\":\"string\"}}],\"entityTypeId\":4,\"fields\":[{\"mapperId\":96,\"value\":\"client_type1\"},{\"mapperId\":97,\"value\":\"02/07/2023\"},{\"mapperId\":112,\"value\":\"office1\"}]}";
        JsonPath jp = gw.gatewaySwaggerCall(operationID, null, null, requestBody);
        Assert.assertTrue(jp.getMap("$").containsKey("id"), "Key 'id' not found in JSON");
        Assert.assertTrue(jp.getMap("$").containsKey("entityName"), "Key 'entityName' not found in JSON");
        Assert.assertTrue(jp.getMap("$").containsKey("contacts"), "Key 'contacts' not found in JSON");
        newEntityId=jp.getInt("id");
        LinkedHashMap contact = (LinkedHashMap) jp.getList("contacts").toArray()[0];;
        Assert.assertEquals(contact.get("firstName"),"automation");
        Assert.assertEquals(contact.get("lastName"),"test");
    }

    @Test
    public void getEntityFields() {
        Assert.assertTrue(newEntityId>0,"new entity id not found");
        gatewayApi gw = new gatewayApi();
        String operationID = "get_entity_fields_data_api_v3_entities__id__fields_get";
        Map<String, String> pathParams = new HashMap<>();
        pathParams.put("id", String.valueOf(newEntityId));
        JsonPath jp = gw.gatewaySwaggerCall(operationID, pathParams, null, null);
        LinkedHashMap firstElement = (LinkedHashMap) jp.getList("$").toArray()[0];
        Assert.assertEquals(firstElement.get("key"), "onboarding__office");
        Assert.assertEquals(firstElement.get("value"), "Germany");
    }
}
