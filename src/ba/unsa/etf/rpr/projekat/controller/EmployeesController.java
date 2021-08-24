package ba.unsa.etf.rpr.projekat.controller;

import ba.unsa.etf.rpr.projekat.dal.CRMDao;
import ba.unsa.etf.rpr.projekat.model.Client;
import ba.unsa.etf.rpr.projekat.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.ArrayList;

public class EmployeesController {
    public CRMDao model;
    public TableView<User> tableViewZaposleni;
    private final ObservableList<User> zaposleni = FXCollections.observableArrayList();
    public Button closeBtn;
    public TableColumn<Client, String> colIme;
    public TableColumn<Client, String> colPrezime;
    public TableColumn<Client, String> colEmail;
    @FXML
    public void initialize() {
        model = CRMDao.getInstance();
        ArrayList<User> employees = model.getEmployees();
        colIme.setCellValueFactory(new PropertyValueFactory<Client, String>("ime"));
        colPrezime.setCellValueFactory(new PropertyValueFactory<Client, String>("prezime"));
        colEmail.setCellValueFactory(new PropertyValueFactory<Client, String>("email"));
        zaposleni.addAll(employees);
        tableViewZaposleni.setItems(zaposleni);

        closeBtn.setOnAction(actionEvent -> close(actionEvent));
    }

    private void close(ActionEvent actionEvent) {
        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }
}
