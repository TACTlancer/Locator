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

System.out.println("=========================��ȡ����=========================================");
String title = doc.title();
System.out.println(title);
System.out.println("===========================��ȡclass:warp ��ǩ�������=======================================");
Elements links=doc.getElementsByClass("wrap");

for(Element link:links){
  System.out.println( link.text());
}
System.out.println("============================��ȡ����javascript����======================================");


Elements els = doc.getElementsByTag("script");

System.out.println("\n\n\n"+els+"\n");
System.out.println("======================��ȡָ�������Ӽ�����=============================================");

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
System.out.println("==================================================== ��ȡָ����ǩ������==============");
Elements Liws= doc.getElementsByAttributeValue("class","fleft");
for (Element element :Liws) {
  System.out.println(element.html());
}
System.out.println("==================================================== ��ȡ����td��ǩ ==============");

//��ȡ����td��ǩ 
Elements el = doc.getElementsByTag("td"); 
System.out.println("\n\n\n"+el+"\n"); 

System.out.println("==================================================== ��ȡҳ����ڶ���script��ǩ ==============");
Elements e = doc.getElementsByTag("script").eq(2);  
System.out.println(e); 

System.out.println("====================================================��ȡҳ����ڶ���script��ǩ==============");
Elements elScripts = doc.getElementsByTag("script");  
String elScriptList = elScripts.get(2).data();
System.out.println(elScriptList); 

System.out.println("==================================================== ��ȡ�ڶ���script��ǩ ���aqiPercent��ֵ==============");
Elements eles = doc.getElementsByTag("script").eq(2);
for (Element ele : eles) {

// �z���Ƿ���detailInfoObject�ִ�
String script = ele.toString();
if (script.indexOf("aqiPercent") > -1 ) {

// ֻȡ��script�ă���
script = ele.childNode(0).toString();

// ʹ��ScriptEngine��parse
ScriptEngine engine = new ScriptEngineManager().getEngineByName("javascript");
engine.eval(script);

// ȡ����Ҫ��׃��
Object obj = engine.get("aqiPercent");
System.out.println("aqiPercent= " + obj);

}
System.out.println("==================================================== ��ȡ�ڶ���script��ǩ ���idx��ֵ==============");
if (script.indexOf("idx") > -1 ) {

// ֻȡ��script�ă���
script = ele.childNode(0).toString();

// ʹ��ScriptEngine��parse
ScriptEngine engine = new ScriptEngineManager().getEngineByName("javascript");
engine.eval(script);

// ȡ����Ҫ��׃��
Object obj1 = engine.get("idx");
System.out.println("idx = " + obj1);
}
System.out.println("==================================================== ��ȡ�ڶ���script��ǩ ���qualityStr��ֵ==============");
if (script.indexOf("qualityStr") > -1 ) {

// ֻȡ��script�ă���
script = ele.childNode(0).toString();

// ʹ��ScriptEngine��parse
ScriptEngine engine = new ScriptEngineManager().getEngineByName("javascript");
engine.eval(script);

// ȡ����Ҫ��׃��
Object obj2 = engine.get("qualityStr");
System.out.println("qualityStr = " + obj2);
}
System.out.println("==================================================== ��ȡ�ڶ���script��ǩ ���aqiLevel��ֵ==============");
if (script.indexOf("aqiLevel") > -1 ) {

// ֻȡ��script�ă���
script = ele.childNode(0).toString();

// ʹ��ScriptEngine��parse
ScriptEngine engine = new ScriptEngineManager().getEngineByName("javascript");
engine.eval(script);

// ȡ����Ҫ��׃��
Object obj3 = engine.get("aqiLevel");
System.out.println("aqiLevel = " + obj3);
}
}
System.out.println("=====================��ȡ������ҳ��html����=============================================");


Elements els1 = doc.getElementsByTag("html");

System.out.println(els1);
System.out.println("=====================��ǩmeta=============================================");
Elements els2 = doc.getElementsByTag("meta");

System.out.println(els2);
System.out.println("=====================��ǩmeta������=============================================");

 Elements lit1 = doc.select("meta");  
 // ʹ��ѭ������ÿ����ǩ����  
 for (Iterator<Element> it = lit1.iterator(); it.hasNext();) {  
     Element esta = (Element) it.next();  
     // �����textֵ��������ֵ 
     System.out.println(esta.text() + " " + esta.attr("content"));  
  }  
System.out.println("=====================link��ǩ����href�������=============================================");

Elements li = doc.select("link");  
// ʹ��ѭ������ÿ����ǩ����  
for (Iterator<Element> it = li.iterator(); it.hasNext();) {  
    Element es = (Element) it.next();  
    // �����textֵ��������ֵ�����Խ�href��Ϊ�����������ƣ�����src��  
    System.out.println(es.text() + " " + es.attr("href"));  
 }  
