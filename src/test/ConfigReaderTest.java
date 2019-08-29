package test;

import game.ConfigReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.io.IOException;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

public class ConfigReaderTest {

    @Test
    @DisplayName("Parse correct")
    void parseCorrect() {
        try {
            ConfigReader configReader = new ConfigReader();
            configReader.readFile("configCorrect.txt");
            int actualSize = configReader.getBoardSize();
            assertEquals(4, actualSize, "Wrong board size parsed!");

            String[] expectedSym = new String[]{"X", "O", "A"};
            String[] actualSym = configReader.getSymbols();
            for (int i = 0; i < expectedSym.length; i++) {
                assertEquals(expectedSym[i], actualSym[i], "Wrong symbol parsed!");
            }

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Parse wrong format")
    void parseWrongFormat() {
        ConfigReader configReader = new ConfigReader();
        String[] files = new String[]{"configWrongFormat1.txt", "configWrongFormat2.txt", "configWrongFormat3.txt"};

        for (String file : files) {
            Executable functionCall = () -> configReader.readFile(file);
            assertThrows(ParseException.class, functionCall, "Wrong format file should throw ParseException!");
        }
    }
}
