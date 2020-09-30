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

    private Type[] types;

    public TypeManager() {

    }
    public Type getType(int type){
        for (int i = 0; i < types.length; i++) {
            if (types[i].getNumber()==type) return types[i];
        }
        return null;
    }
    
    public void loadTypes(int typeOfTypes) {
        types = new Type[3];
        switch (typeOfTypes) {
            case VB_TYPES:
                types[0] = new Type(FLOAT_NAME, FLOAT_TYPE, null);
                types[1] = new Type(INTEGER_NAME, INTEGER_TYPE, types[0]);
                types[2] = new Type(CHAR_NAME, CHAR_TYPE,null );
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
                types[2] = new Type(CHAR_NAME, CHAR_TYPE,null);
                break;
            default:
                throw new AssertionError();
        }
    }

}
