package fizzy.raterecords.cli;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

public class MainTest {

    @Test
    void runMain() throws FileNotFoundException {
        Main.main(new String[]{"src/test/resources/commaDelimited.txt",
                "src/test/resources/pipeDelimited.txt",
                "src/test/resources/spaceDelimited.txt"});
    }
}
