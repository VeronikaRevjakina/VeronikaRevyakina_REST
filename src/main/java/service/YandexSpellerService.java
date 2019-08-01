package service;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static utils.FileUtils.readSpellarURLFromFile;

public class YandexSpellerService {
    private RequestSpecification REQUEST_SPECIFICATION;

    public YandexSpellerService() {
        REQUEST_SPECIFICATION = new RequestSpecBuilder()
                .setBaseUri(getDomain())
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter()).build();
    }

    public static String getDomain(){
        return readSpellarURLFromFile(YandexSpellerConstants.PATH_TO_PROPERTIES)
                .getProperty("domain");
    }

    public Response checkWord(String uri,String word) {
        return given(REQUEST_SPECIFICATION).param("text", word).get(uri);
    }

    public Response checkTextWithParams(String uri, Map<String, Object> params) {
        RequestSpecification specification = given(REQUEST_SPECIFICATION);

        for (Map.Entry<String, Object> param : params.entrySet())
            specification.param(param.getKey(), param.getValue());

        return specification.get(uri);
    }

}
