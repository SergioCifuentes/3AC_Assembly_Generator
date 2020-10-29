/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tac_assembly_generator.TAC.stack;

import java.util.ArrayList;
import tac_assembly_generator.languages.semantic.Tuple;

/**
 *
 * @author sergio
 */
public class Stack {
    private ArrayList<StackPart> stacks;
    private int currentP;
    public static final  String P="p";
    public static final  String RETURN="return";
    public static final  String STACK_NAME="stack";
    
    public Stack() {
        currentP=0;
        stacks= new ArrayList<>();
        stacks.add(new StackPart("main","C"));
    }

    public ArrayList<StackPart> getStacks() {
        return stacks;
    }
    
    public void addToCurrentStack(Tuple tuple){
        stacks.get(currentP).addTuple(tuple);
    }
    
    public void creatNewStack(String id,String lan){
        stacks.add(new StackPart(id,lan));
        currentP++;
    }
    
    public void enterMain(){
        currentP=0;
    }
    
    public int getStackSize(){
        int size=0;
        for (int i = 0; i < stacks.size(); i++) {
            for (int j = 0; j < stacks.get(i).getTuples().size(); j++) {
                size+=stacks.get(i).getTuples().get(j).getSize();
            }
        }
        return size;
    }
        public Integer getIdPosition(String id){
            System.out.println("FFFFFFFFFF "+id);
        int space=0;
        for (int i = 0; i < stacks.get(currentP).getTuples().size(); i++) {
            System.out.println(stacks.get(currentP).getTuples().get(i).getName());
            if (stacks.get(currentP).getTuples().get(i).getName().equals(id)) {
                System.out.println("sPACE");
                return space;
            }
            space+=stacks.get(currentP).getTuples().get(i).getSize();
        }
        return null;
    }
    public static String getOutputStack(String id){
        return STACK_NAME+"["+id+"]";
    }

    public void setCurrentP(int currentP) {
        this.currentP = currentP;
    }
    public void changeCurrent(String function){
        Integer index=null;
        for (int i = 0; i < stacks.size(); i++) {
            System.out.println(stacks.get(i).getId());
            if (stacks.get(i).getId().equals(function)) {
                index=i;
                break;
            }
        }
        if (index!=null) {
            currentP=index;
        }else{
            currentP=0;
        }
    }
    public void changeCurrent(Integer c){
        currentP=c;
    }

    public int getCurrentP() {
        return currentP;
    }
    

    public Integer getFunctionIndex(String name) {
        System.out.println("Index of  "+name);
        int asst=0;
        for (int i = 0; i < stacks.get(currentP).getTuples().size(); i++) {
            asst+=stacks.get(currentP).getTuples().get(i).getSize();
        }
        return asst;
//        for (int i = 0; i < stacks.size(); i++) {
//            System.out.println("IDD "+stacks.get(i).getId());
//            if (name.contains(stacks.get(i).getId())&&name.contains(stacks.get(i).getLanguaje())) {
//                return asst;
//            }else{
//                for (int j = 0; j < stacks.get(i).getTuples().size(); j++) {
//                    asst+=stacks.get(i).getTuples().get(j).getSize();
//                }
//            }
//        }
    }
}
