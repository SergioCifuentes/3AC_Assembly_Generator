/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tac_assembly_generator.Assembly;

import java.util.ArrayList;

/**
 *
 * @author sergio
 */
public class TextSection {

    private static final String NAME = "section .text";
    private static final String START = "_start";
    private static final String CALL = "call";
    
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
    private static final String JNE = "jne";
    
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
