package com.kanbanize.tests;

import com.kanbanize.pojos.CardPojo;
import com.kanbanize.utils.JsonPayload;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Random;

import static io.restassured.RestAssured.given;

public class BaseTest {
    private String subdomain = "nocompanyyet";
    private String apiKey = "9mU3VZAgXJkGoHNwwjos43t3FVMT2YaVlAXZ3EhL";
    private final String BASE_URL = "https://" + subdomain + ".kanbanize.com/api/v2";

    @BeforeTest
    public void setup() {
        RequestSpecification customReqSpec = new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .addHeader("apiKey", apiKey)
                .addHeader("Content-Type", "application/json")
                .log(LogDetail.ALL)
                .setRelaxedHTTPSValidation()
                .build();

        ResponseSpecification customRespSpec = new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .expectResponseTime(Matchers.lessThan(20000L))
                .build();

        RestAssured.requestSpecification = customReqSpec;
        RestAssured.responseSpecification = customRespSpec;
    }
}
