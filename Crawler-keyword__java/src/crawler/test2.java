package crawler;

import java.util.Iterator;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class test2 {

public  void test(String s) throws Exception {
//      // TODO Auto-generated method stub
//      String html = s;
//      Document doc = Jsoup.parseBodyFragment(html);
//      Element body = doc.body();
Document doc = Jsoup.connect(s).timeout(3000000).get();

System.out.println("=========================获取标题=========================================");
String title = doc.title();
System.out.println(title);
System.out.println("===========================获取class:warp 标签里的内容=======================================");
Elements links=doc.getElementsByClass("wrap");

for(Element link:links){
  System.out.println( link.text());
}
System.out.println("============================获取所有javascript内容======================================");


Elements els = doc.getElementsByTag("script");

System.out.println("\n\n\n"+els+"\n");
System.out.println("======================获取指定的链接及内容=============================================");

//    Elements elScripts = doc.getElementsByTag("script");  
//    String[] elScriptList = elScripts.get(0).data().toString().split("var");
Elements ListDiv = doc.getElementsByAttributeValue("id","clist");
for (Element element :ListDiv) {
  Elements ln = element.getElementsByTag("a");
  for (Element link : ln) {
      String linkHref = link.attr("href");
      String linkText = link.text().trim();
      System.out.println("http://www.86pm25.com/city/"+linkHref);
      System.out.println(linkText);
  }
}
System.out.println("==================================================== 获取指定标签的内容==============");
Elements Liws= doc.getElementsByAttributeValue("class","fleft");
for (Element element :Liws) {
  System.out.println(element.html());
}
System.out.println("==================================================== 获取所有td标签 ==============");

//获取所有td标签 
Elements el = doc.getElementsByTag("td"); 
System.out.println("\n\n\n"+el+"\n"); 

System.out.println("==================================================== 获取页面里第二个script标签 ==============");
Elements e = doc.getElementsByTag("script").eq(2);  
System.out.println(e); 

System.out.println("====================================================获取页面里第二个script标签==============");
Elements elScripts = doc.getElementsByTag("script");  
String elScriptList = elScripts.get(2).data();
System.out.println(elScriptList); 

System.out.println("==================================================== 获取第二个script标签 里的aqiPercent的值==============");
Elements eles = doc.getElementsByTag("script").eq(2);
for (Element ele : eles) {

// 檢查是否有detailInfoObject字串
String script = ele.toString();
if (script.indexOf("aqiPercent") > -1 ) {

// 只取得script的內容
script = ele.childNode(0).toString();

// 使用ScriptEngine來parse
ScriptEngine engine = new ScriptEngineManager().getEngineByName("javascript");
engine.eval(script);

// 取得你要的變數
Object obj = engine.get("aqiPercent");
System.out.println("aqiPercent= " + obj);

}
System.out.println("==================================================== 获取第二个script标签 里的idx的值==============");
if (script.indexOf("idx") > -1 ) {

// 只取得script的內容
script = ele.childNode(0).toString();

// 使用ScriptEngine來parse
ScriptEngine engine = new ScriptEngineManager().getEngineByName("javascript");
engine.eval(script);

// 取得你要的變數
Object obj1 = engine.get("idx");
System.out.println("idx = " + obj1);
}
System.out.println("==================================================== 获取第二个script标签 里的qualityStr的值==============");
if (script.indexOf("qualityStr") > -1 ) {

// 只取得script的內容
script = ele.childNode(0).toString();

// 使用ScriptEngine來parse
ScriptEngine engine = new ScriptEngineManager().getEngineByName("javascript");
engine.eval(script);

// 取得你要的變數
Object obj2 = engine.get("qualityStr");
System.out.println("qualityStr = " + obj2);
}
System.out.println("==================================================== 获取第二个script标签 里的aqiLevel的值==============");
if (script.indexOf("aqiLevel") > -1 ) {

// 只取得script的內容
script = ele.childNode(0).toString();

// 使用ScriptEngine來parse
ScriptEngine engine = new ScriptEngineManager().getEngineByName("javascript");
engine.eval(script);

// 取得你要的變數
Object obj3 = engine.get("aqiLevel");
System.out.println("aqiLevel = " + obj3);
}
}
System.out.println("=====================获取整个网页的html代码=============================================");


Elements els1 = doc.getElementsByTag("html");

System.out.println(els1);
System.out.println("=====================标签meta=============================================");
Elements els2 = doc.getElementsByTag("meta");

System.out.println(els2);
System.out.println("=====================标签meta的内容=============================================");

 Elements lit1 = doc.select("meta");  
 // 使用循环遍历每个标签数据  
 for (Iterator<Element> it = lit1.iterator(); it.hasNext();) {  
     Element esta = (Element) it.next();  
     // 输出其text值和其属性值 
     System.out.println(esta.text() + " " + esta.attr("content"));  
  }  
System.out.println("=====================link标签里面href里的内容=============================================");

Elements li = doc.select("link");  
// 使用循环遍历每个标签数据  
for (Iterator<Element> it = li.iterator(); it.hasNext();) {  
    Element es = (Element) it.next();  
    // 输出其text值和其属性值（可以将href改为其他属性名称，例如src）  
    System.out.println(es.text() + " " + es.attr("href"));  
 }  
System.out.println("=====================img里src的内容=============================================");
// 使用jsoup将html里面的a标签里面的数据全部读取出来（假如想读取其他标签，直接将a改为其他标签名称即可，例如"img"）  
 Elements lit = doc.select("img");  
 // 使用循环遍历每个标签数据  
 for (Iterator<Element> it = lit.iterator(); it.hasNext();) {  
     Element est = (Element) it.next();  
     // 输出其text值和其属性值（可以将href改为其他属性名称，例如src）  
     System.out.println(est.text() + " " + est.attr("src"));  
  } 
 System.out.println("=====================ul= li============================================");
// 使用jsoup将html里面的a标签里面的数据全部读取出来（假如想读取其他标签，直接将a改为其他标签名称即可，例如"img"）  
  Elements lite = doc.select("ul");  
  // 使用循环遍历每个标签数据  
  for (Iterator<Element> it1 = lite.iterator(); it1.hasNext();) {  
      Element estsw = (Element) it1.next();  
      // 输出其text值和其属性值（可以将href改为其他属性名称，例如src）  
      System.out.println(estsw.text() + " "+ estsw.attr("li"));  
 System.out.println("=====================table= td============================================");
// 使用jsoup将html里面的a标签里面的数据全部读取出来（假如想读取其他标签，直接将a改为其他标签名称即可，例如"img"）  
  Elements lit2 = doc.select("table");  
  // 使用循环遍历每个标签数据  
  for (Iterator<Element> it = lit2.iterator(); it.hasNext();) {  
      Element ests = (Element) it.next();  
      // 输出其text值和其属性值（可以将href改为其他属性名称，例如src）  
      System.out.println(ests.text() + " "+ ests.attr("td"));  
   } 
  System.out.println("=====================tr=============================================");
  Elements lit3 = doc.select("table");  
  // 使用循环遍历每个标签数据  
  for (Iterator<Element> it = lit3.iterator(); it.hasNext();) {  
      Element ests = (Element) it.next();  
      // 输出其text值和其属性值（可以将href改为其他属性名称，例如src）  
      System.out.println(ests.text() + " "+ ests.attr("tr"));  
   } 
//                  System.out.println("====================================================layout 获取指定的内容==============");
//                  Elements Liws2= doc.getElementsByAttributeValue("class","layout");
//                  for (Element element :Liws2) {
//                      System.out.println(element.html());
//                  }
  System.out.println("====================================================class为content 的html代码内容==============");
  Elements Liws1= doc.getElementsByAttributeValue("class","content");
  for (Element element :Liws1) {
      System.out.println(element.html());
  }
  System.out.println("=====================font==style内容=============================================");
  Elements litg = doc.select("font");  
  // 使用循环遍历每个标签数据  
  for (Iterator<Element> it = litg.iterator(); it.hasNext();) {  
      Element ests = (Element) it.next();  
      // 输出其text值和其属性值（可以将href改为其他属性名称，例如src）  
      System.out.println(ests.text() + " "+ ests.attr("style"));  
   } 
  }
  System.out.println("=====================table=============================================");
  Elements litg1 = doc.getElementsByTag("table");  

      System.out.println(litg1);  

      System.out.println("======================获取a标签=============================================");
   Elements ListDivs = doc.getElementsByAttributeValue("id","clist");
   for (Element element :ListDivs) {
       Elements ln = element.getElementsByTag("a");
       System.out.println(ln);
   }
System.out.println("======================获取时间remark内容=============================================");
   Element Liws1= doc.getElementsByAttributeValue("class", "remark").first(); 
 System.out.println(Liws1);

  System.out.println("=====================获取每列中img标签 =============================================");
    Elements trs = doc.select("table").select("tr");
       for(int i = 0;i<trs.size();i++){
           Elements tds = trs.get(i).select("td");
           for(int j = 0;j<tds.size();j++){
            Elements text= tds.get(j).select("img");
             for (Iterator<Element> it = text.iterator(); it.hasNext();) {  
                    Element est = (Element) it.next();  
                    // 输出其text值和其属性值（可以将href改为其他属性名称，例如src）  
                    System.out.println(est.attr("src",est.attr("src").substring(3)));  
                    } 

           }

   }

          System.out.println("=====================每行a 标签href内容并截取前四位将后缀名改为.jsp=============================================");
    Elements trsw = doc.select("table").select("tr");
       for(int i = 0;i<trsw.size();i++){
           Elements tds = trsw.get(i).select("td");
           for(int j = 0;j<tds.size();j++){
            Elements text= tds.get(j).select("a");
             for (Iterator<Element> it = text.iterator(); it.hasNext();) {  
                    Element est = (Element) it.next();  
                    // 输出其text值和其属性值（可以将href改为其他属性名称，例如src）  
                    System.out.println(est.attr("href").substring(0, 4)+".jsp");  
                    } 

           }
       }

       System.out.println("=====================table里每行的文本内容=============================================");
    Elements trsw1 = doc.select("table").select("tr");
       for(int i = 0;i<trsw1.size();i++){
           Elements tds = trsw1.get(i).select("td");
           for(int j = 0;j<tds.size();j++){
            String text= tds.get(j).text();
             System.out.print(text);
           }
           System.out.println();
       }
//                      System.out.println("=====================img某个列=============================================");
//                          Elements trsw2 = doc.select("table").select("tr");
//                          for(int i = 0;i<trsw2.size();i++){
//                              Elements tds = trsw2.get(i).select("td");
//                              for(int j = 0;j<1;j++){
//                                  String text= tds.get(j).text();
//                                   System.out.println(text);
//                                    
//                                 
//                              }
//                          }
       System.out.println("======================获取指定的a链接文本内容=============================================");
    Elements trsw3 = doc.select("table").select("tr");
       for(int i = 0;i<trsw3.size();i++){
           Elements tds3 = trsw3.get(i).select("td");
           for(int j = 0;j<tds3.size();j++){
            String text= tds3.get(j).getElementsByTag("a").text();
             System.out.print(text);


           }
           System.out.println();
       }
       System.out.println("=====================每行第一列td:lt(1)文本内容============================================");
    Elements trsi2 = doc.select("table").select("tr");
       for(int i = 0;i<trsi2.size();i++){
           Elements tds = trsi2.get(i).select("td:lt(1)");
           for (@SuppressWarnings("unused") Element element :ListDivs) {
            String ln = tds.text();
            System.out.println(ln);
        }

           }  
       System.out.println("=====================每行a 标签href内容=============================================");
    Elements trswy = doc.select("table").select("tr");
       for(int i = 0;i<trswy.size();i++){
           Elements tds = trswy.get(i).select("td");
           for(int j = 0;j<tds.size();j++){
            Elements text= tds.get(j).select("a");
             for (Iterator<Element> it = text.iterator(); it.hasNext();) {  
                    Element est = (Element) it.next();  
                    // 输出其text值和其属性值（可以将href改为其他属性名称，例如src）  
                    System.out.println(est.attr("href"));  
                    } 

           }
       }
      System.out.println("=====================img标签 src内容前加 http://www.86pm25.com/=============================================");
    Elements trse = doc.select("table").select("tr");
       for(int i = 0;i<trse.size();i++){
           Elements tds = trse.get(i).select("td");
           for(int j = 0;j<tds.size();j++){
            Elements text= tds.get(j).select("img");
             for (Iterator<Element> it = text.iterator(); it.hasNext();) {  
                    Element est = (Element) it.next();  
                    // 输出其text值和其属性值（可以将href改为其他属性名称，例如src）  
                    est.attr("src", "http://www.86pm25.com/"+ est.attr("src").substring(3)); 

                    } 

           }

   }

      System.out.println("=====================href标签 href内容后缀改为.jsp=============================================");
        Elements trsi = doc.select("table").select("tr");
        for(int i = 0;i<trsi.size();i++){
            Elements tds = trsi.get(i).select("td");
            for(int j = 0;j<tds.size();j++){
                Elements text= tds.get(j).select("a");

                 for (Iterator<Element> it = text.iterator(); it.hasNext();) {  
                    Element est = (Element) it.next();  
                    // 输出其text值和其属性值（可以将href改为其他属性名称，例如src） 
                   est.removeAttr("target");
                  est.attr("href",est.attr("href").substring(0, 4)+".jsp"); 
              //   doc = Jsoup.connect("http://www.86pm25.com/city/"+est.attr("href").substring(0, 4)+".html").timeout(3000000).get();
                  //  System.out.println("http://www.86pm25.com/city/"+est.attr("href").substring(0, 4)+".html");
                 } 
                text.attr("onclick", "loadXMLDoc"+i+"()");
            }


    }      System.out.println( trsi);              


    System.out.println("=====================td中给a标签添加onclick为loadXMLDoci()的 事件=============================================");
    Elements trsit = doc.select("table").select("tr");
       for(int i = 0;i<trsit.size();i++){
         Elements tds = trsi.get(i).select("td");
           for(int j = 0;j<tds.size();j++){
                Elements text= tds.get(j).select("a");
         Elements tdst=text.attr("onclick", "loadXMLDoc"+i+"()");
           System.out.print(tdst);
           }
           System.out.println();
           }

}   

}


