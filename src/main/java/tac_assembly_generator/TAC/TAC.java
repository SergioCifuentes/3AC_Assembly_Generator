/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tac_assembly_generator.TAC;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JTextPane;
import javax.swing.text.TextAction;
import tac_assembly_generator.TAC.quadruple.BoolQuad;
import tac_assembly_generator.TAC.quadruple.Operation;
import tac_assembly_generator.TAC.quadruple.Quadruple;
import tac_assembly_generator.TAC.stack.Stack;
import tac_assembly_generator.ui.MainFrame;
import tac_assembly_generator.ui.backend.OutputText;

/**
 *
 * @author sergio
 */
public class TAC {

    private String tac;
    private JTextPane jtp;
    public MainFrame mainFrame;

    public TAC(MainFrame mainFrame) {
        tac = "";
        this.mainFrame = mainFrame;
    }

    public void translateQuads(ArrayList<Object> quads,Stack stack) {

        for (int i = 0; i < quads.size(); i++) {
            translateQuad(quads.get(i),stack,mainFrame.getTACPannel());

        }

    }
        public void translateQuadsOP(ArrayList<Object> quads,Stack stack) {

        for (int i = 0; i < quads.size(); i++) {
            translateQuad(quads.get(i),stack,mainFrame.getOptimizedPannel());

        }

    }
    
        public static void translateQuad(Object quad,Stack stack,JTextPane jtextPane) {
        if (quad.getClass().equals(String.class)) {
            if (!((String) quad).endsWith("\n")) {
                if (((String) quad).startsWith("//") || ((String) quad).startsWith("/*")) {
                    OutputText.appendToPane(jtextPane, (String) quad + "\n", Color.lightGray, false);
                } else {
                    OutputText.appendToPane(jtextPane, (String) quad + "\n", Color.ORANGE, false);
                }
            } else {
                if (((String) quad).startsWith("//") || ((String) quad).startsWith("/*")) {
                    OutputText.appendToPane(jtextPane, (String) quad, Color.lightGray, false);
                } else {
                    OutputText.appendToPane(jtextPane, (String) quad, Color.orange, false);
                }
            }
        } else if (quad.getClass().equals(Quadruple.class)) {

            Quadruple quadAsst = (Quadruple) quad;
            if (quadAsst.getOp() == null) {
                OutputText.appendToPane(jtextPane, quadAsst.getResult() + ":\n", Color.blue, false);
            } else if (quadAsst.getOp().equals(Operation.GO_TO)) {
                OutputText.appendToPane(jtextPane, Operation.getIntOpOutput(quadAsst.getOp()) + " " + quadAsst.getResult() + ";\n", Color.white, false);
            } else if (quadAsst.getOp() == Operation.EQUAL) {
                if (quadAsst.getResult().equals(Stack.P)&&quadAsst.getArg1()!=Integer.valueOf(0)) {
                    OutputText.appendToPane(jtextPane, Stack.P+" = " +stack.getFunctionIndex(quadAsst.getArg1().toString()) +";\n", Color.white, false);
                }else{
                if (quadAsst.isConstante()) {
                    OutputText.appendToPane(jtextPane, "const ", Color.white, false);
                }
                String tacQuad = quadAsst.getResult() + Operation.getIntOpOutput(quadAsst.getOp()) + quadAsst.getArg1();
                OutputText.appendToPane(jtextPane, tacQuad + ";\n", Color.white, false);
                }
            } else if (quadAsst.getOp() <= Operation.MINUS) {
                if (quadAsst.getOp()==Operation.PLUS&&quadAsst.getResult().equals(Stack.P)) {
                     OutputText.appendToPane(jtextPane, Stack.P+" = "+Stack.P +" + "+stack.getFunctionIndex(quadAsst.getArg2().toString()) +";\n", Color.white, false);
                }else{
                         String tacQuad = quadAsst.getResult() + "=" + quadAsst.getArg1() + Operation.getIntOpOutput(quadAsst.getOp()) + quadAsst.getArg2();
                OutputText.appendToPane(jtextPane, tacQuad + ";\n", Color.white, false);
           
                }
            } else if (quadAsst.getOp() <= Operation.EQUAL_BOOL) {
                OutputText.appendToPane(jtextPane, "If " + quadAsst.getArg1() + " " + Operation.getIntOpOutput(quadAsst.getOp()) + " " + quadAsst.getArg2() + " " + Operation.getIntOpOutput(Operation.GO_TO) + " " + quadAsst.getResult() + ";\n", Color.white, false);
            } else if (quadAsst.getOp() == Operation.PRINT) {
                OutputText.appendToPane(jtextPane, Operation.getIntOpOutput(quadAsst.getOp()) + " " + quadAsst.getResult() + ";\n", Color.white, false);
            } else if (quadAsst.getOp() == Operation.CLRSCR) {
                OutputText.appendToPane(jtextPane, "clrscr();\n", Color.white, false);
            } else if (quadAsst.getOp() == Operation.READ) {

                OutputText.appendToPane(jtextPane, "read ", Color.white, false);
                if (quadAsst.getResult() != null) {
                    OutputText.appendToPane(jtextPane, quadAsst.getResult() + "\n", Color.white, false);
                } else {
                    OutputText.appendToPane(jtextPane, "\n", Color.white, false);
                }
            } else if (quadAsst.getOp() == Operation.GETCH) {

                OutputText.appendToPane(jtextPane, "getch ", Color.white, false);
                if (quadAsst.getResult() != null) {
                    OutputText.appendToPane(jtextPane, quadAsst.getResult() + ";\n", Color.white, false);
                } else {
                    OutputText.appendToPane(jtextPane, ";\n", Color.white, false);
                }
            } else if (quadAsst.getOp() == Operation.ARRAY) {
                OutputText.appendToPane(jtextPane, quadAsst.getResult() + "[ " + quadAsst.getArg1() + "];\n", Color.white, false);
            } else if (quadAsst.getOp() == Operation.PARAMS) {
                OutputText.appendToPane(jtextPane, "param " + quadAsst.getResult() + "\n", Color.white, false);
            }else if (quadAsst.getOp() == Operation.TEMP) {
                OutputText.appendToPane(jtextPane, quadAsst.getResult() + ";\n", Color.white, false);
            }else if (quadAsst.getOp() == Operation.INCLUDE) {
                OutputText.appendToPane(jtextPane,"#Include <"+ quadAsst.getResult() + ">\n", Color.white, false);
            }

        }
        
    }
    
    
    
    

    public void addCode(String tacCode, String block) {

    }

    public void addCode(String tacCode) {

    }

    public String getTac() {
        return tac;
    }

}
