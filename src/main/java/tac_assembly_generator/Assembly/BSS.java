/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tac_assembly_generator.Assembly;

import java.util.ArrayList;

/**
 *
 * @author sergio
 */
public class BSS {
    private ArrayList<String> lines;

    public BSS() {
        lines=new ArrayList<>();
    }

       @Override
    public String toString() {
        String s="";
        for (int i = 0; i < lines.size(); i++) {
            s+=lines.get(i)+"\n";
        }
        return s;
    
    }
    
    
    
}
