package Server.server;

import Server.UserAction.BuyItem;
import Server.UserAction.addAndGetFavorite;
import base.BaseVariable;
import base.Listener;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class FavoriteServer extends Listener {
    public FavoriteServer () throws IOException {
        serverSocket = new ServerSocket(BaseVariable.FAVORITE_PORT);
        System.out.println("购物车服务已启动");
        start();
    }

    public void run() {
        try {
            while (true) {
                Socket socket = null;
                try {
                    socket = serverSocket.accept();
                    new addAndGetFavorite(socket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            close();
        }
    }

}
