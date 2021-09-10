package ba.unsa.etf.rpr.projekat.controller;

import ba.unsa.etf.rpr.projekat.dal.CRMDao;
import ba.unsa.etf.rpr.projekat.model.Task;
import ba.unsa.etf.rpr.projekat.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TaskController {
    public User fotograf;
    public CRMDao model;
    public TableView<Task> tableViewTaskovi;
    public ChoiceBox<String> klijentChoice;
    private final ObservableList<Task> taskovi = FXCollections.observableArrayList();

    public TableColumn<Task, String> colNaziv;
    public TableColumn<Task, String> colOpis;
    public TableColumn<Task, Boolean> colUradjen;
    public TableColumn<Task, Date> colDeadline;

    public TaskController(User fotograf) {
        this.fotograf = fotograf;
    }

    @FXML
    public void initialize() {
        model = CRMDao.getInstance();
        ObservableList<String> klijenti = model.getKlijentZaOdgOsobu(fotograf.getId());
        klijentChoice.setItems(klijenti);

        colNaziv.setCellValueFactory(new PropertyValueFactory<Task, String>("naziv"));
        colOpis.setCellValueFactory(new PropertyValueFactory<Task, String>("opis"));
        colDeadline.setCellFactory(column -> {
            TableCell<Task, Date> cell = new TableCell<Task, Date>() {
                private final SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);
                    if(empty) {
                        setText(null);
                    }
                    else {
                        this.setText(format.format(item));

                    }
                }
            };

            return cell;
        });
        colDeadline.setCellValueFactory(new PropertyValueFactory<Task, Date>("deadline"));
        colUradjen.setCellFactory(tc -> new CheckBoxTableCell<>());

        colUradjen.setCellValueFactory(
                c -> {
                    Task task = c.getValue();
                    CheckBox checkBox = new CheckBox();
                    checkBox.selectedProperty().setValue(task.getChekiran());
                    checkBox
                            .selectedProperty()
                            .addListener((ov, old_val, new_val) -> {
                                task.setChekiran(new_val);
                                model.finishTask(task.getNaziv());
                            });
                    return checkBox.selectedProperty();
                });



        klijentChoice.valueProperty().addListener((obs, oldValue, newValue) -> {
            int id = model.getKlijentId(newValue);
            ArrayList<Task> tasks = model.taskovi(fotograf.getId(), id);
            taskovi.clear();
            taskovi.addAll(tasks);
            tableViewTaskovi.refresh();
            tableViewTaskovi.getItems().removeAll();
            tableViewTaskovi.setItems(taskovi);

        });
    }
    public void okAction(ActionEvent actionEvent) {
        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();    }

}
