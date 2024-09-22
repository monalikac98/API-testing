package WaysToCreatePostRequetBody;

import org.testng.annotations.Test;
import java.util.HashMap;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

//1. Hashmap
//2. Using org.json
//3. using POJO Class
//4. external JSON file

public class BodyUsingHashMap {

    String id ="";

    @Test(priority = 1)
    void usingHashMap(){
        HashMap map = new HashMap();
        map.put("name", "Diana");
        map.put("location", "Canada");
        map.put("phone", "123456");

        String courseArr[] = {"Java", "Testing"};
        map.put("courses", courseArr);

        given()
                .contentType("application/json")
                .body(map)

        .when()
                .post("http://localhost:3000/students")

        .then()
                .statusCode(201)
                .body("name", equalTo("Diana"))
                .body("location",equalTo("Canada"))
                .body("phone",equalTo( "123456"))
                .body("courses[0]",equalTo("Java"))
                .body("courses[1]",equalTo("Testing"))

                //verify header
                .header("Content-Type", "application/json")
                .log().all();
    }

    @Test(priority = 2)
    void delete(){

        when()
                .delete("http://localhost:3000/students/" +id)

        .then()
                .statusCode(200);
    }
}
