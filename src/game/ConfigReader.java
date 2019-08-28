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
            if (lines.size() < 2) throw new ParseException("Config file needs size and symbols attributes!", 0);
            parseSize(lines.get(0));
            parseSymbols(lines.get(1));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseSize(String line) throws ParseException {
        String[] parts = line.split(":");
        if (parts.length != 2)
            throw new ParseException("Please write config pairs in the format: [name]:[attribute]", 0);

        if (!parts[0].trim().equals("size"))
            throw new ParseException("First attribute of config file should be \"size\"!", 0);

        try {
            int size = Integer.parseInt(parts[1].trim());
            boardSize = size;
        } catch (NumberFormatException e) {
            throw new ParseException("Could not parse size!", 0);
        }
    }

    private void parseSymbols(String line) throws ParseException {
        String[] parts = line.split(":");
        if (parts.length != 2)
            throw new ParseException("Please write config pairs in the format: [name]:[attribute]", 1);

        if (!parts[0].trim().equals("symbols"))
            throw new ParseException("Second attribute of config file should be \"symbols\"!", 1);

        String[] symbols = parts[1].split(",");
        for (String s : symbols) {
            s = s.trim();
            if (s.length() > 1) throw new ParseException("Symbols should be a single character long!", 1);
        }
        this.symbols = symbols;
    }
}
