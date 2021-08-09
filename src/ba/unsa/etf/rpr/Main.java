package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.Controller.LanguageController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ResourceBundle;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        LanguageController ctrl = new LanguageController();
        ResourceBundle bundle = ResourceBundle.getBundle("Translation");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/choose_your_language.fxml"), bundle);
        loader.setController(ctrl);
        Parent root = loader.load();
        Scene scene = new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        scene.getStylesheets().addAll(this.getClass().getResource("/css/style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.toFront();

    }
    public static void main(String[] args) {
        launch(args);
    }
}