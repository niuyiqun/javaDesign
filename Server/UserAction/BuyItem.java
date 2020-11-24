package Server.UserAction;

import base.DIYClass.Message;
import base.FunctionThread;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

//接收一个包含商品名（context）的message,以及买家名
//发出一个message

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

//        String sql = "SELECT * FROM 商品 WHERE商品名=" + itemName;

        //添加若数据库查找商品不存在的异常处理情况

        try {
            String sql = "UPDATE 商品 SET 买家='" + buyerName + "' WHERE 商品名='" + itemName + "'";//更新买家
//            System.out.println(sql);
            dataBase.update(sql);

            sql = "UPDATE 商品 SET 是否已售=1 WHERE 商品名='" + itemName + "'";//设置为已售
//            System.out.println(sql);
            dataBase.update(sql);

            sql = "UPDATE 商品 SET 被卖时间='" + sendTime + "'WHERE 商品名='" + itemName + "'";//更新被卖时间
            dataBase.update(sql);

            // 更新卖家已售商品数量
            sql = "UPDATE 用户 SET soldnum=soldnum+1 WHERE 用户名='" + seller + "'";
            dataBase.update(sql);
// 更新买家已买
            sql = "UPDATE 用户 SET buynum=buynum+1 WHERE 用户名='" + buyerName + "'";
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
