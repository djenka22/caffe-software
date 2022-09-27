/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import controller.ClientController;
import domain.Racun;
import domain.StavkaRacuna;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Korisnik
 */
public class TableModelStavke extends AbstractTableModel {

    private ArrayList<StavkaRacuna> lista;
    private String[] kolone = {"Rb", "Proizvod", "Kolicina", "Cena"};
    private int rb = 0;

    public TableModelStavke() {
        lista = new ArrayList<>();
    }

    public TableModelStavke(Racun r) {
        try {
            lista = ClientController.getInstance().getAllStavkaRacuna(r);
        } catch (Exception ex) {
            Logger.getLogger(TableModelStavke.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int i) {
        return kolone[i];
    }

    @Override
    public Object getValueAt(int row, int column) {
        StavkaRacuna sr = lista.get(row);

        switch (column) {
            case 0:
                return sr.getRbStavke();
            case 1:
                return sr.getProizvod().getNazivProizvoda();
            case 2:
                return sr.getKolicinaStavke();
            case 3:
                return sr.getCenaStavke() + "din";

            default:
                return null;
        }
    }

    public int dodajStavku(StavkaRacuna sr) {
        rb = lista.size();
        sr.setRbStavke(++rb);
        lista.add(sr);
        fireTableDataChanged();
        return rb;
    }

    public void obrisiStavku(int row) {
        lista.remove(row);

        rb = 0;
        for (StavkaRacuna stavkaRacuna : lista) {
            stavkaRacuna.setRbStavke(++rb);
        }

        fireTableDataChanged();
    }

    public double getUkupnaCena() {
        double ukupnaCena = 0;

        for (StavkaRacuna stavkaRacuna : lista) {
            ukupnaCena += stavkaRacuna.getCenaStavke();
        }

        return ukupnaCena;
    }

    public ArrayList<StavkaRacuna> getLista() {
        return lista;
    }

}
