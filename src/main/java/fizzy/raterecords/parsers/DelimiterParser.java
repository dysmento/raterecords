package fizzy.raterecords.parsers;

import fizzy.raterecords.model.Gender;
import fizzy.raterecords.model.Parser;
import fizzy.raterecords.model.RateRecord;

import java.time.LocalDate;

public class DelimiterParser implements Parser {
    private String delim;

    public DelimiterParser(String delim) {
        this.delim = delim;
    }

    @Override
    public RateRecord parse(String data) {
        final String[] fields = data.trim().split(delim);
        return new RateRecord.Builder()
                .withLastName(fields[0].trim())
                .withFirstName(fields[1].trim())
                .withGender(Gender.valueOf(fields[2].trim().toUpperCase()))
                .withFavoriteColor(fields[3].trim())
                .withDateOfBirth(LocalDate.parse(fields[4].trim()))
                .build();
    }
}
