/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tac_assembly_generator.languages.semantic.verification;

import java.awt.Color;
import java.util.ArrayList;
import java_cup.runtime.Symbol;
import javax.swing.JTextPane;
import tac_assembly_generator.TAC.TranslateControlerTAC;
import tac_assembly_generator.TAC.quadruple.Operation;
import tac_assembly_generator.TAC.quadruple.Quadruple;
import tac_assembly_generator.TAC.stack.Stack;
import tac_assembly_generator.languages.analyzers.syntax.SyntaxConstAsst;
import tac_assembly_generator.languages.analyzers.syntax.SyntaxMlgAnalyzer;
import tac_assembly_generator.languages.analyzers.syntax.SynthesizedOpAsst;
import tac_assembly_generator.languages.semantic.AmbitControler;
import tac_assembly_generator.languages.semantic.SymbolTable;
import tac_assembly_generator.languages.semantic.Tuple;
import tac_assembly_generator.languages.semantic.UniquenessTable;
import tac_assembly_generator.languages.semantic.type.Type;
import tac_assembly_generator.languages.semantic.type.TypeManager;
import tac_assembly_generator.ui.MainFrame;
import tac_assembly_generator.ui.backend.OutputErrors;
import tac_assembly_generator.ui.backend.OutputText;

/**
 *
 * @author sergio
 */
public class TestManager {

    private SymbolTable symbolTable;
    private SyntaxMlgAnalyzer sma;
    private boolean semanticError;
    private boolean semanticRecoverableError;
    private ArrayList<Tuple> preTupleSymbols;
    private TypeManager typeManager;
    private AmbitControler ambitControler;
    private MainFrame mainFrame;
    private ParameterControl parameterControl;
    private Tuple currentClass;
    private ArrayList<Tuple> includedTuples;
    private Stack stack;

    public TestManager(MainFrame mainFrame) {
        typeManager = new TypeManager();
        typeManager.loadTypes(TypeManager.VB_TYPES);
        preTupleSymbols = new ArrayList<>();
        symbolTable = new SymbolTable();
        semanticError = false;
        semanticRecoverableError = false;
        ambitControler = new AmbitControler();
        this.mainFrame = mainFrame;
        includedTuples = new ArrayList<>();
        parameterControl = new ParameterControl(typeManager);
    }

    public Tuple getCurrentClass() {
        return currentClass;
    }

    public void setSma(SyntaxMlgAnalyzer sma) {
        this.sma = sma;
    }

    public void setCurrentClass(Tuple currentClass) {
        if (currentClass == null) {
            stack.changeCurrent("main");
        }
        this.currentClass = currentClass;
    }

    public void switchNextTypes() {
        typeManager.loadnextType();
    }

    public void enterMain() {
        stack.enterMain();
    }

    public void creatFatherAmbit() {
        ambitControler.createFatherAmbit();
    }

    public void creatSonAmbit() {
        ambitControler.createSonAmbit();
    }
    public void creatSonAmbit2(String id) {
        System.out.println("ID "+id);
        ambitControler.createSonAmbit();
    }

    public void setAmbitFuction() {
        ambitControler.getCurrentAmbit().setFunction(true);
    }

    public UniquenessTable createNewUniquenessTable() {
        return new UniquenessTable(mainFrame.getOutputPannel());
    }

    public void finishAmbit() {
        ambitControler.finishAmbit();
    }

    public ParameterControl getParameterControl() {
        return parameterControl;
    }

    public void insertPreTuplesToSymbolTable(Integer typeNumber, Integer dimension, Symbol symbol, TranslateControlerTAC tAC) {
        tAC.nextIdQuad();
        for (int i = 0; i < preTupleSymbols.size(); i++) {
            if (preTupleSymbols.get(i).getValue() != null) {

                SynthesizedOpAsst so = (SynthesizedOpAsst) preTupleSymbols.get(i).getValue();
                if ((so.getType().getNumber() == typeNumber) || so.getType().isFather(typeManager.getType(typeNumber))) {
                    preTupleSymbols.get(i).setDimension(dimension);
                    preTupleSymbols.get(i).setType(typeManager.getType(typeNumber));
                    preTupleSymbols.get(i).setStackInfo(1);
                    symbolTable.insertTuple(preTupleSymbols.get(i));
                    stack.addToCurrentStack(preTupleSymbols.get(i));
                    tAC.createTempIdQuadAssign(preTupleSymbols.get(i).getValue(), preTupleSymbols.get(i).getName());
                    tAC.acceptIdQuad(i);
                    tAC.acceptLastIdQuad();

                } else {
                    OutputErrors.assignmentError(mainFrame.getOutputPannel(), so.getType().getName(), typeManager.getOutputType(typeNumber), symbol);

                }
                //cast test
            } else {
                Tuple findExisting = symbolTable.getTupleWithAmbit(preTupleSymbols.get(i).getName(), ambitControler.getCurrentAmbit());
                if (findExisting != null) {
                    OutputErrors.alreadyDeclared(mainFrame.getOutputPannel(), preTupleSymbols.get(i).getName(), symbol);

                } else {
                    preTupleSymbols.get(i).setDimension(dimension);
                    preTupleSymbols.get(i).setType(typeManager.getType(typeNumber));
                    preTupleSymbols.get(i).setStackInfo(1);
                    symbolTable.insertTuple(preTupleSymbols.get(i));
                    stack.addToCurrentStack(preTupleSymbols.get(i));
                    tAC.acceptIdQuad(i);
                }

            }
        }
        tAC.removeIdQuads();
        preTupleSymbols.removeAll(preTupleSymbols);
    }

    public Type getTypeFromST(String id, Symbol symbol) {
        return symbolTable.getTypeWithAmbit(id, ambitControler.getCurrentAmbit(), mainFrame, symbol);
    }

