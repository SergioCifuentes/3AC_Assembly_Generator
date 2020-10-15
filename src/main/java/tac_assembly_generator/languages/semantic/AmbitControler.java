/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tac_assembly_generator.languages.semantic;

/**
 *
 * @author sergio
 */
public class AmbitControler {
    private int ambitsExisting;
    private Ambit currentAmbit;

    
    public AmbitControler() {
        ambitsExisting=0;
    }

    public void createFatherAmbit(){
        Ambit newAmbit= new Ambit(ambitsExisting++, null);
        currentAmbit=newAmbit;
    }
    public void createSonAmbit(){
        Ambit newAmbit= new Ambit(ambitsExisting++, currentAmbit);
        currentAmbit=newAmbit;
    }
    
    public void finishAmbit(){
        if (currentAmbit.getFather()!=null) {
            currentAmbit=currentAmbit.getFather();
        }
        
    }
    
    public Ambit getCurrentAmbit() {
        return currentAmbit;
    }
    
    
    
    
    
    
}
