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
        System.out.println("������Ʒ����������");
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
