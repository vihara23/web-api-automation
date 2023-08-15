package com.vihara.webapiautomation.API.service;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.extern.java.Log;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

@Log
public class BookingService {
    private String baseURI;

    public BookingService() {
        this.baseURI = "https://restful-booker.herokuapp.com";
        ;
    }

    @Step("Send POST Request")
    public Response post(String relativeURI, HashMap<String,String> headers, String body) {
        try {
            set(baseURI, relativeURI);

            Response response =
                    given()
                            .headers(headers)
                            .body(body)
                            .when()
                            .post();
            reset();

            return response;

        }catch (Exception e){
            log.info("post() Failed!!");
            throw e;
        }
    }

    @Step("Set Base URI: {URI}/{path}")
    private void set(String URI, String path) {
        RestAssured.baseURI = URI;
        RestAssured.basePath = path;
    }

    private void reset() {
        RestAssured.baseURI = null;
        RestAssured.basePath = null;
    }
}
