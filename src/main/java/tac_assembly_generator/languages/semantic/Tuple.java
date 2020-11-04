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
    private String functionName;
    private Type type;
    private String objectType;
    private Object value;
    private Symbol symbol;
    private Ambit ambit;
    private ArrayList<Tuple> parameters;
    private ArrayList<Object> dimensions;
    private Tuple classFather;
    private String language;
    private boolean constante;
    //0 normal type
    //1 one dimension array ...
    private Integer dimension;
    //Stack Info
    private int size;

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }
    

    public Tuple(String name, Type type, Object value,Integer dimension, Symbol symbol,Ambit ambit) {
        
        this.name = name;
        this.type = type;
        this.value = value;
        this.symbol=symbol;
        this.dimension=dimension;
        this.ambit=ambit;
        constante=false;
        
    }
    
    public void setStackInfo(int size){
        this.size=size;
    }

    public void setConstante(boolean constante) {
        this.constante = constante;
    }

    public boolean isConstante() {
        return constante;
    }
    

    public int getSize() {
        return size;
    }
    

    public String getLanguage() {
        return language;
    }

    public void setClassFather(Tuple classFather) {
        this.classFather = classFather;
    }


    public void setDimensions(ArrayList<Object> dimensions) {
        this.dimensions = dimensions;
    }

    public String getFunctionName() {
        return functionName;
    }
    
    public ArrayList<Object> getDimension(){
        return dimensions;
    }
    
    
    public String generateFunctionName(String language){
        this.language=language;
        if (classFather!=null) {
             name=language+"_"+classFather.getName()+"_"+this.name;
        }else{
            name=language+"_"+this.name; 
        }

        if (parameters!=null) {
            for (int i = 0; i < parameters.size(); i++) {
            name+="_"+TypeManager.getOutputTypeStatic(parameters.get(i).type.getNumber());
        }
        
        }
        String output="";
        output+="void ";
        
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

    public String generateClassName(String language) {
         String name=language+"_"+this.name;
        String output="";
            output+="class ";
        output+=name;
        return output;
    }
    
    
    
}
