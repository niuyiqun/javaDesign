package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.*;

import Client.Test.chat.getMessageThread;
import base.BaseVariable;
import base.DIYClass.ChatMsg;
import base.DIYClass.Message;
import base.DIYClass.Time;
import base.DIYClass.User;

public class ChatWindow extends JFrame implements ActionListener{
	
	
	JSplitPane jsp1,jsp2,jsp3,jsp4,jsp5;
	JPanel jp1,jp2,jp3,jp4,jp5;
	JButton jb1;
	JTextPane jtp1,jtp2;
	JScrollPane friendlist,infolist;
	JLabel jl1,jl2;
	JPanel []jps;   //用来装左边的消息列表
	JLabel []jls;  //同样用来装friendlist头像以及名称
	JPanel []informationbox;
	JTextArea [] information;
	JLabel [] headimage;
	int i=0;
	Socket s;
	Socket send;
//	getMessageThread receive;
	User sender,getter;
	
	ObjectOutputStream oos;
	
	
	public User getSender() {
		return sender;
	}
	
	public User getGetter() {
		return getter;
	}


	
	public ChatWindow(User sendman,User getman,Socket s,ObjectOutputStream os)  {
		
//		try {
//			oos = new ObjectOutputStream(s.getOutputStream());
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		oos=os;
		send=s;
//		receive=r;
		sender=sendman;
		getter=getman;
		
//		try { // 使用Windows的界面风格
//			   UIManager
//			     .setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
//			  } catch (Exception e) {
//			   e.printStackTrace();
//			  }
		
		
		jsp1=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		jsp2=new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		jsp3=new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		jsp4=new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		jsp5=new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		
		jp1=new JPanel(new GridLayout(10,1));   //放在左边显示好友信息/消息列表
		
		jp2=new JPanel(new FlowLayout(0,8,10));   //显示当前对话人的名称
		jl2 = new JLabel(getter.getName());
		jl2.setFont((new Font("楷体_GB2312", Font.BOLD, 20)));
		jp2.add(jl2);
//		jtp1 = new JTextPane();      //原本是用来装文字的
//		jtp1.setEditable(false);
		jp3=new JPanel(new FlowLayout(4,4,4));   //放发送按钮
		
		jb1 = new JButton("发送");
		jb1.setBackground(new Color(103,155,248));
//		jb1.setContentAreaFilled(false);
		jb1.setBorderPainted(false);
		jb1.setForeground(Color.white);
		jb1.addActionListener(this);
		jp3.add(jb1);
		jp3.setBackground(new Color(212,255,236));
		
		jtp2 = new JTextPane();
		jtp2.setEditable(true);
		jtp2.setBackground(new Color(212,255,236));
		
		jp5=new JPanel(new GridLayout(99,1));   //真正显示双方消息的地方
		jp5.setBackground(Color.white);
		informationbox = new JPanel[99];   //消息盒子
		information = new JTextArea[99];   //装文字
		headimage = new JLabel[99];        //装头像
		
		for (; i < 3; i++) {
			informationbox[i] = new JPanel(new FlowLayout(4,4,0));
			informationbox[i].setBackground(Color.white);
			informationbox[i].setPreferredSize(new Dimension(500,75));
			information[i] = new JTextArea(" 彩蛋 ");
			information[i].setBackground(new Color(15,145,255));
			information[i].setForeground(Color.white);
			information[i].setEditable(false);
			headimage[i]=new JLabel(new ImageIcon("src\\images\\图片6.jpg"));
			informationbox[i].add(information[i]);
			informationbox[i].add(headimage[i]);
			jp5.add(informationbox[i]);
		}
		
		for (; i < 5; i++) {
			informationbox[i] = new JPanel(new FlowLayout(0,4,0));
			informationbox[i].setBackground(Color.white);
			informationbox[i].setPreferredSize(new Dimension(500,75));
			information[i] = new JTextArea(" 彩蛋 ");
			information[i].setBackground(new Color(220,220,220));
			information[i].setForeground(Color.black);
			information[i].setEditable(false);
			headimage[i]=new JLabel(new ImageIcon("src\\images\\图片6.jpg"));
			informationbox[i].add(headimage[i]);
			informationbox[i].add(information[i]);
			jp5.add(informationbox[i]);
		}
		
		
		
		jp4=new JPanel();   //消息列表四个字
		jsp5.setTopComponent(jtp2);     
		jsp5.setBottomComponent(jp3);
		jsp5.setContinuousLayout(true);
		jsp5.setOneTouchExpandable(true);//让分割线显示出箭头
		jsp5.setContinuousLayout(true);//操作箭头，重绘图形
		jsp5.setDividerSize(0);//设置分割线的宽度
		jsp5.setDividerLocation(130);
		jls	= new JLabel[10];
		jps = new JPanel[10];
		jls[0]=new JLabel(getter.getName(),new ImageIcon("src\\images\\图片6.jpg"), JLabel.LEFT);
		
		jps[0]=new JPanel(new FlowLayout(0,20,4));
		jps[0].add(jls[0]);
		jps[0].setBackground(new Color(210,211,222));
		jp1.add(jps[0]);
		
//		jls[1]=new JLabel("刘",new ImageIcon("src\\images\\图片1.png"), JLabel.LEFT);
//		
//		jps[1]=new JPanel(new FlowLayout(0,10,12));
//		jps[1].add(jls[1]);
//		jps[1].setBackground(Color.lightGray);
//		jp1.add(jps[1]);
		
		jp1.setBackground(Color.white);
		jp1.setBorder(null);
		jp2.setBackground(new Color(159,255,213));
		jp3.setBackground(Color.white);
		jp3.setBackground(new Color(212,255,236));
		jp4.setBackground(new Color(159,255,213));
		jl1 = new JLabel("消息列表");
		jl1.setFont((new Font("楷体_GB2312", Font.BOLD, 25)));
		jp4.add(jl1,JLabel.CENTER);
		
//		TextBorderUtlis border = new TextBorderUtlis(Color.black,1,true);
//		jp1.setBorder(border);
//		jp2.setBorder(border);
		friendlist = new JScrollPane(jp1);
		friendlist.setBorder(null);
		infolist = new JScrollPane(jp5);
		infolist.getVerticalScrollBar().setUI(new DemoScrollBarUI());
		infolist.setBorder(null);
		jsp4.setTopComponent(jp2);     
		jsp4.setBottomComponent(infolist);     
		jsp4.setContinuousLayout(true);
		jsp4.setOneTouchExpandable(true);//让分割线显示出箭头
		jsp4.setContinuousLayout(true);//操作箭头，重绘图形
		jsp4.setDividerSize(1);//设置分割线的宽度
		jsp4.setDividerLocation(50);
		
		jsp2.setTopComponent(jsp4);      //jsp2 右边大的方块
		jsp2.setBottomComponent(jsp5);
		jsp2.setContinuousLayout(true);
		jsp2.setOneTouchExpandable(true);//让分割线显示出箭头
		jsp2.setContinuousLayout(true);//操作箭头，重绘图形
		jsp2.setDividerSize(1);//设置分割线的宽度
		jsp2.setDividerLocation(500);
//		jsp2.setBorder(border);
		jsp3.setTopComponent(jp4);     
		jsp3.setBottomComponent(friendlist);
		jsp3.setContinuousLayout(true);
		jsp3.setOneTouchExpandable(true);//让分割线显示出箭头
		jsp3.setContinuousLayout(true);//操作箭头，重绘图形
		jsp3.setDividerSize(0);//设置分割线的宽度
		jsp3.setDividerLocation(50);
		
		
		jsp1.setLeftComponent(jsp3);
		jsp1.setRightComponent(jsp2);
		jsp1.setContinuousLayout(true);
//		jsp1.setDividerLocation(0.5);
		jsp1.setBackground(new Color(212,255,236));
		jsp1.setOneTouchExpandable(true);//让分割线显示出箭头
		jsp1.setContinuousLayout(true);//操作箭头，重绘图形
		jsp1.setDividerSize(0);//设置分割线的宽度
		jsp1.setDividerLocation(200);
		
		
		
		
		
		this.add(jsp1);
		this.setSize(800,700);
		this.setLocationRelativeTo(null); // 把窗口位置设置到屏幕中心
		this.setUndecorated(false); 
		this.setVisible(true);
	}
	
	
	public void receiveInfo(String s) {
		informationbox[i] = new JPanel(new FlowLayout(0,4,0));
		informationbox[i].setBackground(Color.white);
		informationbox[i].setPreferredSize(new Dimension(500,75));
		information[i] = new JTextArea(" "+s+" ");
		information[i].setBackground(new Color(220,220,220));
		information[i].setForeground(Color.black);
		information[i].setEditable(false);
		headimage[i]=new JLabel(new ImageIcon("src\\images\\图片6.jpg"));
		informationbox[i].add(headimage[i]);
		informationbox[i].add(information[i]);
		jp5.add(informationbox[i]);
		i++;
	}
	
