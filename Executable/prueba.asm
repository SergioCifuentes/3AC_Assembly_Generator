section .data
	p db 0
	h db 0
	labelText0: db 'Ingrese Primer Numero',0
	labelText1: db 'Ingrese Segundo Numero',0
	labelText2: db 'El resultado1 es ',0
	labelText3: db 'El resultadoFinal es ',0
section .bss
	stack times 1000 resb 0
	heap times 1000 resb 0
	digitSpace resb 100
	digitSpacePos resb 8
	t1 resb 2
	t2 resb 2
	t3 resb 2
	t4 resb 2
	t5 resb 2
	t6 resb 2
	t7 resb 2
	t8 resb 2
	t9 resb 2
	t10 resb 2
	t11 resb 2
	t12 resb 2
	t13 resb 2
	t14 resb 2
	t15 resb 2
	t16 resb 2
	t17 resb 2
	t18 resb 2
	t19 resb 2
	t20 resb 2
	t21 resb 2
	t22 resb 2
	t23 resb 2
	t24 resb 2
	t25 resb 2
	t26 resb 2
	t27 resb 2
	t28 resb 2
	t29 resb 2
	t30 resb 2
	t31 resb 2
	t32 resb 2
	t33 resb 2
	t34 resb 2
	t35 resb 2
	t36 resb 2
	t37 resb 2
	t38 resb 2
	t39 resb 2
	t40 resb 2
	t41 resb 2
	t42 resb 2
	t43 resb 2
	t44 resb 2
	t45 resb 2
	t46 resb 2
	t47 resb 2
	t48 resb 2
	t49 resb 2
	t50 resb 2
	t51 resb 2
	t52 resb 2
	t53 resb 2
	t54 resb 2
	t55 resb 2
	t56 resb 2
	t57 resb 2
	t58 resb 2
	t59 resb 2
	t60 resb 2
	t61 resb 2
	t62 resb 2
	t63 resb 2
	t64 resb 2
	t65 resb 2
	t66 resb 2
	t67 resb 2
	t68 resb 2
	t69 resb 2
	t70 resb 2
	t71 resb 2
	t72 resb 2
	t73 resb 2
	t74 resb 2
	t75 resb 2
	t76 resb 2
	t77 resb 2
	t78 resb 2
	t79 resb 2
	t80 resb 2
	t81 resb 2
	t82 resb 2
	t83 resb 2
	t84 resb 2
	t85 resb 2
	t86 resb 2
	t87 resb 2
	t88 resb 2
	t89 resb 2
	t90 resb 2
	t91 resb 2
	t92 resb 2
section .text
	global _start
_VB_SUMAR:
	mov al, 0
	mov [t1],al
	mov al, [p]
	add al, 2
	mov [t2], al
	mov esi, stack
	mov edi, 0
	add esi, [t2]
	mov al, [t1]
	mov [esi], al
	mov al, [p]
	add al, 0
	mov [t3], al
	mov esi, stack
	mov edi, 0
	add esi, [t3]
	mov al, [esi]
	mov [t4], al
	mov al, [p]
	add al, 1
	mov [t5], al
	mov esi, stack
	mov edi, 0
	add esi, [t5]
	mov al, [esi]
	mov [t6], al
	mov al, [t4]
	add al, 0
	mov [t7], al
	mov al, [p]
	add al, 2
	mov [t8], al
	mov esi, stack
	mov edi, 0
	add esi, [t8]
	mov al, [t7]
	mov [esi], al
	mov al, [p]
	add al, 2
	mov [t9], al
	mov esi, stack
	mov edi, 0
	add esi, [t9]
	mov al, [esi]
	mov [t10], al
	mov al, [p]
	add al, 3
	mov [t11], al
	mov esi, stack
	mov edi, 0
	add esi, [t11]
	mov al, [t10]
	mov [esi], al
	jmp _etv1
_etv1:
	ret
_JAVA_Calc_Calc_int_int:
	mov al, [p]
	add al, 0
	mov [t12], al
	mov esi, stack
	mov edi, 0
	add esi, [t12]
	mov al, [h]
	mov [esi], al
	mov al, [h]
	add al, 2
	mov [h], al
	mov al, [p]
	add al, 1
	mov [t13], al
	mov esi, stack
	mov edi, 0
	add esi, [t13]
	mov al, [esi]
	mov [t14], al
	mov al, [p]
	add al, 0
	mov [t15], al
	mov esi, stack
	mov edi, 0
	add esi, [t15]
	mov al, [esi]
	mov [t16], al
	mov al, [t16]
	add al, 0
	mov [t17], al
	mov esi, heap
	mov edi, 0
	add esi, [t17]
	mov al, [t14]
	mov [esi], al
	mov al, [p]
	add al, 2
	mov [t18], al
	mov esi, stack
	mov edi, 0
	add esi, [t18]
	mov al, [esi]
	mov [t19], al
	mov al, [p]
	add al, 0
	mov [t20], al
	mov esi, stack
	mov edi, 0
	add esi, [t20]
	mov al, [esi]
	mov [t21], al
	mov al, [t21]
	add al, 1
	mov [t22], al
	mov esi, heap
	mov edi, 0
	add esi, [t22]
	mov al, [t19]
	mov [esi], al
	ret
