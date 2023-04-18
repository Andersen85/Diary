package diary.database;

import diary.exception.DiaryErrorCode;
import diary.exception.DiaryException;
import diary.model.Date;
import diary.model.Reminder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataBase {
    private static DataBase singletonDataBase;

    private Map<Date, List<Reminder>> mapDateToTimetable;

    private DataBase(Map<Date, List<Reminder>> mapDateToTimetable){
        this.mapDateToTimetable = mapDateToTimetable;
    }

    private DataBase(){
        this.mapDateToTimetable = new HashMap<>();
    }

    public static DataBase getDataBase(Map<Date, List<Reminder>> mapDateToTimetable){
        if(singletonDataBase == null){
            singletonDataBase = new DataBase(mapDateToTimetable);
        }
        return singletonDataBase;
    }

    public static DataBase getDataBase(){
        if(singletonDataBase == null){
            singletonDataBase = new DataBase();
        }
        return singletonDataBase;
    }

    public void addReminder(Reminder newReminder, Date date) {
        List<Reminder> reminderList = mapDateToTimetable.get(date);
        if(reminderList == null){
            mapDateToTimetable.computeIfAbsent(date, k -> new ArrayList<>());
            mapDateToTimetable.get(date).add(newReminder);
            return;
        }

        reminderList.add(newReminder);
    }

    public List<Reminder> getReminderList(Date date) throws DiaryException{
        List<Reminder> output = mapDateToTimetable.get(date);
        if(output == null){
            throw new DiaryException(DiaryErrorCode.REMINDER_LIST_DOES_NOT_EXIST);
        }
        return output;
    }

    public Map<Date, List<Reminder>> getMapDateToTimetable() {
        return mapDateToTimetable;
    }

    public void changeReminder(Date date, Reminder curReminder, Reminder newReminder) throws DiaryException{
        mapDateToTimetable.get(date).remove(curReminder);
        //добавить проверку на временнные рамки
        mapDateToTimetable.get(date).add(newReminder);
    }

    public void deleteReminder(Date date, Reminder reminder){
        mapDateToTimetable.get(date).remove(reminder);
        if(mapDateToTimetable.get(date).isEmpty()){
            mapDateToTimetable.remove(date);
        }
    }
}
