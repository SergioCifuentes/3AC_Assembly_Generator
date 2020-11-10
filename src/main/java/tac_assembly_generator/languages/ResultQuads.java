/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tac_assembly_generator.languages;

import java.util.ArrayList;
import tac_assembly_generator.TAC.TAC;
import tac_assembly_generator.TAC.TempGenerator;
import tac_assembly_generator.TAC.quadruple.Operation;
import tac_assembly_generator.TAC.quadruple.Quadruple;
import tac_assembly_generator.TAC.stack.Stack;

/**
 *
 * @author sergio
 */
public class ResultQuads {
    private ArrayList<Object> quadruples;
    private Stack stack;
    private TempGenerator tempGenerator;
    private ArrayList<String> liberies;
    
    private TAC tac;

    public ResultQuads(ArrayList<Object> quadruples, Stack stack, TempGenerator tempGenerator, ArrayList<String> liberies) {
        this.quadruples = quadruples;
        this.stack = stack;
        this.tempGenerator = tempGenerator;
        this.liberies = liberies;
    }

    public void setTac(TAC tac) {
        this.tac = tac;
    }

    public void setQuadruples(ArrayList<Object> quadruples) {
        this.quadruples = quadruples;
    }

    public void setStack(Stack stack) {
        this.stack = stack;
    }

    public void setTempGenerator(TempGenerator tempGenerator) {
        this.tempGenerator = tempGenerator;
    }

    public void setLiberies(ArrayList<String> liberies) {
        this.liberies = liberies;
    }

    public ArrayList<Object> getQuadruples() {
        return quadruples;
    }

    public Stack getStack() {
        return stack;
    }

    public TempGenerator getTempGenerator() {
        return tempGenerator;
    }

    public ArrayList<String> getLiberies() {
        return liberies;
    }

    public TAC getTac() {
        return tac;
    }
    
    
    public void convertQuads() {
        ArrayList<Object> tempQuad= new ArrayList<>();
        tempQuad.addAll(quadruples);
        //tempGenerator.addTempDeclarations(tempQuad);
        //tempQuad.add(0, new Quadruple(Operation.ARRAY,stack.getStackSize(), null,"float stack"));
        //tempQuad.add(1, new Quadruple(Operation.EQUAL,0, null,"int p"));
        //addLibraries(tempQuad);
        tac.translateQuads(tempQuad,stack);
    }
        
    
       private void addLibraries(ArrayList<Object> obs){
        int aux= 0;
        for (int i = 0; i < liberies.size(); i++) {
            obs.add(aux,new Quadruple(Operation.INCLUDE, null, null, liberies.get(i)));
            aux++;
        }
    }
       
       public  void reduceQuads(){
           boolean gotoBool=false;
           ArrayList<Integer> toDelete= new ArrayList<>();
            for (int i = 0; i < quadruples.size(); i++) {
                if (quadruples.get(i).getClass().equals(Quadruple.class)) {
                    Quadruple quad = (Quadruple) quadruples.get(i);
                    if (quad.getOp()==null) {
                        gotoBool=false;
                    } else if (quad.getOp()==Operation.GO_TO) {
                        if (gotoBool) {
                            toDelete.add(i);
                        }else{
                            gotoBool=true;
                        }
                    }else{
                        gotoBool=false;
                    }
                }
           }
            for (int i = toDelete.size()-1; i >= 0; i--) {
               quadruples.remove(toDelete.get(i).intValue());
           }
       }
       
       
    
}
