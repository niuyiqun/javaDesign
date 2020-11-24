package Server.server;

import Server.BasicFunction.GetItemComments;
import Server.UserAction.ReleaseComment;
import base.BaseVariable;
import base.Listener;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Get_Item_Comment_Server extends Listener {

    public Get_Item_Comment_Server () throws IOException {
        serverSocket = new ServerSocket(BaseVariable.GET_COMMENT_PORT);
        System.out.println("获取评论服务已启动");
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
                    new GetItemComments(socket);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        } finally {
            close();
        }
    }
}
