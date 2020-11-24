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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

import Client.Test.chat.AuctionThread;
import Client.Test.chat.getMessageThread;
import base.BaseVariable;
import base.DIYClass.Item;
import base.DIYClass.Message;
import base.DIYClass.User;

public class AuctionMeeting extends JFrame implements ActionListener{
	
	JPanel jphy1,jphy2,jphy3,jphy4,jphy4_1,jphy4_2;
	JButton jphy_jb1,jphy_jb2,jphy_jb3,jphy_jb4,jphy_jb5,jphy4_jb1;
	JScrollPane jsp1;
	String onewId;
	JLabel jl1;
	JTextField jtf;
	JSplitPane [] jss;
	TextBorderUtlis border = new TextBorderUtlis(Color.black,2,true);
	JLabel time;
	JButton join;
	JPanel buttonpane,pane,infopane;
	JLabel info;
	User u0;
	Socket send;
	getMessageThread receive;
	Item i0;
	public AuctionMeeting(User u,Socket s,getMessageThread r) {
		
		u0=u;
		send=s;
		receive=r;
//		try { // ʹ��Windows�Ľ�����
//			   UIManager
//			     .setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
//			  } catch (Exception e) {
//			   e.printStackTrace();
//			  }
		i0=new Item();
		i0.setName("Ipad");
		i0.setDescription("��������ֵģ��ܺ���");
		i0.setPrice(300);
		jphy1 = new JPanel(new BorderLayout());   //�ܵ�����
		jphy1.setBackground(Color.white);
		jphy2 = new JPanel(new GridLayout(10,1,10,10));   //�м���ʾ������Ʒ��Ϣ
		jphy2.setBackground(Color.white);
//		jphy2.setBackground(Color.LIGHT_GRAY);
		
		
		jss = new JSplitPane[9];
		for (int i = 0; i < jss.length; i++) {
			
			if (i==0) {
				jss[i]= new JSplitPane();
				jss[i].setBackground(Color.darkGray);
				jss[i].setOneTouchExpandable(true);//�÷ָ�����ʾ����ͷ
				jss[i].setContinuousLayout(true);//������ͷ���ػ�ͼ��
				jss[i].setDividerSize(0);//���÷ָ��ߵĿ��
				jss[i].setDividerLocation(150);
				jss[i].setPreferredSize(new Dimension(300,150));
				
				Image image=new ImageIcon("src\\images\\ipad.jpg").getImage();  
				JPanel panel0 = new BackgroundPanel(image);       //��һ���յ�Jpanel��װͼƬ
				panel0.setPreferredSize(new Dimension(100, 150));  //���ô�С
				jss[i].setLeftComponent(panel0);
				
				pane=new JPanel(new GridLayout(1,2));
				infopane=new JPanel(new FlowLayout(0,4,20));
//				TextBorderUtlis border = new TextBorderUtlis(Color.black,2,true);
//				infopane.setBorder(border);
				info=new JLabel(i0.getName(),JLabel.LEFT);
				info.setFont(new Font("΢���ź�", Font.PLAIN, 15));
				infopane.add(info);
				buttonpane=new JPanel(new FlowLayout(4,30,60));
				time= new JLabel("����ʱ��:2020/5/28/9:00");
				join=new JButton("���뾺��");
				join.setBackground(new Color(232,232,232));
				join.addActionListener(this);
				buttonpane.add(time);
				buttonpane.add(join);
				pane.add(infopane);
				pane.add(buttonpane);
				jss[i].setRightComponent(pane);
			}else {
				jss[i]= new JSplitPane();
				jss[i].setBackground(Color.darkGray);
				
				jss[i].setOneTouchExpandable(true);//�÷ָ�����ʾ����ͷ
				jss[i].setContinuousLayout(true);//������ͷ���ػ�ͼ��
				jss[i].setDividerSize(0);//���÷ָ��ߵĿ��
				jss[i].setDividerLocation(150);
				jss[i].setPreferredSize(new Dimension(300,150));
				
				Image image=new ImageIcon("src\\images\\hardware1.jpg").getImage();  
				JPanel panel0 = new BackgroundPanel(image);       //��һ���յ�Jpanel��װͼƬ
				panel0.setPreferredSize(new Dimension(100, 150));  //���ô�С
				jss[i].setLeftComponent(panel0);
				
				JPanel pane0=new JPanel(new GridLayout(1,2));
				JPanel infopane0=new JPanel(new FlowLayout(0,4,20));
//				TextBorderUtlis border = new TextBorderUtlis(Color.black,2,true);
//				infopane.setBorder(border);
				JLabel info0=new JLabel("����"+i,JLabel.LEFT);
				info0.setFont(new Font("΢���ź�", Font.PLAIN, 15));
				infopane0.add(info0);
				JPanel buttonpane0=new JPanel(new FlowLayout(4,30,60));
				JLabel time0= new JLabel("����ʱ��:");
				JButton join0=new JButton("���뾺��");
				join0.setBackground(new Color(232,232,232));
				
				buttonpane0.add(time0);
				buttonpane0.add(join0);
				pane0.add(infopane0);
				pane0.add(buttonpane0);
				jss[i].setRightComponent(pane0);
			}
			
			
//			
			
		}
		
		for (int i = 0; i < jss.length; i++) {
			jphy2.add(jss[i]);
		}
		
		jsp1=new JScrollPane(jphy2);
		jsp1.getVerticalScrollBar().setUI(new DemoScrollBarUI());
		
		
		
		
//		jphy4_jb1 = new JButton("����������Ʒ");
//		jphy4_jb1.setBackground(new Color(232,232,232));
//		jphy4_jb1.addActionListener(this);
//		jphy4_jb1.setPreferredSize(new Dimension(150,30));
		JLabel label=new JLabel("��ӭ�����������");
		label.setFont(new Font("΢���ź�", Font.BOLD, 15));
		
		
		
		
		
		
		
		
		
		jphy3= new JPanel(new GridLayout(1,4));     //�ײ���ť���
//		jphy3.setBackground(Color.LIGHT_GRAY);
		jphy4 = new JPanel(new GridLayout(1,2));
		jphy4_1 = new JPanel(new GridLayout(1,1));
		jphy4_1.setBackground(Color.white);
		jphy4_2 = new JPanel(new FlowLayout(4,4,4));   //����һ���µ������Ⱦֹ�����������ָ�����ڶ����뷽ʽ�Լ�ָ����ˮƽ�ʹ�ֱ��϶��
		jphy4_2 = new JPanel(new FlowLayout(4,50,20));   //����һ���µ������Ⱦֹ�����������ָ�����ڶ����뷽ʽ�Լ�ָ����ˮƽ�ʹ�ֱ��϶��
		jphy4_2.add(label);
		jphy4_2.setBackground(Color.white);
		
		jl1=new JLabel("ɽ����վ", new ImageIcon("src\\images\\ͼƬ6.jpg"),JLabel.CENTER);
//		jl1.setPreferredSize(new Dimension(0, 50));
		jl1.setForeground(Color.black);
		jl1.setFont(new Font("΢���ź�", Font.BOLD, 20));
		jphy_jb2 = new JButton("��Ʒ", new ImageIcon("src\\images\\ͼƬ1.png"));
//		jphy_jb2.setContentAreaFilled(false);
//		jphy_jb2.setBorderPainted(false);
		jphy_jb2.setBackground(new Color(232,232,232));
//		jphy_jb2.setForeground(Color.white);
		jphy_jb3 = new JButton("�������", new ImageIcon("src\\images\\ͼƬ2.png"));
//		jphy_jb3.setContentAreaFilled(false);
		jphy_jb3.setBorder(border);
		jphy_jb3.setBackground(new Color(232,232,232));
//		jphy_jb3.setForeground(Color.black);
//		jphy_jb3.setBorderPainted(false);
		jphy_jb4 = new JButton("���ﳵ", new ImageIcon("src\\images\\ͼƬ3.png"));
//		jphy_jb4.setContentAreaFilled(false);
		jphy_jb4.setBackground(new Color(232,232,232));
		jphy_jb4.setForeground(Color.black);
//		jphy_jb4.setBorderPainted(false);
		jphy_jb5 = new JButton("�ҵ�", new ImageIcon("src\\images\\ͼƬ4.png"));
//		jphy_jb5.setContentAreaFilled(false);
//		jphy_jb5.setBorderPainted(false);
		jphy_jb5.setBackground(new Color(232,232,232));
//		jphy_jb5.setForeground(Color.black);
		
		jphy_jb2.addActionListener(this);
		jphy_jb4.addActionListener(this);
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
		jphy1.add(jsp1,"Center");
		
		this.setTitle("ok");
		this.setDefaultCloseOperation(3);
		this.add(jphy1);
		this.setSize(800,800);
		this.setLocationRelativeTo(null); // �Ѵ���λ�����õ���Ļ����
		this.setUndecorated(false); 
		this.setVisible(true);
		
	}
	public static void main(String[] args) {
//		new AuctionMeeting();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource()==jphy_jb2) {   //��Ʒ��ť
			new ShopList(u0, send, receive);
			this.dispose();
		}
		
		if (e.getSource()==jphy_jb4) {   //���ﳵ��ť
			new ShoppingTrolley(u0,send,receive);
			this.dispose();
		}
		
		if (e.getSource()==jphy_jb5) {    //�ҵİ�ť
			
			new Mine(u0,send,receive);
			this.dispose();
		}
		

		if (e.getSource()==join) {    //�����μ�
			try {
				Socket sendPrice = new Socket("127.0.0.1", BaseVariable.AUCTION_PORT);
				AuctionThread receivePrice=new AuctionThread(sendPrice,u0);
//				receivePrice.addAuctionProcess(u0, i);
				receivePrice.addAuctionProcess(u0, i0);
			} catch (Exception ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		}
	}
}
