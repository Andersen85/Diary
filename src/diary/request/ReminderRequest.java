package diary.request;

public class ReminderRequest {
    private int year;
    private int month;
    private int day;
    private int startTimeMinute;
    private int startTimeHour;
    private int endTimeMinute;
    private int endTimeHour;
    private String reminder;

    public ReminderRequest(int year, int month, int day, int startTimeMinute, int startTimeHour, int endTimeMinute, int endTimeHour, String reminder) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.startTimeMinute = startTimeMinute;
        this.startTimeHour = startTimeHour;
        this.endTimeMinute = endTimeMinute;
        this.endTimeHour = endTimeHour;
        this.reminder = reminder;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getStartTimeMinute() {
        return startTimeMinute;
    }

    public int getStartTimeHour() {
        return startTimeHour;
    }

    public int getEndTimeMinute() {
        return endTimeMinute;
    }

    public int getEndTimeHour() {
        return endTimeHour;
    }

    public String getReminder(){
        return reminder;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setStartTimeMinute(int startTimeMinute) {
        this.startTimeMinute = startTimeMinute;
    }

    public void setStartTimeHour(int startTimeHour) {
        this.startTimeHour = startTimeHour;
    }

    public void setEndTimeMinute(int endTimeMinute) {
        this.endTimeMinute = endTimeMinute;
    }

    public void setEndTimeHour(int endTimeHour) {
        this.endTimeHour = endTimeHour;
    }

    public void setReminder(String reminder){
        this.reminder = reminder;
    }
}
