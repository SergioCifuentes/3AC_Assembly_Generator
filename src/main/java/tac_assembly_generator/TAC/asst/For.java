/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tac_assembly_generator.TAC.asst;

import java.util.ArrayList;
import tac_assembly_generator.TAC.TempGenerator;
import tac_assembly_generator.TAC.quadruple.BoolQuad;
import tac_assembly_generator.TAC.quadruple.BoolQuadControl;
import tac_assembly_generator.TAC.quadruple.Operation;
import tac_assembly_generator.TAC.quadruple.Quadruple;
import tac_assembly_generator.TAC.stack.Stack;

/**
 *
 * @author sergio
 */
public class For {

    private ArrayList<Object> assigment;
    private BoolQuad compare;
    private ArrayList<Object> step;
    private String stepString=null;
    private TempGenerator tempGenerator;
    private Stack stack;
    private String id;
    private String tag;

 

    public For(TempGenerator tempGenerator, ArrayList<Object> assigment, String step, BoolQuad compare,String id) {
        this.tempGenerator = tempGenerator;
        this.compare=compare;
        this.assigment = assigment;
        this.stepString = step;
        this.id=id;
    }
    
        public For(TempGenerator tempGenerator, ArrayList<Object> assigment, Object step, BoolQuad compare) {
        this.tempGenerator = tempGenerator;
        this.compare=compare;
        this.assigment = assigment;
        this.step=(ArrayList<Object>)step;
        this.id=id;
    }

    public void setStack(Stack stack) {
        this.stack = stack;
    }
        
        

    public void addCode(ArrayList<Object> code){
         tag = tempGenerator.generateTag(); 
        Quadruple stepQuadruple=null; 
        if (stepString!=null) {
            Integer position = stack.getIdPosition(id);
            String temp = tempGenerator.generateIntegerTemp();
            code.add(new Quadruple(Operation.PLUS, Stack.P, position, temp));
            String temp2 = tempGenerator.generateIntegerTemp();
            code.add(new Quadruple(Operation.EQUAL, Stack.getOutputStack(temp), null, temp2));
            
            if (stepString.startsWith("-")) {
                stepString=stepString.replace("-", "");
                 stepQuadruple= new Quadruple(Operation.MINUS,temp2, stepString, Stack.getOutputStack(temp));
            }else{
                stepQuadruple= new Quadruple(Operation.PLUS,temp2, stepString, Stack.getOutputStack(temp));
            }  
            code.add(stepQuadruple);
        }else{
            code.addAll(step);
        }
         Quadruple returnQuad = new Quadruple(Operation.GO_TO, null,null, tag);
        
        code.add(returnQuad);
        compare.changeFatherNoBool(code);
    }
        public void addCode2(ArrayList<Object> code){
         tag = tempGenerator.generateTag(); 
        Quadruple stepQuadruple=null; 
        if (stepString!=null) {
            Integer position = stack.getIdPosition(id);
            String temp = tempGenerator.generateIntegerTemp();
            code.add(new Quadruple(Operation.PLUS, Stack.P, position, temp));
            String temp2 = tempGenerator.generateIntegerTemp();
            code.add(new Quadruple(Operation.EQUAL, Stack.getOutputStack(temp), null, temp2));
            
            if (stepString.startsWith("-")) {
                stepString=stepString.replace("-", "");
                 stepQuadruple= new Quadruple(Operation.MINUS,temp2, stepString, Stack.getOutputStack(temp));
            }else{
                stepQuadruple= new Quadruple(Operation.PLUS,temp2, stepString, Stack.getOutputStack(temp));
            }  
            code.add(stepQuadruple);
        }else{
            code.addAll(step);
        }
         Quadruple returnQuad = new Quadruple(Operation.GO_TO, null,null, tag);
        
        code.add(returnQuad);
        compare.changeFatherYesBool(code);
    }
    
    
    public ArrayList<Object> convertToQuad(){
        ArrayList<Object> quads=new ArrayList<>(); 
        quads.addAll(assigment);
        quads.add(new Quadruple(null, null,null, tag));
        BoolQuadControl control = new BoolQuadControl(tempGenerator);
        quads.addAll(control.convertBoolToQuad(compare));
        
        return quads;
    }
    
}
