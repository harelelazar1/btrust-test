package btrust.coreApi;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.Assert;

import java.net.URL;
import java.util.Iterator;
import java.util.Map;

public class gatewayApi {
    public JsonPath gatewaySwaggerCall(String operationId, Map<String, String> pathParams, Map<String, String> queryParams, String requestBody) {
        Response response = null;
        Assert.assertNotNull(System.getProperty("ACCESS_TOKEN"), "Token not found");
        String callPath = null;
        String callMethod = null;
        String swaggerDef = System.getProperty("SWAGGER_DEF");
        JSONObject swaggerJson = new JSONObject((swaggerDef));
        JSONObject swaggerPathsJson = swaggerJson.getJSONObject("paths");
        Iterator<String> pathsNames = swaggerPathsJson.keys();
        while (pathsNames.hasNext()) {
            String pathName = pathsNames.next();
            JSONObject pathMainJson = swaggerPathsJson.getJSONObject(pathName);
            Iterator<String> pathMainKeys = pathMainJson.keys();
            while (pathMainKeys.hasNext()) {
                String pathMainKey = pathMainKeys.next();
                JSONObject pathJson = pathMainJson.getJSONObject(pathMainKey);
                if (pathJson.getString("operationId").equalsIgnoreCase(operationId)) {
                    System.out.println("Operation id:" + pathJson.get("operationId") + "||Path: " + pathName + "||Method:" + pathMainKey);
                    callMethod = pathMainKey;
                    callPath = pathName;
                }
            }
        }
        Assert.assertNotNull(callMethod, "operation id not found in swagger");
        Assert.assertNotNull(callPath, "operation id not found in swagger");
        RequestSpecification gatewayRequest = RestAssured.given();
        gatewayRequest.baseUri(System.getProperty("SWAGGER_BASE_URL"));
        gatewayRequest.header("authorization",System.getProperty("ACCESS_TOKEN"));
        if (queryParams != null) {
            gatewayRequest.queryParams(queryParams);
        }
        if (pathParams != null) {
            gatewayRequest.pathParams(pathParams);
        }
        if (requestBody != null) {
            gatewayRequest.body(requestBody);
        }
        if (callMethod.equals("get")) {
            response = gatewayRequest.get(callPath);
        }
        if (callMethod.equals("post")) {
            gatewayRequest.header("Content-Type","application/json");
            response = gatewayRequest.post(callPath);
        }

        Assert.assertEquals(response.statusCode(),200,"gateway API request failed,status:"+response.statusCode());
        return response.jsonPath();
    }
}