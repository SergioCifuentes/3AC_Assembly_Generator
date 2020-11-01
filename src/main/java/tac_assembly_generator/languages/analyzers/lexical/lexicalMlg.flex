//jflex lexicalMlg.flex
package tac_assembly_generator.languages.analyzers.lexical;
import java_cup.runtime.Symbol;
import tac_assembly_generator.languages.analyzers.syntax.SimbolosMlg;
import tac_assembly_generator.TAC.TranslateControlerTAC;

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
Input_Ignore_Case= ("I"|"i")("N"|"n")("P"|"p")("U"|"u")("T"|"t")
//Input= "input"
    // CommentC can be the last line of the file, without line terminator.
    EndOfLineComment     = "//"[^\r\n]* {LineTerminator}?

%{
    TranslateControlerTAC tac;
    Integer blockIndentation=0;
    public void addTac(TranslateControlerTAC tac){
        this.tac=tac;
    } 
%}

%%

/* reglas lexicas */
<YYINITIAL> {
    /* Reserved words */
    "%%VB"                                    { yybegin(VBCODE);System.out.println("VBCODE "+yytext()); return new Symbol(SimbolosMlg.VB_SEPERATOR, yycolumn,yyline,yytext());}
     ({Letra}|("\_"))({Letra}|{Digito}|("\_"))*      { }
    {IntegerLiteral}                        { }
    ({IntegerLiteral}("\."{Digito})*)       { }
    {CommentC}                               {tac.addComment(yytext());}
/* error fallback */
    [^]                                     { /*errores.add(new ErrorAnalisis("Lexico",yytext(),"Caracter no aceptado",yyline+1, yycolumn+1));*/}
}

<COMMON_SYMBOLS>{    
    "="                                    {return new Symbol(SimbolosMlg.EQUAL, yycolumn,yyline,yytext());}
    "("                                    { return new Symbol(SimbolosMlg.PARENTHESIS_A, yycolumn,yyline,yytext());}
    ")"                                    { return new Symbol(SimbolosMlg.PARENTHESIS_B, yycolumn,yyline,yytext());}
    "+"                                    { return new Symbol(SimbolosMlg.PLUS, yycolumn,yyline,yytext());}
    "-"                                    { return new Symbol(SimbolosMlg.MINUS, yycolumn,yyline,yytext());}
    "*"                                    { return new Symbol(SimbolosMlg.MULTIPLICATION, yycolumn,yyline,yytext());}
    "/"                                    { return new Symbol(SimbolosMlg.DIVISION, yycolumn,yyline,yytext());}
   
}

