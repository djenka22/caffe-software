/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.racun;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Racun;
import domain.StavkaRacuna;
import so.AbstractSO;

/**
 *
 * @author Korisnik
 */
public class SOUpdateRacun extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Racun)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Racun!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        
        DBBroker.getInstance().update(ado);

        Racun r = (Racun) ado;

        for (StavkaRacuna stavkaRacuna : r.getStavkeRacuna()) {
            DBBroker.getInstance().insert(stavkaRacuna);
        }
        
    }

}
