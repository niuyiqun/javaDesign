package Server.server;

import Server.UserAction.LoginOrRegisterManager;
import base.BaseVariable;
import base.Listener;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Login_Register_Server extends Listener {
    public Login_Register_Server () throws IOException {
        serverSocket = new ServerSocket(BaseVariable.LOGIN_REGISTER_PORT);
        System.out.println("登录注册服务已启动");
        start();
    }

    public void run () {
        try {
            while (true) {
                Socket socket = null;
                try {
                    socket = serverSocket.accept();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    new LoginOrRegisterManager(socket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            close();
        }
    }
}
