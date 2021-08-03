package DAO;

import Model.Korisnik;
import Model.POZICIJA;
import javafx.scene.control.Alert;

import java.sql.*;
import java.util.Random;

public class CRMDao {
    private static CRMDao instance = null;
    private Connection conn;
    private PreparedStatement dodajKorisnika, dajKorisnika;

    private CRMDao() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:baza.db");
            dodajKorisnika = conn.prepareStatement("INSERT INTO Korisnik VALUES(?,?,?,?,?,?)");
            dajKorisnika = conn.prepareStatement("SELECT ime, prezime, email, password, pozicija FROM Korisnik where" +
                    " email=? and password=?");
        } catch (
                SQLException e) {

        }

    }

    public static CRMDao getInstance() {
        if (instance == null) instance = new CRMDao();
        return instance;
    }


    public static void removeInstance() {
        if (instance != null) {
            try {
                instance.conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        instance = null;
    }


    public void dodajKorisnika(String ime, String prezime, String email, String password, POZICIJA pozicija) {

        try {
            dodajKorisnika.setString(1, ime);
            dodajKorisnika.setString(2, prezime);
            dodajKorisnika.setString(3, email);
            dodajKorisnika.setString(4, password);
            dodajKorisnika.setString(5, pozicija.toString());
            Random random = new Random();
            dodajKorisnika.setInt(6, random.nextInt(2555));
            dodajKorisnika.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Korisnik getKorisnik(String email, String pass) {
        Korisnik k = null;
        try {
            dajKorisnika.setString(1, email);
            dajKorisnika.setString(2, pass);

            ResultSet result = dajKorisnika.executeQuery();
//            if(result.next() == false) {
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setTitle("Greška");
//                alert.setHeaderText("Neispravan email ili password.");
//                alert.setContentText("Pokušajte ponovo!");
//
//                alert.showAndWait();
//                return k;
//            }
            while (result.next()) {
                String ime = result.getString(1);
                String prezime = result.getString(2);
                String poz = result.getString(5);
                POZICIJA pozicija = POZICIJA.Klijent;
                if(poz.equals("Vlasnik"))
                    pozicija = POZICIJA.Vlasnik;
                else if(poz.equals("Fotograf"))
                    pozicija = POZICIJA.Fotograf;
                k = new Korisnik(ime, prezime, email, pass, pozicija);
                return k;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return k;
    }
}
