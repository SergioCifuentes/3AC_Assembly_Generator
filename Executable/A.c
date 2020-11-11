#include <stdlib.h>
#include <stdio.h>
float stack[1000];
float heap[1000];
int p;
int h;
float t1;
int t2;
float t3;
int t4;
float t5;
float t6;
int t7;
float t8;
float t9;
float t10;
int t11;
int t12;
int t13;
float t14;
float t15;
float t16;
int t17;
int t18;
int t19;
float t20;
int t21;
int t22;
float t23;
int t24;
float t25;
int t26;
float t27;
int t28;
int t29;
float t30;
int t31;
int t32;
int t33;
float t34;
int t35;
float t36;
int t37;
float t38;
int t39;
int t40;
float t41;
float t42;
float t43;
int t44;
float t45;
float t46;
float t47;
int t48;
int t49;
float t50;
int t51;
float t52;
int t53;
int t54;
float t55;
int t56;
float t57;
int t58;
int t59;
float t60;
int t61;
float t62;
int t63;
int t64;
float t65;
int t66;
float t67;
int t68;
float t69;
int t70;
float t71;
float t72;
int t73;
int t74;
float t75;
float t76;
float t77;
float t78;
int t79;
int t80;
float t81;
int t82;
float t83;
float t84;
float t85;
int t86;
float t87;
float t88;
float t89;
int t90;
int t91;
int t92;
float t93;
int t94;
int t95;
float t96;
int t97;
float t98;
float t99;
float t100;
int t101;
int t102;
float t103;
int t104;
float t105;
float t106;
float t107;
int t108;
float t109;
int t110;
float t111;
int t112;
float t113;
int t114;
float t115;
float t116;
float t117;
int t118;
int t119;
float t120;
int t121;
int t122;
float t123;
float t124;
float t125;
int t126;
float t127;
int t128;
int t129;
float t130;
int t131;
float t132;
float t133;
int t134;
float t135;
int t136;
int t137;
float t138;
int t139;
float t140;
int t141;
float t142;
float t143;
float t144;
int t145;
int t146;
int t147;
int t148;
int t149;
float t150;
void VB_Saludo(){
printf ("Segundo Proyecto de Compiladores 2");
printf ("Segundo Proyecto de Compiladores 2");
t1=0;
t2=p+0;
stack[t2]=t1;
etv5:
t3=8;
t4=p+0;
t5=stack[t4];
if (t5 = t3) goto etv2;
goto etv3;
etv2:
goto etv4;
etv3:
t7=p+0;
t8=stack[t7];
t9=2;
t10=t8+t9;
t11=p+0;
stack[t11]=t10;
t12=p+0;
t13=stack[t12];
stack[t12]=t13+1;
goto etv5;
etv4:
;}
void VB_Factorial(){
t14=5;
t17=p+1;
stack[t17]=t14;
t15=75;
t18=p+2;
stack[t18]=t15;
t16=1;
t19=p+3;
stack[t19]=t16;
t20=3;
t21=p+5;
stack[t21]=t20;
printf ("ingreseCaracter");
t22=p+6;
scanf ("%f",&(t23));
stack[t22]=t23;
printf ("hola");
printf ("ingreseCaracter");
t24=p+3;
scanf ("%f",&(t25));
stack[t24]=t25;
t26=p+0;
t27=stack[t26];
t28=p+6;
stack[t28]=t27;
t29=p+6;
t30=stack[t29];
t31=p+7;
stack[t31]=t30;
goto etv6;
etv6:
;}
void JAVA_Calc_Calc_int(){
t32=p+0;
stack[t32]=h;
h=h+2;
t33=p+1;
t34=stack[t33];
t35=p+0;
t36=stack[t35];
t37=t36+0;
heap[t37]=t34;
t38=0;
t39=p+2;
stack[t39]=t38;
etv11:
t40=p+2;
t41=stack[t40];
t42=10;
if (t41 < t42) goto etv8;
goto etv9;
etv8:
t49=p+2;
t50=stack[t49];
printf ("%f",t50);
printf ("\n");
t44=p+2;
t45=stack[t44];
t46=1;
t47=t45+t46;
t48=p+2;
stack[t48]=t47;
goto etv11;
etv9:
goto etv10;
etv10:
printf ("este es el valor de n1 y n2:");
printf ("\n");
printf ("\n");
t51=p+1;
t52=stack[t51];
printf ("%f",t52);
printf ("\n");
;}
void JAVA_Calc_Calc_int_int(){
t53=p+0;
stack[t53]=h;
h=h+2;
t54=p+1;
t55=stack[t54];
t56=p+0;
t57=stack[t56];
t58=t57+0;
heap[t58]=t55;
t59=p+2;
t60=stack[t59];
t61=p+0;
t62=stack[t61];
t63=t62+1;
heap[t63]=t60;
//for(int i=0;i<=2;i=i+1){
//this.n1=this.n1+1		
//}
;}
void JAVA_Calc_sumar_int_int(){
t64=p+0;
t65=stack[t64];
t66=t65+0;
t67=heap[t66];
t68=p+0;
t69=stack[t68];
t70=t69+1;
t71=heap[t70];
t72=t67+t71;
t73=p+1;
stack[t73]=t72;
goto etv12;
etv12:
;}
void JAVA_Calc_factorial_int(){
t74=p+2;
t75=stack[t74];
t76=1;
if (t75 <= t76) goto etv15;
goto etv16;
etv15:
t78=1;
t79=p+1;
stack[t79]=t78;
goto etv13;
etv16:
goto etv17;
etv17:
t80=p+2;
t81=stack[t80];
t82=p+2;
t83=stack[t82];
t84=1;
t85=t83-t84;
t89 = p + 3;
t90=t89+2;
stack[t90]=t85;
t86 = p + 0;
t87=stack[t86];
t88 = p + 3;
t91=t88+0;
stack[t91]=t87;
p = p + 3;
JAVA_Calc_factorial_int();
t92=p+1;
p = p - 3;
t93=t81*stack[t92];
//* factorial();
t94=p+1;
stack[t94]=t93;
goto etv13;
etv13:
;}
void PY_edadmeses(){
printf ("Ingrese su edad:");
t95=p+0;
scanf ("%f",&(t96));
stack[t95]=t96;
t97=p+0;
t98=stack[t97];
t99=12;
t100=t98*t99;
t101=p+1;
stack[t101]=t100;
printf ("meses = ");
t102=p+1;
t103=stack[t102];
printf ("%f",t103);
t104=p+0;
t105=stack[t104];
t106=12;
if (t105 > t106) goto etv20;
goto etv21;
etv20:
printf ("edad = ");
t108=p+0;
t109=stack[t108];
printf ("%f",t109);
goto etv22;
etv21:
goto etv22;
etv22:
etv18:
;}
void PY_holaMundo(){
printf ("hola MUNDO");
etv23:
;}
void main(){
// Constantes
t110=p+0;
stack[t110]=15;
// Variables globales
t111=8;
t112=p+3;
stack[t112]=t111;
t113='y';
t114=p+244;
stack[t114]=t113;
t116=3/2;
t115=8+t116;
t118=p+245;
stack[t118]=t115;
t117=2;
t119=p+246;
stack[t119]=t117;
t120=5+2;
t121=p+1;
stack[t121]=t120;
t122=p+1;
t123=stack[t122];
t124=6;
t125=t123+t124;
t126=p+2;
stack[t126]=t125;
t127=2;
t128=p+247;
stack[t128]=t127;
//Arreglo1[0][3][1]=6;
t129=p+245;
t130=stack[t129];
t131=p+247;
t132=stack[t131];
t133 = p + 250;
t134=t133+1;
t135 = p + 250;
t136=t135+2;
stack[t134]=t130;
stack[t136]=t132;
p = p + 250;
JAVA_Calc_Calc_int_int();
p = p - 250;
t137 = p + 250;
t138=t137+0;
t139=p+248;
stack[t139]=stack[t137];
//z=VB.Factorial(x);
t140=5;
t144 = p + 250;
t145=t144+2;
stack[t145]=t140;
t141=p+248;
t142=stack[t141];
t143 = p + 250;
t146=t143+0;
stack[t146]=t142;
p = p + 250;
JAVA_Calc_factorial_int();
t147=p+1;
p = p - 250;
t148=p+249;
stack[t148]=stack[t147];
t148=p+249;
stack[t148]=stack[t147];
t149=p+249;
t150=stack[t149];
printf ("%f",t150);
printf ("prueba");
//PY.edadmeses(x,3);
//for(x=4;x>2; x=x+1){
//	scanf("Hola %d", &x);
//	w=w+7;
//}
getchar();
;}
