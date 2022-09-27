/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.proizvod;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Proizvod;
import so.AbstractSO;

import java.util.ArrayList;

/**
 *
 * @author Korisnik
 */
public class SOGetAllProizvod extends AbstractSO {

    private ArrayList<Proizvod> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Proizvod)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Proizvod!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> proizvodi = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Proizvod>) (ArrayList<?>) proizvodi;
    }

    public ArrayList<Proizvod> getLista() {
        return lista;
    }

}