    public void verifyPrints(ArrayList<Object> obs, Symbol s, TranslateControlerTAC tac) {
        int aux = 0;
        for (int i = 0; i < obs.size(); i++) {
            if (obs.get(i).getClass().equals(SynthesizedOpAsst.class)) {
                aux++;
            }
        }
        if (aux >= 2) {
            sma.error = true;
            OutputErrors.printsParameters(mainFrame.getOutputPannel(), s);
        } else if (obs.get(0).getClass().equals(String.class) && obs.size() == 1) {

            tac.addTempQuadToCurrent(tac.createPrintQuad((String) obs.get(0)));
            tac.acceptAllIdQuas();
        } else {

            if (obs.get(0).getClass().equals(String.class)) {
                if (obs.get(obs.size() - 1).getClass().equals(SynthesizedOpAsst.class)) {
                    SynthesizedOpAsst soa = (SynthesizedOpAsst) obs.get(obs.size() - 1);
                    Integer inte = soa.getType().getNumber();
                    String text = (String) obs.get(0);
                    String[] st;
                    switch (inte) {
                        case TypeManager.CHAR_TYPE:
                            st = text.split("%c");
                            if (st.length > 1) {
                                if (!st[0].equals("\"")) {

                                    tac.addTempQuadToCurrent(tac.createPrintQuad(st[0] + "\""));
                                }
                                for (int i = 1; i < obs.size() - 1; i++) {
                                    tac.addTempQuadToCurrent((Quadruple) obs.get(i));
                                }

                                tac.addTempQuadToCurrent(tac.createPrintQuad(soa));
                                if (!st[1].equals("\"")) {
                                    tac.addTempQuadToCurrent(tac.createPrintQuad("\"" + st[1]));

                                }
                                break;
                            }

                        case TypeManager.INTEGER_TYPE:
                            st = text.split("%d");
                            
                            if (st.length > 1) {
                                if (!st[0].equals("\"")) {

                                    tac.addTempQuadToCurrent(tac.createPrintQuad(st[0] + "\""));
                                }
                                for (int i = 1; i < obs.size() - 1; i++) {
                                    tac.addTempQuadToCurrent((Quadruple) obs.get(i));
                                }

                                tac.addTempQuadToCurrent(tac.createPrintQuad(soa));
                                if (!st[1].equals("\"")) {
                                    tac.addTempQuadToCurrent(tac.createPrintQuad("\"" + st[1]));

                                }
                                break;
                            }

                        case TypeManager.FLOAT_TYPE:
                            st = text.split("%f");
                            if (st.length > 1) {
                                if (!st[0].equals("\"")) {

                                    tac.addTempQuadToCurrent(tac.createPrintQuad(st[0] + "\""));
                                }
                                for (int i = 1; i < obs.size() - 1; i++) {
                                    tac.addTempQuadToCurrent((Quadruple) obs.get(i));
                                }

                                tac.addTempQuadToCurrent(tac.createPrintQuad(soa));
                                if (!st[1].equals("\"")) {
                                    tac.addTempQuadToCurrent(tac.createPrintQuad("\"" + st[1]));

                                }
                            } else {
                                System.out.println("NO existe %");
                                //No existing %d
                            }
                            break;
                        default:
                            throw new AssertionError();
                    }
                    tac.acceptAllIdQuas();
                } else {
                    System.out.println("las not soa");
                    //last not soa

                }
            } else {
                System.out.println("Debe empezar con un string ");
                //Debe empezar con un string 
            }
        }

//        boolean primero=true;
//        ArrayList<Object> prints=new ArrayList<>();
//        ArrayList<Object> quads=new ArrayList<>();
//        
//        for (int i = 0; i < obs.size(); i++) {
//            if (obs.get(i).getClass()!=Quadruple.class) {
//                if (primero) {
//                    primero=false;
//                    if (obs.get(i).getClass().equals(String.class)) {
//                        if (obs.size()==1) {
//                        }else{
//                            prints.add(prints);
//                        }
//                    }else{
//                        //Error primero no es string
//                        
//                    }
//                }else{
//                    prints.add(obs.get(i));
//                }
//            }else{
//                quads.add(obs.get(i));
//            }
//        }
//        System.out.println("PRINT");
//        return null;
    }

    public void insertTuple(String id, Integer type, Symbol symbol) {
        Tuple tuple = new Tuple(id, typeManager.getType(type), null, 0, symbol, ambitControler.getCurrentAmbit());
        tuple.setStackInfo(1);
        if (stack.getIdPosition(id) == null) {
            stack.addToCurrentStack(tuple);
        }
        symbolTable.insertTuple(tuple);
    }

    public String testInput(String id, String output, Symbol symbol) {
        Type type = getTypeFromST(id, symbol);
        Integer inputType = null;
        String split = null;
        int types = 0;
        if (type != null) {
            if (output.contains(TypeManager.INT_INPUT)) {
                split = TypeManager.INT_INPUT;
                inputType = TypeManager.INTEGER_TYPE;
                types += 1;
            } else if (output.contains(TypeManager.CHAR_INPUT)) {
                split = TypeManager.CHAR_INPUT;
                inputType = TypeManager.CHAR_TYPE;
                types += 1;
            } else if (output.contains(TypeManager.FLOAT_INPUT)) {
                split = TypeManager.FLOAT_INPUT;
                inputType = TypeManager.FLOAT_TYPE;
                types += 1;
            }
            if (types > 1) {
                OutputErrors.multipleMasks(mainFrame.getOutputPannel(), id, symbol);
            } else if (types == 0) {
                OutputErrors.noMasks(mainFrame.getOutputPannel(), id, symbol);
            } else {
                if (verifyType(type.getNumber(), inputType, symbol, id)) {
                    return split;
                }
            }
        }
        return null;
    }

