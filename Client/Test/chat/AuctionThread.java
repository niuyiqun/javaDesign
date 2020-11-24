package Client.Test.chat;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import GUI.AuctionProcess;
import GUI.ChatWindow;
import base.DIYClass.Item;
import base.DIYClass.Message;
import base.DIYClass.User;

public class AuctionThread extends Thread{
	Socket socket;
	Message message,initMessage;
	AuctionProcess ap;
	ObjectOutputStream oos;
	User u0;
	
	public AuctionThread(Socket socket,User u) throws IOException {
        this.socket = socket;
        this.u0=u;
        try {
			oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(u0);
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			initMessage = (Message) ois.readObject();
		} catch (Exception e) {
			
		}
        start();
    }
	
	public void run() {
		
        while (true) {
            ObjectInputStream objectInputStream = null;
            message = null;
            try {
                objectInputStream = new ObjectInputStream(socket.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }

            
            try {
            	message = (Message) objectInputStream.readObject();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            
            if (message != null) {
            	ap.updatePrice(message);
            }
            
        }
    }
	
	public void addAuctionProcess(User u,Item i) {
		ap=new AuctionProcess(u, i,socket,oos,initMessage);
	}
	
}
