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
public class Proizvod extends AbstractDomainObject {
    
    private Long proizvodID;
    private String nazivProizvoda;
    private String opis;
    private double cena;
    private Dobavljac dobavljac;

    @Override
    public String toString() {
        return nazivProizvoda + " (Cena: " + cena + "din)";
    }

    public Proizvod(Long proizvodID, String nazivProizvoda, String opis, double cena, Dobavljac dobavljac) {
        this.proizvodID = proizvodID;
        this.nazivProizvoda = nazivProizvoda;
        this.opis = opis;
        this.cena = cena;
        this.dobavljac = dobavljac;
    }

    public Proizvod() {
    }
    
    @Override
    public String nazivTabele() {
        return " proizvod ";
    }

    @Override
    public String alijas() {
        return " p ";
    }

    @Override
    public String join() {
        return " JOIN DOBAVLJAC D ON (D.DOBAVLJACID = P.DOBAVLJACID) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            
            Dobavljac d = new Dobavljac(rs.getLong("dobavljacID"), 
                    rs.getString("PIB"), rs.getString("nazivDobavljaca"), 
                    rs.getString("adresa"), rs.getString("brojTelefona"));
            
            Proizvod p = new Proizvod(rs.getLong("proizvodID"), rs.getString("nazivProizvoda"), 
                    rs.getString("opis"), rs.getDouble("cena"), d);
                    

            lista.add(p);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (nazivProizvoda, opis, cena, dobavljacID) ";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " proizvodID = " + proizvodID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + nazivProizvoda + "', '" + opis + "', "
                + cena + ", " + dobavljac.getDobavljacID() + " ";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " nazivProizvoda = '" + nazivProizvoda + "', opis = '" + opis + "', "
                + "cena = " + cena + " ";
    }

    @Override
    public String uslov() {
        return "";
    }

    public Long getProizvodID() {
        return proizvodID;
    }

    public void setProizvodID(Long proizvodID) {
        this.proizvodID = proizvodID;
    }

    public String getNazivProizvoda() {
        return nazivProizvoda;
    }

    public void setNazivProizvoda(String nazivProizvoda) {
        this.nazivProizvoda = nazivProizvoda;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public Dobavljac getDobavljac() {
        return dobavljac;
    }

    public void setDobavljac(Dobavljac dobavljac) {
        this.dobavljac = dobavljac;
    }
    
}
