package Controller;

import DAO.CRMDao;
import Model.Projekat;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class ProjektiController {
    public CRMDao model;
    public TableView<Projekat> tableViewProjekti;
    private ObservableList<Projekat> projekti = FXCollections.observableArrayList();
    public TableColumn<Projekat, String> colNaziv;
    public TableColumn<Projekat, String> colOdgovornaOsoba;
    public TableColumn<Projekat, String> colKlijent;
    public TableColumn<Projekat, String> colStatus;

    @FXML
    public void initialize() {
        model = CRMDao.getInstance();
        ArrayList<Projekat> projects = model.getProjects();
        colNaziv.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().naziv));
        colOdgovornaOsoba.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().odgovornaOsoba));
        colKlijent.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().klijent));
        colStatus.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().gotov));
        projekti.addAll(projects);
        tableViewProjekti.setItems(projekti);
    }
}
