package nuscas.FileHandler;

import nuscas.Data.Participant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class CsvWriter extends CsvHandler{
    public abstract void writeFile() throws IOException;
}
