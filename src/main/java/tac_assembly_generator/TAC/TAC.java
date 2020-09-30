/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tac_assembly_generator.TAC;

/**
 *
 * @author sergio
 */
public class TAC {
    private String tac;

    public TAC() {
        tac="";
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
    public void addCode(String tacCode, String block){
        
    
        
    }
    public void addCode(String tacCode){
        
    }

    public String getTac() {
        return tac;
    }
    
}
