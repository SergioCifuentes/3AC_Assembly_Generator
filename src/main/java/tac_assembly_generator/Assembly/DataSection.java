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
public class DataSection {
    private static final String NAME="section .data";
    private static final String DB="db";
    private static final String EQU="equ $-";

    private ArrayList<String> lines;
    private LabelGenerator lb;
    
    public DataSection() {
        lines=new ArrayList<>();
        lb= new LabelGenerator();
    }
    @Override
    public String toString() {
        String s = NAME+"\n";
        for (int i = 0; i < lines.size(); i++) {
            s+=lines.get(i)+"\n";
        }
        return s;
    
    }
    
    public String createDB(String text){
        String label=lb.generateLabelText();
        text=text.replace("\"", "\'");
        lines.add("\t"+label +": "+DB+" "+text+",0");
        
        return label;
    }
    
    
    
    
}
