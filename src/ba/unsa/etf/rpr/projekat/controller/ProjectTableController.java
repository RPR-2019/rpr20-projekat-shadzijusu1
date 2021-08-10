package ba.unsa.etf.rpr.projekat.controller;

import ba.unsa.etf.rpr.projekat.dao.CRMDao;
import ba.unsa.etf.rpr.projekat.model.Project;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class ProjectTableController {
    public CRMDao model;
    public TableView<Project> tableViewProjekti;
    private final ObservableList<Project> projekti = FXCollections.observableArrayList();
    public TableColumn<Project, String> colNaziv;
    public TableColumn<Project, String> colOdgovornaOsoba;
    public TableColumn<Project, String> colKlijent;
    public TableColumn<Project, String> colStatus;
    public Button closeBtn;

    @FXML
    public void initialize() {
        model = CRMDao.getInstance();
        ArrayList<Project> projects = model.getProjects();
        colNaziv.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNaziv()));
        colOdgovornaOsoba.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getOdgovornaOsoba()));
        colKlijent.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getKlijent()));
        colStatus.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getGotov()));
        projekti.addAll(projects);
        tableViewProjekti.setItems(projekti);

        tableViewProjekti.getSelectionModel().selectedItemProperty().addListener(
                ((observableValue, stariProjekat, noviProjekat) -> {
                    try {
                        openProjectDetails(noviProjekat.getNaziv());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                })
        );
        closeBtn.setOnAction(actionEvent -> close(actionEvent));
    }

    private void openProjectDetails(String naziv) throws IOException {
        Stage myStage = new Stage();
        ResourceBundle bundle = ResourceBundle.getBundle("Translation");
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/fxml/project_detail.fxml"), bundle);
        ProjectDataController ctrl = new ProjectDataController(naziv);
        loader.setController(ctrl);
        Parent root = loader.load();
        myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        myStage.setResizable(false);
        myStage.show();
    }

    public void close(ActionEvent actionEvent) {
        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }
}
