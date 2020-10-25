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
public class SyntaxConstAsst {
    private Object value;
    private Integer type;

    public SyntaxConstAsst(Object value, Integer type) {
        this.value = value;
        this.type = type;
    }

    public Object getValue() {
        return value;
    }

    public Integer getType() {
        return type;
    }
    
    
}
