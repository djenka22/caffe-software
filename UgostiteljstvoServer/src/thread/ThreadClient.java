/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread;

import controller.ServerController;
import domain.Dobavljac;
import domain.Proizvod;
import domain.Racun;
import domain.Radnik;
import transfer.Request;
import transfer.Response;
import transfer.util.Operation;
import transfer.util.ResponseStatus;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author Korisnik
 */
public class ThreadClient extends Thread {

    private Socket socket;

    ThreadClient(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                Request request = (Request) in.readObject();
                Response response = handleRequest(request);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Response handleRequest(Request request) {
        Response response = new Response(null, null, ResponseStatus.Success);
        try {
            switch (request.getOperation()) {
                case Operation.ADD_DOBAVLJAC:
                    ServerController.getInstance().addDobavljac((Dobavljac) request.getData());
                    break;
                case Operation.DELETE_DOBAVLJAC:
                    ServerController.getInstance().deleteDobavljac((Dobavljac) request.getData());
                    break;
                case Operation.UPDATE_DOBAVLJAC:
                    ServerController.getInstance().updateDobavljac((Dobavljac) request.getData());
                    break;
                case Operation.ADD_PROIZVOD:
                    ServerController.getInstance().addProizvod((Proizvod) request.getData());
                    break;
                case Operation.ADD_RACUN:
                    ServerController.getInstance().addRacun((Racun) request.getData());
                    break;
                case Operation.DELETE_PROIZVOD:
                    ServerController.getInstance().deleteProizvod((Proizvod) request.getData());
                    break;
                case Operation.UPDATE_PROIZVOD:
                    ServerController.getInstance().updateProizvod((Proizvod) request.getData());
                    break;
                case Operation.UPDATE_RACUN:
                    ServerController.getInstance().updateRacun((Racun) request.getData());
                    break;
                case Operation.GET_ALL_PROIZVOD:
                    response.setData(ServerController.getInstance().getAllProizvod());
                    break;
                case Operation.GET_ALL_RACUN:
                    response.setData(ServerController.getInstance().getAllRacun());
                    break;
                case Operation.GET_ALL_DOBAVLJAC:
                    response.setData(ServerController.getInstance().getAllDobavljac());
                    break;
                case Operation.GET_ALL_STO:
                    response.setData(ServerController.getInstance().getAllSto());
                    break;
                case Operation.GET_ALL_RADNIK:
                    response.setData(ServerController.getInstance().getAllRadnik());
                    break;
                case Operation.GET_ALL_STAVKA_RACUNA:
                    response.setData(ServerController.getInstance().getAllStavkaRacuna((Racun) request.getData()));
                    break;
                case Operation.LOGIN:
                    Radnik radnik = (Radnik) request.getData();
                    Radnik ulogovani = ServerController.getInstance().login(radnik);
                    System.out.println("ulogovani " + ulogovani);
                    response.setData(ulogovani);
                    break;
                default:
                    return null;
            }
        } catch (Exception e) {
            response.setResponseStatus(ResponseStatus.Error);
            response.setException(e);
        }
        return response;
    }

}
