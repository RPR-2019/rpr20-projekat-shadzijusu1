package ba.unsa.etf.rpr.projekat.controller;

import ba.unsa.etf.rpr.projekat.dal.CRMDao;
import ba.unsa.etf.rpr.projekat.model.Project;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Locale;

public class ProjectDataController {
    public String naziv;
    public Label nazivProjekta;
    public TextField fldOdgovornaOsoba;
    public Label klijent;
    public TextField fldStatus;
    public CRMDao model;
    public String koGleda;
    public ProjectDataController(String nazivProjekta, String koGleda) {
        this.naziv = nazivProjekta;
        this.koGleda = koGleda;
    }
    public Project projekat;
    public Button okBtn;
    @FXML
    public void initialize() {
        model = CRMDao.getInstance();
        projekat = model.dajProjekat(naziv);
        nazivProjekta.setText(naziv);
        fldOdgovornaOsoba.setText(projekat.getOdgovornaOsoba());
        klijent.setText(projekat.getKlijent());

        if(koGleda.equals("Klijent")) {
            fldOdgovornaOsoba.setEditable(false);
            fldStatus.setEditable(false);
        }

        Locale locale = new Locale("en_US", "US");
        if(Locale.getDefault().getCountry().equals(locale.getCountry())) {
            if(projekat.getGotov().equals("Aktivan"))
            fldStatus.setText("Active");
            else fldStatus.setText("Completed");
        }
        else {
            fldStatus.setText(projekat.getGotov());
        }
        fldOdgovornaOsoba.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()) {
                fldOdgovornaOsoba.getStyleClass().removeAll("poljeNijeIspravno");
                fldOdgovornaOsoba.getStyleClass().add("poljeIspravno");
            } else {
                fldOdgovornaOsoba.getStyleClass().removeAll("poljeIspravno");
                fldOdgovornaOsoba.getStyleClass().add("poljeNijeIspravno");
            }
        });
        fldStatus.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()) {
                fldStatus.getStyleClass().removeAll("poljeNijeIspravno");
                fldStatus.getStyleClass().add("poljeIspravno");
            } else {
                fldStatus.getStyleClass().removeAll("poljeIspravno");
                fldStatus.getStyleClass().add("poljeNijeIspravno");
            }
        });
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
