/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Korisnik
 */
public class Racun extends AbstractDomainObject {

    private static final long serialVersionUID = 1113799434508676095L;

    private Long racunID;
    private Date datumVreme;
    private double ukupanIznos;
    private boolean obradjen;
    private Sto sto;
    private Radnik radnik;
    private ArrayList<StavkaRacuna> stavkeRacuna;

    public Racun(Long racunID, Date datumVreme, double ukupanIznos, boolean obradjen, Sto sto, Radnik radnik, ArrayList<StavkaRacuna> stavkeRacuna) {
        this.racunID = racunID;
        this.datumVreme = datumVreme;
        this.ukupanIznos = ukupanIznos;
        this.obradjen = obradjen;
        this.sto = sto;
        this.radnik = radnik;
        this.stavkeRacuna = stavkeRacuna;
    }

    public Racun() {
    }

    @Override
    public String nazivTabele() {
        return " racun ";
    }

    @Override
    public String alijas() {
        return " rac ";
    }

    @Override
    public String join() {
        return " JOIN STO S ON (S.STOID = RAC.STOID) "
                + "JOIN RADNIK R ON (R.RADNIKID = RAC.RADNIKID) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Radnik r = new Radnik(rs.getLong("RadnikID"),
                    rs.getString("Ime"), rs.getString("Prezime"),
                    rs.getString("Username"), rs.getString("Password"),
                    rs.getBoolean("Glavni"));

            Sto s = new Sto(rs.getLong("stoID"), rs.getInt("brojStola"));

            Racun racun = new Racun(rs.getLong("racunID"), rs.getTimestamp("datumVreme"),
                    rs.getDouble("ukupanIznos"), rs.getBoolean("obradjen"), s, r, null);

            lista.add(racun);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (datumVreme, ukupanIznos, obradjen, stoID, radnikID) ";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " racunID = " + racunID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + new Timestamp(datumVreme.getTime()) + "', " + ukupanIznos + ", "
                + (obradjen ? 1 : 0) + ", " + sto.getStoID() + ", " + radnik.getRadnikID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return " datumVreme = '" + new Timestamp(datumVreme.getTime()) + "', "
                + "ukupanIznos = " + ukupanIznos + ", "
                + "Obradjen = " + (obradjen ? 1 : 0) + " ";
    }

    @Override
    public String uslov() {
        return "";
    }

    public Long getRacunID() {
        return racunID;
    }

    public void setRacunID(Long racunID) {
        this.racunID = racunID;
    }

    public Date getDatumVreme() {
        return datumVreme;
    }

    public void setDatumVreme(Date datumVreme) {
        this.datumVreme = datumVreme;
    }

    public double getUkupanIznos() {
        return ukupanIznos;
    }

    public void setUkupanIznos(double ukupanIznos) {
        this.ukupanIznos = ukupanIznos;
    }

    public boolean isObradjen() {
        return obradjen;
    }

    public void setObradjen(boolean obradjen) {
        this.obradjen = obradjen;
    }

    public Sto getSto() {
        return sto;
    }

    public void setSto(Sto sto) {
        this.sto = sto;
    }

    public Radnik getRadnik() {
        return radnik;
    }

    public void setRadnik(Radnik radnik) {
        this.radnik = radnik;
    }

    public ArrayList<StavkaRacuna> getStavkeRacuna() {
        return stavkeRacuna;
    }

    public void setStavkeRacuna(ArrayList<StavkaRacuna> stavkeRacuna) {
        this.stavkeRacuna = stavkeRacuna;
    }

}
