/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tac_assembly_generator.languages;


import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import tac_assembly_generator.TAC.TAC;
import tac_assembly_generator.TAC.TranslateControlerTAC;
import tac_assembly_generator.TAC.stack.Stack;
import tac_assembly_generator.languages.analyzers.lexical.MlgLexicAnalizer;
import tac_assembly_generator.languages.analyzers.syntax.SyntaxMlgAnalyzer;
import tac_assembly_generator.languages.semantic.verification.TestManager;
import tac_assembly_generator.ui.FileMlg;
import tac_assembly_generator.ui.MainFrame;

/**
 *
 * @author sergio
 */
public class LanguageManager {
    private FileMlg file;
    private Stack stack;

    public LanguageManager(FileMlg file) {
        this.file = file;
    }

    public Stack getStack() {
        return stack;
    }
    
    public void generateTAC(MainFrame mainframe){
        
        try {
            TAC tac = new TAC(mainframe);
            TestManager tm = new TestManager(mainframe);
            TranslateControlerTAC controler= new TranslateControlerTAC(tac);
            stack = new Stack();
            controler.setStack(stack);
            tm.setStack(stack);
            MlgLexicAnalizer mlgLexicAnalizer= new MlgLexicAnalizer(new StringReader(file.getMlgText()));
            mlgLexicAnalizer.addTac(controler);   
            SyntaxMlgAnalyzer sma= new SyntaxMlgAnalyzer(mlgLexicAnalizer);
            sma.setTestManager(tm);
            sma.setTranslateControlerTAC(controler);
            sma.parse();
            //error condition
            
            
        } catch (Exception ex) {
            Logger.getLogger(LanguageManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void generateOptimized(MainFrame mainframe){
        
    }
    
    public void generateAssembly(MainFrame mainframe){
        
    }
    
}
