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

// 拍卖功能
public class AuctionManager extends Thread {
    // 哈希图：用于储存连接到的socket，但是在广播的时候直接广播到所有连接的客户端
    // 因此，用连接客户端的用户名保存没有意义
    // 故，使用integer保存
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
        System.out.println("拍卖服务已启动");
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
                // 每一个连接的客户端，创建一条新线程来处理其发送的消息
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
                System.out.println("参与拍卖的客户端有：" + cnt + "\n" + "当前价格为：" + prc.getContext());
            }
            objectOutputStream.writeObject(prc);
            while (true) {
                try {
                    try {
                        socket.sendUrgentData(0xFF);
                    } catch (Exception ex) {
                        ChatManager.hashMap.remove(user.getUserId());
                        System.out.println(user.getUserId() + "下线");
                        System.out.println("在线的客户端有：" + ChatManager.hashMap.size());
                        break;
                    }
                    // 开始计时
                    Timer timer = new Timer(hashMap, hs2, user.getUserId());
                    hs2.put(user.getUserId(), timer);
                    // 某一客户端传来报价
                    // 服务器将其广播到全部已经连接的客户端
                    // message要求 包含用户名以及报价

                    Message message = (Message) objectInputStream.readObject();
                    timer.interrupt();
//                    hashMap.remove(user.getUserId());
                    System.out.println(user.getUserId() + "出价：" + message.getContext());
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