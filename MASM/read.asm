;���ݶ�
data            segment
file             db    "e:\1.txt" , 0       ;�ļ���·��
buf             db   256 dup(0)        ;�ļ������ݴ���
error_message    db   0ah , 'error !' , '$'    ;����ʱ����ʾ
handle           dw  ?                ;�����ļ���
data             ends
;�����
code            segment
                assume  cs:code  , ds:data
start:
              mov ax , data                
              mov ds , ax                ;��ȡ�ε�ַ    
              mov dx , offset file        ;dx��ȡfile��ƫ�Ƶ�ַ
              mov al , 0                
              mov ah , 3dh                
              int 21h                  ;���ļ���ֻ��
              jc error                  ;���򿪳���תerror
              mov handle , ax           ;�����ļ����
              mov bx , ax                ;�ļ����
              mov cx , 255                ;��ȡ255�ֽ�
              mov dx , offset buf        ;��ȡbuf��ƫ�Ƶ�ַ
              mov ah , 3fh                
              int 21h                  ;���ļ��ж�255�ֽڡ�buf
              jc error                  ;��������תerror
              mov bx , ax              ;ʵ�ʶ������ַ�������bx
              mov buf[bx] , '$'          ;���ļ�����������һ��$����
              mov dx , offset buf
              mov ah , 9
              int 21h                            ;��ʾ�ļ�����
              mov bx , handle                    ;�ļ����
              mov ah , 3eh                        
              int 21h                            ;�ر��ļ�
              jnc end1             ;���رչ����޴�ת��end1������dos
error:
              mov dx , offset error_message        ;��ȡerror_message��ƫ�Ƶ�ַ
              mov ah , 9                        
              int 21h                            ;��ʾerror_message
end1:
             mov ah , 4ch                        ;��������Ľ���
             int 21h
code   ends
             end  start
             
             

