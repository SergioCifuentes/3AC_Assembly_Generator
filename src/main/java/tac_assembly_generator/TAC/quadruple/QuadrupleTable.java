/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tac_assembly_generator.TAC.quadruple;

import java.util.ArrayList;

/**
 *
 * @author sergio
 */
public class QuadrupleTable {
    private ArrayList<Quadruple> quadruples;

    public QuadrupleTable() {
        this.quadruples = new ArrayList<>();
    }
    
    public void addQuad(Quadruple quadruple){
        quadruples.add(quadruple);
    }
}
