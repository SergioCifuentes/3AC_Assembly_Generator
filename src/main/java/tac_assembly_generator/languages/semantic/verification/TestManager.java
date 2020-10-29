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

    public void setCurrentClass(Tuple currentClass) {
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

    public void setAmbitFuction(){
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
        for (int i = 0; i < preTupleSymbols.size(); i++) {
            System.out.println(preTupleSymbols.get(i).getName());
            System.out.println(preTupleSymbols.get(i).getValue());
            
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
                    //tAC.acceptIdQuad(i);
                }

            }
        }
        tAC.removeIdQuads();
        preTupleSymbols.removeAll(preTupleSymbols);
    }

    public Type getTypeFromST(String id, Symbol symbol) {
        return symbolTable.getTypeWithAmbit(id, ambitControler.getCurrentAmbit(), mainFrame, symbol);
    }

    public void insertTuple(String id, Integer type, Symbol symbol) {
        Tuple tuple = new Tuple(id, typeManager.getType(type), null, 0, symbol, ambitControler.getCurrentAmbit());
        tuple.setStackInfo(1);
        if (stack.getIdPosition(id)==null) {
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
                    for (int i = 0; i < dimension.size(); i++) {
                        String result = null;
                        if (i == dimension.size() - 1) {
                            results.add(tac.getTempGenerator().generateTemp());
                            quads.add(new Quadruple(Operation.PLUS, results.get(results.size() - 2), dimension.get(dimension.size()-1-i), results.get(results.size() - 1)));
                            break;
                        }
                        if (i < dimension.size() - 1) {
                                result = String.valueOf( sizes.get(sizes.size() - 2 - i));
                        }
                        String temp = null;
                        if (i == dimension.size() - 2) {
                            temp = tac.getTempGenerator().generateTemp();
                            quads.add(new Quadruple(Operation.EQUAL, result, null, temp));
                        }

                        for (int j = sizes.size() - 3 - i; j >= 0; j--) {
                            temp = tac.getTempGenerator().generateTemp();
                            quads.add(new Quadruple(Operation.MULTIPLICATION, result, sizes.get(j), temp));
                            result = temp;
                        }
                        String result2 = tac.getTempGenerator().generateTemp();
                        quads.add(new Quadruple(Operation.MULTIPLICATION, dimension.get(dimension.size()-1-i), result, result2));
                        results.add(result2);
                        if (results.size() > 1) {
                            String result3 = tac.getTempGenerator().generateTemp();
                            quads.add(new Quadruple(Operation.PLUS, results.get(results.size() - 2), results.get(results.size() - 1), result3));
                            results.add(result3);
                        }

                    }
                    String temp=tac.getTempGenerator().generateTemp();
                    quads.add(new Quadruple(Operation.PLUS, Stack.P, stack.getIdPosition(id), temp));
                    
                    String temp2=tac.getTempGenerator().generateTemp();
                    
                    quads.add(new Quadruple(Operation.PLUS, temp, results.get(results.size() - 1) ,temp2 ));
                    tac.addTempQuads(quads);
                    
                    return new SynthesizedOpAsst(new Quadruple(Operation.EQUAL,null, null, Stack.getOutputStack(results.get(results.size() - 1))), tuple.getType());
                }
            }
        }
        return null;

    }

    public void insertArray(String id, Integer type, ArrayList<Object> objects, Symbol symbol) {
        System.out.println("DIM of"+id);
        for (int i = 0; i < objects.size(); i++) {
            System.out.println(objects.get(i));
        }
        Tuple tuple = new Tuple(id, typeManager.getType(type), null, objects.size(), symbol, ambitControler.getCurrentAmbit());
        
        tuple.setDimensions(objects);
        int size=(Integer)objects.get(0);
        for (int i = 1; i < objects.size(); i++) {
            size*=(Integer)objects.get(i);
        }
        System.out.println("SIZE "+size);
        tuple.setStackInfo(size);
        stack.addToCurrentStack(tuple);
        symbolTable.insertTuple(tuple);
    }

    public Integer getConstValue(String id, Symbol s) {
        Type type = getTypeFromST(id, s);

        if (type != null) {
            Tuple tuple = symbolTable.getTupleWithAmbit(id, ambitControler.getCurrentAmbit());

            if (tuple != null&&tuple.isConstante()) {
                if (type.equals(typeManager.getType(TypeManager.INTEGER_TYPE))) {
                       return (Integer)tuple.getValue();
                } else if (type.equals(typeManager.getType(TypeManager.CHAR_TYPE))) {
                        char charValue=(char)tuple.getValue();
                        return (int)charValue;
                } else {
                     //Not integer value error
                }
            }else{
                //Not const error
            }

        }
        return null;
    }

    
    public SyntaxConstAsst operateConstAsst(Object const1,Object const2,Integer op, Symbol s){
        SyntaxConstAsst sc1= (SyntaxConstAsst)const1;
        SyntaxConstAsst sc2= (SyntaxConstAsst)const2;
        Type type = operateType(sc1.getType(), sc2.getType(), s);
        if (type!=null) {
            switch (type.getNumber()) {
                case TypeManager.INTEGER_TYPE:
                    Object val=Operation.operateInteger(sc1.getValue(),sc2.getValue(),op);
                    return new SyntaxConstAsst(val, type.getNumber());
                case TypeManager.FLOAT_TYPE:
                    Object val2=Operation.operateFloat(sc1.getValue(),sc2.getValue(),op);
                    return new SyntaxConstAsst(val2, type.getNumber());
                case TypeManager.CHAR_TYPE:
                    Object val3=Operation.operateInteger(sc1.getValue(),sc2.getValue(),op);
                    return new SyntaxConstAsst(val3, type.getNumber());
                default:
                    throw new AssertionError();
            }
        }
        return null;
    }
    
    public boolean insertConst(String id, Integer type, Object val,Symbol s){
        SyntaxConstAsst sc=(SyntaxConstAsst)val; 
        if (typeManager.getType(sc.getType()).isFather(typeManager.getType(type))||typeManager.getType(type).equals(typeManager.getType(sc.getType()))) {
            Tuple tuple = new Tuple(id, typeManager.getType(type), sc.getValue(),0, s, ambitControler.getCurrentAmbit());
            tuple.setConstante(true);
            symbolTable.insertTuple(tuple);
            return true;
                  
        }else{
            
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
            ArrayList<Tuple> paArrayList = insertParameters();
            tuple1.setParameters(paArrayList);
            parameterControl.removeParameters();
            return tuple1.generateFunctionName(typeManager.getLanguage());
        }

    }

    public Tuple insertClass(String id, Symbol s) {

        if (symbolTable.getTupleWithAmbit(id, ambitControler.getCurrentAmbit(), TypeManager.CLASS) != null) {
            return null;
        } else {
            Tuple tuple = new Tuple(id, TypeManager.CLASS, null, 0, s, ambitControler.getCurrentAmbit());
            symbolTable.insertTuple(tuple);
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
                if (soa.getType().getNumber()==idType.getNumber() || soa.getType().isFather(idType)) {
                    System.out.println("TRUE");
                    return true;
                }
            }
        }
        System.out.println("FALSE");
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
        System.out.println("VAL "+value);
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
    
    public void insertReturnValue(Object value,Symbol s,TranslateControlerTAC tac){
        Integer posicion = stack.getIdPosition(Stack.RETURN);
        if (posicion==null) {
            Tuple tuple = new Tuple(Stack.RETURN, null, null, 0, s, ambitControler.getCurrentAmbit());
            tuple.setStackInfo(1);
            stack.addToCurrentStack(tuple);
            posicion = stack.getIdPosition(Stack.RETURN);
        }
        String temp = tac.getTempGenerator().generateTemp();
        SynthesizedOpAsst soa=(SynthesizedOpAsst)value;
        tac.addTempQuadToCurrent(new Quadruple(Operation.PLUS, Stack.P, posicion, temp));
        System.out.println("QUAD "+soa.getQuadruple().getResult());
        tac.addTempQuadToCurrent(new Quadruple(Operation.EQUAL, soa.getQuadruple().getResult(), null, Stack.getOutputStack(temp)));
        tac.acceptAllIdQuas();
    }
    
    
    public boolean  verifyFuctionForReturn(Symbol s){
        if (ambitControler.getCurrentAmbit().isFunction()) {
            return true;
        }else{
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

    public SynthesizedOpAsst verifyFunction(String id, ArrayList<Object> parameters, Symbol s, TranslateControlerTAC tac) {
        if (parameters == null) {
            parameters = new ArrayList<>();
        }
        if (id.contains(".")) {
            id = id.replace('.', '_');
            String[] ids = id.split("_");
            Tuple function = null;

            for (int i = 0; i < includedTuples.size(); i++) {

                if (includedTuples.get(i).getName().contains(ids[1]) && parameters.size() == includedTuples.get(i).getParameters().size()
                        && includedTuples.get(i).getLanguage().equals(ids[0])) {
                    function = includedTuples.get(i);
                }

            }
            int fatherStack=0;
            if (function != null) {
                fatherStack=stack.getCurrentP();
                tac.addTempQuadToCurrent(new Quadruple(Operation.PLUS, Stack.P, stack.getFunctionIndex(function.getName()), Stack.P));
                
                stack.changeCurrent(function.getName());
                ArrayList<String> strings = new ArrayList<>();
                for (int i = 0; i < parameters.size(); i++) {

                    String temp = tac.getTempGenerator().generateTemp();
                    tac.addTempQuadToCurrent(new Quadruple(Operation.PLUS, Stack.P, i, temp));
                    strings.add(temp);

                }
                for (int i = 0; i < strings.size(); i++) {

                    tac.addTempQuadToCurrent(new Quadruple(Operation.EQUAL, (String) parameters.get(i), null, Stack.getOutputStack(strings.get(i))));
                }
                String call = function.getName();
//                Integer indexReturn =s
//                if () {
//                    
//                }
                //Quadruple quad = new Quadruple(Operation.TEMP, null, null, call);
                tac.addTempQuadToCurrent(new Quadruple(Operation.TEMP, null, null, call));
                String temp = tac.getTempGenerator().generateTemp();
                Integer ret = stack.getIdPosition(Stack.RETURN);
                if (ret != null) {
                    tac.addTempQuadToCurrent(new Quadruple(Operation.PLUS, Stack.P, ret, temp));
                    Quadruple quad2 = new Quadruple(Operation.EQUAL, null, null, Stack.getOutputStack(temp));
                    tac.acceptAllIdQuas();
                    stack.changeCurrent(fatherStack);
                    tac.addTempQuadToCurrent(new Quadruple(Operation.MINUS, Stack.P,stack.getFunctionIndex(function.getName()), Stack.P));
                    
                    tac.acceptAllIdQuas();
                    return new SynthesizedOpAsst(quad2, function.getType());
                }
                stack.changeCurrent(fatherStack);
                tac.addTempQuadToCurrent(new Quadruple(Operation.MINUS, Stack.P, stack.getFunctionIndex(function.getName()), Stack.P));
                
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

}
