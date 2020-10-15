/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tac_assembly_generator.TAC.quadruple;

import java.util.ArrayList;
import tac_assembly_generator.TAC.TempGenerator;

/**
 *
 * @author sergio
 */
public class BoolQuadControl {

    private TempGenerator tempGenerator;

    public BoolQuadControl(TempGenerator tempGenerator) {
        this.tempGenerator = tempGenerator;
    }

    public BoolQuad operateBoolQuad(Object boolQuad1, Object boolQuad2, Integer op) {
        BoolQuad bool1 = (BoolQuad) boolQuad1;
        BoolQuad bool2 = (BoolQuad) boolQuad2;
        if (op == Operation.OP_AND) {
            String tagRemove = bool1.getFatherYesBool().getTag();
            tempGenerator.removeTag(tagRemove);
            bool1.changeFatherYesBool(bool2, false);
            tagRemove = bool2.getFatherNoBool().getTag();
            tempGenerator.removeTag(tagRemove);
            bool2.changeFatherNoBool(bool1.getFatherNoBool(), true);
            bool2.setFatherNoBool(bool1.getFatherNoBool());
            bool2.setExit(bool1.getExit(), true);
            bool2.setSonExit(true);
            bool1.setFatherYesBool(bool2.getYesBool());
            return bool1;
        } else if (op == Operation.OP_OR) {
            String tagRemove = bool1.getNoBool().getTag();
            tempGenerator.removeTag(tagRemove);
            bool1.changeFatherNoBool(bool2, false);
            tagRemove = bool1.getFatherYesBool().getTag();
            tempGenerator.removeTag(tagRemove);
            bool2.changeFatherYesBool(bool1.getFatherYesBool(), true);
            bool2.setFatherYesBool(bool1.getFatherYesBool());
            bool2.setExit(bool1.getExit(), true);
            bool1.setFatherNoBool(bool2.getNoBool());
            return bool1;

        } else if (op == Operation.CASE) {
            String tagRemove = bool1.getNoBool().getTag();
            tempGenerator.removeTag(tagRemove);
            bool1.changeFatherNoBool(bool2, false);
            tagRemove = bool1.getFatherYesBool().getTag();
            tempGenerator.removeTag(tagRemove);
            bool2.setExit(bool1.getExit(), true);
            bool1.setFatherNoBool(bool2.getNoBool());
            return bool1;
        }
        return null;
    }

    public BoolQuad convertQuadToBool(ArrayList<Object> quads) {
        
        BoolQuad bo = new BoolQuad(tempGenerator.generateTag(), quads, tempGenerator.generateTag(), tempGenerator.generateTag(), tempGenerator.generateTag());
        
        return bo;
    }

    public BoolQuad createCaseBool(String id, String compare, ArrayList<Object> lines) {
        ArrayList<Object> quads = new ArrayList<>();
        String yes = tempGenerator.generateTag();
        quads.add(new Quadruple(Operation.EQUAL_BOOL, id, compare, yes));
        BoolQuad boolQuad = new BoolQuad(tempGenerator.generateTag(), quads, yes, tempGenerator.generateTag(), tempGenerator.generateTag());
        boolQuad.changeFatherYesBool(lines);
        return boolQuad;
    }

    public ArrayList<Object> convertBoolToQuad(BoolQuad boolQuad) {


        if (boolQuad.getNoBool() != null) {
            ArrayList<Object> quads = new ArrayList<>();
            Object lastQuad = getLastQuad(boolQuad.getQuadruple());
            for (int i = 0; i < boolQuad.getQuadruple().size(); i++) {
                if (boolQuad.getQuadruple().get(i) != lastQuad) {
                    quads.add(boolQuad.getQuadruple().get(i));
                } else {
                    Quadruple asst = (Quadruple) lastQuad;
                    quads.add(new Quadruple(asst.getOp(), asst.getArg1(), asst.getArg2(), boolQuad.getYesBool().getTag()));
                }
            }
            quads.add(new Quadruple(Operation.GO_TO, null, null, boolQuad.getNoBool().getTag()));
            if (boolQuad.getYesBoolTag() == null) {
                quads.add(new Quadruple(null, null, null, boolQuad.getYesBool().getTag()));
                if (boolQuad.getYesBool().getQuadruple() != null) {
                    ArrayList<Object> aux = convertBoolToQuad(boolQuad.getYesBool());
                    for (int i = 0; i < aux.size(); i++) {
                        quads.add(aux.get(i));
                    }

                }

                quads.add(new Quadruple(Operation.GO_TO, null, null, boolQuad.getExit()));
            }
            if (boolQuad.getNoBoolTag() == null) {
                quads.add(new Quadruple(null, null, null, boolQuad.getNoBool().getTag()));
                if (boolQuad.getNoBool().getQuadruple() != null) {
                    ArrayList<Object> aux = convertBoolToQuad(boolQuad.getNoBool());
                    for (int i = 0; i < aux.size(); i++) {
                        quads.add(aux.get(i));
                    }
                }
                if (!boolQuad.getSonExit()) {
                    quads.add(new Quadruple(Operation.GO_TO, null, null, boolQuad.getExit()));
                }

            }
            if (!boolQuad.getSonExit()) {
                quads.add(new Quadruple(null, null, null, boolQuad.getExit()));
            }

            return quads;
        } else {
            return boolQuad.getQuadruple();
        }

    }

    public Object getLastQuad(ArrayList<Object> quads) {
        Object lastQuad = null;
        for (int i = 0; i < quads.size(); i++) {
            try {
                Quadruple aux = (Quadruple) quads.get(i);
                lastQuad = quads.get(i);
            } catch (Exception e) {
            }

        }
        return lastQuad;
    }

}
