package fizzy.raterecords.parsers;

import fizzy.raterecords.model.Parser;
import fizzy.raterecords.model.RateRecord;

public class DelegatingParser implements Parser {
    private final DelimiterParser pipeParser = new DelimiterParser("\\|");
    private final DelimiterParser commaParser = new DelimiterParser(",");
    private final DelimiterParser spaceParser = new DelimiterParser(" ");

    @Override
    public RateRecord parse(String data) {
        if (data.contains(",")) {
            return commaParser.parse(data);
        } else if (data.contains("|")) {
            return pipeParser.parse(data);
        } else if (data.contains(" ")) {
            return spaceParser.parse(data);
        } else {
            throw new IllegalArgumentException("Unrecognized data format.");
        }
    }
}
