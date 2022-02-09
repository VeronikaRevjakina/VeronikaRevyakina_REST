package speller.low_level;

import enums.Languages;
import enums.Options;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import service.YandexSpellerConstants;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static service.YandexSpellerService.getDomain;


public class CheckSpellerStatusCodeAndPerformance {

    private final String WORD = "cucamber";
    private final String CORRECTFORM1 = "cucumber";
    private final String CORRECTFORM2 = "cucumbers";
    private final String CORRECTFORM3 = "cucomber";

    private final String DOMAIN = "domain";
    private final String CHECKTEXT_ENDPOINT = DOMAIN +
            YandexSpellerConstants.CHECK_TEXT_URI;

    private RequestSpecification REQUEST_SPECIFICATION;

    @BeforeMethod
    public void setup() {
        REQUEST_SPECIFICATION = new RequestSpecBuilder()
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter()).build();
    }

    @AfterMethod
    public void teardown() {
    }


    @Test(description = "statusCodes")
    public void checkStatusCodeSuccess() {
        RestAssured.given(REQUEST_SPECIFICATION)
                .get(CHECKTEXT_ENDPOINT)
                .then().statusCode(HttpStatus.SC_OK);

    }

    @Test(description = "Check correction of word")
    public void checkWordCorrection() {
        RestAssured.given(REQUEST_SPECIFICATION)
                .param(YandexSpellerConstants.PARAM_LANGUAGES, Languages.EN)
                .param(YandexSpellerConstants.PARAM_OPTIONS, Options.DEFAULT)
                .param(YandexSpellerConstants.PARAM_TEXTS, WORD)
                .then().statusCode(HttpStatus.SC_OK)
                .and()
                .body("s[0]", is(asList(CORRECTFORM1, CORRECTFORM2, CORRECTFORM3)))
                .body("s[0]", not(WORD))
                .header("Content-Type", "application/json; charset=utf-8");
    }

    @Test
    void yandexSpellerPositiveTest() {
        String body =
                "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:spel=\"http://speller.yandex.net/services/spellservice\">\n" +
                        "   <soapenv:Header/>\n" +
                        "   <soapenv:Body>\n" +
                        "      <spel:CheckTextRequest lang=\"?\" options=\"0\" format=\"\">\n" +
                        "         <spel:text>lilt</spel:text>\n" +
                        "      </spel:CheckTextRequest>\n" +
                        "   </soapenv:Body>\n" +
                        "</soapenv:Envelope>";

        RestAssured
                .given().body(body)
                .when().post("http://speller.yandex.net/services/spellservice")
                .then().statusCode(200);
    }

}
