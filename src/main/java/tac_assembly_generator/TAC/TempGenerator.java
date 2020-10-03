/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tac_assembly_generator.TAC;

/**
 *
 * @author sergio
 */
public class TempGenerator {
    private static final int START_POINT=0;
    private static final String PREFIX="t";
    private int currentTemp;

    public TempGenerator() {
        currentTemp= START_POINT;
    }
    public String generate(){
        if (currentTemp==START_POINT) return PREFIX+currentTemp;
        currentTemp++;
        return PREFIX+currentTemp;
    }
    
    
}
