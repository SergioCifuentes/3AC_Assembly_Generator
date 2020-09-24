/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tac_assembly_generator.languages;


import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import tac_assembly_generator.languages.analyzers.lexical.MlgLexicAnalizer;
import tac_assembly_generator.languages.analyzers.syntax.SyntaxMlgAnalyzer;
import tac_assembly_generator.ui.FileMlg;
import tac_assembly_generator.ui.MainFrame;

/**
 *
 * @author sergio
 */
public class LanguageManager {
    private FileMlg file;

    public LanguageManager(FileMlg file) {
        this.file = file;
    }
    
    public void generateTAC(MainFrame mainframe){
        
        try {
            MlgLexicAnalizer mlgLexicAnalizer= new MlgLexicAnalizer(new StringReader(file.getMlgText()));
            SyntaxMlgAnalyzer sma= new SyntaxMlgAnalyzer(mlgLexicAnalizer);
            sma.parse();
        } catch (Exception ex) {
            Logger.getLogger(LanguageManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void generateOptimized(MainFrame mainframe){
        
    }
    
    public void generateAssembly(MainFrame mainframe){
        
    }
    
}
