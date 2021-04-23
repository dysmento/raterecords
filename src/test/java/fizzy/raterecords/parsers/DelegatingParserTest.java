package fizzy.raterecords.parsers;

import fizzy.raterecords.model.Parser;
import fizzy.raterecords.model.RateRecord;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DelegatingParserTest {
    @Test
    void parseFile() throws Exception {
        final Parser parser = new DelegatingParser();
        final File pipeData = new ClassPathResource("pipeDelimited.txt").getFile();
        final ArrayList<RateRecord> rateRecords = new ArrayList<>();
        final Scanner scanner = new Scanner(pipeData);
        while (scanner.hasNextLine()) {
            rateRecords.add(parser.parse(scanner.nextLine()));
        }
        assertEquals(2, rateRecords.size());
    }

    @Test
    void parseThreeFiles() throws Exception {
        final Parser parser = new DelegatingParser();
        final File pipeData = new ClassPathResource("pipeDelimited.txt").getFile();
        final File commaData = new ClassPathResource("commaDelimited.txt").getFile();
        final File spaceData = new ClassPathResource("spaceDelimited.txt").getFile();
        final ArrayList<RateRecord> rateRecords = new ArrayList<>();
        final Scanner pipeScanner = new Scanner(pipeData);
        final Scanner commaScanner = new Scanner(commaData);
        final Scanner spaceScanner = new Scanner(spaceData);
        while (pipeScanner.hasNextLine()) {
            rateRecords.add(parser.parse(pipeScanner.nextLine()));
        }
        while (commaScanner.hasNextLine()) {
            rateRecords.add(parser.parse(commaScanner.nextLine()));
        }
        while (spaceScanner.hasNextLine()) {
            rateRecords.add(parser.parse(spaceScanner.nextLine()));
        }
        assertEquals(6, rateRecords.size());
    }
}
