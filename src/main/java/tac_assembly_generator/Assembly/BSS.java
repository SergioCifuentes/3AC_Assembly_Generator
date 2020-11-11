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
public class BSS {

    private static final String NAME = "section .bss";
    private ArrayList<String> lines;
    private static final String STACK = "\tstack times 1000 resb 0";
    private static final String HEAP = "\theap times 1000 resb 0";

    private static final String digitSpace = "\tdigitSpace resb 100";
    private static final String digitSpacePos = "\tdigitSpacePos resb 8";

    public BSS() {
        lines = new ArrayList<>();
        lines.add(STACK);
        lines.add(HEAP);
        lines.add(digitSpace);
        lines.add(digitSpacePos);
    }

    @Override
    public String toString() {
        String s = NAME + "\n";
        for (int i = 0; i < lines.size(); i++) {
            s += lines.get(i) + "\n";
        }
        return s;

    }
    public void addTemps(ArrayList<String> temp){
        
        for (int i = 0; i < temp.size(); i++) {
            lines.add("\t"+temp.get(i)+" resb 2");
        }
    }

}
