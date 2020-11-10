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

    public void translateQuads(ArrayList<Object> quads, Stack stack) {
        
        for (int i = 0; i < quads.size(); i++) {
            translateQuad(quads.get(i), stack, mainFrame.getTACPannel());
        

        }

    }

    public void translateQuadsOP(ArrayList<Object> quads, Stack stack) {

        for (int i = 0; i < quads.size(); i++) {
            translateQuad(quads.get(i), stack, mainFrame.getOptimizedPannel());

        }

    }

    public static void translateQuad(Object quad, Stack stack, JTextPane jtextPane) {
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
                    OutputText.appendToPane(jtextPane, (String) quad, Color.green, false);
                }
            }
        } else if (quad.getClass().equals(Quadruple.class)) {
            
            Quadruple quadAsst = (Quadruple) quad;
            
            if (quadAsst.getOp() == null) {
                OutputText.appendToPane(jtextPane, quadAsst.getResult() + ":\n", Color.blue, false);
            } else if (quadAsst.getOp().equals(Operation.GO_TO)) {
                OutputText.appendToPane(jtextPane, Operation.getIntOpOutput(quadAsst.getOp()) + " " + quadAsst.getResult() + ";\n", Color.white, false);
            } else if (quadAsst.getOp() == Operation.EQUAL) {

                String tacQuad = quadAsst.getResult() + Operation.getIntOpOutput(quadAsst.getOp()) + quadAsst.getArg1();
                OutputText.appendToPane(jtextPane, tacQuad + ";\n", Color.white, false);
                
                
                
            } else if (quadAsst.getOp() <= Operation.MINUS) {
                if ((quadAsst.getOp() == Operation.PLUS || quadAsst.getOp() == Operation.MINUS) && quadAsst.getResult().equals(Stack.P)) {
                    OutputText.appendToPane(jtextPane, Stack.P + " = " + Stack.P + " " + Operation.getIntOpOutput(quadAsst.getOp()) + " " + stack.getFunctionSize(quadAsst.getArg2().toString()) + ";\n", Color.white, false);
                } else if ((quadAsst.getOp() == Operation.PLUS || quadAsst.getOp() == Operation.MINUS) && quadAsst.getArg1().equals(Stack.P) && quadAsst.getArg2().getClass().equals(String.class)) {
                    OutputText.appendToPane(jtextPane, quadAsst.getResult() + " = " + Stack.P + " " + Operation.getIntOpOutput(quadAsst.getOp()) + " " + stack.getFunctionSize(quadAsst.getArg2().toString()) + ";\n", Color.white, false);
                } else {
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
            } else if (quadAsst.getOp() == Operation.TEMP) {
                OutputText.appendToPane(jtextPane, quadAsst.getResult() + ";\n", Color.white, false);
            } else if (quadAsst.getOp() == Operation.INCLUDE) {
                OutputText.appendToPane(jtextPane, "#Include <" + quadAsst.getResult() + ">\n", Color.white, false);
            }else if (quadAsst.getOp() == Operation.FUNCTION) {
                OutputText.appendToPane(jtextPane, quadAsst.getResult()+"\n", Color.orange, false);
            }


        }

    }

    public String translateQuad(Object quad, Stack stack) {
        if (quad.getClass().equals(String.class)) {
            if (!((String) quad).endsWith("\n")) {
                return (String)quad+"\n";
            } else {
               return (String)quad;
            }
        } else if (quad.getClass().equals(Quadruple.class)) {
            
            Quadruple quadAsst = (Quadruple) quad;
            System.out.println(quadAsst);
            if (quadAsst.getOp() == null) {
                return quadAsst.getResult() + ":\n";
                
            } else if (quadAsst.getOp().equals(Operation.GO_TO)) {
                return Operation.getIntOpOutput(quadAsst.getOp()) + " " + quadAsst.getResult() + ";\n";
            } else if (quadAsst.getOp() == Operation.EQUAL) {
                String aux="";
                
                aux+=quadAsst.getResult() + Operation.getIntOpOutput(quadAsst.getOp()) + quadAsst.getArg1()+";\n";
                return aux;
                

            } else if (quadAsst.getOp() <= Operation.MINUS) {
                if ((quadAsst.getOp() == Operation.PLUS || quadAsst.getOp() == Operation.MINUS) && quadAsst.getResult().equals(Stack.P)) {
                    return Stack.P + " = " + Stack.P + " " + Operation.getIntOpOutput(quadAsst.getOp()) + " " + stack.getFunctionSize(quadAsst.getArg2().toString()) + ";\n";
                    
                } else if ((quadAsst.getOp() == Operation.PLUS || quadAsst.getOp() == Operation.MINUS) && quadAsst.getArg1().equals(Stack.P) && quadAsst.getArg2().getClass().equals(String.class)) {
                    return quadAsst.getResult() + " = " + Stack.P + " " + Operation.getIntOpOutput(quadAsst.getOp()) + " " + stack.getFunctionSize(quadAsst.getArg2().toString()) + ";\n";
                    
                } else {
                    return quadAsst.getResult() + "=" + quadAsst.getArg1() + Operation.getIntOpOutput(quadAsst.getOp()) + quadAsst.getArg2()+ ";\n";

                }
            } else if (quadAsst.getOp() <= Operation.EQUAL_BOOL) {
                return "if (" + quadAsst.getArg1() + " " + Operation.getIntOpOutput(quadAsst.getOp()) + " " + quadAsst.getArg2() + ") " + Operation.getIntOpOutput(Operation.GO_TO) + " " + quadAsst.getResult() + ";\n";
                
            } else if (quadAsst.getOp() == Operation.PRINT) {
                
                if (quadAsst.getResult().matches("t[0-9]+")) {
                    return Operation.getIntOpOutput(quadAsst.getOp()) + "f (\"%f\"," + quadAsst.getResult() + ");\n";
                }else{
                    return Operation.getIntOpOutput(quadAsst.getOp()) + "f (" + quadAsst.getResult() + ");\n";
                }
                
                
            } else if (quadAsst.getOp() == Operation.CLRSCR) {
                return "clrscr();\n";
                
            } else if (quadAsst.getOp() == Operation.READ) {
                String scanf="scanf (";
                
                if (quadAsst.getResult() != null) {
                    scanf+="\"%f\",&("+quadAsst.getResult()+"));\n";
                    
                } else {
                    scanf+=");\n";
                    
                }
                return scanf;
            } else if (quadAsst.getOp() == Operation.GETCH) {
                
                if (quadAsst.getResult() != null) {
                    return quadAsst.getResult()+" = getchar();\n";
                    
                } else {
                   return "getchar();\n";
                }
            } else if (quadAsst.getOp() == Operation.ARRAY) {
                return  quadAsst.getResult() + "[ " + quadAsst.getArg1() + "];\n";
                
            } else if (quadAsst.getOp() == Operation.TEMP) {
                System.out.println("TEMPP "+quadAsst.getResult());
                return  quadAsst.getResult() + "();\n";
                
            } else if (quadAsst.getOp() == Operation.INCLUDE) {
                return "#Include <" + quadAsst.getResult() + ">\n";
                
            }else if (quadAsst.getOp() == Operation.FUNCTION) {
                if (quadAsst.getResult().equals("}")) {
                    return ";}\n";
                }else{
                    return  quadAsst.getResult()+"\n";
                }
                
                
            }
            
        }
        return "";

    }

    public void addCode(String tacCode, String block) {

    }

    public void addCode(String tacCode) {

    }

    public String getTac() {
        return tac;
    }

}
