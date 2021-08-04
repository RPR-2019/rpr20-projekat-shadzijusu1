package Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class HomeController {
    Stage parentStage;

    public void setParentStage(Stage parentStage) {
        this.parentStage = parentStage;
    }
    public void login() throws Exception{
        Stage myStage = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/fxml/login.fxml"));
        Parent root = loader.load();
        myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        myStage.setResizable(false);
        myStage.show();
    }
    public void register() throws Exception{
        Stage myStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/fxml/register.fxml"));
        Parent root = loader.load();
        myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        myStage.setResizable(false);
        myStage.show();
    }
}