package Server.server;

import Server.BasicFunction.GetItemsList;
import Server.UserAction.LoginOrRegisterManager;
import base.BaseVariable;
import base.Listener;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Get_List_Server extends Listener {
    public Get_List_Server () throws IOException {
        serverSocket = new ServerSocket(BaseVariable.GET_LIST_PORT);
        System.out.println("获取商品列表服务已启动");
        start();
    }

    public void run() {
        try {
            while (true) {
                Socket socket = null;
                try {
//                    System.out.println("获取商品列表服务等待连接");
                    socket = serverSocket.accept();
                    System.out.println("已连接");
                    new GetItemsList(socket);
//                    System.out.println("new完了");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            close();
        }
    }
}
