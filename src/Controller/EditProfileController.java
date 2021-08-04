package Controller;

import Model.Korisnik;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class EditProfileController {
    public Korisnik korisnik;
    public TextField imeFld;
    public TextField prezimeFld;
    public TextField emailFld;
    public TextField passFld;
    public ImageView profileImg;
    private GifController gifController;

    public EditProfileController(Korisnik korisnik) {
        this.korisnik = korisnik;
    }
    @FXML
    public void initialize() {
        imeFld.setText(korisnik.getIme());
        prezimeFld.setText(korisnik.getPrezime());
        emailFld.setText(korisnik.getEmail());
        passFld.setText(korisnik.getPassword());
        Image image;
        if(korisnik.getSlika().equals("/img/blank-profile-picture"))
            image = new Image(getClass().getResourceAsStream(korisnik.getSlika()));
        else
            image = new Image(korisnik.getSlika());

        profileImg.setImage(image);
    }
    public void dajSlike(ActionEvent actionEvent) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource(
//                "/fxml/pretragaslike.fxml"));
//        GifController ctrl = new GifController(korisnik);
//        ctrl.setImageView(profileImg);
//        loader.setController(ctrl);
//        Parent root = loader.load();
//        Stage myStage = new Stage();
//        myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
//        myStage.setResizable(false);
//        myStage.show();
        Stage myStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/fxml/pretragaslike.fxml"));
        Parent root = loader.load();
        gifController = loader.getController();
        gifController.setImageView(profileImg);
        gifController.setTrenutni(korisnik);
        myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        myStage.show();
    }
    public void sacuvajPromjene(ActionEvent actionEvent) {
        korisnik.setSlika(korisnik.getSlika());
        korisnik.setIme(imeFld.getText());
        korisnik.setPrezime(prezimeFld.getText());
        korisnik.setEmail(emailFld.getText());
        korisnik.setPassword(passFld.getText());
        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }
}
