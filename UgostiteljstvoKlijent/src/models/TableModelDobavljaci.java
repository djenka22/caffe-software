/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import controller.ClientController;
import domain.Dobavljac;
import domain.Proizvod;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Korisnik
 */
public class TableModelDobavljaci extends AbstractTableModel implements Runnable {

    private ArrayList<Dobavljac> lista;
    private String[] kolone = {"ID", "PIB", "Naziv", "adresa", "broj telefona"};
    private String parametar = "";

    public TableModelDobavljaci() {
        try {
            lista = ClientController.getInstance().getAllDobavljac();
        } catch (Exception ex) {
            Logger.getLogger(TableModelDobavljaci.class.getName()).log(Level.SEVERE, null, ex);
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
        Dobavljac d = lista.get(row);

        switch (column) {
            case 0:
                return d.getDobavljacID();
            case 1:
                return d.getPIB();
            case 2:
                return d.getNazivDobavljaca();
            case 3:
                return d.getAdresa();
            case 4:
                return d.getBrojTelefona();
            default:
                return null;
        }
    }

    public Dobavljac getSelectedDobavljac(int row) {
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
            Logger.getLogger(TableModelDobavljaci.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setParametar(String parametar) {
        this.parametar = parametar;
        refreshTable();
    }

    public void refreshTable() {
        try {
            lista = ClientController.getInstance().getAllDobavljac();
            if (!parametar.equals("")) {
                ArrayList<Dobavljac> novaLista = new ArrayList<>();
                for (Dobavljac d : lista) {
                    if (d.getNazivDobavljaca().toLowerCase().contains(parametar.toLowerCase())) {
                        novaLista.add(d);
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
