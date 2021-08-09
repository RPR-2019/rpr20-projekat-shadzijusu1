package ba.unsa.etf.rpr.Controller;

import ba.unsa.etf.rpr.DAO.CRMDao;
import ba.unsa.etf.rpr.Model.POZICIJA;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddEmployeeController {
    public CRMDao model;
    public TextField fldIme;
    public TextField fldPrezime;
    public TextField fldEmail;
    public TextField fldPassword;
    public Button addEmployeeBtn;
    public Button closeBtn;
    @FXML
    public void initialize() {
        addEmployeeBtn.setOnAction(actionEvent -> addEmployee(actionEvent));
        closeBtn.setOnAction(actionEvent -> closeAction(actionEvent));
    }
    private void closeAction(ActionEvent actionEvent) {
        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }

    private void addEmployee(ActionEvent actionEvent) {
        model = CRMDao.getInstance();
        model.dodajKorisnika(fldIme.getText(), fldPrezime.getText(), fldEmail.getText(), fldPassword.getText(), POZICIJA.Fotograf);
        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }
}
