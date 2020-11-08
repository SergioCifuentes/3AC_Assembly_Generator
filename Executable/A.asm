section .data
	labelText0: db 'Segundo Proyecto de Compiladores 2',0
	labelText1: db 'Segundo Proyecto de Compiladores 2',0
	labelText2: db 'ingreseCaracter',0
	labelText3: db 'hola',0
	labelText4: db 'ingreseCaracter',0
	labelText5: db 'este es el valor de n1 y n2:',0
	labelText6: db '\n',0
	labelText7: db '\n',0
	labelText8: db '\n',0
	labelText9: db 'Ingrese su edad:',0
	labelText10: db 'meses = ',0
	labelText11: db '\n',0
	labelText12: db 'pru0eba',0
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
	ret
_VB_Factorial:
	mov rax, labelText2
	call _printSubroutine
	mov rax, labelText3
	call _printSubroutine
	mov rax, labelText4
	call _printSubroutine
	ret
_JAVA_Calc_Calc_int:
	mov rax, labelText5
	call _printSubroutine
	mov rax, labelText6
	call _printSubroutine
	mov rax, labelText7
	call _printSubroutine
	mov rax, labelText8
	call _printSubroutine
	ret
_JAVA_Calc_Calc_int_int:
	ret
_JAVA_Calc_sumar:
	ret
_JAVA_Calc_factorial:
	ret
_PY_edadmeses:
	mov rax, labelText9
	call _printSubroutine
	mov rax, labelText10
	call _printSubroutine
	mov rax, labelText11
	call _printSubroutine
	ret
_start:
	mov rax, labelText12
	call _printSubroutine
	mov rax, 60
	mov rdi, 0
	syscall
