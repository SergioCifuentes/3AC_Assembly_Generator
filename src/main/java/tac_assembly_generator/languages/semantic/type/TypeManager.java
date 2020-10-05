/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tac_assembly_generator.languages.semantic.type;

/**
 *
 * @author sergio
 */
public class TypeManager {

    private static final String INTEGER_NAME = "Integer";
    public static final int INTEGER_TYPE = 2;
    private static final String FLOAT_NAME = "Float";
    public static final int FLOAT_TYPE = 1;
    private static final String CHAR_NAME = "Char";
    public static final int CHAR_TYPE = 3;

    public static final int VB_TYPES = 1;
    public static final int JAVA_TYPES = 2;
    public static final int PYTHON_TYPES = 3;
    public static final int C_TYPES = 4;
    private int languageType;

    private Type[] types;

    public TypeManager() {

    }

    public Type operateTypes(Integer type1, Integer type2) {
        if (type2 == null) {
            return getType(type1);
        } else {
            if (type1==type2) {
                return getType(type1);
            }
            if(getType(type1).isFather(getType(type2))){
                return getType(type2);
            }else if (getType(type2).isFather(getType(type1))) {
                 return getType(type1);
            }else{
                return null;
            }
                  
        }
    }

    public String getOutputType(Integer type){
        switch (type) {
            case INTEGER_TYPE:
                return INTEGER_NAME;
            case CHAR_TYPE:
                return CHAR_NAME;
            case FLOAT_TYPE:
                return FLOAT_NAME;
            default:
                throw new AssertionError();
        }
    }
    public Type getType(int type) {
        for (int i = 0; i < types.length; i++) {
            if (types[i].getNumber() == type) {
                return types[i];
            }
        }
        return null;
    }

    public void loadnextType() {
        switch (languageType) {
            case VB_TYPES:
                loadTypes(JAVA_TYPES);
                break;
            case JAVA_TYPES:
                loadTypes(PYTHON_TYPES);
                break;
            case PYTHON_TYPES:
                loadTypes(C_TYPES);
                break;
            default:
                throw new AssertionError();
        }
    }

    public void loadTypes(int typeOfTypes) {
        types = new Type[3];
        languageType = typeOfTypes;
        switch (typeOfTypes) {
            case VB_TYPES:
                types[0] = new Type(FLOAT_NAME, FLOAT_TYPE, null);
                types[1] = new Type(INTEGER_NAME, INTEGER_TYPE, types[0]);
                types[2] = new Type(CHAR_NAME, CHAR_TYPE, null);
                break;
            case JAVA_TYPES:
                types[0] = new Type(FLOAT_NAME, FLOAT_TYPE, null);
                types[1] = new Type(INTEGER_NAME, INTEGER_TYPE, types[0]);
                types[2] = new Type(CHAR_NAME, CHAR_TYPE, types[2]);

                break;
            case PYTHON_TYPES:
                types[0] = new Type(FLOAT_NAME, FLOAT_TYPE, null);
                types[1] = new Type(INTEGER_NAME, INTEGER_TYPE, types[0]);
                types[2] = new Type(CHAR_NAME, CHAR_TYPE, types[2]);
                break;
            case C_TYPES:
                types[0] = new Type(FLOAT_NAME, FLOAT_TYPE, null);
                types[1] = new Type(INTEGER_NAME, INTEGER_TYPE, types[0]);
                types[2] = new Type(CHAR_NAME, CHAR_TYPE, null);
                break;
            default:
                throw new AssertionError();
        }
    }

}