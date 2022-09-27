/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.dobavljac;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Dobavljac;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Korisnik
 */
public class SOAddDobavljac extends AbstractSO{

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Dobavljac)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Dobavljac!");
        }
        
        Dobavljac noviDobavljac = (Dobavljac) ado;


        ArrayList<Dobavljac> dobavljaci = (ArrayList<Dobavljac>) (ArrayList<?>) DBBroker.getInstance().select(ado);

        for (Dobavljac dobavljac : dobavljaci) {
            if (dobavljac.getNazivDobavljaca().equals(noviDobavljac.getNazivDobavljaca())) {
                throw new Exception("Dobavljac sa tim nazivom vec postoji!");
            }
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().insert(ado);
    }
    
}
