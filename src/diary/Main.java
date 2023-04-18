package diary;

import com.google.gson.Gson;
import diary.database.DataBase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Main extends Application {


    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        DataBase.getDataBase(new HashMap<>());
        scene = new Scene(loadFXML("mainScene"));
        stage.setTitle("Diary");
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/icon.png"))));
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        return FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("vis/" + fxml + ".fxml")));
    }

    public static void main(String[] args) {
        launch();
    }
}
