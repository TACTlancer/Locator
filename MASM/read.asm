;数据段
data            segment
file             db    "e:\1.txt" , 0       ;文件的路径
buf             db   256 dup(0)        ;文件内容暂存区
error_message    db   0ah , 'error !' , '$'    ;出错时的提示
handle           dw  ?                ;保存文件号
data             ends
;代码段
code            segment
                assume  cs:code  , ds:data
start:
              mov ax , data                
              mov ds , ax                ;获取段地址    
              mov dx , offset file        ;dx获取file的偏移地址
              mov al , 0                
              mov ah , 3dh                
              int 21h                  ;打开文件，只读
              jc error                  ;若打开出错，转error
              mov handle , ax           ;保存文件句柄
              mov bx , ax                ;文件句柄
              mov cx , 255                ;读取255字节
              mov dx , offset buf        ;获取buf的偏移地址
              mov ah , 3fh                
              int 21h                  ;从文件中读255字节→buf
              jc error                  ;若读出错，转error
              mov bx , ax              ;实际读到的字符数送入bx
              mov buf[bx] , '$'          ;在文件结束处放置一“$”符
              mov dx , offset buf
              mov ah , 9
              int 21h                            ;显示文件内容
              mov bx , handle                    ;文件句柄
              mov ah , 3eh                        
              int 21h                            ;关闭文件
              jnc end1             ;若关闭过程无错，转到end1处返回dos
error:
              mov dx , offset error_message        ;获取error_message的偏移地址
              mov ah , 9                        
              int 21h                            ;显示error_message
end1:
             mov ah , 4ch                        ;待返回码的结束
             int 21h
code   ends
             end  start
             
             

