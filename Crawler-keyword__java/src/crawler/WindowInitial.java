package crawler;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

@SuppressWarnings("serial")
public class WindowInitial extends JFrame implements ActionListener

{

	JPanel panel;//声明用到的组件
	JTextField text;
	JMenuBar bar;
	JMenu menu;
	JMenuItem itemInitial,itemhtml,itemtext,itemkwfilter,itemmore,itemkwedit;

	FlowLayout layout = new FlowLayout();

	HTMLCrawlerGUI hgui;//声明每个页面内容对应的类
	TextCrawlerGUI tgui;
	KWFilterGUI kwfgui;
	MoreCrawlerGUI mgui;
	KWEditGUI kwegui;
	
	
	WindowInitial(){

		init();
		
		setBounds(300,300,500,300);

		setVisible(true);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public void init(){

		bar = new JMenuBar();

		menu = new JMenu("菜单");

		itemInitial = new JMenuItem("初始");//初始化菜单上的各个选项
		itemhtml = new JMenuItem("HTML源码提取");
		itemtext = new JMenuItem("网页文本提取");
		itemkwfilter=new JMenuItem("关键词识别");
		itemmore=new JMenuItem("多网页提取");
		itemkwedit=new JMenuItem("敏感词编辑");

		panel = new JPanel();

		text = new JTextField(10);

		hgui=new HTMLCrawlerGUI();//初始化各界面的类
		tgui=new TextCrawlerGUI();
		kwfgui=new KWFilterGUI();
		mgui=new MoreCrawlerGUI();
		kwegui=new KWEditGUI();

		text.setText("点击菜单选择功能");
		text.setEditable(false);

		itemInitial.addActionListener(this);//为每个菜单项添加动作监听器
		itemhtml.addActionListener(this);
		itemtext.addActionListener(this);
		itemkwfilter.addActionListener(this);
		itemmore.addActionListener(this);
		itemkwedit.addActionListener(this);

		bar.add(menu);

		menu.add(itemInitial);//将每个菜单项加入菜单
		menu.add(itemhtml);
		menu.add(itemtext);
		menu.add(itemkwfilter);
		menu.add(itemmore);
		menu.add(itemkwedit);

		panel.setLayout(layout);

		add(panel);

		panel.add(text);

		setJMenuBar(bar);

	}

	public void actionPerformed(ActionEvent e){

		if(e.getSource() == itemhtml){//html提取界面。

			panel.removeAll();

			panel.add("HTML源码提取" , hgui);//切换代码。

			panel.validate();

			repaint();

		}
		
		if(e.getSource()==itemtext) {//文本提取界面

			panel.removeAll();

			panel.add("网页文本提取" , tgui);//切换代码。

			panel.validate();

			repaint();
		}
		
		if(e.getSource() == itemkwfilter){//敏感词提取。

			panel.removeAll();

			panel.add("敏感词提取" , kwfgui);//切换代码。

			panel.validate();

			repaint();

		}
		
		if(e.getSource() == itemmore){//多网页提取

			panel.removeAll();

			panel.add("多网页提取" , mgui);//切换代码。

			panel.validate();

			repaint();

		}
		
		if(e.getSource() == itemkwedit){//敏感词库编辑。

			panel.removeAll();

			panel.add("敏感词库编辑" , kwegui);//切换代码。

			panel.validate();

			repaint();

		}

		else if(e.getSource() == itemInitial){//初始界面。

			panel.removeAll();

			panel.add(text);

			panel.validate();

			repaint();

		}

	}

}

