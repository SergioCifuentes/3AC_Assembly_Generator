/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tac_assembly_generator.TAC.quadruple;

import tac_assembly_generator.languages.semantic.Tuple;

/**
 *
 * @author sergio
 */
public class Quadruple {
    private Integer op;
    private Object arg1;
    private Object arg2;
    private String result;

    public Quadruple(Integer op, Object arg1, Object arg2, String result) {
        this.op = op;
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.result = result;
    }

    @Override
    public String toString() {
        return "Quadruple{" + "op=" + op + ", arg1=" + arg1 + ", arg2=" + arg2 + ", result=" + result + '}';
    }

    public Integer getOp() {
        return op;
    }

    public Object getArg1() {
        return arg1;
    }

    public Object getArg2() {
        return arg2;
    }

    public String getResult() {
        return result;
    }
    
     
    
    
}
