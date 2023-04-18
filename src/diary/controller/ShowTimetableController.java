package diary.controller;

import com.google.gson.Gson;
import diary.Main;
import diary.exception.DiaryException;
import diary.model.Reminder;
import diary.request.GetReminderListRequest;
import diary.response.GetReminderListResponse;
import diary.service.ReminderService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ShowTimetableController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private VBox VBoxField;

    @FXML
    private Text errorTextField;

    @FXML
    private TextField enterDateField;

    @FXML
    private Button exitToMenuButton;

    @FXML
    private Button showTimetableButton;

    @FXML
    private ScrollPane scrollPaneField;

    @FXML
    void initialize() {
        exitToMenuButton.setOnAction(actionEvent -> {
            try {
                Main.setRoot("mainScene");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        showTimetableButton.setOnAction(actionEvent -> {
            List<Reminder> reminderList = enterDateRightFormat();
            if(reminderList != null){
                showTimetable(reminderList);
            }
        });

    }


    public List<Reminder> enterDateRightFormat() {
        String enterDate = enterDateField.getText().trim();
        if (enterDate.length() != 10) {
            errorTextField.setText("Неправильный формат даты!");
            return null;
        }
        String[] dateArray = enterDate.split("\\.");
        if (dateArray.length != 3) {
            errorTextField.setText("Неправильный формат даты!");
            return null;
        }
        int[] intDateArray = new int[3];
        try {
            for (int i = 0; i < dateArray.length; i++) {
                intDateArray[i] = Integer.parseInt(dateArray[i]);
            }
        } catch (NumberFormatException e) {
            errorTextField.setText("Неправильный формат даты!");
            return null;
        }
        Gson gson = new Gson();
        String jsonAnswer;
        try{
            jsonAnswer = ReminderService.getReminderList(gson.toJson(new GetReminderListRequest(intDateArray[2],intDateArray[1],intDateArray[0])));
        }
        catch (DiaryException e){
            errorTextField.setText(e.getErrorCode().getErrorString());
            return null;
        }
        GetReminderListResponse response = gson.fromJson(jsonAnswer,GetReminderListResponse.class);

        return response.getReminderList();


        /*List<Reminder> reminderList = DataBase.getDataBase().getReminderList(date);
        dateTextField.setText(date.toString());
        for(Reminder reminder : reminderList){
            Text text = new Text(reminder.toString());
            text.setFont(new Font(15));
            VBoxField.getChildren().add(text);
        }
        scrollPaneField.setContent(VBoxField);*/
    }

    private void showTimetable(List<Reminder> reminderList){
        VBoxField.getChildren().clear();
        for(Reminder reminder : reminderList){
            VBoxField.getChildren().add(new Text(reminder.toString()));
        }
    }

}
