/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tac_assembly_generator.Assembly;

import java.util.ArrayList;
import tac_assembly_generator.TAC.TempGenerator;

/**
 *
 * @author sergio
 */
public class DataSection {

    private static final String NAME = "section .data";
    private static final String DB = "db";
    private static final String EQU = "equ $-";
    private static final String P = "\tp db 0";
    private static final String H = "\th db 0";

    private ArrayList<String> lines;
    private ArrayList<String> tempLines;
    private LabelGenerator lb;

    public DataSection() {
        lines = new ArrayList<>();
        lb = new LabelGenerator();
        lines.add(P);
        lines.add(H);

    }

    @Override
    public String toString() {
        String s = NAME + "\n";
        for (int i = 0; i < lines.size(); i++) {
            s += lines.get(i) + "\n";
        }
        return s;

    }

    public void addTemp(TempGenerator temp) {

        tempLines.add("\t");
    }

    public String createDB(String text) {
        String label = lb.generateLabelText();
        text = text.replace("\"", "\'");
        lines.add("\t" + label + ": " + DB + " " + text + ",0");

        return label;
    }

}
