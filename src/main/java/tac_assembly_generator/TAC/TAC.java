/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tac_assembly_generator.TAC;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JTextPane;
import javax.swing.text.TextAction;
import tac_assembly_generator.TAC.quadruple.Quadruple;
import tac_assembly_generator.ui.MainFrame;
import tac_assembly_generator.ui.backend.OutputText;

/**
 *
 * @author sergio
 */
public class TAC {
    private String tac;
    private JTextPane jtp;
    public MainFrame mainFrame;
    public TAC(MainFrame mainFrame) {
        tac="";
        this.mainFrame=mainFrame;
    }
    public void addComment(String comment){
        if (!tac.isEmpty()&&!tac.isBlank()&&tac.charAt(tac.length()-1)=='\n') {
            tac+=comment;
        }else{
            if (tac.isEmpty()||tac.isBlank()) {
                tac+=comment;
            }else{
               tac+='\n'+comment;
            }
            
        }
    }
    
    public void translateQuads(ArrayList<Object> quads){
        for (int i = 0; i < quads.size(); i++) {
            if (quads.get(i).getClass().equals(String.class)) {
                if (!((String)quads.get(i)).endsWith("\n")) {
                     OutputText.appendToPane(mainFrame.getTACPannel(), (String)quads.get(i)+"\n", Color.lightGray, false);
                }else{
                     OutputText.appendToPane(mainFrame.getTACPannel(), (String)quads.get(i), Color.lightGray, false);
                }
               
            }else if(quads.get(i).getClass().equals(Quadruple.class)){
                OutputText.appendToPane(mainFrame.getTACPannel(), ((Quadruple)quads.get(i)).toString()+"\n", Color.white, false);
            }else{
            }
        } 
    }
    
    public void addCode(String tacCode, String block){
        
    
        
    }
    public void addCode(String tacCode){
        
    }

    public String getTac() {
        return tac;
    }
    
}
