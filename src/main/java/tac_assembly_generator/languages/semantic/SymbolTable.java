/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tac_assembly_generator.languages.semantic;

import java.awt.Color;
import java.util.ArrayList;
import java_cup.runtime.Symbol;
import javax.swing.JTextPane;
import tac_assembly_generator.languages.semantic.type.Type;
import tac_assembly_generator.languages.semantic.type.TypeManager;
import tac_assembly_generator.languages.semantic.verification.ParameterControl;
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
        symbols = new ArrayList<>();
    }

    public void insertTuple(Tuple tuple) {
        symbols.add(tuple);

    }

    public ArrayList<Tuple> getSymbols() {
        return symbols;
    }

    public Tuple getTupleWithAmbit(String id, Ambit ambit) {

        ArrayList<Tuple> tuplesWithId = new ArrayList<>();
        for (int i = 0; i < symbols.size(); i++) {

            if (symbols.get(i).getName().equals(id) && symbols.get(i).getParameters() == null) {
                tuplesWithId.add(symbols.get(i));
            }
        }

        for (int i = 0; i < tuplesWithId.size(); i++) {
            if (ambit.isSon(tuplesWithId.get(i).getAmbit())) {
                return tuplesWithId.get(i);
            }
        }
        return null;
    }

    public Tuple getTupleWithAmbit(String id, Ambit ambit, Type type) {

        ArrayList<Tuple> tuplesWithId = new ArrayList<>();
        for (int i = 0; i < symbols.size(); i++) {
            if (symbols.get(i).getName().equals(id) && symbols.get(i).getParameters() == null) {
                tuplesWithId.add(symbols.get(i));
            }
        }
        for (int i = 0; i < tuplesWithId.size(); i++) {
            if (tuplesWithId.get(i).getAmbit().isSon(ambit) && tuplesWithId.get(i).getType().equals(type)) {
                return tuplesWithId.get(i);
            }
        }
        return null;
    }

    public Type getTypeWithAmbit(String id, Ambit ambit, MainFrame mainFrame, Symbol symbol) {
        ArrayList<Tuple> tuplesWithId = new ArrayList<>();
        for (int i = 0; i < symbols.size(); i++) {
            if (symbols.get(i).getName().equals(id) && symbols.get(i).getParameters() == null) {
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
        } else {
            OutputErrors.declaredOutOfAmbit(mainFrame.getOutputPannel(), id, symbol);
        }
        return null;
    }

    public ArrayList<Tuple> getLanguageFunctions(String language) {
        ArrayList<Tuple> tuplesOfLanguage = new ArrayList<>();
        for (int i = 0; i < symbols.size(); i++) {
            if (symbols.get(i).getLanguage() != null && symbols.get(i).getLanguage().equals(language) && symbols.get(i).getParameters() != null) {
                tuplesOfLanguage.add(symbols.get(i));
            }
        }
        return tuplesOfLanguage;
    }

    public ArrayList<Tuple> getLanguageFunctions(String language, String function) {
        ArrayList<Tuple> tuplesOfLanguage = new ArrayList<>();
        for (int i = 0; i < symbols.size(); i++) {
            if (symbols.get(i).getLanguage() != null && symbols.get(i).getLanguage().equals(language) && symbols.get(i).getParameters() != null && symbols.get(i).getName().contains(function)) {
                tuplesOfLanguage.add(symbols.get(i));
            }
        }
        return tuplesOfLanguage;
    }

    public Tuple insertFunction(String id, Type type, ParameterControl parameterControl, Symbol symbol, Ambit ambit, JTextPane textPane) {

        ArrayList<Tuple> tuplesWithId = new ArrayList<>();

        for (int i = 0; i < symbols.size(); i++) {
            if (symbols.get(i).getName().equals(id) && symbols.get(i).getParameters() != null && symbols.get(i).getType().equals(type)) {
                tuplesWithId.add(symbols.get(i));
            }
        }
        for (int i = 0; i < tuplesWithId.size(); i++) {
            if (compateParameters(tuplesWithId.get(i).getParameters(), parameterControl.getTypes())) {
                OutputErrors.alreadyDeclaredFunctions(textPane, id, symbol);
                return null;
            }
        }
        Tuple functionTuple = new Tuple(id, type, null, 0, symbol, ambit);
        insertTuple(functionTuple);
        return functionTuple;

    }

    public String getObjectClass(String id, Ambit ambit){
        ArrayList<Tuple> tuplesWithId = new ArrayList<>();
        for (int i = 0; i < symbols.size(); i++) {
            
            if (symbols.get(i).getName().equals(id) && symbols.get(i).getParameters() == null) {
                tuplesWithId.add(symbols.get(i));
            }
        }
        
        for (int i = 0; i < tuplesWithId.size(); i++) {
           
            if (tuplesWithId.get(i).getAmbit().equals(ambit)||tuplesWithId.get(i).getAmbit().isSon(ambit) ) {
                return tuplesWithId.get(i).getObjectType();
            }
        }
        return null;
    }
    
    public Tuple insertObject(String id, String type, Symbol symbol, Ambit ambit, JTextPane textPane) {

        ArrayList<Tuple> tuplesWithId = new ArrayList<>();

        for (int i = 0; i < symbols.size(); i++) {
            if (symbols.get(i).getName().equals(id)) {
                tuplesWithId.add(symbols.get(i));
            }
        }
        boolean existe = false;

        for (int i = 0; i < tuplesWithId.size(); i++) {
            if (ambit.isSon(tuplesWithId.get(i).getAmbit())) {
                existe = true;
            }
        }
        if (existe) {
            OutputErrors.alreadyDeclared(textPane, id, symbol);
        } else {
            Tuple tuple = new Tuple(id, null, null, 0, symbol, ambit);
            tuple.setObjectType(type);
            tuple.setStackInfo(1);
            insertTuple(tuple);
            return tuple;
        }

        return null;

    }

    public boolean compateParameters(ArrayList<Tuple> parameters1, ArrayList<Type> parameter2) {
        boolean same = true;
        if (parameters1.size() == parameter2.size()) {
            for (int i = 0; i < parameters1.size(); i++) {
                if (!parameters1.get(i).getType().equals(parameter2.get(i))) {
                    same = false;
                }
            }
            return same;
        }
        return false;

    }

}
