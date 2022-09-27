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
public class Dobavljac extends AbstractDomainObject {
    
    private Long dobavljacID;
    private String PIB;
    private String nazivDobavljaca;
    private String adresa;
    private String brojTelefona;

    @Override
    public String toString() {
        return nazivDobavljaca;
    }

    public Dobavljac(Long dobavljacID, String PIB, String nazivDobavljaca, String adresa, String brojTelefona) {
        this.dobavljacID = dobavljacID;
        this.PIB = PIB;
        this.nazivDobavljaca = nazivDobavljaca;
        this.adresa = adresa;
        this.brojTelefona = brojTelefona;
    }

    public Dobavljac() {
    }

    public Long getDobavljacID() {
        return dobavljacID;
    }

    public void setDobavljacID(Long dobavljacID) {
        this.dobavljacID = dobavljacID;
    }

    public String getPIB() {
        return PIB;
    }

    public void setPIB(String PIB) {
        this.PIB = PIB;
    }

    public String getNazivDobavljaca() {
        return nazivDobavljaca;
    }

    public void setNazivDobavljaca(String nazivDobavljaca) {
        this.nazivDobavljaca = nazivDobavljaca;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getBrojTelefona() {
        return brojTelefona;
    }

    public void setBrojTelefona(String brojTelefona) {
        this.brojTelefona = brojTelefona;
    }
    
    @Override
    public String nazivTabele() {
        return " dobavljac ";
    }

    @Override
    public String alijas() {
        return " d ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Dobavljac d = new Dobavljac(rs.getLong("dobavljacID"), 
                    rs.getString("PIB"), rs.getString("nazivDobavljaca"), 
                    rs.getString("adresa"), rs.getString("brojTelefona"));
            
            lista.add(d);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (PIB, nazivDobavljaca, adresa, brojTelefona) ";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " dobavljacID = " + dobavljacID;
    }

    @Override
    public String vrednostiZaInsert() {
        return  "'" + PIB + "', '" + nazivDobavljaca + "', '"
                + adresa + "', '" + brojTelefona + "'";
    }

    @Override
    public String vrednostiZaUpdate() {
         return  "PIB = '" + PIB + "', NazivDobavljaca = '" + nazivDobavljaca + "', Adresa = '"
                + adresa + "', BrojTelefona = '" + brojTelefona + "' ";
    }

    @Override
    public String uslov() {
        return "";
    }
    
}
