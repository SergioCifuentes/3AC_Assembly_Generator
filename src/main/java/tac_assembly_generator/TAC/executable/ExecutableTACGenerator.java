/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tac_assembly_generator.TAC.executable;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import tac_assembly_generator.TAC.stack.Stack;
import tac_assembly_generator.languages.ResultQuads;
import tac_assembly_generator.optimized.HtmlCreator;

/**
 *
 * @author sergio
 */
public class ExecutableTACGenerator {

    private ResultQuads resultQuads;
    private final static String DIRECTORY_NAME = "Executable";
    private String filename;
    public ExecutableTACGenerator(ResultQuads resultQuads, String fileName) {
        this.resultQuads = resultQuads;
        this.filename=fileName;

    }

    public void createCFile() {
        File folder = new File("./" + DIRECTORY_NAME);
        String output = "";
        if (!folder.exists() || !folder.isDirectory()) {
            folder.mkdir();
            folder.mkdirs();
            try {
                folder.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(HtmlCreator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        File file = new File(folder.getPath() + "/" + filename.replace(".mlg", ".c"));
        System.out.println(file.getPath());
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(HtmlCreator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        for (int i = 0; i < resultQuads.getLiberies().size(); i++) {
            output+="#include <"+resultQuads.getLiberies().get(i)+">\n";
        }
        
        output+="float[1000] "+Stack.STACK_NAME+";\n";
        output+="float[1000] "+Stack.HEAP_NAME+";\n";
        output+="int "+Stack.P+";\n";
        output+="int "+Stack.H+";\n";
        output=resultQuads.getTempGenerator().addTempDeclarations(output);
        try {
            FileWriter fw = new FileWriter(file);
            fw.write(output);
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(ExecutableTACGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        

    }

}
