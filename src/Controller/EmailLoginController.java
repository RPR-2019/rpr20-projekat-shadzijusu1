package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class EmailLoginController {
    public TextField emailFld;
    public TextField passFld;

    @FXML
    public void initialize() {
//        emailFld.getStyleClass().add("ok");
//        emailFld.setStyle("-fx-border-color: red");
//
//
//        passFld.getStyleClass().add("ok");
//        passFld.setStyle("-fx-border-color: red");
//        emailFld.textProperty().addListener((observableValue, oldValue, newValue) ->{
//            if(emailFld.getText().trim().isEmpty()){
//                emailFld.setStyle("-fx-border-color: red");
//                emailFld.getStyleClass().add("ok");
//            }
//            else{
//                emailFld.setStyle("-fx-border-color: lightgreen");
//                emailFld.getStyleClass().add("ok");
//            }
//        } );
//
//        passFld.textProperty().addListener((observableValue, oldValue, newValue) ->{
//            if(passFld.getText().trim().isEmpty()){
//                passFld.setStyle("-fx-border-color: red");
//                passFld.getStyleClass().add("ok");
//            }
//            else{
//                passFld.setStyle("-fx-border-color: lightgreen");
//                passFld.getStyleClass().add("ok");
//            }
//        } );
    }
    public void signIn(ActionEvent actionEvent) throws IOException {
        Stage myStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/fxml/send_email.fxml"));
        Parent root = loader.load();
        myStage.setTitle("Pošta");
        myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        myStage.setResizable(false);
        myStage.show();
        }
        public void close(ActionEvent actionEvent) {
            Node n = (Node) actionEvent.getSource();
            Stage stage = (Stage) n.getScene().getWindow();
            stage.close();
        }
    }
