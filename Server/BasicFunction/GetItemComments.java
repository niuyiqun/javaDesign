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
获取一个message，context为商品名
返回一个comment对象数组
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
            if (message.getFileCon() == 0) { // 若fileCon为0，进行获取评论操作
                String itemName = message.getContext();
//            System.out.println(itemName);

                int cmtNum = 0;
                String sql = "SELECT 评论数 FROM 商品 WHERE 商品名='" + itemName + "'";
                ResultSet resultSet = dataBase.query(sql);

                while (resultSet.next()) {
                    cmtNum = resultSet.getInt("评论数");
                }
                Comment[] comments = new Comment[cmtNum];
                System.out.println("评论数为" + cmtNum);

                sql = "SELECT * FROM 评论 WHERE 商品='" + itemName + "'";
                resultSet = dataBase.query(sql);
//            System.out.println("-----------------------------------------------------");
//            while (resultSet.next()) {
//                System.out.println(resultSet.getString("评论内容"));
//            }
//            System.out.println("-----------------------------------------------------");
                int i = 0;
                while (resultSet.next()) {
                    comments[i] = new Comment();
                    comments[i].setComment(resultSet.getString("评论内容"));
                    comments[i].setSender(resultSet.getString("发送者"));
                    comments[i].setTimeDetail(resultSet.getString("时间"));

                    i++;
                }

                writeObj(comments);
            } else if (message.getFileCon() == 1) { // 若为1，进行获取离线消息
                String username = message.getContext();
                int msgNum = message.getFileLen();
                ChatMsg[] msg = new ChatMsg[msgNum];

                String sql = "SELECT * FROM 离线消息 WHERE getter='" + username + "'";
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

                sql = "DELETE FROM 离线消息 WHERE getter='" + username +"'";
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