    public SynthesizedOpAsst getArrayType(String id, Object dimensions, Symbol symbol, TranslateControlerTAC tac) {
        System.out.println("IDDD "+id);
        System.out.println("DII"+ dimensions);
        System.out.println(symbol.right);

        System.out.println(symbol.left);
        Tuple tuple = symbolTable.getTupleWithAmbit(id, ambitControler.getCurrentAmbit());
        ArrayList<Object> dimension = (ArrayList<Object>) dimensions;
        if (tuple == null) {
            OutputErrors.notDeclared(mainFrame.getOutputPannel(), id, symbol);
        } else {
            if (tuple.getDimensions() == 0) {
                OutputErrors.notArray(mainFrame.getOutputPannel(), id, symbol);
            } else {
                if (tuple.getDimension().size() != dimension.size()) {
                    OutputErrors.arrayOutOfBounds(mainFrame.getOutputPannel(), id, tuple.getDimension().size(), dimension.size(), symbol);
                } else {
                    ArrayList<Object> quads = new ArrayList<Object>();
                    ArrayList<Object> sizes = tuple.getDimension();
                    ArrayList<String> results = new ArrayList<String>();
                    if (dimension.size()==1) {
                        System.out.println("STTTTT "+dimension.get(0).toString());
                        results.add(dimension.get(0).toString());
                        
                    }else{
                        for (int i = 0; i < dimension.size(); i++) {
                        String result = null;
                        if (i == dimension.size() - 1) {
                            results.add(tac.getTempGenerator().generateTemp());
                            quads.add(new Quadruple(Operation.PLUS, results.get(results.size() - 2), dimension.get(dimension.size() - 1 - i), results.get(results.size() - 1)));
                            break;
                        }
                        if (i < dimension.size() - 1) {
                            result = String.valueOf(sizes.get(sizes.size() - 2 - i));
                        }
                        String temp = null;

                        for (int j = sizes.size() - 3 - i; j >= 0; j--) {
                            temp = tac.getTempGenerator().generateTemp();

                            quads.add(new Quadruple(Operation.MULTIPLICATION, result, sizes.get(j), temp));
                            result = temp;
                        }
                        String result2 = tac.getTempGenerator().generateTemp();
                        quads.add(new Quadruple(Operation.MULTIPLICATION, dimension.get(dimension.size() - 1 - i), result, result2));
                        results.add(result2);
                        if (results.size() > 1) {
                            String result3 = tac.getTempGenerator().generateTemp();
                            quads.add(new Quadruple(Operation.PLUS, results.get(results.size() - 2), results.get(results.size() - 1), result3));
                            results.add(result3);
                        }

                    }
                    }
                    
                    String temp = tac.getTempGenerator().generateTemp();
                    quads.add(new Quadruple(Operation.PLUS, Stack.P, stack.getIdPosition(id), temp));

                    String temp2 = tac.getTempGenerator().generateIntegerTemp();

                    quads.add(new Quadruple(Operation.PLUS, temp, results.get(results.size() - 1), temp2));
                    System.out.println("===========");
                    for (int i = 0; i < quads.size(); i++) {
                        System.out.println(quads.get(i));
                    }
                    System.out.println("$$$$$$$$$$");
                    tac.addTempQuads(quads);
                    tac.acceptAllIdQuas();
                    System.out.println("RETURNING "+Stack.getOutputStack(temp2));
                    return new SynthesizedOpAsst(new Quadruple(Operation.EQUAL, null, null, Stack.getOutputStack(temp2)), tuple.getType());
                }
            }
        }
        return null;

    }

    public void insertArray(String id, Integer type, ArrayList<Object> objects, Symbol symbol) {

        Tuple tuple = new Tuple(id, typeManager.getType(type), null, objects.size(), symbol, ambitControler.getCurrentAmbit());

        tuple.setDimensions(objects);
        int size = (Integer) objects.get(0);
        for (int i = 1; i < objects.size(); i++) {
            size *= (Integer) objects.get(i);
        }
        tuple.setStackInfo(size);
        stack.addToCurrentStack(tuple);
        symbolTable.insertTuple(tuple);
    }

    public Integer getConstValue(String id, Symbol s) {
        Type type = getTypeFromST(id, s);

        if (type != null) {
            Tuple tuple = symbolTable.getTupleWithAmbit(id, ambitControler.getCurrentAmbit());

            if (tuple != null && tuple.isConstante()) {
                if (type.equals(typeManager.getType(TypeManager.INTEGER_TYPE))) {
                    return (Integer) tuple.getValue();
                } else if (type.equals(typeManager.getType(TypeManager.CHAR_TYPE))) {
                    char charValue = (char) tuple.getValue();
                    return (int) charValue;
                } else {
                    //Not integer value error
                }
            } else {
                //Not const error
            }

        }
        return null;
    }

    public SyntaxConstAsst operateConstAsst(Object const1, Object const2, Integer op, Symbol s) {
        SyntaxConstAsst sc1 = (SyntaxConstAsst) const1;
        SyntaxConstAsst sc2 = (SyntaxConstAsst) const2;
        Type type = operateType(sc1.getType(), sc2.getType(), s);
        if (type != null) {
            switch (type.getNumber()) {
                case TypeManager.INTEGER_TYPE:
                    Object val = Operation.operateInteger(sc1.getValue(), sc2.getValue(), op);
                    return new SyntaxConstAsst(val, type.getNumber());
                case TypeManager.FLOAT_TYPE:
                    Object val2 = Operation.operateFloat(sc1.getValue(), sc2.getValue(), op);
                    return new SyntaxConstAsst(val2, type.getNumber());
                case TypeManager.CHAR_TYPE:
                    Object val3 = Operation.operateInteger(sc1.getValue(), sc2.getValue(), op);
                    return new SyntaxConstAsst(val3, type.getNumber());
                default:
                    throw new AssertionError();
            }
        }
        return null;
    }

    public boolean insertConst(String id, Integer type, Object val, Symbol s) {
        SyntaxConstAsst sc = (SyntaxConstAsst) val;
        if (typeManager.getType(sc.getType()).isFather(typeManager.getType(type)) || typeManager.getType(type).equals(typeManager.getType(sc.getType()))) {
            Tuple tuple = new Tuple(id, typeManager.getType(type), sc.getValue(), 0, s, ambitControler.getCurrentAmbit());
            tuple.setStackInfo(1);
            tuple.setConstante(true);
            symbolTable.insertTuple(tuple);
            stack.addToCurrentStack(tuple);
            return true;

        } else {

            OutputErrors.typeOpError(mainFrame.getOutputPannel(), typeManager.getOutputType(type), typeManager.getOutputType(sc.getType()), s);
        }
        return false;

    }

