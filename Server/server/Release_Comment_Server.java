package Server.server;

import Server.UserAction.ReleaseComment;
import Server.UserAction.ReleaseItem;
import base.BaseVariable;
import base.Listener;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Release_Comment_Server extends Listener {
    public Release_Comment_Server () throws IOException {
        serverSocket = new ServerSocket(BaseVariable.RELEASE_COMMENT_PORT);
        System.out.println("发布评论服务已启动");
        start();
    }

    public void run () {
        try {
            while (true) {
                Socket socket = null;
                try {
                    socket = serverSocket.accept();
                    new ReleaseComment(socket);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        } finally {
            close();
        }
    }
}
