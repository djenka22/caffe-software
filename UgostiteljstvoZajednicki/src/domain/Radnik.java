/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Korisnik
 */
public class Radnik extends AbstractDomainObject {

    private Long radnikID;
    private String ime;
    private String prezime;
    private String username;
    private String password;
    private boolean glavni;

    public Radnik() {
    }

    public Radnik(Long radnikID, String ime, String prezime, String username, String password, boolean glavni) {
        this.radnikID = radnikID;
        this.ime = ime;
        this.prezime = prezime;
        this.username = username;
        this.password = password;
        this.glavni = glavni;
    }

    public Long getRadnikID() {
        return radnikID;
    }

    public void setRadnikID(Long radnikID) {
        this.radnikID = radnikID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    @Override
    public String nazivTabele() {
        return " radnik ";
    }

    @Override
    public String alijas() {
        return " r ";
    }

    @Override
    public String join() {
        return "";
    }

    public Radnik vratiRadnika(ResultSet rs) throws SQLException {
        Radnik r = new Radnik();
        if (!rs.next()) {
            return null;
        }

        r.setRadnikID(rs.getLong("RadnikID"));
        r.setIme(rs.getString("Ime"));
        r.setPrezime(rs.getString("Prezime"));
        r.setUsername(rs.getString("Username"));
        r.setPassword(rs.getString("Password"));
        r.setGlavni(rs.getBoolean("Glavni"));

        rs.close();
        return r;
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Radnik r = new Radnik(rs.getLong("RadnikID"),
                    rs.getString("Ime"), rs.getString("Prezime"),
                    rs.getString("Username"), rs.getString("Password"),
                    rs.getBoolean("Glavni"));

            lista.add(r);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (Ime, Prezime, Username, Password) ";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " radnikID = " + radnikID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + ime + "', '" + prezime + "', "
                + "'" + username + "', '" + password + "'";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " Ime = '" + ime + "', Prezime = '" + prezime + "', "
                + "Username = '" + username + "', Password = '" + password + "' ";
    }

    @Override
    public String uslov() {
        return "";
    }

    public String loginUslov() {
        return "WHERE r.username = " + "'" + getUsername()
                + "'" + " AND " + "r" + "." + "password" + " = " + "'" + getPassword()
                + "'";
    }

    public boolean isGlavni() {
        return glavni;
    }

    public void setGlavni(boolean glavni) {
        this.glavni = glavni;
    }

}
