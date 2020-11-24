package Client.Test;



import Client.Test.chat.getMessageThread;
import base.BaseVariable;
import base.DIYClass.Message;
import base.DIYClass.Time;
import base.DIYClass.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class sentMessageThread {
    public static void main(String[] args) {
        try {
        	
            Scanner scanner = new Scanner(System.in);
            Socket socket = new Socket("127.0.0.1", BaseVariable.CHAT_PORT);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

            User user = new User();
            user.setUserId("test1");

            oos.writeObject(user);
//            new getMessageThread(socket);
//            oos.writeObject(message);
            while (true) {
            	
                Message message = new Message();
                message.setSender("test1");
                message.setGetter("asd");
                message.setContext("hello");
                Time time = new Time();
                message.setSendTime(time.getDetail());
                System.out.println(message.getSendTime());
                message.setContext(scanner.nextLine());
                
                oos.writeObject(message);
                
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
