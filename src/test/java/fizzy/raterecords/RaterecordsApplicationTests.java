package fizzy.raterecords;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.get;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RaterecordsApplicationTests {
    @Value(value = "${local.server.port}")
    protected int serverPort;

    @BeforeEach
    public void init() {
        RestAssured.port = serverPort;
    }


    @Test
    void contextLoads() {
    }

    @Test
    void getByBirthDate() {
        get("/records/birthdate")
                .then()
                .assertThat()
                .statusCode(200);
    }

}
/**/