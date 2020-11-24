package Server.server;

import Server.BasicFunction.GetItemsList;
import Server.UserAction.ReleaseComment;
import Server.UserAction.ReleaseItem;
import base.BaseVariable;
import base.Listener;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Release_Item_Server extends Listener {
    public Release_Item_Server () throws IOException {
        serverSocket = new ServerSocket(BaseVariable.RELEASE_ITEM_PORT);
        System.out.println("发布商品服务已启动");
        start();
    }

    public void run () {
        try {
            while (true) {
                Socket socket = null;
                try {
//                    System.out.println("发布商品服务等待连接");
                    socket = serverSocket.accept();
//                    System.out.println("已连接");
                    new ReleaseItem(socket);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        } finally {
            close();
        }
    }
}
