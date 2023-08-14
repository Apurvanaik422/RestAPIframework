package stepDefenitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;
import resourses.ApiResource;
import resourses.TestData;
import resourses.Utils;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.responseSpecification;
import static org.junit.Assert.assertEquals;

public class Steps extends Utils {

    ResponseSpecification resp;
    RequestSpecification request;
    Response AddplacaApiresponse;
    static String place_id;
    TestData testData =new TestData();

    @Given("AddPlace API payload with {string} {string} {string}")
    public void add_place_api_payload_with(String name, String address, String language) throws IOException {
      request = given().log().all().spec(requestSpecification())
                .body(testData.AddPlacePayload(name,address,language));

    }
    @When("user calls the {string} API with {string} http request")
    public void user_calls_the_api_with_http_request(String resource, String method) {
        ApiResource apiResource =ApiResource.valueOf(resource);
        String resourceval =apiResource.getResource();

        if(method.equalsIgnoreCase("POST"))
        AddplacaApiresponse = request.post(resourceval);
        else if(method.equalsIgnoreCase("GET"))
        AddplacaApiresponse = request.get(resourceval);
        else if(method.equalsIgnoreCase("DELETE"))
        AddplacaApiresponse = request.delete(resourceval);
        else if(method.equalsIgnoreCase("PUT"))
            AddplacaApiresponse = request.put(resourceval);


    }
    @Then("API call got success with status code {int}")
    public void api_call_got_success_with_status_code(int ExpectedStatusCode) {
        AddplacaApiresponse.then().log().all().spec(responseSpecification()).header("Server", "Apache/2.4.52 (Ubuntu)").extract().response();
      assertEquals(AddplacaApiresponse.statusCode(),ExpectedStatusCode);


    }
    @Then("{string} in response body is {string}")
    public void in_response_body_is(String key, String expectedValue) {
       String keyValue =getvaluebyJsonPath(AddplacaApiresponse,key);
       assertEquals(keyValue,expectedValue);
    }

    @Then("verify place_id created maps to the {string} using {string}")
    public void verify_place_id_created_maps_to_the_using(String expectedName, String resource) throws IOException {
        place_id =getvaluebyJsonPath(AddplacaApiresponse,"place_id");

        request =given().log().all().spec(requestSpecification()).queryParam("place_id",place_id);
        user_calls_the_api_with_http_request(resource,"GET");
        AddplacaApiresponse.then().log().all().spec(responseSpecification()).header("Server", "Apache/2.4.52 (Ubuntu)");
        String actualName =getvaluebyJsonPath(AddplacaApiresponse,"name");
        assertEquals(actualName,expectedName);
    }


    @Given("DeletPlace API payload")
    public void delet_place_api_payload() throws IOException {
       request = given().log().all().spec(requestSpecification()).body(testData.deletePlacePayload(place_id));

    }

}
