package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class LanguageController {
    public RadioButton bhsBtn;
    public RadioButton englishBtn;
    @FXML
    public void initialize() {
        bhsBtn.setOnAction(actionEvent -> {
            try {
                setBHS();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        englishBtn.setOnAction(actionEvent -> {
            try {
                setEng();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void setEng() throws IOException {
        Locale.setDefault(new Locale("en_US", "US"));
        ResourceBundle bundle = ResourceBundle.getBundle("Translation");
        FXMLLoader loader = new FXMLLoader( getClass().getResource(
                "/fxml/home.fxml" ), bundle);
        HomeController ctrl = new HomeController();
        loader.setController(ctrl);
        Parent root = loader.load();
        Stage myStage = new Stage();
        myStage.setTitle("RPR pictures");
        myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        myStage.show();
    }

    private void setBHS() throws IOException {
        Locale.setDefault(new Locale("bs", "BA"));
        ResourceBundle bundle = ResourceBundle.getBundle("Translation");
        FXMLLoader loader = new FXMLLoader( getClass().getResource(
                "/fxml/home.fxml" ), bundle);
        HomeController ctrl = new HomeController();
        loader.setController(ctrl);
        Parent root = loader.load();
        Stage myStage = new Stage();
        myStage.setTitle("RPR pictures");
        myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        myStage.show();
    }
}
