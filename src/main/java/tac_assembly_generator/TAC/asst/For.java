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
    private String id;
    private String tag;

    public For(TempGenerator tempGenerator, ArrayList<Object> assigment, ArrayList<Object> step, BoolQuad compare,String id) {
        this.tempGenerator = tempGenerator;
        this.assigment = assigment;
        this.step = step;
        this.compare = compare;
        this.id=id;
    }

    public For(TempGenerator tempGenerator, ArrayList<Object> assigment, String step, BoolQuad compare,String id) {
        this.tempGenerator = tempGenerator;
        this.compare=compare;
        this.assigment = assigment;
        this.stepString = step;
        this.id=id;
    }

    public void addCode(ArrayList<Object> code){
         tag = tempGenerator.generateTag(); 
        Quadruple stepQuadruple=null; 
        if (stepString!=null) {
            if (stepString.startsWith("-")) {
                stepString=stepString.replace("-", "");
                 stepQuadruple= new Quadruple(Operation.PLUS,id, stepString, id);
            }else{
                stepQuadruple= new Quadruple(Operation.PLUS,id, stepString, id);
            }  
        }
         Quadruple returnQuad = new Quadruple(Operation.GO_TO, null,null, tag);
        code.add(stepQuadruple);
        code.add(returnQuad);
        compare.changeFatherNoBool(code);
    }
    public ArrayList<Object> convertToQuad(){
        ArrayList<Object> quads=new ArrayList<>(); 
        quads.addAll(assigment);
        quads.add(new Quadruple(null, null,null, tag));
        BoolQuadControl control = new BoolQuadControl(tempGenerator);
        quads.addAll(control.convertBoolToQuad(compare));
        System.out.println("RETURNINGGGGOOGOGOGO +"+quads.size());
        return quads;
    }
    
}
