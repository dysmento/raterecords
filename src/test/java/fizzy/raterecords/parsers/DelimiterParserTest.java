package fizzy.raterecords.parsers;

import fizzy.raterecords.model.Gender;
import fizzy.raterecords.model.RateRecord;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DelimiterParserTest {

    @Test
    void parseCommas() {
        String data = "Lincoln, Abraham, male, brown, 1809-02-12";
        final DelimiterParser commaParser = new DelimiterParser(",");
        final RateRecord lincoln = commaParser.parse(data);
        assertEquals(canonicalLincoln(), lincoln);
    }

    @Test
    void parsePipes() {
        String data = "Lincoln | Abraham | male | brown | 1809-02-12";
        final DelimiterParser commaParser = new DelimiterParser("\\|");
        final RateRecord lincoln = commaParser.parse(data);
        assertEquals(canonicalLincoln(), lincoln);
    }

    @Test
    void parseSpaces() {
        String data = "Lincoln Abraham male brown 1809-02-12";
        final DelimiterParser commaParser = new DelimiterParser(" ");
        final RateRecord lincoln = commaParser.parse(data);
        assertEquals(canonicalLincoln(), lincoln);
    }

    private RateRecord canonicalLincoln() {
        return new RateRecord.Builder()
                .withDateOfBirth(LocalDate.of(1809, 2, 12))
                .withGender(Gender.MALE)
                .withLastName("Lincoln")
                .withFirstName("Abraham")
                .withFavoriteColor("brown")
                .build();
    }
}
