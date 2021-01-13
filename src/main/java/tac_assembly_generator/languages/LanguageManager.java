/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tac_assembly_generator.languages;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import tac_assembly_generator.Assembly.QuadsToAssemblyManager;
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
    private boolean recentError = true;
    private TAC tac;
    private OptimizedManager optimizedManager;
    private boolean assemblyReady=false;

    public LanguageManager(FileMlg file) {
        this.file = file;
    }

    public Stack getStack() {
        return stack;
    }

    public void generateTAC(MainFrame mainframe) {

        try {
            assemblyReady=false;
            tac = new TAC(mainframe);
            TestManager tm = new TestManager(mainframe);
            TranslateControlerTAC controler = new TranslateControlerTAC(tac);
            stack = new Stack();
            controler.setStack(stack);
            tm.setStack(stack);
            MlgLexicAnalizer mlgLexicAnalizer = new MlgLexicAnalizer(new StringReader(file.getMlgText()));
            mlgLexicAnalizer.addTac(controler);
            SyntaxMlgAnalyzer sma = new SyntaxMlgAnalyzer(mlgLexicAnalizer);
            sma.setTestManager(tm);
            sma.setTranslateControlerTAC(controler);
            tm.setSma(sma);
            sma.parse();
            resultQuads = sma.resultQuads;
            if (!sma.getError() && resultQuads != null) {
                resultQuads.reduceQuads();
                resultQuads.setTac(tac);
                resultQuads.convertQuads();
                recentError = false;

            } else {
                recentError = true;
            }
            //error condition

        } catch (Exception ex) {
            Logger.getLogger(LanguageManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public OptimizedManager getOptimizedManager() {
        return optimizedManager;
    }

    public void generateOptimized(MainFrame mainframe) {
        if (recentError) {
            JOptionPane.showMessageDialog(mainframe, "No se puede optimizar ya que existen errores en el TAC", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            if (resultQuads == null) {
                generateTAC(mainframe);
                generateOptimized(mainframe);
            } else {
//                optimizedManager= new OptimizedManager(resultQuads);
//                resultQuadsOP=optimizedManager.optimize();
//                resultQuadsOP.setTac(tac);
//                
//                recentError=false;
//                mainframe.enableOptimizedHtml();
            }
        }
    }

    public void generateExitutable(MainFrame mainframe) {
        if (recentError) {
            JOptionPane.showMessageDialog(mainframe, "No se puede ejecutar ya que existen errores en el TAC", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            if (resultQuads == null) {
                System.out.println("GENER");
                if (!loopAsst) {
                    
                generateTAC(mainframe);
                generateExitutable(mainframe);
                loopAsst=true;
                }else{
                    loopAsst=false;
                     JOptionPane.showMessageDialog(mainframe, "No se puede ejecutar assembler", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                ExecutableTACGenerator etacg = new ExecutableTACGenerator(resultQuads, file.getName());
                etacg.createCFile();

                String comando = " gcc -lm Executable/" + file.getName().replace(".mlg", ".c") + " -o Executable/" + file.getName().replace(".mlg", "");
                
                Runtime rt = Runtime.getRuntime();
                try {
                    rt.exec(comando);
                    Thread.sleep(2000);
                    String comando2 = "gnome-terminal hold -e Executable/" + file.getName().replace(".mlg", "");
                    rt.exec(comando2);
                } catch (IOException ex) {
                    Logger.getLogger(LanguageManager.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(LanguageManager.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
    }
  private boolean loopAsst=false;  
        public void exeAssembly(MainFrame mainframe) {
       
            File file = new File("Executable/"+this.file.getName().replace(".mlg", ".asm"));
            if (file.exists()) {
                String comando = "nasm -f elf64 Executable/" + file.getName();
                
                String comando2 = "ld Executable/" + file.getName().replace(".asm", ".o")+" ";
                    String comando3 = "gnome-terminal hold -e Executable/a.out";
                Runtime rt = Runtime.getRuntime();
                try {
                    rt.exec(comando);
                    rt.exec(comando2);
                    Thread.sleep(500);
                    rt.exec(comando3);
                    
                } catch (IOException ex) {
                    Logger.getLogger(LanguageManager.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(LanguageManager.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                
            }else{
                if (!loopAsst) {
                    
                    if (resultQuads==null) {
                
                        generateTAC(mainframe);
                    }
                
                    generateAssembly(mainframe);
                loopAsst=true;
                
                exeAssembly(mainframe);
                }else{
                    loopAsst=false;
                     JOptionPane.showMessageDialog(mainframe, "No se puede ejecutar assembler", "Error", JOptionPane.ERROR_MESSAGE);
                }
                
            
        }
    }
    

    public void generateAssembly(MainFrame mainframe) {
        if (recentError) {
            JOptionPane.showMessageDialog(mainframe, "No se puede generar assembler ya que existen errores en el TAC", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            if (resultQuads == null) {
                generateTAC(mainframe);
                
            } else {
                for (int i = 0; i < resultQuads.getQuadruples().size(); i++) {
                    
                }
                QuadsToAssemblyManager quadsToAssemblyManager = new QuadsToAssemblyManager(resultQuads, file.getName());
                quadsToAssemblyManager.translate();
                mainframe.getAssemblyPannel().setText(quadsToAssemblyManager.getAssemblyObject().OutputVal());
                assemblyReady=true;
            }
        }
    }

}
