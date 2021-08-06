package Controller;

import DAO.CRMDao;
import Model.Projekat;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ProjectDataController {
    public String naziv;
    public Label nazivProjekta;
    public TextField fldOdgovornaOsoba;
    public Label klijent;
    public TextField fldStatus;
    public CRMDao model;
    public ProjectDataController(String nazivProjekta) {
        this.naziv = nazivProjekta;
    }
    public Projekat projekat;
    public Button okBtn;
    @FXML
    public void initialize() {
        model = CRMDao.getInstance();
        projekat = model.dajProjekat(naziv);
        nazivProjekta.setText(naziv);
        fldOdgovornaOsoba.setText(projekat.odgovornaOsoba);
        klijent.setText(projekat.klijent);
        fldStatus.setText(projekat.gotov);
        okBtn.setOnAction(actionEvent -> okAction(actionEvent));
    }
        public void okAction(ActionEvent actionEvent) {
            String odgovornaOsoba = fldOdgovornaOsoba.getText();
            String status = fldStatus.getText();
            int odgovornaOsobaId = model.getKorisnikFromName(odgovornaOsoba);
            int gotov = 0;
            if(status.equals("Završen"))
                gotov = 1;
            model.updateProject(odgovornaOsobaId, gotov, naziv);


            if(status.equals("Završen")) {
                int klijentId = model.getKlijentId(klijent.getText());
                model.arhivirajKlijenta(klijentId);
            }
            Node n = (Node) actionEvent.getSource();
            Stage stage = (Stage) n.getScene().getWindow();
            stage.close();
        }

}
