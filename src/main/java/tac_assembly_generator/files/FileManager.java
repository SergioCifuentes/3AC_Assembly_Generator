/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tac_assembly_generator.files;

import tac_assembly_generator.ui.FileMlg;
import tac_assembly_generator.ui.MainFrame;
import tac_assembly_generator.ui.NameChooser;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import tac_assembly_generator.TAC.TAC;

/**
 *
 * @author sergio
 */
public class FileManager {

    private ArrayList<FileMlg> files;

    public FileManager() {
        this.files = new ArrayList<>();
    }

    public void addFileMlg(FileMlg fileMlg) {
        files.add(fileMlg);
        if (fileMlg.getFile() != null && !fileMlg.getFile().exists()) {
            createFile(fileMlg);
        }
    }

    public void createFile(FileMlg file) {
        try {
            file.getFile().createNewFile();
        } catch (IOException ex) {
            System.out.println("Error Creating File");
        }
    }

    public ArrayList<FileMlg> getFiles() {
        return files;
    }
    
    public JTextPane getTextPane(int index){
        return files.get(index).getOptText();
    }

    public void openFile(MainFrame frame) {
        JFileChooser jfc = new JFileChooser();
        jfc.setDialogTitle("Select The File Location");

        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jfc.showOpenDialog(frame);
        File file = jfc.getSelectedFile();
        if (file != null) {
            if (file.getPath().endsWith(NameChooser.MLG_EXTENSION)) {
                FileMlg newMlg = new FileMlg(file.getName(), file);
                newMlg.addMlgText(readFile(file));
                frame.addFileMlg(newMlg);

            } else {
                JOptionPane.showMessageDialog(frame, "Wrong Extension, must be .mlg", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public String readFile(File file) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        String text = "";
        String st;
        try {
            while ((st = br.readLine()) != null) {
                text += st + "\n";
            }
            if (text.endsWith("\n")) {
                text = text.substring(0, text.length() - 1);
            }
        } catch (IOException ex1) {
            System.out.println("Error while reading file");
        }
        return text;
    }

    public void saveFile(int selectedIndex, MainFrame mf) {
        FileMlg fileMlgToSave = files.get(selectedIndex);
        if (fileMlgToSave.getFile() != null) {
            try {
                FileWriter fw = new FileWriter(fileMlgToSave.getFile());
                fw.write(fileMlgToSave.getMlgText());
                fw.close();
                JOptionPane.showMessageDialog(mf, "Saved Succesfuly ", "Information", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                System.out.println("Error Saving file");
            }
        } else {
            while (true) {
                JFileChooser jfc = new JFileChooser();
                jfc.setDialogTitle("Select Location to Save: " + fileMlgToSave.getName());
                jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                jfc.showOpenDialog(mf);
                File file = jfc.getSelectedFile();
                if (file != null) {
                    File toSave = new File(file.getPath() + "/" + fileMlgToSave.getName());
                    if (toSave.exists()) {
                        JOptionPane.showMessageDialog(mf, "File Already Exists", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        fileMlgToSave.setFile(toSave);
                        saveFile(selectedIndex, mf);
                        break;
                    }
                } else {
                    break;
                }
            }

        }
    }

    public void saveAsFile(int selectedIndex, MainFrame mf) {
        FileMlg fileMlgToSave = files.get(selectedIndex);
        while (true) {
            JFileChooser jfc = new JFileChooser();
            jfc.setDialogTitle("Select Location to Save: " + fileMlgToSave.getName());
            jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            jfc.showOpenDialog(mf);
            File file = jfc.getSelectedFile();
            if (file != null) {
                File toSave = new File(file.getPath() + "/" + fileMlgToSave.getName());
                if (toSave.exists()) {
                    JOptionPane.showMessageDialog(mf, "File Already Exists", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    fileMlgToSave.setFile(toSave);
                    saveFile(selectedIndex, mf);
                    break;
                }
            } else {
                break;
            }
        }
    }

    public void openStarterFile(MainFrame frame) {
        File file = new File("/home/sergio/AA/eje.mlg");
        FileMlg newMlg = new FileMlg(file.getName(), file);
        newMlg.addMlgText(readFile(file));
        frame.addFileMlg(newMlg);

    }

    public void showTAC(int selectedIndex, TAC tac) {
        files.get(selectedIndex).showTAC(tac);
    }
}
