/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tac_assembly_generator.TAC;

import java.util.ArrayList;
import tac_assembly_generator.TAC.quadruple.*;
import tac_assembly_generator.languages.analyzers.syntax.SynthesizedOpAsst;

/**
 *
 * @author sergio
 */
public class TranslateControlerTAC {

    private TAC tac;
    private QuadrupleTable mainQuadrupleTable;
    private QuadrupleTable currentQuadrupleTable;
    private BoolQuadControl boolQuadControl;
    private TempGenerator tempGenerator;

    public TranslateControlerTAC(TAC tac) {
        this.tac = tac;
        mainQuadrupleTable = new QuadrupleTable(null);
        currentQuadrupleTable = mainQuadrupleTable;
        tempGenerator = new TempGenerator();
        boolQuadControl = new BoolQuadControl(tempGenerator);
    }

    public BoolQuadControl getBoolQuadControl() {
        return boolQuadControl;
    }

    public TAC getTac() {
        return tac;
    }

    public void addComment(String comment) {
        if (currentQuadrupleTable.getIdQuads().isEmpty()) {
            currentQuadrupleTable.addQuad(comment);
        }else{
            currentQuadrupleTable.addIdQuad(comment);
        }
        
    }

    public void createNewQuadrupleBlock() {
        currentQuadrupleTable = new QuadrupleTable(currentQuadrupleTable);
    }

    public void acceptCurrentBlock() {
        
        if (currentQuadrupleTable.getFather() != null) {
            currentQuadrupleTable.transerQuadruples();
            currentQuadrupleTable = currentQuadrupleTable.getFather();
        }
    }

    public void convertQuads(ArrayList<Object> obs) {
        System.out.println("QUADS TO CONVERT "+obs.size());
        for (int i = 0; i < obs.size(); i++) {
            System.out.println(obs.get(i));
        }
        tac.translateQuads(obs);
    }

    public void rejectCurrentBlock() {
        if (currentQuadrupleTable.getFather() != null) {
            for (int i = 0; i < currentQuadrupleTable.getQuadruples().size(); i++) {
                if (currentQuadrupleTable.getQuadruples().get(i).getClass() == String.class) {
                    currentQuadrupleTable.getFather().addQuad(currentQuadrupleTable.getQuadruples().get(i));
                }
            }
            currentQuadrupleTable = currentQuadrupleTable.getFather();
        }
    }

    public void printQuads() {
        System.out.println("SIZE " + mainQuadrupleTable.getQuadruples().size());
        System.out.println("#  OP  ARGS1  ARGS2 RESULT");
        for (int i = 0; i < mainQuadrupleTable.getQuadruples().size(); i++) {
            Object ob = mainQuadrupleTable.getQuadruples().get(i);
            if (ob.getClass() == Quadruple.class) {
                Quadruple qu = (Quadruple) ob;
                System.out.println(i + " " + qu.getOp() + " " + qu.getArg1() + " " + qu.getArg2() + " " + qu.getResult());
            } else {
                System.out.println(ob.toString());
            }

        }
    }

    public ArrayList<Object> getcurrentTempQuads() {
        ArrayList<Object> obList = new ArrayList<>();
        System.out.println("GETTING TEMP CUADS");
        ArrayList<ArrayList<Object>> obAux = currentQuadrupleTable.getIdQuads();
        for (int i = 0; i < obAux.size(); i++) {
           obList.addAll(obAux.get(i)); 
        }
        System.out.println(obList);
        currentQuadrupleTable.removeIdQuads();
        return obList;
    }

    public ArrayList<Object> getcurrentQuads() {
        
        ArrayList<Object> obList = new ArrayList<>();
        
        obList.addAll(currentQuadrupleTable.getQuadruples());
        System.out.println("GETTING "+obList.size());
        currentQuadrupleTable.removeQuads();
        return obList;
    }

    public Quadruple creatTempQuad(int op, Object arg1, Object arg2, String result) {
        if (result == null) {
            result = tempGenerator.generateTemp();
        }
        Quadruple newQuad = new Quadruple(op, arg1, arg2, result);
        currentQuadrupleTable.addQuad(newQuad);
        return newQuad;
    }

    public Quadruple creatTempIdQuad(int op, Object arg1, Object arg2, String result) {
        if (result == null) {
            result = tempGenerator.generateTemp();
        }
        Quadruple newQuad = new Quadruple(op, arg1, arg2, result);
        currentQuadrupleTable.addIdQuad(newQuad);
        return newQuad;
    }

    public void removeQuadruple(Quadruple quadruple) {
        tempGenerator.removeTemp(quadruple.getResult());
        currentQuadrupleTable.removeQuad(quadruple);
    }

    public void removeIdQuadruple(Quadruple quadruple) {
        tempGenerator.removeTemp(quadruple.getResult());
        currentQuadrupleTable.removeIdQuad(quadruple);
    }

    public Quadruple operateQuadruple(Quadruple q1, Quadruple q2, int op) {
        if (q1.getArg2() == null && q2.getArg2() == null) {
            removeQuadruple(q1);
            removeQuadruple(q2);
            Quadruple newQuadruple = creatTempQuad(op, q1.getArg1(), q2.getArg1(), null);
            return newQuadruple;
        } else {
            Quadruple quadruple = creatTempQuad(op, q1.getResult(), q2.getResult(), null);

            return quadruple;
        }
    }

    public void creatTempIdQuadAssign(Object val, String result) {
        SynthesizedOpAsst soa = (SynthesizedOpAsst) val;
        creatTempIdQuad(Operation.EQUAL, soa.getQuadruple().getResult(), null, result);
    }

    public void nextIdQuad() {
        currentQuadrupleTable.nextIdQuad();
    }

    public void acceptIdQuad(int index) {
        currentQuadrupleTable.acceptIdQuad(index);
    }

    public void acceptAllIdQuas() {
        for (int i = 0; i < currentQuadrupleTable.getIdQuads().size(); i++) {
            acceptIdQuad(i);
        }
        removeIdQuads();
    }

    public Quadruple operateIdQuadruple(Quadruple q1, Quadruple q2, int op) {
        if (q1.getArg2() == null && q2.getArg2() == null) {
            removeIdQuadruple(q1);
            removeIdQuadruple(q2);
            Quadruple newQuadruple = creatTempIdQuad(op, q1.getArg1(), q2.getArg1(), null);
            return newQuadruple;
        } else {
            Quadruple quadruple = creatTempIdQuad(op, q1.getResult(), q2.getResult(), null);
            return quadruple;
        }
    }

    public Quadruple operateIdBoolQuadruple(Quadruple q1, Quadruple q2, int op) {
        return creatTempIdQuad(op, q1.getResult(), q2.getResult(), null);

    }

    public void removeIdQuads() {
        //remove temperory variables
        currentQuadrupleTable.removeIdQuads();
    }
    public void addQuadsToCurrent(ArrayList<Object> quads){
        System.out.println("ADD TO ");
        System.out.println(currentQuadrupleTable);
        System.out.println(currentQuadrupleTable.getFather().equals(mainQuadrupleTable));
        System.out.println(quads.size());
        currentQuadrupleTable.addQuads(quads);
    }
    
}
