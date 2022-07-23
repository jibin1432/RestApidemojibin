package ApiTests.Service;

import ApiTests.Models.User;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;

public class UserServie {

    private RequestSpecification requestSpecification;

    public UserServie() {
        requestSpecification= new RequestSpecBuilder()
                .log(LogDetail.ALL)
                .setBaseUri(Endpoints.BASE_URL)
                .setBasePath(Endpoints.User_Service)
                .setContentType(ContentType.JSON)
                .build();
    }


    public int AddNewUser(User user) {

        Response response=  RestAssured
                .given()
                .spec(requestSpecification)
                .body(user)
                .when()
                .post()
                .then()
                .log().all()
                .assertThat().statusCode(HttpStatus.SC_OK)
                .extract().response();
        int Id = response.jsonPath().getInt("id");

        return(Id);
    }

    public void GetAllUsers() {
        List<User> actUser= Arrays.asList( RestAssured
                .given()
                .spec(requestSpecification)
                .when()
                .get()
                .then()
                .log().all()
                .assertThat().statusCode(HttpStatus.SC_OK)
                .extract().body().as(User[].class));


    }

    public void GetSingleUser(int id) {
        Response response=  RestAssured
                .given()
                .spec(requestSpecification)

                .when()
                .get("/"+id)
                .then()
                .log().all()
                .assertThat().statusCode(HttpStatus.SC_OK)
                .extract().response();
        int Id = response.jsonPath().getInt("id");
        Assert.assertEquals(Id,id);
        System.out.println(Id);
    }

    public void UpdateUserName(int id, User user) {
        User updatedUserName=  RestAssured
                .given()
                .spec(requestSpecification)
                .body(user)
                .when()
                .put("/"+id)
                .then()
                .log().all()
                .assertThat().statusCode(HttpStatus.SC_OK)
                .extract().body().as(User.class);
        Assert.assertEquals(updatedUserName,user);
    }

    public void DeleteUser(int id) {
        RestAssured
                .given()
                .spec(requestSpecification)
                .when()
                .delete("/"+id)
                .then()
                .log().all()
                .assertThat().statusCode(HttpStatus.SC_OK);
    }

}
