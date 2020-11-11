/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tac_assembly_generator.Assembly;

import java.util.ArrayList;
import tac_assembly_generator.TAC.quadruple.Operation;

/**
 *
 * @author sergio
 */
public class TextSection {

    private static final String NAME = "section .text";
    private static final String START = "_start";
    private static final String CALL = "call";
    
    private static final String ADD = "add";
    private static final String SUB = "sub";
    private static final String MUL = "mul";
    private static final String DIV = "div";
    
    
    private static final String RET = "ret";
    private static final String RAX = "rax";
    private static final String RDI = "rdi";
    private static final String RSI = "rsi";
    private static final String RDX = "rdx";
    private static final String RBX = "rbx";
    
    private static final String PUSH = "push";
    private static final String POP = "pop";
    
    private static final String INC = "inc";
    private static final String CMP = "cmp";
    private static final String CL = "cl";
    
    private static final String JMP = "jmp";
    private static final String JE = "je";
    private static final String JNE = "jne";
    private static final String JG = "jg";
    private static final String JGE = "jge";
    private static final String JLE = "jle";
    private static final String JL = "jl";
    
    private static final String SYSCALL = "syscall";
    private static final String PRINT = "printSubroutine";
    private static final String PRINT_LOOP = "printSubroutineLoop";
    

    private static final String MOV = "mov";
    private ArrayList<String> lines;

    public TextSection() {
        lines = new ArrayList<>();

        lines.add("\tglobal _start");
        addPrintSubroutine();
    }

    @Override
    public String toString() {
        String s = NAME + "\n";
        for (int i = 0; i < lines.size(); i++) {
            s += lines.get(i) + "\n";
        }
        return s;

    }
    public void addJump(String label){
        lines.add("\t" + JMP + " _" + label );
    }
        public void addEqual(String ob1, String ob2){
            if (ob2.contains("stack")) {
                
                ob2=getStackValue(ob2);
            }
            lines.add("\t"+MOV+" eax, "+ ob2);
            if (ob1.contains("stack")) {
                
                ob1=getStackValue(ob1);
              lines.add("\t"+MOV+" eax, "+ ob2);  
            }else{
                ob1="["+ob1+"]";
            }
            lines.add("\t"+MOV+" "+ob1+", eax");  
        
    }
        public void addOp(String ob1, String ob2,int op){
            if (ob2.contains("stack")) {
                
                ob2=getStackValue(ob2);
            }
            lines.add("\t"+MOV+" eax, "+ ob2);
            if (ob1.contains("stack")) {
                
                ob1=getStackValue(ob1);
              lines.add("\t"+MOV+" eax, "+ ob2);  
            }
            switch (op) {
                case Operation.PLUS:
                    lines.add("\t"+ADD+" eax, "+ ob1);
                    break;
                case Operation.MINUS:
                    lines.add("\t"+SUB+" eax, "+ ob1);
                    break;    
                case Operation.MULTIPLICATION:
                    lines.add("\t"+MUL+" eax, "+ ob1);
                    break;    
                case Operation.DIVISION:
                    lines.add("\t"+MOV+" ax, "+ ob1+"");
                    lines.add("\t"+SUB+" ax, \'0\'");
                    lines.add("\t"+MOV+" bl, "+ ob2);
                    lines.add("\t"+SUB+" bl, \'0\'");
                    lines.add("\t"+DIV+" bl");
                    lines.add("\t"+ADD+" ax, \'0\'");
                    break;    
            }
            
            lines.add("\t"+MOV+" "+ob1+", eax");  
        
    }
    
            public void addCondicion(String ob1, String ob2,int op,String result){
            if (ob2.contains("stack")) {
                
                ob2=getStackValue(ob2);
            }
            lines.add("\t"+MOV+" eax, "+ ob2);
            if (ob1.contains("stack")) {
                
                ob1=getStackValue(ob1);
              lines.add("\t"+MOV+" eax, "+ ob2);  
            }
                System.out.println("CCCCMMMM "+ob1+" "+ob2 +" "+result);
                lines.add("\t"+CMP+" "+ ob1+" "+ob2); 
            
            switch (op) {
                case Operation.EQUAL_BOOL:
                    lines.add("\t"+JE+" "+result);
                    break;
                case Operation.DIFERENT:
                    lines.add("\t"+JNE+" "+result);
                    break;   
                case Operation.GREATER_THAN:
                    lines.add("\t"+JG+" "+result);
                    break;   
                case Operation.GREATER_THAN_EQUAL:
                    lines.add("\t"+JGE+" "+result);
                    break;  
                case Operation.LESS_THAN:
                    lines.add("\t"+JL+" "+result);
                    break;   
                case Operation.LESS_THAN_EQUAL:
                    lines.add("\t"+JLE+" "+result);
                    break;      
            }
            
            lines.add("\t"+MOV+" "+ob1+", eax");  
        
    }    
        
        
    public String getStackValue(String ob2){
        ob2= ob2.replace("stack[", "");
                ob2= ob2.replace("]", "");
                ob2="[stack+"+ob2+"]";
                return ob2;
    }
    public void closeStart() {
        lines.add("\t" + MOV + " " + RAX + ", 60");
        lines.add("\t" + MOV + " " + RDI + ", 0");
        addSyscall();
    }

    public void closeLabel() {
        lines.add("\t" + RET);
    }

    public void startLabel() {
        lines.add(START + ":");
    }

    public void openLable(String label) {
        lines.add("_" + label + ":");
    }
    private void addSyscall(){
        lines.add("\t" + SYSCALL);
    }
    private void addPrintSubroutine(){
        lines.add("_"+PRINT+":");
        lines.add("\t"+PUSH+" "+RAX);
        lines.add("\t"+MOV+" "+RBX+", 0");
        lines.add("_"+PRINT_LOOP+":");
        lines.add("\t"+INC+" "+RAX);
        lines.add("\t"+INC+" "+RBX);
        lines.add("\t"+MOV+" "+CL+", ["+RAX+"]");
        lines.add("\t"+CMP+" "+CL+", 0");
        lines.add("\t"+JNE+" _"+PRINT_LOOP);
        lines.add("\t" + MOV + " " + RAX + ", 1");
        lines.add("\t" + MOV + " " + RDI + ", 1");
        lines.add("\t" + POP + " " + RSI);
        lines.add("\t" + MOV + " " + RDX + ", "+RBX);
        addSyscall();
        lines.add("\t" + RET);
        
        
    }
    public void createPrint(String text) {
        lines.add("\t" + MOV + " " + RAX + ", "+text);
        lines.add("\t" +CALL+" _"+ PRINT);
        
    }
}
