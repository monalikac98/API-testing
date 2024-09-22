package WaysToCreatePostRequetBody;

import WaysToCreatePostRequetBody.POJO.POJO_PostRequest;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class BodyUsingPOJO {

    @Test(priority = 1)
    void usingPOJO() {

        POJO_PostRequest pojo = new POJO_PostRequest();
        pojo.setName("Dan");
        pojo.setLocation("UK");
        pojo.setPhone("98765");

        String courseArr[] = {"Python", "Java"};
        pojo.setCourses(courseArr);

        given()
                .contentType("application/json")
                .body(pojo)

                .when()
                .post("http://localhost:3000/students")

                .then()
                .statusCode(201)
                .body("name", equalTo("Dan"))
                .body("location",equalTo("UK"))
                .body("phone",equalTo( "98765"))
                .body("courses[0]",equalTo("Python"))
                .body("courses[1]",equalTo("Java"))

                //verify header
                .header("Content-Type", "application/json")
                .log().all();
    }
}