    public Stack getStack() {
        return stack;
    }

    public boolean insertDeclaration(String id, Integer type, Object val, Symbol symbol) {
        if (!checkExistence(id, symbol)) {
            if (val != null) {
                SynthesizedOpAsst si = (SynthesizedOpAsst) val;
                if (verifyType(type, si.getType().getNumber(), symbol, id)) {
                    symbolTable.insertTuple(new Tuple(id, typeManager.getType(type), null, 0, symbol, ambitControler.getCurrentAmbit()));
                    return true;
                }

            }
        }
        return false;

    }

    public boolean verifyIntegerValue(Object ob, Symbol s) {
        SynthesizedOpAsst soa = (SynthesizedOpAsst) ob;

        if (soa.getType().equals(typeManager.getType(TypeManager.INTEGER_TYPE))) {
            return true;
        } else {
            OutputErrors.notIntegerType(mainFrame.getOutputPannel(), typeManager.getOutputType(soa.getType().getNumber()), s);
            return false;
        }

    }

    public MainFrame getMainFrame() {

        return mainFrame;
    }

    public void setStack(Stack stack) {
        this.stack = stack;
    }

    public String insertFunction(String id, Integer type, Symbol s) {

        Type ty = null;
        if (type != null) {
            ty = typeManager.getType(type);
        }
        for (int i = 0; i < parameterControl.getIds().size(); i++) {
            for (int j = i + 1; j < parameterControl.getIds().size(); j++) {

                if (i != j && parameterControl.getIds().get(i).equals(parameterControl.getIds().get(j))) {
                    OutputErrors.alreadyDeclaredParameter(mainFrame.getOutputPannel(), parameterControl.getIds().get(i), parameterControl.getSymbols().get(i));
                    return null;
                }
            }
        }

        Tuple tuple = symbolTable.insertFunction(id, ty, parameterControl, s, ambitControler.getCurrentAmbit(), mainFrame.getOutputPannel());

        if (tuple == null) {
            parameterControl.removeParameters();
            return null;
        } else {
            String name = tuple.generateFunctionName(typeManager.getLanguage());
            if (id != "main") {
                stack.creatNewStack(tuple.getName(), typeManager.getLanguage());
            }
            ArrayList<Tuple> paArrayList = insertParameters();
            tuple.setParameters(paArrayList);
            parameterControl.removeParameters();

            return name;
        }

    }

    public String insertFunction(String id, Integer type, Symbol s, Tuple tuple) {
        Type ty = null;
        if (type != null) {
            ty = typeManager.getType(type);
        }
        for (int i = 0; i < parameterControl.getIds().size(); i++) {
            for (int j = i + 1; j < parameterControl.getIds().size(); j++) {

                if (i != j && parameterControl.getIds().get(i).equals(parameterControl.getIds().get(j))) {
                    OutputErrors.alreadyDeclaredParameter(mainFrame.getOutputPannel(), parameterControl.getIds().get(i), parameterControl.getSymbols().get(i));
                    return null;
                }
            }
        }
        Tuple tuple1 = symbolTable.insertFunction(id, ty, parameterControl, s, ambitControler.getCurrentAmbit(), mainFrame.getOutputPannel());

        if (tuple1 == null) {
            parameterControl.removeParameters();
            return null;
        } else {
            tuple1.setClassFather(tuple);
            stack.creatNewStack(tuple1.getName(), typeManager.getLanguage());
            ArrayList<Tuple> paArrayList = insertParameters();
            tuple1.setParameters(paArrayList);
            parameterControl.removeParameters();
            String name = tuple1.generateFunctionName(typeManager.getLanguage());
            stack.changeName(tuple1.getName());
            stack.addThis();
            if (ty != null) {
                stack.addReturn();
            }
            return name;
        }

    }

    public Type verifyClassId(String id, Symbol s) {
        String className = stack.getCurrentClassName();
        Tuple tuple = stack.getTupleIdFromFunction(className, id);
        if (tuple != null) {
            return tuple.getType();
        } else {
            OutputErrors.notDeclaredThisId(mainFrame.getOutputPannel(), id, className, s);
        }
        return null;
    }

    public boolean testIfInClass() {
        return stack.getCurrentClassName() != null;
    }

    public Tuple insertClass(String id, Symbol s) {

        if (symbolTable.getTupleWithAmbit(id, ambitControler.getCurrentAmbit(), TypeManager.CLASS) != null) {
            return null;
        } else {
            Tuple tuple = new Tuple(id, TypeManager.CLASS, null, 0, s, ambitControler.getCurrentAmbit());
            symbolTable.insertTuple(tuple);
            stack.creatNewStack(typeManager.getLanguage() + "_" + id, typeManager.getLanguage());
            return tuple;

        }

    }

    public boolean checkExistence(String id) {

        if (symbolTable.getTupleWithAmbit(id, ambitControler.getCurrentAmbit()) != null) {
            return true;
        } else {
            return false;
        }

    }

    public boolean checkExistence(String id, Symbol s) {

        if (symbolTable.getTupleWithAmbit(id, ambitControler.getCurrentAmbit()) != null) {
            OutputErrors.alreadyDeclared(mainFrame.getOutputPannel(), id, s);
            return true;
        } else {

            return false;
        }

    }

    public TypeManager getTypeManager() {
        return typeManager;
    }

    public ArrayList<Tuple> insertParameters() {
        ArrayList<Tuple> parameters = new ArrayList<>();
        for (int i = 0; i < parameterControl.getIds().size(); i++) {
            Tuple tuple = new Tuple(parameterControl.getIds().get(i), parameterControl.getTypes().get(i), null, null, parameterControl.getSymbols().get(i), ambitControler.getCurrentAmbit());
            tuple.setStackInfo(1);
            stack.addToCurrentStack(tuple);
            parameters.add(tuple);
            symbolTable.insertTuple(tuple);
        }
        parameterControl.removeParameters();
        return parameters;
    }

    public void callNumericError(String id, Type type, Symbol symbol) {
        OutputErrors.typeNotNumeric(mainFrame.getOutputPannel(), id, typeManager.getOutputType(type.getNumber()), symbol);
    }

