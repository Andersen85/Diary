package diary.exception;

public enum DiaryErrorCode {

    REMINDER_ALREADY_ADDED("Reminder already added!"),
    WRONG_REMINDER("Wrong reminder!"),
    WRONG_DAY("Wrong day!"),
    WRONG_MONTH("Wrong month!"),
    WRONG_YEAR("Wrong year!"),
    WRONG_TIME_START_HOUR("Wrong time start hour!"),
    WRONG_TIME_START_MINUTE("Wrong time start minute!"),
    WRONG_TIME_END_HOUR("Wrong time end minute!"),
    WRONG_TIME_END_MINUTE("Wrong time end minute!"),
    WRONG_REMINDER_LIST("Wrong reminder list!"),
    REMINDER_LIST_DOES_NOT_EXIST("Reminder list doesn't exist!"),
    WRONG_TIME_FRAME("Wrong time frame!");





    private final String errorString;

    DiaryErrorCode(String errorString){
        this.errorString = errorString;
    }

    public String getErrorString(){
        return errorString;
    }

}
