package fizzy.raterecords.cli;

import fizzy.raterecords.model.Parser;
import fizzy.raterecords.model.RateRecord;
import fizzy.raterecords.parsers.DelegatingParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Parser parser = new DelegatingParser();

    public static void main(String[] args) throws FileNotFoundException {

        if (args.length < 3) {
            System.out.println("Usage: file1 file2 file3");
            System.exit(1);
        } else {
            // Input
            final List<RateRecord> rateRecords = new ArrayList<>();
            final Scanner fileScanner1 = new Scanner(new File(args[0]));
            final Scanner fileScanner2 = new Scanner(new File(args[1]));
            final Scanner fileScanner3 = new Scanner(new File(args[2]));
            rateRecords.addAll(loadMulti(fileScanner1, fileScanner2, fileScanner3));

            // Output
            rateRecords.sort(Comparator.comparing(RateRecord::getGender).thenComparing(RateRecord::getLastName));
            rateRecords.forEach(System.out::println);
            rateRecords.sort(Comparator.comparing(RateRecord::getDateOfBirth));
            rateRecords.forEach(System.out::println);
            rateRecords.sort(Comparator.comparing(RateRecord::getLastName).reversed());
            rateRecords.forEach(System.out::println);
        }
    }

    private static List<RateRecord> loadMulti(Scanner... scanners) {
        List<RateRecord> rv = new ArrayList<>();
        for (Scanner s : scanners) {
            while(s.hasNextLine()) {
                rv.add(parser.parse(s.nextLine()));
            }
        }
        return rv;
    }
}
