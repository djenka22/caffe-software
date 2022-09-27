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
public class StavkaRacuna extends AbstractDomainObject {

    private Racun racun;
    private int rbStavke;
    private int kolicinaStavke;
    private double cenaStavke;
    private Proizvod proizvod;

    public StavkaRacuna(Racun racun, int rbStavke, int kolicinaStavke, double cenaStavke, Proizvod proizvod) {
        this.racun = racun;
        this.rbStavke = rbStavke;
        this.kolicinaStavke = kolicinaStavke;
        this.cenaStavke = cenaStavke;
        this.proizvod = proizvod;
    }

    public StavkaRacuna() {
    }

    @Override
    public String nazivTabele() {
        return " stavkaRacuna ";
    }

    @Override
    public String alijas() {
        return " sr ";
    }

    @Override
    public String join() {
        return " JOIN RACUN RAC USING (RACUNID) "
                + "JOIN STO S ON (S.STOID = RAC.STOID) "
                + "JOIN RADNIK R ON (R.RADNIKID = RAC.RADNIKID) "
                + "JOIN PROIZVOD P USING (PROIZVODID) "
                + "JOIN DOBAVLJAC D ON (D.DOBAVLJACID = P.DOBAVLJACID) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Radnik r = new Radnik(rs.getLong("RadnikID"),
                    rs.getString("Ime"), rs.getString("Prezime"),
                    rs.getString("Username"), rs.getString("Password"),
                    rs.getBoolean("Glavni"));

            Dobavljac d = new Dobavljac(rs.getLong("dobavljacID"),
                    rs.getString("PIB"), rs.getString("nazivDobavljaca"),
                    rs.getString("adresa"), rs.getString("brojTelefona"));

            Proizvod p = new Proizvod(rs.getLong("proizvodID"), rs.getString("nazivProizvoda"),
                    rs.getString("opis"), rs.getDouble("cena"), d);

            Sto s = new Sto(rs.getLong("stoID"), rs.getInt("brojStola"));

            Racun racun = new Racun(rs.getLong("racunID"), rs.getTimestamp("datumVreme"),
                    rs.getDouble("ukupanIznos"), rs.getBoolean("obradjen"), s, r, null);

            StavkaRacuna sr = new StavkaRacuna(racun, rs.getInt("rbStavke"),
                    rs.getInt("kolicinaStavke"), rs.getDouble("cenaStavke"), p);

            lista.add(sr);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (racunID, rbStavke, kolicinaStavke, cenaStavke, proizvodID) ";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " racunID = " + racun.getRadnik();
    }

    @Override
    public String vrednostiZaInsert() {
        return racun.getRacunID() + ", " + rbStavke + ", "
                + kolicinaStavke + ", " + cenaStavke + ", " + proizvod.getProizvodID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    @Override
    public String uslov() {
        return " WHERE RAC.RACUNID = " + racun.getRacunID();
    }

    public Racun getRacun() {
        return racun;
    }

    public void setRacun(Racun racun) {
        this.racun = racun;
    }

    public int getRbStavke() {
        return rbStavke;
    }

    public void setRbStavke(int rbStavke) {
        this.rbStavke = rbStavke;
    }

    public int getKolicinaStavke() {
        return kolicinaStavke;
    }

    public void setKolicinaStavke(int kolicinaStavke) {
        this.kolicinaStavke = kolicinaStavke;
    }

    public double getCenaStavke() {
        return cenaStavke;
    }

    public void setCenaStavke(double cenaStavke) {
        this.cenaStavke = cenaStavke;
    }

    public Proizvod getProizvod() {
        return proizvod;
    }

    public void setProizvod(Proizvod proizvod) {
        this.proizvod = proizvod;
    }

}
