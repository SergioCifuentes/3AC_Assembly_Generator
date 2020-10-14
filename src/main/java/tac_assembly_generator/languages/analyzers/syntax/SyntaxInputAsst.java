/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tac_assembly_generator.languages.analyzers.syntax;

/**
 *
 * @author sergio
 */
public class SyntaxInputAsst {
    private Integer type;
    private String string;

    public SyntaxInputAsst(Integer type, String string) {
        this.type = type;
        this.string = string;
    }

    public Integer getType() {
        return type;
    }

    public String getString() {
        return string;
    }
    
    
}
