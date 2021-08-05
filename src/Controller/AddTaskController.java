package Controller;

import DAO.CRMDao;
import Model.Korisnik;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class AddTaskController {
    public Korisnik fotograf;
    public TextField naziv;
    public TextField opis;
    public DatePicker rok;
    public TextField klijent;
    public CRMDao model;
    public AddTaskController(Korisnik fotograf) {
        this.fotograf = fotograf;
    }
    public void dodajTask(ActionEvent actionEvent) {
        model = CRMDao.getInstance();
        int id = model.getKlijentId(klijent.getText());
        model.addTask(naziv.getText(), opis.getText(), rok.getValue(),id, fotograf.getId());
        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }
    public void closeAction(ActionEvent actionEvent) {
        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }
}
