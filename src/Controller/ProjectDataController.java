package Controller;

import DAO.CRMDao;
import Model.Projekat;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ProjectDataController {
    public String naziv;
    public Label nazivProjekta;
    public Label odgovornaOsoba;
    public Label klijent;
    public Label status;
    public CRMDao model;
    public ProjectDataController(String nazivProjekta) {
        this.naziv = nazivProjekta;
    }
    @FXML
    public void initialize() {
        model = CRMDao.getInstance();
        Projekat projekat = model.dajProjekat(naziv);
        nazivProjekta.setText(naziv);
        odgovornaOsoba.setText(projekat.odgovornaOsoba);
        klijent.setText(projekat.klijent);
        status.setText(projekat.gotov);
    }
        public void close(ActionEvent actionEvent) {
            Node n = (Node) actionEvent.getSource();
            Stage stage = (Stage) n.getScene().getWindow();
            stage.close();
        }

}
