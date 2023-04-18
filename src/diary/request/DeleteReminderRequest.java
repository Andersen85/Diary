package diary.request;

import diary.model.Date;
import diary.model.Reminder;

public class DeleteReminderRequest {
    private Date date;
    private Reminder reminderToDelete;

    public DeleteReminderRequest(Reminder reminderToDelete, Date date) {
        this.reminderToDelete = reminderToDelete;
        this.date = date;
    }

    public Reminder getReminderToDelete() {
        return reminderToDelete;
    }

    public Date getDate() {
        return date;
    }
}