    public Type operateType(Integer type1, Integer type2, Symbol symbol) {
        Type type = typeManager.operateTypes(type1, type2);
        if (type == null) {
            //Type Error
            OutputErrors.typeOpError(mainFrame.getOutputPannel(), typeManager.getOutputType(type1), typeManager.getOutputType(type2), symbol);
        }
        return type;

    }

    public Type operateBoolType(Integer type1, Integer type2, Symbol symbol) {
        Type type = typeManager.operateBoolTypes(type1, type2);
        if (type == null) {
            //Type Error
            OutputErrors.typeOpBoolError(mainFrame.getOutputPannel(), typeManager.getOutputType(type1), typeManager.getOutputType(type2), symbol);
        }
        return type;
    }

    public boolean assigValue(String id, Object value, Symbol symbol) {
        Type idType = symbolTable.getTypeWithAmbit(id, ambitControler.getCurrentAmbit(), mainFrame, symbol);

        if (idType != null) {
            if (value != null) {
                SynthesizedOpAsst soa = (SynthesizedOpAsst) value;
                
                if (soa.getType().getNumber() == idType.getNumber() || soa.getType().isFather(idType)) {

                    return true;
                }
                OutputErrors.typeOpError(mainFrame.getOutputPannel(),TypeManager.getOutputTypeStatic(soa.getType().getNumber()), TypeManager.getOutputTypeStatic(idType.getNumber()), symbol);
            }
            
        }
        
        return false;
    }
    

    

    public boolean verifyType(Integer type1, Integer type2, Symbol symbol, String id) {
        if (type1.equals(type2) || typeManager.getType(type2).isFather(typeManager.getType(type1))) {
            return true;
        }
        OutputErrors.typeOpError(mainFrame.getOutputPannel(), typeManager.getOutputType(type1), typeManager.getOutputType(type2), id, symbol);
        return false;
    }

    public void insertPreTuple(String name, Integer typeNumber, Object value, Integer dimension, Symbol symbol) {
        Tuple newPreTuple;
        if (typeNumber != null) {
            newPreTuple = new Tuple(name, typeManager.getType(typeNumber), value, dimension, symbol, ambitControler.getCurrentAmbit());
        } else {
            newPreTuple = new Tuple(name, null, value, dimension, symbol, ambitControler.getCurrentAmbit());
        }

        preTupleSymbols.add(newPreTuple);
    }

    public void include(String language, String functions, Symbol s, TranslateControlerTAC tac) {
        if (language.equals("JAVA")) {
            if (functions == null || functions.equals("*")) {
                includedTuples.addAll(symbolTable.getLanguageFunctions(language));
            } else {
                ArrayList<Tuple> tu = symbolTable.getLanguageFunctions(language, functions);
                if (tu.isEmpty()) {
                    OutputErrors.wrongImport(mainFrame.getOutputPannel(), functions, language, s);
                }
                includedTuples.addAll(tu);
            }

        } else if (language.equals("VB")) {
            if (functions == null || functions.equals("*")) {
                includedTuples.addAll(symbolTable.getLanguageFunctions(language));
            } else {
                ArrayList<Tuple> tu = symbolTable.getLanguageFunctions(language, functions);
                if (tu.isEmpty()) {
                    OutputErrors.wrongImport(mainFrame.getOutputPannel(), functions, language, s);
                }
                includedTuples.addAll(tu);
            }
        } else if (language.equals("PY")) {

            if (functions == null || functions.equals("*")) {
                includedTuples.addAll(symbolTable.getLanguageFunctions(language));
            } else {
                ArrayList<Tuple> tu = symbolTable.getLanguageFunctions(language, functions);
                if (tu.isEmpty()) {
                    OutputErrors.wrongImport(mainFrame.getOutputPannel(), functions, language, s);
                }
                includedTuples.addAll(tu);
            }
        } else {
            tac.addLibrary(language + "." + functions);
        }

    }

    public void insertReturnValue(Object value, Symbol s, TranslateControlerTAC tac) {
        Integer posicion = stack.getIdPosition(Stack.RETURN);
        if (posicion == null) {
            Tuple tuple = new Tuple(Stack.RETURN, null, null, 0, s, ambitControler.getCurrentAmbit());
            tuple.setStackInfo(1);
            stack.addToCurrentStack(tuple);
            posicion = stack.getIdPosition(Stack.RETURN);
        }
        String temp = tac.getTempGenerator().generateIntegerTemp();
        SynthesizedOpAsst soa = (SynthesizedOpAsst) value;
        tac.addTempQuadToCurrent(new Quadruple(Operation.PLUS, Stack.P, posicion, temp));
        tac.addTempQuadToCurrent(new Quadruple(Operation.EQUAL, soa.getQuadruple().getResult(), null, Stack.getOutputStack(temp)));
        tac.addTempQuadToCurrent(new Quadruple(Operation.GO_TO, null, null, tac.getReturnLabel()));
        tac.acceptAllIdQuas();

    }

    public boolean verifyFuctionForReturn(Symbol s) {
        ///Verijy Tu
        if (ambitControler.getCurrentAmbit().isFunction() || ambitControler.isSonOfAFuncion()) {
            return true;
        } else {
            sma.error=true;
            OutputErrors.notFunctionForReturn(mainFrame.getOutputPannel(), s);
            return false;
        }

    }

    public void include(String string, Symbol s, TranslateControlerTAC tac) {
        if (string.contains("JAVA") || string.contains("PY") || string.contains("VB")) {
            string = string.substring(1, string.length() - 1);
            string = string.replace('.', '_');
            String[] functions = string.split("_");
            if (functions.length == 1) {
                include(functions[0], null, s, tac);
            } else if (functions.length == 2) {
                include(functions[0], functions[1], s, tac);
            }
        } else {
            tac.addLibrary(string);
        }

    }

