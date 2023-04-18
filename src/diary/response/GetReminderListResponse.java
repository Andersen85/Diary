package diary.response;

import diary.model.Reminder;

import java.util.List;

public class GetReminderListResponse {
    private List<Reminder> reminderList;

    public GetReminderListResponse(List<Reminder> reminderList) {
        this.reminderList = reminderList;
    }

    public List<Reminder> getReminderList() {
        return reminderList;
    }
}
