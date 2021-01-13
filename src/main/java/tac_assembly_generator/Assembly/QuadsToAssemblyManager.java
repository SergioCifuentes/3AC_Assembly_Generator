/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tac_assembly_generator.Assembly;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import tac_assembly_generator.TAC.quadruple.Operation;
import tac_assembly_generator.TAC.quadruple.Quadruple;
import tac_assembly_generator.languages.ResultQuads;
import tac_assembly_generator.optimized.HtmlCreator;

/**
 *
 * @author sergio
 */
public class QuadsToAssemblyManager {
    
    private ResultQuads resultQuads;
    private String fileName;
    private final static String DIRECTORY_NAME = "Executable";
    private AssemblyObject assemblyObject;
    private boolean currentMain;
    
    public QuadsToAssemblyManager(ResultQuads resultQuads, String fileName) {
        this.resultQuads = resultQuads;
        this.fileName = fileName;
        assemblyObject = new AssemblyObject();
        assemblyObject.declareTemps(resultQuads.getTempGenerator());
        currentMain = false;
        resultQuads.getStack().changeFunctionNames(resultQuads.getQuadruples());
    }

    public AssemblyObject getAssemblyObject() {
        return assemblyObject;
    }
    
    public void translate() {
        for (int i = 0; i < resultQuads.getQuadruples().size(); i++) {
            
            if (resultQuads.getQuadruples().get(i).getClass().equals(Quadruple.class)) {
                Quadruple quad = (Quadruple) resultQuads.getQuadruples().get(i);
                
                if (quad.getOp()==null) {
                    assemblyObject.getTextSection().openLable(quad.getResult());
                }else if (quad.getOp() == Operation.PRINT) {
                    if (((String) quad.getResult()).startsWith("\"")) {
                        String stringData = assemblyObject.getDataSection().createDB((String) quad.getResult());
                        assemblyObject.getTextSection().createPrint(stringData);
                    }else{
                        assemblyObject.getTextSection().createIntegerPrint((String)quad.getResult());
                    }
                } else if (quad.getOp() == Operation.FUNCTION) {
                    if (quad.getResult().equals("}")) {
                        if (currentMain) {
                            assemblyObject.getTextSection().closeStart();
                            currentMain = false;
                        } else {
                            assemblyObject.getTextSection().closeLabel();
                        }
                    } else if (quad.getResult().contains("main")) {
                        
                        assemblyObject.getTextSection().startLabel();
                        currentMain=true;
                    }else {
                        String function = quad.getResult().replace("(){","");
                        function=function.replace("void", "");
                        function=function.replace(" ", "");
                        assemblyObject.getTextSection().openLable(function);
                    }
                    
                }else if(quad.getOp() == Operation.GO_TO){
                    assemblyObject.getTextSection().addJump(quad.getResult());
                    
                }else if(quad.getOp() == Operation.EQUAL){
                    assemblyObject.getTextSection().addEqual(quad.getResult(),quad.getArg1().toString());
                    
                }else if(quad.getOp() <= Operation.MINUS&&quad.getOp() != Operation.MOD){
                    assemblyObject.getTextSection().addOp(quad.getArg2().toString(),quad.getArg1().toString(),quad.getOp(),quad.getResult());
                    
                }else if (quad.getOp()<=Operation.EQUAL_BOOL) {
                    
                    
                     assemblyObject.getTextSection().addCondicion(quad.getArg1().toString(),quad.getArg2().toString(),quad.getOp(),quad.getResult());
                }
                
            }
        }
        assemblyObject.getTextSection().addPrints();
        createAsmFile(assemblyObject.toString());
    }
    
    public void createAsmFile(String output) {
        File folder = new File("./" + DIRECTORY_NAME);
        
        if (!folder.exists() || !folder.isDirectory()) {
            folder.mkdir();
            folder.mkdirs();
            try {
                folder.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(HtmlCreator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        File file = new File(folder.getPath() + "/" + fileName.replace(".mlg", ".asm"));
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(HtmlCreator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            FileWriter fw = new FileWriter(file);
            fw.write(output);
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(QuadsToAssemblyManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
