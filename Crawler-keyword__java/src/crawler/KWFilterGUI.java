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

	text.setText("���д�ʶ��");

	text.setEditable(false);

JButton jb0=new JButton("ѡ���ı��ļ�");
jp1.add(text);
jp1.add(jb0);
jp.add(jp1);

JButton jb = new JButton("start"); //����һ����ť
JButton jb1= new JButton("clear");
jp2.add(jb);
jp2.add(jb1);
jp.add(jp2);

this.add(jp,BorderLayout.NORTH);

JTextArea jta=new JTextArea(6,35);
jta.setLineWrap(true);//�Զ�����
//jta.setMaximumSize(new Dimension(6,35));
JScrollPane jsp = new JScrollPane(jta , JScrollPane.VERTICAL_SCROLLBAR_ALWAYS ,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);    //��������������Ҫָ�����λ��
jsp.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); //���ù���������ʽ
//jta.setBackground(new Color(255,255,255) );//�ı��򱳾�ɫ
jsp.setBounds(13, 10, 350, 340);
jp3.add(jsp);
this.add(jp3,BorderLayout.CENTER);

jta.append("��ǰ�ļ���"+path+'\n');

jb0.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
	FileNameExtensionFilter filter=new FileNameExtensionFilter("*.txt","txt");

    JFileChooser file=new JFileChooser("D:\\crawler");
    file.setFileFilter(filter);//����һ���ļ�ɸѡ��

    file.setAcceptAllFileFilterUsed(false);//���������ȥ����ʾ�����ļ������������
    int result = file.showOpenDialog(null);
    if(result == JFileChooser.APPROVE_OPTION){//�����ѡ����ļ�����·�������
        path = file.getSelectedFile().getAbsolutePath();
        jta.setText("");
        jta.append("��ǰ�ļ���"+path+'\n');
     }
    
}});

jb.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
			try {
				
				 File filename = new File(path); // Ҫ��ȡ����·����input��txt�ļ�  
	                InputStreamReader reader = new InputStreamReader(  
	                        new FileInputStream(filename)); // ����һ������������reader  
	                @SuppressWarnings("resource")
					BufferedReader br = new BufferedReader(reader); // ����һ�����������ļ�����ת�ɼ�����ܶ���������  
	                String line = "";  
	                line = br.readLine();  
	                StringBuffer s=new StringBuffer();
	                while (line != null) {  
	                	s.append(line);
	                    line = br.readLine(); // һ�ζ���һ������  
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
        jta.append("��ǰ�ļ���"+path+'\n');
}});	
setVisible(true); 

}

}
