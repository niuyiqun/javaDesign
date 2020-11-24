package Client.Test.chat;

import base.DIYClass.ChatMsg;
import base.DIYClass.Message;
import base.DIYClass.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

import GUI.ChatWindow;

public class getMessageThread extends Thread {
    Socket socket;
    public ChatMsg chatmsg;
    public ChatWindow [] cw;
    public int count;
    public ObjectOutputStream oos;

    public getMessageThread(Socket socket,ObjectOutputStream os) throws IOException {
    	count=0;
    	cw = new ChatWindow [99];
        this.socket = socket;
        this.oos=os;
        start();
    }
    
    public void addOfflineMsgWindow(ChatMsg [] cm) {
    	for (int i = 0; i < cm.length; i++) {
//			addChatWindow(cm[i].getGetter(), cm[i].getSender());
    		for (int j = 0; j < cw.length; j++) {
    			if (cw[j]==null) {
        			cw[j]=new ChatWindow(cm[i].getGetter(), cm[i].getSender(),socket,oos);
        			count++;
        			cw[j].receiveInfo(cm[i].getContext());
					break;
				}
        		
				if (cw[j].getGetter().getId()==cm[i].getSender().getId()
						&&cw[j].getSender().getId()==cm[i].getGetter().getId()) {
					 cw[j].receiveInfo(cm[i].getContext());
					 break;
				}
			}
    		
		}
    }
    
    public void addChatWindow(User sender,User getter) {   //这里是点击联系卖家的时候生成新的对话窗口
    	for (int i = 0; i < cw.length; i++) {
			if (cw[i]==null) {
				cw[i]=new ChatWindow(sender,getter,socket,oos);
				count++;
				break;
			}
			if (sender.getId()==cw[i].getSender().getId()&&getter.getId()==cw[i].getGetter().getId()) {
				JOptionPane.showMessageDialog(null, "对话框已存在");
				break;
			}
		}
    }

	public void run() {
		
//		try {
//			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
////			oos.writeObject(o);
//			socket.shutdownOutput();
//			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
//			Message message = (Message) ois.readObject();
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
		
		
        while (true) {
            ObjectInputStream objectInputStream = null;
            chatmsg = null;
            try {
                objectInputStream = new ObjectInputStream(socket.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }

            
            try {
            	chatmsg = (ChatMsg) objectInputStream.readObject();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            
//            if (message != null) {
//                System.out.println(message.getSender() + "说：" + message.getContext());
//            } else {
//                System.out.println("error: message is null");
//            }
            if (chatmsg != null) {
//            	JOptionPane.showMessageDialog(null, "您有新的消息，请注意查收");
//            	JOptionPane.showOptionDialog(null, objectInputStream, title, optionType, messageType, icon, options, initialValue)
//            	Object[] options ={ "查看", "好的" };  
//            	int m = JOptionPane.showOptionDialog(null, "您新收到了6条信息", "提示",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]); 
            	
            	for (int i = 0; i < cw.length; i++) {
            		if (cw[i]==null) {
            			cw[i]=new ChatWindow(chatmsg.getGetter(), chatmsg.getSender(), socket,oos);
            			count++;
            			cw[i].receiveInfo(chatmsg.getContext());
						break;
					}
            		
					if (cw[i].getGetter().getId()==chatmsg.getSender().getId()
							&&cw[i].getSender().getId()==chatmsg.getGetter().getId()) {
						 cw[i].receiveInfo(chatmsg.getContext());
						 break;
					}
				}
            }
            
        }
    }
}
