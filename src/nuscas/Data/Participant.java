package nuscas.Data;

import nuscas.FileHandler.AnswerHandler;

import java.util.ArrayList;

public class Participant {
    private ArrayList<TimeStamp> timeStampsAccepted = new ArrayList<>();
    private ArrayList<String> timeStampsRejected = new ArrayList<>();
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
            timeStampsRejected.add(timeStamp.toString());
            System.out.println("rejected: " + timeStampString);
        } else {
            this.timeStampsAccepted.add(timeStamp);
        }
    }

    @Override
    public String toString() {
        String input = "\nParticipant Name: %s\n"
                + "Participant Telegram Name: %s\n"
                + "Participant Answers (Accepted): "
                + printTimeStampsWithPoints()
                + "\n"
                + "Participant Answers (Rejected, Format Error): "
                + printTimeStampsRejected()
                + "\n"
                + "Participant Points: "
                + getAllPoints()
                + "\n";
        return String.format(input, participantName, participantTelegramName);
    }

    private String printTimeStampsWithPoints() {
        String output = "";
        for (TimeStamp timeStamp : this.timeStampsAccepted) {
            if (timeStamp.isValid) {
                output += timeStamp.toString() + ", ";
            }
        }
        return output.substring(0, output.length()-2);
    }

    public int getAllPoints() {
        int sumOfPoints = 0;
        for (TimeStamp timeStamp : timeStampsAccepted) {
            sumOfPoints += timeStamp.getPoints();
        }
        return sumOfPoints;
    }

    private String printTimeStampsRejected() {
        return String.join(",", this.timeStampsRejected);
    }
}
