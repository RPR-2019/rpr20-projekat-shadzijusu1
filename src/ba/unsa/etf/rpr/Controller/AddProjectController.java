package ba.unsa.etf.rpr.Controller;

import ba.unsa.etf.rpr.DAO.CRMDao;
import ba.unsa.etf.rpr.Model.Klijent;
import ba.unsa.etf.rpr.Model.Korisnik;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;

public class AddProjectController {
    public TextField fldNaziv;
    public ChoiceBox<String> choiceKlijenti;
    public ChoiceBox<String> choiceOdgovornaOsoba;
    public Button okBtn;
    public Button closeBtn;
    public CRMDao model;
    public ArrayList<Korisnik> uposlenici = new ArrayList<>();
    public ArrayList<Klijent> klijenti = new ArrayList<>();

    @FXML
    public void initialize() {
        model = CRMDao.getInstance();
        uposlenici = model.getEmployees();
        klijenti = model.getClients();
        ObservableList<String> employeeNames = FXCollections.observableArrayList();
        ObservableList<String> clientNames = FXCollections.observableArrayList();

        for (int i = 0; i < uposlenici.size(); i++) {
            employeeNames.add(uposlenici.get(i).getIme() + " " + uposlenici.get(i).getPrezime());
        }
        for (int i = 0; i < klijenti.size(); i++) {
            clientNames.add(klijenti.get(i).getIme() + " " + klijenti.get(i).getPrezime());
        }
        choiceOdgovornaOsoba.setItems(employeeNames);
        choiceKlijenti.setItems(clientNames);

        fldNaziv.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()) {
                fldNaziv.getStyleClass().removeAll("poljeNijeIspravno");
                fldNaziv.getStyleClass().add("poljeIspravno");
            } else {
                fldNaziv.getStyleClass().removeAll("poljeIspravno");
                fldNaziv.getStyleClass().add("poljeNijeIspravno");
            }
        });
        okBtn.setOnAction(actionEvent -> addAction(actionEvent));
        closeBtn.setOnAction(actionEvent -> closeAction(actionEvent));
    }

    private void addAction(ActionEvent actionEvent) {
        int klijentId = model.getKlijentId(choiceKlijenti.getValue());
        int odgovornaOsobaId = model.getKorisnikFromName(choiceOdgovornaOsoba.getValue());
        model.addProject(klijentId, fldNaziv.getText(), odgovornaOsobaId);

        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }

    private void closeAction(ActionEvent actionEvent) {
        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }

}
