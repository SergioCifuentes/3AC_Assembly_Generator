/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tac_assembly_generator.TAC;

import tac_assembly_generator.TAC.quadruple.*;

/**
 *
 * @author sergio
 */
public class TranslateControlerTAC {
    private TAC tac;
    private QuadrupleTable quadrupleTable;
    private TempGenerator tempGenerator;

    public TranslateControlerTAC(TAC tac) {
        this.tac = tac;
        quadrupleTable= new QuadrupleTable();
        tempGenerator= new TempGenerator();
    }

    public TAC getTac() {
        return tac;
    }
    
    public Quadruple creatTempQuad(int op, Object arg1, Object arg2, String result){
        if (result==null) {
            result=tempGenerator.generate();
        }
        Quadruple newQuad = new Quadruple(op, arg1, arg2, result);
        quadrupleTable.addQuad(newQuad);
        return newQuad;
    }
    
    
}
