/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tac_assembly_generator.languages.semantic;

import java_cup.runtime.Symbol;
import tac_assembly_generator.languages.semantic.type.Type;

/**
 *
 * @author sergio
 */
public class Tuple {
    private String name;
    private Type type;
    private Object value;
    private Symbol symbol;
    private Ambit ambit;
    //0 normal type
    //1 one dimension array ...
    private Integer dimension;

    public Tuple(String name, Type type, Object value,Integer dimension, Symbol symbol,Ambit ambit) {
        this.name = name;
        this.type = type;
        this.value = value;
        this.symbol=symbol;
        this.dimension=dimension;
        this.ambit=ambit;
        
    }

    public void setDimension(Integer dimension) {
        this.dimension = dimension;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public Object getValue() {
        return value;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public int getDimensions() {
        return dimension;
    }

    public void setType(Type type) {
        this.type = type;
    }
    
    
    
}
