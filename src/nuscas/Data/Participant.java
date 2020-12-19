package nuscas.Data;

import nuscas.FileHandler.AnswerHandler;

import java.util.ArrayList;

public class Participant {
    private ArrayList<TimeStamp> timeStampsAccepted = new ArrayList<>();
    private ArrayList<TimeStamp> timeStampsRejected = new ArrayList<>();
    private ArrayList<TimeStamp> timeStampsCorrect = new ArrayList<>();
    private ArrayList<TimeStamp> timeStampsReference = new ArrayList<>();
    private ArrayList<TimeStamp> timeStampsWrong = new ArrayList<>();
    private String participantName = "";
    private String participantTelegramName = "";
    private String submissionTimeStamp = "";

    public Participant(String submissionTimeStamp, String participantName, String participantTelegramName) {
        this.submissionTimeStamp = submissionTimeStamp;
        this.participantName = participantName;
        this.participantTelegramName = participantTelegramName;
    }

    public void addTimeStampByString(String timeStampString) {
        timeStampString = timeStampString.replace("\"", "");
        TimeStamp timeStamp = new TimeStamp(timeStampString);
        if (!timeStamp.isValid) {
            timeStampsRejected.add(timeStamp);
        } else {
            this.timeStampsAccepted.add(timeStamp);
        }
    }

    @Override
    public String toString() {
        String input = "\nParticipant Name: %s\n"
            + "Participant Telegram Name: %s\n"
            + "Participant Answers (Accepted, All): "
            + printTimeStamps(timeStampsAccepted)
            + "\n"
            + "Participant Answers (Rejected, Format Error): "
            + printTimeStamps(timeStampsRejected)
            + "\n"
            + "Participant Points: "
            + getAllPoints()
            + "\n"
            + "Participant Answers (Accepted, Correct)[%.2f%%]: "
            + printTimeStamps(timeStampsCorrect)
            + "\n"
            + "Participant Answers (Accepted, Reference): "
            + printTimeStamps(timeStampsReference)
            + "\n"
            + "Participant Answers (Accepted, Wrong): "
            + printTimeStamps(timeStampsWrong)
            + "\n";

        return String.format(input, participantName, participantTelegramName, (double)(timeStampsCorrect.size() * 100 / 24));
    }

    private String printTimeStamps(ArrayList<TimeStamp> timeStamps) {
        String output = "";
        for (TimeStamp timeStamp : timeStamps) {
            output += timeStamp.toString() + ", ";
        }
        if (output.length() == 0) {
            return "";
        }
        return output.substring(0, output.length()-2);
    }

    public int getAllPoints() {
        int sumOfPoints = 0;
        for (TimeStamp timeStamp : timeStampsAccepted) {
            int points = timeStamp.getPoints(timeStampsReference);
            sumOfPoints += points;
            if (points == 2) {
                this.timeStampsCorrect.add(timeStamp);
            } else if (points == -1) {
                this.timeStampsWrong.add(timeStamp);
            }
        }
        return sumOfPoints;
    }
}