_JAVA_Calc_Calc:
	mov al, [p]
	add al, 0
	mov [t23], al
	mov esi, stack
	mov edi, 0
	add esi, [t23]
	mov al, [h]
	mov [esi], al
	mov al, [h]
	add al, 2
	mov [h], al
	mov al, 1
	mov [t24],al
	mov al, [p]
	add al, 0
	mov [t25], al
	mov esi, stack
	mov edi, 0
	add esi, [t25]
	mov al, [esi]
	mov [t26], al
	mov al, [t26]
	add al, 0
	mov [t27], al
	mov esi, heap
	mov edi, 0
	add esi, [t27]
	mov al, [t24]
	mov [esi], al
	mov al, 2
	mov [t28],al
	mov al, [p]
	add al, 0
	mov [t29], al
	mov esi, stack
	mov edi, 0
	add esi, [t29]
	mov al, [esi]
	mov [t30], al
	mov al, [t30]
	add al, 1
	mov [t31], al
	mov esi, heap
	mov edi, 0
	add esi, [t31]
	mov al, [t28]
	mov [esi], al
	ret
_JAVA_Calc_sumar:
	mov al, [p]
	add al, 0
	mov [t32], al
	mov esi, stack
	mov edi, 0
	add esi, [t32]
	mov al, [esi]
	mov [t33], al
	mov al, [t33]
	add al, 0
	mov [t34], al
	mov esi, heap
	mov edi, 0
	add esi, [t34]
	mov al, [esi]
	mov [t35], al
	mov al, [p]
	add al, 0
	mov [t36], al
	mov esi, stack
	mov edi, 0
	add esi, [t36]
	mov al, [esi]
	mov [t37], al
	mov al, [t37]
	add al, 1
	mov [t38], al
	mov esi, heap
	mov edi, 0
	add esi, [t38]
	mov al, [esi]
	mov [t39], al
	mov al, [t35]
	add al, 0
	mov [t40], al
	mov al, [p]
	add al, 1
	mov [t41], al
	mov esi, stack
	mov edi, 0
	add esi, [t41]
	mov al, [t40]
	mov [esi], al
	jmp _etv2
_etv2:
	ret
_PY_DIVIDIR:
	mov al, [p]
	add al, 1
	mov [t42], al
	mov esi, stack
	mov edi, 0
	add esi, [t42]
	mov al, [esi]
	mov [t43], al
	mov al, 0
	mov [t44],al
	mov al, [0]
	cmp [t43], al
	je _etv5
	jmp _etv6
_etv5:
	mov al, 0
	mov [t46],al
	mov al, [p]
	add al, 2
	mov [t47], al
	mov esi, stack
	mov edi, 0
	add esi, [t47]
	mov al, [t46]
	mov [esi], al
	jmp _etv3
_etv6:
	mov al, [p]
	add al, 0
	mov [t48], al
	mov esi, stack
	mov edi, 0
	add esi, [t48]
	mov al, [esi]
	mov [t49], al
	mov al, [p]
	add al, 1
	mov [t50], al
	mov esi, stack
	mov edi, 0
	add esi, [t50]
	mov al, [esi]
	mov [t51], al
	mov al, [t49]
	mov ax, 0
	mov dl, [t49]
	div dl
	add ax, '0'
	mov [t52], al
	mov al, [p]
	add al, 2
	mov [t53], al
	mov esi, stack
	mov edi, 0
	add esi, [t53]
	mov al, [t52]
	mov [esi], al
	jmp _etv3
_etv7:
_etv3:
	ret
