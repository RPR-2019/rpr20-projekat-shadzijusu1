package ba.unsa.etf.rpr.Controller;

import ba.unsa.etf.rpr.DAO.CRMDao;
import ba.unsa.etf.rpr.Model.Korisnik;
import ba.unsa.etf.rpr.Model.Projekat;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PopupControl;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class FotografController {
    public Label fldIme;
    public Label fldPrezime;
    public Label fldEmail;
    public Label fldPass;
    public ListView<Projekat> mojiProjekti;
    public Label nameFld;
    public Korisnik fotograf;
    public CRMDao model;
    public ImageView profileImg;
    public ListView<String> projektiView;


    public FotografController(Korisnik fotograf) {
        this.fotograf = fotograf;
    }

    @FXML
    public void initialize() {
        model = CRMDao.getInstance();
        fotograf = model.getUser(fotograf.getId());
        nameFld.setText(fotograf.getIme() + " " + fotograf.getPrezime());
        fldIme.setText(fotograf.getIme());
        fldPrezime.setText(fotograf.getPrezime());
        fldEmail.setText(fotograf.getEmail());
        fldPass.setText(fotograf.getPassword());
        Image image;
        if(fotograf.getSlika().equals("/img/blank-profile-picture.png"))
             image = new Image(getClass().getResourceAsStream(fotograf.getSlika()));
        else
         image = new Image(fotograf.getSlika());

        profileImg.setImage(image);
        ObservableList<String> projekti = model.dajProjekte(fotograf.getId());

        projektiView.setItems(projekti);

        projektiView.getSelectionModel().selectedItemProperty().addListener((obs, oldProjekat, newProjekat) -> {
            try {
                openProjectDetails(newProjekat);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void openProjectDetails(String newProjekat) throws IOException {
        Stage myStage = new Stage();
        ResourceBundle bundle = ResourceBundle.getBundle("Translation");
        FXMLLoader loader = new FXMLLoader( getClass().getResource(
                "/fxml/project_detail.fxml" ), bundle);
        ProjectDataController ctrl = new ProjectDataController(newProjekat);
        loader.setController(ctrl);
        Parent root = loader.load();
        myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        myStage.setResizable(false);
        myStage.show();
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
        fldIme.getScene().getWindow().hide();
    }

    public void editProfile(ActionEvent actionEvent) throws IOException {
        Stage myStage = new Stage();
        ResourceBundle bundle = ResourceBundle.getBundle("Translation");
        FXMLLoader loader = new FXMLLoader( getClass().getResource(
                "/fxml/edit_profile.fxml" ), bundle);
        EditProfileController ctrl = new EditProfileController();
        ctrl.setProfileImg(profileImg);
        ctrl.setKorisnik(fotograf);
        ctrl.setTipKorisnika("Fotograf");
        loader.setController(ctrl);
        Parent root = loader.load();
        myStage.setTitle("Uređivanje profila");
        myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        myStage.setResizable(false);
        myStage.show();
        fldIme.getScene().getWindow().hide();

    }
    public void openMail(ActionEvent actionEvent) throws IOException {
        Stage myStage = new Stage();
        ResourceBundle bundle = ResourceBundle.getBundle("Translation");
        FXMLLoader loader = new FXMLLoader( getClass().getResource(
                "/fxml/send_email.fxml" ), bundle);
        Parent root = loader.load();
        myStage.setTitle("Mail");
        myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        myStage.setResizable(false);
        myStage.show();
    }
    public void openTasks(ActionEvent actionEvent) throws IOException {
        Stage myStage = new Stage();
        ResourceBundle bundle = ResourceBundle.getBundle("Translation");
        FXMLLoader loader = new FXMLLoader( getClass().getResource(
                "/fxml/taskovi.fxml" ), bundle);
        TaskController ctrl = new TaskController(fotograf);
        loader.setController(ctrl);
        Parent root = loader.load();
        myStage.setTitle("Taskovi");
        myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        myStage.setResizable(false);
        myStage.show();
    }
    public void addTask(ActionEvent actionEvent) throws IOException {
        Stage myStage = new Stage();
        ResourceBundle bundle = ResourceBundle.getBundle("Translation");
        FXMLLoader loader = new FXMLLoader( getClass().getResource(
                "/fxml/add_task.fxml" ), bundle);
        AddTaskController ctrl = new AddTaskController(fotograf);
        loader.setController(ctrl);
        Parent root = loader.load();
        myStage.setTitle("Dodaj task");
        myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        myStage.setResizable(false);
        myStage.show();
    }
}
