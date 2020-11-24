package Server.Chat;

import base.DIYClass.Message;
import base.DIYClass.Time;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;

import static Server.Chat.AuctionManager.hashMap;

public class Timer extends Thread {
    HashMap<String, Socket> hashMap;
    HashMap<String, Thread> hs2;
    String user;
    // 计时器，用于监测拍卖进程的接收message

    public Timer(HashMap<String, Socket> hashMap,HashMap<String, Thread> hs2, String user) {
        this.hashMap = hashMap;
        this.hs2 = hs2;
        this.user = user;

        start();
    }

    public void run() {
        try {
            this.sleep(10000);
            if (interrupted()) {
                throw new InterruptedException("接收消息，计时中断");
            } else if (!interrupted()) {
                Message message = new Message();
                message.setContext("end");
                message.setSender(user);

                for (String key : hs2.keySet()) {
                    Thread t = hs2.get(key);
                    t.interrupt();
                }

                for (String key : hashMap.keySet()) {
                    Socket s = hashMap.get(key);
                    ObjectOutputStream objectOutputStream = null;
                    objectOutputStream = new ObjectOutputStream(s.getOutputStream());
                    objectOutputStream.writeObject(message);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
