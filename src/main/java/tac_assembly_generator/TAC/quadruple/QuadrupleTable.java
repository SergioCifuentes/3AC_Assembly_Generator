/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tac_assembly_generator.TAC.quadruple;

import java.util.ArrayList;
import tac_assembly_generator.TAC.TempGenerator;
import tac_assembly_generator.TAC.asst.For;
import tac_assembly_generator.TAC.asst.Switch;

/**
 *
 * @author sergio
 */
public class QuadrupleTable {

    private ArrayList<Object> quadruples;
    private ArrayList<ArrayList<Object>> idQuads;
    private QuadrupleTable father;
    private Switch switchAsst;
    private For forAsst;
    private TempGenerator tem;

    public QuadrupleTable(QuadrupleTable father,TempGenerator temp) {
        this.father=father;
        tem=temp;
        this.quadruples = new ArrayList<>();
        idQuads = new ArrayList<>();
        idQuads.add(new ArrayList<>());
    }

    public QuadrupleTable getFather() {
        return father;
    }

    public void transerQuadruples() {
        father.addQuads(quadruples);
    }

    public ArrayList<Object> getQuadruples() {
        return quadruples;
    }

    public void addQuads(ArrayList<Object> qList) {
        quadruples.addAll(qList);
    }

    public void addQuad(Object quadruple) {
        quadruples.add(quadruple);
    }

    public void addIdQuad(Object quadruple) {
        
        if (idQuads.isEmpty()) {
            idQuads.add(new ArrayList<>());
        }
        idQuads.get(idQuads.size() - 1).add(quadruple);

    }
    public void createSwitchAsst(String id){
        switchAsst=new  Switch(id,tem);
    }

    public Switch getSwitchAsst() {
        return switchAsst;
    }

    public void acceptIdQuad(int index) {
        addQuads(idQuads.get(index));
    }

    public void removeIdQuads() {
        idQuads.removeAll(idQuads);
        

    }

    public void nextIdQuad() {
        idQuads.add(new ArrayList<Object>());
    }

    public void removeQuad(Quadruple quadruple) {
        quadruples.remove(quadruple);
    }
      public void removeIdQuad(Quadruple quadruple) {
        idQuads.get(idQuads.size() - 1).remove(quadruple);
    }

    public void acceptAllIdQuas() {
        for (int i = 0; i < idQuads.size(); i++) {
            acceptIdQuad(i);
        }
        removeIdQuads();
    }

    public void removeQuads() {
        quadruples.removeAll(quadruples);
    }

    public ArrayList<ArrayList<Object>> getIdQuads() {
        return idQuads;
    }

    
}
