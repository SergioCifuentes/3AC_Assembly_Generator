default rel
extern scanf
section .data
	formatIntScanf     db  '%d\n',	0         ; Declarando el formato scanf int
	p db 0
	h db 0
	labelText0: db 'Ingrese Primer Numero',0
	labelText1: db 'Ingrese Segundo Numero',0
	labelText2: db 'El resultadoFinal es ',0
	stack  times 10000 dq  0,	0		; Declarando el vector que almacenara los datos de la estructura stack
	heap   times 10000 dq  0,	0		; 
	t1 dw 0
	t2 dw 0
	t3 dw 0
	t4 dw 0
	t5 dw 0
	t6 dw 0
	t7 dw 0
	t8 dw 0
	t9 dw 0
	t10 dw 0
	t11 dw 0
	t12 dw 0
section .bss
	digitSpace resb 100
	digitSpacePos resb 8
section .text
	global _start
_start:
	mov	rbx,	0
	mov	[p],	rbx

	mov	rbx,	0
	mov	[h],	rbx
	mov rax, labelText0
	call _print
	mov al, [p]
	add al, 0
	mov [t1], al
	mov esi, stack
	mov edi, 0
	add esi, [t1]
	
	lea	esi,	[t2]
	lea	rdi,	[rel formatIntScanf]
	xor	rax,	rax
	call	scanf
	
	mov	rbx,	[t2]
	mov eax, [t1]
	mov [stack + eax  * 4], rbx
	
	mov rax, labelText1
	call _print
	
	mov eax, [p]
	add eax, 1
	mov [t3], eax
	
	lea	esi,	[t4]
	lea	rdi,	[rel formatIntScanf]
	xor	rax,	rax
	call	scanf
	
	mov	rbx,	[t4]
	mov eax, [t3]
	mov [stack + eax  * 4], rbx
		
	
	mov al, [p]
	add al, 0
	mov [t5], al

	mov	eax,	[t5]
	mov	rbx,	[stack + eax  * 4]
	mov [t6], rbx      

	mov al, [p]
	add al, 1
	mov [t7], al
	
	mov	eax,	[t5]
	mov	rbx,	[stack + eax  * 4]
	mov [t6], rbx      

	mov eax, [t6]
	add eax, [t6]
	mov [t9], eax
	
	mov eax, [p]
	add eax, 2
	mov [t10], eax
	
	mov	rbx,	[t9]
	mov eax, [t10]
	mov [stack + eax  * 4], rbx

	mov rax, labelText2
	call _print
	
	mov eax, [p]
	add eax, 2
	mov [t11], eax
	
	mov esi, stack
	mov edi, 0
	add esi, [t11]
	mov al, [esi]
	mov [t12], al
	mov rax, [t12]
	call _printRAX
	mov rax, 60
	mov rdi, 0
	syscall
_print:
	push rax
	mov rbx, 0
_printLoop:
	inc rax
	inc rbx
	mov cl, [rax]
	cmp cl, 0
	jne _printLoop

	mov rax, 1
	mov rdi, 1
	pop rsi
	mov rdx, rbx
	syscall

	ret
_printRAX:
    mov rcx, digitSpace
    mov rbx, 10
    mov [rcx], rbx
    inc rcx
    mov [digitSpacePos], rcx
 
_printRAXLoop:
    mov rdx, 0
    mov rbx, 10
    div rbx
    push rax
    add rdx, 48
 
    mov rcx, [digitSpacePos]
    mov [rcx], dl
    inc rcx
    mov [digitSpacePos], rcx
    
    pop rax
    cmp rax, 0
    jne _printRAXLoop
 
_printRAXLoop2:
    mov rcx, [digitSpacePos]
 
    mov rax, 1
    mov rdi, 1
    mov rsi, rcx
    mov rdx, 1
    syscall
 
    mov rcx, [digitSpacePos]
    dec rcx
    mov [digitSpacePos], rcx
 
    cmp rcx, digitSpace
    jge _printRAXLoop2
 
    ret
