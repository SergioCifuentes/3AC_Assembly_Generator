//jflex lexicalMlg.flex
package tac_assembly_generator.languages.analyzers.lexical;
import java_cup.runtime.Symbol;
import tac_assembly_generator.languages.analyzers.syntax.SimbolosMlg;

%%
%public
%class MlgLexicAnalizer
%cupsym SimbolosMlg
%cup
%ignorecase
%cupdebug
%line
%column
%states COMMON_SYMBOLS,VBCODE,JAVACODE,PYCODE,PROGRAM

/*Identifiers*/
Letra = [a-zA-ZÀ-ÿ\u00f1\u00d1]
Signo = [!@#$S.-]
Digito = [0123456789]
IntegerLiteral = 0 | [1-9]{Digito}*
CommentC = {TraditionalComment} | {EndOfLineComment}
LineTerminator = \r|\n|\r\n
 TraditionalComment   = "/*" [^*] ~"*/" | "/*" "*"+ "/"
    // CommentC can be the last line of the file, without line terminator.
    EndOfLineComment     = "//"({Letra}|" "|{Signo}|{Digito})* {LineTerminator}?

%%
/* reglas lexicas */
<YYINITIAL> {
    /* Reserved words */
    "%%VB"                                    { yybegin(VBCODE);System.out.println("VBCODE "+yytext()); return new Symbol(SimbolosMlg.VB_SEPERATOR, yycolumn,yyline,yytext());}
     ({Letra}|("\_"))({Letra}|{Digito}|("\_"))*      { }
    {IntegerLiteral}                        { }
    ({IntegerLiteral}("\."{Digito})*)       { }
    {CommentC}                               { /* se ignoran los comentarios */}
/* error fallback */
    [^]                                     { /*errores.add(new ErrorAnalisis("Lexico",yytext(),"Caracter no aceptado",yyline+1, yycolumn+1));*/}
}

<COMMON_SYMBOLS>{    
    "="                                    { System.out.println("EQUAL "+yytext());return new Symbol(SimbolosMlg.EQUAL, yycolumn,yyline,yytext());}
    "("                                    { return new Symbol(SimbolosMlg.PARENTESIS_A, yycolumn,yyline,yytext());}
    ")"                                    { return new Symbol(SimbolosMlg.PARENTESIS_B, yycolumn,yyline,yytext());}
    "+"                                    { return new Symbol(SimbolosMlg.PLUS, yycolumn,yyline,yytext());}
    "-"                                    { return new Symbol(SimbolosMlg.MINUS, yycolumn,yyline,yytext());}
    "*"                                    { return new Symbol(SimbolosMlg.MULTIPLICATION, yycolumn,yyline,yytext());}
    "/"                                    { return new Symbol(SimbolosMlg.DIVISION, yycolumn,yyline,yytext());}
   
}

<VBCODE>{
    //%ignorecase
    {LineTerminator}                        { System.out.println("SALTO "); return new Symbol(SimbolosMlg.LINE_BREAK, yycolumn,yyline,yytext());}
    %ignorecase
    "%%JAVA"                                    { System.out.println("JAVA "+yytext());  yybegin(JAVACODE); return new Symbol(SimbolosMlg.JAVA_SEPERATOR, yycolumn,yyline,yytext());}
    "Integer"                                    { System.out.println("INTEGER "+yytext()); return new Symbol(SimbolosMlg.INTEGER_TYPE_VB, yycolumn,yyline,yytext());}
    "Float"                                    {  return new Symbol(SimbolosMlg.FLOAT_TYPE_VB, yycolumn,yyline,yytext());}
    "Char"                                    { return new Symbol(SimbolosMlg.CHAR_TYPE_VB, yycolumn,yyline,yytext());}
    "Dim"                                    {System.out.println("DIM "+yytext()); return new Symbol(SimbolosMlg.DIM_VB, yycolumn,yyline,yytext());}
    "="                                    { System.out.println("EQUAL "+yytext());return new Symbol(SimbolosMlg.EQUAL, yycolumn,yyline,yytext());}
    [ \t\b]                {System.out.println("ESPACIO ");}
.                                            {System.out.println("ERROR "+yytext()); return new Symbol(SimbolosMlg.ERROR,yycolumn,yyline,yytext());}

    
}

<JAVACODE>{
    //%caseless
    "%%PY"                                    { yybegin(PYCODE); return new Symbol(SimbolosMlg.PY_SEPERATOR, yycolumn,yyline,yytext());}
     "int"                                    {  System.out.println("INTEGERJ "+yytext());return new Symbol(SimbolosMlg.INTEGER_TYPE_J, yycolumn,yyline,yytext());}
    "float"                                    { return new Symbol(SimbolosMlg.FLOAT_TYPE_J, yycolumn,yyline,yytext());}
    "char"                                    { return new Symbol(SimbolosMlg.CHAR_TYPE_J, yycolumn,yyline,yytext());}
     {LineTerminator}                        { System.out.println("SALTO "); return new Symbol(SimbolosMlg.LINE_BREAK, yycolumn,yyline,yytext());}
    
}
<PYCODE>{
    "%%PROGRAMA"                                    { yybegin(PROGRAM); return new Symbol(SimbolosMlg.PROGRAM_SEPERATOR, yycolumn,yyline,yytext());}
    [^]                                     { /*return symbol(yyline+1, yycolumn+1, yytext(), sym.JAVA_CODE);*/}
}

<PROGRAM>{
    
    [^]                                     { /*return symbol(yyline+1, yycolumn+1, yytext(), sym.JAVA_CODE);*/}
}