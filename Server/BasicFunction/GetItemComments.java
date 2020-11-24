package Server.BasicFunction;

import base.DIYClass.ChatMsg;
import base.DIYClass.Comment;
import base.DIYClass.Message;
import base.DIYClass.User;
import base.FunctionThread;

import java.io.IOException;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
��ȡһ��message��contextΪ��Ʒ��
����һ��comment��������
*/

public class GetItemComments extends FunctionThread {

    public GetItemComments(Socket socket) {
        this.socket = socket;

        start();
    }

    public void run() {
        try {
            getStream();
            Message message = (Message) readObj();
            if (message.getFileCon() == 0) { // ��fileConΪ0�����л�ȡ���۲���
                String itemName = message.getContext();
//            System.out.println(itemName);

                int cmtNum = 0;
                String sql = "SELECT ������ FROM ��Ʒ WHERE ��Ʒ��='" + itemName + "'";
                ResultSet resultSet = dataBase.query(sql);

                while (resultSet.next()) {
                    cmtNum = resultSet.getInt("������");
                }
                Comment[] comments = new Comment[cmtNum];
                System.out.println("������Ϊ" + cmtNum);

                sql = "SELECT * FROM ���� WHERE ��Ʒ='" + itemName + "'";
                resultSet = dataBase.query(sql);
//            System.out.println("-----------------------------------------------------");
//            while (resultSet.next()) {
//                System.out.println(resultSet.getString("��������"));
//            }
//            System.out.println("-----------------------------------------------------");
                int i = 0;
                while (resultSet.next()) {
                    comments[i] = new Comment();
                    comments[i].setComment(resultSet.getString("��������"));
                    comments[i].setSender(resultSet.getString("������"));
                    comments[i].setTimeDetail(resultSet.getString("ʱ��"));

                    i++;
                }

                writeObj(comments);
            } else if (message.getFileCon() == 1) { // ��Ϊ1�����л�ȡ������Ϣ
                String username = message.getContext();
                int msgNum = message.getFileLen();
                ChatMsg[] msg = new ChatMsg[msgNum];

                String sql = "SELECT * FROM ������Ϣ WHERE getter='" + username + "'";
                ResultSet resultSet = dataBase.query(sql);
                int i = 0;
                while (resultSet.next()) {
                    msg[i] = new ChatMsg();
                    User sender = new User();
                    sender.setUserId(resultSet.getString("sender"));
                    User getter = new User();
                    getter.setUserId(resultSet.getString("getter"));
                    msg[i].setSender(sender);
                    msg[i].setGetter(getter);
                    msg[i].setContext(resultSet.getString("context"));
                    msg[i].setSendTime(resultSet.getString("time"));

                    i++;
                }

                sql = "DELETE FROM ������Ϣ WHERE getter='" + username +"'";
                dataBase.update(sql);

                writeObj(msg);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
    }
}
