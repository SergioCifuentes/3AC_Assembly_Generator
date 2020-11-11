section .data
	stack times 1000 db 0
	heap times 1000 db 0
	labelText0: db 'Segundo Proyecto de Compiladores 2',0
	labelText1: db 'Segundo Proyecto de Compiladores 2',0
	labelText2: db 'ingreseCaracter',0
	labelText3: db 'hola',0
	labelText4: db 'ingreseCaracter',0
	labelText5: db '\n',0
	labelText6: db 'este es el valor de n1 y n2:',0
	labelText7: db '\n',0
	labelText8: db '\n',0
	labelText9: db '\n',0
	labelText10: db 'Ingrese su edad:',0
	labelText11: db 'meses = ',0
	labelText12: db 'edad = ',0
	labelText13: db 'hola MUNDO',0
	labelText14: db 'prueba',0
section .text
	global _start
_printSubroutine:
	push rax
	mov rbx, 0
_printSubroutineLoop:
	inc rax
	inc rbx
	mov cl, [rax]
	cmp cl, 0
	jne _printSubroutineLoop
	mov rax, 1
	mov rdi, 1
	pop rsi
	mov rdx, rbx
	syscall
	ret
_VB_Saludo:
	mov rax, labelText0
	call _printSubroutine
	mov rax, labelText1
	call _printSubroutine
	mov eax, 0
	mov [t1], eax
	mov eax, p
	add eax, t2
	mov t2, eax
	mov eax, t1
	mov eax, t1
	mov [stack+t2], eax
_etv5:
	mov eax, 8
	mov [t3], eax
	mov eax, p
	add eax, t4
	mov t4, eax
	mov eax, [stack+t4]
	mov [t5], eax
	mov eax, t3
	cmp t5 t3
	je etv2
	mov t5, eax
	jmp _etv3
_etv2:
	jmp _etv4
_etv3:
	mov eax, p
	add eax, t7
	mov t7, eax
	mov eax, [stack+t7]
	mov [t8], eax
	mov eax, 2
	mov [t9], eax
	mov eax, t8
	add eax, t10
	mov t10, eax
	mov eax, p
	add eax, t11
	mov t11, eax
	mov eax, t10
	mov eax, t10
	mov [stack+t11], eax
	mov eax, p
	add eax, t12
	mov t12, eax
	mov eax, [stack+t12]
	mov [t13], eax
	mov eax, t13
	mov eax, t13
	add eax, [stack+t12]
	mov [stack+t12], eax
	jmp _etv5
_etv4:
	ret
_VB_Factorial:
	mov eax, 5
	mov [t14], eax
	mov eax, p
	add eax, t17
	mov t17, eax
	mov eax, t14
	mov eax, t14
	mov [stack+t17], eax
	mov eax, 75
	mov [t15], eax
	mov eax, p
	add eax, t18
	mov t18, eax
	mov eax, t15
	mov eax, t15
	mov [stack+t18], eax
	mov eax, 1
	mov [t16], eax
	mov eax, p
	add eax, t19
	mov t19, eax
	mov eax, t16
	mov eax, t16
	mov [stack+t19], eax
	mov eax, 3
	mov [t20], eax
	mov eax, p
	add eax, t21
	mov t21, eax
	mov eax, t20
	mov eax, t20
	mov [stack+t21], eax
	mov rax, labelText2
	call _printSubroutine
	mov eax, p
	add eax, t22
	mov t22, eax
	mov eax, t23
	mov eax, t23
	mov [stack+t22], eax
	mov rax, labelText3
	call _printSubroutine
	mov rax, labelText4
	call _printSubroutine
	mov eax, p
	add eax, t24
	mov t24, eax
	mov eax, t25
	mov eax, t25
	mov [stack+t24], eax
	mov eax, p
	add eax, t26
	mov t26, eax
	mov eax, [stack+t26]
	mov [t27], eax
	mov eax, p
	add eax, t28
	mov t28, eax
	mov eax, t27
	mov eax, t27
	mov [stack+t28], eax
	mov eax, p
	add eax, t29
	mov t29, eax
	mov eax, [stack+t29]
	mov [t30], eax
	mov eax, p
	add eax, t31
	mov t31, eax
	mov eax, t30
	mov eax, t30
	mov [stack+t31], eax
	jmp _etv6
