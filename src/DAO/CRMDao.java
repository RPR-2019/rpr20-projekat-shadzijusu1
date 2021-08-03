package DAO;

import Model.POZICIJA;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class CRMDao {
    private static CRMDao instance = null;
    private Connection conn;
    private PreparedStatement dodajKorisnika;

    private CRMDao() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:baza.db");
            dodajKorisnika = conn.prepareStatement("INSERT INTO Korisnik VALUES(?,?,?,?,?,?)");
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

}
