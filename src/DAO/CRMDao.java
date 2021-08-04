package DAO;

import Model.Korisnik;
import Model.POZICIJA;
import javafx.scene.control.Alert;

import java.sql.*;
import java.util.Random;

public class CRMDao {
    private static CRMDao instance = null;
    private Connection conn;
    private static PreparedStatement dodajKorisnika, dajKorisnika, postaviSliku, postaviIme, postaviPrezime,
    postaviMail, postaviPass;

    private CRMDao() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:baza.db");
            dodajKorisnika = conn.prepareStatement("INSERT INTO Korisnik VALUES(?,?,?,?,?,?,?)");
            dajKorisnika = conn.prepareStatement("SELECT ime, prezime, email, password, pozicija, slika FROM Korisnik where" +
                    " email=? and password=?");
            postaviSliku = conn.prepareStatement("UPDATE Korisnik SET slika=? where email=?");
            postaviIme = conn.prepareStatement("UPDATE Korisnik SET ime=? where email=?");
            postaviPrezime = conn.prepareStatement("UPDATE Korisnik SET prezime=? where email=?");
            postaviMail = conn.prepareStatement("UPDATE Korisnik SET email=? where ime=? and prezime=? and password=?");
            postaviPass = conn.prepareStatement("UPDATE Korisnik SET password=? where email=?");


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
            dodajKorisnika.setString(6, "/img/blank-profile-picture.png");
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
                String slika = result.getString(6);
                if(poz.equals("Vlasnik"))
                    pozicija = POZICIJA.Vlasnik;
                else if(poz.equals("Fotograf"))
                    pozicija = POZICIJA.Fotograf;
                k = new Korisnik(ime, prezime, email, pass, pozicija, slika);
                return k;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return k;
    }
    public static void postaviSliku(String slika, String email) {
        try {
            postaviSliku.setString(1, slika);
            postaviSliku.setString(2, email);
            postaviSliku.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void postaviIme(String ime, String email) {
        try {
            postaviIme.setString(1, ime);
            postaviIme.setString(2, email);
            postaviIme.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void postaviPrezime(String prezime, String email) {
        try {
            postaviPrezime.setString(1, prezime);
            postaviPrezime.setString(2, email);
            postaviPrezime.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void postaviMail(String email, String ime, String prezime, String password) {
        try {
            postaviMail.setString(1, email);
            postaviMail.setString(2, ime);
            postaviMail.setString(3, ime);
            postaviMail.setString(4, ime);

            postaviMail.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void postaviPass(String password, String email) {
        try {
            postaviPass.setString(1, password);
            postaviPass.setString(2, email);
            postaviPass.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
