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

    private static final String ESI = "esi";
    private static final String EDI = "edi";

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

    }

    public void addPrints() {
        addPrintSubroutine();
        addIntegerPrint();
    }

    @Override
    public String toString() {
        String s = NAME + "\n";
        for (int i = 0; i < lines.size(); i++) {
            s += lines.get(i) + "\n";
        }
        return s;

    }

    public void addJump(String label) {
        lines.add("\t" + JMP + " _" + label);
    }

    public void addRead(String ob1) {
        lines.add("	mov rax, 0\n"
                + "	mov rdi, 0\n"
                + "	mov rsi, res\n"
                + "	mov rdx, 16\n"
                + "	syscall\n"
                + "	mov al, [res]");
        if (ob1.contains("stack") || ob1.startsWith("heap")) {

            ob1 = getStackValue(ob1);
        } else {
            ob1 = "[" + ob1 + "]";
        }
        lines.add("\t" + MOV + " " + ob1 + ", al");
    }

    public void addEqual(String ob1, String ob2) {

        Integer num = verifyNumValues(ob2);
        if (num != null) {

            lines.add("\t" + MOV + " al, " + num);
            if (ob1.startsWith("stack") || ob1.startsWith("heap")) {
                ob2 = getStackValue(ob2);
                lines.add("\t" + MOV + " " + ob1 + ",al");
            } else {
                lines.add("\t" + MOV + " [" + ob1 + "],al");
            }
        } else {
            if (ob2.contains("stack")|| ob2.startsWith("heap")) {

                ob2 = getStackValue(ob2);
            } else {
                ob2 = "[" + ob2 + "]";
            }

            if (ob1.contains("stack") || ob1.startsWith("heap")) {

                ob1 = getStackValue(ob1);
            } else {
                ob1 = "[" + ob1 + "]";
            }
            lines.add("\t" + MOV + " al, " + ob2);
            lines.add("\t" + MOV + " " + ob1 + ", al");
        }

    }

    public Integer verifyNumValues(String ob2) {
        boolean num = false;
        boolean flo = false;
        try {
            Integer s = Integer.parseInt(ob2);
            num = true;
        } catch (Exception e) {
            try {
                Float s = Float.parseFloat(ob2);
                flo = true;
            } catch (Exception e2) {

            }
        }
        if (num) {
            Integer s = Integer.parseInt(ob2);
            return s;
        } else if (flo) {
            Float s = Float.parseFloat(ob2);
            return s.intValue();
        }
        return null;

    }

    public void addOp(String ob1, String ob2, int op, String result) {
        Integer num = verifyNumValues(ob2);
        if (num != null) {
            ob2 = String.valueOf(num);
        } else if (ob2.contains("stack") || ob1.startsWith("heap")) {

            ob2 = getStackValue(ob2);
        } else {
            ob2 = "[" + ob2 + "]";
        }
        lines.add("\t" + MOV + " al, " + ob2);

        Integer num2 = verifyNumValues(ob1);
        if (num2 != null) {
            ob1 = String.valueOf(num2);
        } else if (ob1.contains("stack") || ob1.startsWith("heap")) {

            ob1 = getStackValue(ob1);
        } else {
            ob1 = "[" + ob1 + "]";
        }

        switch (op) {
            case Operation.PLUS:
                lines.add("\t" + ADD + " al, " + ob1);
                break;
            case Operation.MINUS:
                lines.add("\t" + SUB + " al, " + ob1);
                break;
            case Operation.MULTIPLICATION:
                lines.add("\t" + MUL + " al, " + ob1);
                break;
            case Operation.DIVISION:
                lines.add("\t" + MOV + " ax, " + ob1 + "");
                lines.add("\t" + MOV + " dl, " + ob2);
                lines.add("\t" + DIV + " dl");
                lines.add("\t" + ADD + " ax, \'0\'");
                break;
        }
        if (result.contains("stack") || ob1.startsWith("heap")) {

            result = getStackValue(ob2);
        } else {
            result = "[" + result + "]";
        }

        lines.add("\t" + MOV + " " + result + ", al");

    }

    public void addCondicion(String ob1, String ob2, int op, String result) {
        if (ob2.contains("stack") || ob1.startsWith("heap")) {

            ob2 = getStackValue(ob2);
        } else {
            ob2 = "[" + ob2 + "]";
        }
        lines.add("\t" + MOV + " al, " + ob2);
        if (ob1.contains("stack") || ob1.startsWith("heap")) {

            ob1 = getStackValue(ob1);
        } else {
            ob1 = "[" + ob1 + "]";
        }

        lines.add("\t" + CMP + " " + ob1 + ", al");

        switch (op) {
            case Operation.EQUAL_BOOL:
                lines.add("\t" + JE + " _" + result);
                break;
            case Operation.DIFERENT:
                lines.add("\t" + JNE + " _" + result);
                break;
            case Operation.GREATER_THAN:
                lines.add("\t" + JG + " _" + result);
                break;
            case Operation.GREATER_THAN_EQUAL:
                lines.add("\t" + JGE + " _" + result);
                break;
            case Operation.LESS_THAN:
                lines.add("\t" + JL + " _" + result);
                break;
            case Operation.LESS_THAN_EQUAL:
                lines.add("\t" + JLE + " _" + result);
                break;
        }

    }

    public String getStackValue(String ob2) {
        if (ob2.startsWith("heap")) {
            ob2 = ob2.replace("heap[", "");
            ob2 = ob2.replace("]", "");
            lines.add("\t" + MOV + " " + ESI + ", heap");
            lines.add("\t" + MOV + " " + EDI + ", 0");
            lines.add("\t" + ADD + " " + ESI + ", [" + ob2 + "]");

            return "[" + ESI + "]";
        } else {
            ob2 = ob2.replace("stack[", "");
            ob2 = ob2.replace("]", "");
            lines.add("\t" + MOV + " " + ESI + ", stack");
            lines.add("\t" + MOV + " " + EDI + ", 0");
            lines.add("\t" + ADD + " " + ESI + ", [" + ob2 + "]");

            return "[" + ESI + "]";
        }
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

    private void addSyscall() {
        lines.add("\t" + SYSCALL);
    }

    private void addPrintSubroutine() {
        lines.add("_print:\n"
                + "	push rax\n"
                + "	mov rbx, 0\n"
                + "_printLoop:\n"
                + "	inc rax\n"
                + "	inc rbx\n"
                + "	mov cl, [rax]\n"
                + "	cmp cl, 0\n"
                + "	jne _printLoop\n"
                + "\n"
                + "	mov rax, 1\n"
                + "	mov rdi, 1\n"
                + "	pop rsi\n"
                + "	mov rdx, rbx\n"
                + "	syscall\n"
                + "\n"
                + "	ret");

    }

    private void addIntegerPrint() {
        lines.add("_printRAX:\n"
                + "    mov rcx, digitSpace\n"
                + "    mov rbx, 10\n"
                + "    mov [rcx], rbx\n"
                + "    inc rcx\n"
                + "    mov [digitSpacePos], rcx\n"
                + " \n"
                + "_printRAXLoop:\n"
                + "    mov rdx, 0\n"
                + "    mov rbx, 10\n"
                + "    div rbx\n"
                + "    push rax\n"
                + "    add rdx, 48\n"
                + " \n"
                + "    mov rcx, [digitSpacePos]\n"
                + "    mov [rcx], dl\n"
                + "    inc rcx\n"
                + "    mov [digitSpacePos], rcx\n"
                + "    \n"
                + "    pop rax\n"
                + "    cmp rax, 0\n"
                + "    jne _printRAXLoop\n"
                + " \n"
                + "_printRAXLoop2:\n"
                + "    mov rcx, [digitSpacePos]\n"
                + " \n"
                + "    mov rax, 1\n"
                + "    mov rdi, 1\n"
                + "    mov rsi, rcx\n"
                + "    mov rdx, 1\n"
                + "    syscall\n"
                + " \n"
                + "    mov rcx, [digitSpacePos]\n"
                + "    dec rcx\n"
                + "    mov [digitSpacePos], rcx\n"
                + " \n"
                + "    cmp rcx, digitSpace\n"
                + "    jge _printRAXLoop2\n"
                + " \n"
                + "    ret");
    }

    public void createPrint(String text) {
        lines.add("\t" + MOV + " " + RAX + ", " + text);
        lines.add("\t" + CALL + " _print");

    }

    public void createIntegerPrint(String text) {
        if (!text.contains("stack")) {
            lines.add("\t" + MOV + " " + RAX + ", [" + text + "]");
            lines.add("\t" + CALL + " _printRAX");

        }

    }

}
