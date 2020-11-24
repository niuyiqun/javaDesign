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
            Message m1 = (Message) readObj(); // ���ܷ�����message
            message = new Message();
            if (m1.getFileCon() == 0) { // ��fileConΪ0����Ϊ��ӹ��ﳵ
                String name = m1.getFileName(); // ��Ʒ��ΪfileName
                String username = m1.getContext(); // �û���
                String sql0 = "SELECT * FROM favorite";
                ResultSet resultSet = dataBase.query(sql0);
                while (resultSet.next()) {
                    if (name.equals(resultSet.getString("item")) && username.equals(resultSet.getString("user"))) {
                        System.out.println("����Ʒ�Ѿ������ڹ��ﳵ�����ʧ��");
                        message.setContext("Fail");
                        writeObj(message);
                        return;
                    }
                }
                String sql = "INSERT INTO favorite(item,user) VALUES('" + name + "','" + username + "')";
                dataBase.update(sql);

                sql = "UPDATE �û� SET favnum=favnum+1 WHERE �û���='" + username + "'";
                dataBase.update(sql);

                message.setContext("s"); // ��ӳɹ�������һ��message
                System.out.println("�û�:" + username + "�������Ʒ:" + name);

                writeObj(message);//����message
            } else if (m1.getFileCon() == 1) { //if it is 1, get
                String username = m1.getContext();
                String sql1 = "SELECT favnum FROM �û� WHERE �û���='" + username + "'";
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
                    sql = "SELECT * FROM ��Ʒ WHERE ��Ʒ��='" + fs[j] + "'";
                    ResultSet s = dataBase.query(sql);
                    while (s.next()) {
                        favorites[j] = new Item();
                        favorites[j].setName(s.getString("��Ʒ��"));
                        favorites[j].setPrice(s.getInt("��Ʒ�۸�"));
                        favorites[j].setDescription(s.getString("��Ʒ����"));
                        favorites[j].setSeller(s.getString("����"));
                        favorites[j].setIfSold(s.getInt("�Ƿ�����"));
                        favorites[j].setComment(s.getInt("������"));
                        favorites[j].setImagePos(s.getString("ͼƬ·��"));
                        favorites[j].setAction(s.getInt("������ʽ"));
                    }
                }
                writeObj(favorites);//����item����
            } else if (m1.getFileCon() == 2) {// ��Ϊ2��delete
                String item = m1.getFileName();
                String user = m1.getContext();

                String sql = "DELETE FROM favorite WHERE item='" + item + "' and user='" + user + "'";
                dataBase.update(sql);

                sql = "UPDATE �û� SET favnum=favnum-1 WHERE �û���='" + user + "'";
                dataBase.update(sql);
                message.setContext("ds"); // ɾ���ɹ�������һ��message
                System.out.println("�û�:" + user + "��ɾ����Ʒ:" + item);

                writeObj(message);//����message
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
