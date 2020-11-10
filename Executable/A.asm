section .data
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
_etv5:
	jmp _etv3
_etv2:
	jmp _etv4
_etv3:
	jmp _etv5
_etv4:
	ret
_VB_Factorial:
	mov rax, labelText2
	call _printSubroutine
	mov rax, labelText3
	call _printSubroutine
	mov rax, labelText4
	call _printSubroutine
	jmp _etv6
_etv6:
	ret
_JAVA_Calc_Calc_int:
_etv11:
	jmp _etv9
_etv8:
	mov rax, labelText5
	call _printSubroutine
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
	mov rax, labelText9
	call _printSubroutine
	ret
_JAVA_Calc_Calc_int_int:
	ret
_JAVA_Calc_sumar_int_int:
	jmp _etv12
_etv12:
	ret
_JAVA_Calc_factorial_int:
	jmp _etv16
_etv15:
	jmp _etv13
_etv16:
	jmp _etv17
_etv17:
	jmp _etv13
_etv13:
	ret
_PY_edadmeses:
	mov rax, labelText10
	call _printSubroutine
	mov rax, labelText11
	call _printSubroutine
	jmp _etv21
_etv20:
	mov rax, labelText12
	call _printSubroutine
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
	mov rax, labelText14
	call _printSubroutine
	mov rax, 60
	mov rdi, 0
	syscall
