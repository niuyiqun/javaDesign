package Server.UserAction;


import base.DIYClass.Comment;
import base.DIYClass.Message;
import base.FunctionThread;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

//����һ��comment����
public class ReleaseComment extends FunctionThread {
    public ReleaseComment(Socket socket) {
        this.socket = socket;
        start();
    }

    public void run() {
        try {
            getStream();
            Comment comment = (Comment) readObj();
//            System.out.println(comment.getTime().getDetail());
            String sql = "INSERT INTO ����(��Ʒ,��������,������,ʱ��) VALUES('" + comment.getItem() + "','"
                    + comment.getComment() + "','" + comment.getSender() + "','" + comment.getTimeDetail() + "')";

            System.out.println("��������ƷΪ"+ comment.getItem());
            dataBase.update(sql);

            sql = "UPDATE ��Ʒ SET ������=������+1 WHERE ��Ʒ��='" + comment.getItem() +"'";
            dataBase.update(sql);

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
