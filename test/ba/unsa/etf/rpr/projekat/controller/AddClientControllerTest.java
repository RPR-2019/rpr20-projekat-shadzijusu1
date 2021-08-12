package ba.unsa.etf.rpr.projekat.controller;

import ba.unsa.etf.rpr.projekat.dao.CRMDao;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.util.ResourceBundle;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

@ExtendWith(ApplicationExtension.class)
class AddClientControllerTest {
    public CRMDao model;

    @Start
    public void start(Stage stage) throws Exception {
        model = CRMDao.getInstance();
        LanguageController ctrl = new LanguageController();
        ResourceBundle bundle = ResourceBundle.getBundle("Translation");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/choose_your_language.fxml"), bundle);
        loader.setController(ctrl);
        Parent root = loader.load();
        Scene scene = new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        scene.getStylesheets().addAll(this.getClass().getResource("/css/style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        stage.toFront();
    }

    //Fix
    @Test
    void test1(FxRobot robot) {
        robot.clickOn("B/H/S");
        robot.clickOn("Prijava");
        robot.clickOn("#emailFld").write("ernah@gmail.com");
        robot.clickOn("#passFld").write("erna123");
        robot.clickOn("Prijavi se");
        robot.clickOn("Klijenti");
        robot.clickOn("Dodaj klijenta");
//        ArrayList<Client> klijenti1 = model.getClients();
        robot.clickOn("#fldIme").write("Kenan");
        robot.clickOn("#fldPrezime").write("Marić");
        robot.clickOn("#fldEmail").write("kenanm@gmail.com");
        robot.clickOn("#fldPassword").write("kenan123");
        robot.clickOn("#datumPicker").write("");
        robot.clickOn("#fldTelefon").write("061789456");
        robot.clickOn("#osobaChoice").clickOn("Anesa Fazlagić");
        robot.clickOn("Dodaj");
//        ArrayList<Client> klijenti2 = model.getClients();
//        assertEquals(klijenti2.size(), klijenti1.size()+1);
    }
}
