package diary.controller;

import com.google.gson.Gson;
import diary.Main;
import diary.exception.DiaryException;
import diary.request.ReminderRequest;
import diary.service.ReminderService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddReminderController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addReminderFinalButton;

    @FXML
    private TextField dateTextField;

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
        exitToMenuButton.setOnAction(actionEvent -> {
            try {
                Main.setRoot("mainScene");
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        addReminderFinalButton.setOnAction(actionEvent -> {
            if (addReminderRightFormat()) {
                try {
                    Main.setRoot("mainScene");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private boolean addReminderRightFormat() {
        String date = dateTextField.getText().trim();
        String reminder = reminderField.getText().trim();
        String timeStart = timeStartField.getText().trim();
        String timeEnd = timeEndField.getText().trim();

        if (date.length() != 10) {
            errorTextField.setText("Неправильный формат даты!");
            return false;
        }
        String[] dateArray = date.split("\\.");
        if (dateArray.length != 3) {
            errorTextField.setText("Неправильный формат даты!");
            return false;
        }
        int[] intDateArray = new int[3];
        try {
            for (int i = 0; i < dateArray.length; i++) {
                intDateArray[i] = Integer.parseInt(dateArray[i]);
            }
        } catch (NumberFormatException e) {
            errorTextField.setText("Неправильный формат даты!");
            return false;
        }


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

        if (reminder.isEmpty()) {
            errorTextField.setText("Поле с напоминанием пустое!");
            return false;
        }
        Gson gson = new Gson();
        ReminderRequest request = new ReminderRequest(intDateArray[2], intDateArray[1], intDateArray[0], startTimeIntArray[1],
                startTimeIntArray[0], endTimeIntArray[1], endTimeIntArray[0], reminder);
        try{
            ReminderService.addReminder(gson.toJson(request));
        }
        catch (DiaryException e){
            errorTextField.setText(e.getErrorCode().getErrorString());
            return false;
        }
        return true;
    }

}

