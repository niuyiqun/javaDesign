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
	JButton jbb=new JButton("确认修改");
	TextBorderUtlis border = new TextBorderUtlis(Color.black,2,true);
	public Mine(User u,Socket s,getMessageThread r) {
		
//		try { // 使用Windows的界面风格
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
		jphy1 = new JPanel(new BorderLayout());   //总的容器
		jphy1.setBackground(Color.darkGray);
		
		jphy2= new JPanel(new GridLayout(1,2));   //中间显示信息的容器
		jphy3= new JPanel(new GridLayout(1,4));     //底部按钮面板
		jphy2_1= new JPanel(new FlowLayout(1,4,20));   //左     
//		jphy2_1.setBackground(Color.darkGray);
		jphy2_2= new JPanel(new GridLayout(8,1));   //右边
//		jphy2_3.setBackground(Color.darkGray);
//		jphy2_2.setPreferredSize(new Dimension());
		
		
		blank2 =new JLabel("     ");
		jl2 = new JLabel("个人中心：");
		jl2.setForeground(Color.black);
		jl2.setFont(new Font("微软雅黑", Font.BOLD,30));
		blank1 =new JLabel("   ");
		userId =new JLabel("账号："+u0.getUserId());
//		userId.setPreferredSize(new Dimension(400, 50));
		userId.setFont(new Font("仿宋", Font.BOLD, 30));
		sex =new JLabel("性别："+u0.getSex());
//		sex.setPreferredSize(new Dimension(400, 50));
		sex.setFont(new Font("仿宋", Font.BOLD, 30));
		name =new JLabel("名字："+u0.getName());
//		name.setPreferredSize(new Dimension(400, 50));
		name.setFont(new Font("仿宋", Font.BOLD, 30));
		major =new JLabel("专业："+u0.getMajor());
//		major.setPreferredSize(new Dimension(400, 50));
		major.setFont(new Font("仿宋", Font.BOLD, 30));
		grade =new JLabel("年级："+u0.getGrade());
//		grade.setPreferredSize(new Dimension(400, 50));
		grade.setFont(new Font("仿宋", Font.BOLD, 30));
		
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
		jphy4_2 = new JPanel(new FlowLayout(1,4,10));   //创建一个新的流布度局管理器，具有指定的内对容齐方式以及指定的水平和垂直间隙。
		/*  FlowLayout的第一个参数
		 	public static final int CENTER 1
			public static final int LEADING 3
			public static final int LEFT 0
			public static final int RIGHT 2
			public static final int TRAILING 4
		 */
		jphy4_2.setBackground(Color.white);
		jl1=new JLabel("山大交易站", new ImageIcon("src\\images\\图片6.jpg"),JLabel.CENTER);
//		jl1.setPreferredSize(new Dimension(0, 50));
		jl1.setForeground(Color.black);
		jl1.setFont(new Font("微软雅黑", Font.BOLD, 20));
		jphy4_jb1 = new JButton("商业报表");
		jphy4_jb1.setBackground(Color.LIGHT_GRAY);
		jphy4_jb1.addActionListener(this);
		jphy4_jb2 = new JButton("编辑资料");
		jphy4_jb2.setBackground(Color.LIGHT_GRAY);
		jphy4_jb3 = new JButton("发布商品");
		jphy4_jb3.setBackground(Color.LIGHT_GRAY);
		jphy4_jb4= new JButton("我的商品");
		jphy4_jb4.setBackground(Color.LIGHT_GRAY);
		
		
		
		
//		jphy_jb2 = new JButton("商品", new ImageIcon("src\\images\\图片1.png"));
////		jphy_jb2.setContentAreaFilled(false);
//		jphy_jb2.setBorderPainted(false);
//		jphy_jb2.setBackground(Color.LIGHT_GRAY);
//		jphy_jb2.setForeground(Color.black);
//		jphy_jb3 = new JButton("社区", new ImageIcon("src\\images\\图片2.png"));
////		jphy_jb3.setContentAreaFilled(false);
//		jphy_jb3.setBackground(Color.LIGHT_GRAY);
//		jphy_jb3.setForeground(Color.black);
//		jphy_jb3.setBorderPainted(false);
//		jphy_jb4 = new JButton("购物车", new ImageIcon("src\\images\\图片3.png"));
////		jphy_jb4.setContentAreaFilled(false);
//		jphy_jb4.setBackground(Color.LIGHT_GRAY);
//		jphy_jb4.setForeground(Color.black);
//		jphy_jb4.setBorderPainted(false);
//		jphy_jb5 = new JButton("我的", new ImageIcon("src\\images\\图片4.png"));
////		jphy_jb5.setContentAreaFilled(false);
//		jphy_jb5.setBorderPainted(false);
//		jphy_jb5.setBackground(Color.darkGray);
//		jphy_jb5.setForeground(Color.white);
		
		jphy_jb2 = new JButton("商品", new ImageIcon("src\\images\\图片1.png"));
//		jphy_jb2.setContentAreaFilled(false);
//		jphy_jb2.setBorderPainted(false);
		
		jphy_jb2.setBackground(new Color(232,232,232));
//		jphy_jb2.setForeground(Color.white);
		jphy_jb3 = new JButton("拍卖大会", new ImageIcon("src\\images\\图片2.png"));
//		jphy_jb3.setContentAreaFilled(false);
		jphy_jb3.setBackground(new Color(232,232,232));
//		jphy_jb3.setForeground(Color.black);
//		jphy_jb3.setBorderPainted(false);
		jphy_jb4 = new JButton("购物车", new ImageIcon("src\\images\\图片3.png"));
//		jphy_jb4.setContentAreaFilled(false);
		jphy_jb4.setBackground(new Color(232,232,232));
		jphy_jb4.setForeground(Color.black);
//		jphy_jb4.setBorderPainted(false);
		jphy_jb5 = new JButton("我的", new ImageIcon("src\\images\\图片4.png"));
//		jphy_jb5.setContentAreaFilled(false);
//		jphy_jb5.setBorderPainted(false);
		jphy_jb5.setBackground(new Color(232,232,232));
//		jphy_jb5.setForeground(Color.black);
		jphy_jb5.setBorder(border);
		
		jbb=new JButton("确认修改");
		jbb.setBackground(new Color(232,232,232));
		
		jphy_jb4.addActionListener(this);   //购物车
		jphy_jb2.addActionListener(this);   //商品列表
		jphy4_jb3.addActionListener(this);   //发布商品
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
		
//		userId =new JLabel("学号"+u0.getUserId());
//		sex =new JLabel("性别"+u0.getSex());
//		name =new JLabel("名字"+u0.getName());
//		major =new JLabel("专业"+u0.getMajor());
//		grade =new JLabel("年级"+u0.getGrade());
		
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
		this.setLocationRelativeTo(null); // 把窗口位置设置到屏幕中心
		this.setUndecorated(false); 
		this.setVisible(true);
		
	}
	public static void main(String[] args) throws IOException {
		User u=new User();
		u.setGrade("大一");
		u.setUserId("201900301075");
		u.setMajor("软件工程");
		u.setName("牛毅群");
		u.setSex("男");
		new Mine(u,new Socket(),new getMessageThread(null, null));
	}
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource()==jphy_jb4) {   //购物车按钮
			
			new ShoppingTrolley(u0,send,receive);
			this.dispose();
//			this.setVisible(true);
		}
		
		if (e.getSource()==jphy_jb2) {     //商品列表按钮
			new ShopList(u0,send,receive);
			this.dispose();

		}
		
		if (e.getSource()==jphy_jb3) {    //拍卖大会按钮
			new AuctionMeeting(u0, send, receive);
			this.dispose();
		}
		
		if (e.getSource()==jphy4_jb3) {     //发布商品
			new ReleaseCommodity(u0);
		}
		
		if (e.getSource()==jphy4_jb1) {   //商业报表按钮
			new BusinessReport(u0);
		}
		
		if (e.getSource()==jphy4_jb2) {    //编辑资料
			JLabel tuserId =new JLabel("账号："+u0.getUserId());
//			tuserId.setPreferredSize(new Dimension(400, 50));
			tuserId.setFont(new Font("仿宋", Font.BOLD, 30));
			JLabel tsex =new JLabel("性别：");
//			tsex.setPreferredSize(new Dimension(400, 50));
			tsex.setFont(new Font("仿宋", Font.BOLD, 30));
			JLabel tname =new JLabel("名字：");
//			tname.setPreferredSize(new Dimension(400, 50));
			tname.setFont(new Font("仿宋", Font.BOLD, 30));
			JLabel tmajor =new JLabel("专业：");
//			tmajor.setPreferredSize(new Dimension(400, 50));
			tmajor.setFont(new Font("仿宋", Font.BOLD, 30));
			JLabel tgrade =new JLabel("年级：");
//			tgrade.setPreferredSize(new Dimension(400, 50));
			tgrade.setFont(new Font("仿宋", Font.BOLD, 30));
			
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
		
		if (e.getSource()==jbb) {   //确认修改按钮
//			System.out.println("s");
//			JOptionPane.showMessageDialog(this, "修改成功");
			User chUser=new User();
			chUser.setAction(5);
			chUser.setUserId(u0.getUserId());
			chUser.setSex(sexfi.getText().trim());
			chUser.setName(namefi.getText().trim());
			chUser.setMajor(majorfi.getText().trim());
			chUser.setGrade(gradefi.getText().trim());
			Connect con=new Connect();
			if (con.editUserInfo(chUser)) {
				JOptionPane.showMessageDialog(this, "修改成功");
				sex =new JLabel("性别："+chUser.getSex());
//				sex.setPreferredSize(new Dimension(400, 50));
				sex.setFont(new Font("仿宋", Font.BOLD, 30));
				name =new JLabel("名字："+chUser.getName());
//				name.setPreferredSize(new Dimension(400, 50));
				name.setFont(new Font("仿宋", Font.BOLD, 30));
				major =new JLabel("专业："+chUser.getMajor());
//				major.setPreferredSize(new Dimension(400, 50));
				major.setFont(new Font("仿宋", Font.BOLD, 30));
				grade =new JLabel("年级："+chUser.getGrade());
//				grade.setPreferredSize(new Dimension(400, 50));
				grade.setFont(new Font("仿宋", Font.BOLD, 30));
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
				JOptionPane.showMessageDialog(this, "修改失败");
			}
		}
		if (e.getSource()==jphy4_jb4) {
			new MyCommodity(u0);
		}
	}
}
