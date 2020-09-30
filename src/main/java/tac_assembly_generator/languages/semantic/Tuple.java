/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tac_assembly_generator.languages.semantic;

import tac_assembly_generator.languages.semantic.type.Type;

/**
 *
 * @author sergio
 */
public class Tuple {
    private String name;
    private Type type;
    private Object value;

    public Tuple(String name, Type type, Object value) {
        this.name = name;
        this.type = type;
        this.value = value;
    }
    
    
    
}