    public SynthesizedOpAsst verifyFunction(String id, ArrayList<SynthesizedOpAsst> parameters, Symbol s, TranslateControlerTAC tac) {

        if (parameters == null) {
            parameters = new ArrayList<>();
        }
        if (id.contains(".")) {
            id = id.replace('.', '_');
            String[] ids = id.split("_");
            Tuple function = null;
            if (!ids[0].equals("JAVA") && !ids[0].equals("PY") && !ids[0].equals("VB")) {
                return verifyHeapFunction(ids[0], ids[1], parameters, s, tac);
            }
            for (int i = 0; i < includedTuples.size(); i++) {
                if (includedTuples.get(i).getName().contains(ids[1]) && parameters.size() == includedTuples.get(i).getParameters().size()
                        && includedTuples.get(i).getLanguage().equals(ids[0])) {
                    boolean mismosTipos = true;
                    for (int j = 0; j < parameters.size(); j++) {
                        if (includedTuples.get(i).getParameters().get(j).getType().getNumber() != TypeManager.VAR_TYPE) {
                            if (!(parameters.get(j).getType().getNumber() == includedTuples.get(i).getParameters().get(j).getType().getNumber()) && !parameters.get(j).getType().isFather(includedTuples.get(i).getParameters().get(j).getType())) {
                                mismosTipos = false;
                            }
                        }

                    }
                    if (mismosTipos) {
                        function = includedTuples.get(i);
                    }

                }

            }
            int fatherStack = 0;
            if (function != null) {
                fatherStack = stack.getCurrentP();
                Quadruple quad = new Quadruple(Operation.PLUS, Stack.P, stack.getCurrentId(), Stack.P);
                String name = stack.getCurrentId();
                stack.changeCurrent(function.getName());
                ArrayList<String> strings = new ArrayList<>();
                for (int i = 0; i < parameters.size(); i++) {
                    String temp1 = tac.getTempGenerator().generateTemp();
                    tac.addTempQuadToCurrent(new Quadruple(Operation.PLUS, Stack.P, name, temp1));

                    String temp = tac.getTempGenerator().generateTemp();
                    Integer param = stack.getIdPosition(function.getParameters().get(i).getName());
                    tac.addTempQuadToCurrent(new Quadruple(Operation.PLUS, temp1, param, temp));

                    strings.add(temp);

                }
                for (int i = 0; i < strings.size(); i++) {
                    tac.getTempGenerator().addIntegerTemp(strings.get(i));
                    tac.addTempQuadToCurrent(new Quadruple(Operation.EQUAL, (String) parameters.get(i).getQuadruple().getResult(), null, Stack.getOutputStack(strings.get(i))));
                }
                tac.addTempQuadToCurrent(quad);

                String call = function.getName();

                tac.addTempQuadToCurrent(new Quadruple(Operation.TEMP, null, null, call));

                Integer ret = stack.getIdPosition(Stack.RETURN);
                if (ret != null) {
                    String temp = tac.getTempGenerator().generateIntegerTemp();
                    tac.addTempQuadToCurrent(new Quadruple(Operation.PLUS, Stack.P, ret, temp));
                    String temp2 = tac.getTempGenerator().generateIntegerTemp();
                    Quadruple quad2 = new Quadruple(Operation.EQUAL, Stack.getOutputStack(temp), null,temp2 );
                    tac.addTempQuadToCurrent(quad2);
                    tac.acceptAllIdQuas();
                    stack.changeCurrent(fatherStack);
                    tac.addTempQuadToCurrent(new Quadruple(Operation.MINUS, Stack.P, stack.getCurrentId(), Stack.P));
                    tac.acceptAllIdQuas();
                    return new SynthesizedOpAsst(quad2, function.getType());
                }
                stack.changeCurrent(fatherStack);
                tac.addTempQuadToCurrent(new Quadruple(Operation.MINUS, Stack.P, stack.getCurrentId(), Stack.P));

                tac.acceptAllIdQuas();
                return null;
            } else {
                OutputErrors.functionNotFound(mainFrame.getOutputPannel(), id, s);
            }

        } else {
            Tuple function = null;

            for (int i = 0; i < includedTuples.size(); i++) {

                if (includedTuples.get(i).getName().contains(id) && parameters.size() == includedTuples.get(i).getParameters().size()) {
                    function = includedTuples.get(i);
                }
            }
            if (function != null) {
                String call = "call " + function.getName() + "," + parameters.size();
                Quadruple quad = new Quadruple(Operation.EQUAL, call, null, tac.getTempGenerator().generateTemp());
                tac.addTempQuadToCurrent(quad);
                return new SynthesizedOpAsst(quad, function.getType());
            } else {
                OutputErrors.functionNotFound(mainFrame.getOutputPannel(), id, s);
            }
        }
        return null;
    }

