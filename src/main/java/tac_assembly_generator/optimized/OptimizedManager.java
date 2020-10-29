/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tac_assembly_generator.optimized;

import java.util.ArrayList;
import javax.swing.JTextPane;
import tac_assembly_generator.TAC.quadruple.Operation;
import tac_assembly_generator.TAC.quadruple.Quadruple;
import tac_assembly_generator.languages.ResultQuads;

/**
 *
 * @author sergio
 */
public class OptimizedManager {
    private ResultQuads resultQuads;
    private ArrayList<Integer> changedLines;

    public OptimizedManager(ResultQuads resultQuads) {
        changedLines= new ArrayList<>();
        this.resultQuads.setLiberies(resultQuads.getLiberies());
        this.resultQuads.setStack(resultQuads.getStack());
        this.resultQuads.setTac(resultQuads.getTac());
        this.resultQuads.setTempGenerator(resultQuads.getTempGenerator());
        ArrayList<Object> newQuaList= new ArrayList<>();
        newQuaList.addAll(resultQuads.getQuadruples());
        this.resultQuads.setQuadruples(newQuaList);
        this.resultQuads = resultQuads;
        
    }
    
    public ResultQuads optimize(){
        boolean recentChange=false;
        do {
            recentChange= reduceNumberOperations();
            
        } while (recentChange);
        return resultQuads;
        
        
    }
    public boolean reduceNumberOperations(){
        boolean change=false;
        ArrayList<Object> asstQuads= resultQuads.getQuadruples();
        for (int i = 0; i < asstQuads.size(); i++) {
            if (asstQuads.get(i).getClass().equals(Quadruple.class)) {
                Quadruple quad = (Quadruple)asstQuads.get(i);
                if (quad.getOp()==Operation.PLUS||quad.getOp()==Operation.MINUS||quad.getOp()==Operation.DIVISION
                        ||quad.getOp()==Operation.MULTIPLICATION||quad.getOp()==Operation.MOD) {
                    if (quad.getArg2()!=null) {
                        try {
                            Float num1= Float.parseFloat(quad.getArg1().toString());
                            Float num2= Float.parseFloat(quad.getArg2().toString());
                            Float result=0f;
                            switch (quad.getOp()) {
                                case Operation.PLUS:
                                    result=num1+num2;
                                    break;
                                case Operation.MINUS:
                                    result=num1-num2;
                                    break;
                                case Operation.MULTIPLICATION:
                                    result=num1*num2;
                                    break;
                                case Operation.DIVISION:
                                    result=num1/num2;
                                    break;
                                case Operation.MOD:
                                    result=num1%num2;
                                    break;
                                default:
                                    throw new AssertionError();
                            }
                            if (result %1==0) {
                                Integer in= (int)Math.round(result);
                                Quadruple newQuad= new Quadruple(Operation.EQUAL, in, null,quad.getResult());
                                asstQuads.remove(i);
                                asstQuads.add(i,newQuad);
                            }else{
                                Quadruple newQuad= new Quadruple(Operation.EQUAL, result, null,quad.getResult());
                                asstQuads.remove(i);
                                asstQuads.add(i,newQuad);
                            }
                            
                        } catch (Exception e) {
                        }
                    }
                }
            }
        }
        return change;
        
        
    }
    
    
}
