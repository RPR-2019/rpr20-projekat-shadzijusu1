package ba.unsa.etf.rpr.projekat.controller;

import ba.unsa.etf.rpr.projekat.dao.CRMDao;
import ba.unsa.etf.rpr.projekat.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PopupControl;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class OwnerController {
    public User vlasnik;
    public CRMDao model;
    public Label imeFld;
    public Label prezimeFld;
    public Label emailFld;
    public Label passwordFld;
    public Label nameFld;
    public ImageView profileImg;
    public OwnerController(User vlasnik) {
        this.vlasnik = vlasnik;
    }
    @FXML
    public void initialize() {
        model = CRMDao.getInstance();
        vlasnik = model.getUser(vlasnik.getId());
        nameFld.setText(vlasnik.getIme() + " " + vlasnik.getPrezime());
        imeFld.setText(vlasnik.getIme());
        prezimeFld.setText(vlasnik.getPrezime());
        emailFld.setText(vlasnik.getEmail());
        passwordFld.setText(vlasnik.getPassword());
        Image image;
        if(vlasnik.getSlika().equals("/img/blank-profile-picture.png"))
        image = new Image(getClass().getResourceAsStream(vlasnik.getSlika()));
        else
            image = new Image(vlasnik.getSlika());
        profileImg.setImage(image);

    }
    public void odjaviSe(ActionEvent actionEvent) throws IOException {
        Stage myStage = new Stage();
        LanguageController ctrl = new LanguageController();
        ResourceBundle bundle = ResourceBundle.getBundle("Translation");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/choose_your_language.fxml"), bundle);
        loader.setController(ctrl);
        Parent root = loader.load();
        Scene scene = new Scene(root, PopupControl.USE_COMPUTED_SIZE, PopupControl.USE_COMPUTED_SIZE);
        scene.getStylesheets().addAll(this.getClass().getResource("/css/style.css").toExternalForm());
        myStage.setScene(scene);
        myStage.show();
        myStage.toFront();
        imeFld.getScene().getWindow().hide();

    }

    public void editProfile(ActionEvent actionEvent) throws IOException {
        Stage myStage = new Stage();
        ResourceBundle bundle = ResourceBundle.getBundle("Translation");
        FXMLLoader loader = new FXMLLoader( getClass().getResource(
                "/fxml/edit_profile.fxml" ), bundle);
        EditProfileController ctrl = new EditProfileController();
        ctrl.setProfileImg(profileImg);
        ctrl.setKorisnik(vlasnik);
        ctrl.setTipKorisnika("Vlasnik");
        loader.setController(ctrl);
        Parent root = loader.load();
        myStage.setTitle(bundle.getString("edit_profile"));
        myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        myStage.setResizable(false);
        myStage.show();
        imeFld.getScene().getWindow().hide();

    }
    public void openMail(ActionEvent actionEvent) throws IOException {
        Stage myStage = new Stage();
        ResourceBundle bundle = ResourceBundle.getBundle("Translation");
        FXMLLoader loader = new FXMLLoader( getClass().getResource(
                "/fxml/send_email.fxml" ), bundle);
        Parent root = loader.load();
        myStage.setTitle(bundle.getString("posta"));
        myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        myStage.setResizable(false);
        myStage.show();
    }
    public void activeClients(ActionEvent actionEvent) throws IOException {
        Stage myStage = new Stage();
        ResourceBundle bundle = ResourceBundle.getBundle("Translation");
        FXMLLoader loader = new FXMLLoader( getClass().getResource(
                "/fxml/clients.fxml"), bundle);
        ClientTableController ctrl = new ClientTableController("Aktivan");
        loader.setController(ctrl);
        Parent root = loader.load();
        myStage.setTitle(bundle.getString("active_clients"));
        myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        myStage.setResizable(false);
        myStage.show();
    }
    public void archivedClients(ActionEvent actionEvent) throws IOException {
        Stage myStage = new Stage();
        ResourceBundle bundle = ResourceBundle.getBundle("Translation");
        FXMLLoader loader = new FXMLLoader( getClass().getResource(
                "/fxml/clients.fxml"), bundle);
        ClientTableController ctrl = new ClientTableController("Neaktivan");
        loader.setController(ctrl);
        Parent root = loader.load();
        myStage.setTitle(bundle.getString("archived_clients"));
        myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        myStage.setResizable(false);
        myStage.show();
    }
    public void employeesTable(ActionEvent actionEvent) throws IOException {
        Stage myStage = new Stage();
        ResourceBundle bundle = ResourceBundle.getBundle("Translation");
        FXMLLoader loader = new FXMLLoader( getClass().getResource(
                "/fxml/employees.fxml"), bundle);
        EmployeesController ctrl = new EmployeesController();
        loader.setController(ctrl);
        Parent root = loader.load();
        myStage.setTitle(bundle.getString("employees"));
        myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        myStage.setResizable(false);
        myStage.show();
    }
    public void projectsTable(ActionEvent actionEvent) throws IOException {
        Stage myStage = new Stage();
        ResourceBundle bundle = ResourceBundle.getBundle("Translation");
        FXMLLoader loader = new FXMLLoader( getClass().getResource(
                "/fxml/projects.fxml"), bundle);
        ProjectTableController ctrl = new ProjectTableController();
        loader.setController(ctrl);
        Parent root = loader.load();
        myStage.setTitle(bundle.getString("projekti"));
        myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        myStage.setResizable(false);
        myStage.show();
    }
    public void addEmployee(ActionEvent actionEvent) throws IOException {
        Stage myStage = new Stage();
        ResourceBundle bundle = ResourceBundle.getBundle("Translation");
        FXMLLoader loader = new FXMLLoader( getClass().getResource(
                "/fxml/add_employee.fxml"), bundle);
        AddEmployeeController ctrl = new AddEmployeeController();
        loader.setController(ctrl);
        Parent root = loader.load();
        myStage.setTitle(bundle.getString("adding_employee"));
        myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        myStage.setResizable(false);
        myStage.show();
    }
    public void dodajKlijenta(ActionEvent actionEvent) throws IOException {
        Stage myStage = new Stage();
        ResourceBundle bundle = ResourceBundle.getBundle("Translation");
        FXMLLoader loader = new FXMLLoader( getClass().getResource(
                "/fxml/add_client.fxml"), bundle);
        AddClientController ctrl = new AddClientController();
        loader.setController(ctrl);
        Parent root = loader.load();
        myStage.setTitle(bundle.getString("adding_client"));
        myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        myStage.setResizable(false);
        myStage.show();
        myStage.toFront();
    }
    public void addProject(ActionEvent actionEvent) throws IOException {
        Stage myStage = new Stage();
        ResourceBundle bundle = ResourceBundle.getBundle("Translation");
        FXMLLoader loader = new FXMLLoader( getClass().getResource(
                "/fxml/add_project.fxml" ), bundle);
        AddProjectController ctrl = new AddProjectController();
        loader.setController(ctrl);
        Parent root = loader.load();
        myStage.setTitle(bundle.getString("adding_project"));
        myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        myStage.setResizable(false);
        myStage.show();
    }

    public void addTask(ActionEvent actionEvent) throws IOException {
        Stage myStage = new Stage();
        ResourceBundle bundle = ResourceBundle.getBundle("Translation");
        FXMLLoader loader = new FXMLLoader( getClass().getResource(
                "/fxml/add_task.fxml" ), bundle);
        AddTaskController ctrl = new AddTaskController("Vlasnik");
        loader.setController(ctrl);
        Parent root = loader.load();
        myStage.setTitle(bundle.getString("adding_task"));
        myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        myStage.setResizable(false);
        myStage.show();
    }


}
