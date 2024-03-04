package ipInfoTest;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

import static java.util.Objects.nonNull;

public class TestBase {

    @BeforeAll
    public static void setup(){
        RestAssured.baseURI = nonNull(System.getenv("URL"))
                ? System.getenv("URL")
                : "http://ip-api.com/json";
    }
}
