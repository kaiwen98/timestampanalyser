package nuscas.FileHandler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public abstract class CsvHandler {
    BufferedReader bufferedReader;
    protected final String COMMA_DELIMITER = ",";
    private String sourceDir = "";
    public CsvHandler() {

    }

    public void setUp(String sourceDir) throws IOException {
        this.setSourceDir(sourceDir);
        this.bufferedReader = new BufferedReader(new FileReader(this.sourceDir));
    }

    public void setSourceDir(String sourceDir) {
        this.sourceDir = sourceDir;
    }

    public abstract void readFile();
}
