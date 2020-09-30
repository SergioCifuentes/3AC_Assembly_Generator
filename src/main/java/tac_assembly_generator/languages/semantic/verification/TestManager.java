/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tac_assembly_generator.languages.semantic.verification;

import java.util.ArrayList;
import tac_assembly_generator.languages.semantic.SymbolTable;
import tac_assembly_generator.languages.semantic.Tuple;
import tac_assembly_generator.languages.semantic.type.TypeManager;

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
    
    public TestManager() {
        typeManager= new TypeManager();
        typeManager.loadTypes(TypeManager.VB_TYPES);
        preTupleSymbols= new ArrayList<>();
        symbolTable= new SymbolTable();
        semanticError=false;
        semanticRecoverableError=false;
    }
    
    public void switchTypes(){
        
    }
    
    public void insertPreTuple(String name, Integer type, Object Value){
        Tuple newPreTuple= new Tuple(name, , Value);
        
    }
    
}
