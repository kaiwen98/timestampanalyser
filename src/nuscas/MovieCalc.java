package nuscas;

import nuscas.Data.TimeStamp;
import nuscas.FileHandler.AnswerHandler;
import nuscas.FileHandler.ResponseHandler;

import java.io.IOException;

public class MovieCalc {
    public static void main(String[] args) {
        TimeStamp data = new TimeStamp("00:00:08.400");
        System.out.println(data.getMilliseconds());
        try {
            AnswerHandler.getInstance().readFile();
            ResponseHandler responseHandler = new ResponseHandler();
            responseHandler.readFile();
            AnswerHandler.getInstance().printAllCorrectTimeStamps();
            responseHandler.printAllParticipants();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