_start:
	mov al, [p]
	add al, 7
	mov [p], al
	mov al, [p]
	sub al, 7
	mov [p], al
	mov al, [p]
	add al, 7
	mov [t54], al
	mov al, [t54]
	add al, 0
	mov [t55], al
	mov rax, labelText0
	call _print
	mov al, [p]
	add al, 1
	mov [t56], al
	mov esi, stack
	mov edi, 0
	add esi, [t56]
	mov al, [t57]
	mov [esi], al
	mov rax, labelText1
	call _print
	mov al, [p]
	add al, 2
	mov [t58], al
	mov esi, stack
	mov edi, 0
	add esi, [t58]
	mov al, [t59]
	mov [esi], al
	mov al, [p]
	add al, 1
	mov [t60], al
	mov esi, stack
	mov edi, 0
	add esi, [t60]
	mov al, [esi]
	mov [t61], al
	mov al, [p]
	add al, 2
	mov [t62], al
	mov esi, stack
	mov edi, 0
	add esi, [t62]
	mov al, [esi]
	mov [t63], al
	mov al, [p]
	add al, 7
	mov [t64], al
	mov al, [t64]
	add al, 1
	mov [t65], al
	mov al, [p]
	add al, 7
	mov [t66], al
	mov al, [t66]
	add al, 2
	mov [t67], al
	mov esi, stack
	mov edi, 0
	add esi, [t65]
	mov al, [t61]
	mov [esi], al
	mov esi, stack
	mov edi, 0
	add esi, [t67]
	mov al, [t63]
	mov [esi], al
	mov al, [p]
	add al, 7
	mov [p], al
	mov al, [p]
	sub al, 7
	mov [p], al
	mov al, [p]
	add al, 7
	mov [t68], al
	mov al, [t68]
	add al, 0
	mov [t69], al
	mov al, [p]
	add al, 3
	mov [t70], al
	mov esi, stack
	mov edi, 0
	add esi, [t68]
	mov esi, stack
	mov edi, 0
	add esi, [t70]
	mov al, [esi]
	mov [esi], al
	mov al, [p]
	add al, 3
	mov [t71], al
	mov esi, stack
	mov edi, 0
	add esi, [t71]
	mov al, [esi]
	mov [t72], al
	mov al, [p]
	add al, 7
	mov [t73], al
	mov al, [t73]
	add al, 0
	mov [t74], al
	mov esi, stack
	mov edi, 0
	add esi, [t74]
	mov al, [t72]
	mov [esi], al
	mov al, [p]
	add al, 7
	mov [p], al
	mov al, [p]
	add al, 1
	mov [t75], al
	mov al, [p]
	sub al, 7
	mov [p], al
	mov al, [p]
	add al, 4
	mov [t76], al
	mov esi, stack
	mov edi, 0
	add esi, [t75]
	mov esi, stack
	mov edi, 0
	add esi, [t76]
	mov al, [esi]
	mov [esi], al
	mov al, [p]
	add al, 4
	mov [t76], al
	mov esi, stack
	mov edi, 0
	add esi, [t75]
	mov esi, stack
	mov edi, 0
	add esi, [t76]
	mov al, [esi]
	mov [esi], al
	mov rax, labelText2
	call _print
	mov al, [p]
	add al, 4
	mov [t77], al
	mov esi, stack
	mov edi, 0
	add esi, [t77]
	mov al, [esi]
	mov [t78], al
	mov rax, [t78]
	call _printRAX
	mov al, [p]
	add al, 0
	mov [t79], al
	mov esi, stack
	mov edi, 0
	add esi, [t79]
	mov al, [esi]
	mov [t80], al
	mov al, [p]
	add al, 7
	mov [t81], al
	mov al, [t81]
	add al, 0
	mov [t82], al
	mov esi, stack
	mov edi, 0
	add esi, [t82]
	mov al, [t80]
	mov [esi], al
	mov al, [p]
	add al, 7
	mov [p], al
	mov al, [p]
	add al, 1
	mov [t83], al
	mov al, [p]
	sub al, 7
	mov [p], al
	mov al, [p]
	add al, 5
	mov [t84], al
	mov esi, stack
	mov edi, 0
	add esi, [t83]
	mov esi, stack
	mov edi, 0
	add esi, [t84]
	mov al, [esi]
	mov [esi], al
	mov al, [p]
	add al, 5
	mov [t84], al
	mov esi, stack
	mov edi, 0
	add esi, [t83]
	mov esi, stack
	mov edi, 0
	add esi, [t84]
	mov al, [esi]
	mov [esi], al
	mov al, [p]
	add al, 4
	mov [t85], al
	mov esi, stack
	mov edi, 0
	add esi, [t85]
	mov al, [esi]
	mov [t86], al
	mov al, [p]
	add al, 5
	mov [t87], al
	mov esi, stack
	mov edi, 0
	add esi, [t87]
	mov al, [esi]
	mov [t88], al
	mov al, [t86]
	add al, 0
	mov [t89], al
	mov al, [p]
	add al, 6
	mov [t90], al
	mov esi, stack
	mov edi, 0
	add esi, [t90]
	mov al, [t89]
	mov [esi], al
	mov rax, labelText3
	call _print
	mov al, [p]
	add al, 6
	mov [t91], al
	mov esi, stack
	mov edi, 0
	add esi, [t91]
	mov al, [esi]
	mov [t92], al
	mov rax, [t92]
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
