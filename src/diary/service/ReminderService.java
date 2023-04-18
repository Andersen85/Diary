package diary.service;

import com.google.gson.Gson;
import diary.database.DataBase;
import diary.exception.DiaryErrorCode;
import diary.exception.DiaryException;
import diary.model.Date;
import diary.model.Reminder;
import diary.model.Time;
import diary.request.ChangeReminderRequest;
import diary.request.DeleteReminderRequest;
import diary.request.GetReminderListRequest;
import diary.request.ReminderRequest;
import diary.response.GetReminderListResponse;


public class ReminderService {

    private static Gson gson = new Gson();

    public static void addReminder(String jsonReminder) throws DiaryException {
            ReminderRequest request = gson.fromJson(jsonReminder,ReminderRequest.class);
            validateAddReminderRequest(request);
            DataBase.getDataBase().addReminder(new Reminder(request.getReminder(), new Time(request.getStartTimeMinute(), request.getStartTimeHour()),
                    new Time(request.getEndTimeMinute(), request.getEndTimeHour())), new Date(request.getYear(), request.getMonth(), request.getDay()));
    }

    public static String getReminderList(String jsonDate) throws DiaryException {
            GetReminderListRequest request = gson.fromJson(jsonDate,GetReminderListRequest.class);
            validateGetReminderListRequest(request);
            GetReminderListResponse response = new GetReminderListResponse(DataBase.getDataBase().getReminderList(new Date(request.getYear(), request.getMonth(), request.getDay())));
            return gson.toJson(response);
    }

    public static void changeReminder(String json) throws DiaryException{
        ChangeReminderRequest request = gson.fromJson(json,ChangeReminderRequest.class);
        validateChangeReminderRequest(request);
        DataBase.getDataBase().changeReminder(request.getDate(),request.getCurReminder(),request.getNewReminder());

    }

    public static void deleteReminder(String json){
        DeleteReminderRequest deleteRequest = gson.fromJson(json,DeleteReminderRequest.class);
        DataBase.getDataBase().deleteReminder(deleteRequest.getDate(),deleteRequest.getReminderToDelete());
    }



    private static void validateAddReminderRequest(ReminderRequest request) throws DiaryException {
        if (request.getReminder() == null || request.getReminder().trim().equals("")) {
            throw new DiaryException(DiaryErrorCode.WRONG_REMINDER);
        }

        validateGetReminderListRequest(new GetReminderListRequest(request.getYear(), request.getMonth(), request.getDay()));

        if (request.getStartTimeMinute() < 0 || request.getStartTimeMinute() > 59) {
            throw new DiaryException(DiaryErrorCode.WRONG_TIME_START_MINUTE);
        }

        if (request.getStartTimeHour() < 0 || request.getStartTimeHour() > 23) {
            throw new DiaryException(DiaryErrorCode.WRONG_TIME_START_HOUR);
        }

        if (request.getEndTimeMinute() < 0 || request.getEndTimeMinute() > 59) {
            throw new DiaryException(DiaryErrorCode.WRONG_TIME_END_MINUTE);
        }

        if (request.getEndTimeHour() < 0 || request.getEndTimeHour() > 23) {
            throw new DiaryException(DiaryErrorCode.WRONG_TIME_END_HOUR);
        }

        if(request.getStartTimeHour() > request.getEndTimeHour()){
            throw new DiaryException(DiaryErrorCode.WRONG_TIME_FRAME);
        }

        if(request.getEndTimeHour() == request.getStartTimeHour() && request.getEndTimeMinute() == request.getStartTimeMinute()){
            throw new DiaryException(DiaryErrorCode.WRONG_TIME_FRAME);
        }

    }

    private static void validateGetReminderListRequest(GetReminderListRequest request) throws DiaryException{
        if (request.getMonth() == 1 || request.getMonth() == 3 || request.getMonth() == 5 || request.getMonth() == 7 ||
                request.getMonth() == 8 || request.getMonth() == 10 || request.getMonth() == 12) {
            if (request.getDay() < 1 || request.getDay() > 31) {
                throw new DiaryException(DiaryErrorCode.WRONG_DAY);
            }
        }

        if (request.getMonth() == 4 || request.getMonth() == 6 || request.getMonth() == 9 || request.getMonth() == 11) {
            if (request.getDay() < 1 || request.getDay() > 30) {
                throw new DiaryException(DiaryErrorCode.WRONG_DAY);
            }
        }

        if (request.getMonth() == 2) {
            if (request.getDay() < 1 || request.getDay() > 28) {
                throw new DiaryException(DiaryErrorCode.WRONG_DAY);
            }
        }

        if (request.getMonth() < 1 || request.getMonth() > 12) {
            throw new DiaryException(DiaryErrorCode.WRONG_MONTH);
        }


        if (request.getYear() < 2021) {
            throw new DiaryException(DiaryErrorCode.WRONG_YEAR);
        }
    }

    private static void validateChangeReminderRequest(ChangeReminderRequest request) throws DiaryException {
        validateAddReminderRequest(new ReminderRequest(request.getDate().getYear(),request.getDate().getMonth(),request.getDate().getDay(),
                request.getCurReminder().getStartTime().getMinute(),request.getCurReminder().getStartTime().getHour(),
                request.getCurReminder().getEndTime().getMinute(),request.getCurReminder().getEndTime().getHour(),
                request.getCurReminder().getReminder()));

        validateAddReminderRequest(new ReminderRequest(request.getDate().getYear(),request.getDate().getMonth(),request.getDate().getDay(),
                request.getNewReminder().getStartTime().getMinute(),request.getNewReminder().getStartTime().getHour(),
                request.getNewReminder().getEndTime().getMinute(),request.getNewReminder().getEndTime().getHour(),
                request.getNewReminder().getReminder()));
    }


}
