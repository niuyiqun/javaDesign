package Server.UserAction;

import base.DIYClass.Message;
import base.FunctionThread;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

//����һ��������Ʒ����context����message,�Լ������
//����һ��message

public class BuyItem extends FunctionThread {

    public BuyItem(Socket socket) {
        this.socket = socket;

        start();
    }

    public void run() {
        try {
            getStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            message = (Message) readObj();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String itemName = message.getContext();
        String buyerName = message.getGetter();
        String sendTime = message.getSendTime();
        String seller = message.getSender();

//        String sql = "SELECT * FROM ��Ʒ WHERE��Ʒ��=" + itemName;

        //��������ݿ������Ʒ�����ڵ��쳣�������

        try {
            String sql = "UPDATE ��Ʒ SET ���='" + buyerName + "' WHERE ��Ʒ��='" + itemName + "'";//�������
//            System.out.println(sql);
            dataBase.update(sql);

            sql = "UPDATE ��Ʒ SET �Ƿ�����=1 WHERE ��Ʒ��='" + itemName + "'";//����Ϊ����
//            System.out.println(sql);
            dataBase.update(sql);

            sql = "UPDATE ��Ʒ SET ����ʱ��='" + sendTime + "'WHERE ��Ʒ��='" + itemName + "'";//���±���ʱ��
            dataBase.update(sql);

            // ��������������Ʒ����
            sql = "UPDATE �û� SET soldnum=soldnum+1 WHERE �û���='" + seller + "'";
            dataBase.update(sql);
// �����������
            sql = "UPDATE �û� SET buynum=buynum+1 WHERE �û���='" + buyerName + "'";
            dataBase.update(sql);

            message.setContext("success");
            writeObj(message);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
//        try {
//            ResultSet resultSet = dataBase.query(sql);
//            while (resultSet.next()) {
//
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }
}
