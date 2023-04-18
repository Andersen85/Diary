package diary.request;

import diary.model.Date;
import diary.model.Reminder;

public class ChangeReminderRequest {
    private Reminder curReminder;
    private Reminder newReminder;
    private Date date;

    public ChangeReminderRequest(Reminder curReminder, Reminder newReminder, Date date) {
        this.curReminder = curReminder;
        this.newReminder = newReminder;
        this.date = date;
    }

    public Reminder getCurReminder() {
        return curReminder;
    }

    public Reminder getNewReminder() {
        return newReminder;
    }

    public Date getDate() {
        return date;
    }
}