_etv6:
	ret
_JAVA_Calc_Calc_int:
	mov eax, p
	add eax, t32
	mov t32, eax
	mov eax, h
	mov eax, h
	mov [stack+t32], eax
	mov eax, h
	add eax, h
	mov h, eax
	mov eax, p
	add eax, t33
	mov t33, eax
	mov eax, [stack+t33]
	mov [t34], eax
	mov eax, p
	add eax, t35
	mov t35, eax
	mov eax, [stack+t35]
	mov [t36], eax
	mov eax, t36
	add eax, t37
	mov t37, eax
	mov eax, t34
	mov [heap[t37]], eax
	mov eax, 0
	mov [t38], eax
	mov eax, p
	add eax, t39
	mov t39, eax
	mov eax, t38
	mov eax, t38
	mov [stack+t39], eax
_etv11:
	mov eax, p
	add eax, t40
	mov t40, eax
	mov eax, [stack+t40]
	mov [t41], eax
	mov eax, 10
	mov [t42], eax
	mov eax, t42
	cmp t41 t42
	jl etv8
	mov t41, eax
	jmp _etv9
_etv8:
	mov eax, p
	add eax, t49
	mov t49, eax
	mov eax, [stack+t49]
	mov [t50], eax
	mov rax, labelText5
	call _printSubroutine
	mov eax, p
	add eax, t44
	mov t44, eax
	mov eax, [stack+t44]
	mov [t45], eax
	mov eax, 1
	mov [t46], eax
	mov eax, t45
	add eax, t47
	mov t47, eax
	mov eax, p
	add eax, t48
	mov t48, eax
	mov eax, t47
	mov eax, t47
	mov [stack+t48], eax
	jmp _etv11
_etv9:
	jmp _etv10
_etv10:
	mov rax, labelText6
	call _printSubroutine
	mov rax, labelText7
	call _printSubroutine
	mov rax, labelText8
	call _printSubroutine
	mov eax, p
	add eax, t51
	mov t51, eax
	mov eax, [stack+t51]
	mov [t52], eax
	mov rax, labelText9
	call _printSubroutine
	ret
_JAVA_Calc_Calc_int_int:
	mov eax, p
	add eax, t53
	mov t53, eax
	mov eax, h
	mov eax, h
	mov [stack+t53], eax
	mov eax, h
	add eax, h
	mov h, eax
	mov eax, p
	add eax, t54
	mov t54, eax
	mov eax, [stack+t54]
	mov [t55], eax
	mov eax, p
	add eax, t56
	mov t56, eax
	mov eax, [stack+t56]
	mov [t57], eax
	mov eax, t57
	add eax, t58
	mov t58, eax
	mov eax, t55
	mov [heap[t58]], eax
	mov eax, p
	add eax, t59
	mov t59, eax
	mov eax, [stack+t59]
	mov [t60], eax
	mov eax, p
	add eax, t61
	mov t61, eax
	mov eax, [stack+t61]
	mov [t62], eax
	mov eax, t62
	add eax, t63
	mov t63, eax
	mov eax, t60
	mov [heap[t63]], eax
	ret
_JAVA_Calc_sumar_int_int:
	mov eax, p
	add eax, t64
	mov t64, eax
	mov eax, [stack+t64]
	mov [t65], eax
	mov eax, t65
	add eax, t66
	mov t66, eax
	mov eax, heap[t66]
	mov [t67], eax
	mov eax, p
	add eax, t68
	mov t68, eax
	mov eax, [stack+t68]
	mov [t69], eax
	mov eax, t69
	add eax, t70
	mov t70, eax
	mov eax, heap[t70]
	mov [t71], eax
	mov eax, t67
	add eax, t72
	mov t72, eax
	mov eax, p
	add eax, t73
	mov t73, eax
	mov eax, t72
	mov eax, t72
	mov [stack+t73], eax
	jmp _etv12
