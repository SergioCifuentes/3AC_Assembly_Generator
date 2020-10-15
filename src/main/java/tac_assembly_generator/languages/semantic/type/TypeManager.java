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

    private static final String INTEGER_NAME = "int";
    public static final int INTEGER_TYPE = 2;
    private static final String FLOAT_NAME = "float";
    public static final int FLOAT_TYPE = 1;
    private static final String CHAR_NAME = "char";
    public static final int CHAR_TYPE = 3;
    private static final String BOOL_NAME = "boolean";
    public static final int BOOL_TYPE = 4;
    private static final String VAR_NAME = "var";
    public static final int VAR_TYPE = 5;
    private static final String CLASS_NAME = "class";
    public static final int CLASS_TYPE = 6;
    public static final Type CLASS= new Type(CLASS_NAME, CLASS_TYPE,null);

    public  static final String INT_INPUT = "%d";
    public  static final String CHAR_INPUT = "%c";
    public  static final String FLOAT_INPUT = "%f";
    
    
    
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
            if (type1 == VAR_TYPE) {
                return getType(type2);
            } else if (type2 == VAR_TYPE) {
                return getType(type1);
            }
            if (type1 == type2) {
                return getType(type1);
            }
            if (getType(type1).isFather(getType(type2))) {
                return getType(type2);
            } else if (getType(type2).isFather(getType(type1))) {
                return getType(type1);
            } else {
                return null;
            }

        }
    }

    public Type operateBoolTypes(Integer type1, Integer type2) {

        if (isNumerico(type1) && isNumerico(type2)) {
            return getType(BOOL_TYPE);
        }
        return null;
    }

    public boolean isNumerico(Integer type) {
        if (type == INTEGER_TYPE || type == FLOAT_TYPE || type == VAR_TYPE) {
            return true;
        }
        if (languageType == JAVA_TYPES || languageType == PYTHON_TYPES) {
            if (type == CHAR_TYPE) {
                return true;
            }
        }
        return false;
    }

    public String getOutputType(Integer type) {
        switch (type) {
            case INTEGER_TYPE:
                return INTEGER_NAME;
            case CHAR_TYPE:
                return CHAR_NAME;
            case FLOAT_TYPE:
                return FLOAT_NAME;
            case BOOL_TYPE:
                return BOOL_NAME;
            case VAR_TYPE:
                return VAR_NAME;
            default:
                throw new AssertionError();
        }
    }

    public static String getOutputTypeStatic(Integer type) {
        switch (type) {
            case INTEGER_TYPE:
                return INTEGER_NAME;
            case CHAR_TYPE:
                return CHAR_NAME;
            case FLOAT_TYPE:
                return FLOAT_NAME;
            case BOOL_TYPE:
                return BOOL_NAME;
            case VAR_TYPE:
                return VAR_NAME;
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

    public String getLanguage() {
        switch (languageType) {
            case VB_TYPES:
                return "VB";
            case JAVA_TYPES:
                return "JAVA";
            case PYTHON_TYPES:
                return "PY";
            case C_TYPES :
                return "C";
            default:
                throw new AssertionError();
        }

    }

    public void loadnextType() {
        
       
        switch (languageType) {
            case VB_TYPES:
                 System.out.println("SWWWWWWWWWWWWWWWWWWWWW JAVA");
                loadTypes(JAVA_TYPES);
                break;
            case JAVA_TYPES:
                System.out.println("SWWWWWWWWWWWWWWWWWWWWW pY");
                
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
        types = new Type[5];
        languageType = typeOfTypes;
        switch (typeOfTypes) {
            case VB_TYPES:
                types[0] = new Type(FLOAT_NAME, FLOAT_TYPE, null);
                types[1] = new Type(INTEGER_NAME, INTEGER_TYPE, types[0]);
                types[2] = new Type(CHAR_NAME, CHAR_TYPE, null);
                types[3] = new Type(BOOL_NAME, BOOL_TYPE, null);
                break;
            case JAVA_TYPES:
                types[0] = new Type(FLOAT_NAME, FLOAT_TYPE, null);
                types[1] = new Type(INTEGER_NAME, INTEGER_TYPE, types[0]);
                types[2] = new Type(CHAR_NAME, CHAR_TYPE, types[1]);
                types[3] = new Type(BOOL_NAME, BOOL_TYPE, null);

                   break;
            case PYTHON_TYPES:
                types[0] = new Type(FLOAT_NAME, FLOAT_TYPE, null);
                types[1] = new Type(INTEGER_NAME, INTEGER_TYPE, types[0]);
                types[2] = new Type(CHAR_NAME, CHAR_TYPE, types[1]);
                types[3] = new Type(BOOL_NAME, BOOL_TYPE, null);
                types[4] = new Type(VAR_NAME, VAR_TYPE, null);
                break;
            case C_TYPES:
                types[0] = new Type(FLOAT_NAME, FLOAT_TYPE, null);
                types[1] = new Type(INTEGER_NAME, INTEGER_TYPE, types[0]);
                types[2] = new Type(CHAR_NAME, CHAR_TYPE, null);
                types[3] = new Type(BOOL_NAME, BOOL_TYPE, null);
                break;
            default:
                throw new AssertionError();
        }
    }

}
