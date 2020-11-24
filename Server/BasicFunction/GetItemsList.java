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
    ��Ҫ����item������������޸�
    ����һ����Ʒ�б�
*/

public class GetItemsList extends FunctionThread {

    public GetItemsList(Socket socket) throws IOException {
        this.socket = socket;
        getStream();
        start();
    }

    public void run() {
        try {
//            System.out.println("��ʼ�����Ʒ�б�");
            //�Ȳ�ѯ��Ʒ����
//            System.out.println("��ʼ��ѯ");
            message = (Message) readObj();
            if (message.getFileCon() == 0) {

                String sql = "SELECT * FROM ��Ʒ��";
                ResultSet resultSet = dataBase.query(sql);

//            System.out.println("��ѯ����");

                int num = 0;
//            System.out.println("����Ʒ��Ϊ��" + num);
                while (resultSet.next()) {
                    num = resultSet.getInt("count");
                }


                Item[] items = new Item[num];

                System.out.println("����Ʒ��Ϊ��" + num);

                //��ȡ������Ʒ�����浽item������
                sql = "SELECT * FROM ��Ʒ";
                resultSet = dataBase.query(sql);
                int cnt = 0;
                while (resultSet.next()) {
                    items[cnt] = new Item();
                    items[cnt].setName(resultSet.getString("��Ʒ��"));
                    items[cnt].setPrice(resultSet.getInt("��Ʒ�۸�"));
                    items[cnt].setDescription(resultSet.getString("��Ʒ����"));
                    items[cnt].setSeller(resultSet.getString("����"));
                    items[cnt].setIfSold(resultSet.getInt("�Ƿ�����"));
                    items[cnt].setComment(resultSet.getInt("������"));
                    items[cnt].setImagePos(resultSet.getString("ͼƬ·��"));
                    items[cnt].setAction(resultSet.getInt("������ʽ"));
                    items[cnt].setSoldTime(resultSet.getString("����ʱ��"));

                    cnt++;
                }

                writeObj(items);
                System.out.println("��Ʒ�б������");
            } else if (message.getFileCon() == 1) { // ������Ʒ
                String keyWord = message.getContext();

                String sql = "SELECT * FROM ��Ʒ��";
                ResultSet resultSet = dataBase.query(sql);

                int num = 0;
                while (resultSet.next()) {
                    num = resultSet.getInt("count");
                }
                Item[] requiredItems = new Item[num]; //
                System.out.println("�����ؼ���Ϊ��" + keyWord);

                sql = "SELECT * FROM ��Ʒ WHERE ��Ʒ�� LIKE '%" + keyWord + "%'";
                resultSet = dataBase.query(sql);
                int cnt = 0;
                while (resultSet.next()) {
                    requiredItems[cnt] = new Item();
                    requiredItems[cnt].setName(resultSet.getString("��Ʒ��"));
                    requiredItems[cnt].setPrice(resultSet.getInt("��Ʒ�۸�"));
                    requiredItems[cnt].setDescription(resultSet.getString("��Ʒ����"));
                    requiredItems[cnt].setSeller(resultSet.getString("����"));
                    requiredItems[cnt].setIfSold(resultSet.getInt("�Ƿ�����"));
                    requiredItems[cnt].setComment(resultSet.getInt("������"));
                    requiredItems[cnt].setImagePos(resultSet.getString("ͼƬ·��"));
                    requiredItems[cnt].setAction(resultSet.getInt("������ʽ"));
                    requiredItems[cnt].setSoldTime(resultSet.getString("����ʱ��"));

                    cnt++;
                }
                Item[] items = new Item[cnt];
                for(int k = 0; k < cnt; k ++) {
                    items[k] = requiredItems[k];
                }
                writeObj(items);
            } else if ((message.getFileCon() == 2) || (message.getFileCon() == 3)) { // ��ȡ�û�������Ʒ or ������Ʒ
                String flag2, flag3;// �ж���Ҫ��ȡ����or����
                if (message.getFileCon() == 2) {
                    flag2 = "soldnum";
                    flag3 = "����";
                } else {
                    flag2 = "buynum";
                    flag3 = "���";
                }
                String user = message.getContext();
                int num = 0;
                String sql = "SELECT " + flag2 + " FROM �û� WHERE �û���='" + user + "'";
                ResultSet resultSet = dataBase.query(sql);

                while (resultSet.next()) {
                    num = resultSet.getInt(flag2);//��ȡ������Ʒ����
                }
                Item[] items = new Item[num];

                sql = "SELECT * FROM ��Ʒ WHERE " + flag3 + "='" + user + "' and �Ƿ�����=1";
                resultSet = dataBase.query(sql); // ��ȡ���û����۵�����������Ʒ
                int c = 0;
                while (resultSet.next()) {
                    items[c] = new Item();
                    items[c].setName(resultSet.getString("��Ʒ��"));
                    items[c].setPrice(resultSet.getInt("��Ʒ�۸�"));
                    items[c].setDescription(resultSet.getString("��Ʒ����"));
                    items[c].setSeller(resultSet.getString("����"));
                    items[c].setIfSold(resultSet.getInt("�Ƿ�����"));
                    items[c].setComment(resultSet.getInt("������"));
                    items[c].setImagePos(resultSet.getString("ͼƬ·��"));
                    items[c].setAction(resultSet.getInt("������ʽ"));
                    items[c].setSoldTime(resultSet.getString("����ʱ��"));

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
