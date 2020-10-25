/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tac_assembly_generator.ui;

import tac_assembly_generator.languages.LanguageManager;
import tac_assembly_generator.ui.backend.LineNumber;
import java.awt.Color;
import java.io.File;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.plaf.BorderUIResource;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.InsetsUIResource;
import tac_assembly_generator.TAC.TAC;

/**
 *
 * @author sergio
 */
public class FileMlg extends javax.swing.JPanel {

    private File file;
    private String name;
    private LanguageManager languageManager;

    /**
     * Creates new form ArchivoMlg
     */
    public FileMlg(String name, File file) {
        uiManage();
        this.updateUI();
        this.file=file;
        this.name=name;
        initComponents();
        addLines();
        languageManager= new LanguageManager(this);

    }

    public JTextPane gettACText() {
        return tACText;
    }

    public JTextPane getOptText() {
        return outputText;
    }

    public JTextPane getOPText() {
        return optText;
    }

    public JTextPane getAssemblerText() {
        return assemblerText;
    }
    


    public LanguageManager getLanguageManager() {
        return languageManager;
    }

    public File getFile() {
        return file;
    }

    public String getName() {
        return name;
    }
    
    public  static void uiManage() {
        UIManager.put("TabbedPane.selected", Color.GRAY);
        UIManager.put("TabbedPane.contentBorderInsets", new InsetsUIResource(1, 0,0, 0));
        UIManager.put("TabbedPane.contentAreaColor", new ColorUIResource(Color.DARK_GRAY));
        UIManager.put("TabbedPane.focus", new ColorUIResource(Color.DARK_GRAY));
        UIManager.put("TabbedPane.darkShadow", new ColorUIResource(Color.DARK_GRAY));
        UIManager.put("TabbedPane.borderHightlightColor", new ColorUIResource(new Color(0,59,186)));
        UIManager.put("TabbedPane.light", new ColorUIResource(new Color(0,59,186)));
        UIManager.put("TabbedPane.tabAreaBackground", new ColorUIResource(Color.BLACK));
        UIManager.put("ToolTip.background", Color.WHITE);
        UIManager.put("ToolTip.border", new BorderUIResource(new LineBorder(Color.BLACK)));
        
    }

    private void addLines() {
        LineNumber lnMLG = new LineNumber(mlgText);
        mlgscroll.setRowHeaderView(lnMLG);
        LineNumber lnTAC = new LineNumber(tACText);
        tacScroll.setRowHeaderView(lnTAC);
        LineNumber lnOpt = new LineNumber(optText);
        optScroll.setRowHeaderView(lnOpt);
        LineNumber lnAssy = new LineNumber(assemblerText);
        assemblerScroll.setRowHeaderView(lnAssy);
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        optText1 = new javax.swing.JTextPane();
        mlgscroll = new javax.swing.JScrollPane();
        mlgText = new javax.swing.JTextPane();
        tabsGenerated = new javax.swing.JTabbedPane();
        tacScroll = new javax.swing.JScrollPane();
        tACText = new javax.swing.JTextPane();
        optScroll = new javax.swing.JScrollPane();
        optText = new javax.swing.JTextPane();
        assemblerScroll = new javax.swing.JScrollPane();
        assemblerText = new javax.swing.JTextPane();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        outputScroll = new javax.swing.JScrollPane();
        outputText = new javax.swing.JTextPane();

        optText1.setBackground(new java.awt.Color(32, 31, 31));
        optText1.setFont(new java.awt.Font("Monospaced", 0, 13)); // NOI18N
        optText1.setForeground(new java.awt.Color(255, 255, 255));

        setBackground(new java.awt.Color(0, 0, 0));

        mlgText.setBackground(new java.awt.Color(32, 31, 31));
        mlgText.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        mlgText.setForeground(new java.awt.Color(255, 255, 255));
        mlgText.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        mlgscroll.setViewportView(mlgText);

        tabsGenerated.setBackground(new java.awt.Color(0, 0, 0));
        tabsGenerated.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        tabsGenerated.setForeground(new java.awt.Color(255, 255, 255));
        tabsGenerated.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        tACText.setBackground(new java.awt.Color(32, 31, 31));
        tACText.setForeground(new java.awt.Color(255, 255, 255));
        tACText.setMaximumSize(new java.awt.Dimension(1000, 1000));
        tacScroll.setViewportView(tACText);

        tabsGenerated.addTab("3AC", tacScroll);

        optText.setBackground(new java.awt.Color(32, 31, 31));
        optText.setForeground(new java.awt.Color(255, 255, 255));
        optScroll.setViewportView(optText);

        tabsGenerated.addTab("Optimization", optScroll);

        assemblerText.setBackground(new java.awt.Color(32, 31, 31));
        assemblerText.setFont(new java.awt.Font("Monospaced", 0, 13)); // NOI18N
        assemblerText.setForeground(new java.awt.Color(255, 255, 255));
        assemblerScroll.setViewportView(assemblerText);

        tabsGenerated.addTab("Assembler", assemblerScroll);

        jTabbedPane2.setForeground(new java.awt.Color(255, 255, 255));
        jTabbedPane2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        outputText.setBackground(new java.awt.Color(0, 0, 0));
        outputText.setForeground(new java.awt.Color(255, 255, 255));
        outputText.setMaximumSize(new java.awt.Dimension(7, 21));
        outputScroll.setViewportView(outputText);

        jTabbedPane2.addTab("Output", outputScroll);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(mlgscroll, javax.swing.GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(tabsGenerated, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mlgscroll)
                    .addComponent(tabsGenerated, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

public void addMlgText(String mlgText){
    this.mlgText.setText(mlgText);
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane assemblerScroll;
    private javax.swing.JTextPane assemblerText;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTextPane mlgText;
    private javax.swing.JScrollPane mlgscroll;
    private javax.swing.JScrollPane optScroll;
    private javax.swing.JTextPane optText;
    private javax.swing.JTextPane optText1;
    private javax.swing.JScrollPane outputScroll;
    private javax.swing.JTextPane outputText;
    private javax.swing.JTextPane tACText;
    private javax.swing.JTabbedPane tabsGenerated;
    private javax.swing.JScrollPane tacScroll;
    // End of variables declaration//GEN-END:variables

    public String getMlgText() {
        return mlgText.getText();
    }

    public void setFile(File file) {
        this.file=file;
    }
    
    
}
