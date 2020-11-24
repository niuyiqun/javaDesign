//package Server.BasicFunction;
//
//import base.DIYClass.Item;
//import base.DIYClass.Message;
//import base.FunctionThread;
//
//import java.io.IOException;
//import java.net.Socket;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//
//// 先获取全部商品：同GetItemsList
//
//public class ItemSearch extends FunctionThread {
//    public ItemSearch (Socket socket) {
//        this.socket = socket;
//
//        start();
//    }
//
//    public void run () {
//        try {
//            getStream();
//            message = (Message) readObj();
//
//            String keyWord = message.getContext();
//
//            String sql = "SELECT * FROM 商品数";
//            ResultSet resultSet = dataBase.query(sql);
//
//            int num = 0;
//
//            while (resultSet.next()) {
//                num = resultSet.getInt("count");
//            }
//
//            Item[] requiredItems = new Item[num]; // 获取一个偏大的商品对象数组
//
//            sql = "SELECT * FROM 商品 WHERE 商品名 LIKE '%" + keyWord + "%'";
//            resultSet = dataBase.query(sql);
//            int cnt = 0;
//            while (resultSet.next()) {
//                requiredItems[cnt] = new Item();
//                requiredItems[cnt].setName(resultSet.getString("商品名"));
//                requiredItems[cnt].setPrice(resultSet.getInt("商品价格"));
//                requiredItems[cnt].setDescription(resultSet.getString("商品描述"));
//                requiredItems[cnt].setSeller(resultSet.getString("卖家"));
//                requiredItems[cnt].setIfSold(resultSet.getInt("是否已售"));
//                requiredItems[cnt].setComment(resultSet.getInt("评论数"));
//                requiredItems[cnt].setImagePos(resultSet.getString("图片路径"));
//                requiredItems[cnt].setAction(resultSet.getInt("售卖方式"));
//
//                cnt++;
//            }
//            writeObj(requiredItems);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e){
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            closeAll();
//        }
//    }
//}
