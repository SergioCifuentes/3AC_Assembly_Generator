/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tac_assembly_generator.TAC.stack;

import java.util.ArrayList;
import tac_assembly_generator.languages.semantic.Tuple;

/**
 *
 * @author sergio
 */
public class StackPart {
    private String id;
    private String languaje;
    private ArrayList<Tuple> tuples;
    private String className;
    private boolean currentFunction;
    private Integer current;
    private Integer currentP;

    public StackPart(String id, String languaje) {
        this.id = id;
        this.languaje=languaje;
        tuples= new ArrayList<>();
        className=null;
        currentFunction=false;
        
    }

    public Integer getCurrentP() {
        return currentP;
    }

    public void setCurrentP(Integer currentP) {
        this.currentP = currentP;
    }

    public String getLanguaje() {
        return languaje;
    }


    public void setId(String id) {
        this.id = id;
    }
    
    public void endFunction(){
        currentFunction=false;
    }


    public boolean isCurrentFunction() {
        return currentFunction;
    }
        
    
    public ArrayList<Tuple> getTuples() {
        return tuples;
    }

    public void setTuples(ArrayList<Tuple> tuples) {
        this.tuples = tuples;
    }

    void addTuple(Tuple tuple) {
        
            tuples.add(tuple);
        
        
    }
    void addAtTuple(Tuple tuple,int index) {
       
            tuples.add(index,tuple);
        
        
    }

    public String getId() {
        return id;
    }
    
    
        
}
