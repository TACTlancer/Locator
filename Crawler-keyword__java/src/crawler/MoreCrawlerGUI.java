package crawler;

import java.awt.Desktop;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@SuppressWarnings("serial")
public class MoreCrawlerGUI extends JPanel{

	String URLs_path="D:\\crawler\\SiteURL.txt";
	String Log_path="D:\\crawler\\FilterLog.txt";
	JTextField text;
	
	public MoreCrawlerGUI() {
	JPanel jp1=new JPanel();
	JPanel jp2=new JPanel();

	this.setLayout(new GridLayout(2,1));

	text = new JTextField(9);

	text.setText("����ҳ���д���ȡ");

	text.setEditable(false);


JButton jb1 = new JButton("����ַ�༭");

jb1.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
			try {
				
					Desktop.getDesktop().open(new File(URLs_path));
				
				} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
				}
		
	}});

jp1.add(text);
jp1.add(jb1);
this.add(jp1);

JButton jb2 = new JButton("���дʼ�¼");
JButton jb3 = new JButton("�鿴���дʼ�¼");

jb2.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
			try {
				
				File urls = new File(URLs_path); // Ҫ��ȡ����·����txt�ļ�  
                InputStreamReader reader = new InputStreamReader(  
                        new FileInputStream(urls)); // ����һ������������reader  
                @SuppressWarnings("resource")
				BufferedReader br = new BufferedReader(reader); // ����һ�����������ļ�����ת�ɼ�����ܶ���������  
                String line = "";  
                line = br.readLine();  
                StringBuffer s=new StringBuffer();
                while (line != null) {  
                    //System.out.println(line);
                    Document doc = Jsoup.connect(line).timeout(3000000).get();
                    Elements body = doc.getElementsByTag("body");
                    StringBuffer Chinese=new StringBuffer();
                    StringBuffer English=new StringBuffer();
                    KWFilter kwf=new KWFilter();
                    
                    s.append("\r\n------------------------------------\r\n");
                    s.append("��ȡ��ַ��"+line+"\r\n");
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
                        }}
                    String com=Chinese.toString()+English.toString();
                    s.append(kwf.Filter(com)+"\r\n");
                    s.append(kwf.replace(com)+"\r\n");
                    
                    line = br.readLine(); // һ�ζ���һ������ (ָ��һ����ַ
                } 
                File file = new File(Log_path);
                @SuppressWarnings("resource")
				PrintStream ps = new PrintStream(new FileOutputStream(file));
                ps.println(s);
				
				} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
				}
		
	}});

jb3.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
			try {
				
					Desktop.getDesktop().open(new File(Log_path));
				
				} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
				}
		
	}});

jp2.add(jb2);
jp2.add(jb3);
this.add(jp2);
setVisible(true); 

}

}
