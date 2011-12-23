	.file	"Hello.cpp"
	.text
	.def	__Z41__static_initialization_and_destruction_0ii;	.scl	3;	.type	32;	.endef
__Z41__static_initialization_and_destruction_0ii:
LFB956:
	pushl	%ebp
LCFI0:
	movl	%esp, %ebp
LCFI1:
	subl	$8, %esp
LCFI2:
	cmpl	$1, 8(%ebp)
	jne	L2
	cmpl	$65535, 12(%ebp)
	jne	L2
	movl	$__ZStL8__ioinit, (%esp)
	call	__ZNSt8ios_base4InitC1Ev
L2:
	cmpl	$0, 8(%ebp)
	jne	L4
	cmpl	$65535, 12(%ebp)
	jne	L4
	movl	$__ZStL8__ioinit, (%esp)
	call	__ZNSt8ios_base4InitD1Ev
L4:
	leave
	ret
LFE956:
	.def	__GLOBAL__D_main;	.scl	3;	.type	32;	.endef
__GLOBAL__D_main:
LFB958:
	pushl	%ebp
LCFI3:
	movl	%esp, %ebp
LCFI4:
	subl	$8, %esp
LCFI5:
	movl	$65535, 4(%esp)
	movl	$0, (%esp)
	call	__Z41__static_initialization_and_destruction_0ii
	leave
	ret
LFE958:
	.section	.dtors,"w"
	.align 4
	.long	__GLOBAL__D_main
	.text
	.def	__GLOBAL__I_main;	.scl	3;	.type	32;	.endef
__GLOBAL__I_main:
LFB957:
	pushl	%ebp
LCFI6:
	movl	%esp, %ebp
LCFI7:
	subl	$8, %esp
LCFI8:
	movl	$65535, 4(%esp)
	movl	$1, (%esp)
	call	__Z41__static_initialization_and_destruction_0ii
	leave
	ret
LFE957:
	.section	.ctors,"w"
	.align 4
	.long	__GLOBAL__I_main
	.def	___main;	.scl	2;	.type	32;	.endef
	.section .rdata,"dr"
LC0:
	.ascii "Hello world!\0"
LC1:
	.ascii "%d/n\0"
	.text
.globl _main
	.def	_main;	.scl	2;	.type	32;	.endef
_main:
LFB947:
	leal	4(%esp), %ecx
LCFI9:
	andl	$-16, %esp
	pushl	-4(%ecx)
LCFI10:
	pushl	%ebp
LCFI11:
	movl	%esp, %ebp
LCFI12:
	pushl	%ecx
LCFI13:
	subl	$20, %esp
LCFI14:
	call	___main
	movl	$LC0, 4(%esp)
	movl	$__ZSt4cout, (%esp)
	call	__ZStlsISt11char_traitsIcEERSt13basic_ostreamIcT_ES5_PKc
	movl	$__ZSt4endlIcSt11char_traitsIcEERSt13basic_ostreamIT_T0_ES6_, 4(%esp)
	movl	%eax, (%esp)
	call	__ZNSolsEPFRSoS_E
	movl	$100, 4(%esp)
	movl	$LC1, (%esp)
	call	_printf
	movl	$0, %eax
	addl	$20, %esp
	popl	%ecx
	popl	%ebp
	leal	-4(%ecx), %esp
	ret
LFE947:
.lcomm __ZStL8__ioinit,16
	.section	.eh_frame,"w"
Lframe1:
	.long	LECIE1-LSCIE1
LSCIE1:
	.long	0x0
	.byte	0x1
	.def	___gxx_personality_v0;	.scl	2;	.type	32;	.endef
	.ascii "zP\0"
	.uleb128 0x1
	.sleb128 -4
	.byte	0x8
	.uleb128 0x5
	.byte	0x0
	.long	___gxx_personality_v0
	.byte	0xc
	.uleb128 0x4
	.uleb128 0x4
	.byte	0x88
	.uleb128 0x1
	.align 4
LECIE1:
LSFDE1:
	.long	LEFDE1-LASFDE1
LASFDE1:
	.long	LASFDE1-Lframe1
	.long	LFB956
	.long	LFE956-LFB956
	.uleb128 0x0
	.byte	0x4
	.long	LCFI0-LFB956
	.byte	0xe
	.uleb128 0x8
	.byte	0x85
	.uleb128 0x2
	.byte	0x4
	.long	LCFI1-LCFI0
	.byte	0xd
	.uleb128 0x5
	.align 4
LEFDE1:
LSFDE3:
	.long	LEFDE3-LASFDE3
LASFDE3:
	.long	LASFDE3-Lframe1
	.long	LFB958
	.long	LFE958-LFB958
	.uleb128 0x0
	.byte	0x4
	.long	LCFI3-LFB958
	.byte	0xe
	.uleb128 0x8
	.byte	0x85
	.uleb128 0x2
	.byte	0x4
	.long	LCFI4-LCFI3
	.byte	0xd
	.uleb128 0x5
	.align 4
LEFDE3:
LSFDE5:
	.long	LEFDE5-LASFDE5
LASFDE5:
	.long	LASFDE5-Lframe1
	.long	LFB957
	.long	LFE957-LFB957
	.uleb128 0x0
	.byte	0x4
	.long	LCFI6-LFB957
	.byte	0xe
	.uleb128 0x8
	.byte	0x85
	.uleb128 0x2
	.byte	0x4
	.long	LCFI7-LCFI6
	.byte	0xd
	.uleb128 0x5
	.align 4
LEFDE5:
LSFDE7:
	.long	LEFDE7-LASFDE7
LASFDE7:
	.long	LASFDE7-Lframe1
	.long	LFB947
	.long	LFE947-LFB947
	.uleb128 0x0
	.byte	0x4
	.long	LCFI9-LFB947
	.byte	0xc
	.uleb128 0x1
	.uleb128 0x0
	.byte	0x9
	.uleb128 0x4
	.uleb128 0x1
	.byte	0x4
	.long	LCFI10-LCFI9
	.byte	0xc
	.uleb128 0x4
	.uleb128 0x4
	.byte	0x4
	.long	LCFI11-LCFI10
	.byte	0xe
	.uleb128 0x8
	.byte	0x85
	.uleb128 0x2
	.byte	0x4
	.long	LCFI12-LCFI11
	.byte	0xd
	.uleb128 0x5
	.byte	0x4
	.long	LCFI13-LCFI12
	.byte	0x84
	.uleb128 0x3
	.align 4
LEFDE7:
	.def	__ZNSt8ios_base4InitC1Ev;	.scl	2;	.type	32;	.endef
	.def	__ZStlsISt11char_traitsIcEERSt13basic_ostreamIcT_ES5_PKc;	.scl	2;	.type	32;	.endef
	.def	__ZNSolsEPFRSoS_E;	.scl	2;	.type	32;	.endef
	.def	_printf;	.scl	2;	.type	32;	.endef
	.def	__ZNSt8ios_base4InitD1Ev;	.scl	2;	.type	32;	.endef
	.def	__ZSt4endlIcSt11char_traitsIcEERSt13basic_ostreamIT_T0_ES6_;	.scl	2;	.type	32;	.endef
