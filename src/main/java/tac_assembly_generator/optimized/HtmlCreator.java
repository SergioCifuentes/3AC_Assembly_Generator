/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tac_assembly_generator.optimized;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sergio
 */
public class HtmlCreator {
    private static final String FILE_NAME="Optimizacion.html";
    private static final String FOLDER_NAME="Reports";
    public void createHtml(ArrayList<Object> original,ArrayList<Object> optimized,ArrayList<Integer> changedLines){
        File folder = new File("./"+FOLDER_NAME);
        String output="";
        if (!folder.exists()||!folder.isDirectory()) {
            folder.mkdir();
            folder.mkdirs();
            try {
                folder.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(HtmlCreator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        
        File file= new File(folder.getParent()+"/"+FILE_NAME);
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
            Logger.getLogger(HtmlCreator.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
    }
    
}
