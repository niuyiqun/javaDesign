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
        System.out.println("��ȡ���۷���������");
        start();
    }

    public void run () {
        try {
            while (true) {
                Socket socket = null;
                try {
//                    System.out.println("������Ʒ����ȴ�����");
                    socket = serverSocket.accept();
//                    System.out.println("������");
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
