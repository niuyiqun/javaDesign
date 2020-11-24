package GUI;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import javax.swing.*;


import Client.Connect;
import Client.Test.chat.getMessageThread;
import base.DIYClass.Item;
import base.DIYClass.Message;
import base.DIYClass.User;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.Socket;

import javax.swing.*;

public class ShopList extends JFrame implements MouseListener,ActionListener{
	
	JPanel jphy1,jphy2,jphy3,jphy4,jphy4_1,jphy4_2;
	JButton jphy_jb1,jphy_jb2,jphy_jb3,jphy_jb4,jphy_jb5,jphy4_jb1,flashback;
	JScrollPane jsp1;
	String onewId;
	JLabel jl1;
	JTextField jtf;
	User u0;
	Connect con;
	Item[] itemArr;
	JLabel []jbls1 = new JLabel[50];
//	JLabel []jbls2 = new JLabel[50];
	JPanel []jpls = new JPanel[50];    //������ŵ�����Ʒ��Ϣ
	JPanel []panel = new JPanel[10];   //����ʽ�����½�
	Socket send;
	getMessageThread receive;
	int count;
	public ShopList(User u,Socket s,getMessageThread r) {
		send=s;
		receive=r;
//		try { // ʹ��Windows�Ľ�����
//			   UIManager
//			     .setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
//			  } catch (Exception e) {
//			   e.printStackTrace();
//			  }
		
		con = new Connect();
		Message tempm=new Message();
		tempm.setFileCon(0);
		itemArr=con.getItemList(tempm);
//		this.onewId = u.getName();
		u0=u;
		jphy1 = new JPanel(new BorderLayout());   //�ܵ�����
		jphy1.setBackground(Color.darkGray);
		jphy2 = new JPanel(new GridLayout(10,1,4,0));   //�м���ʾ��Ʒ��Ϣ
//		jphy2 = new JPanel(new FlowLayout(0,4,4)); 
		jphy2.setBackground(Color.LIGHT_GRAY);
		jphy3= new JPanel(new GridLayout(1,4));     //�ײ���ť���
		jphy3.setBackground(Color.LIGHT_GRAY);
		jphy4 = new JPanel(new GridLayout(1,2));
		jphy4_1 = new JPanel(new GridLayout(1,1));
		jphy4_1.setBackground(Color.white);
		jphy4_2 = new JPanel(new FlowLayout(4,4,4));   //����һ���µ������Ⱦֹ�����������ָ�����ڶ����뷽ʽ�Լ�ָ����ˮƽ�ʹ�ֱ��϶��
		jphy4_2.setBackground(Color.white);
		
		jtf = new JTextField();
		TextBorderUtlis border = new TextBorderUtlis(Color.black,2,true);
		jtf.setBorder(border);
		jtf.setPreferredSize(new Dimension(160,20));
		jphy4_jb1 = new JButton("������Ʒ", new ImageIcon("src\\images\\ͼƬ5.png"));
//		jphy4_jb1.setBorderPainted(false);
//		jphy4_jb1.setBackground(Color.lightGray);
//		jphy4_jb1.setContentAreaFilled(false);
//		jphy4_jb1.setForeground(Color.black);
		jphy4_jb1.setBackground(new Color(232,232,232));
//		jl1=new JLabel("ɽ����վ",JLabel.CENTER);
		jl1=new JLabel("ɽ����վ", new ImageIcon("src\\images\\ͼƬ6.jpg"),JLabel.CENTER);
//		jl1.setPreferredSize(new Dimension(0, 50));
		jl1.setForeground(Color.black);
		jl1.setFont(new Font("΢���ź�", Font.BOLD, 20));
//		jphy_jb1 = new JButton("�ҵĺ���");
//		jphy_jb1.setContentAreaFilled(false);
//		jphy_jb1.setBorderPainted(false);
		jphy_jb2 = new JButton("��Ʒ", new ImageIcon("src\\images\\ͼƬ1.png"));
//		jphy_jb2.setContentAreaFilled(false);
//		jphy_jb2.setBorderPainted(false);
		jphy_jb2.setBorder(border);
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
//		jphy_jb4.setBorderPainted(false);
		jphy_jb5 = new JButton("�ҵ�", new ImageIcon("src\\images\\ͼƬ4.png"));
//		jphy_jb5.setContentAreaFilled(false);
//		jphy_jb5.setBorderPainted(false);
		jphy_jb5.setBackground(new Color(232,232,232));
//		jphy_jb5.setForeground(Color.black);
		
		jphy_jb3.addActionListener(this);
		jphy_jb4.addActionListener(this);
		jphy_jb5.addActionListener(this);
		
		
		for (int i = 0; i < panel.length; i++) {
			panel[i]=new JPanel(new FlowLayout(0,4,4));
		}
		count=0;
		for(int i = 0;i<itemArr.length;i++){
//			if (i<20) {
//				Image image=new ImageIcon("src\\images\\hardware1.jpg").getImage();  
//				JPanel panel = new BackgroundPanel(image);       //��һ���յ�Jpanel��װͼƬ
//				panel.setPreferredSize(new Dimension(50, 150));  //���ô�С
//				jbls1[i]= new JLabel("����һ������",JLabel.CENTER);
//				jbls1[i].setBackground(Color.darkGray);
////				jbls1[i].setForeground(Color.white);
//				jpls[i].add(jbls1[i],BorderLayout.SOUTH);
//				jpls[i].add(panel,BorderLayout.CENTER);
//				jphy2.add(jpls[i]);
//			}else 
				if (itemArr[i].getIfSold()==0) {
					jpls[i]=new JPanel(new BorderLayout(0, 10));     //���±߾�Ϊ0�����ұ߾�Ϊ1
					jpls[i].setPreferredSize(new Dimension(148, 200)); 
					jbls1[i]= new JLabel(itemArr[i].getName(),JLabel.CENTER);
					jbls1[i].setFont(new Font("΢���ź�", Font.BOLD, 15));
					Image image=new ImageIcon(itemArr[i].getImagePos()).getImage();  
					System.out.println(itemArr[i].getImagePos());
					JPanel panel0 = new BackgroundPanel(image);       //��һ���յ�Jpanel��װͼƬ
					panel0.setPreferredSize(new Dimension(50, 150));  //���ô�С
					jpls[i].add(jbls1[i],BorderLayout.SOUTH);
					jpls[i].add(panel0,BorderLayout.CENTER);
					jpls[i].setBackground(Color.lightGray);
					jbls1[i].addMouseListener(this);
//					jbls2[i].addMouseListener(this);  //////
//					jbls1[i].setBackground(Color.black);
//					jbls1[i].setFont(new Font("����", Font.BOLD,15));
					panel[(int)(count/5)].add(jpls[i]);
//					panel[(int)(count/5)].setBackground(Color.white);
					count++;
				}
				
		}
		
		for (int i = 0; i < panel.length; i++) {
			jphy2.add(panel[i]);
		}
//		for (int i = 0; i < itemArr.length; i++) {
//			jpls[i]=new JPanel(new BorderLayout(0, 10));     //���±߾�Ϊ0�����ұ߾�Ϊ10
//			Image image=new ImageIcon("src\\images\\hardware1.jpg").getImage();  
//			JPanel panel = new BackgroundPanel(image);       //��һ���յ�Jpanel��װͼƬ
//			panel.setPreferredSize(new Dimension(50, 150));  //���ô�С
//			jbls1[i]= new JLabel(itemArr[i].getName(),JLabel.CENTER);
//			jbls1[i].setBackground(Color.darkGray);
////		jbls1[i].setForeground(Color.white);
//			jpls[i].add(jbls1[i],BorderLayout.SOUTH);
//			jpls[i].add(panel,BorderLayout.CENTER);
//			jphy2.add(jpls[i]);
//		}
		
		
		
		jphy3.add(jphy_jb2);
		jphy3.add(jphy_jb3);
		jphy3.add(jphy_jb4);
		jphy3.add(jphy_jb5);
		jphy3.setPreferredSize(new Dimension(0, 50));
		
		jphy4_jb1.addActionListener(this);
		jphy4_1.add(jl1);
		jphy4_2.add(jtf);
		jphy4_2.add(jphy4_jb1);
		flashback = new JButton(new ImageIcon("src\\images\\ͼƬ9.jpg"));
		flashback.setPreferredSize(new Dimension(30,30));
		flashback.setContentAreaFilled(false);
		flashback.setBorderPainted(false);
		flashback.addActionListener(this);
		jphy4_2.add(flashback);
		jphy4.add(jphy4_1);
		jphy4.add(jphy4_2);
		
		jsp1 = new JScrollPane(jphy2);
		jsp1.getVerticalScrollBar().setUI(new DemoScrollBarUI());
		jphy1.add(jphy4,"North");
		jphy1.add(jsp1,"Center");
		jphy1.add(jphy3,"South");
		this.setTitle(onewId);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.add(jphy1,"Center");
		this.setSize(800,800);
		this.setLocationRelativeTo(null); // �Ѵ���λ�����õ���Ļ����
		this.setUndecorated(false); 
		this.setVisible(true);
//		this.pack();
		System.out.println(u0.getName());
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==jphy_jb4) {   //���ﳵ��ť
			
			new ShoppingTrolley(u0,send,receive);
			this.dispose();
//			this.setVisible(true);
		}
		
