package Server.Chat;

import base.BaseVariable;

import base.DIYClass.ChatMsg;
import base.DIYClass.User;
import base.FunctionThread;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.HashMap;

import static Server.Chat.ChatManager.hashMap;

public class ChatManager extends Thread {
    public static HashMap<String, Socket> hashMap;
    private ServerSocket serverSocket;

    public static void main(String args[]) {
        try {
            new ChatManager();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ChatManager() throws IOException {
        hashMap = new HashMap<>(); // ������ϣ�����ڴ������ӵ�socket�����Ӧ���û���
        serverSocket = new ServerSocket(BaseVariable.CHAT_PORT);
        System.out.println("�������������");

        start();
    }

    @Override
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
                try {
                    // ÿһ�����ӵĿͻ��ˣ�����һ�����߳��������䷢�͵���Ϣ
                    new chatHandler(socket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } finally {

        }
    }
}

class chatHandler extends FunctionThread {
    public chatHandler(Socket socket) throws IOException {
        this.socket = socket;

        start();
    }

    @Override
    public void run() {
        try {
//            System.out.println("here no oos");
            objectInputStream = new ObjectInputStream(socket.getInputStream());
//            System.out.println("here have oos");
            User user = (User) readObj(); // �ȶ�ȡһ���ͻ��˷��͵�user����
//            System.out.println("here have user");

            // ��socket�����Ӧ�û����û��������ڹ�ϣ����
            // ���߳̿���ͬʱ��hashMap�����޸ģ���Ӧ��������ֹ���ʳ�ͻ
            synchronized (hashMap) {
                hashMap.put(user.getUserId(), socket);
                int cnt = hashMap.size();
                System.out.println("���ߵĿͻ����У�" + cnt);
            }

            while (true) {
                try {
                    //���Է��Ƿ�ر�����
                    try {
                        socket.sendUrgentData(0xFF);
                    } catch (Exception ex) {
                        hashMap.remove(user.getUserId());
                        System.out.println(user.getUserId() + "����");
                        System.out.println("���ߵĿͻ����У�" + hashMap.size());
                        break;
                    }

                    //���ղ�����message
                    ChatMsg message = (ChatMsg) readObj();
                    System.out.println(user.getUserId() + "˵��" + message.getContext());
                    if (hashMap.containsKey(message.getGetter().getUserId())) {
                        //���û����ߣ��������ڱ���
                        System.out.println("�Է�����");
                        Socket responder = hashMap.get(message.getGetter().getUserId());
                        ObjectOutputStream objectOutputStream1 = new ObjectOutputStream(responder.getOutputStream());
                        objectOutputStream1.writeObject(message);
                    } else {
                        String sql = "INSERT INTO ������Ϣ(sender,getter,context,time) VALUES('" + message.getSender().getUserId() + "','"
                                + message.getGetter().getUserId() + "','" + message.getContext() + "','" + message.getSendTime() + "')";
                        dataBase.update(sql);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
//                    hashMap.remove(user.getUserId());
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeAll(); //
        }
    }
}
