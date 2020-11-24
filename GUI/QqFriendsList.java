package GUI;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.net.MalformedURLException;
 
import javax.swing.*;

 
public class QqFriendsList {
 
	
	public static void main(String[] args) {
		QqView view = new QqView();	
		view.setSize(500, 700);
		view.actionPerformed(null);
	}
 
}
 
class QqView extends JFrame implements ActionListener {
 
	JButton btn1,btn2,btn3,btn4,btn5,btn6,btnCha,btntom1,btntom2,btntom3,btntom4;
	JPanel jpMid,jpBotm,jtabP1,jtabP2,jtabP3,jpMidN,jpBotmC;
	JLabel jpTop,jlbTop,jpTopl;
	JTextField jqianM,jchaZhao;
	JTabbedPane jtabP;
	JComboBox jcb;
	File file1 = new File("music//����С��-�ഺ�����.wma");
	File file2 = new File("music//gggg.wav");
	AudioClip clip;
	
	public QqView() {
		
		super("QQ����");
		
		//����ͷ������
		this.setLayout(new BorderLayout());
		ImageIcon bounds = new ImageIcon("src\\images\\ͼƬ5.png");
		jpTop = new JLabel(bounds);
		jpTop.setBounds(0, 0, 300, bounds.getIconHeight());
		this.add(jpTop,BorderLayout.NORTH);
		
		//����ͷ��
		btn1 = new JButton(new ImageIcon("src\\images\\ͼƬ5.png"));
		btn1.setBounds(15, 20, new ImageIcon("src\\images\\ͼƬ5.png").getIconWidth(), new ImageIcon("res//qqtou.jpg").getIconHeight());
		jpTop.add(btn1);
		//�����ǳ����
		jpTopl = new JLabel("������ͷ��");
		jpTopl.setFont(new Font("����_GB2312", 10, 14));
		jpTopl.setForeground(Color.black);
		jpTopl.setLocation(152, 20);
		jpTopl.setSize(80, 20);
		jpTop.add(jpTopl);
		
		//����״̬��
		jcb = new JComboBox();
		jcb.addItem("����");
		jcb.addItem("�뿪");
		jcb.addItem("æµ");
		jcb.addItem("����");
		//jcb.setLocation(30, 10);
		jcb.setBounds(new Rectangle(90, 25, 55, 20));
		jpTop.add(jcb);
		
		//����ǩ��
		jqianM = new JTextField("��̫��Ϊ����ĵϰ��ҵİµϡ�����һ��Ŭ���ɡ�����");
		jpTop.add(jqianM);
		jqianM.setLocation(90, 50);
		jqianM.setSize(190,20);
			
		//���ӿռ�����Ȱ�ť
		btn2 = new JButton(new ImageIcon("imags//kongjian.jpg"));
		jpTop.add(btn2);
		btn2.setLocation(90, 80);
		btn2.setSize(25, 16);
		
		btn3 = new JButton(new ImageIcon("imags//weibo.jpg"));
		jpTop.add(btn3);
		btn3.setLocation(115, 80);
		btn3.setSize(25, 16);
		
		btn4 = new JButton(new ImageIcon("imags//youxiang.jpg"));
		jpTop.add(btn4);
		btn4.setLocation(140, 80);
		btn4.setSize(25, 16);
		
		btn5 = new JButton(new ImageIcon("imags//278.png"));
		jpTop.add(btn5);
		btn5.setLocation(165, 80);
		btn5.setSize(25, 16);
		
		btn6 = new JButton(new ImageIcon("imags//227.png"));
		jpTop.add(btn6);
		btn6.setLocation(190, 80);
		btn6.setSize(25, 16);
		
		//���Ѳ�ѯ����
		jchaZhao = new JTextField();
		jchaZhao.setSize(120, 30);
		jchaZhao.setLocation(0, 0);
		btnCha = new JButton(new ImageIcon("imags//143.png"));
		btnCha.setSize(23, 24);
		
		//���ô��ڲ˵�
		jtabP = new JTabbedPane();
		jtabP1 = new JPanel();
		jtabP2 = new JPanel();
		jtabP3 = new JPanel();
		
		jtabP1.setName("�����б�");
		jtabP2.setName("QȺ�б�");
		jtabP3.setName("�����ϵ");	 
		
		//���м������ӵ�frame���в�
		jpMid = new JPanel();
		this.add(jpMid,BorderLayout.CENTER);
		
		//�в�������²��ֳ�borderlayout
		jpMid.setLayout(new BorderLayout());
		jpMid.setSize(300, 300);
		//�����в����ı������������Ӳ��ҿ�Ͱ�ť �������˵���������
		jpMidN = new JPanel();
		jpMid.add(jpMidN, BorderLayout.NORTH);
		//�в����ı�������ٲ��ֳ�borlayout
		jpMidN.setLayout(new BorderLayout());
		//�������ӽ�ȥ
		jpMidN.add(jchaZhao,BorderLayout.CENTER);
		jpMidN.add(btnCha,BorderLayout.EAST);
		
		//�в���彫�˵����ӵ��в����ֵ��в�
		jpMid.add(jtabP,BorderLayout.CENTER);
		jtabP.add(jtabP1);
		jtabP.add(jtabP2);
		jtabP.add(jtabP3);
		
	
		//�ײ����
		jpBotm = new JPanel();
		jpBotm.setSize(300, 100);
		this.add(jpBotm, BorderLayout.SOUTH);
		jpBotm.setLayout(new BorderLayout());
		
		//ϵͳ����
		btntom1 = new JButton(new ImageIcon("imags//379.png"));
		btntom1.setSize(24, 25);
		jpBotm.add(btntom1,BorderLayout.WEST);
		
		jpBotmC = new JPanel();
		//���ֲ���
		btntom2 = new JButton(new ImageIcon("imags//460.png"));
		btntom2.setSize(24, 20);
		jpBotm.add(jpBotmC, BorderLayout.CENTER);
		jpBotmC.setLayout(new GridLayout(1, 2));
		//QQ����
		btntom3 = new JButton(new ImageIcon("imags//qe.jpg"));
		btntom3.setSize(24, 20);
		jpBotmC.add(btntom2);
		jpBotmC.add(btntom3);
		//���������Ŀ
		btntom4 = new JButton(new ImageIcon("imags//547.png"));
		btntom4.setSize(24, 20);
		jpBotm.add(btntom4,BorderLayout.EAST);
	
		
		//���ô���ͼ��
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image img = kit.getImage("res//15.png");
		this.setIconImage(img);
		
		//����������
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(300, 700);
		this.setLocation(400, 20);
		this.setVisible(true);
//		this.setResizable(false);
		//this.validate();
	}
 
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//��������
		btn1.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				JOptionPane.showMessageDialog(btn1, "�ǳƣ�������ͷ������ַ��������ʣ��绰��18311125741");
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		//���Ұ�ť���¼�
		btnCha.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					clip = Applet.newAudioClip(file2.toURL());
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				clip.play();
				
				JOptionPane.showMessageDialog(btnCha, "�����ҵĺ��Ѳ��������б��У�");
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		//���ֲ����¼�
		btntom2.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				
					try {
						clip = Applet.newAudioClip(file1.toURL());
					} catch (MalformedURLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					clip.play();
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				JOptionPane.showInputDialog("����������������");
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		btntom4.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				JOptionPane.showInputDialog(btntom4, "����Է�qq��");
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
	
}