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
        ambitsExisting = 0;
    }

    public void createFatherAmbit() {
        System.out.println("FATHER");
        Ambit newAmbit = new Ambit(ambitsExisting++, null);
        currentAmbit = newAmbit;
    }

    public void createSonAmbit() {
        System.out.println("CCREAT "+ambitsExisting+1+"  "+currentAmbit.getId());
        Ambit newAmbit = new Ambit(ambitsExisting++, currentAmbit);
        currentAmbit = newAmbit;
    }

    public void finishAmbit() {
        System.out.println("FINISHUING "+currentAmbit.getId());
        if (currentAmbit.getFather() != null) {
            System.out.println(currentAmbit.getFather().getId());
            currentAmbit = currentAmbit.getFather();
        }

    }

    public Ambit getCurrentAmbit() {
        return currentAmbit;
    }

    public boolean isSonOfAFuncion() {
        Ambit am = getCurrentAmbit().getFather();
        while (true) {
            if (am != null) {
                if (am.isFunction()) {
                    return true;
                }
                am=am.getFather();
            }else{
                return false;
            }
        }

        
    }

}
