/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import domain.Radnik;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author Korisnik
 */
public class Session {

    private static Session instance;
    private Socket socket;
    private Radnik ulogovani;

    private Session() {
        try {
            socket = new Socket("localhost", 10000);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setUlogovani(Radnik ulogovani) {
        this.ulogovani = ulogovani;
    }

    public Radnik getUlogovani() {
        return ulogovani;
    }

}