		if (e.getSource()==jphy_jb5) {    //�ҵİ�ť
			
			new Mine(u0,send,receive);
			this.dispose();
		}
		if (e.getSource()==jphy4_jb1) {   //������ť
			
			
			Item[] selectedItemArr;
			Message mess=new Message();
			mess.setFileCon(1);
			mess.setContext(jtf.getText().trim());
//			System.out.println(jtf.getText().trim());
			selectedItemArr=con.getSelectedItemList(mess);
			
			
			JPanel tjphy2 = new JPanel(new GridLayout(10,1,4,0));   //�м���ʾ��Ʒ��Ϣ
//			jphy2 = new JPanel(new FlowLayout(0,4,4)); 
			tjphy2.setBackground(Color.LIGHT_GRAY);
			JLabel [] tjbls1 = new JLabel[50];    //����
//			JLabel [] jbls2 = new JLabel[50];	//û�õ�
			JPanel [] tjpls = new JPanel[50];    //������ŵ�����Ʒ��Ϣ
			JPanel [] tpanel = new JPanel[10];   //����ʽ�����½�
			
			for (int i = 0; i < tpanel.length; i++) {
				tpanel[i]=new JPanel(new FlowLayout(0,4,4));
			}
			int tcount=0;
			for(int i = 0;i<selectedItemArr.length;i++){
//				if (i<20) {
//					Image image=new ImageIcon("src\\images\\hardware1.jpg").getImage();  
//					JPanel panel = new BackgroundPanel(image);       //��һ���յ�Jpanel��װͼƬ
//					panel.setPreferredSize(new Dimension(50, 150));  //���ô�С
//					jbls1[i]= new JLabel("����һ������",JLabel.CENTER);
//					jbls1[i].setBackground(Color.darkGray);
////					jbls1[i].setForeground(Color.white);
//					jpls[i].add(jbls1[i],BorderLayout.SOUTH);
//					jpls[i].add(panel,BorderLayout.CENTER);
//					jphy2.add(jpls[i]);
//				}else 
					if (selectedItemArr[i].getIfSold()==0) {
						tjpls[i]=new JPanel(new BorderLayout(0, 10));     //���±߾�Ϊ0�����ұ߾�Ϊ1
						tjpls[i].setPreferredSize(new Dimension(148, 200)); 
						tjbls1[i]= new JLabel(selectedItemArr[i].getName(),JLabel.CENTER);
						tjbls1[i].setFont(new Font("΢���ź�", Font.BOLD, 15));
						Image image=new ImageIcon(selectedItemArr[i].getImagePos()).getImage();  
//						System.out.println(selectedItemArr[i].getImagePos());
						JPanel panel0 = new BackgroundPanel(image);       //��һ���յ�Jpanel��װͼƬ
						panel0.setPreferredSize(new Dimension(50, 150));  //���ô�С
						tjpls[i].add(tjbls1[i],BorderLayout.SOUTH);
						tjpls[i].add(panel0,BorderLayout.CENTER);
						tjpls[i].setBackground(Color.lightGray);
						tjbls1[i].addMouseListener(this);
//						jbls2[i].addMouseListener(this);  //////
//						tjbls1[i].setBackground(Color.black);
//						tjbls1[i].setFont(new Font("����", Font.BOLD,15));
						tpanel[(int)(tcount/5)].add(tjpls[i]);
//						tpanel[(int)(tcount/5)].setBackground(Color.white);
						tcount++;
					}
					
			}
			
			for (int i = 0; i < tpanel.length; i++) {
				tjphy2.add(tpanel[i]);
			}
//			for (int i = 0; i < itemArr.length; i++) {
//				jpls[i]=new JPanel(new BorderLayout(0, 10));     //���±߾�Ϊ0�����ұ߾�Ϊ10
//				Image image=new ImageIcon("src\\images\\hardware1.jpg").getImage();  
//				JPanel panel = new BackgroundPanel(image);       //��һ���յ�Jpanel��װͼƬ
//				panel.setPreferredSize(new Dimension(50, 150));  //���ô�С
//				jbls1[i]= new JLabel(itemArr[i].getName(),JLabel.CENTER);
//				jbls1[i].setBackground(Color.darkGray);
////			jbls1[i].setForeground(Color.white);
//				jpls[i].add(jbls1[i],BorderLayout.SOUTH);
//				jpls[i].add(panel,BorderLayout.CENTER);
//				jphy2.add(jpls[i]);
//			}
			
			JPanel tjphy4_2 = new JPanel(new FlowLayout(4,4,4));   //����һ���µ������Ⱦֹ�����������ָ�����ڶ����뷽ʽ�Լ�ָ����ˮƽ�ʹ�ֱ��϶��
			tjphy4_2.setBackground(Color.white);
			
			JScrollPane tjsp=new JScrollPane(tjphy2);
			jphy1.removeAll();
			jphy1.repaint();
//			jphy1.add(Insert.setInsert());
			jphy1.add(jphy4,"North");
			jphy1.add(tjsp,"Center");
			jphy1.add(jphy3,"South");
			jphy1.revalidate();
			
		}
		
