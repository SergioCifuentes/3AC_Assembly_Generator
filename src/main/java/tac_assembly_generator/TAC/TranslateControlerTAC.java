/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tac_assembly_generator.TAC;

import java.util.ArrayList;
import java_cup.runtime.Symbol;
import tac_assembly_generator.TAC.asst.For;
import tac_assembly_generator.TAC.asst.Switch;
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
        mainQuadrupleTable = new QuadrupleTable(null,tempGenerator);
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
    public void createSwitchAsst(String id){
        currentQuadrupleTable.createSwitchAsst(id);
    }
    public void createIfAsst(String id){
        //currentQuadrupleTable.createForAsst();
    }
    public Switch getSwitchAsst(){
        return currentQuadrupleTable.getSwitchAsst();
    }
    public For createForAsst(ArrayList<Object> assigment, String step,BoolQuad quad, String id){
        return new For(tempGenerator, assigment, step,quad,id);
    }

    public void addComment(String comment) {
        if (currentQuadrupleTable.getIdQuads().isEmpty()) {
            currentQuadrupleTable.addQuad(comment);
        }else{
            currentQuadrupleTable.addIdQuad(comment);
        }
        
    }

    public ArrayList<Object> addGotoWhileTags(ArrayList<Object> quads){
        String temp = tempGenerator.generateTag();
        quads.add(0, new Quadruple(null, null,null, temp));
        int cont=0;
        while (true) {
            if (cont==quads.size()) {
                break;
            }
            Object qObject= quads.get(quads.size()-1-cont);
            try {
                Quadruple aux = (Quadruple) qObject;
                quads.add(quads.size()-cont-2, new Quadruple(Operation.GO_TO, null, null, temp));
                break;
            } catch (Exception e) {
                cont++;
            }
        }
        return quads;
    }
    
    public void createNewQuadrupleBlock() {
        currentQuadrupleTable = new QuadrupleTable(currentQuadrupleTable,tempGenerator);
    }

    public void acceptCurrentBlock() {
        
        if (currentQuadrupleTable.getFather() != null) {
            currentQuadrupleTable.transerQuadruples();
            currentQuadrupleTable = currentQuadrupleTable.getFather();
        }
    }

    public void convertQuads(ArrayList<Object> obs) {
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

    public Quadruple createPrintQuad(Object print){
        if (print.getClass().equals(String.class)) {
            return new Quadruple(Operation.PRINT, null,null, (String)print);
        }else{
            SynthesizedOpAsst soa= (SynthesizedOpAsst)print;
            return new Quadruple(Operation.PRINT, null,null,soa.getQuadruple().getResult());
        }
        
    }
    
    public ArrayList<Object> getcurrentQuads() {
        
        ArrayList<Object> obList = new ArrayList<>();
        
        System.out.println(currentQuadrupleTable.equals(mainQuadrupleTable));
        
        obList.addAll(currentQuadrupleTable.getQuadruples());
        
        currentQuadrupleTable.removeQuads();
        return obList;
    }

    public ArrayList<Object> creatDoWhile(ArrayList<Object> code, BoolQuad condition){
        ArrayList<Object> quads= new ArrayList<>();
        String tag= tempGenerator.generateTag();
        quads.add(new Quadruple(null,null,null,tag));
        quads.addAll(code);
        condition.changeFatherYesBool(new BoolQuad(tag, null, null,null,null), true);
        quads.addAll(boolQuadControl.convertBoolToQuad(condition));
        return quads;
        
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
        System.out.println("ACCEPTINGGGGGGGGGGGGG   ++"+currentQuadrupleTable.getIdQuads().size());
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
        public Quadruple operateIdBoolQuadruple(String q1, Quadruple q2, int op) {
        return creatTempIdQuad(op, q1, q2.getResult(), null);

    }

    public void removeIdQuads() {
        //remove temperory variables
        currentQuadrupleTable.removeIdQuads();
    }
    public void addQuadsToCurrent(ArrayList<Object> quads){
        System.out.println("ADD TO "+quads);
        System.out.println(currentQuadrupleTable);
        System.out.println(currentQuadrupleTable.getFather().equals(mainQuadrupleTable));
        System.out.println(currentQuadrupleTable.equals(mainQuadrupleTable));
        System.out.println(quads.size());
        currentQuadrupleTable.addQuads(quads);
    }
    public void addTempQuadToCurrent(Quadruple quad){
        currentQuadrupleTable.addIdQuad(quad);
    }
    
}