System.out.println("=====================img��src������=============================================");
// ʹ��jsoup��html�����a��ǩ���������ȫ����ȡ�������������ȡ������ǩ��ֱ�ӽ�a��Ϊ������ǩ���Ƽ��ɣ�����"img"��  
 Elements lit = doc.select("img");  
 // ʹ��ѭ������ÿ����ǩ����  
 for (Iterator<Element> it = lit.iterator(); it.hasNext();) {  
     Element est = (Element) it.next();  
     // �����textֵ��������ֵ�����Խ�href��Ϊ�����������ƣ�����src��  
     System.out.println(est.text() + " " + est.attr("src"));  
  } 
 System.out.println("=====================ul= li============================================");
// ʹ��jsoup��html�����a��ǩ���������ȫ����ȡ�������������ȡ������ǩ��ֱ�ӽ�a��Ϊ������ǩ���Ƽ��ɣ�����"img"��  
  Elements lite = doc.select("ul");  
  // ʹ��ѭ������ÿ����ǩ����  
  for (Iterator<Element> it1 = lite.iterator(); it1.hasNext();) {  
      Element estsw = (Element) it1.next();  
      // �����textֵ��������ֵ�����Խ�href��Ϊ�����������ƣ�����src��  
      System.out.println(estsw.text() + " "+ estsw.attr("li"));  
 System.out.println("=====================table= td============================================");
// ʹ��jsoup��html�����a��ǩ���������ȫ����ȡ�������������ȡ������ǩ��ֱ�ӽ�a��Ϊ������ǩ���Ƽ��ɣ�����"img"��  
  Elements lit2 = doc.select("table");  
  // ʹ��ѭ������ÿ����ǩ����  
  for (Iterator<Element> it = lit2.iterator(); it.hasNext();) {  
      Element ests = (Element) it.next();  
      // �����textֵ��������ֵ�����Խ�href��Ϊ�����������ƣ�����src��  
      System.out.println(ests.text() + " "+ ests.attr("td"));  
   } 
  System.out.println("=====================tr=============================================");
  Elements lit3 = doc.select("table");  
  // ʹ��ѭ������ÿ����ǩ����  
  for (Iterator<Element> it = lit3.iterator(); it.hasNext();) {  
      Element ests = (Element) it.next();  
      // �����textֵ��������ֵ�����Խ�href��Ϊ�����������ƣ�����src��  
      System.out.println(ests.text() + " "+ ests.attr("tr"));  
   } 
//                  System.out.println("====================================================layout ��ȡָ��������==============");
//                  Elements Liws2= doc.getElementsByAttributeValue("class","layout");
//                  for (Element element :Liws2) {
//                      System.out.println(element.html());
//                  }
  System.out.println("====================================================classΪcontent ��html��������==============");
  Elements Liws1= doc.getElementsByAttributeValue("class","content");
  for (Element element :Liws1) {
      System.out.println(element.html());
  }
  System.out.println("=====================font==style����=============================================");
  Elements litg = doc.select("font");  
  // ʹ��ѭ������ÿ����ǩ����  
  for (Iterator<Element> it = litg.iterator(); it.hasNext();) {  
      Element ests = (Element) it.next();  
      // �����textֵ��������ֵ�����Խ�href��Ϊ�����������ƣ�����src��  
      System.out.println(ests.text() + " "+ ests.attr("style"));  
   } 
  }
  System.out.println("=====================table=============================================");
  Elements litg1 = doc.getElementsByTag("table");  

      System.out.println(litg1);  

      System.out.println("======================��ȡa��ǩ=============================================");
   Elements ListDivs = doc.getElementsByAttributeValue("id","clist");
   for (Element element :ListDivs) {
       Elements ln = element.getElementsByTag("a");
       System.out.println(ln);
   }
