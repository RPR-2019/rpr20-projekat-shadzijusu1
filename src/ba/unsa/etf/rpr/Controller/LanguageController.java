package ba.unsa.etf.rpr.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
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
                setBHS(actionEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        englishBtn.setOnAction(actionEvent -> {
            try {
                setEng(actionEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void setEng(javafx.event.ActionEvent actionEvent) throws IOException {
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
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
    }

    private void setBHS(javafx.event.ActionEvent actionEvent) throws IOException {
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
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
    }


}
