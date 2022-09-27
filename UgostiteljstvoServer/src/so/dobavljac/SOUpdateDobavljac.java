/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.dobavljac;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Dobavljac;
import domain.Proizvod;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Korisnik
 */
public class SOUpdateDobavljac extends AbstractSO{

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Dobavljac)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Dobavljac!");
        }
        Dobavljac izmenjeni = (Dobavljac) ado;
            
        ArrayList<Dobavljac> dobavljaci = (ArrayList<Dobavljac>) (ArrayList<?>) DBBroker.getInstance().select(ado);

        for (Dobavljac d : dobavljaci) {
            if (!d.getDobavljacID().equals(izmenjeni.getDobavljacID())) {
                if (d.getNazivDobavljaca().equals(izmenjeni.getNazivDobavljaca())) {
                    throw new Exception("Dobavljac sa tim nazivom vec postoji!");
                }
            }
        }
    }
   

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
       DBBroker.getInstance().update(ado);
    }
    
}
