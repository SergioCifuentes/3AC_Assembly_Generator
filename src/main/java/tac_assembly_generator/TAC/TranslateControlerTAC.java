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
import tac_assembly_generator.languages.semantic.type.TypeManager;

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
        mainQuadrupleTable = new QuadrupleTable(null, tempGenerator);
        currentQuadrupleTable = mainQuadrupleTable;
        tempGenerator = new TempGenerator();
        boolQuadControl = new BoolQuadControl(tempGenerator);
    }

    public BoolQuadControl getBoolQuadControl() {
        return boolQuadControl;
    }

    public void addTempQuads(ArrayList<Object> quads){
        
        for (int i = 0; i < quads.size(); i++) {
            currentQuadrupleTable.addIdQuad(quads.get(i));
        }
    }
    public TempGenerator getTempGenerator() {
        return tempGenerator;
    }

    public TAC getTac() {
        return tac;
    }

    public void createSwitchAsst(String id) {
        currentQuadrupleTable.createSwitchAsst(id);
    }

    public void createIfAsst(String id) {
        //currentQuadrupleTable.createForAsst();
    }

    public Switch getSwitchAsst() {
        return currentQuadrupleTable.getSwitchAsst();
    }

    public For createForAsst(ArrayList<Object> assigment, String step, BoolQuad quad, String id) {
        return new For(tempGenerator, assigment, step, quad, id);
    }
        public For createForAsst(ArrayList<Object> assigment, Object step, BoolQuad quad) {
        return new For(tempGenerator, assigment, step, quad);
    }

    public void addComment(String comment) {

        if (currentQuadrupleTable.getIdQuads().isEmpty()) {
            
            currentQuadrupleTable.addQuad(comment);
        } else {
            
            currentQuadrupleTable.addIdQuad(comment);
        }

    }

    public ArrayList<Object> addGotoWhileTags(ArrayList<Object> quads) {
        String temp = tempGenerator.generateTag();
        quads.add(0, new Quadruple(null, null, null, temp));
        int cont = 0;
        int asst=0;
        while (true) {
            if (cont == quads.size()) {
                break;
            }
            Object qObject = quads.get(quads.size() - 1 - cont);
            try {
                Quadruple aux = (Quadruple) qObject;
                asst++;
                if (asst==3) {
                    quads.add(quads.size() - cont - 2, new Quadruple(Operation.GO_TO, null, null, temp));
                    quads.remove(quads.size() - cont - 2);
                break;
                }else{
                    cont++;
                }

            } catch (Exception e) {
                cont++;
            }
        }
        return quads;
    }

    public void createNewQuadrupleBlock() {
        currentQuadrupleTable = new QuadrupleTable(currentQuadrupleTable, tempGenerator);
    }

    public void acceptCurrentBlock() {
        if (currentQuadrupleTable.getFather() != null) {
            currentQuadrupleTable.transerQuadruples();
            currentQuadrupleTable = currentQuadrupleTable.getFather();
        }
    }

    public ArrayList<Object> createInputQuads(String id,String string){
        ArrayList<Object> quads= new ArrayList<>();
        quads.add(createPrintQuad(string));
        if (id!=null) {
            quads.add(new Quadruple(Operation.READ, null,null, id));
        }else{
            quads.add(new Quadruple(Operation.READ, null,null, null));
        }
        return quads;
    }
    
    public void convertQuads(ArrayList<Object> obs) {
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
        ArrayList<ArrayList<Object>> obAux = currentQuadrupleTable.getIdQuads();
        for (int i = 0; i < obAux.size(); i++) {
            obList.addAll(obAux.get(i));
        }
        currentQuadrupleTable.removeIdQuads();
        return obList;
    }

    public ArrayList<Object> tagFunction(String function, ArrayList<Object> quads) {
        ArrayList<Object> aux= new ArrayList<>();
        aux.add(function + "(){");
        if (quads!=null) {
            aux.addAll(quads);
        }
        aux.add("}");
        return aux;
    }

    public Quadruple createPrintQuad(Object print) {
        if (print.getClass().equals(String.class)) {
            return new Quadruple(Operation.PRINT, null, null, (String) print);
        } else {
            SynthesizedOpAsst soa = (SynthesizedOpAsst) print;
            return new Quadruple(Operation.PRINT, null, null, soa.getQuadruple().getResult());
        }

    }
    public ArrayList<Object> createInputQuads(String output, String id,String split){
        ArrayList<Object> quads= new ArrayList<>();
        output=output.replace("\"", "");
        String[] outputs= output.split(split);
       
        if (outputs.length==2) {
            if (outputs[0].length()>0&&!outputs[0].equals("\"")) {
                quads.add(createPrintQuad(outputs[0]));
            }
            quads.add(createPrintQuad(id));
            if (outputs[1].length()>0&&!outputs[1].equals("\"")) {
                quads.add(createPrintQuad(outputs[1]));
            }   
        }else if(outputs.length==1){
        if (outputs[0].length()>0&&!outputs[0].equals("\"")) {
                quads.add(createPrintQuad(outputs[0]));
            }
            quads.add(createPrintQuad(id));
        }else if(outputs.length==0){
            quads.add(createPrintQuad(id));
        }
        
            
        return quads;
        
        
        
        
    }
    

    public ArrayList<Object> getcurrentQuads() {
      
        ArrayList<Object> obList = new ArrayList<>();
        obList.addAll(currentQuadrupleTable.getQuadruples());

        currentQuadrupleTable.removeQuads();
        return obList;
    }

    public ArrayList<Object> creatDoWhile(ArrayList<Object> code, BoolQuad condition) {
        ArrayList<Object> quads = new ArrayList<>();
        String tag = tempGenerator.generateTag();
        quads.add(new Quadruple(null, null, null, tag));
        quads.addAll(code);
        condition.changeFatherYesBool(new BoolQuad(tag, null, null, null, null), true);
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

    public Quadruple createTempIdQuadAssign(Object val, String result) {
        if (val.getClass().equals(SynthesizedOpAsst.class)) {
             SynthesizedOpAsst soa = (SynthesizedOpAsst) val;
        return creatTempIdQuad(Operation.EQUAL, soa.getQuadruple().getResult(), null, result);
        }else{
            return creatTempIdQuad(Operation.EQUAL, (Integer) val, null, result);
        }
       
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

    public Quadruple operateIdBoolQuadruple(String q1, Quadruple q2, int op) {
        return creatTempIdQuad(op, q1, q2.getResult(), null);

    }

    public void addClrScrToCurrent(){
        currentQuadrupleTable.addQuad(new Quadruple(Operation.CLRSCR, null,null, null));
    }
    public void removeIdQuads() {
        //remove temperory variables
        currentQuadrupleTable.removeIdQuads();
    }
    public ArrayList<Object> getch(String id){
        ArrayList<Object> quads=new ArrayList<>();
        if (id!=null) {
            quads.add(new Quadruple(Operation.GETCH,null, null, id));
        }else{
            quads.add(new Quadruple(Operation.GETCH,null, null,null));
        }
        return quads;
    }

    public ArrayList<Object> createArrayQuads(String id, ArrayList<Object>dimensions){
    ArrayList<Object> quads= new ArrayList<>();
        if (dimensions.size()==1) {
            quads.add(new Quadruple(Operation.ARRAY, dimensions.get(0),null, id));
        }else{
            String result=(String)dimensions.get(0);
            for (int i = 1; i < dimensions.size(); i++) {
                String temp=tempGenerator.generateTemp();
                quads.add( new Quadruple(Operation.MULTIPLICATION, result, dimensions.get(i), temp));
                result=temp;
            }
            quads.add(new Quadruple(Operation.ARRAY, result,null, id));
            
            
        }
        
        return quads;
    
    }
    
    
    public void addQuadsToCurrent(ArrayList<Object> quads) {
        currentQuadrupleTable.addQuads(quads);
    }

    public void addTempQuadToCurrent(Quadruple quad) {
        currentQuadrupleTable.addIdQuad(quad);
    }

}
