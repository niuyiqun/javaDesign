package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

import javax.swing.*;

import com.sun.jdi.connect.spi.ClosedConnectionException;

import Client.Connect;
import Client.Test.chat.getMessageThread;
import base.DIYClass.Message;
import base.DIYClass.User;


public class Mine extends JFrame implements ActionListener{
	JPanel jphy1,jphy2,jphy3,jphy4,jphy4_1,jphy4_2,jphy2_1,jphy2_2,jphy2_3 ;
	JButton jphy_jb1,jphy_jb2,jphy_jb3,jphy_jb4,jphy_jb5,jphy4_jb1,jphy4_jb2,jphy4_jb3,jphy4_jb4;
	JScrollPane jsp1;
	String onewId;
	JLabel jl1,jl2,jl3;
	JLabel blank1,blank2,userId,sex,name,major,grade;
	JTextField jtf;
	User u0;
	Socket send;
	getMessageThread receive;
	JTextField userIdfi,sexfi,namefi,majorfi,gradefi;
	JButton jbb=new JButton("ȷ���޸�");
	TextBorderUtlis border = new TextBorderUtlis(Color.black,2,true);
	public Mine(User u,Socket s,getMessageThread r) {
		
//		try { // ʹ��Windows�Ľ�����
//			   UIManager
//			     .setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
//			  } catch (Exception e) {
//			   e.printStackTrace();
//			  }
		send=s;
		receive=r;
		u0=u;
		
		Connect cc=new Connect();
		Message userInfo;
		u0.setAction(9);
		userInfo=cc.getUserInfo(u0);
		u0.setName(userInfo.getGetter());
		u0.setSex(userInfo.getContext());
		u0.setGrade(userInfo.getFileName());
		u0.setMajor(userInfo.getSender());
		jphy1 = new JPanel(new BorderLayout());   //�ܵ�����
		jphy1.setBackground(Color.darkGray);
		
		jphy2= new JPanel(new GridLayout(1,2));   //�м���ʾ��Ϣ������
		jphy3= new JPanel(new GridLayout(1,4));     //�ײ���ť���
		jphy2_1= new JPanel(new FlowLayout(1,4,20));   //��     
//		jphy2_1.setBackground(Color.darkGray);
		jphy2_2= new JPanel(new GridLayout(8,1));   //�ұ�
//		jphy2_3.setBackground(Color.darkGray);
//		jphy2_2.setPreferredSize(new Dimension());
		
		
		blank2 =new JLabel("     ");
		jl2 = new JLabel("�������ģ�");
		jl2.setForeground(Color.black);
		jl2.setFont(new Font("΢���ź�", Font.BOLD,30));
		blank1 =new JLabel("   ");
		userId =new JLabel("�˺ţ�"+u0.getUserId());
//		userId.setPreferredSize(new Dimension(400, 50));
		userId.setFont(new Font("����", Font.BOLD, 30));
		sex =new JLabel("�Ա�"+u0.getSex());
//		sex.setPreferredSize(new Dimension(400, 50));
		sex.setFont(new Font("����", Font.BOLD, 30));
		name =new JLabel("���֣�"+u0.getName());
//		name.setPreferredSize(new Dimension(400, 50));
		name.setFont(new Font("����", Font.BOLD, 30));
		major =new JLabel("רҵ��"+u0.getMajor());
//		major.setPreferredSize(new Dimension(400, 50));
		major.setFont(new Font("����", Font.BOLD, 30));
		grade =new JLabel("�꼶��"+u0.getGrade());
//		grade.setPreferredSize(new Dimension(400, 50));
		grade.setFont(new Font("����", Font.BOLD, 30));
		
		userIdfi = new JTextField();
		userIdfi.setPreferredSize(new Dimension(200,30));
		sexfi = new JTextField();
		sexfi.setPreferredSize(new Dimension(200,30));
		namefi = new JTextField();
		namefi.setPreferredSize(new Dimension(200,30));
		majorfi = new JTextField();
		majorfi.setPreferredSize(new Dimension(200,30));
		gradefi = new JTextField();
		gradefi.setPreferredSize(new Dimension(200,30));
		
		
		
		jphy3.setBackground(Color.LIGHT_GRAY);
		jphy4 = new JPanel(new GridLayout(1,2));
		jphy4_1 = new JPanel(new GridLayout(1,1));
		jphy4_1.setBackground(Color.white);
		jphy4_2 = new JPanel(new FlowLayout(1,4,10));   //����һ���µ������Ⱦֹ�����������ָ�����ڶ����뷽ʽ�Լ�ָ����ˮƽ�ʹ�ֱ��϶��
		/*  FlowLayout�ĵ�һ������
		 	public static final int CENTER 1
			public static final int LEADING 3
			public static final int LEFT 0
			public static final int RIGHT 2
			public static final int TRAILING 4
		 */
		jphy4_2.setBackground(Color.white);
		jl1=new JLabel("ɽ����վ", new ImageIcon("src\\images\\ͼƬ6.jpg"),JLabel.CENTER);
//		jl1.setPreferredSize(new Dimension(0, 50));
		jl1.setForeground(Color.black);
		jl1.setFont(new Font("΢���ź�", Font.BOLD, 20));
		jphy4_jb1 = new JButton("��ҵ����");
		jphy4_jb1.setBackground(Color.LIGHT_GRAY);
		jphy4_jb1.addActionListener(this);
		jphy4_jb2 = new JButton("�༭����");
		jphy4_jb2.setBackground(Color.LIGHT_GRAY);
		jphy4_jb3 = new JButton("������Ʒ");
		jphy4_jb3.setBackground(Color.LIGHT_GRAY);
		jphy4_jb4= new JButton("�ҵ���Ʒ");
		jphy4_jb4.setBackground(Color.LIGHT_GRAY);
		
		
		
		
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
//		jphy_jb4.setBackground(Color.LIGHT_GRAY);
//		jphy_jb4.setForeground(Color.black);
//		jphy_jb4.setBorderPainted(false);
//		jphy_jb5 = new JButton("�ҵ�", new ImageIcon("src\\images\\ͼƬ4.png"));
////		jphy_jb5.setContentAreaFilled(false);
//		jphy_jb5.setBorderPainted(false);
//		jphy_jb5.setBackground(Color.darkGray);
//		jphy_jb5.setForeground(Color.white);
		
		jphy_jb2 = new JButton("��Ʒ", new ImageIcon("src\\images\\ͼƬ1.png"));
//		jphy_jb2.setContentAreaFilled(false);
//		jphy_jb2.setBorderPainted(false);
		
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
		jphy_jb5.setBorder(border);
		
		jbb=new JButton("ȷ���޸�");
		jbb.setBackground(new Color(232,232,232));
		
		jphy_jb4.addActionListener(this);   //���ﳵ
		jphy_jb2.addActionListener(this);   //��Ʒ�б�
		jphy4_jb3.addActionListener(this);   //������Ʒ
		jphy4_jb2.addActionListener(this);
		jbb.addActionListener(this);
		jphy4_jb4.addActionListener(this);
		
		jphy2_1.add(jl2);
		jphy2_1.add(blank2);
		jphy2_2.add(blank1);
		jphy2_2.add(userId);
		jphy2_2.add(sex);
		jphy2_2.add(name);
		jphy2_2.add(major);
		jphy2_2.add(grade);
		
//		userId =new JLabel("ѧ��"+u0.getUserId());
//		sex =new JLabel("�Ա�"+u0.getSex());
//		name =new JLabel("����"+u0.getName());
//		major =new JLabel("רҵ"+u0.getMajor());
//		grade =new JLabel("�꼶"+u0.getGrade());
		
		jphy2.add(jphy2_1);
		jphy2.add(jphy2_2);
		
		jphy3.add(jphy_jb2);
		jphy3.add(jphy_jb3);
		jphy3.add(jphy_jb4);
		jphy3.add(jphy_jb5);
		jphy3.setPreferredSize(new Dimension(0, 50));
		
		jphy4_1.add(jl1);
		
		jphy4_2.add(jphy4_jb1);
		jphy4_2.add(jphy4_jb2);
		jphy4_2.add(jphy4_jb3);
		jphy4_2.add(jphy4_jb4);
		jphy4.add(jphy4_1);
		jphy4.add(jphy4_2);
		
		jphy1.add(jphy2,"Center");
		jphy1.add(jphy4,"North");
		jphy1.add(jphy3,"South");
		
		this.setTitle("ok");
		this.setDefaultCloseOperation(3);
		this.add(jphy1,"Center");
		this.setSize(800,800);
		this.setLocationRelativeTo(null); // �Ѵ���λ�����õ���Ļ����
		this.setUndecorated(false); 
		this.setVisible(true);
		
	}
	public static void main(String[] args) throws IOException {
		User u=new User();
		u.setGrade("��һ");
		u.setUserId("201900301075");
		u.setMajor("�������");
		u.setName("ţ��Ⱥ");
		u.setSex("��");
		new Mine(u,new Socket(),new getMessageThread(null, null));
	}
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource()==jphy_jb4) {   //���ﳵ��ť
			
			new ShoppingTrolley(u0,send,receive);
			this.dispose();
//			this.setVisible(true);
		}
		
		if (e.getSource()==jphy_jb2) {     //��Ʒ�б�ť
			new ShopList(u0,send,receive);
			this.dispose();

		}
		
		if (e.getSource()==jphy_jb3) {    //������ᰴť
			new AuctionMeeting(u0, send, receive);
			this.dispose();
		}
		
		if (e.getSource()==jphy4_jb3) {     //������Ʒ
			new ReleaseCommodity(u0);
		}
		
		if (e.getSource()==jphy4_jb1) {   //��ҵ����ť
			new BusinessReport(u0);
		}
		
		if (e.getSource()==jphy4_jb2) {    //�༭����
			JLabel tuserId =new JLabel("�˺ţ�"+u0.getUserId());
//			tuserId.setPreferredSize(new Dimension(400, 50));
			tuserId.setFont(new Font("����", Font.BOLD, 30));
			JLabel tsex =new JLabel("�Ա�");
//			tsex.setPreferredSize(new Dimension(400, 50));
			tsex.setFont(new Font("����", Font.BOLD, 30));
			JLabel tname =new JLabel("���֣�");
//			tname.setPreferredSize(new Dimension(400, 50));
			tname.setFont(new Font("����", Font.BOLD, 30));
			JLabel tmajor =new JLabel("רҵ��");
//			tmajor.setPreferredSize(new Dimension(400, 50));
			tmajor.setFont(new Font("����", Font.BOLD, 30));
			JLabel tgrade =new JLabel("�꼶��");
//			tgrade.setPreferredSize(new Dimension(400, 50));
			tgrade.setFont(new Font("����", Font.BOLD, 30));
			
			jbb.setPreferredSize(new Dimension(150,40));
			JPanel j1=new JPanel(new FlowLayout(0,4,4));
			JPanel j2=new JPanel(new FlowLayout(0,4,4));
			JPanel j3=new JPanel(new FlowLayout(0,4,4));
			JPanel j4=new JPanel(new FlowLayout(0,4,4));
			JPanel j5=new JPanel(new FlowLayout(0,4,4));
			JPanel j6=new JPanel(new FlowLayout(1,4,4));
			j1.add(tuserId);
//			j1.add(userIdfi);
			j2.add(tsex);
			j2.add(sexfi);
			j3.add(tname);
			j3.add(namefi);
			j4.add(tmajor);
			j4.add(majorfi);
			j5.add(tgrade);
			j5.add(gradefi);
			j6.add(jbb);
			
			jphy2_2.removeAll();
			jphy2_2.repaint();
			jphy2_2.add(blank1);
			jphy2_2.add(j1);
			jphy2_2.add(j2);
			jphy2_2.add(j3);
			jphy2_2.add(j4);
			jphy2_2.add(j5);
			jphy2_2.add(j6);
			jphy2_2.revalidate();
		}
		
		if (e.getSource()==jbb) {   //ȷ���޸İ�ť
//			System.out.println("s");
//			JOptionPane.showMessageDialog(this, "�޸ĳɹ�");
			User chUser=new User();
			chUser.setAction(5);
			chUser.setUserId(u0.getUserId());
			chUser.setSex(sexfi.getText().trim());
			chUser.setName(namefi.getText().trim());
			chUser.setMajor(majorfi.getText().trim());
			chUser.setGrade(gradefi.getText().trim());
			Connect con=new Connect();
			if (con.editUserInfo(chUser)) {
				JOptionPane.showMessageDialog(this, "�޸ĳɹ�");
				sex =new JLabel("�Ա�"+chUser.getSex());
//				sex.setPreferredSize(new Dimension(400, 50));
				sex.setFont(new Font("����", Font.BOLD, 30));
				name =new JLabel("���֣�"+chUser.getName());
//				name.setPreferredSize(new Dimension(400, 50));
				name.setFont(new Font("����", Font.BOLD, 30));
				major =new JLabel("רҵ��"+chUser.getMajor());
//				major.setPreferredSize(new Dimension(400, 50));
				major.setFont(new Font("����", Font.BOLD, 30));
				grade =new JLabel("�꼶��"+chUser.getGrade());
//				grade.setPreferredSize(new Dimension(400, 50));
				grade.setFont(new Font("����", Font.BOLD, 30));
				u0=chUser;
				jphy2_2.removeAll();
				jphy2_2.repaint();
				jphy2_2.add(blank1);
				jphy2_2.add(userId);
				jphy2_2.add(sex);
				jphy2_2.add(name);
				jphy2_2.add(major);
				jphy2_2.add(grade);
				jphy2_2.revalidate();
			}else {
				JOptionPane.showMessageDialog(this, "�޸�ʧ��");
			}
		}
		if (e.getSource()==jphy4_jb4) {
			new MyCommodity(u0);
		}
	}
}
