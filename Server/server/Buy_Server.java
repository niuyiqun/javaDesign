package Server.server;

import Server.BasicFunction.GetItemsList;
import Server.UserAction.BuyItem;
import base.BaseVariable;
import base.Listener;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Buy_Server extends Listener {
    public Buy_Server() throws IOException {
        serverSocket = new ServerSocket(BaseVariable.BUY_ITEM_PORT);
        System.out.println("购买商品服务已启动");
        start();
    }


    public void run() {
        try {
            while (true) {
                Socket socket = null;
                try {
                    socket = serverSocket.accept();
                    new BuyItem(socket);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        } finally {
            close();
        }
    }

}
