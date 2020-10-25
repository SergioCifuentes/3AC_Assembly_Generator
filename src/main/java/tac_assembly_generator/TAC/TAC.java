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
            translateQuad(quads.get(i),stack);

        }

    }

    public String translateQuad(Object quad,Stack stack) {
        if (quad.getClass().equals(String.class)) {
            if (!((String) quad).endsWith("\n")) {
                if (((String) quad).startsWith("//") || ((String) quad).startsWith("/*")) {
                    OutputText.appendToPane(mainFrame.getTACPannel(), (String) quad + "\n", Color.lightGray, false);
                } else {
                    OutputText.appendToPane(mainFrame.getTACPannel(), (String) quad + "\n", Color.ORANGE, false);
                }
            } else {
                if (((String) quad).startsWith("//") || ((String) quad).startsWith("/*")) {
                    OutputText.appendToPane(mainFrame.getTACPannel(), (String) quad, Color.lightGray, false);
                } else {
                    OutputText.appendToPane(mainFrame.getTACPannel(), (String) quad, Color.orange, false);
                }
            }
        } else if (quad.getClass().equals(Quadruple.class)) {

            Quadruple quadAsst = (Quadruple) quad;
            if (quadAsst.getOp() == null) {
                OutputText.appendToPane(mainFrame.getTACPannel(), quadAsst.getResult() + ":\n", Color.blue, false);
            } else if (quadAsst.getOp().equals(Operation.GO_TO)) {
                OutputText.appendToPane(mainFrame.getTACPannel(), Operation.getIntOpOutput(quadAsst.getOp()) + " " + quadAsst.getResult() + ";\n", Color.white, false);
            } else if (quadAsst.getOp() == Operation.EQUAL) {
                if (quadAsst.getResult().equals(Stack.P)&&quadAsst.getArg1()!=Integer.valueOf(0)) {
                    OutputText.appendToPane(mainFrame.getTACPannel(), Stack.P+" = " +stack.getFunctionIndex(quadAsst.getArg1().toString()) +";\n", Color.white, false);
                }else{
                if (quadAsst.isConstante()) {
                    OutputText.appendToPane(mainFrame.getTACPannel(), "const ", Color.white, false);
                }
                String tacQuad = quadAsst.getResult() + Operation.getIntOpOutput(quadAsst.getOp()) + quadAsst.getArg1();
                OutputText.appendToPane(mainFrame.getTACPannel(), tacQuad + ";\n", Color.white, false);
                }
            } else if (quadAsst.getOp() <= Operation.MINUS) {
                String tacQuad = quadAsst.getResult() + "=" + quadAsst.getArg1() + Operation.getIntOpOutput(quadAsst.getOp()) + quadAsst.getArg2();
                OutputText.appendToPane(mainFrame.getTACPannel(), tacQuad + ";\n", Color.white, false);
            } else if (quadAsst.getOp() <= Operation.EQUAL_BOOL) {
                OutputText.appendToPane(mainFrame.getTACPannel(), "If " + quadAsst.getArg1() + " " + Operation.getIntOpOutput(quadAsst.getOp()) + " " + quadAsst.getArg2() + " " + Operation.getIntOpOutput(Operation.GO_TO) + " " + quadAsst.getResult() + ";\n", Color.white, false);
            } else if (quadAsst.getOp() == Operation.PRINT) {
                OutputText.appendToPane(mainFrame.getTACPannel(), Operation.getIntOpOutput(quadAsst.getOp()) + " " + quadAsst.getResult() + ";\n", Color.white, false);
            } else if (quadAsst.getOp() == Operation.CLRSCR) {
                OutputText.appendToPane(mainFrame.getTACPannel(), "clrscr();\n", Color.white, false);
            } else if (quadAsst.getOp() == Operation.READ) {

                OutputText.appendToPane(mainFrame.getTACPannel(), "read ", Color.white, false);
                if (quadAsst.getResult() != null) {
                    OutputText.appendToPane(mainFrame.getTACPannel(), quadAsst.getResult() + "\n", Color.white, false);
                } else {
                    OutputText.appendToPane(mainFrame.getTACPannel(), "\n", Color.white, false);
                }
            } else if (quadAsst.getOp() == Operation.GETCH) {

                OutputText.appendToPane(mainFrame.getTACPannel(), "getch ", Color.white, false);
                if (quadAsst.getResult() != null) {
                    OutputText.appendToPane(mainFrame.getTACPannel(), quadAsst.getResult() + ";\n", Color.white, false);
                } else {
                    OutputText.appendToPane(mainFrame.getTACPannel(), ";\n", Color.white, false);
                }
            } else if (quadAsst.getOp() == Operation.ARRAY) {
                OutputText.appendToPane(mainFrame.getTACPannel(), quadAsst.getResult() + "[ " + quadAsst.getArg1() + "];\n", Color.white, false);
            } else if (quadAsst.getOp() == Operation.PARAMS) {
                OutputText.appendToPane(mainFrame.getTACPannel(), "param " + quadAsst.getResult() + "\n", Color.white, false);
            }else if (quadAsst.getOp() == Operation.TEMP) {
                OutputText.appendToPane(mainFrame.getTACPannel(), quadAsst.getResult() + ";\n", Color.white, false);
            }else if (quadAsst.getOp() == Operation.INCLUDE) {
                OutputText.appendToPane(mainFrame.getTACPannel(),"#Include <"+ quadAsst.getResult() + ">\n", Color.white, false);
            }

        }
        return null;
    }

    public void addCode(String tacCode, String block) {

    }

    public void addCode(String tacCode) {

    }

    public String getTac() {
        return tac;
    }

}