System.out.println("======================��ȡʱ��remark����=============================================");
   Element Liws1= doc.getElementsByAttributeValue("class", "remark").first(); 
 System.out.println(Liws1);

  System.out.println("=====================��ȡÿ����img��ǩ =============================================");
    Elements trs = doc.select("table").select("tr");
       for(int i = 0;i<trs.size();i++){
           Elements tds = trs.get(i).select("td");
           for(int j = 0;j<tds.size();j++){
            Elements text= tds.get(j).select("img");
             for (Iterator<Element> it = text.iterator(); it.hasNext();) {  
                    Element est = (Element) it.next();  
                    // �����textֵ��������ֵ�����Խ�href��Ϊ�����������ƣ�����src��  
                    System.out.println(est.attr("src",est.attr("src").substring(3)));  
                    } 

           }

   }

          System.out.println("=====================ÿ��a ��ǩhref���ݲ���ȡǰ��λ����׺����Ϊ.jsp=============================================");
    Elements trsw = doc.select("table").select("tr");
       for(int i = 0;i<trsw.size();i++){
           Elements tds = trsw.get(i).select("td");
           for(int j = 0;j<tds.size();j++){
            Elements text= tds.get(j).select("a");
             for (Iterator<Element> it = text.iterator(); it.hasNext();) {  
                    Element est = (Element) it.next();  
                    // �����textֵ��������ֵ�����Խ�href��Ϊ�����������ƣ�����src��  
                    System.out.println(est.attr("href").substring(0, 4)+".jsp");  
                    } 

           }
       }

       System.out.println("=====================table��ÿ�е��ı�����=============================================");
    Elements trsw1 = doc.select("table").select("tr");
       for(int i = 0;i<trsw1.size();i++){
           Elements tds = trsw1.get(i).select("td");
           for(int j = 0;j<tds.size();j++){
            String text= tds.get(j).text();
             System.out.print(text);
           }
           System.out.println();
       }
//                      System.out.println("=====================imgĳ����=============================================");
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
       System.out.println("======================��ȡָ����a�����ı�����=============================================");
    Elements trsw3 = doc.select("table").select("tr");
       for(int i = 0;i<trsw3.size();i++){
           Elements tds3 = trsw3.get(i).select("td");
           for(int j = 0;j<tds3.size();j++){
            String text= tds3.get(j).getElementsByTag("a").text();
             System.out.print(text);


           }
           System.out.println();
       }
       System.out.println("=====================ÿ�е�һ��td:lt(1)�ı�����============================================");
    Elements trsi2 = doc.select("table").select("tr");
       for(int i = 0;i<trsi2.size();i++){
           Elements tds = trsi2.get(i).select("td:lt(1)");
           for (@SuppressWarnings("unused") Element element :ListDivs) {
            String ln = tds.text();
            System.out.println(ln);
        }

           }  
       System.out.println("=====================ÿ��a ��ǩhref����=============================================");
    Elements trswy = doc.select("table").select("tr");
       for(int i = 0;i<trswy.size();i++){
           Elements tds = trswy.get(i).select("td");
           for(int j = 0;j<tds.size();j++){
            Elements text= tds.get(j).select("a");
             for (Iterator<Element> it = text.iterator(); it.hasNext();) {  
                    Element est = (Element) it.next();  
                    // �����textֵ��������ֵ�����Խ�href��Ϊ�����������ƣ�����src��  
                    System.out.println(est.attr("href"));  
                    } 

           }
       }
      System.out.println("=====================img��ǩ src����ǰ�� http://www.86pm25.com/=============================================");
    Elements trse = doc.select("table").select("tr");
       for(int i = 0;i<trse.size();i++){
           Elements tds = trse.get(i).select("td");
           for(int j = 0;j<tds.size();j++){
            Elements text= tds.get(j).select("img");
             for (Iterator<Element> it = text.iterator(); it.hasNext();) {  
                    Element est = (Element) it.next();  
                    // �����textֵ��������ֵ�����Խ�href��Ϊ�����������ƣ�����src��  
                    est.attr("src", "http://www.86pm25.com/"+ est.attr("src").substring(3)); 

                    } 

           }

   }

      System.out.println("=====================href��ǩ href���ݺ�׺��Ϊ.jsp=============================================");
        Elements trsi = doc.select("table").select("tr");
        for(int i = 0;i<trsi.size();i++){
            Elements tds = trsi.get(i).select("td");
            for(int j = 0;j<tds.size();j++){
                Elements text= tds.get(j).select("a");

                 for (Iterator<Element> it = text.iterator(); it.hasNext();) {  
                    Element est = (Element) it.next();  
                    // �����textֵ��������ֵ�����Խ�href��Ϊ�����������ƣ�����src�� 
                   est.removeAttr("target");
                  est.attr("href",est.attr("href").substring(0, 4)+".jsp"); 
              //   doc = Jsoup.connect("http://www.86pm25.com/city/"+est.attr("href").substring(0, 4)+".html").timeout(3000000).get();
                  //  System.out.println("http://www.86pm25.com/city/"+est.attr("href").substring(0, 4)+".html");
                 } 
                text.attr("onclick", "loadXMLDoc"+i+"()");
            }


    }      System.out.println( trsi);              


    System.out.println("=====================td�и�a��ǩ���onclickΪloadXMLDoci()�� �¼�=============================================");
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


