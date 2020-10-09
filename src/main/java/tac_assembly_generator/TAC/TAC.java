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



    public void translateQuads(ArrayList<Object> quads) {

        for (int i = 0; i < quads.size(); i++) {
            translateQuad(quads.get(i));

        }

    }

    public String translateQuad(Object quad) {

        if (quad.getClass().equals(String.class)) {
            if (!((String) quad).endsWith("\n")) {
                OutputText.appendToPane(mainFrame.getTACPannel(), (String) quad + "\n", Color.lightGray, false);
            } else {
                OutputText.appendToPane(mainFrame.getTACPannel(), (String) quad, Color.lightGray, false);
            }

        } else if (quad.getClass().equals(Quadruple.class)) {
             
            Quadruple quadAsst = (Quadruple) quad;
            if (quadAsst.getOp() == null) {
                OutputText.appendToPane(mainFrame.getTACPannel(), quadAsst.getResult() + ":\n", Color.blue, false);
            } else if (quadAsst.getOp().equals(Operation.GO_TO)) {
                    OutputText.appendToPane(mainFrame.getTACPannel(),Operation.getIntOpOutput(quadAsst.getOp())+" "+ quadAsst.getResult() + "\n", Color.white, false);
            }else if (quadAsst.getOp() ==Operation.EQUAL) {
                 String tacQuad = quadAsst.getResult() + Operation.getIntOpOutput(quadAsst.getOp())  + quadAsst.getArg1() ;
                 OutputText.appendToPane(mainFrame.getTACPannel(), tacQuad + "\n", Color.white, false);
            } else if (quadAsst.getOp() <= Operation.MINUS) {
                 String tacQuad = quadAsst.getResult() + "=" + quadAsst.getArg1() + Operation.getIntOpOutput(quadAsst.getOp()) + quadAsst.getArg2();
                 OutputText.appendToPane(mainFrame.getTACPannel(), tacQuad + "\n", Color.white, false);
            } else if (quadAsst.getOp() <= Operation.EQUAL_BOOL) {
                 OutputText.appendToPane(mainFrame.getTACPannel(),"If " +quadAsst.getArg1()+" "+Operation.getIntOpOutput(quadAsst.getOp())+" "+quadAsst.getArg2()+" "+Operation.getIntOpOutput(Operation.GO_TO) +" "+quadAsst.getResult()+"\n", Color.white, false);
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
