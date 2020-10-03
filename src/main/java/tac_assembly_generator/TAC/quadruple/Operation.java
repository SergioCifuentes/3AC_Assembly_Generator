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
    
    public static String getIntOpOutput(int op){
        switch (op) {
            case EQUAL:
                return "=";
                
            default:
                throw new AssertionError();
        }
    }
}
