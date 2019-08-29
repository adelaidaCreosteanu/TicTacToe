package test;

import game.ConfigReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

public class ConfigReaderTest {
    private ConfigReader configReader;

    @BeforeEach
    void setUp() {
        configReader = new ConfigReader();
    }

    @Test
    @DisplayName("Parse correct")
    void parseCorrect() {
        String[] tests = new String[]{"size:4\nsymbols:X,O,A\n", "size : 10 \n symbols:1,  3  , I"};
        int[] sizes = new int[]{4, 10};
        String[][] symbols = new String[][]{{"X", "O", "A"}, {"1", "3", "I"}};

        for (int i = 0; i < tests.length; i++) {
            try {
                configReader.parse(new StringReader(tests[i]));
            } catch (ParseException | IOException e) {
                e.printStackTrace();
            }

            int actualSize = configReader.getBoardSize();
            assertEquals(sizes[i], actualSize, "Wrong board size parsed!");

            String[] expectedSym = symbols[i];
            String[] actualSym = configReader.getSymbols();
            for (int j = 0; j < expectedSym.length; j++) {
                assertEquals(expectedSym[j], actualSym[j], "Wrong symbol parsed!");
            }
        }
    }

    @Test
    @DisplayName("Parse wrong format")
    void parseWrongFormat() {
        String[] tests = new String[]{"foobar", "size foo\nsymbols bar", "size:foo\nsymbols:bar"};

        for (String test : tests) {
            Executable functionCall = () -> configReader.parse(new StringReader(test));
            assertThrows(ParseException.class, functionCall, "Wrong format file should throw ParseException!");
        }
    }

    @Test
    @DisplayName("Throw IllegalArgumentException")
    void throwIllegalArgumentException() {
        String[] tests = new String[]{"size:2\nsymbols:X,O,A\n", "size:11\nsymbols:1,3,I", "size:5\nsymbols:1,3,AI"};

        for (String test : tests) {
            Executable functionCall = () -> configReader.parse(new StringReader(test));
            assertThrows(IllegalArgumentException.class, functionCall,
                    "Illegal values of config attributes should throw IllegalArgumentException!");
        }
    }
}
