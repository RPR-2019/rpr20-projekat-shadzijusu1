package Controller;

import Model.Korisnik;
import at.mukprojects.giphy4j.Giphy;
import at.mukprojects.giphy4j.entity.giphy.GiphyData;
import at.mukprojects.giphy4j.entity.search.SearchFeed;
import at.mukprojects.giphy4j.exception.GiphyException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class GifController {
    public TextField fldSearch;
    public TilePane pane;
    private String choosenBtnUrl;
    private Korisnik trenutni;
    private boolean choosenBtn = false;
    private ImageView imageView;

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public GifController() {
    }

    public GifController(Korisnik trenutni) {
        this.trenutni = trenutni;
    }

    public void clickCancel(ActionEvent actionEvent) {
        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }

    public boolean isChoosenBtn() {
        return choosenBtn;
    }

    public void setChoosenBtn(boolean choosenBtn) {
        this.choosenBtn = choosenBtn;
    }

    public void setTrenutni(Korisnik trenutniKorisnik) {
        trenutni = trenutniKorisnik;
    }

    public Korisnik getTrenutni() {
        return trenutni;
    }

    public void clickOk(ActionEvent actionEvent) {
        if (choosenBtn) {
            trenutni.setSlika(choosenBtnUrl);
            Image image = new Image(trenutni.getSlika());
            imageView.setImage(image);

            Node n = (Node) actionEvent.getSource();
            Stage stage = (Stage) n.getScene().getWindow();
            stage.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Nijedna slika nije izabrana");
            alert.setHeaderText("Niste izabrali sliku koju želite");
            alert.setContentText("Unesite pretragu a zatim izaberite sliku");
            alert.showAndWait();
        }
    }

    public void clickSearch(ActionEvent actionEvent) {
        pane.getChildren().clear();
        Button btnn = new Button();
        pane.getChildren().add(btnn);
        Platform.runLater(() -> {
            new Thread(() -> {
                Giphy giphy = new Giphy("QRG3k0GKGjf16SqBDFUqy58C9D0bMLnm");
                SearchFeed feed = null;
                try {
                    feed = giphy.search(fldSearch.getText(), 25, 0);
                } catch (GiphyException e) {
                    e.printStackTrace();
                }
                for (GiphyData data : feed.getDataList()) {
                    String imageUrl = "https://i.giphy.com/media/" + data.getId() + "/giphy_s.gif";
                    ImageView iv = new ImageView(new Image(imageUrl));
                    iv.setFitHeight(128);
                    iv.setFitWidth(128);
                    Button btn = new Button();
                    btn.setGraphic(iv);
                    btn.setOnAction(action -> {
                        choosenBtn = true;
                        choosenBtnUrl = imageUrl;
                    });
                    Platform.runLater(() -> {
                        pane.getChildren().remove(btnn);
                        pane.getChildren().add(btn);
                    });
                }
            }).start();
        });
    }
}