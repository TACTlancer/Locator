
        function disptime(){  
            var today=new Date();  
            var hh=today.getHours();  
            var mm=today.getMinutes();  
            var ss = today.getSeconds();  
            document.getElementById("myclock").innerHTML="<h1>现在是&mdash;"+hh+":"+mm+":"+ss+"</h1>"  
        }   
        //setInterval（）方法可以按照指定的周期调用函数或计算表达式  
        //var mytime = setInterval("disptime()",1000);  
    