package fizzy.raterecords;

import fizzy.raterecords.model.Parser;
import fizzy.raterecords.parsers.DelegatingParser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RaterecordsApplication {

    public static void main(String[] args) {
        SpringApplication.run(RaterecordsApplication.class, args);
    }

    @Bean
    Parser parser() {
        return new DelegatingParser();
    }
}
