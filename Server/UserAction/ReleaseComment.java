package Server.UserAction;


import base.DIYClass.Comment;
import base.DIYClass.Message;
import base.FunctionThread;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

//接收一个comment对象
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
            String sql = "INSERT INTO 评论(商品,评论内容,发送者,时间) VALUES('" + comment.getItem() + "','"
                    + comment.getComment() + "','" + comment.getSender() + "','" + comment.getTimeDetail() + "')";

            System.out.println("被评论商品为"+ comment.getItem());
            dataBase.update(sql);

            sql = "UPDATE 商品 SET 评论数=评论数+1 WHERE 商品名='" + comment.getItem() +"'";
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
