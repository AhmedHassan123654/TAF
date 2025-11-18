package com.automationexercise.apis;

import java.util.Map;

import com.automationexercise.utils.dataReader.PropertyReader;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Builder {
    private static String baseurl = PropertyReader.getProperty("baseURLapi");

    private Builder() {


    }

    public static RequestSpecification getUserMangamentRequestSpecification(Map<String, ?> params) {
        return new RequestSpecBuilder()
                .setBaseUri(baseurl)
                .setContentType(ContentType.URLENC)
                .addFormParams(params)
                .build();

    }
}