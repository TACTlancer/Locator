 DATAS  SEGMENT ;

      string  db        "Please input a number(0-10000):" ,0dh,0ah
 wrongstring  db        " A number please! ",0ah,0dh,'$'
 		hint  db 		'press q/Q to exit,or enter to input again.',0ah,0dh,'$'
 inputbuffer  db        6,?,6 dup(?)
         c10  dw        10     			;输入数的进制数
           n  dw        ?      			;要求的阶乘数
           m  dw        ?      			;步长
           x  dw        ?      			;进位
           i  dw        ?      			;
outputbuffer  dw        30000 dup(?)            
    
       DATAS  ENDS
       
       CODES  SEGMENT
              ASSUME    CS:CODES,DS:DATAS
      START:
              MOV       AX,DATAS    ;
              MOV       DS,AX       ;
;-------------------------------------------------------   
        main  proc
              
              call      input       ;
              call      fractor     ;
              mov       cx,di
              
    routput:                        ;
              push      cx          ;
              mov       di,cx       ;
              call      output
              pop       cx
              dec       cx
              cmp       cx,0
              jge       routput
              
 displaylength:
              mov       dl,0dh
              mov       ah,2
              int       21h
              mov       dl,0ah
              mov       ah,2
              int       21h
              
              mov dx , offset hint	;停止输入提示
              mov ah , 9
              int 21h
              mov       ah,07h      ;
              int       21h         ;
              cmp al , 'q'			;
              jz exit				;
              cmp al ,'Q'			;
              jz exit
              
              jmp       Start
       exit:
                       ;
              mov       ax,4c00h    ;
              INT       21H         ;
                       ;
        main  endp                  ;
;--------------------------------------------------------
       input  proc      near

              lea       bx,string
              mov       cx, 33
  disstring:
              mov       dl,[bx]
              mov       ah,2
              int       21h
              inc       bx
              loop      disstring
              jmp       inputinit
 wronginput:
              lea       dx,wrongstring
              mov       ah,9
              int       21h
  inputinit:
              lea       dx,inputbuffer   	;装载输入缓冲区首地址
              mov       ah,0ah      		;输入功能代码
              int       21h         		;从键盘输入一个数，以回车键结束
              mov       ax,0        		;累加器清0
              mov       cl,inputbuffer+1   	;循环次数
              mov       ch,0
              lea       bx,inputbuffer+2   	;装载字符存放区首地址
   inputone:
              mul       c10      			;输入数的进制
              mov       dl,[bx]
              cmp       dl,'0'
              jb        wronginput
              cmp       dl,'9'
              ja        wronginput  ;
              and       dl,0fh      ;
              add       al,dl       ;
              adc       ah,0        ;
              inc       bx          ;
              loop      inputone    
              
              mov       n,ax        ;要求的阶乘数(二进制
              mov       dl,0dh
              mov       ah,2
              int       21h
              mov       dl,0ah
              mov       ah,2
              int       21h
              ret
                            		;
       input  endp             		;
;-----------------------------------------------------
     fractor  proc      near        ;
              mov       cx,n        ;要求的阶乘数
              mov       i,1d        ;1d =1
              mov       m,0d        ;步长
                            ;
              push      dx
              mov       di,0d       ;
              mov       ax,di       ;
              mov       bx,2d       ;
              mul       bx          ;
              mov       si,ax
              pop       dx
                            		
              mov       outputbuffer[si],1d     
      ctrli:                        ;
              mov       x,0         ;进位
              mov       di,0d       ;

     ctrldi:
              cmp       di,m        ;步长
              jbe       done        ;
              jmp       cmpc        ;
       done:
              push      dx          ;
              mov       ax,di       ;
              mov       bx,2d       ;
              mul       bx          ;
              mov       si,ax
              pop       dx
                            
              mov       ax,outputbuffer[si]     
              mov       bx,i        ;
              mul       bx          ;
              add       ax,x        ;c,进位  
              adc       dx,0        ;                   
              mov       bx,10000    ;
              div       bx          ;
              mov       x,ax    	;c,进位

              push      dx          ;
              mov       ax,di       ;
              mov       bx,2d       ;
              mul       bx          ;
              mov       si,ax
              pop       dx          ;
              mov       outputbuffer[si],dx     
              inc       di          ;

              jmp       ctrldi      ;
       cmpc:                        ;
              cmp       x,0         ;c,进位
              ja        three1      ;
              jmp       next        ;
     three1:                        ;
              inc       m           ;m,步长
              mov       ax,x        ;
              mov       outputbuffer[si+2],ax   
                             
       next:

              inc       i
              cmp       cx,0
              jng       if0         ;
              loop      ctrli
        if0:
              mov       di,m        ;m,步长
              ret                   ;
                            
     fractor  endp                  ;
;-----------------------------------------------------
      output  proc      near        ;
         C2:
              push      dx          ;
              mov       ax,di       ;
              mov       bx,2d       ;
              mul       bx          ;
              mov       si,ax
              pop       dx
              mov       bx,outputbuffer[si]     

         bid  proc                  ;
              mov       cx,10000    ;
              mov       ax,bx       ;
              mov       dx,0        ;
              div       cx          ;
              mov       bx,dx
                            ;
              mov       cx,1000     ;
              call      ddiv        ;
              mov       cx,100      ;
              call      ddiv        ;
              mov       cx,10       ;
              call      ddiv        ;
              mov       cx,1
              call      ddiv        ;
              ret
                        
         bid  endp                  
;--------------------------------------
        ddiv  proc                  
              mov       ax,bx       ;
              mov       dx,0        ;
              div       cx          ;
              mov       bx,dx       ;
              mov       dl,al       ;
              add       dl,30h      ;
              mov       ah,02h      ;
              int       21h         ;
              ret
                         
        ddiv  endp                  ;
                            
              ret                   ;
      output  endp                  
;----------------------------------------
    
       CODES  ENDS                 
              END       START        









