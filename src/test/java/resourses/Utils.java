package resourses;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.*;
import java.util.Properties;

public class Utils {
    public static RequestSpecification req;
    ResponseSpecification resp;

    public RequestSpecification requestSpecification() throws IOException {

        if(req ==null) {

            PrintStream stream = new PrintStream(new FileOutputStream("logging.txt"));
            req = new RequestSpecBuilder().setBaseUri(Utils.getPropertyValue("baseurl_QA")).addQueryParam("key", "qaclick123")
                    .setContentType(ContentType.JSON).addFilter(RequestLoggingFilter.logRequestTo(stream))
                    .addFilter(ResponseLoggingFilter.logResponseTo(stream))
                    .build();
        }
        return req;

    }

    public ResponseSpecification responseSpecification(){
        resp = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        return resp;
    }

    public static  String  getPropertyValue(String key) throws IOException {
        Properties prop =new Properties();
        FileInputStream fis= new FileInputStream("C:\\Users\\91762\\IdeaProjects\\RestAPIFramework\\src\\test\\java\\resourses\\global.properties");
        prop.load(fis);
        return prop.getProperty(key);
    }

    public static String getvaluebyJsonPath(Response response, String key){
        JsonPath js =new JsonPath(response.asString());
        return js.get(key).toString();
    }
}
