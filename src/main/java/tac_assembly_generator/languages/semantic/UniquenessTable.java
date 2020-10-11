/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tac_assembly_generator.languages.semantic;

import java.util.ArrayList;
import java_cup.runtime.Symbol;
import javax.swing.JTextPane;
import tac_assembly_generator.ui.backend.OutputErrors;

/**
 *
 * @author sergio
 */
public class UniquenessTable {
    private ArrayList<Object> uniques;
    public JTextPane pane;
    
        public UniquenessTable(JTextPane pane) {
        this.pane=pane;
        uniques= new ArrayList<>();
    }
    
    
    
    public boolean add(Object newObject,Symbol symbol){
        for (int i = 0; i < uniques.size(); i++) {
            String s1= (String)uniques.get(i);
            String s2= (String)newObject;
            if (s1.equals(s2)) {
                OutputErrors.alreadyDeclaredCase(pane, s2, symbol);
               return false; 
            }
        }
        uniques.add(newObject);
        return true;
    }
}
