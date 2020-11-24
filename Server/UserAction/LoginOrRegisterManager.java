package Server.UserAction;

import base.BaseVariable;
import base.DIYClass.Message;
import base.DIYClass.User;
import base.DataBase;
import base.FunctionThread;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Function;

// �û���¼ע��͵ǳ�
public class LoginOrRegisterManager extends FunctionThread {

    public LoginOrRegisterManager(Socket socket) throws IOException {
        this.socket = socket;

        getStream();
        start();
    }

    public void run() {
        try {
            User user = (User) readObj();

            if (user.getAction() == BaseVariable.LOGIN) {
                message = login(user);
            } else if (user.getAction() == BaseVariable.REGISTER) {
                message = register(user);
            } else if (user.getAction() == BaseVariable.LOGOUT) {
                message = logout(user);
            } else if (user.getAction() == 5) { // �����û���Ϣ�������������Ա�רҵ�����
                String name, sex, major, grade;
                name = user.getName();
                sex = user.getSex();
                grade = user.getGrade();
                major = user.getMajor();
                String userID = user.getUserId();

                String sql = "UPDATE �û� SET ��ʵ����='" + name + "', �Ա�='" + sex + "', רҵ='" + major +
                        "', �꼶='" + grade + "' WHERE �û���='" + userID + "'";
                dataBase.update(sql);
                message = new Message();
                message.setContext("s");
                // ��ȡ�û���ϸ��Ϣ
            } else if(user.getAction() == 9){
                String userID = user.getUserId();

                String sql = "SELECT * FROM �û� WHERE �û���='" + userID + "'";
                ResultSet resultSet = dataBase.query(sql);
                message = new Message();
                while (resultSet.next()) {
                    message.setGetter(resultSet.getString("��ʵ����"));
                    message.setContext(resultSet.getString("�Ա�"));
                    message.setFileName(resultSet.getString("�꼶"));
                    message.setSender(resultSet.getString("רҵ"));
                }
            }else {
                System.out.println("�û�action����");
            }
            writeObj(message);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
    }


    public Message login(User user) {
        Message message = new Message();

        System.out.println("�û���¼");
        String id = user.getUserId();
        String password = user.getPassword();
        System.out.println("UserId:" + id + ";password:" + password);

        String sql = "SELECT * FROM �û�";

        try {
            ResultSet resultSet = dataBase.query(sql);
            while (resultSet.next()) {
                if (id.equals(resultSet.getString("�û���")) && password.equals(resultSet.getString("����"))) {
                    System.out.println("�û���������ȷ");
                    message.setContext("success");

                    int num = 0;
                    sql = "SELECT * FROM ������Ϣ";//�������ڼ�����Ϣ��������Ϊmessage��fileLen
                    ResultSet resultSet1 = dataBase.query(sql);
                    while (resultSet1.next()) {
                        if (resultSet1.getString("getter").equals(id)) {
                            num++;
                        }
                    }
                    message.setFileLen(num);
                    return message;
                }
            }
            System.out.println("�û����������");
            message.setContext("fail");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return message;
        }
    }

    public Message register(User user) throws SQLException {
        Message message = new Message();
        String sql = "SELECT * FROM �û�";
        ResultSet resultSet = dataBase.query(sql);

        String userId = user.getUserId();
        String pw = user.getPassword();
        int status = user.getStatus();

        while (resultSet.next()) {
            if (user.getUserId().equals(resultSet.getString("�û���"))) {
                System.out.println("�û����Ѵ��ڣ�ע��ʧ��");
                message.setContext("RFail");
                return message;
            }
        }
        System.out.println("���ظ��û���");
        sql = "INSERT INTO �û�(�û���,����,����) VALUES('" + userId + "','" + pw + "'," + status + ")";

        dataBase.update(sql);

        sql = "UPDATE �û��� SET count=count+1";

        dataBase.update(sql);
        System.out.println("ע��ɹ�");
        message.setContext("RSuccess");
        return message;

    }

    public Message logout(User user) throws SQLException {
        Message message = new Message();
        String sql = "DELETE FROM onlineuser WHERE userId='" + user.getUserId() + "'";
        dataBase.update(sql);
        message.setContext("Log out successfully");
        return message;
    }
}
