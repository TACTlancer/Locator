package crawler;

import java.awt.Desktop;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class HTMLCrawlerGUI extends JPanel{
	
	JTextField text;

	public HTMLCrawlerGUI(){
		JPanel jp1,jp2;
		jp1=new JPanel();
		jp2=new JPanel();

		this.setLayout(new GridLayout(2,1));

		text = new JTextField(15);

		text.setText("HTML��ȡ��������Ŀ����ַ");

		text.setEditable(false);


    JTextField jtf = new JTextField(20); //��ʼ���ı�������20
    
    jp1.add(text);
    jp1.add(jtf);
	this.add(jp1);
    
    JButton jb = new JButton("start"); //����һ����ť
    
    jb.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    	
    		String s=jtf.getText();
    		
    		try {
				@SuppressWarnings("unused")
				htmlcrawler c=new htmlcrawler(s);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    		
    		
    }});
    
    JButton jb1=new JButton("�鿴����");
    
    jb1.addActionListener(new ActionListener() {
    	@Override
    	public void actionPerformed(ActionEvent e) {
    			try {
    				
    					Desktop.getDesktop().open(new File("D:\\crawler\\HTML.txt"));
    				
    				} catch (IOException e1) {
    						// TODO Auto-generated catch block
    						e1.printStackTrace();
    				}
    		
    	}});

    jp2.add(jb);
    jp2.add(jb1);
	this.add(jp2);
    setVisible(true); 
}
	}