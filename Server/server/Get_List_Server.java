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
        System.out.println("��ȡ��Ʒ�б����������");
        start();
    }

    public void run() {
        try {
            while (true) {
                Socket socket = null;
                try {
//                    System.out.println("��ȡ��Ʒ�б����ȴ�����");
                    socket = serverSocket.accept();
                    System.out.println("������");
                    new GetItemsList(socket);
//                    System.out.println("new����");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            close();
        }
    }
}