_etv12:
	ret
_JAVA_Calc_factorial_int:
	mov eax, p
	add eax, t74
	mov t74, eax
	mov eax, [stack+t74]
	mov [t75], eax
	mov eax, 1
	mov [t76], eax
	mov eax, t76
	cmp t75 t76
	jle etv15
	mov t75, eax
	jmp _etv16
_etv15:
	mov eax, 1
	mov [t78], eax
	mov eax, p
	add eax, t79
	mov t79, eax
	mov eax, t78
	mov eax, t78
	mov [stack+t79], eax
	jmp _etv13
_etv16:
	jmp _etv17
_etv17:
	mov eax, p
	add eax, t80
	mov t80, eax
	mov eax, [stack+t80]
	mov [t81], eax
	mov eax, p
	add eax, t82
	mov t82, eax
	mov eax, [stack+t82]
	mov [t83], eax
	mov eax, 1
	mov [t84], eax
	mov eax, t83
	sub eax, t85
	mov t85, eax
	mov eax, p
	add eax, t89
	mov t89, eax
	mov eax, t89
	add eax, t90
	mov t90, eax
	mov eax, t85
	mov eax, t85
	mov [stack+t90], eax
	mov eax, p
	add eax, t86
	mov t86, eax
	mov eax, [stack+t86]
	mov [t87], eax
	mov eax, p
	add eax, t88
	mov t88, eax
	mov eax, t88
	add eax, t91
	mov t91, eax
	mov eax, t87
	mov eax, t87
	mov [stack+t91], eax
	mov eax, p
	add eax, p
	mov p, eax
	mov eax, p
	add eax, t92
	mov t92, eax
	mov eax, p
	sub eax, p
	mov p, eax
	mov eax, t81
	mul eax, t93
	mov t93, eax
	mov eax, p
	add eax, t94
	mov t94, eax
	mov eax, t93
	mov eax, t93
	mov [stack+t94], eax
	jmp _etv13
_etv13:
	ret
_PY_edadmeses:
	mov rax, labelText10
	call _printSubroutine
	mov eax, p
	add eax, t95
	mov t95, eax
	mov eax, t96
	mov eax, t96
	mov [stack+t95], eax
	mov eax, p
	add eax, t97
	mov t97, eax
	mov eax, [stack+t97]
	mov [t98], eax
	mov eax, 12
	mov [t99], eax
	mov eax, t98
	mul eax, t100
	mov t100, eax
	mov eax, p
	add eax, t101
	mov t101, eax
	mov eax, t100
	mov eax, t100
	mov [stack+t101], eax
	mov rax, labelText11
	call _printSubroutine
	mov eax, p
	add eax, t102
	mov t102, eax
	mov eax, [stack+t102]
	mov [t103], eax
	mov eax, p
	add eax, t104
	mov t104, eax
	mov eax, [stack+t104]
	mov [t105], eax
	mov eax, 12
	mov [t106], eax
	mov eax, t106
	cmp t105 t106
	jg etv20
	mov t105, eax
	jmp _etv21
_etv20:
	mov rax, labelText12
	call _printSubroutine
	mov eax, p
	add eax, t108
	mov t108, eax
	mov eax, [stack+t108]
	mov [t109], eax
	jmp _etv22
_etv21:
	jmp _etv22
_etv22:
_etv18:
	ret
_PY_holaMundo:
	mov rax, labelText13
	call _printSubroutine
_etv23:
	ret
