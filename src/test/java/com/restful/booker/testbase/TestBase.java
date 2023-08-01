package com.restful.booker.testbase;

import io.restassured.RestAssured;
import org.junit.BeforeClass;

/**
 * Created by Jay Vaghani
 */
public class TestBase {

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://restful-booker.herokuapp.com";
        //RestAssured.port = 3030;
        RestAssured.basePath = "/booking" ;
    }
}
