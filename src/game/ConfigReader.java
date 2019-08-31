package game;

import java.io.*;
import java.text.ParseException;

/**
 * Reads a config file and parses board size and symbols. ConfigReader can
 * also parse directly from a Reader, which is convenient if physical files
 * are not desired. The parsed board size and player symbols are accessed
 * through getters.
 */
public class ConfigReader {
    private int boardSize;
    private String[] symbols;

    public void readFile(String configFile) throws IOException, ParseException {
        File filePath = new File(System.getProperty("user.dir"), configFile);
        FileReader reader = new FileReader(filePath);
        parse(reader);
    }

    public void parse(Reader reader) throws IOException, ParseException {
        BufferedReader bReader = new BufferedReader(reader);
        parseSize(bReader.readLine());
        parseSymbols(bReader.readLine());
    }

    public int getBoardSize() {
        if (boardSize != 0) return boardSize;
        throw new RuntimeException("Please call readFile() before getBoardSize()!");
    }

    public String[] getSymbols() {
        if (symbols != null) return symbols;
        throw new RuntimeException("Please call readFile() before getSymbols()!");
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
            if (nChars > 1) throw new IllegalArgumentException("Symbols should be a single character long!");
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
