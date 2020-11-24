package Server.Chat;

import base.BaseVariable;
import base.DIYClass.ChatMsg;
import base.DIYClass.Message;
import base.DIYClass.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import static Server.Chat.AuctionManager.*;

// ��������
public class AuctionManager extends Thread {
    // ��ϣͼ�����ڴ������ӵ���socket�������ڹ㲥��ʱ��ֱ�ӹ㲥���������ӵĿͻ���
    // ��ˣ������ӿͻ��˵��û�������û������
    // �ʣ�ʹ��integer����
    public static HashMap<String, Socket> hashMap = new HashMap<>();
    public static HashMap<String, Thread> hs2 = new HashMap<>();
    public static int price = 10;
    ;
    private ServerSocket serverSocket;

    public static void main(String[] args) {
        try {
            new AuctionManager();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public AuctionManager() throws IOException {
        serverSocket = new ServerSocket(BaseVariable.AUCTION_PORT);
        System.out.println("��������������");
        start();
    }

    public void run() {
        try {
            Socket socket;
            while (true) {
                socket = null;
                try {
                    socket = serverSocket.accept();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // ÿһ�����ӵĿͻ��ˣ�����һ�����߳��������䷢�͵���Ϣ
                new AuctionHandler(socket);
            }
        } finally {

        }

    }
}

class AuctionHandler extends Thread {
    Socket socket;

    public AuctionHandler(Socket socket) {
        this.socket = socket;
        start();
    }

    public void run() {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            User user = (User) objectInputStream.readObject();
            Message prc = new Message();
            prc.setContext(price + "");
            synchronized (AuctionManager.hashMap) {
                AuctionManager.hashMap.put(user.getUserId(), socket);
                int cnt = AuctionManager.hashMap.size();
                System.out.println("���������Ŀͻ����У�" + cnt + "\n" + "��ǰ�۸�Ϊ��" + prc.getContext());
            }
            objectOutputStream.writeObject(prc);
            while (true) {
                try {
                    try {
                        socket.sendUrgentData(0xFF);
                    } catch (Exception ex) {
                        ChatManager.hashMap.remove(user.getUserId());
                        System.out.println(user.getUserId() + "����");
                        System.out.println("���ߵĿͻ����У�" + ChatManager.hashMap.size());
                        break;
                    }
                    // ��ʼ��ʱ
                    Timer timer = new Timer(hashMap, hs2, user.getUserId());
                    hs2.put(user.getUserId(), timer);
                    // ĳһ�ͻ��˴�������
                    // ����������㲥��ȫ���Ѿ����ӵĿͻ���
                    // messageҪ�� �����û����Լ�����

                    Message message = (Message) objectInputStream.readObject();
                    timer.interrupt();
//                    hashMap.remove(user.getUserId());
                    System.out.println(user.getUserId() + "���ۣ�" + message.getContext());
                    message.setSender(user.getUserId());
                    message.setFileLen(hashMap.size());
                    for (String key : hashMap.keySet()) {
                        Socket s = hashMap.get(key);
                        objectOutputStream = new ObjectOutputStream(s.getOutputStream());
                        objectOutputStream.writeObject(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}