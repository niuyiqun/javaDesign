package Server.BasicFunction;

import base.DIYClass.Item;
import base.DIYClass.Message;
import base.DataBase;
import base.FunctionThread;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
/*
    需要根据item类的内容重新修改
    发送一个商品列表
*/

public class GetItemsList extends FunctionThread {

    public GetItemsList(Socket socket) throws IOException {
        this.socket = socket;
        getStream();
        start();
    }

    public void run() {
        try {
//            System.out.println("开始输出商品列表");
            //先查询商品总数
//            System.out.println("开始查询");
            message = (Message) readObj();
            if (message.getFileCon() == 0) {

                String sql = "SELECT * FROM 商品数";
                ResultSet resultSet = dataBase.query(sql);

//            System.out.println("查询结束");

                int num = 0;
//            System.out.println("总商品数为：" + num);
                while (resultSet.next()) {
                    num = resultSet.getInt("count");
                }


                Item[] items = new Item[num];

                System.out.println("总商品数为：" + num);

                //获取所有商品并储存到item数组中
                sql = "SELECT * FROM 商品";
                resultSet = dataBase.query(sql);
                int cnt = 0;
                while (resultSet.next()) {
                    items[cnt] = new Item();
                    items[cnt].setName(resultSet.getString("商品名"));
                    items[cnt].setPrice(resultSet.getInt("商品价格"));
                    items[cnt].setDescription(resultSet.getString("商品描述"));
                    items[cnt].setSeller(resultSet.getString("卖家"));
                    items[cnt].setIfSold(resultSet.getInt("是否已售"));
                    items[cnt].setComment(resultSet.getInt("评论数"));
                    items[cnt].setImagePos(resultSet.getString("图片路径"));
                    items[cnt].setAction(resultSet.getInt("售卖方式"));
                    items[cnt].setSoldTime(resultSet.getString("被卖时间"));

                    cnt++;
                }

                writeObj(items);
                System.out.println("商品列表已输出");
            } else if (message.getFileCon() == 1) { // 搜索商品
                String keyWord = message.getContext();

                String sql = "SELECT * FROM 商品数";
                ResultSet resultSet = dataBase.query(sql);

                int num = 0;
                while (resultSet.next()) {
                    num = resultSet.getInt("count");
                }
                Item[] requiredItems = new Item[num]; //
                System.out.println("搜索关键词为：" + keyWord);

                sql = "SELECT * FROM 商品 WHERE 商品名 LIKE '%" + keyWord + "%'";
                resultSet = dataBase.query(sql);
                int cnt = 0;
                while (resultSet.next()) {
                    requiredItems[cnt] = new Item();
                    requiredItems[cnt].setName(resultSet.getString("商品名"));
                    requiredItems[cnt].setPrice(resultSet.getInt("商品价格"));
                    requiredItems[cnt].setDescription(resultSet.getString("商品描述"));
                    requiredItems[cnt].setSeller(resultSet.getString("卖家"));
                    requiredItems[cnt].setIfSold(resultSet.getInt("是否已售"));
                    requiredItems[cnt].setComment(resultSet.getInt("评论数"));
                    requiredItems[cnt].setImagePos(resultSet.getString("图片路径"));
                    requiredItems[cnt].setAction(resultSet.getInt("售卖方式"));
                    requiredItems[cnt].setSoldTime(resultSet.getString("被卖时间"));

                    cnt++;
                }
                Item[] items = new Item[cnt];
                for(int k = 0; k < cnt; k ++) {
                    items[k] = requiredItems[k];
                }
                writeObj(items);
            } else if ((message.getFileCon() == 2) || (message.getFileCon() == 3)) { // 获取用户已售商品 or 已买商品
                String flag2, flag3;// 判断需要获取已买or已售
                if (message.getFileCon() == 2) {
                    flag2 = "soldnum";
                    flag3 = "卖家";
                } else {
                    flag2 = "buynum";
                    flag3 = "买家";
                }
                String user = message.getContext();
                int num = 0;
                String sql = "SELECT " + flag2 + " FROM 用户 WHERE 用户名='" + user + "'";
                ResultSet resultSet = dataBase.query(sql);

                while (resultSet.next()) {
                    num = resultSet.getInt(flag2);//获取已售商品数量
                }
                Item[] items = new Item[num];

                sql = "SELECT * FROM 商品 WHERE " + flag3 + "='" + user + "' and 是否已售=1";
                resultSet = dataBase.query(sql); // 获取此用户发售的所有已售商品
                int c = 0;
                while (resultSet.next()) {
                    items[c] = new Item();
                    items[c].setName(resultSet.getString("商品名"));
                    items[c].setPrice(resultSet.getInt("商品价格"));
                    items[c].setDescription(resultSet.getString("商品描述"));
                    items[c].setSeller(resultSet.getString("卖家"));
                    items[c].setIfSold(resultSet.getInt("是否已售"));
                    items[c].setComment(resultSet.getInt("评论数"));
                    items[c].setImagePos(resultSet.getString("图片路径"));
                    items[c].setAction(resultSet.getInt("售卖方式"));
                    items[c].setSoldTime(resultSet.getString("被卖时间"));

                    c++;
                }

                writeObj(items);

            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
    }

}
