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

    public static final int EQUAL = 1;
    public static final int MULTIPLICATION = 2;
    public static final int DIVISION = 3;
    public static final int MOD = 4;
    public static final int PLUS = 5;
    public static final int MINUS = 6;
    public static final int DIFERENT = 7;
    public static final int GREATER_THAN = 8;
    public static final int LESS_THAN = 9;
    public static final int GREATER_THAN_EQUAL = 10;
    public static final int LESS_THAN_EQUAL = 11;
    public static final int EQUAL_BOOL = 12;
    public static final Integer OP_AND = 13;
    public static final Integer OP_OR = 14;
    public static final Integer OP_NOT = 15;
    public static final int GO_TO = 16;
    public static final int CASE = 17;
    public static final int PRINT = 18;
    public static final int CLRSCR = 19;
    public static final int READ = 20;
    public static final int GETCH = 21;
    public static final int ARRAY = 22;
    public static final int PARAMS = 23;
    public static final int TEMP = 24;
    public static final int INCLUDE = 25;
    public static final int FUNCTION = 26;

    public static String getIntOpOutput(int op) {
        switch (op) {
            case EQUAL:
                return "=";
            case MULTIPLICATION:
                return "*";
            case DIVISION:
                return "/";
            case MOD:
                return "%";
            case PLUS:
                return "+";
            case MINUS:
                return "-";
            case DIFERENT:
                return "<>";
            case GREATER_THAN:
                return ">";
            case LESS_THAN:
                return "<";
            case GREATER_THAN_EQUAL:
                return ">=";
            case LESS_THAN_EQUAL:
                return "<=";
            case EQUAL_BOOL:
                return "=";
            case GO_TO:
                return "goto";
            case PRINT:
                return "print";
            default:
                throw new AssertionError();
        }
    }
    
    
    public static Object operateInteger(Object ob1,Object ob2,Integer op){
        
        switch (op) {
            case PLUS:
                return (Integer)ob1+(Integer)ob2;
            case MINUS:
                return (Integer)ob1-(Integer)ob2;
            case MULTIPLICATION:
                return (Integer)ob1*(Integer)ob2;
            case DIVISION:
                return (Integer)ob1/(Integer)ob2;
            case MOD:
                return (Integer)ob1%(Integer)ob2;
            default:
                throw new AssertionError();
        }
    }
       public static Object operateFloat(Object ob1,Object ob2,Integer op){
        switch (op) {
            case PLUS:
                return (Float)ob1+(Float)ob2;
            case MINUS:
                return (Float)ob1-(Float)ob2;
            case MULTIPLICATION:
                return (Float)ob1*(Float)ob2;
            case DIVISION:
                return (Float)ob1/(Float)ob2;
            case MOD:
                return (Float)ob1%(Float)ob2;
            default:
                throw new AssertionError();
        }
    }
}
