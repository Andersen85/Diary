package diary.model;

import java.util.Objects;

public class Reminder {
    private String reminder;
    private Time startTime;
    private Time endTime;

    public Reminder(String reminder, Time startTime, Time endTime) {
        this.reminder = reminder;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getReminder() {
        return reminder;
    }

    public Time getStartTime() {
        return startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setReminder(String reminder) {
        this.reminder = reminder;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reminder reminder1 = (Reminder) o;
        return Objects.equals(reminder, reminder1.reminder) && Objects.equals(startTime, reminder1.startTime) && Objects.equals(endTime, reminder1.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reminder, startTime, endTime);
    }

    @Override
    public String toString() {
        //сделай правильный формат вывода времени иесли это не двузначное число
        return  startTime.getHour() + ":" + startTime.getMinute() + " - "
                + endTime.getHour() + ":" + endTime.getMinute() + " => " +
                getReminder();
    }
}
