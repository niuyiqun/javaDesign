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
//// �Ȼ�ȡȫ����Ʒ��ͬGetItemsList
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
//            String sql = "SELECT * FROM ��Ʒ��";
//            ResultSet resultSet = dataBase.query(sql);
//
//            int num = 0;
//
//            while (resultSet.next()) {
//                num = resultSet.getInt("count");
//            }
//
//            Item[] requiredItems = new Item[num]; // ��ȡһ��ƫ�����Ʒ��������
//
//            sql = "SELECT * FROM ��Ʒ WHERE ��Ʒ�� LIKE '%" + keyWord + "%'";
//            resultSet = dataBase.query(sql);
//            int cnt = 0;
//            while (resultSet.next()) {
//                requiredItems[cnt] = new Item();
//                requiredItems[cnt].setName(resultSet.getString("��Ʒ��"));
//                requiredItems[cnt].setPrice(resultSet.getInt("��Ʒ�۸�"));
//                requiredItems[cnt].setDescription(resultSet.getString("��Ʒ����"));
//                requiredItems[cnt].setSeller(resultSet.getString("����"));
//                requiredItems[cnt].setIfSold(resultSet.getInt("�Ƿ�����"));
//                requiredItems[cnt].setComment(resultSet.getInt("������"));
//                requiredItems[cnt].setImagePos(resultSet.getString("ͼƬ·��"));
//                requiredItems[cnt].setAction(resultSet.getInt("������ʽ"));
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
