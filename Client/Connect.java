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
	
	public Message SendLoginInfoToSever(Object o) {		     //���͵�¼��Ϣ

		try {
			s = new Socket(BaseVariable.HOST, BaseVariable.LOGIN_REGISTER_PORT);
			
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(o);
			s.shutdownOutput();
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			Message message = (Message) ois.readObject();
//			System.out.println("��¼״��Ϊ��" + message.getContext());
			
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
	
	
	public boolean SentRegisterInfoToSever(Object o) {    //����ע����Ϣ


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


	public Item[] getItemList(Object o) {    //��ȡ��Ʒ�б�

		
		Item[] itemArr = null;
		
		try {
			s = new Socket(BaseVariable.HOST, BaseVariable.GET_LIST_PORT);
			
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(o);
			s.shutdownOutput();
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			itemArr= (Item[]) ois.readObject();
//			System.out.println("��¼״��Ϊ��" + message.getContext());
			
			s.shutdownInput();
			oos.close();
            ois.close();
            s.close();
            
			
		} catch (Exception e) {
			 e.printStackTrace();
		}
		
		if (itemArr==null) {
			System.out.println("����itemarr���ǿյģ�û�н��յ�����仰ֻ�ڳ��ִ����ʱ��ᴥ��");
		}
		
		return itemArr;
		
	}
	
	
	public Item[] getSelectedItemList(Object o) {    //��ȡ��������Ʒ

		
		Item[] itemArr = null;
		
		try {
			s = new Socket(BaseVariable.HOST, BaseVariable.GET_LIST_PORT);
			
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(o);
			s.shutdownOutput();
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			itemArr= (Item[]) ois.readObject();
//			System.out.println("��¼״��Ϊ��" + message.getContext());
			
			s.shutdownInput();
			oos.close();
            ois.close();
            s.close();
            
			
		} catch (Exception e) {
			 e.printStackTrace();
		}
		
		if (itemArr==null) {
			System.out.println("����itemarr���ǿյģ���������Ʒû�н��յ�����仰ֻ�ڳ��ִ����ʱ��ᴥ��");
		}
		
		return itemArr;
		
	}
	
	public boolean releaseCommodity(Object o) {    //������Ʒ

		
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
	
	public boolean buyItem(Object o) {      //������Ʒ
		
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
	
	 
	
	public boolean ReleaseComment(Object o) {     //��������

		
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
	
//	public int sendChatUser(Object o) {           //���͵�¼�û�
//		
//		try {	
//			s = new Socket(BaseVariable.HOST, BaseVariable.GET_COMMENT_PORT);  //��������������ͨ������
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
	
	public boolean getCommentList(Object o) {         //��ȡ�����б�

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
	
	
	public ChatMsg[] getOfflineInfo(Object o) {   //��ȡ������Ϣ
		
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
			System.out.println("��ȡ������Ϣ���⣡��������");
		}
		
		return null;
	}
	
	public boolean addItemToShoppingTrolley(Object o) {   //���빺�ﳵ
		
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
	
	public Item[] getShoppingTrolleyItem(Object o) {   //��ȡ���ﳵ����Ʒ
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
		System.out.println("û��ȡ��");
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
		System.out.println("û��ȡ��");
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
	
	public Item[] getSoldCommodity(Object o) {   //��ȡ�û�������Ʒ
		
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
	
	public Item[] getBoughtCommodity(Object o) {   //��ȡ�û�������Ʒ
		
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

	