		if (e.getSource()==flashback) {  //���ذ�ť
			jtf.setText("");
			JScrollPane temp=new JScrollPane(jphy2);
			temp.getVerticalScrollBar().setUI(new DemoScrollBarUI());
			jphy1.removeAll();
			jphy1.repaint();
//			jphy1.add(Insert.setInsert());
			jphy1.add(jphy4,"North");
			jphy1.add(temp,"Center");
			jphy1.add(jphy3,"South");
			jphy1.revalidate();
		}
		
		if (e.getSource()==jphy_jb3) {    //������ᰴť
			new AuctionMeeting(u0, send, receive);
			this.dispose();
		}
		
	}
	
	public static void main(String[] args) {
		User u=new User();
		u.setGrade("��һ");
		u.setUserId("201900301075");
		u.setMajor("�������");
		u.setName("ţ��Ⱥ");
		u.setSex("��");
//		new ShopList(u);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		for (int i = 0; i < itemArr.length; i++) {
			if (e.getSource()==jbls1[i]) {
				new CommodityInformation(u0, itemArr[i],send,receive,this,i);
			}
		}
		
//		for (int i = 0; i < itemArr.length; i++) {
//			if (e.getSource()==jbls2[i]) {
//				new CommodityInformation(u0, itemArr[i],send,receive);
//			}
//		}
	}
	
	public void updateItemList(int i) {
		Item [] tempArr=new Item [count-1];
		for (int j = 0; j < itemArr.length; j++) {
			if (i==j) {
				continue;
			}else if (i<j) {
				tempArr[j]=itemArr[j];
			}else {
				tempArr[j-1]=itemArr[j];
			}
		}
		
		itemArr=tempArr;
		this.revalidate();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		for (int i = 0; i < itemArr.length; i++) {
			if (e.getSource()==jbls1[i]) {
				jbls1[i].setForeground(Color.red);
			}
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		for (int i = 0; i < itemArr.length; i++) {
			if (e.getSource()==jbls1[i]) {
				jbls1[i].setForeground(Color.black);
			}
		}
		
	}

	
}

