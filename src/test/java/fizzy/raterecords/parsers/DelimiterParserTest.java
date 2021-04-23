package fizzy.raterecords.parsers;

import fizzy.raterecords.model.Gender;
import fizzy.raterecords.model.RateRecord;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

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
        final DelimiterParser parser = new DelimiterParser("\\|");
        final RateRecord lincoln = parser.parse(data);
        assertEquals(canonicalLincoln(), lincoln);
    }

    @Test
    void parseSpaces() {
        String data = "Lincoln Abraham male brown 1809-02-12";
        final DelimiterParser parser = new DelimiterParser(" ");
        final RateRecord lincoln = parser.parse(data);
        assertEquals(canonicalLincoln(), lincoln);
    }

    @Test
    void parseFile() throws Exception {
        final DelimiterParser parser = new DelimiterParser("\\|");
        final File pipeData = new ClassPathResource("pipeDelimited.txt").getFile();
        final ArrayList<RateRecord> rateRecords = new ArrayList<>();
        final Scanner scanner = new Scanner(pipeData);
        while (scanner.hasNextLine()) {
            rateRecords.add(parser.parse(scanner.nextLine()));
        }
        assertEquals(2, rateRecords.size());
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
