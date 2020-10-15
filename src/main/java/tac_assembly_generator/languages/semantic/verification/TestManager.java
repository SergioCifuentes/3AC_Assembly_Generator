/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tac_assembly_generator.languages.semantic.verification;

import java.awt.Color;
import java.util.ArrayList;
import java_cup.runtime.Symbol;
import tac_assembly_generator.TAC.TranslateControlerTAC;
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

    public TestManager(MainFrame mainFrame) {
        typeManager = new TypeManager();
        typeManager.loadTypes(TypeManager.VB_TYPES);
        preTupleSymbols = new ArrayList<>();
        symbolTable = new SymbolTable();
        semanticError = false;
        semanticRecoverableError = false;
        ambitControler = new AmbitControler();
        this.mainFrame = mainFrame;
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

    public void creatFatherAmbit() {
        ambitControler.createFatherAmbit();
    }

    public void creatSonAmbit() {
        ambitControler.createSonAmbit();
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
 
            if (preTupleSymbols.get(i).getValue() != null) {

                SynthesizedOpAsst so = (SynthesizedOpAsst) preTupleSymbols.get(i).getValue();
                if ((so.getType().getNumber() == typeNumber) || so.getType().isFather(typeManager.getType(typeNumber))) {
                    preTupleSymbols.get(i).setDimension(dimension);
                    preTupleSymbols.get(i).setType(typeManager.getType(typeNumber));
                    symbolTable.insertTuple(preTupleSymbols.get(i));
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
                    symbolTable.insertTuple(preTupleSymbols.get(i));
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

    public void insertTuple(String id, Integer type,Symbol symbol){
        Tuple tuple= new Tuple(id,typeManager.getType(type),null, 0, symbol,ambitControler.getCurrentAmbit());
        symbolTable.insertTuple(tuple);
    }
    
    public String  testInput(String id, String output,Symbol symbol){
        Type type = getTypeFromST(id, symbol);
        Integer inputType=null;
        String split=null;
        int types=0;
        if (type!=null) {
            if (output.contains(TypeManager.INT_INPUT)) {
                split=TypeManager.INT_INPUT;
                inputType=TypeManager.INTEGER_TYPE;
                types+=1;
            }else if (output.contains(TypeManager.CHAR_INPUT)) {
                split=TypeManager.CHAR_INPUT;
                inputType=TypeManager.CHAR_TYPE;
                types+=1;
            }else if (output.contains(TypeManager.FLOAT_INPUT)) {
                split=TypeManager.FLOAT_INPUT;
                inputType=TypeManager.FLOAT_TYPE;
                types+=1;
            }
            if (types>1) {
                OutputErrors.multipleMasks(mainFrame.getOutputPannel(), id, symbol);
            }else if (types==0) {
                OutputErrors.noMasks(mainFrame.getOutputPannel(), id, symbol);
            }else{
                if (verifyType(type.getNumber(), inputType, symbol, id)) {
                    return split;
                }
            }
        }
        return null;
    }

    
    public boolean  insertDeclaration(String id, Integer type, Object val, Symbol symbol){
        if (!checkExistence(id, symbol)) {
            if (val!=null) {
                SynthesizedOpAsst si = (SynthesizedOpAsst) val;
                if (verifyType(type, si.getType().getNumber(), symbol, id)) {
                    symbolTable.insertTuple(new Tuple(id, typeManager.getType(type), null, 0, symbol, ambitControler.getCurrentAmbit()));
                    return true;
                }

            }
        }
        return false;

    }

    public MainFrame getMainFrame() {
        return mainFrame;
    }
    
    public void convertPreTuplesToConstante(){
        for (int i = 0; i < preTupleSymbols.size(); i++) {
            preTupleSymbols.get(i).setConstante(true);
        }
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
            ArrayList<Tuple> paArrayList = insertParameters();
            tuple.setParameters(paArrayList);
            parameterControl.removeParameters();
            return tuple.generateFunctionName(typeManager.getLanguage());
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
   
            if (symbolTable.getTupleWithAmbit(id, ambitControler.getCurrentAmbit(), TypeManager.CLASS)!=null) {
                return null;
            }else{
                Tuple tuple = new Tuple(id,TypeManager.CLASS, null, 0, s,ambitControler.getCurrentAmbit()) ;
                symbolTable.insertTuple(tuple);
                return tuple;
                
            }
        

    }
    public boolean checkExistence(String id){
        
        if (symbolTable.getTupleWithAmbit(id, ambitControler.getCurrentAmbit())!=null) {
            return true;
        }else{
            return false;
        }

    }
    
            public boolean checkExistence(String id,Symbol s){
        
        if (symbolTable.getTupleWithAmbit(id, ambitControler.getCurrentAmbit())!=null) {
            OutputErrors.alreadyDeclared(mainFrame.getOutputPannel(), id, s);
            return true;
        }else{
            
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
                if (soa.getType().equals(idType) || soa.getType().isFather(idType)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    public boolean verifyType(Integer type1,Integer type2,Symbol symbol, String id){
        if (type1.equals(type2) || typeManager.getType(type2).isFather(typeManager.getType(type1))) {
                    return true;
                }
        OutputErrors.typeOpError(mainFrame.getOutputPannel(), typeManager.getOutputType(type1), typeManager.getOutputType(type2),id, symbol);
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

}