	public void sendInfo(String s) {
		informationbox[i] = new JPanel(new FlowLayout(4,4,0));
		informationbox[i].setBackground(Color.white);
		informationbox[i].setPreferredSize(new Dimension(500,75));
		information[i] = new JTextArea(" "+s+" ");
		information[i].setBackground(new Color(15,145,255));
		information[i].setForeground(Color.white);
		information[i].setEditable(false);
		headimage[i]=new JLabel(new ImageIcon("src\\images\\图片6.jpg"));
		informationbox[i].add(information[i]);
		informationbox[i].add(headimage[i]);
		jp5.add(informationbox[i]);
		i++;
	}
	
	public static void main(String[] args) throws IOException {
//		new ChatWindow(new User(),new User(),new Socket(),new ObjectOutputStream(null));
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {    
		
		if (e.getSource()==jb1) {//发送信息按钮
			ChatMsg chatmsg=new ChatMsg();
			chatmsg.setSender(sender);
			chatmsg.setGetter(getter);
			Time time = new Time();
			chatmsg.setSendTime(time.getDetail());
			chatmsg.setContext(jtp2.getText());  //这里需要再查一下
			
            try {
				oos.writeObject(chatmsg);
				sendInfo(jtp2.getText());
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            jtp2.setText("");
		}
		
	}
}
