/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tac_assembly_generator.languages.semantic;

import java.util.ArrayList;
import java_cup.runtime.Symbol;
import tac_assembly_generator.languages.semantic.type.Type;
import tac_assembly_generator.languages.semantic.type.TypeManager;

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
    private ArrayList<Tuple> parameters;
    private String tacFunctionName;
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
    
    public String generateFunctionName(String language){
        
        String name=language+"_"+this.name;
        for (int i = 0; i < parameters.size(); i++) {
            name+="_"+TypeManager.getOutputTypeStatic(parameters.get(i).type.getNumber());
        }
        tacFunctionName=name;
        String output="";
        if (type==null) {
            output+="void ";
        }else{
            if (type.getNumber()!=TypeManager.VAR_TYPE) {
                    output+=TypeManager.getOutputTypeStatic(type.getNumber())+" ";
            }
            
        }
        output+=name;
        return output;
        
        
    }

    public ArrayList<Tuple> getParameters() {
        return parameters;
    }

    public Ambit getAmbit() {
        return ambit;
    }

    public void setParameters(ArrayList<Tuple> parameters) {
        this.parameters = parameters;
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
