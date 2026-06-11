package Assignment_18;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

public class ScoreProcessorTest {

    @Test
    void testValidFile() throws FileNotFoundException {

        ScoreProcessor sp = new ScoreProcessor();

        int result = sp.processScoreFile("out/valid.txt");

        assertEquals(500, result);
    }

    @Test
    void testMissingFile() {

        ScoreProcessor sp = new ScoreProcessor();

        assertThrows(FileNotFoundException.class, () -> {

            sp.processScoreFile("out/missing.txt");

        });
    }
}