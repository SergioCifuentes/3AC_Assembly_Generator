/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tac_assembly_generator.languages.semantic.type;

/**
 *
 * @author sergio
 */
public class Type {
    private String name;
    private int number;
    private Type father;

    public Type(String name, int number, Type father) {
        this.name = name;
        this.number = number;
        this.father = father;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Type{" + "name=" + name + ", number=" + number + '}';
    }

    public int getNumber() {
        return number;
    }

    public Type getFather() {
        return father;
    }
    
    public boolean isFather(Type type){
        System.out.println("FFFFFFFFFFFFFFFAAAAAAAAAAAAAAAAAAA"+father+" DD "+type.getName());
        if (father==null) {
            return false;
        }else{
            if (father.equals(type)) {
                return true;
            }else{
                return father.isFather(type);
            }
        }
    }
}
