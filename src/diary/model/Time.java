package diary.model;


import java.util.Objects;

public class Time {
    private int minute;
    private int hour;

    public Time(int minute, int hour) {
        this.minute = minute;
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getHour() {
        return hour;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Time time = (Time) o;
        return hour == time.hour && minute == time.minute;
    }

    @Override
    public int hashCode() {
        return Objects.hash(minute, hour);
    }

    @Override
    public String toString() {
        return hour + ":" + minute;
    }
}
