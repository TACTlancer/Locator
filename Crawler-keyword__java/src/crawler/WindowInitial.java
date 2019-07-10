package crawler;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

@SuppressWarnings("serial")
public class WindowInitial extends JFrame implements ActionListener

{

	JPanel panel;//�����õ������
	JTextField text;
	JMenuBar bar;
	JMenu menu;
	JMenuItem itemInitial,itemhtml,itemtext,itemkwfilter,itemmore,itemkwedit;

	FlowLayout layout = new FlowLayout();

	HTMLCrawlerGUI hgui;//����ÿ��ҳ�����ݶ�Ӧ����
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

		menu = new JMenu("�˵�");

		itemInitial = new JMenuItem("��ʼ");//��ʼ���˵��ϵĸ���ѡ��
		itemhtml = new JMenuItem("HTMLԴ����ȡ");
		itemtext = new JMenuItem("��ҳ�ı���ȡ");
		itemkwfilter=new JMenuItem("�ؼ���ʶ��");
		itemmore=new JMenuItem("����ҳ��ȡ");
		itemkwedit=new JMenuItem("���дʱ༭");

		panel = new JPanel();

		text = new JTextField(10);

		hgui=new HTMLCrawlerGUI();//��ʼ�����������
		tgui=new TextCrawlerGUI();
		kwfgui=new KWFilterGUI();
		mgui=new MoreCrawlerGUI();
		kwegui=new KWEditGUI();

		text.setText("����˵�ѡ����");
		text.setEditable(false);

		itemInitial.addActionListener(this);//Ϊÿ���˵�����Ӷ���������
		itemhtml.addActionListener(this);
		itemtext.addActionListener(this);
		itemkwfilter.addActionListener(this);
		itemmore.addActionListener(this);
		itemkwedit.addActionListener(this);

		bar.add(menu);

		menu.add(itemInitial);//��ÿ���˵������˵�
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

		if(e.getSource() == itemhtml){//html��ȡ���档

			panel.removeAll();

			panel.add("HTMLԴ����ȡ" , hgui);//�л����롣

			panel.validate();

			repaint();

		}
		
		if(e.getSource()==itemtext) {//�ı���ȡ����

			panel.removeAll();

			panel.add("��ҳ�ı���ȡ" , tgui);//�л����롣

			panel.validate();

			repaint();
		}
		
		if(e.getSource() == itemkwfilter){//���д���ȡ��

			panel.removeAll();

			panel.add("���д���ȡ" , kwfgui);//�л����롣

			panel.validate();

			repaint();

		}
		
		if(e.getSource() == itemmore){//����ҳ��ȡ

			panel.removeAll();

			panel.add("����ҳ��ȡ" , mgui);//�л����롣

			panel.validate();

			repaint();

		}
		
		if(e.getSource() == itemkwedit){//���дʿ�༭��

			panel.removeAll();

			panel.add("���дʿ�༭" , kwegui);//�л����롣

			panel.validate();

			repaint();

		}

		else if(e.getSource() == itemInitial){//��ʼ���档

			panel.removeAll();

			panel.add(text);

			panel.validate();

			repaint();

		}

	}

}

