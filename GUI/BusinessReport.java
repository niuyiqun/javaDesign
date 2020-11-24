package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.*;

import Client.Connect;
import base.DIYClass.Item;
import base.DIYClass.Message;
import base.DIYClass.User;

public class BusinessReport extends JFrame{
	
	JPanel jp1;
	JTable jtb1;
	JScrollPane jsp;
	User u0;
	Item []itemArr;
	JLabel jl;
	public BusinessReport(User u) {
		
		this.u0=u;
		Message m=new Message();
		m.setFileCon(3);
		m.setContext(u0.getUserId());
		Connect con=new Connect();
		
		itemArr=con.getBoughtCommodity(m);
		double totalPrice=0;
		Object[][] tableData=new Object[itemArr.length][5];
		for (int i = 0; i < itemArr.length; i++) {
			tableData[i][0]=i+1+1000;
			tableData[i][1]=itemArr[i].getName();
			tableData[i][2]=itemArr[i].getSoldTime();
			tableData[i][3]=itemArr[i].getBuyer();
			tableData[i][4]=itemArr[i].getPrice();
			totalPrice+=itemArr[i].getPrice();
		}
		
		jl =new JLabel("销售总额为"+Double.toString(totalPrice));
		jl.setFont(new Font("微软雅黑", Font.BOLD, 15));
		Object[] columnTitle = {"编号" , "商品名称" ,"日期","买家", "成交价"};
		
		jtb1=new JTable(tableData,columnTitle);
		
		jsp=new JScrollPane(jtb1);
		jp1=new JPanel(new FlowLayout(1,4,4));
		jp1.add(jl);
		
		this.setTitle("商业报表");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.add(jsp,"Center");
		this.add(jp1,BorderLayout.SOUTH);
		this.setSize(500,400);
		this.setLocationRelativeTo(null); // 把窗口位置设置到屏幕中心
		this.setUndecorated(false); 
		this.setVisible(true);
	}
	
}
