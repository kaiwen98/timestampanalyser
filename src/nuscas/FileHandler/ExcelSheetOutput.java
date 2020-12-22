package nuscas.FileHandler;

import nuscas.Data.Participant;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExcelSheetOutput extends CsvWriter{
    private List<String[]> dataRow = new ArrayList<>();

    @Override
    public void writeFile() throws IOException {
        ArrayList<Participant> participants = ResponseHandler.getInstance().getParticipants();
        dataRow.add(new String[]{"Telegram Name", "Points", "Accuracy"});
        for (Participant participant : participants) {
            dataRow.add(String.format("%s, %d, %.2f%%", participant.getTelegramName(), participant.getAllPoints(), participant.getAccuracy())
                .split(","));
            System.out.println(participant.getTelegramName());
        }
        this.givenDataArray_whenConvertToCSV_thenOutputCreated();
    }

    public void givenDataArray_whenConvertToCSV_thenOutputCreated() throws IOException {
        File csvOutputFile = new File(super.sourceDir);
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            dataRow.stream()
                .map(this::convertToCSV)
                .forEach(pw::println);
        }
    }

    public String escapeSpecialCharacters(String data) {
        String escapedData = data.replaceAll("\\R", " ");
        if (data.contains(",") || data.contains("\"") || data.contains("'")) {
            data = data.replace("\"", "\"\"");
            escapedData = "\"" + data + "\"";
        }
        return escapedData;
    }

    public String convertToCSV(String[] data) {
        return Stream.of(data)
            .map(this::escapeSpecialCharacters)
            .collect(Collectors.joining(","));
    }
}
