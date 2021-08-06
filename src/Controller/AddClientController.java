package Controller;

import DAO.CRMDao;
import Model.Korisnik;
import Model.POZICIJA;
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
import org.w3c.dom.Text;

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
        for(int i = 0; i < uposlenici.size(); i++) {
            employeeNames.add(uposlenici.get(i).getIme() + " " + uposlenici.get(i).getPrezime());
        }
        osobaChoice.setItems(employeeNames);
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
        String  prezimeFotografa= nazivTrimed[1];
        System.out.println(imeFotografa);
        System.out.println(prezimeFotografa);
        uposlenici = model.getEmployees();
        for(int i = 0; i < uposlenici.size(); i++) {
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
