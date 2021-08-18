package ba.unsa.etf.rpr.projekat.model;

import ba.unsa.etf.rpr.projekat.dao.CRMDao;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    User user;
    CRMDao model;
    @Test
    void test() {
        model = CRMDao.getInstance();
        user = new User("ime", "prezime", "email", "password", POSITION.Klijent);
        model.dodajKorisnika(user.getIme(), user.getPrezime(), user.getEmail(), user.getPassword(), user.getPozicija());
        assertEquals("ime", user.getIme());
        assertEquals("prezime", user.getPrezime());
        assertEquals("email", user.getEmail());
        assertEquals("password", user.getPassword());
        assertEquals("Klijent", user.getPozicija().toString());
        user.setIme("a");
        user.setPrezime("b");
        user.setEmail("c");
        user.setPassword("e");
        user.setSlika("slikica");
        assertEquals("a", user.getIme());
        assertEquals("b", user.getPrezime());
        assertEquals("c", user.getEmail());
        assertEquals("e", user.getPassword());
        assertEquals("Klijent", user.getPozicija().toString());
        assertEquals("slikica", user.getSlika());
    }


}