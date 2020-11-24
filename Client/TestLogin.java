package Client;

import base.DIYClass.*;


import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class TestLogin {
	
//	public boolean checkUser(User u){
//
//			return new Connect().SendLoginInfoToSever(u);
//
//		}
	
	public boolean registerUser(User u) {
		return new Connect().SentRegisterInfoToSever(u);
	}
	
	

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String id = scanner.next();
        String pw = scanner.next();

        try {
            Socket socket = new Socket("127.0.0.1", 2333);


            OutputStream os = socket.getOutputStream(); 
            //2.����getOutputStream()������Ŀ���ǽ������ݻ������������ݷ��͵���������
            
            ObjectOutputStream oos = new ObjectOutputStream(os);
            
            User user = new User();
            user.setUserId(id);
            user.setPassword(pw);
            
            oos.writeObject(user);

            socket.shutdownOutput();

            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Message message = (Message) ois.readObject();

            System.out.println("��¼״��Ϊ��" + message.getContext());

            socket.shutdownInput();
            oos.close();
            os.close();
            ois.close();
            socket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
