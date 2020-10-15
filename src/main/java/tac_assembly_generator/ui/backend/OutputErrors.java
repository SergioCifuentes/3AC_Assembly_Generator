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

    public static void typeOpError(JTextPane textPane, String type1, String type2, String id, Symbol symbol) {
        OutputText.appendToPane(textPane, "SEMANTIC ERROR:\n", Color.red, false);
        OutputText.appendToPane(textPane, "\t No se puede asignar un " + type2 + " a " + id + " que es un " + type1 + " \n", Color.white, false);
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

    public static void typeNotNumeric(JTextPane textPane, String id, String type1, Symbol symbol) {
        OutputText.appendToPane(textPane, "ERROR SEMANTICO (Tipo):\n", Color.red, false);
        OutputText.appendToPane(textPane, "\t Var: " + id + " de tipo: " + type1 + " NO es un tipo Numerico\n", Color.white, false);
        OutputText.appendToPane(textPane, "\t Fila: ", Color.white, false);
        OutputText.appendToPane(textPane, (symbol.right + 1) + "\n", Color.YELLOW, false);
        OutputText.appendToPane(textPane, "\t Columna: ", Color.white, false);
        OutputText.appendToPane(textPane, (symbol.left + 1) + "\n", Color.YELLOW, false);
    }

    public static void notDeclared(JTextPane textPane, String id, Symbol symbol) {
        OutputText.appendToPane(textPane, "SEMANTIC ERROR:\n", Color.red, false);
        OutputText.appendToPane(textPane, "\t" + id + " no ha sido declarado \n", Color.white, false);
        OutputText.appendToPane(textPane, "\t Fila: ", Color.white, false);
        OutputText.appendToPane(textPane, (symbol.right + 1) + "\n", Color.YELLOW, false);
        OutputText.appendToPane(textPane, "\t Columna: ", Color.white, false);
        OutputText.appendToPane(textPane, (symbol.left + 1) + "\n", Color.YELLOW, false);
    }

    public static void declaredOutOfAmbit(JTextPane textPane, String id, Symbol symbol) {
        OutputText.appendToPane(textPane, "SEMANTIC ERROR:\n", Color.red, false);
        OutputText.appendToPane(textPane, "\t" + id + " esta fuera de ambito \n", Color.white, false);
        OutputText.appendToPane(textPane, "\t Fila: ", Color.white, false);
        OutputText.appendToPane(textPane, (symbol.right + 1) + "\n", Color.YELLOW, false);
        OutputText.appendToPane(textPane, "\t Columna: ", Color.white, false);
        OutputText.appendToPane(textPane, (symbol.left + 1) + "\n", Color.YELLOW, false);
    }

    public static void syntacticRecovered(JTextPane textPane, String lexema, Integer column, Integer row, Symbol symbol) {
        OutputText.appendToPane(textPane, "Error Sintactico Recuperado\n", Color.red, false);
        OutputText.appendToPane(textPane, "\t Lexema: " + lexema + "\n", Color.white, false);
        OutputText.appendToPane(textPane, "\t Fila: ", Color.white, false);
        OutputText.appendToPane(textPane, (row + 1) + "\n", Color.YELLOW, false);
        OutputText.appendToPane(textPane, "\t Columna: ", Color.white, false);
        OutputText.appendToPane(textPane, (column + 1) + "\n", Color.YELLOW, false);
    }

    public static void syntacticNotRecovered(JTextPane textPane, String lexema, Integer column, Integer row, Symbol symbol) {
        OutputText.appendToPane(textPane, "Error Sintactico No Recuperable\n", Color.red, false);
        OutputText.appendToPane(textPane, "\t Lexema: " + lexema + "\n", Color.white, false);
        OutputText.appendToPane(textPane, "\t Fila: ", Color.white, false);
        OutputText.appendToPane(textPane, (row + 1) + "\n", Color.YELLOW, false);
        OutputText.appendToPane(textPane, "\t Columna: ", Color.white, false);
        OutputText.appendToPane(textPane, (column + 1) + "\n", Color.YELLOW, false);
    }

    public static void alreadyDeclared(JTextPane textPane, String id, Symbol symbol) {
        OutputText.appendToPane(textPane, "SEMANTIC ERROR:\n", Color.red, false);
        OutputText.appendToPane(textPane, "\t" + id + " ya ha sido declarado \n", Color.white, false);
        OutputText.appendToPane(textPane, "\t Fila: ", Color.white, false);
        OutputText.appendToPane(textPane, (symbol.right + 1) + "\n", Color.YELLOW, false);
        OutputText.appendToPane(textPane, "\t Columna: ", Color.white, false);
        OutputText.appendToPane(textPane, (symbol.left + 1) + "\n", Color.YELLOW, false);
    }

    public static void assignmentError(JTextPane textPane, String type1, String type2, Symbol symbol) {
        OutputText.appendToPane(textPane, "SEMANTIC ERROR:\n", Color.red, false);
        OutputText.appendToPane(textPane, "\t No se puede asignar un valor tipo " + type1 + " a una variable tipo " + type2 + "\n", Color.white, false);
        OutputText.appendToPane(textPane, "\t Fila: ", Color.white, false);
        OutputText.appendToPane(textPane, (symbol.right + 1) + "\n", Color.YELLOW, false);
        OutputText.appendToPane(textPane, "\t Columna: ", Color.white, false);
        OutputText.appendToPane(textPane, (symbol.left + 1) + "\n", Color.YELLOW, false);
    }

    public static void alreadyDeclaredFunctions(JTextPane textPane, String id, Symbol symbol) {
        OutputText.appendToPane(textPane, "SEMANTIC ERROR:\n", Color.red, false);
        OutputText.appendToPane(textPane, "\tFuncion: " + id + " ya ha sido declarado \n", Color.white, false);
        OutputText.appendToPane(textPane, "\t Fila: ", Color.white, false);
        OutputText.appendToPane(textPane, (symbol.right + 1) + "\n", Color.YELLOW, false);
        OutputText.appendToPane(textPane, "\t Columna: ", Color.white, false);
        OutputText.appendToPane(textPane, (symbol.left + 1) + "\n", Color.YELLOW, false);
    }

    public static void multipleMasks(JTextPane textPane, String id, Symbol symbol) {
        OutputText.appendToPane(textPane, "SEMANTIC ERROR:\n", Color.red, false);
        OutputText.appendToPane(textPane, "\tEl input para " + id + " contiene muchas mascaras \n", Color.white, false);
        OutputText.appendToPane(textPane, "\t Fila: ", Color.white, false);
        OutputText.appendToPane(textPane, (symbol.right + 1) + "\n", Color.YELLOW, false);
        OutputText.appendToPane(textPane, "\t Columna: ", Color.white, false);
        OutputText.appendToPane(textPane, (symbol.left + 1) + "\n", Color.YELLOW, false);
    }

    public static void noMasks(JTextPane textPane, String id, Symbol symbol) {
        OutputText.appendToPane(textPane, "SEMANTIC ERROR:\n", Color.red, false);
        OutputText.appendToPane(textPane, "\tEl input para " + id + " no contiene mascara \n", Color.white, false);
        OutputText.appendToPane(textPane, "\t Fila: ", Color.white, false);
        OutputText.appendToPane(textPane, (symbol.right + 1) + "\n", Color.YELLOW, false);
        OutputText.appendToPane(textPane, "\t Columna: ", Color.white, false);
        OutputText.appendToPane(textPane, (symbol.left + 1) + "\n", Color.YELLOW, false);
    }

    public static void notIntegerType(JTextPane textPane, String outputType, Symbol symbol) {
        OutputText.appendToPane(textPane, "SEMANTIC ERROR:\n", Color.red, false);
        OutputText.appendToPane(textPane, "\tEl tipo " + outputType + " dede ser de tipo int \n", Color.white, false);
        OutputText.appendToPane(textPane, "\t Fila: ", Color.white, false);
        OutputText.appendToPane(textPane, (symbol.right + 1) + "\n", Color.YELLOW, false);
        OutputText.appendToPane(textPane, "\t Columna: ", Color.white, false);
        OutputText.appendToPane(textPane, (symbol.left + 1) + "\n", Color.YELLOW, false);
    }

    public static void notArray(JTextPane textPane, String id, Symbol symbol) {
        OutputText.appendToPane(textPane, "SEMANTIC ERROR:\n", Color.red, false);
        OutputText.appendToPane(textPane, "\tvariable " + id + " no es un arreglo \n", Color.white, false);
        OutputText.appendToPane(textPane, "\t Fila: ", Color.white, false);
        OutputText.appendToPane(textPane, (symbol.right + 1) + "\n", Color.YELLOW, false);
        OutputText.appendToPane(textPane, "\t Columna: ", Color.white, false);
        OutputText.appendToPane(textPane, (symbol.left + 1) + "\n", Color.YELLOW, false);
    }

    public static void arrayOutOfBounds(JTextPane textPane, String id, int size, int size0, Symbol symbol) {
        OutputText.appendToPane(textPane, "SEMANTIC ERROR:\n", Color.red, false);
        OutputText.appendToPane(textPane, "\t Arreglo " + id + " es de " + size + " dimension(s) no de " + size0 + "dimension(s)\n", Color.white, false);
        OutputText.appendToPane(textPane, "\t Fila: ", Color.white, false);
        OutputText.appendToPane(textPane, (symbol.right + 1) + "\n", Color.YELLOW, false);
        OutputText.appendToPane(textPane, "\t Columna: ", Color.white, false);
        OutputText.appendToPane(textPane, (symbol.left + 1) + "\n", Color.YELLOW, false);
    }

    public static void wrongImport(JTextPane textPane, String functions, String language, Symbol symbol) {
        OutputText.appendToPane(textPane, "SEMANTIC ERROR:\n", Color.red, false);
        OutputText.appendToPane(textPane, "\t Funcion " + functions + " no existe en el lenguaje " + language + "\n", Color.white, false);
        OutputText.appendToPane(textPane, "\t Fila: ", Color.white, false);
        OutputText.appendToPane(textPane, (symbol.right + 1) + "\n", Color.YELLOW, false);
        OutputText.appendToPane(textPane, "\t Columna: ", Color.white, false);
        OutputText.appendToPane(textPane, (symbol.left + 1) + "\n", Color.YELLOW, false);
    }

    public static void functionNotFound(JTextPane textPane, String id, Symbol symbol) {
        OutputText.appendToPane(textPane, "SEMANTIC ERROR:\n", Color.red, false);
        OutputText.appendToPane(textPane, "\tFuncion " + id + " no se encuentra o no cuenta con ese numero de parametros \n", Color.white, false);
        OutputText.appendToPane(textPane, "\t Fila: ", Color.white, false);
        OutputText.appendToPane(textPane, (symbol.right + 1) + "\n", Color.YELLOW, false);
        OutputText.appendToPane(textPane, "\t Columna: ", Color.white, false);
        OutputText.appendToPane(textPane, (symbol.left + 1) + "\n", Color.YELLOW, false);
    }
    
        public static void syntaxFunctionVB(JTextPane textPane, Symbol symbol) {
        OutputText.appendToPane(textPane, "SYNTAX ERROR:\n", Color.red, false);
        OutputText.appendToPane(textPane, "\tPara Vb solo se permiten funcciones y subs eje: function id(parametros)\n\t\t ...\n\tEND Function\n", Color.white, false);
        OutputText.appendToPane(textPane, "\t Fila: ", Color.white, false);
        OutputText.appendToPane(textPane, (symbol.right + 1) + "\n", Color.YELLOW, false);
        OutputText.appendToPane(textPane, "\t Columna: ", Color.white, false);
        OutputText.appendToPane(textPane, (symbol.left + 1) + "\n", Color.YELLOW, false);
    }

}
