/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.Dobavljac;
import domain.Proizvod;
import domain.Racun;
import domain.Radnik;
import domain.StavkaRacuna;
import domain.Sto;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import session.Session;
import transfer.Request;
import transfer.Response;
import transfer.util.ResponseStatus;
import transfer.util.Operation;

/**
 *
 * @author Korisnik
 */
public class ClientController {

    private static ClientController instance;

    public ClientController() {
    }

    public static ClientController getInstance() {
        if (instance == null) {
            instance = new ClientController();
        }
        return instance;
    }

    public Radnik login(Radnik radnik) throws Exception {
        return (Radnik) sendRequest(Operation.LOGIN, radnik);
    }

    public void addProizvod(Proizvod proizvod) throws Exception {
        sendRequest(Operation.ADD_PROIZVOD, proizvod);
    }

    public void addRacun(Racun racun) throws Exception {
        sendRequest(Operation.ADD_RACUN, racun);
    }

    public void deleteProizvod(Proizvod proizvod) throws Exception {
        sendRequest(Operation.DELETE_PROIZVOD, proizvod);
    }

    public void updateProizvod(Proizvod proizvod) throws Exception {
        sendRequest(Operation.UPDATE_PROIZVOD, proizvod);
    }

    public void updateRacun(Racun racun) throws Exception {
        sendRequest(Operation.UPDATE_RACUN, racun);
    }

    public ArrayList<Radnik> getAllRadnik() throws Exception {
        return (ArrayList<Radnik>) sendRequest(Operation.GET_ALL_RADNIK, null);
    }

    public ArrayList<Proizvod> getAllProizvod() throws Exception {
        return (ArrayList<Proizvod>) sendRequest(Operation.GET_ALL_PROIZVOD, null);
    }

    public ArrayList<Racun> getAllRacun() throws Exception {
        return (ArrayList<Racun>) sendRequest(Operation.GET_ALL_RACUN, null);
    }

    public ArrayList<Dobavljac> getAllDobavljac() throws Exception {
        return (ArrayList<Dobavljac>) sendRequest(Operation.GET_ALL_DOBAVLJAC, null);
    }

    public ArrayList<Sto> getAllSto() throws Exception {
        return (ArrayList<Sto>) sendRequest(Operation.GET_ALL_STO, null);
    }

    public ArrayList<StavkaRacuna> getAllStavkaRacuna(Racun r) throws Exception {
        return (ArrayList<StavkaRacuna>) sendRequest(Operation.GET_ALL_STAVKA_RACUNA, r);
    }
    
    public void addDobavljac(Dobavljac d) throws Exception {
        sendRequest(Operation.ADD_DOBAVLJAC, d);
    }
    public void deleteDobavljac(Dobavljac dobavljac) throws Exception {
        sendRequest(Operation.DELETE_DOBAVLJAC, dobavljac);
    }

    public void updateDobavljac(Dobavljac d) throws Exception {
        sendRequest(Operation.UPDATE_DOBAVLJAC, d);
    }

    private Object sendRequest(int operation, Object data) throws Exception {
        Request request = new Request(operation, data);

        ObjectOutputStream out = new ObjectOutputStream(Session.getInstance().getSocket().getOutputStream());
        out.writeObject(request);

        ObjectInputStream in = new ObjectInputStream(Session.getInstance().getSocket().getInputStream());
        Response response = (Response) in.readObject();

        if (response.getResponseStatus().equals(ResponseStatus.Error)) {
            throw response.getException();
        } else {
            return response.getData();
        }

    }

   
}
