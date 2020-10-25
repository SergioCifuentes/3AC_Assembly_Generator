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

    public StackPart(String id, String languaje) {
        this.id = id;
        this.languaje=languaje;
        tuples= new ArrayList<>();
    }

    public String getLanguaje() {
        return languaje;
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

    public String getId() {
        return id;
    }
    
    
        
}
