/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.login;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Radnik;
import so.AbstractSO;

import java.util.ArrayList;

/**
 *
 * @author Korisnik
 */
public class SOLogin extends AbstractSO {
    
    Radnik ulogovani;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Radnik)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Radnik!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {

        Radnik r = (Radnik) ado;
        this.ulogovani = (Radnik) DBBroker.getInstance().login(r);
        if (ulogovani == null) {
            throw new Exception("Pogresno korisnicko ime ili sifra");
        }        
        
    }

    public Radnik getUlogovani() {
        return ulogovani;
    }

}
