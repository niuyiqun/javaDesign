package GUI;

import java.awt.Color;
import java.awt.Rectangle;

import javax.swing.*;
//import javax.swing.plaf.TabbedPaneUI;

import Client.Connect;
import base.DIYClass.Item;
import base.DIYClass.Message;
import base.DIYClass.User;

public class MyCommodity extends JFrame{
	
	JTabbedPane jtp;
	JTable jtb1,jtb2;
	JPanel jp1,jp2;
	//1我买的    2我卖的
	User u0;
	Item [] i1,i2;
//	TabbedPaneUI ui=new TabbedPaneUI("Color.white","Color.black");
	public MyCommodity(User u) {
		
		this.u0=u;
		Message m1=new Message();
		m1.setFileCon(3);
		m1.setContext(u0.getUserId());
		Message m2=new Message();
		m2.setFileCon(2);
		m2.setContext(u0.getUserId());
		
		Connect con=new Connect();
		
		i1=con.getBoughtCommodity(m1);
		i2=con.getSoldCommodity(m2);
		
		Object[] columnTitle1 = {"序号" , "商品名称" ,"卖家", "价格"};
		Object[][] tableData1=new Object[i1.length][4];
		for (int i = 0; i < tableData1.length; i++) {
			tableData1[i][0]=i+1;
			tableData1[i][1]=i1[i].getName();
			tableData1[i][2]=i1[i].getSeller();
			tableData1[i][3]=i1[i].getPrice();
			
		}
		
//		if (i1[i].getAction()==0) {
//			tableData1[i][3]="正常售卖";
//		}else {
//			tableData1[i][3]="拍卖";
//		}
		
		
		jtb1=new JTable(tableData1,columnTitle1);
		
		
		Object[] columnTitle2 = {"序号" , "商品名称" ,"售卖方式", "价格"};
		Object[][] tableData2=new Object[i2.length][4];
		for (int i = 0; i < tableData2.length; i++) {
			tableData2[i][0]=i+1;
			tableData2[i][1]=i2[i].getName();
			if (i2[i].getAction()==0) {
				tableData2[i][2]="正常售卖";
			}else {
				tableData2[i][2]="拍卖";
			}
			tableData2[i][3]=i2[i].getPrice();
			
		}
		jtb2=new JTable(tableData2,columnTitle2);
		
		jtb1.setRowHeight(30);
		jtb2.setRowHeight(30);
		jtp = new JTabbedPane();
//		jtp.setUI(ui);
		jtp.add("我买到的",new JScrollPane(jtb1));
		jtp.add("我发布的",new JScrollPane(jtb2));
		
		this.setTitle("我的商品");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.add(jtp,"Center");
		this.setSize(400,300);
		this.setLocationRelativeTo(null); // 把窗口位置设置到屏幕中心
		this.setUndecorated(false); 
		this.setVisible(true);
	}
	public static void main(String[] args) {
		new MyCommodity(new User());
	}
}
