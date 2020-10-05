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

    private ArrayList<Object> quadruples;
    private ArrayList<ArrayList<Object>> idQuads;
    private QuadrupleTable father;

    public QuadrupleTable(QuadrupleTable father) {
        this.father=father;
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

    public void addQuad(Quadruple quadruple) {
        quadruples.add(quadruple);
    }

    public void addIdQuad(Quadruple quadruple) {

        idQuads.get(idQuads.size() - 1).add(quadruple);

    }

    public void acceptIdQuad(int index) {
        addQuads(idQuads.get(index));
    }

    public void removeIdQuads() {
        idQuads.removeAll(idQuads);
        idQuads.add(new ArrayList<>());

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
}
