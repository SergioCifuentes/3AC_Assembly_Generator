/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tac_assembly_generator.languages.semantic.verification;

import java.awt.Color;
import java.util.ArrayList;
import java_cup.runtime.Symbol;
import tac_assembly_generator.languages.semantic.AmbitControler;
import tac_assembly_generator.languages.semantic.SymbolTable;
import tac_assembly_generator.languages.semantic.Tuple;
import tac_assembly_generator.languages.semantic.type.Type;
import tac_assembly_generator.languages.semantic.type.TypeManager;
import tac_assembly_generator.ui.MainFrame;
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
    private  TypeManager typeManager;
    private AmbitControler ambitControler;
    private MainFrame mainFrame;
    
    public TestManager(MainFrame mainFrame) {
        typeManager= new TypeManager();
        typeManager.loadTypes(TypeManager.VB_TYPES);
        preTupleSymbols= new ArrayList<>();
        symbolTable= new SymbolTable();
        semanticError=false;
        semanticRecoverableError=false;
        ambitControler= new AmbitControler();
        this.mainFrame=mainFrame;
        
    }
    
    public void switchNextTypes(){
        typeManager.loadnextType();
    }
    
    public void insertPreTuplesToSymbolTable(Integer typeNumber, Integer dimension){
        for (int i = 0; i < preTupleSymbols.size(); i++) {
            if (preTupleSymbols.get(i).getValue()!=null) {
                //cast test
            }else{
                preTupleSymbols.get(i).setDimension(dimension);
                preTupleSymbols.get(i).setType(typeManager.getType(typeNumber));
                symbolTable.insertTuple(preTupleSymbols.get(i));
            }
        }
    }

    public TypeManager getTypeManager() {
        return typeManager;
    }
    public Type operateType(Integer type1, Integer type2, Symbol symbol){
        Type type = typeManager.operateTypes(type1, type2);
        if (type==null) {
            //Type Error
            OutputText.appendToPane(mainFrame.getOutputPannel(),"SEMANTIC ERROR:\n", Color.red, false);
            OutputText.appendToPane(mainFrame.getOutputPannel(),"\t Tipos "+typeManager.getOutputType(type1)+" & "+ typeManager.getOutputType(type2)+" NO pueden ser operados\n", Color.white, false);
            OutputText.appendToPane(mainFrame.getOutputPannel(),"\t Fila: ", Color.white, false);
            OutputText.appendToPane(mainFrame.getOutputPannel(),symbol.right+"\n", Color.YELLOW, false);
            OutputText.appendToPane(mainFrame.getOutputPannel(),"\t Columna: ", Color.white, false);
            OutputText.appendToPane(mainFrame.getOutputPannel(),symbol.left+"\n", Color.YELLOW, false);
            return null;
            
        }else{
            return type;
        }
        
    }
    
    
    public void insertPreTuple(String name, Integer typeNumber, Object value,Integer dimension,Symbol symbol){
        Tuple newPreTuple;
        System.out.println("Sy "+symbol+ " "+symbol.left+" "+symbol.right+" "+symbol.value);
        if (symbol!=null && symbol.value!=null) {
            System.out.println("inserting new tuple "+symbol.value.toString()+" name "+name);
        }else{
            System.out.println("inserting new tuple "+null+" name "+name);
        }
        
        if (typeNumber!=null) {
            newPreTuple = new Tuple(name, typeManager.getType(typeNumber), value,dimension,symbol,ambitControler.getCurrentAmbit());
        }else{
             newPreTuple= new Tuple(name, null, value, dimension, symbol,ambitControler.getCurrentAmbit());
        }
        
        preTupleSymbols.add(newPreTuple);
    }
    
}
