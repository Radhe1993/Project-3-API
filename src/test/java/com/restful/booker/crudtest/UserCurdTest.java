package com.restful.booker.crudtest;

import com.restful.booker.model.AuthPojo;
import com.restful.booker.model.BookingDates;
import com.restful.booker.model.PatchPojo;
import com.restful.booker.model.RestPojo;
import com.restful.booker.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class UserCurdTest  extends TestBase {
    Response response;

    String token = "2ff71fd9ed8853a";

    @Test
    public void verifyUserCreated() {


        RestPojo restPojo = new RestPojo();
        restPojo.setFirstname("Sita");
        restPojo.setLastname("Ram");
        restPojo.setTotalprice("5000");
        restPojo.setDepositpaid("600");
        BookingDates bd = new BookingDates();
        bd.setCheckin("11-12-2023");
        bd.setCheckout("11-12-2023");
        restPojo.setBookingdates(bd);
        restPojo.setAdditionalneeds("B & B");
        Response response = given()
                .headers("Content-Type", "application/json", "Authorization", "Bearer " + token)
                .when()
                .body(restPojo)
                .post();
        //To fetch response from server
        String responseBody = response.getBody().asString();
        response.then().log().all().statusCode(200);
        JsonPath jsonPath = new JsonPath(responseBody);
        String bookingId = jsonPath.getString("bookingid");
        System.out.println("bookingid " + bookingId);


    }

    @Test
    public void verifyUserAuth() {
        AuthPojo authPojo = new AuthPojo();
        authPojo.setUsername("siyaram@gmail.com");
        authPojo.setPassword("siyaram11");

        Response response = given()
                .basePath("/auth")
                .headers("Content-Type", "application/json")
                .when()
                .body(authPojo)
                .post();

        String responseBody = response.getBody().asString();
        response.then().log().all().statusCode(200);

    }

    @Test
    public void getAllBookingId() {
        Response response = given()
                .basePath("/booking")
                .headers("Content-Type", "application/json")
                .when()
                .get();

        response.then().log().all().statusCode(200);
        response.prettyPrint();

    }

    @Test
    public void verifyUserUpdateBookingSuccessfully() {

        RestPojo restPojo = new RestPojo();
        restPojo.setFirstname("Krishna");
        restPojo.setLastname("Radha");
        restPojo.setTotalprice("9000");
        restPojo.setDepositpaid("Yes");
        BookingDates bd = new BookingDates();
        bd.setCheckin("1-10-2023");
        bd.setCheckout("10-12-2023");
        restPojo.setBookingdates(bd);
        restPojo.setAdditionalneeds("B & B");
        Response response = given()
                .basePath(RestAssured.basePath + "/2368")
                .headers("Content-Type", "application/json", "Authorization", "Basic " + "YWRtaW46cGFzc3dvcmQxMjM=")
                .when()
                .body(restPojo)
                .put();

        response.prettyPrint();
        response.then().statusCode(200);

        response.then().log().all().statusCode(200);
        String responseBody = response.getBody().asString();
        JsonPath jsonPath = new JsonPath(responseBody);

    }

    @Test
    public void verifyUserUpdateSingleBookingSuccessfully() {
        PatchPojo patchPojo = new PatchPojo();
        patchPojo.setFirstname("Sita");
        patchPojo.setLastname("Ram");
        Response response = given()
                .basePath(RestAssured.basePath + "/1")
                .headers("Content-Type", "application/json", "Authorization", "Basic " + "YWRtaW46cGFzc3dvcmQxMjM=")
                .when()
                .body(patchPojo)
                .patch();

        response.prettyPrint();
        response.then().statusCode(200);

        response.then().log().all().statusCode(200);
        String responseBody = response.getBody().asString();
        JsonPath jsonPath = new JsonPath(responseBody);
    }

    @Test
    public void VerifyUserDeleteBookingSuccessfully() {
        //   PostsPojo postsPojo = new PostsPojo();
        Response response = given()
                .headers("Content-Type", "application/json", "Authorization", "Basic " + "YWRtaW46cGFzc3dvcmQxMjM=")
                .basePath(RestAssured.basePath + "/1")
                .when()
                .body("")
                .delete();

        response.prettyPrint();
        response.then().statusCode(201);


    }
}