<VBCODE>{
    //%ignorecase
    
    "%%JAVA"                                                            {   yybegin(JAVACODE); return new Symbol(SimbolosMlg.JAVA_SEPERATOR, yycolumn,yyline,yytext());}
    ("I"|"i")("N"|"n")("T"|"t")("E"|"e")("G"|"g")("E"|"e")("R"|"r")     {  return new Symbol(SimbolosMlg.INTEGER_TYPE, yycolumn,yyline,yytext());}
    ("F"|"f")("L"|"l")("O"|"o")("A"|"a")("T"|"t")                       {  return new Symbol(SimbolosMlg.FLOAT_TYPE, yycolumn,yyline,yytext());}
    ("C"|"c")("H"|"h")("A"|"a")("R"|"r")                                { return new Symbol(SimbolosMlg.CHAR_TYPE, yycolumn,yyline,yytext());}
    ("D"|"d")("I"|"i")("M"|"m")                                         { return new Symbol(SimbolosMlg.DIM_VB, yycolumn,yyline,yytext());}
    ("P"|"p")("U"|"u")("B"|"b")("L"|"l")("I"|"i")("C"|"c")              { return new Symbol(SimbolosMlg.PUBLIC, yycolumn,yyline,yytext());}
    ("F"|"f")("U"|"u")("N"|"n")("C"|"c")("T"|"t")("I"|"i")("O"|"o")("N"|"n") {  return new Symbol(SimbolosMlg.FUNCTION_VB, yycolumn,yyline,yytext());}
    ("S"|"s")("U"|"u")("B"|"b")                                         { return new Symbol(SimbolosMlg.SUB_VB, yycolumn,yyline,yytext());}
    ("E"|"e")("N"|"n")("D"|"d")                                         { return new Symbol(SimbolosMlg.END_VB, yycolumn,yyline,yytext());}
    ("A"|"a")("S"|"s")                                                  { return new Symbol(SimbolosMlg.AS_VB, yycolumn,yyline,yytext());}
    ("R"|"r")("E"|"e")("T"|"t")("U"|"u")("R"|"r")("N"|"n")              {  return new Symbol(SimbolosMlg.RETURN, yycolumn,yyline,yytext());}
    ("I"|"i")("N"|"n")("T"|"t")("I"|"i")("N"|"n")("P"|"p")("U"|"u")("T"|"t")                      {return new Symbol(SimbolosMlg.INTINPUT, yycolumn,yyline,yytext());}
    ("C"|"c")("H"|"h")("A"|"a")("R"|"r")("I"|"i")("N"|"n")("P"|"p")("U"|"u")("T"|"t")            { return new Symbol(SimbolosMlg.CHARINPUT, yycolumn,yyline,yytext());}
    ("F"|"f")("L"|"l")("O"|"o")("A"|"a")("T"|"t")("I"|"i")("N"|"n")("P"|"p")("U"|"u")("T"|"t")   { return new Symbol(SimbolosMlg.FLOATINPUT, yycolumn,yyline,yytext());}
    ("C"|"c")("O"|"o")("N"|"n")("S"|"s")("O"|"o")("L"|"l")("E"|"e")     { return new Symbol(SimbolosMlg.CONSOLE_VB, yycolumn,yyline,yytext());}
    ("W"|"w")("R"|"r")("I"|"i")("T"|"t")("E"|"e")                       { return new Symbol(SimbolosMlg.WRITE_VB, yycolumn,yyline,yytext());}
    ("W"|"w")("R"|"r")("I"|"i")("T"|"t")("E"|"e")("L"|"l")("N"|"n")     {  return new Symbol(SimbolosMlg.WRITELN_VB, yycolumn,yyline,yytext());}
    ("F"|"f")("O"|"o")("R"|"r")                                         { return new Symbol(SimbolosMlg.FOR, yycolumn,yyline,yytext());}
    ("T"|"t")("O"|"o")                                                  { return new Symbol(SimbolosMlg.TO_VB, yycolumn,yyline,yytext());}
    ("S"|"s")("T"|"t")("E"|"e")("P"|"p")                                { return new Symbol(SimbolosMlg.STEP_VB, yycolumn,yyline,yytext());}
    ("N"|"n")("E"|"e")("X"|"x")("T"|"t")                                { return new Symbol(SimbolosMlg.NEXT_VB, yycolumn,yyline,yytext());}
    ("W"|"w")("H"|"h")("I"|"i")("L"|"l")("E"|"e")                       {  return new Symbol(SimbolosMlg.WHILE, yycolumn,yyline,yytext());}
    ("N"|"n")("O"|"o")("T"|"t")                                         { return new Symbol(SimbolosMlg.NOT, yycolumn,yyline,yytext());}
    ("A"|"a")("N"|"n")("D"|"d")                                         { return new Symbol(SimbolosMlg.AND, yycolumn,yyline,yytext());}
    ("O"|"o")("R"|"r")                                                  { return new Symbol(SimbolosMlg.OR, yycolumn,yyline,yytext());}
    ("T"|"t")("R"|"r")("U"|"u")("E"|"e")                                { return new Symbol(SimbolosMlg.TRUE, yycolumn,yyline,yytext());}
    ("F"|"f")("A"|"a")("L"|"l")("S"|"s")("E"|"e")                       {  return new Symbol(SimbolosMlg.FALSE, yycolumn,yyline,yytext());}
    ("D"|"d")("O"|"o")                                                  {return new Symbol(SimbolosMlg.DO, yycolumn,yyline,yytext());}
    ("L"|"l")("O"|"o")("O"|"o")("P"|"p")                                { return new Symbol(SimbolosMlg.LOOP_VB, yycolumn,yyline,yytext());}
    ("I"|"i")("F"|"f")                                                  { return new Symbol(SimbolosMlg.IF, yycolumn,yyline,yytext());}
    ("E"|"e")("L"|"l")("S"|"s")("E"|"e")                                { return new Symbol(SimbolosMlg.ELSE, yycolumn,yyline,yytext());}
    ("E"|"e")("L"|"l")("S"|"s")("E"|"e")("I"|"i")("F"|"f")              {  return new Symbol(SimbolosMlg.ELSE_IF_VB, yycolumn,yyline,yytext());}
    ("T"|"t")("H"|"h")("E"|"e")("N"|"n")                                { return new Symbol(SimbolosMlg.THEN_VB, yycolumn,yyline,yytext());}
    ("S"|"s")("E"|"e")("L"|"l")("E"|"e")("C"|"c")("T"|"t")              {  return new Symbol(SimbolosMlg.SELECT_VB, yycolumn,yyline,yytext());}
    ("C"|"c")("A"|"a")("S"|"s")("E"|"e")                                { return new Symbol(SimbolosMlg.CASE_VB, yycolumn,yyline,yytext());}
    ("M"|"m")("O"|"o")("D"|"d")                                         { return new Symbol(SimbolosMlg.MOD, yycolumn,yyline,yytext());}
    ("B"|"b")("Y"|"y")("V"|"v")("A"|"a")("L"|"l")                       {  return new Symbol(SimbolosMlg.BY_VAL, yycolumn,yyline,yytext());}
    
    "\"" [^\"] ~ "\""                                                   { return new Symbol(SimbolosMlg.STRING, yycolumn,yyline,yytext());}
     "\'' [^\''] "\''                                                   { return new Symbol(SimbolosMlg.CHAR, yycolumn,yyline,yytext());}
    ({Letra}|"_")({Letra}|{Digito}|"_")*                                { return new Symbol(SimbolosMlg.ID, yycolumn,yyline,yytext());}
    ({Digito})+                                             {  return new Symbol(SimbolosMlg.NUMBER, yycolumn,yyline,yytext());}
    ({Digito})+"."({Digito})+                               { return new Symbol(SimbolosMlg.DECIMAL, yycolumn,yyline,yytext());}
    {CommentC}                               {tac.addComment(yytext());}
    "="                                                     { return new Symbol(SimbolosMlg.EQUAL, yycolumn,yyline,yytext());}
    "<>"                                                    { return new Symbol(SimbolosMlg.DIFERENT_VB, yycolumn,yyline,yytext());}
    ">"                                                     { return new Symbol(SimbolosMlg.GREATER_THAN, yycolumn,yyline,yytext());}
    "<"                                                     { return new Symbol(SimbolosMlg.LESS_THAN, yycolumn,yyline,yytext());}
    (">="|"=>")                                             { return new Symbol(SimbolosMlg.GREATER_THAN_EQUAL, yycolumn,yyline,yytext());}
    ("<="|"=<")                                             { return new Symbol(SimbolosMlg.LESS_THAN_EQUAL, yycolumn,yyline,yytext());}
    "("                                                     { return new Symbol(SimbolosMlg.PARENTHESIS_A, yycolumn,yyline,yytext());}
    ")"                                                     { return new Symbol(SimbolosMlg.PARENTHESIS_B, yycolumn,yyline,yytext());}
    "+"                                    { return new Symbol(SimbolosMlg.PLUS, yycolumn,yyline,yytext());}
    "-"                                    { return new Symbol(SimbolosMlg.MINUS, yycolumn,yyline,yytext());}
    "*"                                    { return new Symbol(SimbolosMlg.MULTIPLICATION, yycolumn,yyline,yytext());}
    "/"                                    { return new Symbol(SimbolosMlg.DIVISION, yycolumn,yyline,yytext());}
    ("M"|"m")("O"|"o")("D"|"d")                                    { return new Symbol(SimbolosMlg.MOD, yycolumn,yyline,yytext());}
   
    "."                                                     { return new Symbol(SimbolosMlg.POINT, yycolumn,yyline,yytext());}
    ","                                                     { return new Symbol(SimbolosMlg.COMMA, yycolumn,yyline,yytext());}
    "&"                                                     { return new Symbol(SimbolosMlg.AMPERSON, yycolumn,yyline,yytext());}
    [ \t\b]                {}
    {LineTerminator}                        { }

    

    
}

<JAVACODE>{
    //%caseless
    "%%PY"                                    { yybegin(PYCODE); return new Symbol(SimbolosMlg.PYTHON_SEPERATOR, yycolumn,yyline,yytext());}
     "int"                                    { return new Symbol(SimbolosMlg.INTEGER_TYPE, yycolumn,yyline,yytext());}
    "float"                                    { return new Symbol(SimbolosMlg.FLOAT_TYPE, yycolumn,yyline,yytext());}
    "char"                                    { return new Symbol(SimbolosMlg.CHAR_TYPE, yycolumn,yyline,yytext());}
    "class"                                    { return new Symbol(SimbolosMlg.CLASS_J, yycolumn,yyline,yytext());}
    "public"                                    { return new Symbol(SimbolosMlg.PUBLIC, yycolumn,yyline,yytext());}
    "void"                                    { return new Symbol(SimbolosMlg.VOID_J, yycolumn,yyline,yytext());}
    "return"                                    { return new Symbol(SimbolosMlg.RETURN, yycolumn,yyline,yytext());}
    "System.out.println"                    { return new Symbol(SimbolosMlg.SYSTEM_OUT_PRINTLN, yycolumn,yyline,yytext());}
    "System.out.print"                    { return new Symbol(SimbolosMlg.SYSTEM_OUT_PRINT, yycolumn,yyline,yytext());}
    "intinput"                              { return new Symbol(SimbolosMlg.INTINPUT, yycolumn,yyline,yytext());}
    "charinput"                              { return new Symbol(SimbolosMlg.CHARINPUT, yycolumn,yyline,yytext());}
    "floatinput"                              { return new Symbol(SimbolosMlg.FLOATINPUT, yycolumn,yyline,yytext());}
    "for"                              { return new Symbol(SimbolosMlg.FOR, yycolumn,yyline,yytext());}
    "while"                              { return new Symbol(SimbolosMlg.WHILE, yycolumn,yyline,yytext());}
    "do"                              { return new Symbol(SimbolosMlg.DO, yycolumn,yyline,yytext());}
    "true"                              { return new Symbol(SimbolosMlg.TRUE, yycolumn,yyline,yytext());}
    "false"                              { return new Symbol(SimbolosMlg.FALSE, yycolumn,yyline,yytext());}
    "if"                              { return new Symbol(SimbolosMlg.IF, yycolumn,yyline,yytext());}
    "else"                              { return new Symbol(SimbolosMlg.ELSE, yycolumn,yyline,yytext());}
    "switch"                              { return new Symbol(SimbolosMlg.SWITCH_J, yycolumn,yyline,yytext());}
    "case"                              { return new Symbol(SimbolosMlg.CASE, yycolumn,yyline,yytext());}
    "default"                              { return new Symbol(SimbolosMlg.DEFAULT, yycolumn,yyline,yytext());}
    "break"                              { return new Symbol(SimbolosMlg.BREAK, yycolumn,yyline,yytext());}
    "this"                                { return new Symbol(SimbolosMlg.THIS, yycolumn,yyline,yytext());}
    "new"                                { return new Symbol(SimbolosMlg.NEW, yycolumn,yyline,yytext());}
   "."                                                     { return new Symbol(SimbolosMlg.POINT, yycolumn,yyline,yytext());}
    ","                                                     {return new Symbol(SimbolosMlg.COMMA, yycolumn,yyline,yytext());}
     
    ":"                                                     {return new Symbol(SimbolosMlg.COLON, yycolumn,yyline,yytext());}
    "="                                                     {return new Symbol(SimbolosMlg.EQUAL, yycolumn,yyline,yytext());}
    "=="                                                     {return new Symbol(SimbolosMlg.EQUAL, yycolumn,yyline,yytext());}
    "=!"                                                    { return new Symbol(SimbolosMlg.DIFERENT_VB, yycolumn,yyline,yytext());}
    ">"                                                     { return new Symbol(SimbolosMlg.GREATER_THAN, yycolumn,yyline,yytext());}
    "<"                                                     { return new Symbol(SimbolosMlg.LESS_THAN, yycolumn,yyline,yytext());}
    (">="|"=>")                                             { return new Symbol(SimbolosMlg.GREATER_THAN_EQUAL, yycolumn,yyline,yytext());}
    ("<="|"=<")                                             { return new Symbol(SimbolosMlg.LESS_THAN_EQUAL, yycolumn,yyline,yytext());}
    "!"                                                    { return new Symbol(SimbolosMlg.NOT, yycolumn,yyline,yytext());}
    ("||"|"|")                                                     {return new Symbol(SimbolosMlg.OR, yycolumn,yyline,yytext());}
    ("&&"|"&")                                                    { return new Symbol(SimbolosMlg.AND, yycolumn,yyline,yytext());}
    

    ";"                                    { return new Symbol(SimbolosMlg.SEMICOLON, yycolumn,yyline,yytext());}
    "{"                                    { return new Symbol(SimbolosMlg.LEFT_CB, yycolumn,yyline,yytext());}
    "}"                                    { return new Symbol(SimbolosMlg.RIGHT_CB, yycolumn,yyline,yytext());}
    "("                                                     { return new Symbol(SimbolosMlg.PARENTHESIS_A, yycolumn,yyline,yytext());}
    ")"                                                     { return new Symbol(SimbolosMlg.PARENTHESIS_B, yycolumn,yyline,yytext());}
    "+"                                    { return new Symbol(SimbolosMlg.PLUS, yycolumn,yyline,yytext());}
    "-"                                    { return new Symbol(SimbolosMlg.MINUS, yycolumn,yyline,yytext());}
    "*"                                    { return new Symbol(SimbolosMlg.MULTIPLICATION, yycolumn,yyline,yytext());}
    "/"                                    { return new Symbol(SimbolosMlg.DIVISION, yycolumn,yyline,yytext());}
    "%"                                    { return new Symbol(SimbolosMlg.MOD, yycolumn,yyline,yytext());}
   
   ({Letra}|"_")({Letra}|{Digito}|"_")*                       { return new Symbol(SimbolosMlg.ID, yycolumn,yyline,yytext());}
    ({Digito})+                                             {  return new Symbol(SimbolosMlg.NUMBER, yycolumn,yyline,yytext());}
    ({Digito})+"."({Digito})+                               { return new Symbol(SimbolosMlg.DECIMAL, yycolumn,yyline,yytext());}
    {CommentC}                               {tac.addComment(yytext());}
    
    "\"" [^\"] ~ "\""                                                   { return new Symbol(SimbolosMlg.STRING, yycolumn,yyline,yytext());}
     {LineTerminator}                        {}
     [ \t\b]                {}
     .                                            { return new Symbol(SimbolosMlg.ERROR,yycolumn,yyline,yytext());}
    
}
<PYCODE>{
    "%%PROGRAMA"                       { yybegin(PROGRAM); return new Symbol(SimbolosMlg.PROGRAM_SEPERATOR, yycolumn,yyline,yytext());}
    
    "def"                              { return new Symbol(SimbolosMlg.DEF, yycolumn,yyline,yytext());}
    "intinput"                         { return new Symbol(SimbolosMlg.INTINPUT, yycolumn,yyline,yytext());}
    "charinput"                        { return new Symbol(SimbolosMlg.CHARINPUT, yycolumn,yyline,yytext());}
    "floatinput"                       { return new Symbol(SimbolosMlg.FLOATINPUT, yycolumn,yyline,yytext());}
    "return"                           { return new Symbol(SimbolosMlg.RETURN, yycolumn,yyline,yytext());}
    "print"                            { return new Symbol(SimbolosMlg.PRINT, yycolumn,yyline,yytext());}
    "for"                              { return new Symbol(SimbolosMlg.FOR, yycolumn,yyline,yytext());}
    "while"                            { return new Symbol(SimbolosMlg.WHILE, yycolumn,yyline,yytext());}
    "in"                               { return new Symbol(SimbolosMlg.IN, yycolumn,yyline,yytext());}
    "range"                            { return new Symbol(SimbolosMlg.RANGE, yycolumn,yyline,yytext());}
    "if"                               { return new Symbol(SimbolosMlg.IF, yycolumn,yyline,yytext());}
    "elif"                             { return new Symbol(SimbolosMlg.ELIF, yycolumn,yyline,yytext());}
    "else"                             { return new Symbol(SimbolosMlg.ELSE, yycolumn,yyline,yytext());}
    "True"                             { return new Symbol(SimbolosMlg.TRUE, yycolumn,yyline,yytext());}
    "False"                            { return new Symbol(SimbolosMlg.FALSE, yycolumn,yyline,yytext());}
    
    ","                                                     {return new Symbol(SimbolosMlg.COMMA, yycolumn,yyline,yytext());}
    
    ":"                                                     {return new Symbol(SimbolosMlg.COLON, yycolumn,yyline,yytext());}
    "="                                                     {return new Symbol(SimbolosMlg.EQUAL, yycolumn,yyline,yytext());}
    ("=="|"is")                                                     {return new Symbol(SimbolosMlg.EQUAL, yycolumn,yyline,yytext());}
    ("=!"|"is not")                                                    {return new Symbol(SimbolosMlg.DIFERENT_VB, yycolumn,yyline,yytext());}
    ">"                                                     {return new Symbol(SimbolosMlg.GREATER_THAN, yycolumn,yyline,yytext());}
    "<"                                                     {return new Symbol(SimbolosMlg.LESS_THAN, yycolumn,yyline,yytext());}
    (">=")                                             { return new Symbol(SimbolosMlg.GREATER_THAN_EQUAL, yycolumn,yyline,yytext());}
    ("<=")                                             { return new Symbol(SimbolosMlg.LESS_THAN_EQUAL, yycolumn,yyline,yytext());}
    "("                                                     {return new Symbol(SimbolosMlg.PARENTHESIS_A, yycolumn,yyline,yytext());}
    ")"                                                     {return new Symbol(SimbolosMlg.PARENTHESIS_B, yycolumn,yyline,yytext());}
    "+"                                    { return new Symbol(SimbolosMlg.PLUS, yycolumn,yyline,yytext());}
    "-"                                    { return new Symbol(SimbolosMlg.MINUS, yycolumn,yyline,yytext());}
    "*"                                    { return new Symbol(SimbolosMlg.MULTIPLICATION, yycolumn,yyline,yytext());}
    "/"                                    { return new Symbol(SimbolosMlg.DIVISION, yycolumn,yyline,yytext());}
    "%"                                    { return new Symbol(SimbolosMlg.MOD, yycolumn,yyline,yytext());}
   "not"                                                    {return new Symbol(SimbolosMlg.NOT, yycolumn,yyline,yytext());}
    "and"                                                     { return new Symbol(SimbolosMlg.OR, yycolumn,yyline,yytext());}
    "or"                                                    { return new Symbol(SimbolosMlg.AND, yycolumn,yyline,yytext());}
    
    "\"" [^\"] ~ "\""|"\"" [\b]*  "\""                                                   {  return new Symbol(SimbolosMlg.STRING, yycolumn,yyline,yytext());}
     "\'' [^\''] "\''                                                   {  return new Symbol(SimbolosMlg.CHAR, yycolumn,yyline,yytext());}
    ({Letra}|"_")({Letra}|{Digito}|"_")*                                {   return new Symbol(SimbolosMlg.ID, yycolumn,yyline,yytext());}
    ({Digito})+                                             { return new Symbol(SimbolosMlg.NUMBER, yycolumn,yyline,yytext());}
    ({Digito})+"."({Digito})+                               { return new Symbol(SimbolosMlg.DECIMAL, yycolumn,yyline,yytext());}

    {CommentC}                               {tac.addComment(yytext());}
    ({LineTerminator})+ ("\t")*                       { int in=0;
                                                    for (int i = 0; i < yytext().length(); i++) {
                                                    if (yytext().charAt(i)=='\t') {
                                                            in++; 
                                                    }
                                                    
                                                    }if(blockIndentation==in){
                                                        
                                                    }else if(blockIndentation>in){
                                                        Symbol sim=new Symbol(SimbolosMlg.DEDENT, yycolumn,yyline,yytext());
                                                        blockIndentation--;
                                                        yypushback(in);
                                                        return sim;
                                                    }else{
                                                        Symbol sim=new Symbol(SimbolosMlg.INDENT, yycolumn,yyline,yytext());
                                                        blockIndentation++;
                                                        yypushback(in);
                                                        
                                                        return sim;
                                                    }}
[ \b]                {}
    ("\t")+                                 { int in=0;
                                                    for (int i = 0; i < yytext().length(); i++) {
                                                    if (yytext().charAt(i)=='\t') {
                                                            in++; 
                                                    }
                                                    
                                                    }if(blockIndentation==in){
                                                        
                                                    }else if(blockIndentation>in){
                                                        Symbol sim=new Symbol(SimbolosMlg.DEDENT, yycolumn,yyline,yytext());
                                                        blockIndentation--;
                                                        yypushback(in);
                                                        
                                                        return sim;
                                                    }else{
                                                        Symbol sim=new Symbol(SimbolosMlg.INDENT, yycolumn,yyline,yytext());
                                                        blockIndentation++;
                                                        yypushback(in);
                                                        return sim;
                                                    }}
    [^]                                     { }
    
}

<PROGRAM>{
    "int"                                    { return new Symbol(SimbolosMlg.INTEGER_TYPE, yycolumn,yyline,yytext());}
    "float"                                    { return new Symbol(SimbolosMlg.FLOAT_TYPE, yycolumn,yyline,yytext());}
    "char"                                    { return new Symbol(SimbolosMlg.CHAR_TYPE, yycolumn,yyline,yytext());}
    "if"                              { return new Symbol(SimbolosMlg.IF, yycolumn,yyline,yytext());}
    "else"                              { return new Symbol(SimbolosMlg.ELSE, yycolumn,yyline,yytext());}
    "switch"                              { return new Symbol(SimbolosMlg.SWITCH, yycolumn,yyline,yytext());}
    "case"                              { return new Symbol(SimbolosMlg.CASE, yycolumn,yyline,yytext());}
    "default"                              { return new Symbol(SimbolosMlg.DEFAULT, yycolumn,yyline,yytext());}
    "break"                              { return new Symbol(SimbolosMlg.BREAK, yycolumn,yyline,yytext());}
    "for"                              { return new Symbol(SimbolosMlg.FOR, yycolumn,yyline,yytext());}
    "while"                              { return new Symbol(SimbolosMlg.WHILE, yycolumn,yyline,yytext());}
    "do"                              { return new Symbol(SimbolosMlg.DO, yycolumn,yyline,yytext());}
    "true"                              { return new Symbol(SimbolosMlg.TRUE, yycolumn,yyline,yytext());}
    "false"                              { return new Symbol(SimbolosMlg.FALSE, yycolumn,yyline,yytext());}
    "scanf"                              { return new Symbol(SimbolosMlg.SCAN_F, yycolumn,yyline,yytext());}
    "printf"                              { return new Symbol(SimbolosMlg.PRINTF, yycolumn,yyline,yytext());}
    "const"                              { return new Symbol(SimbolosMlg.CONST, yycolumn,yyline,yytext());}
    "clrscr"                              { return new Symbol(SimbolosMlg.CLRSCR, yycolumn,yyline,yytext());}
    "getch"                              { return new Symbol(SimbolosMlg.GETCH, yycolumn,yyline,yytext());}
    "void"                                    { return new Symbol(SimbolosMlg.VOID_J, yycolumn,yyline,yytext());}
    "main"                                    { return new Symbol(SimbolosMlg.MAIN, yycolumn,yyline,yytext());}
    "#include"|"#Include"                                    { return new Symbol(SimbolosMlg.INCLUDE, yycolumn,yyline,yytext());}
    "new"                                                    { return new Symbol(SimbolosMlg.NEW, yycolumn,yyline,yytext());}
    
    
    
    ";"                                    { return new Symbol(SimbolosMlg.SEMICOLON, yycolumn,yyline,yytext());}
    "{"                                    { return new Symbol(SimbolosMlg.LEFT_CB, yycolumn,yyline,yytext());}
    "}"                                    { return new Symbol(SimbolosMlg.RIGHT_CB, yycolumn,yyline,yytext());}
    "("                                                     { return new Symbol(SimbolosMlg.PARENTHESIS_A, yycolumn,yyline,yytext());}
    ")"                                                     { return new Symbol(SimbolosMlg.PARENTHESIS_B, yycolumn,yyline,yytext());}
    "["                                                     { return new Symbol(SimbolosMlg.LEFT_BRACKET, yycolumn,yyline,yytext());}
    "]"                                                     { return new Symbol(SimbolosMlg.RIGHT_BRACKET, yycolumn,yyline,yytext());}
    
    "+"                                    { return new Symbol(SimbolosMlg.PLUS, yycolumn,yyline,yytext());}
    "-"                                    { return new Symbol(SimbolosMlg.MINUS, yycolumn,yyline,yytext());}
    "*"                                    { return new Symbol(SimbolosMlg.MULTIPLICATION, yycolumn,yyline,yytext());}
    "/"                                    { return new Symbol(SimbolosMlg.DIVISION, yycolumn,yyline,yytext());}
    "%"                                    { return new Symbol(SimbolosMlg.MOD, yycolumn,yyline,yytext());}
    
    ":"                                                     { return new Symbol(SimbolosMlg.COLON, yycolumn,yyline,yytext());}
    "="                                                     { return new Symbol(SimbolosMlg.EQUAL, yycolumn,yyline,yytext());}
    "=!"                                                    { return new Symbol(SimbolosMlg.DIFERENT_VB, yycolumn,yyline,yytext());}
    ">"                                                     { return new Symbol(SimbolosMlg.GREATER_THAN, yycolumn,yyline,yytext());}
    "<"                                                     {return new Symbol(SimbolosMlg.LESS_THAN, yycolumn,yyline,yytext());}
    (">="|"=>")                                             {return new Symbol(SimbolosMlg.GREATER_THAN_EQUAL, yycolumn,yyline,yytext());}
    ("<="|"=<")                                             {return new Symbol(SimbolosMlg.LESS_THAN_EQUAL, yycolumn,yyline,yytext());}
    "!"                                                    { return new Symbol(SimbolosMlg.NOT, yycolumn,yyline,yytext());}
    ("||"|"|")                                                     {return new Symbol(SimbolosMlg.OR, yycolumn,yyline,yytext());}
    ("&&")                                                    { return new Symbol(SimbolosMlg.AND, yycolumn,yyline,yytext());}
    "&"                                                     { return new Symbol(SimbolosMlg.AMPERSON, yycolumn,yyline,yytext());}
     "."                                                     { return new Symbol(SimbolosMlg.POINT, yycolumn,yyline,yytext());}
    ","                                                     { return new Symbol(SimbolosMlg.COMMA, yycolumn,yyline,yytext());}
   
({Letra}|"_")({Letra}|{Digito}|"_")*                                {  return new Symbol(SimbolosMlg.ID, yycolumn,yyline,yytext());}
    ({Digito})+                                             { return new Symbol(SimbolosMlg.NUMBER, yycolumn,yyline,yytext());}
    ({Digito})+"."({Digito})+                               { return new Symbol(SimbolosMlg.DECIMAL, yycolumn,yyline,yytext());}
    "\'" [^\''] "\'"                                                   {return new Symbol(SimbolosMlg.CHAR, yycolumn,yyline,yytext());}
    "\"" [^\"] ~ "\""                                                   { return new Symbol(SimbolosMlg.STRING, yycolumn,yyline,yytext());}
    {LineTerminator}                        {}
     [ \t\b]                {}
    {CommentC}                               {tac.addComment(yytext());}
     .                                            {System.out.println("ERROR "+yytext()); return new Symbol(SimbolosMlg.ERROR,yycolumn,yyline,yytext());}
}