/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tac_assembly_generator.ui.backend;

import java.awt.Color;
import java_cup.runtime.Symbol;
import javax.swing.JTextPane;

/**
 *
 * @author sergio
 */
public class OutputErrors {

    public static void alreadyDeclaredParameter(JTextPane textPane, String id, Symbol symbol) {
        OutputText.appendToPane(textPane, "SEMANTIC ERROR:\n", Color.red, false);
        OutputText.appendToPane(textPane, "\t Parametro: " + id + " ya ha sido declarado \n", Color.white, false);
        OutputText.appendToPane(textPane, "\t Fila: ", Color.white, false);
        OutputText.appendToPane(textPane, (symbol.right + 1) + "\n", Color.YELLOW, false);
        OutputText.appendToPane(textPane, "\t Columna: ", Color.white, false);
        OutputText.appendToPane(textPane, (symbol.left + 1) + "\n", Color.YELLOW, false);
    }
     public static void alreadyDeclaredCase(JTextPane textPane, String id, Symbol symbol) {
        OutputText.appendToPane(textPane, "ERROR SEMANTICO (UNICIDAD):\n", Color.red, false);
        OutputText.appendToPane(textPane, "\t Case : " + id + " ya existe \n", Color.white, false);
        OutputText.appendToPane(textPane, "\t Fila: ", Color.white, false);
        OutputText.appendToPane(textPane, (symbol.right + 1) + "\n", Color.YELLOW, false);
        OutputText.appendToPane(textPane, "\t Columna: ", Color.white, false);
        OutputText.appendToPane(textPane, (symbol.left + 1) + "\n", Color.YELLOW, false);
    }

    public static void typeOpError(JTextPane textPane, String type1, String type2, Symbol symbol) {
        OutputText.appendToPane(textPane, "SEMANTIC ERROR:\n", Color.red, false);
        OutputText.appendToPane(textPane, "\t Tipos " + type1 + " & " + type2 + " NO pueden ser operados\n", Color.white, false);
        OutputText.appendToPane(textPane, "\t Fila: ", Color.white, false);
        OutputText.appendToPane(textPane, (symbol.right + 1) + "\n", Color.YELLOW, false);
        OutputText.appendToPane(textPane, "\t Columna: ", Color.white, false);
        OutputText.appendToPane(textPane, (symbol.left + 1) + "\n", Color.YELLOW, false);
    }

    public static void typeOpBoolError(JTextPane textPane, String type1, String type2, Symbol symbol) {
        OutputText.appendToPane(textPane, "SEMANTIC ERROR:\n", Color.red, false);
        OutputText.appendToPane(textPane, "\t Tipos " + type1 + " & " + type2 + " NO pueden ser Comparados En este lenguaje\n", Color.white, false);
        OutputText.appendToPane(textPane, "\t Fila: ", Color.white, false);
        OutputText.appendToPane(textPane, (symbol.right + 1) + "\n", Color.YELLOW, false);
        OutputText.appendToPane(textPane, "\t Columna: ", Color.white, false);
        OutputText.appendToPane(textPane, (symbol.left + 1) + "\n", Color.YELLOW, false);
    }
    
        public static void typeNotNumeric(JTextPane textPane, String id,String type1, Symbol symbol) {
        OutputText.appendToPane(textPane, "ERROR SEMANTICO (Tipo):\n", Color.red, false);
        OutputText.appendToPane(textPane, "\t Var: " + id + " de tipo: "+ type1 + " NO es un tipo Numerico\n", Color.white, false);
        OutputText.appendToPane(textPane, "\t Fila: ", Color.white, false);
        OutputText.appendToPane(textPane, (symbol.right + 1) + "\n", Color.YELLOW, false);
        OutputText.appendToPane(textPane, "\t Columna: ", Color.white, false);
        OutputText.appendToPane(textPane, (symbol.left + 1) + "\n", Color.YELLOW, false);
    }
    
    
}
