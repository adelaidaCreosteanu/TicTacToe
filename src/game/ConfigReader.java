package game;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.List;

public class ConfigReader {
    private int boardSize;
    private String[] symbols;

    public ConfigReader(String configFile) throws ParseException {
        parse(configFile);
    }

    public int getBoardSize() {
        return boardSize;
    }

    public String[] getSymbols() {
        return symbols;
    }

    private void parse(String file) throws ParseException {
        Path path = Paths.get(System.getProperty("user.dir"), file);

        try {
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            if (lines.size() < 2) throw new ParseException("Config file does not contain all necessary attributes!", 0);
            parseSize(lines.get(0));
            parseSymbols(lines.get(1));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseSize(String line) throws ParseException {
        String unparsedSize = removeAttributeName(line, "size");

        try {
            int size = Integer.parseInt(unparsedSize.trim());
            if (size < 3 || size > 10) throw new IllegalArgumentException("Board size should be between 3 and 10!");
            boardSize = size;
        } catch (NumberFormatException e) {
            throw new ParseException("Size should be a number!", 0);
        }
    }

    private void parseSymbols(String line) throws ParseException {
        String unparsedSymbols = removeAttributeName(line, "symbols");

        String[] symbols = unparsedSymbols.split(",");
        for (int i = 0; i < symbols.length; i++) {
            symbols[i] = symbols[i].trim();
            int nChars = symbols[i].length();
            if (nChars > 1) throw new ParseException("Symbols should be a single character long!", 1);
        }
        this.symbols = symbols;
    }

    private String removeAttributeName(String line, String name) throws ParseException {
        String[] parts = line.split(":");
        if (parts.length != 2)
            throw new ParseException("Please write config pairs in the format: [name]:[value]", 0);

        if (!parts[0].trim().equals(name))
            throw new ParseException("Config file should contain attribute named \"" + name + "\"!", 0);

        return parts[1];
    }
}
