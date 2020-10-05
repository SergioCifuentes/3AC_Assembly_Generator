/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tac_assembly_generator.TAC.quadruple;

/**
 *
 * @author sergio
 */
public class Operation {
    public static final int EQUAL=1;
    public static final int MULTIPLICATION=2;
    public static final int DIVISION=3;
    public static final int MOD=4;
    public static final int PLUS=5;
    public static final int MINUS=6;
    
    public static String getIntOpOutput(int op){
        switch (op) {
            case EQUAL:
                return "=";
            case MULTIPLICATION:
                return "=";
            case DIVISION:
                return "=";
            case MOD:
                return "=";
            case PLUS:
                return "=";
            case MINUS:
                return "="; 
            default:
                throw new AssertionError();
        }
    }
}
