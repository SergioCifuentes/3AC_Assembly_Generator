/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tac_assembly_generator.Assembly;

import tac_assembly_generator.TAC.TempGenerator;

/**
 *
 * @author sergio
 */
public class AssemblyObject {
    private DataSection dataSection;
    private TextSection textSection;
    private BSS bss;

    public AssemblyObject() {
        dataSection= new DataSection();
        textSection= new TextSection();
        bss= new BSS();
    }

    @Override
    public String toString() {
        return dataSection.toString()+bss.toString()+textSection.toString();
    }
    
    public String OutputVal() {
        return dataSection.toString()+textSection.toString();
    }
public void declareTemps(TempGenerator tempGenerator){
   bss.addTemps(tempGenerator.getTemps());
    
}
    public DataSection getDataSection() {
        return dataSection;
    }

    public TextSection getTextSection() {
        return textSection;
    }

    public BSS getBss() {
        return bss;
    }
    
    
    
    
}
