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
import tac_assembly_generator.TAC.stack.Stack;
import tac_assembly_generator.languages.analyzers.syntax.SyntaxConstAsst;
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
    private Stack stack;
    private ArrayList<String> includeLibraries;

    public TranslateControlerTAC(TAC tac) {
        this.tac = tac;
        includeLibraries = new ArrayList<>();
        mainQuadrupleTable = new QuadrupleTable(null, tempGenerator);
        currentQuadrupleTable = mainQuadrupleTable;
        tempGenerator = new TempGenerator();
        boolQuadControl = new BoolQuadControl(tempGenerator);
    }

    public void setStack(Stack stack) {
        this.stack = stack;
    }

    public BoolQuadControl getBoolQuadControl() {
        return boolQuadControl;
    }

    public void addTempQuads(ArrayList<Object> quads) {

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

    public void addLibrary(String string) {
        includeLibraries.add(string);
    }

    public For createForAsst(ArrayList<Object> assigment, String step, BoolQuad quad, String id) {
        For f =new For(tempGenerator, assigment, step, quad, id);
        f.setStack(stack);
        return f;
    }

    public For createForAsst(ArrayList<Object> assigment, Object step, BoolQuad quad) {
        For f=new For(tempGenerator, assigment, step, quad);
        f.setStack(stack);
        return f;
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
        int asst = 0;
        while (true) {
            if (cont == quads.size()) {
                break;
            }
            Object qObject = quads.get(quads.size() - 1 - cont);
            try {
                Quadruple aux = (Quadruple) qObject;
                asst++;
                if (asst == 3) {
                    quads.add(quads.size() - cont - 2, new Quadruple(Operation.GO_TO, null, null, temp));
                    quads.remove(quads.size() - cont - 2);
                    break;
                } else {
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

    public ArrayList<Object> createInputQuads(String id, String string) {
        ArrayList<Object> quads = new ArrayList<>();
        quads.add(createPrintQuad(string));
        if (id != null) {
            Integer position = stack.getIdPosition(id);
            if (position != null) {
                String temp = tempGenerator.generateIntegerTemp();
                quads.add(new Quadruple(Operation.PLUS, Stack.P, position, temp));
                String temp2 =tempGenerator.generateTemp();
                quads.add(new Quadruple(Operation.READ, null, null, temp2));
                quads.add(new Quadruple(Operation.EQUAL, temp2, null, Stack.getOutputStack(temp)));
                
            }

        } else {
            quads.add(new Quadruple(Operation.READ, null, null, null));
        }
        return quads;
    }

    public ArrayList<String> getIncludeLibraries() {
        return includeLibraries;
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
        ArrayList<Object> aux = new ArrayList<>();
        aux.add(new Quadruple(Operation.FUNCTION, null, null, function + "(){"));
        if (quads != null) {
            aux.addAll(quads);
        }
        aux.add(new Quadruple(Operation.FUNCTION, null, null,"}"));
        
        return aux;
    }

    public Quadruple createPrintQuad(Object print) {
       
        if (print.getClass().equals(String.class)) {
            String text=(String)print;
            if (text.equals(print)) {
                
            }
            return new Quadruple(Operation.PRINT, null, null, (String) print);
        } else {
            SynthesizedOpAsst soa = (SynthesizedOpAsst) print;
            return new Quadruple(Operation.PRINT, null, null, soa.getQuadruple().getResult());
        }

    }

    public ArrayList<Object> createIdPrintQuad(String print) {
        ArrayList<Object> ob = new ArrayList<>();
        Integer position = stack.getIdPosition(print);
        if (position != null) {
            String temp = tempGenerator.generateIntegerTemp();
            ob.add(new Quadruple(Operation.PLUS, Stack.P, position, temp));
            ob.add(new Quadruple(Operation.PRINT, null, null, Stack.getOutputStack(temp)));

        }
        return ob;
    }

    public ArrayList<Object> createInputQuads(String output, String id, String split) {
        ArrayList<Object> quads = new ArrayList<>();
        output = output.replace("\"", "");
        String[] outputs = output.split(split);

        if (outputs.length == 2) {
            if (outputs[0].length() > 0 && !outputs[0].equals("\"")) {
                quads.add(createPrintQuad(outputs[0]));
            }
            quads.add(createPrintQuad(id));
            if (outputs[1].length() > 0 && !outputs[1].equals("\"")) {
                quads.add(createPrintQuad(outputs[1]));
            }
        } else if (outputs.length == 1) {
            if (outputs[0].length() > 0 && !outputs[0].equals("\"")) {
                quads.add(createPrintQuad(outputs[0]));
            }
            quads.add(createPrintQuad(id));
        } else if (outputs.length == 0) {
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
        Quadruple newQuad = null;
        if (arg1.getClass().equals(Quadruple.class)) {
            Quadruple quad = (Quadruple) arg1;
            if (arg2 == null && quad.getArg2() == null) {
                newQuad = new Quadruple(op, quad.getArg1(), arg2, result);
                currentQuadrupleTable.addIdQuad(newQuad);
            } else {
                newQuad = new Quadruple(op, quad.getResult(), arg2, result);
                currentQuadrupleTable.addIdQuad(newQuad);
            }
        } else {
            newQuad = new Quadruple(op, arg1, arg2, result);
            currentQuadrupleTable.addIdQuad(newQuad);
        }

        return newQuad;
    }

    public Quadruple getIdForStack(String id) {
        Integer position = stack.getIdPosition(id);
        if (position != null) {
            String temp = tempGenerator.generateIntegerTemp();
            currentQuadrupleTable.addIdQuad(new Quadruple(Operation.PLUS, Stack.P, position, temp));
            String result = Stack.getOutputStack(temp);
            String temp2 = tempGenerator.generateTemp();
            return creatTempIdQuad(Operation.EQUAL, result, null, temp2);

        } else {
            // return creatTempIdQuad(Operation.EQUAL, soa.getQuadruple().getResult(), null, id);
        }
        return null;
    }
        public String getStackValue(String id) {
        Integer position = stack.getIdPosition(id);
        if (position != null) {
            String temp = tempGenerator.generateIntegerTemp();
            currentQuadrupleTable.addIdQuad(new Quadruple(Operation.PLUS, Stack.P, position, temp));
            String result = Stack.getOutputStack(temp);
            
            return result;

        } else {
            // return creatTempIdQuad(Operation.EQUAL, soa.getQuadruple().getResult(), null, id);
        }
        return null;
    }

    public void removeQuadruple(Quadruple quadruple) {
        tempGenerator.removeTemp(quadruple.getResult());
        currentQuadrupleTable.removeQuad(quadruple);
    }

    public void removeIdQuadruple(Quadruple quadruple) {
        tempGenerator.removeTemp(quadruple.getResult());
        currentQuadrupleTable.removeIdQuad(quadruple);
    }
public void acceptLastIdQuad() {
        currentQuadrupleTable.acceptLastIdQuad();
    }
    public Quadruple createTempIdQuadAssign(Object val, String result) {
        
        if (val.getClass().equals(SynthesizedOpAsst.class)) {
            SynthesizedOpAsst soa = (SynthesizedOpAsst) val;

            Integer position = stack.getIdPosition(result);

            if (position != null) {
                String temp = tempGenerator.generateIntegerTemp();
                
                currentQuadrupleTable.addIdQuad(new Quadruple(Operation.PLUS, Stack.P, position, temp));
                return creatTempIdQuad(Operation.EQUAL, soa.getQuadruple().getResult(), null, Stack.getOutputStack(temp));
            } else {
                return creatTempIdQuad(Operation.EQUAL, soa.getQuadruple().getResult(), null, result);
            }

        } else if (val.getClass().equals(SyntaxConstAsst.class)) {
            SyntaxConstAsst sca = (SyntaxConstAsst) val;
            Integer position = stack.getIdPosition(result);

            if (position != null) {
                String temp = tempGenerator.generateIntegerTemp();
                currentQuadrupleTable.addIdQuad(new Quadruple(Operation.PLUS, Stack.P, position, temp));
                return creatTempIdQuad(Operation.EQUAL, sca.getValue(), null, Stack.getOutputStack(temp));
            } else {
                return creatTempIdQuad(Operation.EQUAL, sca.getValue(), null, result);
            }
        } else {
            return creatTempIdQuad(Operation.EQUAL, val, null, result);
        }

    }
    public void removeIdQuad(){
        currentQuadrupleTable.removeIdQuad();
    }
    public void createTempThisIdQuad(Object val, String result){
        SynthesizedOpAsst soa =(SynthesizedOpAsst)val;
        String temp =tempGenerator.generateIntegerTemp();
        addTempQuadToCurrent(new Quadruple(Operation.PLUS, Stack.P, stack.getIdPosition("this"), temp));
        String temp2 = tempGenerator.generateTemp();
        addTempQuadToCurrent(new Quadruple(Operation.EQUAL, Stack.getOutputStack(temp), null, temp2));
        temp= tempGenerator.generateIntegerTemp();
        
        addTempQuadToCurrent(new Quadruple(Operation.PLUS, temp2, stack.getIdIndexCurrentClass(result), temp));
        addTempQuadToCurrent(new Quadruple(Operation.EQUAL, soa.getQuadruple().getResult(), null, Stack.getOutputHeap(temp)));
        
        
        
    }
    
        public Quadruple getThisIdForStack(String id) {
            
        String temp =tempGenerator.generateIntegerTemp();
        addTempQuadToCurrent(new Quadruple(Operation.PLUS, Stack.P, stack.getIdPosition("this"), temp));
        String temp2 = tempGenerator.generateTemp();
        addTempQuadToCurrent(new Quadruple(Operation.EQUAL, Stack.getOutputStack(temp), null, temp2));
        temp= tempGenerator.generateIntegerTemp();
        
        addTempQuadToCurrent(new Quadruple(Operation.PLUS, temp2, stack.getIdIndexCurrentClass(id), temp)); 
        temp2=tempGenerator.generateTemp();
        Quadruple result=new Quadruple(Operation.EQUAL, Stack.getOutputHeap(temp), null,temp2 );
        
        addTempQuadToCurrent(result);
        
       
        return result;
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
        String q1String;
        if (q1.getArg2() == null && q1.getArg1() != null && !(q1.getArg1().toString().contains(Stack.STACK_NAME)||q1.getArg1().toString().contains(Stack.HEAP_NAME))) {
            q1String = q1.getArg1().toString();
            removeIdQuadruple(q1);
        } else {
            q1String = q1.getResult().toString();
        }
        String q2String;
        if (q2.getArg2() == null && q2.getArg1() != null && !(q1.getArg1().toString().contains(Stack.STACK_NAME)||q1.getArg1().toString().contains(Stack.HEAP_NAME))) {
            q2String = q2.getArg1().toString();
            removeIdQuadruple(q2);
        } else {
            q2String = q2.getResult().toString();
        }

        Quadruple quadruple = creatTempIdQuad(op, q1String, q2String, null);
        return quadruple;
    }

    public Quadruple operateIdBoolQuadruple(Quadruple q1, Quadruple q2, int op) {
        return creatTempIdQuad(op, q1.getResult(), q2.getResult(), null);

    }

    public Quadruple operateIdBoolQuadruple(String q1, Quadruple q2, int op) {
        return creatTempIdQuad(op, q1, q2.getResult(), null);

    }

    public void addClrScrToCurrent() {
        currentQuadrupleTable.addQuad(new Quadruple(Operation.CLRSCR, null, null, null));
    }

    public void removeIdQuads() {
        //remove temperory variables
        currentQuadrupleTable.removeIdQuads();
    }

    public ArrayList<Object> getch(String id) {
        ArrayList<Object> quads = new ArrayList<>();
        if (id != null) {
            quads.add(new Quadruple(Operation.GETCH, null, null, id));
        } else {
            quads.add(new Quadruple(Operation.GETCH, null, null, null));
        }
        return quads;
    }

    public void addHeapManagement(String name) {
        String strings[] = name.split("_");
        String temp = tempGenerator.generateIntegerTemp();
        addTempQuadToCurrent(new Quadruple(Operation.PLUS, Stack.P, stack.getIdPosition("this"), temp));
        addTempQuadToCurrent(new Quadruple(Operation.EQUAL, Stack.H, null, Stack.getOutputStack(temp)));
        addTempQuadToCurrent(new Quadruple(Operation.PLUS, Stack.H, stack.getFunctionSize("JAVA_"+strings[1]), Stack.H));
        acceptAllIdQuas();
        
    }

    public ArrayList<Object> createArrayQuads(String id, ArrayList<Object> dimensions) {
        ArrayList<Object> quads = new ArrayList<>();
//        if (dimensions.size() == 1) {
//            quads.add(new Quadruple(Operation.ARRAY, dimensions.get(0), null, id));
//        } else {
//            String result = (String) dimensions.get(0);
//            for (int i = 1; i < dimensions.size(); i++) {
//                String temp = tempGenerator.generateTemp();
//                quads.add(new Quadruple(Operation.MULTIPLICATION, result, dimensions.get(i), temp));
//                result = temp;
//            }
//            quads.add(new Quadruple(Operation.ARRAY, result, null, id));
//
//        }

        return quads;

    }

    public void addQuadsToCurrent(ArrayList<Object> quads) {
        currentQuadrupleTable.addQuads(quads);
    }

    public void addTempQuadToCurrent(Quadruple quad) {
        currentQuadrupleTable.addIdQuad(quad);
    }

}
