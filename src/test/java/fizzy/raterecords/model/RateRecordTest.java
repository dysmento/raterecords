package fizzy.raterecords.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RateRecordTest {

    @Test
    void checkStringFormatting() {
        final RateRecord marty = new RateRecord.Builder()
                .withLastName("McFly")
                .withFirstName("Marty")
                .withGender(Gender.MALE)
                .withFavoriteColor("orange")
                .withDateOfBirth(LocalDate.of(1968, 6, 12))
                .build();

        assertEquals("name:McFly, Marty gender:MALE color:orange DOB:6/12/1968", marty.toString());
    }
}
