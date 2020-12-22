package nuscas.FileHandler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public abstract class CsvHandler {
    BufferedReader bufferedReader;
    protected final String COMMA_DELIMITER = ",";
    protected String sourceDir = "";
    public CsvHandler() {

    }

    public void setUpReader(String sourceDir) throws IOException {
        this.setSourceDir(sourceDir);
        this.bufferedReader = new BufferedReader(new FileReader(this.sourceDir));
    }

    public void setUpWriter(String sourceDir) throws IOException {
        this.setSourceDir(sourceDir);
    }

    public void setSourceDir(String sourceDir) {
        this.sourceDir = sourceDir;
    }
}
