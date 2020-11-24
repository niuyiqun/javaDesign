package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;

public class FirstPage extends JFrame{
	
	JPanel jphy1,jphy2,jphy3,jphy4,jphy4_1,jphy4_2;
	JButton jphy_jb1,jphy_jb2,jphy_jb3,jphy_jb4,jphy_jb5,jphy4_jb1;
	JScrollPane jsp1;
	String onewId;
	JLabel jl1;
	JTextField jtf;
	
	public FirstPage() {
		jphy1 = new JPanel(new BorderLayout());   //总的容器
		jphy1.setBackground(Color.darkGray);
//		jphy2 = new JPanel(new GridLayout(10,5,4,4));   //中间显示商品信息
//		jphy2.setBackground(Color.LIGHT_GRAY);
		jphy3= new JPanel(new GridLayout(1,4));     //底部按钮面板
		jphy3.setBackground(Color.LIGHT_GRAY);
		jphy4 = new JPanel(new GridLayout(1,2));
		jphy4_1 = new JPanel(new GridLayout(1,1));
		jphy4_1.setBackground(Color.white);
		jphy4_2 = new JPanel(new FlowLayout(4,4,4));   //创建一个新的流布度局管理器，具有指定的内对容齐方式以及指定的水平和垂直间隙。
		jphy4_2.setBackground(Color.white);
		
		jl1=new JLabel("山大交易站", new ImageIcon("src\\images\\图片6.jpg"),JLabel.CENTER);
//		jl1.setPreferredSize(new Dimension(0, 50));
		jl1.setForeground(Color.black);
		jl1.setFont(new Font("微软雅黑", Font.BOLD, 20));
		jphy_jb2 = new JButton("商品", new ImageIcon("src\\images\\图片1.png"));
//		jphy_jb2.setContentAreaFilled(false);
		jphy_jb2.setBorderPainted(false);
		jphy_jb2.setBackground(Color.LIGHT_GRAY);
		jphy_jb2.setForeground(Color.black);
		jphy_jb3 = new JButton("社区", new ImageIcon("src\\images\\图片2.png"));
//		jphy_jb3.setContentAreaFilled(false);
		jphy_jb3.setBackground(Color.LIGHT_GRAY);
		jphy_jb3.setForeground(Color.black);
		jphy_jb3.setBorderPainted(false);
		jphy_jb4 = new JButton("购物车", new ImageIcon("src\\images\\图片3.png"));
//		jphy_jb4.setContentAreaFilled(false);
		jphy_jb4.setBackground(Color.LIGHT_GRAY);
		jphy_jb4.setForeground(Color.black);
		jphy_jb4.setBorderPainted(false);
		jphy_jb5 = new JButton("我的", new ImageIcon("src\\images\\图片4.png"));
//		jphy_jb5.setContentAreaFilled(false);
		jphy_jb5.setBorderPainted(false);
		jphy_jb5.setBackground(Color.LIGHT_GRAY);
		jphy_jb5.setForeground(Color.black);
		
		
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
		
		this.setTitle("ok");
		this.setDefaultCloseOperation(3);
		this.add(jphy1,"Center");
		this.setSize(800,800);
		this.setLocationRelativeTo(null); // 把窗口位置设置到屏幕中心
		this.setUndecorated(false); 
		this.setVisible(true);
		
	}
	public static void main(String[] args) {
		new FirstPage();
	}
}
