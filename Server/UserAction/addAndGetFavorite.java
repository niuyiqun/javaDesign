package Server.UserAction;

import base.DIYClass.Item;
import base.DIYClass.Message;
import base.FunctionThread;

import java.io.IOException;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;

public class addAndGetFavorite extends FunctionThread {
    public addAndGetFavorite(Socket socket) {
        this.socket = socket;

        start();
    }

    public void run() {
        try {
            getStream();
            Message m1 = (Message) readObj(); // 接受发来的message
            message = new Message();
            if (m1.getFileCon() == 0) { // 若fileCon为0，则为添加购物车
                String name = m1.getFileName(); // 商品名为fileName
                String username = m1.getContext(); // 用户名
                String sql0 = "SELECT * FROM favorite";
                ResultSet resultSet = dataBase.query(sql0);
                while (resultSet.next()) {
                    if (name.equals(resultSet.getString("item")) && username.equals(resultSet.getString("user"))) {
                        System.out.println("该商品已经存在于购物车，添加失败");
                        message.setContext("Fail");
                        writeObj(message);
                        return;
                    }
                }
                String sql = "INSERT INTO favorite(item,user) VALUES('" + name + "','" + username + "')";
                dataBase.update(sql);

                sql = "UPDATE 用户 SET favnum=favnum+1 WHERE 用户名='" + username + "'";
                dataBase.update(sql);

                message.setContext("s"); // 添加成功，返回一个message
                System.out.println("用户:" + username + "已添加商品:" + name);

                writeObj(message);//返回message
            } else if (m1.getFileCon() == 1) { //if it is 1, get
                String username = m1.getContext();
                String sql1 = "SELECT favnum FROM 用户 WHERE 用户名='" + username + "'";
                ResultSet rs = dataBase.query(sql1);
                int favnum = 0;
                while (rs.next()) {
                    favnum = rs.getInt("favnum");
                }
                Item[] favorites = new Item[favnum];
                String sql = "SELECT item FROM favorite WHERE user='" + username + "'";
                ResultSet resultSet = dataBase.query(sql);
                String[] fs = new String[favnum];
                int i = 0;
                while (resultSet.next()) {
                    fs[i] = resultSet.getString("item");
                    i++;
                }
                for (int j = 0; j < favnum; j++) {
                    sql = "SELECT * FROM 商品 WHERE 商品名='" + fs[j] + "'";
                    ResultSet s = dataBase.query(sql);
                    while (s.next()) {
                        favorites[j] = new Item();
                        favorites[j].setName(s.getString("商品名"));
                        favorites[j].setPrice(s.getInt("商品价格"));
                        favorites[j].setDescription(s.getString("商品描述"));
                        favorites[j].setSeller(s.getString("卖家"));
                        favorites[j].setIfSold(s.getInt("是否已售"));
                        favorites[j].setComment(s.getInt("评论数"));
                        favorites[j].setImagePos(s.getString("图片路径"));
                        favorites[j].setAction(s.getInt("售卖方式"));
                    }
                }
                writeObj(favorites);//返回item数组
            } else if (m1.getFileCon() == 2) {// 若为2，delete
                String item = m1.getFileName();
                String user = m1.getContext();

                String sql = "DELETE FROM favorite WHERE item='" + item + "' and user='" + user + "'";
                dataBase.update(sql);

                sql = "UPDATE 用户 SET favnum=favnum-1 WHERE 用户名='" + user + "'";
                dataBase.update(sql);
                message.setContext("ds"); // 删除成功，返回一个message
                System.out.println("用户:" + user + "已删除商品:" + item);

                writeObj(message);//返回message
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
