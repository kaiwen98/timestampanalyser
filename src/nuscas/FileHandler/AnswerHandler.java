package nuscas.FileHandler;

import nuscas.Data.Participant;
import nuscas.Data.TimeStamp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnswerHandler extends CsvHandler {
    private String sourceDir = "C:\\Users\\Looi Kai Wen\\Desktop\\NUS\\MovieCalc\\data\\Movie Screening Answer.csv";
    List<TimeStamp> timeStampsCorrect = new ArrayList<>();
    private static AnswerHandler answerHandler = new AnswerHandler();

    public AnswerHandler() {
        super();
    }

    public static AnswerHandler getInstance() {
        return answerHandler;
    }

    @Override
    public void readFile() {
        try {
            super.setUp(sourceDir);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] timeStampsString = line.split(super.COMMA_DELIMITER);
                for (String timeStampString : timeStampsString) {
                    timeStampsCorrect.add(new TimeStamp(timeStampString));
                }
            }
        } catch (java.io.IOException e) {
            System.out.println("Cannot read file!");
        }
    }

    public List<TimeStamp> getTimeStampsCorrect() {
        return timeStampsCorrect;
    }

    public void printAllCorrectTimeStamps() {
        System.out.println(timeStampsCorrect);
    }
}
