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
        hashMap = new HashMap<>(); // 创建哈希表，用于储存连接的socket及其对应的用户名
        serverSocket = new ServerSocket(BaseVariable.CHAT_PORT);
        System.out.println("聊天服务已启动");

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
                    // 每一个连接的客户端，创建一条新线程来处理其发送的消息
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
            User user = (User) readObj(); // 先读取一个客户端发送的user对象
//            System.out.println("here have user");

            // 将socket与其对应用户的用户名储存在哈希表中
            // 多线程可能同时对hashMap进行修改，故应锁定，防止访问冲突
            synchronized (hashMap) {
                hashMap.put(user.getUserId(), socket);
                int cnt = hashMap.size();
                System.out.println("上线的客户端有：" + cnt);
            }

            while (true) {
                try {
                    //检测对方是否关闭连接
                    try {
                        socket.sendUrgentData(0xFF);
                    } catch (Exception ex) {
                        hashMap.remove(user.getUserId());
                        System.out.println(user.getUserId() + "下线");
                        System.out.println("在线的客户端有：" + hashMap.size());
                        break;
                    }

                    //接收并处理message
                    ChatMsg message = (ChatMsg) readObj();
                    System.out.println(user.getUserId() + "说：" + message.getContext());
                    if (hashMap.containsKey(message.getGetter().getUserId())) {
                        //若用户在线：即储存在表中
                        System.out.println("对方在线");
                        Socket responder = hashMap.get(message.getGetter().getUserId());
                        ObjectOutputStream objectOutputStream1 = new ObjectOutputStream(responder.getOutputStream());
                        objectOutputStream1.writeObject(message);
                    } else {
                        String sql = "INSERT INTO 离线消息(sender,getter,context,time) VALUES('" + message.getSender().getUserId() + "','"
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
