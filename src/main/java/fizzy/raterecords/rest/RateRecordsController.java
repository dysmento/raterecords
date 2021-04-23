package fizzy.raterecords.rest;

import fizzy.raterecords.model.Parser;
import fizzy.raterecords.model.RateRecord;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RateRecordsController {
    final List<RateRecord> rateRecords = new ArrayList<>();
    private final Parser parser;

    RateRecordsController(Parser parser) {
        this.parser = parser;
    }

    @PostMapping(value="/records", consumes = "text/plain")
    ResponseEntity<String> addRecord(@RequestBody String data) {
        rateRecords.add(parser.parse(data));
        return ResponseEntity.created(URI.create("/records/name")).body("success");
    }

    @GetMapping("/records/name")
    ResponseEntity<List<RateRecord>> getByName() {
        return sortedResponse(Comparator.comparing(RateRecord::getLastName).thenComparing(RateRecord::getFirstName));
    }

    @GetMapping("/records/gender")
    ResponseEntity<List<RateRecord>> getByGender() {
        return sortedResponse(Comparator.comparing(RateRecord::getGender));
    }

    @GetMapping("/records/birthdate")
    ResponseEntity<List<RateRecord>> getByBirthdate() {
        return sortedResponse(Comparator.comparing(RateRecord::getDateOfBirth));
    }

    ResponseEntity<List<RateRecord>> sortedResponse(Comparator<RateRecord> comparator) {
        return ResponseEntity.ok(rateRecords.stream().sorted(comparator).collect(Collectors.toList()));
    }
}
