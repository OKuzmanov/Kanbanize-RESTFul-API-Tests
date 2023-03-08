package com.kanbanize.tests;

import io.github.cdimascio.dotenv.Dotenv;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.commons.text.StringSubstitutor;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeTest;

import java.util.HashMap;
import java.util.Map;

public class BaseTest {
    private String baseUrl = "https://${subdomain}.kanbanize.com/api/v2";

    @BeforeTest
    public void setup() {
        Dotenv dotenv = Dotenv.load();
        String apiKey = dotenv.get("API_KEY");
        String subdomain = dotenv.get("SUBDOMAIN");

        Map<String, String> valuesMap = new HashMap<>();
        valuesMap.put("subdomain", subdomain);

        StringSubstitutor sub = new StringSubstitutor(valuesMap);

        RequestSpecification customReqSpec = new RequestSpecBuilder()
                .setBaseUri(sub.replace(baseUrl))
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
