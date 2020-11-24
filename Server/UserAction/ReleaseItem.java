package Server.UserAction;

import base.DIYClass.Item;
import base.DIYClass.Message;
import base.DataBase;
import base.FunctionThread;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;

//����һ��item���󣬴��浽���ݿ���
//����һ��message������Ʒ�����ɹ�ʱ��contextΪsuccess
public class ReleaseItem extends FunctionThread {//Ӧ���ͼƬ����

    public ReleaseItem(Socket socket) {
        this.socket = socket;

        start();
    }

    public void run() {
//        System.out.println("������Ʒ��������");

        try {
            getStream();
            Item item = (Item) readObj();
            System.out.println(item.getImagePos());
            String pos = item.getImagePos();
            String str = pos.replace('\\', '/');
            System.out.println(str);
            String sql = "INSERT INTO ��Ʒ(��Ʒ��,��Ʒ�۸�,��Ʒ����,����,ͼƬ·��,�Ƿ�����,���,������,������ʽ) VALUES('" + item.getName() + "','"
                    + item.getPrice() + "','" + item.getDescription() + "','"
                    + item.getSeller() + "','" + str + "',0,'null',0,"+ item.getAction()+")";

            dataBase.update(sql);
            sql = "UPDATE ��Ʒ�� SET count=count+1";
            dataBase.update(sql);

            System.out.println("��Ʒ�����ɹ�");
            message = new Message();
            message.setContext("success");
            writeObj(message);
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }

    }
}
