package WaysToCreatePostRequetBody;

import org.json.JSONObject;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class BodyUsingJsonLibrary {


    @Test(priority = 1)
    void usingOrgJSON() {

        JSONObject obj = new JSONObject();
        obj.put("name", "Dan");
        obj.put("location", "France");
        obj.put("phone", "67890");

        String courseArr[] = {".Net", "Automation"};
        obj.put("courses", courseArr);

        given()
                .contentType("application/json")
                .body(obj.toString())

        .when()
                .post("http://localhost:3000/students")

        .then()
                .statusCode(201)
                .body("name", equalTo("Dan"))
                .body("location",equalTo("France"))
                .body("phone",equalTo( "67890"))
                .body("courses[0]",equalTo(".Net"))
                .body("courses[1]",equalTo("Automation"))

                //verify header
                .header("Content-Type", "application/json")
                .log().all();
    }
//
//    @Test(priority = 2)
//    void delete(){
//
//        when()
//                .delete("http://localhost:3000/students/" +id)
//
//                .then()
//                .statusCode(200);
//    }
}
