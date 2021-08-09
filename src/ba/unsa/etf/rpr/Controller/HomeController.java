package ba.unsa.etf.rpr.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ResourceBundle;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class HomeController {
    public Button prijavaBtn;
    public Button registerBtn;
    @FXML
    public void initialize() {
        prijavaBtn.setOnAction(actionEvent -> {
            try {
                login(actionEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        registerBtn.setOnAction(actionEvent -> {
            try {
                register(actionEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void register(javafx.event.ActionEvent actionEvent) throws IOException {
        Stage myStage = new Stage();
        ResourceBundle bundle = ResourceBundle.getBundle("Translation");
        FXMLLoader loader = new FXMLLoader( getClass().getResource(
                "/fxml/register.fxml" ), bundle);
        Parent root = loader.load();
        myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        myStage.setResizable(false);
        myStage.show();
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
    }

    private void login(javafx.event.ActionEvent actionEvent) throws IOException {
        Stage myStage = new Stage();

        ResourceBundle bundle = ResourceBundle.getBundle("Translation");
        FXMLLoader loader = new FXMLLoader( getClass().getResource(
                "/fxml/login.fxml" ), bundle);
        Parent root = loader.load();
        myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        myStage.setResizable(false);
        myStage.show();
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();

    }

}