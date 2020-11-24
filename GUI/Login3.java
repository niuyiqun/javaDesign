package GUI;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

import Client.Connect;
import Client.TestLogin;
import Client.Test.chat.getMessageThread;
import base.BaseVariable;
import base.DIYClass.ChatMsg;
import base.DIYClass.Message;
import base.DIYClass.User;

public class Login3 extends JFrame implements ActionListener{
	
	JPanel jp1,jp2,jp3,jp4,jp5;
	JLabel jl1,jl2,jl3,jl4;
	JButton jb1,jb2,jb3;
	JTextField jtf;
	JPasswordField jpf;
	TextBorderUtlis border;
	User u0;
	Socket send;
	getMessageThread receive;
	
	public Login3() {
		
		jp1=new JPanel();   //整体
		jp2=new JPanel();   //上方文字
		jp3=new JPanel();   //登录框
		jp1.setBackground(Color.white);
		jp1.setBounds(0,0,800,800);
		
		jp2.setBackground(Color.darkGray);
		jp2.setBounds(0, 0, 800, 100);
		
		jp3.setBackground(Color.darkGray);
		jp3.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		jp3.setBounds(200, 200, 400,400 );
//		
		
		

//		border = new TextBorderUtlis(new Color(160,35,50),2,true);
		
		jl1=new JLabel("学号");
		jl1.setFont(new Font("微软雅黑", Font.BOLD, 20));
		jl1.setForeground(Color.white);
		jl2=new JLabel("密码");
		jl2.setFont(new Font("微软雅黑", Font.BOLD, 20));
		jl2.setForeground(Color.white);
		jl3=new JLabel("山大闲置商品交易", new ImageIcon("src\\images\\图片6.jpg"),JLabel.CENTER);
		jl3.setFont(new Font("仿宋", Font.BOLD, 50));
		jl3.setForeground(Color.white);
		jl4=new JLabel("用户登录");
		jl4.setFont(new Font("微软雅黑", Font.BOLD, 20));
		jl4.setForeground(Color.white);
		
		jtf=new JTextField();
		jpf=new JPasswordField();
		
		jb1=new JButton("登录");
		jb2=new JButton("注册");
		jb3=new JButton("取消");
		
		
		jtf.setBorder(null);
		jtf.setBackground(Color.LIGHT_GRAY);
		jpf.setBorder(null);
		jpf.setBackground(Color.LIGHT_GRAY);
		jb1.setBorderPainted(false);
		jb1.setBackground(Color.BLACK);
		jb1.setForeground(Color.white);
		jb2.setBorderPainted(false);
//		jb2.setContentAreaFilled(false);
		jb2.setBackground(Color.BLACK);
		jb2.setForeground(Color.white);
		jb3.setBorderPainted(false);
		jb3.setBackground(Color.BLACK);
//		jb3.setContentAreaFilled(false);
		jb3.setForeground(Color.white);
		
		jl4.setBounds(350, 250, 100, 50);
		jl1.setBounds(250, 250+50+10, 40, 25);
		jl2.setBounds(250, 300+50+10, 40, 25);
		jtf.setBounds(300, 250+50+10, 200, 25);
		jpf.setBounds(300, 300+50+10, 200, 25);
		jb1.setBounds(320, 350+50+10, 150, 25);
		jb2.setBounds(320, 400+50+10, 70, 25);
		jb3.setBounds(400, 400+50+10, 70, 25);
		
		
		
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		
		
		jp2.add(jl3,JLabel.CENTER);
		
		
		
		
		
		this.setLayout(null);
		this.add(jl4);
		this.add(jl1);
		this.add(jl2);
		this.add(jtf);
		this.add(jpf);
		this.add(jb1);
		this.add(jb2);
		this.add(jb3);
		
		this.add(jp2);
		this.add(jp3);    //这里组件的添加顺序非常重要，不然可能不能正常显示，到目前为止我也不知道是为什么
		this.add(jp1);
		
		
		
		
		
		
		this.setSize(800,800);
		this.setLocationRelativeTo(null); // 把窗口位置设置到屏幕中心
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}
	
	public User getUser() {
		return u0;
	}
	
	public static void main(String[] args) {
		new Login3();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==jb1) {
//			User u0=new User();
//			u0.setName("牛毅群");
//			this.dispose();
//			ShopList s=new ShopList(u0);
			TestLogin t=new TestLogin();
			User u=new User();
			u.setUserId(jtf.getText().trim());
			u.setPassword(new String(jpf.getPassword()));
			u.setAction(1);
//			if (t.checkUser(u)) {   // login successfully
//				JOptionPane.showMessageDialog(this, "登录成功");
				Connect c0=new Connect();
				Message m=c0.SendLoginInfoToSever(u);
				if (m.getContext().equals("success")) {
					JOptionPane.showMessageDialog(this, "登录成功"+"  "+"您有"+m.getFileLen()+"离线消息");
					try {
						send= new Socket("127.0.0.1", BaseVariable.CHAT_PORT);
						ObjectOutputStream oos = new ObjectOutputStream(send.getOutputStream());
						oos.writeObject(u);
						receive = new getMessageThread(send,oos);
						Message mess=new Message();
						mess.setContext(u.getUserId());
						mess.setFileCon(1);
						mess.setFileLen(m.getFileLen());
						ChatMsg[] cm=c0.getOfflineInfo(mess);
						this.u0=u;
						new ShopList(u,send,receive);
						receive.addOfflineMsgWindow(cm);
						this.dispose();
					} catch (UnknownHostException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}else {
					JOptionPane.showMessageDialog(this,"用户名或密码输入错误");
				}
				
				
				
				
//			}else {       //fail
//				JOptionPane.showMessageDialog(this, "用户名或者密码错误");
//			}
		}
		
		if (e.getSource()==jb2) {
			new Register2();
			this.dispose();
		}
		
	}
}
