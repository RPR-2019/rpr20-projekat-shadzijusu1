package Controller;

import DAO.CRMDao;
import Model.Klijent;
import Model.Korisnik;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.Date;

public class UposleniciController {
    public CRMDao model;
    public TableView<Korisnik> tableViewZaposleni;
    private ObservableList<Korisnik> zaposleni = FXCollections.observableArrayList();

    public TableColumn<Klijent, String> colIme;
    public TableColumn<Klijent, String> colPrezime;
    public TableColumn<Klijent, String> colEmail;
    @FXML
    public void initialize() {
        model = CRMDao.getInstance();
        ArrayList<Korisnik> employees = model.getEmployees();
        colIme.setCellValueFactory(new PropertyValueFactory<Klijent, String>("ime"));
        colPrezime.setCellValueFactory(new PropertyValueFactory<Klijent, String>("prezime"));
        colEmail.setCellValueFactory(new PropertyValueFactory<Klijent, String>("email"));
        zaposleni.addAll(employees);
        tableViewZaposleni.setItems(zaposleni);
    }
}