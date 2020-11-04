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
    public static final String P = "p";
    public static final String H = "h";
    public static final String RETURN = "return";
    public static final String STACK_NAME = "stack";
    public static final String HEAP_NAME = "heap";
    
    public Stack() {
        currentP = 0;
        stacks = new ArrayList<>();
        stacks.add(new StackPart("main", "C"));
    }
    
    public ArrayList<StackPart> getStacks() {
        return stacks;
    }
    
    public void addToCurrentStack(Tuple tuple) {
        stacks.get(currentP).addTuple(tuple);
    }
    
    public void creatNewStack(String id, String lan) {
        stacks.add(new StackPart(id, lan));
        currentP = stacks.size() - 1;
    }
    
    public void enterMain() {
        currentP = 0;
    }
    
    public void changeName(String id) {
        stacks.get(currentP).setId(id);
    }
    
    public int getStackSize() {
        int size = 0;
        for (int i = 0; i < stacks.size(); i++) {
            for (int j = 0; j < stacks.get(i).getTuples().size(); j++) {
                size += stacks.get(i).getTuples().get(j).getSize();
            }
        }
        return size;
    }
    
    public void addThis() {
        Tuple newTuple = new Tuple("this", null, null, 0, null, null);
        newTuple.setStackInfo(1);
        stacks.get(currentP).addAtTuple(newTuple, 0);
    }
    
    public void addReturn() {
        Tuple newTuple = new Tuple("return", null, null, 0, null, null);
        newTuple.setStackInfo(1);
        if (stacks.get(currentP).getTuples().isEmpty()) {
            stacks.get(currentP).addAtTuple(newTuple, 0);
        } else if (stacks.get(currentP).getTuples().get(0).getName().equals("this")) {
            stacks.get(currentP).addAtTuple(newTuple, 1);
        } else {
            stacks.get(currentP).addAtTuple(newTuple, 0);
        }
        
    }
    
    public void endClassFunction() {
        stacks.get(currentP).endFunction();
    }
    
    public Integer getIdPosition(String id) {
        
        int space = 0;
        
        for (int i = 0; i < stacks.get(currentP).getTuples().size(); i++) {
            
            if (stacks.get(currentP).getTuples().get(i).getName().equals(id)) {
                
                return space;
            }
            space += stacks.get(currentP).getTuples().get(i).getSize();
            
        }
        return null;
    }
    
    public static String getOutputStack(String id) {
        return STACK_NAME + "[" + id + "]";
    }
    public static String getOutputHeap(String id) {
        return HEAP_NAME + "[" + id + "]";
    }
    
    
    public void setCurrentP(int currentP) {
        this.currentP = currentP;
    }
    
    public void changeCurrent(String function) {
        Integer index = null;
        for (int i = 0; i < stacks.size(); i++) {
            if (stacks.get(i).getId().equals(function)) {
                index = i;
                break;
            }
        }
        if (index != null) {
            currentP = index;
        } else {
            currentP = 0;
        }
    }
    
    public Integer getIdFromFunction(String name, String id) {
        for (int i = 0; i < stacks.size(); i++) {
            if (stacks.get(i).getId().equals(name)) {
                for (int j = 0; j < stacks.get(i).getTuples().size(); j++) {
                    if (stacks.get(i).getTuples().get(j).getName().equals(id)) {
                        return j;
                    }
                }
            }
            
        }
        return null;
    }
public String getCurrentClassName(){
    String[] fun =getCurrentId().split("_");
    if (fun.length>=1) {
        
        String className=fun[0]+"_"+fun[1];
        if (fun[0].equals("JAVA")) {
            return className;
        }
        
    }
    return null;
}
public Integer getIdIndexCurrentClass(String id){
    String className=getCurrentClassName();
   return getIdFromFunction(className, id);
    
    
    
}

    public Tuple getTupleIdFromFunction(String name, String id) {
        for (int i = 0; i < stacks.size(); i++) {
            if (stacks.get(i).getId().equals(name)) {
                for (int j = 0; j < stacks.get(i).getTuples().size(); j++) {
                    if (stacks.get(i).getTuples().get(j).getName().equals(id)) {
                        return stacks.get(i).getTuples().get(j);
                    }
                }
            }
            
        }
        return null;
    }
    
    public Integer getCurrentSize(int a) {
        int size = 0;
        for (int i = 0; i < stacks.get(currentP).getTuples().size(); i++) {
            size += stacks.get(currentP).getTuples().get(i).getSize();
        }
        return size;
        
    }
    
    public void changeCurrent(Integer c) {
        currentP = c;
    }
    
    public int getCurrentP() {
        return currentP;
    }
    
    public Integer getFunctionSize(String name) {
        int asst = 0;
        for (int i = 0; i < stacks.size(); i++) {
            if (stacks.get(i).getId().equals(name)) {
                for (int j = 0; j < stacks.get(i).getTuples().size(); j++) {
                    asst += stacks.get(i).getTuples().get(j).getSize();
                }
            }
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
    
    public String getCurrentId() {
        return stacks.get(currentP).getId();
        
    }

    public Integer getFunctionIndex(String name) {
        int asst = 0;
        for (int i = 0; i < stacks.get(currentP).getTuples().size(); i++) {
            asst += stacks.get(currentP).getTuples().get(i).getSize();
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
