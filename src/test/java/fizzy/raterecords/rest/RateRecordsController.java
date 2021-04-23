package fizzy.raterecords.rest;

import fizzy.raterecords.model.RateRecord;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RateRecordsController {
    final List<RateRecord> rateRecords = new ArrayList<>();

    @GetMapping("/records/birthdate")
    ResponseEntity<List<RateRecord>> getByBirthdate() {
        return sortedResponse(Comparator.comparing(RateRecord::getDateOfBirth));
    }

    ResponseEntity<List<RateRecord>> sortedResponse(Comparator<RateRecord> comparator) {
        return ResponseEntity.ok(rateRecords.stream().sorted(comparator).collect(Collectors.toList()));
    }
}
