/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tac_assembly_generator.TAC.quadruple;

import java.util.ArrayList;

/**
 *
 * @author sergio
 */
public class BoolQuad {

    private String tag;
    private ArrayList<Object> quadruple;
    private String exit;
    private BoolQuad yesBool;
    private String yesBoolTag;
    private BoolQuad noBool;
    private String noBoolTag;
    private BoolQuad fatherYesBool;
    private BoolQuad fatherNoBool;
    private Boolean sonExit;

    public BoolQuad(String tag, ArrayList<Object> quadruple, String yes, String no, String exit) {
        this.tag = tag;
        this.quadruple = quadruple;
        this.exit = exit;
        sonExit = false;

        if (yes != null) {
            yesBool = new BoolQuad(yes, null, null, null, exit);
            noBool = new BoolQuad(no, null, null, null, exit);
            fatherYesBool = yesBool;
            fatherNoBool = noBool;
        }

    }

    public void addQuad(ArrayList<Object> quList) {
        if (quadruple == null) {
            quadruple = new ArrayList<>();
        }
        for (int i = 0; i < quList.size(); i++) {
            quadruple.add(quList.get(i));
        }

    }

    public void setYesBool(BoolQuad yesBool) {
        this.yesBool = yesBool;
    }

    public BoolQuad getYesBool() {
        return yesBool;
    }

    public String getTag() {
        return tag;
    }

    public void setNoBool(BoolQuad noBool) {
        this.noBool = noBool;
    }

    public BoolQuad getNoBool() {
        return noBool;
    }

    public void setExit(String exit, boolean sonExit) {
        this.exit = exit;
        this.sonExit = sonExit;
        if (yesBoolTag == null && yesBool != null) {
            yesBool.setExit(exit, sonExit);
        }
        if (noBoolTag == null && noBool != null) {
            yesBool.setExit(exit, sonExit);
        }
    }

    public void changeNoFatherWhile(){
        changeFatherNoBool(new BoolQuad(exit, null,null,null,null), true);
    }
    
    public String getExit() {
        return exit;
    }

    public Boolean getSonExit() {
        return sonExit;
    }

    @Override
    public String toString() {
        String fatheryes = "VACIO";
        if (fatherYesBool != null) {
            fatheryes = fatherYesBool.tag;
        }
        String fatherNo = "VACIO";
        if (fatherNoBool != null) {
            fatherNo = fatherNoBool.tag;
        }
        String quadSize = "VACIO";
        if (quadruple != null) {
            quadSize = String.valueOf(quadruple.size());
        }
        String yes = "VACIO";
        if (yesBool != null) {
            yes = yesBool.tag;
        }
        String no = "VACIO";
        if (noBool != null) {
            no = noBool.tag;
        }

        return "BoolQuad{" + "tag=" + tag + ", quadruple=" + quadruple + "SIZE " + quadSize + ", exit=" + exit + ", yesBool=" + yes + ", noBool=" + no + ", fatherYesBool=" + fatheryes + ", fatherNoBool=" + fatherNo + '}';
    }

    public void setFatherYesBool(BoolQuad fatherYesBool) {

        this.fatherYesBool = fatherYesBool;
    }

    public void changeFatherYesBool(ArrayList<Object> fatherYesBool) {
        System.out.println(noBool + "<<<<<-------------------");
        System.out.println(noBool.getTag());
        System.out.println(fatherYesBool.size());
        if (noBool != null && this.fatherYesBool != null && noBool.getTag().equals(this.fatherYesBool.tag)) {
            noBool.addQuad(fatherYesBool);
        } else if (noBool.quadruple != null) {
            noBool.changeFatherYesBool(fatherYesBool);

        }
        if (yesBool.getTag().equals(this.fatherYesBool.tag)) {
            yesBool.addQuad(fatherYesBool);
        } else if (yesBool.quadruple != null) {

            yesBool.changeFatherYesBool(fatherYesBool);
        }
    }

    public void setSonExit(Boolean sonExit) {
        this.sonExit = sonExit;
    }

    public void changeFatherNoBool(ArrayList<Object> fatherNoBool) {
        if (noBool != null) {
            if (noBool.getTag().equals(this.fatherNoBool.tag)) {
                noBool.addQuad(fatherNoBool);

            } else if (noBool.quadruple != null) {
                noBool.changeFatherNoBool(fatherNoBool);
            }
        }
        if (yesBool != null) {

            if (yesBool.getTag().equals(this.fatherNoBool.tag)) {
                yesBool.addQuad(fatherNoBool);
            } else if (yesBool.quadruple != null) {

                yesBool.changeFatherNoBool(fatherNoBool);
            }
        }
    }

    public ArrayList<Object> getQuadruple() {
        return quadruple;
    }

    public void changeFatherYesBool(BoolQuad fatherYesBool, boolean justtag) {
        if (noBool.getTag().equals(this.fatherYesBool.tag)) {
            if (justtag) {
                noBool = fatherYesBool;
                noBoolTag = fatherYesBool.getTag();
            } else {
                noBool = fatherYesBool;
            }

        } else if (noBool.quadruple != null) {
            noBool.changeFatherYesBool(fatherYesBool, justtag);
        }
        if (yesBool.getTag().equals(this.fatherYesBool.tag)) {
            if (justtag) {
                yesBool = fatherYesBool;
                yesBoolTag = fatherYesBool.getTag();
            } else {
                yesBool = fatherYesBool;
            }
        } else if (yesBool.quadruple != null) {

            yesBool.changeFatherYesBool(fatherYesBool, justtag);
        }
    }

    public void changeFatherNoBool(BoolQuad fatherYesBool, boolean justtag) {
        if (noBool != null) {
            if (noBool.getTag().equals(fatherNoBool.tag)) {
                if (justtag) {
                    noBool = fatherYesBool;
                    noBoolTag = fatherYesBool.getTag();
                } else {
                    noBool = fatherYesBool;
                }

            } else if (noBool.quadruple != null) {
                noBool.changeFatherNoBool(fatherYesBool, justtag);
            }
        }
        if (yesBool != null) {
            if (yesBool.getTag().equals(fatherNoBool.tag)) {
                if (justtag) {
                    yesBool = fatherYesBool;
                    yesBoolTag = fatherYesBool.getTag();
                } else {
                    yesBool = fatherYesBool;
                }
            } else if (yesBool.quadruple != null) {
                yesBool.changeFatherNoBool(fatherYesBool, justtag);
            }
        }

    }

    public String getYesBoolTag() {
        return yesBoolTag;
    }

    public String getNoBoolTag() {
        return noBoolTag;
    }

    public void setFatherNoBool(BoolQuad fatherNoBool) {
        this.fatherNoBool = fatherNoBool;
    }

    public BoolQuad getFatherYesBool() {
        return fatherYesBool;
    }

    public BoolQuad getFatherNoBool() {
        return fatherNoBool;
    }

}
