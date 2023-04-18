package diary.controller;

import diary.Main;
import diary.database.DataBase;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class MainController {
    @FXML
    private Button addReminderButton;

    @FXML
    private Button changeReminderButton;

    @FXML
    private Button deleteReminderButton;

    @FXML
    private Button exitButton;

    @FXML
    private Button getTimetableByDateButton;

    @FXML
    private Button cheatButton;

    @FXML
    void initialize() {
        addReminderButton.setOnAction(actionEvent -> {
            try {
                Main.setRoot("addReminder");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        exitButton.setOnAction(actionEvent -> exitButton.getScene().getWindow().hide());//добавь сохранение базы данных

        changeReminderButton.setOnAction(actionEvent -> {
            try {
                Main.setRoot("changeReminderChoose");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        getTimetableByDateButton.setOnAction(actionEvent -> {
            try {
                Main.setRoot("showTimetable");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        deleteReminderButton.setOnAction(actionEvent -> {
            try {
                Main.setRoot("deleteReminder");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        cheatButton.setOnAction(actionEvent -> {
            System.out.println(DataBase.getDataBase().getMapDateToTimetable());
        });
    }
}
