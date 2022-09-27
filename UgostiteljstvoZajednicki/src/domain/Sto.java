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
public class Sto extends AbstractDomainObject {
    
    private Long stoID;
    private int brojStola;

    @Override
    public String toString() {
        return brojStola + "";
    }

    public Sto(Long stoID, int brojStola) {
        this.stoID = stoID;
        this.brojStola = brojStola;
    }

    public Sto() {
    }

    @Override
    public String nazivTabele() {
        return " sto ";
    }

    @Override
    public String alijas() {
        return " s ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Sto s = new Sto(rs.getLong("stoID"), rs.getInt("brojStola"));

            lista.add(s);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return "";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " stoID = " + stoID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "";
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    @Override
    public String uslov() {
        return "";
    }

    public Long getStoID() {
        return stoID;
    }

    public void setStoID(Long stoID) {
        this.stoID = stoID;
    }

    public int getBrojStola() {
        return brojStola;
    }

    public void setBrojStola(int brojStola) {
        this.brojStola = brojStola;
    }
    
}
