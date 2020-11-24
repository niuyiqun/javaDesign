package GUI;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import javax.swing.*;

import Client.*;
import base.DIYClass.*;


public class Register extends JFrame implements ActionListener{
	
	JPanel jp1;
	JLabel nameLabel,label1;
	JTextField textField;
	JLabel passwdLabel;	
	JPasswordField passwdField;
	JLabel surePasswdLabel;
	JPasswordField surePasswdField;
	JButton ensure;

	public Register() {
		
		
		
		
		super("注册账号");
		setSize(600,500);
		setLocation(500,100);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		label1=new JLabel("账号注册");
		label1.setBounds(120, 20, 200, 20);
		add(label1);
		
		
		jp1=new JPanel();
		add(jp1);
		jp1.setLayout(null);
		
		
		nameLabel =new JLabel("学号:");
		textField =new JTextField();
		passwdLabel =new JLabel("密码:");
		passwdField =new JPasswordField();
		surePasswdLabel =new JLabel("确认密码:");
		surePasswdField =new JPasswordField();
		ensure =new JButton("确定");
		ensure.setContentAreaFilled(false);
		ensure.setBorderPainted(true);
		
		nameLabel.setBounds(100,70,80,25);
		textField.setBounds(160, 70, 100, 20);
		passwdLabel.setBounds(100,100,80,25);
		passwdField.setBounds(160, 100, 100, 20);
		surePasswdLabel.setBounds(100, 130, 80, 25);
		surePasswdField.setBounds(160, 130, 100, 20);
		ensure.setBounds(160, 160, 60, 20);
		ensure.addActionListener(this);
		
		
		jp1.add(nameLabel);
		jp1.add(textField);
		jp1.add(passwdLabel);
		jp1.add(passwdField);
		jp1.add(surePasswdLabel);
		jp1.add(surePasswdField);
		jp1.add(ensure);
		
		
	}
	
	public static void main(String[] args) {
		new Register();
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==ensure) {
			
			if (!(new String(passwdField.getPassword()).equals(new String(surePasswdField.getPassword())))){
				JOptionPane.showMessageDialog(this, "两次密码不一致");
			}else {
				TestLogin t=new TestLogin();
				User u=new User();
				u.setUserId(textField.getText().trim());
				u.setPassword(new String(passwdField.getPassword()));
				u.setAction(0);
				if (t.registerUser(u)) {   //成功注册
					JOptionPane.showMessageDialog(this, "注册成功");
					this.dispose();
					new Login2();
				}else {
					JOptionPane.showMessageDialog(this, "出现了些问题");
				}
			}
			
			
		}
		
	}
		
}
