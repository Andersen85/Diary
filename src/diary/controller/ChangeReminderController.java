package diary.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.google.gson.Gson;
import diary.Main;
import diary.exception.DiaryException;
import diary.model.Date;
import diary.model.Reminder;
import diary.model.Time;
import diary.request.ChangeReminderRequest;
import diary.service.ReminderService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class ChangeReminderController {

    private Date date;

    private Reminder reminder;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button changeReminderFinalButton;

    @FXML
    private Text dateTextField;

    @FXML
    private Text errorTextField;

    @FXML
    private Button exitToMenuButton;

    @FXML
    private TextField reminderField;

    @FXML
    private TextField timeEndField;

    @FXML
    private TextField timeStartField;

    @FXML
    void initialize() {

        changeReminderFinalButton.setOnAction(actionEvent -> {
            if(changeReminder()){
                try {
                    Main.setRoot("mainScene");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });

        exitToMenuButton.setOnAction(actionEvent -> {
            try {
                Main.setRoot("mainScene");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


    }

    public void transfer(Date date, Reminder reminder){
        this.date = date;
        this.reminder = reminder;
        setFieldsText();
    }

    private void setFieldsText(){
        dateTextField.setText(date.toString());
        timeStartField.setText(reminder.getStartTime().toString());
        timeEndField.setText(reminder.getEndTime().toString());
        reminderField.setText(reminder.getReminder().toString());
    }

    private boolean changeReminder() {
        String newReminder = reminderField.getText().trim();
        String timeStart = timeStartField.getText().trim();
        String timeEnd = timeEndField.getText().trim();

        if (timeStart.length() != 5) {
            errorTextField.setText("Неправильный временной формат!");
            return false;
        }
        String[] timeStartArray = timeStart.split(":");
        if (timeStartArray.length != 2) {
            errorTextField.setText("Неправильный временной формат!");
            return false;
        }
        int[] startTimeIntArray = new int[2];
        try {
            for (int i = 0; i < timeStartArray.length; i++) {
                startTimeIntArray[i] = Integer.parseInt(timeStartArray[i]);
            }
        } catch (NumberFormatException e) {
            errorTextField.setText("Неправильный временной формат!");
            return false;
        }

        if (timeEnd.length() != 5) {
            errorTextField.setText("Неправильный временной формат!");
            return false;
        }
        String[] timeEndArray = timeEnd.split(":");
        if (timeEndArray.length != 2) {
            errorTextField.setText("Неправильный временной формат!");
            return false;
        }
        int[] endTimeIntArray = new int[2];
        try {
            for (int i = 0; i < timeEndArray.length; i++) {
                endTimeIntArray[i] = Integer.parseInt(timeEndArray[i]);
            }
        } catch (NumberFormatException e) {
            errorTextField.setText("Неправильный временной формат!");
            return false;
        }

        if (newReminder.isEmpty()) {
            errorTextField.setText("Поле с напоминанием пустое!");
            return false;
        }

        Gson gson = new Gson();
        ChangeReminderRequest request = new ChangeReminderRequest(reminder,new Reminder(newReminder,new Time(startTimeIntArray[1],
                startTimeIntArray[0]), new Time(endTimeIntArray[1], endTimeIntArray[0])),date);
        try{
            ReminderService.changeReminder(gson.toJson(request));
        }
        catch (DiaryException e){
            errorTextField.setText(e.getErrorCode().getErrorString());
            return false;
        }
        return true;
    }

}
