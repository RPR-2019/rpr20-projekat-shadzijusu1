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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;


public class AddTaskController {
    public Korisnik fotograf;
    public TextField naziv;
    public TextField opis;
    public DatePicker rok;
    public ChoiceBox<String> choiceOdgovornaOsoba;
    public ChoiceBox<String> choiceKlijenti;

    public String koDodaje = "fotograf";
    public CRMDao model;
    public Button dodajBtn;
    public Button closeBtn;
    public ArrayList<Korisnik> uposlenici = new ArrayList<>();
    public ArrayList<Klijent> klijenti = new ArrayList<>();

    public AddTaskController(String koDodaje) {
        this.koDodaje = koDodaje;
    }
    public AddTaskController(Korisnik fotograf) {
        this.fotograf = fotograf;
    }

    @FXML
    public void initialize() {
        if(koDodaje.equals("fotograf"))
            choiceOdgovornaOsoba.setVisible(false);
        model = CRMDao.getInstance();
        uposlenici = model.getEmployees();
        klijenti = model.getClients();
        ObservableList<String> employeeNames = FXCollections.observableArrayList();
        ObservableList<String> clientNames = FXCollections.observableArrayList();

        for(int i = 0; i < uposlenici.size(); i++) {
            employeeNames.add(uposlenici.get(i).getIme() + " " + uposlenici.get(i).getPrezime());
        }
        for(int i = 0; i < klijenti.size(); i++) {
            clientNames.add(klijenti.get(i).getIme() + " " + klijenti.get(i).getPrezime());
        }
        choiceOdgovornaOsoba.setItems(employeeNames);
        choiceKlijenti.setItems(clientNames);
        dodajBtn.setOnAction(actionEvent -> dodajTask(actionEvent));
        closeBtn.setOnAction(actionEvent -> closeAction(actionEvent));
    }


    public void dodajTask(ActionEvent actionEvent) {
        model = CRMDao.getInstance();
        int id = model.getKlijentId(choiceKlijenti.getValue());
        if(koDodaje.equals("fotograf")) {
            model.addTask(naziv.getText(), opis.getText(), rok.getValue(), id, fotograf.getId());
        }
        else {
            //kad vlasnik dodaje i dodjeljuje fotografu
            int fotografId = model.getKorisnikFromName(choiceOdgovornaOsoba.getValue());
            model.addTask(naziv.getText(), opis.getText(), rok.getValue(), id, fotografId);
        }
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
