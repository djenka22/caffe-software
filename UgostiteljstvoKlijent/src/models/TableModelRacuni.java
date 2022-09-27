/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import controller.ClientController;
import domain.Racun;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Korisnik
 */
public class TableModelRacuni extends AbstractTableModel implements Runnable {

    private ArrayList<Racun> lista;
    private String[] kolone = {"ID", "Datum i vreme", "Ukupan iznos", "Sto", "Obradjen", "Radnik"};
    private boolean obradjen = false;

    public TableModelRacuni() {
        try {
            lista = ClientController.getInstance().getAllRacun();
        } catch (Exception ex) {
            Logger.getLogger(TableModelRacuni.class.getName()).log(Level.SEVERE, null, ex);
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
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 4) {
            return Boolean.class;
        }
        return super.getColumnClass(columnIndex); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getValueAt(int row, int column) {
        Racun r = lista.get(row);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

        switch (column) {
            case 0:
                return r.getRacunID();
            case 1:
                return sdf.format(r.getDatumVreme());
            case 2:
                return r.getUkupanIznos() + "din";
            case 3:
                return r.getSto();
            case 4:
                return r.isObradjen();
            case 5:
                return r.getRadnik();

            default:
                return null;
        }
    }

    public Racun getSelectedRacun(int row) {
        return lista.get(row);
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(10000);
                refreshTable();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(TableModelRacuni.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setObradjen(boolean obradjen) {
        this.obradjen = obradjen;
        refreshTable();
    }

    public void refreshTable() {
        try {
            lista = ClientController.getInstance().getAllRacun();
            if (!obradjen) {
                ArrayList<Racun> novaLista = new ArrayList<>();
                for (Racun r : lista) {
                    if (!r.isObradjen()) {
                        novaLista.add(r);
                    }
                }
                lista = novaLista;
            } else {
                ArrayList<Racun> novaLista = new ArrayList<>();
                for (Racun r : lista) {
                    if (r.isObradjen()) {
                        novaLista.add(r);
                    }
                }
                lista = novaLista;
            }

            fireTableDataChanged();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
