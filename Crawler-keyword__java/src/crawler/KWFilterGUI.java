package crawler;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

@SuppressWarnings({ "serial", "unused" })
public class KWFilterGUI extends JPanel{

	String path="D:\\crawler\\TEXT.txt";
	JTextField text;
	
	public KWFilterGUI() {
	JPanel jp=new JPanel();
	JPanel jp1=new JPanel();
	JPanel jp2=new JPanel();
	JPanel jp3=new JPanel();

	this.setLayout(new BorderLayout());
	jp.setLayout(new GridLayout(2,1));

	text = new JTextField(6);

	text.setText("敏感词识别");

	text.setEditable(false);

JButton jb0=new JButton("选择文本文件");
jp1.add(text);
jp1.add(jb0);
jp.add(jp1);

JButton jb = new JButton("start"); //创建一个按钮
JButton jb1= new JButton("clear");
jp2.add(jb);
jp2.add(jb1);
jp.add(jp2);

this.add(jp,BorderLayout.NORTH);

JTextArea jta=new JTextArea(6,35);
jta.setLineWrap(true);//自动换行
//jta.setMaximumSize(new Dimension(6,35));
JScrollPane jsp = new JScrollPane(jta , JScrollPane.VERTICAL_SCROLLBAR_ALWAYS ,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);    //创建滚动条，需要指定添加位置
jsp.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); //设置滚动条的形式
//jta.setBackground(new Color(255,255,255) );//文本域背景色
jsp.setBounds(13, 10, 350, 340);
jp3.add(jsp);
this.add(jp3,BorderLayout.CENTER);

jta.append("当前文件："+path+'\n');

jb0.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
	FileNameExtensionFilter filter=new FileNameExtensionFilter("*.txt","txt");

    JFileChooser file=new JFileChooser("D:\\crawler");
    file.setFileFilter(filter);//设置一个文件筛选器

    file.setAcceptAllFileFilterUsed(false);//下面这句是去掉显示所有文件这个过滤器。
    int result = file.showOpenDialog(null);
    if(result == JFileChooser.APPROVE_OPTION){//获得你选择的文件绝对路径并输出
        path = file.getSelectedFile().getAbsolutePath();
        jta.setText("");
        jta.append("当前文件："+path+'\n');
     }
    
}});

jb.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
			try {
				
				 File filename = new File(path); // 要读取以上路径的input。txt文件  
	                InputStreamReader reader = new InputStreamReader(  
	                        new FileInputStream(filename)); // 建立一个输入流对象reader  
	                @SuppressWarnings("resource")
					BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言  
	                String line = "";  
	                line = br.readLine();  
	                StringBuffer s=new StringBuffer();
	                while (line != null) {  
	                	s.append(line);
	                    line = br.readLine(); // 一次读入一行数据  
	                } 
	                
	                KWFilter kwf=new KWFilter();
	                jta.append(kwf.replace(s.toString()));
	                //jta.append(s.toString());
				
				} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
				}
		
	}});

jb1.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		jta.setText("");
        jta.append("当前文件："+path+'\n');
}});	
setVisible(true); 

}

}
