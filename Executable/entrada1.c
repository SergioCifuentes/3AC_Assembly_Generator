float stack[1000];
float heap[1000];
int p;
int h;
float t1;
float t2;
float t3;
float t4;
int t5;
int t6;
float t7;
int t8;
int t9;
float t10;
float t11;
float t12;
int t13;
float t14;
float t15;
float t16;
int t17;
float t18;
int t19;
float t20;
int t21;
float t22;
int t23;
float t24;
int t25;
float t26;
float t27;
float t28;
int t29;
float t30;
float t31;
float t32;
int t33;
float t34;
float t35;
float t36;
int t37;
float t38;
float t39;
float t40;
int t41;
float t42;
float t43;
float t44;
int t45;
float t46;
float t47;
float t48;
int t49;
float t50;
float t51;
float t52;
int t53;
float t54;
float t55;
float t56;
int t57;
float t58;
float t59;
float t60;
int t61;
float t62;
float t63;
float t64;
int t65;
float t66;
float t67;
int t68;
float t69;
int t70;
int t71;
float t72;
int t73;
float t74;
float t75;
int t76;
float t77;
float t78;
float t79;
int t80;
int t81;
float t82;
int t83;
float t84;
float t85;
int t86;
float t87;
int t88;
float t89;
int t90;
float t91;
int t92;
int t93;
float t94;
float t95;
float t96;
int t97;
float t98;
float t99;
float t100;
int t101;
float t102;
float t103;
int t104;
int t105;
float t106;
float t107;
float t108;
float t109;
int t110;
float t111;
int t112;
float t113;
float t114;
int t115;
int t116;
int t117;
float t118;
int t119;
float t120;
float t121;
float t122;
float t123;
int t124;
float t125;
int t126;
int t127;
float t128;
float t129;
float t130;
int t131;
float t132;
float t133;
int t134;
int t135;
float t136;
float t137;
int t138;
int t139;
int t140;
int t141;
float t142;
int t143;
int t144;
float t145;
float t146;
int t147;
int t148;
int t149;
int t150;
float t151;
float t152;
int t153;
float t154;
int t155;
int t156;
float t157;
int t158;
float t159;
float t160;
int t161;
float t162;
float t163;
float t164;
int t165;
int t166;
float t167;
int t168;
float t169;
float t170;
int t171;
float t172;
int t173;
float t174;
int t175;
void VB_Incremento(){
t1=p+0;
t2=stack[t1];
t3=1;
t4=t2+t3;
t5=p+0;
stack[t5]=t4;
t6=p+0;
t7=stack[t6];
t8=p+1;
stack[t8]=t7;
goto etv1;
etv1:
;}
void PY_Mensaje(){
t9=p+0;
t10=stack[t9];
t11=1;
if (t10 = t11) goto etv4;
goto etv5;
etv4:
printf ("Arreglo antes de ordenarse");
goto etv6;
etv5:
t13=p+0;
t14=stack[t13];
t15=2;
if (t14 = t15) goto etv8;
goto etv9;
etv8:
printf ("Arreglo depues de ordenarse");
goto etv10;
etv9:
printf ("default");
goto etv10;
etv10:
goto etv6;
etv6:
etv2:
;}
void PY_Mostrar(){
printf ("arreglo[");
t17=p+0;
t18=stack[t17];
printf ("%f",t18);
printf ("] = ");
t19=p+1;
t20=stack[t19];
printf ("%f",t20);
etv11:
;}
void main(){
/* ---------------------------------------------
    Declaracion de Constantes
   --------------------------------------------- */
t21=p+0;
/* ---------------------------------------------
    Declaracion de Variables Globales
   --------------------------------------------- */
t22=0;
t23=p+11;
stack[t23]=t22;
t24=0;
t25=p+12;
stack[t25]=t24;
// Inicializar arreglo
t26=0;
t27=7;
t28=p+1;
t29=t28+t26;
stack[t29]=t27;
t30=1;
t31=14;
t32=p+1;
t33=t32+t30;
stack[t33]=t31;
t34=2;
t35=18;
t36=p+1;
t37=t36+t34;
stack[t37]=t35;
t38=3;
t39=19;
t40=p+1;
t41=t40+t38;
stack[t41]=t39;
t42=4;
t43=21;
t44=p+1;
t45=t44+t42;
stack[t45]=t43;
t46=5;
t47=2;
t48=p+1;
t49=t48+t46;
stack[t49]=t47;
t50=6;
t51=9;
t52=p+1;
t53=t52+t50;
stack[t53]=t51;
t54=7;
t55=24;
t56=p+1;
t57=t56+t54;
stack[t57]=t55;
t58=8;
t59=29;
t60=p+1;
t61=t60+t58;
stack[t61]=t59;
t62=9;
t63=47;
t64=p+1;
t65=t64+t62;
stack[t65]=t63;
t66=1;
t67 = p + 14;
t68=t67+0;
stack[t68]=t66;
p = p + 14;
PY_Mensaje();
p = p - 14;
t69=0;
t70=p+11;
stack[t70]=t69;
etv16:
t71=p+11;
t72=stack[t71];
t73=p+0;
t74=stack[t73];
if (t72 < t74) goto etv13;
goto etv14;
etv13:
t81=p+11;
t82=stack[t81];
t83=p+11;
t84=stack[t83];
t85=p+1;
t86=t85+t84;
t87 = p + 14;
t88=t87+0;
t89 = p + 14;
t90=t89+1;
stack[t88]=t82;
stack[t90]=stack[t86];
p = p + 14;
PY_Mostrar();
p = p - 14;
t76=p+11;
t77=stack[t76];
t78=1;
t79=t77+t78;
t80=p+11;
stack[t80]=t79;
goto etv16;
etv14:
goto etv15;
etv15:
t91=0;
t92=p+11;
stack[t92]=t91;
// Ordenar el arreglo
// Inicio while 1
// fin while
etv30:
t93=p+11;
t94=stack[t93];
t95=10;
if (t94 < t95) goto etv18;
goto etv19;
etv18:
// fin while
// contador 1
etv29:
// Inicio while 2
t97=p+12;
t98=stack[t97];
t99=10;
if (t98 < t99) goto etv22;
goto etv23;
etv22:
// Inicio if
t101=p+12;
t102=stack[t101];
t103=p+1;
t104=t103+t102;
t105=p+12;
t106=stack[t105];
t107=1;
t108=t106+t107;
t109=p+1;
t110=t109+t108;
// fin if
// contador 2
if (stack[t104] < stack[t110]) goto etv26;
goto etv27;
etv26:
t112=p+12;
t113=stack[t112];
t114=p+1;
t115=t114+t113;
t116=p+13;
stack[t116]=stack[t115];
t117=p+12;
t118=stack[t117];
t119=p+12;
t120=stack[t119];
t121=1;
t122=t120+t121;
t123=p+1;
t124=t123+t122;
t125=p+1;
t126=t125+t118;
stack[t126]=stack[t124];
t127=p+12;
t128=stack[t127];
t129=1;
t130=t128+t129;
t131=p+13;
t132=stack[t131];
t133=p+1;
t134=t133+t130;
stack[t134]=t132;
goto etv28;
etv27:
goto etv28;
etv28:
t135=p+12;
t136=stack[t135];
t137 = p + 14;
t138=t137+0;
stack[t138]=t136;
p = p + 14;
VB_Incremento();
t139=p+1;
t140=stack[t139];
p = p - 14;
t141=p+12;
stack[t141]=t140;
goto etv29;
etv23:
goto etv24;
etv24:
t142=0;
t143=p+12;
stack[t143]=t142;
t144=p+11;
t145=stack[t144];
t146 = p + 14;
t147=t146+0;
stack[t147]=t145;
p = p + 14;
VB_Incremento();
t148=p+1;
t149=stack[t148];
p = p - 14;
t150=p+11;
stack[t150]=t149;
goto etv30;
etv19:
goto etv20;
etv20:
t151=2;
t152 = p + 14;
t153=t152+0;
stack[t153]=t151;
p = p + 14;
PY_Mensaje();
p = p - 14;
// Mostrar el arreglo ordenado
t154=0;
t155=p+11;
stack[t155]=t154;
etv35:
t156=p+11;
t157=stack[t156];
t158=p+0;
t159=stack[t158];
if (t157 < t159) goto etv32;
goto etv33;
etv32:
t166=p+11;
t167=stack[t166];
t168=p+11;
t169=stack[t168];
t170=p+1;
t171=t170+t169;
t172 = p + 14;
t173=t172+0;
t174 = p + 14;
t175=t174+1;
stack[t173]=t167;
stack[t175]=stack[t171];
p = p + 14;
PY_Mostrar();
p = p - 14;
t161=p+11;
t162=stack[t161];
t163=1;
t164=t162+t163;
t165=p+11;
stack[t165]=t164;
goto etv35;
etv33:
goto etv34;
etv34:
getchar();
;}
