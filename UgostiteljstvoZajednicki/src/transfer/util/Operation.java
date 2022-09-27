/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer.util;

/**
 *
 * @author Korisnik
 */
public interface Operation {

    public static final int LOGIN = 0;

    public static final int GET_ALL_RADNIK = 1;
    
    public static final int GET_ALL_STO = 2;

    public static final int ADD_PROIZVOD = 3;
    public static final int DELETE_PROIZVOD = 4;
    public static final int UPDATE_PROIZVOD = 5;
    public static final int GET_ALL_PROIZVOD = 6;
    
    public static final int ADD_RACUN = 7;
    public static final int UPDATE_RACUN = 8;
    public static final int GET_ALL_RACUN = 9;

    public static final int GET_ALL_STAVKA_RACUNA = 10;

    public static final int ADD_DOBAVLJAC = 12;
    public static final int DELETE_DOBAVLJAC = 13;
    public static final int UPDATE_DOBAVLJAC = 14;
    public static final int GET_ALL_DOBAVLJAC = 11;

}
