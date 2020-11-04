/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tac_assembly_generator.languages;


import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import tac_assembly_generator.TAC.TAC;
import tac_assembly_generator.TAC.TranslateControlerTAC;
import tac_assembly_generator.TAC.executable.ExecutableTACGenerator;
import tac_assembly_generator.TAC.stack.Stack;
import tac_assembly_generator.languages.analyzers.lexical.MlgLexicAnalizer;
import tac_assembly_generator.languages.analyzers.syntax.SyntaxMlgAnalyzer;
import tac_assembly_generator.languages.semantic.verification.TestManager;
import tac_assembly_generator.optimized.OptimizedManager;
import tac_assembly_generator.ui.FileMlg;
import tac_assembly_generator.ui.MainFrame;

/**
 *
 * @author sergio
 */
public class LanguageManager {
    private FileMlg file;
    private Stack stack;
    private ResultQuads resultQuads;
    private ResultQuads resultQuadsOP;
    private boolean recentError=true;
    private TAC tac;
    private OptimizedManager optimizedManager;

    public LanguageManager(FileMlg file) {
        this.file = file;
    }

    public Stack getStack() {
        return stack;
    }
    
    public void generateTAC(MainFrame mainframe){
        
        try {
            tac = new TAC(mainframe);
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
            if (!sma.getError()) {
                resultQuads=sma.resultQuads;
                resultQuads.setTac(tac);
                resultQuads.convertQuads();
                recentError=false;
                
            }else{
                recentError=true;
            }
            //error condition
            
            
        } catch (Exception ex) {
            Logger.getLogger(LanguageManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public OptimizedManager getOptimizedManager() {
        return optimizedManager;
    }
    
        
    
    
    public void generateOptimized(MainFrame mainframe){
        if (recentError) {
            JOptionPane.showMessageDialog(mainframe, "No se puede optimizar ya que existen errores en el TAC", "Error", JOptionPane.ERROR_MESSAGE);
        }else{
            if (resultQuads==null) {
                generateTAC(mainframe);
                generateOptimized(mainframe);
            }else{
//                optimizedManager= new OptimizedManager(resultQuads);
//                resultQuadsOP=optimizedManager.optimize();
//                resultQuadsOP.setTac(tac);
//                
//                recentError=false;
//                mainframe.enableOptimizedHtml();
            }
        }
    }
        public void generateExitutable(MainFrame mainframe){
        if (recentError) {
            JOptionPane.showMessageDialog(mainframe, "No se puede optimizar ya que existen errores en el TAC", "Error", JOptionPane.ERROR_MESSAGE);
        }else{
            if (resultQuads==null) {
                generateTAC(mainframe);
                generateOptimized(mainframe);
            }else{
                ExecutableTACGenerator etacg= new ExecutableTACGenerator(resultQuads,file.getName());
                etacg.createCFile();
            }
        }
    }
    
    
    public void generateAssembly(MainFrame mainframe){
        
    }
    
}