    public SynthesizedOpAsst verifyJavaFunction(String id, ArrayList<SynthesizedOpAsst> parameters, Symbol s, TranslateControlerTAC tac) {
        String objectType = stack.getCurrentClassName();

        if (objectType.contains("JAVA")) {
            objectType = objectType.replace("JAVA_", "");
            if (objectType != null) {
                if (parameters == null) {
                    parameters = new ArrayList<>();
                }
                String name = "JAVA_" + objectType + "_" + id;
                Tuple function = null;
                ArrayList<Tuple> tuples = symbolTable.getSymbols();
                for (int i = 0; i < tuples.size(); i++) {
                    if (tuples.get(i).getName().contains(name) && parameters.size() == tuples.get(i).getParameters().size()) {
                        boolean mismosTipos = true;
                        for (int j = 0; j < parameters.size(); j++) {
                            if (!(parameters.get(j).getType().getNumber() == tuples.get(i).getParameters().get(j).getType().getNumber()) && !parameters.get(j).getType().isFather(tuples.get(i).getParameters().get(j).getType())) {
                                mismosTipos = false;
                            }
                        }
                        if (mismosTipos) {
                            function = tuples.get(i);
                            break;
                        }
                    }

                }
                int fatherStack = 0;
                if (function != null) {
                    fatherStack = stack.getCurrentP();

                    Quadruple quad = new Quadruple(Operation.PLUS, Stack.P, stack.getCurrentId(), Stack.P);
                    String temp0 = tac.getTempGenerator().generateIntegerTemp();
                    Quadruple quad0 = new Quadruple(Operation.PLUS, Stack.P, "this", temp0);
                    String temp01 = tac.getTempGenerator().generateTemp();
                    Quadruple quad01 = new Quadruple(Operation.EQUAL, Stack.getOutputStack(temp0), null, temp01);
                    String temp02 = tac.getTempGenerator().generateTemp();
                    Quadruple quad02 = new Quadruple(Operation.PLUS, Stack.P, stack.getCurrentId(), temp02);
                    String fun = stack.getCurrentId();
                    stack.changeCurrent(function.getName());

                    ArrayList<String> strings = new ArrayList<>();
                    for (int i = 0; i < parameters.size(); i++) {
                        String temp1 = tac.getTempGenerator().generateTemp();

                        tac.addTempQuadToCurrent(new Quadruple(Operation.PLUS, Stack.P, fun, temp1));
                        String temp = tac.getTempGenerator().generateTemp();

                        Integer param = stack.getIdPosition(function.getParameters().get(i).getName());
                        tac.addTempQuadToCurrent(new Quadruple(Operation.PLUS, temp1, param, temp));
                        strings.add(temp);
                    }
                    for (int i = 0; i < strings.size(); i++) {
                        tac.getTempGenerator().addIntegerTemp(strings.get(i));
                        tac.addTempQuadToCurrent(new Quadruple(Operation.EQUAL, (String) parameters.get(i).getQuadruple().getResult(), null, Stack.getOutputStack(strings.get(i))));
                    }
                    tac.addTempQuadToCurrent(quad0);
                    tac.addTempQuadToCurrent(quad01);
                    tac.addTempQuadToCurrent(quad02);
                    String temp03 = tac.getTempGenerator().generateIntegerTemp();
                    tac.addTempQuadToCurrent(new Quadruple(Operation.PLUS, temp02, stack.getIdPosition("this"), temp03));
                    tac.addTempQuadToCurrent(new Quadruple(Operation.EQUAL, temp01, null, Stack.getOutputStack(temp03)));

                    tac.addTempQuadToCurrent(quad);

                    tac.addTempQuadToCurrent(new Quadruple(Operation.TEMP, null, null, function.getName()));
                    stack.changeCurrent(fatherStack);

                    Integer ret = stack.getIdFromFunction(function.getName(), Stack.RETURN);
                    if (ret != null) {
                        String temp = tac.getTempGenerator().generateIntegerTemp();
                        tac.addTempQuadToCurrent(new Quadruple(Operation.PLUS, Stack.P, ret, temp));
                        Quadruple quad2 = new Quadruple(Operation.EQUAL, null, null, Stack.getOutputStack(temp));
                        tac.addTempQuadToCurrent(new Quadruple(Operation.MINUS, Stack.P, stack.getCurrentId(), Stack.P));

                        tac.acceptAllIdQuas();
                        return new SynthesizedOpAsst(quad2, function.getType());
                    }
                    tac.addTempQuadToCurrent(new Quadruple(Operation.MINUS, Stack.P, stack.getCurrentId(), Stack.P));
                    tac.acceptAllIdQuas();

                } else {
                    OutputErrors.functionNotFound(mainFrame.getOutputPannel(), id, s);
                }
            } else {
                //OBJECT DOES NOT EXISTE
            }
        } else {
            //
        }

        return null;
    }

    public SynthesizedOpAsst verifyClass(Object id1, Object id2, Object obId, ArrayList<SynthesizedOpAsst> parameters, Symbol s, TranslateControlerTAC tac) {
        String stringid1 = (String) id1;
        String stringid2 = (String) id2;
        if (stringid1.equals(stringid2) && insertObject((String) obId, stringid2, s) != null) {
            if (parameters == null) {
                parameters = new ArrayList<>();
            }
            Tuple function = null;
            String constructorName = "JAVA_" + id1 + "_" + id1;
            for (int i = 0; i < includedTuples.size(); i++) {
                if (includedTuples.get(i).getName().contains(constructorName) && parameters.size() == includedTuples.get(i).getParameters().size()) {
                    boolean mismosTipos = true;
                    for (int j = 0; j < parameters.size(); j++) {
                        if (!(parameters.get(j).getType().getNumber() == includedTuples.get(i).getParameters().get(j).getType().getNumber()) && !parameters.get(j).getType().isFather(includedTuples.get(i).getParameters().get(j).getType())) {
                            mismosTipos = false;
                        }
                    }
                    if (mismosTipos) {
                        function = includedTuples.get(i);
                        break;
                    }
                }

            }
            int fatherStack = 0;
            if (function != null) {
                fatherStack = stack.getCurrentP();
                Quadruple quad = new Quadruple(Operation.PLUS, Stack.P, stack.getCurrentId(), Stack.P);
                String curent = stack.getCurrentId();
                stack.changeCurrent(function.getName());
                ArrayList<String> strings = new ArrayList<>();
                for (int i = 0; i < parameters.size(); i++) {
                    String temp1 = tac.getTempGenerator().generateTemp();
                    tac.addTempQuadToCurrent(new Quadruple(Operation.PLUS, Stack.P, curent, temp1));
                    String temp = tac.getTempGenerator().generateTemp();
                    Integer param = stack.getIdPosition(function.getParameters().get(i).getName());
                    tac.addTempQuadToCurrent(new Quadruple(Operation.PLUS, temp1, param, temp));
                    strings.add(temp);
                }
                for (int i = 0; i < strings.size(); i++) {
                    tac.getTempGenerator().addIntegerTemp(strings.get(i));
                    tac.addTempQuadToCurrent(new Quadruple(Operation.EQUAL, (String) parameters.get(i).getQuadruple().getResult(), null, Stack.getOutputStack(strings.get(i))));
                }
                tac.addTempQuadToCurrent(quad);
                String call = function.getName();
                tac.addTempQuadToCurrent(new Quadruple(Operation.TEMP, null, null, call));
                stack.changeCurrent(fatherStack);
                tac.addTempQuadToCurrent(new Quadruple(Operation.MINUS, Stack.P, stack.getCurrentId(), Stack.P));

                String temp = tac.getTempGenerator().generateIntegerTemp();
                tac.addTempQuadToCurrent(new Quadruple(Operation.PLUS, Stack.P, stack.getCurrentId(), temp));
                String temp2 = tac.getTempGenerator().generateTemp();
                tac.addTempQuadToCurrent(new Quadruple(Operation.PLUS, temp, stack.getIdFromFunction(function.getName(), "this"), temp2));

                Quadruple quad2 = new Quadruple(Operation.EQUAL, null, null, Stack.getOutputStack(temp));

                tac.acceptAllIdQuas();
                return new SynthesizedOpAsst(quad2, function.getType());

            } else {
                OutputErrors.functionNotFound(mainFrame.getOutputPannel(), stringid1, s);
            }
        } else {
            //Not the same object 
        }

        return null;

    }

