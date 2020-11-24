package Client;

import java.io.*;

import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import Client.Test.chat.getMessageThread;
import base.BaseVariable;
import base.DIYClass.ChatMsg;
import base.DIYClass.Comment;
import base.DIYClass.Item;
import base.DIYClass.Message;
import base.DIYClass.Time;
import base.DIYClass.User;


public class Connect {
	public Socket s;
	
	public Message SendLoginInfoToSever(Object o) {		     //发送登录信息

		try {
			s = new Socket(BaseVariable.HOST, BaseVariable.LOGIN_REGISTER_PORT);
			
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(o);
			s.shutdownOutput();
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			Message message = (Message) ois.readObject();
//			System.out.println("登录状况为：" + message.getContext());
			
			s.shutdownInput();
			oos.close();
            ois.close();
            s.close();
            
            return message;
			
		} catch (Exception e) {
			 e.printStackTrace();
		}
		
		return null;
		}
	
	
	public boolean SentRegisterInfoToSever(Object o) {    //发送注册信息


		try {
			s = new Socket(BaseVariable.HOST, BaseVariable.LOGIN_REGISTER_PORT);
			
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(o);
			s.shutdownOutput();
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			Message message = (Message) ois.readObject();
			
			s.shutdownInput();
			oos.close();
            ois.close();
            s.close();
            
            if (message.getContext().equals("RSuccess")) {
				return true;
			}else {
				return false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}


	public Item[] getItemList(Object o) {    //获取商品列表

		
		Item[] itemArr = null;
		
		try {
			s = new Socket(BaseVariable.HOST, BaseVariable.GET_LIST_PORT);
			
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(o);
			s.shutdownOutput();
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			itemArr= (Item[]) ois.readObject();
//			System.out.println("登录状况为：" + message.getContext());
			
			s.shutdownInput();
			oos.close();
            ois.close();
            s.close();
            
			
		} catch (Exception e) {
			 e.printStackTrace();
		}
		
		if (itemArr==null) {
			System.out.println("这里itemarr还是空的，没有接收到，这句话只在出现错误的时候会触发");
		}
		
		return itemArr;
		
	}
	
	
	public Item[] getSelectedItemList(Object o) {    //获取搜索的商品

		
		Item[] itemArr = null;
		
		try {
			s = new Socket(BaseVariable.HOST, BaseVariable.GET_LIST_PORT);
			
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(o);
			s.shutdownOutput();
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			itemArr= (Item[]) ois.readObject();
//			System.out.println("登录状况为：" + message.getContext());
			
			s.shutdownInput();
			oos.close();
            ois.close();
            s.close();
            
			
		} catch (Exception e) {
			 e.printStackTrace();
		}
		
		if (itemArr==null) {
			System.out.println("这里itemarr还是空的，搜索的商品没有接收到，这句话只在出现错误的时候会触发");
		}
		
		return itemArr;
		
	}
	
	public boolean releaseCommodity(Object o) {    //发布商品

		
		try {
			s = new Socket(BaseVariable.HOST, BaseVariable.RELEASE_ITEM_PORT);
			
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(o);
			s.shutdownOutput();
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			Message message = (Message) ois.readObject();
			
			s.shutdownInput();
			oos.close();
            ois.close();
            s.close();
            
            if (message.getContext().equals("success")) {
				return true;
			}else {
				return false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
		
	}
	
	public boolean buyItem(Object o) {      //购买商品
		
		try {
			s = new Socket(BaseVariable.HOST, BaseVariable.BUY_ITEM_PORT);
			
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(o);
			s.shutdownOutput();
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			Message message = (Message) ois.readObject();
			
			s.shutdownInput();
			oos.close();
            ois.close();
            s.close();
            
            if (message.getContext().equals("success")) {
				return true;
			}else {
				return false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
		
	}
	
	 
	
	public boolean ReleaseComment(Object o) {     //发表评论

		
		try {
			s = new Socket(BaseVariable.HOST, BaseVariable.RELEASE_COMMENT_PORT);
			
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(o);
			s.shutdownOutput();
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			Message message = (Message) ois.readObject();
			
			s.shutdownInput();
			oos.close();
            ois.close();
            s.close();
            
            if (message.getContext().equals("success")) {
				return true;
			}else {
				return false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
		
	}
	
//	public int sendChatUser(Object o) {           //发送登录用户
//		
//		try {	
//			s = new Socket(BaseVariable.HOST, BaseVariable.GET_COMMENT_PORT);  //打算把这个当成普通方法整
//			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
//			oos.writeObject(o);
//			s.shutdownOutput();
//			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
//			Message message = (Message) ois.readObject();
//			
//			s.shutdownInput();
//			oos.close();
//            ois.close();
//            s.close();
//            return message.getFileLen();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//			return 0;
//		
//	}
	
	public boolean getCommentList(Object o) {         //获取评论列表

		try {
			s = new Socket(BaseVariable.HOST, BaseVariable.GET_COMMENT_PORT);
			
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(o);
			s.shutdownOutput();
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			Message message = (Message) ois.readObject();
			
			s.shutdownInput();
			oos.close();
            ois.close();
            s.close();
            
            if (message.getContext().equals("success")) {
				return true;
			}else {
				return false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
		
	}
	
	
	public ChatMsg[] getOfflineInfo(Object o) {   //获取离线信息
		
		try {
			s = new Socket(BaseVariable.HOST, BaseVariable.GET_COMMENT_PORT);
			
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(o);
			s.shutdownOutput();
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			ChatMsg[] offlineInfo = (ChatMsg[]) ois.readObject();
			
			s.shutdownInput();
			oos.close();
            ois.close();
            s.close();
            
            return offlineInfo;
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("获取离线信息问题！！！！！");
		}
		
		return null;
	}
	
	public boolean addItemToShoppingTrolley(Object o) {   //加入购物车
		
		try {
			s = new Socket(BaseVariable.HOST, BaseVariable.FAVORITE_PORT);
			
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(o);
			s.shutdownOutput();
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			Message message = (Message) ois.readObject();
			
			s.shutdownInput();
			oos.close();
            ois.close();
            s.close();
            
            if (message.getContext().equals("s")) {
				return true;
			}else {
				return false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public Item[] getShoppingTrolleyItem(Object o) {   //获取购物车内商品
		try {
			s = new Socket(BaseVariable.HOST, BaseVariable.FAVORITE_PORT);
			
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(o);
			s.shutdownOutput();
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			Item[] items = (Item[]) ois.readObject();
			
			s.shutdownInput();
			oos.close();
            ois.close();
            s.close();
            
            return items;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("没获取到");
		return null;
	}
	
	public Comment [] getCommodityComments(Object o) {
		try {
			s = new Socket(BaseVariable.HOST, BaseVariable.GET_COMMENT_PORT);
			
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(o);
			s.shutdownOutput();
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			Comment[] Comments = (Comment[]) ois.readObject();
			
			s.shutdownInput();
			oos.close();
            ois.close();
            s.close();
            
            return Comments;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("没获取到");
		return null;
	}
	
	public boolean editUserInfo(Object o) {
		try {
			s = new Socket(BaseVariable.HOST, BaseVariable.LOGIN_REGISTER_PORT);
			
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(o);
			s.shutdownOutput();
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			Message message = (Message) ois.readObject();
			
			s.shutdownInput();
			oos.close();
            ois.close();
            s.close();
            
            if (message.getContext().equals("s")) {
				return true;
			}
            
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Item[] getSoldCommodity(Object o) {   //获取用户已售商品
		
		try {
			s = new Socket(BaseVariable.HOST, BaseVariable.GET_LIST_PORT);
			
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(o);
			s.shutdownOutput();
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			Item[] items = (Item[]) ois.readObject();
			
			s.shutdownInput();
			oos.close();
            ois.close();
            s.close();
            
            return items;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Item[] getBoughtCommodity(Object o) {   //获取用户已买商品
		
		try {
			s = new Socket(BaseVariable.HOST, BaseVariable.GET_LIST_PORT);
			
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(o);
			s.shutdownOutput();
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			Item[] items = (Item[]) ois.readObject();
			
			s.shutdownInput();
			oos.close();
            ois.close();
            s.close();
            
            return items;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Message getUserInfo(Object o) {
		
		try {
			s = new Socket(BaseVariable.HOST, BaseVariable.LOGIN_REGISTER_PORT);
			
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(o);
			s.shutdownOutput();
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			Message message = (Message) ois.readObject();
			
			s.shutdownInput();
			oos.close();
            ois.close();
            s.close();
            
            return message;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}

	
