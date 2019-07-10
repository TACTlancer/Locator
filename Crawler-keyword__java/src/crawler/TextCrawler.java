package crawler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TextCrawler {

    public TextCrawler(String s) throws IOException {
    	
	Document doc = Jsoup.connect("https://"+s).timeout(3000000).get();

/*	System.out.println("=========================获取标题=========================================");
	String title = doc.title();
	System.out.println(title);
	System.out.println("=========================获取内容=========================================");
	Elements Liws= doc.getElementsByAttributeValue("class","fleft");
	for (Element element :Liws) {
	  System.out.println(element.html());
	}
	Elements el = doc.getElementsByTag("p"); 
	System.out.println("\n\n\n"+el+"\n");

File file = new File("D:TEXT.txt");
@SuppressWarnings("resource")
PrintStream ps = new PrintStream(new FileOutputStream(file));
ps.println(title+el);
*/
StringBuffer English=new StringBuffer();
StringBuffer Chinese=new StringBuffer();

 Elements body = doc.getElementsByTag("body");     
 for (Element Text : body) {
       String text = Text.text();
       for(int i=0;i<text.length();i++) {
           char c=text.charAt(i);
           if(  (c >= 0x4E00 &&  c <= 0x9FA5  )||  c ==' ') {
               Chinese.append(c);
           }
           if ((c>='a' && c<='z') || (c>='A' && c<='Z') || c==' ') {
               English.append(c);
           }
       }    
    }

File file = new File("D:\\crawler\\TEXT.txt");
@SuppressWarnings("resource")
PrintStream ps = new PrintStream(new FileOutputStream(file));
ps.append(Chinese);
ps.append(English);
 //System.out.println(Chinese);
 //System.out.println(English);
 
 
    }
}
