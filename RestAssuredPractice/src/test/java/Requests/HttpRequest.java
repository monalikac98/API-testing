package Requests;

import org.testng.annotations.Test;
import java.util.HashMap;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class HttpRequest {

    int id;

    @Test (priority = 1)
    void getUsers(){

        when()
                .get("https://reqres.in/api/users?page=2")

        .then()
                .statusCode(200)
                .body("page" ,equalTo(2))
                .log().all();
    }

    @Test(priority = 2)
    void createUser(){

        HashMap map= new HashMap();
        map.put("name" , "Scott");
        map.put("job" , "Engineer");


        id= given()
                .contentType("application/json")
                .body(map)

        .when()
                .post("https://reqres.in/api/users")
                .jsonPath().getInt("id");
//
//        .then()
//                .statusCode(201)
//                .log().all();
    }

    @Test(priority = 3 , dependsOnMethods = {"createUser"})
    void updateUserDetails(){
        HashMap map= new HashMap();
        map.put("name" , "Scott");
        map.put("job" , "Trainer");


        given()
                .contentType("application/json")
                .body(map)

        .when()
                .put("https://reqres.in/api/users/" +id)


        .then()
                .statusCode(200)
                .log().all();
    }

    @Test(priority = 4)
    void deleteUser(){

        when()
                .delete("https://reqres.in/api/users/"+id)
        .then()
                .statusCode(204)
                .log().all();
    }
}