    public SynthesizedOpAsst verifyHeapFunction(String id, String functionName, ArrayList<SynthesizedOpAsst> parameters, Symbol s, TranslateControlerTAC tac) {
        System.out.println("HEAP FUN "+id);
        System.out.println(functionName);
        
        String objectType = symbolTable.getObjectClass(id, ambitControler.getCurrentAmbit());
        if (objectType != null) {
            if (parameters == null) {
                parameters = new ArrayList<>();
            }
            String name = "JAVA_" + objectType + "_" + functionName;
            Tuple function = null;
            for (int i = 0; i < includedTuples.size(); i++) {
                if (includedTuples.get(i).getName().contains(name) && parameters.size() == includedTuples.get(i).getParameters().size()) {
                    boolean mismosTipos = true;
                    for (int j = 0; j < parameters.size(); j++) {
                        if (!(parameters.get(j).getType().getNumber() == includedTuples.get(i).getParameters().get(j).getType().getNumber()) && !parameters.get(j).getType().isFather(includedTuples.get(i).getParameters().get(j).getType())) {
                            mismosTipos = false;
                        }
                    }
                    if (mismosTipos) {
                        function = includedTuples.get(i);
                        break;
                    }
                }

            }
            int fatherStack = 0;
            if (function != null) {
                fatherStack = stack.getCurrentP();
                Quadruple quad = new Quadruple(Operation.PLUS, Stack.P, stack.getCurrentId(), Stack.P);
                String temp0 = tac.getTempGenerator().generateIntegerTemp();
                Quadruple quad0 = new Quadruple(Operation.PLUS, Stack.P, stack.getIdPosition(id), temp0);
                String temp01 = tac.getTempGenerator().generateTemp();
                Quadruple quad01 = new Quadruple(Operation.EQUAL, Stack.getOutputStack(temp0), null, temp01);
                String temp02 = tac.getTempGenerator().generateTemp();
                Quadruple quad02 = new Quadruple(Operation.PLUS, Stack.P, stack.getCurrentId(), temp02);
                String fun = stack.getCurrentId();
                stack.changeCurrent(function.getName());

                ArrayList<String> strings = new ArrayList<>();
                for (int i = 0; i < parameters.size(); i++) {
                    String temp1 = tac.getTempGenerator().generateTemp();
                    tac.addTempQuadToCurrent(new Quadruple(Operation.PLUS, Stack.P, fun, temp1));
                    String temp = tac.getTempGenerator().generateTemp();
                    Integer param = stack.getIdPosition(function.getParameters().get(i).getName());
                    tac.addTempQuadToCurrent(new Quadruple(Operation.PLUS, temp1, param, temp));
                    strings.add(temp);
                }
                for (int i = 0; i < strings.size(); i++) {
                    tac.getTempGenerator().addIntegerTemp(strings.get(i));
                    tac.addTempQuadToCurrent(new Quadruple(Operation.EQUAL, (String) parameters.get(i).getQuadruple().getResult(), null, Stack.getOutputStack(strings.get(i))));
                }
                tac.addTempQuadToCurrent(quad0);
                tac.addTempQuadToCurrent(quad01);
                tac.addTempQuadToCurrent(quad02);
                String temp03 = tac.getTempGenerator().generateIntegerTemp();
                tac.addTempQuadToCurrent(new Quadruple(Operation.PLUS, temp02, stack.getIdPosition("this"), temp03));
                tac.addTempQuadToCurrent(new Quadruple(Operation.EQUAL, temp01, null, Stack.getOutputStack(temp03)));

                tac.addTempQuadToCurrent(quad);

                tac.addTempQuadToCurrent(new Quadruple(Operation.TEMP, null, null, function.getName()));
                stack.changeCurrent(fatherStack);

                Integer ret = stack.getIdFromFunction(function.getName(), Stack.RETURN);
                if (ret != null) {
                    String temp = tac.getTempGenerator().generateIntegerTemp();
                    tac.addTempQuadToCurrent(new Quadruple(Operation.PLUS, Stack.P, ret, temp));
                    Quadruple quad2 = new Quadruple(Operation.EQUAL, null, null, Stack.getOutputStack(temp));
                    tac.addTempQuadToCurrent(new Quadruple(Operation.MINUS, Stack.P, stack.getCurrentId(), Stack.P));

                    tac.acceptAllIdQuas();
                    return new SynthesizedOpAsst(quad2, function.getType());
                }
                tac.addTempQuadToCurrent(new Quadruple(Operation.MINUS, Stack.P, stack.getCurrentId(), Stack.P));
                tac.acceptAllIdQuas();

            } else {
                sma.error=true;
                OutputErrors.functionNotFound(mainFrame.getOutputPannel(), id, s);
            }
        } else {
            sma.error=true;
           OutputErrors.functionNotFound2(mainFrame.getOutputPannel(), id, s);
        }

        return null;

    }

    public Tuple insertObject(String id, String name, Symbol symbol) {

        Tuple tuple = symbolTable.insertObject(id, name, symbol, ambitControler.getCurrentAmbit(), mainFrame.getOutputPannel());
        if (tuple != null) {
            stack.addToCurrentStack(tuple);
        }
        return tuple;
    }

}
