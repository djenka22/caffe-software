/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.sto;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Sto;
import so.AbstractSO;

import java.util.ArrayList;

/**
 *
 * @author Korisnik
 */
public class SOGetAllSto extends AbstractSO {

    private ArrayList<Sto> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Sto)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Sto!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> stolovi = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Sto>) (ArrayList<?>) stolovi;
    }

    public ArrayList<Sto> getLista() {
        return lista;
    }

}
