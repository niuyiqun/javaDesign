package GUI;


import java.awt.Color;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import Client.Connect;
import base.DIYClass.Item;
import base.DIYClass.User;

public class ReleaseAuctionCommodity extends JFrame implements ActionListener{
	
	
	/*
	 * 
	 * 
	 * item的上传还没有设置完哈
	 * 
	 * 
	 * 
	 */
	JPanel jp1,jp2;
	JButton jb1,jb2,jb3;
	JLabel jl1,jl2,jl3,jl11,jl22,jl33;
	JTextField jtf1,jtf2,jtf3;
	ImageIcon icon;
	File f;
	JLabel lbimg;
	User u0;
	JTextField jt1,jt2,jt3,jt4,jt5,jt6;
	TextBorderUtlis border = new TextBorderUtlis(Color.black,2,true);
	public ReleaseAuctionCommodity(User u) {
		
		u0=u;
		jp1= new JPanel();
		jp1.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		jp1.setBorder(border);
		jp1.setBounds(30, 40, 300, 400);
		
//		jp2= new JPanel();
//		jp2.setBackground(Color.darkGray);
//		jp2.setBounds(350,220,250,100);
		
		jl1= new JLabel("名称:");
		jl1.setBounds(350, 40, 60, 40);
		jl1.setFont(new Font("微软雅黑", Font.BOLD, 20));
		jl2= new JLabel("价格:");
		jl2.setFont(new Font("微软雅黑", Font.BOLD, 20));
		jl2.setBounds(350, 100, 60, 40);
		jl3= new JLabel("描述:");
		jl3.setFont(new Font("微软雅黑", Font.BOLD, 20));
		jl3.setBounds(350, 160, 60, 40);
		
		lbimg=new JLabel();
		
		jtf1 = new JTextField();
		jtf1.setBounds(400, 45, 200, 30);
		jtf2= new JTextField();
		jtf2.setBounds(400, 105, 200, 30);
		jtf3= new JTextField(3);
//		jl33.setText("<html>abc <br>def </html>");
		jtf3.setBounds(350,200,300,100);
		
		
		jb1= new JButton("发布");
		jb1.setBounds(340, 400, 100, 25);
		jb1.setBackground(Color.lightGray);
		jb1.setBorderPainted(false);
		jb1.addActionListener(this);
		jb2= new JButton("添加图片");
		jb2.setBounds(450, 400, 100, 25);
		jb2.setBackground(Color.lightGray);
		jb2.setBorderPainted(false);
		jb2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btn_addImgActionPerformed(e);
			}
		});
		jb3= new JButton("取消");
		jb3.setBounds(560, 400, 100, 25);
		jb3.setBackground(Color.lightGray);
		jb3.setBorderPainted(false);
		
		jp1.add(lbimg);
		
		this.setTitle("发布商品");
		this.add(jb1);
		this.add(jb2);
		this.add(jb3);
		this.add(jl1);
		this.add(jl2);
		this.add(jl3);
		this.add(jtf1);
		this.add(jtf2);
		this.add(jtf3);
		
		
		
		this.add(jp1);
		this.setLayout(null);
		this.setSize(700,500);
		this.setLocationRelativeTo(null); // 把窗口位置设置到屏幕中心
		this.setUndecorated(false); 
		this.setVisible(true);
		
		
	}
	
	private void btn_addImgActionPerformed(ActionEvent e) {
			
			JFileChooser fileChooser=new JFileChooser();    //new一个文件选择器对象
			
			FileNameExtensionFilter filter=new FileNameExtensionFilter("jpeg,gif,bmp,png", "jpg","gif","png","gif");   
			fileChooser.setFileFilter(filter);
			//设置文件过滤选择器，这里是只能选择图片文件
			
			try {
				fileChooser.showOpenDialog(null);
				
			} catch (HeadlessException e1) {
				// TODO: handle exception
				e1.printStackTrace();
			}   //弹出选择框
			
			try {
				f=fileChooser.getSelectedFile();
				icon=new ImageIcon(f.getPath());
				System.out.println(f.getPath());
			} catch (Exception e2) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "添加图片失败！");
				return;
			}
			
			icon.setImage(icon.getImage().getScaledInstance(300,400,Image.SCALE_DEFAULT));//80和100为大小 可以自由设置		
			lbimg.setIcon(icon);
		}
	
	public static void main(String[] args){
		new ReleaseCommodity(new User());
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==jb1) {
			Item i=new Item();
			i.setName(jtf1.getText().trim());
			i.setIfSold(0);
			double d=Double.parseDouble(new String(jtf2.getText().trim()));
			i.setPrice(d);
			i.setDescription(jtf3.getText().trim());
			String temp=f.getPath();
			i.setImagePos(temp);
			System.out.println(f.getPath());
			i.setSeller(u0.getUserId());
			i.setAction(0);
			Connect con= new Connect();
			if(con.releaseCommodity(i)) {
				JOptionPane.showMessageDialog(null, "发布商品成功");
				this.dispose();
			}else {
				JOptionPane.showMessageDialog(null, "发布商品失败,请重新发布");
				new ReleaseCommodity(u0);
				this.dispose();
			}
			
		}
		
	}
}

