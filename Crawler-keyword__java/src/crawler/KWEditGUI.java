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
public class KWEditGUI extends JPanel{
	
	JTextField text;
	
	public KWEditGUI() {
	JPanel jp1=new JPanel();
	JPanel jp2=new JPanel();

	this.setLayout(new GridLayout(2,1));

	text = new JTextField(7);

	text.setText("敏感词库编辑");

	text.setEditable(false);



jp1.add(text);
this.add(jp1);

JButton jb = new JButton("start"); //创建一个按钮

jb.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
			try {
				
					Desktop.getDesktop().open(new File("D:\\crawler\\SensitiveWord.txt"));
				
				} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
				}
		
	}});

jp2.add(jb);
this.add(jp2);
setVisible(true); 

}

}
