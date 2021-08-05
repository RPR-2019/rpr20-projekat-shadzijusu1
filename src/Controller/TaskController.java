package Controller;

import DAO.CRMDao;
import Model.Korisnik;
import Model.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Date;

public class TaskController {
    public Korisnik fotograf;
    public CRMDao model;
    public TableView<Task> tableViewTaskovi;
    public ChoiceBox<String> klijentChoice;
    private ObservableList<Task> taskovi = FXCollections.observableArrayList();

    public TableColumn<Task, String> colNaziv;
    public TableColumn<Task, String> colOpis;
    public TableColumn<Task, CheckBox> colUradjen;
    public TableColumn<Task, Date> colDeadline;

    public TaskController(Korisnik fotograf) {
        this.fotograf = fotograf;
    }

    @FXML
    public void initialize() {
        model = CRMDao.getInstance();
        ObservableList<String> klijenti = model.getKlijentZaOdgOsobu(fotograf.getId());
        klijentChoice.setItems(klijenti);

        colNaziv.setCellValueFactory(new PropertyValueFactory<Task, String>("naziv"));
        colOpis.setCellValueFactory(new PropertyValueFactory<Task, String>("opis"));
        colDeadline.setCellValueFactory(new PropertyValueFactory<Task, Date>("deadline"));

        klijentChoice.valueProperty().addListener((obs, oldValue, newValue) -> {
            int id = model.getKlijentId(newValue);
            ArrayList<Task> tasks = model.taskovi(fotograf.getId(), id);
            taskovi.addAll(tasks);
            tableViewTaskovi.setItems(taskovi);
        });
    }
    public void okBtn(ActionEvent actionEvent) {
        System.out.println("implement later");
    }
    public void cancelBtn(ActionEvent actionEvent) {
        System.out.println("implement later");
    }
}
