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
    private boolean function;
    public Ambit(int id, Ambit father) {
        this.id = id;
        this.father = father;
        function=false;
    }

    public boolean isFunction() {
        return function;
    }

    public void setFunction(boolean function) {
        this.function = function;
    }

    
    public Ambit getFather() {
        return father;
    }

    public int getId() {
        return id;
    }
        
    public boolean isSon(Ambit ambitFatherbit){
        if (id==ambitFatherbit.id) {
            return true;
        }else{
            if (father!=null) {
                return father.isSon(ambitFatherbit);
            }else{
                return false;
            }
        }
    }
}
