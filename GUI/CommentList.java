package GUI;

import java.awt.*;

import javax.swing.*;

import base.DIYClass.Comment;
import base.DIYClass.User;

public class CommentList extends JFrame{
	
	JScrollPane jsp;
	JPanel [] jpls;   //每个人的评论总窗格
	JPanel [] head;  //如果要头像的话
	JPanel jp;
	TextBorderUtlis border = new TextBorderUtlis(Color.black,2,true);
	public CommentList(Comment []comment) {
		
		jp = new JPanel(new GridLayout(10,1));
		
		jpls = new JPanel [10];
		
		for (int i = 0; i < comment.length; i++) {
			jpls[i]=new JPanel(new BorderLayout());
			JPanel j1=new JPanel(new FlowLayout(0,4,4));
			JPanel j2=new JPanel(new FlowLayout(1,4,4));
			JLabel jl1=new JLabel(comment[i].getSender()+"    "+comment[i].getTimeDetail());
			JLabel jl2=new JLabel("<html>"+comment[i].getComment()+"</html>");
			jl2.setPreferredSize(new Dimension(350,50));
			j1.add(jl1);
//			j1.setPreferredSize(new Dimension(400,50));
			j2.add(jl2);
//			j2.setPreferredSize(new Dimension(400,150));
			jpls[i].add(j1,BorderLayout.NORTH);
			jpls[i].add(j2,BorderLayout.CENTER);
			jpls[i].setPreferredSize(new Dimension(350,100));
			jpls[i].setBorder(border);
			jp.add(jpls[i]);
		}
		
		jsp = new JScrollPane(jp);
		
		
		
		
		this.add(jsp);
		this.setSize(500,500);
		this.setLocationRelativeTo(null); // 把窗口位置设置到屏幕中心
		this.setUndecorated(false); 
		this.setVisible(true);
	}
	
	public static void main(String[] args){
//		new CommentList();
	}
}
