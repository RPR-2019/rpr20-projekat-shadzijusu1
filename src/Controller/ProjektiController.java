package Controller;

import DAO.CRMDao;
import Model.Projekat;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

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

        tableViewProjekti.getSelectionModel().selectedItemProperty().addListener(
                ((observableValue, stariProjekat, noviProjekat) -> {
                    try {
                        openProjectDetails(noviProjekat.naziv);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                })
        );
    }

    private void openProjectDetails(String naziv) throws IOException {
        Stage myStage = new Stage();
        ResourceBundle bundle = ResourceBundle.getBundle("Translation");
        FXMLLoader loader = new FXMLLoader( getClass().getResource(
                "/fxml/project_detail.fxml" ), bundle);
        ProjectDataController ctrl = new ProjectDataController(naziv);
        loader.setController(ctrl);
        Parent root = loader.load();
        myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        myStage.setResizable(false);
        myStage.show();
    }
}
