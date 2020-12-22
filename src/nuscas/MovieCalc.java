package nuscas;

import nuscas.Data.Participant;
import nuscas.FileHandler.AnswerHandler;
import nuscas.FileHandler.ExcelSheetOutput;
import nuscas.FileHandler.ResponseHandler;

import java.io.IOException;
import java.util.ArrayList;

public class MovieCalc {
    public static void main(String[] args) {
        AnswerHandler.getInstance().readFile();
        ResponseHandler.getInstance().readFile();
        ArrayList<Participant> participants = ResponseHandler.getInstance().getParticipants();
        for (Participant participant : participants) {
            participant.evaluate();
        }
        // AnswerHandler.getInstance().printAllCorrectTimeStamps();
        ResponseHandler.getInstance().printAllParticipants();
        ExcelSheetOutput output = new ExcelSheetOutput();
        try {
            output.setUpWriter("C:\\Users\\Looi Kai Wen\\Desktop\\NUS\\MovieCalc\\data\\NUSCAS Xmas Watchalong breakdown.csv");
            output.writeFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
