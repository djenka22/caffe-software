/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.*;
import so.dobavljac.SOGetAllDobavljac;
import so.login.SOLogin;
import so.proizvod.SOAddProizvod;
import so.proizvod.SODeleteProizvod;
import so.proizvod.SOGetAllProizvod;
import so.proizvod.SOUpdateProizvod;
import so.racun.SOAddRacun;
import so.racun.SOGetAllRacun;
import so.racun.SOUpdateRacun;
import so.radnik.SOGetAllRadnik;
import so.stavkaRacuna.SOGetAllStavkaRacuna;
import so.sto.SOGetAllSto;

import java.util.ArrayList;
import so.dobavljac.SOAddDobavljac;
import so.dobavljac.SODeleteDobavljac;
import so.dobavljac.SOUpdateDobavljac;

/**
 *
 * @author Korisnik
 */
public class ServerController {

    private static ServerController instance;

    public ServerController() {
    }

    public static ServerController getInstance() {
        if (instance == null) {
            instance = new ServerController();
        }
        return instance;
    }

    public Radnik login(Radnik radnik) throws Exception {
        SOLogin so = new SOLogin();
        so.templateExecute(radnik);
        return so.getUlogovani();
    }

    public void addProizvod(Proizvod proizvod) throws Exception {
        (new SOAddProizvod()).templateExecute(proizvod);
    }
    
    public void addRacun(Racun racun) throws Exception {
        (new SOAddRacun()).templateExecute(racun);
    }

    public void deleteProizvod(Proizvod proizvod) throws Exception {
        (new SODeleteProizvod()).templateExecute(proizvod);
    }

    public void updateRacun(Racun racun) throws Exception {
        (new SOUpdateRacun()).templateExecute(racun);
    }

    public void updateProizvod(Proizvod proizvod) throws Exception {
        (new SOUpdateProizvod()).templateExecute(proizvod);
    }

    public ArrayList<Proizvod> getAllProizvod() throws Exception {
        SOGetAllProizvod so = new SOGetAllProizvod();
        so.templateExecute(new Proizvod());
        return so.getLista();
    }

    public ArrayList<Racun> getAllRacun() throws Exception {
        SOGetAllRacun so = new SOGetAllRacun();
        so.templateExecute(new Racun());
        return so.getLista();
    }

    public ArrayList<Radnik> getAllRadnik() throws Exception {
        SOGetAllRadnik so = new SOGetAllRadnik();
        so.templateExecute(new Radnik());
        return so.getLista();
    }

    public ArrayList<Sto> getAllSto() throws Exception {
        SOGetAllSto so = new SOGetAllSto();
        so.templateExecute(new Sto());
        return so.getLista();
    }

    public ArrayList<Dobavljac> getAllDobavljac() throws Exception {
        SOGetAllDobavljac so = new SOGetAllDobavljac();
        so.templateExecute(new Dobavljac());
        return so.getLista();
    }

    public ArrayList<StavkaRacuna> getAllStavkaRacuna(Racun r) throws Exception {
        SOGetAllStavkaRacuna so = new SOGetAllStavkaRacuna();

        StavkaRacuna sr = new StavkaRacuna();
        sr.setRacun(r);

        so.templateExecute(sr);
        return so.getLista();
    }

    public void addDobavljac(Dobavljac dobavljac) throws Exception {
        SOAddDobavljac addDobavljac = new SOAddDobavljac();
        addDobavljac.templateExecute(dobavljac);
    }

    public void deleteDobavljac(Dobavljac dobavljac) throws Exception {
        (new SODeleteDobavljac()).templateExecute(dobavljac);
    }

    public void updateDobavljac(Dobavljac dobavljac) throws Exception {
        (new SOUpdateDobavljac()).templateExecute(dobavljac);
    }

}
