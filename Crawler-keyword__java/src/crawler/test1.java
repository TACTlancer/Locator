package crawler;

import java.io.*;

import java.net.*;

import java.text.SimpleDateFormat;

import java.util.Date;

import java.util.regex.Matcher;

import java.util.regex.Pattern;

public class test1 {

/**

 * java实现爬虫

 */


	//爬取链接

	// String regex = "(http|https)://[\\w+\\.?/?]+\\.[A-Za-z]+";

	public static void spiderURL(String url,String regex,String filename){

		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

	

		String time=sdf.format(new Date());

		URL realURL=null;

		URLConnection connection=null;

		BufferedReader br=null;

		PrintWriter pw=null;

		PrintWriter pw1=null;

		

		Pattern pattern=Pattern.compile(regex);

		try{

			realURL=new URL(url);

			connection=realURL.openConnection();

			//connection.connect();

 

			File fileDir = new File("E:/spider/"+time);

			if (!fileDir.exists()) {

				fileDir.mkdirs();

			}

			//将爬取到的内容放到E盘相应目录下   

			pw = new PrintWriter(new FileWriter("E:/spider/"+time+"/"+filename+"_content.txt"),true);

			pw1 = new PrintWriter(new FileWriter("E:/spider/"+time+"/"+filename+"_URL.txt"),true);

			

			br=new BufferedReader(new InputStreamReader(connection.getInputStream()));

			String line=null;

			

			

			while((line=br.readLine())!=null){

				pw.println(line);

				Matcher matcher=pattern.matcher(line);

				while(matcher.find()){

					pw1.println(matcher.group());

				}

				

			}

			System.out.println("爬取成功！");

		}catch(Exception e){

			e.printStackTrace();

		}finally{

			try {

				br.close();

				pw.close();

				pw1.close();

			} catch (IOException e) {

				e.printStackTrace();

			}

			

		}

		

	}

    public void test(String url) {

    	String regex= "(http|https)://[\\w+\\.?/?]+\\.[A-Za-z]+";

    	spiderURL(url,regex,"rua");

    }


}
