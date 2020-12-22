package nuscas.Data;

import nuscas.FileHandler.AnswerHandler;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimeStamp {
    int milliseconds = 0;
    Pattern regexPattern;
    Matcher regexMatcher;
    String rawTimeStampString = "";
    boolean isValid = false;

    public TimeStamp(String inputString) {
        this.rawTimeStampString = inputString;
        setUpRegex("\\s+[0-9]{2}[\\W][0-9]{2}[\\W][0-9]{2}[\\W][0-9]{3}\\s+");
        String testedString = " " + inputString + " ";
        if (isValidTimeStampFormat(testedString)) {
            this.milliseconds = convertTimeStampStringToMilliseconds(inputString);
            this.isValid = true;
        }
    }

    private void setUpRegex(String patternString) {
        regexPattern = Pattern.compile(patternString);
    }

    public int getMilliseconds() {
        return this.milliseconds;
    }

    private int convertTimeStampStringToMilliseconds(String timeStamp) {
        String inputString = timeStamp.replace(".", ":");
        String[] timeStampTokens = inputString.split(":");
        int factor = 0;
        int millisecondResult = 0;
        for (int i = timeStampTokens.length - 1; i >= 0; i--) {
            millisecondResult += Math.pow(60, factor) * Integer.parseInt(timeStampTokens[i]);
            factor += 1;
        }
        return millisecondResult;
    }

    private boolean isValidTimeStampFormat(String inputString) {
        this.regexMatcher = regexPattern.matcher(inputString);
        return regexMatcher.find();
    }

    public int getPoints(ArrayList<TimeStamp> timeStampReference) {
        for (TimeStamp correctTimeStamp : AnswerHandler.getInstance().getTimeStampsCorrect()) {
            if (Math.abs(this.milliseconds - correctTimeStamp.milliseconds) < 700) {
                timeStampReference.add(correctTimeStamp);
                return 2;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        return this.rawTimeStampString;
    }
}
