/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tac_assembly_generator.languages.semantic;

import java.awt.Color;
import java.util.ArrayList;
import java_cup.runtime.Symbol;
import tac_assembly_generator.languages.semantic.type.Type;
import tac_assembly_generator.ui.MainFrame;
import tac_assembly_generator.ui.backend.OutputErrors;
import tac_assembly_generator.ui.backend.OutputText;

/**
 *
 * @author sergio
 */
public class SymbolTable {
    private ArrayList<Tuple> symbols;

    public SymbolTable() {
        symbols= new ArrayList<>();
    }
    
    public void insertTuple(Tuple tuple){
        symbols.add(tuple);
    }
    
    public Tuple getTupleWithAmbit(String id, Ambit ambit){
        
        ArrayList<Tuple> tuplesWithId=new ArrayList<>();
        for (int i = 0; i < symbols.size(); i++) {
            if (symbols.get(i).getName().equals(id)&&symbols.get(i).getParameters()==null) {
                tuplesWithId.add(symbols.get(i));
            }
        }
        for (int i = 0; i < tuplesWithId.size(); i++) {
            if (tuplesWithId.get(i).getAmbit().isSon(ambit)) {
             return tuplesWithId.get(i);
            }
        }
        return null;
    }
    
    public Type getTypeWithAmbit(String id, Ambit ambit,MainFrame mainFrame,Symbol symbol){
        ArrayList<Tuple> tuplesWithId=new ArrayList<>();
        for (int i = 0; i < symbols.size(); i++) {
            if (symbols.get(i).getName().equals(id)&&symbols.get(i).getParameters()==null) {
                tuplesWithId.add(symbols.get(i));
            }
        }

        for (int i = 0; i < tuplesWithId.size(); i++) {
            if (ambit.isSon(tuplesWithId.get(i).getAmbit())) {
             return tuplesWithId.get(i).getType();
            }
        }
        if (tuplesWithId.isEmpty()) {
            OutputErrors.notDeclared(mainFrame.getOutputPannel(), id, symbol);
        }else{
            OutputErrors.declaredOutOfAmbit(mainFrame.getOutputPannel(), id, symbol);
        }
        return null;
    }
    
    
}
