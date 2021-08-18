package ba.unsa.etf.rpr.projekat.controller;

import ba.unsa.etf.rpr.projekat.dao.CRMDao;
import ba.unsa.etf.rpr.projekat.model.Client;
import ba.unsa.etf.rpr.projekat.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;


public class AddTaskController {
    public User fotograf;
    public TextField naziv;
    public TextField opis;
    public DatePicker rok;
    public ChoiceBox<String> choiceOdgovornaOsoba;
    public ChoiceBox<String> choiceKlijenti;
    public Label labelOsoba;
    public String koDodaje = "fotograf";
    public CRMDao model;
    public Button dodajBtn;
    public Button closeBtn;
    public ArrayList<User> uposlenici = new ArrayList<>();
    public ArrayList<Client> klijenti = new ArrayList<>();

    public AddTaskController(String koDodaje) {
        this.koDodaje = koDodaje;
    }
    public AddTaskController(User fotograf) {
        this.fotograf = fotograf;
    }

    @FXML
    public void initialize() {
        if (!koDodaje.equals("fotograf")) {
        } else {
            choiceOdgovornaOsoba.setVisible(false);
            labelOsoba.setVisible(false);
        }
        model = CRMDao.getInstance();
        uposlenici = model.getEmployees();
        klijenti = model.getClients();
        ObservableList<String> employeeNames = FXCollections.observableArrayList();
        ObservableList<String> clientNames = FXCollections.observableArrayList();

        for (User user : uposlenici) {
            employeeNames.add(user.getIme() + " " + user.getPrezime());
        }
        for (Client client : klijenti) {
            clientNames.add(client.getIme() + " " + client.getPrezime());
        }
        choiceOdgovornaOsoba.setItems(employeeNames);
        choiceKlijenti.setItems(clientNames);
        naziv.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()) {
                naziv.getStyleClass().removeAll("poljeNijeIspravno");
                naziv.getStyleClass().add("poljeIspravno");
            } else {
                naziv.getStyleClass().removeAll("poljeIspravno");
                naziv.getStyleClass().add("poljeNijeIspravno");
            }
        });
        opis.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()) {
                opis.getStyleClass().removeAll("poljeNijeIspravno");
                opis.getStyleClass().add("poljeIspravno");
            } else {
                opis.getStyleClass().removeAll("poljeIspravno");
                opis.getStyleClass().add("poljeNijeIspravno");
            }
        });

        dodajBtn.setOnAction(actionEvent -> dodajTask(actionEvent));
        closeBtn.setOnAction(actionEvent -> closeAction(actionEvent));
    }


    public void dodajTask(ActionEvent actionEvent) {
        model = CRMDao.getInstance();
        var id = model.getKlijentId(choiceKlijenti.getValue());
        if(koDodaje.equals("fotograf")) {
            if(rok.getValue() != null) {
                model.addTask(naziv.getText(), opis.getText(), rok.getValue(), id, fotograf.getId());
            }
            else
                model.addTask(naziv.getText(), opis.getText(), LocalDate.parse("2021-12-12"), id, fotograf.getId());

        }
        else {
            //kad vlasnik dodaje i dodjeljuje fotografu
            int fotografId = model.getKorisnikFromName(choiceOdgovornaOsoba.getValue());
            if(rok.getValue() != null) {
                model.addTask(naziv.getText(), opis.getText(), rok.getValue(), id, fotografId);
            }
            else
                model.addTask(naziv.getText(), opis.getText(), LocalDate.parse("2021-12-12"), id, fotografId);
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
