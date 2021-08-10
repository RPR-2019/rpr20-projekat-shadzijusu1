package ba.unsa.etf.rpr.Controller;

import ba.unsa.etf.rpr.DAO.CRMDao;
import ba.unsa.etf.rpr.Model.Korisnik;
import ba.unsa.etf.rpr.Model.POZICIJA;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;


public class AddClientController {
    public CRMDao model;
    public TextField fldIme;
    public TextField fldPrezime;
    public TextField fldEmail;
    public TextField fldPassword;
    public DatePicker datumPicker;
    public TextField fldTelefon;
    public ChoiceBox<String> osobaChoice;
    public Button addUserBtn;
    public Button closeBtn;
    public ArrayList<Korisnik> uposlenici = new ArrayList<>();

    @FXML
    public void initialize() {
        model = CRMDao.getInstance();
        uposlenici = model.getEmployees();
        ObservableList<String> employeeNames = FXCollections.observableArrayList();
        for (int i = 0; i < uposlenici.size(); i++) {
            employeeNames.add(uposlenici.get(i).getIme() + " " + uposlenici.get(i).getPrezime());
        }
        osobaChoice.setItems(employeeNames);
        fldIme.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()) {
                fldIme.getStyleClass().removeAll("poljeNijeIspravno");
                fldIme.getStyleClass().add("poljeIspravno");
            } else {
                fldIme.getStyleClass().removeAll("poljeIspravno");
                fldIme.getStyleClass().add("poljeNijeIspravno");
            }
        });

        fldPrezime.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()) {
                fldPrezime.getStyleClass().removeAll("poljeNijeIspravno");
                fldPrezime.getStyleClass().add("poljeIspravno");
            } else {
                fldPrezime.getStyleClass().removeAll("poljeIspravno");
                fldPrezime.getStyleClass().add("poljeNijeIspravno");
            }
        });

        fldEmail.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()) {
                fldEmail.getStyleClass().removeAll("poljeNijeIspravno");
                fldEmail.getStyleClass().add("poljeIspravno");
            } else {
                fldEmail.getStyleClass().removeAll("poljeIspravno");
                fldEmail.getStyleClass().add("poljeNijeIspravno");
            }
        });

        fldPassword.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()) {
                fldPassword.getStyleClass().removeAll("poljeNijeIspravno");
                fldPassword.getStyleClass().add("poljeIspravno");
            } else {
                fldPassword.getStyleClass().removeAll("poljeIspravno");
                fldPassword.getStyleClass().add("poljeNijeIspravno");
            }
        });
        fldTelefon.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()) {
                fldTelefon.getStyleClass().removeAll("poljeNijeIspravno");
                fldTelefon.getStyleClass().add("poljeIspravno");
            } else {
                fldTelefon.getStyleClass().removeAll("poljeIspravno");
                fldTelefon.getStyleClass().add("poljeNijeIspravno");
            }
        });
        addUserBtn.setOnAction(event -> addUser(event));
        closeBtn.setOnAction(actionEvent -> closeAction(actionEvent));
    }

    private void closeAction(ActionEvent actionEvent) {
        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }

    private void addUser(javafx.event.ActionEvent event) {
        String ime = fldIme.getText();
        String prezime = fldPrezime.getText();
        String email = fldEmail.getText();
        String password = fldPassword.getText();
        model.dodajKorisnika(ime, prezime, email, password, POZICIJA.Klijent);
        String osoba = osobaChoice.getValue();
        String[] nazivTrimed = osoba.split(" ");
        String imeFotografa = nazivTrimed[0];
        String prezimeFotografa = nazivTrimed[1];
        uposlenici = model.getEmployees();
        for (int i = 0; i < uposlenici.size(); i++) {
            if (uposlenici.get(i).getIme().equals(imeFotografa) &&
                    uposlenici.get(i).getPrezime().equals(prezimeFotografa)) {
                model.addKlijentInfo(datumPicker.getValue(), fldTelefon.getText(), uposlenici.get(i).getId());
                break;
            }
        }
        Node n = (Node) event.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }


}
