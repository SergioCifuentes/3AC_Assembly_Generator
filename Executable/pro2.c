#include <stdio.h>
#include <stdlib.h>
float stack[1000];
float heap[1000];
int p;
int h;
float t1;
int t2;
int t3;
float t4;
int t5;
float t6;
float t7;
int t8;
int t9;
float t10;
int t11;
float t12;
int t13;
int t14;
float t15;
int t16;
float t17;
float t18;
int t19;
int t20;
float t21;
int t22;
float t23;
int t24;
int t25;
float t26;
int t27;
float t28;
float t29;
int t30;
int t31;
float t32;
int t33;
int t34;
float t35;
int t36;
float t37;
float t38;
int t39;
int t40;
int t41;
int t42;
int t43;
float t44;
int t45;
int t46;
float t47;
int t48;
int t49;
int t50;
float t51;
int t52;
float t53;
int t54;
float t55;
int t56;
float t57;
int t58;
float t59;
int t60;
float t61;
int t62;
float t63;
int t64;
float t65;
int t66;
float t67;
int t68;
int t69;
float t70;
int t71;
float t72;
int t73;
float t74;
int t75;
float t76;
float t77;
int t78;
float t79;
int t80;
int t81;
int t82;
int t83;
int t84;
float t85;
int t86;
float t87;
float t88;
int t89;
float t90;
int t91;
int t92;
int t93;
int t94;
int t95;
float t96;
int t97;
float t98;
float t99;
int t100;
float t101;
int t102;
int t103;
int t104;
int t105;
int t106;
float t107;
int t108;
float t109;
float t110;
int t111;
float t112;
int t113;
int t114;
int t115;
int t116;
int t117;
float t118;
int t119;
float t120;
float t121;
int t122;
float t123;
int t124;
int t125;
int t126;
int t127;
int t128;
float t129;
int t130;
float t131;
int t132;
float t133;
int t134;
float t135;
int t136;
float t137;
int t138;
float t139;
int t140;
float t141;
int t142;
float t143;
int t144;
float t145;
int t146;
float t147;
int t148;
float t149;
int t150;
float t151;
float t152;
float t153;
int t154;
float t155;
float t156;
float t157;
float t158;
int t159;
float t160;
float t161;
int t162;
float t163;
float t164;
float t165;
float t166;
int t167;
float t168;
float t169;
int t170;
float t171;
float t172;
float t173;
float t174;
int t175;
float t176;
float t177;
int t178;
float t179;
float t180;
float t181;
float t182;
int t183;
float t184;
float t185;
float t186;
float t187;
float t188;
int t189;
int t190;
float t191;
float t192;
float t193;
float t194;
float t195;
int t196;
int t197;
float t198;
float t199;
float t200;
float t201;
float t202;
int t203;
int t204;
float t205;
float t206;
float t207;
float t208;
float t209;
int t210;
int t211;
float t212;
int t213;
int t214;
float t215;
float t216;
float t217;
int t218;
float t219;
float t220;
float t221;
int t222;
int t223;
float t224;
float t225;
float t226;
int t227;
float t228;
int t229;
float t230;
int t231;
float t232;
int t233;
float t234;
int t235;
float t236;
int t237;
float t238;
int t239;
float t240;
int t241;
float t242;
void VB_SUMAR(){
t1=0;
t2=p+2;
stack[t2]=t1;
t3=p+0;
t4=stack[t3];
t5=p+1;
t6=stack[t5];
t7=t4+t6;
t8=p+2;
stack[t8]=t7;
t9=p+2;
t10=stack[t9];
t11=p+3;
stack[t11]=t10;
goto etv1;
etv1:
;}
void VB_RESTAR(){
t12=0;
t13=p+2;
stack[t13]=t12;
t14=p+0;
t15=stack[t14];
t16=p+1;
t17=stack[t16];
t18=t15-t17;
t19=p+2;
stack[t19]=t18;
t20=p+2;
t21=stack[t20];
t22=p+3;
stack[t22]=t21;
goto etv2;
etv2:
;}
void VB_MULTIPLICAR(){
t23=0;
t24=p+2;
stack[t24]=t23;
t25=p+0;
t26=stack[t25];
t27=p+1;
t28=stack[t27];
t29=t26*t28;
t30=p+2;
stack[t30]=t29;
t31=p+2;
t32=stack[t31];
t33=p+3;
stack[t33]=t32;
goto etv3;
etv3:
;}
void PY_DIVIDIR(){
t34=p+0;
t35=stack[t34];
t36=p+1;
t37=stack[t36];
t38=t35/t37;
t39=p+2;
stack[t39]=t38;
goto etv4;
etv4:
;}
void PY_MODULO(){
t40=p+0;
t41=stack[t40];
t42=p+1;
t43=stack[t42];
t44=t41%t43;
t45=p+2;
stack[t45]=t44;
t46=p+2;
t47=stack[t46];
t48=p+3;
stack[t48]=t47;
goto etv5;
etv5:
;}
void main(){
t49=p+0;
stack[t49]=2;
t50=p+1;
stack[t50]=2;
t51=0;
t52=p+6;
stack[t52]=t51;
t53=1;
t54=p+7;
stack[t54]=t53;
t55=3;
t56=p+8;
stack[t56]=t55;
t57=4;
t58=p+9;
stack[t58]=t57;
t59=0;
t60=p+17;
stack[t60]=t59;
printf("\e[1;1H\e[2J");
t61=1+2;
t62=p+13;
stack[t62]=t61;
t63=0;
t64=p+14;
stack[t64]=t63;
t65=86;
t66=p+15;
stack[t66]=t65;
t67=100;
t68=p+16;
stack[t68]=t67;
printf ("Bienvenido");
printf ("Archivo de prueba...");
printf ("Ingrese el primer valor entero");
t69=p+10;
scanf ("%f",&(t70));
stack[t69]=t70;
printf ("Ingrese el segudo valor entero ");
t71=p+11;
scanf ("%f",&(t72));
stack[t71]=t72;
t73=p+10;
t74=stack[t73];
t75=p+11;
t76=stack[t75];
t77 = p + 18;
t78=t77+0;
t79 = p + 18;
t80=t79+1;
stack[t78]=t74;
stack[t80]=t76;
p = p + 18;
PY_MODULO();
t81=p+3;
t82=stack[t81];
p = p - 18;
t83=p+16;
stack[t83]=t82;
//la variable correcta es Respuesta4
t84=p+10;
t85=stack[t84];
t86=p+11;
t87=stack[t86];
t88 = p + 18;
t89=t88+0;
t90 = p + 18;
t91=t90+1;
stack[t89]=t85;
stack[t91]=t87;
p = p + 18;
VB_SUMAR();
t92=p+3;
t93=stack[t92];
p = p - 18;
t94=p+13;
stack[t94]=t93;
t95=p+10;
t96=stack[t95];
t97=p+11;
t98=stack[t97];
t99 = p + 18;
t100=t99+0;
t101 = p + 18;
t102=t101+1;
stack[t100]=t96;
stack[t102]=t98;
p = p + 18;
VB_RESTAR();
t103=p+3;
t104=stack[t103];
p = p - 18;
t105=p+14;
stack[t105]=t104;
t106=p+10;
t107=stack[t106];
t108=p+11;
t109=stack[t108];
t110 = p + 18;
t111=t110+0;
t112 = p + 18;
t113=t112+1;
stack[t111]=t107;
stack[t113]=t109;
p = p + 18;
VB_MULTIPLICAR();
t114=p+3;
t115=stack[t114];
p = p - 18;
t116=p+15;
stack[t116]=t115;
t117=p+10;
t118=stack[t117];
t119=p+11;
t120=stack[t119];
t121 = p + 18;
t122=t121+0;
t123 = p + 18;
t124=t123+1;
stack[t122]=t118;
stack[t124]=t120;
p = p + 18;
PY_DIVIDIR();
t125=p+2;
t126=stack[t125];
p = p - 18;
t127=p+12;
stack[t127]=t126;
t128=p+10;
t129=stack[t128];
printf ("%f",t129);
printf (" ");
t130=p+11;
t131=stack[t130];
printf ("%f",t131);
printf (" ");
printf ("+ es igual a ");
t132=p+13;
t133=stack[t132];
printf ("%f",t133);
t134=p+10;
t135=stack[t134];
printf ("%f",t135);
printf (" ");
t136=p+11;
t137=stack[t136];
printf ("%f",t137);
printf (" ");
printf ("- es igual a ");
t138=p+14;
t139=stack[t138];
printf ("%f",t139);
t140=p+10;
t141=stack[t140];
printf ("%f",t141);
printf (" ");
t142=p+11;
t143=stack[t142];
printf ("%f",t143);
printf (" ");
printf ("* es igual a ");
t144=p+15;
t145=stack[t144];
printf ("%f",t145);
t146=p+10;
t147=stack[t146];
printf ("%f",t147);
printf (" ");
t148=p+11;
t149=stack[t148];
printf ("%f",t149);
printf (" ");
printf ("/ es igual a ");
t150=p+16;
t151=stack[t150];
printf ("%f",t151);
t152=0;
t153=0;
t154=p+13;
t155=stack[t154];
t156=t153*2;
t157=t156+t152;
t158=p+2;
t159=t158+t157;
stack[t159]=t155;
t160=0;
t161=1;
t162=p+14;
t163=stack[t162];
t164=t161*2;
t165=t164+t160;
t166=p+2;
t167=t166+t165;
stack[t167]=t163;
t168=1;
t169=0;
t170=p+15;
t171=stack[t170];
t172=t169*2;
t173=t172+t168;
t174=p+2;
t175=t174+t173;
stack[t175]=t171;
t176=1;
t177=1;
t178=p+16;
t179=stack[t178];
t180=t177*2;
t181=t180+t176;
t182=p+2;
t183=t182+t181;
stack[t183]=t179;
t184=0;
t185=0;
t186=t185*2;
t187=t186+t184;
t188=p+2;
t189=t188+t187;
t190=p+6;
stack[t190]=stack[t189];
t191=0;
t192=1;
t193=t192*2;
t194=t193+t191;
t195=p+2;
t196=t195+t194;
t197=p+7;
stack[t197]=stack[t196];
t198=1;
t199=0;
t200=t199*2;
t201=t200+t198;
t202=p+2;
t203=t202+t201;
t204=p+8;
stack[t204]=stack[t203];
t205=1;
t206=1;
t207=t206*2;
t208=t207+t205;
t209=p+2;
t210=t209+t208;
t211=p+9;
stack[t211]=stack[t210];
t212=0;
t213=p+17;
stack[t213]=t212;
etv14:
t214=p+17;
t215=stack[t214];
t216=2;
if (t215 < t216) goto etv7;
goto etv8;
etv7:
// error en el for
t223=p+17;
t224=stack[t223];
t225=0;
if (t224 == t225) goto etv11;
goto etv12;
etv11:
printf ("Arreglo [ ");
t227=p+17;
t228=stack[t227];
printf ("%f",t228);
printf (" ] [ 0 ] su contenido es");
t229=p+6;
t230=stack[t229];
printf ("%f",t230);
printf ("Arreglo [ ");
t231=p+17;
t232=stack[t231];
printf ("%f",t232);
printf (" ] [ 1] su contenido es");
t233=p+7;
t234=stack[t233];
printf ("%f",t234);
goto etv13;
etv12:
printf ("Arreglo [ ");
t235=p+17;
t236=stack[t235];
printf ("%f",t236);
printf (" ] [ 0 ] su contenido es");
t237=p+8;
t238=stack[t237];
printf ("%f",t238);
printf ("Arreglo [ ");
t239=p+17;
t240=stack[t239];
printf ("%f",t240);
printf (" ] [ 1] su contenido es");
t241=p+9;
t242=stack[t241];
printf ("%f",t242);
goto etv13;
etv13:
t218=p+17;
t219=stack[t218];
t220=1;
t221=t219+t220;
t222=p+17;
stack[t222]=t221;
goto etv14;
etv8:
goto etv9;
etv9:
getchar();
getchar();
;}