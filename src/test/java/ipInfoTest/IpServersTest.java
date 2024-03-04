package ipInfoTest;

import DTO.IpInfoDTO;
import apiClient.APIClient;
import io.qameta.allure.junit5.AllureJunit5;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Stream;

@ExtendWith(AllureJunit5.class)
public class IpServersTest {
    private static final Logger logger = LoggerFactory.getLogger(APIClient.class);
    protected APIClient apiClient = new APIClient(logger);

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://ip-api.com/json";
    }

    static Stream<String> serversDomains() {
        return Stream.of(
                "0.ua.pool.ntp.org",
                "1.ua.pool.ntp.org",
                "2.ua.pool.ntp.org",
                "3.ua.pool.ntp.org",
                "4.ua.pool.ntp.org"
        );
    }

    @ParameterizedTest
    @MethodSource("serversDomains")
    public void test(String domain) {
        Response response = null;
        SoftAssertions softAssertions = new SoftAssertions();
        response = apiClient.getRequest(domain);
        IpInfoDTO responseDTO = response.getBody().as(IpInfoDTO.class);
        softAssertions.assertThat(responseDTO.getStatus())
                .as("Current status is " + responseDTO.getStatus())
                .isEqualTo("success");
        softAssertions.assertThat(responseDTO.getCountry())
                .as("Current country is " + responseDTO.getCountry())
                .isEqualTo("Ukraine");
        softAssertions.assertAll();
    }
}

