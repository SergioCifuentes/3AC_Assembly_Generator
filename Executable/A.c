#include <stdlib.h>
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
int t52;
float t53;
int t54;
float t55;
int t56;
int t57;
float t58;
int t59;
float t60;
int t61;
int t62;
float t63;
int t64;
float t65;
int t66;
float t67;
int t68;
float t69;
float t70;
int t71;
int t72;
float t73;
float t74;
float t75;
float t76;
int t77;
int t78;
float t79;
int t80;
float t81;
float t82;
float t83;
int t84;
float t85;
float t86;
float t87;
int t88;
int t89;
int t90;
float t91;
int t92;
int t93;
float t94;
int t95;
float t96;
float t97;
float t98;
int t99;
int t100;
float t101;
int t102;
float t103;
float t104;
float t105;
int t106;
float t107;
float t108;
int t109;
float t110;
int t111;
float t112;
float t113;
float t114;
int t115;
int t116;
float t117;
int t118;
int t119;
float t120;
float t121;
float t122;
int t123;
float t124;
int t125;
int t126;
float t127;
int t128;
float t129;
float t130;
int t131;
float t132;
int t133;
int t134;
float t135;
int t136;
float t137;
int t138;
float t139;
float t140;
float t141;
int t142;
int t143;
int t144;
int t145;
int t146;
float t147;
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
printf ("\n");
;}
void JAVA_Calc_Calc_int_int(){
t51=p+0;
stack[t51]=h;
h=h+2;
t52=p+1;
t53=stack[t52];
t54=p+0;
t55=stack[t54];
t56=t55+0;
heap[t56]=t53;
t57=p+2;
t58=stack[t57];
t59=p+0;
t60=stack[t59];
t61=t60+1;
heap[t61]=t58;
//for(int i=0;i<=2;i=i+1){
//this.n1=this.n1+1		
//}
;}
void JAVA_Calc_sumar_int_int(){
t62=p+0;
t63=stack[t62];
t64=t63+0;
t65=heap[t64];
t66=p+0;
t67=stack[t66];
t68=t67+1;
t69=heap[t68];
t70=t65+t69;
t71=p+1;
stack[t71]=t70;
goto etv12;
etv12:
;}
void JAVA_Calc_factorial_int(){
t72=p+2;
t73=stack[t72];
t74=1;
if (t73 <= t74) goto etv15;
goto etv16;
etv15:
t76=1;
t77=p+1;
stack[t77]=t76;
goto etv13;
etv16:
goto etv17;
etv17:
t78=p+2;
t79=stack[t78];
t80=p+2;
t81=stack[t80];
t82=1;
t83=t81-t82;
t87 = p + 3;
t88=t87+2;
stack[t88]=t83;
t84 = p + 0;
t85=stack[t84];
t86 = p + 3;
t89=t86+0;
stack[t89]=t85;
p = p + 3;
JAVA_Calc_factorial_int();
t90=p+1;
p = p - 3;
t91=t79*stack[t90];
//* factorial();
t92=p+1;
stack[t92]=t91;
goto etv13;
etv13:
;}
void PY_edadmeses(){
printf ("Ingrese su edad:");
t93=p+0;
scanf ("%f",&(t94));
stack[t93]=t94;
t95=p+0;
t96=stack[t95];
t97=12;
t98=t96*t97;
t99=p+1;
stack[t99]=t98;
printf ("meses = ");
t100=p+1;
t101=stack[t100];
printf ("%f",t101);
t102=p+0;
t103=stack[t102];
t104=12;
if (t103 > t104) goto etv20;
goto etv21;
etv20:
printf ("edad = ");
t106=p+0;
t107=stack[t106];
printf ("%f",t107);
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
// Variables globales
t108=8;
t109=p+2;
stack[t109]=t108;
t110='y';
t111=p+243;
stack[t111]=t110;
t113=3/2;
t112=8+t113;
t115=p+244;
stack[t115]=t112;
t114=2;
t116=p+245;
stack[t116]=t114;
t117=5+2;
t118=p+0;
stack[t118]=t117;
t119=p+0;
t120=stack[t119];
t121=6;
t122=t120+t121;
t123=p+1;
stack[t123]=t122;
t124=2;
t125=p+246;
stack[t125]=t124;
//Arreglo1[0][3][1]=6;
t126=p+244;
t127=stack[t126];
t128=p+246;
t129=stack[t128];
t130 = p + 249;
t131=t130+1;
t132 = p + 249;
t133=t132+2;
stack[t131]=t127;
stack[t133]=t129;
p = p + 249;
JAVA_Calc_Calc_int_int();
p = p - 249;
t134 = p + 249;
t135=t134+0;
t136=p+247;
stack[t136]=stack[t134];
//z=VB.Factorial(x);
t137=5;
t141 = p + 249;
t142=t141+2;
stack[t142]=t137;
t138=p+247;
t139=stack[t138];
t140 = p + 249;
t143=t140+0;
stack[t143]=t139;
p = p + 249;
JAVA_Calc_factorial_int();
t144=p+1;
p = p - 249;
t145=p+248;
stack[t145]=stack[t144];
t145=p+248;
stack[t145]=stack[t144];
t146=p+248;
t147=stack[t146];
printf ("%f",t147);
printf ("prueba");
//PY.edadmeses(x,3);
//for(x=4;x>2; x=x+1){
//	scanf("Hola %d", &x);
//	w=w+7;
//}
getchar();
;}
