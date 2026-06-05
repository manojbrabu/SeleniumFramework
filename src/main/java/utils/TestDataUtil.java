package utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestDataUtil {

    public static List<JsonNode> getExecutableTestData(String testCaseId) throws Exception {
        String apiURL = "/testcases/" + testCaseId;
        //System.out.println(apiURL);
        Response response = RestAssured
                .given()
                .baseUri("http://localhost:3000")
                .when()
                .get(apiURL)
                .then()
                .statusCode(200)
                .extract()
                .response();

         ObjectMapper mapper = new ObjectMapper();
        JsonNode rootArray = mapper.readTree(response.asString());
        JsonNode dataset = rootArray.get("datasets");
        List<JsonNode> executableData = new ArrayList<>();

         for (JsonNode node : dataset) {
            if (node.has("execution") && node.get("execution").asBoolean()) {
                executableData.add(node);
            }

        }

        return executableData;
    }
    //npx json-server --watch db.json
    @DataProvider(name = "apiData")
    public Object[][] getData(ITestContext context) throws Exception {
        String testcaseID = context.getCurrentXmlTest().getParameter("testcaseid");
        List<JsonNode> dataList = TestDataUtil.getExecutableTestData(testcaseID);

        Object[][] data = new Object[dataList.size()][1];

        for (int i = 0; i < dataList.size(); i++) {
            data[i][0] = dataList.get(i);
        }
        return data;
    }


}
