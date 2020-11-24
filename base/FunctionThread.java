package base;

import base.DIYClass.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;

public class FunctionThread extends Thread {
    public Socket socket;
    public ObjectInputStream objectInputStream;
    public ObjectOutputStream objectOutputStream;
    public Message message;
    public DataBase dataBase = new DataBase();

    public void closeAll () {
        try {
            objectOutputStream.close();
            objectInputStream.close();
            socket.close();
            dataBase.close();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void getStream () throws IOException {
//        System.out.println("获取输入输出流");
        objectInputStream = new ObjectInputStream(socket.getInputStream());
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
//        System.out.println("获取输入输出流over");
    }

    public Object readObj () throws IOException, ClassNotFoundException {
        return objectInputStream.readObject();
    }
    public void writeObj (Object obj) throws IOException {
        objectOutputStream.writeObject(obj);
    }
}
