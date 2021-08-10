package ba.unsa.etf.rpr.Controller;

import ba.unsa.etf.rpr.DAO.CRMDao;
import ba.unsa.etf.rpr.Model.Klijent;
import ba.unsa.etf.rpr.Model.Korisnik;
import ba.unsa.etf.rpr.Model.Task;
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
    public Korisnik fotograf;
    public CRMDao model;
    public TableView<Task> tableViewTaskovi;
    public ChoiceBox<String> klijentChoice;
    private ObservableList<Task> taskovi = FXCollections.observableArrayList();

    public TableColumn<Task, String> colNaziv;
    public TableColumn<Task, String> colOpis;
    public TableColumn<Task, Boolean> colUradjen;
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
        colDeadline.setCellFactory(column -> {
            TableCell<Task, Date> cell = new TableCell<Task, Date>() {
                private SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

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
                    checkBox.selectedProperty().setValue(task.chekiran);
                    checkBox
                            .selectedProperty()
                            .addListener((ov, old_val, new_val) -> {
                                task.setChekiran(new_val);
                                model.finishTask(task.naziv);
                            });
                    return checkBox.selectedProperty();
                });



        klijentChoice.valueProperty().addListener((obs, oldValue, newValue) -> {

            int id = model.getKlijentId(newValue);
            ArrayList<Task> tasks = model.taskovi(fotograf.getId(), id);
            taskovi.addAll(tasks);
            tableViewTaskovi.setItems(taskovi);

        });
    }
    public void okAction(ActionEvent actionEvent) {
        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();    }

}
