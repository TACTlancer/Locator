 DATAS  SEGMENT ;

      string  db        "Please input a number(0-10000):" ,0dh,0ah
 wrongstring  db        " A number please! ",0ah,0dh,'$'
 		hint  db 		'press q/Q to exit,or enter to input again.',0ah,0dh,'$'
 inputbuffer  db        6,?,6 dup(?)
         c10  dw        10     			;�������Ľ�����
           n  dw        ?      			;Ҫ��Ľ׳���
           m  dw        ?      			;����
           x  dw        ?      			;��λ
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
              
              mov dx , offset hint	;ֹͣ������ʾ
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
              lea       dx,inputbuffer   	;װ�����뻺�����׵�ַ
              mov       ah,0ah      		;���빦�ܴ���
              int       21h         		;�Ӽ�������һ�������Իس�������
              mov       ax,0        		;�ۼ�����0
              mov       cl,inputbuffer+1   	;ѭ������
              mov       ch,0
              lea       bx,inputbuffer+2   	;װ���ַ�������׵�ַ
   inputone:
              mul       c10      			;�������Ľ���
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
              
              mov       n,ax        ;Ҫ��Ľ׳���(������
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
              mov       cx,n        ;Ҫ��Ľ׳���
              mov       i,1d        ;1d =1
              mov       m,0d        ;����
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
              mov       x,0         ;��λ
              mov       di,0d       ;

     ctrldi:
              cmp       di,m        ;����
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
              add       ax,x        ;c,��λ  
              adc       dx,0        ;                   
              mov       bx,10000    ;
              div       bx          ;
              mov       x,ax    	;c,��λ

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
              cmp       x,0         ;c,��λ
              ja        three1      ;
              jmp       next        ;
     three1:                        ;
              inc       m           ;m,����
              mov       ax,x        ;
              mov       outputbuffer[si+2],ax   
                             
       next:

              inc       i
              cmp       cx,0
              jng       if0         ;
              loop      ctrli
        if0:
              mov       di,m        ;m,����
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









