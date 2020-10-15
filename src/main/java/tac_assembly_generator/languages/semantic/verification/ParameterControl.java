/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tac_assembly_generator.languages.semantic.verification;

import java.util.ArrayList;
import java_cup.runtime.Symbol;
import tac_assembly_generator.languages.semantic.type.Type;
import tac_assembly_generator.languages.semantic.type.TypeManager;

/**
 *
 * @author sergio
 */
public class ParameterControl {
    private ArrayList<String> ids;
    private ArrayList<Type> types;
    private TypeManager typeManager;
    private ArrayList<Symbol> symbols;

    public ParameterControl(TypeManager typeManager) {
        this.typeManager=typeManager;
        types= new ArrayList<>();
        ids= new ArrayList<>();
        symbols= new ArrayList<>();
    }
    
 
    public void insertParameter(int num,String id,Symbol symbol){
        ids.add(id);
        types.add(typeManager.getType(num));
        symbols.add(symbol);
    }
    
    public void removeParameters(){
        ids.removeAll(ids);
        types.removeAll(types);
        symbols.removeAll(symbols);
    }

    public ArrayList<String> getIds() {
        return ids;
    }

    public ArrayList<Symbol> getSymbols() {
        return symbols;
    }
    
    public ArrayList<Type> getTypes() {
        return types;
    }

    
    
}
