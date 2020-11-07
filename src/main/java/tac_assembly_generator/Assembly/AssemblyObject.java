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
public class AssemblyObject {
    private DataSection dataSection;
    private TextSection textSection;
    private BSS bss;

    public AssemblyObject() {
        dataSection= new DataSection();
        textSection= new TextSection();
        bss= new BSS();
    }
    
    
    
}
