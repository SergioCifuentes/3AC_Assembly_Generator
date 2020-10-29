/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tac_assembly_generator.TAC;

import java.util.ArrayList;
import tac_assembly_generator.TAC.quadruple.Operation;
import tac_assembly_generator.TAC.quadruple.Quadruple;

/**
 *
 * @author sergio
 */
public class TempGenerator {

    private static final int START_POINT = 0;
    private static final String PREFIX_TEMP = "t";
    private int currentTemp;
    private static final String PREFIX_TAG = "etv";
    private int currentTag;
    private ArrayList<Integer> recentyRemovedTemp;
    private ArrayList<Integer> recentyRemovedTag;

    public TempGenerator() {
        currentTemp = START_POINT;
        currentTag = START_POINT;
        recentyRemovedTemp = new ArrayList<>();
        recentyRemovedTag = new ArrayList<>();
    }

    public void removeTemp(String temp) {
        int value = Integer.parseInt(temp.replace(PREFIX_TEMP, ""));
        recentyRemovedTemp.add(value);
    }

    public String generateTemp() {
        if (currentTemp == START_POINT) {
            currentTemp++;
            return PREFIX_TEMP + currentTemp;
        }
        if (!recentyRemovedTemp.isEmpty()) {
            int minor = recentyRemovedTemp.get(0);
            for (int i = 1; i < recentyRemovedTemp.size(); i++) {
                if (recentyRemovedTemp.get(i) < minor) {
                    minor = recentyRemovedTemp.get(i);
                }
            }
            recentyRemovedTemp.remove((Object) minor);
            return PREFIX_TEMP + minor;
        }
        currentTemp++;
        return PREFIX_TEMP + currentTemp;
    }

    public void removeTag(String tag) {
        int value = Integer.parseInt(tag.replace(PREFIX_TAG, ""));
        recentyRemovedTag.add(value);
    }

    public String generateTag() {
        if (currentTag == START_POINT) {
            currentTag++;
            return PREFIX_TAG + currentTag;
        }
        if (!recentyRemovedTag.isEmpty()) {
            int minor = recentyRemovedTag.get(0);
            for (int i = 1; i < recentyRemovedTag.size(); i++) {
                if (recentyRemovedTag.get(i) < minor) {
                    minor = recentyRemovedTag.get(i);
                }
            }
            recentyRemovedTag.remove((Object) minor);   
            return PREFIX_TAG + minor;
        }
        currentTag++;
        return PREFIX_TAG + currentTag;
    }

    public void addTempDeclarations(ArrayList<Object> obs) {
        int asst=START_POINT+1;
        System.out.println(currentTemp);
        int aux=0;
        while (asst<currentTemp) {
            if (!recentyRemovedTemp.contains(obs)) {
                obs.add(aux,new Quadruple(Operation.TEMP, null, null, "float "+PREFIX_TEMP+asst));
                aux++;
            }
            
            asst++;
        }
    }

}
