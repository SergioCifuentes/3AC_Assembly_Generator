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
public class Ambit {
    private int id;
    private Ambit father;

    public Ambit(int id, Ambit father) {
        this.id = id;
        this.father = father;
    }

    public Ambit getFather() {
        return father;
    }
    
    public boolean isSon(Ambit ambitSon){
        if (id==ambitSon.id) {
            return true;
        }else{
            if (ambitSon.father!=null) {
                return ambitSon.father.isSon(this);
            }else{
                return false;
            }
        }
    }
}
