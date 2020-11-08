/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tac_assembly_generator.Assembly;

/**
 *
 * @author sergio
 */
public class LabelGenerator {
    private int count=0;
    private static final String LABEL_TEXT="labelText";
    private static final String LABEL_SIZE="labelSize";
    
    public LabelGenerator() {
    }
    public String generateLabelText(){
        return LABEL_TEXT+count++;
    }
    public String generateLabelSize(){
        return LABEL_SIZE+count++;
    }
    
    
}
