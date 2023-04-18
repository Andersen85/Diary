package diary.controller;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import diary.Main;
import diary.exception.DiaryException;
import diary.model.Date;
import diary.model.Reminder;
import diary.request.GetReminderListRequest;
import diary.response.GetReminderListResponse;
import diary.service.ReminderService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ChangeReminderChooseController {

    private Date localDate;

    public void setLocalDate(Date localDate) {
        this.localDate = localDate;
    }

    @FXML
    private VBox VBoxField;

    @FXML
    private Text chooseReminderToChangeText;

    @FXML
    private TextField enterDateField;

    @FXML
    private Text errorTextField;

    @FXML
    private Button exitToMenuButton;

    @FXML
    private ScrollPane scrollPaneField;

    @FXML
    private Button showTimetableButton;

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
                chooseReminderToChange(reminderList);
            }

        });
    }


    public List<Reminder> enterDateRightFormat() {
        //помести эту проверку в класс сервиса
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
        setLocalDate(new Date(intDateArray[2],intDateArray[1],intDateArray[0]));
        return response.getReminderList();
    }

    private void chooseReminderToChange(List<Reminder> reminderList) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../vis/changeReminder.fxml"));
        for (Reminder reminder : reminderList) {
            chooseReminderToChangeText.setText("Выбери уведомление, которое нужно изменить:");
            Button button = new Button(reminder.toString());
            button.setFont(new Font(15));
            VBoxField.getChildren().add(button);
            button.setOnAction(actionEvent -> {
                Parent root = null;
                try {
                    root = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                    exitToMenuButton.getScene().getWindow().hide();
                }

                ChangeReminderController controller = loader.getController();
                controller.transfer(localDate, reminder);

                exitToMenuButton.getScene().setRoot(root);


            });
        }
        scrollPaneField.setContent(VBoxField);
    }
}