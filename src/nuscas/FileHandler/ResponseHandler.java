package nuscas.FileHandler;

import nuscas.Data.Participant;

import java.io.IOException;
import java.util.ArrayList;

public class ResponseHandler extends CsvReader {
    Participant participant;
    ArrayList<Participant> participants = new ArrayList<>();
    boolean isNonZeroParticipants = false;
    String sourceDir = "C:\\Users\\Looi Kai Wen\\Desktop\\NUS\\MovieCalc\\data\\NUSCAS Xmas Watchalong answer.csv";
    private static ResponseHandler responseHandler = new ResponseHandler();

    public static ResponseHandler getInstance() {
        return responseHandler;
    }

    public ResponseHandler() {
        super();
        try {
            super.setUpReader(sourceDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void readFile() {
        try {
            String line;
            bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {
                String[] surveyEntryByLine = line.split(this.COMMA_DELIMITER);
                if (surveyEntryByLine.length == 4) {
                    if (isNonZeroParticipants) {
                        participants.add(participant);
                    }
                    participant = new Participant(surveyEntryByLine[0], surveyEntryByLine[1], surveyEntryByLine[2]);
                    participant.addTimeStampByString(surveyEntryByLine[3]);
                }
                else if(surveyEntryByLine.length == 1) {
                    participant.addTimeStampByString(surveyEntryByLine[0]);
                }
                isNonZeroParticipants = true;
            }
            if (isNonZeroParticipants) {
                participants.add(participant);
            }
        } catch (java.io.IOException e) {
            System.out.println("Cannot read file!");
        }
    }

    public ArrayList<Participant> getParticipants() {
        return this.participants;
    }

    public void printAllParticipants() {
        System.out.println(getParticipants());
    }
}
