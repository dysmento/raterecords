package fizzy.raterecords;

import fizzy.raterecords.model.Gender;
import fizzy.raterecords.model.RateRecord;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.mapper.TypeRef;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @Test
    void getByName() {
        get("/records/name")
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    void getByGender() {
        get("/records/gender")
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    void testAddRecords() {
        // first we add a new record
        final String randomName = UUID.randomUUID().toString();
        given()
            .contentType(ContentType.TEXT)
            .and()
            .body(randomName + ",Peggy,FEMALE,green,1955-06-05")
        .when().post("/records")
        .then().body(containsString("success")).statusCode(201);

        // get the records back and verify that the one we added is there
        final List<RateRecord> results = get("/records/name").as(new TypeRef<>() {});
        final RateRecord testRecord = new RateRecord.Builder()
                .withDateOfBirth(LocalDate.of(1955, 6, 5))
                .withLastName(randomName)
                .withFirstName("Peggy")
                .withGender(Gender.FEMALE)
                .withFavoriteColor("green")
                .build();

        assert (results.contains(testRecord));
    }

    @Test
    void verifySorting() {
        given()
                .contentType(ContentType.TEXT)
                .and()
                .body(UUID.randomUUID().toString() + ",Peggy,FEMALE,green,1955-06-05")
                .when().post("/records")
                .then().body(containsString("success")).statusCode(201);
        given()
                .contentType(ContentType.TEXT)
                .and()
                .body(UUID.randomUUID().toString() + ",Peggy,FEMALE,green,1900-01-01")
                .when().post("/records")
                .then().body(containsString("success")).statusCode(201);
        given()
                .contentType(ContentType.TEXT)
                .and()
                .body(UUID.randomUUID().toString() + ",Peggy,FEMALE,green,2001-06-05")
                .when().post("/records")
                .then().body(containsString("success")).statusCode(201);

        // the earliest birth date must come first
        final List<RateRecord> results = get("/records/birthdate").as(new TypeRef<>() {});
        assertEquals(LocalDate.of(1900, 1, 1), results.get(0).getDateOfBirth());
    }

}
/**/