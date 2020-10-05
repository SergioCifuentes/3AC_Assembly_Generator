/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tac_assembly_generator.TAC;

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
    
    private TempGenerator tempGenerator;

    public TranslateControlerTAC(TAC tac) {
        this.tac = tac;
        mainQuadrupleTable= new QuadrupleTable(null);
        currentQuadrupleTable=mainQuadrupleTable;
        tempGenerator= new TempGenerator();
    }

    public TAC getTac() {
        return tac;
    }
    
    public void createNewQuadrupleBlock(){
        currentQuadrupleTable= new QuadrupleTable(currentQuadrupleTable);
    }
    public void acceptCurrentBlock(){
        System.out.println("ACEPTING "+currentQuadrupleTable.getQuadruples().size());
        System.out.println("Father "+currentQuadrupleTable.getFather());
        for (int i = 0; i < currentQuadrupleTable.getQuadruples().size(); i++) {
            System.out.println(currentQuadrupleTable.getQuadruples().get(i).toString());
        }
        if (currentQuadrupleTable.getFather()!=null) {
            currentQuadrupleTable.transerQuadruples();
            currentQuadrupleTable=currentQuadrupleTable.getFather();
        }
    }
    public void rejectCurrentBlock(){
        if (currentQuadrupleTable.getFather()!=null) {
            currentQuadrupleTable=currentQuadrupleTable.getFather();
        }
    }
           
    public void printQuads(){
        System.out.println("SIZE "+mainQuadrupleTable.getQuadruples().size());
        System.out.println("#  OP  ARGS1  ARGS2 RESULT");
        for (int i = 0; i < mainQuadrupleTable.getQuadruples().size(); i++) {
            Object ob =mainQuadrupleTable.getQuadruples().get(i);
            if (ob.getClass()==Quadruple.class) {
                Quadruple qu = (Quadruple)ob;
                System.out.println(i+" "+qu.getOp()+" "+qu.getArg1()+" "+qu.getArg2()+" "+qu.getResult());
            }else{
                System.out.println(ob.toString());
            }
            
        }
    }
    public Quadruple creatTempQuad(int op, Object arg1, Object arg2, String result){
        if (result==null) {
            result=tempGenerator.generate();
        }
        Quadruple newQuad = new Quadruple(op, arg1, arg2, result);
        currentQuadrupleTable.addQuad(newQuad);
        return newQuad;
    }
    public Quadruple creatTempIdQuad(int op, Object arg1, Object arg2, String result){
        if (result==null) {
            result=tempGenerator.generate();
        }
        Quadruple newQuad = new Quadruple(op, arg1, arg2, result);
        currentQuadrupleTable.addIdQuad(newQuad);
        return newQuad;
    }
    
    public void removeQuadruple(Quadruple quadruple){
        tempGenerator.remove(quadruple.getResult());
        currentQuadrupleTable.removeQuad(quadruple);
    }
    public void removeIdQuadruple(Quadruple quadruple){
        tempGenerator.remove(quadruple.getResult());
        currentQuadrupleTable.removeIdQuad(quadruple);
    }
    
    public Quadruple operateQuadruple(Quadruple q1,Quadruple q2,int op){
        if (q1.getArg2()==null&&q2.getArg2()==null) {
            removeQuadruple(q1);
            removeQuadruple(q2);
            Quadruple newQuadruple= creatTempQuad(op, q1.getArg1(), q2.getArg1(), null);
            return newQuadruple;
        }else{
            Quadruple quadruple= creatTempQuad(op, q1.getResult(), q2.getResult(), null);
            
            return quadruple;
        }
    }
    public void creatTempIdQuadAssign(Object val, String result){
        SynthesizedOpAsst soa= (SynthesizedOpAsst) val;
        creatTempIdQuad(Operation.EQUAL,soa.getQuadruple().getResult(), null, result);
    }
    
    public void nextIdQuad(){
        currentQuadrupleTable.nextIdQuad();
    }
    public void acceptIdQuad(int index){
        currentQuadrupleTable.acceptIdQuad(index);
    }
    
        public Quadruple operateIdQuadruple(Quadruple q1,Quadruple q2,int op){
        if (q1.getArg2()==null&&q2.getArg2()==null) {
            removeIdQuadruple(q1);
            removeIdQuadruple(q2);
            Quadruple newQuadruple= creatTempIdQuad(op, q1.getArg1(), q2.getArg1(), null);
            return newQuadruple;
        }else{
            Quadruple quadruple= creatTempIdQuad(op, q1.getResult(), q2.getResult(), null);
            return quadruple;
        }
    }

    public void removeIdQuads() {
        //remove temperory variables
        currentQuadrupleTable.removeIdQuads();
    }
}
