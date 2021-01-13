/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tac_assembly_generator.TAC.asst;

import java.util.ArrayList;
import java_cup.runtime.Symbol;
import javax.swing.JTextPane;
import tac_assembly_generator.TAC.TempGenerator;
import tac_assembly_generator.TAC.TranslateControlerTAC;
import tac_assembly_generator.TAC.quadruple.BoolQuad;
import tac_assembly_generator.TAC.quadruple.BoolQuadControl;
import tac_assembly_generator.TAC.quadruple.Operation;
import tac_assembly_generator.TAC.quadruple.Quadruple;
import tac_assembly_generator.languages.semantic.UniquenessTable;

/**
 *
 * @author sergio
 */
public class Switch {

    private String id;
    private BoolQuad fatherCases;
    public static final Object ELSE = "ELSE";
    public BoolQuadControl control;
;
    public Switch(String id,TempGenerator tempGenerator) {
        this.id = id;
        this.control=new BoolQuadControl(tempGenerator);
    }

    public BoolQuad getFatherCases() {
        return fatherCases;
    }

    

    public void addCases(String compare,ArrayList<Object> lines) {     
        BoolQuad boolQuad= control.createCaseBool(id, compare, lines);
        Quadruple quad= (Quadruple)boolQuad.getQuadruple().get(boolQuad.getQuadruple().size()-1);
        if (quad.getArg2().equals(ELSE)) {
            fatherCases.changeFatherNoBool(lines);
        }else{
            if (fatherCases==null) {
                fatherCases=boolQuad;
            }else{
                fatherCases=control.operateBoolQuad(fatherCases, boolQuad, Operation.CASE);
                
            }
        }
    }

}
