/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tac_assembly_generator.TAC;

import java.util.ArrayList;

/**
 *
 * @author sergio
 */
public class TempGenerator {
    private static final int START_POINT=0;
    private static final String PREFIX="t";
    private int currentTemp;
    private ArrayList<Integer> recentyRemoves;

    public TempGenerator() {
        currentTemp= START_POINT;
        recentyRemoves= new ArrayList<>();
    }
    public void remove(String temp){
        int value = Integer.parseInt(temp.replace(PREFIX,""));
        recentyRemoves.add(value);
    }
    
    public String generate(){
        if (currentTemp==START_POINT){
            currentTemp++;
        return PREFIX+ currentTemp;
        } 
        if (!recentyRemoves.isEmpty()) {
            int minor=recentyRemoves.get(0);
            for (int i = 1; i < recentyRemoves.size(); i++) {
                if (recentyRemoves.get(i)<minor) {
                    minor=recentyRemoves.get(i);
                }
            }
            recentyRemoves.remove((Object)minor);
            return PREFIX+minor;
        }
        currentTemp++;
        return PREFIX+currentTemp;
    }
    
    
}