_start:
	mov eax, p
	add eax, t110
	mov t110, eax
	mov eax, 15
	mov eax, 15
	mov [stack+t110], eax
	mov eax, 8
	mov [t111], eax
	mov eax, p
	add eax, t112
	mov t112, eax
	mov eax, t111
	mov eax, t111
	mov [stack+t112], eax
	mov eax, 'y'
	mov [t113], eax
	mov eax, p
	add eax, t114
	mov t114, eax
	mov eax, t113
	mov eax, t113
	mov [stack+t114], eax
	mov eax, 3
	mov ax, t116
	sub ax, '0'
	mov bl, 3
	sub bl, '0'
	div bl
	add ax, '0'
	mov t116, eax
	mov eax, 8
	add eax, t115
	mov t115, eax
	mov eax, p
	add eax, t118
	mov t118, eax
	mov eax, t115
	mov eax, t115
	mov [stack+t118], eax
	mov eax, 2
	mov [t117], eax
	mov eax, p
	add eax, t119
	mov t119, eax
	mov eax, t117
	mov eax, t117
	mov [stack+t119], eax
	mov eax, 5
	add eax, t120
	mov t120, eax
	mov eax, p
	add eax, t121
	mov t121, eax
	mov eax, t120
	mov eax, t120
	mov [stack+t121], eax
	mov eax, p
	add eax, t122
	mov t122, eax
	mov eax, [stack+t122]
	mov [t123], eax
	mov eax, 6
	mov [t124], eax
	mov eax, t123
	add eax, t125
	mov t125, eax
	mov eax, p
	add eax, t126
	mov t126, eax
	mov eax, t125
	mov eax, t125
	mov [stack+t126], eax
	mov eax, 2
	mov [t127], eax
	mov eax, p
	add eax, t128
	mov t128, eax
	mov eax, t127
	mov eax, t127
	mov [stack+t128], eax
	mov eax, p
	add eax, t129
	mov t129, eax
	mov eax, [stack+t129]
	mov [t130], eax
	mov eax, p
	add eax, t131
	mov t131, eax
	mov eax, [stack+t131]
	mov [t132], eax
	mov eax, p
	add eax, t133
	mov t133, eax
	mov eax, t133
	add eax, t134
	mov t134, eax
	mov eax, p
	add eax, t135
	mov t135, eax
	mov eax, t135
	add eax, t136
	mov t136, eax
	mov eax, t130
	mov eax, t130
	mov [stack+t134], eax
	mov eax, t132
	mov eax, t132
	mov [stack+t136], eax
	mov eax, p
	add eax, p
	mov p, eax
	mov eax, p
	sub eax, p
	mov p, eax
	mov eax, p
	add eax, t137
	mov t137, eax
	mov eax, t137
	add eax, t138
	mov t138, eax
	mov eax, p
	add eax, t139
	mov t139, eax
	mov eax, [stack+t137]
	mov eax, [stack+t137]
	mov [stack+t139], eax
	mov eax, 5
	mov [t140], eax
	mov eax, p
	add eax, t144
	mov t144, eax
	mov eax, t144
	add eax, t145
	mov t145, eax
	mov eax, t140
	mov eax, t140
	mov [stack+t145], eax
	mov eax, p
	add eax, t141
	mov t141, eax
	mov eax, [stack+t141]
	mov [t142], eax
	mov eax, p
	add eax, t143
	mov t143, eax
	mov eax, t143
	add eax, t146
	mov t146, eax
	mov eax, t142
	mov eax, t142
	mov [stack+t146], eax
	mov eax, p
	add eax, p
	mov p, eax
	mov eax, p
	add eax, t147
	mov t147, eax
	mov eax, p
	sub eax, p
	mov p, eax
	mov eax, p
	add eax, t148
	mov t148, eax
	mov eax, [stack+t147]
	mov eax, [stack+t147]
	mov [stack+t148], eax
	mov eax, p
	add eax, t148
	mov t148, eax
	mov eax, [stack+t147]
	mov eax, [stack+t147]
	mov [stack+t148], eax
	mov eax, p
	add eax, t149
	mov t149, eax
	mov eax, [stack+t149]
	mov [t150], eax
	mov rax, labelText14
	call _printSubroutine
	mov rax, 60
	mov rdi, 0
	syscall
