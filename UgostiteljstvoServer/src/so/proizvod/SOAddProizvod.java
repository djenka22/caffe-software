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
public class SOAddProizvod extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Proizvod)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Proizvod!");
        }

        Proizvod noviProizvod = (Proizvod) ado;

        if (noviProizvod.getCena() < 100 || noviProizvod.getCena() > 2500) {
            throw new Exception("Cena proizvoda mora biti izmedju 100 i 2500din!");
        }

        ArrayList<Proizvod> proizvodi = (ArrayList<Proizvod>) (ArrayList<?>) DBBroker.getInstance().select(ado);

        for (Proizvod proizvod : proizvodi) {
            if (proizvod.getNazivProizvoda().equals(noviProizvod.getNazivProizvoda())) {
                throw new Exception("Proizvod s tim nazivom vec postoji!");
            }
        }

    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().insert(ado);
    }

}
