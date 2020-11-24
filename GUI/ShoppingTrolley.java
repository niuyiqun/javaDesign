package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

import Client.Connect;
import Client.Test.chat.getMessageThread;
import base.DIYClass.Item;
import base.DIYClass.Message;
import base.DIYClass.User;

public class ShoppingTrolley extends JFrame implements ActionListener{
	JPanel jphy1,jphy2,jphy3,jphy4,jphy4_1,jphy4_2;
	JButton jphy2_jb1,jphy_jb2,jphy_jb3,jphy_jb4,jphy_jb5,jphy4_jb1,delete1,buy1;
	JScrollPane jsp1;
	String onewId;
	JLabel jl1,jl2;
	JTextField jtf;
	Image image;
	User u0;
	Socket send;
	getMessageThread receive;
	Item [] itemArr;
	JSplitPane [] jss;
	JButton []delete,buy;
	
	TextBorderUtlis border = new TextBorderUtlis(Color.black,2,true);
	public ShoppingTrolley(User u,Socket s,getMessageThread r) {
		
//		try { // ʹ��Windows�Ľ�����
//			   UIManager
//			     .setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
//			  } catch (Exception e) {
//			   e.printStackTrace();
//			  }
		
		send=s;
		receive=r;
		u0=u;
		
		Connect con= new Connect();
		Message m=new Message();
		m.setFileCon(1);
		m.setContext(u0.getUserId());
		itemArr=con.getShoppingTrolleyItem(m);
		
		
		jphy1 = new JPanel(new BorderLayout());   //�ܵ�����
		jphy1.setBackground(Color.darkGray);
		
		
//		if (itemArr[0]!=null) {
//			jphy2 = new JPanel(new GridLayout(10,1,10,10));   //�м���ʾ������Ʒ��Ϣ
//			jphy2.setBackground(Color.white);
//			jss = new JSplitPane[9];
//			for (int i = 0; i < itemArr.length; i++) {
//				
//				jss[i]= new JSplitPane();
//				jss[i].setBackground(Color.darkGray);
//				
//				jss[i].setOneTouchExpandable(true);//�÷ָ�����ʾ����ͷ
//				jss[i].setContinuousLayout(true);//������ͷ���ػ�ͼ��
//				jss[i].setDividerSize(0);//���÷ָ��ߵĿ��
//				jss[i].setDividerLocation(150);
//				jss[i].setPreferredSize(new Dimension(300,150));
//				
//				Image image=new ImageIcon(itemArr[i].getImagePos()).getImage();  
//				JPanel panel0 = new BackgroundPanel(image);       //��һ���յ�Jpanel��װͼƬ
//				panel0.setPreferredSize(new Dimension(100, 150));  //���ô�С
//				jss[i].setLeftComponent(panel0);
//				
//				JPanel pane=new JPanel(new GridLayout(1,2));
//				JPanel infopane=new JPanel(new FlowLayout(0,4,20));
////				TextBorderUtlis border = new TextBorderUtlis(Color.black,2,true);
////				infopane.setBorder(border);
//				JLabel info=new JLabel(itemArr[i].getDescription(),JLabel.LEFT);
//				info.setFont(new Font("΢���ź�", Font.PLAIN, 15));
//				infopane.add(info);
//				JPanel buttonpane=new JPanel(new FlowLayout(4,30,60));
//				JButton time= new JButton("ɾ��");
//				JButton join=new JButton("��������");
//				
//				buttonpane.add(time);
//				buttonpane.add(join);
//				pane.add(infopane);
//				pane.add(buttonpane);
//				jss[i].setRightComponent(pane);
//				
////				
//				
//			}
//			
//			for (int i = 0; i < jss.length; i++) {
//				jphy2.add(jss[i]);
//			}
//			
//			jsp1=new JScrollPane(jphy2);
//		}else {
//			jphy2 = new JPanel(new FlowLayout(1));   //�м���ʾ��Ʒ��Ϣ
//			jphy2.setBackground(Color.white);
//			
//			jphy2_jb1= new JButton("ȥ���");
//			jphy2_jb1.addActionListener(this);
//			jphy2_jb1.setBackground(Color.LIGHT_GRAY);
//			jphy2_jb1.setForeground(Color.black);
//			jl2= new JLabel("���Ĺ��ﳵ���ǿյ�");
//			JLabel l1 = new JLabel();
//			ImageIcon icon = new ImageIcon("src\\images\\ͼƬ7.gif"); //�ڴ�ֱ�Ӵ�������,ע��Ŀ¼֮��ķָ�����˫б��
//			l1.setIcon(icon);
//			JLabel l2 = new JLabel("   ");
////			l.setBounds(100, 100, icon.getIconWidth(),icon.getIconHeight());
////			l.setHorizontalTextPosition(JLabel.CENTER);    //����������jlabel��λ��
//			jphy2.add(l1);
//			jphy2.add(l2);
//			jphy2.add(jl2);
//			jphy2.add(jphy2_jb1);
//		}
		
		if (itemArr.length!=0) {
			jphy2 = new JPanel(new GridLayout(10,1,10,10));   //�м���ʾ������Ʒ��Ϣ
			jphy2.setBackground(Color.white);
//			jphy2.setBackground(Color.LIGHT_GRAY);
			delete=new JButton[9];
			buy=new JButton[9];
			
			
			
			jss = new JSplitPane[9];
			for (int i = 0; i < itemArr.length; i++) {
				
					jss[i]= new JSplitPane();
					jss[i].setBackground(Color.darkGray);
					
					jss[i].setOneTouchExpandable(true);//�÷ָ�����ʾ����ͷ
					jss[i].setContinuousLayout(true);//������ͷ���ػ�ͼ��
					jss[i].setDividerSize(0);//���÷ָ��ߵĿ��
					jss[i].setDividerLocation(150);
					jss[i].setPreferredSize(new Dimension(300,150));
					
					Image image=new ImageIcon(itemArr[i].getImagePos()).getImage();  
					JPanel panel0 = new BackgroundPanel(image);       //��һ���յ�Jpanel��װͼƬ
					panel0.setPreferredSize(new Dimension(100, 150));  //���ô�С
					jss[i].setLeftComponent(panel0);
					
					JPanel pane=new JPanel(new GridLayout(1,2));
					JPanel infopane=new JPanel(new FlowLayout(0,4,20));
//					TextBorderUtlis border = new TextBorderUtlis(Color.black,2,true);
//					infopane.setBorder(border);
					JLabel info=new JLabel(itemArr[i].getName(),JLabel.LEFT);
					info.setFont(new Font("΢���ź�", Font.PLAIN, 15));
					infopane.add(info);
					JPanel buttonpane=new JPanel(new FlowLayout(4,30,60));
					delete[i]= new JButton("ɾ��");
					delete[i].addActionListener(this);
					buy[i]=new JButton("����");
					buy[i].addActionListener(this);
					buttonpane.add(delete[i]);
					buttonpane.add(buy[i]);
					pane.add(infopane);
					pane.add(buttonpane);
					jss[i].setRightComponent(pane);
				
				
				
//				
				
			}
			
			for (int i = 0; i < itemArr.length; i++) {
				jphy2.add(jss[i]);
			}
			
			jsp1=new JScrollPane(jphy2);
		}else {
			jphy2 = new JPanel(new FlowLayout(1));   //�м���ʾ��Ʒ��Ϣ
			jphy2.setBackground(Color.white);
			jphy2_jb1= new JButton("ȥ���");
			jphy2_jb1.addActionListener(this);
			jphy2_jb1.setBackground(Color.LIGHT_GRAY);
			jphy2_jb1.setForeground(Color.black);
			jl2= new JLabel("���Ĺ��ﳵ���ǿյ�");
			JLabel l1 = new JLabel();
			ImageIcon icon = new ImageIcon("src\\images\\ͼƬ7.gif"); //�ڴ�ֱ�Ӵ�������,ע��Ŀ¼֮��ķָ�����˫б��
			l1.setIcon(icon);
			JLabel l2 = new JLabel("   ");
	//		l.setBounds(100, 100, icon.getIconWidth(),icon.getIconHeight());
	//		l.setHorizontalTextPosition(JLabel.CENTER);    //����������jlabel��λ��
			jphy2.add(l1);
			jphy2.add(l2);
			jphy2.add(jl2);
			jphy2.add(jphy2_jb1);
		}
		
		
		
		
		
		
		
		
		
		
		jphy3= new JPanel(new GridLayout(1,4));     //�ײ���ť���
		jphy3.setBackground(Color.LIGHT_GRAY);
		jphy4 = new JPanel(new GridLayout(1,2));
		jphy4_1 = new JPanel(new GridLayout(1,1));
		jphy4_1.setBackground(Color.white);
		jphy4_2 = new JPanel(new FlowLayout(4,4,4));   //����һ���µ������Ⱦֹ�����������ָ�����ڶ����뷽ʽ�Լ�ָ����ˮƽ�ʹ�ֱ��϶��
		jphy4_2.setBackground(Color.white);
		
		jl1=new JLabel("ɽ����վ", new ImageIcon("src\\images\\ͼƬ6.jpg"),JLabel.CENTER);
//		jl1.setPreferredSize(new Dimension(0, 50));
		jl1.setForeground(Color.black);
		jl1.setFont(new Font("΢���ź�", Font.BOLD, 20));
		
		
		
//		jphy_jb2 = new JButton("��Ʒ", new ImageIcon("src\\images\\ͼƬ1.png"));
////		jphy_jb2.setContentAreaFilled(false);
//		jphy_jb2.setBorderPainted(false);
//		jphy_jb2.setBackground(Color.LIGHT_GRAY);
//		jphy_jb2.setForeground(Color.black);
//		jphy_jb3 = new JButton("����", new ImageIcon("src\\images\\ͼƬ2.png"));
////		jphy_jb3.setContentAreaFilled(false);
//		jphy_jb3.setBackground(Color.LIGHT_GRAY);
//		jphy_jb3.setForeground(Color.black);
//		jphy_jb3.setBorderPainted(false);
//		jphy_jb4 = new JButton("���ﳵ", new ImageIcon("src\\images\\ͼƬ3.png"));
////		jphy_jb4.setContentAreaFilled(false);
//		jphy_jb4.setBackground(Color.darkGray);
//		jphy_jb4.setForeground(Color.white);
//		jphy_jb4.setBorderPainted(false);
//		jphy_jb5 = new JButton("�ҵ�", new ImageIcon("src\\images\\ͼƬ4.png"));
////		jphy_jb5.setContentAreaFilled(false);
//		jphy_jb5.setBorderPainted(false);
//		jphy_jb5.setBackground(Color.LIGHT_GRAY);
//		jphy_jb5.setForeground(Color.black);
		
		jphy_jb2 = new JButton("��Ʒ", new ImageIcon("src\\images\\ͼƬ1.png"));
//		jphy_jb2.setContentAreaFilled(false);
//		jphy_jb2.setBorderPainted(false);
//		jphy_jb2.setBorder(border);
		jphy_jb2.setBackground(new Color(232,232,232));
//		jphy_jb2.setForeground(Color.white);
		jphy_jb3 = new JButton("�������", new ImageIcon("src\\images\\ͼƬ2.png"));
//		jphy_jb3.setContentAreaFilled(false);
		jphy_jb3.setBackground(new Color(232,232,232));
//		jphy_jb3.setForeground(Color.black);
//		jphy_jb3.setBorderPainted(false);
		jphy_jb4 = new JButton("���ﳵ", new ImageIcon("src\\images\\ͼƬ3.png"));
//		jphy_jb4.setContentAreaFilled(false);
		jphy_jb4.setBackground(new Color(232,232,232));
		jphy_jb4.setForeground(Color.black);
		jphy_jb4.setBorder(border);
//		jphy_jb4.setBorderPainted(false);
		jphy_jb5 = new JButton("�ҵ�", new ImageIcon("src\\images\\ͼƬ4.png"));
//		jphy_jb5.setContentAreaFilled(false);
//		jphy_jb5.setBorderPainted(false);
		jphy_jb5.setBackground(new Color(232,232,232));
//		jphy_jb5.setForeground(Color.black);
		
		
		jphy_jb2.addActionListener(this);
		jphy_jb3.addActionListener(this);
		jphy_jb5.addActionListener(this);
		
		jphy3.add(jphy_jb2);
		jphy3.add(jphy_jb3);
		jphy3.add(jphy_jb4);
		jphy3.add(jphy_jb5);
		jphy3.setPreferredSize(new Dimension(0, 50));
		
		jphy4_1.add(jl1);
		jphy4.add(jphy4_1);
		jphy4.add(jphy4_2);
		
		jphy1.add(jphy4,"North");
		jphy1.add(jphy3,"South");
		if (itemArr[0]!=null) {
			jphy1.add(jsp1,"Center");
		}else {
			jphy1.add(jphy2,"Center");
		}
		
		
		this.setTitle("���ﳵ");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.add(jphy1,"Center");
		this.setSize(800,800);
		this.setLocationRelativeTo(null); // �Ѵ���λ�����õ���Ļ����
		this.setUndecorated(false); 
		this.setVisible(true);
		System.out.println(u0.getName());
	}
	
