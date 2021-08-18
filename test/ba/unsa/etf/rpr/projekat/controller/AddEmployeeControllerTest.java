package ba.unsa.etf.rpr.projekat.controller;

import ba.unsa.etf.rpr.projekat.dao.CRMDao;
import ba.unsa.etf.rpr.projekat.model.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.util.ArrayList;
import java.util.ResourceBundle;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(ApplicationExtension.class)
class AddEmployeeControllerTest {

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

    @Test
    void addEmployeeTest(FxRobot robot) {
        ArrayList<User> employeesBefore = model.getEmployees();
        robot.clickOn("B/H/S");
        robot.clickOn("Prijava");
        robot.clickOn("#emailFld").write("rprstudios@gmail.com");
        robot.clickOn("#passFld").write("rpr1234");
        robot.clickOn("Prijavi se");
        robot.clickOn("Zaposlenici");
        robot.clickOn("Dodaj zaposlenog");
        robot.clickOn("#fldIme").write("Kenan");
        robot.clickOn("#fldPrezime").write("Marić");
        robot.clickOn("#fldEmail").write("kenanm@gmail.com");
        robot.clickOn("#fldPassword").write("kenan123");
        robot.clickOn("Dodaj");
        ArrayList<User> employeesAfter = model.getEmployees();
        assertEquals(employeesBefore.size()+1, employeesAfter.size());
    }

}