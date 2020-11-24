package base;

import Server.UserAction.LoginOrRegisterManager;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Listener extends Thread {
    public ServerSocket serverSocket;



//    public void run () {
//        try {
//            while (true) {
//                Socket socket = null;
//                try {
//                    socket = serverSocket.accept();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                try {
//                    new LoginOrRegisterManager(socket);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        } finally {
//            close();
//        }
//    }

    public void close () {
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