	public static void main(String[] args) throws IOException{
//		User u= new User();
//		new ShoppingTrolley(u, new Socket(), new getMessageThread(null));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==jphy2_jb1) {     //ȥ���
			new ShopList(u0,send,receive);
			this.dispose();
		}
		
		if (e.getSource()==jphy_jb2) {     //��Ʒ�б�ť
			new ShopList(u0,send,receive);
			this.dispose();
		}
		
		if (e.getSource()==jphy_jb5) {    //�ҵİ�ť
			
			new Mine(u0,send,receive);
			this.dispose();
		}
		
		if (e.getSource()==jphy_jb3) {    //������ᰴť
			new AuctionMeeting(u0, send, receive);
			this.dispose();
		}
		
//		if (e.getSource()==delete1) {  //ɾ����ť
//			
//		}
//		
//		if (e.getSource()==buy1) {    //����ť
//			Connect cc=new Connect();
//			Message m= new Message();
//			m.setContext(itemArr[0].getName());
//			m.setGetter(u0.getUserId());
//			m.setSender(itemArr[0].getSeller());
//			Date date=new Date();
//			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
//			m.setSendTime(formatter.format(date));
//			if (cc.buyItem(m)) {
//				JOptionPane.showMessageDialog(this, "����ɹ�");
//			}else {
//				JOptionPane.showMessageDialog(this, "����ʧ�ܣ�������");
//			}
//			
//		}
		for (int i = 0; i < itemArr.length; i++) {   //ɾ����ť
			if (e.getSource()==delete[i]) {
				Item [] temp=new Item [itemArr.length-1];
				for (int j = 0; j < itemArr.length; j++) {
					if (i==j) {
						continue;
					}else {
						temp[j]=itemArr[j];
					}
				}
				itemArr=temp;
				this.validate();   //ʹ�����ٴβ���
			}
		}
		
		for (int i = 0; i < itemArr.length; i++) {   //����ť
			if (e.getSource()==buy[i]) {
				
				Connect cc=new Connect();
				Message m= new Message();
				m.setContext(itemArr[i].getName());
				m.setGetter(u0.getUserId());
				m.setSender(itemArr[i].getSeller());
				Date date=new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
				m.setSendTime(formatter.format(date));
				if (cc.buyItem(m)) {
					JOptionPane.showMessageDialog(this, "����ɹ�");
				}else {
					JOptionPane.showMessageDialog(this, "����ʧ�ܣ�������");
				}
				Item [] temp=new Item [itemArr.length-1];
				for (int j = 0; j < itemArr.length; j++) {
					if (i==j) {
						continue;
					}else {
						temp[j]=itemArr[j];
					}
				}
				itemArr=temp;
				this.validate();   //ʹ�����ٴβ���
				
			}
		}
		
		
	}
}
