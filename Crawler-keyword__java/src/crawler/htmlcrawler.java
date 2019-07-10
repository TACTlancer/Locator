package crawler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class htmlcrawler {
	public htmlcrawler(String s) throws IOException {

		Document doc = Jsoup.connect("https://"+s).timeout(3000000).get();
		
//System.out.println("=====================获取整个网页的html代码=============================================");


Elements els = doc.getElementsByTag("html");

//System.out.println(els);

File file = new File("D:\\crawler\\HTML.txt");
@SuppressWarnings("resource")
PrintStream ps = new PrintStream(new FileOutputStream(file));
ps.println(els);
	}
}
